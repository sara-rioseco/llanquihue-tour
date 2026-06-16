# Llanquihue Tour

## Descripción

Llanquihue Tour es una aplicación desarrollada en Java como parte de la asignatura de Desarrollo Orientado a Objetos. El proyecto representa una agencia de turismo que permite gestionar clientes, empleados y un catálogo de tours turísticos de la región de Los Lagos.

La aplicación aplica conceptos fundamentales de la programación orientada a objetos como encapsulamiento, herencia, composición, validación de datos, manejo de archivos y colecciones.

---

## Funcionalidades implementadas

### Gestión de Personas
* Encapsulamiento mediante atributos privados.
* Uso de constructores, getters y setters.
* Herencia entre clases (`Cliente` y `Empleado` heredan de `Persona`).
* Composición entre objetos (`Persona` contiene una `Direccion` y un `Rut`).
* Sobrescritura del método `toString()`.
* Validación de datos al crear objetos.
* Manejo de excepciones personalizadas para RUT inválidos.

### Gestión de Tours
* Lectura de datos desde archivos de texto (`.txt`).
* Procesamiento y separación de datos mediante delimitadores.
* Almacenamiento de objetos en colecciones (`ArrayList`).
* Recorrido completo de colecciones.
* Filtrado de datos según criterios específicos.
* Visualización de resultados con formato y colores en consola.

### Documentación
* Documentación completa mediante Javadocs.
* Código siguiendo buenas prácticas y patrones de diseño.

---

## Estructura del proyecto

```text
llanquihue-tour/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.llanquihuetour/
│   │   │       ├── ui/
│   │   │       │   └── Main.java
│   │   │       ├── data/
│   │   │       │   └── GestorDatos.java
│   │   │       ├── exception/
│   │   │       │   └── RutInvalidException.java
│   │   │       └── model/
│   │   │           ├── Persona.java
│   │   │           ├── Cliente.java
│   │   │           ├── Empleado.java
│   │   │           ├── Direccion.java
│   │   │           ├── Rut.java
│   │   │           ├── Tour.java
│   │   │           └── Color.java
│   │   └── resources/
│   │       ├── tours.txt
│   │       └── images/
│   └── test/
├── pom.xml
└── README.md
```

---

## Relaciones entre clases

* `Persona` corresponde a la clase base del sistema.
* `Cliente` hereda de `Persona` y agrega el atributo tipoCliente.
* `Empleado` hereda de `Persona` t agrega el atributo cargo.
* Una `Persona` tiene una `Direccion` (composición).
* Una `Persona` tiene un `Rut` (composición).

---

## Modelo de Tours

* `Tour` representa un programa turístico con nombre, destino y precio.
* `GestorDatos` se encarga de leer el archivo `tours.txt`y crear objetos `Tour`.

---

## Utilidades

* `Color` proporciona códigos ANSI para colorear la salida en consola.
* `RutInvalidException` es una excepción personalizada para validar RUTs.

---

## Archivo de datos
### tours.txt

El archivo `tours.txt` ubicado en `src/main/resources/` contiene información de los tours disponibles en formato CSV (separado por punto y coma):
```text
Tour Volcán Osorno;Puerto Varas;45000
Navegación Isla de Chiloé;Ancud;65000
City Tour Puerto Montt;Puerto Montt;25000
Saltos del Petrohué;Puerto Varas;38000
Frutillar y Teatro del Lago;Frutillar;32000
Ruta de los Volcanes;Puerto Varas;85000
Parque Nacional Alerce Andino;Puerto Montt;55000
``` 
**Formato:** Nombre del Tour;Destino;Precio

---

## Requisitos

* Java 21 o superior.
* Maven.
* IntelliJ IDEA (recomendado).

---

## Ejecución

1. Abrir el proyecto en IntelliJ IDEA.
2. Ubicar la clase `Main` dentro del paquete `com.llanquihuetour.ui`.
3. Ejecutar el método `main()`.
4. Revisar la información mostrada en la consola que mostrará:}
- Catálogo completo de tours
- Tours filtrados por precio (tours premium > $40.000)

## Ejemplo de salida
```text
====================================================
🌎 Bienvenido al Sistema de Llanquihue Tour
====================================================
🗺️ CATÁLOGO DE TOURS DISPONIBLES
====================================================

📋 LISTA COMPLETA DE TOURS:
----------------------------------------------------
Tour:
Nombre: Tour Volcán Osorno
Destino: Puerto Varas
Precio: $45,000 CLP
...

🔍 TOURS PREMIUM (precio mayor a $40,000 CLP):
----------------------------------------------------
...
```
---

## Autor

Sara Rioseco
