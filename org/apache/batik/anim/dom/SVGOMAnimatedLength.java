/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGOMAnimatedLength
/*    */   extends AbstractSVGAnimatedLength
/*    */ {
/*    */   protected String defaultValue;
/*    */   
/*    */   public SVGOMAnimatedLength(AbstractElement elt, String ns, String ln, String def, short dir, boolean nonneg) {
/* 51 */     super(elt, ns, ln, dir, nonneg);
/* 52 */     this.defaultValue = def;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getDefaultValue() {
/* 60 */     return this.defaultValue;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedLength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */