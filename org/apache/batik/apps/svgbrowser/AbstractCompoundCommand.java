/*    */ package org.apache.batik.apps.svgbrowser;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractCompoundCommand
/*    */   extends AbstractUndoableCommand
/*    */ {
/* 40 */   protected ArrayList atomCommands = new ArrayList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addCommand(UndoableCommand command) {
/* 50 */     if (command.shouldExecute()) {
/* 51 */       this.atomCommands.add(command);
/*    */     }
/*    */   }
/*    */   
/*    */   public void execute() {
/* 56 */     int n = this.atomCommands.size();
/* 57 */     for (Object atomCommand : this.atomCommands) {
/* 58 */       UndoableCommand cmd = (UndoableCommand)atomCommand;
/* 59 */       cmd.execute();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void undo() {
/* 64 */     int size = this.atomCommands.size();
/* 65 */     for (int i = size - 1; i >= 0; i--) {
/* 66 */       UndoableCommand command = this.atomCommands.get(i);
/* 67 */       command.undo();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void redo() {
/* 72 */     int n = this.atomCommands.size();
/* 73 */     for (Object atomCommand : this.atomCommands) {
/* 74 */       UndoableCommand cmd = (UndoableCommand)atomCommand;
/* 75 */       cmd.redo();
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean shouldExecute() {
/* 80 */     boolean shouldExecute = true;
/* 81 */     if (this.atomCommands.size() == 0) {
/* 82 */       shouldExecute = false;
/*    */     }
/* 84 */     int n = this.atomCommands.size();
/* 85 */     for (int i = 0; i < n && shouldExecute; i++) {
/* 86 */       UndoableCommand command = this.atomCommands.get(i);
/* 87 */       shouldExecute = (command.shouldExecute() && shouldExecute);
/*    */     } 
/* 89 */     return shouldExecute;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCommandNumber() {
/* 98 */     return this.atomCommands.size();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/AbstractCompoundCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */