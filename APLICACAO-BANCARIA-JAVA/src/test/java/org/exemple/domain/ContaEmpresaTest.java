package org.exemple.domain;

import org.example.domain.ContaEmpresa;
import org.example.domain.ContaPessoa;
import org.example.domain.enums.TipoConta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContaEmpresaTest
{
    @Test
    public void DeveCriarContaEmpresaCorrente()
    {
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");
        Assertions.assertNotNull(contaEmpresa);
    }

    @Test
    public void DeveCriarContaEmpresaSalario()
    {
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.SALARIO, 0.0, "Josers Market", "11.111.111/0001-11");
        Assertions.assertNotNull(contaEmpresa);
    }

    @Test
    public void DeveCriarContaEmpresaPoupanca()
    {
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.POUPANCA, 50.0, "Josers Market", "11.111.111/0001-11");
        Assertions.assertNotNull(contaEmpresa);
    }

    @Test
    public void DeveGerarExceptionQuandoValorEmContaEmpresaPoupancaForMenorQueOMinimo()
    {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.POUPANCA, 0.0, "Josers Market", "11.111.111/0001-11");
        });
        Assertions.assertEquals("O valor em conta é menor do que o valor de abertura permitido.", exception.getMessage());
    }

    @Test
    public void DeveVerificarDocumentacao()
    {
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");
        contaEmpresa.VerificarDocumentacao("Josers Market", "11.111.111/0001-11");
        Assertions.assertNotNull(contaEmpresa);
    }

    @Test
    void DeveGerarExceptionQuandoNomeForInvalido()
    {
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            contaEmpresa.VerificarDocumentacao("Ambros Market", "11.111.111/0001-11");
        });
        Assertions.assertEquals("Os documentos fornecidos são diferentes dos registrados.", exception.getMessage());
    }

    @Test
    void DeveGerarExceptionQuandoCnpjForInvalido()
    {
        ContaEmpresa contaEmpresa = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            contaEmpresa.VerificarDocumentacao("Josers Market", "22.222.222/0001-22");
        });
        Assertions.assertEquals("Os documentos fornecidos são diferentes dos registrados.", exception.getMessage());
    }
}
