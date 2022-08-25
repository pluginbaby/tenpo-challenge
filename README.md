# Challenge de Tenpo (por Alex rojas)

## Consideraciones:
1. Unit tests agregados en las clases más relevantes
2. Swagger y OpenAPI para documentación.
3. Si desea ver la lista de endpoints, dirigirse a la siguiente url: http://localhost:8080/swagger-ui/index.html
4. El job está configurado para que se ejecute por primera vez luego de 3 minutos de levantada la app y luego cada 30 minutos.  
Se realizó de esta manera para que se pueda probar el endpoint cuando todavía no se haya cargado el porcentaje (devuelto por el servicio externo) y luego para que se pruebe cuando el porcentaje ya hasa ido cargado.
5. Los endpoints se pueden probar o bien desde POSTMAN (importando la coleccion con nombre de archivo tenpo-challenge.postman_collection.json ubicada en la raiz del proyecto) o bien desde swagger.   
Tener en cuenta que los endpoints /api/sum y /api/histories requieren estar autenticados, por lo que esperan recibir un token de autenticacion. Este token es devuelto cuando se realiza el login del usuario (/signin) y es devuelto en el header de la respuesta. 
Lo que se debe hacer es tomar este token y agregarlo como header en el request, una vez hecho esto, los servicios /api/sum y /api/histories deberian responder satisfactoriamente.
6. El historial de endpoints guarda resultados sólo para las llamadas a /api/sum, /api/histories api/auth/signin y api/auth/signup.

## Prerequisitos
1. Tener Docker instalar
2. Tener Postman instalado

## Guia de Usuario
1. Clonar el proyecto
2. Ir hacia el directorio del proyecto clonado y ejecutar la siguiente instruccion: 'docker-compose up'
3. Realizar requests a la aplicacion desde POSTMAN o desde Swagger. 