/*    */ package org.apache.batik.ext.awt.image;
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
/*    */ public class IdentityTransfer
/*    */   implements TransferFunction
/*    */ {
/* 35 */   public static byte[] lutData = new byte[256];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 44 */     for (int j = 0; j <= 255; j++) {
/* 45 */       lutData[j] = (byte)j;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] getLookupTable() {
/* 54 */     return lutData;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/IdentityTransfer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */