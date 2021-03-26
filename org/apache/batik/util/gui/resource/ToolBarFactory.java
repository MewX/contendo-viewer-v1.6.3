/*     */ package org.apache.batik.util.gui.resource;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JToolBar;
/*     */ import org.apache.batik.util.resources.ResourceFormatException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ToolBarFactory
/*     */   extends ResourceManager
/*     */ {
/*     */   private static final String SEPARATOR = "-";
/*     */   private ButtonFactory buttonFactory;
/*     */   
/*     */   public ToolBarFactory(ResourceBundle rb, ActionMap am) {
/*  65 */     super(rb);
/*  66 */     this.buttonFactory = new ButtonFactory(rb, am);
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
/*     */   public JToolBar createJToolBar(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/*  83 */     JToolBar result = new JToolBar();
/*  84 */     List buttons = getStringList(name);
/*     */     
/*  86 */     for (Object button : buttons) {
/*  87 */       String s = (String)button;
/*  88 */       if (s.equals("-")) {
/*  89 */         result.add(new JToolbarSeparator()); continue;
/*     */       } 
/*  91 */       result.add(createJButton(s));
/*     */     } 
/*     */     
/*  94 */     return result;
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
/*     */   public JButton createJButton(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 111 */     return this.buttonFactory.createJToolbarButton(name);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/resource/ToolBarFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */