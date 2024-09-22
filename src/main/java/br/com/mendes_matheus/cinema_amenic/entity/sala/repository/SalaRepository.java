package br.com.mendes_matheus.cinema_amenic.entity.sala.repository;

import br.com.mendes_matheus.cinema_amenic.entity.sala.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

}
