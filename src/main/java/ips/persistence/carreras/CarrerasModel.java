package ips.persistence.carreras;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.util.Database;

public class CarrerasModel {

	private Database db=new Database();
	
	public static final String SQL_LISTA_CARRERAS= "SELECT * FROM CARRERAS where fechafin>=? and fechaInicio<=?";
	
	public static final String SQL_CARRERA_BY_IDCARRERA= "SELECT * FROM CARRERAS WHERE IDCARRERA=? and fechafin>=? and fechaInicio<=?";
	
	public static final String SQL_CARRERAS_INSCRIPCION = "SELECT * FROM CARRERAS where fechafin>=? and fechaInicio<=? and plazasDisponibles>0";
	
	public static final String SQL_CARRERA_BY_ID = "SELECT * FROM CARRERAS WHERE IDCARRERA=?";
	
	public List<CarreraDisplayDTO> getListaCarreras() {
		//List<CarreraDisplayDTO> listCarreras = new ArrayList<CarreraDisplayDTO>();
		Date fecha = java.sql.Date.valueOf(LocalDate.now());
		return db.executeQueryPojo(CarreraDisplayDTO.class, SQL_LISTA_CARRERAS,fecha,fecha);
	}
	
	public List<CarreraDisplayDTO> getCarreraByIdCarrera(int idCarrera) throws BusinessException {
		// FOrmas de no hacer el cast? DUDA
		Date fecha = java.sql.Date.valueOf(LocalDate.now());
		List<CarreraDisplayDTO> carrera = db.executeQueryPojo(CarreraDisplayDTO.class, SQL_CARRERA_BY_IDCARRERA,idCarrera,fecha,fecha);
		return carrera;
	}
	
	public List<CarreraDisplayDTO> getCarrerasInscripcion() {
		Date fecha = java.sql.Date.valueOf(LocalDate.now());
		return db.executeQueryPojo(CarreraDisplayDTO.class, SQL_CARRERAS_INSCRIPCION, fecha, fecha);
	}
	
	public List<CarreraDisplayDTO> getCarreraById(int idCarrera) throws BusinessException {
		List<CarreraDisplayDTO> carrera = db.executeQueryPojo(CarreraDisplayDTO.class, SQL_CARRERA_BY_ID, idCarrera);
		return carrera;
	}

}
