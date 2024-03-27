import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

public class StockArticulosPrueba {
    //	ATRIBUTOS
    private String nombre;
    private double precioBruto;
    private int cantidad;
    private double IVA;
    private final double IVAesencial = 1.04;
    private final double IVAnormal = 1.21;

    // Lista para almacenar los productos
    public static ArrayList<StockArticulosPrueba> inventario = new ArrayList<>();

    //	CONSTRUCTORES
    public StockArticulosPrueba() {
        this.nombre = "##";
        this.precioBruto = 0.0;
        this.cantidad = 0;
        this.IVA = IVAnormal;
    }

    public StockArticulosPrueba(String nombre, double precioBruto, boolean esencial, int cantidad) {
        this.nombre = nombre;
        this.precioBruto = precioBruto;
        this.cantidad = cantidad;
        if (esencial) {
            this.IVA = IVAesencial;            
        } else {
        this.IVA = IVAnormal;
        }
    }

    // METODOS
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precioBruto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getIVA() {
        return IVA;
    }

    public String toString() {
        return "  -  Productos:\n\t{" +
                " Artículo='" + nombre + '\'' +
                ", Precio='" + precioBruto + '\'' +
                ", Cantidad=" + cantidad + '\'' +
                ", IVA=" + IVA + '\'' +
                '}';
    }

    public static StockArticulosPrueba buscar(String nombreProducto) {
        StockArticulosPrueba esteEs = new StockArticulosPrueba();
        for (StockArticulosPrueba producto : StockArticulosPrueba.inventario) {
            String objetoProducto = producto.getNombre();
            if (nombreProducto.equals(objetoProducto)) {
                esteEs = producto;
                break;
            }
        }
        return esteEs;
    }

    public static String crearTicket(HashMap<String, Integer> cesta, DecimalFormat dosDecimales, StringBuilder ticket, ArrayList<Double> precios) {
        double totalProducto = 0;
        double totalCompra = 0;
        int i = 0;
        for (String nomProducto : cesta.keySet()) {
            totalProducto = precios.get(i) * cesta.get(nomProducto);
            System.out.println(totalProducto);

            totalCompra +=totalProducto;
            System.out.println(totalCompra);

            i++;
        }
        String totalCompraFormateado = dosDecimales.format(totalCompra);
        ticket.append("\nEl total de la compra será: " + totalCompraFormateado);
        return ticket.toString();
    }

    public static void agregarArticulo() {
        JPanel infoArticulos = new JPanel(new GridLayout(0, 2));

        infoArticulos.add(new JLabel("Nombre del producto: "));
        JTextField productoTxt = new JTextField(10);
        infoArticulos.add(productoTxt);

        infoArticulos.add(new JLabel("Precio del producto: "));
        JTextField precioTxt = new JTextField(10);
        infoArticulos.add(precioTxt);

        infoArticulos.add(new JLabel("Cantidad:"));
        JTextField cantidadTxt = new JTextField(10);
        infoArticulos.add(cantidadTxt);

        infoArticulos.add(new JLabel("¿Artículo esencial?"));
        JCheckBox articuloEsencialCheck = new JCheckBox();
        infoArticulos.add(articuloEsencialCheck);

        int opcion = JOptionPane.showConfirmDialog(null, infoArticulos, "Titulo", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.CANCEL_OPTION || opcion == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Agregar artículo cancelado correctamente", "ERROR 404", JOptionPane.ERROR_MESSAGE);
        } else {
            
        boolean esencial = articuloEsencialCheck.isSelected() ? true : false;
        String producto = "";
        double precio = 0.0;
        int cantidad = 0;

        Policias poliProducto = new Policias(productoTxt, "letras");
        Policias poliPrecio = new Policias(precioTxt, "decimales");
        Policias poliCantidad = new Policias(cantidadTxt, "enteros");

            if (poliProducto.getBoolean()) {
                producto = productoTxt.getText().toUpperCase();

                if (poliPrecio.getBoolean()) {
                    precio = Double.parseDouble(precioTxt.getText());

                    if (poliCantidad.getBoolean()) {

                        if (inventario.size() >= 20) {
                            JOptionPane.showMessageDialog(null, "¡El inventario contiene 20 o más productos!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if (!poliProducto.getBoolean() || !poliPrecio.getBoolean() || !poliCantidad.getBoolean()) {
                                JOptionPane.showMessageDialog(null, "El artículo no se ha podido agregar", "Mensaje", JOptionPane.ERROR_MESSAGE);
                            } else {
                                cantidad = Integer.parseInt(cantidadTxt.getText());

                                StockArticulosPrueba articulos = new StockArticulosPrueba(producto, precio, esencial, cantidad);
                                inventario.add(articulos);

                                JOptionPane.showMessageDialog(null, "¡Artículo agregado correctamente!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cantidad del producto debe ser un número entero y superior a 0.", "Error 404", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El precio del producto deben ser números decimales y superior a 0.", "Error 404", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El nombre del producto solo puede tener letras.", "Error 404", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}