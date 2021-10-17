package ips.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ips.business.BusinessException;
import ips.business.carreras.CarreraDisplayDTO;
import ips.ui.carreras.CarrerasView;
import ips.ui.carreras.EstadoInscripcionesView;
import ips.ui.carreras.InscripcionView;
import ips.util.Printer;

public class MenuCorredor {
	
	public void execute() throws BusinessException {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; // Guardaremos la opcion del usuario
 
        while (!salir) {
            System.out.println("1. Ver competiciones abiertas");
            System.out.println("2. Inscribirse en una competición");
            System.out.println("3. Visualizar inscripciones");
            System.out.println("4. Salir");
 
            try {
                System.out.println("Escriba una de las opciones:");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                    	CarrerasView carrerasView = new CarrerasView();
                    	List<CarreraDisplayDTO> carreras1 = carrerasView.getCompeticiones();
                        System.out.println("Ha seleccionado la opción 1 - Ver competiciones abiertas");
                        Printer.printCarreras(carreras1);
                        break;
                    case 2:
                        System.out.println("Ha seleccionado la opción 2 - Inscribirse en una competición");
                        InscripcionView inscripcion = new InscripcionView();
    					inscripcion.setVisible(true);
    	                break;
                    case 3:
                        System.out.println("Ha seleccionado la opción 3 - Visualizar inscripciones");
                        EstadoInscripcionesView estado = new EstadoInscripcionesView();
                        estado.setVisible(true);
    	                break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            } catch(BusinessException e) {
            	Printer.printBusinessException(e);
            }
        }
 
    }
}
