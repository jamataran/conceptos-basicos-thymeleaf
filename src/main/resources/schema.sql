DROP TABLE categoria IF EXISTS ;
DROP TABLE producto IF EXISTS ;
DROP TABLE puntuacion IF EXISTS ;
DROP sequence IF EXISTS hibernate_sequence ;

CREATE sequence hibernate_sequence start WITH 100 increment BY 1;

CREATE TABLE categoria (
	id BIGINT NOT NULL,
	destacada BOOLEAN NOT NULL,
	imagen VARCHAR(512),
nombre VARCHAR(512),
PRIMARY KEY(id)
);

CREATE TABLE producto (
	id BIGINT NOT NULL,
	descripcion clob,
	descuento FLOAT NOT NULL,
	imagen VARCHAR(512),
nombre VARCHAR(512),
pvp FLOAT NOT NULL,
categoria_id BIGINT,
PRIMARY KEY(id)
);

CREATE TABLE puntuacion(
id BIGINT NOT NULL,
fecha TIMESTAMP,
puntuacion integer NOT NULL,
producto_id BIGINT,
PRIMARY KEY(id)
);

ALTER TABLE producto ADD constraint fk_producto_categoria foreign KEY(categoria_id)references categoria;
ALTER TABLE puntuacion ADD constraint fk_puntuacion_producto foreign KEY(producto_id)references producto;