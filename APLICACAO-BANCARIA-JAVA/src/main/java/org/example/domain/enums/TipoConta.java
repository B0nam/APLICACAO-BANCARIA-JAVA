package org.example.domain.enums;

public enum TipoConta
{
    POUPANCA(50.0),
    SALARIO(0.0),
    CORRENTE(0.0);

    private Double dValorAbertura;

    TipoConta(Double dValorAbertura)
    {
        this.dValorAbertura = dValorAbertura;
    }

    public Double getValorAbertura() {
        return dValorAbertura;
    }
}
