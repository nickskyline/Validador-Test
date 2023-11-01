# WorkshopValidador
Creacion de una aplicacion validadora y procesadora de archivos, en este caso de tipo xlsx y csv.

---

## ACLARACIÓN
La aplicación se encuentra en la rama entrega_workshop

---

## Instrucciones de uso
Para utilizar el aplicativo simplemente se deben ejecutar los dos microservicios, validador y procesador.

A través de una herramienta como Postman enviar la petición POST a la siguiente url:

```
http://localhost:8081/api/v1/files
```

y enviar un JSON con el siguiente cuerpo:


```
{
    "path" : "ruta del archivo",
    "fileType" : "tipo del archivo"
}
```

Si la solicitud es válida, debe retornar la cantidad de la cantidad de líneas válidas e invalidas del documento.

