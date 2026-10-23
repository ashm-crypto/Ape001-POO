package edu.unl.cc.ape.domain;
/**
 * @author Ashley Mocha, Matias Ortega, Sdier Roblez, Tyrone Encarnación
 * Ecuación cuadrática con menú interactivo
 */

public class EcuacionCuadratica {

    private double a;
    private double b;
    private double c;

    public EcuacionCuadratica(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double calcularDiscriminante() {
        return b * b - 4 * a * c;
    }

    public void mostrarRaices() {
        double discriminante = calcularDiscriminante();

        System.out.printf("Discriminante: %.4f%n", discriminante);

        if (discriminante > 0) {
            double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);

            System.out.printf("Primera raiz real: x1 = %.4f%n", x1);
            System.out.printf("Segunda raiz real: x2 = %.4f%n", x2);
        } else if (discriminante == 0) {
            double x = -b / (2 * a);

            System.out.printf("Raiz doble: x = %.4f%n", x);
        } else {
            double parteReal = -b / (2 * a);
            double parteImaginaria = Math.sqrt(-discriminante) / (2 * Math.abs(a));

            System.out.printf(
                "Primera raiz compleja: x1 = %.4f + %.4fi%n",
                parteReal, parteImaginaria
            );
            System.out.printf(
                "Segunda raiz compleja: x2 = %.4f - %.4fi%n",
                parteReal, parteImaginaria
            );
        }
    }
}
