package br.edu.unoesc.projetofinal.jdbc.relatorio;

import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioUtil {

	public void viewReport(String pathRelatorio, Connection con, Map<String, Object> parametros) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(pathRelatorio, parametros, con);
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void CompileViewReport(String pathRelatorio, Connection con, Map<String, Object> parametros) {
		try {
			JasperDesign jasperDesign = JRXmlLoader.load(pathRelatorio);
			JasperReport jr = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jr, parametros, con);
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void gerarPdf(String pathRelatorio, Connection con, Map<String, Object> parametros) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(pathRelatorio, parametros, con);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorioteste.pdf");

		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}