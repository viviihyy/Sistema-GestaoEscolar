package com.weg.gestaoescolar.GestaoEscolar.dto.notaDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NotaRequisicaoDto(
        @NotNull(message = "O ID do aluno não pode ser nulo")
        int alunoId,
        @NotNull(message = "O ID da aula não pode ser nulo")
        int aulaId,
        @Positive(message = "O valor deve ser maior que zero")
        double valor
) {
}