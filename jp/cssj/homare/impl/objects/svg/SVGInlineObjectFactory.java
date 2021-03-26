/*    */ package jp.cssj.homare.impl.objects.svg;
/*    */ 
/*    */ import jp.cssj.homare.css.InlineObjectFactory;
/*    */ import jp.cssj.homare.css.a;
/*    */ import jp.cssj.homare.css.h;
/*    */ 
/*    */ public class SVGInlineObjectFactory implements InlineObjectFactory {
/*    */   public static final String URI = "http://www.w3.org/2000/svg";
/*    */   
/*    */   public boolean match(a key) {
/* 11 */     a ce = key;
/* 12 */     return "http://www.w3.org/2000/svg".equals(ce.B);
/*    */   }
/*    */   
/*    */   public h createInlineObject() {
/* 16 */     return new a();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/svg/SVGInlineObjectFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */