package com.test.lifung.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDoAddRequestDto {
    /** Todo key */
    private String id;

    /** User id */
    private String userId;

    /** Title. */
    @Size(max = 70, message = "Title is too long.")
    @NotBlank
    private String title;

    /** Description. */
    private String description;

    /** At datetime. */
    @NotNull
    private LocalDateTime startTime;
}
