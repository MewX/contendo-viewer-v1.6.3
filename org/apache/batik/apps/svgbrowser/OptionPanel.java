/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
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
/*     */ public class OptionPanel
/*     */   extends JPanel
/*     */ {
/*     */   public static final String RESOURCES = "org.apache.batik.apps.svgbrowser.resources.GUI";
/*  62 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.apps.svgbrowser.resources.GUI", Locale.getDefault());
/*  63 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OptionPanel(LayoutManager layout) {
/*  70 */     super(layout);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Dialog
/*     */     extends JDialog
/*     */   {
/*     */     protected JButton ok;
/*     */ 
/*     */ 
/*     */     
/*     */     protected JPanel panel;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Dialog(Component parent, String title, JPanel panel) {
/*  89 */       super(JOptionPane.getFrameForComponent(parent), title);
/*  90 */       setModal(true);
/*  91 */       this.panel = panel;
/*  92 */       getContentPane().add(panel, "Center");
/*  93 */       getContentPane().add(createButtonPanel(), "South");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected JPanel createButtonPanel() {
/* 100 */       JPanel panel = new JPanel(new FlowLayout());
/* 101 */       this.ok = new JButton(OptionPanel.resources.getString("OKButton.text"));
/* 102 */       this.ok.addActionListener(new OKButtonAction());
/* 103 */       panel.add(this.ok);
/* 104 */       return panel;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected class OKButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 113 */         OptionPanel.Dialog.this.dispose();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/OptionPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */