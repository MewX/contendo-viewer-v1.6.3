/*     */ package org.apache.batik.util.gui;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import org.apache.batik.util.gui.resource.ActionMap;
/*     */ import org.apache.batik.util.gui.resource.ButtonFactory;
/*     */ import org.apache.batik.util.gui.resource.MissingListenerException;
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
/*     */ public class LanguageDialog
/*     */   extends JDialog
/*     */   implements ActionMap
/*     */ {
/*     */   public static final int OK_OPTION = 0;
/*     */   public static final int CANCEL_OPTION = 1;
/*     */   protected static final String RESOURCES = "org.apache.batik.util.gui.resources.LanguageDialogMessages";
/*  90 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.util.gui.resources.LanguageDialogMessages", Locale.getDefault());
/*  91 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected Map listeners = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   protected Panel panel = new Panel();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int returnCode;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LanguageDialog(JFrame f) {
/* 113 */     super(f);
/* 114 */     setModal(true);
/* 115 */     setTitle(resources.getString("Dialog.title"));
/*     */     
/* 117 */     this.listeners.put("OKButtonAction", new OKButtonAction());
/* 118 */     this.listeners.put("CancelButtonAction", new CancelButtonAction());
/*     */     
/* 120 */     getContentPane().add(this.panel);
/* 121 */     getContentPane().add(createButtonsPanel(), "South");
/*     */     
/* 123 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int showDialog() {
/* 131 */     setVisible(true);
/* 132 */     return this.returnCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLanguages(String s) {
/* 139 */     this.panel.setLanguages(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguages() {
/* 146 */     return this.panel.getLanguages();
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
/*     */   public Action getAction(String key) throws MissingListenerException {
/* 158 */     return (Action)this.listeners.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JPanel createButtonsPanel() {
/* 165 */     JPanel p = new JPanel(new FlowLayout(2));
/* 166 */     ButtonFactory bf = new ButtonFactory(bundle, this);
/* 167 */     p.add(bf.createJButton("OKButton"));
/* 168 */     p.add(bf.createJButton("CancelButton"));
/*     */     
/* 170 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Panel
/*     */     extends JPanel
/*     */     implements ActionMap
/*     */   {
/*     */     protected JList userList;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected JList languageList;
/*     */ 
/*     */ 
/*     */     
/* 190 */     protected DefaultListModel userListModel = new DefaultListModel();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 195 */     protected DefaultListModel languageListModel = new DefaultListModel();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected JButton addLanguageButton;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected JButton removeLanguageButton;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected JButton upLanguageButton;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected JButton downLanguageButton;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected JButton clearLanguageButton;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     protected Map listeners = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     private static Map iconMap = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Panel() {
/* 237 */       super(new GridBagLayout());
/*     */       
/* 239 */       initCountryIcons();
/*     */       
/* 241 */       setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LanguageDialog.resources.getString("Panel.title")));
/*     */ 
/*     */ 
/*     */       
/* 245 */       this.listeners.put("AddLanguageButtonAction", new AddLanguageButtonAction());
/*     */       
/* 247 */       this.listeners.put("RemoveLanguageButtonAction", new RemoveLanguageButtonAction());
/*     */       
/* 249 */       this.listeners.put("UpLanguageButtonAction", new UpLanguageButtonAction());
/*     */       
/* 251 */       this.listeners.put("DownLanguageButtonAction", new DownLanguageButtonAction());
/*     */       
/* 253 */       this.listeners.put("ClearLanguageButtonAction", new ClearLanguageButtonAction());
/*     */ 
/*     */ 
/*     */       
/* 257 */       this.userList = new JList(this.userListModel);
/* 258 */       this.userList.setCellRenderer(new IconAndTextCellRenderer());
/*     */       
/* 260 */       this.languageList = new JList(this.languageListModel);
/* 261 */       this.languageList.setCellRenderer(new IconAndTextCellRenderer());
/*     */ 
/*     */       
/* 264 */       StringTokenizer st = new StringTokenizer(LanguageDialog.resources.getString("Country.list"), " ");
/* 265 */       while (st.hasMoreTokens()) {
/* 266 */         this.languageListModel.addElement(st.nextToken());
/*     */       }
/*     */ 
/*     */       
/* 270 */       ExtendedGridBagConstraints constraints = new ExtendedGridBagConstraints();
/*     */       
/* 272 */       constraints.insets = new Insets(5, 5, 5, 5);
/*     */       
/* 274 */       constraints.weightx = 1.0D;
/* 275 */       constraints.weighty = 1.0D;
/* 276 */       constraints.fill = 1;
/*     */ 
/*     */       
/* 279 */       constraints.setGridBounds(0, 0, 1, 1);
/* 280 */       JScrollPane sp = new JScrollPane();
/* 281 */       sp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), LanguageDialog.resources.getString("Languages.title")), BorderFactory.createLoweredBevelBorder()));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 286 */       sp.getViewport().add(this.languageList);
/* 287 */       add(sp, constraints);
/*     */       
/* 289 */       this.languageList.setSelectionMode(0);
/* 290 */       this.languageList.addListSelectionListener(new LanguageListSelectionListener());
/*     */ 
/*     */ 
/*     */       
/* 294 */       constraints.setGridBounds(2, 0, 1, 1);
/*     */       
/* 296 */       JScrollPane sp2 = new JScrollPane();
/* 297 */       sp2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), LanguageDialog.resources.getString("User.title")), BorderFactory.createLoweredBevelBorder()));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 302 */       sp2.getViewport().add(this.userList);
/* 303 */       add(sp2, constraints);
/*     */       
/* 305 */       this.userList.setSelectionMode(0);
/* 306 */       this.userList.addListSelectionListener(new UserListSelectionListener());
/*     */ 
/*     */       
/* 309 */       constraints.setGridBounds(0, 1, 3, 1);
/* 310 */       constraints.weightx = 0.0D;
/* 311 */       constraints.weighty = 0.0D;
/* 312 */       add(new JLabel(LanguageDialog.resources.getString("InfoLabel.text")), constraints);
/*     */ 
/*     */ 
/*     */       
/* 316 */       ButtonFactory bf = new ButtonFactory(LanguageDialog.bundle, this);
/* 317 */       JPanel p = new JPanel(new GridLayout(5, 1, 0, 3));
/* 318 */       p.add(this.addLanguageButton = bf.createJButton("AddLanguageButton"));
/* 319 */       this.addLanguageButton.setEnabled(false);
/* 320 */       p.add(this.removeLanguageButton = bf.createJButton("RemoveLanguageButton"));
/*     */       
/* 322 */       this.removeLanguageButton.setEnabled(false);
/* 323 */       p.add(this.upLanguageButton = bf.createJButton("UpLanguageButton"));
/* 324 */       this.upLanguageButton.setEnabled(false);
/* 325 */       p.add(this.downLanguageButton = bf.createJButton("DownLanguageButton"));
/* 326 */       this.downLanguageButton.setEnabled(false);
/* 327 */       p.add(this.clearLanguageButton = bf.createJButton("ClearLanguageButton"));
/*     */       
/* 329 */       this.clearLanguageButton.setEnabled(false);
/*     */       
/* 331 */       JPanel t = new JPanel(new GridBagLayout());
/* 332 */       constraints.setGridBounds(1, 0, 1, 1);
/* 333 */       add(t, constraints);
/*     */       
/* 335 */       constraints.fill = 2;
/* 336 */       constraints.setGridBounds(0, 0, 1, 1);
/* 337 */       constraints.insets = new Insets(0, 0, 0, 0);
/* 338 */       t.add(p, constraints);
/*     */       
/* 340 */       sp2.setPreferredSize(sp.getPreferredSize());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static synchronized void initCountryIcons() {
/* 354 */       if (iconMap == null) {
/* 355 */         iconMap = new HashMap<Object, Object>();
/*     */         
/* 357 */         StringTokenizer st = new StringTokenizer(LanguageDialog.resources.getString("Country.list"), " ");
/*     */         
/* 359 */         while (st.hasMoreTokens()) {
/* 360 */           computeCountryIcon(Panel.class, st.nextToken());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getLanguages() {
/* 370 */       StringBuffer result = new StringBuffer();
/* 371 */       if (this.userListModel.getSize() > 0) {
/* 372 */         result.append(this.userListModel.getElementAt(0));
/*     */         
/* 374 */         for (int i = 1; i < this.userListModel.getSize(); i++) {
/* 375 */           result.append(',');
/* 376 */           result.append(this.userListModel.getElementAt(i));
/*     */         } 
/*     */       } 
/* 379 */       return result.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setLanguages(String str) {
/* 386 */       int len = this.userListModel.getSize();
/* 387 */       for (int i = 0; i < len; i++) {
/* 388 */         Object o = this.userListModel.getElementAt(0);
/* 389 */         this.userListModel.removeElementAt(0);
/* 390 */         String userListModelStr = (String)o;
/*     */         
/* 392 */         int size = this.languageListModel.getSize();
/* 393 */         int n = 0;
/* 394 */         while (n < size) {
/* 395 */           String s = this.languageListModel.getElementAt(n);
/* 396 */           if (userListModelStr.compareTo(s) > 0) {
/*     */             break;
/*     */           }
/* 399 */           n++;
/*     */         } 
/* 401 */         this.languageListModel.insertElementAt(o, n);
/*     */       } 
/*     */ 
/*     */       
/* 405 */       StringTokenizer st = new StringTokenizer(str, ",");
/* 406 */       while (st.hasMoreTokens()) {
/* 407 */         String s = st.nextToken();
/* 408 */         this.userListModel.addElement(s);
/* 409 */         this.languageListModel.removeElement(s);
/*     */       } 
/*     */       
/* 412 */       updateButtons();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void updateButtons() {
/* 419 */       int size = this.userListModel.size();
/* 420 */       int i = this.userList.getSelectedIndex();
/*     */       
/* 422 */       boolean empty = (size == 0);
/* 423 */       boolean selected = (i != -1);
/* 424 */       boolean zeroSelected = (i == 0);
/* 425 */       boolean lastSelected = (i == size - 1);
/*     */       
/* 427 */       this.removeLanguageButton.setEnabled((!empty && selected));
/* 428 */       this.upLanguageButton.setEnabled((!empty && selected && !zeroSelected));
/* 429 */       this.downLanguageButton.setEnabled((!empty && selected && !lastSelected));
/* 430 */       this.clearLanguageButton.setEnabled(!empty);
/*     */       
/* 432 */       size = this.languageListModel.size();
/* 433 */       i = this.languageList.getSelectedIndex();
/*     */       
/* 435 */       empty = (size == 0);
/* 436 */       selected = (i != -1);
/*     */       
/* 438 */       this.addLanguageButton.setEnabled((!empty && selected));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String getCountryText(String code) {
/* 445 */       return LanguageDialog.resources.getString(code + ".text");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Icon getCountryIcon(String code) {
/* 452 */       return computeCountryIcon(getClass(), code);
/*     */     }
/*     */ 
/*     */     
/*     */     private static Icon computeCountryIcon(Class ref, String code) {
/* 457 */       ImageIcon icon = null;
/*     */       try {
/* 459 */         if ((icon = (ImageIcon)iconMap.get(code)) != null)
/* 460 */           return icon; 
/* 461 */         String s = LanguageDialog.resources.getString(code + ".icon");
/* 462 */         URL url = ref.getResource(s);
/* 463 */         if (url != null) {
/* 464 */           iconMap.put(code, icon = new ImageIcon(url));
/* 465 */           return icon;
/*     */         } 
/* 467 */       } catch (MissingResourceException missingResourceException) {}
/*     */       
/* 469 */       return new ImageIcon(ref.getResource("resources/blank.gif"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Action getAction(String key) throws MissingListenerException {
/* 481 */       return (Action)this.listeners.get(key);
/*     */     }
/*     */ 
/*     */     
/*     */     protected class AddLanguageButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 489 */         int i = LanguageDialog.Panel.this.languageList.getSelectedIndex();
/* 490 */         Object o = LanguageDialog.Panel.this.languageListModel.getElementAt(i);
/* 491 */         LanguageDialog.Panel.this.languageListModel.removeElementAt(i);
/* 492 */         LanguageDialog.Panel.this.userListModel.addElement(o);
/* 493 */         LanguageDialog.Panel.this.userList.setSelectedValue(o, true);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected class RemoveLanguageButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 502 */         int i = LanguageDialog.Panel.this.userList.getSelectedIndex();
/* 503 */         Object o = LanguageDialog.Panel.this.userListModel.getElementAt(i);
/* 504 */         LanguageDialog.Panel.this.userListModel.removeElementAt(i);
/* 505 */         String userListModelStr = (String)o;
/* 506 */         int size = LanguageDialog.Panel.this.languageListModel.getSize();
/* 507 */         int n = 0;
/* 508 */         while (n < size) {
/* 509 */           String s = LanguageDialog.Panel.this.languageListModel.getElementAt(n);
/* 510 */           if (userListModelStr.compareTo(s) > 0) {
/*     */             break;
/*     */           }
/* 513 */           n++;
/*     */         } 
/* 515 */         LanguageDialog.Panel.this.languageListModel.insertElementAt(o, n);
/* 516 */         LanguageDialog.Panel.this.languageList.setSelectedValue(o, true);
/* 517 */         LanguageDialog.Panel.this.updateButtons();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected class UpLanguageButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 526 */         int i = LanguageDialog.Panel.this.userList.getSelectedIndex();
/* 527 */         Object o = LanguageDialog.Panel.this.userListModel.getElementAt(i);
/* 528 */         LanguageDialog.Panel.this.userListModel.removeElementAt(i);
/* 529 */         LanguageDialog.Panel.this.userListModel.insertElementAt(o, i - 1);
/* 530 */         LanguageDialog.Panel.this.userList.setSelectedIndex(i - 1);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected class DownLanguageButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 539 */         int i = LanguageDialog.Panel.this.userList.getSelectedIndex();
/* 540 */         Object o = LanguageDialog.Panel.this.userListModel.getElementAt(i);
/* 541 */         LanguageDialog.Panel.this.userListModel.removeElementAt(i);
/* 542 */         LanguageDialog.Panel.this.userListModel.insertElementAt(o, i + 1);
/* 543 */         LanguageDialog.Panel.this.userList.setSelectedIndex(i + 1);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected class ClearLanguageButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 552 */         int len = LanguageDialog.Panel.this.userListModel.getSize();
/* 553 */         for (int i = 0; i < len; i++) {
/* 554 */           Object o = LanguageDialog.Panel.this.userListModel.getElementAt(0);
/* 555 */           LanguageDialog.Panel.this.userListModel.removeElementAt(0);
/* 556 */           String userListModelStr = (String)o;
/*     */           
/* 558 */           int size = LanguageDialog.Panel.this.languageListModel.getSize();
/* 559 */           int n = 0;
/* 560 */           while (n < size) {
/* 561 */             String s = LanguageDialog.Panel.this.languageListModel.getElementAt(n);
/* 562 */             if (userListModelStr.compareTo(s) > 0) {
/*     */               break;
/*     */             }
/* 565 */             n++;
/*     */           } 
/* 567 */           LanguageDialog.Panel.this.languageListModel.insertElementAt(o, n);
/*     */         } 
/* 569 */         LanguageDialog.Panel.this.updateButtons();
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected class LanguageListSelectionListener
/*     */       implements ListSelectionListener
/*     */     {
/*     */       public void valueChanged(ListSelectionEvent e) {
/* 579 */         int i = LanguageDialog.Panel.this.languageList.getSelectedIndex();
/* 580 */         LanguageDialog.Panel.this.userList.getSelectionModel().clearSelection();
/* 581 */         LanguageDialog.Panel.this.languageList.setSelectedIndex(i);
/* 582 */         LanguageDialog.Panel.this.updateButtons();
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected class UserListSelectionListener
/*     */       implements ListSelectionListener
/*     */     {
/*     */       public void valueChanged(ListSelectionEvent e) {
/* 592 */         int i = LanguageDialog.Panel.this.userList.getSelectedIndex();
/* 593 */         LanguageDialog.Panel.this.languageList.getSelectionModel().clearSelection();
/* 594 */         LanguageDialog.Panel.this.userList.setSelectedIndex(i);
/* 595 */         LanguageDialog.Panel.this.updateButtons();
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected class IconAndTextCellRenderer
/*     */       extends JLabel
/*     */       implements ListCellRenderer
/*     */     {
/*     */       public IconAndTextCellRenderer() {
/* 606 */         setOpaque(true);
/* 607 */         setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 614 */         String s = (String)value;
/* 615 */         setText(LanguageDialog.Panel.this.getCountryText(s));
/* 616 */         setIcon(LanguageDialog.Panel.this.getCountryIcon(s));
/* 617 */         setEnabled(list.isEnabled());
/* 618 */         setFont(list.getFont());
/* 619 */         if (isSelected) {
/* 620 */           setBackground(list.getSelectionBackground());
/* 621 */           setForeground(list.getSelectionForeground());
/*     */         } else {
/* 623 */           setBackground(list.getBackground());
/* 624 */           setForeground(list.getForeground());
/*     */         } 
/* 626 */         return this;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class OKButtonAction
/*     */     extends AbstractAction
/*     */   {
/*     */     public void actionPerformed(ActionEvent e) {
/* 636 */       LanguageDialog.this.returnCode = 0;
/* 637 */       LanguageDialog.this.dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class CancelButtonAction
/*     */     extends AbstractAction
/*     */   {
/*     */     public void actionPerformed(ActionEvent e) {
/* 646 */       LanguageDialog.this.returnCode = 1;
/* 647 */       LanguageDialog.this.dispose();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/LanguageDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */