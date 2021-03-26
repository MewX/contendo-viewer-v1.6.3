/*     */ package org.apache.commons.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileDeleteStrategy
/*     */ {
/*  39 */   public static final FileDeleteStrategy NORMAL = new FileDeleteStrategy("Normal");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public static final FileDeleteStrategy FORCE = new ForceFileDeleteStrategy();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected FileDeleteStrategy(String name) {
/*  56 */     this.name = name;
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
/*     */   public boolean deleteQuietly(File fileToDelete) {
/*  71 */     if (fileToDelete == null || !fileToDelete.exists()) {
/*  72 */       return true;
/*     */     }
/*     */     try {
/*  75 */       return doDelete(fileToDelete);
/*  76 */     } catch (IOException ex) {
/*  77 */       return false;
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
/*     */   public void delete(File fileToDelete) throws IOException {
/*  92 */     if (fileToDelete.exists() && !doDelete(fileToDelete)) {
/*  93 */       throw new IOException("Deletion failed: " + fileToDelete);
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
/*     */   protected boolean doDelete(File fileToDelete) throws IOException {
/* 114 */     return fileToDelete.delete();
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
/* 125 */     return "FileDeleteStrategy[" + this.name + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class ForceFileDeleteStrategy
/*     */     extends FileDeleteStrategy
/*     */   {
/*     */     ForceFileDeleteStrategy() {
/* 135 */       super("Force");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean doDelete(File fileToDelete) throws IOException {
/* 151 */       FileUtils.forceDelete(fileToDelete);
/* 152 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/FileDeleteStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */