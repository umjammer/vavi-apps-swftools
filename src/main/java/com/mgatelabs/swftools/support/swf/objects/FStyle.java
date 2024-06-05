/*
  Copyright M-Gate Labs 2007
  Can be edited with permission only.
*/

package com.mgatelabs.swftools.support.swf.objects;

import java.util.ArrayList;
import java.util.List;


public class FStyle {
    private List<Object> myLineStyles;
    private List<Object> myFillStyles;
    int NfillBits;
    int NumLineBits;
    private List<Object> myShapeRecords;

    public FStyle() {
        // Default Everything
        myLineStyles = new ArrayList<>();
        myFillStyles = new ArrayList<>();
        myShapeRecords = new ArrayList<>();
        NfillBits = 0;
        NumLineBits = 0;
    }

    public void clean() {
        // Clean up, Remove Useless Information
        myLineStyles.clear();
        myFillStyles.clear();
        myShapeRecords.clear();

        myLineStyles = null;
        myFillStyles = null;
        myShapeRecords = null;
    }

    public void setNfillBits(int value) {
        NfillBits = value;
    }

    public void setNumLineBits(int value) {
        NumLineBits = value;
    }

    public int getNfillBits() {
        return NfillBits;
    }

    public int getNumLineBits() {
        return NumLineBits;
    }

    // Get my Line Styles

    public List<Object> getLineStyles() {
        return myLineStyles;
    }

    // Get my Fill Styles

    public List<Object> getFillStyles() {
        return myFillStyles;
    }

    // Get my Shape Records

    public List<Object> getShapeRecords() {
        return myShapeRecords;
    }

    public void setShapeRecords(List<Object> vec) {
        myShapeRecords = vec;
    }
}