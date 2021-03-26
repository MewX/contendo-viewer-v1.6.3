/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.EventObject;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UpdateManagerEvent
/*     */   extends EventObject
/*     */ {
/*     */   protected BufferedImage image;
/*     */   protected List dirtyAreas;
/*     */   protected boolean clearPaintingTransform;
/*     */   
/*     */   public UpdateManagerEvent(Object source, BufferedImage bi, List das) {
/*  59 */     super(source);
/*  60 */     this.image = bi;
/*  61 */     this.dirtyAreas = das;
/*  62 */     this.clearPaintingTransform = false;
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
/*     */   public UpdateManagerEvent(Object source, BufferedImage bi, List das, boolean cpt) {
/*  76 */     super(source);
/*  77 */     this.image = bi;
/*  78 */     this.dirtyAreas = das;
/*  79 */     this.clearPaintingTransform = cpt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage() {
/*  86 */     return this.image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDirtyAreas() {
/*  93 */     return this.dirtyAreas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getClearPaintingTransform() {
/* 101 */     return this.clearPaintingTransform;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/UpdateManagerEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */