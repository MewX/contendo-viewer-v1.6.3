/*    */ package jp.cssj.homare.impl.objects.barcode;
/*    */ 
/*    */ import jp.cssj.homare.css.InlineObjectFactory;
/*    */ import jp.cssj.homare.css.a;
/*    */ import jp.cssj.homare.css.h;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BarcodeInlineObjectFactory
/*    */   implements InlineObjectFactory
/*    */ {
/*    */   private static final String a = "http://barcode4j.krysalis.org/ns";
/*    */   
/*    */   public boolean match(a key) {
/* 17 */     a ce = key;
/* 18 */     return ce.B.equals("http://barcode4j.krysalis.org/ns");
/*    */   }
/*    */   
/*    */   public h createInlineObject() {
/* 22 */     return new b();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/BarcodeInlineObjectFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */