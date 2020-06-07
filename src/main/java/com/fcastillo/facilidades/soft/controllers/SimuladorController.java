package com.fcastillo.facilidades.soft.controllers;

//<editor-fold defaultstate="collpased" desc="imports">
import com.fcastillo.facilidades.soft.Clientes;
import com.fcastillo.facilidades.soft.Cuotas;
import com.fcastillo.facilidades.soft.Frances;
import com.fcastillo.facilidades.soft.Personas;
import com.fcastillo.facilidades.soft.Prestamos;
import com.fcastillo.facilidades.soft.Usuarios;
import com.fcastillo.facilidades.soft.ejb.CuotasFacadeLocal;
import com.fcastillo.facilidades.soft.ejb.PrestamosFacadeLocal;
import com.fcastillo.facilidades.soft.seguridad.LoginController;
import com.fcastillo.facilidades.soft.utilidades.Constantes;
import com.fcastillo.facilidades.soft.utilidades.Mensajes;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
//</editor-fold>

/**
 *
 * @author fcastillo
 */
@Named(value = "simuladorController")
@ViewScoped
public class SimuladorController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="fields">
    @EJB
    PrestamosFacadeLocal prestamoEJB;
    @EJB
    CuotasFacadeLocal cuotasEJB;
    @Inject
    LoginController login;

    private int plazo;
    private Date fechaPrimerPago;
    private double montoSolicitado;
    private double totalHaberes;
    private double tna;
    private double iva;
    private double gastosOtorgamiento;
    private double seguroVida;
    private boolean mostrarTabla = false;

    private Clientes cliente = new Clientes();
    List<Cuotas> lstCuotas = new ArrayList<>();
    private BarChartModel barModelFrances = new BarChartModel();
    //</editor-fold>

    ///<editor-fold defaultstate="collpased" desc="get/set">
    public List<Cuotas> getLstCuotas() {
        return lstCuotas;
    }

    public void setLstCuotas(List<Cuotas> lstCuotas) {
        this.lstCuotas = lstCuotas;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    private List<Frances> lstFrances;

    public double getTotalHaberes() {
        return totalHaberes;
    }

    public void setTotalHaberes(double totalHaberes) {
        this.totalHaberes = totalHaberes;
    }

    public List<Frances> getLstFrances() {
        return lstFrances;
    }

    public void setLstFrances(List<Frances> lstFrances) {
        this.lstFrances = lstFrances;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public Date getFechaPrimerPago() {
        return fechaPrimerPago;
    }

    public void setFechaPrimerPago(Date fechaPrimerPago) {
        this.fechaPrimerPago = fechaPrimerPago;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(double montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public double getTna() {
        return tna;
    }

    public void setTna(double tna) {
        this.tna = tna;
    }

    public double getIva() {
        return iva / Constantes.CIEN;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getGastosOtorgamiento() {
        return gastosOtorgamiento / Constantes.CIEN;
    }

    public void setGastosOtorgamiento(double gastosOtorgamiento) {
        this.gastosOtorgamiento = gastosOtorgamiento;
    }

    public double getSeguroVida() {
        return seguroVida / Constantes.CIEN;
    }

    public void setSeguroVida(double seguroVida) {
        this.seguroVida = seguroVida;
    }

    public BarChartModel getBarModelFrances() {
        return barModelFrances;
    }

    public void setBarModelFrances(BarChartModel barModelFrances) {
        this.barModelFrances = barModelFrances;
    }

    //</editor-fold>
    
   
    public void crearBarModelFrances() {
        barModelFrances = new BarChartModel();
        ChartSeries serieInteres = new ChartSeries();
        ChartSeries serieCuota = new ChartSeries();
        serieInteres.setLabel("interes");
        serieCuota.setLabel("Capital");
        lstFrances.stream().forEach(x -> {
            serieInteres.set(x.getPlazo(), x.getInteres());
            serieCuota.set(x.getPlazo(), x.getAmortizacion());
        });
        barModelFrances.addSeries(serieInteres);
        barModelFrances.addSeries(serieCuota);

        barModelFrances.setLegendPosition("ne");

        Axis yAxis = barModelFrances.getAxis(AxisType.Y);
        Axis xAxis = barModelFrances.getAxis(AxisType.X);
        xAxis.setLabel("Periodo");
        yAxis.setLabel("valor");

    }

    /**
     * Este método se utiliza para incrementarle una cantidad de meses determinada a la fecha de pago inicial
     *
     * @param fecha Fecha de solicitud del préstamo
     * @param meses Representa la cantidad de meses a sumar
     * @return Retorna fecha en formato dd/mm/yyy
     */
    public Date obtenerFechasDePagos(Date fecha, int meses) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, meses);
        return cal.getTime();
    }

    public double tasaInteresPeriodica() {
        return ((tna / Constantes.CIEN) * Constantes.DIAS_DEL_MES) / Constantes.DIAS_DEL_ANIO;
    }

    /**
     * Método que decrementa un porcentaje determinado al monto de prestamo solicitado
     *
     * @return
     */
    public double montoConDescuento() {
        return montoSolicitado - (montoSolicitado * getGastosOtorgamiento());
    }

    /**
     * Método que permite calcular el valor de la cuota pura. Cuota pura = capital + intereses
     *
     * @return Retorna el valor de la cuota pura.
     */
    private double calcularCuotaPura() {
        return ((Math.pow((1 + tasaInteresPeriodica()), plazo) * tasaInteresPeriodica())
                / (Math.pow((1 + tasaInteresPeriodica()), plazo) - 1)) * montoConDescuento();
    }

    /**
     * Calcula el saldo en periodo intermedios
     *
     * @param p
     * @return
     */
    private double calcularSaldos(int p) {
        return montoConDescuento() - amortizacionPrimerPeriodo() * ((Math.pow((1 + tasaInteresPeriodica()), p) - 1)
                / tasaInteresPeriodica());
    }

    /**
     * Calcula el valor de amortización para la primer cuota.
     *
     * @return Retonar el capital amortizado
     */
    private double amortizacionPrimerPeriodo() {
        return calcularCuotaPura() - interesInicial();
    }

    private double interesInicial() {
        return montoConDescuento() * tasaInteresPeriodica();
    }

    private double interesEntrePeriodos(int p) {
        return calcularSaldos(p) * tasaInteresPeriodica();
    }

    /**
     * Capital amortizado . Es la parte de la cuota destinada a amortizar el capital tomado en prestamo.
     *
     * @param p Período
     * @return
     */
    private double calcularCapitalAmortizado(int p) {
        return calcularCuotaPura() - interesEntrePeriodos(p);
    }

    /**
     * Método que permite calcular el capital amortizado en las distintas cuotas.
     *
     * @param p
     * @return
     */
    private double calcularTotalAmortizado(int p) {
        double cantidad = 0;
        cantidad = cantidad + (calcularCuotaPura() - interesEntrePeriodos(p));
        return cantidad;
    }

    /**
     * Metodo que permite calcular el gasto de otorgamiento para un monto solicitado.
     *
     * @param dineroSolicitado Representa el monto solicitado en el credito.
     * @return retorna el gasto de otorgamiento
     */
    public double calcularGastoOtorgamiento(double dineroSolicitado) {
        return dineroSolicitado * getGastosOtorgamiento();
    }

    public String procesar() {
        lstFrances = new ArrayList<>();

//        if (totalHaberes == 0 || montoSolicitado == 0) {
//            Mensajes.error("El total de haberes percibidos y/o monto solicitado no pueden ser 0.");
//        }
//        
//        if (totalHaberes < montoSolicitado) {
//            Mensajes.error("El total de haberes no puede ser menor que el monto solicitado");
//            return "";
//        }
        double totalAmortizado = 0;

        for (int periodo = 0; periodo < plazo; periodo++) {
            totalAmortizado = totalAmortizado + calcularTotalAmortizado(periodo);

            Frances frances = new Frances();

            frances.setPlazo(periodo + 1);
            frances.setSaldoDeuda(calcularSaldos(periodo));
            frances.setInteres(interesEntrePeriodos(periodo));
            frances.setAmortizacion(calcularCapitalAmortizado(periodo));
            frances.setCuotaPura(calcularCuotaPura());
            frances.setTotalAmortizado(totalAmortizado);
            frances.setSeguroDeVida((calcularSaldos(periodo) * getSeguroVida()));
            frances.setIva((interesEntrePeriodos(periodo) * getIva()));
            frances.setCuotaTotal((calcularCuotaPura() + (interesEntrePeriodos(periodo) * getIva()) + (calcularSaldos(periodo) * getSeguroVida())));
            frances.setFechaSolicitud(obtenerFechasDePagos(fechaPrimerPago, periodo));

            lstFrances.add(frances);
        }
        return null;
    }

    public void otorgarPrestamo() {
        Prestamos prestamo = new Prestamos();
        try {
            prestamo.setDineroprestado(BigDecimal.valueOf(montoConDescuento()));
            prestamo.setEstado(Constantes.PENDIENTE_DE_PAGO);
            prestamo.setFsolicitud(new Date());
            prestamo.setCantidadcuotas(plazo);
            prestamo.setTna(BigDecimal.valueOf(getTna()));
            prestamo.setGastosadmin(BigDecimal.valueOf(getGastosOtorgamiento()));
            prestamo.setSegvida(BigDecimal.valueOf(getSeguroVida()));
            prestamo.setIva(BigDecimal.valueOf(getIva()));
            prestamo.setIdcliente(cliente);
            prestamo.setIdoperador(new Usuarios(new Personas(1)));

            for (int i = 0; i < lstFrances.size(); i++) {
                Cuotas c = new Cuotas();
                c.setNro(i + 1);
                c.setValor(BigDecimal.valueOf(lstFrances.get(i).getCuotaTotal()));
                c.setEstado(0);
                c.setFvencimiento(lstFrances.get(i).getFechaSolicitud());
                c.setIdprestamo(prestamo);
                lstCuotas.add(c);
            }
            prestamo.setCuotasCollection(lstCuotas);

            prestamoEJB.create(prestamo);

            Mensajes.success("¡Prestamo generado exitosamente!");
        } catch (Exception e) {
            Mensajes.error("Ocurrió un error y el préstamo no pudo generarse : " + e.getLocalizedMessage());
        }
    }

    public void limpiarCampos() {
        lstFrances = new ArrayList<>();
        lstCuotas = new ArrayList<>();
        iva = 0;
        plazo = 1;
        tna = 0;
        gastosOtorgamiento = 0;
        seguroVida = 0;
        montoSolicitado = 0;
        PrimeFaces.current().executeScript("PF('calendario').setDate(null)");
    }
}
