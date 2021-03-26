package com.levigo.jbig2;

import com.levigo.jbig2.err.IntegerMaxValueException;
import com.levigo.jbig2.err.InvalidHeaderValueException;
import java.io.IOException;
import java.util.ArrayList;

public interface Dictionary extends SegmentData {
  ArrayList<Bitmap> getDictionary() throws IOException, InvalidHeaderValueException, IntegerMaxValueException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/Dictionary.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */