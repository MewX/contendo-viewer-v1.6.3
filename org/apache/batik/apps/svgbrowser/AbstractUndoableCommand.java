/*    */ package org.apache.batik.apps.svgbrowser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractUndoableCommand
/*    */   implements UndoableCommand
/*    */ {
/*    */   protected String name;
/*    */   
/*    */   public void execute() {}
/*    */   
/*    */   public void undo() {}
/*    */   
/*    */   public void redo() {}
/*    */   
/*    */   public String getName() {
/* 44 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 54 */     this.name = name;
/*    */   }
/*    */   
/*    */   public boolean shouldExecute() {
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/AbstractUndoableCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */