/*    */ package org.apache.pdfbox.pdmodel.encryption;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.security.Provider;
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
/*    */ public class SecurityProvider
/*    */ {
/* 29 */   private static Provider provider = null;
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
/*    */   public static Provider getProvider() throws IOException {
/* 45 */     if (provider == null) {
/*    */       
/*    */       try {
/*    */ 
/*    */         
/* 50 */         Class<Provider> providerClass = (Class)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider");
/* 51 */         provider = providerClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*    */       }
/* 53 */       catch (ClassNotFoundException ex) {
/*    */         
/* 55 */         throw new IOException(ex);
/*    */       }
/* 57 */       catch (InstantiationException ex) {
/*    */         
/* 59 */         throw new IOException(ex);
/*    */       }
/* 61 */       catch (IllegalAccessException ex) {
/*    */         
/* 63 */         throw new IOException(ex);
/*    */       }
/* 65 */       catch (NoSuchMethodException ex) {
/*    */         
/* 67 */         throw new IOException(ex);
/*    */       }
/* 69 */       catch (InvocationTargetException ex) {
/*    */         
/* 71 */         throw new IOException(ex);
/*    */       } 
/*    */     }
/* 74 */     return provider;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setProvider(Provider provider) {
/* 84 */     SecurityProvider.provider = provider;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/SecurityProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */