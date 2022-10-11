package Reportes;
import DataBase.ConexionBD;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.sql.Connection;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibroDiario {

    public LibroDiario(){}
    public void conexionReporte(){
        try {
            ConexionBD con = new ConexionBD();
            Connection conn = con.conexion();

            JasperReport jasperMasterReport = (JasperReport) JRLoader.loadObject(new File("/Reportes/LibroDiario.jasper"));
            //JasperReport jasperSubReport = JasperCompileManager.compileReport("/Reportes/subreporte-cuentas.jasper");


            JasperPrint mostrarReporte = JasperFillManager.fillReport(jasperMasterReport,null, conn);
            JasperViewer reporteMaster = new JasperViewer(mostrarReporte,false);

            reporteMaster.setVisible(true);

            System.out.println("Se conecto");

        } catch (JRException ex) {
             //Logger.getLogger(vista.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se conecto");
        }
    }




}
