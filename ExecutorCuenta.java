package edu.unl.cc.ape.view;

import edu.unl.cc.ape.domain.CuentaBancaria;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * @author Ashley Mocha, Matias Ortega, Sdier Roblez, Tyrone Encarnación
 * Cuenta bancaria con menú interactivo
 */

public class CuentaBancariaExecutor {
    private final Consola consola;
    private CuentaBancaria cuenta;

    public CuentaBancariaExecutor(Consola consola) {
        this.consola = consola;
    }

    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            new CuentaBancariaExecutor(new Consola(entrada, System.out)).iniciar();
        }
    }

    public void iniciar() {
        try {
            boolean continuar = true;
            while (continuar) {
                consola.mostrar("""

                        CUENTA BANCARIA
                        1. Crear cuenta
                        2. Depositar
                        3. Retirar
                        4. Consultar datos, saldo e historial
                        0. Salir
                        """);
                int opcion = consola.leerOpcion("Opción: ", 0, 4);
                try {
                    switch (opcion) {
                        case 0 -> continuar = false;
                        case 1 -> crearCuenta();
                        case 2 -> depositar();
                        case 3 -> retirar();
                        case 4 -> consultarCuenta();
                    }
                } catch (IllegalArgumentException | IllegalStateException e) {
                    consola.mostrar("Error: " + e.getMessage());
                }
            }
            consola.mostrar("Hasta pronto.");
        } catch (NoSuchElementException e) {
            consola.mostrar("Fin de entrada. Programa terminado.");
        }
    }

    private void crearCuenta() {
        if (cuenta != null) {
            consola.mostrar("Ya existe una cuenta creada.");
            return;
        }
        String numero = consola.leerTexto("Número de cuenta: ");
        String titular = consola.leerTexto("Nombre del titular: ");
        float saldoInicial = consola.leerNumero("Saldo inicial: ");
        cuenta = new CuentaBancaria(numero, titular, saldoInicial);
        consola.mostrar("Cuenta creada correctamente.");
    }

    private void validarCuenta() {
        if (cuenta == null) {
            throw new IllegalStateException("Primero debe crear una cuenta.");
        }
    }

    private void depositar() {
        validarCuenta();
        cuenta.depositar(consola.leerNumero("Monto a depositar: "));
        consola.mostrar("Depósito realizado. Saldo actual: " + cuenta.getSaldo());
    }

    private void retirar() {
        validarCuenta();
        cuenta.retirar(consola.leerNumero("Monto a retirar: "));
        consola.mostrar("Retiro realizado. Saldo actual: " + cuenta.getSaldo());
    }

    private void consultarCuenta() {
        validarCuenta();
        consola.mostrar(cuenta.toString());
        consola.mostrar("Historial de movimientos:");
        if (cuenta.getHistorial().isEmpty()) {
            consola.mostrar("Sin movimientos registrados.");
        } else {
            for (String movimiento : cuenta.getHistorial()) {
                consola.mostrar(movimiento);
            }
        }
    }
}
