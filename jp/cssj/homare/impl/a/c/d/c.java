/*     */ package jp.cssj.homare.impl.a.c.d;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.css.c.d;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.c.o;
/*     */ import jp.cssj.homare.css.f.C;
/*     */ import jp.cssj.homare.css.f.aa;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.n;
/*     */ import jp.cssj.homare.impl.a.c.f;
/*     */ import jp.cssj.homare.impl.a.c.j;
/*     */ import jp.cssj.homare.impl.a.c.m;
/*     */ import jp.cssj.homare.impl.a.c.q;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends d
/*     */ {
/*  24 */   public static final o a = (o)new c();
/*     */   
/*     */   protected c() {
/*  27 */     super("border-color");
/*     */   }
/*     */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*     */     n n1, n2, n3, n4;
/*  31 */     if (lu.getLexicalUnitType() == 12) {
/*  32 */       primitives.a(j.a, (ad)C.a);
/*  33 */       primitives.a(q.a, (ad)C.a);
/*  34 */       primitives.a(m.a, (ad)C.a);
/*  35 */       primitives.a(f.a, (ad)C.a);
/*     */       
/*     */       return;
/*     */     } 
/*  39 */     if (jp.cssj.homare.css.e.c.a(lu)) {
/*  40 */       aa aa = aa.a;
/*     */     } else {
/*  42 */       n1 = jp.cssj.homare.css.e.c.a(ua, lu);
/*     */     } 
/*  44 */     if (n1 == null) {
/*  45 */       throw new l();
/*     */     }
/*  47 */     lu = lu.getNextLexicalUnit();
/*  48 */     if (lu == null) {
/*  49 */       primitives.a(j.a, (ad)n1);
/*  50 */       primitives.a(q.a, (ad)n1);
/*  51 */       primitives.a(m.a, (ad)n1);
/*  52 */       primitives.a(f.a, (ad)n1);
/*     */       
/*     */       return;
/*     */     } 
/*  56 */     if (jp.cssj.homare.css.e.c.a(lu)) {
/*  57 */       aa aa = aa.a;
/*     */     } else {
/*  59 */       n2 = jp.cssj.homare.css.e.c.a(ua, lu);
/*     */     } 
/*  61 */     if (n2 == null) {
/*  62 */       throw new l();
/*     */     }
/*  64 */     lu = lu.getNextLexicalUnit();
/*  65 */     if (lu == null) {
/*  66 */       primitives.a(j.a, (ad)n2);
/*  67 */       primitives.a(q.a, (ad)n1);
/*  68 */       primitives.a(m.a, (ad)n2);
/*  69 */       primitives.a(f.a, (ad)n1);
/*     */       
/*     */       return;
/*     */     } 
/*  73 */     if (jp.cssj.homare.css.e.c.a(lu)) {
/*  74 */       aa aa = aa.a;
/*     */     } else {
/*  76 */       n3 = jp.cssj.homare.css.e.c.a(ua, lu);
/*     */     } 
/*  78 */     if (n3 == null) {
/*  79 */       throw new l();
/*     */     }
/*  81 */     lu = lu.getNextLexicalUnit();
/*  82 */     if (lu == null) {
/*  83 */       primitives.a(j.a, (ad)n2);
/*  84 */       primitives.a(q.a, (ad)n1);
/*  85 */       primitives.a(m.a, (ad)n2);
/*  86 */       primitives.a(f.a, (ad)n3);
/*     */       
/*     */       return;
/*     */     } 
/*  90 */     if (jp.cssj.homare.css.e.c.a(lu)) {
/*  91 */       aa aa = aa.a;
/*     */     } else {
/*  93 */       n4 = jp.cssj.homare.css.e.c.a(ua, lu);
/*     */     } 
/*  95 */     if (n4 == null) {
/*  96 */       throw new l();
/*     */     }
/*  98 */     primitives.a(j.a, (ad)n4);
/*  99 */     primitives.a(q.a, (ad)n1);
/* 100 */     primitives.a(m.a, (ad)n2);
/* 101 */     primitives.a(f.a, (ad)n3);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */