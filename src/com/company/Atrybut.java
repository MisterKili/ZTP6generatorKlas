package com.company;

public class Atrybut {

    String typ;         //obl
    String nazwa;       //obl
    String wartosc;     //opc

    public Atrybut(String nazwa, String typ){
        this.typ = typ;
        this.nazwa = nazwa;
    }

    public void setWartosc(String wartosc){
        this.wartosc = wartosc;
    }

    @Override
    public String toString(){
        String s = typ+" "+nazwa;
        if (wartosc != null)
            s += " = "+wartosc;
        return s;
    }
}
