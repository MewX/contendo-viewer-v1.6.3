/*     */ package org.apache.xmlgraphics.image.codec.tiff;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*     */ import org.apache.xmlgraphics.image.codec.util.SeekableStream;
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
/*     */ public class TIFFDirectory
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2007844835460959003L;
/*     */   boolean isBigEndian;
/*     */   int numEntries;
/*     */   TIFFField[] fields;
/*  78 */   Map fieldIndex = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  81 */   long ifdOffset = 8L;
/*     */   
/*     */   long nextIFDOffset;
/*     */ 
/*     */   
/*     */   TIFFDirectory() {}
/*     */ 
/*     */   
/*     */   private static boolean isValidEndianTag(int endian) {
/*  90 */     return (endian == 18761 || endian == 19789);
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
/*     */   public TIFFDirectory(SeekableStream stream, int directory) throws IOException {
/* 106 */     long globalSaveOffset = stream.getFilePointer();
/*     */ 
/*     */ 
/*     */     
/* 110 */     stream.seek(0L);
/* 111 */     int endian = stream.readUnsignedShort();
/* 112 */     if (!isValidEndianTag(endian)) {
/* 113 */       throw new IllegalArgumentException(PropertyUtil.getString("TIFFDirectory1"));
/*     */     }
/* 115 */     this.isBigEndian = (endian == 19789);
/*     */     
/* 117 */     int magic = readUnsignedShort(stream);
/* 118 */     if (magic != 42) {
/* 119 */       throw new IllegalArgumentException(PropertyUtil.getString("TIFFDirectory2"));
/*     */     }
/*     */ 
/*     */     
/* 123 */     long ifdOffset = readUnsignedInt(stream);
/*     */     
/* 125 */     for (int i = 0; i < directory; i++) {
/* 126 */       if (ifdOffset == 0L) {
/* 127 */         throw new IllegalArgumentException(PropertyUtil.getString("TIFFDirectory3"));
/*     */       }
/*     */       
/* 130 */       stream.seek(ifdOffset);
/* 131 */       long entries = readUnsignedShort(stream);
/* 132 */       stream.skip(12L * entries);
/*     */       
/* 134 */       ifdOffset = readUnsignedInt(stream);
/*     */     } 
/* 136 */     if (ifdOffset == 0L) {
/* 137 */       throw new IllegalArgumentException(PropertyUtil.getString("TIFFDirectory3"));
/*     */     }
/*     */     
/* 140 */     stream.seek(ifdOffset);
/* 141 */     initialize(stream);
/* 142 */     stream.seek(globalSaveOffset);
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
/*     */   
/*     */   public TIFFDirectory(SeekableStream stream, long ifdOffset, int directory) throws IOException {
/* 161 */     long globalSaveOffset = stream.getFilePointer();
/* 162 */     stream.seek(0L);
/* 163 */     int endian = stream.readUnsignedShort();
/* 164 */     if (!isValidEndianTag(endian)) {
/* 165 */       throw new IllegalArgumentException(PropertyUtil.getString("TIFFDirectory1"));
/*     */     }
/* 167 */     this.isBigEndian = (endian == 19789);
/*     */ 
/*     */     
/* 170 */     stream.seek(ifdOffset);
/*     */ 
/*     */     
/* 173 */     int dirNum = 0;
/* 174 */     while (dirNum < directory) {
/*     */       
/* 176 */       long numEntries = readUnsignedShort(stream);
/*     */ 
/*     */       
/* 179 */       stream.seek(ifdOffset + 12L * numEntries);
/*     */ 
/*     */       
/* 182 */       ifdOffset = readUnsignedInt(stream);
/*     */ 
/*     */       
/* 185 */       stream.seek(ifdOffset);
/*     */ 
/*     */       
/* 188 */       dirNum++;
/*     */     } 
/*     */     
/* 191 */     initialize(stream);
/* 192 */     stream.seek(globalSaveOffset);
/*     */   }
/*     */   
/* 195 */   private static final int[] SIZE_OF_TYPE = new int[] { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8 };
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
/*     */   private void initialize(SeekableStream stream) throws IOException {
/* 216 */     this.ifdOffset = stream.getFilePointer();
/*     */     
/* 218 */     this.numEntries = readUnsignedShort(stream);
/* 219 */     this.fields = new TIFFField[this.numEntries];
/*     */     
/* 221 */     for (int i = 0; i < this.numEntries; i++) {
/* 222 */       int j; byte[] bvalues; char[] cvalues; long lvalues[], llvalues[][]; short[] svalues; int ivalues[], iivalues[][]; float[] fvalues; double[] dvalues; int tag = readUnsignedShort(stream);
/* 223 */       int type = readUnsignedShort(stream);
/* 224 */       int count = (int)readUnsignedInt(stream);
/* 225 */       int value = 0;
/*     */ 
/*     */       
/* 228 */       long nextTagOffset = stream.getFilePointer() + 4L;
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 233 */         if (count * SIZE_OF_TYPE[type] > 4) {
/* 234 */           value = (int)readUnsignedInt(stream);
/* 235 */           stream.seek(value);
/*     */         } 
/* 237 */       } catch (ArrayIndexOutOfBoundsException ae) {
/*     */ 
/*     */         
/* 240 */         stream.seek(nextTagOffset);
/*     */       } 
/*     */ 
/*     */       
/* 244 */       this.fieldIndex.put(Integer.valueOf(tag), Integer.valueOf(i));
/* 245 */       Object obj = null;
/*     */       
/* 247 */       switch (type) {
/*     */         case 1:
/*     */         case 2:
/*     */         case 6:
/*     */         case 7:
/* 252 */           bvalues = new byte[count];
/* 253 */           stream.readFully(bvalues, 0, count);
/*     */           
/* 255 */           if (type == 2) {
/*     */ 
/*     */             
/* 258 */             int index = 0;
/* 259 */             int prevIndex = 0;
/* 260 */             List<String> v = new ArrayList();
/*     */             
/* 262 */             while (index < count) {
/*     */               
/* 264 */               while (index < count && bvalues[index++] != 0);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 269 */               v.add(new String(bvalues, prevIndex, index - prevIndex, "UTF-8"));
/*     */               
/* 271 */               prevIndex = index;
/*     */             } 
/*     */             
/* 274 */             count = v.size();
/* 275 */             String[] strings = new String[count];
/* 276 */             v.toArray(strings);
/* 277 */             obj = strings; break;
/*     */           } 
/* 279 */           obj = bvalues;
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 285 */           cvalues = new char[count];
/* 286 */           for (j = 0; j < count; j++) {
/* 287 */             cvalues[j] = (char)readUnsignedShort(stream);
/*     */           }
/* 289 */           obj = cvalues;
/*     */           break;
/*     */         
/*     */         case 4:
/* 293 */           lvalues = new long[count];
/* 294 */           for (j = 0; j < count; j++) {
/* 295 */             lvalues[j] = readUnsignedInt(stream);
/*     */           }
/* 297 */           obj = lvalues;
/*     */           break;
/*     */         
/*     */         case 5:
/* 301 */           llvalues = new long[count][2];
/* 302 */           for (j = 0; j < count; j++) {
/* 303 */             llvalues[j][0] = readUnsignedInt(stream);
/* 304 */             llvalues[j][1] = readUnsignedInt(stream);
/*     */           } 
/* 306 */           obj = llvalues;
/*     */           break;
/*     */         
/*     */         case 8:
/* 310 */           svalues = new short[count];
/* 311 */           for (j = 0; j < count; j++) {
/* 312 */             svalues[j] = readShort(stream);
/*     */           }
/* 314 */           obj = svalues;
/*     */           break;
/*     */         
/*     */         case 9:
/* 318 */           ivalues = new int[count];
/* 319 */           for (j = 0; j < count; j++) {
/* 320 */             ivalues[j] = readInt(stream);
/*     */           }
/* 322 */           obj = ivalues;
/*     */           break;
/*     */         
/*     */         case 10:
/* 326 */           iivalues = new int[count][2];
/* 327 */           for (j = 0; j < count; j++) {
/* 328 */             iivalues[j][0] = readInt(stream);
/* 329 */             iivalues[j][1] = readInt(stream);
/*     */           } 
/* 331 */           obj = iivalues;
/*     */           break;
/*     */         
/*     */         case 11:
/* 335 */           fvalues = new float[count];
/* 336 */           for (j = 0; j < count; j++) {
/* 337 */             fvalues[j] = readFloat(stream);
/*     */           }
/* 339 */           obj = fvalues;
/*     */           break;
/*     */         
/*     */         case 12:
/* 343 */           dvalues = new double[count];
/* 344 */           for (j = 0; j < count; j++) {
/* 345 */             dvalues[j] = readDouble(stream);
/*     */           }
/* 347 */           obj = dvalues;
/*     */           break;
/*     */         
/*     */         default:
/* 351 */           throw new RuntimeException(PropertyUtil.getString("TIFFDirectory0"));
/*     */       } 
/*     */       
/* 354 */       this.fields[i] = new TIFFField(tag, type, count, obj);
/* 355 */       stream.seek(nextTagOffset);
/*     */     } 
/*     */ 
/*     */     
/* 359 */     this.nextIFDOffset = readUnsignedInt(stream);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumEntries() {
/* 364 */     return this.numEntries;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFField getField(int tag) {
/* 372 */     Integer i = (Integer)this.fieldIndex.get(Integer.valueOf(tag));
/* 373 */     if (i == null) {
/* 374 */       return null;
/*     */     }
/* 376 */     return this.fields[i.intValue()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTagPresent(int tag) {
/* 384 */     return this.fieldIndex.containsKey(Integer.valueOf(tag));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getTags() {
/* 392 */     int[] tags = new int[this.fieldIndex.size()];
/* 393 */     Iterator iter = this.fieldIndex.keySet().iterator();
/* 394 */     int i = 0;
/*     */     
/* 396 */     while (iter.hasNext()) {
/* 397 */       tags[i++] = ((Integer)iter.next()).intValue();
/*     */     }
/*     */     
/* 400 */     return tags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFField[] getFields() {
/* 408 */     return this.fields;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getFieldAsByte(int tag, int index) {
/* 418 */     Integer i = (Integer)this.fieldIndex.get(Integer.valueOf(tag));
/* 419 */     byte[] b = this.fields[i.intValue()].getAsBytes();
/* 420 */     return b[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getFieldAsByte(int tag) {
/* 430 */     return getFieldAsByte(tag, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFieldAsLong(int tag, int index) {
/* 440 */     Integer i = (Integer)this.fieldIndex.get(Integer.valueOf(tag));
/* 441 */     return this.fields[i.intValue()].getAsLong(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFieldAsLong(int tag) {
/* 451 */     return getFieldAsLong(tag, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFieldAsFloat(int tag, int index) {
/* 461 */     Integer i = (Integer)this.fieldIndex.get(Integer.valueOf(tag));
/* 462 */     return this.fields[i.intValue()].getAsFloat(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFieldAsFloat(int tag) {
/* 471 */     return getFieldAsFloat(tag, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFieldAsDouble(int tag, int index) {
/* 481 */     Integer i = (Integer)this.fieldIndex.get(Integer.valueOf(tag));
/* 482 */     return this.fields[i.intValue()].getAsDouble(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFieldAsDouble(int tag) {
/* 491 */     return getFieldAsDouble(tag, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short readShort(SeekableStream stream) throws IOException {
/* 498 */     if (this.isBigEndian) {
/* 499 */       return stream.readShort();
/*     */     }
/* 501 */     return stream.readShortLE();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int readUnsignedShort(SeekableStream stream) throws IOException {
/* 507 */     if (this.isBigEndian) {
/* 508 */       return stream.readUnsignedShort();
/*     */     }
/* 510 */     return stream.readUnsignedShortLE();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int readInt(SeekableStream stream) throws IOException {
/* 516 */     if (this.isBigEndian) {
/* 517 */       return stream.readInt();
/*     */     }
/* 519 */     return stream.readIntLE();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private long readUnsignedInt(SeekableStream stream) throws IOException {
/* 525 */     if (this.isBigEndian) {
/* 526 */       return stream.readUnsignedInt();
/*     */     }
/* 528 */     return stream.readUnsignedIntLE();
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
/*     */   private float readFloat(SeekableStream stream) throws IOException {
/* 543 */     if (this.isBigEndian) {
/* 544 */       return stream.readFloat();
/*     */     }
/* 546 */     return stream.readFloatLE();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double readDouble(SeekableStream stream) throws IOException {
/* 552 */     if (this.isBigEndian) {
/* 553 */       return stream.readDouble();
/*     */     }
/* 555 */     return stream.readDoubleLE();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int readUnsignedShort(SeekableStream stream, boolean isBigEndian) throws IOException {
/* 562 */     if (isBigEndian) {
/* 563 */       return stream.readUnsignedShort();
/*     */     }
/* 565 */     return stream.readUnsignedShortLE();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static long readUnsignedInt(SeekableStream stream, boolean isBigEndian) throws IOException {
/* 572 */     if (isBigEndian) {
/* 573 */       return stream.readUnsignedInt();
/*     */     }
/* 575 */     return stream.readUnsignedIntLE();
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
/*     */   public static int getNumDirectories(SeekableStream stream) throws IOException {
/* 587 */     long pointer = stream.getFilePointer();
/*     */     
/* 589 */     stream.seek(0L);
/* 590 */     int endian = stream.readUnsignedShort();
/* 591 */     if (!isValidEndianTag(endian)) {
/* 592 */       throw new IllegalArgumentException(PropertyUtil.getString("TIFFDirectory1"));
/*     */     }
/* 594 */     boolean isBigEndian = (endian == 19789);
/* 595 */     int magic = readUnsignedShort(stream, isBigEndian);
/* 596 */     if (magic != 42) {
/* 597 */       throw new IllegalArgumentException(PropertyUtil.getString("TIFFDirectory2"));
/*     */     }
/*     */     
/* 600 */     stream.seek(4L);
/* 601 */     long offset = readUnsignedInt(stream, isBigEndian);
/*     */     
/* 603 */     int numDirectories = 0;
/* 604 */     while (offset != 0L) {
/* 605 */       numDirectories++;
/*     */       
/* 607 */       stream.seek(offset);
/* 608 */       long entries = readUnsignedShort(stream, isBigEndian);
/* 609 */       stream.skip(12L * entries);
/* 610 */       offset = readUnsignedInt(stream, isBigEndian);
/*     */     } 
/*     */     
/* 613 */     stream.seek(pointer);
/* 614 */     return numDirectories;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBigEndian() {
/* 623 */     return this.isBigEndian;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getIFDOffset() {
/* 631 */     return this.ifdOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getNextIFDOffset() {
/* 639 */     return this.nextIFDOffset;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/TIFFDirectory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */