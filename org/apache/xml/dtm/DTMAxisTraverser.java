/*    */ package org.apache.xml.dtm;
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
/*    */ public abstract class DTMAxisTraverser
/*    */ {
/*    */   public int first(int context) {
/* 59 */     return next(context, context);
/*    */   }
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
/*    */   public int first(int context, int extendedTypeID) {
/* 78 */     return next(context, context, extendedTypeID);
/*    */   }
/*    */   
/*    */   public abstract int next(int paramInt1, int paramInt2);
/*    */   
/*    */   public abstract int next(int paramInt1, int paramInt2, int paramInt3);
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/DTMAxisTraverser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */