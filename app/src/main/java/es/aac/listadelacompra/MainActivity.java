package es.aac.listadelacompra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvNombreGrupo = (TextView) findViewById(R.id.tvNombreGrupo);
        ListView listado = (ListView) findViewById(R.id.lvProductos);

        tvNombreGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto = (Producto)v.getTag();
                Producto padre = producto.getGrupoPadre();
                if( padre != null ) {
                    Toast.makeText(MainActivity.this, padre.getNombre(), Toast.LENGTH_SHORT).show();

                    List<Producto> listaProductos;
                    listaProductos = padre.getSubGrupos();
                    adaptador.setListaProductos(listaProductos);
                }
            }
        });

        ListaCompraApplication app = (ListaCompraApplication) this.getApplicationContext();
        adaptador = new ProductoAdapter(this, R.layout.producto_adapter, app.getListaProductos(), tvNombreGrupo);
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
}
