/*    */ package net.a.a.e.c.c;
/*    */ 
/*    */ import net.a.a.c;
/*    */ import net.a.a.e.d;
/*    */ import net.a.a.g.d;
/*    */ import net.a.a.g.f;
/*    */ import net.a.a.g.g;
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.mathml.MathMLElement;
/*    */ import org.w3c.dom.mathml.MathMLScriptElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class b
/*    */   extends a
/*    */   implements MathMLScriptElement
/*    */ {
/*    */   public b(String paramString, AbstractDocument paramAbstractDocument) {
/* 50 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(d paramd, c paramc) {
/* 66 */     return paramd.isSameNode((Node)a());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(g paramg, d paramd, f paramf, c paramc) {
/* 74 */     m.a(paramg, paramd, paramf, 
/* 75 */         b(paramc), (d)this, a(), 
/* 76 */         j(), i(), 
/* 77 */         getSubscriptshift(), getSuperscriptshift());
/*    */   }
/*    */   
/*    */   public abstract d a();
/*    */   
/*    */   public abstract d i();
/*    */   
/*    */   public abstract d j();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/c/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */