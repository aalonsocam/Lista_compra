package es.aac.listadelacompra;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by manana on 16/09/15.
 */
public class Producto implements Comparable<Producto> {

    private int id;
    private String nombre;
    private Producto grupoPadre;
    private Producto grupoPrincipal;
    private GrupoProductos listaSubGrupos;

    public Producto(int id, String nombre, Producto grupoPadre) {
        this.id = id;
        this.nombre = nombre;
        this.grupoPadre = grupoPadre;
        listaSubGrupos = new GrupoProductos(nombre);

        if (grupoPadre != null) {
            grupoPadre.addSubproducto(this);
            grupoPrincipal = grupoPadre.getGrupoPrincipal();
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Producto getGrupoPadre() {
        return grupoPadre;
    }

    public Producto getGrupoPrincipal() {
        return grupoPrincipal;
    }

    public void addSubproducto(Producto item) {
        listaSubGrupos.put(item.getId(), item);
    }

    public void remove(Producto item) {
        listaSubGrupos.remove(item.getId());
    }

    public List<Producto> getSubGrupos() {
        return listaSubGrupos.getLista();
    }

    public boolean tieneSubgrupos() {
        return listaSubGrupos.size() > 0;
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

        if( grupoPadre != null )
            ret += ", grupoPadre=" + grupoPadre.getNombre();

        if( grupoPrincipal != null )
            ret += ", grupoPrincipal=" + grupoPrincipal.getNombre();

        ret += '}';

        return ret;
    }
}
