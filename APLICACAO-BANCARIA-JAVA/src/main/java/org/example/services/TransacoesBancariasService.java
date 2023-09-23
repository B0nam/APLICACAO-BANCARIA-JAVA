package org.example.services;

import org.example.domain.ContaBancaria;
import org.example.domain.enums.TipoOperacao;

public class TransacoesBancariasService
{
    public void Deposito(ContaBancaria contaBancaria, Double dValor)
    {
        contaBancaria.setdValorEmConta(contaBancaria.getdValorEmConta() + dValor);
        contaBancaria.addOperacao(dValor, TipoOperacao.DEPOSITO);
    }

    public void Pagamento(ContaBancaria contaBancariaOrigem, ContaBancaria contaBancariaDestino, Double dValor)
    {
        contaBancariaOrigem.setdValorEmConta(contaBancariaOrigem.getdValorEmConta() - dValor);
        contaBancariaOrigem.addOperacao(dValor*(-1.0), TipoOperacao.PAGAMENTO);

        contaBancariaDestino.setdValorEmConta(contaBancariaDestino.getdValorEmConta() + dValor);
        contaBancariaDestino.addOperacao(dValor, TipoOperacao.PAGAMENTO);
    }

    public Double Saldo(ContaBancaria contaBancaria)
    {
        return contaBancaria.getdValorEmConta();
    }

    public void Financiamento(ContaBancaria contaBancaria, Double dValor, Integer iNumeroDeParcelas)
    {
        contaBancaria.setdValorEmConta(contaBancaria.getdValorEmConta() + dValor);
        contaBancaria.addOperacao(dValor, TipoOperacao.FINANCIAMENTO);
        for (int x = 0; x != iNumeroDeParcelas; x++)
        {
            contaBancaria.addParcela(dValor / iNumeroDeParcelas);
        }
    }

    public void PagamentoDoFinanciamento(ContaBancaria contaBancaria, Double dValor, Integer iNumeroDeParcelas)
    {
        Double dValorTotalParcelas = (iNumeroDeParcelas * contaBancaria.getParcelasDoFinanciamento().get(0));

        if (Math.round(dValor * 10.0)/10.0 == Math.round(dValorTotalParcelas * 10.0)/10.0)
        {
            contaBancaria.setdValorEmConta(contaBancaria.getdValorEmConta() - dValor);
            contaBancaria.addOperacao(dValor * (-1.0), TipoOperacao.PAGAMENTOFINANCIAMENTO);
            for (int x = 0; x < iNumeroDeParcelas; x++)
            {
                contaBancaria.rmParcela();
            }
        }
        else
        {
            throw new IllegalArgumentException("O valor inserido não paga a quantidade de parcelas desejadas.");
        }
    }


    public void Aplicacao(ContaBancaria contaBancaria, Double dValor, int iPorcentagemRetorno)
    {
        contaBancaria.addOperacao(dValor*(-1.0), TipoOperacao.APLICACAO);
        contaBancaria.setdValorEmConta(contaBancaria.getdValorEmConta() - dValor);

        Double dRetornoDaAplicacao = dValor + dValor + ((dValor * (double)(iPorcentagemRetorno) / 100));

        contaBancaria.setdValorEmConta(contaBancaria.getdValorEmConta() + dRetornoDaAplicacao);
        contaBancaria.addOperacao(dRetornoDaAplicacao, TipoOperacao.APLICACAO);
    }
}
