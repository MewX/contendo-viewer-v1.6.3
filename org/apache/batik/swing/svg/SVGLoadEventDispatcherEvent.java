/*    */ package org.apache.batik.swing.svg;
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
/*    */ 
/*    */ 
/*    */ public class SVGLoadEventDispatcherEvent
/*    */   extends EventObject
/*    */ {
/*    */   protected GraphicsNode gvtRoot;
/*    */   
/*    */   public SVGLoadEventDispatcherEvent(Object source, GraphicsNode root) {
/* 46 */     super(source);
/* 47 */     this.gvtRoot = root;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GraphicsNode getGVTRoot() {
/* 55 */     return this.gvtRoot;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGLoadEventDispatcherEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */