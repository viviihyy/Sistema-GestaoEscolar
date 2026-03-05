package com.weg.gestaoescolar.GestaoEscolar.service;

import com.weg.gestaoescolar.GestaoEscolar.dto.notaDto.NotaRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.notaDto.NotaRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.mapper.NotaMapper;
import com.weg.gestaoescolar.GestaoEscolar.model.Nota;
import com.weg.gestaoescolar.GestaoEscolar.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;

    public NotaService(NotaRepository notaRepository, NotaMapper notaMapper) {
        this.notaRepository = notaRepository;
        this.notaMapper = notaMapper;
    }

    public NotaRespostaDto cadastrarNota(NotaRequisicaoDto dto) throws SQLException {
        Nota nota = notaMapper.paraEntidade(dto);
        Nota cadastro = notaRepository.cadastrarNota(nota);
        Nota completa = notaRepository.buscarPorId(cadastro.getId());
        return notaMapper.paraResposta(completa);
    }

    public List<NotaRespostaDto> listarNotas() throws SQLException {
        List<Nota> notas = notaRepository.listarNotas();
        return notaMapper.paraListaResposta(notas);
    }

    public NotaRespostaDto buscarPorId(int id) throws SQLException {
        Nota nota = notaRepository.buscarPorId(id);

        if (nota == null) {
            throw new RuntimeException("Nota não encontrada");
        }

        return notaMapper.paraResposta(nota);
    }

    public NotaRespostaDto atualizarNota(int id, NotaRequisicaoDto dto) throws SQLException {
        Nota nota = notaMapper.paraEntidade(dto);
        nota.setId(id);
        notaRepository.atualizarNota(nota);
        Nota atualizada = notaRepository.buscarPorId(id);
        return notaMapper.paraResposta(atualizada);
    }

    public boolean deletarNota(int id) throws SQLException {
        return notaRepository.deletarNota(id);
    }
}