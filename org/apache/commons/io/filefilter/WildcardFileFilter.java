/*     */ package org.apache.commons.io.filefilter;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.apache.commons.io.FilenameUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WildcardFileFilter
/*     */   extends AbstractFileFilter
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7426486598995782105L;
/*     */   private final String[] wildcards;
/*     */   private final IOCase caseSensitivity;
/*     */   
/*     */   public WildcardFileFilter(String wildcard) {
/*  65 */     this(wildcard, IOCase.SENSITIVE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WildcardFileFilter(String wildcard, IOCase caseSensitivity) {
/*  76 */     if (wildcard == null) {
/*  77 */       throw new IllegalArgumentException("The wildcard must not be null");
/*     */     }
/*  79 */     this.wildcards = new String[] { wildcard };
/*  80 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WildcardFileFilter(String[] wildcards) {
/*  91 */     this(wildcards, IOCase.SENSITIVE);
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
/*     */   public WildcardFileFilter(String[] wildcards, IOCase caseSensitivity) {
/* 103 */     if (wildcards == null) {
/* 104 */       throw new IllegalArgumentException("The wildcard array must not be null");
/*     */     }
/* 106 */     this.wildcards = new String[wildcards.length];
/* 107 */     System.arraycopy(wildcards, 0, this.wildcards, 0, wildcards.length);
/* 108 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WildcardFileFilter(List<String> wildcards) {
/* 119 */     this(wildcards, IOCase.SENSITIVE);
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
/*     */   public WildcardFileFilter(List<String> wildcards, IOCase caseSensitivity) {
/* 131 */     if (wildcards == null) {
/* 132 */       throw new IllegalArgumentException("The wildcard list must not be null");
/*     */     }
/* 134 */     this.wildcards = wildcards.<String>toArray(new String[wildcards.size()]);
/* 135 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
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
/* 148 */     for (String wildcard : this.wildcards) {
/* 149 */       if (FilenameUtils.wildcardMatch(name, wildcard, this.caseSensitivity)) {
/* 150 */         return true;
/*     */       }
/*     */     } 
/* 153 */     return false;
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
/* 164 */     String name = file.getName();
/* 165 */     for (String wildcard : this.wildcards) {
/* 166 */       if (FilenameUtils.wildcardMatch(name, wildcard, this.caseSensitivity)) {
/* 167 */         return true;
/*     */       }
/*     */     } 
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 180 */     StringBuilder buffer = new StringBuilder();
/* 181 */     buffer.append(super.toString());
/* 182 */     buffer.append("(");
/* 183 */     if (this.wildcards != null) {
/* 184 */       for (int i = 0; i < this.wildcards.length; i++) {
/* 185 */         if (i > 0) {
/* 186 */           buffer.append(",");
/*     */         }
/* 188 */         buffer.append(this.wildcards[i]);
/*     */       } 
/*     */     }
/* 191 */     buffer.append(")");
/* 192 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/filefilter/WildcardFileFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */