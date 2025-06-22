package com.team1.assessment.conversion.strategies;

import com.team1.assessment.conversion.ConversionStrategy;
import com.team1.assessment.SystemLog;

public class DocxConversionStrategy implements ConversionStrategy {

    @Override
    public byte[] convert(String sourceFilePath, SystemLog systemLog) {
        systemLog.info("Iniciando conversión a DOCX...");
        systemLog.info("Configurando estilos y formato DOCX");

        simulateDocxProcessing();

        systemLog.info("Conversión a DOCX completada exitosamente");
        return new byte[120];
    }

    @Override
    public String getFormatName() {
        return "DOCX";
    }

    private void simulateDocxProcessing() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}