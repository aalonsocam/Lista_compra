package es.aac.listadelacompra;

import android.app.Application;

import java.util.List;

/**
 * Created by manana on 16/09/15.
 */
public class ListaCompraApplication extends Application {

    private GrupoProductos listaProductos;

    public ListaCompraApplication() {
        Persistencia p = new Persistencia();
        listaProductos = p.getListaProductos();
    }

    public List<Producto> getListaProductos() {
        return listaProductos.getLista();
    }
}
