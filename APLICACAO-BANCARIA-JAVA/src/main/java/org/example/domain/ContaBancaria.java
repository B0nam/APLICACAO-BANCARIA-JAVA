package org.example.domain;

import org.example.domain.enums.TipoConta;
import org.example.domain.enums.TipoOperacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class ContaBancaria
{
    private Integer iNumeroDaConta;
    private LocalDate ldDataDeCriacao;
    private TipoConta tcConta;
    private Double dValorEmConta;
    private ArrayList<Double> dlParcelasDoFinanciamento;
    private List<RegistroDeOperacao> rolOperacoes;

    public Double getdValorEmConta()
    {
        return dValorEmConta;
    }

    public void setdValorEmConta(Double dValorEmConta)
    {
        this.dValorEmConta = dValorEmConta;
    }

    public List<RegistroDeOperacao> getOperacoes()
    {
        return rolOperacoes;
    }

    public void addOperacao(Double dValor, TipoOperacao tipoOperacao)
    {
        this.rolOperacoes.add(new RegistroDeOperacao(tipoOperacao, dValor));
    }

    public void addParcela(Double dValorParcela)
    {
        this.dlParcelasDoFinanciamento.add(dValorParcela);
    }


    public void rmParcela()
    {
        this.dlParcelasDoFinanciamento.remove(this.dlParcelasDoFinanciamento.size() - 1);
    }

    public ArrayList<Double> getParcelasDoFinanciamento() {
        return dlParcelasDoFinanciamento;
    }

    public ContaBancaria(Integer iNumeroDaConta, TipoConta tcConta, Double dValorEmConta)
    {
        this.iNumeroDaConta = iNumeroDaConta;
        this.ldDataDeCriacao = LocalDate.now();
        this.tcConta = tcConta;
        this.dValorEmConta = dValorEmConta;
        this.rolOperacoes = new ArrayList<>();
        this.dlParcelasDoFinanciamento = new ArrayList<>();
        if (dValorEmConta < tcConta.getValorAbertura())
        {
            throw new IllegalArgumentException("O valor em conta é menor do que o valor de abertura permitido.");
        }
    }

}