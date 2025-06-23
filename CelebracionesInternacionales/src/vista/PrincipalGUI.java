package vista;

import javax.swing.*;
import java.util.ArrayList;
import modelo.Celebracion;


public class PrincipalGUI extends javax.swing.JFrame {

    private JTabbedPane pestañas;
    
    private ArrayList<Celebracion> listaCelebraciones;
    private int siguienteId;


    public PrincipalGUI() {
        initComponents();
        setTitle("Registro de Celebraciones Internacionales");
        setLocationRelativeTo(null); // Centra la ventana en pantalla
        
        // Inicializa la lista y el contador de IDs
        listaCelebraciones = new ArrayList<>();
        siguienteId = 1; // El primer ID será 1
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        pestañas = new JTabbedPane();

        // Paneles vacíos para cada funcionalidad, los llenaremos luego.
        JPanel panelRegistro = new JPanel();
        pestañas.addTab("Registrar Celebración", panelRegistro);

        JPanel panelListado = new JPanel();
        pestañas.addTab("Listado de Celebraciones", panelListado);

        JPanel panelBusqueda = new JPanel();
        pestañas.addTab("Buscar/Editar Celebración", panelBusqueda);

        JPanel panelPaisesInvertidos = new JPanel();
        pestañas.addTab("Países Invertidos", panelPaisesInvertidos);

        JPanel panelOrdenamiento = new JPanel();
        pestañas.addTab("Ordenar Celebraciones", panelOrdenamiento);

        // Layout principal
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(pestañas);

        setSize(800, 600); // Tamaño sugerido
        // pack(); // Opcional: puedes usar setSize o pack, pero no ambos si setSize es suficiente.
    }
}

