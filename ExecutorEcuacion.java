import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EcuacionCuadratica ecuacion = null;

        while (true) {
            System.out.println("\n===== ECUACION CUADRATICA =====");
            System.out.println("1. Ingresar coeficientes (a, b, c)");
            System.out.println("2. Calcular y mostrar las raices");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.print("Ingrese el coeficiente a: ");
                    double a = scanner.nextDouble();

                    System.out.print("Ingrese el coeficiente b: ");
                    double b = scanner.nextDouble();

                    System.out.print("Ingrese el coeficiente c: ");
                    double c = scanner.nextDouble();

                    if (a == 0) {
                        ecuacion = null;
                        System.out.println(
                            "Error: el coeficiente a no puede ser cero."
                        );
                    } else {
                        ecuacion = new EcuacionCuadratica(a, b, c);
                        System.out.println(
                            "Coeficientes ingresados correctamente."
                        );
                        System.out.printf(
                            "Ecuacion: (%.2f)x^2 + (%.2f)x + (%.2f) = 0%n",
                            a, b, c
                        );
                    }
                    break;
                }

                case 2: {
                    if (ecuacion == null) {
                        System.out.println(
                            "Primero debe ingresar coeficientes validos con a diferente de cero."
                        );
                    } else {
                        ecuacion.mostrarRaices();
                    }
                    break;
                }

                case 3:
                    System.out.println("Hasta luego.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
    }
}
