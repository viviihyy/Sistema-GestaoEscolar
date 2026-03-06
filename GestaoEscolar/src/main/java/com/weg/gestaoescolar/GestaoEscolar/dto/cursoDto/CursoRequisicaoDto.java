package com.weg.gestaoescolar.GestaoEscolar.dto.cursoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CursoRequisicaoDto(
        @NotBlank(message = "O nome do curso não pode estar vazio")
        String nome,
        @NotBlank(message = "O código do curso não pode estar vazio")
        String codigo,
        @NotEmpty(message = "A lista de professores não pode estar vazia")
        List<Integer> listaProfessorIds
) {
}
