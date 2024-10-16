package com.example.clientManager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ContactsDto {
    @Schema(description = "Список email")
    List<String> email;
    @Schema(description = "Список телефонов")
    List<String> phoneNumber;
}

