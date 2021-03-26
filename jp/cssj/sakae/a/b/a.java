/*     */ package jp.cssj.sakae.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import jp.cssj.sakae.a.d;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.a.l;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.a.a.b;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import net.zamasoft.a.b;
/*     */ import net.zamasoft.a.b.G;
/*     */ import net.zamasoft.a.b.H;
/*     */ import net.zamasoft.a.b.T;
/*     */ import net.zamasoft.a.b.U;
/*     */ import net.zamasoft.a.b.X;
/*     */ import net.zamasoft.a.b.ah;
/*     */ import net.zamasoft.a.b.j;
/*     */ import net.zamasoft.a.b.k;
/*     */ import net.zamasoft.a.b.q;
/*     */ import net.zamasoft.a.b.z;
/*     */ import net.zamasoft.a.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class a
/*     */   implements l
/*     */ {
/*     */   private static final long g = 2L;
/*     */   protected static final int a = 880;
/*     */   protected static final boolean b = false;
/*     */   protected final c c;
/*     */   protected final X d;
/*     */   protected final ah e;
/*     */   protected final ah f;
/*     */   
/*     */   protected a(c source) {
/*  47 */     this.c = source;
/*  48 */     d ttfFont = source.o();
/*  49 */     this.f = (ah)ttfFont.a(1752003704);
/*     */     
/*  51 */     if (this.c.e() == 3) {
/*     */       
/*  53 */       q gsub = (q)ttfFont.a(1196643650);
/*  54 */       U scriptList = gsub.b();
/*  55 */       T script = scriptList.a("kana");
/*  56 */       if (script == null) {
/*  57 */         script = scriptList.a("hani");
/*     */       }
/*  59 */       if (script == null) {
/*  60 */         script = scriptList.a("latn");
/*     */       }
/*  62 */       if (script == null) {
/*  63 */         script = scriptList.a("hang");
/*     */       }
/*  65 */       if (script != null) {
/*  66 */         z langSys = script.a();
/*  67 */         k featureList = gsub.c();
/*  68 */         j feature = featureList.a(langSys, "vert");
/*  69 */         if (feature != null) {
/*  70 */           H lookupList = gsub.d();
/*  71 */           G lookup = lookupList.a(feature, 0);
/*  72 */           this.d = (X)lookup.a(0);
/*  73 */           this.e = (ah)ttfFont.a(1986884728);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*  78 */     this.d = null;
/*  79 */     this.e = null;
/*     */   }
/*     */   
/*     */   protected final boolean g_() {
/*  83 */     return (this.e != null);
/*     */   }
/*     */   
/*     */   protected final Shape a(Shape shape, int gid) {
/*  87 */     if (!g_()) {
/*  88 */       return shape;
/*     */     }
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
/* 101 */     int cid = g(gid);
/* 102 */     if (cid == 65293 || cid == 65308 || cid == 65310 || cid == 8722 || cid == 8810 || cid == 8811) {
/* 103 */       GeneralPath path = new GeneralPath(shape);
/* 104 */       Rectangle2D bound = shape.getBounds2D();
/* 105 */       path.transform(AffineTransform.getRotateInstance(1.5707963267948966D, bound.getCenterX(), bound.getCenterY()));
/* 106 */       shape = path;
/*     */     } 
/* 108 */     return shape;
/*     */   }
/*     */   
/*     */   protected final short e(int gid) {
/* 112 */     c source = (c)a();
/*     */     
/* 114 */     short advance = (short)(this.f.a(gid) * 1000 / source.q());
/* 115 */     return advance;
/*     */   }
/*     */   
/*     */   protected final short b_(int gid) {
/* 119 */     if (this.e == null) {
/* 120 */       return 1000;
/*     */     }
/* 122 */     c source = (c)a();
/*     */     
/* 124 */     short advance = (short)(this.e.a(gid) * 1000 / source.q());
/* 125 */     return advance;
/*     */   }
/*     */   
/*     */   public g a() {
/* 129 */     return (g)this.c;
/*     */   }
/*     */   
/*     */   public int a(int i) {
/* 133 */     c source = (c)a();
/* 134 */     int gid = source.r().a(i);
/* 135 */     if (this.d != null) {
/* 136 */       gid = this.d.a(gid);
/*     */     }
/* 138 */     return gid;
/*     */   }
/*     */   
/*     */   public Shape d(int gid) {
/* 142 */     c source = (c)a();
/* 143 */     b glyph = source.o().b(gid);
/* 144 */     if (glyph == null) {
/* 145 */       return null;
/*     */     }
/* 147 */     Shape shape = glyph.a();
/* 148 */     shape = a(shape, gid);
/* 149 */     return shape;
/*     */   }
/*     */   
/*     */   public short b(int gid) {
/* 153 */     if (g_()) {
/* 154 */       return b_(gid);
/*     */     }
/* 156 */     return e(gid);
/*     */   }
/*     */   
/*     */   public short c(int gid) {
/* 160 */     return e(gid);
/*     */   }
/*     */   
/*     */   public void a(b gc, h text) throws IOException, c {
/* 164 */     jp.cssj.sakae.c.a.a.a.a(gc, (d)this, text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   private static final b h = (b)new jp.cssj.sakae.c.d.a.a.a("‘“（〔［｛〈《「『【⦅〖«〝");
/*     */   
/* 172 */   private static final b i = (b)new jp.cssj.sakae.c.d.a.a.a("’”）〕］｝〉》」』】⦆〙〗»〟");
/*     */   
/* 174 */   private static final b j = (b)new jp.cssj.sakae.c.d.a.a.a("。．、，");
/*     */   
/*     */   public short a(int sgid, int gid) {
/* 177 */     int scid = g(sgid);
/*     */     
/* 179 */     if (h.a((char)scid) && c(sgid) > 500) {
/* 180 */       int cid = g(gid);
/* 181 */       if (h.a((char)cid) && c(gid) > 500) {
/* 182 */         return 500;
/*     */       }
/* 184 */     } else if (i.a((char)scid) && c(sgid) > 500) {
/* 185 */       int cid = g(gid);
/* 186 */       if ((h.a((char)cid) && c(gid) > 500) || (i
/* 187 */         .a((char)cid) && c(gid) > 500) || j.a((char)cid)) {
/* 188 */         return 500;
/*     */       }
/* 190 */     } else if (j.a((char)scid)) {
/* 191 */       int cid = g(gid);
/* 192 */       if ((h.a((char)cid) && c(gid) > 500) || (i
/* 193 */         .a((char)cid) && c(gid) > 500)) {
/* 194 */         return 500;
/*     */       }
/*     */     } 
/* 197 */     return 0;
/*     */   }
/*     */   protected abstract int g(int paramInt);
/*     */   public int b(int sgid, int gid) {
/* 201 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/a/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */