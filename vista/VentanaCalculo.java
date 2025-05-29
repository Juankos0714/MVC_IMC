package MVC_IMC.vista;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.dto.PersonaDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaCalculo extends JDialog implements ActionListener {

    private Coordinador miCoordinador;
    private JLabel lblTitulo, lblPersona, lblPeso, lblTalla;
    private JComboBox<String> cmbPersonas;
    private JTextField txtPeso, txtTalla;
    private JButton btnCalcular, btnCerrar, btnActualizar;
    private JTextArea areaResultado;

    public VentanaCalculo(VentanaPrincipal ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
        initComponents();
        setupLayout();
        setupListeners();
        cargarPersonas();
    }

    private void initComponents() {
        setTitle("Calcular IMC");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        lblTitulo = new JLabel("Cálculo de IMC");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 102, 204));

        lblPersona = new JLabel("Persona:");
        cmbPersonas = new JComboBox<>();
        cmbPersonas.setPreferredSize(new Dimension(200, 25));

        lblPeso = new JLabel("Peso (kg):");
        txtPeso = new JTextField(10);

        lblTalla = new JLabel("Talla (metros):");
        txtTalla = new JTextField(10);

        btnCalcular = new JButton("Calcular IMC");
        btnCalcular.setBackground(new Color(0, 102, 204));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 12));

        btnActualizar = new JButton("↻");
        btnActualizar.setBackground(new Color(102, 153, 255));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setToolTipText("Actualizar lista");

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(204, 0, 0));
        btnCerrar.setForeground(Color.WHITE);

        areaResultado = new JTextArea(8, 30);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaResultado.setBackground(new Color(245, 245, 245));
        areaResultado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(lblTitulo, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 1;
        panelPrincipal.add(lblPersona, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(cmbPersonas, gbc);
        gbc.gridx = 2;
        panelPrincipal.add(btnActualizar, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelPrincipal.add(lblPeso, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtPeso, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelPrincipal.add(lblTalla, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtTalla, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnCalcular);
        panelBotones.add(btnCerrar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(panelBotones, gbc);

        add(panelPrincipal, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
    }

    private void setupListeners() {
        btnCalcular.addActionListener(this);
        btnCerrar.addActionListener(this);
        btnActualizar.addActionListener(this);
        txtPeso.addActionListener(this);  // Enter va a talla
        txtTalla.addActionListener(this); // Enter calcula
    }

    private void cargarPersonas() {
        cmbPersonas.removeAllItems();
        cmbPersonas.addItem("-- Seleccione una persona --");

        if (miCoordinador != null) {
            List<PersonaDTO> personas = miCoordinador.obtenerTodasLasPersonas();
            if (personas != null) {
                for (PersonaDTO persona : personas) {
                    cmbPersonas.addItem(persona.getNombre());
                }
            }

            if (personas == null || personas.isEmpty()) {
                areaResultado.setText("No hay personas registradas.\n\n" +
                        "Primero registre una persona usando la opción 'Registrar Persona'.");
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular || e.getSource() == txtTalla) {
            procesarCalculo();
        } else if (e.getSource() == txtPeso) {
            txtTalla.requestFocus(); // Pasar al siguiente campo
        } else if (e.getSource() == btnCerrar) {
            dispose();
        } else if (e.getSource() == btnActualizar) {
            cargarPersonas();
        }
    }

    private void procesarCalculo() {
        String personaSeleccionada = (String) cmbPersonas.getSelectedItem();
        String pesoStr = txtPeso.getText().trim();
        String tallaStr = txtTalla.getText().trim();

        if (personaSeleccionada == null || personaSeleccionada.equals("-- Seleccione una persona --")) {
            JOptionPane.showMessageDialog(this, "Seleccione una persona.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (pesoStr.isEmpty() || tallaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete peso y talla.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double peso = Double.parseDouble(pesoStr);
            double talla = Double.parseDouble(tallaStr);

            if (peso <= 0 || talla <= 0) {
                JOptionPane.showMessageDialog(this, "Peso y talla deben ser positivos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (talla > 3) {
                JOptionPane.showMessageDialog(this, "Ingrese la talla en metros (ej: 1.75)", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PersonaDTO persona = miCoordinador.consultarPersona(personaSeleccionada);
            if (persona == null) {
                JOptionPane.showMessageDialog(this, "Error: Persona no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            persona.setPeso(peso);
            persona.setTalla(talla);
            persona = miCoordinador.calcularIMC(persona);
            miCoordinador.actualizarPersona(persona);

            StringBuilder resultado = new StringBuilder();
            resultado.append("RESULTADO DEL CÁLCULO\n");
            resultado.append("================================\n\n");
            resultado.append("Persona: ").append(persona.getNombre()).append("\n");
            resultado.append("Edad: ").append(persona.getEdad()).append(" años\n");
            resultado.append(String.format("Peso: %.1f kg\n", persona.getPeso()));
            resultado.append(String.format("Talla: %.2f m\n", persona.getTalla()));
            resultado.append(String.format("IMC: %.2f\n\n", persona.getImc()));
            resultado.append("Estado: ").append(persona.getEstado()).append("\n\n");
            resultado.append("Recomendación:\n").append(persona.getMensaje());

            areaResultado.setText(resultado.toString());
            areaResultado.setCaretPosition(0);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Use números válidos (punto para decimales).",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}