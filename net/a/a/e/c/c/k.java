/*    */ package net.a.a.e.c.c;
/*    */ 
/*    */ import net.a.a.e.d;
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.mathml.MathMLElement;
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
/*    */ public final class k
/*    */   extends c
/*    */ {
/*    */   public static final String v = "munderover";
/*    */   private static final long w = 1L;
/*    */   
/*    */   public k(String paramString, AbstractDocument paramAbstractDocument) {
/* 50 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 56 */     return (Node)new k(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public d k() {
/* 62 */     return a(2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public d l() {
/* 68 */     return a(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOverscript(MathMLElement paramMathMLElement) {
/* 73 */     a(2, paramMathMLElement);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUnderscript(MathMLElement paramMathMLElement) {
/* 78 */     a(1, paramMathMLElement);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/c/k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */