package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Generator {

    Klasa klasa;

    public void czytajZPliku(String nazwaPliku){
        String fileName = "C:\\Users\\wkili\\IdeaProjects\\ZTP6generatorKlas\\src\\"+nazwaPliku;
        File file = null;
        try {
            file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String linia = scanner.nextLine();
            String [] splitted = linia.split(" ");
            klasa = new Klasa();
            //widocznosc klasy i dodatki
            wczytajKlase(splitted);
            //atrubuty
            wczytajAtrybuty(splitted);
//            klasa.printAtrybuty();
            generujPlik();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
            System.out.println("Wpisz poprawną nazwę: ");
            Scanner scanner = new Scanner(System.in);
            fileName = "C:\\Users\\wkili\\IdeaProjects\\ZTP6generatorKlas\\src\\"+scanner.nextLine();

            file = new File(fileName);
            try {
                scanner = new Scanner(file);
                String linia = scanner.nextLine();
                String [] splitted = linia.split(" ");
                klasa = new Klasa();
                //widocznosc klasy i dodatki
                wczytajKlase(splitted);
                //atrubuty
                wczytajAtrybuty(splitted);
                generujPlik();
            } catch (FileNotFoundException e1) {
                System.out.println("Trzeba bylo podac poprawna nazwe...");
            }
        }
    }

    public void wczytajKlase(String [] splitted){
        char widocznosc = splitted[1].charAt(0);
        if(widocznosc == '+'){
            klasa.widocznosc = "public";
            klasa.setNazwa(splitted[1].substring(1));
        }else{
            if(widocznosc == '-'){
                klasa.widocznosc = "private";
                klasa.setNazwa(splitted[1].substring(1));
            }else{
                if(widocznosc == '/'){
                    klasa.widocznosc = "protected";
                    klasa.setNazwa(splitted[1].substring(1));
                }else{
                    klasa.setNazwa(splitted[1]);
                }
            }
        }
        if(splitted[splitted.length-1].charAt(0) == '-'){
            wczytajDodatkiKlasy(splitted[splitted.length-1]);
        }
    }

    public void wczytajDodatkiKlasy(String dodatki){
        char[] chars = dodatki.toCharArray();
        for (char c: chars) {
            if(c == 'g'){
                klasa.getters = true;
            }
            if(c == 's'){
                klasa.setters = true;
            }
            if(c == 'i'){
                klasa.singleton = true;
            }
        }
    }

    public void wczytajAtrybuty(String[] atrybuty){
        String currAtrybut;
        String[] czescAtr;
        Atrybut temp;
        for(int i = 2; i<atrybuty.length; i++){
            if(i != atrybuty.length - 1 && atrybuty[i].charAt(0) != '-') {
                currAtrybut = atrybuty[i];
                czescAtr = currAtrybut.split(":|=");
                temp = new Atrybut(czescAtr[0], czescAtr[1]);
                if(czescAtr.length == 3){
                    if(czescAtr[1].equals("String")){
                        String wart = "\"";
                        wart += czescAtr[2].substring(1, czescAtr[2].length()-1) + "\"";
                        temp.setWartosc(wart);
                    }else {
                        temp.setWartosc(czescAtr[2]);
                    }
                }
                klasa.atrybuty.add(temp);
            }
        }
    }


    public void generujPlik(){
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(klasa.nazwa+".java");
            fileWriter.write(klasa.klasaZWidocznoscia()+" {\n\n");
            if (klasa.singleton){
                fileWriter.write(klasa.sSingletonObiekt());
                fileWriter.write(klasa.sAtrybuty()+"\n");
                fileWriter.write(klasa.sSingletonKonstruktor());
            }else {
                fileWriter.write(klasa.sAtrybuty() + "\n");
            }
            if(klasa.getters){
                fileWriter.write(klasa.sGetters());
            }
            if(klasa.setters){
                fileWriter.write(klasa.sSetters());
            }
            fileWriter.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                //zostawiam bo to jedyna sytuacja gdzie mozna
            }
        }

    }


}
