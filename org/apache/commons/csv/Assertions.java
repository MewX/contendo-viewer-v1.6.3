/*    */ package org.apache.commons.csv;
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
/*    */ final class Assertions
/*    */ {
/*    */   static void notNull(Object parameter, String parameterName) {
/* 34 */     if (parameter == null)
/* 35 */       throw new IllegalArgumentException("Parameter '" + parameterName + "' must not be null!"); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/csv/Assertions.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */