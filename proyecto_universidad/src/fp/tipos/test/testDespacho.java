package fp.tipos.test;

import fp.tipos.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class testDespacho {

    public static void main(String[] args) {
        System.out.println("--- Iniciando Pruebas para Despacho (Actualizadas a Parte 4) ---");
        testConstructoresBasicos();
       // testConstructorDesdeString(); // Nuevo test para Parte 4
        testCapacidadInsuficiente();
        testSetTipoUnsupportedOperation();
        testToString();
        System.out.println("\n--- Fin de las Pruebas ---");
    }

    public static void testConstructoresBasicos() {
        System.out.println("\n-- Test Constructores Básicos --");
        // Se añade la planta como segundo argumento
        Despacho d1 = new Despacho("F0.41", 0, 2);
        System.out.println("ÉXITO: Despacho sin profesores: " + d1);

        Profesor p1 = new Profesor("Juan", "12345678A", "Pérez", LocalDate.of(1980, 5, 15), "juan.perez@univ.edu", tipoProfesor.TITULAR);
        Despacho d2 = new Despacho("F0.42", 0, 2, p1);
        System.out.println("ÉXITO: Despacho con un profesor: " + d2);

        Profesor p2 = new Profesor("Maria", "23456789B", "García", LocalDate.of(1975, 3, 20), "maria.garcia@univ.edu", tipoProfesor.CATEDRATICO);
        Set<Profesor> profesores = new HashSet<>(Set.of(p1, p2));
        Despacho d3 = new Despacho("F0.43", 0, 3, profesores);
        System.out.println("ÉXITO: Despacho con varios profesores: " + d3);
    }

    /*
     * public static void testConstructorDesdeString() {
        System.out.println("\n-- Test Constructor desde String (Parte 4) --");
        try {
            Despacho d_str = new Despacho("F1.43,1,3");
            System.out.println("ÉXITO: Creado Despacho desde String: " + d_str);
            System.out.println("  Nombre: " + d_str.getNombre() + " (Esperado: F1.43)");
            System.out.println("  Planta: " + d_str.getPlanta() + " (Esperado: 1)");
            System.out.println("  Capacidad: " + d_str.getCapacidad() + " (Esperado: 3)");
        } catch (Exception e) {
            System.err.println("ERROR: Falló la creación de Despacho desde un String válido: " + e.getMessage());
        }
        
        try {
            new Despacho("F1.44,1"); // Formato incorrecto
             System.err.println("ERROR: Se permitió crear Despacho desde String con formato incorrecto.");
        } catch(IllegalArgumentException e) {
            System.out.println("ÉXITO: Excepción capturada por formato de String incorrecto.");
        }
    }
     */
//	public Persona(String name, String dni, String apellidos, LocalDate fechaNac, String correo) 

    public static void testCapacidadInsuficiente() {
        System.out.println("\n-- Test de Capacidad Insuficiente --");
        Profesor prof1 = new Profesor("Ana", "12345678y", "López", LocalDate.now().minusYears(30), "ana@test.com", tipoProfesor.AYUDANTE);
        Profesor prof2 = new Profesor("Luis", "12345678y", "Sanz", LocalDate.now().minusYears(40), "luis@test.com", tipoProfesor.CATEDRATICO);
        Set<Profesor> profesores = Set.of(prof1, prof2);

        try {
            new Despacho("D-ERR", 1, 1, profesores); // Capacidad 1, 2 profesores
            System.err.println("ERROR: Se permitió crear un despacho con más profesores que capacidad.");
        } catch (IllegalArgumentException e) {
            System.out.println("ÉXITO: Excepción capturada por capacidad insuficiente.");
        }
    }

    public static void testSetTipoUnsupportedOperation() {
        System.out.println("\n-- Test setTipo lanza excepción --");
        Despacho despacho = new Despacho("D-TEST", 0, 1);
        try {
            despacho.setTipo(tipoEspacio.TEORIA);
            System.err.println("ERROR: Se permitió cambiar el tipo de un despacho.");
        } catch (UnsupportedOperationException e) {
            System.out.println("ÉXITO: Excepción capturada al intentar cambiar el tipo.");
        }
    }

    public static void testToString() {
        System.out.println("\n-- Test del método toString --");
        Profesor p = new Profesor("Test", "12345678c", "Prof", LocalDate.now().minusYears(50), "t@t.com", tipoProfesor.TITULAR);
        Despacho d = new Despacho("D-STR", 2, 2, p);
        System.out.println("toString() -> " + d.toString());
    }
}