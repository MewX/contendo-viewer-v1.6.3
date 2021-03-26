/*     */ package org.apache.batik.swing.svg;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JOptionPane;
/*     */ import org.apache.batik.util.gui.JErrorPane;
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
/*     */ public class SVGUserAgentGUIAdapter
/*     */   extends SVGUserAgentAdapter
/*     */ {
/*     */   public Component parentComponent;
/*     */   
/*     */   public SVGUserAgentGUIAdapter(Component parentComponent) {
/*  39 */     this.parentComponent = parentComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayError(String message) {
/*  46 */     JOptionPane pane = new JOptionPane(message, 0);
/*  47 */     JDialog dialog = pane.createDialog(this.parentComponent, "ERROR");
/*  48 */     dialog.setModal(false);
/*  49 */     dialog.setVisible(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayError(Exception ex) {
/*  56 */     JErrorPane pane = new JErrorPane(ex, 0);
/*  57 */     JDialog dialog = pane.createDialog(this.parentComponent, "ERROR");
/*  58 */     dialog.setModal(false);
/*  59 */     dialog.setVisible(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayMessage(String message) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showAlert(String message) {
/*  74 */     String str = "Script alert:\n" + message;
/*  75 */     JOptionPane.showMessageDialog(this.parentComponent, str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String showPrompt(String message) {
/*  82 */     String str = "Script prompt:\n" + message;
/*  83 */     return JOptionPane.showInputDialog(this.parentComponent, str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String showPrompt(String message, String defaultValue) {
/*  90 */     String str = "Script prompt:\n" + message;
/*  91 */     return (String)JOptionPane.showInputDialog(this.parentComponent, str, null, -1, null, null, defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean showConfirm(String message) {
/* 101 */     String str = "Script confirm:\n" + message;
/* 102 */     return (JOptionPane.showConfirmDialog(this.parentComponent, str, "Confirm", 0) == 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGUserAgentGUIAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */