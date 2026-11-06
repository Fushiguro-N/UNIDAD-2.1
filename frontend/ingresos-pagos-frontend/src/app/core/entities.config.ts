// AUTO-GENERATED from backend Request DTOs. Edit entities.config.ts manually after generation if needed.
export type FieldType = 'text' | 'textarea' | 'number' | 'boolean' | 'date' | 'datetime-local';

export interface EntityField {
  name: string;
  label: string;
  type: FieldType;
  required: boolean;
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
    "resource": "arqueos-caja",
    "entity": "ArqueoCaja",
    "label": "Arqueo Caja",
    "fields": [
      {
        "name": "sesionCajaId",
        "label": "Sesion Caja Id",
        "type": "number",
        "required": true
      },
      {
        "name": "montoContado",
        "label": "Monto Contado",
        "type": "number",
        "required": true
      },
      {
        "name": "montoSistema",
        "label": "Monto Sistema",
        "type": "number",
        "required": true
      },
      {
        "name": "observaciones",
        "label": "Observaciones",
        "type": "textarea",
        "required": false
      }
    ]
  },
  {
    "resource": "becas",
    "entity": "Beca",
    "label": "Beca",
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
        "name": "porcentajeDescuento",
        "label": "Porcentaje Descuento",
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
    "resource": "becas-asignadas",
    "entity": "BecaAsignada",
    "label": "Beca Asignada",
    "fields": [
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
      {
        "name": "becaId",
        "label": "Beca Id",
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
    "resource": "bitacoras-auditoria-pago",
    "entity": "BitacoraAuditoriaPago",
    "label": "Bitacora Auditoria Pago",
    "fields": [
      {
        "name": "pagoId",
        "label": "Pago Id",
        "type": "number",
        "required": true
      },
      {
        "name": "accion",
        "label": "Accion",
        "type": "text",
        "required": true
      },
      {
        "name": "idUsuario",
        "label": "Id Usuario",
        "type": "text",
        "required": true
      },
      {
        "name": "observaciones",
        "label": "Observaciones",
        "type": "textarea",
        "required": false
      }
    ]
  },
  {
    "resource": "cajas",
    "entity": "Caja",
    "label": "Caja",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "ubicacion",
        "label": "Ubicacion",
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
    "resource": "conceptos-pago",
    "entity": "ConceptoPago",
    "label": "Concepto Pago",
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
        "name": "monto",
        "label": "Monto",
        "type": "number",
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
    "resource": "convenios-institucionales",
    "entity": "ConvenioInstitucional",
    "label": "Convenio Institucional",
    "fields": [
      {
        "name": "nombreEmpresa",
        "label": "Nombre Empresa",
        "type": "text",
        "required": true
      },
      {
        "name": "ruc",
        "label": "Ruc",
        "type": "text",
        "required": true
      },
      {
        "name": "porcentajeDescuento",
        "label": "Porcentaje Descuento",
        "type": "number",
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
    "resource": "entidades-financieras",
    "entity": "EntidadFinanciera",
    "label": "Entidad Financiera",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "codigo",
        "label": "Codigo",
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
    "resource": "financiamientos-educativos",
    "entity": "FinanciamientoEducativo",
    "label": "Financiamiento Educativo",
    "fields": [
      {
        "name": "entidadFinancieraId",
        "label": "Entidad Financiera Id",
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
        "name": "montoFinanciado",
        "label": "Monto Financiado",
        "type": "number",
        "required": true
      },
      {
        "name": "numeroCuotas",
        "label": "Numero Cuotas",
        "type": "number",
        "required": true
      },
      {
        "name": "fechaInicio",
        "label": "Fecha Inicio",
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
    "resource": "logs-transaccion-pasarela",
    "entity": "LogTransaccionPasarela",
    "label": "Log Transaccion Pasarela",
    "fields": [
      {
        "name": "pagoId",
        "label": "Pago Id",
        "type": "number",
        "required": true
      },
      {
        "name": "proveedor",
        "label": "Proveedor",
        "type": "text",
        "required": true
      },
      {
        "name": "referenciaExterna",
        "label": "Referencia Externa",
        "type": "text",
        "required": true
      },
      {
        "name": "estado",
        "label": "Estado",
        "type": "text",
        "required": true
      },
      {
        "name": "payloadRespuesta",
        "label": "Payload Respuesta",
        "type": "textarea",
        "required": false
      }
    ]
  },
  {
    "resource": "metodos-pago",
    "entity": "MetodoPago",
    "label": "Metodo Pago",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
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
    "resource": "notas-credito-debito",
    "entity": "NotaCreditoDebito",
    "label": "Nota Credito Debito",
    "fields": [
      {
        "name": "pagoId",
        "label": "Pago Id",
        "type": "number",
        "required": true
      },
      {
        "name": "tipo",
        "label": "Tipo",
        "type": "text",
        "required": true
      },
      {
        "name": "motivo",
        "label": "Motivo",
        "type": "textarea",
        "required": true
      },
      {
        "name": "monto",
        "label": "Monto",
        "type": "number",
        "required": true
      }
    ]
  },
  {
    "resource": "pagadores-terceros",
    "entity": "PagadorTercero",
    "label": "Pagador Tercero",
    "fields": [
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true
      },
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
        "name": "tipoDocumento",
        "label": "Tipo Documento",
        "type": "text",
        "required": true
      },
      {
        "name": "numeroDocumento",
        "label": "Numero Documento",
        "type": "text",
        "required": true
      },
      {
        "name": "telefono",
        "label": "Telefono",
        "type": "text",
        "required": false
      },
      {
        "name": "email",
        "label": "Email",
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
    "resource": "plantillas-notificacion",
    "entity": "PlantillaNotificacion",
    "label": "Plantilla Notificacion",
    "fields": [
      {
        "name": "codigo",
        "label": "Codigo",
        "type": "text",
        "required": true
      },
      {
        "name": "asunto",
        "label": "Asunto",
        "type": "text",
        "required": true
      },
      {
        "name": "cuerpo",
        "label": "Cuerpo",
        "type": "textarea",
        "required": true
      },
      {
        "name": "canal",
        "label": "Canal",
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
    "resource": "recargos-mora",
    "entity": "RecargoMora",
    "label": "Recargo Mora",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true
      },
      {
        "name": "tipoCalculo",
        "label": "Tipo Calculo",
        "type": "text",
        "required": true
      },
      {
        "name": "valor",
        "label": "Valor",
        "type": "number",
        "required": true
      },
      {
        "name": "diasGracia",
        "label": "Dias Gracia",
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
    "resource": "series-comprobante",
    "entity": "SerieComprobante",
    "label": "Serie Comprobante",
    "fields": [
      {
        "name": "tipoComprobanteId",
        "label": "Tipo Comprobante Id",
        "type": "number",
        "required": true
      },
      {
        "name": "serie",
        "label": "Serie",
        "type": "text",
        "required": true
      },
      {
        "name": "correlativoActual",
        "label": "Correlativo Actual",
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
    "resource": "sesiones-caja",
    "entity": "SesionCaja",
    "label": "Sesion Caja",
    "fields": [
      {
        "name": "cajaId",
        "label": "Caja Id",
        "type": "number",
        "required": true
      },
      {
        "name": "idCajero",
        "label": "Id Cajero",
        "type": "text",
        "required": true
      },
      {
        "name": "fechaApertura",
        "label": "Fecha Apertura",
        "type": "datetime-local",
        "required": true
      },
      {
        "name": "fechaCierre",
        "label": "Fecha Cierre",
        "type": "datetime-local",
        "required": false
      },
      {
        "name": "montoInicial",
        "label": "Monto Inicial",
        "type": "number",
        "required": true
      },
      {
        "name": "montoFinal",
        "label": "Monto Final",
        "type": "number",
        "required": false
      }
    ]
  },
  {
    "resource": "tarifarios-programa",
    "entity": "TarifarioPrograma",
    "label": "Tarifario Programa",
    "fields": [
      {
        "name": "programa",
        "label": "Programa",
        "type": "text",
        "required": true
      },
      {
        "name": "conceptoPagoId",
        "label": "Concepto Pago Id",
        "type": "number",
        "required": true
      },
      {
        "name": "monto",
        "label": "Monto",
        "type": "number",
        "required": true
      },
      {
        "name": "vigenteDesde",
        "label": "Vigente Desde",
        "type": "date",
        "required": true
      },
      {
        "name": "vigenteHasta",
        "label": "Vigente Hasta",
        "type": "date",
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
    "resource": "tipos-cambio",
    "entity": "TipoCambio",
    "label": "Tipo Cambio",
    "fields": [
      {
        "name": "fecha",
        "label": "Fecha",
        "type": "date",
        "required": true
      },
      {
        "name": "monedaOrigen",
        "label": "Moneda Origen",
        "type": "text",
        "required": true
      },
      {
        "name": "monedaDestino",
        "label": "Moneda Destino",
        "type": "text",
        "required": true
      },
      {
        "name": "valor",
        "label": "Valor",
        "type": "number",
        "required": true
      }
    ]
  },
  {
    "resource": "tipos-comprobante",
    "entity": "TipoComprobante",
    "label": "Tipo Comprobante",
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
        "name": "activo",
        "label": "Activo",
        "type": "boolean",
        "required": true
      }
    ]
  },
  {
    "resource": "pagos",
    "entity": "Pago",
    "label": "Pago",
    "fields": [
      { "name": "matriculaId", "label": "Matricula Id", "type": "number", "required": false },
      { "name": "monto", "label": "Monto", "type": "number", "required": false },
      { "name": "estado", "label": "Estado", "type": "text", "required": false },
      { "name": "fechaPago", "label": "Fecha Pago", "type": "datetime-local", "required": false }
    ],
    "readOnly": true,
    "noDelete": true
  }
];
