package es.aac.listadelacompra.vistas;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import es.aac.listadelacompra.ListaCompraAdapter;
import es.aac.listadelacompra.ListaCompraApplication;
import es.aac.listadelacompra.R;

public class Compra extends Fragment {

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compra, container, false);

        final ListView listado = (ListView) view.findViewById(R.id.lvCompra);

        Activity activity = getActivity();
        final ListaCompraAdapter adaptador = new ListaCompraAdapter(activity, R.layout.producto_adapter);
        ListaCompraApplication app = (ListaCompraApplication) activity.getApplicationContext();

        adaptador.setListaProductos(app.getListaCompra());
        listado.setAdapter(adaptador);

        return view;
    }

}
