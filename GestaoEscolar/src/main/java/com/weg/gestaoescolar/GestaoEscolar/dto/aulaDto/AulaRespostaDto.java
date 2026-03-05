package com.weg.gestaoescolar.GestaoEscolar.dto.aulaDto;

import java.time.LocalDateTime;

public record AulaRespostaDto(
        int id,
        String nomeTurma,
        LocalDateTime data_hora,
        String assunto
) {
}
