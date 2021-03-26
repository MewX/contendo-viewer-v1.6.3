/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFDirectory;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFTag;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.ImageOutputStream;
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
/*     */ public class TIFFIFD
/*     */   extends TIFFDirectory
/*     */ {
/*  69 */   private long stripOrTileByteCountsPosition = -1L;
/*  70 */   private long stripOrTileOffsetsPosition = -1L;
/*  71 */   private long lastPosition = -1L;
/*     */   
/*     */   public static TIFFTag getTag(int tagNumber, List tagSets) {
/*  74 */     Iterator<TIFFTagSet> iter = tagSets.iterator();
/*  75 */     while (iter.hasNext()) {
/*  76 */       TIFFTagSet tagSet = iter.next();
/*  77 */       TIFFTag tag = tagSet.getTag(tagNumber);
/*  78 */       if (tag != null) {
/*  79 */         return tag;
/*     */       }
/*     */     } 
/*     */     
/*  83 */     return null;
/*     */   }
/*     */   
/*     */   public static TIFFTag getTag(String tagName, List tagSets) {
/*  87 */     Iterator<TIFFTagSet> iter = tagSets.iterator();
/*  88 */     while (iter.hasNext()) {
/*  89 */       TIFFTagSet tagSet = iter.next();
/*  90 */       TIFFTag tag = tagSet.getTag(tagName);
/*  91 */       if (tag != null) {
/*  92 */         return tag;
/*     */       }
/*     */     } 
/*     */     
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeTIFFFieldToStream(TIFFField field, ImageOutputStream stream) throws IOException {
/* 102 */     int i, count = field.getCount();
/* 103 */     Object data = field.getData();
/*     */     
/* 105 */     switch (field.getType()) {
/*     */       case 2:
/* 107 */         for (i = 0; i < count; i++) {
/* 108 */           String s = ((String[])data)[i];
/* 109 */           int length = s.length();
/* 110 */           for (int j = 0; j < length; j++) {
/* 111 */             stream.writeByte(s.charAt(j) & 0xFF);
/*     */           }
/* 113 */           stream.writeByte(0);
/*     */         } 
/*     */         break;
/*     */       case 1:
/*     */       case 6:
/*     */       case 7:
/* 119 */         stream.write((byte[])data);
/*     */         break;
/*     */       case 3:
/* 122 */         stream.writeChars((char[])data, 0, ((char[])data).length);
/*     */         break;
/*     */       case 8:
/* 125 */         stream.writeShorts((short[])data, 0, ((short[])data).length);
/*     */         break;
/*     */       case 9:
/* 128 */         stream.writeInts((int[])data, 0, ((int[])data).length);
/*     */         break;
/*     */       case 4:
/* 131 */         for (i = 0; i < count; i++) {
/* 132 */           stream.writeInt((int)((long[])data)[i]);
/*     */         }
/*     */         break;
/*     */       case 13:
/* 136 */         stream.writeInt(0);
/*     */         break;
/*     */       case 11:
/* 139 */         stream.writeFloats((float[])data, 0, ((float[])data).length);
/*     */         break;
/*     */       case 12:
/* 142 */         stream.writeDoubles((double[])data, 0, ((double[])data).length);
/*     */         break;
/*     */       case 10:
/* 145 */         for (i = 0; i < count; i++) {
/* 146 */           stream.writeInt(((int[][])data)[i][0]);
/* 147 */           stream.writeInt(((int[][])data)[i][1]);
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 151 */         for (i = 0; i < count; i++) {
/* 152 */           long num = ((long[][])data)[i][0];
/* 153 */           long den = ((long[][])data)[i][1];
/* 154 */           stream.writeInt((int)num);
/* 155 */           stream.writeInt((int)den);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFIFD(List tagSets, TIFFTag parentTag) {
/* 164 */     super((TIFFTagSet[])tagSets.toArray((Object[])new TIFFTagSet[tagSets.size()]), parentTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public TIFFIFD(List tagSets) {
/* 169 */     this(tagSets, (TIFFTag)null);
/*     */   }
/*     */   
/*     */   public List getTagSetList() {
/* 173 */     return Arrays.asList(getTagSets());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 184 */     return Arrays.<TIFFField>asList(getTIFFFields()).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize(ImageInputStream stream, boolean ignoreUnknownFields) throws IOException {
/* 192 */     removeTIFFFields();
/*     */     
/* 194 */     List tagSetList = getTagSetList();
/*     */     
/* 196 */     int numEntries = stream.readUnsignedShort();
/* 197 */     for (int i = 0; i < numEntries; i++) {
/*     */       
/* 199 */       int tag = stream.readUnsignedShort();
/* 200 */       int type = stream.readUnsignedShort();
/* 201 */       int count = (int)stream.readUnsignedInt();
/*     */ 
/*     */       
/* 204 */       TIFFTag tiffTag = getTag(tag, tagSetList);
/*     */ 
/*     */       
/* 207 */       if (ignoreUnknownFields && tiffTag == null) {
/*     */ 
/*     */         
/* 210 */         stream.skipBytes(4);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 218 */         long nextTagOffset = stream.getStreamPosition() + 4L;
/*     */         
/* 220 */         int sizeOfType = TIFFTag.getSizeOfType(type);
/* 221 */         if (count * sizeOfType > 4) {
/* 222 */           long value = stream.readUnsignedInt();
/* 223 */           stream.seek(value);
/*     */         } 
/*     */         
/* 226 */         if (tag == 279 || tag == 325 || tag == 514) {
/*     */ 
/*     */           
/* 229 */           this
/* 230 */             .stripOrTileByteCountsPosition = stream.getStreamPosition();
/* 231 */         } else if (tag == 273 || tag == 324 || tag == 513) {
/*     */ 
/*     */           
/* 234 */           this
/* 235 */             .stripOrTileOffsetsPosition = stream.getStreamPosition();
/*     */         } 
/*     */         
/* 238 */         Object obj = null; try {
/*     */           byte[] bvalues; char[] cvalues; int j; long[] lvalues; int k; long[][] llvalues; int m; short[] svalues; int n; int[] ivalues; int i1; int[][] iivalues; int i2; float[] fvalues; int i3; double[] dvalues;
/*     */           int i4;
/* 241 */           switch (type) {
/*     */             case 1:
/*     */             case 2:
/*     */             case 6:
/*     */             case 7:
/* 246 */               bvalues = new byte[count];
/* 247 */               stream.readFully(bvalues, 0, count);
/*     */               
/* 249 */               if (type == 2) {
/*     */                 String[] strings;
/* 251 */                 Vector<String> v = new Vector();
/* 252 */                 boolean inString = false;
/* 253 */                 int prevIndex = 0;
/* 254 */                 for (int index = 0; index <= count; index++) {
/* 255 */                   if (index < count && bvalues[index] != 0) {
/* 256 */                     if (!inString)
/*     */                     {
/* 258 */                       prevIndex = index;
/* 259 */                       inString = true;
/*     */                     }
/*     */                   
/* 262 */                   } else if (inString) {
/*     */                     
/* 264 */                     String s = new String(bvalues, prevIndex, index - prevIndex);
/*     */                     
/* 266 */                     v.add(s);
/* 267 */                     inString = false;
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/* 272 */                 count = v.size();
/*     */                 
/* 274 */                 if (count != 0) {
/* 275 */                   strings = new String[count];
/* 276 */                   for (int c = 0; c < count; c++) {
/* 277 */                     strings[c] = v.elementAt(c);
/*     */                   
/*     */                   }
/*     */                 }
/*     */                 else {
/*     */                   
/* 283 */                   count = 1;
/* 284 */                   strings = new String[] { "" };
/*     */                 } 
/*     */                 
/* 287 */                 obj = strings; break;
/*     */               } 
/* 289 */               obj = bvalues;
/*     */               break;
/*     */ 
/*     */             
/*     */             case 3:
/* 294 */               cvalues = new char[count];
/* 295 */               for (j = 0; j < count; j++) {
/* 296 */                 cvalues[j] = (char)stream.readUnsignedShort();
/*     */               }
/* 298 */               obj = cvalues;
/*     */               break;
/*     */             
/*     */             case 4:
/*     */             case 13:
/* 303 */               lvalues = new long[count];
/* 304 */               for (k = 0; k < count; k++) {
/* 305 */                 lvalues[k] = stream.readUnsignedInt();
/*     */               }
/* 307 */               obj = lvalues;
/*     */               break;
/*     */             
/*     */             case 5:
/* 311 */               llvalues = new long[count][2];
/* 312 */               for (m = 0; m < count; m++) {
/* 313 */                 llvalues[m][0] = stream.readUnsignedInt();
/* 314 */                 llvalues[m][1] = stream.readUnsignedInt();
/*     */               } 
/* 316 */               obj = llvalues;
/*     */               break;
/*     */             
/*     */             case 8:
/* 320 */               svalues = new short[count];
/* 321 */               for (n = 0; n < count; n++) {
/* 322 */                 svalues[n] = stream.readShort();
/*     */               }
/* 324 */               obj = svalues;
/*     */               break;
/*     */             
/*     */             case 9:
/* 328 */               ivalues = new int[count];
/* 329 */               for (i1 = 0; i1 < count; i1++) {
/* 330 */                 ivalues[i1] = stream.readInt();
/*     */               }
/* 332 */               obj = ivalues;
/*     */               break;
/*     */             
/*     */             case 10:
/* 336 */               iivalues = new int[count][2];
/* 337 */               for (i2 = 0; i2 < count; i2++) {
/* 338 */                 iivalues[i2][0] = stream.readInt();
/* 339 */                 iivalues[i2][1] = stream.readInt();
/*     */               } 
/* 341 */               obj = iivalues;
/*     */               break;
/*     */             
/*     */             case 11:
/* 345 */               fvalues = new float[count];
/* 346 */               for (i3 = 0; i3 < count; i3++) {
/* 347 */                 fvalues[i3] = stream.readFloat();
/*     */               }
/* 349 */               obj = fvalues;
/*     */               break;
/*     */             
/*     */             case 12:
/* 353 */               dvalues = new double[count];
/* 354 */               for (i4 = 0; i4 < count; i4++) {
/* 355 */                 dvalues[i4] = stream.readDouble();
/*     */               }
/* 357 */               obj = dvalues;
/*     */               break;
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 364 */         } catch (EOFException eofe) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 370 */           if (BaselineTIFFTagSet.getInstance().getTag(tag) == null) {
/* 371 */             throw eofe;
/*     */           }
/*     */         } 
/*     */         
/* 375 */         if (tiffTag != null)
/*     */         {
/* 377 */           if (tiffTag.isDataTypeOK(type))
/*     */           {
/* 379 */             if (tiffTag.isIFDPointer() && obj != null) {
/* 380 */               stream.mark();
/* 381 */               stream.seek(((long[])obj)[0]);
/*     */               
/* 383 */               List<TIFFTagSet> tagSets = new ArrayList(1);
/* 384 */               tagSets.add(tiffTag.getTagSet());
/* 385 */               TIFFIFD subIFD = new TIFFIFD(tagSets);
/*     */ 
/*     */               
/* 388 */               subIFD.initialize(stream, ignoreUnknownFields);
/* 389 */               obj = subIFD;
/* 390 */               stream.reset();
/*     */             }  } 
/*     */         }
/* 393 */         if (tiffTag == null) {
/* 394 */           tiffTag = new TIFFTag(null, tag, 1 << type, null);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 399 */         if (obj != null) {
/* 400 */           TIFFField f = new TIFFField(tiffTag, type, count, obj);
/* 401 */           addTIFFField(f);
/*     */         } 
/*     */         
/* 404 */         stream.seek(nextTagOffset);
/*     */       } 
/*     */     } 
/* 407 */     this.lastPosition = stream.getStreamPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToStream(ImageOutputStream stream) throws IOException {
/* 413 */     int numFields = getNumTIFFFields();
/* 414 */     stream.writeShort(numFields);
/*     */     
/* 416 */     long nextSpace = stream.getStreamPosition() + (12 * numFields) + 4L;
/*     */     
/* 418 */     Iterator<TIFFField> iter = iterator();
/* 419 */     while (iter.hasNext()) {
/* 420 */       long pos; TIFFField f = iter.next();
/*     */       
/* 422 */       TIFFTag tag = f.getTag();
/*     */       
/* 424 */       int type = f.getType();
/* 425 */       int count = f.getCount();
/*     */ 
/*     */       
/* 428 */       if (type == 0) {
/* 429 */         type = 7;
/*     */       }
/* 431 */       int size = count * TIFFTag.getSizeOfType(type);
/*     */       
/* 433 */       if (type == 2) {
/* 434 */         int chars = 0;
/* 435 */         for (int i = 0; i < count; i++) {
/* 436 */           chars += f.getAsString(i).length() + 1;
/*     */         }
/* 438 */         count = chars;
/* 439 */         size = count;
/*     */       } 
/*     */       
/* 442 */       int tagNumber = f.getTagNumber();
/* 443 */       stream.writeShort(tagNumber);
/* 444 */       stream.writeShort(type);
/* 445 */       stream.writeInt(count);
/*     */ 
/*     */       
/* 448 */       stream.writeInt(0);
/* 449 */       stream.mark();
/* 450 */       stream.skipBytes(-4);
/*     */ 
/*     */ 
/*     */       
/* 454 */       if (size > 4 || tag.isIFDPointer()) {
/*     */         
/* 456 */         nextSpace = nextSpace + 3L & 0xFFFFFFFFFFFFFFFCL;
/*     */         
/* 458 */         stream.writeInt((int)nextSpace);
/* 459 */         stream.seek(nextSpace);
/* 460 */         pos = nextSpace;
/*     */         
/* 462 */         if (tag.isIFDPointer()) {
/* 463 */           TIFFIFD subIFD = (TIFFIFD)f.getData();
/* 464 */           subIFD.writeToStream(stream);
/* 465 */           nextSpace = subIFD.lastPosition;
/*     */         } else {
/* 467 */           writeTIFFFieldToStream(f, stream);
/* 468 */           nextSpace = stream.getStreamPosition();
/*     */         } 
/*     */       } else {
/* 471 */         pos = stream.getStreamPosition();
/* 472 */         writeTIFFFieldToStream(f, stream);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 480 */       if (tagNumber == 279 || tagNumber == 325 || tagNumber == 514) {
/*     */ 
/*     */ 
/*     */         
/* 484 */         this.stripOrTileByteCountsPosition = pos;
/* 485 */       } else if (tagNumber == 273 || tagNumber == 324 || tagNumber == 513) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 491 */         this.stripOrTileOffsetsPosition = pos;
/*     */       } 
/*     */       
/* 494 */       stream.reset();
/*     */     } 
/*     */     
/* 497 */     this.lastPosition = nextSpace;
/*     */   }
/*     */   
/*     */   public long getStripOrTileByteCountsPosition() {
/* 501 */     return this.stripOrTileByteCountsPosition;
/*     */   }
/*     */   
/*     */   public long getStripOrTileOffsetsPosition() {
/* 505 */     return this.stripOrTileOffsetsPosition;
/*     */   }
/*     */   
/*     */   public long getLastPosition() {
/* 509 */     return this.lastPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setPositions(long stripOrTileOffsetsPosition, long stripOrTileByteCountsPosition, long lastPosition) {
/* 515 */     this.stripOrTileOffsetsPosition = stripOrTileOffsetsPosition;
/* 516 */     this.stripOrTileByteCountsPosition = stripOrTileByteCountsPosition;
/* 517 */     this.lastPosition = lastPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFIFD getShallowClone() {
/* 527 */     BaselineTIFFTagSet baselineTIFFTagSet = BaselineTIFFTagSet.getInstance();
/*     */ 
/*     */     
/* 530 */     List tagSetList = getTagSetList();
/* 531 */     if (!tagSetList.contains(baselineTIFFTagSet)) {
/* 532 */       return this;
/*     */     }
/*     */ 
/*     */     
/* 536 */     TIFFIFD shallowClone = new TIFFIFD(tagSetList, getParentTag());
/*     */ 
/*     */     
/* 539 */     Set baselineTagNumbers = baselineTIFFTagSet.getTagNumbers();
/*     */ 
/*     */     
/* 542 */     Iterator<TIFFField> fields = iterator();
/* 543 */     while (fields.hasNext()) {
/*     */       
/* 545 */       TIFFField fieldClone, field = fields.next();
/*     */ 
/*     */       
/* 548 */       Integer tagNumber = new Integer(field.getTagNumber());
/*     */ 
/*     */ 
/*     */       
/* 552 */       if (baselineTagNumbers.contains(tagNumber)) {
/*     */         
/* 554 */         Object fieldData = field.getData();
/*     */         
/* 556 */         int fieldType = field.getType();
/*     */         
/*     */         try {
/* 559 */           switch (fieldType) {
/*     */             case 1:
/*     */             case 6:
/*     */             case 7:
/* 563 */               fieldData = ((byte[])fieldData).clone();
/*     */               break;
/*     */             case 2:
/* 566 */               fieldData = ((String[])fieldData).clone();
/*     */               break;
/*     */             case 3:
/* 569 */               fieldData = ((char[])fieldData).clone();
/*     */               break;
/*     */             case 4:
/*     */             case 13:
/* 573 */               fieldData = ((long[])fieldData).clone();
/*     */               break;
/*     */             case 5:
/* 576 */               fieldData = ((long[][])fieldData).clone();
/*     */               break;
/*     */             case 8:
/* 579 */               fieldData = ((short[])fieldData).clone();
/*     */               break;
/*     */             case 9:
/* 582 */               fieldData = ((int[])fieldData).clone();
/*     */               break;
/*     */             case 10:
/* 585 */               fieldData = ((int[][])fieldData).clone();
/*     */               break;
/*     */             case 11:
/* 588 */               fieldData = ((float[])fieldData).clone();
/*     */               break;
/*     */             case 12:
/* 591 */               fieldData = ((double[])fieldData).clone();
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/* 596 */         } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 601 */         fieldClone = new TIFFField(field.getTag(), fieldType, field.getCount(), fieldData);
/*     */       } else {
/*     */         
/* 604 */         fieldClone = field;
/*     */       } 
/*     */ 
/*     */       
/* 608 */       shallowClone.addTIFFField(fieldClone);
/*     */     } 
/*     */ 
/*     */     
/* 612 */     shallowClone.setPositions(this.stripOrTileOffsetsPosition, this.stripOrTileByteCountsPosition, this.lastPosition);
/*     */ 
/*     */ 
/*     */     
/* 616 */     return shallowClone;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFIFD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */