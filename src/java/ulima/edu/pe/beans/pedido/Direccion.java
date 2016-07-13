package ulima.edu.pe.beans.pedido;

public class Direccion {

    private String calle;
    private String distrito;

    public Direccion() {
    }

    public Direccion(String calle, String distrito) {
        this.calle = calle;
        this.distrito = distrito;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    
    public boolean esIgualA(Direccion direccion){
        if (direccion.calle.equals(this.calle)){
            return false;
        }
        if (direccion.distrito.equals(this.distrito)){
            return false;
        }
        return true;
    }

}
