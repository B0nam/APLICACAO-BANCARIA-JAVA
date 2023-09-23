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

    public ContaBancaria(Integer iNumeroDaConta, TipoConta tcConta, Double dValorEmConta)
    {
        this.iNumeroDaConta = iNumeroDaConta;
        this.ldDataDeCriacao = LocalDate.now();
        this.tcConta = tcConta;
        this.dValorEmConta = dValorEmConta;
        this.rolOperacoes = new ArrayList<>();
        if (dValorEmConta < tcConta.getValorAbertura())
        {
            throw new IllegalArgumentException("O valor em conta é menor do que o valor de abertura permitido.");
        }
    }

/*    public void Depositar(Double dValor)
    {
        this.dValorEmConta = dValorEmConta + dValor;
        this.rolOperacoes.add(new RegistroDeOperacao(TipoOperacao.DEPOSITO, dValor));
    }

    public void Pagamento(Double dValor)
    {
        this.dValorEmConta = dValorEmConta - dValor;
        this.rolOperacoes.add(new RegistroDeOperacao(TipoOperacao.PAGAMENTO, dValor));
    }


    public void PagamentoFinanciamento(Double dValor, Integer iNumeroDeParcelas)
    {
        this.dValorEmConta = dValorEmConta - dValor;
        for (int x = 0; x < iNumeroDeParcelas; x++)
        {
            this.dlParcelasDoFinanciamento.remove(dlParcelasDoFinanciamento.size() - 1);
            this.rolOperacoes.add(new RegistroDeOperacao(TipoOperacao.PAGAMENTOFINANCIAMENTO, dValor));
        }
    }

    public Double Saldo()
    {
        return dValorEmConta;
    }

    public void ExtratoPorPeriodo(LocalDate ldInicio, LocalDate ldFim)
    {
        for ( RegistroDeOperacao operacao : this.rolOperacoes)
        {
            if (operacao.getData().isAfter(ldInicio) && operacao.getData().isBefore(ldFim))
            {
                System.out.println("Data: " + operacao.getData());
                System.out.println("Tipo: " + operacao.getOperacao());
                System.out.println("Valor " + operacao.getValor());
            }
        }
    }

    public void Financiamento(Double dValor, Integer iNumeroDeParcelas)
    {
        this.dValorEmConta = dValorEmConta + dValor;
        for (int x = 0; x < iNumeroDeParcelas; x++)
        {
            this.dlParcelasDoFinanciamento.add(dValor/(double)iNumeroDeParcelas);
        }
    }

    public void Aplicacao(Double dValor, Double dValorRetorno)
    {
        this.rolOperacoes.add(new RegistroDeOperacao(TipoOperacao.APLICACAO, dValor));
        this.Pagamento(dValor);
        this.Depositar(dValor * dValorRetorno);
    }
*/
}