package com.shared.algo.service;

import java.io.ByteArrayInputStream;
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

import static com.shared.algo.enums.FileConstants.SHEET_NAME;
import static com.shared.algo.enums.Messages.*;
import com.shared.algo.exception.BadRequestException;
import com.shared.algo.model.IpData;

@Service
public class XlsxServiceImpl implements XlsxService {

	@Override
	public Collection<?> read(MultipartFile multipartFile, Class<?> clazz) throws Exception {
		List<Object> response = new ArrayList<>();

		try (Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream())){
			Sheet sheet = workbook.getSheet(SHEET_NAME.getValue());
			if(Objects.isNull(sheet)) {
				throw new BadRequestException(INVLAID_SHEET.getMessage());
			}else {
				for (Row row : sheet) {
					if(row.getRowNum() == 0) {
						continue;
					}else {
						
						if(clazz.isInstance(new IpData())) {
							IpData data = BeanUtils.instantiateClass(IpData.class);
							
							data.setId((int) row.getCell(0).getNumericCellValue());
							data.setFirst_name(row.getCell(1).getStringCellValue());
							data.setLast_name(row.getCell(2).getStringCellValue());
							data.setEmail(row.getCell(3).getStringCellValue());
							data.setGender(row.getCell(4).getStringCellValue());
							data.setIp_address(row.getCell(5).getStringCellValue());
							
							response.add(data);
						}else {
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
		// TODO Auto-generated method stub
		return null;
	}

}
