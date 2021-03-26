/*     */ package jp.cssj.homare.b.b;
/*     */ 
/*     */ import jp.cssj.homare.b.a.b.h;
/*     */ import jp.cssj.homare.b.a.b.i;
/*     */ import jp.cssj.homare.b.a.f;
/*     */ import jp.cssj.homare.b.a.i;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.sakae.c.d.g;
/*     */ 
/*     */ public abstract class b
/*     */   extends g {
/*     */   public static final byte a = 1;
/*     */   public static final byte b = 2;
/*     */   public static final byte c = 3;
/*     */   public static final byte d = 4;
/*     */   public static final byte e = 5;
/*  17 */   public double f = 0.0D;
/*     */   
/*     */   public abstract j a();
/*     */   
/*     */   public abstract byte b();
/*     */   
/*     */   public final double c() {
/*  24 */     return this.f;
/*     */   }
/*     */   
/*     */   public static b a(i inline) {
/*  28 */     return new e(inline);
/*     */   }
/*     */   
/*     */   public static b b(i inline) {
/*  32 */     return new c(inline);
/*     */   }
/*     */   
/*     */   public static b a(f replaced) {
/*  36 */     return new d(replaced);
/*     */   }
/*     */   
/*     */   public static b a(h inlineBlock) {
/*  40 */     return new b(inlineBlock);
/*     */   }
/*     */   
/*     */   public static b a(i absoluteBox) {
/*  44 */     return new a(absoluteBox);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class e
/*     */     extends b
/*     */   {
/*     */     public final i g;
/*     */ 
/*     */ 
/*     */     
/*     */     e(i box) {
/*  57 */       this.g = box;
/*     */     }
/*     */     
/*     */     public j a() {
/*  61 */       return (j)this.g;
/*     */     }
/*     */     
/*     */     public byte b() {
/*  65 */       return 1;
/*     */     }
/*     */     
/*     */     public String d() {
/*  69 */       return "​⁠";
/*     */     }
/*     */     
/*     */     public String toString() {
/*  73 */       return "[INLINE]";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class c
/*     */     extends b
/*     */   {
/*     */     public final i g;
/*     */ 
/*     */ 
/*     */     
/*     */     c(i box) {
/*  87 */       this.g = box;
/*     */     }
/*     */     
/*     */     public j a() {
/*  91 */       return (j)this.g;
/*     */     }
/*     */     
/*     */     public byte b() {
/*  95 */       return 2;
/*     */     }
/*     */     
/*     */     public String d() {
/*  99 */       return "⁠​";
/*     */     }
/*     */     
/*     */     public String toString() {
/* 103 */       return "[/INLINE]";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class d
/*     */     extends b
/*     */   {
/*     */     public final f g;
/*     */ 
/*     */ 
/*     */     
/*     */     d(f box) {
/* 117 */       this.g = box;
/*     */     }
/*     */     
/*     */     public j a() {
/* 121 */       return (j)this.g;
/*     */     }
/*     */     
/*     */     public byte b() {
/* 125 */       return 3;
/*     */     }
/*     */     
/*     */     public String d() {
/* 129 */       String alt = (this.g.d_()).a.c();
/* 130 */       return (alt == null || alt.length() == 0) ? "​" : alt;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 134 */       return "[INLINE-REPLACED]";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class b
/*     */     extends b
/*     */   {
/*     */     public final h g;
/*     */ 
/*     */     
/* 147 */     private String n = null;
/*     */     
/*     */     b(h box) {
/* 150 */       this.g = box;
/*     */     }
/*     */     
/*     */     public j a() {
/* 154 */       return (j)this.g;
/*     */     }
/*     */     
/*     */     public byte b() {
/* 158 */       return 4;
/*     */     }
/*     */     
/*     */     public String d() {
/* 162 */       if (this.n == null) {
/* 163 */         StringBuffer textBuff = new StringBuffer();
/* 164 */         this.g.a(textBuff);
/* 165 */         this.n = textBuff.toString();
/*     */       } 
/* 167 */       return (this.n.length() == 0) ? "​" : this.n;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 171 */       return "[INLINE-BLOCK]";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class a extends b {
/*     */     public final i g;
/*     */     
/*     */     a(i box) {
/* 179 */       this.g = box;
/*     */     }
/*     */     
/*     */     public j a() {
/* 183 */       return (j)this.g;
/*     */     }
/*     */     
/*     */     public byte b() {
/* 187 */       return 5;
/*     */     }
/*     */     
/*     */     public String d() {
/* 191 */       return "";
/*     */     }
/*     */     
/*     */     public String toString() {
/* 195 */       return "[INLINE-ABSOLUTE]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */