/*    */ package net.a.a.e.c.c;
/*    */ 
/*    */ import net.a.a.e.d;
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.DOMException;
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
/*    */ public final class j
/*    */   extends c
/*    */ {
/*    */   public static final String v = "munder";
/*    */   private static final long w = 1L;
/*    */   
/*    */   public j(String paramString, AbstractDocument paramAbstractDocument) {
/* 51 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 57 */     return (Node)new j(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public d k() {
/* 63 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public d l() {
/* 69 */     return a(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOverscript(MathMLElement paramMathMLElement) {
/* 74 */     throw new DOMException((short)3, "munder does not have overscript");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setUnderscript(MathMLElement paramMathMLElement) {
/* 80 */     a(1, paramMathMLElement);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/c/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */