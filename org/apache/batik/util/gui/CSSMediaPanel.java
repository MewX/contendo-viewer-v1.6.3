/*     */ package org.apache.batik.util.gui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.event.ListDataEvent;
/*     */ import javax.swing.event.ListDataListener;
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
/*     */ public class CSSMediaPanel
/*     */   extends JPanel
/*     */   implements ActionMap
/*     */ {
/*     */   protected static final String RESOURCES = "org.apache.batik.util.gui.resources.CSSMediaPanel";
/*  85 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.util.gui.resources.CSSMediaPanel", Locale.getDefault());
/*  86 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JButton removeButton;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JButton addButton;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JButton clearButton;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   protected DefaultListModel listModel = new DefaultListModel();
/*     */   protected JList mediaList;
/*     */   protected Map listeners;
/*     */   protected void updateButtons() { this.removeButton.setEnabled(!this.mediaList.isSelectionEmpty());
/*     */     this.clearButton.setEnabled(!this.listModel.isEmpty()); } public void setMedia(List mediaList) { this.listModel.removeAllElements();
/*     */     for (Object aMediaList : mediaList)
/*     */       this.listModel.addElement(aMediaList);  } public void setMedia(String media) {
/*     */     this.listModel.removeAllElements();
/*     */     StringTokenizer tokens = new StringTokenizer(media, " ");
/*     */     while (tokens.hasMoreTokens())
/*     */       this.listModel.addElement(tokens.nextToken()); 
/* 118 */   } public CSSMediaPanel() { super(new GridBagLayout());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 275 */     this.listeners = new HashMap<Object, Object>(); this.listeners.put("AddButtonAction", new AddButtonAction()); this.listeners.put("RemoveButtonAction", new RemoveButtonAction()); this.listeners.put("ClearButtonAction", new ClearButtonAction()); setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), resources.getString("Panel.title"))); ExtendedGridBagConstraints constraints = new ExtendedGridBagConstraints(); constraints.insets = new Insets(5, 5, 5, 5); this.mediaList = new JList(); this.mediaList.setSelectionMode(0); this.mediaList.setModel(this.listModel); this.mediaList.addListSelectionListener(new MediaListSelectionListener()); this.listModel.addListDataListener(new MediaListDataListener()); JScrollPane scrollPane = new JScrollPane(); scrollPane.setBorder(BorderFactory.createLoweredBevelBorder()); constraints.weightx = 1.0D; constraints.weighty = 1.0D; constraints.fill = 1; constraints.setGridBounds(0, 0, 1, 3); scrollPane.getViewport().add(this.mediaList); add(scrollPane, constraints); ButtonFactory bf = new ButtonFactory(bundle, this); constraints.weightx = 0.0D; constraints.weighty = 0.0D; constraints.fill = 2; constraints.anchor = 11; this.addButton = bf.createJButton("AddButton"); constraints.setGridBounds(1, 0, 1, 1); add(this.addButton, constraints); this.removeButton = bf.createJButton("RemoveButton"); constraints.setGridBounds(1, 1, 1, 1); add(this.removeButton, constraints);
/*     */     this.clearButton = bf.createJButton("ClearButton");
/*     */     constraints.setGridBounds(1, 2, 1, 1);
/*     */     add(this.clearButton, constraints);
/*     */     updateButtons(); }
/*     */   public List getMedia() { List media = new ArrayList(this.listModel.size());
/*     */     Enumeration e = this.listModel.elements();
/*     */     while (e.hasMoreElements())
/*     */       media.add(e.nextElement()); 
/* 284 */     return media; } public Action getAction(String key) throws MissingListenerException { return (Action)this.listeners.get(key); } public String getMediaAsString() { StringBuffer buffer = new StringBuffer(); Enumeration<String> e = this.listModel.elements(); while (e.hasMoreElements()) {
/*     */       buffer.append(e.nextElement()); buffer.append(' ');
/*     */     }  return buffer.toString(); }
/*     */   public static int showDialog(Component parent, String title) { return showDialog(parent, title, ""); }
/*     */   public static int showDialog(Component parent, String title, List mediaList) { Dialog dialog = new Dialog(parent, title, mediaList); dialog.setModal(true); dialog.pack(); dialog.setVisible(true); return dialog.getReturnCode(); }
/*     */   public static int showDialog(Component parent, String title, String media) { Dialog dialog = new Dialog(parent, title, media); dialog.setModal(true); dialog.pack();
/*     */     dialog.setVisible(true);
/*     */     return dialog.getReturnCode(); }
/* 292 */   protected class AddButtonAction extends AbstractAction { public void actionPerformed(ActionEvent e) { CSSMediaPanel.AddMediumDialog dialog = new CSSMediaPanel.AddMediumDialog(CSSMediaPanel.this);
/* 293 */       dialog.pack();
/* 294 */       dialog.setVisible(true);
/*     */       
/* 296 */       if (dialog.getReturnCode() == 1 || dialog.getMedium() == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 301 */       String medium = dialog.getMedium().trim();
/* 302 */       if (medium.length() == 0 || CSSMediaPanel.this.listModel.contains(medium)) {
/*     */         return;
/*     */       }
/*     */       
/* 306 */       for (int i = 0; i < CSSMediaPanel.this.listModel.size() && medium != null; i++) {
/* 307 */         String s = CSSMediaPanel.this.listModel.getElementAt(i);
/* 308 */         int c = medium.compareTo(s);
/* 309 */         if (c == 0) {
/* 310 */           medium = null;
/* 311 */         } else if (c < 0) {
/* 312 */           CSSMediaPanel.this.listModel.insertElementAt(medium, i);
/* 313 */           medium = null;
/*     */         } 
/*     */       } 
/* 316 */       if (medium != null) {
/* 317 */         CSSMediaPanel.this.listModel.addElement(medium);
/*     */       } }
/*     */      }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class RemoveButtonAction
/*     */     extends AbstractAction
/*     */   {
/*     */     public void actionPerformed(ActionEvent e) {
/* 327 */       int index = CSSMediaPanel.this.mediaList.getSelectedIndex();
/* 328 */       CSSMediaPanel.this.mediaList.clearSelection();
/* 329 */       if (index >= 0) {
/* 330 */         CSSMediaPanel.this.listModel.removeElementAt(index);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class ClearButtonAction
/*     */     extends AbstractAction
/*     */   {
/*     */     public void actionPerformed(ActionEvent e) {
/* 340 */       CSSMediaPanel.this.mediaList.clearSelection();
/* 341 */       CSSMediaPanel.this.listModel.removeAllElements();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class MediaListSelectionListener
/*     */     implements ListSelectionListener
/*     */   {
/*     */     public void valueChanged(ListSelectionEvent e) {
/* 352 */       CSSMediaPanel.this.updateButtons();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class MediaListDataListener
/*     */     implements ListDataListener
/*     */   {
/*     */     public void contentsChanged(ListDataEvent e) {
/* 362 */       CSSMediaPanel.this.updateButtons();
/*     */     }
/*     */     
/*     */     public void intervalAdded(ListDataEvent e) {
/* 366 */       CSSMediaPanel.this.updateButtons();
/*     */     }
/*     */     
/*     */     public void intervalRemoved(ListDataEvent e) {
/* 370 */       CSSMediaPanel.this.updateButtons();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AddMediumDialog
/*     */     extends JDialog
/*     */     implements ActionMap
/*     */   {
/*     */     public static final int OK_OPTION = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int CANCEL_OPTION = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected JComboBox medium;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int returnCode;
/*     */ 
/*     */ 
/*     */     
/*     */     protected Map listeners;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AddMediumDialog(Component parent) {
/* 407 */       super(JOptionPane.getFrameForComponent(parent), CSSMediaPanel.resources.getString("AddMediumDialog.title"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 467 */       this.listeners = new HashMap<Object, Object>(); setModal(true); this.listeners.put("OKButtonAction", new OKButtonAction()); this.listeners.put("CancelButtonAction", new CancelButtonAction()); getContentPane().add(createContentPanel(), "Center"); getContentPane().add(createButtonsPanel(), "South");
/*     */     } public String getMedium() { return (String)this.medium.getSelectedItem(); } protected Component createContentPanel() { JPanel panel = new JPanel(new BorderLayout()); panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4)); panel.add(new JLabel(CSSMediaPanel.resources.getString("AddMediumDialog.label")), "West"); this.medium = new JComboBox(); this.medium.setEditable(true); String media = CSSMediaPanel.resources.getString("Media.list"); StringTokenizer tokens = new StringTokenizer(media, " "); while (tokens.hasMoreTokens())
/*     */         this.medium.addItem(tokens.nextToken());  panel.add(this.medium, "Center"); return panel; }
/*     */     protected Component createButtonsPanel() { JPanel panel = new JPanel(new FlowLayout(2));
/*     */       ButtonFactory bf = new ButtonFactory(CSSMediaPanel.bundle, this);
/*     */       panel.add(bf.createJButton("OKButton"));
/*     */       panel.add(bf.createJButton("CancelButton"));
/*     */       return panel; }
/*     */     public int getReturnCode() { return this.returnCode; }
/* 476 */     public Action getAction(String key) throws MissingListenerException { return (Action)this.listeners.get(key); }
/*     */ 
/*     */ 
/*     */     
/*     */     protected class OKButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 484 */         CSSMediaPanel.AddMediumDialog.this.returnCode = 0;
/* 485 */         CSSMediaPanel.AddMediumDialog.this.dispose();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected class CancelButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 494 */         CSSMediaPanel.AddMediumDialog.this.returnCode = 1;
/* 495 */         CSSMediaPanel.AddMediumDialog.this.dispose();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Dialog
/*     */     extends JDialog
/*     */     implements ActionMap
/*     */   {
/*     */     public static final int OK_OPTION = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int CANCEL_OPTION = 1;
/*     */ 
/*     */ 
/*     */     
/*     */     protected int returnCode;
/*     */ 
/*     */ 
/*     */     
/*     */     protected Map listeners;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Dialog() {
/* 526 */       this((Component)null, "", "");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Dialog(Component parent, String title, List mediaList)
/*     */     {
/* 537 */       super(JOptionPane.getFrameForComponent(parent), title);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 589 */       this.listeners = new HashMap<Object, Object>(); this.listeners.put("OKButtonAction", new OKButtonAction()); this.listeners.put("CancelButtonAction", new CancelButtonAction()); CSSMediaPanel panel = new CSSMediaPanel(); panel.setMedia(mediaList); getContentPane().add(panel, "Center"); getContentPane().add(createButtonsPanel(), "South"); } public Dialog(Component parent, String title, String media) { super(JOptionPane.getFrameForComponent(parent), title); this.listeners = new HashMap<Object, Object>(); this.listeners.put("OKButtonAction", new OKButtonAction()); this.listeners.put("CancelButtonAction", new CancelButtonAction()); CSSMediaPanel panel = new CSSMediaPanel();
/*     */       panel.setMedia(media);
/*     */       getContentPane().add(panel, "Center");
/*     */       getContentPane().add(createButtonsPanel(), "South"); } public int getReturnCode() { return this.returnCode; }
/*     */     protected JPanel createButtonsPanel() { JPanel p = new JPanel(new FlowLayout(2));
/*     */       ButtonFactory bf = new ButtonFactory(CSSMediaPanel.bundle, this);
/*     */       p.add(bf.createJButton("OKButton"));
/*     */       p.add(bf.createJButton("CancelButton"));
/*     */       return p; }
/* 598 */     public Action getAction(String key) throws MissingListenerException { return (Action)this.listeners.get(key); }
/*     */ 
/*     */ 
/*     */     
/*     */     protected class OKButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 606 */         CSSMediaPanel.Dialog.this.returnCode = 0;
/* 607 */         CSSMediaPanel.Dialog.this.dispose();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected class CancelButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 616 */         CSSMediaPanel.Dialog.this.returnCode = 1;
/* 617 */         CSSMediaPanel.Dialog.this.dispose();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 626 */     String media = "all aural braille embossed handheld print projection screen tty tv";
/* 627 */     int code = showDialog((Component)null, "Test", media);
/* 628 */     System.out.println(code);
/* 629 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/CSSMediaPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */