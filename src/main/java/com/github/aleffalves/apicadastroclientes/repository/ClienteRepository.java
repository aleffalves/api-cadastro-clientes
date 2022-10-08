package com.github.aleffalves.apicadastroclientes.repository;

import com.github.aleffalves.apicadastroclientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "select * from cliente where cpf_cnpj = :cpfCnpj", nativeQuery = true)
    Cliente buscarClientePorCpfCnpjEAtivo(@Param("cpfCnpj") String cpfCnpj);

    @Query(value = "select * from cliente where ativo = true", nativeQuery = true)
    List<Cliente> buscarTodosAtivos();

    @Transactional
    @Modifying
    @Query(value = "delete from cliente where id = :id", nativeQuery = true)
    void excluir(@Param("id") Integer id);

}
