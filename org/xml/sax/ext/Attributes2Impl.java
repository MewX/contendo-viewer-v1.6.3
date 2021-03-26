package org.xml.sax.ext;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

public class Attributes2Impl extends AttributesImpl implements Attributes2 {
  private boolean[] a;
  
  private boolean[] b;
  
  public Attributes2Impl() {}
  
  public Attributes2Impl(Attributes paramAttributes) {
    super(paramAttributes);
  }
  
  public boolean isDeclared(int paramInt) {
    if (paramInt < 0 || paramInt >= getLength())
      throw new ArrayIndexOutOfBoundsException("No attribute at index: " + paramInt); 
    return this.a[paramInt];
  }
  
  public boolean isDeclared(String paramString1, String paramString2) {
    int i = getIndex(paramString1, paramString2);
    if (i < 0)
      throw new IllegalArgumentException("No such attribute: local=" + paramString2 + ", namespace=" + paramString1); 
    return this.a[i];
  }
  
  public boolean isDeclared(String paramString) {
    int i = getIndex(paramString);
    if (i < 0)
      throw new IllegalArgumentException("No such attribute: " + paramString); 
    return this.a[i];
  }
  
  public boolean isSpecified(int paramInt) {
    if (paramInt < 0 || paramInt >= getLength())
      throw new ArrayIndexOutOfBoundsException("No attribute at index: " + paramInt); 
    return this.b[paramInt];
  }
  
  public boolean isSpecified(String paramString1, String paramString2) {
    int i = getIndex(paramString1, paramString2);
    if (i < 0)
      throw new IllegalArgumentException("No such attribute: local=" + paramString2 + ", namespace=" + paramString1); 
    return this.b[i];
  }
  
  public boolean isSpecified(String paramString) {
    int i = getIndex(paramString);
    if (i < 0)
      throw new IllegalArgumentException("No such attribute: " + paramString); 
    return this.b[i];
  }
  
  public void setAttributes(Attributes paramAttributes) {
    int i = paramAttributes.getLength();
    super.setAttributes(paramAttributes);
    this.a = new boolean[i];
    this.b = new boolean[i];
    if (paramAttributes instanceof Attributes2) {
      Attributes2 attributes2 = (Attributes2)paramAttributes;
      for (byte b = 0; b < i; b++) {
        this.a[b] = attributes2.isDeclared(b);
        this.b[b] = attributes2.isSpecified(b);
      } 
    } else {
      for (byte b = 0; b < i; b++) {
        this.a[b] = !"CDATA".equals(paramAttributes.getType(b));
        this.b[b] = true;
      } 
    } 
  }
  
  public void addAttribute(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    super.addAttribute(paramString1, paramString2, paramString3, paramString4, paramString5);
    int i = getLength();
    if (i < this.b.length) {
      boolean[] arrayOfBoolean = new boolean[i];
      System.arraycopy(this.a, 0, arrayOfBoolean, 0, this.a.length);
      this.a = arrayOfBoolean;
      arrayOfBoolean = new boolean[i];
      System.arraycopy(this.b, 0, arrayOfBoolean, 0, this.b.length);
      this.b = arrayOfBoolean;
    } 
    this.b[i - 1] = true;
    this.a[i - 1] = !"CDATA".equals(paramString4);
  }
  
  public void removeAttribute(int paramInt) {
    int i = getLength() - 1;
    super.removeAttribute(paramInt);
    if (paramInt != i) {
      System.arraycopy(this.a, paramInt + 1, this.a, paramInt, i - paramInt);
      System.arraycopy(this.b, paramInt + 1, this.b, paramInt, i - paramInt);
    } 
  }
  
  public void setDeclared(int paramInt, boolean paramBoolean) {
    if (paramInt < 0 || paramInt >= getLength())
      throw new ArrayIndexOutOfBoundsException("No attribute at index: " + paramInt); 
    this.a[paramInt] = paramBoolean;
  }
  
  public void setSpecified(int paramInt, boolean paramBoolean) {
    if (paramInt < 0 || paramInt >= getLength())
      throw new ArrayIndexOutOfBoundsException("No attribute at index: " + paramInt); 
    this.b[paramInt] = paramBoolean;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/ext/Attributes2Impl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */