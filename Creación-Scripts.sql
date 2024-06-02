CREATE TABLE `Users` (
  `id` integer PRIMARY KEY,
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL
);

CREATE TABLE `Insignias` (
  `id` integer PRIMARY KEY,
  `nombre` varchar(255) NOT NULL,
  `imagen` varchar(255),
  `valor` integer NOT NULL
);

CREATE TABLE `Localizacion` (
  `id` integer NOT NULL,
  `latitud` varchar(255),
  `longitud` varchar(255),
  `municipio` varchar(255),
  `provincia` varchar(255),
  `pais` varchar(255)
);

CREATE TABLE `Vehiculos` (
  `id` integer PRIMARY KEY,
  `nombre` varchar(255) NOT NULL,
  `imagen` varchar(255)
);

CREATE TABLE `Etiquetas` (
  `id` integer PRIMARY KEY,
  `nombre` varchar(255) UNIQUE NOT NULL
);

CREATE TABLE `Usuarios` (
  `id` integer PRIMARY KEY,
  `user_id` integer,
  `email` varchar(255) UNIQUE,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255),
  `fecha_nacimiento` date NOT NULL,
  `telefono` varchar(255),
  `fecha_creacion_perfil` datetime NOT NULL,
  `biografia` varchar(255),
  `imagen_perfil` varchar(255),
  `puntuacion` integer
);

CREATE TABLE `UsuarioResenias` (
  `id` integer PRIMARY KEY,
  `usuario_emisor_id` integer,
  `usuario_receptor_id` integer,
  `valoracion` integer
);

CREATE TABLE `Publicaciones` (
  `id` integer PRIMARY KEY,
  `usuario_id` integer,
  `titulo` varchar(255) NOT NULL,
  `descripcion` varchar(255),
  `fecha_subida` datetime NOT NULL,
  `likes` integer NOT NULL,
  `imagen` varchar(255)
);

CREATE TABLE `Comentarios` (
  `id` integer PRIMARY KEY,
  `usuario_id` integer,
  `publicacion_id` integer,
  `fecha` datetime NOT NULL,
  `texto` varchar(255) NOT NULL
);

CREATE TABLE `Ofertantes` (
  `id` integer PRIMARY KEY,
  `usuario_id` integer,
  `fecha_creacion` datetime NOT NULL,
  `checker` bool NOT NULL
);

CREATE TABLE `Eventos` (
  `id` integer PRIMARY KEY,
  `localizacion_id` integer,
  `insignia_id` integer,
  `ofertante_id` integer,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `imagen` varchar(255),
  `fecha_anuncio_evento` datetime NOT NULL,
  `puntuacion` integer,
  `exigencia` integer NOT NULL
);

CREATE TABLE `Fechas` (
  `id` integer PRIMARY KEY,
  `evento_id` integer,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `precio` float NOT NULL,
  `plazas` integer
);

CREATE TABLE `UsuariosEventosResenias` (
  `id` integer PRIMARY KEY,
  `usuario_id` integer,
  `evento_id` integer,
  `puntuacion` integer NOT NULL,
  `fecha_resenia` datetime NOT NULL,
  `informacion` varchar(255)
);

CREATE TABLE `UsuariosInscritosEventos` (
  `id` integer PRIMARY KEY,
  `usuario_id` integer,
  `evento_id` integer,
  `vehiculo_id` integer,
  `fecha_inscripcion` datetime NOT NULL,
  `fecha_elegida` integer
);

CREATE TABLE `VehiculosEventos` (
  `id` integer PRIMARY KEY,
  `vehiculo_id` integer,
  `evento_id` integer,
  `cantidad` integer
);

CREATE TABLE `EtiquetasUsuarios` (
  `id` integer PRIMARY KEY,
  `etiqueta_id` integer,
  `usuario_id` integer
);

CREATE TABLE `EtiquetasEventos` (
  `id` integer PRIMARY KEY,
  `etiqueta_id` integer,
  `evento_id` integer
);

ALTER TABLE `Usuarios` ADD FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`);

ALTER TABLE `UsuarioResenias` ADD FOREIGN KEY (`usuario_emisor_id`) REFERENCES `Usuarios` (`id`);

ALTER TABLE `UsuarioResenias` ADD FOREIGN KEY (`usuario_receptor_id`) REFERENCES `Usuarios` (`id`);

ALTER TABLE `Publicaciones` ADD FOREIGN KEY (`usuario_id`) REFERENCES `Usuarios` (`id`);

ALTER TABLE `Comentarios` ADD FOREIGN KEY (`usuario_id`) REFERENCES `Usuarios` (`id`);

ALTER TABLE `Comentarios` ADD FOREIGN KEY (`publicacion_id`) REFERENCES `Publicaciones` (`id`);

ALTER TABLE `Ofertantes` ADD FOREIGN KEY (`usuario_id`) REFERENCES `Usuarios` (`id`);

ALTER TABLE `Eventos` ADD FOREIGN KEY (`localizacion_id`) REFERENCES `Localizacion` (`id`);

ALTER TABLE `Eventos` ADD FOREIGN KEY (`insignia_id`) REFERENCES `Insignias` (`id`);

ALTER TABLE `Eventos` ADD FOREIGN KEY (`ofertante_id`) REFERENCES `Ofertantes` (`id`);

ALTER TABLE `Fechas` ADD FOREIGN KEY (`evento_id`) REFERENCES `Eventos` (`id`);

ALTER TABLE `UsuariosEventosResenias` ADD FOREIGN KEY (`usuario_id`) REFERENCES `Usuarios` (`id`);

ALTER TABLE `UsuariosEventosResenias` ADD FOREIGN KEY (`evento_id`) REFERENCES `Eventos` (`id`);

ALTER TABLE `UsuariosInscritosEventos` ADD FOREIGN KEY (`usuario_id`) REFERENCES `Usuarios` (`id`);

ALTER TABLE `UsuariosInscritosEventos` ADD FOREIGN KEY (`evento_id`) REFERENCES `Eventos` (`id`);

ALTER TABLE `UsuariosInscritosEventos` ADD FOREIGN KEY (`vehiculo_id`) REFERENCES `Vehiculos` (`id`);

ALTER TABLE `UsuariosInscritosEventos` ADD FOREIGN KEY (`fecha_elegida`) REFERENCES `Fechas` (`id`);

ALTER TABLE `VehiculosEventos` ADD FOREIGN KEY (`vehiculo_id`) REFERENCES `Vehiculos` (`id`);

ALTER TABLE `VehiculosEventos` ADD FOREIGN KEY (`evento_id`) REFERENCES `Eventos` (`id`);

ALTER TABLE `EtiquetasUsuarios` ADD FOREIGN KEY (`etiqueta_id`) REFERENCES `Etiquetas` (`id`);

ALTER TABLE `EtiquetasUsuarios` ADD FOREIGN KEY (`usuario_id`) REFERENCES `Usuarios` (`id`);

ALTER TABLE `EtiquetasEventos` ADD FOREIGN KEY (`etiqueta_id`) REFERENCES `Etiquetas` (`id`);

ALTER TABLE `EtiquetasEventos` ADD FOREIGN KEY (`evento_id`) REFERENCES `Eventos` (`id`);
