# THYMELEAF

## Conceptos teóricos

### Definición

Thymeleaf es un motor de plantillas para generar documentos _html_ utilizando datos de la aplicación.


### Natural templating

Thymeleaf hace su función añadiendo nuevos atributos a etiquetas ya existentes en HTML, por ejemplo

```html
<body>
    <p th:text="${variable}">
        Este es un texto por defecto
    </p>
</body>
```

Esto simplifica mucho la maquetación, permitiendo a aislar esta fase de la codificación del resto.

### Tipos de docuemntos
Pordemos trabajar con documentos
* HTML: Pero no realiza validación con DTD o esquema.
* XML: Thymeleaf espera que el documento está bien formado.
* Javascript
* CSS
* Texto
* Raw

### Dialecto

* Un **procesador** es un objeto que aplica una transformación a un determinado artefacto (texto, etiqueta, comentario, ...)
* Un **dialecto** es una forma concreta de transformar un tipo de documento, es un conjunto por defecto de procesadores. 
* Thymeleaf nos da un dialecto por defecto para trabajar con Spring MVC, permitiendonos usar también Spring SpEL.

## Primeros pasos con Thymeleaf

* El _namespace_ de los documentos de Thymeleaf que debemos añadir a nuestros documentos es ``xmlns="http://www.thymeleaf.org"`` 

* Debemos crear una clase anotada con``@Controller``, que definirá una clase que esta escuchando URL.

* A continuación se muestra un ejemplo de controller básico

```java
/**
 * Controlador de la página principal de la aplicación
 *
 * @author jamataran@gmail.com
 */
@Controller
public class DashboardController {

    @GetMapping("/") // Rutas que aceptará este método
    public String index(
            @RequestParam(name = "parametro", required = false) String param, // Parámetros.
            Model model) { // Spring inyectará aqui un modelo para trabajar con él.

        model.addAttribute("key", param);

        // Ruta por defecto. Spring añadirá prefijo (src/main/resources/templates por defecto) y Sufijo (.html) por defecto.
        return "index";

    }

}
```

## Configuración Thymeleaf

Podemos modificar la configuración de Thymeleaf a través del fichero de properties o con Beans Java.

* Propiedades (```application.properties``` o similar)
    * ```spring.thymeleaf.cache```: Cacheado de las plantillas (false permite _hotswapping_)
    * ```spring.thymeleaf.check-template```: Comprueba que exista la plantilla.
    * ```spring.thymeleaf.encoding```: Codificación, por defecto ```UTF-8```
    * ```spring.thymeleaf.prefix```: Prefijo por defecto.
    * ```spring.thymeleaf.suffix```: Sufijo por defecto.
    
## Sintaxis básica

### ```th:text="${var}"```

Con ```th:text="${var}"``` inyectamos textos en etiquetas HTML que queramos. Además es capaz de hacer el casting que necesitemos.
 
### ```th:if="${var}"```, ```th:unless="${var}"``` y operador ternario.

Nos permite realizar condicionales sobre datos del modelo o selección.
Por ejemplo vamos a mostrar una lista, si existe, si no un literal.

```html
<div th:if="${#lists.isEmpty(DTO.lista)}">
    La lista esta vacía. (<code>th:if="${#lists.isEmpty(DTO.lista)}"</code>)
</div>
<div th:unless="${#lists.isEmpty(DTO.lista)}">
    La lista tiene elementos.(<code>th:unless="${#lists.isEmpty(DTO.lista)}"</code>)
</div>
<div>
    También existe el operador ternario, resultado de <code>"${#lists.isEmpty(DTO.lista)} ? 'TERNARIO_TRUE' : 'TERNARIO_FALSE'"></code>: <span th:text="${#lists.isEmpty(DTO.lista)} ? 'TERNARIO_TRUE' : 'TERNARIO_FALSE'"></span>
</div>
```

### ```th:block```

El elemento ```<th:block>```, genera un bloque a partir de un dato, que luego desaparecerá:

```html
<table>
    <th:block th:each="dato: ${DTO.lista}">
        <tr>
            <td th:text="${dato}"></td>
        </tr>
    </th:block>
</table>
```

### ```th:each ```

Este elemento nos permite iterar sobre una colección, sobre cualquier implementación de:

```java
java.util.List
java.util.Arrays
java.util.Iterable
java.util.Collection
java.util.Enumeration
java.util.Iterator
java.util.Map
```

Con ```th:each```, tenemos disponibles varios datos sobre la iteración:

````html
<th:block th:each="usuario, varStat: ${DTO.listaCompleja}">
                <tr>
                    <td th:text="${varStat.count}"></td> <!-- Donde estamos -->
                    <td th:text="${usuario.username}"></td>
                    <td th:text="${varStat.first}"></td> <!-- Es el primero -->
                    <td th:text="${varStat.last}"></td> <!-- Es el ultimo -->
                    <td th:text="${varStat.even}"></td> <!-- Es par -->
                    <td th:text="${varStat.size}"></td> <!-- Tamaño -->
                    <td th:text="${varStat.odd}"></td> <!-- Es impar -->
                </tr>
</th:block>
````



### Expresiones básicas

Thymeleaf + Spring nos ofrecen una serie de expresiones que se pueden combinar con las expresiones de Spring, SpEL.

* Expresiones variables: ``` ${var} ```
    
   Buscará en el *contexto* de la plantilla el valor de ```var``` (puede ser un valor o una expresión)
   
   ```var``` puede ser un map ``"${map['index']}``
   
   ```var``` puede ser un array ``"${array[1]}``
   
   * Dentro de una expresión variable podemos utilizar *expresiones de utilidad* (ver API)(1), por ejemplo:
   
       ```html
        <!-- Un ejemplo de expresión -->
        <span th:text="${#temporals.format(DTO.dateTime, 'dd-MM-yyyy (HH:mm:ss)')}"></span>
        ```
   
   * Tenemos opcion de utilizar operadores aritméticos, textuales y operadores de SpEL. Podemos ver los siguientes ejemplos de operaciones.    
   
* Expresiones variables de selección: ```*{var} ```

    Similares al uso de _variables_, pero se ejecutan en el marco de otro *objeto*
    
    ```html
    <div class="page-header">
        <h1><code>th:object</code></h1>
        <div th:object="${DTO.usuarioDTO}">
           Con <code>th:object</code> selecciono el usuario. Ahora puedo usar <code>*{var}</code> para seleccionar datos, como <span th:text="*{username}"></span>
        </div>
    </div>
    ```
   
* Expresiones de mensaje: ```#{var} ```   
* Expresiones de enlaces: ```@{var} ```   
* Expresiones de fragmentos: ```~{var} ```  

### Integración con Spring Security

## Bibliografia y recursos.

### Expresiones de utilidad.
* [https://www.baeldung.com/spring-thymeleaf-3-expressions](https://www.baeldung.com/spring-thymeleaf-3-expressions)
* [https://www.thymeleaf.org/apidocs/thymeleaf/3.0.0.RELEASE/org/thymeleaf/expression/package-summary.html](https://www.thymeleaf.org/apidocs/thymeleaf/3.0.0.RELEASE/org/thymeleaf/expression/package-summary.html)
* [https://github.com/OpenWebinarsNet/Curso-introduccion-thymeleaf](https://github.com/OpenWebinarsNet/Curso-introduccion-thymeleaf)
