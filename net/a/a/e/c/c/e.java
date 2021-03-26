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
/*    */ public final class e
/*    */   extends c
/*    */ {
/*    */   public static final String v = "mover";
/*    */   private static final long w = 1L;
/*    */   
/*    */   public e(String paramString, AbstractDocument paramAbstractDocument) {
/* 51 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 57 */     return (Node)new e(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public d k() {
/* 63 */     return a(1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public d l() {
/* 69 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOverscript(MathMLElement paramMathMLElement) {
/* 74 */     a(1, paramMathMLElement);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUnderscript(MathMLElement paramMathMLElement) {
/* 79 */     throw new DOMException((short)3, "mover does not have underscript");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/c/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */