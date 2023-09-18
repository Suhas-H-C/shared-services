package com.shared.algo.service.impl;

import com.csvreader.CsvWriter;
import com.shared.algo.annotations.FiledHeader;
import com.shared.algo.exception.BadRequestException;
import com.shared.algo.exception.ClassTypeNotSupportedException;
import com.shared.algo.dto.InternetProtocol;
import com.shared.algo.service.CsvService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.shared.algo.enums.FileConstants.CONTENT_TYPE;
import static com.shared.algo.enums.Messages.NULL_DATA;
import static com.shared.algo.enums.Messages.TYPE_NOT_FOUND;
import static java.util.Objects.nonNull;

@Service
public final class CsvServiceImpl implements CsvService {

    @Override
    public Collection<?> readCSVFileContent(MultipartFile multipartFile, String contentType) throws Exception {
        List<Object> response = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            List<CSVRecord> list = csvParser.getRecords();

            csvParser.close();
            for (CSVRecord csvRecord : list) {
                if (csvRecord.getRecordNumber() != 1) {
                    if (checkContentType(contentType) instanceof InternetProtocol) {
                        InternetProtocol data = BeanUtils.instantiateClass(InternetProtocol.class);
                        data.setId(Integer.parseInt(nullChecker(csvRecord, 0)));
                        data.setFirst_name(nullChecker(csvRecord, 1));
                        data.setLast_name(nullChecker(csvRecord, 2));
                        data.setEmail(nullChecker(csvRecord, 3));
                        data.setGender(nullChecker(csvRecord, 4));
                        data.setIp_address(nullChecker(csvRecord, 5));
                        response.add(data);
                    } else {
                        throw new ClassTypeNotSupportedException(TYPE_NOT_FOUND.getMessage());
                    }
                }
            }
            reader.close();
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream generateCSV(List<?> data, Class<?> clazz) {
        ByteArrayInputStream byteArrayInputStream;
        try (ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();) {
            CsvWriter csvWriter = new CsvWriter(new BufferedOutputStream(outPutStream), ',', StandardCharsets.UTF_8);
            if (nonNull(data) && !data.isEmpty()) {
                if (data.get(0).getClass().equals(clazz)) {
                    Field[] templateHeaders = clazz.getDeclaredFields();
                    for (Field field : templateHeaders) {
                        FiledHeader filedHeaderConfig = field.getAnnotation(FiledHeader.class);
                        csvWriter.write(filedHeaderConfig.header());
                    }
                    csvWriter.endRecord();
                    for (Object obj : data) {
                        for (Field field : obj.getClass().getDeclaredFields()) {
                            Object value = nonNull(field.get(obj)) ? field.get(obj) : null;
                            csvWriter.write(nonNull(value) ? String.valueOf(value) : null);
                        }
                        csvWriter.endRecord();
                    }

                    csvWriter.close();
                    byteArrayInputStream = new ByteArrayInputStream(outPutStream.toByteArray());
                    outPutStream.close();
                    return byteArrayInputStream;
                } else {
                    throw new BadRequestException(TYPE_NOT_FOUND.getMessage());
                }
            } else {
                throw new BadRequestException(NULL_DATA.getMessage());
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private Object checkContentType(String value) {
        if (value.isEmpty() || value.isBlank()) {
            throw new BadRequestException(NULL_DATA.getMessage());
        } else {
            if (CONTENT_TYPE.getValue().equalsIgnoreCase(value)) {
                return new InternetProtocol();
            } else {
                throw new ClassTypeNotSupportedException(TYPE_NOT_FOUND.getMessage());
            }
        }
    }

    private String nullChecker(CSVRecord csvRecord, int index) {
        if (nonNull(csvRecord.get(index))) {
            return csvRecord.get(index);
        } else {
            return null;
        }
    }
}
