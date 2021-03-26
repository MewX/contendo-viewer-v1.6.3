/*    */ package jp.cssj.homare.impl.a.c.b;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.f.C;
/*    */ import jp.cssj.homare.css.f.H;
/*    */ import jp.cssj.homare.css.f.V;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.ae;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   extends b
/*    */ {
/* 24 */   public static final j a = (j)new f();
/*    */   
/*    */   public static String[] c(c style) {
/* 27 */     ad value = style.a(a);
/* 28 */     if (value.a() == 1007) {
/* 29 */       return null;
/*    */     }
/* 31 */     ae valueList = (ae)value;
/* 32 */     ad[] values = valueList.b();
/* 33 */     String[] names = new String[values.length];
/* 34 */     for (int i = 0; i < names.length; i++) {
/* 35 */       names[i] = ((V)values[i]).b();
/*    */     }
/* 37 */     return names;
/*    */   }
/*    */   
/*    */   private f() {
/* 41 */     super("-cssj-page-content-clear");
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 45 */     return value;
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 49 */     return (ad)H.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 53 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 57 */     if (lu.getLexicalUnitType() == 12) {
/* 58 */       return (ad)C.a;
/*    */     }
/* 60 */     List<ad> list = new ArrayList<>(); do {
/*    */       V v2, v1;
/*    */       String ident;
/* 63 */       short luType = lu.getLexicalUnitType();
/* 64 */       switch (luType) {
/*    */         case 35:
/* 66 */           ident = lu.getStringValue().toLowerCase();
/* 67 */           if (ident.equals("none")) {
/* 68 */             H h = H.a; break;
/*    */           } 
/* 70 */           v2 = new V(lu.getStringValue());
/*    */           break;
/*    */ 
/*    */         
/*    */         case 36:
/* 75 */           v1 = new V(lu.getStringValue());
/*    */           break;
/*    */         
/*    */         default:
/* 79 */           throw new l();
/*    */       } 
/* 81 */       list.add(v1);
/* 82 */       lu = lu.getNextLexicalUnit();
/* 83 */     } while (lu != null);
/*    */     
/* 85 */     return (ad)new ae(list.<ad>toArray(new ad[list.size()]));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/b/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */