package com.weg.gestaoescolar.GestaoEscolar.controller;

import com.weg.gestaoescolar.GestaoEscolar.dto.professorDto.ProfessorRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.professorDto.ProfessorRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/GestaoEscolar")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/professores")
    public ProfessorRespostaDto cadastroProfessor(@RequestBody ProfessorRequisicaoDto professorRequisicaoDto) {
        try {
            return professorService.cadastroProfessor(professorRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/professores")
    public List<ProfessorRespostaDto> listaProfessores() {
        try {
            return professorService.listaProfessor();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/professores/{id}")
    public ProfessorRespostaDto buscaPorId(@PathVariable int id) {
        try {
            return professorService.buscaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/professores/{id}")
    public  ProfessorRespostaDto atualizaProfessor(@RequestBody ProfessorRequisicaoDto professorRequisicaoDto, @PathVariable int id) {
        try {
            return professorService.atualizaProfessor(professorRequisicaoDto, id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/professores/{id}")
    public boolean deletaProfessor(@PathVariable int id) {
        try {
            return professorService.deletaProfessor(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
