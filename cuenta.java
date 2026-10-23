package edu.unl.cc.ape.domain;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Ashley Mocha, Matias Ortega, Sdier Roblez, Tyrone Encarnación
 * Cuenta bancaria con menú interactivo
 */

public class CuentaBancaria {
    private final String numeroCuenta;
    private final String nombreTitular;
    private float saldo;
    private final List<String> historial = new ArrayList<>();

    public CuentaBancaria(String numeroCuenta, String nombreTitular, float saldoInicial) {
        if (numeroCuenta == null || numeroCuenta.isBlank()) {
            throw new IllegalArgumentException("El número de cuenta es obligatorio.");
        }
        if (nombreTitular == null || nombreTitular.isBlank()) {
            throw new IllegalArgumentException("El nombre del titular es obligatorio.");
        }
        if (!Float.isFinite(saldoInicial) || saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial debe ser finito y no negativo.");
        }
        this.numeroCuenta = numeroCuenta.trim();
        this.nombreTitular = nombreTitular.trim();
        this.saldo = saldoInicial;
    }

    public void depositar(float monto) {
        validarMonto(monto);
        float nuevoSaldo = saldo + monto;
        if (!Float.isFinite(nuevoSaldo)) {
            throw new IllegalArgumentException("El depósito excede el saldo máximo permitido.");
        }
        saldo = nuevoSaldo;
        historial.add("Depósito: +" + formatearMonto(monto));
    }

    public void retirar(float monto) {
        validarMonto(monto);
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente: el retiro no puede exceder el saldo disponible.");
        }
        saldo -= monto;
        historial.add("Retiro: -" + formatearMonto(monto));
    }

    private void validarMonto(float monto) {
        if (!Float.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser finito y mayor que cero.");
        }
    }

    private String formatearMonto(float monto) {
        String texto = Float.toString(monto);
        return texto.endsWith(".0") ? texto.substring(0, texto.length() - 2) : texto;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public float getSaldo() {
        return saldo;
    }

    public List<String> getHistorial() {
        return List.copyOf(historial);
    }

    @Override
    public String toString() {
        return "Número de cuenta: " + numeroCuenta
                + "\nTitular: " + nombreTitular
                + "\nSaldo: " + formatearMonto(saldo);
    }
}
