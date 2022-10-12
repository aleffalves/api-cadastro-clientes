package com.github.aleffalves.apicadastroclientes.service.impl;

import com.github.aleffalves.apicadastroclientes.mapper.ClienteMapper;
import com.github.aleffalves.apicadastroclientes.model.Cliente;
import com.github.aleffalves.apicadastroclientes.model.ClienteTelefone;
import com.github.aleffalves.apicadastroclientes.model.dto.ClienteDTO;
import com.github.aleffalves.apicadastroclientes.model.dto.ClienteTelefoneDTO;
import com.github.aleffalves.apicadastroclientes.repository.ClienteRepository;
import com.github.aleffalves.apicadastroclientes.service.ClienteService;
import com.github.aleffalves.apicadastroclientes.service.ClienteTelefoneService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final ClienteTelefoneService clienteTelefoneService;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper, ClienteTelefoneService clienteTelefoneService) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.clienteTelefoneService = clienteTelefoneService;
    }

    @Override
    public void cadastrar(ClienteDTO clienteDTO) {

        Cliente clienteAtivo = clienteRepository.buscarClientePorCpfCnpjEAtivo(clienteDTO.getCpfCnpj());
        if(clienteAtivo != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cpf/Cnpj já cadastrado!");
        }

        try {
            List<ClienteTelefone> clienteTelefone = new ArrayList<>();
            Cliente cliente = clienteRepository.saveAndFlush(clienteMapper.toEntity(clienteDTO));

            for(ClienteTelefoneDTO telefone : clienteDTO.getTelefones()){
                ClienteTelefone tel = new ClienteTelefone();
                tel.setCliente(new Cliente(cliente.getId()));
                tel.setTelefone(telefone.getTelefone());
                clienteTelefone.add(tel);
            }
            clienteTelefoneService.salvarLista(clienteTelefone);

        }catch (RuntimeException e){
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar cliente!", e);
        }

    }

    @Override
    public List<ClienteDTO> buscarTodos() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            List<ClienteDTO> clienteDTOS = new ArrayList<>();

            if(clientes.isEmpty()){
                return null;
            }

            for(Cliente cliente : clientes){

                List<ClienteTelefoneDTO> telefoneDTOS = new ArrayList<>();
                ClienteDTO clienteDTO = clienteMapper.toDTO(cliente);

                for(ClienteTelefone clienteTelefone : cliente.getTelefones()){
                    ClienteTelefoneDTO clienteTelefoneDTO = new ClienteTelefoneDTO();
                    clienteTelefoneDTO.setId(clienteTelefone.getId());
                    clienteTelefoneDTO.setClienteId(clienteTelefone.getCliente().getId());
                    clienteTelefoneDTO.setTelefone(clienteTelefone.getTelefone());
                    telefoneDTOS.add(clienteTelefoneDTO);
                }
                clienteDTO.setTelefones(telefoneDTOS);
                clienteDTOS.add(clienteDTO);
            }
            return clienteDTOS;
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar clientes!");
        }
    }

    @Override
    public ClienteDTO atualizarCliente(ClienteDTO clienteDTO, Integer id) {
        try {
            Cliente cliente = clienteRepository.findById(id).get();

            cliente.setNome(clienteDTO.getNome());
            cliente.setTipo(clienteDTO.getTipo());
            cliente.setCpfCnpj(clienteDTO.getCpfCnpj());
            cliente.setRg(clienteDTO.getRg());
            cliente.setAtivo(clienteDTO.getAtivo());

            cliente = clienteRepository.saveAndFlush(cliente);
            return clienteMapper.toDTO(cliente);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar cliente!", e);
        }
    }

    @Override
    public void desativar(Integer id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if(cliente.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
            }
            cliente.get().setAtivo(false);
            clienteRepository.saveAndFlush(cliente.get());

        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao desativar cliente!", e);
        }
    }

    @Override
    public void excluirCliente(Integer id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
        }

        try {
            clienteTelefoneService.excluirTodosPorCliente(id);
            clienteRepository.excluir(id);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir cliente!", e);
        }
    }
}
