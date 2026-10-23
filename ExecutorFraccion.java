package edu.unl.cc.ape.view;

import edu.unl.cc.ape.domain.Fraccion;
import java.util.Locale;
import java.util.Scanner;

    /** @author Ashley Mocha, Matias Ortega, Sdier Roblez, Tyrone Encarnación
     *  Operations with fractions
     */
public class FraccionExecutor {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Fraccion actual = null;   
        int numOriginal = 0;      
        int denOriginal = 1;
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    int n = leerEntero("Ingrese el numerador: ");
                    int d = leerEntero("Ingrese el denominador: ");
                    try {
                        actual = new Fraccion(n, d);
                        numOriginal = n;
                        denOriginal = d;
                        System.out.println("Fracción guardada: " + n + "/" + d);
                    } catch (IllegalArgumentException | ArithmeticException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    if (hayFraccion(actual)) {
                        System.out.println(numOriginal + "/" + denOriginal + " simplificada = " + actual.aNotacion());
                    }
                    break;
                case 3:
                    if (hayFraccion(actual)) {
                        System.out.println("Valor aproximado de " + actual.aNotacion() + " = " + actual.valorDecimal());
                    }
                    break;
                case 4:
                    if (hayFraccion(actual)) {
                        actual = operar(actual);
                        
                        numOriginal = actual.getNumerador();
                        denOriginal = actual.getDenominador();
                    }
                    break;
                case 5:
                    if (hayFraccion(actual)) {
                        mostrarInversa(actual);
                    }
                    break;
                case 6:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción inválida. Elija un número del 1 al 6.");
            }
        } while (opcion != 6);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ FRACCIONES =====");
        System.out.println("1. Ingresar fracción (numerador y denominador)");
        System.out.println("2. Mostrar fracción simplificada");
        System.out.println("3. Mostrar valor aproximado");
        System.out.println("4. Operar con otra fracción (+, -, *, /)");
        System.out.println("5. Mostrar inversa");
        System.out.println("6. Salir");
    }

    private static boolean hayFraccion(Fraccion f) {
        if (f == null) {
            System.out.println("Primero debe ingresar una fracción (opción 1).");
            return false;
        }
        return true;
    }

    private static void mostrarInversa(Fraccion actual) {
        try {
            Fraccion inv = actual.inversa();
            System.out.println("Inversa de " + actual.aNotacion() + " = " + inv.aNotacion()
                    + " = " + inv.valorDecimal());
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    
    private static Fraccion operar(Fraccion actual) {
        String op = leerLinea("Operación a realizar (+, -, *, /): ").trim();

        if (!op.equals("+") && !op.equals("-") && !op.equals("*") && !op.equals("/")) {
            System.out.println("Operador inválido.");
            return actual;
        }

        System.out.println("Ingrese la fracción con la que desea operar:");
        int n = leerEntero("  Numerador: ");
        int d = leerEntero("  Denominador: ");

        try {
            Fraccion otra = new Fraccion(n, d);
            Fraccion resultado;
            switch (op) {
                case "+": resultado = actual.sumar(otra); break;
                case "-": resultado = actual.restar(otra); break;
                case "*": resultado = actual.multiplicar(otra); break;
                default:  resultado = actual.dividir(otra); break;
            }
            System.out.println(actual.aNotacion() + " " + op + " " + otra.aNotacion()
                    + " = " + resultado.aNotacion() + " = " + resultado.valorDecimal());

            String r = leerLinea("¿Usar el resultado como fracción actual? (s/n): ").trim();
            if (r.equalsIgnoreCase("s")) {
                return resultado;
            }
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        return actual;
    }

    
    private static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        if (!sc.hasNextLine()) {
            System.out.println("\nNo hay más entrada. Programa finalizado.");
            System.exit(0);
        }
        return sc.nextLine();
    }

    
    private static int leerEntero(String mensaje) {
        while (true) {
            String texto = leerLinea(mensaje).trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
            }
        }
    }
}
