package com.weg.gestaoescolar.GestaoEscolar.controller;

import com.weg.gestaoescolar.GestaoEscolar.dto.notaDto.NotaRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.notaDto.NotaRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.service.NotaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/GestaoEscolar")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping("/notas")
    public NotaRespostaDto cadastrarNota(@RequestBody NotaRequisicaoDto dto) {

        try {
            return notaService.cadastrarNota(dto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/notas")
    public List<NotaRespostaDto> listarNotas() {

        try {
            return notaService.listarNotas();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/notas/{id}")
    public NotaRespostaDto buscarPorId(@PathVariable int id) {

        try {
            return notaService.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/notas/{id}")
    public NotaRespostaDto atualizarNota(@PathVariable int id,
                                         @RequestBody NotaRequisicaoDto dto) {

        try {
            return notaService.atualizarNota(id, dto);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/notas/{id}")
    public boolean deletarNota(@PathVariable int id) {

        try {
            return notaService.deletarNota(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}