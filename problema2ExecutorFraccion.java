
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        System.out.println("--- Operaciones Básicas ---");
        Fraccion f1 = new Fraccion(1, 2);
        Fraccion f2 = new Fraccion(1, 3);
        
        
        System.out.println(f1 + " + " + f2 + " = " + f1.sumar(f2));
        System.out.println(f1 + " - " + f2 + " = " + f1.restar(f2));
        System.out.println(f1 + " * " + f2 + " = " + f1.multiplicar(f2));
        System.out.println(f1 + " / " + f2 + " = " + f1.dividir(f2));

        System.out.println("\n--- Simplificación Automática ---");
        Fraccion f3 = new Fraccion(8, 12);
        System.out.println("8/12 se simplifica automáticamente a: " + f3);

        System.out.println("\n--- Suma usando MCM  ---");
        Fraccion f4 = new Fraccion(1, 6);
        Fraccion f5 = new Fraccion(1, 4);
        
        System.out.println(f4 + " + " + f5 + " = " + f4.sumar(f5));

        System.out.println("\n--- Valor Decimal e Inversa ---");
        Fraccion f6 = new Fraccion(3, 4);
        System.out.println(f6 + " = " + f6.valorDecimal());
        System.out.println("Inversa de " + f6 + " = " + f6.inversa());

        System.out.println("\n--- Manejo de Errores ---");
        try {
            Fraccion invalida = new Fraccion(2, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción controlada: " + e.getMessage());
        }
    }
}
