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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GraphicsNodeChangeEvent
/*    */   extends GraphicsNodeEvent
/*    */ {
/*    */   static final int CHANGE_FIRST = 9800;
/*    */   public static final int CHANGE_STARTED = 9800;
/*    */   public static final int CHANGE_COMPLETED = 9801;
/*    */   protected GraphicsNode changeSource;
/*    */   
/*    */   public GraphicsNodeChangeEvent(GraphicsNode source, int id) {
/* 58 */     super(source, id);
/*    */   }
/* 60 */   public void setChangeSrc(GraphicsNode gn) { this.changeSource = gn; } public GraphicsNode getChangeSrc() {
/* 61 */     return this.changeSource;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeChangeEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */