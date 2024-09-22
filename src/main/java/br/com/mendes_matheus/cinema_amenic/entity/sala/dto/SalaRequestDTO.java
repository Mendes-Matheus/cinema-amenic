package br.com.mendes_matheus.cinema_amenic.entity.sala.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalaRequestDTO {

    @NotBlank(message = "O nome da sala não pode estar em branco")
    private String nome;

    @Positive(message = "A capacidade deve ser um número positivo")
    private int capacidade;

}
