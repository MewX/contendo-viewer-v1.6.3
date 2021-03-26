/*    */ package org.apache.batik.gvt.filter;
/*    */ 
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConcreteGraphicsNodeRableFactory
/*    */   implements GraphicsNodeRableFactory
/*    */ {
/*    */   public GraphicsNodeRable createGraphicsNodeRable(GraphicsNode node) {
/* 37 */     return (GraphicsNodeRable)node.getGraphicsNodeRable(true);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/filter/ConcreteGraphicsNodeRableFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */