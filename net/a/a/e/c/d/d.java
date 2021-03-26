/*    */ package net.a.a.e.c.d;
/*    */ 
/*    */ import net.a.a.e.b;
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.mathml.MathMLAlignMarkElement;
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
/*    */ public final class d
/*    */   extends b
/*    */   implements MathMLAlignMarkElement
/*    */ {
/*    */   public static final String r = "malignmark";
/*    */   public static final String s = "edge";
/*    */   private static final long t = 1L;
/*    */   
/*    */   public d(String paramString, AbstractDocument paramAbstractDocument) {
/* 54 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 60 */     return (Node)new d(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getEdge() {
/* 65 */     return a("edge");
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEdge(String paramString) {
/* 70 */     setAttribute("edge", paramString);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/d/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */