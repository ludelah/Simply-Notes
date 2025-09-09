package com.ludelah.notes.dto;


public class NoteDto {
    public Long id;
    public String title;
    public String content;
    public boolean archived;

    public NoteDto(Long id, String title, String content, boolean isArchived) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.archived = isArchived;
    }
}
