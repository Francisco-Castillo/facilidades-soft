/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft;

import java.util.Date;

/**
 *
 * @author fcastillo
 */
public class Frances {

    private double tasa;
    private int plazo;
    private double montoSolicitado;
    private double totalHaberes;
    private double cuotaPura;
    private double saldoDeuda;
    private double interes;
    private double amortizacion;
    private double totalAmortizado;
    private double seguroDeVida;
    private double iva;
    private double cuotaTotal;
    private double gastosOtorgamiento;
    private double primerCuota;
    private Date fechaSolicitud;
    private Date fechaPrimerPago;
    private double tasaDeInteresPeriodica;
    private double go;
    private double sv;
    private double iv;

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(double montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public double getTotalHaberes() {
        return totalHaberes;
    }

    public void setTotalHaberes(double totalHaberes) {
        this.totalHaberes = totalHaberes;
    }

    public double getCuotaPura() {
        return cuotaPura;
    }

    public void setCuotaPura(double cuotaPura) {
        this.cuotaPura = cuotaPura;
    }

    public double getSaldoDeuda() {
        return saldoDeuda;
    }

    public void setSaldoDeuda(double saldoDeuda) {
        this.saldoDeuda = saldoDeuda;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(double amortizacion) {
        this.amortizacion = amortizacion;
    }

    public double getTotalAmortizado() {
        return totalAmortizado;
    }

    public void setTotalAmortizado(double totalAmortizado) {
        this.totalAmortizado = totalAmortizado;
    }

    public double getSeguroDeVida() {
        return seguroDeVida;
    }

    public void setSeguroDeVida(double seguroDeVida) {
        this.seguroDeVida = seguroDeVida;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getCuotaTotal() {
        return cuotaTotal;
    }

    public void setCuotaTotal(double cuotaTotal) {
        this.cuotaTotal = cuotaTotal;
    }

    public double getGastosOtorgamiento() {
        return gastosOtorgamiento;
    }

    public void setGastosOtorgamiento(double gastosOtorgamiento) {
        this.gastosOtorgamiento = gastosOtorgamiento;
    }

    public double getPrimerCuota() {
        return primerCuota;
    }

    public void setPrimerCuota(double primerCuota) {
        this.primerCuota = primerCuota;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaPrimerPago() {
        return fechaPrimerPago;
    }

    public void setFechaPrimerPago(Date fechaPrimerPago) {
        this.fechaPrimerPago = fechaPrimerPago;
    }

    public double getTasaDeInteresPeriodica() {
        return tasaDeInteresPeriodica;
    }

    public void setTasaDeInteresPeriodica(double tasaDeInteresPeriodica) {
        this.tasaDeInteresPeriodica = tasaDeInteresPeriodica;
    }

    public double getGo() {
        return go;
    }

    public void setGo(double go) {
        this.go = go;
    }

    public double getSv() {
        return sv;
    }

    public void setSv(double sv) {
        this.sv = sv;
    }

    public double getIv() {
        return iv;
    }

    public void setIv(double iv) {
        this.iv = iv;
    }

    /**
     * Constructor con parámetros.
     *
     * @param cuotas
     * @param saldo
     * @param inte
     * @param amortiz
     * @param cuotaP
     * @param totalA
     * @param segVida
     * @param iv
     * @param cuotaT
     * @param fecha
     */
    public Frances(int cuotas, double saldo, double inte, double amortiz,
            double cuotaP, double totalA, double segVida, double iv,
            double cuotaT, Date fecha) {
        this.plazo = cuotas;
        this.saldoDeuda = saldo;
        this.interes = inte;
        this.amortizacion = amortiz;
        this.cuotaPura = cuotaP;
        this.totalAmortizado = totalA;
        this.seguroDeVida = segVida;
        this.iva = iv;
        this.cuotaTotal = cuotaT;
        this.fechaSolicitud = fecha;

    }

    public Frances(int pl) {
        this.plazo = pl;

    }

    public Frances() {
    }
    

}
