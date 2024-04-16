import models.Administrador;
import models.Inventario;
import models.Producto;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nombreDeEmpresa = "Punto de Venta";
        Administrador superAdministrador = new Administrador();
        System.out.println("Ingrese nombre del Administrador");
        superAdministrador.setName(scanner.nextLine());
        System.out.println("Ingrese una contraseña para el Administrador");
        superAdministrador.setPassword(scanner.nextLine());
        System.out.println("Bienvenido a " + nombreDeEmpresa);
        Inventario inventario = new Inventario();
        boolean run = true;
        boolean iniciarDia = false;
        do {
            System.out.println("Menu");
            System.out.println("1.-Iniciar modo Administrador");
            System.out.println("2.-Iniciar modo Cajero");
            System.out.println("3.-Salir");
            switch (ingresarEntero()){
                case 1:
                    boolean iniciar = true;
                    System.out.println("Bienvenido " + superAdministrador.getName());
                    do {
                        System.out.println("Menu");
                        System.out.println("1.-Iniciar dia");
                        System.out.println("2.-Realizar arqueo de caja del dia");
                        System.out.println("3.-Salir");
                        switch (ingresarEntero()){
                            case 1:
                                System.out.println("Dia iniciado");
                                iniciarDia = true;
                                break;
                            case 2:
                                if (iniciarDia){
                                    float arqueoDeCaja;
                                    arqueoDeCaja = superAdministrador.realizarArqueo();
                                    System.out.println("El arqueo de caja fue: " + arqueoDeCaja);
                                    System.out.println("Desea acabar el dia? Si(1) No(2)");
                                    if (ingresarEntero() == 1) {
                                        System.out.println("Dia acabado");
                                        superAdministrador.getNewCajero().reiniciarTotalVendido();
                                        iniciarDia = false;
                                    }
                                } else {
                                    System.out.println("No se ha iniciado un dia recientemente");
                                }
                                break;
                            case 3:
                                iniciar = false;
                                break;
                        }
                    } while (iniciar);
                    break;
                case 2:
                    if (iniciarDia){
                        boolean correr = true;
                        do {
                            System.out.println("Menu");
                            System.out.println("1.-Agregar producto al inventario");
                            System.out.println("2.-Eliminar producto");
                            System.out.println("3.-Ver inventario");
                            System.out.println("4.-Realizar venta de producto");
                            System.out.println("5.-Salir");
                            switch (ingresarEntero()){
                                case 1:
                                    Producto producto = new Producto();
                                    System.out.println("Ingrese nombre del producto");
                                    producto.setName(scanner.nextLine());
                                    System.out.println("Ingrese precio del producto");
                                    producto.setPriece(scanner.nextFloat());
                                    System.out.println("Ingrese cantidad de producto");
                                    producto.setQuantity(ingresarEntero());
                                    if (inventario.setProductList(producto)){
                                        System.out.println("Producto agregado");
                                    } else {
                                        System.out.println("Error al ingresar el producto");
                                    }
                                    break;
                                case 2:
                                    scanner.nextLine();
                                    System.out.println("Ingrese nombre del producto a eliminar");
                                    String nombreDel = scanner.nextLine();
                                    boolean productFinded = false;
                                    int auxIndex = 0;
                                    int delIndex = 0;
                                    for (Producto product: inventario.getProductList()){
                                        if (product.getName().equals(nombreDel)){
                                            productFinded = true;
                                            delIndex = auxIndex;
                                        } else {
                                            System.out.println("Producto no encontrado");
                                        }
                                        auxIndex++;
                                    }
                                    if (productFinded){
                                        inventario.getProductList().remove(delIndex);
                                        System.out.println("Producto borrado");
                                    }
                                    break;
                                case 3:
                                    for (Producto product: inventario.getProductList()){
                                        System.out.println(product.toString());
                                    }
                                    break;
                                case 4:
                                    scanner.nextLine();
                                    float total;
                                    System.out.println("Ingrese nombre del producto");
                                    String productName = scanner.nextLine();
                                    int auxIndex1 = 0;
                                    for (Producto productIndex : inventario.getProductList()) {
                                        if (productIndex.getName().equals(productName)) {
                                            System.out.println("Ingrese cantidad de producto");
                                            int cantidad = ingresarEntero();
                                            if (cantidad <= inventario.getProductList().get(auxIndex1).getQuantity()) {
                                                System.out.println("Total:" + (inventario.getProductList().get(auxIndex1).getPriece()*cantidad));
                                                System.out.println("Realizar venta? Si(1) No(2)");
                                                if (ingresarEntero()==1){
                                                    total = (inventario.getProductList().get(auxIndex1).getPriece()*cantidad);
                                                    superAdministrador.getNewCajero().realizarVenta(total);
                                                    System.out.println("Producto vendido");
                                                    inventario.getProductList().get(auxIndex1).setQuantity(inventario.getProductList().get(auxIndex1).getQuantity() - cantidad);
                                                } else {
                                                    System.out.println("Venta cancelada");
                                                }
                                            } else {
                                                System.out.println("Cantidad insuficiente en el inventario");
                                            }
                                            auxIndex1++;
                                        }
                                    }
                                    break;
                                case 5:
                                    scanner.nextLine();
                                    System.out.println("Ingrese contraseña de Administrador");
                                    if (scanner.nextLine().equals(superAdministrador.getPassword())) {
                                        correr = false;
                                    } else {
                                        System.out.println("Contraseña incorrecta");
                                    }
                                    break;
                            }
                        } while (correr);
                    } else {
                        System.out.println("Inicie el dia antes de iniciar como cajero porfavor");
                    }
                    break;
                case 3:
                    run = false;
                    break;
            }
        } while (run);
    }
    public static int ingresarEntero() {
        boolean run = true;
        int seleccion = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            try {
                seleccion = teclado.nextInt();
                teclado.nextLine();
                run = false;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Intente denuevo");
                teclado.nextLine();
            }
        } while (run);
        return seleccion;
    }
}