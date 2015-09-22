package es.aac.listadelacompra;

/**
 * Created by manana on 17/09/15.
 */
public class Persistencia {

    private GrupoProductos lista;

    public GrupoProductos getListaProductos() {
        return lista;
    }

    public Persistencia() {

        lista = new GrupoProductos("Lista de productos");

        Producto carnes = new Producto(1000, "Carnes", null);
        Producto pescados = new Producto(2000, "Pescados", null);
        Producto frutas = new Producto(3000, "Frutas", null);
        Producto verduras = new Producto(4000, "Verduras", null);
        Producto varios = new Producto(10000, "Varios", null);

        Producto vacuno = new Producto(1100, "Vacuno", carnes);
        new Producto(1101, "Entrecot", vacuno);
        new Producto(1102, "Solomillo", vacuno);
        new Producto(1103, "Carne picada", vacuno);
        new Producto(1104, "Chuletón", vacuno);

        new Producto(1200, "Cerdo", carnes);
        new Producto(1300, "Cordero", carnes);
        new Producto(1400, "Pollo", carnes);

        new Producto(2010, "Bacalao", pescados);
        new Producto(2020, "Merluza", pescados);
        new Producto(2030, "Lenguado", pescados);
        new Producto(2040, "Lubina", pescados);

        new Producto(3001, "Naranja", frutas);
        new Producto(3002, "Manzana", frutas);
        new Producto(3003, "Pera", frutas);
        new Producto(3004, "Melocotón", frutas);

        new Producto(4001, "Berenjena", verduras);
        new Producto(4002, "Calabacín", verduras);
        new Producto(4003, "Lechuga", verduras);
        new Producto(4004, "Coliflor", verduras);

        lista.add( carnes );
        lista.add( pescados );
        lista.add( frutas );
        lista.add( verduras );
        lista.add( varios );
    }
}
