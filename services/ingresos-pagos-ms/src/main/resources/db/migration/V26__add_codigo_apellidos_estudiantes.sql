ALTER TABLE estudiantes ADD COLUMN apellidos VARCHAR(150);
ALTER TABLE estudiantes ADD COLUMN codigo_estudiante VARCHAR(9);
ALTER TABLE estudiantes ADD CONSTRAINT uk_estudiantes_codigo_estudiante UNIQUE (codigo_estudiante);
