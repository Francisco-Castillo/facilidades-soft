package com.fcastillo.facilidades.soft.utilidades;

/**
 *
 * @author fcastillo
 */
public class Constantes {

    public static final Integer MASCULINO = 1;
    public static final Integer ESTADO_ACTIVO = 1;
    public static final Integer FEMENINO = 2;
    /* Representa el 20 por ciento, podriamos expresarlo como 0,2*/
    public static final double TASA_NOMINAL_ANUAL = 10;
    public static final int CIEN = 100;
    public static final int DIAS_DEL_MES = 30;
    public static final int DIAS_DEL_ANIO = 365;
    /* Representa el 3% */
    public static final double GASTOS_OTORGAMIENTO = 0.03;
    /* Representa el 2% */
    public static final double SEGURO_DE_VIDA = 0.02;
    public static final double IVA = 0.21;
    public static final double TASA_DE_INTERES_PERIODICA = ((TASA_NOMINAL_ANUAL / CIEN)
            * DIAS_DEL_MES) / DIAS_DEL_ANIO;
    public static final int PENDIENTE_DE_PAGO = 0;
    public static final int CANTIDAD_MESES = 12;
}
