/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.webservice.servicelayer.dao.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ch.ethz.seb.sebserver.gbl.api.EntityType;
import ch.ethz.seb.sebserver.gbl.model.EntityKey;
import ch.ethz.seb.sebserver.gbl.model.sebconfig.View;
import ch.ethz.seb.sebserver.gbl.profile.WebServiceProfile;
import ch.ethz.seb.sebserver.gbl.util.Result;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.ViewRecordDynamicSqlSupport;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.ViewRecordMapper;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.model.ViewRecord;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.DAOLoggingSupport;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.FilterMap;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.ResourceNotFoundException;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.TransactionHandler;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.ViewDAO;

@Lazy
@Component
@WebServiceProfile
public class ViewDAOImpl implements ViewDAO {

    private final ViewRecordMapper viewRecordMapper;

    protected ViewDAOImpl(final ViewRecordMapper viewRecordMapper) {
        this.viewRecordMapper = viewRecordMapper;
    }

    @Override
    public EntityType entityType() {
        return EntityType.VIEW;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<View> byPK(final Long id) {
        return recordById(id)
                .flatMap(ViewDAOImpl::toDomainModel);
    }

    @Override
    @Transactional(readOnly = true)
    public Result<Collection<View>> allOf(final Set<Long> pks) {
        return Result.tryCatch(() -> {
            return this.viewRecordMapper.selectByExample()
                    .where(ViewRecordDynamicSqlSupport.id, isIn(new ArrayList<>(pks)))
                    .build()
                    .execute()
                    .stream()
                    .map(ViewDAOImpl::toDomainModel)
                    .flatMap(DAOLoggingSupport::logUnexpectedErrorAndSkip)
                    .collect(Collectors.toList());
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Result<Collection<View>> allMatching(
            final FilterMap filterMap,
            final Predicate<View> predicate) {

        return Result.tryCatch(() -> this.viewRecordMapper
                .selectByExample()
                .where(
                        ViewRecordDynamicSqlSupport.name,
                        SqlBuilder.isEqualToWhenPresent(filterMap.getName()))
                .build()
                .execute()
                .stream()
                .map(ViewDAOImpl::toDomainModel)
                .flatMap(DAOLoggingSupport::logUnexpectedErrorAndSkip)
                .filter(predicate)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public Result<View> createNew(final View data) {
        return Result.tryCatch(() -> {

            final ViewRecord newRecord = new ViewRecord(
                    null,
                    data.name,
                    data.position);

            this.viewRecordMapper.insert(newRecord);
            return newRecord;
        })
                .flatMap(ViewDAOImpl::toDomainModel)
                .onError(TransactionHandler::rollback);
    }

    @Override
    @Transactional
    public Result<View> save(final View data) {
        return Result.tryCatch(() -> {

            final ViewRecord newRecord = new ViewRecord(
                    data.id,
                    data.name,
                    data.position);

            this.viewRecordMapper.updateByPrimaryKeySelective(newRecord);
            return this.viewRecordMapper.selectByPrimaryKey(data.id);
        })
                .flatMap(ViewDAOImpl::toDomainModel)
                .onError(TransactionHandler::rollback);
    }

    @Override
    @Transactional
    public Result<Collection<EntityKey>> delete(final Set<EntityKey> all) {
        return Result.tryCatch(() -> {

            final List<Long> ids = extractListOfPKs(all);

            this.viewRecordMapper.deleteByExample()
                    .where(ViewRecordDynamicSqlSupport.id, isIn(ids))
                    .build()
                    .execute();

            return ids.stream()
                    .map(id -> new EntityKey(id, EntityType.VIEW))
                    .collect(Collectors.toList());
        });
    }

    private Result<ViewRecord> recordById(final Long id) {
        return Result.tryCatch(() -> {
            final ViewRecord record = this.viewRecordMapper.selectByPrimaryKey(id);
            if (record == null) {
                throw new ResourceNotFoundException(
                        EntityType.VIEW,
                        String.valueOf(id));
            }
            return record;
        });
    }

    private static Result<View> toDomainModel(final ViewRecord record) {
        return Result.tryCatch(() -> new View(
                record.getId(),
                record.getName(),
                record.getPosition()));
    }

}
