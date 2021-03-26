/*     */ package org.apache.commons.io.filefilter;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.io.Serializable;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Arrays;
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
/*     */ public class MagicNumberFileFilter
/*     */   extends AbstractFileFilter
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -547733176983104172L;
/*     */   private final byte[] magicNumbers;
/*     */   private final long byteOffset;
/*     */   
/*     */   public MagicNumberFileFilter(byte[] magicNumber) {
/* 112 */     this(magicNumber, 0L);
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
/*     */   public MagicNumberFileFilter(String magicNumber) {
/* 137 */     this(magicNumber, 0L);
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
/*     */   public MagicNumberFileFilter(String magicNumber, long offset) {
/* 161 */     if (magicNumber == null) {
/* 162 */       throw new IllegalArgumentException("The magic number cannot be null");
/*     */     }
/* 164 */     if (magicNumber.isEmpty()) {
/* 165 */       throw new IllegalArgumentException("The magic number must contain at least one byte");
/*     */     }
/* 167 */     if (offset < 0L) {
/* 168 */       throw new IllegalArgumentException("The offset cannot be negative");
/*     */     }
/*     */     
/* 171 */     this.magicNumbers = magicNumber.getBytes(Charset.defaultCharset());
/*     */     
/* 173 */     this.byteOffset = offset;
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
/*     */   public MagicNumberFileFilter(byte[] magicNumber, long offset) {
/* 201 */     if (magicNumber == null) {
/* 202 */       throw new IllegalArgumentException("The magic number cannot be null");
/*     */     }
/* 204 */     if (magicNumber.length == 0) {
/* 205 */       throw new IllegalArgumentException("The magic number must contain at least one byte");
/*     */     }
/* 207 */     if (offset < 0L) {
/* 208 */       throw new IllegalArgumentException("The offset cannot be negative");
/*     */     }
/*     */     
/* 211 */     this.magicNumbers = new byte[magicNumber.length];
/* 212 */     System.arraycopy(magicNumber, 0, this.magicNumbers, 0, magicNumber.length);
/* 213 */     this.byteOffset = offset;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File file) {
/* 234 */     if (file != null && file.isFile() && file.canRead())
/*     */     {
/* 236 */       try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
/* 237 */         byte[] fileBytes = new byte[this.magicNumbers.length];
/* 238 */         randomAccessFile.seek(this.byteOffset);
/* 239 */         int read = randomAccessFile.read(fileBytes);
/* 240 */         if (read != this.magicNumbers.length) {
/* 241 */           return false;
/*     */         }
/* 243 */         return Arrays.equals(this.magicNumbers, fileBytes);
/*     */       
/*     */       }
/* 246 */       catch (IOException iOException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 251 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 262 */     StringBuilder builder = new StringBuilder(super.toString());
/* 263 */     builder.append("(");
/* 264 */     builder.append(new String(this.magicNumbers, Charset.defaultCharset()));
/*     */     
/* 266 */     builder.append(",");
/* 267 */     builder.append(this.byteOffset);
/* 268 */     builder.append(")");
/* 269 */     return builder.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/filefilter/MagicNumberFileFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */