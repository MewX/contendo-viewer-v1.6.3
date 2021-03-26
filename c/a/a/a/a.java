/*      */ package c.a.a.a;
/*      */ 
/*      */ import c.a.a.d;
/*      */ import c.a.c.a.c;
/*      */ import c.a.f.f;
/*      */ import c.a.g.a.e;
/*      */ import c.a.i.e;
/*      */ import c.a.j.b.i;
/*      */ import com.github.jaiimageio.jpeg2000.impl.J2KImageReadParamJava;
/*      */ import java.awt.Point;
/*      */ import java.io.IOException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class a
/*      */   implements c
/*      */ {
/*      */   protected c.a.b.a a;
/*   91 */   protected boolean[] b = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   99 */   protected int[] c = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  108 */   protected e[] d = null;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final char e = 'B';
/*      */ 
/*      */   
/*  115 */   private static final String[][] F = (String[][])null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int[] f;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int g;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int h;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected i[] i;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int j;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int i_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int j_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int k_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int l_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int m_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int[] n_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int[] o_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int[] p_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int[] q_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int r_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int s_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int t_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int u_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int v_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int w_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int x_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected final d y_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int z_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int A_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected float B_;
/*      */ 
/*      */ 
/*      */   
/*      */   protected float C_;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected a(d hd, c.a.b.a decSpec) {
/*  231 */     this.a = decSpec;
/*  232 */     this.y_ = hd;
/*      */ 
/*      */     
/*  235 */     this.g = hd.i();
/*  236 */     this.n_ = new int[this.g];
/*  237 */     this.o_ = new int[this.g];
/*  238 */     this.p_ = new int[this.g];
/*  239 */     this.q_ = new int[this.g];
/*      */ 
/*      */     
/*  242 */     this.j = hd.c();
/*  243 */     this.i_ = hd.d();
/*  244 */     this.j_ = hd.e();
/*  245 */     this.k_ = hd.f();
/*      */ 
/*      */     
/*  248 */     Point co = hd.a((Point)null);
/*  249 */     this.l_ = co.x;
/*  250 */     this.m_ = co.y;
/*  251 */     this.r_ = hd.g();
/*  252 */     this.s_ = hd.h();
/*  253 */     this.t_ = (this.j_ + this.j - this.l_ + this.r_ - 1) / this.r_;
/*  254 */     this.u_ = (this.k_ + this.i_ - this.m_ + this.s_ - 1) / this.s_;
/*  255 */     this.v_ = this.t_ * this.u_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int a() {
/*  263 */     return this.y_.j();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int b() {
/*  271 */     return this.y_.k();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int c() {
/*  280 */     return this.g;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int a(int j) {
/*  296 */     return this.y_.c(j);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int b(int j) {
/*  312 */     return this.y_.d(j);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int c(int rl) {
/*  338 */     int mindl = this.a.g.d(e());
/*  339 */     if (rl > mindl) {
/*  340 */       throw new IllegalArgumentException("Requested resolution level is not available for, at least, one component in tile: " + this.w_ + "x" + this.x_);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  346 */     int dl = mindl - rl;
/*      */ 
/*      */ 
/*      */     
/*  350 */     int ctulx = (this.w_ == 0) ? this.j_ : (this.l_ + this.w_ * this.r_);
/*      */     
/*  352 */     int ntulx = (this.w_ < this.t_ - 1) ? (this.l_ + (this.w_ + 1) * this.r_) : (this.j_ + this.j);
/*  353 */     dl = 1 << dl;
/*      */     
/*  355 */     return (ntulx + dl - 1) / dl - (ctulx + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int d(int rl) {
/*  381 */     int mindl = this.a.g.d(e());
/*  382 */     if (rl > mindl) {
/*  383 */       throw new IllegalArgumentException("Requested resolution level is not available for, at least, one component in tile: " + this.w_ + "x" + this.x_);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  390 */     int dl = mindl - rl;
/*      */ 
/*      */ 
/*      */     
/*  394 */     int ctuly = (this.x_ == 0) ? this.k_ : (this.m_ + this.x_ * this.s_);
/*      */     
/*  396 */     int ntuly = (this.x_ < this.u_ - 1) ? (this.m_ + (this.x_ + 1) * this.s_) : (this.k_ + this.i_);
/*  397 */     dl = 1 << dl;
/*      */     
/*  399 */     return (ntuly + dl - 1) / dl - (ctuly + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int e(int rl) {
/*  424 */     int mindl = this.a.g.b();
/*  425 */     if (rl > mindl) {
/*  426 */       throw new IllegalArgumentException("Requested resolution level is not available for, at least, one tile-component");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  432 */     int dl = 1 << mindl - rl;
/*  433 */     return (this.j_ + this.j + dl - 1) / dl - (this.j_ + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int f(int rl) {
/*  456 */     int mindl = this.a.g.b();
/*  457 */     if (rl > mindl) {
/*  458 */       throw new IllegalArgumentException("Requested resolution level is not available for, at least, one tile-component");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  464 */     int dl = 1 << mindl - rl;
/*  465 */     return (this.k_ + this.i_ + dl - 1) / dl - (this.k_ + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int g(int rl) {
/*  489 */     int mindl = this.a.g.b();
/*  490 */     if (rl > mindl) {
/*  491 */       throw new IllegalArgumentException("Requested resolution level is not available for, at least, one tile-component");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  497 */     int dl = 1 << mindl - rl;
/*  498 */     return (this.j_ + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int h(int rl) {
/*  522 */     int mindl = this.a.g.b();
/*  523 */     if (rl > mindl) {
/*  524 */       throw new IllegalArgumentException("Requested resolution level is not available for, at least, one tile-component");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  530 */     int dl = 1 << mindl - rl;
/*  531 */     return (this.k_ + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int a(int t, int j, int rl) {
/*  548 */     int tIdx = e();
/*  549 */     if (t != tIdx) {
/*  550 */       throw new Error("Asking the tile-component width of a tile different  from the current one.");
/*      */     }
/*      */ 
/*      */     
/*  554 */     int ntulx = (this.w_ < this.t_ - 1) ? (this.l_ + (this.w_ + 1) * this.r_) : (this.j_ + this.j);
/*      */     
/*  556 */     ntulx = (ntulx + this.y_.c(j) - 1) / this.y_.c(j);
/*  557 */     int dl = 1 << this.f[j] - rl;
/*      */ 
/*      */     
/*  560 */     return (ntulx + dl - 1) / dl - (this.p_[j] + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int b(int t, int j, int rl) {
/*  577 */     int tIdx = e();
/*  578 */     if (t != tIdx) {
/*  579 */       throw new Error("Asking the tile-component width of a tile different  from the current one.");
/*      */     }
/*      */ 
/*      */     
/*  583 */     int ntuly = (this.x_ < this.u_ - 1) ? (this.m_ + (this.x_ + 1) * this.s_) : (this.k_ + this.i_);
/*      */     
/*  585 */     ntuly = (ntuly + this.y_.d(j) - 1) / this.y_.d(j);
/*  586 */     int dl = 1 << this.f[j] - rl;
/*      */ 
/*      */     
/*  589 */     return (ntuly + dl - 1) / dl - (this.q_[j] + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int a(int j, int rl) {
/*  618 */     int sx = (this.j_ + this.y_.c(j) - 1) / this.y_.c(j);
/*      */     
/*  620 */     int ex = (this.j_ + this.j + this.y_.c(j) - 1) / this.y_.c(j);
/*  621 */     int dl = 1 << this.a.g.b(j) - rl;
/*      */     
/*  623 */     return (ex + dl - 1) / dl - (sx + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int b(int j, int rl) {
/*  651 */     int sy = (this.k_ + this.y_.d(j) - 1) / this.y_.d(j);
/*      */     
/*  653 */     int ey = (this.k_ + this.i_ + this.y_.d(j) - 1) / this.y_.d(j);
/*  654 */     int dl = 1 << this.a.g.b(j) - rl;
/*      */     
/*  656 */     return (ey + dl - 1) / dl - (sy + dl - 1) / dl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void c(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void d();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Point a(Point co) {
/*  687 */     if (co != null) {
/*  688 */       co.x = this.w_;
/*  689 */       co.y = this.x_;
/*  690 */       return co;
/*      */     } 
/*      */     
/*  693 */     return new Point(this.w_, this.x_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int e() {
/*  704 */     return this.x_ * this.t_ + this.w_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int d(int j, int rl) {
/*  716 */     int dl = this.f[j] - rl;
/*  717 */     if (dl < 0) {
/*  718 */       throw new IllegalArgumentException("Requested resolution level is not available for, at least, one component in tile: " + this.w_ + "x" + this.x_);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  723 */     int tx0 = Math.max(this.l_ + this.w_ * this.r_, this.j_);
/*  724 */     int tcx0 = (int)Math.ceil(tx0 / a(j));
/*  725 */     return (int)Math.ceil(tcx0 / (1 << dl));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int e(int j, int rl) {
/*  737 */     int dl = this.f[j] - rl;
/*  738 */     if (dl < 0) {
/*  739 */       throw new IllegalArgumentException("Requested resolution level is not available for, at least, one component in tile: " + this.w_ + "x" + this.x_);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  744 */     int ty0 = Math.max(this.m_ + this.x_ * this.s_, this.k_);
/*  745 */     int tcy0 = (int)Math.ceil(ty0 / b(j));
/*  746 */     return (int)Math.ceil(tcy0 / (1 << dl));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Point b(Point co) {
/*  759 */     if (co != null) {
/*  760 */       co.x = this.t_;
/*  761 */       co.y = this.u_;
/*  762 */       return co;
/*      */     } 
/*      */     
/*  765 */     return new Point(this.t_, this.u_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int f() {
/*  775 */     return this.t_ * this.u_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final i f(int t, int j) {
/*  796 */     if (t != e()) {
/*  797 */       throw new IllegalArgumentException("Can not request subband tree of a different tile than the current one");
/*      */     }
/*      */ 
/*      */     
/*  801 */     if (j < 0 || j >= this.g) {
/*  802 */       throw new IllegalArgumentException("Component index out of range");
/*      */     }
/*  804 */     return this.i[j];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static a a(f in, d hd, J2KImageReadParamJava j2krparam, c.a.b.a decSpec, boolean cdstrInfo, d hi) throws IOException {
/*  845 */     return new c(hd, in, decSpec, j2krparam, cdstrInfo, hi);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[][] g() {
/*  863 */     return F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int c(int t, int j, int rl) {
/*  880 */     return this.a.n.a(t, j, rl);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int d(int t, int j, int rl) {
/*  897 */     return this.a.n.b(t, j, rl);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void a(int j, i sb) {
/*  912 */     int t = e();
/*  913 */     int rl = sb.h;
/*      */ 
/*      */     
/*  916 */     int cbw = this.a.q.a((byte)3, t, j);
/*  917 */     int cbh = this.a.q.b((byte)3, t, j);
/*      */     
/*  919 */     if (!sb.e) {
/*  920 */       if (this.y_.l()) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  925 */         int ppxExp = e.a(c(t, j, rl));
/*  926 */         int ppyExp = e.a(d(t, j, rl));
/*  927 */         int cbwExp = e.a(cbw);
/*  928 */         int cbhExp = e.a(cbh);
/*      */         
/*  930 */         switch (sb.h) {
/*      */           case 0:
/*  932 */             sb.r = (cbwExp < ppxExp) ? (1 << cbwExp) : (1 << ppxExp);
/*      */             
/*  934 */             sb.s = (cbhExp < ppyExp) ? (1 << cbhExp) : (1 << ppyExp);
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/*  939 */             sb.r = (cbwExp < ppxExp - 1) ? (1 << cbwExp) : (1 << ppxExp - 1);
/*      */             
/*  941 */             sb.s = (cbhExp < ppyExp - 1) ? (1 << cbhExp) : (1 << ppyExp - 1);
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } else {
/*  947 */         sb.r = cbw;
/*  948 */         sb.s = cbh;
/*      */       } 
/*      */ 
/*      */       
/*  952 */       if (sb.i == null) sb.i = new Point(); 
/*  953 */       if (sb.p == 0 || sb.q == 0) {
/*  954 */         sb.i.x = 0;
/*  955 */         sb.i.y = 0;
/*      */       } else {
/*  957 */         int cb0x = a();
/*  958 */         int cb0y = b();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  966 */         int acb0x = cb0x;
/*  967 */         int acb0y = cb0y;
/*      */         
/*  969 */         switch (sb.k) {
/*      */           case 0:
/*      */             break;
/*      */           
/*      */           case 1:
/*  974 */             acb0x = 0;
/*      */             break;
/*      */           case 2:
/*  977 */             acb0y = 0;
/*      */             break;
/*      */           case 3:
/*  980 */             acb0x = 0;
/*  981 */             acb0y = 0;
/*      */             break;
/*      */           default:
/*  984 */             throw new Error("Internal JJ2000 error");
/*      */         } 
/*  986 */         if (sb.l - acb0x < 0 || sb.m - acb0y < 0) {
/*  987 */           throw new IllegalArgumentException("Invalid code-blocks partition origin or image offset in the reference grid.");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  998 */         int tmp = sb.l - acb0x + sb.r;
/*  999 */         sb.i.x = (tmp + sb.p - 1) / sb.r - tmp / sb.r - 1;
/*      */         
/* 1001 */         tmp = sb.m - acb0y + sb.s;
/* 1002 */         sb.i.y = (tmp + sb.q - 1) / sb.s - tmp / sb.s - 1;
/*      */       } 
/*      */       
/* 1005 */       if (this.b[j]) {
/* 1006 */         sb.A = this.c[j] + (this.d[j]).a[0][0] - this.f[j] - sb.g - 1;
/*      */       } else {
/*      */         
/* 1009 */         sb.A = this.c[j] + (this.d[j]).a[sb.h][sb.k] - 1;
/*      */       } 
/*      */     } else {
/*      */       
/* 1013 */       a(j, (i)sb.b());
/* 1014 */       a(j, (i)sb.c());
/* 1015 */       a(j, (i)sb.d());
/* 1016 */       a(j, (i)sb.e());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int h() {
/* 1028 */     return this.h;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float i() {
/* 1037 */     return this.B_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float j() {
/* 1046 */     this.C_ = this.A_ * 8.0F / this.y_.b() / this.y_.a();
/* 1047 */     return this.C_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int k() {
/* 1056 */     return this.z_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int l() {
/* 1065 */     return this.A_;
/*      */   }
/*      */ 
/*      */   
/*      */   public int m() {
/* 1070 */     return (this.y_.a((Point)null)).x;
/*      */   }
/*      */ 
/*      */   
/*      */   public int n() {
/* 1075 */     return (this.y_.a((Point)null)).y;
/*      */   }
/*      */ 
/*      */   
/*      */   public int o() {
/* 1080 */     return this.y_.g();
/*      */   }
/*      */ 
/*      */   
/*      */   public int p() {
/* 1085 */     return this.y_.h();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/a/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */