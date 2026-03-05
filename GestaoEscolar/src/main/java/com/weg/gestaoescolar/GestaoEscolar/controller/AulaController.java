package com.weg.gestaoescolar.GestaoEscolar.controller;

import com.weg.gestaoescolar.GestaoEscolar.dto.aulaDto.AulaRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.aulaDto.AulaRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.service.AulaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/GestaoEscolar")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping("/aulas")
    public AulaRespostaDto cadsatroAula (@RequestBody AulaRequisicaoDto aulaRequisicaoDto) {

        try {
            return aulaService.cadastroAula(aulaRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/aulas")
    public List<AulaRespostaDto> listaAula() {
        try {
            return aulaService.listaAula();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/aulas/{id}")
    public AulaRespostaDto buscaPorId(@PathVariable int id) {
        try {
            return aulaService.buscaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/aulas/{id}")
    public AulaRespostaDto atualizaAula(@PathVariable int id, @RequestBody AulaRequisicaoDto aulaRequisicaoDto) {
        try {
            return aulaService.atualizaAula(id, aulaRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/aulas/{id}")
    public boolean deletaAula(@PathVariable int id) {
        try {
            return aulaService.deletaAula(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
