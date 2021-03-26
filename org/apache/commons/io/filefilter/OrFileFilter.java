/*     */ package org.apache.commons.io.filefilter;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OrFileFilter
/*     */   extends AbstractFileFilter
/*     */   implements Serializable, ConditionalFileFilter
/*     */ {
/*     */   private static final long serialVersionUID = 5767770777065432721L;
/*     */   private final List<IOFileFilter> fileFilters;
/*     */   
/*     */   public OrFileFilter() {
/*  50 */     this.fileFilters = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OrFileFilter(List<IOFileFilter> fileFilters) {
/*  61 */     if (fileFilters == null) {
/*  62 */       this.fileFilters = new ArrayList<>();
/*     */     } else {
/*  64 */       this.fileFilters = new ArrayList<>(fileFilters);
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
/*     */   public OrFileFilter(IOFileFilter filter1, IOFileFilter filter2) {
/*  76 */     if (filter1 == null || filter2 == null) {
/*  77 */       throw new IllegalArgumentException("The filters must not be null");
/*     */     }
/*  79 */     this.fileFilters = new ArrayList<>(2);
/*  80 */     addFileFilter(filter1);
/*  81 */     addFileFilter(filter2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFileFilter(IOFileFilter ioFileFilter) {
/*  89 */     this.fileFilters.add(ioFileFilter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<IOFileFilter> getFileFilters() {
/*  97 */     return Collections.unmodifiableList(this.fileFilters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeFileFilter(IOFileFilter ioFileFilter) {
/* 105 */     return this.fileFilters.remove(ioFileFilter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileFilters(List<IOFileFilter> fileFilters) {
/* 113 */     this.fileFilters.clear();
/* 114 */     this.fileFilters.addAll(fileFilters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File file) {
/* 122 */     for (IOFileFilter fileFilter : this.fileFilters) {
/* 123 */       if (fileFilter.accept(file)) {
/* 124 */         return true;
/*     */       }
/*     */     } 
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File file, String name) {
/* 135 */     for (IOFileFilter fileFilter : this.fileFilters) {
/* 136 */       if (fileFilter.accept(file, name)) {
/* 137 */         return true;
/*     */       }
/*     */     } 
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 150 */     StringBuilder buffer = new StringBuilder();
/* 151 */     buffer.append(super.toString());
/* 152 */     buffer.append("(");
/* 153 */     if (this.fileFilters != null) {
/* 154 */       for (int i = 0; i < this.fileFilters.size(); i++) {
/* 155 */         if (i > 0) {
/* 156 */           buffer.append(",");
/*     */         }
/* 158 */         Object filter = this.fileFilters.get(i);
/* 159 */         buffer.append((filter == null) ? "null" : filter.toString());
/*     */       } 
/*     */     }
/* 162 */     buffer.append(")");
/* 163 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/filefilter/OrFileFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */