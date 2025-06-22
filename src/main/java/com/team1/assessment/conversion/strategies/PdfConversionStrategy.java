package com.team1.assessment.conversion.strategies;

import com.team1.assessment.conversion.ConversionStrategy;
import com.team1.assessment.SystemLog;

public class PdfConversionStrategy implements ConversionStrategy {

    @Override
    public byte[] convert(String sourceFilePath, SystemLog systemLog) {
        systemLog.info("Iniciando conversión a PDF...");
        systemLog.info("Aplicando configuraciones específicas de PDF");

        simulateComplexPdfProcessing();

        systemLog.info("Conversión a PDF completada exitosamente");
        return new byte[100];
    }

    @Override
    public String getFormatName() {
        return "PDF";
    }

    private void simulateComplexPdfProcessing() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}