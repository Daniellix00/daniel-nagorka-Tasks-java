package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;
    public Mail(String mailTo, String subject, String message) {
        this(mailTo, subject, message, null);
    }

    // Konstruktor z opcjonalnym polem toCc
    public Mail(String mailTo, String subject, String message, String toCc) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
        this.toCc = toCc;
    }

}
