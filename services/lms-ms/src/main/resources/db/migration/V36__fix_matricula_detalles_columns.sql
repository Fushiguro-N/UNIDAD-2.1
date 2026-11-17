ALTER TABLE matricula_detalles
    DROP COLUMN IF EXISTS id_contenido,
    DROP COLUMN IF EXISTS nombre_contenido,
    DROP COLUMN IF EXISTS cantidad,
    DROP COLUMN IF EXISTS precio_unitario,
    ADD COLUMN IF NOT EXISTS id_seccion_curso BIGINT NOT NULL DEFAULT 0,
    ADD COLUMN IF NOT EXISTS nombre_curso VARCHAR(100),
    ADD COLUMN IF NOT EXISTS nombre_seccion VARCHAR(10);

ALTER TABLE matricula_detalles
    ALTER COLUMN id_seccion_curso DROP DEFAULT;
