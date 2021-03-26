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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SuffixFileFilter
/*     */   extends AbstractFileFilter
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3389157631240246157L;
/*     */   private final String[] suffixes;
/*     */   private final IOCase caseSensitivity;
/*     */   
/*     */   public SuffixFileFilter(String suffix) {
/*  62 */     this(suffix, IOCase.SENSITIVE);
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
/*     */   public SuffixFileFilter(String suffix, IOCase caseSensitivity) {
/*  75 */     if (suffix == null) {
/*  76 */       throw new IllegalArgumentException("The suffix must not be null");
/*     */     }
/*  78 */     this.suffixes = new String[] { suffix };
/*  79 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
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
/*     */   public SuffixFileFilter(String[] suffixes) {
/*  92 */     this(suffixes, IOCase.SENSITIVE);
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
/*     */   public SuffixFileFilter(String[] suffixes, IOCase caseSensitivity) {
/* 105 */     if (suffixes == null) {
/* 106 */       throw new IllegalArgumentException("The array of suffixes must not be null");
/*     */     }
/* 108 */     this.suffixes = new String[suffixes.length];
/* 109 */     System.arraycopy(suffixes, 0, this.suffixes, 0, suffixes.length);
/* 110 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SuffixFileFilter(List<String> suffixes) {
/* 121 */     this(suffixes, IOCase.SENSITIVE);
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
/*     */   public SuffixFileFilter(List<String> suffixes, IOCase caseSensitivity) {
/* 135 */     if (suffixes == null) {
/* 136 */       throw new IllegalArgumentException("The list of suffixes must not be null");
/*     */     }
/* 138 */     this.suffixes = suffixes.<String>toArray(new String[suffixes.size()]);
/* 139 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
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
/* 150 */     String name = file.getName();
/* 151 */     for (String suffix : this.suffixes) {
/* 152 */       if (this.caseSensitivity.checkEndsWith(name, suffix)) {
/* 153 */         return true;
/*     */       }
/*     */     } 
/* 156 */     return false;
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
/*     */   public boolean accept(File file, String name) {
/* 168 */     for (String suffix : this.suffixes) {
/* 169 */       if (this.caseSensitivity.checkEndsWith(name, suffix)) {
/* 170 */         return true;
/*     */       }
/*     */     } 
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 183 */     StringBuilder buffer = new StringBuilder();
/* 184 */     buffer.append(super.toString());
/* 185 */     buffer.append("(");
/* 186 */     if (this.suffixes != null) {
/* 187 */       for (int i = 0; i < this.suffixes.length; i++) {
/* 188 */         if (i > 0) {
/* 189 */           buffer.append(",");
/*     */         }
/* 191 */         buffer.append(this.suffixes[i]);
/*     */       } 
/*     */     }
/* 194 */     buffer.append(")");
/* 195 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/filefilter/SuffixFileFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */