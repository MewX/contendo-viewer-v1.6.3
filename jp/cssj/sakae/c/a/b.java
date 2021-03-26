/*     */ package jp.cssj.sakae.c.a;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import jp.cssj.sakae.c.a.a.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   implements Serializable
/*     */ {
/*     */   private static final long k = 0L;
/*     */   public static final short a = 1;
/*     */   public static final short b = 2;
/*     */   public static final short c = 3;
/*     */   public static final short d = 4;
/*     */   public static final short e = 5;
/*  20 */   public static final b f = new b((short)1, "serif");
/*     */   
/*  22 */   public static final b g = new b((short)2, "sans-serif");
/*     */   
/*  24 */   public static final b h = new b((short)3, "cursive");
/*     */   
/*  26 */   public static final b i = new b((short)4, "fantasy");
/*     */   
/*  28 */   public static final b j = new b((short)5, "monospace");
/*     */   
/*     */   private final boolean l;
/*     */   
/*     */   private final short m;
/*     */   
/*     */   private final String n;
/*     */   
/*     */   public static b a(String name) {
/*     */     b family;
/*  38 */     if (name == null || name.equalsIgnoreCase("serif")) {
/*  39 */       family = f;
/*  40 */     } else if (name.equalsIgnoreCase("cursive")) {
/*  41 */       family = h;
/*  42 */     } else if (name.equalsIgnoreCase("fantasy")) {
/*  43 */       family = i;
/*  44 */     } else if (name.equalsIgnoreCase("monospace")) {
/*  45 */       family = j;
/*  46 */     } else if (name.equalsIgnoreCase("sans-serif")) {
/*  47 */       family = g;
/*     */     } else {
/*  49 */       family = new b(name);
/*     */     } 
/*  51 */     return family;
/*     */   }
/*     */   
/*     */   private b(short genericFamily, String name) {
/*  55 */     this.l = true;
/*  56 */     this.m = genericFamily;
/*  57 */     this.n = name;
/*     */   }
/*     */   
/*     */   public b(String name) {
/*  61 */     this.l = false;
/*  62 */     this.m = 0;
/*  63 */     this.n = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  72 */     return this.l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short b() {
/*  81 */     return this.m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String c() {
/*  90 */     return this.n;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  94 */     return c();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  98 */     if (o == null || !(o instanceof b)) {
/*  99 */       return false;
/*     */     }
/* 101 */     b a = (b)o;
/* 102 */     if (a.l != this.l) {
/* 103 */       return false;
/*     */     }
/* 105 */     if (a.l) {
/* 106 */       return (a.m == this.m);
/*     */     }
/* 108 */     return a.a(a.n).equals(a.a(this.n));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 112 */     if (this.l) {
/* 113 */       return this.m;
/*     */     }
/* 115 */     return a.a(this.n).hashCode();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */