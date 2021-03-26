/*    */ package org.apache.batik.util.gui.resource;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Insets;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JButton;
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
/*    */ public class JToolbarButton
/*    */   extends JButton
/*    */ {
/*    */   public JToolbarButton() {
/* 38 */     initialize();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JToolbarButton(String txt) {
/* 46 */     super(txt);
/* 47 */     initialize();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void initialize() {
/* 54 */     if (!System.getProperty("java.version").startsWith("1.3")) {
/* 55 */       setOpaque(false);
/* 56 */       setBackground(new Color(0, 0, 0, 0));
/*    */     } 
/* 58 */     setBorderPainted(false);
/* 59 */     setMargin(new Insets(2, 2, 2, 2));
/* 60 */     addMouseListener(new MouseListener());
/*    */   }
/*    */ 
/*    */   
/*    */   protected class MouseListener
/*    */     extends MouseAdapter
/*    */   {
/*    */     public void mouseEntered(MouseEvent ev) {
/* 68 */       JToolbarButton.this.setBorderPainted(true);
/*    */     }
/*    */     public void mouseExited(MouseEvent ev) {
/* 71 */       JToolbarButton.this.setBorderPainted(false);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/resource/JToolbarButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */