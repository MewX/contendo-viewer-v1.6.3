/*    */ package org.apache.batik.util.gui.xmleditor;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import javax.swing.JEditorPane;
/*    */ import javax.swing.event.UndoableEditEvent;
/*    */ import javax.swing.event.UndoableEditListener;
/*    */ import javax.swing.text.Element;
/*    */ import javax.swing.undo.CannotRedoException;
/*    */ import javax.swing.undo.CannotUndoException;
/*    */ import javax.swing.undo.UndoManager;
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
/*    */ public class XMLTextEditor
/*    */   extends JEditorPane
/*    */ {
/*    */   protected UndoManager undoManager;
/*    */   
/*    */   public XMLTextEditor() {
/* 51 */     XMLEditorKit kit = new XMLEditorKit();
/* 52 */     setEditorKitForContentType("text/xml", kit);
/* 53 */     setContentType("text/xml");
/* 54 */     setBackground(Color.white);
/*    */ 
/*    */ 
/*    */     
/* 58 */     this.undoManager = new UndoManager();
/* 59 */     UndoableEditListener undoableEditHandler = new UndoableEditListener() {
/*    */         public void undoableEditHappened(UndoableEditEvent e) {
/* 61 */           XMLTextEditor.this.undoManager.addEdit(e.getEdit());
/*    */         }
/*    */       };
/* 64 */     getDocument().addUndoableEditListener(undoableEditHandler);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setText(String t) {
/* 70 */     super.setText(t);
/*    */     
/* 72 */     this.undoManager.discardAllEdits();
/*    */   }
/*    */ 
/*    */   
/*    */   public void undo() {
/*    */     try {
/* 78 */       this.undoManager.undo();
/* 79 */     } catch (CannotUndoException cannotUndoException) {}
/*    */   }
/*    */ 
/*    */   
/*    */   public void redo() {
/*    */     try {
/* 85 */       this.undoManager.redo();
/* 86 */     } catch (CannotRedoException cannotRedoException) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void gotoLine(int line) {
/* 95 */     Element element = getDocument().getDefaultRootElement().getElement(line);
/*    */     
/* 97 */     if (element == null)
/* 98 */       return;  int pos = element.getStartOffset();
/* 99 */     setCaretPosition(pos);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/xmleditor/XMLTextEditor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */