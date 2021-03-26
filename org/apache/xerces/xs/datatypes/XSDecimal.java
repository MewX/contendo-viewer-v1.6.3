package org.apache.xerces.xs.datatypes;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface XSDecimal {
  BigDecimal getBigDecimal();
  
  BigInteger getBigInteger() throws NumberFormatException;
  
  long getLong() throws NumberFormatException;
  
  int getInt() throws NumberFormatException;
  
  short getShort() throws NumberFormatException;
  
  byte getByte() throws NumberFormatException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/datatypes/XSDecimal.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */