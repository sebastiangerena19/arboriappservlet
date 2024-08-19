package model;
public class EspecieVivero {
    private int IDVivero;
    private String Especie;
    private double Precio;
    private double Altura;
    private double DAP;
    private double AnchoCopa;
    private int EdadAnos;

    public int getIDVivero() {
        return IDVivero;
    }

    public void setIDVivero(int IDVivero) {
        this.IDVivero = IDVivero;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String Especie) {
        this.Especie = Especie;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public double getAltura() {
        return Altura;
    }

    public void setAltura(double Altura) {
        this.Altura = Altura;
    }

    public double getDAP() {
        return DAP;
    }

    public void setDAP(double DAP) {
        this.DAP = DAP;
    }

    public double getAnchoCopa() {
        return AnchoCopa;
    }

    public void setAnchoCopa(double AnchoCopa) {
        this.AnchoCopa = AnchoCopa;
    }

    public int getEdadAnos() {
        return EdadAnos;
    }

    public void setEdadAnos(int EdadAnos) {
        this.EdadAnos = EdadAnos;
    }
}