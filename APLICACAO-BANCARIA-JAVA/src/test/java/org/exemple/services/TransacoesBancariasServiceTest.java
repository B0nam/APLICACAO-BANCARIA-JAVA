package org.exemple.services;

import org.example.domain.ContaEmpresa;
import org.example.domain.enums.TipoConta;
import org.example.services.TransacoesBancariasService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransacoesBancariasServiceTest
{
    @Test
    public void ContaEmpresaDeveRealizarDeposito()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        transacoesBancariasService.Deposito(contaEmpresa, 100.0);
        Assertions.assertEquals(100.0, contaEmpresa.getdValorEmConta());
    }

    @Test
    public void ContaEmpresaDeveRealizarPagamentoParaContraEmpresa()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ContaEmpresa contaEmpresa1 = new ContaEmpresa(1, TipoConta.CORRENTE, 200.0, "Josers Market", "11.111.111/0001-11");
        ContaEmpresa contaEmpresa2 = new ContaEmpresa(2, TipoConta.CORRENTE, 200.0, "Ambros Market", "22.222.222/0001-22");

        transacoesBancariasService.Pagamento(contaEmpresa1, contaEmpresa2, 100.0);
        Assertions.assertEquals(100.0, contaEmpresa1.getdValorEmConta());
        Assertions.assertEquals(300.0, contaEmpresa2.getdValorEmConta());
    }

    @Test
    public void ContaEmpresaDeveRealizarFianciamento()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        transacoesBancariasService.Financiamento(contaEmpresa, 1000.00, 10);;
        Assertions.assertEquals(1000.00, contaEmpresa.getdValorEmConta());
        Assertions.assertEquals(10, contaEmpresa.getParcelasDoFinanciamento().size());
        Assertions.assertEquals(100.00, contaEmpresa.getParcelasDoFinanciamento().get(0));
    }

    @Test
    public void ContaEmpresaDeveRealizarPagamentoDoFinanciamento()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        transacoesBancariasService.Financiamento(contaEmpresa, 1000.00, 10);;
        transacoesBancariasService.PagamentoDoFinanciamento(contaEmpresa, 1000.0, 10);

        Assertions.assertEquals(0, contaEmpresa.getParcelasDoFinanciamento().size());
    }

    @Test
    public void ContaEmpresaDeveRealizarAplicacao()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        transacoesBancariasService.Aplicacao(contaEmpresa, 100.00, 20);
        Assertions.assertEquals(120.00, contaEmpresa.getdValorEmConta());
    }
}
