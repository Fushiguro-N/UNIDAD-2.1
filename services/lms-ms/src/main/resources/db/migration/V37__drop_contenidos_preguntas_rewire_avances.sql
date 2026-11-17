ALTER TABLE avances_contenido DROP CONSTRAINT IF EXISTS avances_contenido_id_contenido_fkey;
ALTER TABLE avances_contenido DROP CONSTRAINT IF EXISTS uk_avances_contenido_contenido_estudiante;

ALTER TABLE avances_contenido RENAME COLUMN id_contenido TO id_recurso;

ALTER TABLE avances_contenido
    ADD CONSTRAINT avances_contenido_id_recurso_fkey FOREIGN KEY (id_recurso) REFERENCES recursos_complementarios(id);

ALTER TABLE avances_contenido
    ADD CONSTRAINT uk_avances_contenido_recurso_estudiante UNIQUE (id_recurso, id_estudiante);

DROP TABLE IF EXISTS preguntas_evaluacion;
DROP TABLE IF EXISTS contenidos;
