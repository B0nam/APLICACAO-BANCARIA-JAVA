package org.exemple.domain;

import org.example.domain.ContaPessoa;
import org.example.domain.enums.TipoConta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContaPessoaTest
{
    @Test
    public void DeveCriarContaPessoaCorrente()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.CORRENTE, 0.0, "Joselito", "111.111.111-11");
        Assertions.assertNotNull(contaPessoa);
    }

    @Test
    public void DeveCriarContaPessoaSalario()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.SALARIO, 0.0, "Joselito", "111.111.111-11");
        Assertions.assertNotNull(contaPessoa);
    }

    @Test
    public void DeveCriarContaPessoaPoupanca()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.POUPANCA, 50.0, "Joselito", "111.111.111-11");
        Assertions.assertNotNull(contaPessoa);
    }

    @Test
    public void DeveGerarExceptionQuandoValorEmContaPessoaPoupancaForMenorQueOMinimo()
    {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.POUPANCA, 0.0, "Joselito", "111.111.111-11");
        });
        Assertions.assertEquals("O valor em conta é menor do que o valor de abertura permitido.", exception.getMessage());
    }

    @Test
    public void DeveVerificarDocumentacao()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.CORRENTE, 0.0, "Joselito", "111.111.111-11");
        contaPessoa.VerificarDocumentacao("Joselito", "111.111.111-11");
        Assertions.assertNotNull(contaPessoa);
    }

    @Test
    void DeveGerarExceptionQuandoNomeForInvalido()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.CORRENTE, 0.0, "Joselito", "111.111.111-11");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            contaPessoa.VerificarDocumentacao("Josue", "111.111.111-11");
        });
        Assertions.assertEquals("Os documentos fornecidos são diferentes dos registrados.", exception.getMessage());
    }

    @Test
    void DeveGerarExceptionQuandoCpfForInvalido()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.CORRENTE, 0.0, "Joselito", "111.111.111-11");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            contaPessoa.VerificarDocumentacao("Joselito", "222.222.222-22");
        });
        Assertions.assertEquals("Os documentos fornecidos são diferentes dos registrados.", exception.getMessage());
    }

    @Test
    void DeveObterOValorEmConta()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.CORRENTE, 50.0, "Joselito", "111.111.111-11");
        Assertions.assertEquals(50.00, contaPessoa.getdValorEmConta());
    }

    @Test
    void DeveSetarOValorEmConta()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.CORRENTE, 50.0, "Joselito", "111.111.111-11");
        contaPessoa.setdValorEmConta(300.00);
        Assertions.assertEquals(300.00, contaPessoa.getdValorEmConta());
    }

    @Test
    void DeveAdicionarParcela()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.CORRENTE, 50.0, "Joselito", "111.111.111-11");
        contaPessoa.addParcela(300.00);
        Assertions.assertEquals(1, contaPessoa.getParcelasDoFinanciamento().size());
        Assertions.assertEquals(300.00, contaPessoa.getParcelasDoFinanciamento().get(0));
    }

    @Test
    void DeveRemoverParcela()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.CORRENTE, 50.0, "Joselito", "111.111.111-11");
        contaPessoa.addParcela(300.00);
        contaPessoa.addParcela(300.00);

        contaPessoa.rmParcela();

        Assertions.assertEquals(1, contaPessoa.getParcelasDoFinanciamento().size());
        Assertions.assertEquals(300.00, contaPessoa.getParcelasDoFinanciamento().get(0));
    }

    @Test
    void DeveObterAsParcelasDoFinanciamento()
    {
        ContaPessoa contaPessoa = new ContaPessoa(1, TipoConta.CORRENTE, 50.0, "Joselito", "111.111.111-11");
        contaPessoa.addParcela(300.00);
        contaPessoa.addParcela(300.00);

        contaPessoa.rmParcela();

        Assertions.assertEquals(1, contaPessoa.getParcelasDoFinanciamento().size());
        Assertions.assertEquals(300.00, contaPessoa.getParcelasDoFinanciamento().get(0));
    }

}
