package fp.tipos.test;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set; // Added for Alumno's getAsignaturasMatriculadas

import fp.tipos.*;


public class TestPart3 {

	public static void main (String[] args) {

			        //====================================================================================
	        // INICIO DE PRUEBAS PARA LA CLASE ALUMNO
	        //====================================================================================
	        System.out.println("==== INICIO PRUEBAS CLASE ALUMNO ====");

	        // Asignaturas de prueba para Alumno
	        Asignatura fp = new Asignatura("Fundamentos de Programación", "0000230", 6.0, tipoAsignatura.PRIMERCUATRI, 1);
	        Asignatura ed = new Asignatura("Estructura de Datos", "0000231", 6.0, tipoAsignatura.SEGUNDOCUATRI, 1);
	        Asignatura calc = new Asignatura("Cálculo", "0000110", 6.0, tipoAsignatura.ANUAL, 1);
	        Asignatura algebra = new Asignatura("Álgebra Lineal", "0000115", 6.0, tipoAsignatura.PRIMERCUATRI, 1);
	        Asignatura fisicaII = new Asignatura("Física II", "0000330", 4.5, tipoAsignatura.SEGUNDOCUATRI, 2);
	        Asignatura progII = new Asignatura("Programación II", "0000235", 6.0, tipoAsignatura.ANUAL, 2);

	        System.out.println("\n--- Pruebas Constructor Alumno y Getters Básicos ---");
	        try {
	            Alumno aluValido = new Alumno("Eva", "12345678A", "Ruiz Sanz", LocalDate.of(2002, 5, 15), "eva.ruiz@alum.us.es");
	            System.out.println("Alumno creado (válido): " + aluValido);
	            System.out.println("  Nombre: " + aluValido.getName());
	            System.out.println("  DNI: " + aluValido.getDni());
	            System.out.println("  Apellidos: " + aluValido.getApellidos());
	            System.out.println("  Fecha Nacimiento: " + aluValido.getFechaNac());
	            System.out.println("  Email: " + aluValido.getCorreo());
	            System.out.println("  Asignaturas Matriculadas (inicio): " + aluValido.getAsignaturasMatriculadas());
	            System.out.println("  Curso (inicio): " + aluValido.getCurso()); // Suponiendo getCurso ya implementado
	        } catch (Exception e) {
	            System.err.println("ERROR Inesperado creando Alumno válido: " + e.getMessage());
	            e.printStackTrace();
	        }

	        System.out.println("\n--- Pruebas Constructor Alumno con Email Inválido ---");
	        try {
	            Alumno aluInv1 = new Alumno("Juan", "87654321B", "Lopez", LocalDate.of(2001, 1, 10), "juan.lopez@us.es"); // Dominio incorrecto
	            System.err.println("ERROR: Se creó Alumno con email inválido (dominio incorrecto): " + aluInv1);
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO: Excepción capturada para email con dominio incorrecto: " + e.getMessage());
	        }
	        try {
	            Alumno aluInv2 = new Alumno("Maria", "11223344C", "Garcia", LocalDate.of(2000, 3, 20), ""); // Email vacío
	            System.err.println("ERROR: Se creó Alumno con email vacío: " + aluInv2);
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO: Excepción capturada para email vacío: " + e.getMessage());
	        }
	        try {
	            Alumno aluInv3 = new Alumno("Pedro", "55667788D", "Martin", LocalDate.of(1999, 7, 5), null); // Email null
	            System.err.println("ERROR: Se creó Alumno con email null: " + aluInv3);
	        } catch (NullPointerException | IllegalArgumentException e) { // Puede ser NPE por Objects.requireNonNull o IAE
	            System.out.println("ÉXITO: Excepción capturada para email null: " + e.getMessage());
	        }

	        System.out.println("\n--- Pruebas setCorreo Alumno ---");
	        Alumno aluParaSetCorreo = new Alumno("Ana", "99887766X", "Perez Diaz", LocalDate.of(2003, 11, 30), "ana.perez.temp@alum.us.es");
	        System.out.println("Alumno antes de setCorreo: " + aluParaSetCorreo.getCorreo());
	        try {
	            aluParaSetCorreo.setCorreo("ana.actualizado@alum.us.es");
	            System.out.println("Email actualizado (válido): " + aluParaSetCorreo.getCorreo());
	        } catch (Exception e) {
	            System.err.println("ERROR Inesperado actualizando a email válido: " + e.getMessage());
	        }
	        try {
	            aluParaSetCorreo.setCorreo("ana.perez@gmail.com"); // Dominio incorrecto
	            System.err.println("ERROR: Se permitió actualizar a email inválido (dominio incorrecto): " + aluParaSetCorreo.getCorreo());
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO: Excepción capturada al intentar actualizar a email inválido: " + e.getMessage());
	        }

	        System.out.println("\n--- Pruebas Matriculación y Desmatriculación ---");
	        Alumno aluMatricula = new Alumno("Carlos", "12312312C", "Gomez Isla", LocalDate.of(2002, 8, 22), "carlos.gomez@alum.us.es");
	        System.out.println("Alumno para matriculación: " + aluMatricula.getName());
	        System.out.println("  Asignaturas iniciales: " + aluMatricula.getAsignaturasMatriculadas());

	        // Matricular
	        try {
	            aluMatricula.matriculaAsignatura(fp);
	            System.out.println("  Matriculado en FP? " + aluMatricula.estaMatriculadoEn(fp));
	            System.out.println("  Asignaturas ahora: " + aluMatricula.getAsignaturasMatriculadas());
	            aluMatricula.matriculaAsignatura(ed);
	            System.out.println("  Matriculado en ED? " + aluMatricula.estaMatriculadoEn(ed));
	            System.out.println("  Asignaturas ahora: " + aluMatricula.getAsignaturasMatriculadas());
	        } catch (Exception e) {
	            System.err.println("ERROR Inesperado durante matriculación válida: " + e.getMessage());
	        }

	        // Intentar matricular asignatura duplicada
	        try {
	            aluMatricula.matriculaAsignatura(fp);
	            System.err.println("ERROR: Se permitió matricular en asignatura duplicada (FP)");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO: Excepción capturada al intentar matricular en asignatura duplicada: " + e.getMessage());
	        }

	        // Eliminar asignatura
	        try {
	            aluMatricula.eliminaAsignatura(ed);
	            System.out.println("  Matriculado en ED (después de eliminar)? " + aluMatricula.estaMatriculadoEn(ed));
	            System.out.println("  Asignaturas después de eliminar ED: " + aluMatricula.getAsignaturasMatriculadas());
	        } catch (Exception e) {
	            System.err.println("ERROR Inesperado al eliminar asignatura matriculada: " + e.getMessage());
	        }

	        // Intentar eliminar asignatura no matriculada
	        try {
	            aluMatricula.eliminaAsignatura(algebra);
	            System.err.println("ERROR: Se permitió eliminar asignatura no matriculada (Algebra)");
	        } catch (IllegalArgumentException e) {
	            System.out.println("ÉXITO: Excepción capturada al intentar eliminar asignatura no matriculada: " + e.getMessage());
	        }

	        System.out.println("\n--- Pruebas getCurso Alumno ---");
	        Alumno aluCurso = new Alumno("Laura", "32132132L", "Sanz Marin", LocalDate.of(2001, 4, 1), "laura.sanz@alum.us.es");
	        System.out.println("Curso de " + aluCurso.getName() + " (sin asignaturas): " + aluCurso.getCurso()); // Expected: 0
	        
	        aluCurso.matriculaAsignatura(fp); // Curso 1
	        System.out.println("Curso de " + aluCurso.getName() + " (con FP - curso 1): " + aluCurso.getCurso()); // Expected: 1
	        
	        aluCurso.matriculaAsignatura(progII); // Curso 2
	        System.out.println("Curso de " + aluCurso.getName() + " (con FP y ProgII - curso 2): " + aluCurso.getCurso()); // Expected: 2

	        aluCurso.matriculaAsignatura(ed); // Curso 1
	        System.out.println("Curso de " + aluCurso.getName() + " (con FP, ProgII, ED - curso 1 y 2): " + aluCurso.getCurso()); // Expected: 2

	        aluCurso.eliminaAsignatura(progII); // Elimina la de curso 2
	        System.out.println("Curso de " + aluCurso.getName() + " (después de eliminar ProgII): " + aluCurso.getCurso()); // Expected: 1

	        aluCurso.eliminaAsignatura(fp);
	        aluCurso.eliminaAsignatura(ed);
	        System.out.println("Curso de " + aluCurso.getName() + " (sin asignaturas de nuevo): " + aluCurso.getCurso()); // Expected: 0

	        System.out.println("\n--- Prueba toString Alumno ---");
	        Alumno aluToString = new Alumno("David", "45645645D", "Blanco Paz", LocalDate.of(2002, 9, 9), "david.blanco@alum.us.es");
	        System.out.println("toString (sin asignaturas): " + aluToString);
	        // Esperado: (0º) 45645645D - Blanco Paz, David - 2002-09-09 (o el formato de Persona)

	        aluToString.matriculaAsignatura(calc); // calc es de curso 1
	        System.out.println("toString (con Cálculo - curso 1): " + aluToString);
	        // Esperado: (1º) 45645645D - Blanco Paz, David - 2002-09-09

	        aluToString.matriculaAsignatura(fisicaII); // fisicaII es de curso 2
	        System.out.println("toString (con Cálculo y Física II - cursos 1 y 2): " + aluToString);
	        // Esperado: (2º) 45645645D - Blanco Paz, David - 2002-09-09

	        System.out.println("\n--- Pruebas equals y hashCode Alumno ---");
	        // Usa el constructor de Alumno que has definido
	        Alumno aluEq1 = new Alumno("Elena", "78978978E", "Ruiz Lopez", LocalDate.of(2003, 3, 3), "elena.ruiz@alum.us.es");
	        Alumno aluEq2 = new Alumno("Elena", "78978978E", "Ruiz Lopez", LocalDate.of(2003, 3, 3), "elena.ruiz.diffmail@alum.us.es"); // Mismo DNI, nombre, apellidos
	        Alumno aluEq3 = new Alumno("Luisa", "67867867L", "Soler Vega", LocalDate.of(2003, 6, 6), "luisa.soler@alum.us.es");
	        
	        aluEq1.matriculaAsignatura(fp); // Estado interno diferente, no debe afectar equals

	        System.out.println("aluEq1.equals(aluEq2)? " + aluEq1.equals(aluEq2)); // Esperado: true (si Persona.equals se basa en DNI, nombre, apellidos)
	        System.out.println("  Hashcodes: aluEq1=" + aluEq1.hashCode() + ", aluEq2=" + aluEq2.hashCode() + " (Deberían ser iguales si equals es true)");
	        System.out.println("aluEq1.equals(aluEq3)? " + aluEq1.equals(aluEq3)); // Esperado: false
	        System.out.println("aluEq1.equals(null)? " + aluEq1.equals(null));   // Esperado: false
	        
	        Persona personaEq = new Persona("Elena", "78978978E", "Ruiz Lopez", LocalDate.of(2003, 3, 3), "elena.ruiz@alum.us.es");
	        System.out.println("aluEq1.equals(personaEq)? " + aluEq1.equals(personaEq)); // Esperado: true, si Alumno hereda equals de Persona y no lo rompe,
	                                                                                    // Y SI la implementación de equals de Persona es `obj instanceof Persona` y no `obj.getClass() == this.getClass()`

	        System.out.println("\n==== FIN PRUEBAS CLASE ALUMNO ====");
	        //====================================================================================
	        // FIN DE PRUEBAS PARA LA CLASE ALUMNO
	        //====================================================================================
	
	}

}