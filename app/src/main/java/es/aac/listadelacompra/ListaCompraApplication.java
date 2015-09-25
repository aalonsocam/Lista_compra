package es.aac.listadelacompra;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import es.aac.listadelacompra.entidades.Producto;
import es.aac.listadelacompra.persistencia.ListaCompraSQlite;
import es.aac.listadelacompra.persistencia.ProductoDaoSqlite;

/**
 * Created by manana on 16/09/15.
 */
public class ListaCompraApplication extends Application {

    private Producto listaProductos;

    public Producto getListaProductos() {
        return listaProductos;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SQLiteDatabase db = new ListaCompraSQlite(this, "lista_compra_db", null, 1).getWritableDatabase();

        listaProductos = new ProductoDaoSqlite(db).GetListaProductos();
    }
}
