/*    */ package org.apache.batik.util.gui;
/*    */ 
/*    */ import java.awt.GridBagConstraints;
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
/*    */ public class ExtendedGridBagConstraints
/*    */   extends GridBagConstraints
/*    */ {
/*    */   public void setGridBounds(int x, int y, int width, int height) {
/* 41 */     this.gridx = x;
/* 42 */     this.gridy = y;
/* 43 */     this.gridwidth = width;
/* 44 */     this.gridheight = height;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setWeight(double weightx, double weighty) {
/* 54 */     this.weightx = weightx;
/* 55 */     this.weighty = weighty;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/ExtendedGridBagConstraints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */