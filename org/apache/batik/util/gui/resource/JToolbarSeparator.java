/*    */ package org.apache.batik.util.gui.resource;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JComponent;
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
/*    */ public class JToolbarSeparator
/*    */   extends JComponent
/*    */ {
/*    */   public JToolbarSeparator() {
/* 38 */     setMaximumSize(new Dimension(15, 2147483647));
/*    */   }
/*    */   
/*    */   protected void paintComponent(Graphics g) {
/* 42 */     super.paintComponent(g);
/*    */     
/* 44 */     Dimension size = getSize();
/* 45 */     int pos = size.width / 2;
/* 46 */     g.setColor(Color.gray);
/* 47 */     g.drawLine(pos, 3, pos, size.height - 5);
/* 48 */     g.drawLine(pos, 2, pos + 1, 2);
/* 49 */     g.setColor(Color.white);
/* 50 */     g.drawLine(pos + 1, 3, pos + 1, size.height - 5);
/* 51 */     g.drawLine(pos, size.height - 4, pos + 1, size.height - 4);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/resource/JToolbarSeparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */