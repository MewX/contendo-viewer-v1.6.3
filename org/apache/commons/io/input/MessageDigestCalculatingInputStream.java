/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
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
/*     */ public class MessageDigestCalculatingInputStream
/*     */   extends ObservableInputStream
/*     */ {
/*     */   private final MessageDigest messageDigest;
/*     */   
/*     */   public static class MessageDigestMaintainingObserver
/*     */     extends ObservableInputStream.Observer
/*     */   {
/*     */     private final MessageDigest md;
/*     */     
/*     */     public MessageDigestMaintainingObserver(MessageDigest pMd) {
/*  45 */       this.md = pMd;
/*     */     }
/*     */ 
/*     */     
/*     */     void data(int pByte) throws IOException {
/*  50 */       this.md.update((byte)pByte);
/*     */     }
/*     */ 
/*     */     
/*     */     void data(byte[] pBuffer, int pOffset, int pLength) throws IOException {
/*  55 */       this.md.update(pBuffer, pOffset, pLength);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageDigestCalculatingInputStream(InputStream pStream, MessageDigest pDigest) {
/*  67 */     super(pStream);
/*  68 */     this.messageDigest = pDigest;
/*  69 */     add(new MessageDigestMaintainingObserver(pDigest));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageDigestCalculatingInputStream(InputStream pStream, String pAlgorithm) throws NoSuchAlgorithmException {
/*  79 */     this(pStream, MessageDigest.getInstance(pAlgorithm));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageDigestCalculatingInputStream(InputStream pStream) throws NoSuchAlgorithmException {
/*  88 */     this(pStream, MessageDigest.getInstance("MD5"));
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
/*     */   public MessageDigest getMessageDigest() {
/* 100 */     return this.messageDigest;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/MessageDigestCalculatingInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */