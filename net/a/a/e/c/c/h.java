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
/*    */ 
/*    */ public final class h
/*    */   extends b
/*    */ {
/*    */   public static final String t = "msubsup";
/*    */   private static final long u = 1L;
/*    */   
/*    */   public h(String paramString, AbstractDocument paramAbstractDocument) {
/* 51 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 57 */     return (Node)new h(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public d a() {
/* 63 */     return a(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public d j() {
/* 69 */     return a(1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public d i() {
/* 75 */     return a(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBase(MathMLElement paramMathMLElement) {
/* 80 */     a(0, paramMathMLElement);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSubscript(MathMLElement paramMathMLElement) {
/* 85 */     a(1, paramMathMLElement);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSuperscript(MathMLElement paramMathMLElement) {
/* 90 */     a(2, paramMathMLElement);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/c/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */