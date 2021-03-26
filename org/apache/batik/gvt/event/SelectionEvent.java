/*    */ package org.apache.batik.gvt.event;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SelectionEvent
/*    */ {
/*    */   public static final int SELECTION_CHANGED = 1;
/*    */   public static final int SELECTION_CLEARED = 3;
/*    */   public static final int SELECTION_STARTED = 4;
/*    */   public static final int SELECTION_DONE = 2;
/*    */   protected Shape highlightShape;
/*    */   protected Object selection;
/*    */   protected int id;
/*    */   
/*    */   public SelectionEvent(Object selection, int id, Shape highlightShape) {
/* 70 */     this.id = id;
/* 71 */     this.selection = selection;
/* 72 */     this.highlightShape = highlightShape;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Shape getHighlightShape() {
/* 79 */     return this.highlightShape;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getSelection() {
/* 87 */     return this.selection;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getID() {
/* 97 */     return this.id;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/SelectionEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */