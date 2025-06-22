package com.team1.assessment;
import lombok.Data;



@Data
public class DocumentJob {
    private String sourceFilePath;
    private String outputFormat;
    private String watermarkText;
    private boolean highPriority;
    private String userEmail;
    private User requestingUser;


    public DocumentJob(DocumentBuilder builder) {
        this.sourceFilePath = builder.sourceFilePath;
        this.outputFormat = builder.outputFormat;
        this.watermarkText = builder.watermarkText;
        this.highPriority = builder.highPriority;
        this.userEmail = builder.userEmail;
        this.requestingUser = builder.requestingUser;
    }

    public static class DocumentBuilder {


        private final String userEmail;
        private final User requestingUser;

        private String sourceFilePath;
        private String outputFormat;
        private String watermarkText;
        private boolean highPriority;

        public DocumentBuilder(String userEmail, User requestingUser) {
            if (userEmail == null || userEmail.trim().isEmpty()) {
                throw new IllegalArgumentException("El email del usuario es obligatorio");
            }
            if (requestingUser == null) {
                throw new IllegalArgumentException("El usuario solicitante es obligatorio");
            }
            if (requestingUser.getUsername() == null || requestingUser.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("El username del usuario es obligatorio");
            }

            this.userEmail = userEmail;
            this.requestingUser = requestingUser;
        }

        public DocumentBuilder withSourceFilePath(String sourceFilePath) {
            this.sourceFilePath = sourceFilePath;
            return this;
        }

        public DocumentBuilder withOutputFormat(String outputFormat) {
            this.outputFormat = outputFormat;
            return this;
        }

        public DocumentBuilder withWatermarkText(String watermarkText) {
            this.watermarkText = watermarkText;
            return this;
        }

        public DocumentBuilder withHighPriority(boolean highPriority) {
            this.highPriority = highPriority;
            return this;
        }

        public DocumentBuilder asHighPriority() {
            this.highPriority = true;
            return this;
        }

        public DocumentJob build() {

            if (sourceFilePath != null && sourceFilePath.trim().isEmpty()) {
                throw new IllegalArgumentException("El path del archivo no puede estar vacío si se proporciona");
            }

            if (requestingUser.getPlan() == User.UserPlan.FREE && watermarkText != null) {
                throw new IllegalArgumentException("Solo los usuarios Premium pueden usar marca de agua personalizada");
            }

            if (requestingUser.getPlan() == User.UserPlan.FREE && highPriority) {
                throw new IllegalArgumentException("Solo los usuarios Premium pueden usar procesamiento de alta prioridad");
            }

            return new DocumentJob(this);
        }
    }

    @Override
    public String toString() {
        return "DocumentJob{" +
                "sourceFilePath='" + sourceFilePath + '\'' +
                ", outputFormat=" + outputFormat +
                ", watermarkText='" + watermarkText + '\'' +
                ", highPriority=" + highPriority +
                ", userEmail='" + userEmail + '\'' +
                ", requestingUser=" + requestingUser +
                '}';
    }


}



