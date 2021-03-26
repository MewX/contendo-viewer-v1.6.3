package org.apache.batik.svggen.font.table;

import java.io.IOException;
import java.io.RandomAccessFile;

public interface LookupSubtableFactory {
  LookupSubtable read(int paramInt1, RandomAccessFile paramRandomAccessFile, int paramInt2) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/LookupSubtableFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */