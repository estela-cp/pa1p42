package cuentapalabras;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class ContadorPalabras {
    private List<PalabraEnTexto> palabras;

    public ContadorPalabras(){
        palabras= new ArrayList<>();
    }

    protected void incluye(String pal){
        if(pal!=""){
            PalabraEnTexto palabra= new PalabraEnTexto(pal);
            int posicion=buscar(palabra);
            if(posicion==-1){
                palabras.add(palabra);
            }else{
                palabras.get(posicion).incrementa();
            }
        }

    }

    private int buscar(PalabraEnTexto pal){
        boolean encontrado=false;
        int posicion=-1;

        for(int i=0; i< palabras.size() && !encontrado;i++){
            if(pal.equals(palabras.get(i))){
                encontrado=true;
                posicion=i;
            }
        }
        return posicion;
    }

    private void incluyeTodas(String linea, String del){
        String[] palabrasSeparadas= linea.split(del);
        for(int i=0; i<palabrasSeparadas.length;i++){
            incluye(palabrasSeparadas[i]);
        }
    }

    public void incluyeTodas(String[] texto, String del){
        for(int i=0; i< texto.length; i++){
            incluyeTodas(texto[i],del);
        }
    }

    public void incluyeTodasFichero(String nomFich, String del) throws IOException {
        Path rutaFichero= Path.of(nomFich);
        //para leer cada linea del archivo de texto se usa la libreria BufferedReader
        //para ello primero creamos el objeto bufferedReader, pasando como argumento la ruta
        //bufferedReader necesita cerrarlo una vez acabas -> try(..){}
        try(BufferedReader bufferedReader= Files.newBufferedReader(rutaFichero)){//entre parentesis esta lo q tiene q cerrar, que sera la que abre el Buffered
            String lineaFichero= bufferedReader.readLine();
            while(lineaFichero!=null){
                incluyeTodas(lineaFichero,del);
                lineaFichero=bufferedReader.readLine();
            }
        }

    }

    public PalabraEnTexto encuentra(String pal) {
        PalabraEnTexto palabra= new PalabraEnTexto(pal);
        int posicion=buscar(palabra);
        if(posicion==-1){
            throw new NoSuchElementException();
        }
        return palabras.get(posicion);
    }

    public String toString(){
        StringJoiner cadena= new StringJoiner(" - ","[","]");
        for(int i=0; i<palabras.size();i++){
            cadena.add(palabras.get(i).toString());
        }
        return cadena.toString();
    }


    public void presentaPalabras(String fichero) throws FileNotFoundException{
        try(PrintWriter printWriter= new PrintWriter(fichero)){
            presentaPalabras(printWriter);
        }
    }

    public void presentaPalabras(PrintWriter printWriter){
        for(int i=0; i< palabras.size();i++){
            printWriter.println(palabras.get(i).toString());
        }
    }






}






