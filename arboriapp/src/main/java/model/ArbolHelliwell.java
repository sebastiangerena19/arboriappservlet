package model;

public class ArbolHelliwell extends Arbol {
    private double ConstanteHelliwell;
    private double ValorHelliwell;

    public ArbolHelliwell(int ID, String Especie, double Altura, double DAP, double PrecioEspecie, double ValorEconomico, double ConstanteHelliwell, double ValorHelliwell) {
        super();
        this.ConstanteHelliwell = ConstanteHelliwell;
        this.ValorHelliwell = ValorHelliwell;
    }

    public double getConstanteHelliwell() {
        return ConstanteHelliwell;
    }

    public void setConstanteHelliwell(double ConstanteHelliwell) {
        this.ConstanteHelliwell = ConstanteHelliwell;
    }

    public double getValorHelliwell() {
        return ValorHelliwell;
    }

    public void setValorHelliwell(double ValorHelliwell) {
        this.ValorHelliwell = ValorHelliwell;
    }
}

