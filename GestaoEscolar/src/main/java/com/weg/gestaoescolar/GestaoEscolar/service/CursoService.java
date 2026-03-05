package com.weg.gestaoescolar.GestaoEscolar.service;

import com.weg.gestaoescolar.GestaoEscolar.dto.cursoDto.CursoRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.cursoDto.CursoRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.mapper.CursoMapper;
import com.weg.gestaoescolar.GestaoEscolar.model.Curso;
import com.weg.gestaoescolar.GestaoEscolar.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public CursoRespostaDto cadastroCurso(CursoRequisicaoDto cursoRequisicaoDto) throws SQLException {
        Curso curso = cursoMapper.paraEntidade(cursoRequisicaoDto);
        Curso cadastro = cursoRepository.cadastroCurso(curso);
        return cursoMapper.paraResposta(cadastro);
    }

    public List<CursoRespostaDto> listaCurso() throws SQLException {
        List<Curso> cursos = cursoRepository.listarCursos();
        return cursoMapper.paraListaResposta(cursos);
    }

    public CursoRespostaDto buscaPorId(int id) throws SQLException {
        Curso curso = cursoRepository.buscaPorId(id);
        return cursoMapper.paraResposta(curso);
    }

    public CursoRespostaDto atualizaCurso(CursoRequisicaoDto cursoRequisicaoDto, int id) throws SQLException {
        Curso curso = cursoMapper.paraEntidade(cursoRequisicaoDto);
        curso.setId(id);
        cursoRepository.atualizaCurso(curso);
        return cursoMapper.paraResposta(curso);
    }

    public boolean deletaCurso(int id) throws SQLException {
        return cursoRepository.deletaCurso(id);
    }
}
