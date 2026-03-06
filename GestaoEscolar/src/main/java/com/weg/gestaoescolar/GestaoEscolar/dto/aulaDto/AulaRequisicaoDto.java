package com.weg.gestaoescolar.GestaoEscolar.dto.aulaDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;

public record AulaRequisicaoDto(
        int turma_id,
        @NotNull(message = "A data e hora não pode estar vazia")
        @Past(message = "A data e hora deve ser no passado")
        LocalDateTime data_hora,
        @NotEmpty(message = "O assunto não pode estar vazio")
        String assunto
) {
}
