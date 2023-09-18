package com.shared.info.service.impl;

import com.shared.info.service.ImageContentService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public final class ImageContentServiceImpl implements ImageContentService {

    @Override
    public String processImage(MultipartFile multipartFile, String lang) {
        if (!lang.equalsIgnoreCase("eng") || !lang.equalsIgnoreCase("kan")) {
            return "Language group not supported";
        }
        ITesseract instance = new Tesseract();
        try {
            BufferedImage in = ImageIO.read(convert(multipartFile));
            BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = newImage.createGraphics();
            g.drawImage(in, 0, 0, null);
            g.dispose();

            instance.setLanguage(lang);
            instance.setDatapath("src/main/resources/tess4j/");
            return instance.doOCR(newImage);
        } catch (TesseractException | IOException e) {
            log.error("{}", e.getMessage());
            return "Error while reading image";
        }
    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        boolean newFile = convFile.createNewFile();
        log.info("creating new file {}", newFile);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        return convFile;
    }

}
