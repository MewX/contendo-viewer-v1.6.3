/*     */ package jp.cssj.homare.b.b.a;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.homare.b.a.a.d;
/*     */ import jp.cssj.homare.b.a.b.f;
/*     */ import jp.cssj.homare.b.a.b.n;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.i;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.b.c;
/*     */ import jp.cssj.homare.b.b.d;
/*     */ import jp.cssj.homare.b.b.e;
/*     */ 
/*     */ public class h
/*     */   extends b {
/*     */   private static final Logger D;
/*     */   private final e E;
/*     */   private n F;
/*     */   
/*     */   static {
/*  22 */     D = Logger.getLogger(h.class.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h(e pageGenerator, byte mode) {
/*  29 */     super((d)null, (c)null, mode);
/*  30 */     this.E = pageGenerator;
/*  31 */     this.F = pageGenerator.f();
/*     */     
/*  33 */     this.s = this.E.e();
/*  34 */     this.b = new c.b((c)this.F, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public final boolean c() {
/*  38 */     return true;
/*     */   }
/*     */   
/*     */   public final h h() {
/*  42 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(d mode, byte flags) {
/*  52 */     if (!C && this.d != null) throw new AssertionError(); 
/*  53 */     this.z = 0;
/*  54 */     this.v = -1;
/*  55 */     this.w = false;
/*  56 */     this.x = false;
/*  57 */     if (this.c.isEmpty()) {
/*  58 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  62 */     for (int i = 0; i < this.c.size(); i++) {
/*  63 */       c.b flow = this.c.get(i);
/*  64 */       flow.a.a(this.k - flow.c);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     c.b root = this.c.get(0);
/*     */ 
/*     */     
/*  75 */     double lastFrame = 0.0D;
/*  76 */     for (int j = this.c.size() - 1; j >= 0; j--) {
/*  77 */       c.b flow = this.c.get(j);
/*  78 */       if (flow.a.i() > 1) {
/*  79 */         lastFrame = a(root, this.c.size() - j);
/*  80 */         flags = (byte)(flags | 0x10);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  85 */     f prevRootBox = (f)root.a;
/*  86 */     double pageAxis = z() - root.c - lastFrame;
/*     */ 
/*     */     
/*  89 */     f nextRootBox = (f)prevRootBox.b(pageAxis, mode, flags);
/*     */     
/*  91 */     if (prevRootBox == nextRootBox || nextRootBox == null)
/*     */     {
/*     */       
/*  94 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     A();
/* 102 */     this.E.a(this.F);
/* 103 */     n pageBox = this.F;
/* 104 */     this.F = this.E.f();
/* 105 */     if (this.s != 0) {
/* 106 */       this.s = (this.s == 5) ? 6 : 5;
/*     */     }
/*     */     
/* 109 */     if (D.isLoggable(Level.FINE)) {
/* 110 */       D.fine("breaked: " + mode + "/pageSide=" + this.s);
/*     */     }
/*     */ 
/*     */     
/* 114 */     this.b = new c.b((c)this.F, 0.0D, 0.0D);
/* 115 */     this.k = 0.0D;
/* 116 */     this.j = 0.0D;
/* 117 */     this.h = 0.0D;
/* 118 */     this.i = 0.0D;
/* 119 */     this.u = 0;
/* 120 */     this.m = null;
/* 121 */     this.y = true;
/*     */ 
/*     */     
/* 124 */     int depth = this.c.size();
/* 125 */     this.c.clear();
/* 126 */     pageBox.a(this, 0);
/* 127 */     nextRootBox.a(this, depth);
/* 128 */     if (!C && this.c.size() != depth) throw new AssertionError("break flow failed. " + (k().b()).al);
/*     */     
/* 130 */     if (D.isLoggable(Level.FINE)) {
/* 131 */       D.fine("restyled");
/*     */     }
/*     */ 
/*     */     
/* 135 */     if (mode.a() == 1) {
/* 136 */       d.b force = (d.b)mode;
/* 137 */       if ((force.e == 5 || force.e == 6) && (this.s == 5 || this.s == 6))
/*     */       {
/* 139 */         if (force.e != this.s) {
/* 140 */           if (D.isLoggable(Level.FINE)) {
/* 141 */             D.fine("white page: " + force);
/*     */           }
/* 143 */           b(force.e);
/*     */         } 
/*     */       }
/*     */     } 
/* 147 */     this.y = false;
/*     */     
/* 149 */     return true;
/*     */   }
/*     */   
/*     */   public void a(i box) {
/* 153 */     box.a((m)this.F);
/* 154 */     this.F.a(box);
/*     */   }
/*     */   
/*     */   protected void A() {
/* 158 */     this.F.a((m)this.F);
/*     */   }
/*     */   
/*     */   public void x() {
/* 162 */     A();
/* 163 */     this.E.a(this.F);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */