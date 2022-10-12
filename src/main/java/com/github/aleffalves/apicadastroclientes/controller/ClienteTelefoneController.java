package com.github.aleffalves.apicadastroclientes.controller;

import com.github.aleffalves.apicadastroclientes.model.dto.ClienteTelefoneDTO;
import com.github.aleffalves.apicadastroclientes.service.ClienteTelefoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity<?> atualizar(@RequestBody List<ClienteTelefoneDTO> clienteTelefoneDTO, @PathVariable("idCliente") Integer idCliente){
        clienteTelefoneService.atualizar(clienteTelefoneDTO, idCliente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        clienteTelefoneService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
