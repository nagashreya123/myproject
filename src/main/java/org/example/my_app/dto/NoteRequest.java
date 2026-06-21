package org.example.my_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoteRequest {

    @NotBlank(message = "Cannot be empty")
    private String title;

    @NotBlank(message = "Cannot be empty")
    private String description;
}
