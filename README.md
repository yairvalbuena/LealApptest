# LealApptest
Prueba de Leal
# Aplicacion de Android
Repositorio de aplicación de prueba para Leal Prueba Ingreso
## Descripcion de la aplicacion:
La aplicacion muestra un listados de transacciones en la barra lateral, dichas transacciones son obtenidas desde la API otorgada por LEAL(https://mobiletest.leal.co).
## Descripcion Tecnica:
* La app se estructura con MVC
* La App utiliza la API desde (https://mobiletest.leal.co) para obtener los listados de Transacciones, informacion de usuario e informacion de transaccion 
* Se utiliza retrofit para realizar los peticiones REST al servicio.
* Se puede eliminar la lista de Transacciones obtenida con el boton ubicado en el fonde de la aplicacion.
* Se puede eliminar cada transacción si se desliza hacia la derecha.
* La aplicacion cuenta con una opción para recargar todos los elementos de la lista de transacciones.
* Se realizaron pruebas unitarias sobre dispositivo movil.
* Se realizan pruebas con Expresso Test Archivo Ubicado en app\src\androidTest\java\yairvalbuena\android\lealtestapp
* Librerias de terceros Utilizada:Retrofit2, Picasso.
