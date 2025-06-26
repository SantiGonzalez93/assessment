package com.team1.assessment;

import com.team1.assessment.conversion.ConversionStrategy;
import com.team1.assessment.conversion.ConversionStrategyFactory;
import com.team1.assessment.validation.ValidationHandler;
import com.team1.assessment.validation.ValidationResult;
import java.io.File;

public class DocumentProcessor {

    private final ConfigurationManager configManager;
    private final SystemLog systemLog;
    private final EmailService emailService;
    private final ArchiveService archiveService;
    private final ValidationHandler validationChain;

    public DocumentProcessor(
            ConfigurationManager configManager,
            SystemLog systemLog,
            EmailService emailService,
            ArchiveService archiveService,
            ValidationHandler validationChain
    ) {
        this.configManager = configManager;
        this.systemLog = systemLog;
        this.emailService = emailService;
        this.archiveService = archiveService;
        this.validationChain = validationChain;
    }

    public void processDocument(DocumentJob job) {
        systemLog.info("🚀 Iniciando procesamiento...");

        systemLog.info("🔗 Ejecutando cadena de validaciones...");
        ValidationResult validationResult = validationChain.validate(job, configManager, systemLog);

        if (!validationResult.isValid()) {
            systemLog.error("❌ Validación fallida: " + validationResult.getErrorMessage());
            return;
        }

        systemLog.info("✅ Todas las validaciones pasaron exitosamente");

        byte[] convertedFile = convertDocumentUsingFactory(job);

        if (convertedFile == null) {
            systemLog.error("❌ Error en la conversión.");
            return;
        }

        DocumentFile documentFile = createDocumentFile(convertedFile, job);
        archiveService.archive(documentFile);

        emailService.send(job.getUserEmail(), "Procesamiento completado", "Su documento está listo.");

        triggerBilling(job.getRequestingUser(), job.getOutputFormat());
        systemLog.info("✅ Trabajo finalizado exitosamente.");
    }

    private byte[] convertDocumentUsingFactory(DocumentJob job) {
        try {
            ConversionStrategy strategy = ConversionStrategyFactory.getStrategy(job.getOutputFormat());
            return strategy.convert(job.getSourceFilePath(), systemLog);
        } catch (Exception e) {
            systemLog.error("Error en conversión: " + e.getMessage());
            return null;
        }
    }

    private DocumentFile createDocumentFile(byte[] content, DocumentJob job) {
        String fileName = extractFileName(job.getSourceFilePath());
        return new DocumentFile(content, fileName, job.getOutputFormat(), job.getUserEmail());
    }

    private String extractFileName(String filePath) {
        if (filePath == null) return "document";
        int lastSlash = filePath.lastIndexOf('/');
        return lastSlash >= 0 ? filePath.substring(lastSlash + 1) : filePath;
    }

    private void triggerBilling(User user, String format) {
        systemLog.info("💳 Facturación procesada para: " + user.getUsername());
    }
}
