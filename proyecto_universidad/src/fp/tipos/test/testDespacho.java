package fp.tipos.test;

import fp.tipos.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
public class testDespacho {
	
public static void main(String[] args) {
    testCrearDespachoSinProfesores();
    testCrearDespachoConUnProfesor();
    testCrearDespachoConVariosProfesores();
    testCrearDespachoConCapacidadInsuficiente();
    testSetTipoUnsupportedOperation();
    testToString();
}

public static void testCrearDespachoSinProfesores() {
    System.out.println("Running testCrearDespachoSinProfesores...");
    Despacho despacho = new Despacho("M2.25", 3);
    System.out.println("Expected nombre: M2.25, Actual: " + despacho.getNombre());
    System.out.println("Expected capacidad: 3, Actual: " + despacho.getCapacidad());
    System.out.println("Expected profesores: [], Actual: " + despacho.getProfesores());
    System.out.println();
}

public static void testCrearDespachoConUnProfesor() {
    System.out.println("Running testCrearDespachoConUnProfesor...");
    Profesor profesor = new Profesor("Juan", "12345678A", "Pérez", LocalDate.of(1980, 5, 15), "juan.perez@univ.edu", tipoProfesor.TITULAR);
    Despacho despacho = new Despacho("M2.25", 3, profesor);
    System.out.println("Expected profesores size: 1, Actual: " + despacho.getProfesores().size());
    System.out.println("Expected profesores to contain: " + profesor + ", Actual: " + despacho.getProfesores());
    System.out.println();
}

public static void testCrearDespachoConVariosProfesores() {
    System.out.println("Running testCrearDespachoConVariosProfesores...");
    Profesor profesor1 = new Profesor("Maria", "23456789B", "García", LocalDate.of(1975, 3, 20), "maria.garcia@univ.edu", tipoProfesor.CATEDRATICO);
    Profesor profesor2 = new Profesor("Luis", "34567890C", "Martínez", LocalDate.of(1985, 7, 10), "luis.martinez@univ.edu", tipoProfesor.CONTRATADO_DOCTOR);
    
    Set<Profesor> profesores = new HashSet<>();
    
    profesores.add(profesor1);
    profesores.add(profesor2);
    
    Despacho despacho = new Despacho("M2.25", 3, profesores);
    
    System.out.println("Expected profesores size: 2, Actual: " + despacho.getProfesores().size());
    System.out.println("Expected profesores to contain: " + profesores + ", Actual: " + despacho.getProfesores());
    System.out.println();
}

public static void testCrearDespachoConCapacidadInsuficiente() {
    System.out.println("Running testCrearDespachoConCapacidadInsuficiente...");
    Profesor profesor1 = new Profesor("Maria", "23456789B", "García", LocalDate.of(1975, 3, 20), "maria.garcia@univ.edu", tipoProfesor.CATEDRATICO);
    Profesor profesor2 = new Profesor("Luis", "34567890C", "Martínez", LocalDate.of(1985, 7, 10), "luis.martinez@univ.edu", tipoProfesor.CONTRATADO_DOCTOR);
    Profesor profesor3 = new Profesor("Ana", "45678901D", "López", LocalDate.of(1990, 12, 5), "ana.lopez@univ.edu", tipoProfesor.AYUDANTE);
    Set<Profesor> profesores = new HashSet<>();
    profesores.add(profesor1);
    profesores.add(profesor2);
    profesores.add(profesor3);

    try {
        new Despacho("M2.25", 1, profesores);
        System.out.println("Test failed: No exception was thrown, but it was expected.");
    } catch (IllegalArgumentException e) {
        System.out.println("Test passed: Exception thrown as expected. Message: " + e.getMessage());
    }
    System.out.println();
}

public static void testSetTipoUnsupportedOperation() {
    System.out.println("Running testSetTipoUnsupportedOperation...");
    Despacho despacho = new Despacho("M2.25", 3);
    try {
        despacho.setTipo(tipoEspacio.TEORIA);
        System.out.println("Test failed: No exception was thrown, but it was expected.");
    } catch (UnsupportedOperationException e) {
        System.out.println("Test passed: Exception thrown as expected. Message: " + e.getMessage());
    }
    System.out.println();
}

public static void testToString() {
    System.out.println("Running testToString...");
    Profesor profesor1 = new Profesor("Maria", "23456789B", "García", LocalDate.of(1975, 3, 20), "maria.garcia@univ.edu", tipoProfesor.CATEDRATICO);
    Profesor profesor2 = new Profesor("Luis", "34567890C", "Martínez", LocalDate.of(1985, 7, 10), "luis.martinez@univ.edu", tipoProfesor.CONTRATADO_DOCTOR);
    Set<Profesor> profesores = new HashSet<>();
    profesores.add(profesor1);
    profesores.add(profesor2);
    Despacho despacho = new Despacho("M2.25", 3, profesores);

    String expectedRepresentation = "M2.25 (planta 2) [Maria García, Luis Martínez]";
    System.out.println("Expected: " + expectedRepresentation);
    System.out.println("Actual: " + despacho.toString());
    System.out.println();

}
}
