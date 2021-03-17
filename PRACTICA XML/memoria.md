# Práctica 1

Práctica realizada por Alejandro Barrio Mateos y Jorge Poza Tamayo.

## Construcción del DTD

Antes de la realización del documento XML, decidimos hacer el DTD para estructurar cómo realizar el recetario. Sabemos que el recetario dispondrá de al menos una receta, teniendo ésta los siguientes componentes:

- **Título:** Nombre de la receta. Obligatorio para poder identificarla.
- **Imagen:** Foto del plato una vez preparado y emplatado. Hemos puesto este elemento como obligatorio para que el usuario pueda apreciar cuál sería el resultado en caso de preparar la receta.
- **Datos:** En esta sección hemos agrupado información general sobre el plato. Especificaremos su contenido posteriormente.
- **Ingredientes:** Lista de ingredientes que se requieren para la elaboración de la comida. Sabemos que como mínimo la receta llevará un ingrediente. A ellos les asociamos un nombre que los identifica y la cantidad que hace falta.
- **Procedimiento:** Pasos necesarios para la elaboración de la receta (como mínimo hay uno). Hemos añadido como atributo el nº de paso para que se puedan ordenar.
- **Elementos de elaboración:** Instrumental necesario para preparar la receta. Como mínimo se suele requerir de un utensilio para preparar cualquier receta.
- **Opcional:** Información adicional sobre la receta que no siempre se incluye. Los distintos elementos de este apartado se concretarán a posteriori.

### Datos

Compendio de información diversa sobre la receta. Decidimos agruparlos aquí, pues suelen incluirse al principio de los recetarios como datos generales. Consta de los siguientes elementos:

- **Tipo:** Información variada sobre el plato (para niños, se sirve en frío, ...). Definimos los siguientes atributos, que creamos como tal para poder limitar los valores que pueden tomar:
    * Definición de si es *entrante*, *primero*, *segundo* ó *postre*.
    * Indica si el plato es apto para celíacos o no.
    * Indica si el plato es vegetariano o no.
- **Nº personas:** Cantidad de personas que pueden comer una ración media si se sigue la receta con las cantidades proporcionadas.
- **Dificultad:** Destreza requerida para elaborar el plato. Dudamos si ponerlo como atributo para limitar las opciones a *Fácil*, *Medio* ó *Difícil*; pero preferimos incluirlo como elemento.
- **Tiempo:** Cuánto se tarda en elaborar la receta.
- **Calorías:** Información calórica sobre lo que vamos a consumir.

### Opcional

Tanto este campo como sus componentes las hemos incluido como opcionales; pues podríamos querer incluir alguna de ellas, todas o ninguna. Hemos definido los siguientes elementos:

- **Autor:** Origen de la receta, bien sea el nombre del propio autor o la web/libro de donde se ha extraído.
- **Temporada:** Época del año predilecta para su consumo, bien sea por ser típico de esas fechas o por que los ingredientes requeridos para su elaboración únicamente están disponibles entonces. Este elemento lo hemos definido vacío y únicamente consta de un atributo cuyos posibles valores son *Otoño*, *Invierno*, *Primavera* ó *Verano*.
- **Bebida:** Refrigerio recomendado para acompañar la comida.
- **Consejo del Chef:** Sugerencia sobre la elaboración y presentación del plato.

## Construcción del XML

Documento

## Validación del documento

Para la validación del documento XML hemos construido una applicación en Java a partir de la clase **DocumentBuilder**, la cual permite manejar correctamente este tipo de documentos. Gracias a las excepciones que dicha clase implementa, podemos notificar al usuario de los siguientes posibles errores:

- No ceñirse al DTD que use como esquema
- El documento XML proporcionado no está bien formado

Para ello, se toma el documento *recetario.xml* que se encuentre dentro de la carpeta del proyecto Java y se efectúa dicha validación. Este programa lo hemos extraído del siguiente [foro de Stack Overflow](https://stackoverflow.com/questions/8699620/how-to-validate-xml-with-dtd-using-java).