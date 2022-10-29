package com.shared.algo.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csvreader.CsvWriter;
import com.shared.algo.annotations.Headers;
import com.shared.algo.exception.BadRequestException;
import com.shared.algo.model.IpData;

@Service
public class CsvServiceImpl implements CsvService {

    @Override
    public Collection<?> retrieveData(MultipartFile multipartFile, Class<?> clazz) throws Exception {
        List<Object> response = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            List<CSVRecord> list = csvParser.getRecords();

            for (CSVRecord csvRecord : list) {
                if (csvRecord.getRecordNumber() == 1) {
                    continue;
                } else {
                	if(clazz.isInstance(new IpData())) {
                		IpData data = BeanUtils.instantiateClass(IpData.class);
                        data.setId(Integer.parseInt(csvRecord.get(0)));
                        data.setFirst_name(csvRecord.get(1));
                        data.setLast_name(csvRecord.get(2));
                        data.setEmail(csvRecord.get(3));
                        data.setGender(csvRecord.get(4));
                        data.setIp_address(csvRecord.get(5));
                        
                        response.add(data);
                	}
                    
                }
            }

            csvParser.close();
            reader.close();
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream getCSV(List<?> data) {
        ByteArrayInputStream byteArrayInputStream;
        try (ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();) {
            CsvWriter csvWriter = new CsvWriter(new BufferedOutputStream(outPutStream), ',', StandardCharsets.UTF_8);

            if (Objects.nonNull(data) && !data.isEmpty()) {
                if (data.get(0) instanceof IpData ipDataCasted) {
                    Field[] fields = ipDataCasted.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        Headers annotation = field.getAnnotation(Headers.class);
                        csvWriter.write(annotation.header());
                    }
                    csvWriter.endRecord();

                    for (Object obj : data) {
                        IpData ipData = (IpData) obj;
                        csvWriter.write(String.valueOf(ipData.getId()));
                        csvWriter.write(ipData.getFirst_name());
                        csvWriter.write(ipData.getLast_name());
                        csvWriter.write(ipData.getEmail());
                        csvWriter.write(ipData.getGender());
                        csvWriter.write(ipData.getIp_address());
                        csvWriter.endRecord();
                    }

                    csvWriter.close();
                    byteArrayInputStream = new ByteArrayInputStream(outPutStream.toByteArray());
                    outPutStream.close();
                    return byteArrayInputStream;
                }else {
                	throw new BadRequestException("Class type not supported");
                }
            } else {
                throw new BadRequestException("Data is null/empty");
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
