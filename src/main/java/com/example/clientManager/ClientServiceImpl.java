package com.example.clientManager;


import com.example.clientManager.dto.ClientDto;
import com.example.clientManager.dto.ClientInDto;
import com.example.clientManager.dto.ContactsDto;
import com.example.clientManager.exception.NotFoundException;
import com.example.clientManager.exception.ValidationDataException;
import com.example.clientManager.mapper.ClientMapper;
import com.example.clientManager.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ResponseEntity<ClientDto> getClient(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
        ClientDto clientDto = clientMapper.toClientDto(client);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClientDto>> getClients(int from, int size) {
        List<Client> clients = clientRepository.findAll(Paging.paging(from, size)).toList();
        List<ClientDto> clientsDto = clientMapper.toListClientDto(clients);
        return new ResponseEntity<>(clientsDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientDto> createClient(ClientInDto clientInDto) {
        UUID id = UUID.randomUUID();
        Client newClient = clientMapper.toClient(clientInDto);
        newClient.setId(id);
        Client client = clientRepository.save(newClient);
        return new ResponseEntity<>(clientMapper.toClientDto(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientDto> addPhone(UUID id, String phone) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
        client.getPhoneNumber().add(phone);

        Client updateClient = clientRepository.save(client);
        return new ResponseEntity<>(clientMapper.toClientDto(updateClient), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientDto> addEmail(UUID id, String email) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
        client.getEmail().add(email);

        Client updateClient = clientRepository.save(client);
        return new ResponseEntity<>(clientMapper.toClientDto(updateClient), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContactsDto> getContacts(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
        return new ResponseEntity<>(clientMapper.toContactsDto(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getPhoneOrEmail(UUID id, String type) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
        if("email".equals(type)){
            return new ResponseEntity<>(client.getEmail(), HttpStatus.OK);
        }
        else if("phone".equals(type)){
            return new ResponseEntity<>(client.getPhoneNumber(), HttpStatus.OK);
        }
        else {
            throw new ValidationDataException("Type is not valid.");
        }
    }
}
