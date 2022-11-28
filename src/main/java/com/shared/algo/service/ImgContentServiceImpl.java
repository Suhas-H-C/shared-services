package com.shared.algo.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shared.algo.exception.BadRequestException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

@Service
public class ImgContentServiceImpl implements ImgContentService {

	@Override
	public String processImage(MultipartFile multipartFile, String lang) throws Exception {
		ITesseract tesseract = new Tesseract();

		try {
			BufferedImage in = ImageIO.read(convert(multipartFile));
			BufferedImage img = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);

			Graphics2D g = img.createGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();

			tesseract.setLanguage(lang);
			//Data path currently is local
			tesseract.setDatapath("C:\\Users\\My PC\\AppData\\Local\\Tesseract-OCR\\tessdata");

			return tesseract.doOCR(img);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	private File convert(MultipartFile multipartFile) throws Exception {
		File convFile = new File(multipartFile.getOriginalFilename());
		convFile.createNewFile();

		FileOutputStream fileOutputStream = new FileOutputStream(convFile);

		fileOutputStream.write(multipartFile.getBytes());
		fileOutputStream.close();

		return convFile;

	}

}
