# Llanquihue Tour

## Descripción

Llanquihue Tour es una aplicación desarrollada en Java como parte de la asignatura de Desarrollo Orientado a Objetos. El proyecto representa una agencia de turismo de la región de Los Lagos e implementa una jerarquía de clases para gestionar los distintos servicios turísticos que ofrece, además de las entidades internas de la operación (guías turísticos, vehículos y colaboradores externos).

La aplicación aplica conceptos fundamentales de la programación orientada a objetos como encapsulamiento, herencia, composición, constructores, getters, setters, sobrescritura de métodos, interfaces, polimorfismo y validación de tipos con `instanceof`. Incluye además una interfaz gráfica básica construida con Swing (`JFrame` y `JOptionPane`).

---

## Objetivo de la Semana 8

Esta semana se amplía el sistema integrando **interfaces, polimorfismo y estructuras dinámicas**, junto con una interfaz gráfica básica:

* Se define la interfaz `Registrable` en el paquete `model`, con el método `mostrarResumen()`.
* **Todas** las clases del paquete `model` que representan una entidad de la agencia implementan `Registrable`, ya sea heredándolo o implementándolo directamente:
  * `Persona` implementa `Registrable`; sus subclases `Cliente`, `Empleado`, `GuiaTuristico` y `ColaboradorExterno` heredan y/o sobrescriben el contrato.
  * `ServicioTuristico` implementa `Registrable`; sus subclases `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural` heredan y sobrescriben el contrato.
  * `Vehiculo` implementa `Registrable` directamente (no es una `Persona` ni un `ServicioTuristico`).
* Se agregan las clases nuevas `GuiaTuristico`, `ColaboradorExterno` y `Vehiculo`.
* Se crea `GestorEntidades` en el paquete `data`, con una colección genérica `ArrayList<Registrable>` recorrida con `for-each`, usando `instanceof` (mediante `switch` con *pattern matching*) para diferenciar el tipo específico de cada objeto —incluyendo tanto entidades de tipo `Persona` como servicios turísticos— y aplicar lógica adicional.
* Se implementa una interfaz gráfica simple (`GestorEntidadesFrame`, con `JFrame` y `JOptionPane`) que permite ingresar nuevos guías turísticos, vehículos y colaboradores externos, y visualizar el resumen de todos los registrados.

---

## Objetivo de la Semana 7 (base sobre la que se construyó esta etapa)

* Método `mostrarInformacion()` en la superclase `ServicioTuristico` con una implementación base.
* Sobrescritura de `mostrarInformacion()` con `@Override` en cada subclase (`RutaGastronomica`, `PaseoLacustre`, `ExcursionCultural`).
* Colección polimórfica `List<ServicioTuristico>` en `GestorServicios`.
* Recorrido con `for-each`, invocando `mostrarInformacion()` desde referencias del tipo `ServicioTuristico`.

---

## Mejoras aplicadas según retroalimentación

A partir de la retroalimentación de semanas anteriores se aplicaron los siguientes ajustes:

* **`ServicioTuristico` convertida en clase abstracta** con `mostrarInformacion()` declarado como método abstracto, reforzando que cada subclase concreta defina su propia versión.
* **Una sola estrategia de visualización en `GestorServicios`**: la lista se construye una vez y `Main` delega en `mostrarServicios()`, evitando duplicar el recorrido.
* **Integración de `Cliente` y `Empleado` en la ejecución**: ahora se cargan como entidades `Registrable` en `GestorEntidades`, evidenciándolos por consola y ejercitando la rama genérica `Persona` del `instanceof`.
* **Mensaje de validación de `Rut` en español** y con el mismo estilo del resto (`Formato esperado: 12345678-9`).
* **Versión de Java (21) indicada explícitamente** en la sección *Requisitos*.
* **Uso de `List<...>` como tipo de referencia** (en vez de `ArrayList<...>`) en los gestores, para mayor flexibilidad.

---

## Clases e interfaces del sistema

### Interfaz
* **`Registrable`** — contrato común que declara `mostrarResumen()`. Es implementada por `Persona` y `ServicioTuristico` (y heredada/sobrescrita por todas sus subclases) y directamente por `Vehiculo`. Es decir, **todas** las clases del paquete `model` que representan una entidad gestionable la implementan.

### Jerarquía de Servicios Turísticos
* **`ServicioTuristico`** — clase **abstracta** que define los atributos comunes a todos los servicios: `nombre`, `destino`, `precio` y `duracionHoras`. Incluye constructores, getters, setters, `toString()` e implementa `Registrable` con una versión base de `mostrarResumen()`. Declara `mostrarInformacion()` como **método abstracto**, obligando a cada subclase concreta a definir su propia versión.
* **`RutaGastronomica`** — hereda de `ServicioTuristico`, agrega `numeroDeParadas` y sobrescribe `mostrarResumen()`.
* **`PaseoLacustre`** — hereda de `ServicioTuristico`, agrega `tipoEmbarcacion` y sobrescribe `mostrarResumen()`.
* **`ExcursionCultural`** — hereda de `ServicioTuristico`, agrega `lugarHistorico` y sobrescribe `mostrarResumen()`.

### Jerarquía de Entidades Registrables
* **`Persona`** — clase base de las personas del sistema. Implementa `Registrable` con una implementación base de `mostrarResumen()`.
* **`Cliente`** — hereda de `Persona`, agrega `tipoCliente` y sobrescribe `mostrarResumen()`.
* **`Empleado`** — hereda de `Persona`, agrega `cargo` y sobrescribe `mostrarResumen()`.
* **`GuiaTuristico`** *(nueva)* — hereda de `Persona`, agrega `idioma` y `aniosExperiencia`, y sobrescribe `mostrarResumen()`.
* **`ColaboradorExterno`** *(nueva)* — hereda de `Persona`, agrega `empresa` y `tipoServicio`, y sobrescribe `mostrarResumen()`.
* **`Vehiculo`** *(nueva)* — implementa `Registrable` directamente (no es una `Persona`), con `patente`, `marca`, `modelo` y `capacidad`.

### Clases de soporte
* **`Direccion`** — representa la dirección de una persona (composición).
* **`Rut`** — encapsula y valida el RUT chileno (composición).
* **`GestorServicios`** — crea la lista de servicios turísticos (una sola vez, al instanciarse) y la expone mediante `getServicios()`. Ofrece un único punto de visualización, `mostrarServicios()`, que recorre la colección polimórficamente; `Main` delega en él en lugar de duplicar el recorrido.
* **`GestorEntidades`** *(nueva)* — mantiene la colección `ArrayList<Registrable>`, carga entidades de ejemplo (guía, vehículo, colaborador externo **y** un servicio turístico) y las recorre con `for-each` aplicando `instanceof` para diferenciar el tipo.
* **`Main`** — punto de entrada del programa: muestra los servicios y entidades por consola y luego abre la interfaz gráfica.
* **`GestorEntidadesFrame`** *(nueva)* — interfaz gráfica (`JFrame`) para ingresar nuevas entidades mediante `JOptionPane` y visualizar el resumen de todas las registradas.

---

## Estructura del proyecto

