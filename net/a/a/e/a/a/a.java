/*    */ package net.a.a.e.a.a;
/*    */ 
/*    */ import net.a.a.e.b;
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.mathml.MathMLAnnotationElement;
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
/*    */ public final class a
/*    */   extends b
/*    */   implements MathMLAnnotationElement
/*    */ {
/*    */   public static final String r = "annotation";
/*    */   public static final String s = "encoding";
/*    */   private static final long t = 1L;
/*    */   
/*    */   public a(String paramString, AbstractDocument paramAbstractDocument) {
/* 54 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 60 */     return (Node)new a(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getBody() {
/* 65 */     return f();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getEncoding() {
/* 70 */     return a("encoding");
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBody(String paramString) {
/* 75 */     setTextContent(paramString);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEncoding(String paramString) {
/* 80 */     setAttribute("encoding", paramString);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/a/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */