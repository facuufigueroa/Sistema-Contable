package Reportes;
import DataBase.ConexionBD;

import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class LibroDiario {

    public LibroDiario(){}
    public void conexionReporte(Date fecha_desde, Date fecha_hasta){
        try {
            ConexionBD con = new ConexionBD();
            Connection conn = con.conexion();
            HashMap<String, Object> parameters = new HashMap<>();
            JasperReport archivo = JasperCompileManager.compileReport("C:\\Users\\Facundo\\Desktop\\Facultad 2022\\Segundo Cuatrimestre\\Sistemas Administrativos II\\Sistema Contable\\src\\main\\java\\Reportes\\LibroDiario.jrxml");
            parameters.put("fecha_desde",fecha_desde);
            parameters.put("fecha_hasta",fecha_hasta);
            JasperPrint prin = JasperFillManager.fillReport(archivo, parameters,conn);
            JasperViewer ver = new JasperViewer(prin, false);
            ver.setVisible(true);
        } catch (JRException ex) {
            System.out.println(ex);
        }
    }




}
