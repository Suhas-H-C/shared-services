package com.shared.info.service.impl;

import com.shared.info.annotations.FiledHeader;
import com.shared.info.dto.InternetProtocol;
import com.shared.info.enums.FileConstants;
import com.shared.info.enums.Messages;
import com.shared.info.exception.BadRequestException;
import com.shared.info.exception.ClassTypeNotSupportedException;
import com.shared.info.service.XlsxService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public final class XlsxServiceImpl implements XlsxService {

    @Override
    public Collection<?> read(MultipartFile multipartFile, Class<?> clazz) throws Exception {
        List<Object> response = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream())) {
            Sheet sheet = workbook.getSheet(FileConstants.SHEET_NAME.getValue());
            if (Objects.isNull(sheet)) {
                throw new BadRequestException(Messages.INVALID_SHEET.getMessage());
            } else {
                for (Row row : sheet) {
                    if (row.getRowNum() != 0) {
                        if (clazz.isInstance(new InternetProtocol())) {
                            InternetProtocol data = BeanUtils.instantiateClass(InternetProtocol.class);
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
            Sheet sheet = workBook.createSheet(FileConstants.SHEET_NAME.getValue());
            if (Objects.nonNull(data) && !data.isEmpty()) {
                List<String> fileHeaders = new ArrayList<>();

                if (data.get(0) instanceof InternetProtocol internetProtocolInstance) {
                    Field[] fields = internetProtocolInstance.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        FiledHeader headers = field.getAnnotation(FiledHeader.class);
                        fileHeaders.add(headers.header());
                    }
                    Row headerRow = sheet.createRow(0);
                    for (int i = 0; i < fileHeaders.size(); i++) {
                        headerRow.createCell(i).setCellValue(fileHeaders.get(i));
                    }
                    int flag = 1;
                    for (Object obj : data) {
                        InternetProtocol internetProtocol = (InternetProtocol) obj;
                        Row valueRow = sheet.createRow(flag++);
                        valueRow.createCell(0).setCellValue(internetProtocol.getId());
                        valueRow.createCell(1).setCellValue(internetProtocol.getFirst_name());
                        valueRow.createCell(2).setCellValue(internetProtocol.getLast_name());
                        valueRow.createCell(3).setCellValue(internetProtocol.getEmail());
                        valueRow.createCell(4).setCellValue(internetProtocol.getGender());
                        valueRow.createCell(5).setCellValue(internetProtocol.getIp_address());
                    }
                } else {
                    throw new ClassTypeNotSupportedException(Messages.TYPE_NOT_FOUND.getMessage());
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
