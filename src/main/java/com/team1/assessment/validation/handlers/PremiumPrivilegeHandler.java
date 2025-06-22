package com.team1.assessment.validation.handlers;

import com.team1.assessment.validation.ValidationHandler;
import com.team1.assessment.validation.ValidationResult;
import com.team1.assessment.DocumentJob;
import com.team1.assessment.ConfigurationManager;
import com.team1.assessment.SystemLog;
import com.team1.assessment.User;

import static com.team1.assessment.User.UserPlan.FREE;

public class PremiumPrivilegeHandler extends ValidationHandler {

    @Override
    protected ValidationResult doValidate(DocumentJob job, ConfigurationManager config, SystemLog log) {
        log.info("🏆 Verificando privilegios Premium...");

        if (!job.isHighPriority()) {
            log.info("✅ No se requiere validación Premium (no es alta prioridad)");
            return ValidationResult.success("No requiere privilegios Premium");
        }

        User user = job.getRequestingUser();

        if (user.getPlan() == FREE) {
            return ValidationResult.failure("Solo usuarios Premium pueden usar procesamiento de alta prioridad");
        }

        log.info("✅ Usuario Premium verificado para alta prioridad");
        return ValidationResult.success("Privilegios Premium válidos");
    }

    @Override
    protected String getValidationName() {
        return "Validación de Privilegios Premium";
    }
}
