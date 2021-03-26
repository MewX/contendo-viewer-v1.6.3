/*     */ package com.github.jaiimageio.plugins.tiff;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.imageio.ImageReadParam;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFImageReadParam
/*     */   extends ImageReadParam
/*     */ {
/*  83 */   List allowedTagSets = new ArrayList(4);
/*     */   
/*  85 */   TIFFDecompressor decompressor = null;
/*     */   
/*  87 */   TIFFColorConverter colorConverter = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFImageReadParam() {
/* 101 */     addAllowedTagSet(BaselineTIFFTagSet.getInstance());
/* 102 */     addAllowedTagSet(FaxTIFFTagSet.getInstance());
/* 103 */     addAllowedTagSet(EXIFParentTIFFTagSet.getInstance());
/* 104 */     addAllowedTagSet(GeoTIFFTagSet.getInstance());
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
/*     */   public void addAllowedTagSet(TIFFTagSet tagSet) {
/* 117 */     if (tagSet == null) {
/* 118 */       throw new IllegalArgumentException("tagSet == null!");
/*     */     }
/* 120 */     this.allowedTagSets.add(tagSet);
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
/*     */   public void removeAllowedTagSet(TIFFTagSet tagSet) {
/* 135 */     if (tagSet == null) {
/* 136 */       throw new IllegalArgumentException("tagSet == null!");
/*     */     }
/* 138 */     this.allowedTagSets.remove(tagSet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllowedTagSets() {
/* 148 */     return this.allowedTagSets;
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
/*     */   public void setTIFFDecompressor(TIFFDecompressor decompressor) {
/* 164 */     this.decompressor = decompressor;
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
/*     */   public TIFFDecompressor getTIFFDecompressor() {
/* 179 */     return this.decompressor;
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
/*     */   public void setColorConverter(TIFFColorConverter colorConverter) {
/* 194 */     this.colorConverter = colorConverter;
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
/*     */   public TIFFColorConverter getColorConverter() {
/* 206 */     return this.colorConverter;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/plugins/tiff/TIFFImageReadParam.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */