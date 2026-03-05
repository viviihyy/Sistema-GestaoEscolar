package com.weg.gestaoescolar.GestaoEscolar.model;

public class Nota {

    private int id;
    private int alunoId;
    private int aulaId;
    private double valor;

    private String alunoNome;
    private String aulaAssunto;

    public Nota() {
    }

    public Nota(int id, int alunoId, int aulaId, double valor, String alunoNome, String aulaAssunto) {
        this.id = id;
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.valor = valor;
        this.alunoNome = alunoNome;
        this.aulaAssunto = aulaAssunto;
    }

    public Nota(int alunoId, int aulaId, double valor) {
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.valor = valor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAlunoId() { return alunoId; }
    public void setAlunoId(int alunoId) { this.alunoId = alunoId; }

    public int getAulaId() { return aulaId; }
    public void setAulaId(int aulaId) { this.aulaId = aulaId; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getAlunoNome() { return alunoNome; }
    public void setAlunoNome(String alunoNome) { this.alunoNome = alunoNome; }

    public String getAulaAssunto() { return aulaAssunto; }
    public void setAulaAssunto(String aulaAssunto) { this.aulaAssunto = aulaAssunto; }
}