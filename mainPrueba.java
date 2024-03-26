import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.*;

public class mainPrueba {
    public static void main(String[] args) {
        StockArticulosPrueba leche = new StockArticulosPrueba("LECHE", 1.20, true, 170);
        StockArticulosPrueba.inventario.add(leche);

        StockArticulosPrueba pan = new StockArticulosPrueba("PAN", 0.80, true, 150);
        StockArticulosPrueba.inventario.add(pan);

        StockArticulosPrueba huevos = new StockArticulosPrueba("HUEVOS", 1.80, true, 210);
        StockArticulosPrueba.inventario.add(huevos);

        StockArticulosPrueba arroz = new StockArticulosPrueba("ARROZ", 1.50, false, 200);
        StockArticulosPrueba.inventario.add(arroz);

        StockArticulosPrueba pasta = new StockArticulosPrueba("PASTA", 1.40, false, 190);
        StockArticulosPrueba.inventario.add(pasta);

        StockArticulosPrueba tomates = new StockArticulosPrueba("TOMATES", 2.20, false, 180);
        StockArticulosPrueba.inventario.add(tomates);

        StockArticulosPrueba patatas = new StockArticulosPrueba("PATATAS", 2.00, false, 220);
        StockArticulosPrueba.inventario.add(patatas);

        StockArticulosPrueba manzanas = new StockArticulosPrueba("MANZANAS", 1.60, false, 200);
        StockArticulosPrueba.inventario.add(manzanas);

        StockArticulosPrueba pollo = new StockArticulosPrueba("POLLO", 4.50, true, 190);
        StockArticulosPrueba.inventario.add(pollo);

        StockArticulosPrueba queso = new StockArticulosPrueba("QUESO", 3.20, true, 200);
        StockArticulosPrueba.inventario.add(queso);

        // PRUEBA PARA NO AÑADIR MÁS DE 20
        
        // StockArticulosPrueba cerveza = new StockArticulosPrueba("CERVEZA", 1.80, true, 180);
        // StockArticulosPrueba.inventario.add(cerveza);

        // StockArticulosPrueba vino = new StockArticulosPrueba("VINO", 8.50, true, 170);
        // StockArticulosPrueba.inventario.add(vino);

        // StockArticulosPrueba carne = new StockArticulosPrueba("CARNE", 10.00, true, 160);
        // StockArticulosPrueba.inventario.add(carne);

        // StockArticulosPrueba yogur = new StockArticulosPrueba("YOGUR", 0.90, true, 220);
        // StockArticulosPrueba.inventario.add(yogur);

        // StockArticulosPrueba refresco = new StockArticulosPrueba("REFRESCO", 1.20, true, 190);
        // StockArticulosPrueba.inventario.add(refresco);

        // StockArticulosPrueba cafe = new StockArticulosPrueba("CAFÉ", 5.00, true, 200);
        // StockArticulosPrueba.inventario.add(cafe);

        // StockArticulosPrueba aceite = new StockArticulosPrueba("ACEITE", 3.50, false, 210);
        // StockArticulosPrueba.inventario.add(aceite);

        // StockArticulosPrueba chocolate = new StockArticulosPrueba("CHOCOLATE", 2.00, false, 220);
        // StockArticulosPrueba.inventario.add(chocolate);

        // StockArticulosPrueba galletas = new StockArticulosPrueba("GALLETAS", 1.30, false, 200);
        // StockArticulosPrueba.inventario.add(galletas);

        // StockArticulosPrueba agua = new StockArticulosPrueba("AGUA", 0.60, false, 230);
        // StockArticulosPrueba.inventario.add(agua);

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
        panelProductos(productos, productosTXT);
        panelBotones(frame, botones, productos, productosTXT);

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
        JLabel titulArticulos = new JLabel("L  I  D  L");
        titulArticulos.setFont(new Font("Arial", Font.BOLD, 55));
        titulArticulos.setForeground(Color.YELLOW);
        titulArticulos.setAlignmentX(Component.CENTER_ALIGNMENT);
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
                    "Articulo " + (i+1) + " >  " + producto + ": " + precioFormateado + "€ / " + stock
                            + " unidades en stock");
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

        JButton anadir = new JButton(escalarImagen("Iconos\\paquetes.png"));
        modificarBoton(anadir);
        JButton comprar = new JButton(escalarImagen("Iconos\\carrito-compra.png"));
        modificarBoton(comprar);
        JButton lista = new JButton(escalarImagen("Iconos\\lista-colores.png"));
        modificarBoton(lista);
        JButton lupa = new JButton(escalarImagen("Iconos\\buscar-compra-escalada.png"));
        modificarBoton(lupa);

        accionesLista(lista, productosTXT);
        accionesAnadir(frame, anadir, productos, productosTXT);
        accionesComprar(frame, comprar, productos, productosTXT);

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
        ImageIcon imagenEscalada = new ImageIcon(imagen.getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH));
        return imagenEscalada;
    }

    public static void accionesLista(JButton lista, StringBuilder productosTXT) {
        lista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(productosTXT.toString());
            }
        });
    }

    public static void accionesComprar(JFrame frame, JButton comprar, JPanel productos, StringBuilder productosTXT) {
        ArrayList<String> arrayProductos = new ArrayList<>();
        HashMap<String, Integer> cesta = new HashMap<>();
        ArrayList<Double> precios = new ArrayList<>();
        DecimalFormat dosDecimales = new DecimalFormat("0.00");
        StringBuilder ticket = new StringBuilder("TICKET LIDL\n\n");

        comprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ticket.setLength(0);
                ticket.append("TICKET LIDL\n\n");
                int qty = 0;
                
                UIManager.put("OptionPane.yesButtonText", "Sí");
                UIManager.put("OptionPane.noButtonText", "No");

                int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres definir la longitud de tu compra?", "Cesta Lidl", JOptionPane.YES_NO_CANCEL_OPTION);

                if (respuesta == JOptionPane.CANCEL_OPTION || respuesta == JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada", "Error 404", JOptionPane.ERROR_MESSAGE);
                } else if (respuesta == JOptionPane.NO_OPTION) {
                    panelesGenerativos(dosDecimales, ticket, arrayProductos, cesta, precios);


                } else if (respuesta == JOptionPane.YES_OPTION) {
                    int i = 1;
                    qty = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de articulos a comprar", "Comprar varios articulos", JOptionPane.QUESTION_MESSAGE));
                    while (i <= qty) {
                    System.out.println(cesta);
                    i++;
                    }
                }
            }
        });
    }

    public static void panelesGenerativos(DecimalFormat dosDecimales, StringBuilder ticket, ArrayList<String> arrayProductos, HashMap<String, Integer> cesta, ArrayList<Double> precios) {
        boolean error = false;
        boolean masArticulos = true;

        for (StockArticulosPrueba producto : StockArticulosPrueba.inventario) {
            arrayProductos.add(producto.getNombre());
        }
        while (!error && masArticulos) {
            if (arrayProductos.size() < 1) { //NO ENTIENDO
                JOptionPane.showMessageDialog(null, "¡ ¡ ÚLTIMO ARTÍCULO ! !", "Máximo de productos", JOptionPane.WARNING_MESSAGE);
                break;
            }
            JPanel panelComprar = new JPanel(new GridLayout(0, 2));

            panelComprar.add(new JLabel("Nombre del producto: "));
            JComboBox<String> seleccionable = crearSeleccionable(arrayProductos);
            panelComprar.add(seleccionable);

            JLabel textoPrecioPanel = new JLabel("Precio   /+IVA            ➟");
            JLabel precioPanel = new JLabel("0.0   /0.0");
            
            // Hacer que muestre el precio del artículo seleccionado según se selecciona
            mostrarPrecio(dosDecimales, seleccionable, precioPanel, precios);

            panelComprar.add(textoPrecioPanel);
            panelComprar.add(precioPanel);

            panelComprar.add(new JLabel("Cantidad:"));
            JTextField cantidadTxt = new JTextField(10);
            panelComprar.add(cantidadTxt);

            panelComprar.add(new JLabel("¿Más articulos?"));
            JCheckBox masArticulosCheck = new JCheckBox();
            panelComprar.add(masArticulosCheck);

            int confirmacion = JOptionPane.showConfirmDialog(null, panelComprar, "Cesta Lidl", JOptionPane.OK_CANCEL_OPTION);

            //  ERRORES
            masArticulos = masArticulosCheck.isSelected();
            
            if (confirmacion == JOptionPane.CANCEL_OPTION || confirmacion == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación cancelada", "Error 404", JOptionPane.ERROR_MESSAGE);
                error = true;
            } else {
                String productoStr = (String) seleccionable.getSelectedItem();
                if (productoStr != null && !productoStr.equals("- Seleccionar producto -")) {
                    Policias poliCantidad = new Policias(cantidadTxt, "enteros");
                    if (poliCantidad.getBoolean()) {
                        int cantidad = Integer.parseInt(cantidadTxt.getText());
                        int stockProducto = 0;
                        for (StockArticulosPrueba producto : StockArticulosPrueba.inventario) {
                            String objetoProducto = producto.getNombre();
                            if (productoStr.equals(objetoProducto)) {
                                stockProducto = producto.getCantidad();
                                if (cantidad > stockProducto) {
                                    JOptionPane.showMessageDialog(null, "El stock del producto es de " + stockProducto
                                    + " y usted esta intentando comprar " + cantidad, "Error 404", JOptionPane.ERROR_MESSAGE);
                                    error = true;
                                } else {
                                    //      CODIGO
                                    stockProducto -= cantidad;
                                    producto.setCantidad(stockProducto);
                                    arrayProductos.remove(productoStr);
                                    cesta.put(productoStr, cantidad);

                                    ticket.append("Artículo: \""+productoStr+"\"\n    Cantidad comprada: "+ cantidad);
                                    precio_precioCantidad(ticket, dosDecimales, productoStr, cantidad);
                                }
                            }
                        }
                    //      ERRORES
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
            }
        }
        if (!error) {
            String ticketMostrar = StockArticulosPrueba.crearTicket(cesta, dosDecimales, ticket, precios);
            JOptionPane.showMessageDialog(null, ticketMostrar, "Ticket", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void precio_precioCantidad(StringBuilder ticket, DecimalFormat dosDecimales, String productoStr, int cantidad) {
        StockArticulosPrueba articulo = buscar(productoStr);
        double precio = articulo.getPrecio();
        double precioCantidad = precio * cantidad;
        ticket.append("\n    Precio  /* Cantidad: "+ dosDecimales.format(precio) +"   / " + dosDecimales.format(precioCantidad)+"\n\n");
    }

    public static void mostrarPrecio(DecimalFormat dosDecimales, JComboBox<String> seleccionable, JLabel precioPanel, ArrayList<Double> precios) {
        seleccionable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreProducto = (String) seleccionable.getSelectedItem();
                StockArticulosPrueba producto = buscar(nombreProducto);

                double precio = producto.getPrecio();
                String precioFormateado = dosDecimales.format(precio);
                double iva = producto.getIVA();
                double precioIVA = precio * iva;
                String precioConIva = dosDecimales.format(precioIVA);
                precioPanel.setText(precioFormateado + "   /"+precioConIva);
                precios.add(precioIVA);
            }
        });
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

    public static void ticket(DecimalFormat dosDecimales, StringBuilder texto, double totalCompra) {
        String laMulta = dosDecimales.format(totalCompra);
        double losBilletes = Double
                .parseDouble(JOptionPane.showInputDialog(null, texto.toString() + "\nTotal a pagar: " + laMulta));
        double cambio = losBilletes - totalCompra;
        String laCalderilla = dosDecimales.format(cambio);
        JOptionPane.showMessageDialog(null,
                "Has pagado el precio de " + laMulta + " con " + losBilletes + "\nTus vueltas son ==> " + laCalderilla);
    }

    public static JComboBox<String> crearSeleccionable(ArrayList<String> inventario) {
        String[] productos = new String[(inventario.size() + 1)];
        productos[0] = "- Seleccionar producto -";

        for (int i = 0; i < inventario.size(); i++) {
            String producto = inventario.get(i);
            productos[(i+1)] = producto;
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
                            "Articulo " + (i+1) + " >  " + producto + ": " + precioFormateado + "€ / " + stock
                                    + " unidades en stock");
                    labelProductos.setForeground(Color.BLACK);
                    productosTXT.append(
                            "Articulo: '" + producto + "'\n\t" + precioFormateado + "€(EUROS)\n\t" + stock
                                    + " unidades en stock\n");
                    productos.add(labelProductos);
                    productos.add(Box.createVerticalStrut(5));
                } else {
                    JOptionPane.showMessageDialog(null, "Has llegado al máximo de Articulos posible");
                }
            }
            productos.revalidate();
            productos.repaint();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}