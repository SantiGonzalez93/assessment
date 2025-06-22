package com.team1.assessment.validation.handlers;

import com.team1.assessment.validation.ValidationHandler;
import com.team1.assessment.validation.ValidationResult;
import com.team1.assessment.DocumentJob;
import com.team1.assessment.ConfigurationManager;
import com.team1.assessment.SystemLog;
import java.io.File;

public class FileExistenceHandler extends ValidationHandler {

    @Override
    protected ValidationResult doValidate(DocumentJob job, ConfigurationManager config, SystemLog log) {
        log.info("🔍 Verificando existencia del archivo...");

        String filePath = job.getSourceFilePath();

        if (filePath == null || filePath.trim().isEmpty()) {
            return ValidationResult.failure("La ruta del archivo no puede estar vacía");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            return ValidationResult.failure("El archivo no existe: " + filePath);
        }

        if (!file.isFile()) {
            return ValidationResult.failure("La ruta especificada no es un archivo válido: " + filePath);
        }

        log.info("✅ Archivo encontrado: " + filePath);
        return ValidationResult.success("Archivo existe y es válido");
    }

    @Override
    protected String getValidationName() {
        return "Validación de Existencia de Archivo";
    }
}