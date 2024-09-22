package br.com.mendes_matheus.cinema_amenic.controller;

import br.com.mendes_matheus.cinema_amenic.entity.sala.dto.SalaRequestDTO;
import br.com.mendes_matheus.cinema_amenic.entity.sala.dto.SalaResponseDTO;
import br.com.mendes_matheus.cinema_amenic.entity.sala.model.Sala;
import br.com.mendes_matheus.cinema_amenic.entity.sala.service.SalaService;
import br.com.mendes_matheus.cinema_amenic.infraestructure.util.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sala")
@RequiredArgsConstructor
public class SalaController {
    private final SalaService salaService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping
    public ResponseEntity<SalaResponseDTO> save(@RequestBody @Valid SalaRequestDTO request) {

        Sala sala = objectMapperUtil.map(request, Sala.class);
        Sala salaSaved = this.salaService.save(sala);
        SalaResponseDTO response = objectMapperUtil.map(salaSaved, SalaResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
