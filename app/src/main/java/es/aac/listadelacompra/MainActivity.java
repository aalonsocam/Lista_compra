package es.aac.listadelacompra;


import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import es.aac.listadelacompra.vistas.Compra;
import es.aac.listadelacompra.vistas.Favoritos;
import es.aac.listadelacompra.vistas.Productos;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;
    private float lastX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("fragment_compra").setIndicator(getString(R.string.listaDeLaCompra)), Compra.class, null);
        tabHost.addTab(tabHost.newTabSpec("fragment_productos").setIndicator(getString(R.string.listaDeProductos)), Productos.class, null);
        tabHost.addTab(tabHost.newTabSpec("fragment_favoritos").setIndicator(getString(R.string.listaDeFavoritos)), Favoritos.class, null);
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


    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = touchevent.getX();
                break;

            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();
                int tabActual = tabHost.getCurrentTab();

                if (lastX < currentX)
                    tabHost.setCurrentTab(tabActual > 0 ? tabActual - 1 : tabHost.getTabWidget().getTabCount() - 1);

                if (lastX > currentX)
                    tabHost.setCurrentTab(tabActual < tabHost.getTabWidget().getTabCount() - 1 ? tabActual + 1 : 0);

                break;
        }
        return false;
    }
}
