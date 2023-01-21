package com.app.notes.dto;

import javax.validation.constraints.NotBlank;

public class NoteDto {
    private Long id;

    @NotBlank(message = "Required field")
    private String description;
    private Long userId;

    public NoteDto() {}

    public NoteDto(Long id, String description, Long userId) {
        this.id = id;
        this.description = description;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
