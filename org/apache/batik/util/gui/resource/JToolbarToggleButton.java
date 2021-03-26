/*    */ package org.apache.batik.util.gui.resource;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Insets;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JToggleButton;
/*    */ import javax.swing.UIManager;
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
/*    */ public class JToolbarToggleButton
/*    */   extends JToggleButton
/*    */ {
/*    */   public JToolbarToggleButton() {
/* 39 */     initialize();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JToolbarToggleButton(String txt) {
/* 47 */     super(txt);
/* 48 */     initialize();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void initialize() {
/* 55 */     if (!System.getProperty("java.version").startsWith("1.3")) {
/* 56 */       setOpaque(false);
/* 57 */       setBackground(new Color(0, 0, 0, 0));
/*    */     } 
/* 59 */     setBorderPainted(false);
/* 60 */     setMargin(new Insets(2, 2, 2, 2));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 66 */     if (!UIManager.getLookAndFeel().getName().equals("Windows")) {
/* 67 */       addMouseListener(new MouseListener());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected class MouseListener
/*    */     extends MouseAdapter
/*    */   {
/*    */     public void mouseEntered(MouseEvent ev) {
/* 76 */       JToolbarToggleButton.this.setBorderPainted(true);
/*    */     }
/*    */     public void mouseExited(MouseEvent ev) {
/* 79 */       JToolbarToggleButton.this.setBorderPainted(false);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/resource/JToolbarToggleButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */