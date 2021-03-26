/*    */ package org.apache.batik.gvt;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import org.apache.batik.gvt.event.GraphicsNodeChangeListener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RootGraphicsNode
/*    */   extends CompositeGraphicsNode
/*    */ {
/* 34 */   List treeGraphicsNodeChangeListeners = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RootGraphicsNode getRoot() {
/* 46 */     return this;
/*    */   }
/*    */   
/*    */   public List getTreeGraphicsNodeChangeListeners() {
/* 50 */     if (this.treeGraphicsNodeChangeListeners == null) {
/* 51 */       this.treeGraphicsNodeChangeListeners = new LinkedList();
/*    */     }
/* 53 */     return this.treeGraphicsNodeChangeListeners;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTreeGraphicsNodeChangeListener(GraphicsNodeChangeListener l) {
/* 58 */     getTreeGraphicsNodeChangeListeners().add(l);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeTreeGraphicsNodeChangeListener(GraphicsNodeChangeListener l) {
/* 63 */     getTreeGraphicsNodeChangeListeners().remove(l);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/RootGraphicsNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */