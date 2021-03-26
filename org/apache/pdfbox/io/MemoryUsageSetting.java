/*     */ package org.apache.pdfbox.io;
/*     */ 
/*     */ import java.io.File;
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
/*     */ public final class MemoryUsageSetting
/*     */ {
/*     */   private final boolean useMainMemory;
/*     */   private final boolean useTempFile;
/*     */   private final long maxMainMemoryBytes;
/*     */   private final long maxStorageBytes;
/*     */   private File tempDir;
/*     */   
/*     */   private MemoryUsageSetting(boolean useMainMemory, boolean useTempFile, long maxMainMemoryBytes, long maxStorageBytes) {
/*  61 */     boolean locUseMainMemory = useTempFile ? useMainMemory : true;
/*  62 */     long locMaxMainMemoryBytes = useMainMemory ? maxMainMemoryBytes : -1L;
/*  63 */     long locMaxStorageBytes = (maxStorageBytes > 0L) ? maxStorageBytes : -1L;
/*     */     
/*  65 */     if (locMaxMainMemoryBytes < -1L)
/*     */     {
/*  67 */       locMaxMainMemoryBytes = -1L;
/*     */     }
/*     */     
/*  70 */     if (locUseMainMemory && locMaxMainMemoryBytes == 0L)
/*     */     {
/*  72 */       if (useTempFile) {
/*  73 */         locUseMainMemory = false;
/*     */       }
/*     */       else {
/*     */         
/*  77 */         locMaxMainMemoryBytes = locMaxStorageBytes;
/*     */       } 
/*     */     }
/*     */     
/*  81 */     if (locUseMainMemory && locMaxStorageBytes > -1L && (locMaxMainMemoryBytes == -1L || locMaxMainMemoryBytes > locMaxStorageBytes))
/*     */     {
/*     */       
/*  84 */       locMaxStorageBytes = locMaxMainMemoryBytes;
/*     */     }
/*     */ 
/*     */     
/*  88 */     this.useMainMemory = locUseMainMemory;
/*  89 */     this.useTempFile = useTempFile;
/*  90 */     this.maxMainMemoryBytes = locMaxMainMemoryBytes;
/*  91 */     this.maxStorageBytes = locMaxStorageBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MemoryUsageSetting setupMainMemoryOnly() {
/* 100 */     return setupMainMemoryOnly(-1L);
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
/*     */   public static MemoryUsageSetting setupMainMemoryOnly(long maxMainMemoryBytes) {
/* 112 */     return new MemoryUsageSetting(true, false, maxMainMemoryBytes, maxMainMemoryBytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MemoryUsageSetting setupTempFileOnly() {
/* 121 */     return setupTempFileOnly(-1L);
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
/*     */   public static MemoryUsageSetting setupTempFileOnly(long maxStorageBytes) {
/* 134 */     return new MemoryUsageSetting(false, true, 0L, maxStorageBytes);
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
/*     */   public static MemoryUsageSetting setupMixed(long maxMainMemoryBytes) {
/* 147 */     return setupMixed(maxMainMemoryBytes, -1L);
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
/*     */   public static MemoryUsageSetting setupMixed(long maxMainMemoryBytes, long maxStorageBytes) {
/* 163 */     return new MemoryUsageSetting(true, true, maxMainMemoryBytes, maxStorageBytes);
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
/*     */   public MemoryUsageSetting getPartitionedCopy(int parallelUseCount) {
/* 178 */     long newMaxMainMemoryBytes = (this.maxMainMemoryBytes <= 0L) ? this.maxMainMemoryBytes : (this.maxMainMemoryBytes / parallelUseCount);
/*     */     
/* 180 */     long newMaxStorageBytes = (this.maxStorageBytes <= 0L) ? this.maxStorageBytes : (this.maxStorageBytes / parallelUseCount);
/*     */ 
/*     */     
/* 183 */     MemoryUsageSetting copy = new MemoryUsageSetting(this.useMainMemory, this.useTempFile, newMaxMainMemoryBytes, newMaxStorageBytes);
/*     */     
/* 185 */     copy.tempDir = this.tempDir;
/*     */     
/* 187 */     return copy;
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
/*     */   public MemoryUsageSetting setTempDir(File tempDir) {
/* 199 */     this.tempDir = tempDir;
/* 200 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useMainMemory() {
/* 211 */     return this.useMainMemory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useTempFile() {
/* 222 */     return this.useTempFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMainMemoryRestricted() {
/* 231 */     return (this.maxMainMemoryBytes >= 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStorageRestricted() {
/* 240 */     return (this.maxStorageBytes > 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaxMainMemoryBytes() {
/* 248 */     return this.maxMainMemoryBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaxStorageBytes() {
/* 257 */     return this.maxStorageBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getTempDir() {
/* 266 */     return this.tempDir;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 272 */     return this.useMainMemory ? (this.useTempFile ? ("Mixed mode with max. of " + this.maxMainMemoryBytes + " main memory bytes" + (
/*     */       
/* 274 */       isStorageRestricted() ? (" and max. of " + this.maxStorageBytes + " storage bytes") : " and unrestricted scratch file size")) : (
/*     */       
/* 276 */       isMainMemoryRestricted() ? ("Main memory only with max. of " + this.maxMainMemoryBytes + " bytes") : "Main memory only with no size restriction")) : (
/*     */       
/* 278 */       isStorageRestricted() ? ("Scratch file only with max. of " + this.maxStorageBytes + " bytes") : "Scratch file only with no size restriction");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/MemoryUsageSetting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */