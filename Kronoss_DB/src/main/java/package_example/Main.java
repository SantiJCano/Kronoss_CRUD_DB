package package_example;

import DAO.*;
import model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Menú Principal =====");
            System.out.println("1. Recordatorios Generales");
            System.out.println("2. Actividad Física");
            System.out.println("3. Eventos");
            System.out.println("4. Medicamentos");
            System.out.println("5. Personalizados");
            System.out.println("6. Reuniones");
            System.out.println("7. Tareas");
            System.out.println("8. Terminar");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();
            switch (opcion) {
                case 1: crudRecordatorios(); break;
                case 2: crudActividadFisica(); break;
                case 3: crudEventos(); break;
                case 4: crudMedicamentos(); break;
                case 5: crudPersonalizados(); break;
                case 6: crudReuniones(); break;
                case 7: crudTareas(); break;
                case 8: System.out.println("¡Hasta luego!"); return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // CRUD Recordatorios Generales
    private static void crudRecordatorios() {
        RecordatoriosCRUD crud = new RecordatoriosCRUD();
        while (true) {
            System.out.println("\n--- CRUD de Recordatorios Generales ---");
            menuCrud();
            int op = leerEntero();
            switch (op) {
                case 1: // Agregar
                    Recordatorio r = leerRecordatorio();
                    crud.agregarRecordatorios(r);
                    System.out.println("Agregado correctamente.");
                    break;
                case 2: // Buscar
                    System.out.print("Ingrese título: ");
                    String t = sc.nextLine();
                    System.out.print("Ingrese ID: ");
                    int id = leerEntero();
                    List<Recordatorio> encontrados = crud.BuscarRecordatorioPortituloId(t, id);
                    encontrados.forEach(Main::mostrarRecordatorio);
                    break;
                case 3: // Actualizar
                    Recordatorio rAct = leerRecordatorio();
                    crud.actualizarRecordatorios(rAct);
                    System.out.println("Actualizado correctamente.");
                    break;
                case 4: // Eliminar
                    System.out.print("Ingrese ID a eliminar: ");
                    crud.eliminarRecordatorio(leerEntero());
                    System.out.println("Eliminado correctamente.");
                    break;
                case 5: // Listar
                    crud.listarRecordatorios().forEach(Main::mostrarRecordatorio);
                    break;
                case 6: return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // CRUD Actividad Física
    private static void crudActividadFisica() {
        ActividadFisicaCRUD crud = new ActividadFisicaCRUD();
        RecordatoriosCRUD crudGen = new RecordatoriosCRUD();
        while (true) {
            System.out.println("\n--- CRUD de Actividad Física ---");
            menuCrud();
            int op = leerEntero();
            switch (op) {
                case 1: // Agregar
                    Recordatorio r = leerRecordatorio();
                    crudGen.agregarRecordatorios(r);
                    ActividadFisica af = leerActividadFisicaSoloEspecifico(r.getId());
                    crud.agregarActividadFisica(af);
                    System.out.println("Agregado correctamente.");
                    break;
                case 2: // Buscar
                    System.out.print("Ingrese nombre de la actividad: ");
                    String actividad = sc.nextLine();
                    System.out.print("Ingrese ID: ");
                    int id = leerEntero();
                    crud.buscarActividadFisicaPorTituloId(actividad, id).forEach(Main::mostrarActividadFisica);
                    break;
                case 3: // Actualizar
                    System.out.print("Ingrese ID del registro a actualizar: ");
                    int idAct = leerEntero();
                    Recordatorio rAct = leerRecordatorioConId(idAct);
                    crudGen.actualizarRecordatorios(rAct);
                    ActividadFisica afAct = leerActividadFisicaSoloEspecifico(idAct);
                    crud.actualizarActividadFisica(afAct);
                    System.out.println("Actualizado correctamente.");
                    break;
                case 4: // Eliminar
                    System.out.print("Ingrese ID a eliminar: ");
                    int idDel = leerEntero();
                    crud.eliminarActividadFisica(idDel);
                    crudGen.eliminarRecordatorio(idDel);
                    System.out.println("Eliminado correctamente.");
                    break;
                case 5: // Listar
                    crud.listarActividadesFisicas().forEach(Main::mostrarActividadFisica);
                    break;
                case 6: return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // CRUD Eventos
    private static void crudEventos() {
        EventoCRUD crud = new EventoCRUD();
        RecordatoriosCRUD crudGen = new RecordatoriosCRUD();
        while (true) {
            System.out.println("\n--- CRUD de Eventos ---");
            menuCrud();
            int op = leerEntero();
            switch (op) {
                case 1: // Agregar
                    Recordatorio r = leerRecordatorio();
                    crudGen.agregarRecordatorios(r);
                    Evento e = leerEventoSoloEspecifico(r.getId());
                    crud.agregarEvento(e);
                    System.out.println("Agregado correctamente.");
                    break;
                case 2: // Buscar
                    System.out.print("Ingrese ubicación: ");
                    String ubicacion = sc.nextLine();
                    System.out.print("Ingrese ID: ");
                    int id = leerEntero();
                    crud.buscarEventoPorTituloId(ubicacion, id).forEach(Main::mostrarEvento);
                    break;
                case 3: // Actualizar
                    System.out.print("Ingrese ID del registro a actualizar: ");
                    int idAct = leerEntero();
                    Recordatorio rAct = leerRecordatorioConId(idAct);
                    crudGen.actualizarRecordatorios(rAct);
                    Evento eAct = leerEventoSoloEspecifico(idAct);
                    crud.actualizarEvento(eAct);
                    System.out.println("Actualizado correctamente.");
                    break;
                case 4: // Eliminar
                    System.out.print("Ingrese ID a eliminar: ");
                    int idDel = leerEntero();
                    crud.eliminarEvento(idDel);
                    crudGen.eliminarRecordatorio(idDel);
                    System.out.println("Eliminado correctamente.");
                    break;
                case 5: // Listar
                    crud.listarEventos().forEach(Main::mostrarEvento);
                    break;
                case 6: return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // CRUD Medicamentos
    private static void crudMedicamentos() {
        MedicamentoCRUD crud = new MedicamentoCRUD();
        RecordatoriosCRUD crudGen = new RecordatoriosCRUD();
        while (true) {
            System.out.println("\n--- CRUD de Medicamentos ---");
            menuCrud();
            int op = leerEntero();
            switch (op) {
                case 1: // Agregar
                    Recordatorio r = leerRecordatorio();
                    crudGen.agregarRecordatorios(r);
                    Medicamento m = leerMedicamentoSoloEspecifico(r.getId());
                    crud.agregarMedicamento(m);
                    System.out.println("Agregado correctamente.");
                    break;
                case 2: // Buscar
                    System.out.print("Ingrese nombre del medicamento: ");
                    String medicamento = sc.nextLine();
                    System.out.print("Ingrese ID: ");
                    int id = leerEntero();
                    crud.buscarMedicamentoPorTituloId(medicamento, id).forEach(Main::mostrarMedicamento);
                    break;
                case 3: // Actualizar
                    System.out.print("Ingrese ID del registro a actualizar: ");
                    int idAct = leerEntero();
                    Recordatorio rAct = leerRecordatorioConId(idAct);
                    crudGen.actualizarRecordatorios(rAct);
                    Medicamento mAct = leerMedicamentoSoloEspecifico(idAct);
                    crud.actualizarMedicamento(mAct);
                    System.out.println("Actualizado correctamente.");
                    break;
                case 4: // Eliminar
                    System.out.print("Ingrese ID a eliminar: ");
                    int idDel = leerEntero();
                    crud.eliminarMedicamento(idDel);
                    crudGen.eliminarRecordatorio(idDel);
                    System.out.println("Eliminado correctamente.");
                    break;
                case 5: // Listar
                    crud.listarMedicamentos().forEach(Main::mostrarMedicamento);
                    break;
                case 6: return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // CRUD Personalizados
    private static void crudPersonalizados() {
        PersonalizadoCRUD crud = new PersonalizadoCRUD();
        RecordatoriosCRUD crudGen = new RecordatoriosCRUD();
        while (true) {
            System.out.println("\n--- CRUD de Personalizados ---");
            menuCrud();
            int op = leerEntero();
            switch (op) {
                case 1: // Agregar
                    Recordatorio r = leerRecordatorio();
                    crudGen.agregarRecordatorios(r);
                    Personalizado p = leerPersonalizadoSoloEspecifico(r.getId());
                    crud.agregarPersonalizado(p);
                    System.out.println("Agregado correctamente.");
                    break;
                case 2: // Buscar
                    System.out.print("Ingrese categoría: ");
                    String categoria = sc.nextLine();
                    System.out.print("Ingrese ID: ");
                    int id = leerEntero();
                    crud.buscarPersonalizadoPorTituloId(categoria, id).forEach(Main::mostrarPersonalizado);
                    break;
                case 3: // Actualizar
                    System.out.print("Ingrese ID del registro a actualizar: ");
                    int idAct = leerEntero();
                    Recordatorio rAct = leerRecordatorioConId(idAct);
                    crudGen.actualizarRecordatorios(rAct);
                    Personalizado pAct = leerPersonalizadoSoloEspecifico(idAct);
                    crud.actualizarPersonalizado(pAct);
                    System.out.println("Actualizado correctamente.");
                    break;
                case 4: // Eliminar
                    System.out.print("Ingrese ID a eliminar: ");
                    int idDel = leerEntero();
                    crud.eliminarPersonalizado(idDel);
                    crudGen.eliminarRecordatorio(idDel);
                    System.out.println("Eliminado correctamente.");
                    break;
                case 5: // Listar
                    crud.listarPersonalizados().forEach(Main::mostrarPersonalizado);
                    break;
                case 6: return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // CRUD Reuniones
    private static void crudReuniones() {
        ReunionCRUD crud = new ReunionCRUD();
        RecordatoriosCRUD crudGen = new RecordatoriosCRUD();
        while (true) {
            System.out.println("\n--- CRUD de Reuniones ---");
            menuCrud();
            int op = leerEntero();
            switch (op) {
                case 1: // Agregar
                    Recordatorio r = leerRecordatorio();
                    crudGen.agregarRecordatorios(r);
                    Reunion reu = leerReunionSoloEspecifico(r.getId());
                    crud.agregarReunion(reu);
                    System.out.println("Agregado correctamente.");
                    break;
                case 2: // Buscar
                    System.out.print("Ingrese ubicación: ");
                    String ubicacion = sc.nextLine();
                    System.out.print("Ingrese ID: ");
                    int id = leerEntero();
                    crud.buscarReunionPorTituloId(ubicacion, id).forEach(Main::mostrarReunion);
                    break;
                case 3: // Actualizar
                    System.out.print("Ingrese ID del registro a actualizar: ");
                    int idAct = leerEntero();
                    Recordatorio rAct = leerRecordatorioConId(idAct);
                    crudGen.actualizarRecordatorios(rAct);
                    Reunion reuAct = leerReunionSoloEspecifico(idAct);
                    crud.actualizarReunion(reuAct);
                    System.out.println("Actualizado correctamente.");
                    break;
                case 4: // Eliminar
                    System.out.print("Ingrese ID a eliminar: ");
                    int idDel = leerEntero();
                    crud.eliminarReunion(idDel);
                    crudGen.eliminarRecordatorio(idDel);
                    System.out.println("Eliminado correctamente.");
                    break;
                case 5: // Listar
                    crud.listarReuniones().forEach(Main::mostrarReunion);
                    break;
                case 6: return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // CRUD Tareas
    private static void crudTareas() {
        TareaCRUD crud = new TareaCRUD();
        RecordatoriosCRUD crudGen = new RecordatoriosCRUD();
        while (true) {
            System.out.println("\n--- CRUD de Tareas ---");
            menuCrud();
            int op = leerEntero();
            switch (op) {
                case 1: // Agregar
                    Recordatorio r = leerRecordatorio();
                    crudGen.agregarRecordatorios(r);
                    Tarea t = leerTareaSoloEspecifico(r.getId());
                    crud.agregarTarea(t);
                    System.out.println("Agregado correctamente.");
                    break;
                case 2: // Buscar
                    System.out.print("Ingrese prioridad (número): ");
                    int prioridad = leerEntero();
                    System.out.print("Ingrese ID: ");
                    int id = leerEntero();
                    crud.buscarTareaPorTituloId(prioridad, id).forEach(Main::mostrarTarea);
                    break;
                case 3: // Actualizar
                    System.out.print("Ingrese ID del registro a actualizar: ");
                    int idAct = leerEntero();
                    Recordatorio rAct = leerRecordatorioConId(idAct);
                    crudGen.actualizarRecordatorios(rAct);
                    Tarea tAct = leerTareaSoloEspecifico(idAct);
                    crud.actualizarTarea(tAct);
                    System.out.println("Actualizado correctamente.");
                    break;
                case 4: // Eliminar
                    System.out.print("Ingrese ID a eliminar: ");
                    int idDel = leerEntero();
                    crud.eliminarTarea(idDel);
                    crudGen.eliminarRecordatorio(idDel);
                    System.out.println("Eliminado correctamente.");
                    break;
                case 5: // Listar
                    crud.listarTareas().forEach(Main::mostrarTarea);
                    break;
                case 6: return;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // Menú CRUD común
    private static void menuCrud() {
        System.out.println("1. Agregar");
        System.out.println("2. Buscar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.println("5. Listar");
        System.out.println("6. Retornar");
        System.out.print("Seleccione una opción: ");
    }

    // Métodos para leer y mostrar cada tipo
    private static Recordatorio leerRecordatorio() {
        System.out.print("ID: ");
        int id = leerEntero();
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
        LocalDateTime fechaHora = LocalDateTime.parse(sc.nextLine(), formatter);
        System.out.print("Tipo: ");
        String tipo = sc.nextLine();
        return new Recordatorio(id, titulo, fechaHora, tipo);
    }
    private static Recordatorio leerRecordatorioConId(int id) {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
        LocalDateTime fechaHora = LocalDateTime.parse(sc.nextLine(), formatter);
        System.out.print("Tipo: ");
        String tipo = sc.nextLine();
        return new Recordatorio(id, titulo, fechaHora, tipo);
    }
    private static void mostrarRecordatorio(Recordatorio r) {
        System.out.println(r.getId() + " | " + r.getTitulo() + " | " + r.getFecha_Hora() + " | " + r.getTipo());
    }
    private static ActividadFisica leerActividadFisicaSoloEspecifico(int id) {
        System.out.print("Actividad: ");
        String actividad = sc.nextLine();
        System.out.print("Duración (minutos): ");
        int duracion = leerEntero();
        System.out.print("Nivel de Intensidad: ");
        String nivel = sc.nextLine();
        ActividadFisica af = new ActividadFisica();
        af.setId(id);
        af.setActividad(actividad);
        af.setDuracion(duracion);
        af.setNivelIntensidad(nivel);
        return af;
    }
    private static void mostrarActividadFisica(ActividadFisica af) {
        System.out.println(af.getId() + " | " + af.getActividad() + " | " + af.getDuracion() + " | " + af.getNivelIntensidad());
    }
    private static Evento leerEventoSoloEspecifico(int id) {
        System.out.print("Ubicación: ");
        String ubicacion = sc.nextLine();
        Evento e = new Evento();
        e.setId(id);
        e.setUbicacion(ubicacion);
        return e;
    }
    private static void mostrarEvento(Evento e) {
        System.out.println(e.getId() + " | " + e.getUbicacion());
    }
    private static Medicamento leerMedicamentoSoloEspecifico(int id) {
        System.out.print("Medicamento: ");
        String medicamento = sc.nextLine();
        System.out.print("Dosis: ");
        String dosis = sc.nextLine();
        System.out.print("Frecuencia: ");
        String frecuencia = sc.nextLine();
        System.out.print("Duración (días): ");
        int duracion = leerEntero();
        Medicamento m = new Medicamento();
        m.setId(id);
        m.setMedicamento(medicamento);
        m.setDosis(dosis);
        m.setFrecuencia(frecuencia);
        m.setDuracionDias(duracion);
        return m;
    }
    private static void mostrarMedicamento(Medicamento m) {
        System.out.println(m.getId() + " | " + m.getMedicamento() + " | " + m.getDosis() + " | " + m.getFrecuencia() + " | " + m.getDuracionDias());
    }
    private static Personalizado leerPersonalizadoSoloEspecifico(int id) {
        System.out.print("Categoría: ");
        String categoria = sc.nextLine();
        System.out.print("Notas: ");
        String notas = sc.nextLine();
        Personalizado p = new Personalizado();
        p.setId(id);
        p.setCategoria(categoria);
        p.setNotas(notas);
        return p;
    }
    private static void mostrarPersonalizado(Personalizado p) {
        System.out.println(p.getId() + " | " + p.getCategoria() + " | " + p.getNotas());
    }
    private static Reunion leerReunionSoloEspecifico(int id) {
        System.out.print("Ubicación: ");
        String ubicacion = sc.nextLine();
        System.out.print("Enlace Virtual: ");
        String enlace = sc.nextLine();
        System.out.print("Participantes: ");
        String participantes = sc.nextLine();
        System.out.print("Organizador: ");
        String organizador = sc.nextLine();
        Reunion reunion = new Reunion();
        reunion.setId(id);
        reunion.setUbicacion(ubicacion);
        reunion.setEnlaceVirtual(enlace);
        reunion.setParticipantes(participantes);
        reunion.setOrganizador(organizador);
        return reunion;
    }
    private static void mostrarReunion(Reunion r) {
        System.out.println(r.getId() + " | " + r.getUbicacion() + " | " + r.getEnlaceVirtual() + " | " + r.getParticipantes() + " | " + r.getOrganizador());
    }
    private static Tarea leerTareaSoloEspecifico(int id) {
        System.out.print("Prioridad (número): ");
        int prioridad = leerEntero();
        Tarea t = new Tarea();
        t.setId(id);
        t.setPrioridad(prioridad);
        return t;
    }
    private static void mostrarTarea(Tarea t) {
        System.out.println(t.getId() + " | " + t.getPrioridad());
    }

    // Utilidad para leer enteros robustamente
    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}