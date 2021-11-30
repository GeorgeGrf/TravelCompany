package com.travelcompany.eshop.model;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public enum Directory {
//    FILE_DIRECTORY(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "DB_Backup"+File.separator);
    FILE_DIRECTORY(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+ File.separator);

    private final String path;

    Directory(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
