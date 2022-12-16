package com.shared.algo.service;

import java.util.List;

public interface ReportService {

	byte[] generatePdfReport(List<?> data, String reportTitle) throws Exception;
}
