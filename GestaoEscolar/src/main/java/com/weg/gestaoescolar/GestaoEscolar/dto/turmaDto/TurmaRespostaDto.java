package com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto;

import java.util.List;

public record TurmaRespostaDto(
        int id,
        String nome,
        String nomeCurso,
        String nomeProfessor,
        List<String> listaAlunos
) {
}
