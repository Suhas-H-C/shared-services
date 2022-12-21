package com.shared.algo.service;

import com.shared.algo.exception.BadRequestException;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

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
            //Data path currently in local
            tesseract.setDatapath("C:\\Users\\My PC\\AppData\\Local\\Tesseract-OCR\\tessdata");

            return tesseract.doOCR(img);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private File convert(MultipartFile multipartFile) throws Exception {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        convFile.createNewFile();

        FileOutputStream fileOutputStream = new FileOutputStream(convFile);

        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        return convFile;

    }

}
