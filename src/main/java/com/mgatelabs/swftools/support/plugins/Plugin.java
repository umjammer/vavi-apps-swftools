package com.mgatelabs.swftools.support.plugins;

public interface Plugin {
    int CONVERSION = 1;
    int BLOCK = 3;
    int DISPLAY = 5;

    String getName();

    int getType();

}