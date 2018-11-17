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

### ```th:src``` y ``th:href``
Rellena el src o href  de un elemento, por ejemplo:

```html
<img th:src="${not (#strings.isEmpty(producto.imagen))} ? ${producto.imagen} : 'http://placehold.it/48x48'" class="img-responsive icono-categoria" alt="imagen" /></td>
```
### ```th:data-*```

Se utilizan para rellenar los valores de los atributos de HTML5 ``data-*``, válidos para interaccionar con Javascript.

### ``th:class``, ``th:classappend`` y ``th:styleappend``

Nos permiten manejar clases CSS utilizando expresiones de Thymeleaf 

### ``th:field`` y ``th:object``

Nos permiten asociar el _Command Object_ a un formulario y los campos al formulario. Ver [Formularios]()


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
    
    Sirven para generar enlaces con Thymeleaf. Se utilizan en las etiquetas que tienen sentido ``<a>``, ``<img>``, ``<script>``y ``<link>``. Para utilizarlas usamos ``th:href``y ``th:src``. Atendiendo al tipo de enlace que queresmo distinguimos:
    
    * Absolutas
    * Relativas
        * A la página
            * Con variables en parámetro
            ```html
            <th:block th:each="categoria: ${categorias}">
                <li>
                    <a th:href="@{/(idCategoria=${categoria.id})}"
                       th:text="${#strings.concat('Filtrar por', categoria.nombre)}"
                       data-toggle="dropdown"
                       role="button"
                       aria-haspopup="true"
                       aria-expanded="false">
                    </a>
                </li>
            </th:block>  
            ```        
            * Con variables en el _path_
            ```html
            <td>
                <a th:text="${producto.nombre}"
                   th:href="@{/{variable}(variable=${producto.id})}">
                    No se pudo recuperar el nombre del producto.
                </a>
            </td>
            ```
        * Al contexto
        * Al servidor
        * Al protocolo
   
* Expresiones de fragmentos: ```~{var} ``` 

### Formularios

* Spring ofrece funcionalidades para el manejo de formularios, a traves de un objeto _Command Object_
* El flujo de un formulario con Spring MVC sigue los siguientes pasos:
    1. Enviar ``@GetMapping``, añadir al modelo un objeto ``Model`` vacío.
       ```java
           /**
                * Método que añadirá o editará un producto
                *
                * @param model Command Object para el formulario.
                * @return Dirección de la plantilla
                */
               @GetMapping("/editar/{id}")
               public String upsertProducto(Model model, @PathVariable(name = "id", required = false) Long id) {
                   Producto p= productoService.findById(id);
                   if (p!=null)
                       model.addAttribute("producto", productoService.findById(id));
                   else
                       model.addAttribute("producto", Producto.builder().build());
           
                   return "productos/upsert";
           
               }
        ```
    1. Crear una plantilla, valiendonos de las etiquetas ``th:field`` y ``th:object`` para bindear los datos. con el _Command Object_
        ```html
         <form method="post" action="#"
                          th:action="@{/productos/save}" th:object="${producto}">
                        <h1>
                            Nuevo producto
                        </h1>
                        <div class="form-group">
                            <label for="nombre">Nombre</label> <input type="text"
                                                                      class="form-control" id="nombre" 
                                                                       placeholder="Nombre"
                                                                      th:field="*{nombre}" />  
                          <...> 
        ```
    1. Dirigimos el ``action`` del formulario a la misma URL con ``@PostMapping``, con el _Command Object_. Se recoge mediante la anotación ``@ModelAttribute("nombre")`
        ```java
           /**
             * Método que maneja el guardado de un formulario (procesa los submit de los formularios)
             *
             * @param producto Elemento del cuerpo del <code>POST</code>
             * @param model    Command Object del formulario.
             * @return Redirección a la lista.
             */
            @PostMapping("/upsert")
            public String handleSubmitForm(Producto producto, Model model) {
                productoService.save(producto);
                return "redirect:/productos";
            }
        ```
    1. Dirigir, generalmente hacia un listado.

### Validación

Podemos añadir dentro de nuestro método ``POST`` como argumento un objeto de tipo ``org.springframework.validation.BindingResult`` que Spring rellenará con los datos de errores.
Existen herramientas de Thymeleaf para controlar errores.
```html
#fields.hasHerrors('')
th:errors
```

### Ejemplo de manejo de ficheros.



### Integración con Spring Security

Pendiente.

## Bibliografia y recursos.

### Expresiones de utilidad.
* [https://www.baeldung.com/spring-thymeleaf-3-expressions](https://www.baeldung.com/spring-thymeleaf-3-expressions)
* [https://www.thymeleaf.org/apidocs/thymeleaf/3.0.0.RELEASE/org/thymeleaf/expression/package-summary.html](https://www.thymeleaf.org/apidocs/thymeleaf/3.0.0.RELEASE/org/thymeleaf/expression/package-summary.html)
* [https://github.com/OpenWebinarsNet/Curso-introduccion-thymeleaf](https://github.com/OpenWebinarsNet/Curso-introduccion-thymeleaf)
