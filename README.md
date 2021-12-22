## PROYECTO ACTIVIDAD APRENDIZAJE ACCESO A DATOS

###### WEB API DE APP DE COMUNICACIÓN CON FAMILIAS

1. ENUNCIADO

Una empresa dedicada al cuidado de personas mayores, salud mental y  discapacidad nos encarga el desarrollo de una
App de comunicación con las familias. Indican que esta debe ser adaptable a iOS y ANDROID.

Para ello debemos desarrollar una una aplicación web que conecte con una Base de Datos Relacional.
Se debe implementar una aplicación web que gestione información almacenándola y recuperándola de una Base de
Datos Relacional. Se pensará en un supuesto real y se crearán las operaciones necesarias.


1. Deberemos tener identificados los residentes, profesionales, los centros de trabajo y los planes en los que participan.
2. Se prodrá consultar y crear, modificar o elminar sobre cada una de las clases anteriores.
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
