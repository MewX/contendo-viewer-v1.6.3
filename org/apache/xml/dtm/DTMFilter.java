package org.apache.xml.dtm;

public interface DTMFilter {
  public static final int SHOW_ALL = -1;
  
  public static final int SHOW_ELEMENT = 1;
  
  public static final int SHOW_ATTRIBUTE = 2;
  
  public static final int SHOW_TEXT = 4;
  
  public static final int SHOW_CDATA_SECTION = 8;
  
  public static final int SHOW_ENTITY_REFERENCE = 16;
  
  public static final int SHOW_ENTITY = 32;
  
  public static final int SHOW_PROCESSING_INSTRUCTION = 64;
  
  public static final int SHOW_COMMENT = 128;
  
  public static final int SHOW_DOCUMENT = 256;
  
  public static final int SHOW_DOCUMENT_TYPE = 512;
  
  public static final int SHOW_DOCUMENT_FRAGMENT = 1024;
  
  public static final int SHOW_NOTATION = 2048;
  
  public static final int SHOW_NAMESPACE = 4096;
  
  public static final int SHOW_BYFUNCTION = 65536;
  
  short acceptNode(int paramInt1, int paramInt2);
  
  short acceptNode(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/DTMFilter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */