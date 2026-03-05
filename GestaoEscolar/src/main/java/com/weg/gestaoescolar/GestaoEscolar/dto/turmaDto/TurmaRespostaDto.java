package com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto;

public record TurmaRespostaDto(
        int id,
        String nome,
        int curso_id,
        int professor_id
) {
}
