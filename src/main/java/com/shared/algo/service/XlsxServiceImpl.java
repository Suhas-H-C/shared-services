package com.shared.algo.service;

import static com.shared.algo.enums.FileConstants.SHEET_NAME;
import static com.shared.algo.enums.Messages.INVLAID_SHEET;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shared.algo.annotations.Headers;
import com.shared.algo.enums.Messages;
import com.shared.algo.exception.BadRequestException;
import com.shared.algo.model.IpData;

@Service
public class XlsxServiceImpl implements XlsxService {

	@Override
	public Collection<?> read(MultipartFile multipartFile, Class<?> clazz) throws Exception {
		List<Object> response = new ArrayList<>();

		try (Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream())) {
			Sheet sheet = workbook.getSheet(SHEET_NAME.getValue());
			if (Objects.isNull(sheet)) {
				throw new BadRequestException(INVLAID_SHEET.getMessage());
			} else {
				for (Row row : sheet) {
					if (row.getRowNum() == 0) {
						continue;
					} else {

						if (clazz.isInstance(new IpData())) {
							IpData data = BeanUtils.instantiateClass(IpData.class);

							data.setId((int) row.getCell(0).getNumericCellValue());
							data.setFirst_name(row.getCell(1).getStringCellValue());
							data.setLast_name(row.getCell(2).getStringCellValue());
							data.setEmail(row.getCell(3).getStringCellValue());
							data.setGender(row.getCell(4).getStringCellValue());
							data.setIp_address(row.getCell(5).getStringCellValue());

							response.add(data);
						} else {
							break;
						}
					}
				}
			}

			return response;
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	@Override
	public ByteArrayInputStream write(List<?> data) throws Exception {
		try (Workbook workBook = new XSSFWorkbook();
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workBook.createSheet(SHEET_NAME.getValue());

			if (Objects.nonNull(data) && !data.isEmpty()) {
				List<String> fileHeaders = new ArrayList<>();

				if (data.get(0) instanceof IpData ipDataInstance) {
					Field[] fields = ipDataInstance.getClass().getDeclaredFields();
					for (Field field : fields) {
						Headers headers = field.getAnnotation(Headers.class);
						fileHeaders.add(headers.header());
					}
					Row headerRow = sheet.createRow(0);
					for (int i = 0; i < fileHeaders.size(); i++) {
						headerRow.createCell(i).setCellValue(fileHeaders.get(i));
					}
					int flag = 1;

					for (Object obj : data) {
						IpData ipData = (IpData) obj;
						Row valueRow = sheet.createRow(flag++);
						valueRow.createCell(0).setCellValue(ipData.getId());
						valueRow.createCell(1).setCellValue(ipData.getFirst_name());
						valueRow.createCell(2).setCellValue(ipData.getLast_name());
						valueRow.createCell(3).setCellValue(ipData.getEmail());
						valueRow.createCell(4).setCellValue(ipData.getGender());
						valueRow.createCell(5).setCellValue(ipData.getIp_address());
					}
				} else {
					throw new BadRequestException(Messages.TYPE_NOT_FOUND.getMessage());
				}

				workBook.write(byteArrayOutputStream);
				workBook.close();
				return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			}

		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
		return null;
	}

}