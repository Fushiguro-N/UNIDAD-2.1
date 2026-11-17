export interface ModuleConfig {
  key: string;
  label: string;
  resources: string[];
  roles?: ('ADMIN' | 'DOCENTE' | 'ESTUDIANTE')[];
  /** When set, the sidebar link routes here directly instead of to the generic module page. */
  route?: string;
}

export const MODULES: ModuleConfig[] = [
  {
    key: 'academico',
    label: 'Académico',
    resources: [
      'cursos',
      'secciones-curso',
      'mallas-curriculares',
      'periodos-academicos',
      'prerequisitos-curso',
      'modalidades-curso',
      'categorias-curso',
      'docentes',
      'convalidaciones-curso',
    ],
  },
  {
    key: 'matriculas-pagos',
    label: 'Matrículas y Pagos',
    resources: ['matriculas', 'listas-espera', 'estados-matricula'],
    roles: ['ADMIN'],
  },
  {
    key: 'aula-evaluacion',
    label: 'Aula y Evaluación',
    resources: [
      'sesiones-clase',
      'horarios-clase',
      'recursos-complementarios',
      'tareas',
      'entregas-tarea',
      'evaluaciones',
      'preguntas-banco',
      'rubricas-evaluacion',
      'foros-hilo',
      'respuestas-foro',
    ],
  },
  {
    key: 'seguimiento-calidad',
    label: 'Seguimiento y Calidad',
    resources: [
      'asistencias',
      'calificaciones',
      'avances-contenido',
      'anuncios-curso',
      'encuestas-satisfaccion',
      'insignias',
      'insignias-estudiante',
      'plantillas-certificado',
    ],
  },
  {
    key: 'inscribirse',
    label: 'Inscribirse',
    resources: [],
    roles: ['ESTUDIANTE'],
    route: '/enrolar',
  },
  {
    key: 'registrar-estudiante',
    label: 'Registrar Estudiante',
    resources: [],
    roles: ['ADMIN'],
    route: '/crear-estudiante',
  },
];
