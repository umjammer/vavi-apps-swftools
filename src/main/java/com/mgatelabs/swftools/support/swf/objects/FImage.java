package com.mgatelabs.swftools.support.swf.objects;

import java.awt.image.BufferedImage;

public interface FImage {
    int getVersion();

    BufferedImage getImage();

    int getWidth();

    int getHeight();

    int getID();
}