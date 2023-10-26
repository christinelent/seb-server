/*
 * Copyright (c) 2022 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.webservice.servicelayer.exam.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import ch.ethz.seb.sebserver.gbl.api.EntityType;
import ch.ethz.seb.sebserver.gbl.model.EntityKey;
import ch.ethz.seb.sebserver.gbl.model.exam.Exam;
import ch.ethz.seb.sebserver.gbl.model.exam.ProctoringServiceSettings;
import ch.ethz.seb.sebserver.gbl.model.exam.ProctoringServiceSettings.ProctoringServerType;
import ch.ethz.seb.sebserver.gbl.model.exam.ScreenProctoringSettings;
import ch.ethz.seb.sebserver.gbl.profile.WebServiceProfile;
import ch.ethz.seb.sebserver.gbl.util.Result;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.ProctoringSettingsDAO;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.impl.ProctoringSettingsDAOImpl;
import ch.ethz.seb.sebserver.webservice.servicelayer.exam.ProctoringAdminService;
import ch.ethz.seb.sebserver.webservice.servicelayer.session.RemoteProctoringService;
import ch.ethz.seb.sebserver.webservice.servicelayer.session.ScreenProctoringService;
import ch.ethz.seb.sebserver.webservice.servicelayer.session.impl.ExamSessionCacheService;
import ch.ethz.seb.sebserver.webservice.servicelayer.session.impl.proctoring.RemoteProctoringServiceFactory;

@Lazy
@Service
@WebServiceProfile
public class ProctoringAdminServiceImpl implements ProctoringAdminService {

    private final ProctoringSettingsDAO proctoringSettingsDAO;
    private final RemoteProctoringServiceFactory remoteProctoringServiceFactory;
    private final ScreenProctoringService screenProctoringService;
    private final ExamSessionCacheService examSessionCacheService;

    public ProctoringAdminServiceImpl(
            final ProctoringSettingsDAOImpl proctoringSettingsDAO,
            final RemoteProctoringServiceFactory remoteProctoringServiceFactory,
            final ScreenProctoringService screenProctoringService,
            final ExamSessionCacheService examSessionCacheService) {

        this.proctoringSettingsDAO = proctoringSettingsDAO;
        this.remoteProctoringServiceFactory = remoteProctoringServiceFactory;
        this.screenProctoringService = screenProctoringService;
        this.examSessionCacheService = examSessionCacheService;
    }

    @Override
    public Result<ProctoringServiceSettings> getProctoringSettings(final EntityKey parentEntityKey) {
        return Result.tryCatch(() -> {
            checkType(parentEntityKey);
            return this.proctoringSettingsDAO
                    .getProctoringSettings(parentEntityKey)
                    .getOrThrow();

        });
    }

    @Override
    public Result<ProctoringServiceSettings> saveProctoringServiceSettings(
            final EntityKey parentEntityKey,
            final ProctoringServiceSettings proctoringServiceSettings) {

        return Result.tryCatch(() -> {

            checkType(parentEntityKey);

            final ProctoringServiceSettings result = this.proctoringSettingsDAO
                    .saveProctoringServiceSettings(parentEntityKey, proctoringServiceSettings)
                    .getOrThrow();

            if (parentEntityKey.entityType == EntityType.EXAM) {
                try {
                    this.examSessionCacheService.evict(Long.parseLong(parentEntityKey.modelId));
                } catch (final Exception e) {
                    log.warn("Failed to update Exam cache:_{}", e.getMessage());
                }
            }

            return result;
        });
    }

    @Override
    public Result<ScreenProctoringSettings> getScreenProctoringSettings(final EntityKey parentEntityKey) {
        return Result.tryCatch(() -> {

            checkType(parentEntityKey);

            return this.proctoringSettingsDAO
                    .getScreenProctoringSettings(parentEntityKey)
                    .getOrThrow();
        });
    }

    @Override
    public Result<ScreenProctoringSettings> saveScreenProctoringSettings(
            final EntityKey parentEntityKey,
            final ScreenProctoringSettings screenProctoringSettings) {

        return Result.tryCatch(() -> {

            checkType(parentEntityKey);

            this.screenProctoringService
                    .testSettings(screenProctoringSettings)
                    .flatMap(settings -> this.proctoringSettingsDAO.storeScreenProctoringSettings(
                            parentEntityKey,
                            screenProctoringSettings))
                    .getOrThrow();

            if (parentEntityKey.entityType == EntityType.EXAM) {

                this.screenProctoringService
                        .applyScreenProctoingForExam(screenProctoringSettings.examId)
                        .onError(error -> this.proctoringSettingsDAO
                                .disableScreenProctoring(screenProctoringSettings.examId))
                        .getOrThrow();

                try {
                    this.examSessionCacheService.evict(Long.parseLong(parentEntityKey.modelId));
                } catch (final Exception e) {
                    log.warn("Failed to update Exam cache:_{}", e.getMessage());
                }
            }

            return screenProctoringSettings;
        });
    }

    @Override
    public Result<RemoteProctoringService> getExamProctoringService(final ProctoringServerType type) {
        return this.remoteProctoringServiceFactory
                .getExamProctoringService(type);
    }

    @Override
    public void notifyExamSaved(final Exam exam) {
        this.screenProctoringService.notifyExamSaved(exam);
    }

    private void checkType(final EntityKey parentEntityKey) {
        if (!SUPPORTED_PARENT_ENTITES.contains(parentEntityKey.entityType)) {
            throw new UnsupportedOperationException(
                    "No proctoring service settings supported for entity: " + parentEntityKey);
        }
    }

}
