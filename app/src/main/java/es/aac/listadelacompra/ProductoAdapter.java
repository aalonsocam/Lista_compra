package es.aac.listadelacompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by manana on 17/09/15.
 */
public class ProductoAdapter extends BaseAdapter {

    private Context contexto;
    private int layout;
    private List<Producto> listaProductos;
    private DesplegarGrupoListener desplegarGrupoListener;
    private TextView tvNombreGrupo;

    public ProductoAdapter(Context contexto, int layout, List<Producto> listaProductos, TextView tvNombreGrupo) {
        this.listaProductos = listaProductos;
        this.contexto = contexto;
        this.layout = layout;
        this.tvNombreGrupo = tvNombreGrupo;
        desplegarGrupoListener = new DesplegarGrupoListener();
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
        notifyDataSetChanged();
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

            productoAdapterDecorator.ivSubGrupos.setOnClickListener(desplegarGrupoListener);
        }

        ProductoAdapterDecorator productoAdapterDecorator = (ProductoAdapterDecorator) convertView.getTag();
        productoAdapterDecorator.producto = producto;
        convertView.setTag(productoAdapterDecorator);

        productoAdapterDecorator.cbProducto.setText(producto.getNombre());
        productoAdapterDecorator.ivSubGrupos.setVisibility(producto.tieneSubgrupos() ? View.VISIBLE : View.INVISIBLE);


        return convertView;
    }


    // Clase para almacenar información en el tag del convertView
    private class ProductoAdapterDecorator {
        Producto producto;
        CheckBox cbProducto;
        ImageView ivSubGrupos;
    }


    // Clase para el listener sobre el icono de desplegar un grupo
    private class DesplegarGrupoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ProductoAdapterDecorator productoAdapterDecorator = (ProductoAdapterDecorator) ((View) v.getParent()).getTag();

            tvNombreGrupo.setText(productoAdapterDecorator.producto.getNombre());
            tvNombreGrupo.setTag(productoAdapterDecorator.producto);
            setListaProductos(productoAdapterDecorator.producto.getSubGrupos());
        }
    }
}
