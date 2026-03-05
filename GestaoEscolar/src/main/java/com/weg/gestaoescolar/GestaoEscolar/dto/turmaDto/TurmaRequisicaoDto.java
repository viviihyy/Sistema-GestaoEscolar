package com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto;

import java.util.List;

public record TurmaRequisicaoDto(
        String nome,
        int curso_id,
        int professor_id,
        List<Integer> listaAlunosIds
) {
}
