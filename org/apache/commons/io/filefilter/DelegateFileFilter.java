/*     */ package org.apache.commons.io.filefilter;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DelegateFileFilter
/*     */   extends AbstractFileFilter
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8723373124984771318L;
/*     */   private final FilenameFilter filenameFilter;
/*     */   private final FileFilter fileFilter;
/*     */   
/*     */   public DelegateFileFilter(FilenameFilter filter) {
/*  47 */     if (filter == null) {
/*  48 */       throw new IllegalArgumentException("The FilenameFilter must not be null");
/*     */     }
/*  50 */     this.filenameFilter = filter;
/*  51 */     this.fileFilter = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DelegateFileFilter(FileFilter filter) {
/*  60 */     if (filter == null) {
/*  61 */       throw new IllegalArgumentException("The FileFilter must not be null");
/*     */     }
/*  63 */     this.fileFilter = filter;
/*  64 */     this.filenameFilter = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File file) {
/*  75 */     if (this.fileFilter != null) {
/*  76 */       return this.fileFilter.accept(file);
/*     */     }
/*  78 */     return super.accept(file);
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
/*     */   public boolean accept(File dir, String name) {
/*  91 */     if (this.filenameFilter != null) {
/*  92 */       return this.filenameFilter.accept(dir, name);
/*     */     }
/*  94 */     return super.accept(dir, name);
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
/* 105 */     String delegate = (this.fileFilter != null) ? this.fileFilter.toString() : this.filenameFilter.toString();
/* 106 */     return super.toString() + "(" + delegate + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/filefilter/DelegateFileFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */