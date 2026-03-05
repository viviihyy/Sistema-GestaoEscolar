package com.weg.gestaoescolar.GestaoEscolar.service;

import com.weg.gestaoescolar.GestaoEscolar.dto.aulaDto.AulaRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.aulaDto.AulaRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.mapper.AulaMapper;
import com.weg.gestaoescolar.GestaoEscolar.model.Aula;
import com.weg.gestaoescolar.GestaoEscolar.repository.AulaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AulaService {

    private final AulaMapper aulaMapper;
    private final AulaRepository aulaRepository;

    public AulaService(AulaMapper aulaMapper, AulaRepository aulaRepository) {
        this.aulaMapper = aulaMapper;
        this.aulaRepository = aulaRepository;
    }

    public AulaRespostaDto cadastroAula(AulaRequisicaoDto aulaRequisicaoDto) throws SQLException {
        Aula aula = aulaMapper.paraEntidade(aulaRequisicaoDto);
        Aula cadastro = aulaRepository.cadastroAula(aula);
        return aulaMapper.paraResposta(cadastro);
    }

    public List<AulaRespostaDto> listaAula() throws SQLException {
        List<Aula> aulas = aulaRepository.listarAula();
        return aulaMapper.paraListaResposta(aulas);
    }

    public AulaRespostaDto buscaPorId(int id) throws SQLException {
        Aula aula = aulaRepository.buscaPorId(id);

        if (aula == null) {
            throw new RuntimeException("Aula não encontrada");
        }
        return aulaMapper.paraResposta(aula);
    }

    public AulaRespostaDto atualizaAula(int id, AulaRequisicaoDto aulaRequisicaoDto) throws SQLException {
        Aula aula = aulaMapper.paraEntidade(aulaRequisicaoDto);
        aula.setId(id);
        aulaRepository.atualizaAula(aula);
        return aulaMapper.paraResposta(aula);
    }

    public boolean deletaAula(int id) throws SQLException {
        return aulaRepository.deletaAula(id);
    }

}
