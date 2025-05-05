package pruebas;

import cuentapalabras.ContadorPalabras;

public class PruebaContadorPalabras {
    public static void main(String[] args){
        ContadorPalabras palabras= new ContadorPalabras();
        String[] datos={"Esta es la primera frase del ejemplo", "y esta es la segunda frase"};
        String delimitadores= "[ ]";//para indicar delimitador se hace entre corchetes
        palabras.incluyeTodas(datos,delimitadores);
        System.out.println(palabras);
    }


}
