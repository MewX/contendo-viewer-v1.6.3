/*    */ package org.apache.batik.gvt.event;
/*    */ 
/*    */ import java.util.EventObject;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GraphicsNodeEvent
/*    */   extends EventObject
/*    */ {
/*    */   private boolean consumed = false;
/*    */   protected int id;
/*    */   
/*    */   public GraphicsNodeEvent(GraphicsNode source, int id) {
/* 45 */     super(source);
/* 46 */     this.id = id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getID() {
/* 53 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GraphicsNode getGraphicsNode() {
/* 60 */     return (GraphicsNode)this.source;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void consume() {
/* 68 */     this.consumed = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isConsumed() {
/* 75 */     return this.consumed;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */