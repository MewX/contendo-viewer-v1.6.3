/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.EventListener;
/*     */ import java.util.EventObject;
/*     */ import javax.swing.event.EventListenerList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HistoryBrowser
/*     */ {
/*     */   public static final int EXECUTING = 1;
/*     */   public static final int UNDOING = 2;
/*     */   public static final int REDOING = 3;
/*     */   public static final int IDLE = 4;
/*  58 */   protected EventListenerList eventListeners = new EventListenerList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ArrayList history;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   protected int currentCommandIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   protected int historySize = 1000;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   protected int state = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CommandController commandController;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HistoryBrowser(CommandController commandController) {
/*  91 */     this.history = new ArrayList();
/*  92 */     this.commandController = commandController;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HistoryBrowser(int historySize) {
/* 100 */     this.history = new ArrayList();
/* 101 */     setHistorySize(historySize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setHistorySize(int size) {
/* 111 */     this.historySize = size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommandController(CommandController newCommandController) {
/* 121 */     this.commandController = newCommandController;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCommand(UndoableCommand command) {
/* 133 */     int n = this.history.size();
/* 134 */     for (int i = n - 1; i > this.currentCommandIndex; i--) {
/* 135 */       this.history.remove(i);
/*     */     }
/*     */     
/* 138 */     if (this.commandController != null) {
/* 139 */       this.commandController.execute(command);
/*     */     } else {
/* 141 */       this.state = 1;
/* 142 */       command.execute();
/* 143 */       this.state = 4;
/*     */     } 
/*     */     
/* 146 */     this.history.add(command);
/*     */ 
/*     */     
/* 149 */     this.currentCommandIndex = this.history.size() - 1;
/* 150 */     if (this.currentCommandIndex >= this.historySize) {
/* 151 */       this.history.remove(0);
/* 152 */       this.currentCommandIndex--;
/*     */     } 
/* 154 */     fireExecutePerformed(new HistoryBrowserEvent(new CommandNamesInfo(command.getName(), getLastUndoableCommandName(), getLastRedoableCommandName())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void undo() {
/* 164 */     if (this.history.isEmpty() || this.currentCommandIndex < 0) {
/*     */       return;
/*     */     }
/*     */     
/* 168 */     UndoableCommand command = this.history.get(this.currentCommandIndex);
/*     */     
/* 170 */     if (this.commandController != null) {
/* 171 */       this.commandController.undo(command);
/*     */     } else {
/* 173 */       this.state = 2;
/* 174 */       command.undo();
/* 175 */       this.state = 4;
/*     */     } 
/*     */     
/* 178 */     this.currentCommandIndex--;
/* 179 */     fireUndoPerformed(new HistoryBrowserEvent(new CommandNamesInfo(command.getName(), getLastUndoableCommandName(), getLastRedoableCommandName())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void redo() {
/* 189 */     if (this.history.isEmpty() || this.currentCommandIndex == this.history.size() - 1) {
/*     */       return;
/*     */     }
/*     */     
/* 193 */     UndoableCommand command = this.history.get(++this.currentCommandIndex);
/*     */     
/* 195 */     if (this.commandController != null) {
/* 196 */       this.commandController.redo(command);
/*     */     } else {
/* 198 */       this.state = 3;
/* 199 */       command.redo();
/* 200 */       this.state = 4;
/*     */     } 
/* 202 */     fireRedoPerformed(new HistoryBrowserEvent(new CommandNamesInfo(command.getName(), getLastUndoableCommandName(), getLastRedoableCommandName())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void compoundUndo(int undoNumber) {
/* 214 */     for (int i = 0; i < undoNumber; i++) {
/* 215 */       undo();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void compoundRedo(int redoNumber) {
/* 226 */     for (int i = 0; i < redoNumber; i++) {
/* 227 */       redo();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLastUndoableCommandName() {
/* 237 */     if (this.history.isEmpty() || this.currentCommandIndex < 0) {
/* 238 */       return "";
/*     */     }
/* 240 */     return ((UndoableCommand)this.history.get(this.currentCommandIndex)).getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLastRedoableCommandName() {
/* 249 */     if (this.history.isEmpty() || this.currentCommandIndex == this.history.size() - 1) {
/* 250 */       return "";
/*     */     }
/* 252 */     return ((UndoableCommand)this.history.get(this.currentCommandIndex + 1)).getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetHistory() {
/* 260 */     this.history.clear();
/* 261 */     this.currentCommandIndex = -1;
/* 262 */     fireHistoryReset(new HistoryBrowserEvent(new Object()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getState() {
/* 271 */     if (this.commandController != null) {
/* 272 */       return this.commandController.getState();
/*     */     }
/* 274 */     return this.state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class HistoryBrowserEvent
/*     */     extends EventObject
/*     */   {
/*     */     public HistoryBrowserEvent(Object source) {
/* 289 */       super(source);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface HistoryBrowserListener
/*     */     extends EventListener
/*     */   {
/*     */     void executePerformed(HistoryBrowser.HistoryBrowserEvent param1HistoryBrowserEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void undoPerformed(HistoryBrowser.HistoryBrowserEvent param1HistoryBrowserEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void redoPerformed(HistoryBrowser.HistoryBrowserEvent param1HistoryBrowserEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void historyReset(HistoryBrowser.HistoryBrowserEvent param1HistoryBrowserEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void doCompoundEdit(HistoryBrowser.HistoryBrowserEvent param1HistoryBrowserEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void compoundEditPerformed(HistoryBrowser.HistoryBrowserEvent param1HistoryBrowserEvent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class HistoryBrowserAdapter
/*     */     implements HistoryBrowserListener
/*     */   {
/*     */     public void executePerformed(HistoryBrowser.HistoryBrowserEvent event) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void undoPerformed(HistoryBrowser.HistoryBrowserEvent event) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void redoPerformed(HistoryBrowser.HistoryBrowserEvent event) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void historyReset(HistoryBrowser.HistoryBrowserEvent event) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void compoundEditPerformed(HistoryBrowser.HistoryBrowserEvent event) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void doCompoundEdit(HistoryBrowser.HistoryBrowserEvent event) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addListener(HistoryBrowserListener listener) {
/* 363 */     this.eventListeners.add(HistoryBrowserListener.class, listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireExecutePerformed(HistoryBrowserEvent event) {
/* 373 */     Object[] listeners = this.eventListeners.getListenerList();
/* 374 */     int length = listeners.length;
/* 375 */     for (int i = 0; i < length; i += 2) {
/* 376 */       if (listeners[i] == HistoryBrowserListener.class) {
/* 377 */         ((HistoryBrowserListener)listeners[i + 1]).executePerformed(event);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireUndoPerformed(HistoryBrowserEvent event) {
/* 390 */     Object[] listeners = this.eventListeners.getListenerList();
/* 391 */     int length = listeners.length;
/* 392 */     for (int i = 0; i < length; i += 2) {
/* 393 */       if (listeners[i] == HistoryBrowserListener.class) {
/* 394 */         ((HistoryBrowserListener)listeners[i + 1]).undoPerformed(event);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireRedoPerformed(HistoryBrowserEvent event) {
/* 407 */     Object[] listeners = this.eventListeners.getListenerList();
/* 408 */     int length = listeners.length;
/* 409 */     for (int i = 0; i < length; i += 2) {
/* 410 */       if (listeners[i] == HistoryBrowserListener.class) {
/* 411 */         ((HistoryBrowserListener)listeners[i + 1]).redoPerformed(event);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireHistoryReset(HistoryBrowserEvent event) {
/* 424 */     Object[] listeners = this.eventListeners.getListenerList();
/* 425 */     int length = listeners.length;
/* 426 */     for (int i = 0; i < length; i += 2) {
/* 427 */       if (listeners[i] == HistoryBrowserListener.class) {
/* 428 */         ((HistoryBrowserListener)listeners[i + 1]).historyReset(event);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireDoCompoundEdit(HistoryBrowserEvent event) {
/* 441 */     Object[] listeners = this.eventListeners.getListenerList();
/* 442 */     int length = listeners.length;
/* 443 */     for (int i = 0; i < length; i += 2) {
/* 444 */       if (listeners[i] == HistoryBrowserListener.class) {
/* 445 */         ((HistoryBrowserListener)listeners[i + 1]).doCompoundEdit(event);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fireCompoundEditPerformed(HistoryBrowserEvent event) {
/* 458 */     Object[] listeners = this.eventListeners.getListenerList();
/* 459 */     int length = listeners.length;
/* 460 */     for (int i = 0; i < length; i += 2) {
/* 461 */       if (listeners[i] == HistoryBrowserListener.class) {
/* 462 */         ((HistoryBrowserListener)listeners[i + 1]).compoundEditPerformed(event);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class CommandNamesInfo
/*     */   {
/*     */     private String lastUndoableCommandName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String lastRedoableCommandName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String commandName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CommandNamesInfo(String commandName, String lastUndoableCommandName, String lastRedoableCommandName) {
/* 502 */       this.lastUndoableCommandName = lastUndoableCommandName;
/* 503 */       this.lastRedoableCommandName = lastRedoableCommandName;
/* 504 */       this.commandName = commandName;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getLastRedoableCommandName() {
/* 513 */       return this.lastRedoableCommandName;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getLastUndoableCommandName() {
/* 522 */       return this.lastUndoableCommandName;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getCommandName() {
/* 531 */       return this.commandName;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface CommandController
/*     */   {
/*     */     void execute(UndoableCommand param1UndoableCommand);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void undo(UndoableCommand param1UndoableCommand);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void redo(UndoableCommand param1UndoableCommand);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int getState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DocumentCommandController
/*     */     implements CommandController
/*     */   {
/*     */     protected DOMViewerController controller;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 575 */     protected int state = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DocumentCommandController(DOMViewerController controller) {
/* 584 */       this.controller = controller;
/*     */     }
/*     */     
/*     */     public void execute(final UndoableCommand command) {
/* 588 */       Runnable r = new Runnable() {
/*     */           public void run() {
/* 590 */             HistoryBrowser.DocumentCommandController.this.state = 1;
/* 591 */             command.execute();
/* 592 */             HistoryBrowser.DocumentCommandController.this.state = 4;
/*     */           }
/*     */         };
/* 595 */       this.controller.performUpdate(r);
/*     */     }
/*     */     
/*     */     public void undo(final UndoableCommand command) {
/* 599 */       Runnable r = new Runnable() {
/*     */           public void run() {
/* 601 */             HistoryBrowser.DocumentCommandController.this.state = 2;
/* 602 */             command.undo();
/* 603 */             HistoryBrowser.DocumentCommandController.this.state = 4;
/*     */           }
/*     */         };
/* 606 */       this.controller.performUpdate(r);
/*     */     }
/*     */     
/*     */     public void redo(final UndoableCommand command) {
/* 610 */       Runnable r = new Runnable() {
/*     */           public void run() {
/* 612 */             HistoryBrowser.DocumentCommandController.this.state = 3;
/* 613 */             command.redo();
/* 614 */             HistoryBrowser.DocumentCommandController.this.state = 4;
/*     */           }
/*     */         };
/* 617 */       this.controller.performUpdate(r);
/*     */     }
/*     */     
/*     */     public int getState() {
/* 621 */       return this.state;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/HistoryBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */