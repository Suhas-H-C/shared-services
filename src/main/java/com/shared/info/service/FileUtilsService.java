package com.shared.info.service;

import java.io.File;
import java.util.Collection;

@FunctionalInterface
public interface FileUtilsService {
    byte[] zipFiles(Collection<File> files) throws Exception;
}
