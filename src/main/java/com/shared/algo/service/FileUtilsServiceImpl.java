package com.shared.algo.service;

import com.shared.algo.enums.Messages;
import com.shared.algo.exception.BadRequestException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileUtilsServiceImpl implements FileUtilsService {

    @Override
    public byte[] zipFiles(Collection<File> files) throws Exception {

        if (Objects.nonNull(files) && !files.isEmpty()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

            for (File file : files) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getName());

                zipOutputStream.putNextEntry(zipEntry);
                IOUtils.copy(fileInputStream, zipOutputStream);
            }
            zipOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } else {
            throw new BadRequestException(Messages.NULL_DATA.getMessage());
        }
    }
}
