/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.Y;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class an
/*    */   extends b
/*    */ {
/* 19 */   public static final j a = (j)new an();
/*    */   
/*    */   public static byte c(c style) {
/* 22 */     Y value = (Y)style.a(a);
/* 23 */     return value.b();
/*    */   }
/*    */   
/*    */   protected an() {
/* 27 */     super("text-decoration");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 31 */     return (ad)Y.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 39 */     return value;
/*    */   }
/*    */ 
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 44 */     if (lu.getLexicalUnitType() == 35) {
/* 45 */       Y value; String ident = lu.getStringValue().toLowerCase();
/* 46 */       if (ident.equals("none")) {
/* 47 */         value = Y.a;
/*    */       } else {
/* 49 */         byte flags = 0;
/*    */         while (true) {
/* 51 */           if (ident.equals("underline")) {
/* 52 */             flags = (byte)(flags | 0x1);
/* 53 */           } else if (ident.equals("overline")) {
/* 54 */             flags = (byte)(flags | 0x2);
/* 55 */           } else if (ident.equals("line-through")) {
/* 56 */             flags = (byte)(flags | 0x4);
/* 57 */           } else if (ident.equals("blink")) {
/* 58 */             flags = (byte)(flags | 0x8);
/*    */           } else {
/* 60 */             throw new l();
/*    */           } 
/*    */           
/* 63 */           lu = lu.getNextLexicalUnit();
/* 64 */           if (lu == null) {
/*    */             break;
/*    */           }
/* 67 */           if (lu.getLexicalUnitType() != 35) {
/* 68 */             throw new l();
/*    */           }
/* 70 */           ident = lu.getStringValue().toLowerCase();
/*    */         } 
/* 72 */         value = Y.a(flags);
/*    */       } 
/* 74 */       return (ad)value;
/*    */     } 
/* 76 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/an.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */