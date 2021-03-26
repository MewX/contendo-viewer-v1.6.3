/*     */ package org.a.a;
/*     */ 
/*     */ import javax.json.JsonString;
/*     */ import javax.json.JsonValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class t
/*     */   implements JsonString
/*     */ {
/*     */   private final String a;
/*     */   
/*     */   t(String value) {
/*  55 */     this.a = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString() {
/*  60 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public CharSequence getChars() {
/*  65 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonValue.ValueType getValueType() {
/*  70 */     return JsonValue.ValueType.STRING;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  75 */     return getString().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  80 */     if (this == obj) {
/*  81 */       return true;
/*     */     }
/*  83 */     if (!(obj instanceof JsonString)) {
/*  84 */       return false;
/*     */     }
/*  86 */     JsonString other = (JsonString)obj;
/*  87 */     return getString().equals(other.getString());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  92 */     StringBuilder sb = new StringBuilder();
/*  93 */     sb.append('"');
/*     */     
/*  95 */     for (int i = 0; i < this.a.length(); i++) {
/*  96 */       char c = this.a.charAt(i);
/*     */       
/*  98 */       if (c >= ' ' && c <= 1114111 && c != '"' && c != '\\') {
/*  99 */         sb.append(c);
/*     */       } else {
/* 101 */         String hex; switch (c) {
/*     */           case '"':
/*     */           case '\\':
/* 104 */             sb.append('\\'); sb.append(c);
/*     */             break;
/*     */           case '\b':
/* 107 */             sb.append('\\'); sb.append('b');
/*     */             break;
/*     */           case '\f':
/* 110 */             sb.append('\\'); sb.append('f');
/*     */             break;
/*     */           case '\n':
/* 113 */             sb.append('\\'); sb.append('n');
/*     */             break;
/*     */           case '\r':
/* 116 */             sb.append('\\'); sb.append('r');
/*     */             break;
/*     */           case '\t':
/* 119 */             sb.append('\\'); sb.append('t');
/*     */             break;
/*     */           default:
/* 122 */             hex = "000" + Integer.toHexString(c);
/* 123 */             sb.append("\\u").append(hex.substring(hex.length() - 4));
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 128 */     sb.append('"');
/* 129 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/t.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */