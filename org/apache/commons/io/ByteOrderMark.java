/*     */ package org.apache.commons.io;
/*     */ 
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
/*     */ public class ByteOrderMark
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  36 */   public static final ByteOrderMark UTF_8 = new ByteOrderMark("UTF-8", new int[] { 239, 187, 191 });
/*     */ 
/*     */   
/*  39 */   public static final ByteOrderMark UTF_16BE = new ByteOrderMark("UTF-16BE", new int[] { 254, 255 });
/*     */ 
/*     */   
/*  42 */   public static final ByteOrderMark UTF_16LE = new ByteOrderMark("UTF-16LE", new int[] { 255, 254 });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static final ByteOrderMark UTF_32BE = new ByteOrderMark("UTF-32BE", new int[] { 0, 0, 254, 255 });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final ByteOrderMark UTF_32LE = new ByteOrderMark("UTF-32LE", new int[] { 255, 254, 0, 0 });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final char UTF_BOM = '﻿';
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String charsetName;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int[] bytes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOrderMark(String charsetName, int... bytes) {
/*  77 */     if (charsetName == null || charsetName.isEmpty()) {
/*  78 */       throw new IllegalArgumentException("No charsetName specified");
/*     */     }
/*  80 */     if (bytes == null || bytes.length == 0) {
/*  81 */       throw new IllegalArgumentException("No bytes specified");
/*     */     }
/*  83 */     this.charsetName = charsetName;
/*  84 */     this.bytes = new int[bytes.length];
/*  85 */     System.arraycopy(bytes, 0, this.bytes, 0, bytes.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCharsetName() {
/*  94 */     return this.charsetName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int length() {
/* 103 */     return this.bytes.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int get(int pos) {
/* 113 */     return this.bytes[pos];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes() {
/* 122 */     byte[] copy = new byte[this.bytes.length];
/* 123 */     for (int i = 0; i < this.bytes.length; i++) {
/* 124 */       copy[i] = (byte)this.bytes[i];
/*     */     }
/* 126 */     return copy;
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
/*     */   public boolean equals(Object obj) {
/* 138 */     if (!(obj instanceof ByteOrderMark)) {
/* 139 */       return false;
/*     */     }
/* 141 */     ByteOrderMark bom = (ByteOrderMark)obj;
/* 142 */     if (this.bytes.length != bom.length()) {
/* 143 */       return false;
/*     */     }
/* 145 */     for (int i = 0; i < this.bytes.length; i++) {
/* 146 */       if (this.bytes[i] != bom.get(i)) {
/* 147 */         return false;
/*     */       }
/*     */     } 
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 161 */     int hashCode = getClass().hashCode();
/* 162 */     for (int b : this.bytes) {
/* 163 */       hashCode += b;
/*     */     }
/* 165 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 175 */     StringBuilder builder = new StringBuilder();
/* 176 */     builder.append(getClass().getSimpleName());
/* 177 */     builder.append('[');
/* 178 */     builder.append(this.charsetName);
/* 179 */     builder.append(": ");
/* 180 */     for (int i = 0; i < this.bytes.length; i++) {
/* 181 */       if (i > 0) {
/* 182 */         builder.append(",");
/*     */       }
/* 184 */       builder.append("0x");
/* 185 */       builder.append(Integer.toHexString(0xFF & this.bytes[i]).toUpperCase());
/*     */     } 
/* 187 */     builder.append(']');
/* 188 */     return builder.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/ByteOrderMark.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */