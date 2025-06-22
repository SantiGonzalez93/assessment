package com.team1.assessment.conversion.strategies;

import com.team1.assessment.conversion.ConversionStrategy;
import com.team1.assessment.SystemLog;

public class XmlConversionStrategy implements ConversionStrategy {

    @Override
    public byte[] convert(String sourceFilePath, SystemLog systemLog) {
        systemLog.info("Iniciando conversión a XML...");
        systemLog.info("Estructurando datos en formato XML");

        simulateXmlProcessing();

        systemLog.info("Conversión a XML completada");
        return new byte[110];
    }

    @Override
    public String getFormatName() {
        return "XML";
    }

    private void simulateXmlProcessing() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
