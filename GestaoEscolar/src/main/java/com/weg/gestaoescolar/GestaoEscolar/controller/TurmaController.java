package com.weg.gestaoescolar.GestaoEscolar.controller;

import com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto.TurmaRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto.TurmaRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/GestaoEscolar")
public class TurmaController {

private final TurmaService turmaService;

public TurmaController(TurmaService turmaService) {
    this.turmaService = turmaService;
}

@PostMapping("/turmas")
    public TurmaRespostaDto cadastroTurma(@Valid @RequestBody TurmaRequisicaoDto turmaRequisicaoDto) {
        try {
            return turmaService.cadastroTurma(turmaRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/turmas")
    public List<TurmaRespostaDto> listaTurma() {
        try {
            return turmaService.listaTurma();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/turmas/{id}")
    public TurmaRespostaDto buscaPorId(@PathVariable int id) {
        try {
            return turmaService.buscaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/turmas/{id}")
    public TurmaRespostaDto atualizaTurma(@Valid @RequestBody TurmaRequisicaoDto turmaRequisicaoDto, @PathVariable int id) {
        try {
            return turmaService.atualizaTurma(turmaRequisicaoDto, id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/turmas/{id}")
    public boolean deletaTurma(@PathVariable int id) {
        try {
            return turmaService.deletaTurma(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/cursos/{id}/turmas")
    public List<TurmaRespostaDto> listarTurmasPorCurso(@PathVariable int id) {

        try {
            return turmaService.listarTurmasPorCurso(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
