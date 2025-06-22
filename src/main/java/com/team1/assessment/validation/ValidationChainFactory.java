package com.team1.assessment.validation;

import com.team1.assessment.validation.handlers.*;

public class ValidationChainFactory {

    public static ValidationHandler createCompleteValidationChain() {
        ValidationHandler userHandler = new UserValidationHandler();
        ValidationHandler fileExistenceHandler = new FileExistenceHandler();
        ValidationHandler fileSizeHandler = new FileSizeHandler();
        ValidationHandler premiumHandler = new PremiumPrivilegeHandler();

        userHandler
                .setNext(fileExistenceHandler)
                .setNext(fileSizeHandler)
                .setNext(premiumHandler);

        return userHandler;
    }

    public static ValidationHandler createFileValidationChain() {
        ValidationHandler fileExistenceHandler = new FileExistenceHandler();
        ValidationHandler fileSizeHandler = new FileSizeHandler();

        fileExistenceHandler.setNext(fileSizeHandler);

        return fileExistenceHandler;
    }

    public static ValidationHandler createCustomChain(ValidationHandler... handlers) {
        if (handlers.length == 0) {
            throw new IllegalArgumentException("Debe proporcionar al menos un manejador");
        }

        for (int i = 0; i < handlers.length - 1; i++) {
            handlers[i].setNext(handlers[i + 1]);
        }

        return handlers[0];
    }
}