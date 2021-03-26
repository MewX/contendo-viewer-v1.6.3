/*     */ package jp.cssj.homare.b.b.a;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.b.i;
/*     */ import jp.cssj.homare.b.a.c.f;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.b.a;
/*     */ import jp.cssj.homare.b.b.b;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.d.b.a.a;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ import jp.cssj.sakae.c.d.g;
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
/*     */   private static final boolean b = false;
/*     */   final a a;
/*  31 */   private List<f> c = null;
/*     */   
/*     */   private boolean d = false;
/*     */   
/*     */   private boolean e;
/*     */   
/*     */   private byte f;
/*     */   
/*     */   public c(a builder) {
/*  40 */     this.a = builder;
/*  41 */     b((f)this.a.k().c_());
/*     */   }
/*     */   
/*     */   public void a(f params) {
/*  45 */     if (this.c == null) {
/*  46 */       this.c = new ArrayList<>();
/*     */     }
/*  48 */     this.c.add(params);
/*  49 */     b(params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() {
/*  56 */     f params = this.c.remove(this.c.size() - 1);
/*     */ 
/*     */ 
/*     */     
/*  60 */     if (this.c.isEmpty()) {
/*  61 */       i i = this.a.k().c_();
/*     */     } else {
/*  63 */       params = this.c.get(this.c.size() - 1);
/*     */     } 
/*  65 */     b(params);
/*     */   }
/*     */   
/*     */   public void d() {
/*  69 */     i i = this.a.k().c_();
/*  70 */     b((f)i);
/*     */   }
/*     */   
/*     */   private void b(f params) {
/*  74 */     switch (params.K) {
/*     */       case 2:
/*  76 */         this.e = false;
/*     */         break;
/*     */       
/*     */       case 3:
/*  80 */         this.e = false;
/*     */         break;
/*     */       
/*     */       case 1:
/*  84 */         this.e = true;
/*     */         break;
/*     */       
/*     */       case 5:
/*  88 */         this.e = true;
/*     */         break;
/*     */       
/*     */       case 4:
/*  92 */         this.e = true;
/*     */         break;
/*     */       default:
/*  95 */         throw new IllegalStateException();
/*     */     } 
/*  97 */     this.f = params.D;
/*     */   }
/*     */   
/*     */   public void a(int charOffset, h fontStyle, f fontMetrics) {
/* 101 */     this.a.a(charOffset, fontStyle, fontMetrics);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int charOffset, char[] ch, int coff, byte clen, int gid) {
/* 106 */     this.a.a(charOffset, ch, coff, clen, gid);
/*     */   }
/*     */   
/*     */   public void a() {
/* 110 */     this.a.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(g quad) {
/* 115 */     if (quad instanceof b) {
/*     */       b.e inlineStartQuad; b.c inlineEndQuad; i inlineBox; double endAdvance, startAdvance; b.d inlineReplacedQuad; double advance; f params; double d1;
/* 117 */       b inlineQuad = (b)quad;
/* 118 */       switch (inlineQuad.b()) {
/*     */         
/*     */         case 1:
/* 121 */           inlineStartQuad = (b.e)inlineQuad;
/* 122 */           inlineBox = inlineStartQuad.g;
/*     */ 
/*     */           
/* 125 */           switch (this.f) {
/*     */             
/*     */             case 1:
/* 128 */               startAdvance = inlineStartQuad.g.m().b();
/*     */               break;
/*     */             
/*     */             case 2:
/*     */             case 3:
/* 133 */               startAdvance = inlineStartQuad.g.m().a();
/*     */               break;
/*     */             default:
/* 136 */               throw new IllegalStateException();
/*     */           } 
/* 138 */           inlineStartQuad.f = startAdvance;
/*     */           
/* 140 */           params = inlineBox.g();
/* 141 */           a(params);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 147 */           inlineEndQuad = (b.c)inlineQuad;
/* 148 */           c();
/*     */ 
/*     */           
/* 151 */           switch (this.f) {
/*     */             
/*     */             case 1:
/* 154 */               endAdvance = inlineEndQuad.g.m().d();
/*     */               break;
/*     */             
/*     */             case 2:
/*     */             case 3:
/* 159 */               endAdvance = inlineEndQuad.g.m().c();
/*     */               break;
/*     */             default:
/* 162 */               throw new IllegalStateException();
/*     */           } 
/* 164 */           inlineEndQuad.f = endAdvance;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 169 */           inlineReplacedQuad = (b.d)inlineQuad;
/* 170 */           e.a(this.a, inlineReplacedQuad.g);
/*     */           
/* 172 */           switch (this.f) {
/*     */             
/*     */             case 1:
/* 175 */               d1 = inlineReplacedQuad.g.p();
/*     */               break;
/*     */             
/*     */             case 2:
/*     */             case 3:
/* 180 */               d1 = inlineReplacedQuad.g.q();
/*     */               break;
/*     */             default:
/* 183 */               throw new IllegalStateException();
/*     */           } 
/* 185 */           inlineReplacedQuad.f = d1;
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 192 */           switch (this.f) {
/*     */             
/*     */             case 1:
/* 195 */               advance = inlineQuad.a().p();
/*     */               break;
/*     */             
/*     */             case 2:
/*     */             case 3:
/* 200 */               advance = inlineQuad.a().q();
/*     */               break;
/*     */             default:
/* 203 */               throw new IllegalStateException();
/*     */           } 
/* 205 */           inlineQuad.f = advance;
/*     */           break;
/*     */         
/*     */         case 5:
/*     */           break;
/*     */         
/*     */         default:
/* 212 */           throw new IllegalStateException();
/*     */       } 
/*     */     
/*     */     } else {
/* 216 */       a control = (a)quad;
/* 217 */       switch (control.e()) {
/*     */         case '\n':
/* 219 */           this.d = true;
/*     */           break;
/*     */         
/*     */         case '\t':
/*     */         case ' ':
/*     */           break;
/*     */         
/*     */         default:
/* 227 */           throw new IllegalStateException();
/*     */       } 
/*     */     } 
/* 230 */     this.a.a(quad);
/*     */   }
/*     */   
/*     */   public void b() {
/* 234 */     if (!this.e && !this.d) {
/*     */       return;
/*     */     }
/* 237 */     this.d = false;
/* 238 */     this.a.b();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */