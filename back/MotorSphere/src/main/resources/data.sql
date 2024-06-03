
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

-- Table Insignias
LOCK TABLES `insignias` WRITE;
INSERT INTO `insignias` (valor, imagen, nombre)
VALUES
    (1, 'http://localhost:8080/api/v0/media/logo.webp', 'Nombre insignia 1'),
    (3, 'http://localhost:8080/api/v0/media/logo.webp', 'Nombre insignia 2'),
    (5, 'http://localhost:8080/api/v0/media/logo.webp', 'Nombre insginia 3');
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
    (1, 'usuario1@example.com', 'Juan', 'Pérez', '1990-05-15','660536475', '2022-01-01', 'Biografía de Juan Pérez', 'http://localhost:8080/api/v0/media/logo.webp', 56),
    (2, 'usuario2@example.com', 'María', 'Gómez', '1985-08-25','626137535', '2022-01-02', 'Biografía de María Gómez', 'http://localhost:8080/api/v0/media/logo.webp', 70),
    (3, 'usuario3@example.com', 'Carlos', 'López', '1998-12-10','637536826', '2022-01-03', 'Biografía de Carlos López', 'http://localhost:8080/api/v0/media/logo.webp', 10);

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
INSERT INTO eventos (nombre, descripcion, imagen, fecha_anuncio_evento, ofertante_id, exigencia, puntuacion, insignia_id, localizacion_id)
VALUES
    ('Evento 1', 'Descripción del evento 1', 'http://localhost:8080/api/v0/media/logo.webp', '2022-01-01', 1, 45, 25, 1, 1),
    ('Evento 2', 'Descripción del evento 2', 'http://localhost:8080/api/v0/media/logo.webp', '2022-01-02', 2, 78, 70, 2, 2),
    ('Evento 3', 'Descripción del evento 3', 'http://localhost:8080/api/v0/media/logo.webp', '2022-01-03', 2, 89, 10, 3, 3);

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
INSERT INTO usuarios_inscritos_eventos (usuario_id, evento_id, fecha_inscripcion, fecha_id)
VALUES
    (1, 1, '2022-01-01', 1),
    (2, 2, '2022-01-02', 2),
    (2, 3, '2022-01-03', 3);

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