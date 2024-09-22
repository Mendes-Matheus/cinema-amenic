package br.com.mendes_matheus.cinema_amenic.entity.sala.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SalaResponseDTO {

    @JsonProperty(value = "nome")
    @NotBlank(message = "O nome da sala não pode estar em branco")
    private String nome;

    @JsonProperty(value = "capacidade")
    @Positive(message = "A capacidade deve ser um número positivo")
    private int capacidade;

}
