// AUTO-GENERATED from backend Request DTOs. Edit entities.config.ts manually after generation if needed.
export type FieldType = 'text' | 'textarea' | 'number' | 'boolean' | 'date' | 'datetime-local';

export interface EntityField {
  name: string;
  label: string;
  type: FieldType;
  required: boolean;
  optionsResource?: string;
  optionsLabel?: string;
}

export interface EntityConfig {
  resource: string;
  entity: string;
  label: string;
  fields: EntityField[];
  readOnly?: boolean;
  noDelete?: boolean;
}

export const ENTITIES: EntityConfig[] = [
  {
    "resource": "anuncios-curso",
    "entity": "AnuncioCurso",
    "label": "Anuncio Curso",
    "fields": [
      {
        "name": "cursoId",
        "label": "Curso",
        "type": "number",
        "required": true,
        "optionsResource": "cursos",
        "optionsLabel": "nombre"
      },
      {
        "name": "titulo",
        "label": "Titulo",
        "type": "text",
        "required": true
      },
      {
        "name": "mensaje",
        "label": "Mensaje",
        "type": "textarea",
        "required": true
      }
    ]
  },
  {
    "resource": "asistencias",
    "entity": "Asistencia",
    "label": "Asistencia",
    "fields": [
      {
        "name": "sesionClaseId",
        "label": "Sesion Clase Id",
        "type": "number",
        "required": true
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
      {
        "name": "presente",
        "label": "Presente",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "avances-contenido",
    "entity": "AvanceContenido",
    "label": "Avance Contenido",
    "fields": [
      {
        "name": "contenidoId",
        "label": "Contenido Id",
        "type": "number",
        "required": true
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
      {
        "name": "consumido",
        "label": "Consumido",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "calificaciones",
    "entity": "Calificacion",
    "label": "Calificacion",
    "fields": [
      {
        "name": "evaluacionId",
        "label": "Evaluacion Id",
        "type": "number",
        "required": true
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
      {
        "name": "nota",
        "label": "Nota",
        "type": "number",
        "required": true
      }
    ]
  },
  {
    "resource": "categorias-curso",
    "entity": "CategoriaCurso",
    "label": "Categoria Curso",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "contenidos",
    "entity": "Contenido",
    "label": "Contenido",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false
      },
      {
        "name": "precio",
        "label": "Precio",
        "type": "number",
        "required": true
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      },
      {
        "name": "stock",
        "label": "Stock",
        "type": "number",
        "required": true
      },
      {
        "name": "cursoId",
        "label": "Curso Id",
        "type": "number",
        "required": true
      }
    ]
  },
  {
    "resource": "convalidaciones-curso",
    "entity": "ConvalidacionCurso",
    "label": "Convalidacion Curso",
    "fields": [
      {
        "name": "cursoId",
        "label": "Curso",
        "type": "number",
        "required": true,
        "optionsResource": "cursos",
        "optionsLabel": "nombre"
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
      {
        "name": "institucionOrigen",
        "label": "Institucion Origen",
        "type": "text",
        "required": true
      },
      {
        "name": "cursoOrigen",
        "label": "Curso Origen",
        "type": "text",
        "required": true
      },
      {
        "name": "fechaConvalidacion",
        "label": "Fecha Convalidacion",
        "type": "date",
        "required": true
      }
    ]
  },
  {
    "resource": "cursos",
    "entity": "Curso",
    "label": "Curso",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false
      }
    ]
  },
  {
    "resource": "docentes",
    "entity": "Docente",
    "label": "Docente",
    "fields": [
      {
        "name": "nombres",
        "label": "Nombres",
        "type": "text",
        "required": true
      },
      {
        "name": "apellidos",
        "label": "Apellidos",
        "type": "text",
        "required": true
      },
      {
        "name": "email",
        "label": "Email",
        "type": "text",
        "required": true
      },
      {
        "name": "especialidad",
        "label": "Especialidad",
        "type": "text",
        "required": false
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "encuestas-satisfaccion",
    "entity": "EncuestaSatisfaccion",
    "label": "Encuesta Satisfaccion",
    "fields": [
      {
        "name": "cursoId",
        "label": "Curso",
        "type": "number",
        "required": true,
        "optionsResource": "cursos",
        "optionsLabel": "nombre"
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
      {
        "name": "calificacion",
        "label": "Calificacion",
        "type": "number",
        "required": true
      },
      {
        "name": "comentario",
        "label": "Comentario",
        "type": "textarea",
        "required": false
      }
    ]
  },
  {
    "resource": "entregas-tarea",
    "entity": "EntregaTarea",
    "label": "Entrega Tarea",
    "fields": [
      {
        "name": "tareaId",
        "label": "Tarea Id",
        "type": "number",
        "required": true
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
      {
        "name": "urlArchivo",
        "label": "Url Archivo",
        "type": "textarea",
        "required": false
      },
      {
        "name": "nota",
        "label": "Nota",
        "type": "number",
        "required": false
      }
    ]
  },
  {
    "resource": "estados-matricula",
    "entity": "EstadoMatriculaCatalogo",
    "label": "Estado Matricula Catalogo",
    "fields": [
      {
        "name": "codigo",
        "label": "Codigo",
        "type": "text",
        "required": true
      },
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "evaluaciones",
    "entity": "Evaluacion",
    "label": "Evaluacion",
    "fields": [
      {
        "name": "cursoId",
        "label": "Curso",
        "type": "number",
        "required": true,
        "optionsResource": "cursos",
        "optionsLabel": "nombre"
      },
      {
        "name": "titulo",
        "label": "Titulo",
        "type": "text",
        "required": true
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false
      },
      {
        "name": "fechaEvaluacion",
        "label": "Fecha Evaluacion",
        "type": "datetime-local",
        "required": false
      },
      {
        "name": "puntajeMaximo",
        "label": "Puntaje Maximo",
        "type": "text",
        "required": true
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "foros-hilo",
    "entity": "ForoHilo",
    "label": "Foro Hilo",
    "fields": [
      {
        "name": "cursoId",
        "label": "Curso",
        "type": "number",
        "required": true,
        "optionsResource": "cursos",
        "optionsLabel": "nombre"
      },
      {
        "name": "titulo",
        "label": "Titulo",
        "type": "text",
        "required": true
      },
      {
        "name": "idEstudianteCreador",
        "label": "Id Estudiante Creador",
        "type": "text",
        "required": true
      }
    ]
  },
  {
    "resource": "horarios-clase",
    "entity": "HorarioClase",
    "label": "Horario Clase",
    "fields": [
      {
        "name": "seccionCursoId",
        "label": "Seccion Curso Id",
        "type": "number",
        "required": true
      },
      {
        "name": "diaSemana",
        "label": "Dia Semana",
        "type": "text",
        "required": true
      },
      {
        "name": "horaInicio",
        "label": "Hora Inicio",
        "type": "text",
        "required": true
      },
      {
        "name": "horaFin",
        "label": "Hora Fin",
        "type": "text",
        "required": true
      }
    ]
  },
  {
    "resource": "insignias",
    "entity": "Insignia",
    "label": "Insignia",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false
      },
      {
        "name": "criterio",
        "label": "Criterio",
        "type": "textarea",
        "required": false
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "insignias-estudiante",
    "entity": "InsigniaEstudiante",
    "label": "Insignia Estudiante",
    "fields": [
      {
        "name": "insigniaId",
        "label": "Insignia Id",
        "type": "number",
        "required": true
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      }
    ]
  },
  {
    "resource": "listas-espera",
    "entity": "ListaEspera",
    "label": "Lista Espera",
    "fields": [
      {
        "name": "seccionCursoId",
        "label": "Seccion Curso Id",
        "type": "number",
        "required": true
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
      {
        "name": "posicion",
        "label": "Posicion",
        "type": "number",
        "required": true
      }
    ]
  },
  {
    "resource": "mallas-curriculares",
    "entity": "MallaCurricular",
    "label": "Malla Curricular",
    "fields": [
      {
        "name": "programa",
        "label": "Programa",
        "type": "text",
        "required": true
      },
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "version",
        "label": "Version",
        "type": "text",
        "required": true
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "matriculas",
    "entity": "Matricula",
    "label": "Matricula",
    "fields": [
      {
        "name": "periodoAcademico",
        "label": "Periodo Academico",
        "type": "text",
        "required": false
      },
      {
        "name": "metodoPago",
        "label": "Metodo Pago",
        "type": "text",
        "required": true
      },
      {
        "name": "detalles",
        "label": "Detalles",
        "type": "text",
        "required": false
      }
    ],
    "readOnly": true,
    "noDelete": true
  },
  {
    "resource": "modalidades-curso",
    "entity": "ModalidadCurso",
    "label": "Modalidad Curso",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "periodos-academicos",
    "entity": "PeriodoAcademico",
    "label": "Periodo Academico",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "fechaInicio",
        "label": "Fecha Inicio",
        "type": "date",
        "required": true
      },
      {
        "name": "fechaFin",
        "label": "Fecha Fin",
        "type": "date",
        "required": true
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "plantillas-certificado",
    "entity": "PlantillaCertificado",
    "label": "Plantilla Certificado",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "contenidoPlantilla",
        "label": "Contenido Plantilla",
        "type": "textarea",
        "required": true
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "preguntas",
    "entity": "Pregunta",
    "label": "Pregunta",
    "fields": [
      {
        "name": "evaluacionId",
        "label": "Evaluacion Id",
        "type": "number",
        "required": true
      },
      {
        "name": "enunciado",
        "label": "Enunciado",
        "type": "textarea",
        "required": true
      },
      {
        "name": "tipo",
        "label": "Tipo",
        "type": "text",
        "required": true
      },
      {
        "name": "puntaje",
        "label": "Puntaje",
        "type": "number",
        "required": true
      }
    ]
  },
  {
    "resource": "preguntas-banco",
    "entity": "PreguntaBanco",
    "label": "Pregunta Banco",
    "fields": [
      {
        "name": "enunciado",
        "label": "Enunciado",
        "type": "textarea",
        "required": true
      },
      {
        "name": "tipo",
        "label": "Tipo",
        "type": "text",
        "required": true
      },
      {
        "name": "puntajeSugerido",
        "label": "Puntaje Sugerido",
        "type": "number",
        "required": true
      },
      {
        "name": "categoria",
        "label": "Categoria",
        "type": "text",
        "required": false
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "prerequisitos-curso",
    "entity": "PrerequisitoCurso",
    "label": "Prerequisito Curso",
    "fields": [
      {
        "name": "cursoId",
        "label": "Curso",
        "type": "number",
        "required": true,
        "optionsResource": "cursos",
        "optionsLabel": "nombre"
      },
      {
        "name": "cursoPrerequisitoId",
        "label": "Curso Prerequisito Id",
        "type": "number",
        "required": true
      }
    ]
  },
  {
    "resource": "recursos-complementarios",
    "entity": "RecursoComplementario",
    "label": "Recurso Complementario",
    "fields": [
      {
        "name": "cursoId",
        "label": "Curso",
        "type": "number",
        "required": true,
        "optionsResource": "cursos",
        "optionsLabel": "nombre"
      },
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "tipo",
        "label": "Tipo",
        "type": "text",
        "required": true
      },
      {
        "name": "url",
        "label": "Url",
        "type": "textarea",
        "required": true
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "respuestas-foro",
    "entity": "RespuestaForo",
    "label": "Respuesta Foro",
    "fields": [
      {
        "name": "foroHiloId",
        "label": "Foro Hilo Id",
        "type": "number",
        "required": true
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
      {
        "name": "mensaje",
        "label": "Mensaje",
        "type": "textarea",
        "required": true
      }
    ]
  },
  {
    "resource": "rubricas-evaluacion",
    "entity": "RubricaEvaluacion",
    "label": "Rubrica Evaluacion",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false
      },
      {
        "name": "puntajeMaximo",
        "label": "Puntaje Maximo",
        "type": "number",
        "required": true
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "secciones-curso",
    "entity": "SeccionCurso",
    "label": "Seccion Curso",
    "fields": [
      {
        "name": "cursoId",
        "label": "Curso",
        "type": "number",
        "required": true,
        "optionsResource": "cursos",
        "optionsLabel": "nombre"
      },
      {
        "name": "docenteId",
        "label": "Docente Id",
        "type": "number",
        "required": true
      },
      {
        "name": "periodoAcademicoId",
        "label": "Periodo Academico Id",
        "type": "number",
        "required": true
      },
      {
        "name": "nombreSeccion",
        "label": "Nombre Seccion",
        "type": "text",
        "required": true
      },
      {
        "name": "cupoMaximo",
        "label": "Cupo Maximo",
        "type": "number",
        "required": true
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "sesiones-clase",
    "entity": "SesionClase",
    "label": "Sesion Clase",
    "fields": [
      {
        "name": "seccionCursoId",
        "label": "Seccion Curso Id",
        "type": "number",
        "required": true
      },
      {
        "name": "fecha",
        "label": "Fecha",
        "type": "date",
        "required": true
      },
      {
        "name": "horaInicio",
        "label": "Hora Inicio",
        "type": "text",
        "required": true
      },
      {
        "name": "horaFin",
        "label": "Hora Fin",
        "type": "text",
        "required": true
      },
      {
        "name": "enlaceVirtual",
        "label": "Enlace Virtual",
        "type": "textarea",
        "required": false
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "tareas",
    "entity": "Tarea",
    "label": "Tarea",
    "fields": [
      {
        "name": "cursoId",
        "label": "Curso",
        "type": "number",
        "required": true,
        "optionsResource": "cursos",
        "optionsLabel": "nombre"
      },
      {
        "name": "titulo",
        "label": "Titulo",
        "type": "text",
        "required": true
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false
      },
      {
        "name": "fechaLimite",
        "label": "Fecha Limite",
        "type": "datetime-local",
        "required": true
      },
      {
        "name": "puntajeMaximo",
        "label": "Puntaje Maximo",
        "type": "number",
        "required": true
      },
      {
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  }
];
