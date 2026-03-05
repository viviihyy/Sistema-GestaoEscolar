package com.weg.gestaoescolar.GestaoEscolar.dto.aulaDto;

import java.time.LocalDateTime;

public record AulaRequisicaoDto(
        int turma_id,
        LocalDateTime data_hora,
        String assunto
) {
}
