package org.example.domain;

import org.example.domain.enums.TipoConta;

public class ContaEmpresa extends ContaBancaria implements ContaBancariaInterface
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
