package org.example.domain;

import org.example.domain.enums.TipoOperacao;

import java.time.LocalDate;

public class RegistroDeOperacao
{
    private LocalDate ldData;
    private TipoOperacao toOperacao;
    private Double dValor;

    public RegistroDeOperacao(TipoOperacao toOperacao, Double dValor)
    {
        this.ldData = LocalDate.now();
        this.toOperacao = toOperacao;
        this.dValor = dValor;
    }

    public LocalDate getData() {
        return ldData;
    }

    public TipoOperacao getOperacao() {
        return toOperacao;
    }

    public Double getValor() {
        return dValor;
    }
}
