package es.aac.listadelacompra.entidades;

import es.aac.listadelacompra.entidades.Producto;

/**
 * Created by manana on 01/10/15.
 */
public interface EstadoProductoObserver {
    public void EstadoChanged(Producto p);
}
