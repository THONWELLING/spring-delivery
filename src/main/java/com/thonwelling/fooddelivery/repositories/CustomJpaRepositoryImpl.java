package com.thonwelling.fooddelivery.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

/**
 * O código Abaixo implementa a interface CustomJpaRepository que foi definida anteriormente.
 * A classe CustomJpaRepositoryImpl estende a classe SimpleJpaRepository, que é uma classe de suporte do Spring Data JPA que fornece implementações padrão para muitos métodos de repositório.
 * A classe CustomJpaRepositoryImpl tem um atributo chamado manager, que é uma instância da classe EntityManager do JPA.
 * O EntityManager é usado para executar consultas JPQL no banco de dados.

 * JPQL (Java Persistence Query Language) é uma linguagem de consulta orientada a objeto independente de plataforma definida como parte da especificação JPA. JPQL é usado para fazer consultas em entidades armazenadas em um banco de dados relacional. A sintaxe JPQL é semelhante à sintaxe SQL, mas é orientada a objetos.
 * JPQL permite que você escreva consultas que são mais próximas do seu modelo de dados do que as consultas SQL. Isso pode tornar suas consultas mais fáceis de entender e manter.

 * O método FindFirst() implementa o método FindFirst() da interface CustomJpaRepository. O método FindFirst() executa uma consulta JPQL que retorna a primeira entidade da tabela.
 * A consulta JPQL é:
 *
 * from " + getDomainClass()
 *         .getName();
 *
 * O método getDomainClass() retorna a classe de domínio da entidade que está sendo manipulada. No exemplo, a classe de domínio é T.(que quer dizer que vai usar da classe que está sendo manipulada)
 * ou seja, se a classe onde a interface for usada for de Restaurant então será Restaurant se for usada na classe Kitchen então será Kitchen.
 *
 * O método FindFirst() retorna um Optional da entidade encontrada. O Optional é usado para indicar que a entidade pode ser nula.
 *
 * Em resumo, o código acima implementa uma classe de repositório que pode ser usada para retornar a primeira entidade da tabela.
 * @author Thonwelling
 * */

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T,ID>{

  private EntityManager manager;

  public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.manager = entityManager;
  }

  @Override
  public Optional<T> FindFirst() {
    var jpql = "from " + getDomainClass()
        .getName();
    T entity = manager.createQuery(jpql, getDomainClass())
        .setMaxResults(1)
        .getSingleResult();
    return Optional.ofNullable(entity);
  }
}
