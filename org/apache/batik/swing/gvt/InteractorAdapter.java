/*    */ package org.apache.batik.swing.gvt;
/*    */ 
/*    */ import java.awt.event.InputEvent;
/*    */ import java.awt.event.KeyEvent;
/*    */ import java.awt.event.MouseEvent;
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
/*    */ public class InteractorAdapter
/*    */   implements Interactor
/*    */ {
/*    */   public boolean startInteraction(InputEvent ie) {
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean endInteraction() {
/* 44 */     return false;
/*    */   }
/*    */   
/*    */   public void keyTyped(KeyEvent e) {}
/*    */   
/*    */   public void keyPressed(KeyEvent e) {}
/*    */   
/*    */   public void keyReleased(KeyEvent e) {}
/*    */   
/*    */   public void mouseClicked(MouseEvent e) {}
/*    */   
/*    */   public void mousePressed(MouseEvent e) {}
/*    */   
/*    */   public void mouseReleased(MouseEvent e) {}
/*    */   
/*    */   public void mouseEntered(MouseEvent e) {}
/*    */   
/*    */   public void mouseExited(MouseEvent e) {}
/*    */   
/*    */   public void mouseDragged(MouseEvent e) {}
/*    */   
/*    */   public void mouseMoved(MouseEvent e) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/InteractorAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */