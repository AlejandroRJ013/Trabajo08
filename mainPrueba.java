import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

public class mainPrueba {
    public static void main(String[] args) {
        StockArticulosPrueba leche = new StockArticulosPrueba("LECHE", 1.10, true, 20);
            StockArticulosPrueba.inventario.add(leche);
        StockArticulosPrueba pan = new StockArticulosPrueba("PAN", 0.90, true, 30);
            StockArticulosPrueba.inventario.add(pan);
        StockArticulosPrueba huevos = new StockArticulosPrueba("HUEVOS", 2.50, true, 40);
            StockArticulosPrueba.inventario.add(huevos);
        StockArticulosPrueba arroz = new StockArticulosPrueba("ARROZ", 1.30, false, 15);
            StockArticulosPrueba.inventario.add(arroz);
        StockArticulosPrueba pasta = new StockArticulosPrueba("PASTA", 1.25, false, 25);
            StockArticulosPrueba.inventario.add(pasta);
        StockArticulosPrueba tomates = new StockArticulosPrueba("TOMATES", 2.35, false, 35);
            StockArticulosPrueba.inventario.add(tomates);
        StockArticulosPrueba patatas = new StockArticulosPrueba("PATATAS", 2.10, false, 10);
            StockArticulosPrueba.inventario.add(patatas);
        StockArticulosPrueba manzanas = new StockArticulosPrueba("MANZANAS", 1.75, false, 45);
            StockArticulosPrueba.inventario.add(manzanas);


        ventana();
    }

    public static void ventana() {
        StringBuilder productosTXT = new StringBuilder("");
        JFrame frame = new JFrame("Almacen LIDL");
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints posicion = new GridBagConstraints();
        posicion.fill = GridBagConstraints.BOTH; // Rellenar vertical y horizontalmente
        posicion.weightx = 1; // Permitir que los componentes se expandan horizontalmente
        posicion.weighty = 1; // Permitir que los componentes se expandan verticalmente

        JPanel tituloLIDL = new JPanel();
        JPanel botones = new JPanel();
        JPanel productos = new JPanel();

        panelTitulo(tituloLIDL);
        panelBotones(frame, botones, productos, productosTXT);
        panelProductos(productos, productosTXT);

        posicion.gridy = 0;
        panelPrincipal.add(tituloLIDL, posicion);

        posicion.gridy = 1;
        panelPrincipal.add(botones, posicion);

        posicion.gridy = 2;
        panelPrincipal.add(productos, posicion);

        frame.add(panelPrincipal, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void panelTitulo(JPanel tituloLIDL) {
        tituloLIDL.setBackground(Color.BLUE);
        JLabel titulArticulos = new JLabel("ARTICULOS LIDL");
        titulArticulos.setFont(new Font("Arial", Font.BOLD, 35));
        titulArticulos.setForeground(Color.YELLOW);
        titulArticulos.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulArticulos.setBorder(new EmptyBorder(5, 10, 5, 10));
        tituloLIDL.add(titulArticulos);
    }

    public static void panelProductos(JPanel productos, StringBuilder productosTXT) {
        productos.setBackground(Color.LIGHT_GRAY);
        productos.setLayout(new BoxLayout(productos, BoxLayout.Y_AXIS));
        productos.setBorder(new EmptyBorder(5, 10, 7, 10));
        DecimalFormat decimales = new DecimalFormat("0.00");
        for (int i = 0; i < StockArticulosPrueba.inventario.size(); i++) {
            StockArticulosPrueba articulo = StockArticulosPrueba.inventario.get(i);
            String producto = articulo.getNombre();
            double precio = articulo.getPrecio();
            String precioFormateado = decimales.format(precio);
            int stock = articulo.getCantidad();

            JLabel labelProductos = new JLabel(
                    "Artículo " + (i+1) + " >  " + producto + ": " + precioFormateado + "€ / " + stock
                            + " unidades en stock");
            labelProductos.setForeground(Color.BLACK);
            productosTXT.append(
                    "Articulo: '" + producto + "'\n\t" + precioFormateado + "€(EUROS)\n\t" + stock
                            + " unidades en stock\n");
            productos.add(labelProductos);
            productos.add(Box.createVerticalStrut(5)); // Agregar relleno vertical entre los componentes
        }
    }

    public static void panelBotones(JFrame frame, JPanel botones, JPanel productos, StringBuilder productosTXT) {
        botones.setLayout(new GridLayout(0, 4));
        botones.setBackground(Color.GRAY);

        JButton anadir = new JButton(escalarImagen("Iconos\\stock.png"));
        modificarBoton(anadir);
        JButton comprar = new JButton(escalarImagen("Iconos\\cart-check.png"));
        modificarBoton(comprar);
        JButton lista = new JButton(escalarImagen("Iconos\\list-task.png"));
        modificarBoton(lista);
        JButton lupa = new JButton(escalarImagen("Iconos\\search.png"));
        modificarBoton(lupa);

        accionesAnadir(frame, anadir, productos, productosTXT);

        botones.add(comprar);
        botones.add(anadir);
        botones.add(lista);
        botones.add(lupa);
    }

    public static void modificarBoton(JButton boton) {
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public static ImageIcon escalarImagen(String ruta) {
        ImageIcon imagen = new ImageIcon(ruta);
        ImageIcon imagenEscalada = new ImageIcon(imagen.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        return imagenEscalada;
    }

    public static void accionesComprar(JFrame frame, JButton comprar, JPanel productos, StringBuilder productosTXT) {
        DecimalFormat dosDecimales = new DecimalFormat("0.00");
        ArrayList<String> arrayProductos = new ArrayList<>();
        comprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //EMPIEZAN LAS ACCIONES DEL BOTON
                arrayProductos.removeAll(arrayProductos);

                for (int i = 0; i < StockArticulosPrueba.inventario.size(); i++) {
                    StockArticulosPrueba articulo = StockArticulosPrueba.inventario.get(i);
                        if (i <= 20) {
                            String producto = articulo.getNombre();
                            double precio = articulo.getPrecio();
                            String precioFormateado = dosDecimales.format(precio);
                            int stock = articulo.getCantidad();
                        }
                    }

                    for (int i = 0; i < StockArticulosPrueba.inventario.size(); i++) {
                        // objeto.getStock
                        inventario.getNombre().add(producto);
                    }

                JPanel infoArticulos = new JPanel(new GridLayout(0, 2));

                infoArticulos.add(new JLabel("Nombre del producto: "));
                JComboBox<String> seleccionable = crearSeleccionable(StockArticulosPrueba.inventario.get(0));
                infoArticulos.add(seleccionable);

                infoArticulos.add(new JLabel("Cantidad:"));
                JTextField cantidadTxt = new JTextField(10);
                infoArticulos.add(cantidadTxt);

                
                panelCrearTicket(arrayProductos, productoStock, productoPrecio, dosDecimales);

                actualizarProductos(frame, productoStock, productoPrecio, productos, productosTXT);
            }
        });
    }

    public static JComboBox<String> crearSeleccionable(ArrayList<String> arrayProductos) {
        String[] productos = new String[(arrayProductos.size() + 1)];
        productos[0] = "- Seleccionar producto -";
        int i = 1;
        for (String producto : arrayProductos) {
            productos[i] = producto;
            i++;
        }
        JComboBox<String> seleccionable = new JComboBox<>(productos);

        return seleccionable;
    }

    public static void accionesAnadir(JFrame frame, JButton anadir, JPanel productos, StringBuilder productosTXT) {
        anadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel cantidad = new JPanel(new GridLayout(0, 2));

                cantidad.add(new JLabel("Cantidad del producto: "));
                JTextField cantidadTxt = new JTextField(10);
                cantidad.add(cantidadTxt);

                int opcion = JOptionPane.showConfirmDialog(null, cantidad, "Ingrese la cantidad", JOptionPane.OK_CANCEL_OPTION);

                boolean error = false;
                int qty = 0;
                try {
                    qty = Integer.parseInt(cantidadTxt.getText());
                    error = false;
                } catch (Exception et) {
                    error = true; 
                }  

                if (opcion == JOptionPane.CANCEL_OPTION || opcion == JOptionPane.CLOSED_OPTION || error) {
                    JOptionPane.showMessageDialog(null, "No se ha introducido una cantidad válida", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (int i = 1; i <= qty; i++) {
                        StockArticulosPrueba.agregarArticulo();
                    }
                    actualizarProductos(frame, productos, productosTXT);
                }
            }
        });
    }

    public static void actualizarProductos(JFrame frame, JPanel productos, StringBuilder productosTXT) {

        frame.setVisible(false);
        frame.setSize(1000, 1000);
        productos.removeAll();
        DecimalFormat decimales = new DecimalFormat("0.00");
        for (int i = 0; i < StockArticulosPrueba.inventario.size(); i++) {
            StockArticulosPrueba articulo = StockArticulosPrueba.inventario.get(i);
                if (i <= 20) {
                    String producto = articulo.getNombre();
                    double precio = articulo.getPrecio();
                    String precioFormateado = decimales.format(precio);
                    int stock = articulo.getCantidad();


                    JLabel labelProductos = new JLabel(
                            "Artículo " + (i+1) + " >  " + producto + ": " + precioFormateado + "€ / " + stock
                                    + " unidades en stock");
                    labelProductos.setForeground(Color.BLACK);
                    productosTXT.append(
                            "Articulo: '" + producto + "'\n\t" + precioFormateado + "€(EUROS)\n\t" + stock
                                    + " unidades en stock\n");
                    productos.add(labelProductos);
                    productos.add(Box.createVerticalStrut(5));
                } else {
                    JOptionPane.showMessageDialog(null, "Has llegado al máximo de artículos posible");
                }
            }
            productos.revalidate();
            productos.repaint();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}