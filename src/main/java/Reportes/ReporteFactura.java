package Reportes;

import DataBase.ConexionBD;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.util.HashMap;

public class ReporteFactura {

    public void loadFactura(){
        try {
            ConexionBD con = new ConexionBD();
            Connection conn = con.conexion();
            HashMap<String, Object> parameters = new HashMap<>();
            JasperReport archivo = JasperCompileManager.compileReport("src\\main\\java\\Reportes\\Factura.jrxml");

            JasperPrint prin = JasperFillManager.fillReport(archivo,null, conn);
            JasperViewer ver = new JasperViewer(prin, false);
            ver.setVisible(true);
        } catch (JRException ex) {
            System.out.println(ex);
        }
    }

}
