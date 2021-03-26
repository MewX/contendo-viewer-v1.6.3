/*     */ package jp.cssj.homare.b.a.c;
/*     */ 
/*     */ import jp.cssj.sakae.c.c.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class j
/*     */   implements Comparable<j>
/*     */ {
/*     */   public static final short a = 0;
/*     */   public static final short b = 1;
/*     */   public static final short c = 2;
/*     */   public static final short d = 3;
/*     */   public static final short e = 4;
/*     */   public static final short f = 5;
/*     */   public static final short g = 6;
/*     */   public static final short h = 7;
/*     */   public static final short i = 8;
/*     */   public static final short j = 9;
/*  35 */   public static final j k = new j((short)0, 0.0D, null);
/*  36 */   public static final j l = new j((short)1, 0.0D, null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double m;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final short n;
/*     */ 
/*     */ 
/*     */   
/*     */   public final b o;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static j a(short style, double width, b color) {
/*  56 */     switch (style) {
/*     */       case 0:
/*  58 */         if (color == null) {
/*  59 */           return k;
/*     */         }
/*  61 */         width = 0.0D;
/*     */         break;
/*     */       case 1:
/*  64 */         if (color == null) {
/*  65 */           return l;
/*     */         }
/*  67 */         width = 0.0D;
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     return new j(style, width, color);
/*     */   }
/*     */   
/*     */   private j(short style, double width, b color) {
/*  76 */     this.n = style;
/*  77 */     this.m = width;
/*  78 */     this.o = color;
/*     */   }
/*     */   
/*     */   public boolean a() {
/*  82 */     if (b() || this.o == null) {
/*  83 */       return false;
/*     */     }
/*  85 */     return true;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  89 */     return (this.m <= 0.0D);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  93 */     return "[style=" + this.n + ",width=" + this.m + ",color=" + this.o + "]";
/*     */   }
/*     */   
/*     */   public int a(j o) {
/*  97 */     j next = o;
/*  98 */     if (next == null) {
/*  99 */       return -1;
/*     */     }
/*     */     
/* 102 */     if (this.n == 1) {
/* 103 */       if (next.n == 1) {
/* 104 */         return 0;
/*     */       }
/* 106 */       return -1;
/*     */     } 
/* 108 */     if (next.n == 1) {
/* 109 */       return 1;
/*     */     }
/*     */     
/* 112 */     if (this.n == 0) {
/* 113 */       if (next.n == 0) {
/* 114 */         return 0;
/*     */       }
/* 116 */       return 1;
/*     */     } 
/* 118 */     if (next.n == 0) {
/* 119 */       return -1;
/*     */     }
/*     */     
/* 122 */     if (next.m > this.m) {
/* 123 */       return 1;
/*     */     }
/* 125 */     if (next.m < this.m) {
/* 126 */       return -1;
/*     */     }
/* 128 */     if (next.n < this.n) {
/* 129 */       return 1;
/*     */     }
/* 131 */     if (next.n > this.n) {
/* 132 */       return -1;
/*     */     }
/* 134 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 138 */     j j1 = (j)o;
/* 139 */     return (this.n == j1.n && this.m == j1.m && this.o.equals(j1.o));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */