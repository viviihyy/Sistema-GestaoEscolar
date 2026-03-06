package com.weg.gestaoescolar.GestaoEscolar.dto.professorDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequisicaoDto(
        @NotBlank(message = "O nome não pode estar vazio")
        String nome,
        @NotBlank(message = "O email não pode estar vazio")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "A disciplina não pode estar vazia")
        String disciplina
) {
}
