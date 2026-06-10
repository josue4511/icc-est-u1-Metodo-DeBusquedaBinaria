import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner ingresoPersonas = new Scanner(System.in);

        int cantidad = 0;

        System.out.print("Ingrese un numero de personas para el listado: ");

        while (!ingresoPersonas.hasNextInt()) {
            System.out.println("Error: Debe ingresar un numero entero");
            ingresoPersonas.next();
            System.out.print("Ingrese un numero de personas para el listado: ");
        }

        cantidad = ingresoPersonas.nextInt();
        ingresoPersonas.nextLine();

        Persona[] personas = new Persona[cantidad];

        for (int i = 0; i < cantidad; i++) {

            System.out.println("\nPersona " + (i + 1));

            System.out.print("Nombre: ");
            String nombre = ingresoPersonas.nextLine();

            int edad = -1;

            while (edad < 0) {

                System.out.print("Edad: ");

                while (!ingresoPersonas.hasNextInt()) {
                    System.out.println("Error: Debe ingresar un numero entero");
                    ingresoPersonas.next();
                    System.out.print("Edad: ");
                }

                edad = ingresoPersonas.nextInt();
                ingresoPersonas.nextLine();

                if (edad < 0) {
                    System.out.println("Error: La edad no puede ser negativa");
                }
            }

            personas[i] = new Persona(nombre, edad);
        }

        // Ordenamiento Burbuja
        for (int i = 0; i < personas.length - 1; i++) {
            for (int j = 0; j < personas.length - i - 1; j++) {

                if (personas[j].getEdad() > personas[j + 1].getEdad()) {

                    Persona aux = personas[j];
                    personas[j] = personas[j + 1];
                    personas[j + 1] = aux;
                }
            }
        }

        int edadBuscar;

        System.out.print("\nCual es la edad que desea buscar: ");

        while (!ingresoPersonas.hasNextInt()) {
            System.out.println("Error: Debe ingresar un numero entero");
            ingresoPersonas.next();
            System.out.print("Cual es la edad que desea buscar: ");
        }

        edadBuscar = ingresoPersonas.nextInt();

        int bajo = 0;
        int alto = personas.length - 1;

        boolean existe = false;
        Persona personaEncontrada = null;

        System.out.println("\nProceso de Busqueda Binaria:");

        while (bajo <= alto) {

            int centro = (bajo + alto) / 2;
            int valorCentro = personas[centro].getEdad();

            for (int i = bajo; i <= alto; i++) {
                System.out.print(personas[i].getEdad());

                if (i < alto) {
                    System.out.print(" | ");
                }
            }

            System.out.println();

            System.out.print(
                    "bajo=" + bajo
                    + " alto=" + alto
                    + " centro=" + centro
                    + " valorCentro=" + valorCentro
            );

            if (valorCentro == edadBuscar) {

                System.out.println(" --> ENCONTRADO");
                existe = true;
                personaEncontrada = personas[centro];
                break;

            } else if (valorCentro < edadBuscar) {

                System.out.println(" --> DERECHA");
                bajo = centro + 1;

            } else {

                System.out.println(" --> IZQUIERDA");
                alto = centro - 1;
            }
        }

        System.out.println();

        if (existe) {
            System.out.println(
                    "La persona con la edad "
                    + edadBuscar
                    + " es "
                    + personaEncontrada.getNombre()
            );
        } else {
            System.out.println(
                    "No se encontro ninguna persona con la edad "
                    + edadBuscar
            );
        }

        ingresoPersonas.close();
    }
}