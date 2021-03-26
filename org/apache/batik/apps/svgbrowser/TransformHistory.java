/*    */ package org.apache.batik.apps.svgbrowser;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ 
/*    */ public class TransformHistory
/*    */ {
/* 36 */   protected List transforms = new ArrayList();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   protected int position = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void back() {
/* 48 */     this.position -= 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canGoBack() {
/* 55 */     return (this.position > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void forward() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canGoForward() {
/* 69 */     return (this.position < this.transforms.size() - 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AffineTransform currentTransform() {
/* 76 */     return this.transforms.get(this.position + 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update(AffineTransform at) {
/* 83 */     if (this.position < -1) {
/* 84 */       this.position = -1;
/*    */     }
/* 86 */     if (++this.position < this.transforms.size()) {
/* 87 */       if (!this.transforms.get(this.position).equals(at)) {
/* 88 */         this.transforms = this.transforms.subList(0, this.position + 1);
/*    */       }
/* 90 */       this.transforms.set(this.position, at);
/*    */     } else {
/* 92 */       this.transforms.add(at);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/TransformHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */