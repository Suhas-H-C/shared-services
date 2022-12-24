package com.shared.algo.service;

import java.io.File;
import java.util.Collection;

public interface FileUtilsService {

    byte[] zipFiles(Collection<File> files) throws Exception;

    String localFilePath();
}
