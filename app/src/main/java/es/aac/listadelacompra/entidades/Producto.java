package es.aac.listadelacompra.entidades;

import android.support.annotation.NonNull;

/**
 * Created by manana on 16/09/15.
 */
public class Producto implements Comparable<Producto> {

    private int id;
    private String nombre;
    private int idPadre;
    private GrupoProductos grupoPrincipal;
    private GrupoProductos grupoPadre;
    private GrupoProductos grupoSubProductos;
    private Boolean seleccionado;
    private Boolean comprado;

    public Producto(int id, String nombre, Integer idPadre) {
        this.id = id;
        this.nombre = nombre;
        this.idPadre = idPadre;
        grupoSubProductos = new GrupoProductos(this);
        seleccionado = false;
        comprado = false;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public GrupoProductos getGrupoPadre() {
        return grupoPadre;
    }

    public void setGrupoPadre(GrupoProductos grupoPadre) {
        this.grupoPadre = grupoPadre;
    }

    public GrupoProductos getGrupoPrincipal() {
        return grupoPrincipal;
    }

    public GrupoProductos getGrupoSubProductos() {
        return grupoSubProductos;
    }

    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Boolean getComprado() {
        return comprado;
    }

    public void setComprado(Boolean comprado) {
        this.comprado = comprado;
    }

    public void addSubProducto(Producto producto) {
        if (grupoSubProductos.containsKey(producto.getId()) == false) {
            producto.idPadre = id;
            producto.grupoPadre = this.grupoSubProductos;
            producto.grupoPrincipal = grupoPrincipal != null ? grupoPrincipal : grupoSubProductos;
            grupoSubProductos.add(producto);
        }
    }

    public void removeSubProducto(Producto producto) {
        grupoSubProductos.remove(producto);
    }

    public boolean tieneSubgrupos() {
        return grupoSubProductos.size() > 0;
    }

    @Override
    public int compareTo(@NonNull Producto another) {
        return nombre.compareTo(another.getNombre());
    }

    @Override
    public String toString() {
        String ret = "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'';

        if (grupoPadre != null)
            ret += ", Padre=" + grupoPadre.getNombre();

        if (grupoPrincipal != null)
            ret += ", Principal=" + grupoPrincipal.getNombre();

        ret += '}';

        return ret;
    }
}
