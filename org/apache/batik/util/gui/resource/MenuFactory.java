/*     */ package org.apache.batik.util.gui.resource;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.List;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JCheckBoxMenuItem;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JRadioButtonMenuItem;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.KeyStroke;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MenuFactory
/*     */   extends ResourceManager
/*     */ {
/*     */   private static final String TYPE_MENU = "MENU";
/*     */   private static final String TYPE_ITEM = "ITEM";
/*     */   private static final String TYPE_RADIO = "RADIO";
/*     */   private static final String TYPE_CHECK = "CHECK";
/*     */   private static final String SEPARATOR = "-";
/*     */   private static final String TYPE_SUFFIX = ".type";
/*     */   private static final String TEXT_SUFFIX = ".text";
/*     */   private static final String MNEMONIC_SUFFIX = ".mnemonic";
/*     */   private static final String ACCELERATOR_SUFFIX = ".accelerator";
/*     */   private static final String ACTION_SUFFIX = ".action";
/*     */   private static final String SELECTED_SUFFIX = ".selected";
/*     */   private static final String ENABLED_SUFFIX = ".enabled";
/*     */   private static final String ICON_SUFFIX = ".icon";
/*     */   private ActionMap actions;
/*     */   private ButtonGroup buttonGroup;
/*     */   
/*     */   public MenuFactory(ResourceBundle rb, ActionMap am) {
/* 105 */     super(rb);
/* 106 */     this.actions = am;
/* 107 */     this.buttonGroup = null;
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
/*     */   public JMenuBar createJMenuBar(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 126 */     return createJMenuBar(name, (String)null);
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
/*     */   public JMenuBar createJMenuBar(String name, String specialization) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 146 */     JMenuBar result = new JMenuBar();
/* 147 */     List menus = getSpecializedStringList(name, specialization);
/*     */     
/* 149 */     for (Object menu : menus) {
/* 150 */       result.add(createJMenuComponent((String)menu, specialization));
/*     */     }
/* 152 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getSpecializedString(String name, String specialization) {
/*     */     String str;
/*     */     try {
/* 164 */       str = getString(name + '.' + specialization);
/* 165 */     } catch (MissingResourceException mre) {
/* 166 */       str = getString(name);
/*     */     } 
/* 168 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getSpecializedStringList(String name, String specialization) {
/*     */     List list;
/*     */     try {
/* 181 */       list = getStringList(name + '.' + specialization);
/* 182 */     } catch (MissingResourceException mre) {
/* 183 */       list = getStringList(name);
/*     */     } 
/* 185 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean getSpecializedBoolean(String name, String specialization) {
/*     */     boolean bool;
/*     */     try {
/* 198 */       bool = getBoolean(name + '.' + specialization);
/* 199 */     } catch (MissingResourceException mre) {
/* 200 */       bool = getBoolean(name);
/*     */     } 
/* 202 */     return bool;
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
/*     */   protected JComponent createJMenuComponent(String name, String specialization) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 221 */     if (name.equals("-")) {
/* 222 */       this.buttonGroup = null;
/* 223 */       return new JSeparator();
/*     */     } 
/* 225 */     String type = getSpecializedString(name + ".type", specialization);
/*     */     
/* 227 */     JComponent item = null;
/*     */     
/* 229 */     if (type.equals("RADIO")) {
/* 230 */       if (this.buttonGroup == null) {
/* 231 */         this.buttonGroup = new ButtonGroup();
/*     */       }
/*     */     } else {
/* 234 */       this.buttonGroup = null;
/*     */     } 
/*     */     
/* 237 */     if (type.equals("MENU")) {
/* 238 */       item = createJMenu(name, specialization);
/* 239 */     } else if (type.equals("ITEM")) {
/* 240 */       item = createJMenuItem(name, specialization);
/* 241 */     } else if (type.equals("RADIO")) {
/* 242 */       item = createJRadioButtonMenuItem(name, specialization);
/* 243 */       this.buttonGroup.add((AbstractButton)item);
/* 244 */     } else if (type.equals("CHECK")) {
/* 245 */       item = createJCheckBoxMenuItem(name, specialization);
/*     */     } else {
/* 247 */       throw new ResourceFormatException("Malformed resource", this.bundle.getClass().getName(), name + ".type");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 252 */     return item;
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
/*     */   public JMenu createJMenu(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 271 */     return createJMenu(name, (String)null);
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
/*     */   public JMenu createJMenu(String name, String specialization) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 291 */     JMenu result = new JMenu(getSpecializedString(name + ".text", specialization));
/*     */     
/* 293 */     initializeJMenuItem(result, name, specialization);
/*     */     
/* 295 */     List items = getSpecializedStringList(name, specialization);
/*     */     
/* 297 */     for (Object item : items) {
/* 298 */       result.add(createJMenuComponent((String)item, specialization));
/*     */     }
/* 300 */     return result;
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
/*     */   public JMenuItem createJMenuItem(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 319 */     return createJMenuItem(name, (String)null);
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
/*     */   public JMenuItem createJMenuItem(String name, String specialization) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 339 */     JMenuItem result = new JMenuItem(getSpecializedString(name + ".text", specialization));
/*     */     
/* 341 */     initializeJMenuItem(result, name, specialization);
/* 342 */     return result;
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
/*     */   public JRadioButtonMenuItem createJRadioButtonMenuItem(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 361 */     return createJRadioButtonMenuItem(name, (String)null);
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
/*     */   public JRadioButtonMenuItem createJRadioButtonMenuItem(String name, String specialization) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 383 */     JRadioButtonMenuItem result = new JRadioButtonMenuItem(getSpecializedString(name + ".text", specialization));
/*     */     
/* 385 */     initializeJMenuItem(result, name, specialization);
/*     */ 
/*     */     
/*     */     try {
/* 389 */       result.setSelected(getSpecializedBoolean(name + ".selected", specialization));
/*     */     }
/* 391 */     catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */     
/* 394 */     return result;
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
/*     */   public JCheckBoxMenuItem createJCheckBoxMenuItem(String name) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 413 */     return createJCheckBoxMenuItem(name, (String)null);
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
/*     */   public JCheckBoxMenuItem createJCheckBoxMenuItem(String name, String specialization) throws MissingResourceException, ResourceFormatException, MissingListenerException {
/* 435 */     JCheckBoxMenuItem result = new JCheckBoxMenuItem(getSpecializedString(name + ".text", specialization));
/*     */     
/* 437 */     initializeJMenuItem(result, name, specialization);
/*     */ 
/*     */     
/*     */     try {
/* 441 */       result.setSelected(getSpecializedBoolean(name + ".selected", specialization));
/*     */     }
/* 443 */     catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */     
/* 446 */     return result;
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
/*     */   protected void initializeJMenuItem(JMenuItem item, String name, String specialization) throws ResourceFormatException, MissingListenerException {
/*     */     try {
/* 465 */       Action a = this.actions.getAction(getSpecializedString(name + ".action", specialization));
/*     */       
/* 467 */       if (a == null) {
/* 468 */         throw new MissingListenerException("", "Action", name + ".action");
/*     */       }
/*     */       
/* 471 */       item.setAction(a);
/* 472 */       item.setText(getSpecializedString(name + ".text", specialization));
/*     */       
/* 474 */       if (a instanceof JComponentModifier) {
/* 475 */         ((JComponentModifier)a).addJComponent(item);
/*     */       }
/* 477 */     } catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 482 */       String s = getSpecializedString(name + ".icon", specialization);
/* 483 */       URL url = this.actions.getClass().getResource(s);
/* 484 */       if (url != null) {
/* 485 */         item.setIcon(new ImageIcon(url));
/*     */       }
/* 487 */     } catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 492 */       String str = getSpecializedString(name + ".mnemonic", specialization);
/*     */       
/* 494 */       if (str.length() == 1) {
/* 495 */         item.setMnemonic(str.charAt(0));
/*     */       } else {
/* 497 */         throw new ResourceFormatException("Malformed mnemonic", this.bundle.getClass().getName(), name + ".mnemonic");
/*     */       }
/*     */     
/*     */     }
/* 501 */     catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 506 */       if (!(item instanceof JMenu)) {
/* 507 */         String str = getSpecializedString(name + ".accelerator", specialization);
/*     */         
/* 509 */         KeyStroke ks = KeyStroke.getKeyStroke(str);
/* 510 */         if (ks != null) {
/* 511 */           item.setAccelerator(ks);
/*     */         } else {
/* 513 */           throw new ResourceFormatException("Malformed accelerator", this.bundle.getClass().getName(), name + ".accelerator");
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 519 */     catch (MissingResourceException missingResourceException) {}
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 524 */       item.setEnabled(getSpecializedBoolean(name + ".enabled", specialization));
/*     */     }
/* 526 */     catch (MissingResourceException missingResourceException) {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/resource/MenuFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */