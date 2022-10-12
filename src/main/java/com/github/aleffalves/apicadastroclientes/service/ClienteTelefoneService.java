package com.github.aleffalves.apicadastroclientes.service;

import com.github.aleffalves.apicadastroclientes.model.ClienteTelefone;
import com.github.aleffalves.apicadastroclientes.model.dto.ClienteTelefoneDTO;

import java.util.List;

public interface ClienteTelefoneService {

    void salvar(ClienteTelefoneDTO telefoneDTO);

    void salvarLista(List<ClienteTelefone> telefones);

    void excluirTodosPorCliente(Integer idCliente);

    void atualizar(List<ClienteTelefoneDTO> telefone, Integer idCliente);

    void deletar(Integer id);
}
