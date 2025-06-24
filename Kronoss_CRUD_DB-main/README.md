Kronoss_DB
Descripción General

Kronoss_DB es una aplicación de consola en Java que permite la gestión de recordatorios personales, actividades físicas, eventos, medicamentos, tareas, reuniones y recordatorios personalizados. Utiliza una arquitectura basada en el patrón DAO para interactuar con una base de datos MySQL, facilitando operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para cada tipo de recordatorio.
Estructura del Proyecto

Kronoss_DB/
│
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── package_example/
│   │   │   │   └── Main.java
│   │   │   ├── DAO/
│   │   │   │   ├── ActividadFisicaCRUD.java
│   │   │   │   ├── EventoCRUD.java
│   │   │   │   ├── MedicamentoCRUD.java
│   │   │   │   ├── PersonalizadoCRUD.java
│   │   │   │   ├── RecordatoriosCRUD.java
│   │   │   │   ├── ReunionCRUD.java
│   │   │   │   └── TareaCRUD.java
│   │   │   └── model/
│   │   │       ├── ActividadFisica.java
│   │   │       ├── Evento.java
│   │   │       ├── Medicamento.java
│   │   │       ├── Personalizado.java
│   │   │       ├── Recordatorio.java
│   │   │       ├── Reunion.java
│   │   │       └── Tarea.java
│   └── test/
│       └── java/
└── .gitignore

Instalación y Configuración

    Requisitos previos:
        Java 17 o superior.
        Maven.
        MySQL Server.

    Configuración de la base de datos:
        Crea una base de datos llamada kronoss_ en tu servidor MySQL.
        Asegúrate de que el usuario y contraseña en los archivos DAO coincidan con los de tu servidor MySQL.
        Crea las tablas necesarias (recordatorios, recordatoriosactividadFisica, recordatorioseventos, recordatoriosmedicamentos, recordatoriospersonalizados, recordatoriosreuniones, recordatoriostareas) según los atributos de los modelos.

    Compilación y ejecución:
        Ejecuta mvn clean install para compilar el proyecto.
        Ejecuta la clase Main desde tu IDE o con Maven.

Uso

Al ejecutar la aplicación, se mostrará un menú principal donde puedes seleccionar el tipo de recordatorio a gestionar. Cada módulo permite agregar, buscar, actualizar, eliminar y listar registros.
Estructura de Clases
package_example.Main

    Función: Punto de entrada de la aplicación. Gestiona el menú principal y las operaciones CRUD para cada tipo de recordatorio.
    Métodos principales:
        crudRecordatorios()
        crudActividadFisica()
        crudEventos()
        crudMedicamentos()
        crudPersonalizados()
        crudReuniones()
        crudTareas()

DAO (Data Access Object)

Cada clase DAO implementa las operaciones CRUD para su entidad correspondiente, conectándose a la base de datos MySQL.

    RecordatoriosCRUD: Gestiona recordatorios generales.
    ActividadFisicaCRUD: Gestiona actividades físicas.
    EventoCRUD: Gestiona eventos.
    MedicamentoCRUD: Gestiona medicamentos.
    PersonalizadoCRUD: Gestiona recordatorios personalizados.
    ReunionCRUD: Gestiona reuniones.
    TareaCRUD: Gestiona tareas.

model

Contiene las clases de modelo que representan las entidades del sistema. Todas heredan de Recordatorio (excepto Recordatorio en sí).

    Recordatorio: Clase base con atributos comunes (id, titulo, fecha_Hora, tipo).
    ActividadFisica, Evento, Medicamento, Personalizado, Reunion, Tarea: Extienden Recordatorio y añaden atributos específicos.

Dependencias

El proyecto utiliza el conector JDBC de MySQL:

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>

Notas de Desarrollo

    El acceso a la base de datos está centralizado en las clases DAO.
    El sistema utiliza Scanner para la entrada de datos por consola.
    El formato de fecha y hora es yyyy-MM-dd HH:mm.
    El código está preparado para ser extendido con nuevas entidades o funcionalidades.