```text
llanquihue-tour/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.llanquihuetour/
│   │   │       ├── ui/
│   │   │       │   ├── Main.java
│   │   │       │   └── GestorEntidadesFrame.java
│   │   │       ├── data/
│   │   │       │   ├── GestorServicios.java
│   │   │       │   └── GestorEntidades.java
│   │   │       ├── exception/
│   │   │       │   └── RutInvalidException.java
│   │   │       ├── util/
│   │   │       │   └── Color.java
│   │   │       └── model/
│   │   │           ├── Registrable.java
│   │   │           ├── Persona.java
│   │   │           ├── Cliente.java
│   │   │           ├── Empleado.java
│   │   │           ├── GuiaTuristico.java
│   │   │           ├── ColaboradorExterno.java
│   │   │           ├── Vehiculo.java
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

```text
ServicioTuristico (abstracta)
│
├── RutaGastronomica
├── PaseoLacustre
└── ExcursionCultural
```

| Clase | Atributos heredados | Atributo específico |
|---|---|---|
| `ServicioTuristico` *(abstracta)* | — | `nombre`, `destino`, `precio`, `duracionHoras` |
| `RutaGastronomica` | `nombre`, `destino`, `precio`, `duracionHoras` | `numeroDeParadas` |
| `PaseoLacustre` | `nombre`, `destino`, `precio`, `duracionHoras` | `tipoEmbarcacion` |
| `ExcursionCultural` | `nombre`, `destino`, `precio`, `duracionHoras` | `lugarHistorico` |

Todas las subclases:
* Usan `extends ServicioTuristico`.
* Llaman a `super(nombre, destino, precio, duracionHoras)` en su constructor.
* Están **obligadas** a implementar `mostrarInformacion()` (abstracto en la superclase) y sobrescriben además `toString()` y `mostrarResumen()` con `@Override`, invocados polimórficamente desde referencias `ServicioTuristico` o `Registrable`.

---

## Jerarquía de Entidades Registrables

`Registrable` es el contrato común a **todo** el paquete `model`. Tanto la jerarquía de personas como la de servicios turísticos, y `Vehiculo` de forma independiente, lo implementan:

```text
Registrable (interfaz)
│
├── Persona (implements Registrable)
│   ├── Cliente
│   ├── Empleado
│   ├── GuiaTuristico
│   └── ColaboradorExterno
│
├── ServicioTuristico (abstracta, implements Registrable)
│   ├── RutaGastronomica
│   ├── PaseoLacustre
│   └── ExcursionCultural
│
└── Vehiculo (implements Registrable)
```

| Clase | Hereda de | Implementa | Atributo específico |
|---|---|---|---|
| `Persona` | — | `Registrable` | `nombre`, `apellido`, `rut`, `direccion` |
| `Cliente` | `Persona` | — (hereda) | `tipoCliente` |
| `Empleado` | `Persona` | — (hereda) | `cargo` |
| `GuiaTuristico` | `Persona` | — (hereda) | `idioma`, `aniosExperiencia` |
| `ColaboradorExterno` | `Persona` | — (hereda) | `empresa`, `tipoServicio` |
| `ServicioTuristico` *(abstracta)* | — | `Registrable` | `nombre`, `destino`, `precio`, `duracionHoras` |
| `RutaGastronomica` | `ServicioTuristico` | — (hereda) | `numeroDeParadas` |
| `PaseoLacustre` | `ServicioTuristico` | — (hereda) | `tipoEmbarcacion` |
| `ExcursionCultural` | `ServicioTuristico` | — (hereda) | `lugarHistorico` |
| `Vehiculo` | — | `Registrable` (directo) | `patente`, `marca`, `modelo`, `capacidad` |

`GestorEntidades` almacena entidades de **ambas** jerarquías (un `GuiaTuristico`, un `Vehiculo`, un `ColaboradorExterno`, un `Cliente`, un `Empleado` y una `ExcursionCultural`) en un único `ArrayList<Registrable>`. Al recorrerlo con `for-each` se usa `instanceof` (mediante un `switch` con *pattern matching*, por ejemplo `case Vehiculo vehiculo -> ...`) para identificar el tipo concreto de cada objeto y mostrar un detalle adicional específico de esa clase, además del `mostrarResumen()` común. Los tipos que no tienen un caso específico (`Cliente` y `Empleado`) caen en el caso genérico de su superclase `Persona`, demostrando el polimorfismo a través de toda la jerarquía.

---

## Relaciones entre clases

* `Persona` es la clase base del sistema de personas e implementa `Registrable`.
* `Cliente`, `Empleado`, `GuiaTuristico` y `ColaboradorExterno` heredan de `Persona`.
* Una `Persona` tiene una `Direccion` (composición).
* Una `Persona` tiene un `Rut` (composición).
* `ServicioTuristico` es la clase base de los servicios turísticos e implementa `Registrable`.
* `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural` heredan de `ServicioTuristico`.
* `Vehiculo` no es una `Persona` ni un `ServicioTuristico`: implementa `Registrable` de forma independiente.

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

## Instrucciones para compilar y ejecutar

### Desde IntelliJ IDEA

1. Abrir el proyecto en IntelliJ IDEA.
2. Ubicar la clase `Main` dentro del paquete `com.llanquihuetour.ui`.
3. Ejecutar el método `main()`.
4. Revisar la consola:
    - Mensaje de bienvenida al sistema.
    - Listado polimórfico de todos los servicios turísticos, cada uno mostrando su información específica mediante `mostrarInformacion()`.
    - Listado de las entidades registradas (guía, vehículo, colaborador, cliente, empleado y servicio de ejemplo), cada una mostrando su resumen mediante `mostrarResumen()` y un detalle adicional según su tipo (`instanceof`).
5. Se abrirá automáticamente la ventana **Gestión de Entidades**, desde donde se puede:
    - Agregar un nuevo guía turístico, vehículo o colaborador externo (ingresando los datos en los cuadros de diálogo).
    - Presionar "Mostrar Resúmenes" para ver el resumen de todas las entidades cargadas inicialmente y las registradas hasta el momento.

### Desde la terminal con Maven

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.llanquihuetour.ui.Main"
```

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

    ...

Total de servicios turísticos: 6
====================================================

🪪 ENTIDADES REGISTRADAS EN LA AGENCIA
====================================================
Guía Turístico: Matías Soto | Idioma: Alemán | Experiencia: 6 años
   -> Guía turístico especializado en idioma Alemán.
Vehículo: Mercedes-Benz Sprinter | Patente: PVCT-24 | Capacidad: 18 pasajeros
   -> Vehículo con capacidad para 18 pasajeros.
Colaborador Externo: Carla Muñoz | Empresa: Transportes Lago Azul | Servicio: Traslados turísticos
   -> Colaborador externo de la empresa Transportes Lago Azul.
Cliente: Josefa Vargas | Tipo: Frecuente | RUT: 12345678-5
   -> Persona registrada: Josefa.
Empleado: Diego Fuentes | Cargo: Coordinador de Turismo | RUT: 15555555-6
   -> Persona registrada: Diego.
Excursión Cultural: Patrimonio de Frutillar | Destino: Frutillar | Lugar histórico: Teatro del Lago
   -> Servicio turístico con destino Frutillar.
====================================================
```

Luego de esta salida por consola se abre la ventana **Gestión de Entidades**, donde se pueden ingresar nuevas entidades y visualizar sus resúmenes.

---

## Autor

Sara Rioseco
