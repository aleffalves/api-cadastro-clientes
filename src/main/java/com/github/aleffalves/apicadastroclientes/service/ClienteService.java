package com.github.aleffalves.apicadastroclientes.service;

import com.github.aleffalves.apicadastroclientes.model.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    void cadastrar(ClienteDTO clienteDTO);

    List<ClienteDTO> buscarTodos();

    ClienteDTO atualizarCliente(ClienteDTO clienteDTO, Integer id);

    void desativar(Integer id);

    void excluirCliente(Integer id);
}
