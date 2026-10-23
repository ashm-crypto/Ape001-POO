package edu.unl.cc.ape.view;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Fraccion actual = null; 
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    actual = ingresarFraccion("");
                    if (actual != null) {
                        System.out.println("Fracción guardada: " + actual.aNotacion());
                    }
                    break;
                case 2:
                    if (hayFraccion(actual)) {
                        System.out.println("Fracción simplificada: " + actual.aNotacion());
                    }
                    break;
                case 3:
                    if (hayFraccion(actual)) {
                        System.out.println("Valor aproximado: " + actual.valorDecimal());
                    }
                    break;
                case 4:
                    if (hayFraccion(actual)) {
                        operar(actual);
                    }
                    break;
                case 5:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción inválida. Elija un número del 1 al 5.");
            }
        } while (opcion != 5);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ FRACCIONES =====");
        System.out.println("1. Ingresar fracción (numerador y denominador)");
        System.out.println("2. Mostrar fracción simplificada");
        System.out.println("3. Mostrar valor aproximado");
        System.out.println("4. Operar con otra fracción (+, -, *, /)");
        System.out.println("5. Salir");
    }

    private static boolean hayFraccion(Fraccion f) {
        if (f == null) {
            System.out.println("Primero debe ingresar una fracción (opción 1).");
            return false;
        }
        return true;
    }

    
    private static Fraccion ingresarFraccion(String etiqueta) {
        int num = leerEntero("Ingrese el numerador" + etiqueta + ": ");
        int den = leerEntero("Ingrese el denominador" + etiqueta + ": ");
        try {
            return new Fraccion(num, den);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void operar(Fraccion actual) {
        System.out.print("Operación a realizar (+, -, *, /): ");
        String op = sc.next().trim();

        if (!op.equals("+") && !op.equals("-") && !op.equals("*") && !op.equals("/")) {
            System.out.println("Operador inválido.");
            return;
        }

        Fraccion otra = ingresarFraccion(" de la segunda fracción");
        if (otra == null) {
            return;
        }

        try {
            Fraccion resultado;
            switch (op) {
                case "+": resultado = actual.sumar(otra); break;
                case "-": resultado = actual.restar(otra); break;
                case "*": resultado = actual.multiplicar(otra); break;
                default:  resultado = actual.dividir(otra); break;
            }
            System.out.println(actual.aNotacion() + " " + op + " " + otra.aNotacion()
                    + " = " + resultado.aNotacion() + " = " + resultado.valorDecimal());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                sc.next(); 
            }
        }
    }
}
