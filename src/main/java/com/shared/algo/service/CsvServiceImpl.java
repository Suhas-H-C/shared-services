package com.shared.algo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shared.algo.model.IpData;

@Service
public class CsvServiceImpl implements CsvService {

	@Override
	public Collection<?> retrieveData(MultipartFile multipartFile) throws Exception {
		List<Object> ipDatas = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			List<CSVRecord> list = csvParser.getRecords();

			for (CSVRecord csvRecord : list) {
				if(csvRecord.getRecordNumber() == 1) {
					continue;
				}else {
					IpData data = new IpData();

					data.setId(Integer.valueOf(csvRecord.get(0)));
					data.setFirst_name(csvRecord.get(1));
					data.setLast_name(csvRecord.get(2));
					data.setEmail(csvRecord.get(3));
					data.setGender(csvRecord.get(4));
					data.setIp_address(csvRecord.get(5));

					ipDatas.add(data);
				}
			}

			csvParser.close();
			reader.close();
			return ipDatas;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
