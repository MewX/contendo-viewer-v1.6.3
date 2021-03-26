/*    */ package org.apache.batik.gvt.event;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GraphicsNodeMouseWheelEvent
/*    */   extends GraphicsNodeInputEvent
/*    */ {
/*    */   public static final int MOUSE_WHEEL = 600;
/*    */   protected int wheelDelta;
/*    */   
/*    */   public GraphicsNodeMouseWheelEvent(GraphicsNode source, int id, long when, int modifiers, int lockState, int wheelDelta) {
/* 53 */     super(source, id, when, modifiers, lockState);
/* 54 */     this.wheelDelta = wheelDelta;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWheelDelta() {
/* 61 */     return this.wheelDelta;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeMouseWheelEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */