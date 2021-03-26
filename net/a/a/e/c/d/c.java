/*    */ package net.a.a.e.c.d;
/*    */ 
/*    */ import net.a.a.e.b;
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.mathml.MathMLAlignGroupElement;
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
/*    */ public final class c
/*    */   extends b
/*    */   implements MathMLAlignGroupElement
/*    */ {
/*    */   public static final String r = "maligngroup";
/*    */   public static final String s = "groupalign";
/*    */   private static final long t = 1L;
/*    */   
/*    */   public c(String paramString, AbstractDocument paramAbstractDocument) {
/* 55 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 61 */     return (Node)new c(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGroupalign() {
/* 66 */     return a("groupalign");
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGroupalign(String paramString) {
/* 71 */     setAttribute("groupalign", paramString);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/d/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */