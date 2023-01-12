package com.ramidev.socialnetwork.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentEditDto {
    @NotBlank(message = "El campo no puede estar vacio!")
    private String description;
}
