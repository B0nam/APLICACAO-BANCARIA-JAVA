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
        contaBancariaOrigem.addOperacao(dValor, TipoOperacao.PAGAMENTO);

        contaBancariaDestino.setdValorEmConta(contaBancariaDestino.getdValorEmConta() + dValor);
        contaBancariaDestino.addOperacao(dValor, TipoOperacao.PAGAMENTO);
    }
    public void Financiamento(ContaBancaria contaBancaria, Double dValor, Integer iNumeroDeParcelas)
    {
        contaBancaria.setdValorEmConta(contaBancaria.getdValorEmConta() + dValor);
        for (int x = 0; x < iNumeroDeParcelas; x++)
        {
            contaBancaria.addParcela(dValor / iNumeroDeParcelas);
        }
    }

    public void PagamentoDoFinanciamento(ContaBancaria contaBancaria, Double dValor, Integer iNumeroDeParcelas)
    {
        contaBancaria.setdValorEmConta(contaBancaria.getdValorEmConta() - dValor);
        for (int x = 0; x < iNumeroDeParcelas; x++)
        {
            contaBancaria.rmParcela();
        }
    }

    public void Aplicacao(ContaBancaria contaBancaria, Double dValor, Double dValorRetorno)
    {
        contaBancaria.addOperacao(dValor, TipoOperacao.APLICACAO);
        contaBancaria.setdValorEmConta(contaBancaria.getdValorEmConta() - dValor);

        Double dRetornoDaAplicacao = contaBancaria.getdValorEmConta() + (dValor * (dValorRetorno * 100));

        contaBancaria.setdValorEmConta(dRetornoDaAplicacao);
        contaBancaria.addOperacao(dRetornoDaAplicacao, TipoOperacao.DEPOSITO);
    }
}
