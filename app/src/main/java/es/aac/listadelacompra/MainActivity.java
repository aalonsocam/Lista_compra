package es.aac.listadelacompra;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Stack;

import es.aac.listadelacompra.entidades.Producto;

public class MainActivity extends AppCompatActivity {

    View.OnClickListener listenerMigaDePan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listado = (ListView) findViewById(R.id.lvProductos);
        final LinearLayout migasDePan = (LinearLayout) findViewById(R.id.migasDePan);
        final listaProductosAdapter adaptador = new listaProductosAdapter(this, R.layout.producto_adapter);
        ListaCompraApplication app = (ListaCompraApplication) this.getApplicationContext();

        listenerMigaDePan = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = (Producto) v.getTag();
                if (p != null) {
                    adaptador.setListaProductos(p);
                }
            }
        };

        adaptador.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                //Toast.makeText(MainActivity.this, this.getClass().toString(), Toast.LENGTH_SHORT).show();
                Producto p = adaptador.getGrupoListaProductos();
                actualizaMigasDePan(migasDePan, p);
            }
        });

        adaptador.setListaProductos(app.getListaProductos());
        listado.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void actualizaMigasDePan(LinearLayout migasDePan, Producto producto) {

        Stack<Producto> pila = new Stack<>();
        migasDePan.removeAllViews();

        do {
            pila.add(producto);
            producto = producto.getProductoPadre();
        } while (producto != null);

        while (!pila.empty()) {
            producto = pila.pop();
            TextView t = new TextView(this);
            t.setPadding(0, 10, 10, 10);
            t.setText(producto.getNombre() + " >");
            t.setTag(producto);
            t.setOnClickListener(listenerMigaDePan);
            migasDePan.addView(t);
        }
    }
}
