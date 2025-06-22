package com.team1.assessment.validation.handlers;

import com.team1.assessment.validation.ValidationHandler;
import com.team1.assessment.validation.ValidationResult;
import com.team1.assessment.DocumentJob;
import com.team1.assessment.ConfigurationManager;
import com.team1.assessment.SystemLog;
import com.team1.assessment.User;

public class UserValidationHandler extends ValidationHandler {

    @Override
    protected ValidationResult doValidate(DocumentJob job, ConfigurationManager config, SystemLog log) {
        log.info("👤 Verificando datos del usuario...");

        User user = job.getRequestingUser();

        if (user == null) {
            return ValidationResult.failure("Usuario no especificado");
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return ValidationResult.failure("El username del usuario es obligatorio");
        }

        if (user.getPlan() == null) {
            return ValidationResult.failure("El plan del usuario debe estar especificado");
        }

        log.info("✅ Usuario válido: " + user.getUsername() + " (" + user.getPlan() + ")");
        return ValidationResult.success("Usuario válido");
    }

    @Override
    protected String getValidationName() {
        return "Validación de Usuario";
    }
}