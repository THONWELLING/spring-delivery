package com.thonwelling.fooddelivery.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

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
public class Address {
  @Column(name = "address_zip-code")
  private String zipCode;
  @Column(name = "address_street")
  private String street;
  @Column(name = "address_number")
  private String number;
  @Column(name = "address_complement")
  private String complement;
  @Column(name = "address_neighborhood")
  private String neighborhood;

  @ManyToOne
  @JoinColumn(name = "address_city-id")
  private City city;

}
