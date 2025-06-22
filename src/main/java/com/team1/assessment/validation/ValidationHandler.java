package com.team1.assessment.validation;

import com.team1.assessment.DocumentJob;
import com.team1.assessment.ConfigurationManager;
import com.team1.assessment.SystemLog;

public abstract class ValidationHandler {

    protected ValidationHandler nextHandler;

    public ValidationHandler setNext(ValidationHandler next) {
        this.nextHandler = next;
        return next;
    }

    public ValidationResult validate(DocumentJob job, ConfigurationManager config, SystemLog log) {
        ValidationResult result = doValidate(job, config, log);

        if (!result.isValid()) {
            log.error("❌ Validación fallida: " + result.getErrorMessage());
            return result;
        }

        if (nextHandler != null) {
            log.info("✅ " + getValidationName() + " pasó. Continuando...");
            return nextHandler.validate(job, config, log);
        }

        log.info("🎉 Todas las validaciones completadas exitosamente");
        return ValidationResult.success("Todas las validaciones pasaron");
    }

    protected abstract ValidationResult doValidate(DocumentJob job, ConfigurationManager config, SystemLog log);
    protected abstract String getValidationName();
}