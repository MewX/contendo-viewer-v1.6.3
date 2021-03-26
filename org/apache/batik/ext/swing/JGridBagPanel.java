/*     */ package org.apache.batik.ext.swing;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JPanel;
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
/*     */ public class JGridBagPanel
/*     */   extends JPanel
/*     */   implements GridBagConstants
/*     */ {
/*     */   public static interface InsetsManager
/*     */   {
/*     */     Insets getInsets(int param1Int1, int param1Int2);
/*     */   }
/*     */   
/*     */   private static class ZeroInsetsManager
/*     */     implements InsetsManager
/*     */   {
/*  50 */     private Insets insets = new Insets(0, 0, 0, 0);
/*     */     
/*     */     public Insets getInsets(int gridx, int gridy) {
/*  53 */       return this.insets;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private ZeroInsetsManager() {}
/*     */   }
/*     */ 
/*     */   
/*     */   private static class DefaultInsetsManager
/*     */     implements InsetsManager
/*     */   {
/*  65 */     int leftInset = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     int topInset = 5;
/*     */     
/*  73 */     public Insets positiveInsets = new Insets(this.topInset, this.leftInset, 0, 0);
/*  74 */     public Insets leftInsets = new Insets(this.topInset, 0, 0, 0);
/*  75 */     public Insets topInsets = new Insets(0, this.leftInset, 0, 0);
/*  76 */     public Insets topLeftInsets = new Insets(0, 0, 0, 0);
/*     */     
/*     */     public Insets getInsets(int gridx, int gridy) {
/*  79 */       if (gridx > 0) {
/*  80 */         if (gridy > 0) {
/*  81 */           return this.positiveInsets;
/*     */         }
/*  83 */         return this.topInsets;
/*     */       } 
/*     */       
/*  86 */       if (gridy > 0) {
/*  87 */         return this.leftInsets;
/*     */       }
/*  89 */       return this.topLeftInsets;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private DefaultInsetsManager() {}
/*     */   }
/*     */   
/*  97 */   public static final InsetsManager ZERO_INSETS = new ZeroInsetsManager();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public static final InsetsManager DEFAULT_INSETS = new DefaultInsetsManager();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InsetsManager insetsManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JGridBagPanel() {
/* 114 */     this(new DefaultInsetsManager(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JGridBagPanel(InsetsManager insetsManager) {
/* 121 */     super(new GridBagLayout());
/*     */     
/* 123 */     if (insetsManager != null) {
/* 124 */       this.insetsManager = insetsManager;
/*     */     } else {
/* 126 */       this.insetsManager = new DefaultInsetsManager();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLayout(LayoutManager layout) {
/* 133 */     if (layout instanceof GridBagLayout) {
/* 134 */       super.setLayout(layout);
/*     */     }
/*     */   }
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
/*     */   public void add(Component cmp, int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill, double weightx, double weighty) {
/* 157 */     Insets insets = this.insetsManager.getInsets(gridx, gridy);
/* 158 */     GridBagConstraints constraints = new GridBagConstraints();
/* 159 */     constraints.gridx = gridx;
/* 160 */     constraints.gridy = gridy;
/* 161 */     constraints.gridwidth = gridwidth;
/* 162 */     constraints.gridheight = gridheight;
/* 163 */     constraints.anchor = anchor;
/* 164 */     constraints.fill = fill;
/* 165 */     constraints.weightx = weightx;
/* 166 */     constraints.weighty = weighty;
/* 167 */     constraints.insets = insets;
/* 168 */     add(cmp, constraints);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/swing/JGridBagPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */