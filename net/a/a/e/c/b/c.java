/*    */ package net.a.a.e.c.b;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.a.a.c.f;
/*    */ import net.a.a.e.c.a;
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
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
/*    */   extends a
/*    */ {
/*    */   public static final String r = "merror";
/*    */   private static final long s = 1L;
/*    */   
/*    */   public c(String paramString, AbstractDocument paramAbstractDocument) {
/* 53 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 59 */     return (Node)new c(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public net.a.a.c b(net.a.a.c paramc) {
/* 66 */     return super
/* 67 */       .b((net.a.a.c)new f(paramc, null, Color.RED));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/b/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */