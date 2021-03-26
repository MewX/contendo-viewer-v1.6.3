/*    */ package jp.cssj.homare.css.d;
/*    */ 
/*    */ import jp.cssj.homare.css.a;
/*    */ import jp.cssj.homare.css.f.H;
/*    */ import jp.cssj.homare.css.f.N;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.t;
/*    */ import jp.cssj.homare.css.k;
/*    */ import jp.cssj.homare.css.l;
/*    */ import jp.cssj.homare.impl.a.c.G;
/*    */ import jp.cssj.homare.impl.a.c.b.e;
/*    */ import jp.cssj.homare.impl.a.c.b.g;
/*    */ import jp.cssj.homare.impl.a.c.y;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class c
/*    */   extends b
/*    */ {
/*    */   public c(l styleContext) {
/* 23 */     super(styleContext, (byte[])null, (String)null);
/*    */   }
/*    */   
/*    */   public void a(e builder) {
/* 27 */     k applier = new k(builder.a(), this.b);
/* 28 */     a page = builder.b();
/* 29 */     this.b.a(page);
/* 30 */     int j = 0;
/* 31 */     for (int i = 0; i < this.h.b(); i++) {
/* 32 */       jp.cssj.homare.css.c c1; int charOffset; jp.cssj.homare.css.c style; a a1; char[] chars; a ce; switch (this.h.a(i)) {
/*    */         case 1:
/* 34 */           c1 = (jp.cssj.homare.css.c)this.g.get(j++);
/* 35 */           a1 = c1.a();
/* 36 */           if (!a1.a()) {
/*    */             jp.cssj.homare.css.c parentStyle;
/* 38 */             if (i == 0) {
/* 39 */               parentStyle = c1.c();
/*    */             } else {
/* 41 */               parentStyle = builder.c();
/*    */             } 
/* 43 */             c1 = jp.cssj.homare.css.c.a(c1.b(), parentStyle, a1);
/* 44 */             applier.a(c1);
/* 45 */             if (i == 0) {
/* 46 */               c1.a(G.a, (ad)t.r, (byte)1);
/* 47 */               c1.a(e.a, (ad)H.a, (byte)1);
/* 48 */               c1.a(g.a, (ad)H.a, (byte)1);
/* 49 */               c1.a(y.a, (ad)N.j, (byte)1);
/*    */             } 
/*    */             
/* 52 */             builder.a(c1);
/*    */           } 
/*    */           break;
/*    */         
/*    */         case 2:
/* 57 */           charOffset = ((Integer)this.g.get(j++)).intValue();
/* 58 */           chars = (char[])this.g.get(j++);
/* 59 */           builder.a(charOffset, chars, 0, chars.length);
/*    */           break;
/*    */         
/*    */         case 3:
/* 63 */           style = (jp.cssj.homare.css.c)this.g.get(j++);
/* 64 */           ce = style.a();
/* 65 */           if (!ce.a()) {
/* 66 */             builder.d();
/* 67 */             applier.c();
/*    */           } 
/*    */           break;
/*    */ 
/*    */         
/*    */         default:
/* 73 */           throw new IllegalStateException();
/*    */       } 
/*    */     } 
/* 76 */     this.b.a();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/d/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */