package ips.business;

import java.util.List;

import ips.business.carreras.CarreraDisplayDTO;
import ips.persistence.carreras.CarrerasModel;
import ips.ui.carreras.CarrerasView;

public class CarrerasController {

	private CarrerasModel model;
	private CarrerasView view;
	
	public CarrerasController(CarrerasModel m, CarrerasView v) {
		this.model = m;
		this.view = v;
	}
	
	public void getListaCarreras() {
		List<CarreraDisplayDTO> carreras=model.getListaCarreras();
		for(CarreraDisplayDTO c: carreras) {
			if(c!=null) {
				System.out.println(c.getIdCarrera());
			}
		}
		
	}
}
