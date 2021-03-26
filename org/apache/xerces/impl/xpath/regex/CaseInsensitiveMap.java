package org.apache.xerces.impl.xpath.regex;

final class CaseInsensitiveMap {
  private static int CHUNK_SHIFT = 10;
  
  private static int CHUNK_SIZE = 1 << CHUNK_SHIFT;
  
  private static int CHUNK_MASK = CHUNK_SIZE - 1;
  
  private static int INITIAL_CHUNK_COUNT = 64;
  
  private static int[][][] caseInsensitiveMap;
  
  private static int LOWER_CASE_MATCH = 1;
  
  private static int UPPER_CASE_MATCH = 2;
  
  public static int[] get(int paramInt) {
    return (paramInt < 65536) ? getMapping(paramInt) : null;
  }
  
  private static int[] getMapping(int paramInt) {
    int i = paramInt >>> CHUNK_SHIFT;
    int j = paramInt & CHUNK_MASK;
    return caseInsensitiveMap[i][j];
  }
  
  private static void buildCaseInsensitiveMap() {
    caseInsensitiveMap = new int[INITIAL_CHUNK_COUNT][CHUNK_SIZE][];
    for (byte b = 0; b < 65536; b++) {
      char c1 = Character.toLowerCase((char)b);
      char c2 = Character.toUpperCase((char)b);
      if (c1 != c2 || c1 != b) {
        int[] arrayOfInt = new int[2];
        byte b1 = 0;
        if (c1 != b) {
          arrayOfInt[b1++] = c1;
          arrayOfInt[b1++] = LOWER_CASE_MATCH;
          int[] arrayOfInt1 = getMapping(c1);
          if (arrayOfInt1 != null)
            arrayOfInt = updateMap(b, arrayOfInt, c1, arrayOfInt1, LOWER_CASE_MATCH); 
        } 
        if (c2 != b) {
          if (b1 == arrayOfInt.length)
            arrayOfInt = expandMap(arrayOfInt, 2); 
          arrayOfInt[b1++] = c2;
          arrayOfInt[b1++] = UPPER_CASE_MATCH;
          int[] arrayOfInt1 = getMapping(c2);
          if (arrayOfInt1 != null)
            arrayOfInt = updateMap(b, arrayOfInt, c2, arrayOfInt1, UPPER_CASE_MATCH); 
        } 
        set(b, arrayOfInt);
      } 
    } 
  }
  
  private static int[] expandMap(int[] paramArrayOfint, int paramInt) {
    int i = paramArrayOfint.length;
    int[] arrayOfInt = new int[i + paramInt];
    System.arraycopy(paramArrayOfint, 0, arrayOfInt, 0, i);
    return arrayOfInt;
  }
  
  private static void set(int paramInt, int[] paramArrayOfint) {
    int i = paramInt >>> CHUNK_SHIFT;
    int j = paramInt & CHUNK_MASK;
    caseInsensitiveMap[i][j] = paramArrayOfint;
  }
  
  private static int[] updateMap(int paramInt1, int[] paramArrayOfint1, int paramInt2, int[] paramArrayOfint2, int paramInt3) {
    for (byte b = 0; b < paramArrayOfint2.length; b += 2) {
      int i = paramArrayOfint2[b];
      int[] arrayOfInt = getMapping(i);
      if (arrayOfInt != null && contains(arrayOfInt, paramInt2, paramInt3)) {
        if (!contains(arrayOfInt, paramInt1)) {
          arrayOfInt = expandAndAdd(arrayOfInt, paramInt1, paramInt3);
          set(i, arrayOfInt);
        } 
        if (!contains(paramArrayOfint1, i))
          paramArrayOfint1 = expandAndAdd(paramArrayOfint1, i, paramInt3); 
      } 
    } 
    if (!contains(paramArrayOfint2, paramInt1)) {
      paramArrayOfint2 = expandAndAdd(paramArrayOfint2, paramInt1, paramInt3);
      set(paramInt2, paramArrayOfint2);
    } 
    return paramArrayOfint1;
  }
  
  private static boolean contains(int[] paramArrayOfint, int paramInt) {
    for (byte b = 0; b < paramArrayOfint.length; b += 2) {
      if (paramArrayOfint[b] == paramInt)
        return true; 
    } 
    return false;
  }
  
  private static boolean contains(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    for (byte b = 0; b < paramArrayOfint.length; b += 2) {
      if (paramArrayOfint[b] == paramInt1 && paramArrayOfint[b + 1] == paramInt2)
        return true; 
    } 
    return false;
  }
  
  private static int[] expandAndAdd(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    int i = paramArrayOfint.length;
    int[] arrayOfInt = new int[i + 2];
    System.arraycopy(paramArrayOfint, 0, arrayOfInt, 0, i);
    arrayOfInt[i] = paramInt1;
    arrayOfInt[i + 1] = paramInt2;
    return arrayOfInt;
  }
  
  static {
    buildCaseInsensitiveMap();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/xpath/regex/CaseInsensitiveMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */