package cuentapalabras;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ContadorPalabrasSig extends ContadorPalabras{
    private List<String> noSignificativas;

    public ContadorPalabrasSig(){
        super();
        noSignificativas=new ArrayList<>();
    }

    public void leeArrayNoSig(String[] palsNS){
        noSignificativas.clear();

        for(int i=0; i< palsNS.length;i++){
            if(palsNS[i]!=null && !palsNS[i].isEmpty()){
                noSignificativas.add(palsNS[i].toUpperCase());
            }
        }
    }

    public void leeFicheroNoSig(String filNoSig, String del) throws IOException{
        noSignificativas.clear();
        Path rutaFichero= Path.of(filNoSig);

        try(BufferedReader bufferedReader = Files.newBufferedReader(rutaFichero)){
            String lineaFichero= bufferedReader.readLine();
            while(lineaFichero!=null){
                anyadePalabrasNoSignificativas(lineaFichero,del);
                lineaFichero= bufferedReader.readLine();
            }
        }

    }

    private void anyadePalabrasNoSignificativas(String linea, String del){
        String[] palabrasSeparadas= linea.split(del);
        for(int i=0; i< noSignificativas.size();i++){
            if(palabrasSeparadas[i]!=null && !palabrasSeparadas[i].isEmpty()){
                noSignificativas.add(palabrasSeparadas[i]);
            }
        }
    }

    protected void incluye(String pal){
        if(!noSignificativas.contains(pal)){
            super.incluye(pal);
        }
    }
}
