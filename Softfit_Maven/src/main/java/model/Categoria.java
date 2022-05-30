package model;


public class Categoria {
    private int codigo;
    private String nombre;
    private String seccion;
    
    public void clear() {
        codigo = 0;
        nombre = null;
        seccion = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    
    
    
}
