package com.team1.assessment.conversion;

import com.team1.assessment.SystemLog;

public interface ConversionStrategy {
    byte[] convert(String sourceFilePath, SystemLog systemLog);
    String getFormatName();
}
