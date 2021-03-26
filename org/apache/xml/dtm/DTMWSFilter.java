package org.apache.xml.dtm;

public interface DTMWSFilter {
  public static final short NOTSTRIP = 1;
  
  public static final short STRIP = 2;
  
  public static final short INHERIT = 3;
  
  short getShouldStripSpace(int paramInt, DTM paramDTM);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/DTMWSFilter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */