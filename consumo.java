package edu.unl.cc.ape.domain;

/**
 * @author Ashley Mocha, Matias Ortega, Sdier Roblez, Tyrone Encarnación
 * Representa la planilla mensual de consumo eléctrico de un servicio doméstico.
 * Tarifas por bloques (valores de prueba):
 *   t1 = $0.10/kWh (primeros 100 kWh)
 *   t2 = $0.15/kWh (de 101 a 200 kWh)
 *   t3 = $0.20/kWh (más de 200 kWh)
 */
public class PlanillaConsumo {

    private static final double TARIFA_T1 = 0.10;
    private static final double TARIFA_T2 = 0.15;
    private static final double TARIFA_T3 = 0.20;
    private static final double LIMITE_BLOQUE_1 = 100;
    private static final double LIMITE_BLOQUE_2 = 200;
    private static final Double PORCENTAJE_IVA = Double.valueOf(15);

    private String numeroMedidor;
    private String titular;
    private double lecturaAnterior;
    private double lecturaActual;

    public PlanillaConsumo(String numeroMedidor, String titular, double lecturaAnterior, double lecturaActual) {
        this.numeroMedidor = numeroMedidor;
        this.titular = titular;
        this.lecturaAnterior = lecturaAnterior;
        setLecturaActual(lecturaActual);
    }

    public String getNumeroMedidor() {
        return numeroMedidor;
    }

    public void setNumeroMedidor(String numeroMedidor) {
        this.numeroMedidor = numeroMedidor;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getLecturaAnterior() {
        return lecturaAnterior;
    }

    public void setLecturaAnterior(double lecturaAnterior) {
        if (lecturaAnterior > this.lecturaActual) {
            throw new IllegalArgumentException("La lectura anterior (" + lecturaAnterior
                    + " kWh) no puede ser mayor que la lectura actual (" + this.lecturaActual + " kWh).");
        }
        this.lecturaAnterior = lecturaAnterior;
    }

    public double getLecturaActual() {
        return lecturaActual;
    }

    /**
     * Asigna la lectura actual solo si es mayor o igual que la lectura anterior.
     */
    public void setLecturaActual(double lecturaActual) {
        if (lecturaActual < this.lecturaAnterior) {
            throw new IllegalArgumentException("La lectura actual (" + lecturaActual
                    + " kWh) debe ser mayor o igual que la lectura anterior (" + this.lecturaAnterior + " kWh).");
        }
        this.lecturaActual = lecturaActual;
    }

    public double calcularConsumo() {
        return lecturaActual - lecturaAnterior;
    }

    /**
     * Calcula el valor a pagar por bloques antes de impuestos.
     */
    public double calcularSubtotal() {
        double consumo = calcularConsumo();
        double subtotal;
        if (consumo <= LIMITE_BLOQUE_1) {
            subtotal = consumo * TARIFA_T1;
        } else if (consumo <= LIMITE_BLOQUE_2) {
            subtotal = LIMITE_BLOQUE_1 * TARIFA_T1
                    + (consumo - LIMITE_BLOQUE_1) * TARIFA_T2;
        } else {
            subtotal = LIMITE_BLOQUE_1 * TARIFA_T1
                    + (LIMITE_BLOQUE_2 - LIMITE_BLOQUE_1) * TARIFA_T2
                    + (consumo - LIMITE_BLOQUE_2) * TARIFA_T3;
        }
        return redondear(subtotal);
    }

    /**
     * Calcula el IVA (15 %) sobre el subtotal usando la clase wrapper Double.
     */
    public Double calcularIva() {
        Double subtotal = Double.valueOf(calcularSubtotal());
        return subtotal * PORCENTAJE_IVA / 100;
    }

    public double calcularTotal() {
        return redondear(calcularSubtotal() + calcularIva());
    }

    private static double redondear(double valor) {
        return Math.round(valor * 100) / 100.0;
    }

    @Override
    public String toString() {
        return String.format(
                "Planilla de consumo eléctrico%n"
                        + "  Medidor:          %s%n"
                        + "  Titular:          %s%n"
                        + "  Lectura anterior: %.0f kWh%n"
                        + "  Lectura actual:   %.0f kWh%n"
                        + "  Consumo:          %.0f kWh%n"
                        + "  Subtotal:         $%.2f%n"
                        + "  IVA (15%%):        $%.2f%n"
                        + "  Total a pagar:    $%.2f",
                numeroMedidor, titular, lecturaAnterior, lecturaActual,
                calcularConsumo(), calcularSubtotal(), redondear(calcularIva()), calcularTotal());
    }
}
