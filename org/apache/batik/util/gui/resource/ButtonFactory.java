/*     */ package org.apache.batik.util.gui.resource;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JToggleButton;
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
/*     */ 
/*     */ public class ButtonFactory
/*     */   extends ResourceManager
/*     */ {
/*     */   private static final String ICON_SUFFIX = ".icon";
/*     */   private static final String TEXT_SUFFIX = ".text";
/*     */   private static final String MNEMONIC_SUFFIX = ".mnemonic";
/*     */   private static final String ACTION_SUFFIX = ".action";
/*     */   private static final String SELECTED_SUFFIX = ".selected";
/*     */   private static final String TOOLTIP_SUFFIX = ".tooltip";
/*     */   private ActionMap actions;
/*     */   
/*     */   public ButtonFactory(ResourceBundle rb, ActionMap am) {
/*  76 */     super(rb);
/*  77 */     this.actions = am;
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
/*     */     JButton jButton;
/*     */     try {
/*  96 */       jButton = new JButton(getString(name + ".text"));
/*  97 */     } catch (MissingResourceException e) {
/*  98 */       jButton = new JButton();
/*     */     } 
/* 100 */     initializeButton(jButton, name);
/* 101 */     return jButton;
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
/*     */   public JButton createJToolbarButton(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/*     */     JButton jButton;
/*     */     try {
/* 121 */       jButton = new JToolbarButton(getString(name + ".text"));
/* 122 */     } catch (MissingResourceException e) {
/* 123 */       jButton = new JToolbarButton();
/*     */     } 
/* 125 */     initializeButton(jButton, name);
/* 126 */     return jButton;
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
/*     */   public JToggleButton createJToolbarToggleButton(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/*     */     JToggleButton jToggleButton;
/*     */     try {
/* 146 */       jToggleButton = new JToolbarToggleButton(getString(name + ".text"));
/* 147 */     } catch (MissingResourceException e) {
/* 148 */       jToggleButton = new JToolbarToggleButton();
/*     */     } 
/* 150 */     initializeButton(jToggleButton, name);
/* 151 */     return jToggleButton;
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
/*     */   public JRadioButton createJRadioButton(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 169 */     JRadioButton result = new JRadioButton(getString(name + ".text"));
/* 170 */     initializeButton(result, name);
/*     */ 
/*     */     
/*     */     try {
/* 174 */       result.setSelected(getBoolean(name + ".selected"));
/* 175 */     } catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */     
/* 178 */     return result;
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
/*     */   public JCheckBox createJCheckBox(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 195 */     JCheckBox result = new JCheckBox(getString(name + ".text"));
/* 196 */     initializeButton(result, name);
/*     */ 
/*     */     
/*     */     try {
/* 200 */       result.setSelected(getBoolean(name + ".selected"));
/* 201 */     } catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */     
/* 204 */     return result;
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
/*     */   private void initializeButton(AbstractButton b, String name) throws ResourceFormatException, MissingListenerException {
/*     */     try {
/* 220 */       Action a = this.actions.getAction(getString(name + ".action"));
/* 221 */       if (a == null) {
/* 222 */         throw new MissingListenerException("", "Action", name + ".action");
/*     */       }
/*     */       
/* 225 */       b.setAction(a);
/*     */       try {
/* 227 */         b.setText(getString(name + ".text"));
/* 228 */       } catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */ 
/*     */       
/* 232 */       if (a instanceof JComponentModifier) {
/* 233 */         ((JComponentModifier)a).addJComponent(b);
/*     */       }
/* 235 */     } catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 240 */       String s = getString(name + ".icon");
/* 241 */       URL url = this.actions.getClass().getResource(s);
/* 242 */       if (url != null) {
/* 243 */         b.setIcon(new ImageIcon(url));
/*     */       }
/* 245 */     } catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 250 */       String str = getString(name + ".mnemonic");
/* 251 */       if (str.length() == 1) {
/* 252 */         b.setMnemonic(str.charAt(0));
/*     */       } else {
/* 254 */         throw new ResourceFormatException("Malformed mnemonic", this.bundle.getClass().getName(), name + ".mnemonic");
/*     */       }
/*     */     
/*     */     }
/* 258 */     catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 263 */       String s = getString(name + ".tooltip");
/* 264 */       if (s != null) {
/* 265 */         b.setToolTipText(s);
/*     */       }
/* 267 */     } catch (MissingResourceException missingResourceException) {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/resource/ButtonFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */