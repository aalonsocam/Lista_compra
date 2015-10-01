package es.aac.listadelacompra;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import es.aac.listadelacompra.entidades.GrupoProductos;
import es.aac.listadelacompra.entidades.ListaCompraObserver;
import es.aac.listadelacompra.entidades.Producto;
import es.aac.listadelacompra.persistencia.ListaCompraSQlite;
import es.aac.listadelacompra.persistencia.ProductoDaoSqlite;

/**
 * Created by manana on 16/09/15.
 */
public class ListaCompraApplication extends Application {

    List<ListaCompraObserver> listaCompraObservers;
    private GrupoProductos listaCompra;
    private GrupoProductos listaProductos;

    public GrupoProductos getListaCompra() {
        return listaCompra;
    }

    public GrupoProductos getListaProductos() {
        return listaProductos;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SQLiteDatabase db = new ListaCompraSQlite(this, "lista_compra_db", null, 1).getWritableDatabase();

        listaCompra = new GrupoProductos("Lista");
        listaProductos = new ProductoDaoSqlite(db).GetListaProductos();
        listaCompraObservers = new ArrayList<>();
    }

    public void addProductoLista(Producto p) {
        listaCompra.add(p);
        for (ListaCompraObserver observer : listaCompraObservers)
            observer.OnChanged(listaProductos);
    }

    public void registerListaCompraObserver(ListaCompraObserver listener) {
        listaCompraObservers.add(listener);
    }
}
