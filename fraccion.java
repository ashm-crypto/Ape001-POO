package edu.unl.cc.ape.domain;

public class Fraccion {
    private int numerador;
    private int denominador;

    public Fraccion(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("Error: el denominador no puede ser cero.");
        }

        // Se trabaja con long para que Math.abs(Integer.MIN_VALUE) y los cálculos no desborden
        long n = numerador;
        long d = denominador;
        long mcd = calcularMcd(Math.abs(n), Math.abs(d));
        n = n / mcd;
        d = d / mcd;

        // El signo siempre se mantiene en el numerador
        if (d < 0) {
            n = -n;
            d = -d;
        }

        // toIntExact lanza ArithmeticException si el valor no cabe en un int
        this.numerador = Math.toIntExact(n);
        this.denominador = Math.toIntExact(d);
    }

    // Crea una fracción a partir de long: primero simplifica y luego convierte a int
    private static Fraccion desdeLong(long n, long d) {
        if (d == 0) {
            throw new IllegalArgumentException("Error: el denominador no puede ser cero.");
        }
        long mcd = calcularMcd(Math.abs(n), Math.abs(d));
        n = n / mcd;
        d = d / mcd;
        return new Fraccion(Math.toIntExact(n), Math.toIntExact(d));
    }

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public Fraccion sumar(Fraccion otra) {
        // MCM de los denominadores en lugar de multiplicar en cruz
        long mcm = calcularMcm(this.denominador, otra.denominador);
        long nuevoNum = (mcm / this.denominador) * this.numerador + (mcm / otra.denominador) * otra.numerador;
        return desdeLong(nuevoNum, mcm);
    }

    public Fraccion restar(Fraccion otra) {
        long mcm = calcularMcm(this.denominador, otra.denominador);
        long nuevoNum = (mcm / this.denominador) * this.numerador - (mcm / otra.denominador) * otra.numerador;
        return desdeLong(nuevoNum, mcm);
    }

    public Fraccion multiplicar(Fraccion otra) {
        return desdeLong((long) this.numerador * otra.numerador, (long) this.denominador * otra.denominador);
    }

    public Fraccion dividir(Fraccion otra) {
        if (otra.numerador == 0) {
            throw new IllegalArgumentException("Error: no se puede dividir entre una fracción con numerador cero.");
        }
        return desdeLong((long) this.numerador * otra.denominador, (long) this.denominador * otra.numerador);
    }

    public double valorDecimal() {
        return (double) numerador / denominador;
    }

    public Fraccion inversa() {
        if (numerador == 0) {
            throw new IllegalArgumentException("Error: una fracción con numerador cero no tiene inversa.");
        }
        return new Fraccion(denominador, numerador);
    }

    // Máximo Común Divisor (algoritmo de Euclides)
    private static long calcularMcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Mínimo Común Múltiplo
    private static long calcularMcm(long a, long b) {
        return (a / calcularMcd(a, b)) * b;
    }

    // Notación pedida en el problema: [n / d]
    public String aNotacion() {
        return "[" + numerador + " / " + denominador + "]";
    }

    @Override
    public String toString() {
        if (denominador == 1) return String.valueOf(numerador);
        return numerador + "/" + denominador;
    }
}
