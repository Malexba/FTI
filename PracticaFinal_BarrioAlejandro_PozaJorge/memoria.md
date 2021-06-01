# Práctica 2

Práctica realizada por Alejandro Barrio Mateos y Jorge Poza Tamayo.

## Construcción de las recetas

Primero creamos un documento sencillo que nos permitía mostrar por pantalla una receta simple (únicamente contenía título, pasos e ingredientes) cuyos datos importamos de un archivo de extensión json. Una vez hecho esto, hicimos un esquema con todos los atributos que podía tener una receta en caso de que combinase los atributos de los 4 tipos. Así, únicamente tendríamos que distinguir los datos relativos a ese tipo de receta a la hora de mostrarlo. Como referencia para las recetas de libros de papel hemos usado lo hecho en la práctica 1, con alguna modificación sobre algunos atributos que no habíamos definido correctamente  por aquel entonces. Por lo tanto, los atributos de los que puede constar son:

- **Modo:** Identificador para distinguir si es una receta de un libro de papel (1), de Cookpad (2), de un vídeo de YouTube (3) o de Recetas de rechupete (4).
- **Título:** Nombre de la receta. Común para todos los tipos.
- **Imagen Final:** Foto del plato una vez preparado y emplatado. En caso de que la receta provenga de YouTube, se muestra únicamente en la página principal (cuando se elige qué receta mostrar)
- **Datos:** En esta sección se agrupa diversa información general sobre el plato. Especificaremos su contenido posteriormente.
- **Descripción:** Solo lo hemos incluido para recetas de Cookpad (donde se incluye únicamente una breve descripción) y para las del cuarto tipo (que al ser más extensas hemos almacenado como lista para albergar los diversos párrafos).
- **Vídeo:** Vídeo de la receta cuyo origen es YouTube; o el de aquellas provenientes de Recetas de rechupete que incluyan vídeo (para ello incluimos un booleano que indica si existe o no dicho recurso).
- **Ingredientes:** Lista de ingredientes que se requieren para la elaboración de la comida.
- **Pasos:** Procedimiento necesario para la elaboración de la receta. Se muestra por pantalla como una lista ordenada.
- **Utensilios:** Instrumental necesario para preparar la receta. Únicamente se incluye en las recetas de papel, pues ya incluimos este atributo en la primera práctica.
- **Consejos:** Diversas sugerencias sobre la elaboración y presentación del plato. El único recurso que no los incluye es Cookpad.
- **Autor:** Nombre de quien comparte la receta. Común a cualquier tipo.
- **Fuente:** Enlace a la web o vídeo de donde se ha extraido la receta. En caso de que sea de tipo 1, simplemente se dice que proviene de un libro de papel.

### Datos

Compendio de información diversa sobre la receta. Únicamente los incluyen las recetas de los libros de papel y las de Recetas de rechupete. Algunos de estos son comúnes a ambas, como:

- **Nº de personas:** Cantidad de personas que pueden comer una ración media si se sigue la receta con las cantidades proporcionadas.
- **Dificultad:** Destreza requerida para elaborar el plato. Suele reducirse a si es *Fácil*, *Medio* ó *Difícil*.
- **Tiempo:** Cuánto se tarda en elaborar la receta.

Para las recetas del tipo 1, se incluyen también los siguientes datos:

- **Tipo:** Información variada sobre el plato. Suele indicar el tipo de plato (entrante, primero, segundo o postre) y si se sirve frío o caliente.
- **Temporada:** Época del año (estación) predilecta para su consumo, bien sea por ser típico de esas fechas o por que los ingredientes requeridos para su elaboración únicamente están disponibles entonces.
- **Apto para celíacos:** Indica si el plato contiene gluten. En caso afirmativo, lo indicamos con un icono de una rebanada de pan (para advertir a personas intolerantes al gluten).
- **Apto para veganos:** En caso de que el plato no haya sido preparado con productos de origen animal, lo indicamos con el logo de una simiente germinando.

En el caso de las recetas del tipo 4, hemos visto que en esa página se incluye como información adicional:

- **Precio:** Cuantía por persona.
- **Calorías:** Kilocalorías por 100 gramos.
- **Tipo plato:** La página web agrupa sus recetas en diversas categorías como ensaladas, postres, etc. Por tanto, estos datos se almacenan en una lista (pues una misma receta puede pertenecer a varias categorías).

### Pasos

Atributo común para todas las recetas. Se almacena como una lista salvo si es del tipo 2 o 3. Para dichos casos deben considerarse más elementos, pues no es únicamente el texto que indica cómo hacer la receta. Las recetas de Cookpad pueden incluir en algunos de sus pasos una o varias imágenes de cómo proceder. Como también puede no incluirse ninguna foto, debemos considerar un booleano que nos indique si dicho paso tiene alguna imagen asociada o no.

### Particularidades de las recetas de YouTube

La principal diferencia de las recetas del tipo 3 con respecto al resto es que toda la información se encuentra en un archivo de vídeo, por lo que la página busca facilitar el acceso a ella. Así, se incluyen enlaces que fijan el punto de inicio del vídeo en momentos clave del mismo como cuando se presentan los ingredientes, los diversos pasos que hay que seguir para hacer la receta o los consejos que da el chef. Esta asignación se realiza a través de la función *moverVideo*, definida en el fichero javascript asociado.

## Construcción del agregador de recetas

Una vez definido el esquema general de visualización de cada uno de los tipos de recetas, creamos la página principal de la aplicación web. Ésta indica al usuario que se encuentra ante un agregador de recetas y le incita a indagar sobre las mismas a través de diversos botones, representando cada uno una receta distinta. A la hora de definir dichos botones decidimos que simplemente mostrasen el título de la receta, la imagen final del plato y el autor; pues son los únicos atributos comúnes a todos los tipos y dota de una mayor sencillez a la interfaz.

A nivel de implementación, el archivo de extensión javascript se encarga de importar los datos de las diversas recetas (almacenadas en el json correspondiente) y guardarlos en una variable *recetario*. También consta de un booleano *mostrarReceta* que indica si se está o no en el menú principal. En caso negativo, los datos de la receta seleccionada se almacenan en la variable *receta* para mostrarlos (esta operación se ha realizado a través del método *mostrarReceta*). Las otras funciones de las que disponemos son *retroceder*, para poder regresar al menú principal; y la ya mentada *moverVideo*.

En vez de optar por el booleano para mostrar recetas, intentamos en primer lugar recurrir a los comandos CSS, *display* y después a *visibility* para modificar su valor haciendo uso de funciones JavaScript igual que hacemos ahora. Sin embargo, ambos métodos nos daban algunos problemas a la hora de mostrar la página, lo cual nos hizo decantarnos por la metodología actual.

## Hoja de estilo e implementación Bootstrap

Para la elaboración de la hoja de estilo hemos tomado como referencia tanto el formato empleado en la web Cookpad, como el de Recetas de rechupete. La elección de la fuente ha sido Times New Roman para dotar a toda la aplicación de coherencia con respecto a su formato.

Con respecto a que la aplicación fuese Responsive, hemos procurado que para la página principal se mantuviese el formato de título y cada fila constase de una pareja de botones, independientemente del tamaño del navegador o dispositivo. El mismo procedimiento estilístico hemos seguido para con las páginas individuales de las receta, pues cada campo se mantiene estructurado de forma coherente y se le proporciona una presentación secuencial.

El uso de Bootstrap puede verse en la presentación que realizamos de las diversas imágenes que puede tener un paso del segundo tipo, pues en caso de no haber empleado esto las imágenes se nos solapaban con el texto.

## Acceso a documentos online

Tal y como se nos pedía en la práctica, hemos colgado también los archivos en los subdominios web de la primera práctica de laboratorio. Enlaces:

- **Página de Jorge:** [Inicio](https://jorgepoza.000webhostapp.com/) y [agregador de recetas](https://jorgepoza.000webhostapp.com/PracticaFinal/index.html)
- **Página de Alejandro:** [Inicio](https://alejandrobarrio.000webhostapp.com/) y [agregador de recetas](https://alejandrobarrio.000webhostapp.com/PracticaFinal/index.html)