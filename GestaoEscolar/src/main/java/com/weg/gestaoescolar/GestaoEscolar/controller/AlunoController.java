package com.weg.gestaoescolar.GestaoEscolar.controller;

import com.weg.gestaoescolar.GestaoEscolar.dto.alunoDto.AlunoRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.alunoDto.AlunoRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/GestaoEscolar")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/alunos")
    public AlunoRespostaDto cadastroAluno (@Valid @RequestBody AlunoRequisicaoDto alunoRequisicaoDto) {

        try {
            return alunoService.cadastroAluno(alunoRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/alunos")
    public List<AlunoRespostaDto> listaAlunos() {
        try {
            return alunoService.listaAlunos();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/alunos/{id}")
    public AlunoRespostaDto buscaPorId(@PathVariable int id) {
        try {
            return alunoService.buscaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/alunos/{id}")
    public AlunoRespostaDto atualizaAluno(@PathVariable int id, @RequestBody AlunoRequisicaoDto alunoRequisicaoDto) {
        try {
            return alunoService.atualizaAluno(id, alunoRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/alunos/{id}")
    public boolean deletaAluno(@PathVariable int id) {
        try {
            return alunoService.deletaAluno(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/turmas/{id}/alunos")
    public List<AlunoRespostaDto> listarAlunosPorTurma(@PathVariable int id) {
        try {
            return alunoService.listarAlunosPorTurma(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
