package com.mgatelabs.swftools.support.plugins;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import com.mgatelabs.swftools.support.xml.XMLHash;


public class PluginInfo {
    public String name;
    public String fileName;
    public String className;
    public String description;
    public boolean encrypted;
    public int id;
    public int type;
    public int major;
    public int minor;
    private boolean packaged;

    public PluginInfo(String path, InputStream xmlStream, boolean packaged) {

        XMLHash reader = new XMLHash();
        Map<String, String> myHash = reader.read(xmlStream);
        this.packaged = packaged;

        name = myHash.get("name");
        fileName = myHash.get("file");
        this.className = path + myHash.get("class");
        description = myHash.get("description");
        encrypted = false;

        id = Integer.parseInt(myHash.get("id"));
        type = Integer.parseInt(myHash.get("type"));

        major = Integer.parseInt(myHash.get("major"));
        minor = Integer.parseInt(myHash.get("minor"));
    }

    public PluginInfo(File target, boolean packaged) {
        XMLHash reader = new XMLHash();
        Map<String, String>myHash = reader.read(target);
        this.packaged = packaged;

        name = myHash.get("name");
        fileName = myHash.get("file");
        className = myHash.get("class");
        description = myHash.get("description");
        encrypted = false;

        id = Integer.parseInt(myHash.get("id"));
        type = Integer.parseInt(myHash.get("type"));

        major = Integer.parseInt(myHash.get("major"));
        minor = Integer.parseInt(myHash.get("minor"));
    }

    public String toString() {
        String result;

        if (encrypted) {
            result = "(PRO)" + name;
        } else {
            result = "(Free)" + name;
        }

        return result;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public boolean isPackaged() {
        return packaged;
    }
}