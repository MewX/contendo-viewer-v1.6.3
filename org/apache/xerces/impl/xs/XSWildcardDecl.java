package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSWildcard;

public class XSWildcardDecl implements XSWildcard {
  public static final String ABSENT = null;
  
  public short fType = 1;
  
  public short fProcessContents = 1;
  
  public String[] fNamespaceList;
  
  public XSObjectList fAnnotations = null;
  
  private String fDescription = null;
  
  public boolean allowNamespace(String paramString) {
    if (this.fType == 1)
      return true; 
    if (this.fType == 2) {
      boolean bool = false;
      int i = this.fNamespaceList.length;
      for (byte b = 0; b < i && !bool; b++) {
        if (paramString == this.fNamespaceList[b])
          bool = true; 
      } 
      if (!bool)
        return true; 
    } 
    if (this.fType == 3) {
      int i = this.fNamespaceList.length;
      for (byte b = 0; b < i; b++) {
        if (paramString == this.fNamespaceList[b])
          return true; 
      } 
    } 
    return false;
  }
  
  public boolean isSubsetOf(XSWildcardDecl paramXSWildcardDecl) {
    if (paramXSWildcardDecl == null)
      return false; 
    if (paramXSWildcardDecl.fType == 1)
      return true; 
    if (this.fType == 2 && paramXSWildcardDecl.fType == 2 && this.fNamespaceList[0] == paramXSWildcardDecl.fNamespaceList[0])
      return true; 
    if (this.fType == 3) {
      if (paramXSWildcardDecl.fType == 3 && subset2sets(this.fNamespaceList, paramXSWildcardDecl.fNamespaceList))
        return true; 
      if (paramXSWildcardDecl.fType == 2 && !elementInSet(paramXSWildcardDecl.fNamespaceList[0], this.fNamespaceList) && !elementInSet(ABSENT, this.fNamespaceList))
        return true; 
    } 
    return false;
  }
  
  public boolean weakerProcessContents(XSWildcardDecl paramXSWildcardDecl) {
    return ((this.fProcessContents == 3 && paramXSWildcardDecl.fProcessContents == 1) || (this.fProcessContents == 2 && paramXSWildcardDecl.fProcessContents != 2));
  }
  
  public XSWildcardDecl performUnionWith(XSWildcardDecl paramXSWildcardDecl, short paramShort) {
    if (paramXSWildcardDecl == null)
      return null; 
    XSWildcardDecl xSWildcardDecl = new XSWildcardDecl();
    xSWildcardDecl.fProcessContents = paramShort;
    if (areSame(paramXSWildcardDecl)) {
      xSWildcardDecl.fType = this.fType;
      xSWildcardDecl.fNamespaceList = this.fNamespaceList;
    } else if (this.fType == 1 || paramXSWildcardDecl.fType == 1) {
      xSWildcardDecl.fType = 1;
    } else if (this.fType == 3 && paramXSWildcardDecl.fType == 3) {
      xSWildcardDecl.fType = 3;
      xSWildcardDecl.fNamespaceList = union2sets(this.fNamespaceList, paramXSWildcardDecl.fNamespaceList);
    } else if (this.fType == 2 && paramXSWildcardDecl.fType == 2) {
      xSWildcardDecl.fType = 2;
      xSWildcardDecl.fNamespaceList = new String[2];
      xSWildcardDecl.fNamespaceList[0] = ABSENT;
      xSWildcardDecl.fNamespaceList[1] = ABSENT;
    } else if ((this.fType == 2 && paramXSWildcardDecl.fType == 3) || (this.fType == 3 && paramXSWildcardDecl.fType == 2)) {
      String[] arrayOfString1 = null;
      String[] arrayOfString2 = null;
      if (this.fType == 2) {
        arrayOfString1 = this.fNamespaceList;
        arrayOfString2 = paramXSWildcardDecl.fNamespaceList;
      } else {
        arrayOfString1 = paramXSWildcardDecl.fNamespaceList;
        arrayOfString2 = this.fNamespaceList;
      } 
      boolean bool = elementInSet(ABSENT, arrayOfString2);
      if (arrayOfString1[0] != ABSENT) {
        boolean bool1 = elementInSet(arrayOfString1[0], arrayOfString2);
        if (bool1 && bool) {
          xSWildcardDecl.fType = 1;
        } else if (bool1 && !bool) {
          xSWildcardDecl.fType = 2;
          xSWildcardDecl.fNamespaceList = new String[2];
          xSWildcardDecl.fNamespaceList[0] = ABSENT;
          xSWildcardDecl.fNamespaceList[1] = ABSENT;
        } else {
          if (!bool1 && bool)
            return null; 
          xSWildcardDecl.fType = 2;
          xSWildcardDecl.fNamespaceList = arrayOfString1;
        } 
      } else if (bool) {
        xSWildcardDecl.fType = 1;
      } else {
        xSWildcardDecl.fType = 2;
        xSWildcardDecl.fNamespaceList = arrayOfString1;
      } 
    } 
    return xSWildcardDecl;
  }
  
  public XSWildcardDecl performIntersectionWith(XSWildcardDecl paramXSWildcardDecl, short paramShort) {
    if (paramXSWildcardDecl == null)
      return null; 
    XSWildcardDecl xSWildcardDecl = new XSWildcardDecl();
    xSWildcardDecl.fProcessContents = paramShort;
    if (areSame(paramXSWildcardDecl)) {
      xSWildcardDecl.fType = this.fType;
      xSWildcardDecl.fNamespaceList = this.fNamespaceList;
    } else if (this.fType == 1 || paramXSWildcardDecl.fType == 1) {
      XSWildcardDecl xSWildcardDecl1 = this;
      if (this.fType == 1)
        xSWildcardDecl1 = paramXSWildcardDecl; 
      xSWildcardDecl.fType = xSWildcardDecl1.fType;
      xSWildcardDecl.fNamespaceList = xSWildcardDecl1.fNamespaceList;
    } else if ((this.fType == 2 && paramXSWildcardDecl.fType == 3) || (this.fType == 3 && paramXSWildcardDecl.fType == 2)) {
      String[] arrayOfString1 = null;
      String[] arrayOfString2 = null;
      if (this.fType == 2) {
        arrayOfString2 = this.fNamespaceList;
        arrayOfString1 = paramXSWildcardDecl.fNamespaceList;
      } else {
        arrayOfString2 = paramXSWildcardDecl.fNamespaceList;
        arrayOfString1 = this.fNamespaceList;
      } 
      int i = arrayOfString1.length;
      String[] arrayOfString3 = new String[i];
      byte b1 = 0;
      for (byte b2 = 0; b2 < i; b2++) {
        if (arrayOfString1[b2] != arrayOfString2[0] && arrayOfString1[b2] != ABSENT)
          arrayOfString3[b1++] = arrayOfString1[b2]; 
      } 
      xSWildcardDecl.fType = 3;
      xSWildcardDecl.fNamespaceList = new String[b1];
      System.arraycopy(arrayOfString3, 0, xSWildcardDecl.fNamespaceList, 0, b1);
    } else if (this.fType == 3 && paramXSWildcardDecl.fType == 3) {
      xSWildcardDecl.fType = 3;
      xSWildcardDecl.fNamespaceList = intersect2sets(this.fNamespaceList, paramXSWildcardDecl.fNamespaceList);
    } else if (this.fType == 2 && paramXSWildcardDecl.fType == 2) {
      if (this.fNamespaceList[0] != ABSENT && paramXSWildcardDecl.fNamespaceList[0] != ABSENT)
        return null; 
      XSWildcardDecl xSWildcardDecl1 = this;
      if (this.fNamespaceList[0] == ABSENT)
        xSWildcardDecl1 = paramXSWildcardDecl; 
      xSWildcardDecl.fType = xSWildcardDecl1.fType;
      xSWildcardDecl.fNamespaceList = xSWildcardDecl1.fNamespaceList;
    } 
    return xSWildcardDecl;
  }
  
  private boolean areSame(XSWildcardDecl paramXSWildcardDecl) {
    if (this.fType == paramXSWildcardDecl.fType) {
      if (this.fType == 1)
        return true; 
      if (this.fType == 2)
        return (this.fNamespaceList[0] == paramXSWildcardDecl.fNamespaceList[0]); 
      if (this.fNamespaceList.length == paramXSWildcardDecl.fNamespaceList.length) {
        for (byte b = 0; b < this.fNamespaceList.length; b++) {
          if (!elementInSet(this.fNamespaceList[b], paramXSWildcardDecl.fNamespaceList))
            return false; 
        } 
        return true;
      } 
    } 
    return false;
  }
  
  String[] intersect2sets(String[] paramArrayOfString1, String[] paramArrayOfString2) {
    String[] arrayOfString1 = new String[Math.min(paramArrayOfString1.length, paramArrayOfString2.length)];
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramArrayOfString1.length; b2++) {
      if (elementInSet(paramArrayOfString1[b2], paramArrayOfString2))
        arrayOfString1[b1++] = paramArrayOfString1[b2]; 
    } 
    String[] arrayOfString2 = new String[b1];
    System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, b1);
    return arrayOfString2;
  }
  
  String[] union2sets(String[] paramArrayOfString1, String[] paramArrayOfString2) {
    String[] arrayOfString1 = new String[paramArrayOfString1.length];
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramArrayOfString1.length; b2++) {
      if (!elementInSet(paramArrayOfString1[b2], paramArrayOfString2))
        arrayOfString1[b1++] = paramArrayOfString1[b2]; 
    } 
    String[] arrayOfString2 = new String[b1 + paramArrayOfString2.length];
    System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, b1);
    System.arraycopy(paramArrayOfString2, 0, arrayOfString2, b1, paramArrayOfString2.length);
    return arrayOfString2;
  }
  
  boolean subset2sets(String[] paramArrayOfString1, String[] paramArrayOfString2) {
    for (byte b = 0; b < paramArrayOfString1.length; b++) {
      if (!elementInSet(paramArrayOfString1[b], paramArrayOfString2))
        return false; 
    } 
    return true;
  }
  
  boolean elementInSet(String paramString, String[] paramArrayOfString) {
    boolean bool = false;
    for (byte b = 0; b < paramArrayOfString.length && !bool; b++) {
      if (paramString == paramArrayOfString[b])
        bool = true; 
    } 
    return bool;
  }
  
  public String toString() {
    if (this.fDescription == null) {
      byte b;
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("WC[");
      switch (this.fType) {
        case 1:
          stringBuffer.append("##any");
          break;
        case 2:
          stringBuffer.append("##other");
          stringBuffer.append(":\"");
          if (this.fNamespaceList[0] != null)
            stringBuffer.append(this.fNamespaceList[0]); 
          stringBuffer.append("\"");
          break;
        case 3:
          if (this.fNamespaceList.length == 0)
            break; 
          stringBuffer.append("\"");
          if (this.fNamespaceList[0] != null)
            stringBuffer.append(this.fNamespaceList[0]); 
          stringBuffer.append("\"");
          for (b = 1; b < this.fNamespaceList.length; b++) {
            stringBuffer.append(",\"");
            if (this.fNamespaceList[b] != null)
              stringBuffer.append(this.fNamespaceList[b]); 
            stringBuffer.append("\"");
          } 
          break;
      } 
      stringBuffer.append(']');
      this.fDescription = stringBuffer.toString();
    } 
    return this.fDescription;
  }
  
  public short getType() {
    return 9;
  }
  
  public String getName() {
    return null;
  }
  
  public String getNamespace() {
    return null;
  }
  
  public short getConstraintType() {
    return this.fType;
  }
  
  public StringList getNsConstraintList() {
    return (StringList)new StringListImpl(this.fNamespaceList, (this.fNamespaceList == null) ? 0 : this.fNamespaceList.length);
  }
  
  public short getProcessContents() {
    return this.fProcessContents;
  }
  
  public String getProcessContentsAsString() {
    switch (this.fProcessContents) {
      case 2:
        return "skip";
      case 3:
        return "lax";
      case 1:
        return "strict";
    } 
    return "invalid value";
  }
  
  public XSAnnotation getAnnotation() {
    return (this.fAnnotations != null) ? (XSAnnotation)this.fAnnotations.item(0) : null;
  }
  
  public XSObjectList getAnnotations() {
    return (this.fAnnotations != null) ? this.fAnnotations : (XSObjectList)XSObjectListImpl.EMPTY_LIST;
  }
  
  public XSNamespaceItem getNamespaceItem() {
    return null;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xs/XSWildcardDecl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */