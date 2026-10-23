
public class Fraccion {
    private int numerador;
    private int denominador;

    public Fraccion(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("Error: el denominador no puede ser cero.");
        }
        
        // Eficiencia: Simplificamos directamente al crear el objeto. 
        // Así evitamos tener que llamar a simplificar() en cada operación.
        int mcd = calcularMcd(Math.abs(numerador), Math.abs(denominador));
        int num = numerador / mcd;
        int den = denominador / mcd;
        
        // Mantener el signo siempre en el numerador
        if (den < 0) {
            num = -num;
            den = -den;
        }
        
        this.numerador = num;
        this.denominador = den;
    }

    public Fraccion sumar(Fraccion otra) {
        // Eficiencia (lo que mencionó el profesor): Usar el Mínimo Común Múltiplo (MCM) 
        // evita multiplicar en cruz a ciegas, reduciendo el tamaño de los números en memoria.
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

    // Método privado para calcular Máximo Común Divisor
    private int calcularMcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Método privado para calcular Mínimo Común Múltiplo
    private int calcularMcm(int a, int b) {
        return (a * b) / calcularMcd(a, b);
    }

    @Override
    public String toString() {
        if (denominador == 1) return String.valueOf(numerador); // Mostrar "2" en vez de "2/1"
        return numerador + "/" + denominador;
    }
}
