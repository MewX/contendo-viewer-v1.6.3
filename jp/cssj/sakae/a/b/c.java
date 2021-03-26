/*     */ package jp.cssj.sakae.a.b;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.sakae.a.a;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.a.e;
/*     */ import jp.cssj.sakae.c.a.j;
/*     */ import net.zamasoft.a.a;
/*     */ import net.zamasoft.a.b;
/*     */ import net.zamasoft.a.b.L;
/*     */ import net.zamasoft.a.b.M;
/*     */ import net.zamasoft.a.b.N;
/*     */ import net.zamasoft.a.b.O;
/*     */ import net.zamasoft.a.b.a;
/*     */ import net.zamasoft.a.b.ah;
/*     */ import net.zamasoft.a.b.r;
/*     */ import net.zamasoft.a.b.s;
/*     */ import net.zamasoft.a.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends a
/*     */ {
/*  35 */   private static final Logger y = Logger.getLogger(c.class.getName());
/*     */   
/*     */   private static final long z = 4L;
/*     */   
/*  39 */   protected static Map<File, a> i = new WeakHashMap<>();
/*     */   
/*     */   protected final File j;
/*     */   
/*     */   protected final int k;
/*     */   
/*     */   protected final short l;
/*     */   protected String m;
/*     */   protected final b n;
/*     */   protected final short o;
/*     */   protected final short p;
/*     */   protected final short q;
/*     */   protected final short r;
/*     */   protected final short s;
/*     */   protected final short t;
/*     */   protected final short u;
/*     */   protected j v;
/*     */   protected final byte w;
/*     */   protected final a x;
/*     */   
/*     */   public c(File file, int index, byte direction) throws IOException {
/*  60 */     this.k = index;
/*  61 */     this.j = file;
/*  62 */     d ttFont = o();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     r head = (r)ttFont.a(1751474532);
/*  68 */     this.l = head.l();
/*  69 */     short llx = (short)(head.o() * 1000 / this.l);
/*  70 */     short lly = (short)(head.q() * 1000 / this.l);
/*  71 */     short urx = (short)(head.n() * 1000 / this.l);
/*  72 */     short ury = (short)(head.p() * 1000 / this.l);
/*  73 */     b bbox = new b(llx, lly, urx, ury);
/*  74 */     this.n = bbox;
/*  75 */     a(((head.j() & 0x2) != 0));
/*     */ 
/*     */     
/*  78 */     Set<String> aliases = new TreeSet<>();
/*  79 */     String fontName = null;
/*     */ 
/*     */     
/*  82 */     M name = (M)ttFont.a(1851878757);
/*  83 */     for (int i = 0; i < name.b(); i++) {
/*  84 */       L record = name.a(i);
/*  85 */       short nameId = record.c();
/*  86 */       if (nameId == 1 || nameId == 3 || nameId == 4) {
/*  87 */         aliases.add(record.e());
/*  88 */       } else if (nameId == 6) {
/*  89 */         fontName = record.e();
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     this.X_ = aliases.<String>toArray(new String[aliases.size()]);
/*     */     
/*  95 */     if (fontName == null) {
/*  96 */       throw new NullPointerException();
/*     */     }
/*  98 */     this.m = fontName;
/*     */ 
/*     */ 
/*     */     
/* 102 */     N os2 = (N)ttFont.a(1330851634);
/* 103 */     short weight = (short)os2.d();
/* 104 */     a(weight);
/* 105 */     short cFamilyClass = os2.q();
/* 106 */     O panose = os2.r();
/* 107 */     this.v = new j(cFamilyClass, panose.a);
/*     */ 
/*     */ 
/*     */     
/* 111 */     s hhea = (s)ttFont.a(1751672161);
/* 112 */     this.o = (short)(hhea.c() * 1000 / this.l);
/* 113 */     this.p = (short)(-hhea.f() * 1000 / this.l);
/*     */ 
/*     */     
/* 116 */     a cmap = ((net.zamasoft.a.b.c)ttFont.a(1668112752)).a((short)3, (short)10);
/*     */     
/* 118 */     if (cmap == null) {
/* 119 */       cmap = ((net.zamasoft.a.b.c)ttFont.a(1668112752)).a((short)3, (short)1);
/*     */     }
/* 121 */     if (cmap == null) {
/* 122 */       cmap = ((net.zamasoft.a.b.c)ttFont.a(1668112752)).a((short)0, (short)-1);
/*     */     }
/* 124 */     this.x = cmap;
/*     */     
/* 126 */     int gid = this.x.a(32);
/* 127 */     ah hmtx = (ah)ttFont.a(1752003704);
/* 128 */     this.s = (short)(hmtx.a(gid) * 1000 / this.l);
/*     */ 
/*     */ 
/*     */     
/* 132 */     d font = o();
/* 133 */     b gx = font.b(this.x.a(120));
/* 134 */     this.q = (gx == null || gx.a() == null) ? 500 : (short)(gx.a().getBounds()).height;
/* 135 */     b gh = font.b(this.x.a(72));
/* 136 */     this.r = (gh == null || gh.a() == null) ? 700 : (short)(gh.a().getBounds()).height;
/*     */ 
/*     */     
/* 139 */     this.t = 0;
/* 140 */     this.u = 0;
/*     */     
/* 142 */     if (y.isLoggable(Level.FINE)) {
/* 143 */       y.fine("new font: " + d());
/*     */     }
/*     */     
/* 146 */     this.w = direction;
/*     */   }
/*     */   
/*     */   public d o() {
/* 150 */     return a(this.j, this.k);
/*     */   }
/*     */   
/*     */   public static synchronized d a(File file, int index) {
/* 154 */     a fontFile = i.get(file);
/*     */     try {
/* 156 */       if (fontFile != null) {
/* 157 */         return fontFile.a(index);
/*     */       }
/* 159 */       fontFile = new a(file);
/* 160 */       i.put(file, fontFile);
/* 161 */       return fontFile.a(index);
/* 162 */     } catch (Exception e) {
/* 163 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte e() {
/* 168 */     return this.w;
/*     */   }
/*     */   
/*     */   public void a(String fontName) {
/* 172 */     this.m = fontName;
/*     */   }
/*     */   
/*     */   public j p() {
/* 176 */     return this.v;
/*     */   }
/*     */   
/*     */   public void a(j panose) {
/* 180 */     this.v = panose;
/*     */   }
/*     */   
/*     */   public b f() {
/* 184 */     return this.n;
/*     */   }
/*     */   
/*     */   public String d() {
/* 188 */     return this.m;
/*     */   }
/*     */   
/*     */   public short l() {
/* 192 */     return this.q;
/*     */   }
/*     */   
/*     */   public short h() {
/* 196 */     return this.r;
/*     */   }
/*     */   
/*     */   public short m() {
/* 200 */     return this.s;
/*     */   }
/*     */   
/*     */   public short g() {
/* 204 */     return this.o;
/*     */   }
/*     */   
/*     */   public short i() {
/* 208 */     return this.p;
/*     */   }
/*     */   
/*     */   public short j() {
/* 212 */     return this.t;
/*     */   }
/*     */   
/*     */   public short k() {
/* 216 */     return this.u;
/*     */   }
/*     */   
/*     */   public short q() {
/* 220 */     return this.l;
/*     */   }
/*     */   
/*     */   public a r() {
/* 224 */     return this.x;
/*     */   }
/*     */   
/*     */   public boolean a(int i) {
/* 228 */     if (e() == 3 && (
/* 229 */       i <= 255 || (i >= 65376 && i <= 65503))) {
/* 230 */       return false;
/*     */     }
/*     */     
/* 233 */     return (this.x.a(i) != 0);
/*     */   }
/*     */   
/*     */   public e n() {
/* 237 */     return (e)new b(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/a/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */