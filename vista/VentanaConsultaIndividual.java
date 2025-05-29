package MVC_IMC.vista;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.dto.PersonaDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConsultaIndividual extends JDialog implements ActionListener {

    private Coordinador miCoordinador;
    private JLabel lblTitulo, lblDocumento, lblNombre, lblEdad;
    private JTextField txtDocumento, txtNombre,txtEdad;
    private JButton btnBuscar, btnCerrar,btnActualizar, btnEliminar;
;

    public VentanaConsultaIndividual(VentanaPrincipal ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        setTitle("Consulta Individual");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        lblTitulo = new JLabel("Consulta Individual");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        lblDocumento = new JLabel("Documento:");
        txtDocumento = new JTextField(20);

        lblNombre = new JLabel("Nombre completo:");
        txtNombre = new JTextField(20);

        lblEdad = new JLabel("Edad (años):");
        txtEdad = new JTextField(10);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setForeground(Color.BLACK);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(188, 195, 151, 29);
        btnEliminar.addActionListener(this);
        getContentPane().add(btnEliminar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(17, 194, 159, 30);
        btnActualizar.addActionListener(this);
        getContentPane().add(btnActualizar);

    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelSuperior.add(lblTitulo, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 1;
        panelSuperior.add(lblDocumento, gbc);
        gbc.gridx = 1;
        panelSuperior.add(txtDocumento, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelSuperior.add(lblNombre, gbc);
        gbc.gridx = 1;gbc.weightx = 0.7;
        panelSuperior.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 3;gbc.weightx = 0.0;
        panelSuperior.add(lblEdad, gbc);
        gbc.gridx = 1;gbc.weightx = 1.0;
        panelSuperior.add(txtEdad, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnBuscar);
        panelBotones.add(btnCerrar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelSuperior.add(panelBotones, gbc);

        add(panelSuperior, BorderLayout.NORTH);

    }

    private void setupListeners() {
        btnBuscar.addActionListener(this);
        btnCerrar.addActionListener(this);
        txtDocumento.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBuscar) {

            PersonaDTO miPersona=miCoordinador.consultarPersona(txtDocumento.getText());

            if (miPersona!=null) {
                txtNombre.setText(miPersona.getNombre());
                txtEdad.setText(miPersona.getEdad()+"");
            }else {
                JOptionPane.showMessageDialog(null, "NO SE ENCUENTRA LA PERSONA","NO REGISTRA",JOptionPane.ERROR_MESSAGE);
                txtNombre.setText("");
                txtEdad.setText("");
            }
        } else if (e.getSource() == btnCerrar) {
            dispose();
        } else if(e.getSource()==btnActualizar) {
        actualizarUsuario();
        }else if(e.getSource()==btnEliminar) {
        eliminarUsuario();
    }

    }


    private void eliminarUsuario() {
        if (txtDocumento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un documento", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea eliminar esta persona?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String resp = miCoordinador.eliminarPersona(txtDocumento.getText().trim());

            if (resp.equals("ok")) {
                JOptionPane.showMessageDialog(null, "Se elimina exitosamente", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                txtDocumento.setText("");
                txtNombre.setText("");
                txtEdad.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void actualizarUsuario() {
        if (txtDocumento.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() || txtEdad.getText().trim().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los parametros", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            PersonaDTO personaActualizada = new PersonaDTO();
            personaActualizada.setDocumento(txtDocumento.getText().trim());
            personaActualizada.setNombre(txtNombre.getText().trim());
            personaActualizada.setEdad(Integer.parseInt(txtEdad.getText().trim()));

            String resp = miCoordinador.actualizarPersona(personaActualizada);

            if (resp.equals("ok")) {
                JOptionPane.showMessageDialog(null, "Se actualiza exitosamente", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}