/*    */ package org.apache.batik.apps.svgbrowser;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JCheckBox;
/*    */ import javax.swing.JLabel;
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
/*    */ public class SVGOptionPanel
/*    */   extends OptionPanel
/*    */ {
/*    */   protected JCheckBox xmlbaseCB;
/*    */   protected JCheckBox prettyPrintCB;
/*    */   
/*    */   public SVGOptionPanel() {
/* 44 */     super(new BorderLayout());
/* 45 */     add(new JLabel(resources.getString("SVGOptionPanel.label")), "North");
/*    */ 
/*    */     
/* 48 */     this.xmlbaseCB = new JCheckBox(resources.getString("SVGOptionPanel.UseXMLBase"));
/*    */     
/* 50 */     this.xmlbaseCB.setSelected(resources.getBoolean("SVGOptionPanel.UseXMLBaseDefault"));
/*    */     
/* 52 */     add(this.xmlbaseCB, "Center");
/*    */     
/* 54 */     this.prettyPrintCB = new JCheckBox(resources.getString("SVGOptionPanel.PrettyPrint"));
/*    */     
/* 56 */     this.prettyPrintCB.setSelected(resources.getBoolean("SVGOptionPanel.PrettyPrintDefault"));
/*    */     
/* 58 */     add(this.prettyPrintCB, "South");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getUseXMLBase() {
/* 65 */     return this.xmlbaseCB.isSelected();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getPrettyPrint() {
/* 72 */     return this.prettyPrintCB.isSelected();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static SVGOptionPanel showDialog(Component parent) {
/* 80 */     String title = resources.getString("SVGOptionPanel.dialog.title");
/* 81 */     SVGOptionPanel panel = new SVGOptionPanel();
/* 82 */     OptionPanel.Dialog dialog = new OptionPanel.Dialog(parent, title, panel);
/* 83 */     dialog.pack();
/* 84 */     dialog.setVisible(true);
/* 85 */     return panel;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/SVGOptionPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */