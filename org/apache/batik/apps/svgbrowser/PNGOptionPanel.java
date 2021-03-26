/*    */ package org.apache.batik.apps.svgbrowser;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.GridBagLayout;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.JCheckBox;
/*    */ import javax.swing.JLabel;
/*    */ import org.apache.batik.util.gui.ExtendedGridBagConstraints;
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
/*    */ public class PNGOptionPanel
/*    */   extends OptionPanel
/*    */ {
/*    */   protected JCheckBox check;
/*    */   
/*    */   public PNGOptionPanel() {
/* 49 */     super(new GridBagLayout());
/*    */     
/* 51 */     ExtendedGridBagConstraints constraints = new ExtendedGridBagConstraints();
/*    */ 
/*    */     
/* 54 */     constraints.insets = new Insets(5, 5, 5, 5);
/* 55 */     constraints.weightx = 0.0D;
/* 56 */     constraints.weighty = 0.0D;
/* 57 */     constraints.fill = 0;
/* 58 */     constraints.setGridBounds(0, 0, 1, 1);
/* 59 */     add(new JLabel(resources.getString("PNGOptionPanel.label")), constraints);
/*    */ 
/*    */     
/* 62 */     this.check = new JCheckBox();
/*    */     
/* 64 */     constraints.weightx = 1.0D;
/* 65 */     constraints.fill = 2;
/* 66 */     constraints.setGridBounds(1, 0, 1, 1);
/* 67 */     add(this.check, constraints);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isIndexed() {
/* 74 */     return this.check.isSelected();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean showDialog(Component parent) {
/* 81 */     String title = resources.getString("PNGOptionPanel.dialog.title");
/* 82 */     PNGOptionPanel panel = new PNGOptionPanel();
/* 83 */     OptionPanel.Dialog dialog = new OptionPanel.Dialog(parent, title, panel);
/* 84 */     dialog.pack();
/* 85 */     dialog.setVisible(true);
/* 86 */     return panel.isIndexed();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/PNGOptionPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */