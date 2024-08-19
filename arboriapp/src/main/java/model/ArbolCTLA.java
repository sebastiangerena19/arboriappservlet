package model;

public class ArbolCTLA extends Arbol {
    private double ConstanteCTLA;

    public ArbolCTLA(int ID, String Especie, double Altura, double DAP, double PrecioEspecie, double ValorEconomico, double ConstanteCTLA) {
        super();
        this.ConstanteCTLA = ConstanteCTLA;
    }

    public double getConstanteCTLA() {
        return ConstanteCTLA;
    }

    public void setConstanteCTLA(double ConstanteCTLA) {
        this.ConstanteCTLA = ConstanteCTLA;
    }
}
