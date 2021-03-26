/*     */ package org.apache.commons.io.filefilter;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.apache.commons.io.IOCase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NameFileFilter
/*     */   extends AbstractFileFilter
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 176844364689077340L;
/*     */   private final String[] names;
/*     */   private final IOCase caseSensitivity;
/*     */   
/*     */   public NameFileFilter(String name) {
/*  59 */     this(name, (IOCase)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NameFileFilter(String name, IOCase caseSensitivity) {
/*  70 */     if (name == null) {
/*  71 */       throw new IllegalArgumentException("The wildcard must not be null");
/*     */     }
/*  73 */     this.names = new String[] { name };
/*  74 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
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
/*     */   public NameFileFilter(String[] names) {
/*  87 */     this(names, (IOCase)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NameFileFilter(String[] names, IOCase caseSensitivity) {
/*  98 */     if (names == null) {
/*  99 */       throw new IllegalArgumentException("The array of names must not be null");
/*     */     }
/* 101 */     this.names = new String[names.length];
/* 102 */     System.arraycopy(names, 0, this.names, 0, names.length);
/* 103 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NameFileFilter(List<String> names) {
/* 114 */     this(names, (IOCase)null);
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
/*     */   public NameFileFilter(List<String> names, IOCase caseSensitivity) {
/* 126 */     if (names == null) {
/* 127 */       throw new IllegalArgumentException("The list of names must not be null");
/*     */     }
/* 129 */     this.names = names.<String>toArray(new String[names.size()]);
/* 130 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
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
/*     */   public boolean accept(File file) {
/* 142 */     String name = file.getName();
/* 143 */     for (String name2 : this.names) {
/* 144 */       if (this.caseSensitivity.checkEquals(name, name2)) {
/* 145 */         return true;
/*     */       }
/*     */     } 
/* 148 */     return false;
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
/*     */   public boolean accept(File dir, String name) {
/* 160 */     for (String name2 : this.names) {
/* 161 */       if (this.caseSensitivity.checkEquals(name, name2)) {
/* 162 */         return true;
/*     */       }
/*     */     } 
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 175 */     StringBuilder buffer = new StringBuilder();
/* 176 */     buffer.append(super.toString());
/* 177 */     buffer.append("(");
/* 178 */     if (this.names != null) {
/* 179 */       for (int i = 0; i < this.names.length; i++) {
/* 180 */         if (i > 0) {
/* 181 */           buffer.append(",");
/*     */         }
/* 183 */         buffer.append(this.names[i]);
/*     */       } 
/*     */     }
/* 186 */     buffer.append(")");
/* 187 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/filefilter/NameFileFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */