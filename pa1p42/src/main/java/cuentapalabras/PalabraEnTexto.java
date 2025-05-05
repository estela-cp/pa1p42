package cuentapalabras;

public class PalabraEnTexto {
    private String palabra;
    private int veces;

    public PalabraEnTexto(String palabra){
        this.palabra=palabra.toUpperCase();
        veces=1;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        return (o instanceof PalabraEnTexto nuevapalabra) && this.palabra.equals(nuevapalabra.palabra);
    }

    public String toString(){
        return this.palabra+": "+this.veces;
    }

    public void incrementa(){
         this.veces++;
    }
}
