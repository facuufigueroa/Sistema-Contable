package Reportes;
import DataBase.ConexionBD;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class LibroDiario {

    public LibroDiario(){}
    public void conexionReporte(){
        try {
            ConexionBD con = new ConexionBD();
            Connection conn = con.conexion();

            /*JasperReport jasperMasterReport = (JasperReport) JRLoader.loadObject(new File("Reportes/LibroDiario.jasper"));
            //JasperReport jasperSubReport = JasperCompileManager.compileReport("/Reportes/subreporte-cuentas.jasper");


            JasperPrint mostrarReporte = JasperFillManager.fillReport(jasperMasterReport,null, conn);
            JasperViewer reporteMaster = new JasperViewer(mostrarReporte,false);

            reporteMaster.setVisible(true);

            System.out.println("Se conecto");*/

            //JasperReport jreport = JasperCompileManager.compileReport("Reportes/LibroDiario.jrxml");

            // The data source to use to create the report

            //JasperPrint jprint = JasperFillManager.fillReport(jreport, null, conn);

            // Viewing the report
            //JasperViewer.viewReport(jprint, false);
            HashMap<String, Object> parameters = new HashMap<>();
            JasperReport archivo = JasperCompileManager.compileReport("C:\\Users\\Facundo\\Desktop\\Facultad 2022\\Segundo Cuatrimestre\\Sistemas Administrativos II\\Sistema Contable\\src\\main\\java\\Reportes\\LibroDiario.jrxml");
            JasperReport subReport = JasperCompileManager.compileReport("C:\\Users\\Facundo\\Desktop\\Facultad 2022\\Segundo Cuatrimestre\\Sistemas Administrativos II\\Sistema Contable\\src\\main\\java\\Reportes\\subreporte-cuentas.jrxml");

            //JasperReport subReport = JasperCompileManager.compileReport("Reportes/subreporte-cuentas.jrxml");
            parameters.put("subReport",subReport);
            JasperPrint prin = JasperFillManager.fillReport(archivo, null,conn);
            JasperViewer ver = new JasperViewer(prin, false);
            ver.setVisible(true);


        } catch (JRException ex) {

            System.out.println(ex);
        }
    }




}
