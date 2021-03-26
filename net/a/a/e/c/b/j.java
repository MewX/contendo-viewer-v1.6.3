/*    */ package net.a.a.e.c.b;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.a.a.e.d;
/*    */ import net.a.a.g.i;
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
/*    */ public final class j
/*    */   extends a
/*    */ {
/*    */   public static final String r = "msqrt";
/*    */   private static final long s = 1L;
/*    */   
/*    */   public j(String paramString, AbstractDocument paramAbstractDocument) {
/* 53 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 59 */     return (Node)new j(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected List<i> a() {
/* 65 */     return b();
/*    */   }
/*    */ 
/*    */   
/*    */   public MathMLElement getIndex() {
/* 70 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public MathMLElement getRadicand() {
/*    */     i i;
/* 76 */     if (e() == 1) {
/* 77 */       d d = a(0);
/*    */     } else {
/* 79 */       i = new i("mrow", this.ownerDocument);
/* 80 */       i.b((d)this);
/* 81 */       for (byte b = 0; b < e(); b++) {
/* 82 */         i.appendChild((Node)a(b));
/*    */       }
/*    */     } 
/* 85 */     return (MathMLElement)i;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setIndex(MathMLElement paramMathMLElement) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRadicand(MathMLElement paramMathMLElement) {
/* 95 */     while (e() > 0) {
/* 96 */       removeChild((Node)a(0));
/*    */     }
/* 98 */     a(0, paramMathMLElement);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/b/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */