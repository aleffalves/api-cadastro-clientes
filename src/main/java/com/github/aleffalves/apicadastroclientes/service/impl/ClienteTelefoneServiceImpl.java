package com.github.aleffalves.apicadastroclientes.service.impl;

import com.github.aleffalves.apicadastroclientes.mapper.ClienteTelefoneMapper;
import com.github.aleffalves.apicadastroclientes.model.ClienteTelefone;
import com.github.aleffalves.apicadastroclientes.model.dto.ClienteTelefoneDTO;
import com.github.aleffalves.apicadastroclientes.repository.ClienteTelefoneRepository;
import com.github.aleffalves.apicadastroclientes.service.ClienteTelefoneService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteTelefoneServiceImpl implements ClienteTelefoneService {

    private final ClienteTelefoneRepository clienteTelefoneRepository;
    private final ClienteTelefoneMapper clienteTelefoneMapper;

    public ClienteTelefoneServiceImpl(ClienteTelefoneRepository clienteTelefoneRepository, ClienteTelefoneMapper clienteTelefoneMapper) {
        this.clienteTelefoneRepository = clienteTelefoneRepository;
        this.clienteTelefoneMapper = clienteTelefoneMapper;
    }

    @Override
    public void salvar(ClienteTelefoneDTO telefoneDTO) {
        try {
            clienteTelefoneRepository.saveAndFlush(clienteTelefoneMapper.toEntity(telefoneDTO));
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar telefone do cliente!");
        }
    }

    @Override
    public void salvarLista(List<ClienteTelefone> telefones) {
        try {
           clienteTelefoneRepository.saveAll(telefones);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar telefone do cliente!");
        }
    }

    @Override
    public void excluirTodosPorCliente(Integer idCliente) {
        List<ClienteTelefone> clienteTelefones = clienteTelefoneRepository.buscarPorIdCliente(idCliente);
        if(clienteTelefones == null)return;
        try {
           clienteTelefoneRepository.limparTelefoneDoCliente(idCliente);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir telefones do cliente!", e);
        }
    }

    @Override
    public void atualizar(List<ClienteTelefoneDTO> telefoneDTO, Integer idCliente) {

        for(ClienteTelefoneDTO clienteTelefoneDTO : telefoneDTO) {
            if(clienteTelefoneDTO.getId() != null) {
                Optional<ClienteTelefone> clienteTelefone = clienteTelefoneRepository.findById(clienteTelefoneDTO.getId());
                if (clienteTelefone.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado!");
                }
                try {
                    clienteTelefone.get().setTelefone(clienteTelefoneDTO.getTelefone());
                    clienteTelefoneRepository.saveAndFlush(clienteTelefone.get());
                } catch (RuntimeException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao tentar atualizar telefone", e);
                }
            }else {
                clienteTelefoneDTO.setClienteId(idCliente);
                clienteTelefoneRepository.saveAndFlush(clienteTelefoneMapper.toEntity(clienteTelefoneDTO));
            }
        }
    }

    @Override
    public void deletar(Integer id) {
        Optional<ClienteTelefone> clienteTelefone = clienteTelefoneRepository.findById(id);
        if(clienteTelefone.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado!");
        }
        try {
            clienteTelefoneRepository.deletar(id);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar telefone", e);
        }
    }

}
