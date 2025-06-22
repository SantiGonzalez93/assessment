package com.team1.assessment.validation.handlers;

import com.team1.assessment.validation.ValidationHandler;
import com.team1.assessment.validation.ValidationResult;
import com.team1.assessment.DocumentJob;
import com.team1.assessment.ConfigurationManager;
import com.team1.assessment.SystemLog;
import java.io.File;

public class FileSizeHandler extends ValidationHandler {

    @Override
    protected ValidationResult doValidate(DocumentJob job, ConfigurationManager config, SystemLog log) {
        log.info("📏 Verificando tamaño del archivo...");

        String filePath = job.getSourceFilePath();
        File file = new File(filePath);

        long fileSize = file.length();
        long maxSize = config.getMaxFileSize();

        log.info("📊 Tamaño del archivo: " + fileSize + " bytes");
        log.info("📊 Tamaño máximo permitido: " + maxSize + " bytes");

        if (fileSize > maxSize) {
            return ValidationResult.failure(
                    String.format("El archivo es demasiado grande. Tamaño: %d bytes, Máximo: %d bytes",
                            fileSize, maxSize)
            );
        }

        if (fileSize == 0) {
            return ValidationResult.failure("El archivo está vacío");
        }

        log.info("✅ Tamaño de archivo válido");
        return ValidationResult.success("Tamaño de archivo dentro de los límites");
    }

    @Override
    protected String getValidationName() {
        return "Validación de Tamaño de Archivo";
    }
}