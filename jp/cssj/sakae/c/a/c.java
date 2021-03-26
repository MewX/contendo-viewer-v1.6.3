/*     */ package jp.cssj.sakae.c.a;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   implements Serializable
/*     */ {
/*     */   private static final long a = 0L;
/*  14 */   public static final c f = new c(b.f);
/*     */   
/*  16 */   public static final c g = new c(b.g);
/*     */   
/*  18 */   public static final c h = new c(b.h);
/*     */   
/*  20 */   public static final c i = new c(b.i);
/*     */   
/*  22 */   public static final c j = new c(b.j);
/*     */   
/*     */   private final b[] b;
/*     */   
/*     */   public static c a(String name) {
/*     */     c family;
/*  28 */     if (name == null || name.equalsIgnoreCase("serif")) {
/*  29 */       family = f;
/*  30 */     } else if (name.equalsIgnoreCase("cursive")) {
/*  31 */       family = h;
/*  32 */     } else if (name.equalsIgnoreCase("fantasy")) {
/*  33 */       family = i;
/*  34 */     } else if (name.equalsIgnoreCase("monospace")) {
/*  35 */       family = j;
/*  36 */     } else if (name.equalsIgnoreCase("sans-serif")) {
/*  37 */       family = g;
/*     */     } else {
/*  39 */       family = new c(new b(name));
/*     */     } 
/*  41 */     return family;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c(b[] families) {
/*  50 */     this.b = families;
/*     */   }
/*     */   
/*     */   public c(b f1) {
/*  54 */     this(new b[] { f1 });
/*     */   }
/*     */   
/*     */   public c(b f1, b f2) {
/*  58 */     this(new b[] { f1, f2 });
/*     */   }
/*     */   
/*     */   public c(b f1, b f2, b f3) {
/*  62 */     this(new b[] { f1, f2, f3 });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int index) {
/*  72 */     return this.b[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/*  81 */     return this.b.length;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  85 */     if (this.b.length == 0) {
/*  86 */       return "";
/*     */     }
/*  88 */     StringBuffer buffer = new StringBuffer();
/*  89 */     for (int i = 0; i < this.b.length; i++) {
/*  90 */       b entry = this.b[i];
/*  91 */       if (entry.a()) {
/*  92 */         buffer.append(entry.c()).append(' ');
/*     */       } else {
/*  94 */         buffer.append('\'').append(entry.c()).append("' ");
/*     */       } 
/*     */     } 
/*  97 */     return buffer.substring(0, buffer.length() - 1);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 101 */     if (o == null || !(o instanceof c)) {
/* 102 */       return false;
/*     */     }
/* 104 */     b[] a = ((c)o).b;
/* 105 */     b[] arrayOfB1 = this.b;
/* 106 */     if (a.length != arrayOfB1.length) {
/* 107 */       return false;
/*     */     }
/* 109 */     for (int i = 0; i < a.length; i++) {
/* 110 */       if (!a[i].equals(arrayOfB1[i])) {
/* 111 */         return false;
/*     */       }
/*     */     } 
/* 114 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 118 */     int h = 0;
/* 119 */     for (int i = 0; i < this.b.length; i++) {
/* 120 */       h = 31 * h + this.b[i].hashCode();
/*     */     }
/* 122 */     return h;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */