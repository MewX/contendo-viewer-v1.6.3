/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GlyphData
/*     */ {
/*     */   private short xMin;
/*     */   private short yMin;
/*     */   private short xMax;
/*     */   private short yMax;
/*  35 */   private BoundingBox boundingBox = null;
/*     */   private short numberOfContours;
/*  37 */   private GlyfDescript glyphDescription = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initData(GlyphTable glyphTable, TTFDataStream data, int leftSideBearing) throws IOException {
/*  49 */     this.numberOfContours = data.readSignedShort();
/*  50 */     this.xMin = data.readSignedShort();
/*  51 */     this.yMin = data.readSignedShort();
/*  52 */     this.xMax = data.readSignedShort();
/*  53 */     this.yMax = data.readSignedShort();
/*  54 */     this.boundingBox = new BoundingBox(this.xMin, this.yMin, this.xMax, this.yMax);
/*     */     
/*  56 */     if (this.numberOfContours >= 0) {
/*     */ 
/*     */       
/*  59 */       short x0 = (short)(leftSideBearing - this.xMin);
/*  60 */       this.glyphDescription = new GlyfSimpleDescript(this.numberOfContours, data, x0);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  65 */       this.glyphDescription = new GlyfCompositeDescript(data, glyphTable);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() {
/*  74 */     return this.boundingBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoundingBox(BoundingBox boundingBoxValue) {
/*  82 */     this.boundingBox = boundingBoxValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getNumberOfContours() {
/*  90 */     return this.numberOfContours;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberOfContours(short numberOfContoursValue) {
/*  98 */     this.numberOfContours = numberOfContoursValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphDescription getDescription() {
/* 107 */     return this.glyphDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath() {
/* 116 */     return (new GlyphRenderer(this.glyphDescription)).getPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getXMaximum() {
/* 125 */     return this.xMax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getXMinimum() {
/* 134 */     return this.xMin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getYMaximum() {
/* 143 */     return this.yMax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getYMinimum() {
/* 152 */     return this.yMin;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/GlyphData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */