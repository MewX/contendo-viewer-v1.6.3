/*     */ package org.apache.pdfbox.pdmodel;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern;
/*     */ import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
/*     */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultResourceCache
/*     */   implements ResourceCache
/*     */ {
/*  41 */   private final Map<COSObject, SoftReference<PDFont>> fonts = new HashMap<COSObject, SoftReference<PDFont>>();
/*     */ 
/*     */   
/*  44 */   private final Map<COSObject, SoftReference<PDColorSpace>> colorSpaces = new HashMap<COSObject, SoftReference<PDColorSpace>>();
/*     */ 
/*     */   
/*  47 */   private final Map<COSObject, SoftReference<PDXObject>> xobjects = new HashMap<COSObject, SoftReference<PDXObject>>();
/*     */ 
/*     */   
/*  50 */   private final Map<COSObject, SoftReference<PDExtendedGraphicsState>> extGStates = new HashMap<COSObject, SoftReference<PDExtendedGraphicsState>>();
/*     */ 
/*     */   
/*  53 */   private final Map<COSObject, SoftReference<PDShading>> shadings = new HashMap<COSObject, SoftReference<PDShading>>();
/*     */ 
/*     */   
/*  56 */   private final Map<COSObject, SoftReference<PDAbstractPattern>> patterns = new HashMap<COSObject, SoftReference<PDAbstractPattern>>();
/*     */ 
/*     */   
/*  59 */   private final Map<COSObject, SoftReference<PDPropertyList>> properties = new HashMap<COSObject, SoftReference<PDPropertyList>>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFont getFont(COSObject indirect) throws IOException {
/*  65 */     SoftReference<PDFont> font = this.fonts.get(indirect);
/*  66 */     if (font != null)
/*     */     {
/*  68 */       return font.get();
/*     */     }
/*  70 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSObject indirect, PDFont font) throws IOException {
/*  76 */     this.fonts.put(indirect, new SoftReference<PDFont>(font));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getColorSpace(COSObject indirect) throws IOException {
/*  82 */     SoftReference<PDColorSpace> colorSpace = this.colorSpaces.get(indirect);
/*  83 */     if (colorSpace != null)
/*     */     {
/*  85 */       return colorSpace.get();
/*     */     }
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSObject indirect, PDColorSpace colorSpace) throws IOException {
/*  93 */     this.colorSpaces.put(indirect, new SoftReference<PDColorSpace>(colorSpace));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDExtendedGraphicsState getExtGState(COSObject indirect) {
/*  99 */     SoftReference<PDExtendedGraphicsState> extGState = this.extGStates.get(indirect);
/* 100 */     if (extGState != null)
/*     */     {
/* 102 */       return extGState.get();
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSObject indirect, PDExtendedGraphicsState extGState) {
/* 110 */     this.extGStates.put(indirect, new SoftReference<PDExtendedGraphicsState>(extGState));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDShading getShading(COSObject indirect) throws IOException {
/* 116 */     SoftReference<PDShading> shading = this.shadings.get(indirect);
/* 117 */     if (shading != null)
/*     */     {
/* 119 */       return shading.get();
/*     */     }
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSObject indirect, PDShading shading) throws IOException {
/* 127 */     this.shadings.put(indirect, new SoftReference<PDShading>(shading));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAbstractPattern getPattern(COSObject indirect) throws IOException {
/* 133 */     SoftReference<PDAbstractPattern> pattern = this.patterns.get(indirect);
/* 134 */     if (pattern != null)
/*     */     {
/* 136 */       return pattern.get();
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSObject indirect, PDAbstractPattern pattern) throws IOException {
/* 144 */     this.patterns.put(indirect, new SoftReference<PDAbstractPattern>(pattern));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPropertyList getProperties(COSObject indirect) {
/* 150 */     SoftReference<PDPropertyList> propertyList = this.properties.get(indirect);
/* 151 */     if (propertyList != null)
/*     */     {
/* 153 */       return propertyList.get();
/*     */     }
/* 155 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSObject indirect, PDPropertyList propertyList) {
/* 161 */     this.properties.put(indirect, new SoftReference<PDPropertyList>(propertyList));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDXObject getXObject(COSObject indirect) throws IOException {
/* 167 */     SoftReference<PDXObject> xobject = this.xobjects.get(indirect);
/* 168 */     if (xobject != null)
/*     */     {
/* 170 */       return xobject.get();
/*     */     }
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(COSObject indirect, PDXObject xobject) throws IOException {
/* 178 */     this.xobjects.put(indirect, new SoftReference<PDXObject>(xobject));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/DefaultResourceCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */