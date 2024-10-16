package com.example.clientManager;



import com.example.clientManager.dto.ClientDto;
import com.example.clientManager.dto.ClientInDto;
import com.example.clientManager.dto.ContactsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    ResponseEntity<ClientDto> getClient(UUID id);

    ResponseEntity<List<ClientDto>> getClients(int from, int size);

    ResponseEntity<ClientDto> createClient(ClientInDto user);

    ResponseEntity<ClientDto> addPhone(UUID id, String phone);

    ResponseEntity<ClientDto> addEmail(UUID id,String email);

    ResponseEntity<ContactsDto> getContacts(UUID id);

    ResponseEntity<List<String>> getPhoneOrEmail(UUID id, String type);


}
