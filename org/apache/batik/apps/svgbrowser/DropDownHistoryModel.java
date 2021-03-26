/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import org.apache.batik.util.gui.DropDownComponent;
/*     */ import org.apache.batik.util.resources.ResourceManager;
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
/*     */ 
/*     */ public class DropDownHistoryModel
/*     */   implements DropDownComponent.ScrollablePopupMenuModel
/*     */ {
/*     */   private static final String RESOURCES = "org.apache.batik.apps.svgbrowser.resources.DropDownHistoryModelMessages";
/*  59 */   private static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.apps.svgbrowser.resources.DropDownHistoryModelMessages", Locale.getDefault());
/*  60 */   private static ResourceManager resources = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected ArrayList items = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected HistoryBrowserInterface historyBrowserInterface;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DropDownComponent.ScrollablePopupMenu parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DropDownHistoryModel(DropDownComponent.ScrollablePopupMenu parent, HistoryBrowserInterface historyBrowserInterface) {
/*  89 */     this.parent = parent;
/*  90 */     this.historyBrowserInterface = historyBrowserInterface;
/*     */ 
/*     */     
/*  93 */     historyBrowserInterface.getHistoryBrowser().addListener(new HistoryBrowser.HistoryBrowserAdapter()
/*     */         {
/*     */           public void historyReset(HistoryBrowser.HistoryBrowserEvent event) {
/*  96 */             DropDownHistoryModel.this.clearAllScrollablePopupMenuItems("");
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFooterText() {
/* 107 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DropDownComponent.ScrollablePopupMenuItem createItem(String itemName) {
/* 118 */     return (DropDownComponent.ScrollablePopupMenuItem)new DropDownComponent.DefaultScrollablePopupMenuItem(this.parent, itemName);
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
/*     */   
/*     */   protected void addItem(DropDownComponent.ScrollablePopupMenuItem item, String details) {
/* 131 */     int oldSize = this.items.size();
/* 132 */     this.items.add(0, item);
/* 133 */     this.parent.add(item, 0, oldSize, this.items.size());
/* 134 */     this.parent.fireItemsWereAdded(new DropDownComponent.ScrollablePopupMenuEvent(this.parent, 1, 1, details));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeItem(DropDownComponent.ScrollablePopupMenuItem item, String details) {
/* 151 */     int oldSize = this.items.size();
/* 152 */     this.items.remove(item);
/* 153 */     this.parent.remove(item, oldSize, this.items.size());
/* 154 */     this.parent.fireItemsWereRemoved(new DropDownComponent.ScrollablePopupMenuEvent(this.parent, 2, 1, details));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean removeLastScrollablePopupMenuItem(String details) {
/* 170 */     int i = this.items.size() - 1; if (i >= 0) {
/* 171 */       DropDownComponent.ScrollablePopupMenuItem item = this.items.get(i);
/*     */       
/* 173 */       removeItem(item, details);
/* 174 */       return true;
/*     */     } 
/* 176 */     return false;
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
/*     */   protected boolean removeFirstScrollablePopupMenuItem(String details) {
/* 188 */     Iterator i$ = this.items.iterator(); if (i$.hasNext()) { Object item1 = i$.next();
/* 189 */       DropDownComponent.ScrollablePopupMenuItem item = (DropDownComponent.ScrollablePopupMenuItem)item1;
/*     */       
/* 191 */       removeItem(item, details);
/* 192 */       return true; }
/*     */     
/* 194 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clearAllScrollablePopupMenuItems(String details) {
/* 205 */     while (removeLastScrollablePopupMenuItem(details));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processItemClicked() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processBeforeShowed() {
/* 217 */     this.historyBrowserInterface.performCurrentCompoundCommand();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processAfterShowed() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class UndoPopUpMenuModel
/*     */     extends DropDownHistoryModel
/*     */   {
/* 231 */     protected static String UNDO_FOOTER_TEXT = DropDownHistoryModel.resources.getString("UndoModel.footerText");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     protected static String UNDO_TOOLTIP_PREFIX = DropDownHistoryModel.resources.getString("UndoModel.tooltipPrefix");
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
/*     */     public UndoPopUpMenuModel(DropDownComponent.ScrollablePopupMenu parent, HistoryBrowserInterface historyBrowserInterface) {
/* 253 */       super(parent, historyBrowserInterface);
/* 254 */       init();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void init() {
/* 261 */       this.historyBrowserInterface.getHistoryBrowser().addListener(new HistoryBrowser.HistoryBrowserAdapter()
/*     */           {
/*     */             public void executePerformed(HistoryBrowser.HistoryBrowserEvent event) {
/* 264 */               HistoryBrowser.CommandNamesInfo info = (HistoryBrowser.CommandNamesInfo)event.getSource();
/*     */               
/* 266 */               String details = DropDownHistoryModel.UndoPopUpMenuModel.UNDO_TOOLTIP_PREFIX + info.getLastUndoableCommandName();
/*     */               
/* 268 */               DropDownHistoryModel.UndoPopUpMenuModel.this.addItem(DropDownHistoryModel.UndoPopUpMenuModel.this.createItem(info.getCommandName()), details);
/*     */             }
/*     */             
/*     */             public void undoPerformed(HistoryBrowser.HistoryBrowserEvent event) {
/* 272 */               HistoryBrowser.CommandNamesInfo info = (HistoryBrowser.CommandNamesInfo)event.getSource();
/*     */               
/* 274 */               String details = DropDownHistoryModel.UndoPopUpMenuModel.UNDO_TOOLTIP_PREFIX + info.getLastUndoableCommandName();
/*     */               
/* 276 */               DropDownHistoryModel.UndoPopUpMenuModel.this.removeFirstScrollablePopupMenuItem(details);
/*     */             }
/*     */             
/*     */             public void redoPerformed(HistoryBrowser.HistoryBrowserEvent event) {
/* 280 */               HistoryBrowser.CommandNamesInfo info = (HistoryBrowser.CommandNamesInfo)event.getSource();
/*     */               
/* 282 */               String details = DropDownHistoryModel.UndoPopUpMenuModel.UNDO_TOOLTIP_PREFIX + info.getLastUndoableCommandName();
/*     */               
/* 284 */               DropDownHistoryModel.UndoPopUpMenuModel.this.addItem(DropDownHistoryModel.UndoPopUpMenuModel.this.createItem(info.getCommandName()), details);
/*     */             }
/*     */             
/*     */             public void doCompoundEdit(HistoryBrowser.HistoryBrowserEvent event) {
/* 288 */               if (!DropDownHistoryModel.UndoPopUpMenuModel.this.parent.isEnabled()) {
/* 289 */                 DropDownHistoryModel.UndoPopUpMenuModel.this.parent.setEnabled(true);
/*     */               }
/*     */             }
/*     */ 
/*     */             
/*     */             public void compoundEditPerformed(HistoryBrowser.HistoryBrowserEvent event) {}
/*     */           });
/*     */     }
/*     */ 
/*     */     
/*     */     public String getFooterText() {
/* 300 */       return UNDO_FOOTER_TEXT;
/*     */     }
/*     */     
/*     */     public void processItemClicked() {
/* 304 */       this.historyBrowserInterface.getHistoryBrowser().compoundUndo(this.parent.getSelectedItemsCount());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class RedoPopUpMenuModel
/*     */     extends DropDownHistoryModel
/*     */   {
/* 317 */     protected static String REDO_FOOTER_TEXT = DropDownHistoryModel.resources.getString("RedoModel.footerText");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     protected static String REDO_TOOLTIP_PREFIX = DropDownHistoryModel.resources.getString("RedoModel.tooltipPrefix");
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
/*     */     public RedoPopUpMenuModel(DropDownComponent.ScrollablePopupMenu parent, HistoryBrowserInterface historyBrowserInterface) {
/* 339 */       super(parent, historyBrowserInterface);
/* 340 */       init();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void init() {
/* 347 */       this.historyBrowserInterface.getHistoryBrowser().addListener(new HistoryBrowser.HistoryBrowserAdapter()
/*     */           {
/*     */             public void executePerformed(HistoryBrowser.HistoryBrowserEvent event)
/*     */             {
/* 351 */               HistoryBrowser.CommandNamesInfo info = (HistoryBrowser.CommandNamesInfo)event.getSource();
/*     */               
/* 353 */               String details = DropDownHistoryModel.RedoPopUpMenuModel.REDO_TOOLTIP_PREFIX + info.getLastRedoableCommandName();
/*     */               
/* 355 */               DropDownHistoryModel.RedoPopUpMenuModel.this.clearAllScrollablePopupMenuItems(details);
/*     */             }
/*     */             
/*     */             public void undoPerformed(HistoryBrowser.HistoryBrowserEvent event) {
/* 359 */               HistoryBrowser.CommandNamesInfo info = (HistoryBrowser.CommandNamesInfo)event.getSource();
/*     */               
/* 361 */               String details = DropDownHistoryModel.RedoPopUpMenuModel.REDO_TOOLTIP_PREFIX + info.getLastRedoableCommandName();
/*     */               
/* 363 */               DropDownHistoryModel.RedoPopUpMenuModel.this.addItem(DropDownHistoryModel.RedoPopUpMenuModel.this.createItem(info.getCommandName()), details);
/*     */             }
/*     */             
/*     */             public void redoPerformed(HistoryBrowser.HistoryBrowserEvent event) {
/* 367 */               HistoryBrowser.CommandNamesInfo info = (HistoryBrowser.CommandNamesInfo)event.getSource();
/*     */               
/* 369 */               String details = DropDownHistoryModel.RedoPopUpMenuModel.REDO_TOOLTIP_PREFIX + info.getLastRedoableCommandName();
/*     */               
/* 371 */               DropDownHistoryModel.RedoPopUpMenuModel.this.removeFirstScrollablePopupMenuItem(details);
/*     */             }
/*     */             
/*     */             public void doCompoundEdit(HistoryBrowser.HistoryBrowserEvent event) {
/* 375 */               if (DropDownHistoryModel.RedoPopUpMenuModel.this.parent.isEnabled()) {
/* 376 */                 DropDownHistoryModel.RedoPopUpMenuModel.this.parent.setEnabled(false);
/*     */               }
/*     */             }
/*     */ 
/*     */             
/*     */             public void compoundEditPerformed(HistoryBrowser.HistoryBrowserEvent event) {}
/*     */           });
/*     */     }
/*     */ 
/*     */     
/*     */     public String getFooterText() {
/* 387 */       return REDO_FOOTER_TEXT;
/*     */     }
/*     */     
/*     */     public void processItemClicked() {
/* 391 */       this.historyBrowserInterface.getHistoryBrowser().compoundRedo(this.parent.getSelectedItemsCount());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/DropDownHistoryModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */