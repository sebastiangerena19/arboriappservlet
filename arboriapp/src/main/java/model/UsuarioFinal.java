package model;

public class UsuarioFinal extends Usuario {
    private String nombreDeUsuario;
    private String ubicacionDelPredio;

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getUbicacionDelPredio() {
        return ubicacionDelPredio;
    }

    public void setUbicacionDelPredio(String ubicacionDelPredio) {
        this.ubicacionDelPredio = ubicacionDelPredio;
    }
}