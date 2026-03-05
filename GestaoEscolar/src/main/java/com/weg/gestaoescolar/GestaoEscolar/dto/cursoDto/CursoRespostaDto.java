package com.weg.gestaoescolar.GestaoEscolar.dto.cursoDto;

import java.util.List;

public record CursoRespostaDto(
        int id,
        String nome,
        String codigo,
        List<String> listaProfessores
) {
}
