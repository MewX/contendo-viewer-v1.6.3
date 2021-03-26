/*    */ package org.apache.fontbox.cff;
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
/*    */ public abstract class FDSelect
/*    */ {
/*    */   protected final CFFCIDFont owner;
/*    */   
/*    */   public FDSelect(CFFCIDFont owner) {
/* 31 */     this.owner = owner;
/*    */   }
/*    */   
/*    */   public abstract int getFDIndex(int paramInt);
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/FDSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */