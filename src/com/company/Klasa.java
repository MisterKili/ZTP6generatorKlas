package com.company;

import java.util.ArrayList;

public class Klasa {

    String nazwa;       //obl
    String widocznosc;  //opc
    boolean getters = false;
    boolean setters = false;
    boolean singleton = false;
    ArrayList<Atrybut> atrybuty = new ArrayList<>();

    public Klasa(){}

    public Klasa(String nazwa){
        this.nazwa = nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setWidocznosc(String widocznosc) {
        this.widocznosc = widocznosc;
    }

    public void printKlasa(){
        String s;
        if(widocznosc != null){
            s = widocznosc+" "+nazwa;
        }else {
            s = nazwa;
        }
        System.out.println(s);
        String s1 = "Dodatki: ";
        if(getters){
            s1 += "gettery, ";
        }
        if(setters)
            s1 += "settery, ";
        if(singleton)
            s1 += "singleton, ";
        System.out.println(s1);
    }

    public void printAtrybuty(){
        for(Atrybut a: atrybuty){
            System.out.println(a);
        }
    }

    public String klasaZWidocznoscia(){
        String s;
        if(widocznosc != null){
            s = widocznosc+" class "+nazwa;
        }else {
            s = "class "+nazwa;
        }
        return s;
    }

    public String sAtrybuty(){
        StringBuilder sb = new StringBuilder();
        for(Atrybut a: atrybuty){
            sb.append("\tprivate "+a+";\n");
        }
        return sb.toString();
    }

    public String sGetters(){
        StringBuilder sb = new StringBuilder();
        for(Atrybut a: atrybuty){
            sb.append("\tpublic "+a.typ+" get"+a.nazwa.substring(0, 1).toUpperCase() + a.nazwa.substring(1)+"(){\n");
            sb.append("\t\treturn "+a.nazwa);
            sb.append(";\n\t}\n\n");
        }
        return sb.toString();
    }

    public String sSetters(){
        StringBuilder sb = new StringBuilder();
        for(Atrybut a: atrybuty){
            sb.append("\tpublic void set"+a.nazwa.substring(0, 1).toUpperCase() + a.nazwa.substring(1)+"("+a.typ+" "+a.nazwa+"){\n");
            sb.append("\t\tthis."+a.nazwa+" = "+a.nazwa);
            sb.append(";\n\t}\n\n");
        }
        return sb.toString();
    }

    public String sSingletonObiekt(){
        String temp = "\tpublic static final "+nazwa+" INSTANCE = new "+nazwa+"();\n\n";
        return temp;
    }

    public String sSingletonKonstruktor(){
        String temp = "\tprivate "+nazwa+"(){}\n\n";
        return temp;
    }
}
