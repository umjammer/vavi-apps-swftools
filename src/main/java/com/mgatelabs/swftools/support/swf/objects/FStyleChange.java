/*
    Copyright M-Gate Labs 2007
    Can be edited with permission only.
*/

package com.mgatelabs.swftools.support.swf.objects;

import java.util.ArrayList;
import java.util.List;


public class FStyleChange {
       /*
         TypeFlag        	      UB[1] = 0        	      Non-edge record flag
   	  StateNewStyles      	     NewStyles = UB[1]      	     New styles flag. Used by DefineShape2 and DefineShape3 only.     
   	  StateLineStyle      	     lineStyle = UB[1]      	     Line style change flag
   	  StateFillStyle1      	     fillStyle1 = UB[1]      	     Fill style 1 change flag
   	  StateFillStyle0      	     fillStyle0 = UB[1]      	     Fill style 0 change flag
   	  StateMoveTo      	     MoveTo = UB[1]      	     Move to flag     
   	  MoveBits      	     If moveTo nMoveBits = UB[5]      	     Move bit count     
   	  moveDeltaX      	     If moveTo SB[nMoveBits]      	     Delta X value
   	  moveDeltaY      	     If moveTo SB[nMoveBits]      	     Delta Y value
   	  fillStyle0      	     If fillStyle0 UB[nFillBits]      	     Fill 0 Style
   	  fillStyle1      	     If fillStyle1 UB[nFillBits]      	     Fill 1 Style
   	  lineStyle      	     If lineStyle UB[nLineBits]      	     Line Style
   	  FillStyles      	     If newStyles fillStyleArray      	     Array of new fill styles
   	  LineStyles      	     If newStyles lineStyleArray      	     Array of new line styles
   	  NumFillBits      	     If newStyles NfillBits = UB[4]      	     Number of fill index bits for new styles     
   	  NumLineBits      	     If newStyles NlineBits = UB[4]      	     Number of line index bits for new styles
   	*/

    private boolean isNewStyles;
    private boolean isLineStyle;
    private boolean isFillStyle1;
    private boolean isFillStyle0;
    private boolean isMoveTo;

    private long moveDeltaX;
    private long moveDeltaY;
    private int fillStyle0;
    private int fillStyle1;
    private int lineStyle;
    private List<Object> fillStyleArray;
    private List<Object> lineStyleArray;

    public FStyleChange() {
        // Default Everything
        isNewStyles = false;
        isLineStyle = false;
        isFillStyle1 = false;
        isFillStyle0 = false;
        isMoveTo = false;

        moveDeltaX = 0;
        moveDeltaY = 0;

        fillStyle0 = 0;
        fillStyle1 = 0;
        lineStyle = 0;

        fillStyleArray = new ArrayList<>();
        lineStyleArray = new ArrayList<>();
    }

    public void setMoveDeltaX(long aValue) {
        isMoveTo = true;
        moveDeltaX = aValue;
    }

    public void setMoveDeltaY(long aValue) {
        isMoveTo = true;
        moveDeltaY = aValue;
    }

    public void setFillStyle0(int aValue) {
        isFillStyle0 = true;
        fillStyle0 = aValue;
    }

    public void setFillStyle1(int aValue) {
        isFillStyle1 = true;
        fillStyle1 = aValue;
    }

    public void setLineStyle(int aValue) {
        isLineStyle = true;
        lineStyle = aValue;
    }

    public int getFillStyle0() {
        return fillStyle0;
    }

    public int getFillStyle1() {
        return fillStyle1;
    }

    public int getLineStyle() {
        return lineStyle;
    }

    public long getMoveDeltaX() {
        return moveDeltaX;
    }

    public long getMoveDeltaY() {
        return moveDeltaY;
    }

    public List<Object> getFillStyleArray() {
        isNewStyles = true;
        return fillStyleArray;
    }

    public List<Object> getLineStyleArray() {
        isNewStyles = true;
        return lineStyleArray;
    }

    public boolean isNewStyles() {
        return isNewStyles;
    }

    public boolean isLineStyle() {
        return isLineStyle;
    }

    public boolean isFillStyle1() {
        return isFillStyle1;
    }

    public boolean isFillStyle0() {
        return isFillStyle0;
    }

    public boolean isMoveTo() {
        return isMoveTo;
    }
}