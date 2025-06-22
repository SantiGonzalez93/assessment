package com.team1.assessment;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DocumentFile {
    private final byte[] content;
    private final String fileName;
    private final String format;
    private final String userEmail;
    private final long size;

    public DocumentFile(byte[] content, String fileName, String format, String userEmail) {
        this.content = content;
        this.fileName = fileName;
        this.format = format;
        this.userEmail = userEmail;
        this.size = content.length;
    }
}
