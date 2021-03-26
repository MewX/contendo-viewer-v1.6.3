/*     */ package org.apache.bcel.verifier;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import javax.swing.UIManager;
/*     */ import org.apache.bcel.generic.Type;
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
/*     */ public class GraphicalVerifier
/*     */ {
/*     */   boolean packFrame = false;
/*     */   
/*     */   public GraphicalVerifier() {
/*  74 */     VerifierAppFrame frame = new VerifierAppFrame();
/*     */ 
/*     */     
/*  77 */     if (this.packFrame) {
/*  78 */       frame.pack();
/*     */     } else {
/*     */       
/*  81 */       frame.validate();
/*     */     } 
/*     */     
/*  84 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  85 */     Dimension frameSize = frame.getSize();
/*  86 */     if (frameSize.height > screenSize.height) {
/*  87 */       frameSize.height = screenSize.height;
/*     */     }
/*  89 */     if (frameSize.width > screenSize.width) {
/*  90 */       frameSize.width = screenSize.width;
/*     */     }
/*  92 */     frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*  93 */     frame.setVisible(true);
/*     */     
/*  95 */     frame.classNamesJList.setModel(new VerifierFactoryListModel());
/*  96 */     VerifierFactory.getVerifier(Type.OBJECT.getClassName());
/*  97 */     frame.classNamesJList.setSelectedIndex(0);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 102 */       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*     */     } catch (Exception e) {
/*     */       
/* 105 */       e.printStackTrace();
/*     */     } 
/* 107 */     new GraphicalVerifier();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/GraphicalVerifier.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */