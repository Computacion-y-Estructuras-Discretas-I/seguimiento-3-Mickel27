package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import structures.PilaGenerica;
import structures.TablasHash;

public class Main {

    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    public void ejecutar() throws Exception {
        while (true) {
            System.out.println("\nSeleccione la opcion:");
            System.out.println("1. Punto 1, Verificar balanceo de expresión");
            System.out.println("2. Punto 2, Encontrar pares con suma objetivo");
            System.out.println("3. Salir del programa");
            System.out.print("Opcion: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese expresion a verificar:");
                    String expresion = sc.nextLine();
                    boolean resultado = verificarBalanceo(expresion);
                    System.out.println("Resultado: " + (resultado ? "TRUE" : "FALSE"));
                    break;

                case 2:
                    System.out.println("Ingrese numeros separados por espacio: ");
                    String lineaNumeros = sc.nextLine();
                    System.out.println("Ingrese suma objetivo: ");
                    int objetivo = Integer.parseInt(sc.nextLine());

                    String[] partes = lineaNumeros.trim().split("\\s+");
                    int[] numeros = new int[partes.length];
                    for (int i = 0; i < partes.length; i++) {
                        numeros[i] = Integer.parseInt(partes[i]);
                    }

                    encontrarParesConSuma(numeros, objetivo);
                    break;

                case 3:
                    System.out.println("Chao");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opcion no permitida");
            }
        }
    }

    /**
     * Verifica si la expresion esta balanceada usando PilaGenerica
     *
     * @param s expresion a verificar
     * @return true si esta balanceada, false si no
     */
    public boolean verificarBalanceo(String s) {

        String[] partes = s.trim().split("");

        PilaGenerica<String> pila = new PilaGenerica<>(partes.length);

        for (int i = 0; i < pila.getSize(); i++) {
            String current = partes[i];
            if (current.equals("(")|| current.equals("{") || current.equals("[")){
                pila.Push(current);
            } else if (current.equals(")") || current.equals("}") || current.equals("]")){
                if (pila.isEmpty()){
                    return false;
                }
                switch (pila.Pop()){
                    case "(":
                        if (!current.equals(")")){
                            return false;
                        }
                        break;
                    case "{":
                        if (!current.equals("}")){
                            return false;
                        }
                        break;
                    case "[":
                        if (!current.equals("]")){
                            return false;
                        }
                        break;
                }
            }
        }
        return pila.isEmpty();
    }

    /**
     * Encuentra y muestra todos los pares unicos de numeros que sumen objetivo usando TablasHash.
     * @param numeros arreglo de numeros enteros
     * @param objetivo suma objetivo
     */
    public void encontrarParesConSuma(int[] numeros, int objetivo) throws Exception {

        String msj = "";
        TablasHash tabla = new TablasHash(objetivo);

        for (int i = 0; i < numeros.length; i++) {
            if (!tabla.search(numeros[i], numeros[i])){
                tabla.insert(numeros[i], numeros[i]);
            }
        }

        for (int i = 0; i < numeros.length; i++) {
            int complemento = objetivo - numeros[i];
            if (tabla.search(complemento, complemento)){

                //el comparador (numeros[i] < complemento) ayuda para que la dupla solo se añada en una dirección:
                // como solo en alguno de los dos casos se cumple esta regla (ya sabemos que el numero y su complemento
                // no son iguales) solo se añade una versión de la tupla. (por ejemplo, teniendo objetivo 10 se añade
                // la dupla 2 y 8 pero no la de 8 y 2).

                if (numeros[i] < complemento){
                    msj+="("+numeros[i]+", "+complemento+") ";
                }
            }
        }
        System.out.println(msj);
    }

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        app.ejecutar();
    }
}
