-- Datos de ejemplo (seed) para catalogos del Portal de Ingresos y Pagos

INSERT INTO conceptos_pago (nombre, descripcion, monto, activo) VALUES
    ('Matricula', 'Derecho de matricula por periodo academico', 350.00, true),
    ('Mensualidad', 'Pension mensual de estudios', 480.00, true),
    ('Constancia de Estudios', 'Emision de constancia de matricula o estudios', 25.00, true),
    ('Carnet Universitario', 'Emision o duplicado de carnet universitario', 30.00, true),
    ('Derecho de Graduacion', 'Tramite administrativo de graduacion', 600.00, true)
ON CONFLICT DO NOTHING;

INSERT INTO metodos_pago (nombre, activo) VALUES
    ('Deposito Bancario', true),
    ('Tarjeta de Credito/Debito', true),
    ('Pasarela de Pago en Linea', true),
    ('Transferencia Interbancaria', true),
    ('Pago en Caja', true)
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO becas (nombre, descripcion, porcentaje_descuento, activo) VALUES
    ('Beca Excelencia Academica', 'Para estudiantes con rendimiento sobresaliente', 50.00, true),
    ('Beca Socioeconomica', 'Apoyo a estudiantes de bajos recursos', 30.00, true),
    ('Beca Deportiva', 'Para estudiantes destacados en deporte', 25.00, true),
    ('Beca Hijo de Docente', 'Beneficio para hijos de personal docente', 40.00, true),
    ('Beca Convenio Institucional', 'Otorgada por convenios con instituciones aliadas', 20.00, true)
ON CONFLICT DO NOTHING;

INSERT INTO tipos_comprobante (codigo, nombre, activo) VALUES
    ('BOL', 'Boleta de Venta', true),
    ('FAC', 'Factura', true),
    ('REC', 'Recibo de Ingreso', true),
    ('NC', 'Nota de Credito', true),
    ('ND', 'Nota de Debito', true)
ON CONFLICT (codigo) DO NOTHING;

INSERT INTO tarifarios_programa (programa, id_concepto_pago, monto, vigente_desde, vigente_hasta, activo)
SELECT v.programa, c.id, v.monto, v.vigente_desde, v.vigente_hasta, true
FROM (VALUES
    ('Ingenieria de Sistemas', 'Matricula', 350.00, DATE '2026-01-01', NULL::date),
    ('Ingenieria de Sistemas', 'Mensualidad', 520.00, DATE '2026-01-01', NULL::date),
    ('Administracion y Negocios Internacionales', 'Matricula', 320.00, DATE '2026-01-01', NULL::date),
    ('Psicologia', 'Mensualidad', 460.00, DATE '2026-01-01', NULL::date),
    ('Contabilidad', 'Mensualidad', 440.00, DATE '2026-01-01', NULL::date)
) AS v(programa, concepto, monto, vigente_desde, vigente_hasta)
JOIN conceptos_pago c ON c.nombre = v.concepto
WHERE NOT EXISTS (
    SELECT 1 FROM tarifarios_programa t
    WHERE t.programa = v.programa AND t.id_concepto_pago = c.id
);
