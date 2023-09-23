package org.example.services;

import org.example.domain.ContaBancaria;
import org.example.domain.RegistroDeOperacao;

import java.time.LocalDate;

public class ExtratoService
{

    public void GerarExtratoPorPeriodo(ContaBancaria contaBancaria, LocalDate ldInicio, LocalDate ldFim)
    {
        for ( RegistroDeOperacao operacao : contaBancaria.getOperacoes() )
        {
            if (operacao.getData().isAfter(ldInicio) && operacao.getData().isBefore(ldFim))
            {
                System.out.println("------------------------------");
                System.out.println("Data : " + operacao.getData());
                System.out.println("Tipo : " + operacao.getOperacao());
                System.out.println("Valor: " + operacao.getValor());
                System.out.println("------------------------------");
            }
        }
    }

}
