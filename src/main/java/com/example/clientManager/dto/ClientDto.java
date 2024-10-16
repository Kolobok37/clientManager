package com.example.clientManager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ClientDto {
    @Schema(description = "ID клиента")
    UUID id;
    @Schema(description = "Имя клиента")
    String name;
    @Schema(description = "Список email")
    List<String> email;
    @Schema(description = "Список телефонов")
    List<String> phoneNumber;
}
