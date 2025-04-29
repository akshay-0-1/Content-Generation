package com.akshay.contentManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentRequestDTO {

    @NotBlank(message = "Content type must not be blank")
    private String type;

    @NotBlank(message = "Input text must not be blank")
    private String input;
}
