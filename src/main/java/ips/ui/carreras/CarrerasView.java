package ips.ui.carreras;

import java.util.List;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.business.carreras.CarrerasController;
import ips.persistence.carreras.CarrerasModel;

public class CarrerasView {

	public List<CarreraDisplayDTO> getCompeticiones() throws BusinessException {
		CarrerasController carrerasController = new CarrerasController(new CarrerasModel(),new CarrerasView());
        return carrerasController.getListaCarreras();
	}
	
	public CarreraDisplayDTO[] getCompeticionesInscripcion() throws BusinessException {
		CarrerasController carrerasController = new CarrerasController(new CarrerasModel(),new CarrerasView());
        return carrerasController.getCarrerasInscripcion().toArray(new CarreraDisplayDTO[carrerasController.getCarrerasInscripcion().size()]);
	}
	
	public List<CarreraDisplayDTO> getCarrerasById(int idCarrera) throws BusinessException {
		CarrerasController carrerasController = new CarrerasController(new CarrerasModel(),new CarrerasView());
        return carrerasController.getCarrerasById(idCarrera);
	}
	
}
