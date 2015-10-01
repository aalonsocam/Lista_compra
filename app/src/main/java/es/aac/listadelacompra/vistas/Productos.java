package es.aac.listadelacompra.vistas;


import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Stack;

import es.aac.listadelacompra.ListaCompraApplication;
import es.aac.listadelacompra.ListaProductosAdapter;
import es.aac.listadelacompra.R;
import es.aac.listadelacompra.entidades.EstadoProductoObserver;
import es.aac.listadelacompra.entidades.GrupoProductos;
import es.aac.listadelacompra.entidades.Producto;

public class Productos extends Fragment {

    private ListaCompraApplication app;
    private View.OnClickListener listenerMigaDePan;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        Activity activity = getActivity();
        final ListView listado = (ListView) view.findViewById(R.id.lvProductos);
        final LinearLayout migasDePan = (LinearLayout) view.findViewById(R.id.migasDePan);
        final ListaProductosAdapter adaptador = new ListaProductosAdapter(activity, R.layout.producto_adapter);

        app = (ListaCompraApplication) activity.getApplicationContext();

        listenerMigaDePan = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GrupoProductos g = (GrupoProductos) v.getTag();
                if (g != null)
                    adaptador.setGrupoProductos(g);
            }
        };

        adaptador.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                GrupoProductos g = adaptador.getGrupoProductos();
                actualizaMigasDePan(migasDePan, g);
            }
        });

        adaptador.registerEstadoProductoObserver(new EstadoProductoObserver() {
            @Override
            public void EstadoChanged(Producto p) {
                //Toast.makeText(getContext(), p.toString(), Toast.LENGTH_LONG).show();
                app.addProductoLista(p);
            }
        });

        adaptador.setGrupoProductos(app.getListaProductos());
        listado.setAdapter(adaptador);

        return view;
    }


    private void actualizaMigasDePan(LinearLayout migasDePan, GrupoProductos grupo) {

        Stack<GrupoProductos> pila = new Stack<>();
        migasDePan.removeAllViews();

        do {
            pila.add(grupo);
            grupo = grupo.getPadre();
        } while (grupo != null);

        while (!pila.empty()) {
            TextView tv;

            if (migasDePan.getChildCount() > 0) {
                tv = new TextView(getActivity());
                tv.setPadding(10, 0, 10, 0);
                tv.setText(">");
                migasDePan.addView(tv);
            }

            grupo = pila.pop();
            tv = new TextView(getActivity());
            tv.setText(grupo.getNombre());
            tv.setTag(grupo);
            tv.setOnClickListener(listenerMigaDePan);
            migasDePan.addView(tv);
        }
    }
}
