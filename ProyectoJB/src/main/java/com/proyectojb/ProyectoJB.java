package com.proyectojb;

import com.proyectojb.logica.Empleados;
import com.proyectojb.persistencia.ControladoraPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProyectoJB {
    static Empleados empleado = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ControladoraPersistencia controlPersis = new ControladoraPersistencia();
        boolean salirMenu = false;

        ///////////////COMIENZO////////////////

        System.out.println("Bienvenido al sistema de registro de empleado.");
        do {

            System.out.println("Que operación desea realizar?");
            System.out.println("1 Insertar un nuevo usuario");
            System.out.println("2 Eliminar un empleado");
            System.out.println("3 Modificar un empleado");
            System.out.println("4 Listar empleados");
            System.out.println("5 Buscar empleado por cargo");
            System.out.println("6 Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1://CREAR EMPLEADO---------------------------------------------------------------------------------
                    Scanner scanner1 = new Scanner(System.in);
                    String nombre;
                    do {//Uso un do while para que en caso de que no entren los valores correctos se los vuelva a pedir.
                        System.out.println("Ingrese nombre:");
                        nombre = scanner.nextLine();
                        if (nombre.trim().isEmpty()) {
                            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido.");
                        }
                    } while (nombre.trim().isEmpty()); // Continuar pidiendo el nombre hasta que no esté vacío
                    System.out.println("Nombre ingresado: " + nombre);


                    String apellido;
                    do{
                        System.out.println("Ingrese apellido");
                        apellido= scanner.nextLine();
                        if (apellido.trim().isEmpty()) {
                            System.out.println("El appelido no puede estar vacío. Por favor, ingrese un apellido válido.");
                        }
                    } while (apellido.trim().isEmpty());
                    System.out.println("Apellido ingresado: " + apellido);

                    String cargo;
                    do{
                        System.out.println("Ingrese cargo");
                        cargo = scanner.nextLine();
                        if (cargo.trim().isEmpty()) {
                            System.out.println("El cargo no puede estar vacío. Por favor, ingrese un cargo válido.");
                        }
                    } while (cargo.trim().isEmpty());
                    System.out.println("Cargo ingresado: " + cargo);

                    int salario = 0;
                    while (true){
                        System.out.println("Ingrese salario");
                            if(scanner.hasNextInt()){//Validamos que lo que entran son numeros y si no se se le lanza el aviso
                            salario = scanner.nextInt();

                            break;
                            }else {
                                System.out.println("Salario no valido. Por favor, ingrese un salario válido.");
                                scanner.next();
                            }
                    }
                    System.out.println("Salario ingresado: "+ salario);
                    scanner.nextLine();


                    String fechaInicio;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha;
                    do {
                        System.out.println("Ingrese fecha inicio con formato DD/MM/AAAA:");
                        fechaInicio = scanner.nextLine().trim();

                        try {
                            // Intenta parsear la cadena de entrada como una fecha en el formato especificado
                            fecha = dateFormat.parse(fechaInicio);

                            // Verifica si el resultado del parsing es igual a la cadena de entrada
                            // Esto asegura que la entrada sea una fecha válida en el formato esperado
                            if (fechaInicio.equals(dateFormat.format(fecha))) {
                                break; // Sale del bucle si la fecha es válida
                            } else {
                                System.out.println("Fecha no válida. Por favor, ingrese una fecha en el formato DD/MM/AAAA.");
                            }
                        } catch (ParseException e) {
                            System.out.println("Fecha no válida. Por favor, ingrese una fecha en el formato DD/MM/AAAA.");
                        }
                    } while (true);
                    System.out.println("Fecha de inicio ingresada: " + fechaInicio);

                    System.out.println("Usuario creado correctamente");

                    empleado = new Empleados(null,
                            nombre,
                            apellido,
                            cargo,
                            salario,
                            fechaInicio);
                    controlPersis.crearEmpelado(empleado);
                    empleado.setFechaInicio(fechaInicio);
                    controlPersis.modificarEmpleado(empleado); // FORMA EN LA QUE CREO LA FECHA PORQUE DESDE QUE METÍ EN EL CASE 1
                    break;                                     // LA FECHA DE INICIO NO CONSEGUÍA QUE SE ALMACENARA EN LA BASE DE DATOS
                                                               // PERO DE ESTA MANERA SI AUNQUE NO SE SI ES LA MEJOR.
                case 2:
                    System.out.println("Eliminar empleado");
                    List<Empleados> listaEmpleados = controlPersis.traerEmplados();
                    for (Empleados emple : listaEmpleados) {
                        System.out.println(emple.toString());
                    }
                    System.out.println("Para dar de baja al empleado introduzca su id:");
                    Long id = scanner.nextLong();
                    controlPersis.buscarEmpleado(id);
                    controlPersis.eliminarEmpleado(id);
                    System.out.println("Empleado eliminado correctamente");
                    break;
                case 3:
                    boolean salirModificar = false;
                    System.out.println("Modificar empleado");


                    System.out.println("Para modificar al empleado introduzca su id:");
                    int idMod = scanner.nextInt();


                    List<Empleados> listaEmpleadosMod = controlPersis.traerEmplados();
                    for (Empleados emple : listaEmpleadosMod) {
                        if (emple.getId() == idMod) {
                            System.out.println(emple.toString());
                            System.out.println();

                            System.out.println("¿Que dato desea modificar(nombre o apellido)?");
                            System.out.println("1 para nombre");
                            System.out.println("2 para apellido");
                            System.out.println("3 para salario");
                            System.out.println("4 para cargo");
                            System.out.println("5 para fecha de inicio");
                            System.out.println("6 para salir");

                            System.out.println("Elige una opción(1-6): ");
                            int opcionModificar = scanner.nextInt();

                            switch (opcionModificar) {
                                case 1:
                                    scanner.nextLine();
                                    System.out.println("Ingrese el nuevo nombre");
                                    String nombreModificar;
                                    do {
                                        nombreModificar = scanner.nextLine();
                                        if (nombreModificar.trim().isEmpty() ) {
                                            System.out.println("Nombre inválido. Por favor, ingrese un nombre sin espacios en blanco ni números.");
                                        }
                                    } while (nombreModificar.trim().isEmpty() );

                                    emple.setNombre(nombreModificar);
                                    controlPersis.modificarEmpleado(emple);
                                    System.out.println("Nombre modificado correctamente: "+nombreModificar);

                                    break;
                                case 2:
                                    scanner.nextLine();
                                    System.out.println("Ingrese el nuevo apellido");
                                    String apellidoModificar;
                                    do {
                                        apellidoModificar = scanner.nextLine();
                                        if (apellidoModificar.trim().isEmpty() ) {
                                            System.out.println("Apellido inválido. Por favor, ingrese un nombre sin espacios en blanco ni números.");
                                        }
                                    } while (apellidoModificar.trim().isEmpty() );

                                    emple.setNombre(apellidoModificar);
                                    controlPersis.modificarEmpleado(emple);
                                    System.out.println("Apellido modificado correctamente: "+apellidoModificar);
                                    break;

                                case 3:
                                    scanner.nextLine();
                                    System.out.println("Ingrese el nuevo salario");
                                    int salarioModificado;
                                    do {
                                        while (!scanner.hasNextInt()) {
                                            System.out.println("Salario inválido. Por favor, ingrese un valor numérico.");
                                            scanner.next(); // Limpiar el buffer del scanner
                                        }
                                        salarioModificado = scanner.nextInt();
                                        if (salarioModificado <= 0) {
                                            System.out.println("Salario inválido. El salario debe ser un valor positivo.");
                                        }
                                    } while (salarioModificado <= 0);

                                    emple.setSalario(salarioModificado);
                                    controlPersis.modificarEmpleado(emple);
                                    System.out.println("Salario modificado correctamente.");
                                    break;
                                case 4:
                                    scanner.nextLine();
                                    System.out.println("Ingrese el nuevo cargo");
                                    String cargoModificado;
                                    do {
                                        cargoModificado = scanner.nextLine();
                                        if (cargoModificado.trim().isEmpty() ) {
                                            System.out.println("Cargo inválido. Por favor, ingrese un nombre sin espacios en blanco ni números.");
                                        }
                                    } while (cargoModificado.trim().isEmpty() );

                                    emple.setNombre(cargoModificado);
                                    controlPersis.modificarEmpleado(emple);
                                    System.out.println("Cargo modificado correctamente: "+cargoModificado);
                                    break;

                                case 5:
                                    dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    System.out.println("Ingrese La fecha a modificar con formato DD/MM/AAAA");
                                    String fechaModificada;
                                    do {
                                        fechaModificada = scanner.nextLine().trim();
                                        try {
                                            fecha = dateFormat.parse(fechaModificada);

                                            if (fechaModificada.equals(dateFormat.format(fecha))) {
                                                break;
                                            } else {
                                                System.out.println("Fecha no válida. Por favor, ingrese una fecha en el formato DD/MM/AAAA.");
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Fecha no válida. Por favor, ingrese una fecha en el formato DD/MM/AAAA.");
                                        }
                                    } while (true);

                                    emple.setFechaInicio(fechaModificada);
                                    controlPersis.modificarEmpleado(emple);
                                    System.out.println("Fecha modificada correctamente.");
                                    break;

                                case 6:
                                    salirModificar = true;
                                    System.out.println("Hasta luego...");
                                    break;

                                default:
                                    System.out.println("Opción no valida");
                                    break;
                            }
                            System.out.println();
                            scanner.nextLine();
                            System.out.println("El empleado modificado es: ");
                            System.out.println(emple);
                            System.out.println();

                        }
                    }
                        break;
                    case 4:
                        System.out.println("Listado de los empleados");
                        List<Empleados> empleados = controlPersis.traerEmplados();
                        for (Empleados emple : empleados) {
                            System.out.println(emple.toString());
                            System.out.println("------------------------------");
                        }
                         break;
                case 5:
                    System.out.println("Introduce el cargo a buscar: ");
                    String cargoBuscar = scanner.nextLine();
                    empleados = controlPersis.buscarEmpleadoPorCargo(cargoBuscar);
                    for (Empleados emple : empleados) {
                        if (emple.getCargo().equalsIgnoreCase(cargoBuscar)) {
                            System.out.println(emple.toString());
                            System.out.println("------------------------------");
                        }

                    }
                    break;
                case 6:
                    System.out.println("Hasta luego...");
                    salirMenu = true;
                    break;
                default:
                    System.out.println("Opción no valida");

            }

        }while (!salirMenu);

    }
}

