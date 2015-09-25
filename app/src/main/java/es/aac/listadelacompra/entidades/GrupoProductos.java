package es.aac.listadelacompra.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by manana on 16/09/15.
 */
public class GrupoProductos extends TreeMap<Integer, Producto> {

    public void add(Producto producto) {
        this.put(producto.getId(), producto);
    }

    public void remove(Producto producto) {
        this.put(producto.getId(), producto);
    }

    public List<Producto> getLista() {
        List<Producto> lista = new ArrayList<>();

        for (TreeMap.Entry<Integer, Producto> item : this.entrySet()) {
            lista.add(item.getValue());
        }

        Collections.sort(lista);

        return lista;
    }
}
