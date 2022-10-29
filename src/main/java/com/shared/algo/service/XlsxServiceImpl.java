package com.shared.algo.service;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class XlsxServiceImpl implements XlsxService {

	@Override
	public Collection<?> read(MultipartFile multipartFile, Class<?> clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteArrayInputStream write(List<?> data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
