/*     */ package org.apache.pdfbox.pdmodel.encryption;
/*     */ 
/*     */ import java.security.Key;
/*     */ import java.security.KeyStore;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.UnrecoverableKeyException;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PublicKeyDecryptionMaterial
/*     */   extends DecryptionMaterial
/*     */ {
/*  45 */   private String password = null;
/*  46 */   private KeyStore keyStore = null;
/*  47 */   private String alias = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PublicKeyDecryptionMaterial(KeyStore keystore, String a, String pwd) {
/*  60 */     this.keyStore = keystore;
/*  61 */     this.alias = a;
/*  62 */     this.password = pwd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public X509Certificate getCertificate() throws KeyStoreException {
/*  76 */     if (this.keyStore.size() == 1) {
/*     */       
/*  78 */       Enumeration<String> aliases = this.keyStore.aliases();
/*  79 */       String keyStoreAlias = aliases.nextElement();
/*  80 */       return (X509Certificate)this.keyStore.getCertificate(keyStoreAlias);
/*     */     } 
/*     */ 
/*     */     
/*  84 */     if (this.keyStore.containsAlias(this.alias))
/*     */     {
/*  86 */       return (X509Certificate)this.keyStore.getCertificate(this.alias);
/*     */     }
/*  88 */     throw new KeyStoreException("the keystore does not contain the given alias");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPassword() {
/* 100 */     return this.password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Key getPrivateKey() throws KeyStoreException {
/*     */     try {
/* 112 */       if (this.keyStore.size() == 1) {
/*     */         
/* 114 */         Enumeration<String> aliases = this.keyStore.aliases();
/* 115 */         String keyStoreAlias = aliases.nextElement();
/* 116 */         return this.keyStore.getKey(keyStoreAlias, this.password.toCharArray());
/*     */       } 
/*     */ 
/*     */       
/* 120 */       if (this.keyStore.containsAlias(this.alias))
/*     */       {
/* 122 */         return this.keyStore.getKey(this.alias, this.password.toCharArray());
/*     */       }
/* 124 */       throw new KeyStoreException("the keystore does not contain the given alias");
/*     */     
/*     */     }
/* 127 */     catch (UnrecoverableKeyException ex) {
/*     */       
/* 129 */       throw new KeyStoreException("the private key is not recoverable", ex);
/*     */     }
/* 131 */     catch (NoSuchAlgorithmException ex) {
/*     */       
/* 133 */       throw new KeyStoreException("the algorithm necessary to recover the key is not available", ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/PublicKeyDecryptionMaterial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */