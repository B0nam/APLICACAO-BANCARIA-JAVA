package org.exemple.services;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import org.example.domain.ContaEmpresa;
import org.example.domain.ContaPessoa;
import org.example.domain.enums.TipoConta;
import org.example.services.ExtratoService;
import org.example.services.TransacoesBancariasService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

public class ExtratoServiceTest
{
    @Test
    void DeveGerarExtratoDoDeposito()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ExtratoService extratoService = new ExtratoService();
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        transacoesBancariasService.Deposito(contaEmpresa, 100.0);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa, LocalDate.of(2020,9,23), LocalDate.of(2025,9,23));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa, LocalDate.of(2020, 9, 23), LocalDate.of(2025, 9, 23));

        System.out.flush();
        System.setOut(oldOut);

        String extratoGerado = baos.toString();

        String extratoEsperado = "------------------------------\n" +
                "Data : " + LocalDate.now() + "\n" +
                "Tipo : DEPOSITO\n" +
                "Valor: 100.0\n" +
                "------------------------------\n";

        Assertions.assertEquals(extratoEsperado, extratoGerado);
    }

    @Test
    void DeveGerarExtratoDoPagamento()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ExtratoService extratoService = new ExtratoService();
        ContaEmpresa contaEmpresa1 = new ContaEmpresa(1, TipoConta.CORRENTE, 200.0, "Josers Market", "11.111.111/0001-11");
        ContaEmpresa contaEmpresa2 = new ContaEmpresa(2, TipoConta.CORRENTE, 200.0, "Ambros Market", "22.222.222/0001-22");

        transacoesBancariasService.Pagamento(contaEmpresa1, contaEmpresa2, 100.0);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa1, LocalDate.of(2020,9,23), LocalDate.of(2025,9,23));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa1, LocalDate.of(2020, 9, 23), LocalDate.of(2025, 9, 23));

        System.out.flush();
        System.setOut(oldOut);

        String extratoGerado = baos.toString();

        String extratoEsperado = "------------------------------\n" +
                "Data : " + LocalDate.now() + "\n" +
                "Tipo : PAGAMENTO\n" +
                "Valor: -100.0\n" +
                "------------------------------\n";

        Assertions.assertEquals(extratoEsperado, extratoGerado);
    }

    @Test
    void DeveGerarExtratoDoFinanciamento()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ExtratoService extratoService = new ExtratoService();
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        transacoesBancariasService.Financiamento(contaEmpresa, 1000.0, 10);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa, LocalDate.of(2020,9,23), LocalDate.of(2025,9,23));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa, LocalDate.of(2020, 9, 23), LocalDate.of(2025, 9, 23));

        System.out.flush();
        System.setOut(oldOut);

        String extratoGerado = baos.toString();

        String extratoEsperado = "------------------------------\n" +
                "Data : " + LocalDate.now() + "\n" +
                "Tipo : FINANCIAMENTO\n" +
                "Valor: 1000.0\n" +
                "------------------------------\n";

        Assertions.assertEquals(extratoEsperado, extratoGerado);
    }

    @Test
    void DeveGerarExtratoDoPagamentoDoFinanciamento()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ExtratoService extratoService = new ExtratoService();
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        double valorFinanciamento = 1000.0;
        int numeroDeParcelas = 10;

        // Realize um financiamento com um valor suficiente para cobrir as parcelas
        transacoesBancariasService.Financiamento(contaEmpresa, valorFinanciamento, numeroDeParcelas);
        transacoesBancariasService.PagamentoDoFinanciamento(contaEmpresa, valorFinanciamento, numeroDeParcelas);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa, LocalDate.of(2020,9,23), LocalDate.of(2025,9,23));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa, LocalDate.of(2020, 9, 23), LocalDate.of(2025, 9, 23));

        System.out.flush();
        System.setOut(oldOut);

        String sExtratoGerado = baos.toString();

        String sExtratoExperado = "------------------------------\n" +
                "Data : " + LocalDate.now() + "\n" +
                "Tipo : FINANCIAMENTO\n" +
                "Valor: 1000.0\n" +
                "------------------------------\n" +
                "------------------------------\n" +
                "Data : " + LocalDate.now() + "\n" +
                "Tipo : PAGAMENTOFINANCIAMENTO\n" +
                "Valor: -1000.0\n" +
                "------------------------------\n";

        Assertions.assertEquals(sExtratoExperado, sExtratoGerado);
    }


    @Test
    void DeveGerarExtratoDaAplicacao()
    {
        TransacoesBancariasService transacoesBancariasService = new TransacoesBancariasService();
        ExtratoService extratoService = new ExtratoService();
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        transacoesBancariasService.Aplicacao(contaEmpresa, 100.0, 100);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa, LocalDate.of(2020,9,23), LocalDate.of(2025,9,23));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        extratoService.GerarExtratoPorPeriodo(contaEmpresa, LocalDate.of(2020, 9, 23), LocalDate.of(2025, 9, 23));

        System.out.flush();
        System.setOut(oldOut);

        String sExtratoGerado = baos.toString();

        String sExtratoEsperado = "------------------------------\n" +
                "Data : " + LocalDate.now() + "\n" +
                "Tipo : APLICACAO\n" +
                "Valor: -100.0\n" +
                "------------------------------\n" +
                "------------------------------\n" +
                "Data : " + LocalDate.now() + "\n" +
                "Tipo : APLICACAO\n" +
                "Valor: 300.0\n" +
                "------------------------------\n";

        Assertions.assertEquals(sExtratoEsperado, sExtratoGerado);
    }
}
