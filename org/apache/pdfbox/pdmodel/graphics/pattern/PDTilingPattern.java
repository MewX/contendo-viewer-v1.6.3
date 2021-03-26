/*     */ package org.apache.pdfbox.pdmodel.graphics.pattern;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.pdfbox.contentstream.PDContentStream;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDTilingPattern
/*     */   extends PDAbstractPattern
/*     */   implements PDContentStream
/*     */ {
/*     */   public static final int PAINT_COLORED = 1;
/*     */   public static final int PAINT_UNCOLORED = 2;
/*     */   public static final int TILING_CONSTANT_SPACING = 1;
/*     */   public static final int TILING_NO_DISTORTION = 2;
/*     */   public static final int TILING_CONSTANT_SPACING_FASTER_TILING = 3;
/*     */   
/*     */   public PDTilingPattern() {
/*  57 */     super((COSDictionary)new COSStream());
/*  58 */     getCOSObject().setName(COSName.TYPE, COSName.PATTERN.getName());
/*  59 */     getCOSObject().setInt(COSName.PATTERN_TYPE, 1);
/*     */ 
/*     */     
/*  62 */     setResources(new PDResources());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTilingPattern(COSDictionary dictionary) {
/*  71 */     super(dictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPatternType() {
/*  77 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaintType(int paintType) {
/*  87 */     getCOSObject().setInt(COSName.PAINT_TYPE, paintType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPaintType() {
/*  96 */     return getCOSObject().getInt(COSName.PAINT_TYPE, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTilingType(int tilingType) {
/* 105 */     getCOSObject().setInt(COSName.TILING_TYPE, tilingType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTilingType() {
/* 114 */     return getCOSObject().getInt(COSName.TILING_TYPE, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXStep(float xStep) {
/* 123 */     getCOSObject().setFloat(COSName.X_STEP, xStep);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getXStep() {
/* 132 */     return getCOSObject().getFloat(COSName.X_STEP, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYStep(float yStep) {
/* 141 */     getCOSObject().setFloat(COSName.Y_STEP, yStep);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getYStep() {
/* 150 */     return getCOSObject().getFloat(COSName.Y_STEP, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public PDStream getContentStream() {
/* 155 */     return new PDStream((COSStream)getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getContents() throws IOException {
/* 161 */     COSDictionary dict = getCOSObject();
/* 162 */     if (dict instanceof COSStream)
/*     */     {
/* 164 */       return (InputStream)((COSStream)getCOSObject()).createInputStream();
/*     */     }
/* 166 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources getResources() {
/* 177 */     PDResources retval = null;
/* 178 */     COSBase base = getCOSObject().getDictionaryObject(COSName.RESOURCES);
/* 179 */     if (base instanceof COSDictionary)
/*     */     {
/* 181 */       retval = new PDResources((COSDictionary)base);
/*     */     }
/* 183 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setResources(PDResources resources) {
/* 192 */     getCOSObject().setItem(COSName.RESOURCES, (COSObjectable)resources);
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
/*     */   public PDRectangle getBBox() {
/* 205 */     PDRectangle retval = null;
/* 206 */     COSBase base = getCOSObject().getDictionaryObject(COSName.BBOX);
/* 207 */     if (base instanceof COSArray)
/*     */     {
/* 209 */       retval = new PDRectangle((COSArray)base);
/*     */     }
/* 211 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBBox(PDRectangle bbox) {
/* 220 */     if (bbox == null) {
/*     */       
/* 222 */       getCOSObject().removeItem(COSName.BBOX);
/*     */     }
/*     */     else {
/*     */       
/* 226 */       getCOSObject().setItem(COSName.BBOX, (COSBase)bbox.getCOSArray());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/pattern/PDTilingPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */