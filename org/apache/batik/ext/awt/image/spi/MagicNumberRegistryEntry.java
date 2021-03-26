/*     */ package org.apache.batik.ext.awt.image.spi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.StreamCorruptedException;
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
/*     */ public abstract class MagicNumberRegistryEntry
/*     */   extends AbstractRegistryEntry
/*     */   implements StreamRegistryEntry
/*     */ {
/*     */   public static final float PRIORITY = 1000.0F;
/*     */   MagicNumber[] magicNumbers;
/*     */   
/*     */   public static class MagicNumber
/*     */   {
/*     */     int offset;
/*     */     byte[] magicNumber;
/*     */     byte[] buffer;
/*     */     
/*     */     public MagicNumber(int offset, byte[] magicNumber) {
/*  58 */       this.offset = offset;
/*  59 */       this.magicNumber = (byte[])magicNumber.clone();
/*  60 */       this.buffer = new byte[magicNumber.length];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int getReadlimit() {
/*  68 */       return this.offset + this.magicNumber.length;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean isMatch(InputStream is) throws StreamCorruptedException {
/*  76 */       int idx = 0;
/*  77 */       is.mark(getReadlimit());
/*     */       
/*     */       try {
/*  80 */         while (idx < this.offset) {
/*  81 */           int rn = (int)is.skip((this.offset - idx));
/*  82 */           if (rn == -1) {
/*  83 */             return false;
/*     */           }
/*  85 */           idx += rn;
/*     */         } 
/*     */         
/*  88 */         idx = 0;
/*  89 */         while (idx < this.buffer.length) {
/*  90 */           int rn = is.read(this.buffer, idx, this.buffer.length - idx);
/*  91 */           if (rn == -1) {
/*  92 */             return false;
/*     */           }
/*  94 */           idx += rn;
/*     */         } 
/*     */         
/*  97 */         for (int i = 0; i < this.magicNumber.length; i++) {
/*  98 */           if (this.magicNumber[i] != this.buffer[i]) {
/*  99 */             return false;
/*     */           }
/*     */         } 
/* 102 */       } catch (IOException ioe) {
/* 103 */         return false;
/*     */       } finally {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 109 */           is.reset();
/* 110 */         } catch (IOException ioe) {
/* 111 */           throw new StreamCorruptedException(ioe.getMessage());
/*     */         } 
/*     */       } 
/* 114 */       return true;
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
/*     */   public MagicNumberRegistryEntry(String name, float priority, String ext, String mimeType, int offset, byte[] magicNumber) {
/* 136 */     super(name, priority, ext, mimeType);
/* 137 */     this.magicNumbers = new MagicNumber[1];
/* 138 */     this.magicNumbers[0] = new MagicNumber(offset, magicNumber);
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
/*     */   public MagicNumberRegistryEntry(String name, String ext, String mimeType, int offset, byte[] magicNumber) {
/* 154 */     this(name, 1000.0F, ext, mimeType, offset, magicNumber);
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
/*     */   public MagicNumberRegistryEntry(String name, float priority, String ext, String mimeType, MagicNumber[] magicNumbers) {
/* 171 */     super(name, priority, ext, mimeType);
/* 172 */     this.magicNumbers = magicNumbers;
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
/*     */   public MagicNumberRegistryEntry(String name, String ext, String mimeType, MagicNumber[] magicNumbers) {
/* 187 */     this(name, 1000.0F, ext, mimeType, magicNumbers);
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
/*     */   public MagicNumberRegistryEntry(String name, float priority, String[] exts, String[] mimeTypes, int offset, byte[] magicNumber) {
/* 205 */     super(name, priority, exts, mimeTypes);
/* 206 */     this.magicNumbers = new MagicNumber[1];
/* 207 */     this.magicNumbers[0] = new MagicNumber(offset, magicNumber);
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
/*     */   public MagicNumberRegistryEntry(String name, String[] exts, String[] mimeTypes, int offset, byte[] magicNumbers) {
/* 223 */     this(name, 1000.0F, exts, mimeTypes, offset, magicNumbers);
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
/*     */   public MagicNumberRegistryEntry(String name, float priority, String[] exts, String[] mimeTypes, MagicNumber[] magicNumbers) {
/* 239 */     super(name, priority, exts, mimeTypes);
/* 240 */     this.magicNumbers = magicNumbers;
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
/*     */   public MagicNumberRegistryEntry(String name, String[] exts, String[] mimeTypes, MagicNumber[] magicNumbers) {
/* 254 */     this(name, 1000.0F, exts, mimeTypes, magicNumbers);
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
/*     */   public MagicNumberRegistryEntry(String name, String[] exts, String[] mimeTypes, MagicNumber[] magicNumbers, float priority) {
/* 272 */     super(name, priority, exts, mimeTypes);
/* 273 */     this.magicNumbers = magicNumbers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReadlimit() {
/* 280 */     int maxbuf = 0;
/* 281 */     for (MagicNumber magicNumber : this.magicNumbers) {
/* 282 */       int req = magicNumber.getReadlimit();
/* 283 */       if (req > maxbuf) {
/* 284 */         maxbuf = req;
/*     */       }
/*     */     } 
/* 287 */     return maxbuf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCompatibleStream(InputStream is) throws StreamCorruptedException {
/* 296 */     for (MagicNumber magicNumber : this.magicNumbers) {
/* 297 */       if (magicNumber.isMatch(is)) {
/* 298 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 302 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/MagicNumberRegistryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */