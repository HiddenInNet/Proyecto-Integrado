
USE MotorSphere;

-- Table Users
LOCK TABLES `users` WRITE;
INSERT INTO `users` (username, password)
VALUES
    ('dgongar', '123456'),
    ('dgongar3112', '123456'),
    ('admin', 'admin');
UNLOCK TABLES;

-- Table Etiquetas
LOCK TABLES `etiquetas` WRITE;
INSERT INTO `etiquetas` (nombre)
VALUES
    ('campo'),
    ('carretera'),
    ('sendero');
UNLOCK TABLES;

-- Table Vehiculos
LOCK TABLES `vehiculos` WRITE;
INSERT INTO `vehiculos` (nombre, imagen)
VALUES
    ('coche', 'http://localhost:8081/api/v0/media/logo.webp'),
    ('motocicleta', 'http://localhost:8081/api/v0/media/logo.webp'),
    ('bicicleta', 'http://localhost:8081/api/v0/media/logo.webp');
UNLOCK TABLES;

-- Table Insignias
LOCK TABLES `insignias` WRITE;
INSERT INTO `insignias` (valor, imagen, nombre)
VALUES
    (1, 'http://localhost:8081/api/v0/media/logo.webp', 'Nombre insignia 1'),
    (3, 'http://localhost:8081/api/v0/media/logo.webp', 'Nombre insignia 2'),
    (5, 'http://localhost:8081/api/v0/media/logo.webp', 'Nombre insginia 3');
UNLOCK TABLES;

-- Table Localizacion
LOCK TABLES `localizacion` WRITE;
INSERT INTO `localizacion` (latitud, longitud, municipio, provincia, pais)
VALUES
    ('2.23636', '3.23634', 'Sevilla', 'Sevilla', 'España'),
    ('2.23636', '3.23634', 'Sevilla', 'Sevilla', 'España'),
    ('2.23636', '3.23634', 'Sevilla', 'Sevilla', 'España');
UNLOCK TABLES;

-- Table Usuarios
LOCK TABLES `usuarios` WRITE;
INSERT INTO `usuarios` (user_id, email, nombre, apellidos, fecha_nacimiento, telefono, fecha_creacion_perfil, biografia, imagen_perfil, puntuacion)
VALUES
    (1, 'usuario1@example.com', 'Juan', 'Pérez', '1990-05-15','660536475', '2022-01-01', 'Biografía de Juan Pérez', 'http://localhost:8081/api/v0/media/logo.webp', 56),
    (2, 'usuario2@example.com', 'María', 'Gómez', '1985-08-25','626137535', '2022-01-02', 'Biografía de María Gómez', 'http://localhost:8081/api/v0/media/logo.webp', 70),
    (3, 'usuario3@example.com', 'Carlos', 'López', '1998-12-10','637536826', '2022-01-03', 'Biografía de Carlos López', 'http://localhost:8081/api/v0/media/logo.webp', 10);

UNLOCK TABLES;

-- Table PUBLICATIONS
LOCK TABLES `publicaciones` WRITE;
INSERT INTO publicaciones (nombre, fecha_subida, likes, imagen, informacion, usuario_id)
VALUES
    ('Publicación 1', '2022-01-01', 10, 'http://localhost:8081/api/v0/media/logo.webp', 'Información de la publicación 1', 1),
    ('Publicación 2', '2022-01-02', 15, 'http://localhost:8081/api/v0/media/logo.webp', 'Información de la publicación 2', 2),
    ('Publicación 3', '2022-01-03', 20, 'http://localhost:8081/api/v0/media/logo.webp', 'Información de la publicación 3', 3);

UNLOCK TABLES;

-- Table COMMENTS
LOCK TABLES `comentarios` WRITE;
INSERT INTO comentarios (usuario_id, fecha, informacion, publicacion_id)
VALUES
    (1, '2022-01-01', 'Comentario de UsuarioA en Publicación 1', 1),
    (1, '2022-01-02', 'Comentario de UsuarioB en Publicación 2', 2),
    (2, '2022-01-03', 'Comentario de UsuarioC en Publicación 3', 3);
UNLOCK TABLES;

-- Table BIDDERS
LOCK TABLES `ofertantes` WRITE;
INSERT INTO ofertantes (usuario_id, fecha_creacion, checker)
VALUES
    (1, '2022-01-01', true),
    (2, '2022-01-02', false);

UNLOCK TABLES;

-- Table EVENTS
LOCK TABLES `eventos` WRITE;
INSERT INTO eventos (nombre, descripcion, imagen, fecha_anuncio_evento, ofertante_id, exigencia, puntuacion)
VALUES
    ('Evento 1', 'Descripción del evento 1', 'http://localhost:8081/api/v0/media/logo.webp', '2022-01-01', 1, 45, 25),
    ('Evento 2', 'Descripción del evento 2', 'http://localhost:8081/api/v0/media/logo.webp', '2022-01-02', 2, 78, 70),
    ('Evento 3', 'Descripción del evento 3', 'http://localhost:8081/api/v0/media/logo.webp', '2022-01-03', 2, 89, 10);

UNLOCK TABLES;

-- Fechas
LOCK TABLES `fechas` WRITE;
INSERT INTO fechas (evento_id, plazas_disponibles, plazas_totales, precio, fecha_inicio, fecha_final)
VALUES
    (1, 13, 55, 34.5, '2022-04-12 10:35:00', '2022-04-12 15:35:00'),
    (2, 22, 23, 56.8, '2022-03-01 10:35:00', '2022-03-01 18:35:00'),
    (2, 37, 45, 24.5, '2022-08-24 10:35:00', '2022-03-01 13:35:00');

UNLOCK TABLES;

-- Usuario inscrito evento
LOCK TABLES `usuarios_inscritos_eventos` WRITE;
INSERT INTO usuarios_inscritos_eventos (usuario_id, evento_id, fecha_inscripcion, fecha_id, vehiculo_id)
VALUES
    (1, 1, '2022-01-01', 1, 1),
    (2, 2, '2022-01-02', 2, 2),
    (2, 3, '2022-01-03', 3, 3);

UNLOCK TABLES;

-- Vehiculo en evento
LOCK TABLES `vehiculos_eventos` WRITE;
INSERT INTO vehiculos_eventos (cantidad, evento_id, vehiculo_id)
VALUES
    (5, 1, 1),
    (8, 2, 3),
    (2, 3, 2);

UNLOCK TABLES;

LOCK TABLES `usuario_evento_resenia` WRITE;
INSERT INTO usuario_evento_resenia (puntuacion, evento_id, fecha_resenia, usuario_id, informacion)
VALUES
    (67, 1, '2022-01-01', 1, 'Buenisimo'),
    (28, 2, '2022-01-02', 2, 'Mejorable'),
    (89, 3, '2022-01-03', 3, 'Lo mejor');

UNLOCK TABLES;

-- User-Event (Inscripctions)
LOCK TABLES `resenias_usuarios_a_usuario` WRITE;
INSERT INTO resenias_usuarios_a_usuario (valoracion, usuario_emisor_id, usuario_receptor_id)
VALUES
    (54, 1, 3),
    (79, 1, 2),
    (23, 2, 1);

UNLOCK TABLES;

LOCK TABLES `etiquetas_eventos` WRITE;
INSERT INTO etiquetas_eventos (etiqueta_id, evento_id)
VALUES
    (1, 3),
    (2, 2),
    (3, 1);

UNLOCK TABLES;


-- User-Event (Inscripctions)
LOCK TABLES `etiquetas_usuarios` WRITE;
INSERT INTO etiquetas_usuarios (usuario_id, etiqueta_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 1);

UNLOCK TABLES;