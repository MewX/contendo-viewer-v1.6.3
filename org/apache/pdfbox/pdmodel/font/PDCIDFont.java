/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInputStream;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.util.Matrix;
/*     */ import org.apache.pdfbox.util.Vector;
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
/*     */ public abstract class PDCIDFont
/*     */   implements COSObjectable, PDFontLike, PDVectorFont
/*     */ {
/*     */   protected final PDType0Font parent;
/*     */   private Map<Integer, Float> widths;
/*     */   private float defaultWidth;
/*     */   private float averageWidth;
/*  52 */   private final Map<Integer, Float> verticalDisplacementY = new HashMap<Integer, Float>();
/*  53 */   private final Map<Integer, Vector> positionVectors = new HashMap<Integer, Vector>();
/*  54 */   private float[] dw2 = new float[] { 880.0F, -1000.0F };
/*     */ 
/*     */ 
/*     */   
/*     */   protected final COSDictionary dict;
/*     */ 
/*     */   
/*     */   private PDFontDescriptor fontDescriptor;
/*     */ 
/*     */ 
/*     */   
/*     */   PDCIDFont(COSDictionary fontDictionary, PDType0Font parent) throws IOException {
/*  66 */     this.dict = fontDictionary;
/*  67 */     this.parent = parent;
/*  68 */     readWidths();
/*  69 */     readVerticalDisplacements();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readWidths() {
/*  74 */     this.widths = new HashMap<Integer, Float>();
/*  75 */     COSBase wBase = this.dict.getDictionaryObject(COSName.W);
/*  76 */     if (wBase instanceof COSArray) {
/*     */       
/*  78 */       COSArray wArray = (COSArray)wBase;
/*  79 */       int size = wArray.size();
/*  80 */       int counter = 0;
/*  81 */       while (counter < size) {
/*     */         
/*  83 */         COSNumber firstCode = (COSNumber)wArray.getObject(counter++);
/*  84 */         COSBase next = wArray.getObject(counter++);
/*  85 */         if (next instanceof COSArray) {
/*     */           
/*  87 */           COSArray array = (COSArray)next;
/*  88 */           int j = firstCode.intValue();
/*  89 */           int arraySize = array.size();
/*  90 */           for (int k = 0; k < arraySize; k++) {
/*     */             
/*  92 */             COSNumber cOSNumber = (COSNumber)array.getObject(k);
/*  93 */             this.widths.put(Integer.valueOf(j + k), Float.valueOf(cOSNumber.floatValue()));
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/*  98 */         COSNumber secondCode = (COSNumber)next;
/*  99 */         COSNumber rangeWidth = (COSNumber)wArray.getObject(counter++);
/* 100 */         int startRange = firstCode.intValue();
/* 101 */         int endRange = secondCode.intValue();
/* 102 */         float width = rangeWidth.floatValue();
/* 103 */         for (int i = startRange; i <= endRange; i++)
/*     */         {
/* 105 */           this.widths.put(Integer.valueOf(i), Float.valueOf(width));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readVerticalDisplacements() {
/* 115 */     COSBase dw2Base = this.dict.getDictionaryObject(COSName.DW2);
/* 116 */     if (dw2Base instanceof COSArray) {
/*     */       
/* 118 */       COSArray dw2Array = (COSArray)dw2Base;
/* 119 */       COSBase base0 = dw2Array.getObject(0);
/* 120 */       COSBase base1 = dw2Array.getObject(1);
/* 121 */       if (base0 instanceof COSNumber && base1 instanceof COSNumber) {
/*     */         
/* 123 */         this.dw2[0] = ((COSNumber)base0).floatValue();
/* 124 */         this.dw2[1] = ((COSNumber)base1).floatValue();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 129 */     COSBase w2Base = this.dict.getDictionaryObject(COSName.W2);
/* 130 */     if (w2Base instanceof COSArray) {
/*     */       
/* 132 */       COSArray w2Array = (COSArray)w2Base;
/* 133 */       for (int i = 0; i < w2Array.size(); i++) {
/*     */         
/* 135 */         COSNumber c = (COSNumber)w2Array.getObject(i);
/* 136 */         COSBase next = w2Array.getObject(++i);
/* 137 */         if (next instanceof COSArray) {
/*     */           
/* 139 */           COSArray array = (COSArray)next;
/* 140 */           for (int j = 0; j < array.size(); j++)
/*     */           {
/* 142 */             int cid = c.intValue() + j / 3;
/* 143 */             COSNumber w1y = (COSNumber)array.getObject(j);
/* 144 */             COSNumber v1x = (COSNumber)array.getObject(++j);
/* 145 */             COSNumber v1y = (COSNumber)array.getObject(++j);
/* 146 */             this.verticalDisplacementY.put(Integer.valueOf(cid), Float.valueOf(w1y.floatValue()));
/* 147 */             this.positionVectors.put(Integer.valueOf(cid), new Vector(v1x.floatValue(), v1y.floatValue()));
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 152 */           int first = c.intValue();
/* 153 */           int last = ((COSNumber)next).intValue();
/* 154 */           COSNumber w1y = (COSNumber)w2Array.getObject(++i);
/* 155 */           COSNumber v1x = (COSNumber)w2Array.getObject(++i);
/* 156 */           COSNumber v1y = (COSNumber)w2Array.getObject(++i);
/* 157 */           for (int cid = first; cid <= last; cid++) {
/*     */             
/* 159 */             this.verticalDisplacementY.put(Integer.valueOf(cid), Float.valueOf(w1y.floatValue()));
/* 160 */             this.positionVectors.put(Integer.valueOf(cid), new Vector(v1x.floatValue(), v1y.floatValue()));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/* 170 */     return this.dict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseFont() {
/* 180 */     return this.dict.getNameAsString(COSName.BASE_FONT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 186 */     return getBaseFont();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFontDescriptor getFontDescriptor() {
/* 192 */     if (this.fontDescriptor == null) {
/*     */       
/* 194 */       COSDictionary fd = (COSDictionary)this.dict.getDictionaryObject(COSName.FONT_DESC);
/* 195 */       if (fd != null)
/*     */       {
/* 197 */         this.fontDescriptor = new PDFontDescriptor(fd);
/*     */       }
/*     */     } 
/* 200 */     return this.fontDescriptor;
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
/*     */   public final PDType0Font getParent() {
/* 213 */     return this.parent;
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
/*     */   private float getDefaultWidth() {
/* 226 */     if (this.defaultWidth == 0.0F) {
/*     */       
/* 228 */       COSBase base = this.dict.getDictionaryObject(COSName.DW);
/* 229 */       if (base instanceof COSNumber) {
/*     */         
/* 231 */         this.defaultWidth = ((COSNumber)base).floatValue();
/*     */       }
/*     */       else {
/*     */         
/* 235 */         this.defaultWidth = 1000.0F;
/*     */       } 
/*     */     } 
/* 238 */     return this.defaultWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Vector getDefaultPositionVector(int cid) {
/* 248 */     return new Vector(getWidthForCID(cid) / 2.0F, this.dw2[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   private float getWidthForCID(int cid) {
/* 253 */     Float width = this.widths.get(Integer.valueOf(cid));
/* 254 */     if (width == null)
/*     */     {
/* 256 */       width = Float.valueOf(getDefaultWidth());
/*     */     }
/* 258 */     return width.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExplicitWidth(int code) throws IOException {
/* 264 */     return (this.widths.get(Integer.valueOf(codeToCID(code))) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getPositionVector(int code) {
/* 270 */     int cid = codeToCID(code);
/* 271 */     Vector v = this.positionVectors.get(Integer.valueOf(cid));
/* 272 */     if (v == null)
/*     */     {
/* 274 */       v = getDefaultPositionVector(cid);
/*     */     }
/* 276 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVerticalDisplacementVectorY(int code) {
/* 287 */     int cid = codeToCID(code);
/* 288 */     Float w1y = this.verticalDisplacementY.get(Integer.valueOf(cid));
/* 289 */     if (w1y == null)
/*     */     {
/* 291 */       w1y = Float.valueOf(this.dw2[1]);
/*     */     }
/* 293 */     return w1y.floatValue();
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
/*     */   public float getWidth(int code) throws IOException {
/* 305 */     return getWidthForCID(codeToCID(code));
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
/*     */   public float getAverageFontWidth() {
/* 318 */     if (this.averageWidth == 0.0F) {
/*     */       
/* 320 */       float totalWidths = 0.0F;
/* 321 */       int characterCount = 0;
/* 322 */       if (this.widths != null)
/*     */       {
/* 324 */         for (Float width : this.widths.values()) {
/*     */           
/* 326 */           if (width.floatValue() > 0.0F) {
/*     */             
/* 328 */             totalWidths += width.floatValue();
/* 329 */             characterCount++;
/*     */           } 
/*     */         } 
/*     */       }
/* 333 */       this.averageWidth = totalWidths / characterCount;
/* 334 */       if (this.averageWidth <= 0.0F || Float.isNaN(this.averageWidth))
/*     */       {
/* 336 */         this.averageWidth = getDefaultWidth();
/*     */       }
/*     */     } 
/* 339 */     return this.averageWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCIDSystemInfo getCIDSystemInfo() {
/* 347 */     COSBase base = this.dict.getDictionaryObject(COSName.CIDSYSTEMINFO);
/* 348 */     if (base instanceof COSDictionary)
/*     */     {
/* 350 */       return new PDCIDSystemInfo((COSDictionary)base);
/*     */     }
/* 352 */     return null;
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
/*     */   final int[] readCIDToGIDMap() throws IOException {
/* 386 */     int[] cid2gid = null;
/* 387 */     COSBase map = this.dict.getDictionaryObject(COSName.CID_TO_GID_MAP);
/* 388 */     if (map instanceof COSStream) {
/*     */       
/* 390 */       COSStream stream = (COSStream)map;
/*     */       
/* 392 */       COSInputStream cOSInputStream = stream.createInputStream();
/* 393 */       byte[] mapAsBytes = IOUtils.toByteArray((InputStream)cOSInputStream);
/* 394 */       IOUtils.closeQuietly((Closeable)cOSInputStream);
/* 395 */       int numberOfInts = mapAsBytes.length / 2;
/* 396 */       cid2gid = new int[numberOfInts];
/* 397 */       int offset = 0;
/* 398 */       for (int index = 0; index < numberOfInts; index++) {
/*     */         
/* 400 */         int gid = (mapAsBytes[offset] & 0xFF) << 8 | mapAsBytes[offset + 1] & 0xFF;
/* 401 */         cid2gid[index] = gid;
/* 402 */         offset += 2;
/*     */       } 
/*     */     } 
/* 405 */     return cid2gid;
/*     */   }
/*     */   
/*     */   public abstract Matrix getFontMatrix();
/*     */   
/*     */   public abstract BoundingBox getBoundingBox() throws IOException;
/*     */   
/*     */   public abstract float getHeight(int paramInt) throws IOException;
/*     */   
/*     */   public abstract float getWidthFromFont(int paramInt) throws IOException;
/*     */   
/*     */   public abstract boolean isEmbedded();
/*     */   
/*     */   public abstract int codeToCID(int paramInt);
/*     */   
/*     */   public abstract int codeToGID(int paramInt) throws IOException;
/*     */   
/*     */   protected abstract byte[] encode(int paramInt) throws IOException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDCIDFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */