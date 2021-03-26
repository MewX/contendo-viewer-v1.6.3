/*    */ package org.apache.pdfbox.pdmodel.encryption;
/*    */ 
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
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
/*    */ final class MessageDigests
/*    */ {
/*    */   static MessageDigest getMD5() {
/*    */     try {
/* 39 */       return MessageDigest.getInstance("MD5");
/*    */     }
/* 41 */     catch (NoSuchAlgorithmException e) {
/*    */ 
/*    */       
/* 44 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static MessageDigest getSHA1() {
/*    */     try {
/* 55 */       return MessageDigest.getInstance("SHA-1");
/*    */     }
/* 57 */     catch (NoSuchAlgorithmException e) {
/*    */ 
/*    */       
/* 60 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static MessageDigest getSHA256() {
/*    */     try {
/* 71 */       return MessageDigest.getInstance("SHA-256");
/*    */     }
/* 73 */     catch (NoSuchAlgorithmException e) {
/*    */ 
/*    */       
/* 76 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/MessageDigests.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */