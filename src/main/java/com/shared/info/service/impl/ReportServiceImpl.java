package com.shared.info.service.impl;

import com.shared.info.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public final class ReportServiceImpl implements ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Override
    public byte[] generatePdfReport(List<?> data, String reportTitle) throws Exception {
        File file = new File("src/main/resources/report_utils/protocol.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", reportTitle);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        log.info("Report created successfully.");
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
