
USE motorSphere;

LOCK TABLES `login` WRITE;
INSERT INTO `login` (username, password)
VALUES
    ('dgongar', '123456'),
    ('dgongar3112', '123456'),
    ('admin', 'admin');
UNLOCK TABLES;

-- Table USERS
LOCK TABLES `usuarios` WRITE;
INSERT INTO `usuarios` (login_id, email, name, last_name, birth_date, phone, profile_create_date, biography, profile_image)
    VALUES
        (1, 'usuario1@example.com', 'Juan', 'Pérez', '1990-05-15','660536475', '2022-01-01', 'Biografía de Juan Pérez', NULL),
        (2, 'usuario2@example.com', 'María', 'Gómez', '1985-08-25','626137535', '2022-01-02', 'Biografía de María Gómez', NULL),
        (3, 'usuario3@example.com', 'Carlos', 'López', '1998-12-10','637536826', '2022-01-03', 'Biografía de Carlos López', NULL);

UNLOCK TABLES;

-- Table PUBLICATIONS
LOCK TABLES `publications` WRITE;
INSERT INTO publications (name, upload_date, likes, image, information, usuario_id)
    VALUES
        ('Publicación 1', '2022-01-01', 10, NULL, 'Información de la publicación 1', 1),
        ('Publicación 2', '2022-01-02', 15, NULL, 'Información de la publicación 2', 2),
        ('Publicación 3', '2022-01-03', 20, NULL, 'Información de la publicación 3', 3);

UNLOCK TABLES;

-- Table COMMENTS
LOCK TABLES `comments` WRITE;
INSERT INTO comments (usuario_id, date, information, publication_id)
VALUES
    (1, '2022-01-01', 'Comentario de UsuarioA en Publicación 1', 1),
    (1, '2022-01-02', 'Comentario de UsuarioB en Publicación 2', 2),
    (2, '2022-01-03', 'Comentario de UsuarioC en Publicación 3', 3);
UNLOCK TABLES;

-- Table BIDDERS
LOCK TABLES `bidders` WRITE;
INSERT INTO bidders (usuario_id, creation_date, checker)
    VALUES
        (1, '2022-01-01', true),
        (2, '2022-01-02', false);

UNLOCK TABLES;

-- Table EVENTS
LOCK TABLES `events` WRITE;
INSERT INTO events (name, description, event_latitude, event_longitude, image, upload_date, due_date, bidder_id)
    VALUES
        ('Evento 1', 'Descripción del evento 1', 40.416775, -3.703790, NULL, '2022-01-01', '2022-02-01', 1),
        ('Evento 2', 'Descripción del evento 2', 41.385064, 2.173404, NULL, '2022-01-02', '2022-02-02', 2),
        ('Evento 3', 'Descripción del evento 3', 51.507351, -0.127758, NULL, '2022-01-03', '2022-02-03', 2);

UNLOCK TABLES;

-- Table INSIGNIAS
LOCK TABLES `insignias` WRITE;
INSERT INTO insignias (name, image, value, event_id)
VALUES
    ('Insignia 1', NULL, 3, 1),
    ('Insignia 2', NULL, 4, 2),
    ('Insignia 3', NULL, 5, 3);

UNLOCK TABLES;

-- User-Event (Inscripctions)
LOCK TABLES `user_event` WRITE;
INSERT INTO user_event (usuario_id, event_id, inscription_date)
    VALUES
        (1, 1, '2022-01-01'),
        (2, 2, '2022-01-02'),
        (2, 3, '2022-01-03');

UNLOCK TABLES;