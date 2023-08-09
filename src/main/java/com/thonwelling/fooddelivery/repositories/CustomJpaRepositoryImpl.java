package com.thonwelling.fooddelivery.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T,ID>{

  private EntityManager manager;

  public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.manager = entityManager;
  }

  @Override
  public Optional<T> FindFirst() {
    var jpql = "from " + getDomainClass() //GetDomainClass retorna a classe dominante, ou seja, se estiver usando na CLASSE Restaurant será Restaurant se estiver usando Kitchen vai ser Kitchen e etc...
        .getName();
    T entity = manager.createQuery(jpql, getDomainClass())
        .setMaxResults(1) // vai limitar a consulta sql em apenas 1 linha ou 1 resultado
        .getSingleResult();
    return Optional.ofNullable(entity);
  }
}
