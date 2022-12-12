package Reportes;


import DataBase.ConexionBD;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.util.HashMap;

public class ReporteRemito {

    public void loadRemito(String numeroRemito){
        try {
            ConexionBD con = new ConexionBD();
            Connection conn = con.conexion();
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("numRem",numeroRemito);
            JasperReport archivo = JasperCompileManager.compileReport("src\\main\\java\\Reportes\\Remito.jrxml");
            JasperPrint prin = JasperFillManager.fillReport(archivo,parameters, conn);
            JasperViewer ver = new JasperViewer(prin, false);
            ver.setTitle("Remito");
            ver.setVisible(true);
        } catch (JRException ex) {
            System.out.println(ex);
        }
    }

}
