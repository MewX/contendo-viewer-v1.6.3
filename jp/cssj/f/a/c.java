/*     */ package jp.cssj.f.a;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.f.a;
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
/*     */   extends d
/*     */   implements a
/*     */ {
/*  20 */   private List<a> d = new ArrayList<>();
/*     */   
/*  22 */   private a e = null; private a f = null;
/*     */   private static final boolean b = false;
/*     */   
/*     */   private class a {
/*     */     public final int a;
/*  27 */     public a b = null; public a c = null;
/*     */     
/*  29 */     public long d = 0L;
/*     */     
/*     */     public a(c this$0, int id) {
/*  32 */       this.a = id;
/*     */     }
/*     */   }
/*     */   
/*     */   public c(a builder) {
/*  37 */     super(builder);
/*  38 */     if (!a && builder.c()) throw new AssertionError(); 
/*     */   }
/*     */   
/*     */   protected int f() {
/*  42 */     return this.d.size();
/*     */   }
/*     */   
/*     */   protected a c(int id) {
/*  46 */     return this.d.get(id);
/*     */   }
/*     */   
/*     */   protected void a(int id, a frg) {
/*  50 */     if (!a && id != this.d.size()) throw new AssertionError(); 
/*  51 */     this.d.add(frg);
/*     */   }
/*     */   
/*     */   public a.a d() {
/*  55 */     long[] idToPosition = new long[this.d.size()];
/*  56 */     long position = 0L;
/*  57 */     a frg = this.e;
/*  58 */     while (frg != null) {
/*     */       
/*  60 */       idToPosition[frg.a] = position;
/*  61 */       position += frg.d;
/*  62 */       frg = frg.c;
/*     */     } 
/*  64 */     return new a.a(this, idToPosition) {
/*     */         public long a(int id) {
/*  66 */           long position = this.a[id];
/*  67 */           return position;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public void b() throws IOException {
/*  73 */     int id = f();
/*  74 */     a frg = new a(this, id);
/*  75 */     if (this.e == null) {
/*  76 */       this.e = frg;
/*     */     } else {
/*  78 */       this.f.c = frg;
/*  79 */       frg.b = this.f;
/*     */     } 
/*  81 */     a(id, frg);
/*  82 */     this.f = frg;
/*  83 */     super.b();
/*     */   }
/*     */   
/*     */   public void a(int anchorId) throws IOException {
/*  87 */     int id = f();
/*  88 */     a anchor = c(anchorId);
/*  89 */     a frg = new a(this, id);
/*  90 */     a(id, frg);
/*  91 */     frg.b = anchor.b;
/*  92 */     frg.c = anchor;
/*  93 */     anchor.b.c = frg;
/*  94 */     anchor.b = frg;
/*  95 */     if (this.e == anchor) {
/*  96 */       this.e = frg;
/*     */     }
/*  98 */     super.a(anchorId);
/*     */   }
/*     */   
/*     */   public void a(int id, byte[] b, int off, int len) throws IOException {
/* 102 */     a frg = c(id);
/* 103 */     frg.d += len;
/* 104 */     super.a(id, b, off, len);
/*     */   }
/*     */   
/*     */   public void b(int id) throws IOException {
/* 108 */     super.b(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() throws IOException {
/* 116 */     this.e = null;
/* 117 */     this.f = null;
/* 118 */     this.d.clear();
/* 119 */     super.a();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/f/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */