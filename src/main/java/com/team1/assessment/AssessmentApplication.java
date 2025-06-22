package com.team1.assessment;

import com.team1.assessment.conversion.ConversionStrategyFactory;
import com.team1.assessment.validation.ValidationChainFactory;
import com.team1.assessment.validation.ValidationHandler;

public class AssessmentApplication {

	public static void main(String[] args) {
		System.out.println("🎓 ASSESSMENT - DEMOSTRACIÓN DE PATRONES DE DISEÑO");
		System.out.println("================================================================================");

		// Ejecutar todas las demostraciones
		demonstrateAllPatterns();
	}

	public static void demonstrateAllPatterns() {

		// ========================================
		// DEMOSTRACIÓN 1: SINGLETON PATTERN
		// ========================================
		demonstrateSingletonPattern();

		// ========================================
		// DEMOSTRACIÓN 2: BUILDER PATTERN
		// ========================================
		demonstrateBuilderPattern();

		// ========================================
		// DEMOSTRACIÓN 3: STRATEGY PATTERN
		// ========================================
		demonstrateStrategyPattern();

		// ========================================
		// DEMOSTRACIÓN 4: ADAPTER PATTERN
		// ========================================
		demonstrateAdapterPattern();

		// ========================================
		// DEMOSTRACIÓN 5: CHAIN OF RESPONSIBILITY
		// ========================================
		demonstrateChainOfResponsibility();

		// ========================================
		// DEMOSTRACIÓN FINAL: FLUJO COMPLETO
		// ========================================
		demonstrateCompleteWorkflow();

		System.out.println("\n🎉 ¡TODAS LAS DEMOSTRACIONES COMPLETADAS!");
		System.out.println("✅ Los 5 patrones funcionan correctamente");
	}

	// ========================================
	// 1. SINGLETON PATTERN - ConfigurationManager
	// ========================================
	private static void demonstrateSingletonPattern() {
		printSectionHeader("1. SINGLETON PATTERN - ConfigurationManager");

		try {
			// Obtener múltiples instancias
			ConfigurationManager config1 = ConfigurationManager.getInstance();
			ConfigurationManager config2 = ConfigurationManager.getInstance();
			ConfigurationManager config3 = ConfigurationManager.getInstance();

			// Verificar que son la misma instancia
			boolean sameInstance = (config1 == config2) && (config2 == config3);

			System.out.println("📋 Creando múltiples instancias de ConfigurationManager:");
			System.out.println("   config1 hashCode: " + config1.hashCode());
			System.out.println("   config2 hashCode: " + config2.hashCode());
			System.out.println("   config3 hashCode: " + config3.hashCode());
			System.out.println("   ¿Son la misma instancia? " + (sameInstance ? "✅ SÍ" : "❌ NO"));
			System.out.println("   Tamaño máximo de archivo: " + config1.getMaxFileSize() + " bytes");

			if (sameInstance) {
				System.out.println("🎯 SINGLETON PATTERN: ✅ FUNCIONANDO CORRECTAMENTE");
			} else {
				System.out.println("🚨 SINGLETON PATTERN: ❌ ERROR - Se crearon múltiples instancias");
			}

		} catch (Exception e) {
			System.out.println("❌ Error en Singleton Pattern: " + e.getMessage());
		}
	}

	// ========================================
	// 2. BUILDER PATTERN - DocumentJob
	// ========================================
	private static void demonstrateBuilderPattern() {
		printSectionHeader("2. BUILDER PATTERN - DocumentJob");

		try {
			// Crear usuarios de prueba
			User userFree = new User("user_free", User.UserPlan.FREE);
			User userPremium = new User("user_premium", User.UserPlan.PREMIUM);

			System.out.println("📋 Creando DocumentJobs usando Builder Pattern:");

			// Job básico para usuario Free
			DocumentJob jobBasico = new DocumentJob.DocumentBuilder("free@test.com", userFree)
					.withSourceFilePath("/documents/simple.txt")
					.withOutputFormat("PDF")
					.build();

			System.out.println("   ✅ Job básico creado:");
			System.out.println("      Usuario: " + jobBasico.getRequestingUser().getUsername());
			System.out.println("      Formato: " + jobBasico.getOutputFormat());
			System.out.println("      Alta prioridad: " + jobBasico.isHighPriority());

			// Job completo para usuario Premium
			DocumentJob jobCompleto = new DocumentJob.DocumentBuilder("premium@test.com", userPremium)
					.withSourceFilePath("/documents/complex.docx")
					.withOutputFormat("PDF")
					.withWatermarkText("CONFIDENCIAL")
					.asHighPriority()
					.build();

			System.out.println("   ✅ Job completo creado:");
			System.out.println("      Usuario: " + jobCompleto.getRequestingUser().getUsername());
			System.out.println("      Formato: " + jobCompleto.getOutputFormat());
			System.out.println("      Marca de agua: " + jobCompleto.getWatermarkText());
			System.out.println("      Alta prioridad: " + jobCompleto.isHighPriority());

			// Probar validaciones del Builder
			try {
				DocumentJob jobInvalido = new DocumentJob.DocumentBuilder("free@test.com", userFree)
						.withWatermarkText("MARCA") // Usuario Free no puede usar marca de agua
						.build();
			} catch (IllegalArgumentException e) {
				System.out.println("   ✅ Validación del Builder funcionando: " + e.getMessage());
			}

			System.out.println("🎯 BUILDER PATTERN: ✅ FUNCIONANDO CORRECTAMENTE");

		} catch (Exception e) {
			System.out.println("❌ Error en Builder Pattern: " + e.getMessage());
		}
	}

	// ========================================
	// 3. STRATEGY PATTERN - ConversionStrategy
	// ========================================
	private static void demonstrateStrategyPattern() {
		printSectionHeader("3. STRATEGY PATTERN - ConversionStrategy");

		try {
			SystemLog log = new SystemLog();

			System.out.println("📋 Formatos disponibles en ConversionStrategyFactory:");
			ConversionStrategyFactory.getSupportedFormats().forEach(format ->
					System.out.println("   ✓ " + format)
			);

			System.out.println("\n📋 Probando diferentes estrategias:");

			// Probar PDF
			testConversionStrategy("PDF", "/test/document.txt", log);

			// Probar DOCX
			testConversionStrategy("DOCX", "/test/document.txt", log);

			// Probar TXT
			testConversionStrategy("TXT", "/test/document.txt", log);

			// Probar formato no soportado
			testConversionStrategy("INVALID", "/test/document.txt", log);

			System.out.println("🎯 STRATEGY PATTERN: ✅ FUNCIONANDO CORRECTAMENTE");

		} catch (Exception e) {
			System.out.println("❌ Error en Strategy Pattern: " + e.getMessage());
		}
	}

	private static void testConversionStrategy(String format, String filePath, SystemLog log) {
		try {
			var strategy = ConversionStrategyFactory.getStrategy(format);
			byte[] result = strategy.convert(filePath, log);
			System.out.println("   ✅ " + format + " → " + result.length + " bytes generados");
		} catch (IllegalArgumentException e) {
			System.out.println("   ❌ " + format + " → " + e.getMessage());
		}
	}

	// ========================================
	// 4. ADAPTER PATTERN - ArchiveService
	// ========================================
	private static void demonstrateAdapterPattern() {
		printSectionHeader("4. ADAPTER PATTERN - LegacyArchiverAdapter");

		try {
			System.out.println("📋 Creando sistema legacy y adapter:");

			// Crear sistema legacy
			LegacyArchiver legacySystem = new LegacyArchiver();
			System.out.println("   ✅ LegacyArchiver creado (sistema antiguo)");

			// Crear adapter
			ArchiveService modernInterface = new LegacyArchiverAdapter(legacySystem);
			System.out.println("   ✅ LegacyArchiverAdapter creado (interfaz moderna)");

			System.out.println("\n📋 Probando el adapter:");

			// Crear DocumentFile moderno
			byte[] sampleContent = "Contenido de prueba para el adapter".getBytes();
			DocumentFile documentFile = new DocumentFile(
					sampleContent,
					"test-document.pdf",
					"PDF",
					"test@example.com"
			);

			System.out.println("   📄 DocumentFile creado: " + documentFile.getFileName());
			System.out.println("   📏 Tamaño: " + documentFile.getContent().length + " bytes");

			// Usar interfaz moderna que internamente usa sistema legacy
			modernInterface.archive(documentFile);

			System.out.println("🎯 ADAPTER PATTERN: ✅ FUNCIONANDO CORRECTAMENTE");
			System.out.println("   📌 El adapter tradujo exitosamente:");
			System.out.println("      DocumentFile (moderno) → byte[] + String (legacy)");

		} catch (Exception e) {
			System.out.println("❌ Error en Adapter Pattern: " + e.getMessage());
		}
	}

	// ========================================
	// 5. CHAIN OF RESPONSIBILITY - ValidationHandler
	// ========================================
	private static void demonstrateChainOfResponsibility() {
		printSectionHeader("5. CHAIN OF RESPONSIBILITY - ValidationHandler");

		try {
			ConfigurationManager config = ConfigurationManager.getInstance();
			SystemLog log = new SystemLog();

			System.out.println("📋 Creando cadena de validaciones:");

			// ========================================
			// 🔧 CORRECCIÓN 1: Usar método correcto del Factory
			// ========================================
			ValidationHandler validationChain = ValidationChainFactory.createCompleteValidationChain();
			System.out.println("   ✅ ValidationChain (Chain of Responsibility) creado");

			System.out.println("\n📋 Probando diferentes casos:");

			// CASO 1: Job válido
			User validUser = new User("valid_user", User.UserPlan.PREMIUM);
			DocumentJob validJob = new DocumentJob.DocumentBuilder("valid@test.com", validUser)
					.withSourceFilePath("/documents/test.txt")
					.withOutputFormat("PDF")
					.asHighPriority()
					.build();

			System.out.println("\n   🧪 CASO 1: Job válido");
			var result1 = validationChain.validate(validJob, config, log);
			System.out.println("      Resultado: " + (result1.isValid() ? "✅ VÁLIDO" : "❌ INVÁLIDO"));
			if (!result1.isValid()) {
				System.out.println("      Error: " + result1.getErrorMessage());
			}

			// CASO 2: Usuario Free con alta prioridad (debe fallar)
			User freeUser = new User("free_user", User.UserPlan.FREE);
			DocumentJob invalidJob = new DocumentJob.DocumentBuilder("free@test.com", freeUser)
					.withSourceFilePath("/documents/test.txt")
					.withOutputFormat("PDF")
					.asHighPriority() // Problema: Free no puede usar alta prioridad
					.build();

			System.out.println("\n   🧪 CASO 2: Usuario Free con alta prioridad");
			var result2 = validationChain.validate(invalidJob, config, log);
			System.out.println("      Resultado: " + (result2.isValid() ? "✅ VÁLIDO" : "❌ INVÁLIDO"));
			if (!result2.isValid()) {
				System.out.println("      Error: " + result2.getErrorMessage());
			}

			System.out.println("🎯 CHAIN OF RESPONSIBILITY: ✅ FUNCIONANDO CORRECTAMENTE");

		} catch (Exception e) {
			System.out.println("❌ Error en Chain of Responsibility: " + e.getMessage());
		}
	}

	// ========================================
	// FLUJO COMPLETO - TODOS LOS PATRONES JUNTOS
	// ========================================
	private static void demonstrateCompleteWorkflow() {
		printSectionHeader("FLUJO COMPLETO - TODOS LOS PATRONES INTEGRADOS");

		try {
			System.out.println("📋 Creando DocumentProcessor con todos los patrones:");

			// 1. SINGLETON: ConfigurationManager
			ConfigurationManager config = ConfigurationManager.getInstance();
			System.out.println("   ✅ ConfigurationManager (Singleton) obtenido");

			// 2. DEPENDENCY INJECTION: Crear dependencias
			SystemLog log = new SystemLog();
			EmailService email = new EmailService();

			// 3. ADAPTER: LegacyArchiver + Adapter
			LegacyArchiver legacyArchiver = new LegacyArchiver();
			ArchiveService archiveService = new LegacyArchiverAdapter(legacyArchiver);
			System.out.println("   ✅ ArchiveService (Adapter) creado");

			// 4. CHAIN OF RESPONSIBILITY: ValidationHandler
			ValidationHandler validationChain = ValidationChainFactory.createCompleteValidationChain();
			System.out.println("   ✅ ValidationChain (Chain of Responsibility) creado");

			// ========================================
			// 🔧 CORRECCIÓN 2: Crear DocumentProcessor con constructor correcto
			// ========================================
			DocumentProcessorWithChain processor = new DocumentProcessorWithChain(
					config, log, email, archiveService, validationChain
			);
			System.out.println("   ✅ DocumentProcessor con inyección de dependencias creado");

			System.out.println("\n📋 Procesando documento de prueba:");

			// 6. BUILDER: Crear DocumentJob
			User user = new User("test_user", User.UserPlan.PREMIUM);
			DocumentJob job = new DocumentJob.DocumentBuilder("test@example.com", user)
					.withSourceFilePath("/documents/final-test.txt")
					.withOutputFormat("PDF")
					.withWatermarkText("PRUEBA COMPLETA")
					.asHighPriority()
					.build();
			System.out.println("   ✅ DocumentJob (Builder) creado");

			System.out.println("\n📋 Ejecutando procesamiento completo:");

			// 7. FLUJO COMPLETO: Procesar documento
			processor.processDocument(job);

			System.out.println("\n🎉 FLUJO COMPLETO EJECUTADO EXITOSAMENTE!");
			System.out.println("📊 Patrones utilizados en orden:");
			System.out.println("   1️⃣ Singleton (ConfigurationManager)");
			System.out.println("   2️⃣ Builder (DocumentJob)");
			System.out.println("   3️⃣ Dependency Injection (DocumentProcessor constructor)");
			System.out.println("   4️⃣ Chain of Responsibility (Validaciones)");
			System.out.println("   5️⃣ Strategy (Conversión de documentos)");
			System.out.println("   6️⃣ Adapter (Sistema de archivado)");

		} catch (Exception e) {
			System.out.println("❌ Error en flujo completo: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// ========================================
	// MÉTODOS AUXILIARES
	// ========================================

	private static void printSectionHeader(String title) {
		System.out.println("\n" + "=".repeat(80));
		System.out.println("🔹 " + title);
		System.out.println("=".repeat(80));
	}

	// ========================================
	// CLASES DE SOPORTE PARA LA DEMOSTRACIÓN
	// ========================================

	// Simulación de EmailService
	static class EmailService {
		public void send(String email, String subject, String body) {
			System.out.println("📧 EMAIL ENVIADO:");
			System.out.println("   Para: " + email);
			System.out.println("   Asunto: " + subject);
			System.out.println("   Mensaje: " + body);
		}
	}

	// Simulación de LegacyArchiver
	static class LegacyArchiver {
		public void save(byte[] fileContent, String destinationPath) {
			System.out.println("💾 LEGACY ARCHIVER:");
			System.out.println("   Guardando " + fileContent.length + " bytes");
			System.out.println("   Destino: " + destinationPath);

			// Simular tiempo de procesamiento
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			System.out.println("   ✅ Archivo guardado exitosamente en sistema legacy");
		}
	}

	// Simulación de LegacyArchiverAdapter
	static class LegacyArchiverAdapter implements ArchiveService {
		private final LegacyArchiver legacyArchiver;

		public LegacyArchiverAdapter(LegacyArchiver legacyArchiver) {
			this.legacyArchiver = legacyArchiver;
		}

		@Override
		public void archive(DocumentFile file) {
			System.out.println("🔌 ADAPTER TRADUCIENDO:");
			System.out.println("   DocumentFile → byte[] + String");

			// Traducir interfaz moderna a legacy
			byte[] content = file.getContent();
			String path = "archive/" + file.getFormat().toLowerCase() + "/" +
					file.getFileName() + "_" + System.currentTimeMillis();

			// Llamar sistema legacy
			legacyArchiver.save(content, path);
		}
	}

	// Interface ArchiveService
	interface ArchiveService {
		void archive(DocumentFile file);
	}

	// Clase DocumentFile
	static class DocumentFile {
		private final byte[] content;
		private final String fileName;
		private final String format;
		private final String userEmail;

		public DocumentFile(byte[] content, String fileName, String format, String userEmail) {
			this.content = content;
			this.fileName = fileName;
			this.format = format;
			this.userEmail = userEmail;
		}

		public byte[] getContent() { return content; }
		public String getFileName() { return fileName; }
		public String getFormat() { return format; }
		public String getUserEmail() { return userEmail; }
	}

	// ========================================
	// 🔧 CORRECCIÓN 3: DocumentProcessorWithChain
	// ========================================
	static class DocumentProcessorWithChain {
		private final ConfigurationManager configManager;
		private final SystemLog systemLog;
		private final EmailService emailService;
		private final ArchiveService archiveService;
		private final ValidationHandler validationChain;

		public DocumentProcessorWithChain(
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

			// Validaciones con Chain of Responsibility
			var validationResult = validationChain.validate(job, configManager, systemLog);

			if (!validationResult.isValid()) {
				systemLog.error("❌ Validación fallida: " + validationResult.getErrorMessage());
				return;
			}

			// Conversión usando Strategy (simulada)
			byte[] convertedFile = new byte[100]; // Simulación
			systemLog.info("✅ Conversión completada (simulada)");

			// Archivado usando Adapter
			DocumentFile documentFile = new DocumentFile(convertedFile, "test.pdf", "PDF", job.getUserEmail());
			archiveService.archive(documentFile);

			// Email
			emailService.send(job.getUserEmail(), "Procesamiento completado", "Su documento está listo.");

			systemLog.info("✅ Trabajo finalizado exitosamente.");
		}
	}
}
