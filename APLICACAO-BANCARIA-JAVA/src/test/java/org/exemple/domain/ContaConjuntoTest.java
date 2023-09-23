package org.exemple.domain;

import org.example.domain.ContaConjunto;
import org.example.domain.ContaEmpresa;
import org.example.domain.ContaPessoa;
import org.example.domain.enums.TipoConta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContaConjuntoTest
{
    @Test
    public void DeveGerarContaConjuntaDePessoa()
    {
        ContaPessoa contaPessoa1 = new ContaPessoa(1, TipoConta.CORRENTE, 0.0, "Joselito", "111.111.111-11");
        ContaPessoa contaPessoa2 = new ContaPessoa(2, TipoConta.CORRENTE, 0.0, "Ambrosina", "222.222.222-22");

        ContaConjunto contaConjuntoPessoa = new ContaConjunto(3, TipoConta.CORRENTE, 0.0,  contaPessoa1, contaPessoa2);
        Assertions.assertNotNull(contaConjuntoPessoa);
    }

    @Test
    public void DeveGerarContaConjuntaDeEmpresas()
    {
        ContaEmpresa contaEmpresa1 = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");
        ContaEmpresa contaEmpresa2 = new ContaEmpresa(2, TipoConta.CORRENTE, 0.0, "Ambros Market", "22.222.222/0001-22");

        ContaConjunto contaConjuntoEmpresa = new ContaConjunto(3, TipoConta.CORRENTE, 0.0,  contaEmpresa1, contaEmpresa2);
        Assertions.assertNotNull(contaConjuntoEmpresa);
    }

    @Test
    void DeveGerarExceptionQuandoContaConjuntaSerPessoaEEmpresa()
    {
        ContaPessoa contaPessoa1 = new ContaPessoa(1, TipoConta.CORRENTE, 0.0, "Joselito", "111.111.111-11");
        ContaEmpresa contaEmpresa1 = new ContaEmpresa(2, TipoConta.CORRENTE, 0.0, "Ambros Market", "22.222.222/0001-22");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            ContaConjunto contaConjuntoPessoaEmpresa = new ContaConjunto(3, TipoConta.CORRENTE, 0.0,  contaPessoa1, contaEmpresa1);
        });
        Assertions.assertEquals("As contas bancarias devem ser do mesmo tipo.", exception.getMessage());
    }

    @Test
    void DeveGerarExceptionQuandoContaConjuntaSerEmpresaEPessoa()
    {
        ContaEmpresa contaEmpresa1 = new ContaEmpresa(1, TipoConta.CORRENTE, 0.0, "Josers Market", "11.111.111/0001-11");
        ContaPessoa contaPessoa1 = new ContaPessoa(2, TipoConta.CORRENTE, 0.0, "Ambrosina", "222.222.222-22");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            ContaConjunto contaConjuntoPessoaEmpresa = new ContaConjunto(3, TipoConta.CORRENTE, 0.0,  contaEmpresa1, contaPessoa1);
        });
        Assertions.assertEquals("As contas bancarias devem ser do mesmo tipo.", exception.getMessage());
    }

    @Test
    void DeveObterValorEmConta()
    {
        ContaEmpresa contaEmpresa1 = new ContaEmpresa(1, TipoConta.CORRENTE, 100.0, "Josers Market", "11.111.111/0001-11");
        ContaEmpresa contaEmpresa2 = new ContaEmpresa(2, TipoConta.CORRENTE, 50.0, "Ambros Market", "22.222.222/0001-22");

        ContaConjunto contaConjuntoEmpresa = new ContaConjunto(3, TipoConta.CORRENTE, 0.0,  contaEmpresa1, contaEmpresa2);
        Assertions.assertEquals(100.0, contaEmpresa1.getdValorEmConta());
        Assertions.assertEquals(50.0, contaEmpresa2.getdValorEmConta());
        Assertions.assertEquals(150.00, contaConjuntoEmpresa.getdValorEmConta());
    }
}
