package es.aac.listadelacompra.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by manana on 16/09/15.
 */
public class GrupoProductos {

    private String nombre;
    private Producto contenedor;
    private TreeMap<Integer, Producto> miembros;

    public GrupoProductos() {
        this("");
    }

    public GrupoProductos(Producto padre) {
        this(padre.getNombre());
        this.contenedor = padre;
    }

    public GrupoProductos(String nombre) {
        this.nombre = nombre;
        miembros = new TreeMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public GrupoProductos getPadre() {
        return contenedor != null ? contenedor.getGrupoPadre() : null;
    }

    public Producto get(int key) {
        return miembros.get(key);
    }

    public void add(Producto producto) {
        producto.setGrupoPadre(this);
        miembros.put(producto.getId(), producto);
    }

    public void remove(Producto producto) {
        miembros.remove(producto.getId());
    }

    public Boolean containsKey(int key) {
        return miembros.containsKey(key);
    }

    public int size() {
        return miembros.size();
    }

    public Set<Map.Entry<Integer, Producto>> entrySet() {
        return miembros.entrySet();
    }

    public List<Producto> getLista() {
        List<Producto> lista = new ArrayList<>();

        for (TreeMap.Entry<Integer, Producto> item : miembros.entrySet()) {
            lista.add(item.getValue());
        }

        Collections.sort(lista);

        return lista;
    }

    @Override
    public String toString() {
        return "GrupoProductos{" +
                "nombre='" + nombre + '\'' +
                ", padre=" + contenedor +
                '}';
    }
}
