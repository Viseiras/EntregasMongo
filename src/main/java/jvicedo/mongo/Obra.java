package jvicedo.mongo;

public class Obra {

    private Long id;
    private String  nombre;
    private String autor;
    private int anyoCreacion;
    private String estilo;
    private double precioVenta;

    public Obra() {
    }

    public Obra(Long id,String nombre, String autor, int anyoCreacion, String estilo, double precioVenta) {
        this.id = id;
        this.nombre= nombre;
        this.autor = autor;
        this.anyoCreacion = anyoCreacion;
        this.estilo = estilo;
        this.precioVenta = precioVenta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre= nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnyoCreacion() {
        return anyoCreacion;
    }

    public void setAnyoCreacion(int anyoCreacion) {
        this.anyoCreacion = anyoCreacion;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public String toString() {
        return  "\n\t id=" + id +
                ",\n\t nombre='" + nombre + '\'' +
                ",\n\t autor='" + autor + '\'' +
                ",\n\t anyoCreacion=" + anyoCreacion +
                ",\n\t estilo='" + estilo + '\'' +
                ",\n\t precioVenta=" + precioVenta;
    }
}
