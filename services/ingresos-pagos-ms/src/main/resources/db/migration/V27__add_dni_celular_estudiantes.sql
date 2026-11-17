ALTER TABLE estudiantes ADD COLUMN dni VARCHAR(8);
ALTER TABLE estudiantes ADD COLUMN celular VARCHAR(15);
ALTER TABLE estudiantes ADD CONSTRAINT uk_estudiantes_dni UNIQUE (dni);
