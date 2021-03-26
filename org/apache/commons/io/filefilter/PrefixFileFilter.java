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
/*     */ public class PrefixFileFilter
/*     */   extends AbstractFileFilter
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8533897440809599867L;
/*     */   private final String[] prefixes;
/*     */   private final IOCase caseSensitivity;
/*     */   
/*     */   public PrefixFileFilter(String prefix) {
/*  61 */     this(prefix, IOCase.SENSITIVE);
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
/*     */   public PrefixFileFilter(String prefix, IOCase caseSensitivity) {
/*  74 */     if (prefix == null) {
/*  75 */       throw new IllegalArgumentException("The prefix must not be null");
/*     */     }
/*  77 */     this.prefixes = new String[] { prefix };
/*  78 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
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
/*     */   public PrefixFileFilter(String[] prefixes) {
/*  91 */     this(prefixes, IOCase.SENSITIVE);
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
/*     */   public PrefixFileFilter(String[] prefixes, IOCase caseSensitivity) {
/* 104 */     if (prefixes == null) {
/* 105 */       throw new IllegalArgumentException("The array of prefixes must not be null");
/*     */     }
/* 107 */     this.prefixes = new String[prefixes.length];
/* 108 */     System.arraycopy(prefixes, 0, this.prefixes, 0, prefixes.length);
/* 109 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrefixFileFilter(List<String> prefixes) {
/* 120 */     this(prefixes, IOCase.SENSITIVE);
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
/*     */   public PrefixFileFilter(List<String> prefixes, IOCase caseSensitivity) {
/* 134 */     if (prefixes == null) {
/* 135 */       throw new IllegalArgumentException("The list of prefixes must not be null");
/*     */     }
/* 137 */     this.prefixes = prefixes.<String>toArray(new String[prefixes.size()]);
/* 138 */     this.caseSensitivity = (caseSensitivity == null) ? IOCase.SENSITIVE : caseSensitivity;
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
/* 149 */     String name = file.getName();
/* 150 */     for (String prefix : this.prefixes) {
/* 151 */       if (this.caseSensitivity.checkStartsWith(name, prefix)) {
/* 152 */         return true;
/*     */       }
/*     */     } 
/* 155 */     return false;
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
/* 167 */     for (String prefix : this.prefixes) {
/* 168 */       if (this.caseSensitivity.checkStartsWith(name, prefix)) {
/* 169 */         return true;
/*     */       }
/*     */     } 
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 182 */     StringBuilder buffer = new StringBuilder();
/* 183 */     buffer.append(super.toString());
/* 184 */     buffer.append("(");
/* 185 */     if (this.prefixes != null) {
/* 186 */       for (int i = 0; i < this.prefixes.length; i++) {
/* 187 */         if (i > 0) {
/* 188 */           buffer.append(",");
/*     */         }
/* 190 */         buffer.append(this.prefixes[i]);
/*     */       } 
/*     */     }
/* 193 */     buffer.append(")");
/* 194 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/filefilter/PrefixFileFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */