package com.team1.assessment.conversion.strategies;

import com.team1.assessment.conversion.ConversionStrategy;
import com.team1.assessment.SystemLog;

public class HtmlConversionStrategy implements ConversionStrategy {

    @Override
    public byte[] convert(String sourceFilePath, SystemLog systemLog) {
        systemLog.info("Iniciando conversión a HTML...");
        systemLog.info("Generando estructura HTML y CSS");

        simulateHtmlGeneration();

        systemLog.info("Conversión a HTML completada");
        return new byte[80];
    }

    @Override
    public String getFormatName() {
        return "HTML";
    }

    private void simulateHtmlGeneration() {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}