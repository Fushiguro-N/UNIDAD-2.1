INSERT INTO periodos_academicos (nombre, fecha_inicio, fecha_fin, activo)
SELECT * FROM (VALUES
    ('2026-I', DATE '2026-03-01', DATE '2026-07-15', true),
    ('2026-II', DATE '2026-08-01', DATE '2026-12-15', true),
    ('2025-II', DATE '2025-08-01', DATE '2025-12-15', false)
) AS v(nombre, fecha_inicio, fecha_fin, activo)
WHERE NOT EXISTS (SELECT 1 FROM periodos_academicos p WHERE p.nombre = v.nombre);

INSERT INTO modalidades_curso (nombre, descripcion, activo)
SELECT * FROM (VALUES
    ('Presencial', 'Clases dictadas en aula fisica', true),
    ('Virtual', 'Clases dictadas por plataforma en linea', true),
    ('Semipresencial', 'Combinacion de clases presenciales y virtuales', true)
) AS v(nombre, descripcion, activo)
WHERE NOT EXISTS (SELECT 1 FROM modalidades_curso m WHERE m.nombre = v.nombre);

INSERT INTO categorias_curso (nombre, descripcion, activo)
SELECT * FROM (VALUES
    ('Ingenieria', 'Cursos de la carrera de Ingenieria de Sistemas', true),
    ('Negocios', 'Cursos de administracion y marketing', true),
    ('Idiomas', 'Cursos de idiomas extranjeros', true)
) AS v(nombre, descripcion, activo)
WHERE NOT EXISTS (SELECT 1 FROM categorias_curso c WHERE c.nombre = v.nombre);

INSERT INTO mallas_curriculares (programa, nombre, version, activo)
SELECT * FROM (VALUES
    ('Ingenieria de Sistemas', 'Malla Ingenieria de Sistemas 2024', '2024-1', true),
    ('Administracion', 'Malla Administracion 2023', '2023-2', true)
) AS v(programa, nombre, version, activo)
WHERE NOT EXISTS (SELECT 1 FROM mallas_curriculares mc WHERE mc.nombre = v.nombre);

INSERT INTO secciones_curso (id_curso, id_docente, id_periodo_academico, nombre_seccion, cupo_maximo, activo)
SELECT c.id, d.id, p.id, v.nombre_seccion, v.cupo_maximo, v.activo
FROM (VALUES
    ('Desarrollo de Aplicaciones Distribuidas', 'carlos.ramirez@upeu.edu.pe', '2026-I', 'A', 30, true),
    ('Desarrollo de Aplicaciones Distribuidas', 'pedro.chavez@upeu.edu.pe', '2026-I', 'B', 25, true),
    ('Marketing Digital', 'ana.gutierrez@upeu.edu.pe', '2026-I', 'A', 35, true),
    ('Ingles Basico', 'maria.fernandez@upeu.edu.pe', '2026-II', 'A', 20, true)
) AS v(curso_nombre, docente_email, periodo_nombre, nombre_seccion, cupo_maximo, activo)
JOIN cursos c ON c.nombre = v.curso_nombre
JOIN docentes d ON d.email = v.docente_email
JOIN periodos_academicos p ON p.nombre = v.periodo_nombre
WHERE NOT EXISTS (
    SELECT 1 FROM secciones_curso sc
    WHERE sc.id_curso = c.id AND sc.id_periodo_academico = p.id AND sc.nombre_seccion = v.nombre_seccion
);

INSERT INTO prerequisitos_curso (id_curso, id_curso_prerequisito)
SELECT c.id, cp.id
FROM (VALUES
    ('Desarrollo de Aplicaciones Distribuidas', 'Ingles Basico'),
    ('Diplomado en Desarrollo Web', 'Desarrollo de Aplicaciones Distribuidas')
) AS v(curso_nombre, prerequisito_nombre)
JOIN cursos c ON c.nombre = v.curso_nombre
JOIN cursos cp ON cp.nombre = v.prerequisito_nombre
WHERE NOT EXISTS (
    SELECT 1 FROM prerequisitos_curso pc
    WHERE pc.id_curso = c.id AND pc.id_curso_prerequisito = cp.id
);

INSERT INTO convalidaciones_curso (id_curso, id_estudiante, institucion_origen, curso_origen, fecha_convalidacion, estado)
SELECT c.id, v.id_estudiante, v.institucion_origen, v.curso_origen, v.fecha_convalidacion, v.estado
FROM (VALUES
    ('Desarrollo de Aplicaciones Distribuidas', '11111111-1111-1111-1111-111111111111', 'Universidad Nacional Mayor de San Marcos', 'Programacion I', DATE '2026-02-10', 'APROBADA'),
    ('Ingles Basico', '22222222-2222-2222-2222-222222222222', 'Instituto Cultural Peruano Norteamericano', 'Ingles Intermedio', DATE '2026-01-20', 'PENDIENTE')
) AS v(curso_nombre, id_estudiante, institucion_origen, curso_origen, fecha_convalidacion, estado)
JOIN cursos c ON c.nombre = v.curso_nombre
WHERE NOT EXISTS (
    SELECT 1 FROM convalidaciones_curso cc
    WHERE cc.id_curso = c.id AND cc.id_estudiante = v.id_estudiante
);
