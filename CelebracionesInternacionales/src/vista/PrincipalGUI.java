package vista;

import javax.swing.*;
import java.util.ArrayList;
import modelo.Celebracion;
import com.toedter.calendar.JDateChooser;

public class PrincipalGUI extends javax.swing.JFrame {
    private JTabbedPane pestañas;
    private ArrayList<Celebracion> listaCelebraciones;
    private int siguienteId;

    // Paneles y componentes para cada pestaña
    private JPanel panelRegistro, panelListado, panelBusqueda;
    private JTable tablaListado, tablaBusqueda;
    private JScrollPane scrollListado, scrollBusqueda;
    // Componentes de búsqueda/edición
    private JTextField txtBuscarPais, txtEditDescripcion, txtEditPais;
    private JButton btnBuscar, btnGuardarEdicion;
    private JDateChooser dateChooserEdit;
    private int indiceEdicion = -1;

    public PrincipalGUI() {
        initComponents();
        setTitle("Registro de Celebraciones Internacionales");
        setLocationRelativeTo(null);
        listaCelebraciones = new ArrayList<>();
        siguienteId = 1;
    }

    // Método para actualizar el listado general
    private void actualizarTablaListado() {
        String[] columnas = {"ID", "Fecha", "Descripción", "País"};
        Object[][] datos = new Object[listaCelebraciones.size()][4];
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        java.util.Iterator<Celebracion> it = listaCelebraciones.iterator();
        int i = 0;
        while (it.hasNext()) {
            Celebracion c = it.next();
            datos[i][0] = c.getId();
            datos[i][1] = sdf.format(c.getFecha());
            datos[i][2] = c.getDescripcion();
            datos[i][3] = c.getPais();
            i++;
        }
        tablaListado.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        pestañas = new JTabbedPane();

        // ----- PANEL REGISTRO -----
        panelRegistro = new JPanel();
        panelRegistro.setLayout(null);
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(30, 30, 100, 25);
        panelRegistro.add(lblFecha);
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(150, 30, 150, 25);
        panelRegistro.add(dateChooser);
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(30, 70, 100, 25);
        panelRegistro.add(lblDescripcion);
        JTextField txtDescripcion = new JTextField();
        txtDescripcion.setBounds(150, 70, 250, 25);
        panelRegistro.add(txtDescripcion);
        JLabel lblPais = new JLabel("País:");
        lblPais.setBounds(30, 110, 100, 25);
        panelRegistro.add(lblPais);
        JTextField txtPais = new JTextField();
        txtPais.setBounds(150, 110, 200, 25);
        panelRegistro.add(txtPais);
        JButton btnRegistrar = new JButton("Registrar Celebración");
        btnRegistrar.setBounds(150, 160, 180, 30);
        panelRegistro.add(btnRegistrar);
        btnRegistrar.addActionListener(e -> {
            java.util.Date fecha = dateChooser.getDate();
            String descripcion = txtDescripcion.getText();
            String pais = txtPais.getText();
            if (fecha == null || descripcion.isEmpty() || pais.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Celebracion nueva = new Celebracion(siguienteId, fecha, descripcion, pais);
            listaCelebraciones.add(nueva);
            siguienteId++;
            JOptionPane.showMessageDialog(this, "Celebración registrada correctamente.");
            dateChooser.setDate(null);
            txtDescripcion.setText("");
            txtPais.setText("");
        });
        pestañas.addTab("Registrar Celebración", panelRegistro);

        // ----- PANEL LISTADO DE CELEBRACIONES -----
        panelListado = new JPanel();
        panelListado.setLayout(null);
        String[] columnas = {"ID", "Fecha", "Descripción", "País"};
        Object[][] datos = {};
        tablaListado = new JTable(datos, columnas);
        scrollListado = new JScrollPane(tablaListado);
        scrollListado.setBounds(20, 20, 730, 400);
        panelListado.add(scrollListado);
        JButton btnActualizarListado = new JButton("Actualizar Listado");
        btnActualizarListado.setBounds(20, 440, 200, 30);
        panelListado.add(btnActualizarListado);
        btnActualizarListado.addActionListener(e -> actualizarTablaListado());
        pestañas.addTab("Listado de Celebraciones", panelListado);

        // ----- PANEL BUSCAR/EDITAR CELEBRACION -----
        panelBusqueda = new JPanel();
        panelBusqueda.setLayout(null);
        JLabel lblBuscarPais = new JLabel("Buscar por país:");
        lblBuscarPais.setBounds(20, 20, 120, 25);
        panelBusqueda.add(lblBuscarPais);
        txtBuscarPais = new JTextField();
        txtBuscarPais.setBounds(140, 20, 200, 25);
        panelBusqueda.add(txtBuscarPais);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(350, 20, 100, 25);
        panelBusqueda.add(btnBuscar);
        tablaBusqueda = new JTable();
        scrollBusqueda = new JScrollPane(tablaBusqueda);
        scrollBusqueda.setBounds(20, 60, 730, 200);
        panelBusqueda.add(scrollBusqueda);
        JLabel lblEditDescripcion = new JLabel("Descripción:");
        lblEditDescripcion.setBounds(20, 280, 100, 25);
        panelBusqueda.add(lblEditDescripcion);
        txtEditDescripcion = new JTextField();
        txtEditDescripcion.setBounds(140, 280, 250, 25);
        panelBusqueda.add(txtEditDescripcion);
        JLabel lblEditPais = new JLabel("País:");
        lblEditPais.setBounds(20, 320, 100, 25);
        panelBusqueda.add(lblEditPais);
        txtEditPais = new JTextField();
        txtEditPais.setBounds(140, 320, 200, 25);
        panelBusqueda.add(txtEditPais);
        JLabel lblEditFecha = new JLabel("Fecha:");
        lblEditFecha.setBounds(20, 360, 100, 25);
        panelBusqueda.add(lblEditFecha);
        dateChooserEdit = new JDateChooser();
        dateChooserEdit.setBounds(140, 360, 150, 25);
        panelBusqueda.add(dateChooserEdit);
        btnGuardarEdicion = new JButton("Guardar Cambios");
        btnGuardarEdicion.setBounds(140, 400, 180, 30);
        panelBusqueda.add(btnGuardarEdicion);
        pestañas.addTab("Buscar/Editar Celebración", panelBusqueda);

        // LÓGICA DE BÚSQUEDA Y EDICIÓN
        btnBuscar.addActionListener(e -> {
            String textoBusqueda = txtBuscarPais.getText().trim().toLowerCase();
            ArrayList<Celebracion> resultados = new ArrayList<>();
            for (Celebracion c : listaCelebraciones) {
                if (c.getPais().toLowerCase().contains(textoBusqueda)) {
                    resultados.add(c);
                }
            }
            Object[][] datosBusqueda = new Object[resultados.size()][4];
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < resultados.size(); i++) {
                Celebracion c = resultados.get(i);
                datosBusqueda[i][0] = c.getId();
                datosBusqueda[i][1] = sdf.format(c.getFecha());
                datosBusqueda[i][2] = c.getDescripcion();
                datosBusqueda[i][3] = c.getPais();
            }
            tablaBusqueda.setModel(new javax.swing.table.DefaultTableModel(datosBusqueda, columnas));
            indiceEdicion = -1;
        });

        tablaBusqueda.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaBusqueda.getSelectedRow();
            if (fila >= 0 && tablaBusqueda.getModel().getRowCount() > 0) {
                int idSeleccionado = (int) tablaBusqueda.getModel().getValueAt(fila, 0);
                for (int i = 0; i < listaCelebraciones.size(); i++) {
                    if (listaCelebraciones.get(i).getId() == idSeleccionado) {
                        Celebracion c = listaCelebraciones.get(i);
                        txtEditDescripcion.setText(c.getDescripcion());
                        txtEditPais.setText(c.getPais());
                        dateChooserEdit.setDate(c.getFecha());
                        indiceEdicion = i;
                        break;
                    }
                }
            }
        });

        btnGuardarEdicion.addActionListener(e -> {
            if (indiceEdicion >= 0 && indiceEdicion < listaCelebraciones.size()) {
                Celebracion c = listaCelebraciones.get(indiceEdicion);
                c.setDescripcion(txtEditDescripcion.getText());
                c.setPais(txtEditPais.getText());
                c.setFecha(dateChooserEdit.getDate());
                JOptionPane.showMessageDialog(this, "Celebración actualizada correctamente.");
                btnBuscar.doClick();
            }
        });

        // ----- PANELES VACÍOS RESTANTES -----
        JPanel panelPaisesInvertidos = new JPanel();
        pestañas.addTab("Países Invertidos", panelPaisesInvertidos);
        JPanel panelOrdenamiento = new JPanel();
        pestañas.addTab("Ordenar Celebraciones", panelOrdenamiento);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(pestañas);
        setSize(800, 600);
    }
}