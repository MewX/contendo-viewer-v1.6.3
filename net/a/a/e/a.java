/*    */ package net.a.a.e;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.a.a.e.c.a;
/*    */ import net.a.a.g.i;
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class a
/*    */   extends a
/*    */ {
/*    */   private List<i> r;
/*    */   
/*    */   public a(String paramString, AbstractDocument paramAbstractDocument) {
/* 53 */     super(paramString, paramAbstractDocument);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract List<i> a();
/*    */ 
/*    */ 
/*    */   
/*    */   private void i() {
/* 64 */     if (this.r == null) {
/* 65 */       this.r = a();
/* 66 */       for (i i : this.r) {
/* 67 */         ((d)i).b((d)this);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<i> b() {
/* 75 */     i();
/* 76 */     return this.r;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<i> c() {
/* 82 */     i();
/* 83 */     return this.r;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */