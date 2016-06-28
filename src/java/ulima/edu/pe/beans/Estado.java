package ulima.edu.pe.beans;

public class Estado {

    public static final String NOMBRE_ID1 = "En cola";
    public static final String NOMBRE_ID2 = "Estado 2";
    public static final String NOMBRE_ID3 = "Estado 3";
    public static final String NOMBRE_ID4 = "Estado 4";
    public static final String NOMBRE_ID5 = "Entregado";

    private int id;
    private String fechaHora;
    private String username;

    public Estado() {
    }

    public Estado(int id, String fechaHora, String username) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        switch (id) {
            case 1:
                return NOMBRE_ID1;
            case 2:
                return NOMBRE_ID2;
            case 3:
                return NOMBRE_ID3;
            case 4:
                return NOMBRE_ID4;
            case 5:
                return NOMBRE_ID5;
            default:
                return "";
        }
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
