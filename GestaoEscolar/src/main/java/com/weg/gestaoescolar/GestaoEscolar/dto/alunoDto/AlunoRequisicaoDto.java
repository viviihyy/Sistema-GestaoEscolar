package com.weg.gestaoescolar.GestaoEscolar.dto.alunoDto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AlunoRequisicaoDto(
        @NotBlank(message = "O nome não pode estar vazio")
        String nome,

        @NotBlank(message = "O email não pode estar vazio")
        @Email(message = "O email informado é inválido")
        String email,

        @NotEmpty(message = "A matrícula não pode estar vazia")
        String matricula,

        @NotNull(message = "A data de nascimento não pode estar vazia")
        @Past(message = "A data de nascimento deve ser no passado")
        LocalDate data_nascimento
) {
}
