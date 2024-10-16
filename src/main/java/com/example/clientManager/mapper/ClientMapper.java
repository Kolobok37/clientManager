package com.example.clientManager.mapper;

import com.example.clientManager.dto.ClientDto;
import com.example.clientManager.dto.ClientInDto;
import com.example.clientManager.dto.ContactsDto;
import com.example.clientManager.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toClientDto(Client client);
    List<ClientDto> toListClientDto(List<Client> client);
    Client toClient(ClientInDto clientInDto);
    ContactsDto toContactsDto(Client client);
}

