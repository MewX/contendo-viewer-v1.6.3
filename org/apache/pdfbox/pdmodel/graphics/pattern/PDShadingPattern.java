/*     */ package org.apache.pdfbox.pdmodel.graphics.pattern;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
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
/*     */ 
/*     */ public class PDShadingPattern
/*     */   extends PDAbstractPattern
/*     */ {
/*     */   private PDExtendedGraphicsState extendedGraphicsState;
/*     */   private PDShading shading;
/*     */   
/*     */   public PDShadingPattern() {
/*  41 */     getCOSObject().setInt(COSName.PATTERN_TYPE, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDShadingPattern(COSDictionary resourceDictionary) {
/*  50 */     super(resourceDictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPatternType() {
/*  56 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDExtendedGraphicsState getExtendedGraphicsState() {
/*  65 */     if (this.extendedGraphicsState == null) {
/*     */       
/*  67 */       COSBase base = getCOSObject().getDictionaryObject(COSName.EXT_G_STATE);
/*  68 */       if (base instanceof COSDictionary)
/*     */       {
/*  70 */         this.extendedGraphicsState = new PDExtendedGraphicsState((COSDictionary)base);
/*     */       }
/*     */     } 
/*  73 */     return this.extendedGraphicsState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtendedGraphicsState(PDExtendedGraphicsState extendedGraphicsState) {
/*  82 */     this.extendedGraphicsState = extendedGraphicsState;
/*  83 */     getCOSObject().setItem(COSName.EXT_G_STATE, (COSObjectable)extendedGraphicsState);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDShading getShading() throws IOException {
/*  93 */     if (this.shading == null) {
/*     */       
/*  95 */       COSBase base = getCOSObject().getDictionaryObject(COSName.SHADING);
/*  96 */       if (base instanceof COSDictionary)
/*     */       {
/*  98 */         this.shading = PDShading.create((COSDictionary)base);
/*     */       }
/*     */     } 
/* 101 */     return this.shading;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShading(PDShading shadingResources) {
/* 110 */     this.shading = shadingResources;
/* 111 */     getCOSObject().setItem(COSName.SHADING, (COSObjectable)shadingResources);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/pattern/PDShadingPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */