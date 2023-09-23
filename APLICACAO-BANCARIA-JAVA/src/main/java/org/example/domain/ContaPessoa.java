package org.example.domain;

import org.example.domain.enums.TipoConta;

import java.time.LocalDate;

public class ContaPessoa extends ContaBancaria implements Verificacao
{
    private String sNome;
    private String sCpf;

    public ContaPessoa(Integer iNumeroDaConta, TipoConta tcConta, Double dValorEmConta, String sNome, String sCpf)
    {
        super(iNumeroDaConta, tcConta, dValorEmConta);
        this.sNome = sNome;
        this.sCpf = sCpf;
    }

    @Override
    public void VerificarDocumentacao(String sNome, String sCpf)
    {
        if (this.sNome != sNome || this.sCpf != sCpf)
        {
            throw new IllegalArgumentException("Os documentos fornecidos são diferentes dos registrados.");
        }
    }
}
