/*     */ package jp.cssj.sakae.c.d;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.h;
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
/*     */ public class i
/*     */   extends a
/*     */   implements Serializable
/*     */ {
/*  45 */   public double j = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public double k = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public double[] l = null; private static final long n = 0L; private static final int o = 50; public final h a; public final f b; private int p;
/*     */   
/*     */   public i(int charOffset, h fontStyle, f fontMetrics) {
/*  58 */     if (!m && fontStyle == null) throw new AssertionError("FontStyle required."); 
/*  59 */     if (!m && fontMetrics == null) throw new AssertionError("FontMetrics required."); 
/*  60 */     this.a = fontStyle;
/*  61 */     this.b = fontMetrics;
/*  62 */     this.p = charOffset;
/*  63 */     int len = Math.max(this.g, 50);
/*  64 */     this.e = new int[len];
/*  65 */     this.f = new byte[len];
/*  66 */     this.c = new char[len];
/*     */   }
/*     */   public char[] c; public int d; public int[] e; public byte[] f; public int g;
/*     */   public int e() {
/*  70 */     return this.p;
/*     */   }
/*     */   
/*     */   public double c() {
/*  74 */     double advance = this.j + this.k * this.g;
/*  75 */     if (this.l != null) {
/*  76 */       for (int j = 0; j < this.l.length; j++) {
/*  77 */         advance += this.l[j];
/*     */       }
/*     */     }
/*  80 */     return advance;
/*     */   }
/*     */   
/*     */   public double[] a(boolean make) {
/*  84 */     if (make) {
/*  85 */       this.l = new double[this.g];
/*     */     }
/*  87 */     return this.l;
/*     */   }
/*     */   
/*     */   public double f() {
/*  91 */     return this.b.c();
/*     */   }
/*     */   
/*     */   public double g() {
/*  95 */     return this.b.d();
/*     */   }
/*     */   
/*     */   public h b() {
/*  99 */     return this.a;
/*     */   }
/*     */   
/*     */   public f d() {
/* 103 */     return this.b;
/*     */   }
/*     */   
/*     */   public char[] h() {
/* 107 */     return this.c;
/*     */   }
/*     */   
/*     */   public int i() {
/* 111 */     return this.d;
/*     */   }
/*     */   
/*     */   public int[] j() {
/* 115 */     return this.e;
/*     */   }
/*     */   
/*     */   public byte[] k() {
/* 119 */     return this.f;
/*     */   }
/*     */   
/*     */   public int l() {
/* 123 */     return this.g;
/*     */   }
/*     */   
/*     */   public double m() {
/* 127 */     return this.k;
/*     */   }
/*     */   
/*     */   public void a(double letterSpacing) {
/* 131 */     this.k = letterSpacing;
/*     */   }
/*     */   
/*     */   public h a(int goff) {
/* 135 */     if (!m && goff <= 0) throw new AssertionError("goff <= 0 では分割できません: goff=" + goff); 
/* 136 */     if (!m && goff >= this.g) throw new AssertionError("goff が glen == " + this.g + " 以上では分割できません: goff=" + goff);
/*     */ 
/*     */     
/* 139 */     i text = new i(this.p, this.a, this.b);
/* 140 */     text.g = goff;
/* 141 */     text.e = new int[text.g];
/* 142 */     text.f = new byte[text.g];
/* 143 */     for (int j = 0; j < text.g; j++) {
/* 144 */       int gid = this.e[j];
/* 145 */       text.e[j] = gid;
/* 146 */       text.j += this.b.a(gid);
/* 147 */       if (j > 0) {
/* 148 */         text.j -= this.b.a(text.e[j - 1], gid);
/*     */       }
/* 150 */       int clen = text.f[j] = this.f[j];
/* 151 */       text.d += clen;
/*     */     } 
/* 153 */     text.k = this.k;
/*     */     
/* 155 */     text.c = new char[text.d];
/* 156 */     System.arraycopy(this.c, 0, text.c, 0, text.d);
/*     */     
/* 158 */     this.g -= text.g;
/* 159 */     System.arraycopy(this.e, text.g, this.e, 0, this.g);
/* 160 */     System.arraycopy(this.f, text.g, this.f, 0, this.g);
/*     */     
/* 162 */     this.j -= text.j;
/*     */     
/* 164 */     this.j += this.b.a(text.e[text.g - 1], this.e[0]);
/*     */     
/* 166 */     this.d -= text.d;
/* 167 */     this.p += text.d;
/* 168 */     System.arraycopy(this.c, text.d, this.c, 0, this.d);
/*     */     
/* 170 */     return text;
/*     */   }
/*     */   
/*     */   public double b(int gid) {
/* 174 */     double advance = this.b.a(gid);
/* 175 */     if (this.g > 0) {
/* 176 */       double kerning = this.b.a(this.e[this.g - 1], gid);
/* 177 */       advance -= kerning;
/*     */     } 
/* 179 */     return advance;
/*     */   }
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
/*     */   public double a(char[] ch, int coff, byte clen, int gid) {
/* 192 */     double advance = b(gid);
/* 193 */     int glen = this.g + 1;
/* 194 */     if (glen > this.e.length) {
/* 195 */       int newLen = glen * 3 / 2;
/*     */       
/* 197 */       int[] arrayOfInt = this.e;
/* 198 */       this.e = new int[newLen];
/* 199 */       System.arraycopy(arrayOfInt, 0, this.e, 0, arrayOfInt.length);
/*     */ 
/*     */       
/* 202 */       byte[] arrayOfByte = this.f;
/* 203 */       this.f = new byte[newLen];
/* 204 */       System.arraycopy(arrayOfByte, 0, this.f, 0, arrayOfByte.length);
/*     */     } 
/*     */ 
/*     */     
/* 208 */     int newClen = this.d + clen;
/* 209 */     if (newClen > this.c.length) {
/* 210 */       char[] arrayOfChar = this.c;
/* 211 */       this.c = new char[Math.max(50, newClen * 3 / 2)];
/* 212 */       System.arraycopy(arrayOfChar, 0, this.c, 0, arrayOfChar.length);
/*     */     } 
/* 214 */     System.arraycopy(ch, coff, this.c, this.d, clen);
/* 215 */     this.d = newClen;
/*     */     
/* 217 */     this.e[this.g] = gid;
/* 218 */     this.f[this.g] = clen;
/* 219 */     this.g = glen;
/* 220 */     this.j += advance;
/* 221 */     return advance;
/*     */   }
/*     */   
/*     */   public void n() {
/* 225 */     if (!m && this.g <= 0) throw new AssertionError("空のテキストです"); 
/* 226 */     if (this.e.length != this.g) {
/* 227 */       int[] gids = new int[this.g];
/* 228 */       System.arraycopy(this.e, 0, gids, 0, this.g);
/* 229 */       this.e = gids;
/*     */       
/* 231 */       byte[] clens = new byte[this.g];
/* 232 */       System.arraycopy(this.f, 0, clens, 0, this.g);
/* 233 */       this.f = clens;
/*     */     } 
/* 235 */     if (this.d != this.c.length) {
/* 236 */       char[] chars = new char[this.d];
/* 237 */       System.arraycopy(this.c, 0, chars, 0, this.d);
/* 238 */       this.c = chars;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */