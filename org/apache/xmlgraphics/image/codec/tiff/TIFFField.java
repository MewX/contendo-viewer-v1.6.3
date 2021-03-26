/*     */ package org.apache.xmlgraphics.image.codec.tiff;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFField
/*     */   implements Serializable, Comparable
/*     */ {
/*     */   public static final int TIFF_BYTE = 1;
/*     */   public static final int TIFF_ASCII = 2;
/*     */   public static final int TIFF_SHORT = 3;
/*     */   public static final int TIFF_LONG = 4;
/*     */   public static final int TIFF_RATIONAL = 5;
/*     */   public static final int TIFF_SBYTE = 6;
/*     */   public static final int TIFF_UNDEFINED = 7;
/*     */   public static final int TIFF_SSHORT = 8;
/*     */   public static final int TIFF_SLONG = 9;
/*     */   public static final int TIFF_SRATIONAL = 10;
/*     */   public static final int TIFF_FLOAT = 11;
/*     */   public static final int TIFF_DOUBLE = 12;
/*     */   private static final long serialVersionUID = 207783128222415437L;
/*     */   int tag;
/*     */   int type;
/*     */   int count;
/*     */   Object data;
/*     */   
/*     */   TIFFField() {}
/*     */   
/*     */   public TIFFField(int tag, int type, int count, Object data) {
/* 133 */     this.tag = tag;
/* 134 */     this.type = type;
/* 135 */     this.count = count;
/* 136 */     this.data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTag() {
/* 143 */     return this.tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 154 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 161 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getAsBytes() {
/* 177 */     return (byte[])this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char[] getAsChars() {
/* 188 */     return (char[])this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short[] getAsShorts() {
/* 199 */     return (short[])this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getAsInts() {
/* 210 */     return (int[])this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long[] getAsLongs() {
/* 221 */     return (long[])this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getAsFloats() {
/* 231 */     return (float[])this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getAsDoubles() {
/* 241 */     return (double[])this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[][] getAsSRationals() {
/* 251 */     return (int[][])this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long[][] getAsRationals() {
/* 261 */     return (long[][])this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAsInt(int index) {
/* 278 */     switch (this.type) { case 1:
/*     */       case 7:
/* 280 */         return ((byte[])this.data)[index] & 0xFF;
/*     */       case 6:
/* 282 */         return ((byte[])this.data)[index];
/*     */       case 3:
/* 284 */         return ((char[])this.data)[index] & Character.MAX_VALUE;
/*     */       case 8:
/* 286 */         return ((short[])this.data)[index];
/*     */       case 9:
/* 288 */         return ((int[])this.data)[index]; }
/*     */     
/* 290 */     throw new ClassCastException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getAsLong(int index) {
/* 308 */     switch (this.type) { case 1:
/*     */       case 7:
/* 310 */         return (((byte[])this.data)[index] & 0xFF);
/*     */       case 6:
/* 312 */         return ((byte[])this.data)[index];
/*     */       case 3:
/* 314 */         return (((char[])this.data)[index] & Character.MAX_VALUE);
/*     */       case 8:
/* 316 */         return ((short[])this.data)[index];
/*     */       case 9:
/* 318 */         return ((int[])this.data)[index];
/*     */       case 4:
/* 320 */         return ((long[])this.data)[index]; }
/*     */     
/* 322 */     throw new ClassCastException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAsFloat(int index) {
/*     */     int[] ivalue;
/*     */     long[] lvalue;
/* 338 */     switch (this.type) {
/*     */       case 1:
/* 340 */         return (((byte[])this.data)[index] & 0xFF);
/*     */       case 6:
/* 342 */         return ((byte[])this.data)[index];
/*     */       case 3:
/* 344 */         return (((char[])this.data)[index] & Character.MAX_VALUE);
/*     */       case 8:
/* 346 */         return ((short[])this.data)[index];
/*     */       case 9:
/* 348 */         return ((int[])this.data)[index];
/*     */       case 4:
/* 350 */         return (float)((long[])this.data)[index];
/*     */       case 11:
/* 352 */         return ((float[])this.data)[index];
/*     */       case 12:
/* 354 */         return (float)((double[])this.data)[index];
/*     */       case 10:
/* 356 */         ivalue = getAsSRational(index);
/* 357 */         return (float)(ivalue[0] / ivalue[1]);
/*     */       case 5:
/* 359 */         lvalue = getAsRational(index);
/* 360 */         return (float)(lvalue[0] / lvalue[1]);
/*     */     } 
/* 362 */     throw new ClassCastException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAsDouble(int index) {
/*     */     int[] ivalue;
/*     */     long[] lvalue;
/* 376 */     switch (this.type) {
/*     */       case 1:
/* 378 */         return (((byte[])this.data)[index] & 0xFF);
/*     */       case 6:
/* 380 */         return ((byte[])this.data)[index];
/*     */       case 3:
/* 382 */         return (((char[])this.data)[index] & Character.MAX_VALUE);
/*     */       case 8:
/* 384 */         return ((short[])this.data)[index];
/*     */       case 9:
/* 386 */         return ((int[])this.data)[index];
/*     */       case 4:
/* 388 */         return ((long[])this.data)[index];
/*     */       case 11:
/* 390 */         return ((float[])this.data)[index];
/*     */       case 12:
/* 392 */         return ((double[])this.data)[index];
/*     */       case 10:
/* 394 */         ivalue = getAsSRational(index);
/* 395 */         return ivalue[0] / ivalue[1];
/*     */       case 5:
/* 397 */         lvalue = getAsRational(index);
/* 398 */         return lvalue[0] / lvalue[1];
/*     */     } 
/* 400 */     throw new ClassCastException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAsString(int index) {
/* 411 */     return ((String[])this.data)[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getAsSRational(int index) {
/* 422 */     return ((int[][])this.data)[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long[] getAsRational(int index) {
/* 433 */     return ((long[][])this.data)[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 448 */     if (o == null) {
/* 449 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 452 */     int oTag = ((TIFFField)o).getTag();
/*     */     
/* 454 */     if (this.tag < oTag)
/* 455 */       return -1; 
/* 456 */     if (this.tag > oTag) {
/* 457 */       return 1;
/*     */     }
/* 459 */     return 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/TIFFField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */