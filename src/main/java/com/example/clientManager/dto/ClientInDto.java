package com.example.clientManager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientInDto {
    @Schema(description = "Имя клиента")
    @NotNull
    @Length(min = 3, max = 63)
    String name;
    @Schema(description = "Список email")
    @Nullable
    List<String> email;
    @Schema(description = "Список телефонов")
    @Nullable
    List<String> phoneNumber;
}
