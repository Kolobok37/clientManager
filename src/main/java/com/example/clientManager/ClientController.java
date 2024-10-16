package com.example.clientManager;


import com.example.clientManager.dto.ClientDto;
import com.example.clientManager.dto.ClientInDto;
import com.example.clientManager.dto.ContactsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@Tag(name = "Менеджер клиентов")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Transactional
    @PostMapping("/client")
    @Operation(summary = "Создание задачи")
    public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientInDto client) {
        return clientService.createClient(client);
    }

    @Transactional(readOnly = true)
    @GetMapping("/client/{id}")
    @Operation(summary = "Создание задачи")
    public ResponseEntity<ClientDto> getClients(@PathVariable UUID id) {
        return clientService.getClient(id);
    }
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getClients(@RequestParam(defaultValue = "0") int from,
                                                      @RequestParam(defaultValue = "10") int size) {
        return clientService.getClients(from,size);
    }

    @Transactional(readOnly = true)
    @GetMapping("/client/{id}/contacts")
    @Operation(summary = "Создание задачи")
    public ResponseEntity<ContactsDto> getContacts(@PathVariable UUID id) {
        return clientService.getContacts(id);
    }

    @GetMapping("/client/{id}/phone-email")
    public ResponseEntity<List<String>> getPhoneOrEmail(@PathVariable UUID id, @RequestParam String type) {
        return clientService.getPhoneOrEmail(id, type);
    }

    @Transactional
    @PatchMapping("/client/{id}/phone")
    @Operation(summary = "Создание задачи")
    public ResponseEntity<ClientDto> addPhone(@PathVariable UUID id, @RequestBody String phone) {
        return clientService.addPhone(id,phone);
    }

    @Transactional
    @PatchMapping("/client/{id}/email")
    @Operation(summary = "Создание задачи")
    public ResponseEntity<ClientDto> addEmail(@PathVariable UUID id, @RequestBody String email) {
        return clientService.addEmail(id,email);
    }
}
