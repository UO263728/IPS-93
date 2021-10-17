package ips.ui.carreras;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.corredores.CorredorDTO;
import ips.ui.corredores.CorredoresView;

public class InscripcionView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lbInscripcion;
	private JLabel lbCarreras;
	private JComboBox<CarreraDisplayDTO> cbCarreras;
	private JLabel lbEmail;
	private JTextField txtEmail;
	private JButton btSiguiente;
	private JButton btCancelar;
	
	public InscripcionView() throws BusinessException {
		setTitle("Inscripción");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 433, 426);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(getLbInscripcion());
		contentPane.add(getLbCarreras());
		contentPane.add(getCbCarreras());
		contentPane.add(getLbEmail());
		contentPane.add(getTxtEmail());
		contentPane.add(getBtSiguiente());
		contentPane.add(getBtCancelar());
	}

	private JLabel getLbInscripcion() {
		if (lbInscripcion == null) {
			lbInscripcion = new JLabel("Inscripción");
			lbInscripcion.setBackground(Color.WHITE);
			lbInscripcion.setFont(new Font("Arial Black", Font.PLAIN, 35));
			lbInscripcion.setBounds(42, 41, 270, 42);
		}
		return lbInscripcion;
	}
	
	private JLabel getLbCarreras() throws BusinessException {
		if (lbCarreras == null) {
			lbCarreras = new JLabel("Carreras:");
			lbCarreras.setBounds(44, 125, 87, 28);
			lbCarreras.setLabelFor(getCbCarreras());
			lbCarreras.setDisplayedMnemonic('r');
			lbCarreras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lbCarreras;
	}
	
	private JComboBox<CarreraDisplayDTO> getCbCarreras() throws BusinessException {
		if (cbCarreras == null) {		
			cbCarreras = new JComboBox<CarreraDisplayDTO>();
			cbCarreras.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbCarreras.setBackground(SystemColor.WHITE);
			cbCarreras.setBounds(42, 164, 310, 42);
			cbCarreras.setModel(new DefaultComboBoxModel<CarreraDisplayDTO>(getCarreras()));
		}
		return cbCarreras;
	}
	
	public CarreraDisplayDTO[] getCarreras() throws BusinessException {
		CarrerasView view = new CarrerasView();
		return view.getCompeticionesInscripcion();
	}
	
	private JLabel getLbEmail() {
		if (lbEmail == null) {
			lbEmail = new JLabel("Email:");
			lbEmail.setLabelFor(getTxtEmail());
			lbEmail.setDisplayedMnemonic('e');
			lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbEmail.setBounds(42, 230, 144, 22);
		}
		return lbEmail;
	}
	
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
			txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtEmail.setEditable(true);
			txtEmail.setBackground(SystemColor.WHITE);
			txtEmail.setBounds(42, 257, 310, 42);
		}
		return txtEmail;
	}
	
	private JButton getBtSiguiente() {
		if (btSiguiente == null) {
			btSiguiente = new JButton("Siguiente");
			btSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btSiguiente.setMnemonic('S');
			btSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						mostrarVentanaJustificante();
					} catch (BusinessException be) {
						System.out.println(be.getMessage());
					}
				}
			});
			btSiguiente.setForeground(Color.WHITE);
			btSiguiente.setBackground(new Color(0, 128, 0));
			btSiguiente.setBounds(308, 332, 90, 30);
		}
		return btSiguiente;
	}
	
	private void mostrarVentanaJustificante() throws BusinessException {
		CorredorDTO corredor = obtenerCorredor();
		CarreraDisplayDTO carrera = obtenerCarreraSeleccionada();
		if (comprobaciones(corredor, carrera)) {
			corredor.setIdCarrera(carrera.getIdCarrera());
			carrera.setPlazasDisponibles(carrera.getPlazasDisponibles() - 1);
			corredor.setEstadoInscripcion("Pre-inscrito");

			JustificanteView jv = new JustificanteView(this);
			jv.setVisible(true);
		}
	}
	
	CorredorDTO obtenerCorredor() throws BusinessException {
		String email = getTxtEmail().getText();
		CorredoresView view = new CorredoresView();
		CorredorDTO corredor = view.getCorredorByEmail(email).get(0);
		return corredor;
	}
	
	CarreraDisplayDTO obtenerCarreraSeleccionada() throws BusinessException {
		CarreraDisplayDTO carrera = (CarreraDisplayDTO) getCbCarreras().getSelectedItem();
		return carrera;
	}
	
	private boolean comprobaciones(CorredorDTO corredor, CarreraDisplayDTO carrera) throws BusinessException {
		if (corredor.getIdCarrera() == carrera.getIdCarrera()) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una carrera en la que no esté ya inscrito", "ERROR", JOptionPane.ERROR_MESSAGE);
			getCbCarreras().grabFocus();
			return false;
		}
		return true;
	}
	
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btCancelar.setMnemonic('C');
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setBackground(Color.RED);
			btCancelar.setForeground(Color.WHITE);
			btCancelar.setBounds(183, 332, 90, 30);
		}
		return btCancelar;
	}
	
}
