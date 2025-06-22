package com.team1.assessment.conversion.strategies;

import com.team1.assessment.conversion.ConversionStrategy;
import com.team1.assessment.SystemLog;

public class TxtConversionStrategy implements ConversionStrategy {

    @Override
    public byte[] convert(String sourceFilePath, SystemLog systemLog) {
        systemLog.info("Iniciando conversión a TXT...");
        systemLog.info("Extrayendo texto plano");

        simulateTextExtraction();

        systemLog.info("Conversión a TXT completada");
        return new byte[50];
    }

    @Override
    public String getFormatName() {
        return "TXT";
    }

    private void simulateTextExtraction() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}