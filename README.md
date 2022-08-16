## PROYECTO ACTIVIDAD APRENDIZAJE ACCESO A DATOS "2 Evaluación"

###### WEB API DE APP DE COMUNICACIÓN CON FAMILIAS

1. ENUNCIADO

Una empresa dedicada al cuidado de personas mayores, salud mental y  discapacidad nos encarga el desarrollo de una 
App de comunicación con las familias. Indican que esta debe ser adaptable a iOS y ANDROID.

### **Planteamiento 1ª Evaluación**
Para ello debemos desarrollar una aplicación web que conecte con una Base de Datos Relacional.
Se debe implementar una aplicación web que gestione información almacenándola y recuperándola de una Base de 
Datos Relacional. Se pensará en un supuesto real y se crearán las operaciones necesarias.


1. Deberemos tener identificados los residentes, profesionales, los centros de trabajo y los planes en los que participan.
2. Se prodra consultar y crear, modificar o elminar sobre cada una de las clases anteriores.
3. Se configurará una colección Postman que permita probar todas las operaciones anteriores.
4. Posibilidad de incluir los distintos familiares de los residentes para que tengan acceso.
5. Emisión de comunicados y alertas a los usuarios por parte del profesional

### **Requisitos (1 pto cada uno, obligatorios)**

- El modelo de datos estará compuesto de, al menos, 4 clases y tendrán que existir relaciones entre ellas. Cada clase tendrá, al menos, 6 atributos (String, int, float, boolean, LocalDate)
- Se tendrá que poder realizar, el menos, las operaciones CRUD sobre cada una de las clases. Se controlarán, al menos, los errores 404
- Añade opciones de filtrado para al menos una operación en donde se puedan indicar hasta 3 campos diferentes
- Prepara una colección Postman que permita probar todas las operaciones desarrolladas
- Configura en el proyecto la librería logback para que la aplicación web cuente con un log. Añade trazas en el código de forma que permita seguir el rastro de ejecución en el log

### **Otras funcionalidades (1 pto cada una)**

- Añade una operación PATCH para cada una de las clases del modelo
- Utiliza la herramienta Git (y GitHub) durante todo el desarrollo de la aplicación. Utiliza el gestor de Issues para los problemas/fallos que vayan surgiendo
- Añade 3 nuevos endpoints a la aplicación (sin repetir método) que realicen nuevas operaciones con los datos y que requieran el uso de DTOs
- Securiza algunas de tus operaciones de la API con un token JWT
- Añade 3 operaciones que utilicen consultas JPQL para extraer la información de la base de datos
- Añade 3 operaciones que utilicen consultas SQL nativas para extraer la información de la base de datos
- Añade 2 clases más al modelo de datos con sus respectivas operaciones CRUD (inclúyelas también en la colección Postman)

### **Planteamiento 2 ª Evaluación**

En esta Actividad de Aprendizaje se puede continuar trabajando sobre la misma idea empleada en la AA de la 1ª Evaluación. Y, si se desea, se pueden pensar y trabajar sobre otra idea.
La idea es pensar y diseñar correctamente una API con todo lo aprendido en la 2ª Evaluación. En el caso de que se decide mantener la idea de la 1ª Evaluación, se deberán hacer los cambios y ajustes necesarios en el proyecto, siempre y cuando sean necesarios, para que se ajusten a todas las decisiones de diseño que se tomen en esta 2ª Evaluación (operaciones, métodos HTTP, URIs, gestión de errores, . . .)

### **Requisitos (1 pto cada uno, obligatorios)**

-Diseña la API y escribe el fichero OpenAPI 3.0 de la API. Incluye, al menos, los casos de éxito (20X), los 404 y los 500.
-Diseña una API Virtual de forma que existan, al menos, 2 Casos de Uso para cada operación (uno de OK y otro para KO).
-Prepara una colección Postman de prueba para la API diseñada y otra que permita probar todos los Casos de Uso de la API virtual
-Diseña, al menos, 3 operaciones para que funcionen de forma reactiva con WebFlux.
-Ajusta el desarrollo de tu proyecto para que cumpla todas las decisiones de diseño adoptadas en los puntos anteriores

### **Otras funcionalidades (1 pto cada una)**

-Si tu API está securizada, añade la información necesaria al fichero OpenAPI 3.0
-Añade alguna operación en la que se envien o reciban ficheros
-Parametriza ambas colecciones Postman de forma que sea fácil cambiar el host, puerto o basePath de la API
-Añade en el diseño de tu API casos de fallo para el código de estado 400 (Bad Request)
-Añade al fichero de especificación de la API (OpenAPI 3.0) un par de ejemplos para cada operación
-Utiliza las herramientas Git y GitHub durante todo el desarrollo de la aplicación. Utiliza el gestor de Issues para los problemas/fallos que vayan surgiendo.
-Añade 2 operaciones más donde se haga uso de DTOs y se opere con datos de más de una clase utilizando la librería Spring Validation y ModelMapper para implementarlas
-Configura el proyecto para que el log recoja cualquier traza de aplicación tanto para los casos OK como para los KO (incluyendo la traza de la excepción)
-Añade una operación PATCH para cada clase (donde se controlen todos los posibles errores)
-Utiliza la librería Spring Validation para validar el formato de la información que venga como entrada en cada operación
-Securiza la API con un token JWT


