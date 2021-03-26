package com.levigo.jbig2;

import com.levigo.jbig2.err.IntegerMaxValueException;
import com.levigo.jbig2.err.InvalidHeaderValueException;
import com.levigo.jbig2.io.SubInputStream;
import java.io.IOException;

public interface SegmentData {
  void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws InvalidHeaderValueException, IntegerMaxValueException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/SegmentData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */