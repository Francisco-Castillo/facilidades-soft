/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.controllers;

import com.fcastillo.facilidades.soft.Personas;
import com.fcastillo.facilidades.soft.ejb.PersonasFacadeLocal;
import com.fcastillo.facilidades.soft.utilidades.Mensajes;
import com.fcastillo.facilidades.soft.utilidades.GeneradorReportes;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fcastillo
 */
@Named(value = "personasDataController")
@ApplicationScoped
public class PersonasDataController implements Serializable {

    @Resource(lookup = "jdbc/facsoftdb")
    DataSource ds;

    @EJB
    PersonasFacadeLocal personasEJB;
    private List<Personas> lstPersonas;
    private Personas nuevaPersona = new Personas();

    Logger logger = LogManager.getLogger(PersonasDataController.class);

    public Personas getNuevaPersona() {
        return nuevaPersona;
    }

    public void setNuevaPersona(Personas nuevaPersona) {
        this.nuevaPersona = nuevaPersona;
    }

    public List<Personas> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Personas> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }

    @PostConstruct
    public void init() {
        iniciar();
    }

    private void iniciar() {
        lstPersonas = personasEJB.findAll();
    }

    public StreamedContent getImage() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        ByteArrayOutputStream out = null;
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String path = rb.getString("uploads");
        String noimage = rb.getString("noimage");

        if (ctx.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            try {
                String personaId = ctx.getExternalContext().getRequestParameterMap().get("id");

                int idPersona = Integer.parseInt(personaId);

                Personas persona = personasEJB.findById(idPersona);
                if (persona.getImagen().isEmpty() || persona.getImagen().equals("") || persona.getImagen() == null) {
                    out = getFile(path, noimage);
                } else {
                    out = getFile(path, persona.getImagen());
                }

            } catch (Exception e) {
                out = getFile(path, noimage);
            }

            return new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()));
        }
    }

    public ByteArrayOutputStream getFile(String path, String fileName) {
        ByteArrayOutputStream out = null;

        String fullPath = path + fileName;

        InputStream is = null;

        try {
            File remoteFile = new File(fullPath);

            is = new BufferedInputStream(new FileInputStream(remoteFile));

            out = new ByteArrayOutputStream((int) remoteFile.length());

            byte[] buffer = new byte[4096];

            int len = 0;

            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            String msg = "Error..." + e.getMessage();
            System.out.println(msg);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
        }
        return out;
    }

    public void registrarPersona() {
        try {
            personasEJB.create(nuevaPersona);
            Mensajes.success("Persona registrada exitosamente");
        } catch (Exception e) {
            Mensajes.error("Ocurrio un error y no se pudo registrar la persona " + e.getMessage());
        }
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String path = rb.getString("uploads");

        UploadedFile uploadFile = event.getFile();
        String filenameNew = uploadFile.getFileName();

        byte[] contents = uploadFile.getContents();

        try {
            try (FileOutputStream fos = new FileOutputStream(path + filenameNew)) {
                fos.write(contents);
                actualizarImagen(filenameNew);
            }
        } catch (Exception e) {
            Mensajes.error("No se pudo subir la imagen");
        }
        Mensajes.success("Imagen subida exitosamente");
    }

    private void actualizarImagen(String imagen) {
        try {
            Personas persona = personasEJB.findById(nuevaPersona.getId());
            persona.setImagen(imagen);
            personasEJB.edit(persona);
        } catch (Exception e) {
            System.out.println("Error()" + e.getLocalizedMessage());
        }
    }

    public void generarReporteListadoPersonas() {
        try {
            Map parameters = new HashMap();
            GeneradorReportes generadorReporte = new GeneradorReportes();
            ResourceBundle rb = ResourceBundle.getBundle("config");

            String directorioReportes = rb.getString("directorio.reportes").trim();
            String directorioImagenes = rb.getString("directorio.imagenes").trim();
            String archivoJasper = "listadoPersonas.jasper";
            String logoFacSoft = rb.getString("imagen.logofacsoft").trim();

            parameters.put("P_LOGO", directorioImagenes + logoFacSoft);
            parameters.put("nombreArchivoExportado", "ListadoDePersonas");

            generadorReporte.imprimirPDF(directorioReportes + archivoJasper, parameters, ds.getConnection());
            FacesContext.getCurrentInstance().responseComplete();
            logger.info("Reporte " + archivoJasper + " generado exitosamente");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | NamingException e) {
            logger.error("PersonasDataController.generarReporteListadPersonas()", e.getMessage());
        }

    }
}
