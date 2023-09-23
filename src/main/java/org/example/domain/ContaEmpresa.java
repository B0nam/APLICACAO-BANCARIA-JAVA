package org.example.domain;

import org.example.domain.enums.TipoConta;

import java.time.LocalDate;

public class ContaEmpresa extends ContaBancaria implements Verificacao
{
    private String sNome;
    private String sCnpj;


    public ContaEmpresa(Integer iNumeroDaConta, TipoConta tcConta, Double dValorEmConta, String sNome, String sCnpj)
    {
        super(iNumeroDaConta, tcConta, dValorEmConta);
        this.sNome = sNome;
        this.sCnpj = sCnpj;
    }

    @Override
    public void VerificarDocumentacao(String sNome, String sCnpj)
    {
        if (this.sNome != sNome || this.sCnpj != sCnpj)
        {
            throw new IllegalArgumentException("Os documentos fornecidos são diferentes dos registrados.");
        }
    }
}
