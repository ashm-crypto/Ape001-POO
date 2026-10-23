package ape02;

public class Fraccion {
    private int numerador;
    private int denominador;

    public Fraccion(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("Error: el denominador no puede ser cero.");
        }

        // Se simplifica al crear el objeto, así todo resultado ya sale simplificado.
        int mcd = calcularMcd(Math.abs(numerador), Math.abs(denominador));
        int num = numerador / mcd;
        int den = denominador / mcd;

        // El signo siempre se mantiene en el numerador
        if (den < 0) {
            num = -num;
            den = -den;
        }

        this.numerador = num;
        this.denominador = den;
    }

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public Fraccion sumar(Fraccion otra) {
        // Se usa el MCM de los denominadores en lugar de multiplicar en cruz
        int mcm = calcularMcm(this.denominador, otra.denominador);
        int nuevoNum = (mcm / this.denominador) * this.numerador + (mcm / otra.denominador) * otra.numerador;
        return new Fraccion(nuevoNum, mcm);
    }

    public Fraccion restar(Fraccion otra) {
        int mcm = calcularMcm(this.denominador, otra.denominador);
        int nuevoNum = (mcm / this.denominador) * this.numerador - (mcm / otra.denominador) * otra.numerador;
        return new Fraccion(nuevoNum, mcm);
    }

    public Fraccion multiplicar(Fraccion otra) {
        return new Fraccion(this.numerador * otra.numerador, this.denominador * otra.denominador);
    }

    public Fraccion dividir(Fraccion otra) {
        if (otra.numerador == 0) {
            throw new IllegalArgumentException("Error: división por cero.");
        }
        return new Fraccion(this.numerador * otra.denominador, this.denominador * otra.numerador);
    }

    public double valorDecimal() {
        return (double) numerador / denominador;
    }

    public Fraccion inversa() {
        if (numerador == 0) {
            throw new IllegalArgumentException("Error: fracción con numerador cero no tiene inversa.");
        }
        return new Fraccion(denominador, numerador);
    }

    // Máximo Común Divisor (algoritmo de Euclides)
    private int calcularMcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Mínimo Común Múltiplo
    private int calcularMcm(int a, int b) {
        return (a * b) / calcularMcd(a, b);
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
