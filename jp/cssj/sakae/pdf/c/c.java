/*     */ package jp.cssj.sakae.pdf.c;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import jp.cssj.sakae.a.e;
/*     */ import jp.cssj.sakae.a.f;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.a.h;
/*     */ import jp.cssj.sakae.a.j;
/*     */ import jp.cssj.sakae.c.a.d;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ import jp.cssj.sakae.c.d.f;
/*     */ import jp.cssj.sakae.c.d.g;
/*     */ import jp.cssj.sakae.pdf.c.a.d.b;
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
/*     */ public class c
/*     */   implements e
/*     */ {
/*  34 */   protected g b = null;
/*     */ 
/*     */ 
/*     */   
/*  38 */   protected final Map<h, d> d = new HashMap<>(); private static final long f = 1L;
/*     */   
/*     */   public c(h fontdb, j fontStore) {
/*  41 */     if (!e && fontdb == null) throw new AssertionError(); 
/*  42 */     this.a = fontdb;
/*  43 */     this.c = fontStore;
/*     */   }
/*     */   protected final h a; protected final j c;
/*     */   public c(h fontdb) {
/*  47 */     this(fontdb, (j)new jp.cssj.sakae.a.c());
/*     */   }
/*     */   
/*     */   public void a(jp.cssj.sakae.c.a.a face) throws IOException {
/*  51 */     if (this.b == null) {
/*  52 */       this.b = new g(true);
/*     */     }
/*  54 */     this.b.a(face);
/*     */   }
/*     */   public d a(h fontStyle) {
/*     */     g[] fonts1;
/*  58 */     d flm = this.d.get(fontStyle);
/*  59 */     if (flm != null) {
/*  60 */       return flm;
/*     */     }
/*  62 */     int count = 1;
/*     */     
/*  64 */     if (this.b != null) {
/*  65 */       fonts1 = this.b.a(fontStyle);
/*  66 */       count += fonts1.length;
/*     */     } else {
/*  68 */       fonts1 = null;
/*     */     } 
/*  70 */     g[] fonts2 = this.a.a(fontStyle);
/*  71 */     count += fonts2.length;
/*  72 */     f[] fms = new f[count];
/*  73 */     int k = 0;
/*     */     
/*  75 */     if (fonts1 != null) {
/*  76 */       for (int m = 0; m < fonts1.length; m++) {
/*  77 */         fms[k++] = (f)new f(this.c, fonts1[m], fontStyle);
/*     */       }
/*     */     }
/*  80 */     for (int i = 0; i < fonts2.length; i++) {
/*  81 */       fms[k++] = (f)new f(this.c, fonts2[i], fontStyle);
/*     */     }
/*     */     
/*  84 */     if (fontStyle.a() == 3) {
/*  85 */       fms[fms.length - 1] = (f)new f(this.c, (g)b.j, fontStyle);
/*     */     } else {
/*  87 */       fms[fms.length - 1] = (f)new f(this.c, (g)b.i, fontStyle);
/*     */     } 
/*  89 */     flm = new d(fms);
/*  90 */     this.d.put(fontStyle, flm);
/*  91 */     return flm;
/*     */   }
/*     */   
/*     */   public f a() {
/*  95 */     return new a(this);
/*     */   }
/*     */   
/*     */   protected class a
/*     */     implements f
/*     */   {
/* 101 */     private final char[] d = new char[6];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     private d h = null;
/*     */     
/* 111 */     private int i = 0;
/*     */ 
/*     */ 
/*     */     
/* 115 */     private f k = null;
/*     */     
/* 117 */     private int l = -1; private boolean m = true; private e c;
/*     */     private int e;
/*     */     private byte f;
/*     */     private int g;
/*     */     private h j;
/*     */     
/*     */     public a(c this$0) {}
/*     */     
/*     */     public void a(e glyphHandler) {
/* 126 */       this.c = glyphHandler;
/*     */     }
/*     */     
/*     */     public void a(h fontStyle) {
/* 130 */       b();
/* 131 */       this.h = null;
/* 132 */       this.j = fontStyle;
/*     */     }
/*     */     
/*     */     private void b() {
/* 136 */       if (!this.m) {
/* 137 */         c();
/* 138 */         d();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void a(int charOffset, char[] ch, int off, int len) {
/* 143 */       if (this.h == null) {
/* 144 */         this.h = this.b.a(this.j);
/* 145 */         e();
/*     */       } 
/*     */       
/* 148 */       for (int k = 0; k < len; k++) {
/* 149 */         this.e = charOffset + k;
/* 150 */         char c1 = ch[k + off];
/*     */ 
/*     */         
/* 153 */         int cc = (c1 == ' ') ? 32 : c1;
/*     */ 
/*     */         
/* 156 */         char ls = Character.MIN_VALUE;
/* 157 */         if (Character.isHighSurrogate((char)cc) && 
/* 158 */           k + 1 < len && Character.isLowSurrogate(ch[k + off + 1])) {
/* 159 */           k++;
/* 160 */           cc = Character.toCodePoint((char)cc, ls = ch[k + off]);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 165 */         if (!this.k.c(cc)) {
/* 166 */           b();
/* 167 */           e();
/*     */         } 
/*     */ 
/*     */         
/* 171 */         for (int j = 0; j < this.i; j++) {
/* 172 */           f metrics = (f)this.h.a(j);
/* 173 */           if (metrics.c(cc)) {
/* 174 */             b();
/* 175 */             this.k = (f)this.h.a(j);
/* 176 */             this.i = j;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 181 */         if (this.m) {
/* 182 */           this.m = false;
/* 183 */           this.g = -1;
/* 184 */           this.l = -1;
/* 185 */           this.f = 0;
/* 186 */           this.c.a(charOffset, this.j, (f)this.k);
/*     */         } 
/*     */ 
/*     */         
/* 190 */         e font = this.k.a();
/* 191 */         int gid = font.a(cc);
/*     */ 
/*     */         
/* 194 */         int lgid = this.k.b(this.g, gid);
/* 195 */         if (lgid != -1) {
/*     */           
/* 197 */           this.g = this.l;
/* 198 */           gid = lgid;
/* 199 */           this.d[this.f] = c1;
/* 200 */           this.f = (byte)(this.f + 1);
/* 201 */           if (ls != '\000') {
/* 202 */             this.d[this.f] = ls;
/* 203 */             this.f = (byte)(this.f + 1);
/*     */           } 
/*     */         } else {
/*     */           
/* 207 */           if (this.g != -1) {
/* 208 */             c();
/*     */           }
/* 210 */           this.d[0] = c1;
/* 211 */           this.f = 1;
/* 212 */           if (ls != '\000') {
/* 213 */             this.d[1] = ls;
/* 214 */             this.f = 2;
/*     */           } 
/*     */         } 
/* 217 */         this.l = this.g;
/* 218 */         this.g = gid;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void a(g quad) {
/* 223 */       b();
/* 224 */       this.c.a(quad);
/*     */     }
/*     */     
/*     */     private void c() {
/* 228 */       this.c.a(this.e, this.d, 0, this.f, this.g);
/*     */     }
/*     */     
/*     */     private void d() {
/* 232 */       if (!a && this.m) throw new AssertionError(); 
/* 233 */       this.c.a();
/* 234 */       this.m = true;
/*     */     }
/*     */     
/*     */     private void e() {
/* 238 */       this.i = this.h.b();
/* 239 */       this.k = (f)this.h.a(this.i - 1);
/*     */     }
/*     */     
/*     */     public void a() {
/* 243 */       b();
/* 244 */       this.c.b();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */