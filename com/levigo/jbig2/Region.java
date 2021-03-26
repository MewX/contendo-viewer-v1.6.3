package com.levigo.jbig2;

import com.levigo.jbig2.err.IntegerMaxValueException;
import com.levigo.jbig2.err.InvalidHeaderValueException;
import com.levigo.jbig2.segments.RegionSegmentInformation;
import java.io.IOException;

public interface Region extends SegmentData {
  Bitmap getRegionBitmap() throws IOException, IntegerMaxValueException, InvalidHeaderValueException;
  
  RegionSegmentInformation getRegionInfo();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/Region.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */