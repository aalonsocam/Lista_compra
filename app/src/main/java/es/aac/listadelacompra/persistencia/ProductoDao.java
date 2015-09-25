package es.aac.listadelacompra.persistencia;

import es.aac.listadelacompra.entidades.Producto;

/**
 * Created by manana on 23/09/15.
 */
public interface ProductoDao {
    long insert(String nombre, int idPadre);
    long insert(Producto producto);
    long update(Producto producto);
    long delete(Producto producto);
    Producto GetListaProductos();
}
