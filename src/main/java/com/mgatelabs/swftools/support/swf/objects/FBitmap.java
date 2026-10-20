/*
    Copyright M-Gate Labs 2007
    Can be edited with permission only.
*/
   
   /*
       Bitmap storage
   */

package com.mgatelabs.swftools.support.swf.objects;

// Need List<?> Support

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;


public class FBitmap implements FID, FImage {
    public static final int VER1 = 1;
    public static final int VER2 = 2;
    public static final int VER3 = 3;

    private int myID;
    private int myVersion;
    private BufferedImage myImage;

    private int myHeight;
    private int myWidth;

    byte[] myData;

    /** encoded image waiting to be decoded by {@link #getImage()} */
    private byte[] myPending;

    public FBitmap(int id, byte[] data) {
        myID = id;
        myVersion = 0;
        try {
            myData = data;
            readSize(data);
            myPending = data;
        } catch (Exception e) {
            System.out.println("Data Error: ID:" + myID + " : " + e.getMessage());
            myImage = null;
        }
    }

    public FBitmap(int id, int version, byte[] data, boolean discardData) {
        myID = id;
        myVersion = version;
        try {
            myData = data;
            readSize(data);
            myPending = data;
            if (discardData) {
                myData = null;
            }
        } catch (Exception e) {
            System.out.println("Data Error: ID:" + myID + " : " + e.getMessage());
            myImage = null;
        }
    }

    public FBitmap(int id, byte[] data, byte[] dataA) {
        myID = id;
    }

    /** reads only the header, decoding pixels is deferred until {@link #getImage()} */
    private void readSize(byte[] data) throws IOException {
        try (ImageInputStream iis = new MemoryCacheImageInputStream(new ByteArrayInputStream(data))) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (!readers.hasNext()) {
                throw new IOException("unsupported image format");
            }
            ImageReader reader = readers.next();
            try {
                reader.setInput(iis, true, true);
                myWidth = reader.getWidth(0);
                myHeight = reader.getHeight(0);
            } finally {
                reader.dispose();
            }
        }
    }

    /** decodes in memory, avoids ImageIO's temporary file cache */
    private static BufferedImage readImage(byte[] data) throws IOException {
        // ImageIO.read closes the stream itself
        return ImageIO.read(new MemoryCacheImageInputStream(new ByteArrayInputStream(data)));
    }

    public int getHeight() {
        return myHeight;
    }

    public int getWidth() {
        return myWidth;
    }

    public void setVersion(int value) {
        myVersion = value;
    }

    public int getVersion() {
        return myVersion;
    }

    public int getID() {
        return myID;
    }

    public void setData(byte[] bytes) {
        myData = bytes;
    }

    public byte[] getData() {
        return myData;
    }

    public synchronized BufferedImage getImage() {
        if (myPending != null) {
            try {
                myImage = readImage(myPending);
            } catch (Exception e) {
                System.out.println("Data Error: ID:" + myID + " : " + e.getMessage());
                myImage = null;
            }
            myPending = null;
        }
        return myImage;
    }

    public synchronized void setImage(BufferedImage aImage) {
        myPending = null;
        myImage = aImage;
    }

    public synchronized String toString() {
        if (myImage != null || myPending != null) {
            return "H: " + myWidth + " W: " + myHeight;
        } else {
            return "Null Image";
        }
    }
}