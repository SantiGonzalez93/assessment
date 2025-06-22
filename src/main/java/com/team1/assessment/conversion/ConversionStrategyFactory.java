package com.team1.assessment.conversion;

import com.team1.assessment.conversion.strategies.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConversionStrategyFactory {

    private static final Map<String, ConversionStrategy> strategies = new HashMap<>();

    static {
        strategies.put("PDF", new PdfConversionStrategy());
        strategies.put("DOCX", new DocxConversionStrategy());
        strategies.put("TXT", new TxtConversionStrategy());
        strategies.put("HTML", new HtmlConversionStrategy());
        strategies.put("XML", new XmlConversionStrategy());
    }

    public static ConversionStrategy getStrategy(String format) {

        if (format == null) {
            throw new IllegalArgumentException("El formato no puede ser null");
        }
        ConversionStrategy strategy = strategies.get(format.toUpperCase());

        if (strategy == null) {
            throw new IllegalArgumentException("No hay estrategia disponible para el formato: " + format);
        }
        return strategy;
    }

    public static void registerStrategy(String format, ConversionStrategy strategy) {

        if (format == null || strategy == null) {
            throw new IllegalArgumentException("Formato y estrategia no pueden ser null");
        }
        strategies.put(format.toUpperCase(), strategy);
    }

    public static Set<String> getSupportedFormats() {
        return strategies.keySet();
    }
}