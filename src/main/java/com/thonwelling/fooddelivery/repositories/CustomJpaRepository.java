package com.thonwelling.fooddelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * O código abaixo define uma interface chamada CustomJpaRepository que estende a interface JpaRepository do Spring Data JPA.
 * A interface JpaRepository fornece um conjunto de métodos básicos para acessar e manipular dados de uma entidade JPA.
 * A interface CustomJpaRepository adiciona um método chamado FindFirst(), que retorna um Optional da primeira entidade da tabela.
 * O atributo @NoRepositoryBean na interface CustomJpaRepository, indica ao Spring Data que não deve criar um bean para esta interface.
 * Isso significa que a interface CustomJpaRepository só pode ser usada como um supertipo para outras interfaces de repositório.
 * O método FindFirst() é um exemplo de um método personalizado que pode ser adicionado a uma interface de repositório do Spring Data JPA.
 * Métodos personalizados podem ser usados para adicionar funcionalidade adicional aos repositórios.
 * Em resumo, o código abaixo define uma interface de repositório personalizada que pode ser usada para retornar a primeira entidade da tabela.
 *@author Thonwelling
 * */

@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {
  Optional<T> FindFirst();
}
