/*     */ package org.apache.commons.io.output;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ import org.apache.commons.io.Charsets;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LockableFileWriter
/*     */   extends Writer
/*     */ {
/*     */   private static final String LCK = ".lck";
/*     */   private final Writer out;
/*     */   private final File lockFile;
/*     */   
/*     */   public LockableFileWriter(String fileName) throws IOException {
/*  69 */     this(fileName, false, (String)null);
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
/*     */   public LockableFileWriter(String fileName, boolean append) throws IOException {
/*  81 */     this(fileName, append, (String)null);
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
/*     */   public LockableFileWriter(String fileName, boolean append, String lockDir) throws IOException {
/*  94 */     this(new File(fileName), append, lockDir);
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
/*     */   public LockableFileWriter(File file) throws IOException {
/* 106 */     this(file, false, (String)null);
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
/*     */   public LockableFileWriter(File file, boolean append) throws IOException {
/* 118 */     this(file, append, (String)null);
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
/*     */   @Deprecated
/*     */   public LockableFileWriter(File file, boolean append, String lockDir) throws IOException {
/* 133 */     this(file, Charset.defaultCharset(), append, lockDir);
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
/*     */   public LockableFileWriter(File file, Charset encoding) throws IOException {
/* 146 */     this(file, encoding, false, (String)null);
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
/*     */   public LockableFileWriter(File file, String encoding) throws IOException {
/* 161 */     this(file, encoding, false, (String)null);
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
/*     */   public LockableFileWriter(File file, Charset encoding, boolean append, String lockDir) throws IOException {
/* 179 */     file = file.getAbsoluteFile();
/* 180 */     if (file.getParentFile() != null) {
/* 181 */       FileUtils.forceMkdir(file.getParentFile());
/*     */     }
/* 183 */     if (file.isDirectory()) {
/* 184 */       throw new IOException("File specified is a directory");
/*     */     }
/*     */ 
/*     */     
/* 188 */     if (lockDir == null) {
/* 189 */       lockDir = System.getProperty("java.io.tmpdir");
/*     */     }
/* 191 */     File lockDirFile = new File(lockDir);
/* 192 */     FileUtils.forceMkdir(lockDirFile);
/* 193 */     testLockDir(lockDirFile);
/* 194 */     this.lockFile = new File(lockDirFile, file.getName() + ".lck");
/*     */ 
/*     */     
/* 197 */     createLock();
/*     */ 
/*     */     
/* 200 */     this.out = initWriter(file, encoding, append);
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
/*     */   public LockableFileWriter(File file, String encoding, boolean append, String lockDir) throws IOException {
/* 218 */     this(file, Charsets.toCharset(encoding), append, lockDir);
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
/*     */   private void testLockDir(File lockDir) throws IOException {
/* 230 */     if (!lockDir.exists()) {
/* 231 */       throw new IOException("Could not find lockDir: " + lockDir
/* 232 */           .getAbsolutePath());
/*     */     }
/* 234 */     if (!lockDir.canWrite()) {
/* 235 */       throw new IOException("Could not write to lockDir: " + lockDir
/* 236 */           .getAbsolutePath());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createLock() throws IOException {
/* 246 */     synchronized (LockableFileWriter.class) {
/* 247 */       if (!this.lockFile.createNewFile()) {
/* 248 */         throw new IOException("Can't write file, lock " + this.lockFile
/* 249 */             .getAbsolutePath() + " exists");
/*     */       }
/* 251 */       this.lockFile.deleteOnExit();
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
/*     */   private Writer initWriter(File file, Charset encoding, boolean append) throws IOException {
/* 266 */     boolean fileExistedAlready = file.exists();
/*     */     try {
/* 268 */       return new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath(), append), 
/* 269 */           Charsets.toCharset(encoding));
/*     */     }
/* 271 */     catch (IOException|RuntimeException ex) {
/* 272 */       FileUtils.deleteQuietly(this.lockFile);
/* 273 */       if (!fileExistedAlready) {
/* 274 */         FileUtils.deleteQuietly(file);
/*     */       }
/* 276 */       throw ex;
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
/*     */   public void close() throws IOException {
/*     */     try {
/* 289 */       this.out.close();
/*     */     } finally {
/* 291 */       this.lockFile.delete();
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
/*     */   public void write(int idx) throws IOException {
/* 303 */     this.out.write(idx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(char[] chr) throws IOException {
/* 313 */     this.out.write(chr);
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
/*     */   public void write(char[] chr, int st, int end) throws IOException {
/* 325 */     this.out.write(chr, st, end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(String str) throws IOException {
/* 335 */     this.out.write(str);
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
/*     */   public void write(String str, int st, int end) throws IOException {
/* 347 */     this.out.write(str, st, end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/* 356 */     this.out.flush();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/output/LockableFileWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */