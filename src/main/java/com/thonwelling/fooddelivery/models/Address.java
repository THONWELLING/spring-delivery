package com.thonwelling.fooddelivery.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
/**
 * Indica que esta classe é parte de uma entidade e não uma entidade em si. E todas as propriedades dessa classe são incorporadas nas tabelas das entidades que incorporam essas classes.
 * Em outras Palavras todas as entidades que incorporarem esta classe terão essas propriedades dentro de suas tabelas.
 * Por exemplo se tivermos esta classe incorporada na entidade restaurante, a tabela restaurante terão todas essas colunas que aqui são propriedades.
 * @author Thonwelling
 * */
public class Address implements Serializable {

  @Column(nullable = false, length = 8)
  private String zipCode;
  @Column( nullable = false, length = 60)
  private String street;
  @Column( nullable = false, length = 6)
  private String number;
  @Column(length = 50)
  private String complement;
  @Column(length = 25)
  private String neighborhood;

  @ManyToOne(fetch = FetchType.LAZY )
  @JoinColumn(name = "address_city_id")
  private City city;

}
