package com.github.aleffalves.apicadastroclientes.repository;

import com.github.aleffalves.apicadastroclientes.model.ClienteTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClienteTelefoneRepository extends JpaRepository<ClienteTelefone, Integer> {

    @Query(value = "select * from cliente_telefone where cliente_id = :idCliente", nativeQuery = true)
    List<ClienteTelefone> buscarPorIdCliente(@Param("idCliente") Integer idCliente);

    @Transactional
    @Modifying
    @Query(value = "delete from cliente_telefone where cliente_id = :idCliente", nativeQuery = true)
    void limparTelefoneDoCliente(@Param("idCliente") Integer idCliente);

    @Transactional
    @Modifying
    @Query(value = "delete from cliente_telefone where id = :id", nativeQuery = true)
    void deletar(@Param("id") Integer id);
}
