/*    */ package net.a.a.e.c.e;
/*    */ 
/*    */ import net.a.a.e.d.c.e;
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
/*    */ public final class c
/*    */   extends a
/*    */ {
/*    */   public static final String r = "mi";
/*    */   private static final long s = 1L;
/*    */   
/*    */   public c(String paramString, AbstractDocument paramAbstractDocument) {
/* 50 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 56 */     return (Node)new c(this.nodeName, this.ownerDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void h() {
/* 62 */     super.h();
/* 63 */     if (e.a(f()) == 1) {
/* 64 */       a("mathvariant", "italic");
/*    */     } else {
/*    */       
/* 67 */       a("mathvariant", "normal");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/e/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */