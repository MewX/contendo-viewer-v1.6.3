/*    */ package jp.cssj.homare.impl.a.c.d;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import jp.cssj.homare.css.c.d;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.c.o;
/*    */ import jp.cssj.homare.css.e.e;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.F;
/*    */ import jp.cssj.homare.css.f.G;
/*    */ import jp.cssj.homare.css.f.ab;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.impl.a.c.P;
/*    */ import jp.cssj.homare.impl.a.c.Q;
/*    */ import jp.cssj.homare.impl.a.c.R;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class k
/*    */   extends d
/*    */ {
/* 26 */   public static final o a = (o)new k();
/*    */   
/*    */   protected k() {
/* 29 */     super("list-style");
/*    */   }
/*    */   
/*    */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/* 33 */     if (lu.getLexicalUnitType() == 12) {
/* 34 */       primitives.a(R.a, (ad)C.a);
/* 35 */       primitives.a(P.a, (ad)C.a);
/* 36 */       primitives.a(Q.a, (ad)C.a);
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     for (; lu != null; lu = lu.getNextLexicalUnit()) {
/* 41 */       G g; F f; switch (lu.getLexicalUnitType()) {
/*    */         
/*    */         case 24:
/*    */           try {
/* 45 */             ab imageUri = l.a(ua, uri, lu);
/* 46 */             primitives.a(P.a, (ad)imageUri);
/* 47 */           } catch (URISyntaxException e) {
/* 48 */             ua.a((short)10252, lu.getStringValue());
/*    */           } 
/*    */           break;
/*    */ 
/*    */         
/*    */         case 35:
/* 54 */           g = e.a(lu.getStringValue());
/* 55 */           if (g != null) {
/* 56 */             primitives.a(R.a, (ad)g);
/*    */             
/*    */             break;
/*    */           } 
/* 60 */           f = e.b(lu.getStringValue());
/* 61 */           if (f == null) {
/* 62 */             throw new l();
/*    */           }
/* 64 */           primitives.a(Q.a, (ad)f);
/*    */           break;
/*    */ 
/*    */         
/*    */         default:
/* 69 */           throw new l();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */