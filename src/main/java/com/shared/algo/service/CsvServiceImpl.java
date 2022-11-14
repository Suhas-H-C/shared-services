package com.shared.algo.service;

import static com.shared.algo.enums.FileConstants.CONTENT_TYPE;
import static com.shared.algo.enums.Messages.NULL_DATA;
import static com.shared.algo.enums.Messages.TYPE_NOT_FOUND;
import static java.util.Objects.nonNull;

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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csvreader.CsvWriter;
import com.shared.algo.annotations.FiledHeaderConfig;
import com.shared.algo.exception.BadRequestException;
import com.shared.algo.exception.ClassTypeNotSupportedException;
import com.shared.algo.model.IpData;

@Service
public final class CsvServiceImpl implements CsvService {

	@Override
	public Collection<?> retrieveData(MultipartFile multipartFile, String contentType) throws Exception {
		List<Object> response = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			List<CSVRecord> list = csvParser.getRecords();

			csvParser.close();
			for (CSVRecord csvRecord : list) {
				if (csvRecord.getRecordNumber() == 1) {
					continue;
				} else {
					if (checkContentType(contentType) instanceof IpData) {
						IpData data = BeanUtils.instantiateClass(IpData.class);

						data.setId(Integer.valueOf(nullChecker(csvRecord, 0)));
						data.setFirst_name((String) nullChecker(csvRecord, 0));
						data.setLast_name((String) nullChecker(csvRecord, 0));
						data.setEmail((String) nullChecker(csvRecord, 0));
						data.setGender((String) nullChecker(csvRecord, 0));
						data.setIp_address((String) nullChecker(csvRecord, 0));
						response.add(data);
					}else {
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
	public ByteArrayInputStream getCSV(List<?> data) {
		ByteArrayInputStream byteArrayInputStream;
		try (ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();) {
			CsvWriter csvWriter = new CsvWriter(new BufferedOutputStream(outPutStream), ',', StandardCharsets.UTF_8);

			if (nonNull(data) && !data.isEmpty()) {
				if (data.get(0) instanceof IpData ipDataCasted) {
					Field[] fields = ipDataCasted.getClass().getDeclaredFields();
					for (Field field : fields) {
						FiledHeaderConfig annotation = field.getAnnotation(FiledHeaderConfig.class);
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
				} else {
					throw new ClassTypeNotSupportedException(TYPE_NOT_FOUND.getMessage());
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
				return new IpData();
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
