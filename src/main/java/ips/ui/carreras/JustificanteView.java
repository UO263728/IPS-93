package ips.ui.carreras;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.corredores.CorredorDTO;

public class JustificanteView extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel pnContenido;
	private JLabel lbMensaje;
	private JButton btFinalizar;
	
	private InscripcionView iiu;
	
	public JustificanteView(InscripcionView iiu) throws BusinessException {
		this.iiu = iiu;
		
		setTitle("Inscripción: Justificante");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 442, 300);
		setLocationRelativeTo(null);
		
		pnContenido = new JPanel();
		pnContenido.setBackground(Color.WHITE);
		pnContenido.setLayout(null);
		setContentPane(pnContenido);
		pnContenido.add(getLbMensaje());
		pnContenido.add(getBtFinalizar());
	}
	
	private JLabel getLbMensaje() throws BusinessException {
		if (lbMensaje == null) {
			lbMensaje = new JLabel(justificante());
			lbMensaje.setFont(new Font("Tahoma", Font.BOLD, 20));
			lbMensaje.setBounds(25, 30, 378, 147);
		}
		return lbMensaje;
	}
	
	private String justificante() throws BusinessException {
		CorredorDTO corredor = iiu.obtenerCorredor();
		CarreraDisplayDTO carrera = iiu.obtenerCarreraSeleccionada();
		return "Justificante de inscripción - Nombre corredor: " + corredor.getNombre() + 
				", Competición: " + corredor.getIdCarrera() + ", Categoría: " + corredor.getCategoria() +
				", Fecha inscripción: " + corredor.getFechaInscripcion() +
				", Cantidad a abonar: " + carrera.getPrecio() + "€";
	}
	
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("Finalizar");
			btFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					iiu.dispose();
					dispose();
				}
			});
			btFinalizar.setMnemonic('F');
			btFinalizar.setBackground(new Color(0, 128, 0));
			btFinalizar.setForeground(Color.WHITE);
			btFinalizar.setBounds(168, 212, 89, 26);
		}
		return btFinalizar;
	}

}
