/*    */ package jp.cssj.homare.impl.objects.mathml;
/*    */ 
/*    */ import javax.xml.parsers.ParserConfigurationException;
/*    */ import jp.cssj.homare.css.InlineObjectFactory;
/*    */ import jp.cssj.homare.css.a;
/*    */ import jp.cssj.homare.css.h;
/*    */ 
/*    */ public class MathMLInlineObjectFactory
/*    */   implements InlineObjectFactory {
/*    */   public static final String URI = "http://www.w3.org/1998/Math/MathML";
/*    */   
/*    */   public boolean match(a key) {
/* 13 */     a ce = key;
/* 14 */     return "http://www.w3.org/1998/Math/MathML".equals(ce.B);
/*    */   }
/*    */   
/*    */   public h createInlineObject() {
/*    */     try {
/* 19 */       return new b();
/* 20 */     } catch (ParserConfigurationException e) {
/* 21 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/mathml/MathMLInlineObjectFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */