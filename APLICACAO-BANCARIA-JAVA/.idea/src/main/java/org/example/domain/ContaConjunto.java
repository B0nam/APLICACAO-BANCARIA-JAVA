package org.example.domain;

import org.example.domain.enums.TipoConta;

import java.time.LocalDate;

public class ContaConjunto extends ContaBancaria
{
    private Double dValorEmConta;
    private ContaBancaria cbConta1;
    private ContaBancaria cbConta2;


    public ContaConjunto(Integer iNumeroDaConta, TipoConta tcConta, Double dValorEmConta, ContaBancaria cbConta1, ContaBancaria cbConta2)
    {
        super(iNumeroDaConta, tcConta, dValorEmConta);
        if (cbConta1.getClass() == cbConta2.getClass())
        {
            this.cbConta1 = cbConta1;
            this.cbConta2 = cbConta2;
            this.dValorEmConta =  dValorEmConta + (cbConta1.getdValorEmConta() + cbConta2.getdValorEmConta());
        }
        else
        {
            throw new IllegalArgumentException("As contas bancarias devem ser do mesmo tipo.");
        }
    }
}