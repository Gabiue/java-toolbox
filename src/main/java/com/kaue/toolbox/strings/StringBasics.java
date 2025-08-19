package com.kaue.toolbox.strings;

import java.util.*;

public class StringBasics {
    public static void main(String[] args) {

        String s = "I hate Java";
        // String s = "I Love Java";
        // String s = "Hello World"
        String sEmpty = "";     // string vazia
        String sSpaces = "   "; // string só com espaços
        String sLower = "java";
        String sUpper = "Java";
        String sOther = "Banana";


        //length
        System.out.println("lenght = "+s.length());

        //trim
        System.out.println("trim = '"+s.trim()+"'");

        //toUpperCase / toLowerCase
        System.out.println("upper = "+s.toUpperCase());
        System.out.println("lower = "+s.toLowerCase());

        //charAt
        System.out.println("charAt(2) = "+s.charAt(2));

        //substring (substring (inicia em qual indice, vai até qual indice)
        System.out.println("substring(2, 7) = '" + s.substring(2, 7) + "'");

        //replace
        System.out.println("replace 'Java' -> 'Mundo' = "+s.replace("Java", "Mundo"));

        //split
        String[] partes = s.trim().split("\\s+");
        for(String p : partes){
            System.out.println("parte = " + p);
        }

        //isEmpty(checa se o length == 0    ) isSpace(checa se é vazio ou se só tem espaços brancos)
        System.out.println("sEmpty.isEmpty() = " + sEmpty.isEmpty());   // true
        System.out.println("sSpaces.isEmpty() = " + sSpaces.isEmpty()); // false
        System.out.println("sEmpty.isBlank() = " + sEmpty.isBlank());   // true
        System.out.println("sSpaces.isBlank() = " + sSpaces.isBlank()); // true

        // equals / equalsIgnoreCase
        System.out.println("sLower.equals(sUpper) = " + sLower.equals(sUpper));               // false
        System.out.println("sLower.equalsIgnoreCase(sUpper) = " + sLower.equalsIgnoreCase(sUpper)); // true

        // compareTo
        System.out.println("sUpper.compareTo(sOther) = " + sUpper.compareTo(sOther)); // >0 porque 'J' vem depois de 'B'
        System.out.println("sOther.compareTo(sUpper) = " + sOther.compareTo(sUpper)); // <0
        System.out.println("sUpper.compareTo(sLower) = " + sUpper.compareTo(sLower)); // diferença no case


        //Concat
        String hello = "Hello".concat(" World");
        System.out.println("concat = " + hello);

        // repeat
        System.out.println("repeat 3x = " + "Hi ".repeat(3));

        // join
        String frutas = String.join(", ", "Maçã", "Banana", "Uva");
        System.out.println("join = " + frutas);

        // toCharArray (transforma em array de chars)
        char[] letras = s.toCharArray();
        System.out.println("toCharArray = " + Arrays.toString(letras));

        // format (estilo printf, mas devolve String)
        String nome = "Gabriel";
        int idade = 19;
        String msg = String.format("Meu nome é %s e tenho %d anos.", nome, idade);
        System.out.println("format = " + msg);

        // strip, stripLeading, stripTrailing
        String comEspacos = "   Java   ";
        System.out.println("strip = '" + comEspacos.strip() + "'");
        System.out.println("stripLeading = '" + comEspacos.stripLeading() + "'");
        System.out.println("stripTrailing = '" + comEspacos.stripTrailing() + "'");

        // lines (quebra em linhas → retorna Stream<String>)
        String multiLine = "Linha1\nLinha2\nLinha3";
        multiLine.lines().forEach(l -> System.out.println("line = " + l));

        //valueOf
        String numStr = String.valueOf(123);
        System.out.println(numStr); // "123"
    }
}
