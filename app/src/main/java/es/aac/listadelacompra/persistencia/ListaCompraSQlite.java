package es.aac.listadelacompra.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import es.aac.listadelacompra.R;

/**
 * Created by manana on 23/09/15.
 */
public class ListaCompraSQlite extends SQLiteOpenHelper {

    private Context context;

    public ListaCompraSQlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public static void ejecutarScript(SQLiteDatabase db, String[] script) {
        try {
            db.beginTransaction();

            for (String sql : script) {
                db.execSQL(sql);
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ejecutarScript(db, context.getResources().getStringArray(R.array.scriptCreacion));
        /*String[] s = {
                "INSERT INTO productos VALUES(1001, 'Carnes', 1)",
                "INSERT INTO productos VALUES(1100, 'Vacuno', 1000)"
        };*/
        ejecutarScript(db, context.getResources().getStringArray(R.array.insertProductos));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}