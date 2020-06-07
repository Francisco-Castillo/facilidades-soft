package com.fcastillo.facilidades.soft.utilidades;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeneradorReportes {

    Logger logger = LogManager.getLogger(GeneradorReportes.class);

    public void imprimirPDF(String archivoJasper, Map parametros, Connection con) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, NamingException {
        String nombreArchivoExportado = (String) parametros.get("nombreArchivoExportado");
        try {
            File file = new File(archivoJasper);

            HttpServletResponse httpsr = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpsr.setContentType("Content-Type");
            httpsr.addHeader("Content-disposition", "attachment; filename=" + nombreArchivoExportado.concat(".pdf"));

            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);

            JRExporter jre = new JRPdfExporter();
            jre.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            jre.setParameter(JRExporterParameter.OUTPUT_STREAM, httpsr.getOutputStream());
            jre.exportReport();
        } catch (IOException | JRException e) {
            logger.error("ReporteGenerador.getListaPersonas()", e.getMessage());
        }
    }

}
