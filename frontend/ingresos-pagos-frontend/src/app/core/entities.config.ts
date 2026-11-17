// AUTO-GENERATED from backend Request DTOs. Edit entities.config.ts manually after generation if needed.
export type FieldType = 'text' | 'textarea' | 'number' | 'boolean' | 'date' | 'datetime-local';

export interface EntityField {
  name: string;
  label: string;
  type: FieldType;
  required: boolean;
  placeholder?: string;
}

export type MenuGroup =
  | 'Caja y Transacciones'
  | 'Configuracion Financiera'
  | 'Convenios y Becas'
  | 'Auditoria y Sistema';

export interface EntityConfig {
  resource: string;
  entity: string;
  label: string;
  fields: EntityField[];
  readOnly?: boolean;
  noDelete?: boolean;
  /** Admin-only menu section this entity belongs to. Not shown to Estudiante/Docente. */
  group: MenuGroup;
}

export const ENTITIES: EntityConfig[] = [
  {
    "resource": "arqueos-caja",
    "entity": "ArqueoCaja",
    "label": "Arqueo Caja",
    "group": "Caja y Transacciones",
    "fields": [
      {
        "name": "sesionCajaId",
        "label": "Sesion Caja Id",
        "type": "number",
        "required": true,
        "placeholder": "1"
      },
      {
        "name": "montoContado",
        "label": "Monto Contado",
        "type": "number",
        "required": true,
        "placeholder": "150.00"
      },
      {
        "name": "montoSistema",
        "label": "Monto Sistema",
        "type": "number",
        "required": true,
        "placeholder": "150.00"
      },
      {
        "name": "observaciones",
        "label": "Observaciones",
        "type": "textarea",
        "required": false,
        "placeholder": "Diferencia por redondeo de caja"
      }
    ]
  },
  {
    "resource": "becas",
    "entity": "Beca",
    "label": "Beca",
    "group": "Convenios y Becas",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true,
        "placeholder": "Beca Excelencia Academica"
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false,
        "placeholder": "Para estudiantes con rendimiento sobresaliente"
      },
      {
        "name": "porcentajeDescuento",
        "label": "Porcentaje Descuento",
        "type": "number",
        "required": true,
        "placeholder": "50"
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
    "group": "Convenios y Becas",
    "fields": [
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true,
        "placeholder": "U2023001"
      },
      {
        "name": "becaId",
        "label": "Beca Id",
        "type": "number",
        "required": true,
        "placeholder": "1"
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
    "group": "Auditoria y Sistema",
    "fields": [
      {
        "name": "pagoId",
        "label": "Pago Id",
        "type": "number",
        "required": true,
        "placeholder": "1"
      },
      {
        "name": "accion",
        "label": "Accion",
        "type": "text",
        "required": true,
        "placeholder": "PAGO_REGISTRADO"
      },
      {
        "name": "idUsuario",
        "label": "Id Usuario",
        "type": "text",
        "required": true,
        "placeholder": "admin"
      },
      {
        "name": "observaciones",
        "label": "Observaciones",
        "type": "textarea",
        "required": false,
        "placeholder": "Pago validado por caja"
      }
    ]
  },
  {
    "resource": "cajas",
    "entity": "Caja",
    "label": "Caja",
    "group": "Caja y Transacciones",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true,
        "placeholder": "Caja Principal"
      },
      {
        "name": "ubicacion",
        "label": "Ubicacion",
        "type": "text",
        "required": false,
        "placeholder": "Campus Lima - Modulo A"
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
    "group": "Configuracion Financiera",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true,
        "placeholder": "Matricula"
      },
      {
        "name": "descripcion",
        "label": "Descripcion",
        "type": "textarea",
        "required": false,
        "placeholder": "Derecho de matricula por periodo academico"
      },
      {
        "name": "monto",
        "label": "Monto",
        "type": "number",
        "required": false,
        "placeholder": "350.00"
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
    "group": "Convenios y Becas",
    "fields": [
      {
        "name": "nombreEmpresa",
        "label": "Nombre Empresa",
        "type": "text",
        "required": true,
        "placeholder": "Empresa ABC S.A.C."
      },
      {
        "name": "ruc",
        "label": "Ruc",
        "type": "text",
        "required": true,
        "placeholder": "20123456789"
      },
      {
        "name": "porcentajeDescuento",
        "label": "Porcentaje Descuento",
        "type": "number",
        "required": true,
        "placeholder": "15"
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
    "group": "Convenios y Becas",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true,
        "placeholder": "Banco de la Nacion"
      },
      {
        "name": "codigo",
        "label": "Codigo",
        "type": "text",
        "required": true,
        "placeholder": "BN"
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
    "group": "Convenios y Becas",
    "fields": [
      {
        "name": "entidadFinancieraId",
        "label": "Entidad Financiera Id",
        "type": "number",
        "required": true,
        "placeholder": "1"
      },
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true,
        "placeholder": "U2023001"
      },
      {
        "name": "montoFinanciado",
        "label": "Monto Financiado",
        "type": "number",
        "required": true,
        "placeholder": "2000.00"
      },
      {
        "name": "numeroCuotas",
        "label": "Numero Cuotas",
        "type": "number",
        "required": true,
        "placeholder": "12"
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
    "group": "Auditoria y Sistema",
    "fields": [
      {
        "name": "pagoId",
        "label": "Pago Id",
        "type": "number",
        "required": true,
        "placeholder": "1"
      },
      {
        "name": "proveedor",
        "label": "Proveedor",
        "type": "text",
        "required": true,
        "placeholder": "Niubiz"
      },
      {
        "name": "referenciaExterna",
        "label": "Referencia Externa",
        "type": "text",
        "required": true,
        "placeholder": "TXN-0001A"
      },
      {
        "name": "estado",
        "label": "Estado",
        "type": "text",
        "required": true,
        "placeholder": "APROBADO"
      },
      {
        "name": "payloadRespuesta",
        "label": "Payload Respuesta",
        "type": "textarea",
        "required": false,
        "placeholder": "{\"status\":\"success\"}"
      }
    ]
  },
  {
    "resource": "metodos-pago",
    "entity": "MetodoPago",
    "label": "Metodo Pago",
    "group": "Configuracion Financiera",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true,
        "placeholder": "Deposito Bancario"
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
    "group": "Configuracion Financiera",
    "fields": [
      {
        "name": "pagoId",
        "label": "Pago Id",
        "type": "number",
        "required": true,
        "placeholder": "1"
      },
      {
        "name": "tipo",
        "label": "Tipo",
        "type": "text",
        "required": true,
        "placeholder": "CREDITO"
      },
      {
        "name": "motivo",
        "label": "Motivo",
        "type": "textarea",
        "required": true,
        "placeholder": "Anulacion de pago duplicado"
      },
      {
        "name": "monto",
        "label": "Monto",
        "type": "number",
        "required": true,
        "placeholder": "100.00"
      }
    ]
  },
  {
    "resource": "pagadores-terceros",
    "entity": "PagadorTercero",
    "label": "Pagador Tercero",
    "group": "Convenios y Becas",
    "fields": [
      {
        "name": "idEstudiante",
        "label": "Id Estudiante",
        "type": "text",
        "required": true,
        "placeholder": "U2023001"
      },
      {
        "name": "nombres",
        "label": "Nombres",
        "type": "text",
        "required": true,
        "placeholder": "Maria"
      },
      {
        "name": "apellidos",
        "label": "Apellidos",
        "type": "text",
        "required": true,
        "placeholder": "Lopez Quispe"
      },
      {
        "name": "tipoDocumento",
        "label": "Tipo Documento",
        "type": "text",
        "required": true,
        "placeholder": "DNI"
      },
      {
        "name": "numeroDocumento",
        "label": "Numero Documento",
        "type": "text",
        "required": true,
        "placeholder": "45678912"
      },
      {
        "name": "telefono",
        "label": "Telefono",
        "type": "text",
        "required": false,
        "placeholder": "987654321"
      },
      {
        "name": "email",
        "label": "Email",
        "type": "text",
        "required": false,
        "placeholder": "maria.lopez@example.com"
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
    "group": "Auditoria y Sistema",
    "fields": [
      {
        "name": "codigo",
        "label": "Codigo",
        "type": "text",
        "required": true,
        "placeholder": "PAGO_CONFIRMADO"
      },
      {
        "name": "asunto",
        "label": "Asunto",
        "type": "text",
        "required": true,
        "placeholder": "Confirmacion de pago recibido"
      },
      {
        "name": "cuerpo",
        "label": "Cuerpo",
        "type": "textarea",
        "required": true,
        "placeholder": "Estimado estudiante, su pago ha sido registrado correctamente."
      },
      {
        "name": "canal",
        "label": "Canal",
        "type": "text",
        "required": true,
        "placeholder": "EMAIL"
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
    "group": "Configuracion Financiera",
    "fields": [
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true,
        "placeholder": "Mora Estandar"
      },
      {
        "name": "tipoCalculo",
        "label": "Tipo Calculo",
        "type": "text",
        "required": true,
        "placeholder": "PORCENTAJE"
      },
      {
        "name": "valor",
        "label": "Valor",
        "type": "number",
        "required": true,
        "placeholder": "5"
      },
      {
        "name": "diasGracia",
        "label": "Dias Gracia",
        "type": "number",
        "required": true,
        "placeholder": "3"
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
    "group": "Configuracion Financiera",
    "fields": [
      {
        "name": "tipoComprobanteId",
        "label": "Tipo Comprobante Id",
        "type": "number",
        "required": true,
        "placeholder": "1"
      },
      {
        "name": "serie",
        "label": "Serie",
        "type": "text",
        "required": true,
        "placeholder": "B001"
      },
      {
        "name": "correlativoActual",
        "label": "Correlativo Actual",
        "type": "number",
        "required": true,
        "placeholder": "1"
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
    "group": "Caja y Transacciones",
    "fields": [
      {
        "name": "cajaId",
        "label": "Caja Id",
        "type": "number",
        "required": true,
        "placeholder": "1"
      },
      {
        "name": "idCajero",
        "label": "Id Cajero",
        "type": "text",
        "required": true,
        "placeholder": "admin"
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
        "required": true,
        "placeholder": "100.00"
      },
      {
        "name": "montoFinal",
        "label": "Monto Final",
        "type": "number",
        "required": false,
        "placeholder": "350.00"
      }
    ]
  },
  {
    "resource": "tarifarios-programa",
    "entity": "TarifarioPrograma",
    "label": "Tarifario Programa",
    "group": "Configuracion Financiera",
    "fields": [
      {
        "name": "programa",
        "label": "Programa",
        "type": "text",
        "required": true,
        "placeholder": "Ingenieria de Sistemas"
      },
      {
        "name": "conceptoPagoId",
        "label": "Concepto Pago Id",
        "type": "number",
        "required": true,
        "placeholder": "1"
      },
      {
        "name": "monto",
        "label": "Monto",
        "type": "number",
        "required": true,
        "placeholder": "350.00"
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
    "group": "Configuracion Financiera",
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
        "required": true,
        "placeholder": "USD"
      },
      {
        "name": "monedaDestino",
        "label": "Moneda Destino",
        "type": "text",
        "required": true,
        "placeholder": "PEN"
      },
      {
        "name": "valor",
        "label": "Valor",
        "type": "number",
        "required": true,
        "placeholder": "3.75"
      }
    ]
  },
  {
    "resource": "tipos-comprobante",
    "entity": "TipoComprobante",
    "label": "Tipo Comprobante",
    "group": "Configuracion Financiera",
    "fields": [
      {
        "name": "codigo",
        "label": "Codigo",
        "type": "text",
        "required": true,
        "placeholder": "BOL"
      },
      {
        "name": "nombre",
        "label": "Nombre",
        "type": "text",
        "required": true,
        "placeholder": "Boleta de Venta"
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
    "group": "Caja y Transacciones",
    "fields": [
      { "name": "matriculaId", "label": "Matricula Id", "type": "number", "required": false, "placeholder": "1" },
      { "name": "monto", "label": "Monto", "type": "number", "required": false, "placeholder": "350.00" },
      { "name": "estado", "label": "Estado", "type": "text", "required": false, "placeholder": "PENDIENTE" },
      { "name": "fechaPago", "label": "Fecha Pago", "type": "datetime-local", "required": false }
    ],
    "readOnly": true,
    "noDelete": true
  }
];
