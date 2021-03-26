/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JSlider;
/*     */ import org.apache.batik.util.gui.ExtendedGridBagConstraints;
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
/*     */ public class JPEGOptionPanel
/*     */   extends OptionPanel
/*     */ {
/*     */   protected JSlider quality;
/*     */   
/*     */   public JPEGOptionPanel() {
/*  50 */     super(new GridBagLayout());
/*     */     
/*  52 */     ExtendedGridBagConstraints constraints = new ExtendedGridBagConstraints();
/*     */ 
/*     */ 
/*     */     
/*  56 */     constraints.insets = new Insets(5, 5, 5, 5);
/*     */     
/*  58 */     constraints.weightx = 0.0D;
/*  59 */     constraints.weighty = 0.0D;
/*  60 */     constraints.fill = 0;
/*  61 */     constraints.setGridBounds(0, 0, 1, 1);
/*  62 */     add(new JLabel(resources.getString("JPEGOptionPanel.label")), constraints);
/*     */ 
/*     */     
/*  65 */     this.quality = new JSlider();
/*  66 */     this.quality.setMinimum(0);
/*  67 */     this.quality.setMaximum(100);
/*  68 */     this.quality.setMajorTickSpacing(10);
/*  69 */     this.quality.setMinorTickSpacing(5);
/*  70 */     this.quality.setPaintTicks(true);
/*  71 */     this.quality.setPaintLabels(true);
/*  72 */     this.quality.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
/*  73 */     Hashtable<Object, Object> labels = new Hashtable<Object, Object>();
/*  74 */     for (int i = 0; i < 100; i += 10) {
/*  75 */       labels.put(Integer.valueOf(i), new JLabel("0." + (i / 10)));
/*     */     }
/*  77 */     labels.put(Integer.valueOf(100), new JLabel("1"));
/*  78 */     this.quality.setLabelTable(labels);
/*     */     
/*  80 */     Dimension dim = this.quality.getPreferredSize();
/*  81 */     this.quality.setPreferredSize(new Dimension(350, dim.height));
/*     */     
/*  83 */     constraints.weightx = 1.0D;
/*  84 */     constraints.fill = 2;
/*  85 */     constraints.setGridBounds(1, 0, 1, 1);
/*  86 */     add(this.quality, constraints);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getQuality() {
/*  93 */     return this.quality.getValue() / 100.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float showDialog(Component parent) {
/* 101 */     String title = resources.getString("JPEGOptionPanel.dialog.title");
/* 102 */     JPEGOptionPanel panel = new JPEGOptionPanel();
/* 103 */     OptionPanel.Dialog dialog = new OptionPanel.Dialog(parent, title, panel);
/* 104 */     dialog.pack();
/* 105 */     dialog.setVisible(true);
/* 106 */     return panel.getQuality();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/JPEGOptionPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */