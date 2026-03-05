package com.weg.gestaoescolar.GestaoEscolar.controller;

import com.weg.gestaoescolar.GestaoEscolar.dto.cursoDto.CursoRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.cursoDto.CursoRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/GestaoEscolar")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping("/cursos")
    public CursoRespostaDto cadastroCurso(@RequestBody CursoRequisicaoDto cursoRequisicaoDto) {
        try {
            return cursoService.cadastroCurso(cursoRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/cursos")
    public List<CursoRespostaDto> listaCurso() {
        try {
            return cursoService.listaCurso();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/cursos/{id}")
    public CursoRespostaDto buscaPorId(@PathVariable int id) {
        try {
            return cursoService.buscaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/cursos/{id}")
    public CursoRespostaDto atualizaCurso(@RequestBody CursoRequisicaoDto cursoRequisicaoDto, @PathVariable int id) {
        try {
            return cursoService.atualizaCurso(cursoRequisicaoDto, id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/cursos/{id}")
    public boolean deletaCurso(@PathVariable int id) {

        try {
            return cursoService.deletaCurso(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
