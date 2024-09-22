package br.com.mendes_matheus.cinema_amenic.entity.sala.service;

import br.com.mendes_matheus.cinema_amenic.entity.sala.model.Sala;
import br.com.mendes_matheus.cinema_amenic.entity.sala.repository.SalaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaService {
    private final SalaRepository salaRepository;

    @Transactional
    public Sala save(Sala sala) {
        return this.salaRepository.save(sala);
    }
}
