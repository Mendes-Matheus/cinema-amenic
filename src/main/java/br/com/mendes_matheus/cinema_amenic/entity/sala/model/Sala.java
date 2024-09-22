package br.com.mendes_matheus.cinema_amenic.entity.sala.model;

import br.com.mendes_matheus.cinema_amenic.infraestructure.model.persistenceentity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "sala")
@Table(name = "salas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sala extends PersistenceEntity {

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "capacidade", nullable = false)
    private int capacidade;

}
