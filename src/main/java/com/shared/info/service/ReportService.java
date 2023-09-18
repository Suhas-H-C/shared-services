package com.shared.info.service;

import java.util.List;

@FunctionalInterface
public interface ReportService {
    byte[] generatePdfReport(List<?> data, String reportTitle) throws Exception;
}
