package org.apache.xerces.impl.xs.util;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Vector;
import org.apache.xerces.xs.StringList;

public final class StringListImpl extends AbstractList implements StringList {
  public static final StringListImpl EMPTY_LIST = new StringListImpl(new String[0], 0);
  
  private final String[] fArray;
  
  private final int fLength;
  
  private final Vector fVector;
  
  public StringListImpl(Vector paramVector) {
    this.fVector = paramVector;
    this.fLength = (paramVector == null) ? 0 : paramVector.size();
    this.fArray = null;
  }
  
  public StringListImpl(String[] paramArrayOfString, int paramInt) {
    this.fArray = paramArrayOfString;
    this.fLength = paramInt;
    this.fVector = null;
  }
  
  public int getLength() {
    return this.fLength;
  }
  
  public boolean contains(String paramString) {
    if (this.fVector != null)
      return this.fVector.contains(paramString); 
    if (paramString == null) {
      for (byte b = 0; b < this.fLength; b++) {
        if (this.fArray[b] == null)
          return true; 
      } 
    } else {
      for (byte b = 0; b < this.fLength; b++) {
        if (paramString.equals(this.fArray[b]))
          return true; 
      } 
    } 
    return false;
  }
  
  public String item(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : ((this.fVector != null) ? this.fVector.elementAt(paramInt) : this.fArray[paramInt]);
  }
  
  public Object get(int paramInt) {
    if (paramInt >= 0 && paramInt < this.fLength)
      return (this.fVector != null) ? this.fVector.elementAt(paramInt) : this.fArray[paramInt]; 
    throw new IndexOutOfBoundsException("Index: " + paramInt);
  }
  
  public int size() {
    return getLength();
  }
  
  public Object[] toArray() {
    if (this.fVector != null)
      return this.fVector.toArray(); 
    Object[] arrayOfObject = new Object[this.fLength];
    toArray0(arrayOfObject);
    return arrayOfObject;
  }
  
  public Object[] toArray(Object[] paramArrayOfObject) {
    if (this.fVector != null)
      return this.fVector.toArray(paramArrayOfObject); 
    if (paramArrayOfObject.length < this.fLength) {
      Class clazz1 = paramArrayOfObject.getClass();
      Class clazz2 = clazz1.getComponentType();
      paramArrayOfObject = (Object[])Array.newInstance(clazz2, this.fLength);
    } 
    toArray0(paramArrayOfObject);
    if (paramArrayOfObject.length > this.fLength)
      paramArrayOfObject[this.fLength] = null; 
    return paramArrayOfObject;
  }
  
  private void toArray0(Object[] paramArrayOfObject) {
    if (this.fLength > 0)
      System.arraycopy(this.fArray, 0, paramArrayOfObject, 0, this.fLength); 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/util/StringListImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */