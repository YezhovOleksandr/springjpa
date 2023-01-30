package com.example.springjpa.utils;

import java.io.File;

public class FileUtils {
    public static String getImagesFolderPath() {
        return System.getProperty("user.home") + File.separator + "images" + File.separator;
    }
}
