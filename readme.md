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
* Thymeleaf nos da un dialecto por defecto para trabajar con Spring MVC, permitiendonos usar también Spring SpEl.

