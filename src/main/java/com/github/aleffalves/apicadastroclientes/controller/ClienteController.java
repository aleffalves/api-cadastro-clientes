package com.github.aleffalves.apicadastroclientes.controller;

import com.github.aleffalves.apicadastroclientes.model.dto.ClienteDTO;
import com.github.aleffalves.apicadastroclientes.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping()
    public ResponseEntity<?> cadastrar(@RequestBody ClienteDTO cliente){
        clienteService.cadastrar(cliente);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente cadastrado com sucesso!");
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ClienteDTO>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarTodos());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizarCliente(clienteDTO,id));
    }

    @GetMapping("/desativar/{id}")
    public ResponseEntity<?> desativarCliente(@PathVariable("id") Integer id){
        clienteService.desativar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente desativado com sucesso!");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirCliente(@PathVariable("id") Integer id){
        clienteService.excluirCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluido com sucesso!");
    }

}
