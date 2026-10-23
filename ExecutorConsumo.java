package edu.unl.cc.ape.view;

import java.util.Scanner;

public class PlanillaExecutor {
    public static void main(String[] args) {
        mostrarCasosDePrueba();
        ingresarPlanilla();
    }

    private static void mostrarCasosDePrueba() {
        System.out.println("===== CASOS DE PRUEBA =====");
        System.out.println();

        // Caso 1: consumo 150 kWh -> subtotal $17.50, IVA $2.63, total $20.13
        PlanillaConsumo planilla1 = new PlanillaConsumo("MED-001", "Ana Torres", 1200, 1350);
        System.out.println(planilla1);
        System.out.println();

        // Caso 2: consumo 300 kWh -> subtotal $45.00, IVA $6.75, total $51.75
        PlanillaConsumo planilla2 = new PlanillaConsumo("MED-002", "Luis Jaramillo", 1000, 1300);
        System.out.println(planilla2);
        System.out.println();

        // Caso 3: lectura actual menor que la anterior -> mensaje de validación
        try {
            PlanillaConsumo planilla3 = new PlanillaConsumo("MED-003", "María Ríos", 1200, 1150);
            System.out.println(planilla3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        }
        System.out.println();
    }

    private static void ingresarPlanilla() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== NUEVA PLANILLA =====");

        System.out.print("Número de medidor: ");
        String numeroMedidor = scanner.nextLine();

        System.out.print("Nombre del titular: ");
        String titular = scanner.nextLine();

        double lecturaAnterior = leerNumero(scanner, "Lectura anterior (kWh): ");

        // Se repite hasta que la lectura actual sea válida
        PlanillaConsumo planilla = null;
        while (planilla == null) {
            double lecturaActual = leerNumero(scanner, "Lectura actual (kWh): ");
            try {
                planilla = new PlanillaConsumo(numeroMedidor, titular, lecturaAnterior, lecturaActual);
            } catch (IllegalArgumentException e) {
                System.out.println("Error de validación: " + e.getMessage());
            }
        }

        System.out.println();
        System.out.println(planilla);
    }

    private static double leerNumero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(entrada);
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("La lectura no puede ser negativa.");
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }
}
