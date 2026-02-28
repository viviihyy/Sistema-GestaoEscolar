package com.weg.gestaoescolar.GestaoEscolar.dto.alunoDto;

import java.time.LocalDate;

public record AlunoRequisicaoDto(
        String nome,
        String email,
        String matricula,
        LocalDate data_nascimento
) {
}
