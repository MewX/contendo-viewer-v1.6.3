/*    */ package net.a.a.e.c.d;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.mathml.MathMLElement;
/*    */ import org.w3c.dom.mathml.MathMLLabeledRowElement;
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
/*    */ public final class e
/*    */   extends b
/*    */   implements MathMLLabeledRowElement
/*    */ {
/*    */   public static final String r = "mlabeledtr";
/*    */   private static final long s = 1L;
/*    */   
/*    */   public e(String paramString, AbstractDocument paramAbstractDocument) {
/* 52 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 58 */     return (Node)new e(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */   
/*    */   public MathMLElement getLabel() {
/* 63 */     return (MathMLElement)a(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLabel(MathMLElement paramMathMLElement) {
/* 68 */     a(0, paramMathMLElement);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/d/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */