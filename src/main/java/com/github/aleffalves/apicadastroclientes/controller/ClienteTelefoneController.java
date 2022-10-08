package com.github.aleffalves.apicadastroclientes.controller;

import com.github.aleffalves.apicadastroclientes.model.dto.ClienteTelefoneDTO;
import com.github.aleffalves.apicadastroclientes.service.ClienteTelefoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente-telefone")
public class ClienteTelefoneController {

    private final ClienteTelefoneService clienteTelefoneService;

    public ClienteTelefoneController(ClienteTelefoneService clienteTelefoneService) {
        this.clienteTelefoneService = clienteTelefoneService;
    }

    @PostMapping()
    public ResponseEntity<?> salvar(@RequestBody ClienteTelefoneDTO clienteTelefoneDTO){
        clienteTelefoneService.salvar(clienteTelefoneDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Telefone salvo com sucesso!");
    }

    @PutMapping()
    public ResponseEntity<?> atualizar(@RequestBody ClienteTelefoneDTO clienteTelefoneDTO, @PathVariable Integer id){
        clienteTelefoneService.atualizar(clienteTelefoneDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body("Telefone atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        clienteTelefoneService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Telefone deletado com sucesso!");
    }
}
