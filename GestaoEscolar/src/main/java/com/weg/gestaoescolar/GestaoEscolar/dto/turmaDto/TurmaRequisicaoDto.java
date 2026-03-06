package com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TurmaRequisicaoDto(
        @NotBlank(message = "O nome da turma não pode estar vazio")
        String nome,
        @NotNull(message = "O id do curso não pode estar vazio")
        int curso_id,
        @NotNull(message = "O id do professor não pode estar vazio")
        int professor_id,
        @NotEmpty(message = "A lista de alunos não pode estar vazia")
        List<Integer> listaAlunosIds
) {
}
