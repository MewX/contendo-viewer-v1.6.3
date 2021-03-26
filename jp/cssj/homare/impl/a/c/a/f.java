/*     */ package jp.cssj.homare.impl.a.c.a;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.css.c.d;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.c.o;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.C;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.a.a;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class f
/*     */   extends d
/*     */ {
/*  21 */   public static final o a = (o)new f();
/*     */   
/*     */   protected f() {
/*  24 */     super("border-radius");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*  30 */     E tlh = l.a(ua, lu);
/*  31 */     if (tlh == null) {
/*  32 */       throw new l();
/*     */     }
/*  34 */     if (tlh.a() == 1003) {
/*  35 */       primitives.a(g.a, (ad)C.a);
/*  36 */       primitives.a(h.a, (ad)C.a);
/*  37 */       primitives.a(e.a, (ad)C.a);
/*  38 */       primitives.a(d.a, (ad)C.a);
/*     */       return;
/*     */     } 
/*  41 */     lu = lu.getNextLexicalUnit();
/*  42 */     if (lu == null) {
/*  43 */       a tl = a.a(tlh, tlh);
/*  44 */       primitives.a(g.a, (ad)tl);
/*  45 */       primitives.a(h.a, (ad)tl);
/*  46 */       primitives.a(e.a, (ad)tl);
/*  47 */       primitives.a(d.a, (ad)tl);
/*     */       return;
/*     */     } 
/*  50 */     if (lu.getLexicalUnitType() == 4) {
/*  51 */       E e3 = tlh, e2 = e3, e1 = e2;
/*  52 */       a(lu, ua, primitives, tlh, e1, e2, e3);
/*     */       return;
/*     */     } 
/*  55 */     E trh = l.a(ua, lu);
/*  56 */     if (trh == null) {
/*  57 */       throw new l();
/*     */     }
/*  59 */     lu = lu.getNextLexicalUnit();
/*  60 */     if (lu == null) {
/*  61 */       a tl = a.a(tlh, tlh);
/*  62 */       a tr = a.a(trh, trh);
/*  63 */       primitives.a(g.a, (ad)tl);
/*  64 */       primitives.a(h.a, (ad)tr);
/*  65 */       primitives.a(e.a, (ad)tl);
/*  66 */       primitives.a(d.a, (ad)tr);
/*     */       return;
/*     */     } 
/*  69 */     if (lu.getLexicalUnitType() == 4) {
/*  70 */       E e1 = tlh;
/*  71 */       E e2 = trh;
/*  72 */       a(lu, ua, primitives, tlh, trh, e1, e2);
/*     */       return;
/*     */     } 
/*  75 */     E brh = l.a(ua, lu);
/*  76 */     if (brh == null) {
/*  77 */       throw new l();
/*     */     }
/*  79 */     lu = lu.getNextLexicalUnit();
/*  80 */     if (lu == null) {
/*  81 */       a tl = a.a(tlh, tlh);
/*  82 */       a tr = a.a(trh, trh);
/*  83 */       a br = a.a(brh, brh);
/*  84 */       primitives.a(g.a, (ad)tl);
/*  85 */       primitives.a(h.a, (ad)tr);
/*  86 */       primitives.a(e.a, (ad)br);
/*  87 */       primitives.a(d.a, (ad)tr);
/*     */       return;
/*     */     } 
/*  90 */     if (lu.getLexicalUnitType() == 4) {
/*  91 */       E e = trh;
/*  92 */       a(lu, ua, primitives, tlh, trh, brh, e);
/*     */       return;
/*     */     } 
/*  95 */     E blh = l.a(ua, lu);
/*  96 */     if (blh == null) {
/*  97 */       throw new l();
/*     */     }
/*  99 */     lu = lu.getNextLexicalUnit();
/* 100 */     if (lu == null) {
/* 101 */       a tl = a.a(tlh, tlh);
/* 102 */       a tr = a.a(trh, trh);
/* 103 */       a br = a.a(brh, brh);
/* 104 */       a bl = a.a(blh, blh);
/* 105 */       primitives.a(g.a, (ad)tl);
/* 106 */       primitives.a(h.a, (ad)tr);
/* 107 */       primitives.a(e.a, (ad)br);
/* 108 */       primitives.a(d.a, (ad)bl);
/*     */       return;
/*     */     } 
/* 111 */     if (lu.getLexicalUnitType() == 4) {
/* 112 */       a(lu, ua, primitives, tlh, trh, brh, blh);
/*     */       return;
/*     */     } 
/* 115 */     throw new l();
/*     */   }
/*     */   
/*     */   public void a(LexicalUnit lu, m ua, d.a primitives, E tlh, E trh, E brh, E blh) throws l {
/*     */     E trv, brv, blv;
/* 120 */     lu = lu.getNextLexicalUnit();
/* 121 */     if (lu == null) {
/* 122 */       throw new l();
/*     */     }
/*     */     
/* 125 */     E tlv = l.a(ua, lu);
/* 126 */     if (tlv == null) {
/* 127 */       throw new l();
/*     */     }
/* 129 */     lu = lu.getNextLexicalUnit();
/* 130 */     if (lu == null) {
/* 131 */       trv = brv = blv = tlv;
/*     */     } else {
/* 133 */       trv = l.a(ua, lu);
/* 134 */       if (trv == null) {
/* 135 */         throw new l();
/*     */       }
/* 137 */       lu = lu.getNextLexicalUnit();
/* 138 */       if (lu == null) {
/* 139 */         brv = tlv;
/* 140 */         blv = trv;
/*     */       } else {
/* 142 */         brv = l.a(ua, lu);
/* 143 */         if (brv == null) {
/* 144 */           throw new l();
/*     */         }
/* 146 */         lu = lu.getNextLexicalUnit();
/* 147 */         if (lu == null) {
/* 148 */           blv = trv;
/*     */         } else {
/* 150 */           blv = l.a(ua, lu);
/* 151 */           if (blv == null) {
/* 152 */             throw new l();
/*     */           }
/* 154 */           lu = lu.getNextLexicalUnit();
/* 155 */           if (lu != null) {
/* 156 */             throw new l();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 161 */     a tl = a.a(tlh, tlv);
/* 162 */     a tr = a.a(trh, trv);
/* 163 */     a br = a.a(brh, brv);
/* 164 */     a bl = a.a(blh, blv);
/* 165 */     primitives.a(g.a, (ad)tl);
/* 166 */     primitives.a(h.a, (ad)tr);
/* 167 */     primitives.a(e.a, (ad)br);
/* 168 */     primitives.a(d.a, (ad)bl);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */