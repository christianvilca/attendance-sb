package org.parish.attendancesb.report;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.parish.attendancesb.controllers.utils.alert.AlertFx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Component
public class Report {

    @Autowired
    private ResourceBundle resourceBundle;
    public void export(
            List<?> list,
            Jrxml jrxml
    ) throws FileNotFoundException, JRException {
        this.export(list, jrxml, new HashMap<>());
    }

    public void export(
            List<?> list,
            Jrxml jrxml,
            Map<String, Object> parameters
    ) throws FileNotFoundException, JRException {
        //Report.class.getResource(jrxml.getJrxmlFile() -> Si el metodo es estatico
        JasperPrint jasperPrint = getJasperPrint(list, jrxml, parameters);

        FileChooser fileChooser = new FileChooser();
        //fileChooser.setInitialDirectory(new File("."));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName(jrxml.getExport());

        Arrays.stream(Format.values()).forEach(f ->
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter(
                                f + " Files",
                                "*." + f.toString().toLowerCase()
                        )
                )
        );

        File file1 = fileChooser.showSaveDialog(new Stage());

        if (file1 == null)
            return;

        setFormat(jasperPrint, fileChooser, file1);

        AlertFx.information("Archivo exportado!");
    }

    public JasperPrint getJasperPrint(List<?> list, Jrxml jrxml, Map<String, Object> parameters) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(getClass().getResource(jrxml.getJrxmlFile()));
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        parameters.put("SUBREPORT_DIR", getClass().getResource("/reports/").toString());
        parameters.put("Title", jrxml.getTitle());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return jasperPrint;
    }

    public void viewer(
            List<?> list,
            Jrxml jrxml
    ) throws FileNotFoundException, JRException {
        this.viewer(list, jrxml, new HashMap<>());
    }

    public void viewer(
            List<?> list,
            Jrxml jrxml,
            Map<String, Object> parameters
    ) throws JRException, FileNotFoundException {
        JasperPrint jasperPrint = getJasperPrint(list, jrxml, parameters);
        new JasperViewerFX().viewReport(jrxml.getTitle(), jasperPrint);
    }

    private void setFormat(JasperPrint jasperPrint, FileChooser fileChooser, File file1) throws JRException {
        String formatReport = fileChooser.getSelectedExtensionFilter().getDescription().split(" ")[0];

        if (formatReport.equals(Format.HTML.name())) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, file1.getAbsolutePath());
        }
        if (formatReport.equals(Format.PDF.name())) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, file1.getAbsolutePath());
        }
        if (formatReport.equals(Format.XLSX.name())) {
            excelFormat(jasperPrint, file1.getAbsolutePath());
        }
    }

    private void excelFormat(JasperPrint jasperPrint, String path) throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[]{"sheet1"});
        exporter.setConfiguration(reportConfigXLS);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
        exporter.exportReport();
    }
}
