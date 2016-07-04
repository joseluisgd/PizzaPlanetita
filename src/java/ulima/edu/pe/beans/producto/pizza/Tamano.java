package ulima.edu.pe.beans.producto.pizza;

import java.io.Serializable;

public class Tamano implements Serializable {

    //ChF: Quizá las constantes deberían ser private
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
    private float precio;
    private String nombre;
    
    public Tamano() {
    }

    public Tamano(int id, float precio) {
        this.id = id;
        this.precio = precio;
    }

    public Tamano(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return getNombrePorId(id);
    }

    public static String getNombrePorId(int id) {
        switch (id) {
            case 1:
                return NOMBRE_ID1;
            case 2:
                return NOMBRE_ID2;
            case 3:
                return NOMBRE_ID3;
            case 4:
                return NOMBRE_ID4;
            default:
                return "";
        }
    }

    public int getSlices() {
        switch (id) {
            case 1:
                return SLICES_PEQUENA;
            case 2:
                return SLICES_MEDIANA;
            case 3:
                return SLICES_GRANDE;
            case 4:
                return SLICES_FIESTA;
            default:
                return 0;
        }
    }

    public int getMultiplicador() {
        switch (id) {
            case 1:
                return MULTIPLICADOR_PEQUENA_PERSONALIZADA;
            case 2:
                return MULTIPLICADOR_MEDIANA_PERSONALIZADA;
            case 3:
                return MULTIPLICADOR_GRANDE_PERSONALIZADA;
            case 4:
                return MULTIPLICADOR_FIESTA_PERSONALIZADA;
            default:
                return 0;
        }
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Tamano clonar() {
        return new Tamano(id, precio);
    }
}
