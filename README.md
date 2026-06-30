# Llanquihue Tour

## Descripción

Llanquihue Tour es una aplicación desarrollada en Java como parte de la asignatura de Desarrollo Orientado a Objetos. El proyecto representa una agencia de turismo de la región de Los Lagos e implementa una jerarquía de clases para gestionar los distintos servicios turísticos que ofrece.

La aplicación aplica conceptos fundamentales de la programación orientada a objetos como encapsulamiento, herencia, composición, constructores, getters, setters y sobrescritura de métodos.

---

## Objetivo de la Semana 6

El foco de esta semana es **diseñar una jerarquía de clases con herencia simple**, bien organizada y separada por responsabilidad. Concretamente se trabaja:

* Crear una **superclase** `ServicioTuristico` con los atributos comunes.
* Crear **subclases** (`RutaGastronomica`, `PaseoLacustre`, `ExcursionCultural`) con sus atributos específicos.
* Usar `extends` para heredar y `super(...)` en los constructores.
* Sobrescribir `toString()` con `@Override` en cada subclase.
* Organizar el proyecto en los paquetes `model`, `data` y `ui`.
* Crear objetos de prueba en `GestorServicios` y mostrarlos por consola desde `Main`.

---

## Clases creadas

### Superclase
* **`ServicioTuristico`** — clase padre que define los atributos comunes a todos los servicios: `nombre`, `destino`, `precio` y `duracionHoras`. Incluye constructores, getters, setters y `toString()`.

### Subclases
* **`RutaGastronomica`** — hereda de `ServicioTuristico` y agrega el atributo específico `numeroDeParadas`.
* **`PaseoLacustre`** — hereda de `ServicioTuristico` y agrega el atributo específico `tipoEmbarcacion`.
* **`ExcursionCultural`** — hereda de `ServicioTuristico` y agrega el atributo específico `lugarHistorico`.

### Clases de soporte (semanas anteriores)
* **`Persona`** — clase base para personas del sistema.
* **`Cliente`** — hereda de `Persona`, agrega `tipoCliente`.
* **`Empleado`** — hereda de `Persona`, agrega `cargo`.
* **`Direccion`** — representa la dirección de una persona (composición).
* **`Rut`** — encapsula y valida el RUT chileno (composición).
* **`GestorServicios`** — crea instancias de prueba de cada subclase.
* **`Main`** — punto de entrada del programa, muestra los resultados por consola.

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
│   │   │       │   └── GestorServicios.java
│   │   │       ├── exception/
│   │   │       │   └── RutInvalidException.java
│   │   │       ├── util/
│   │   │       │   └── Color.java
│   │   │       └── model/
│   │   │           ├── Persona.java
│   │   │           ├── Cliente.java
│   │   │           ├── Empleado.java
│   │   │           ├── Direccion.java
│   │   │           ├── Rut.java
│   │   │           ├── ServicioTuristico.java
│   │   │           ├── RutaGastronomica.java
│   │   │           ├── PaseoLacustre.java
│   │   │           └── ExcursionCultural.java
│   │   └── resources/
│   │       └── images/
│   └── test/
├── pom.xml
└── README.md
```

---

## Jerarquía de Servicios Turísticos

Jerarquía de herencia simple implementada en la Semana 6:

```text
ServicioTuristico
│
├── RutaGastronomica
├── PaseoLacustre
└── ExcursionCultural
```

| Clase | Atributos heredados | Atributo específico |
|---|---|---|
| `ServicioTuristico` | — | `nombre`, `destino`, `precio`, `duracionHoras` |
| `RutaGastronomica` | `nombre`, `destino`, `precio`, `duracionHoras` | `numeroDeParadas` |
| `PaseoLacustre` | `nombre`, `destino`, `precio`, `duracionHoras` | `tipoEmbarcacion` |
| `ExcursionCultural` | `nombre`, `destino`, `precio`, `duracionHoras` | `lugarHistorico` |

Todas las subclases:
* Usan `extends ServicioTuristico`.
* Llaman a `super(nombre, destino, precio, duracionHoras)` en su constructor.
* Sobrescriben `toString()` con `@Override` mostrando su información completa.

---

## Relaciones entre clases (semanas anteriores)

* `Persona` corresponde a la clase base del sistema de personas.
* `Cliente` hereda de `Persona` y agrega el atributo `tipoCliente`.
* `Empleado` hereda de `Persona` y agrega el atributo `cargo`.
* Una `Persona` tiene una `Direccion` (composición).
* Una `Persona` tiene un `Rut` (composición).

---

## Utilidades

* `Color` proporciona códigos ANSI para colorear la salida en consola.
* `RutInvalidException` es una excepción personalizada para validar RUTs.

---

## Requisitos

* Java 21 o superior.
* Maven.
* IntelliJ IDEA (recomendado).

---

## Instrucciones para ejecutar desde Main

1. Abrir el proyecto en IntelliJ IDEA.
2. Ubicar la clase `Main` dentro del paquete `com.llanquihuetour.ui`.
3. Ejecutar el método `main()`.
4. Revisar la información mostrada en la consola:
    - Mensaje de bienvenida al sistema.
    - Listado de todos los servicios turísticos (rutas gastronómicas, paseos lacustres y excursiones culturales) con sus atributos completos.
    - Total de servicios turísticos creados.

---

## Ejemplo de salida

```text
====================================================
🌎 Bienvenido al Sistema de Llanquihue Tour
====================================================
🧬 SERVICIOS TURÍSTICOS DISPONIBLES
====================================================

    Ruta Gastronómica:
    Nombre: Sabores del Lago
    Destino: Frutillar
    Precio: $28,000 CLP
    Duración: 4 horas
    Número de paradas: 5

    Ruta Gastronómica:
    Nombre: Cervecería y Cocina Alemana
    Destino: Puerto Varas
    Precio: $22,000 CLP
    Duración: 3 horas
    Número de paradas: 3

    Paseo Lacustre:
    Nombre: Navegación Lago Llanquihue
    Destino: Puerto Varas
    Precio: $35,000 CLP
    Duración: 2 horas
    Tipo de embarcación: Catamarán

    Paseo Lacustre:
    Nombre: Atardecer en el Lago
    Destino: Frutillar
    Precio: $30,000 CLP
    Duración: 3 horas
    Tipo de embarcación: Velero

    Excursión Cultural:
    Nombre: Patrimonio de Frutillar
    Destino: Frutillar
    Precio: $40,000 CLP
    Duración: 5 horas
    Lugar histórico: Teatro del Lago

    Excursión Cultural:
    Nombre: Colonización Alemana
    Destino: Puerto Varas
    Precio: $38,000 CLP
    Duración: 4 horas
    Lugar histórico: Museo Colonial Alemán

Total de servicios turísticos: 6
====================================================
```

---

## Autor

Sara Rioseco
