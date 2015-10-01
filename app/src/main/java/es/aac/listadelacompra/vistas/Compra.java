package es.aac.listadelacompra.vistas;


import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import es.aac.listadelacompra.ListaCompraAdapter;
import es.aac.listadelacompra.ListaCompraApplication;
import es.aac.listadelacompra.R;
import es.aac.listadelacompra.entidades.EstadoProductoObserver;
import es.aac.listadelacompra.entidades.GrupoProductos;
import es.aac.listadelacompra.entidades.ListaCompraObserver;
import es.aac.listadelacompra.entidades.Producto;

public class Compra extends Fragment {

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compra, container, false);

        Activity activity = getActivity();
        final ListView listado = (ListView) view.findViewById(R.id.lvCompra);
        final ListaCompraAdapter adaptador = new ListaCompraAdapter(activity, R.layout.producto_adapter);
        final ListaCompraApplication app = (ListaCompraApplication) activity.getApplicationContext();
/*
        adaptador.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }
        });
*/
        adaptador.registerEstadoProductoObserver(new EstadoProductoObserver() {
            @Override
            public void EstadoChanged(Producto p) {
                //Toast.makeText(getContext(), p.toString(), Toast.LENGTH_LONG).show();
            }
        });

        app.registerListaCompraObserver(new ListaCompraObserver() {
            @Override
            public void OnChanged(GrupoProductos grupoProductos) {
                adaptador.setListaProductos(grupoProductos);
            }
        });

        adaptador.setListaProductos(app.getListaCompra());
        listado.setAdapter(adaptador);

        return view;
    }

}
