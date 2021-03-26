/*     */ package net.a.a.e.d.c;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import net.a.a.e.d.a.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   implements Serializable
/*     */ {
/*     */   private static final long b = 1L;
/*     */   private final int c;
/*     */   private final d d;
/*     */   
/*     */   public c(int paramInt, d paramd) {
/*  48 */     if (!a && paramd == null) throw new AssertionError(); 
/*  49 */     this.c = paramInt;
/*  50 */     this.d = paramd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int a() {
/*  57 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final d b() {
/*  64 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  71 */     int i = 1;
/*  72 */     i = 31 * i + this.c;
/*  73 */     i = 31 * i + this.d.hashCode();
/*  74 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/*  80 */     if (this == paramObject) {
/*  81 */       return true;
/*     */     }
/*  83 */     if (paramObject == null) {
/*  84 */       return false;
/*     */     }
/*  86 */     if (getClass() != paramObject.getClass()) {
/*  87 */       return false;
/*     */     }
/*  89 */     c c1 = (c)paramObject;
/*  90 */     if (this.c != c1.c) {
/*  91 */       return false;
/*     */     }
/*  93 */     if (this.d == null) {
/*  94 */       if (c1.d != null) {
/*  95 */         return false;
/*     */       }
/*  97 */     } else if (!this.d.equals(c1.d)) {
/*  98 */       return false;
/*     */     } 
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 106 */     StringBuilder stringBuilder = new StringBuilder();
/* 107 */     stringBuilder.append('[');
/* 108 */     stringBuilder.append("0x");
/* 109 */     stringBuilder.append(Integer.toHexString(this.c));
/* 110 */     stringBuilder.append(' ');
/* 111 */     stringBuilder.append(this.d.toString());
/* 112 */     stringBuilder.append(']');
/* 113 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/c/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */