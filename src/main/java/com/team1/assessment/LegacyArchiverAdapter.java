package com.team1.assessment;

public class LegacyArchiverAdapter implements ArchiveService {

    // 🏛️ Contiene una instancia del sistema legacy
    private final LegacyArchiver legacyArchiver;

    /**
     * Constructor que recibe el sistema legacy
     */
    public LegacyArchiverAdapter(LegacyArchiver legacyArchiver) {
        this.legacyArchiver = legacyArchiver;
    }

    /**
     * 🔄 MÉTODO ADAPTER - Traduce de la interfaz nueva a la vieja
     * <p>
     * Recibe: DocumentFile (interfaz nueva)
     * Convierte a: byte[] + String (interfaz vieja)
     * Llama al método legacy
     */
    @Override
    public void archive(DocumentFile file) {
        System.out.println("🔌 ADAPTER: Recibiendo archivo moderno: " + file.getFileName());


        byte[] content = file.getContent();
        String format = file.getFormat();
        String userEmail = file.getUserEmail();


        String destinationPath = "Ruta de destino";

        System.out.println("🔄 ADAPTER: Traduciendo a formato legacy...");
        System.out.println("   📄 Archivo: " + file.getFileName());
        System.out.println("   📏 Tamaño: " + content.length + " bytes");
        System.out.println("   📍 Destino: " + destinationPath);


        legacyArchiver.save(content, destinationPath);

        System.out.println("✅ ADAPTER: Archivado completado usando sistema legacy");
    }

}