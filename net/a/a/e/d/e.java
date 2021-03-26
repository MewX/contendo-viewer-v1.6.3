/*    */ package net.a.a.e.d;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.mathml.MathMLNodeList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */   implements MathMLNodeList
/*    */ {
/*    */   private final List<Node> a;
/*    */   
/*    */   public e(List<Node> paramList) {
/* 42 */     this.a = paramList;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLength() {
/* 47 */     return this.a.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public Node item(int paramInt) {
/* 52 */     return this.a.get(paramInt);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */