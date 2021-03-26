/*    */ package org.apache.batik.ext.awt.image.rendered;
/*    */ 
/*    */ import java.awt.image.Raster;
/*    */ import java.lang.ref.Reference;
/*    */ import java.lang.ref.SoftReference;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileLRUMember
/*    */   implements LRUCache.LRUObj
/*    */ {
/*    */   private static final boolean DEBUG = false;
/* 37 */   protected LRUCache.LRUNode myNode = null;
/* 38 */   protected Reference wRaster = null;
/* 39 */   protected Raster hRaster = null;
/*    */   
/*    */   public TileLRUMember() {}
/*    */   
/*    */   public TileLRUMember(Raster ras) {
/* 44 */     setRaster(ras);
/*    */   }
/*    */   
/*    */   public void setRaster(Raster ras) {
/* 48 */     this.hRaster = ras;
/* 49 */     this.wRaster = new SoftReference<Raster>(ras);
/*    */   }
/*    */   
/*    */   public boolean checkRaster() {
/* 53 */     if (this.hRaster != null) return true;
/*    */     
/* 55 */     if (this.wRaster != null && this.wRaster.get() != null) {
/* 56 */       return true;
/*    */     }
/* 58 */     return false;
/*    */   }
/*    */   
/*    */   public Raster retrieveRaster() {
/* 62 */     if (this.hRaster != null) return this.hRaster; 
/* 63 */     if (this.wRaster == null) return null;
/*    */     
/* 65 */     this.hRaster = this.wRaster.get();
/*    */     
/* 67 */     if (this.hRaster == null) {
/* 68 */       this.wRaster = null;
/*    */     }
/* 70 */     return this.hRaster;
/*    */   }
/*    */   
/* 73 */   public LRUCache.LRUNode lruGet() { return this.myNode; } public void lruSet(LRUCache.LRUNode nde) {
/* 74 */     this.myNode = nde;
/*    */   } public void lruRemove() {
/* 76 */     this.myNode = null;
/* 77 */     this.hRaster = null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TileLRUMember.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */