package com.APILISTACOMPRAS.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ListaCompras {

    @Id
    @GeneratedValue
    private Long id;

    private String Itens;

    public String getItens() {
        return Itens;
    }

    public void setItens(String itens) {
        Itens = itens;
    }

    public ListaCompras(String Itens) {
        this.Itens = Itens;

    }
}
