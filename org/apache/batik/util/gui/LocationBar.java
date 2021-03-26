/*     */ package org.apache.batik.util.gui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.net.URL;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
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
/*     */ public class LocationBar
/*     */   extends JPanel
/*     */ {
/*     */   protected static final String RESOURCES = "org.apache.batik.util.gui.resources.LocationBar";
/*  59 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.util.gui.resources.LocationBar", Locale.getDefault());
/*  60 */   protected static ResourceManager rManager = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JComboBox comboBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocationBar() {
/*  72 */     super(new BorderLayout(5, 5));
/*  73 */     JLabel label = new JLabel(rManager.getString("Panel.label"));
/*  74 */     add("West", label);
/*     */     try {
/*  76 */       String s = rManager.getString("Panel.icon");
/*  77 */       URL url = getClass().getResource(s);
/*  78 */       if (url != null) {
/*  79 */         label.setIcon(new ImageIcon(url));
/*     */       }
/*  81 */     } catch (MissingResourceException missingResourceException) {}
/*     */     
/*  83 */     add("Center", this.comboBox = new JComboBox());
/*  84 */     this.comboBox.setEditable(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addActionListener(ActionListener listener) {
/*  91 */     this.comboBox.addActionListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/*  98 */     return (String)this.comboBox.getEditor().getItem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 105 */     this.comboBox.getEditor().setItem(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToHistory(String text) {
/* 112 */     this.comboBox.addItem(text);
/* 113 */     this.comboBox.setPreferredSize(new Dimension(0, (this.comboBox.getPreferredSize()).height));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/LocationBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */