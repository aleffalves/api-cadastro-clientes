package com.github.aleffalves.apicadastroclientes.mapper;

import com.github.aleffalves.apicadastroclientes.model.Cliente;
import com.github.aleffalves.apicadastroclientes.model.dto.ClienteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteDTO dto);
    ClienteDTO toDTO(Cliente entity);

    List<Cliente> toEntity(List<ClienteDTO> dto);
    List<ClienteDTO> toDTO(List<Cliente> entity);

}
