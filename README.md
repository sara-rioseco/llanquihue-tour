# Llanquihue Tour — Sistema de Gestión

## Descripción

Llanquihue Tour es un prototipo de software desarrollado en Java para la Evaluación Final Transversal (EFT) de la asignatura **Desarrollo Orientado a Objetos I**. Modela la operación de una agencia de turismo de la Región de Los Lagos: sus clientes, empleados, guías turísticos, colaboradores externos, vehículos y los servicios turísticos que ofrece (rutas gastronómicas, paseos lacustres y excursiones culturales).

El sistema aplica los principios fundamentales de la Programación Orientada a Objetos:

* **Encapsulamiento** — atributos `private` con getters/setters públicos y validación en cada setter.
* **Composición** — una `Persona` contiene un `Rut` y una `Direccion`.
* **Herencia** — jerarquías `Persona → Cliente / Empleado / GuiaTuristico / ColaboradorExterno` y `ServicioTuristico → RutaGastronomica / PaseoLacustre / ExcursionCultural`.
* **Polimorfismo** — colecciones polimórficas (`List<Registrable>`, `List<ServicioTuristico>`) recorridas invocando métodos sobrescritos con `@Override`, y `instanceof` / *switch pattern matching* para validaciones de tipo.
* **Interfaces** — contrato común `Registrable` implementado por todas las entidades del dominio.
* **Sobrecarga** — métodos sobrecargados como `agregar(...)`, `buscarPersona(...)` y `filtrar(...)`.
* **Excepciones personalizadas** — `RutInvalidException` para la validación del RUT chileno (módulo 11).
* **Colecciones** — `ArrayList` para las listas de entidades y `HashMap` para indexar personas por RUT (búsqueda directa y rechazo de RUT duplicados).
* **Archivos .txt como fuente de datos** — los datos iniciales se cargan desde `src/main/resources/data` mediante la clase utilitaria `CargadorDatos`, y las entidades registradas se guardan de vuelta en los mismos archivos mediante `GuardadorDatos` (persistencia entre ejecuciones).

Incluye una interfaz gráfica Swing con botonera de acciones, formularios de ingreso, tabla con búsqueda en vivo y barra de estado, que simula el funcionamiento de un software de gestión real.

---

## Estructura del proyecto

```text
llanquihue-tour/
├── src/main/java/com/llanquihuetour/
│   ├── app/
│   │   └── Main.java                  # Punto de entrada
│   ├── model/
│   │   ├── Registrable.java           # Interfaz común del dominio
│   │   ├── Persona.java               # Clase base (composición: Rut + Direccion)
│   │   ├── Cliente.java
│   │   ├── Empleado.java
│   │   ├── GuiaTuristico.java
│   │   ├── ColaboradorExterno.java
│   │   ├── Vehiculo.java              # Implementa Registrable directamente
│   │   ├── Direccion.java
│   │   ├── Rut.java                   # Validación módulo 11
│   │   ├── ServicioTuristico.java     # Clase abstracta
│   │   ├── RutaGastronomica.java
│   │   ├── PaseoLacustre.java
│   │   └── ExcursionCultural.java
│   ├── data/
│   │   ├── GestorEntidades.java       # ArrayList<Registrable> + HashMap por RUT
│   │   └── GestorServicios.java       # Lista de servicios + búsqueda y filtros
│   ├── exception/
│   │   └── RutInvalidException.java
│   ├── ui/
│   │   ├── VentanaPrincipal.java      # Ventana principal (tabla + menús + búsqueda)
│   │   └── FormularioDialog.java      # Formulario modal reutilizable
│   └── util/
│       ├── CargadorDatos.java         # Lee los .txt y los convierte en objetos
│       ├── GuardadorDatos.java        # Guarda las entidades de vuelta en los .txt
│       ├── Validador.java             # Validaciones reutilizables de atributos
│       └── Color.java                 # Códigos ANSI para la consola
├── src/main/resources/data/
│   ├── personas.txt                   # Clientes, empleados, guías y colaboradores
│   ├── vehiculos.txt
│   └── servicios.txt
├── pom.xml
└── README.md
```

---

## Clases principales

### Interfaz y jerarquías

* **`Registrable`** — interfaz común del dominio; declara `mostrarResumen()`. La implementan `Persona`, `ServicioTuristico` (y todas sus subclases) y `Vehiculo`.
* **`Persona`** *(base)* — datos comunes de una persona; contiene un `Rut` y una `Direccion` (composición). Subclases: **`Cliente`** (`tipoCliente`), **`Empleado`** (`cargo`), **`GuiaTuristico`** (`idioma`, `aniosExperiencia`) y **`ColaboradorExterno`** (`empresa`, `tipoServicio`).
* **`ServicioTuristico`** *(abstracta)* — atributos comunes de un servicio (`nombre`, `destino`, `precio`, `duracionHoras`) y método abstracto `mostrarInformacion()`. Subclases: **`RutaGastronomica`** (`numeroDeParadas`), **`PaseoLacustre`** (`tipoEmbarcacion`) y **`ExcursionCultural`** (`lugarHistorico`).
* **`Vehiculo`** — vehículo de la agencia; implementa `Registrable` directamente (no es una `Persona`).

```text
Registrable (interfaz)
├── Persona ── Cliente · Empleado · GuiaTuristico · ColaboradorExterno
├── ServicioTuristico (abstracta) ── RutaGastronomica · PaseoLacustre · ExcursionCultural
└── Vehiculo
```

### Gestión de datos

* **`CargadorDatos`** *(utilitaria)* — lee los archivos `.txt` de `resources/data` (separador `;`, comentarios con `#`) y convierte cada línea en el objeto correspondiente. Las líneas inválidas se reportan por consola sin detener la carga.
* **`GuardadorDatos`** *(utilitaria)* — persiste las entidades en los mismos archivos `.txt`: después de cada registro exitoso reescribe los archivos con el estado actual de las colecciones, conservando el formato que lee `CargadorDatos`.
* **`Validador`** *(utilitaria)* — centraliza las validaciones de atributos usadas por todos los setters del modelo: textos requeridos, campos de solo letras (nombres, ciudades, idiomas), formato de patente, números positivos y no negativos. Evita duplicar reglas y mantiene mensajes de error consistentes.
* **`GestorEntidades`** — almacena todas las entidades en un `ArrayList<Registrable>` y mantiene un `HashMap<String, Persona>` indexado por RUT: permite búsqueda directa (`buscarPersona`, sobrecargado para `String` y `Rut`) y rechaza RUT duplicados. Ofrece filtros por tipo (`getPersonas()`, `getVehiculos()`) y el recorrido polimórfico `mostrarResumenes()` con *switch pattern matching*.
* **`GestorServicios`** — administra los servicios turísticos: `agregar(...)`, `buscarPorNombre(...)` y el método sobrecargado `filtrar(String destino)` / `filtrar(int precioMaximo)`.

### Interfaz gráfica

* **`VentanaPrincipal`** — ventana principal con botonera de acciones (agregar cliente, empleado, guía, colaborador, vehículo y servicio, más "Acerca de" y "Salir"), tabla única con todas las entidades, búsqueda en vivo y barra de estado con totales.
* **`FormularioDialog`** — diálogo modal reutilizable que construye formularios con campos etiquetados (texto y combos); todas las ventanas de ingreso se arman con esta misma clase.

---

## Archivos de datos

Los datos de prueba viven en `src/main/resources/data`:

| Archivo | Formato de línea |
|---|---|
| `personas.txt` | `TIPO;nombre;apellido;rut;calle;ciudad;region;<específicos>` con `TIPO` ∈ `CLIENTE`, `EMPLEADO`, `GUIA`, `COLABORADOR` |
| `vehiculos.txt` | `patente;marca;modelo;capacidad` |
| `servicios.txt` | `TIPO;nombre;destino;precio;duracionHoras;<específico>` con `TIPO` ∈ `RUTA`, `PASEO`, `EXCURSION` |

Las líneas en blanco o que comienzan con `#` se ignoran. Todos los RUT de prueba son válidos según el algoritmo módulo 11.

Las entidades registradas desde la interfaz se guardan automáticamente en estos mismos archivos después de cada registro exitoso, por lo que se conservan al volver a ejecutar la aplicación.

---

## Requisitos

* Java 21 o superior.
* Maven.
* IntelliJ IDEA (recomendado).

---

## Instrucciones para clonar y ejecutar

```bash
git clone https://github.com/sara-rioseco/llanquihue-tour.git
cd llanquihue-tour
mvn compile
mvn exec:java -Dexec.mainClass="com.llanquihuetour.app.Main"
```

Desde IntelliJ IDEA: abrir el proyecto y ejecutar el método `main()` de la clase `Main` (paquete `com.llanquihuetour.app`).

Al ejecutar el programa:

1. Se cargan las personas, vehículos y servicios desde los archivos `.txt`.
2. La consola muestra el listado polimórfico de servicios (`mostrarInformacion()`) y el resumen de cada entidad registrada (`mostrarResumen()` + detalle según tipo con `instanceof`).
3. Se abre la ventana **Llanquihue Tour — Sistema de Gestión**, donde se puede:
   * Buscar/filtrar en vivo sobre la tabla de entidades.
   * Registrar nuevos clientes, empleados, guías, colaboradores, vehículos y servicios con los botones **Agregar…** de la parte superior (con validación de RUT, campos vacíos, números y RUT duplicados); si un dato es inválido, el formulario se vuelve a mostrar conservando los valores ingresados, y cada registro exitoso muestra un mensaje de confirmación y se guarda automáticamente en los archivos `.txt`.

---

## Autor

Sara Rioseco
