package dominio.pagamenti;

import dominio.classi_dati.TipoPagamento;


public class PagamentoFactory {
    
    public PagamentoFactory(){
        
    }
    
    public static Pagamento getPagamento(TipoPagamento tipo_pagamento){
        
        switch(tipo_pagamento){
            case Reale: return new PagamentoReale();
            case Virtuale: return new PagamentoVirtuale();
            default: return null;
        }
    }
}
