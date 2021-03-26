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
/*     */ 
/*     */ 
/*     */ public class AndFileFilter
/*     */   extends AbstractFileFilter
/*     */   implements Serializable, ConditionalFileFilter
/*     */ {
/*     */   private static final long serialVersionUID = 7215974688563965257L;
/*     */   private final List<IOFileFilter> fileFilters;
/*     */   
/*     */   public AndFileFilter() {
/*  52 */     this.fileFilters = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AndFileFilter(List<IOFileFilter> fileFilters) {
/*  63 */     if (fileFilters == null) {
/*  64 */       this.fileFilters = new ArrayList<>();
/*     */     } else {
/*  66 */       this.fileFilters = new ArrayList<>(fileFilters);
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
/*     */   public AndFileFilter(IOFileFilter filter1, IOFileFilter filter2) {
/*  78 */     if (filter1 == null || filter2 == null) {
/*  79 */       throw new IllegalArgumentException("The filters must not be null");
/*     */     }
/*  81 */     this.fileFilters = new ArrayList<>(2);
/*  82 */     addFileFilter(filter1);
/*  83 */     addFileFilter(filter2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFileFilter(IOFileFilter ioFileFilter) {
/*  91 */     this.fileFilters.add(ioFileFilter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<IOFileFilter> getFileFilters() {
/*  99 */     return Collections.unmodifiableList(this.fileFilters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeFileFilter(IOFileFilter ioFileFilter) {
/* 107 */     return this.fileFilters.remove(ioFileFilter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileFilters(List<IOFileFilter> fileFilters) {
/* 115 */     this.fileFilters.clear();
/* 116 */     this.fileFilters.addAll(fileFilters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File file) {
/* 124 */     if (this.fileFilters.isEmpty()) {
/* 125 */       return false;
/*     */     }
/* 127 */     for (IOFileFilter fileFilter : this.fileFilters) {
/* 128 */       if (!fileFilter.accept(file)) {
/* 129 */         return false;
/*     */       }
/*     */     } 
/* 132 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accept(File file, String name) {
/* 140 */     if (this.fileFilters.isEmpty()) {
/* 141 */       return false;
/*     */     }
/* 143 */     for (IOFileFilter fileFilter : this.fileFilters) {
/* 144 */       if (!fileFilter.accept(file, name)) {
/* 145 */         return false;
/*     */       }
/*     */     } 
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 158 */     StringBuilder buffer = new StringBuilder();
/* 159 */     buffer.append(super.toString());
/* 160 */     buffer.append("(");
/* 161 */     if (this.fileFilters != null) {
/* 162 */       for (int i = 0; i < this.fileFilters.size(); i++) {
/* 163 */         if (i > 0) {
/* 164 */           buffer.append(",");
/*     */         }
/* 166 */         Object filter = this.fileFilters.get(i);
/* 167 */         buffer.append((filter == null) ? "null" : filter.toString());
/*     */       } 
/*     */     }
/* 170 */     buffer.append(")");
/* 171 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/filefilter/AndFileFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */