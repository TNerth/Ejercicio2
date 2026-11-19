package Ejercicio2;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class MarcoCompraPasaje extends JFrame {
    private JTextField txtNombres;
    private JTextField txtDocumento;
    private JTextField txtFecha;
    private JCheckBox chkAudifonos;
    private JCheckBox chkManta;
    private JCheckBox chkRevistas;
    private JRadioButton rbtPrimerPiso;
    private JRadioButton rbtSegundoPiso;
    private ButtonGroup grupoPiso;
    private JComboBox<String> cboOrigen;
    private JComboBox<String> cboDestino;
    private JList<String> lstCalidad;
    private JButton btnMostrar;
    private JButton btnReiniciar;

    private static final String[] ciudades = {
        "arequipa",
        "lima",
        "cusco",
        "puno",
        "tacna",
        "trujillo"
    };

    private static final String[] calidades = {
        "economico",
        "standard",
        "vip"
    };

    public MarcoCompraPasaje() {
        super("compra de pasajes terrestres harrison diaz");
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        panelFormulario.add(new JLabel("nombres"));
        txtNombres = new JTextField(20);
        panelFormulario.add(txtNombres);

        panelFormulario.add(new JLabel("docuemnto de identidad"));
        txtDocumento = new JTextField(20);
        panelFormulario.add(txtDocumento);

        panelFormulario.add(new JLabel("fecha de viaje"));
        txtFecha = new JTextField(20);
        panelFormulario.add(txtFecha);

        panelFormulario.add(new JLabel("servicios opcionales"));
        JPanel panelServicios = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        chkAudifonos = new JCheckBox("audifonos");
        chkManta = new JCheckBox("manta");
        chkRevistas = new JCheckBox("revistas");
        panelServicios.add(chkAudifonos);
        panelServicios.add(chkManta);
        panelServicios.add(chkRevistas);
        panelFormulario.add(panelServicios);

        panelFormulario.add(new JLabel("piso"));
        JPanel panelPiso = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rbtPrimerPiso = new JRadioButton("1er piso");
        rbtSegundoPiso = new JRadioButton("2do piso");
        grupoPiso = new ButtonGroup();
        grupoPiso.add(rbtPrimerPiso);
        grupoPiso.add(rbtSegundoPiso);
        panelPiso.add(rbtPrimerPiso);
        panelPiso.add(rbtSegundoPiso);
        panelFormulario.add(panelPiso);

        panelFormulario.add(new JLabel("origen"));
        cboOrigen = new JComboBox<>(ciudades);
        panelFormulario.add(cboOrigen);

        panelFormulario.add(new JLabel("destino"));
        cboDestino = new JComboBox<>(ciudades);
        cboDestino.setSelectedIndex(1);
        panelFormulario.add(cboDestino);

        panelFormulario.add(new JLabel("calidad de servicio"));
        lstCalidad = new JList<>(calidades);
        lstCalidad.setVisibleRowCount(3);
        lstCalidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelFormulario.add(new JScrollPane(lstCalidad));

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnMostrar = new JButton("mostrar resumen");
        btnReiniciar = new JButton("reiniciar");
        panelBotones.add(btnMostrar);
        panelBotones.add(btnReiniciar);
        add(panelBotones, BorderLayout.SOUTH);

        ManejadorBotones manejador = new ManejadorBotones();
        btnMostrar.addActionListener(manejador);
        btnReiniciar.addActionListener(manejador);
    }

    private class ManejadorBotones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == btnMostrar) {
                String nombres = txtNombres.getText();
                String documento = txtDocumento.getText();
                String fecha = txtFecha.getText();

                String servicios = "";
                if (chkAudifonos.isSelected()) {
                    servicios += "audifonos ";
                }
                if (chkManta.isSelected()) {
                    servicios += "manta ";
                }
                if (chkRevistas.isSelected()) {
                    servicios += "revistas ";
                }
                if (servicios.isEmpty()) {
                    servicios = "ninguno";
                }

                String piso = "no seleccionado";
                if (rbtPrimerPiso.isSelected()) {
                    piso = "1er piso";
                } else if (rbtSegundoPiso.isSelected()) {
                    piso = "2do piso";
                }

                String origen = (String) cboOrigen.getSelectedItem();
                String destino = (String) cboDestino.getSelectedItem();
                String calidad = lstCalidad.getSelectedValue();
                if (calidad == null) {
                    calidad = "no seleccionado";
                }

                String resumen = "nombres " + nombres
                        + "\ndocumento " + documento
                        + "\nfecha de viaje " + fecha
                        + "\nservicios opcionales " + servicios.trim()
                        + "\npiso " + piso
                        + "\norigen " + origen
                        + "\ndestino " + destino
                        + "\ncalidad de servicio " + calidad;

                JOptionPane.showMessageDialog(null, resumen, "resumen de pasaje", JOptionPane.INFORMATION_MESSAGE);
            } else if (evento.getSource() == btnReiniciar) {
                txtNombres.setText("");
                txtDocumento.setText("");
                txtFecha.setText("");
                chkAudifonos.setSelected(false);
                chkManta.setSelected(false);
                chkRevistas.setSelected(false);
                grupoPiso.clearSelection();
                cboOrigen.setSelectedIndex(0);
                cboDestino.setSelectedIndex(1);
                lstCalidad.clearSelection();
            }
        }
    }
}