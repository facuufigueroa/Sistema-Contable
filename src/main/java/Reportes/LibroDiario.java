package Reportes;

import DataBase.ConexionBD;

import java.sql.Connection;



public class LibroDiario {

   /* public void conexionReporte(){
        try {
            ConexionBD con = new ConexionBD();
            Connection conn = con.conexion();

            JasperReport jasperMasterReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/LibroDiario.jasper"));
            JasperReport jasperSubReport = JasperCompileManager.compileReport("/Reportes/subreporte-cuentas.jasper");


            JasperPrint mostrarReporte = JasperFillManager.fillReport(jasperMasterReport,null, conn);
            JasperViewer reporteMaster = new JasperViewer(mostrarReporte,false);

            reporteMaster.setVisible(true);


        } catch (JRException ex) {
            //  Logger.getLogger(vista.class.getName()).log(Level.SEVERE, null, ex);

        }
    }*/


}
