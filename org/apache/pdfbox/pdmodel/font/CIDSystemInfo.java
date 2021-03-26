/*    */ package org.apache.pdfbox.pdmodel.font;
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
/*    */ public final class CIDSystemInfo
/*    */ {
/*    */   private final String registry;
/*    */   private final String ordering;
/*    */   private final int supplement;
/*    */   
/*    */   CIDSystemInfo(String registry, String ordering, int supplement) {
/* 33 */     this.registry = registry;
/* 34 */     this.ordering = ordering;
/* 35 */     this.supplement = supplement;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRegistry() {
/* 40 */     return this.registry;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOrdering() {
/* 45 */     return this.ordering;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSupplement() {
/* 50 */     return this.supplement;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     return getRegistry() + "-" + getOrdering() + "-" + getSupplement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/CIDSystemInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */