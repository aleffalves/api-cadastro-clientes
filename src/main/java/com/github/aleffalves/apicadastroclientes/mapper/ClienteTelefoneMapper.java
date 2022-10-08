package com.github.aleffalves.apicadastroclientes.mapper;

import com.github.aleffalves.apicadastroclientes.model.ClienteTelefone;
import com.github.aleffalves.apicadastroclientes.model.dto.ClienteTelefoneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteTelefoneMapper {

    @Mapping(source = "clienteId", target = "cliente.id")
    ClienteTelefone toEntity(ClienteTelefoneDTO dto);
    @Mapping(source = "cliente.id", target = "clienteId")
    ClienteTelefoneDTO toDTO(ClienteTelefone entity);

    @Mapping(source = "clienteId", target = "cliente.id")
    List<ClienteTelefone> toEntity(List<ClienteTelefoneDTO> dto);
    @Mapping(source = "cliente.id", target = "clienteId")
    List<ClienteTelefoneDTO> toDTO(List<ClienteTelefone> entity);

}
