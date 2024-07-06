# **Descripción del proyecto**

Music Library es una aplicación de gestión musical que permite a los usuarios organizar, almacenar y acceder a una colección de información sobre canciones, álbumes, artistas y géneros musicales. Con esta aplicación, los usuarios pueden realizar diversas acciones, como añadir canciones a álbumes, vincular artistas con álbumes, explorar diferentes géneros musicales, y administrar sus listas de reproducción personalizadas.

Además, Music Library ofrece funciones de autenticación y autorización para garantizar la seguridad de los usuarios y de su contenido musical. Los roles de usuario pueden ser gestionados, permitiendo que diferentes usuarios tengan diferentes niveles de acceso y control sobre la biblioteca musical.

[Music Library.pdf](https://github.com/user-attachments/files/16112426/Music.Library.pdf)


## Configuración

1. Base de datos:
    
    MySQL.
    Configuración de conexión a la base de datos.
    Mapeo de entidades y relaciones con la base de datos (usando Hibernate).

2. Seguridad:
    
    Configuración de autenticación y autorización, posiblemente utilizando Spring Security.
    Roles y permisos de usuario.
    Encriptación de contraseñas y gestión de sesiones.

3. API RESTful:

    Configuración de los controladores REST para manejar las operaciones CRUD (Crear, Leer, Actualizar, Borrar) para canciones, álbumes, artistas, etc.
    Configuración de las rutas URL para acceder a los recursos de la aplicación.

4. Servicios y repositorios:

    Configuración e inyección de dependencias de los servicios y repositorios utilizados para interactuar con la base de datos.

5. DTOs (Objetos de transferencia de datos):

    Definición de DTOs para transferir datos entre las capas de la aplicación y la presentación de datos en las respuestas de la API REST.

6. Pruebas:

    Configuración de las pruebas unitarias, de integración y de extremo a extremo utilizando las herramientas correspondientes (JUnit, Mockito, MockMvc).

7. Logging y monitoreo:

    Configuración de registro (logging) para realizar un seguimiento de las operaciones y sucesos importantes en la aplicación.

## Tecnologías utilizadas

1. Lenguaje de Programación:

    Java: Utilizado para el desarrollo del backend de la aplicación.

2. Framework de Desarrollo:

    Spring Framework: Spring Boot para la configuración de aplicaciones autocontenidas.

3. Base de Datos:

    MySQL.

4. Persistencia de Datos:

    Hibernate: Utilizado para el mapeo objeto-relacional (ORM).

5. Seguridad:

    Spring Security: Usado para la gestión de autenticación, autorización y seguridad en la aplicación.

6. Construcción de API:

    Spring MVC: Utilizado para la creación de API RESTful.

7. Herramientas de Pruebas:

    JUnit: Marco de pruebas unitarias.
    Mockito: Utilizado para simular objetos en pruebas unitarias.
    MockMvc: Utilizado para pruebas de controladores en el contexto de Spring MVC.

8. Gestión de Dependencias:

    Maven: Herramientas para la gestión de dependencias y construcción del proyecto.

9. Herramientas de testing:

    Postman: Herramientas para probar y documentar API.

10. Control de Versiones:

    Git: Utilizado para el control de versiones del código fuente.

## Estructura de controladores y rutas

#### 1. Controladores:

* **_SongController:_** Maneja las operaciones relacionadas con las canciones, como crear, actualizar, eliminar y recuperar.
* **_AlbumController:_** Controla las acciones relacionadas con los álbumes, como crear, actualizar, eliminar y recuperar.
* **_ArtistController:_** Gestiona las operaciones relacionadas con los artistas, como crear, actualizar, eliminar y recuperar.
* **_PlayListController:_** Encargado de las acciones relacionadas con las listas de reproducción, como crear, actualizar, eliminar y recuperar.
* **_GenreController:_** Controla las operaciones relacionadas con los géneros musicales, como crear, actualizar, eliminar y recuperar.
* **_UserController:_** Maneja las operaciones de gestión de usuarios, como crear, actualizar, eliminar y recuperar.


#### 2. Rutas:

**_/songs_**: 

POST **_/songs_**: Crea una nueva canción.  
PUT **_/songs/{id}_**: Actualiza una canción existente por su ID.  
GET **_/songs_**: Obtiene la lista de todas las canciones.  
GET **_/songs/{id}_**: Obtiene una canción específica por su ID.  
DELETE **_/songs/{id}/delete_**: Elimina una canción por su ID.

**_/albums_**: 

POST **_/albums_**: Crea un nuevo álbum.  
PUT **_/albums/{id}_**: Actualiza un álbum existente por su ID.  
GET **_/albums_**: Obtiene la lista de todos los álbumes.  
GET **_/albums/id/{id}_**: Obtiene un álbum específico por su ID.  
DELETE **_/albums/{id}/delete_**: Elimina un álbum por su ID.  

**_/artists_**: 

POST **_/artists_**: Crea un nuevo artista.  
PUT **_/artists/{id}_**: Actualiza un artista existente por su ID.  
GET **_/artists_**: Obtiene la lista de todos los artistas.  
GET **_/artists/id/{id}_**: Obtiene un artista específico por su ID.  
DELETE **_/artists/{id}/delete_**: Elimina un artista por su ID.  

**_/playlists_**:

POST **_/playlists_**: Crea una nueva lista de reproducción.
GET **_/playlists_**: Obtiene la lista de todas las listas de reproducción.
GET **_/playlists/id/{id}_**: Obtiene una lista de reproducción específica por su ID.
PUT **_/playlists/{id}_**: Actualiza una lista de reproducción existente por su ID.
DELETE **_/playlists/{id}/delete_**: Elimina una lista de reproducción por su ID.

**_/genres_**:

POST **_/genres_**: Crea un nuevo género musical.
PUT **_/genres/{id}_**: Actualiza un género musical existente por su ID.
GET **_/genres_**: Obtiene la lista de todos los géneros musicales.
GET **_/genres/id/{id}_**: Obtiene un género musical específico por su ID.
DELETE **_/genres/{id}/delete_**: Elimina un género musical por su ID.

**_/users_**:

POST **_/users_**: Crea un nuevo usuario.
PUT **_/users/{id}_**: Actualiza un usuario existente por su ID.
GET **_/users_**: Obtiene la lista de todos los usuarios.
GET **_/users/id/{id}_**: Obtiene un usuario específico por su ID.
DELETE **_/users/{id}/delete_**: Elimina un usuario por su ID.

## Enlaces adicionales

Gestor de tareas: Trello  
https://trello.com/invite/b/n8qAjMpR/ATTId3038aa4635ffb5f53a164edcce3ac263F3E0D48/music-library

Presentación:
https://docs.google.com/presentation/d/13toSJe2Cheiwp-cycKvCAukjp9tLeGDNd4ZDicTz-9U/edit?usp=sharing

## Trabajo futuro

1. **Desarrollo de la interfaz de usuario:**

    Crear una interfaz de usuario atractiva y fácil de usar para que los usuarios puedan interactuar con la aplicación de manera intuitiva.

2. **Mejoras de usabilidad:**
    
    Realizar pruebas de usabilidad y recopilar comentarios de los usuarios para mejorar la interfaz de usuario, la navegación y la experiencia general del usuario.

3. **Optimización de rendimiento:**

    Identificar y abordar posibles cuellos de botella de rendimiento, mejorar la velocidad de carga, optimizar consultas de base de datos, etc.

4. **Expansión de la base de datos:**

    Ampliar la base de datos para incluir más información sobre la música, reseñas de usuarios, estadísticas de reproducción, etc.

5. **Pruebas de usuario:**

    Realizar pruebas de usuario para validar la usabilidad, el flujo de trabajo y la funcionalidad del frontend.


