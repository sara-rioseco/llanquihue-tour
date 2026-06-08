# Llanquihue Tour

## Descripción

Llanquihue Tour es una aplicación desarrollada en Java como parte de la asignatura de Desarrollo Orientado a Objetos. El proyecto representa a las personas relacionadas con una agencia de turismo, aplicando conceptos fundamentales de la programación orientada a objetos como encapsulamiento, herencia, composición y validación de datos.

La aplicación permite registrar y mostrar información de clientes y empleados, incluyendo sus datos personales, dirección y RUT.

---

## Funcionalidades implementadas

* Encapsulamiento mediante atributos privados.
* Uso de constructores, getters y setters.
* Herencia entre clases (`Cliente` y `Empleado` heredan de `Persona`).
* Composición entre objetos (`Persona` contiene una `Direccion` y un `Rut`).
* Sobrescritura del método `toString()`.
* Validación de datos al crear objetos.
* Manejo de excepciones personalizadas para RUT inválidos.
* Documentación mediante Javadocs.

---

## Estructura del proyecto

```text
src
│
├── app
│   └── Main.java
│
├── model
│   ├── Persona.java
│   ├── Cliente.java
│   ├── Empleado.java
│   ├── Direccion.java
│   └── Rut.java
│
└── exception
    └── RutInvalidException.java
```

---

## Relaciones entre clases

* `Persona` corresponde a la clase base del sistema.
* `Cliente` hereda de `Persona`.
* `Empleado` hereda de `Persona`.
* Una `Persona` tiene una `Direccion`.
* Una `Persona` tiene un `Rut`.

---

## Requisitos

* Java 21 o superior.
* IntelliJ IDEA (recomendado).

---

## Ejecución

1. Abrir el proyecto en IntelliJ IDEA.
2. Ubicar la clase `Main` dentro del paquete `app`.
3. Ejecutar el método `main()`.
4. Revisar la información mostrada en la consola.

---

## Autor

Sara Rioseco
