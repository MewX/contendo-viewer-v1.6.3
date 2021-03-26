/*    */ package org.apache.pdfbox.pdmodel.encryption;
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
/*    */ public abstract class ProtectionPolicy
/*    */ {
/*    */   private static final int DEFAULT_KEY_LENGTH = 40;
/* 35 */   private int encryptionKeyLength = 40;
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
/*    */   public void setEncryptionKeyLength(int l) {
/* 47 */     if (l != 40 && l != 128 && l != 256)
/*    */     {
/* 49 */       throw new IllegalArgumentException("Invalid key length '" + l + "' value must be 40, 128 or 256!");
/*    */     }
/* 51 */     this.encryptionKeyLength = l;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEncryptionKeyLength() {
/* 62 */     return this.encryptionKeyLength;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/ProtectionPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */