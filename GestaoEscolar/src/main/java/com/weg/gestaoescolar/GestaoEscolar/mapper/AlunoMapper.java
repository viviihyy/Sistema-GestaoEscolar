package com.weg.gestaoescolar.GestaoEscolar.mapper;

import com.weg.gestaoescolar.GestaoEscolar.dto.alunoDto.AlunoRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.alunoDto.AlunoRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.model.Aluno;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlunoMapper {

    public Aluno paraEntidade(AlunoRequisicaoDto alunoRequisicaoDto) {
        return new Aluno(
                alunoRequisicaoDto.nome(),
                alunoRequisicaoDto.email(),
                alunoRequisicaoDto.matricula(),
                alunoRequisicaoDto.data_nascimento()
        );
    }

    public AlunoRespostaDto paraResposta(Aluno aluno) {
        return new AlunoRespostaDto(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getData_nascimento()
        );
    }

    public List<AlunoRespostaDto> paraListaResposta(List<Aluno> alunos) {
        List<AlunoRespostaDto> listaResposta = new ArrayList<>();

        for (Aluno aluno : alunos) {
            listaResposta.add(paraResposta(aluno));
        }
        return listaResposta;
    }
}
