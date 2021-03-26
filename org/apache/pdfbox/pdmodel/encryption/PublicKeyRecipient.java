/*    */ package org.apache.pdfbox.pdmodel.encryption;
/*    */ 
/*    */ import java.security.cert.X509Certificate;
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
/*    */ public class PublicKeyRecipient
/*    */ {
/*    */   private X509Certificate x509;
/*    */   private AccessPermission permission;
/*    */   
/*    */   public X509Certificate getX509() {
/* 42 */     return this.x509;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setX509(X509Certificate aX509) {
/* 52 */     this.x509 = aX509;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AccessPermission getPermission() {
/* 62 */     return this.permission;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPermission(AccessPermission permissions) {
/* 72 */     this.permission = permissions;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/PublicKeyRecipient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */