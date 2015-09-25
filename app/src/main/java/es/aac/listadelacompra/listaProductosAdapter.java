package es.aac.listadelacompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import java.util.List;

import es.aac.listadelacompra.entidades.Producto;

/**
 * Created by manana on 17/09/15.
 */
public class listaProductosAdapter extends BaseAdapter {

    private Context contexto;
    private int layout;
    private List<Producto> listaProductos; // Lista de productos que mostrará el ListView
    private Producto grupoListaProductos;  // Grupo al que pertenece la lista de productos (producto padre)
    private View.OnClickListener desplegarGrupoListener;
    private CheckBox.OnCheckedChangeListener seleccionaProdcutoListener;

    public listaProductosAdapter(Context contexto, int layout) {
        this.contexto = contexto;
        this.layout = layout;

        desplegarGrupoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductoAdapterDecorator productoAdapterDecorator = (ProductoAdapterDecorator) ((View) v.getParent()).getTag();
                setListaProductos(productoAdapterDecorator.producto);
            }
        };

        seleccionaProdcutoListener = new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ProductoAdapterDecorator productoAdapterDecorator = (ProductoAdapterDecorator) ((View) buttonView.getParent()).getTag();
                productoAdapterDecorator.producto.setSeleccionado(isChecked);
            }
        };
    }

    public void setListaProductos(Producto grupoProductos) {
        this.grupoListaProductos = grupoProductos;
        this.listaProductos = grupoProductos.getGrupoSubProductos().getLista();
        notifyDataSetChanged();
    }

    public Producto getGrupoListaProductos() {
        return grupoListaProductos;
    }

    @Override
    public int getCount() {
        return listaProductos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProductos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Producto producto = (Producto) getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            // Guarda en el tag del convertView las referencias a los elementos que lo componen para poder acceder
            // a ellas más tarde sin tener que volver a hacer el findViewById cada vez que se llame a getView
            ProductoAdapterDecorator productoAdapterDecorator = new ProductoAdapterDecorator();
            productoAdapterDecorator.cbProducto = (CheckBox) convertView.findViewById(R.id.cbProducto);
            productoAdapterDecorator.ivSubGrupos = (ImageView) convertView.findViewById(R.id.ivSubGrupos);
            convertView.setTag(productoAdapterDecorator);

            productoAdapterDecorator.cbProducto.setOnCheckedChangeListener(seleccionaProdcutoListener);
            productoAdapterDecorator.ivSubGrupos.setOnClickListener(desplegarGrupoListener);
        }

        ProductoAdapterDecorator productoAdapterDecorator = (ProductoAdapterDecorator) convertView.getTag();
        productoAdapterDecorator.producto = producto;
        convertView.setTag(productoAdapterDecorator);

        productoAdapterDecorator.cbProducto.setText(producto.getNombre());
        productoAdapterDecorator.cbProducto.setChecked(producto.getSeleccionado());
        productoAdapterDecorator.ivSubGrupos.setVisibility(producto.tieneSubgrupos() ? View.VISIBLE : View.INVISIBLE);

        return convertView;
    }


    // Clase para almacenar información en el tag del convertView
    private class ProductoAdapterDecorator {
        Producto producto;
        CheckBox cbProducto;
        ImageView ivSubGrupos;
    }
}
