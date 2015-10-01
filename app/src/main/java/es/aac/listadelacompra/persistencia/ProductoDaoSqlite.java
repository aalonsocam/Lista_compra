package es.aac.listadelacompra.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.TreeMap;

import es.aac.listadelacompra.entidades.GrupoProductos;
import es.aac.listadelacompra.entidades.Producto;

/**
 * Created by manana on 23/09/15.
 */
public class ProductoDaoSqlite implements ProductoDao {

    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_ID_PADRE = "id_padre";
    final String TABLA = "productos";
    private SQLiteDatabase db;

    public ProductoDaoSqlite(SQLiteDatabase db) {
        this.db = db;
    }

    @NonNull
    private ContentValues getContentValues(Producto producto) {
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_ID, producto.getId());
        cv.put(CAMPO_NOMBRE, producto.getNombre());
        cv.put(CAMPO_ID_PADRE, producto.getIdPadre());
        return cv;
    }

    @Override
    public long insert(String nombre, int idPadre) {
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_ID, "");
        cv.put(CAMPO_NOMBRE, nombre);
        cv.put(CAMPO_ID_PADRE, idPadre);
        return db.insert(TABLA, null, cv);
    }

    @Override
    public long insert(Producto producto) {
        return db.insert(TABLA, null, getContentValues(producto));
    }

    @Override
    public long update(Producto producto) {
        String[] args = {String.valueOf(producto.getId())};
        return db.update(TABLA, getContentValues(producto), CAMPO_ID + "=?", args);
    }

    @Override
    public long delete(Producto producto) {
        String[] args = {String.valueOf(producto.getId())};
        return db.update(TABLA, getContentValues(producto), CAMPO_ID + "=?", args);
    }

    public GrupoProductos GetListaProductos() {

        GrupoProductos listadoBd = new GrupoProductos();
        GrupoProductos lista = new GrupoProductos("Productos básicos");

        Cursor cursor = db.query(TABLA, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int iCampoId = cursor.getColumnIndex(CAMPO_ID);
            int iCampoNombre = cursor.getColumnIndex(CAMPO_NOMBRE);
            int iCampoIdPadre = cursor.getColumnIndex(CAMPO_ID_PADRE);

            do
                listadoBd.add(new Producto(cursor.getInt(iCampoId), cursor.getString(iCampoNombre), cursor.getInt(iCampoIdPadre)));
            while (cursor.moveToNext());
        }

        for (TreeMap.Entry<Integer, Producto> item : listadoBd.entrySet()) {
            Producto p = item.getValue();
            generaArbolProductos(lista, p, listadoBd);
        }

        return lista;
    }

    private void generaArbolProductos(GrupoProductos raiz, Producto p, GrupoProductos lista) {

        if (p != null) {
            if (p.getIdPadre() != 0) {
                if (p.getIdPadre() != p.getId() && lista.containsKey(p.getIdPadre())) {
                    Producto padre = lista.get(p.getIdPadre());
                    generaArbolProductos(raiz, padre, lista);
                    padre.addSubProducto(p);
                }
            } else {
                raiz.add(p);
            }
        }
    }
}
