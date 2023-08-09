package com.thonwelling.fooddelivery;

import com.thonwelling.fooddelivery.repositories.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 *
 * O trecho de código @EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class),
 * é usado para informar ao Spring Data JPA que a classe CustomJpaRepositoryImpl deve ser usada como a base para todos os repositórios JPA criados.
 * Isso significa que todas as interfaces de repositório que extendem CustomJpaRepositoryImpl serão automaticamente implementadas pela Spring Data JPA.

 * Por exemplo, se você tiver uma interface de repositório chamada CustomerRepository que estende CustomJpaRepositoryImpl, a Spring Data JPA criará uma classe de implementação chamada CustomerRepositoryImpl
 * que implementa todos os métodos da interface CustomerRepository.
 *
 * Isso pode ser útil se você quiser ADICIONAR funcionalidade personalizada aos seus repositórios JPA. Por exemplo, você pode adicionar métodos personalizados à classe CustomJpaRepositoryImpl que serão disponíveis
 * em todas as suas interfaces de repositório.
 *
 * No código ABAIXO, a classe StartUp é a classe de inicialização da sua aplicação Spring Boot. A anotação @SpringBootApplication indica que a classe StartUp é uma classe de inicialização do Spring Boot.
 * A anotação @EnableJpaRepositories indica que o Spring Data JPA deve ser habilitado. O atributo repositoryBaseClass da anotação @EnableJpaRepositories é usado para indicar que a classe CustomJpaRepositoryImpl
 * deve ser usada como a BASE para todos os repositórios JPA criados.
 *
 * Em resumo, o trecho de código @EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class) é usado para informar ao Spring Data JPA que a classe CustomJpaRepositoryImpl deve ser usada como a BASE
 * para todos os repositórios JPA criados. Isso pode ser útil se você quiser adicionar funcionalidade personalizada aos seus repositórios JPA.
 *
 *@author Thonwelling
 *  */

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class StartUp {
	public static void main(String[] args) {
		SpringApplication.run(StartUp.class, args);
	}
}