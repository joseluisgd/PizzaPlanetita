package ulima.edu.pe.beans;

import java.io.Serializable;

public class Tamano implements Serializable {

    public static final String NOMBRE_ID1 = "Pequeña";
    public static final String NOMBRE_ID2 = "Mediana";
    public static final String NOMBRE_ID3 = "Grande";
    public static final String NOMBRE_ID4 = "Fiesta";

    public static final int SLICES_PEQUENA = 6;
    public static final int SLICES_MEDIANA = 8;
    public static final int SLICES_GRANDE = 10;
    public static final int SLICES_FIESTA = 12;

    public static final int MULTIPLICADOR_PEQUENA_PERSONALIZADA = 4;
    public static final int MULTIPLICADOR_MEDIANA_PERSONALIZADA = 8;
    public static final int MULTIPLICADOR_GRANDE_PERSONALIZADA = 10;
    public static final int MULTIPLICADOR_FIESTA_PERSONALIZADA = 12;

    private int id;
    private String nombre;
    private int slices;
    private float precio;

    public Tamano() {
    }

    public Tamano(int id, String nombre, int slices) {
        this.id = id;
        this.nombre = nombre;
        this.slices = slices;
    }

    public Tamano(int id, float precio) {
        this.id = id;
        nombreSegunId();
        slicesSegunId();
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSlices() {
        return slices;
    }

    private void setSlices(int slices) {
        this.slices = slices;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Tamano clonar() {
        Tamano clon = new Tamano();
        clon.setId(id);
        clon.setNombre(nombre);
        clon.setSlices(slices);
        clon.setPrecio(precio);
        return clon;
    }

    private void nombreSegunId() {
        switch (id) {
            case 1:
                nombre = NOMBRE_ID1;
                break;
            case 2:
                nombre = NOMBRE_ID2;
                break;
            case 3:
                nombre = NOMBRE_ID3;
                break;
            case 4:
                nombre = NOMBRE_ID4;
                break;
        }
    }

    private void slicesSegunId() {
        switch (id) {
            case 1:
                slices = SLICES_PEQUENA;
                break;
            case 2:
                slices = SLICES_MEDIANA;
                break;
            case 3:
                slices = SLICES_GRANDE;
                break;
            case 4:
                slices = SLICES_FIESTA;
                break;
        }
    }
}
