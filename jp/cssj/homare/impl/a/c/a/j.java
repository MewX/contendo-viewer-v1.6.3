/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.a.l;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.a.k;
/*    */ import jp.cssj.sakae.c.a.l;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class j
/*    */   extends b
/*    */ {
/* 22 */   public static final b a = new j();
/*    */   
/*    */   public static l c(c style) {
/* 25 */     return (l)style.a((jp.cssj.homare.css.c.j)a);
/*    */   }
/*    */   
/*    */   protected j() {
/* 29 */     super("unicode-range");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 33 */     return (ad)l.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 41 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 45 */     List<k> list = new ArrayList<>(); do {
/*    */       k unicodeRange;
/* 47 */       if (lu.getLexicalUnitType() == 0) {
/* 48 */         lu = lu.getNextLexicalUnit();
/* 49 */         if (lu == null) {
/*    */           break;
/*    */         }
/*    */       } 
/* 53 */       if (lu.getLexicalUnitType() != 39) {
/* 54 */         throw new l();
/*    */       }
/* 56 */       String str = lu.getStringValue();
/*    */       
/*    */       try {
/* 59 */         unicodeRange = k.a(str);
/* 60 */       } catch (NumberFormatException e) {
/* 61 */         throw new l();
/*    */       } 
/* 63 */       list.add(unicodeRange);
/* 64 */       lu = lu.getNextLexicalUnit();
/* 65 */     } while (lu != null);
/* 66 */     return (ad)new l(list.<k>toArray(new k[list.size()]));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */