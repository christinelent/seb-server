/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.webservice.servicelayer.dao.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ch.ethz.seb.sebserver.gbl.api.API.BulkActionType;
import ch.ethz.seb.sebserver.gbl.api.EntityType;
import ch.ethz.seb.sebserver.gbl.model.EntityKey;
import ch.ethz.seb.sebserver.gbl.model.sebconfig.ExamConfiguration;
import ch.ethz.seb.sebserver.gbl.profile.WebServiceProfile;
import ch.ethz.seb.sebserver.gbl.util.Result;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.ConfigurationNodeRecordMapper;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.ExamConfigurationMapRecordDynamicSqlSupport;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.ExamConfigurationMapRecordMapper;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.ExamRecordDynamicSqlSupport;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.ExamRecordMapper;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.model.ConfigurationNodeRecord;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.model.ExamConfigurationMapRecord;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.model.ExamRecord;
import ch.ethz.seb.sebserver.webservice.servicelayer.bulkaction.BulkAction;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.DAOLoggingSupport;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.ExamConfigurationDAO;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.FilterMap;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.ResourceNotFoundException;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.TransactionHandler;

@Lazy
@Component
@WebServiceProfile
public class ExamConfigurationDAOImpl implements ExamConfigurationDAO {

    private final ExamRecordMapper examRecordMapper;
    private final ExamConfigurationMapRecordMapper examConfigurationMapRecordMapper;
    private final ConfigurationNodeRecordMapper configurationNodeRecordMapper;

    protected ExamConfigurationDAOImpl(
            final ExamRecordMapper examRecordMapper,
            final ExamConfigurationMapRecordMapper examConfigurationMapRecordMapper,
            final ConfigurationNodeRecordMapper configurationNodeRecordMapper) {

        this.examRecordMapper = examRecordMapper;
        this.examConfigurationMapRecordMapper = examConfigurationMapRecordMapper;
        this.configurationNodeRecordMapper = configurationNodeRecordMapper;
    }

    @Override
    public EntityType entityType() {
        return EntityType.EXAM_CONFIGURATION_MAP;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<ExamConfiguration> byPK(final Long id) {
        return recordById(id)
                .flatMap(ExamConfigurationDAOImpl::toDomainModel);
    }

    @Override
    @Transactional(readOnly = true)
    public Result<Collection<ExamConfiguration>> allOf(final Set<Long> pks) {
        return Result.tryCatch(() -> {
            return this.examConfigurationMapRecordMapper.selectByExample()
                    .where(ExamConfigurationMapRecordDynamicSqlSupport.id, isIn(new ArrayList<>(pks)))
                    .build()
                    .execute()
                    .stream()
                    .map(ExamConfigurationDAOImpl::toDomainModel)
                    .flatMap(DAOLoggingSupport::logUnexpectedErrorAndSkip)
                    .collect(Collectors.toList());
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Result<Collection<ExamConfiguration>> allMatching(
            final FilterMap filterMap,
            final Predicate<ExamConfiguration> predicate) {

        return Result.tryCatch(() -> this.examConfigurationMapRecordMapper
                .selectByExample()
                .where(
                        ExamConfigurationMapRecordDynamicSqlSupport.institutionId,
                        SqlBuilder.isEqualToWhenPresent(filterMap.getInstitutionId()))
                .and(
                        ExamConfigurationMapRecordDynamicSqlSupport.examId,
                        SqlBuilder.isEqualToWhenPresent(filterMap.getExamConfigExamId()))
                .and(
                        ExamConfigurationMapRecordDynamicSqlSupport.configurationNodeId,
                        SqlBuilder.isEqualToWhenPresent(filterMap.getExamConfigConfigId()))
                .build()
                .execute()
                .stream()
                .map(ExamConfigurationDAOImpl::toDomainModel)
                .flatMap(DAOLoggingSupport::logUnexpectedErrorAndSkip)
                .filter(predicate)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public Result<ExamConfiguration> createNew(final ExamConfiguration data) {
        return checkMappingIntegrity(data)
                .map(config -> {
                    final ExamConfigurationMapRecord newRecord = new ExamConfigurationMapRecord(
                            null,
                            data.institutionId,
                            data.examId,
                            data.configurationNodeId,
                            data.userNames);

                    this.examConfigurationMapRecordMapper.insert(newRecord);
                    return newRecord;
                })
                .flatMap(ExamConfigurationDAOImpl::toDomainModel)
                .onErrorDo(TransactionHandler::rollback);
    }

    @Override
    @Transactional
    public Result<ExamConfiguration> save(final ExamConfiguration data) {
        return Result.tryCatch(() -> {

            final ExamConfigurationMapRecord newRecord = new ExamConfigurationMapRecord(
                    data.id,
                    null,
                    null,
                    null,
                    data.userNames);

            this.examConfigurationMapRecordMapper.updateByPrimaryKeySelective(newRecord);
            return this.examConfigurationMapRecordMapper.selectByPrimaryKey(data.id);
        })
                .flatMap(ExamConfigurationDAOImpl::toDomainModel)
                .onErrorDo(TransactionHandler::rollback);
    }

    @Override
    @Transactional
    public Result<Collection<EntityKey>> delete(final Set<EntityKey> all) {
        return Result.tryCatch(() -> {

            final List<Long> ids = extractListOfPKs(all);

            this.examConfigurationMapRecordMapper.deleteByExample()
                    .where(ExamConfigurationMapRecordDynamicSqlSupport.id, isIn(ids))
                    .build()
                    .execute();

            return ids.stream()
                    .map(id -> new EntityKey(id, EntityType.EXAM_CONFIGURATION_MAP))
                    .collect(Collectors.toList());
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Set<EntityKey> getDependencies(final BulkAction bulkAction) {
        if (bulkAction.type == BulkActionType.ACTIVATE || bulkAction.type == BulkActionType.DEACTIVATE) {
            return Collections.emptySet();
        }

        // define the select function in case of source type
        Function<EntityKey, Result<Collection<EntityKey>>> selectionFunction;
        switch (bulkAction.sourceType) {
            case INSTITUTION:
                selectionFunction = this::allIdsOfInstitution;
                break;
            case LMS_SETUP:
                selectionFunction = this::allIdsOfLmsSetup;
                break;
            case EXAM:
                selectionFunction = this::allIdsOfExam;
                break;
            case CONFIGURATION_NODE:
                selectionFunction = this::allIdsOfConfig;
                break;
            default:
                selectionFunction = key -> Result.of(Collections.emptyList()); //empty select function
        }

        return getDependencies(bulkAction, selectionFunction);
    }

    private Result<ExamConfigurationMapRecord> recordById(final Long id) {
        return Result.tryCatch(() -> {
            final ExamConfigurationMapRecord record = this.examConfigurationMapRecordMapper
                    .selectByPrimaryKey(id);
            if (record == null) {
                throw new ResourceNotFoundException(
                        EntityType.EXAM_CONFIGURATION_MAP,
                        String.valueOf(id));
            }
            return record;
        });
    }

    private static Result<ExamConfiguration> toDomainModel(final ExamConfigurationMapRecord record) {
        return Result.tryCatch(() -> new ExamConfiguration(
                record.getId(),
                record.getInstitutionId(),
                record.getExamId(),
                record.getConfigurationNodeId(),
                record.getUserNames()));
    }

    private Result<ExamConfiguration> checkMappingIntegrity(final ExamConfiguration data) {
        return Result.tryCatch(() -> {
            final ConfigurationNodeRecord config =
                    this.configurationNodeRecordMapper.selectByPrimaryKey(data.configurationNodeId);

            if (config == null) {
                throw new ResourceNotFoundException(
                        EntityType.CONFIGURATION_NODE,
                        String.valueOf(data.configurationNodeId));
            }

            if (config.getInstitutionId().longValue() != data.institutionId.longValue()) {
                throw new IllegalArgumentException("Institutional integrity constraint violation");
            }

            final ExamRecord exam = this.examRecordMapper.selectByPrimaryKey(data.examId);

            if (exam == null) {
                throw new ResourceNotFoundException(
                        EntityType.EXAM,
                        String.valueOf(data.configurationNodeId));
            }

            if (exam.getInstitutionId().longValue() != data.institutionId.longValue()) {
                throw new IllegalArgumentException("Institutional integrity constraint violation");
            }

            return data;
        });
    }

    private Result<Collection<EntityKey>> allIdsOfInstitution(final EntityKey institutionKey) {
        return Result.tryCatch(() -> {
            return this.examConfigurationMapRecordMapper.selectIdsByExample()
                    .where(
                            ExamConfigurationMapRecordDynamicSqlSupport.institutionId,
                            isEqualTo(Long.valueOf(institutionKey.modelId)))
                    .build()
                    .execute()
                    .stream()
                    .map(id -> new EntityKey(id, EntityType.EXAM_CONFIGURATION_MAP))
                    .collect(Collectors.toList());
        });
    }

    private Result<Collection<EntityKey>> allIdsOfLmsSetup(final EntityKey lmsSetupKey) {
        return Result.tryCatch(() -> {
            return this.examConfigurationMapRecordMapper.selectIdsByExample()
                    .leftJoin(ExamRecordDynamicSqlSupport.examRecord)
                    .on(
                            ExamRecordDynamicSqlSupport.id,
                            equalTo(ExamConfigurationMapRecordDynamicSqlSupport.examId))

                    .where(
                            ExamRecordDynamicSqlSupport.lmsSetupId,
                            isEqualTo(Long.valueOf(lmsSetupKey.modelId)))
                    .build()
                    .execute()
                    .stream()
                    .map(id -> new EntityKey(id, EntityType.EXAM_CONFIGURATION_MAP))
                    .collect(Collectors.toList());
        });
    }

    private Result<Collection<EntityKey>> allIdsOfExam(final EntityKey examKey) {
        return Result.tryCatch(() -> {
            return this.examConfigurationMapRecordMapper.selectIdsByExample()
                    .where(
                            ExamConfigurationMapRecordDynamicSqlSupport.examId,
                            isEqualTo(Long.valueOf(examKey.modelId)))
                    .build()
                    .execute()
                    .stream()
                    .map(id -> new EntityKey(id, EntityType.EXAM_CONFIGURATION_MAP))
                    .collect(Collectors.toList());
        });
    }

    private Result<Collection<EntityKey>> allIdsOfConfig(final EntityKey configKey) {
        return Result.tryCatch(() -> {
            return this.examConfigurationMapRecordMapper.selectIdsByExample()
                    .where(
                            ExamConfigurationMapRecordDynamicSqlSupport.configurationNodeId,
                            isEqualTo(Long.valueOf(configKey.modelId)))
                    .build()
                    .execute()
                    .stream()
                    .map(id -> new EntityKey(id, EntityType.EXAM_CONFIGURATION_MAP))
                    .collect(Collectors.toList());
        });
    }

}
