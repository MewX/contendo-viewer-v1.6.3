/*     */ package org.apache.batik.util.gui;
/*     */ 
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
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
/*     */ 
/*     */ public class UserStyleDialog
/*     */   extends JDialog
/*     */   implements ActionMap
/*     */ {
/*     */   public static final int OK_OPTION = 0;
/*     */   public static final int CANCEL_OPTION = 1;
/*     */   protected static final String RESOURCES = "org.apache.batik.util.gui.resources.UserStyleDialog";
/*  86 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.util.gui.resources.UserStyleDialog", Locale.getDefault());
/*  87 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*     */   
/*     */   protected Panel panel;
/*     */   
/*     */   protected String chosenPath;
/*     */   
/*     */   protected int returnCode;
/*     */   
/*     */   protected Map listeners;
/*     */ 
/*     */   
/*     */   public int showDialog() {
/*     */     pack();
/*     */     setVisible(true);
/*     */     return this.returnCode;
/*     */   }
/*     */   
/*     */   public String getPath() {
/*     */     return this.chosenPath;
/*     */   }
/*     */   
/*     */   public UserStyleDialog(JFrame f) {
/* 109 */     super(f);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     this.listeners = new HashMap<Object, Object>(); setModal(true);
/*     */     setTitle(resources.getString("Dialog.title"));
/*     */     this.listeners.put("OKButtonAction", new OKButtonAction());
/*     */     this.listeners.put("CancelButtonAction", new CancelButtonAction());
/*     */     getContentPane().add(this.panel = new Panel());
/*     */     getContentPane().add(createButtonsPanel(), "South");
/*     */     pack();
/*     */   } public void setPath(String s) { this.chosenPath = s;
/*     */     this.panel.fileTextField.setText(s);
/* 214 */     this.panel.fileCheckBox.setSelected(true); } public Action getAction(String key) throws MissingListenerException { return (Action)this.listeners.get(key); } protected JPanel createButtonsPanel() { JPanel p = new JPanel(new FlowLayout(2));
/*     */     ButtonFactory bf = new ButtonFactory(bundle, this);
/*     */     p.add(bf.createJButton("OKButton"));
/*     */     p.add(bf.createJButton("CancelButton"));
/*     */     return p; } protected class OKButtonAction extends AbstractAction {
/*     */     public void actionPerformed(ActionEvent e) {
/*     */       if (UserStyleDialog.this.panel.fileCheckBox.isSelected()) {
/*     */         String path = UserStyleDialog.this.panel.fileTextField.getText();
/*     */         if (path.equals("")) {
/*     */           JOptionPane.showMessageDialog(UserStyleDialog.this, UserStyleDialog.resources.getString("StyleDialogError.text"), UserStyleDialog.resources.getString("StyleDialogError.title"), 0);
/*     */           return;
/*     */         } 
/*     */         File f = new File(path);
/*     */         if (f.exists())
/*     */           if (f.isDirectory()) {
/*     */             path = null;
/*     */           } else {
/*     */             path = "file:" + path;
/*     */           }  
/*     */         UserStyleDialog.this.chosenPath = path;
/*     */       } else {
/*     */         UserStyleDialog.this.chosenPath = null;
/*     */       } 
/*     */       UserStyleDialog.this.returnCode = 0;
/*     */       UserStyleDialog.this.dispose();
/*     */     } } protected class CancelButtonAction extends AbstractAction {
/*     */     public void actionPerformed(ActionEvent e) {
/*     */       UserStyleDialog.this.returnCode = 1;
/*     */       UserStyleDialog.this.dispose();
/*     */     } } public static class Panel extends JPanel {
/*     */     protected JCheckBox fileCheckBox; public Panel() {
/* 245 */       super(new GridBagLayout());
/* 246 */       setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), UserStyleDialog.resources.getString("Panel.title")));
/*     */ 
/*     */ 
/*     */       
/* 250 */       ExtendedGridBagConstraints constraints = new ExtendedGridBagConstraints();
/*     */       
/* 252 */       constraints.insets = new Insets(5, 5, 5, 5);
/*     */       
/* 254 */       this.fileCheckBox = new JCheckBox(UserStyleDialog.resources.getString("PanelFileCheckBox.text"));
/*     */       
/* 256 */       this.fileCheckBox.addChangeListener(new FileCheckBoxChangeListener());
/* 257 */       constraints.weightx = 0.0D;
/* 258 */       constraints.weighty = 0.0D;
/* 259 */       constraints.fill = 2;
/* 260 */       constraints.setGridBounds(0, 2, 3, 1);
/* 261 */       add(this.fileCheckBox, constraints);
/*     */       
/* 263 */       this.fileLabel = new JLabel(UserStyleDialog.resources.getString("PanelFileLabel.text"));
/* 264 */       constraints.weightx = 0.0D;
/* 265 */       constraints.weighty = 0.0D;
/* 266 */       constraints.fill = 2;
/* 267 */       constraints.setGridBounds(0, 3, 3, 1);
/* 268 */       add(this.fileLabel, constraints);
/*     */       
/* 270 */       this.fileTextField = new JTextField(30);
/* 271 */       constraints.weightx = 1.0D;
/* 272 */       constraints.weighty = 0.0D;
/* 273 */       constraints.fill = 2;
/* 274 */       constraints.setGridBounds(0, 4, 2, 1);
/* 275 */       add(this.fileTextField, constraints);
/*     */       
/* 277 */       ButtonFactory bf = new ButtonFactory(UserStyleDialog.bundle, null);
/* 278 */       constraints.weightx = 0.0D;
/* 279 */       constraints.weighty = 0.0D;
/* 280 */       constraints.fill = 0;
/* 281 */       constraints.anchor = 13;
/* 282 */       constraints.setGridBounds(2, 4, 1, 1);
/* 283 */       this.browseButton = bf.createJButton("PanelFileBrowseButton");
/* 284 */       add(this.browseButton, constraints);
/* 285 */       this.browseButton.addActionListener(new FileBrowseButtonAction());
/*     */       
/* 287 */       this.fileLabel.setEnabled(false);
/* 288 */       this.fileTextField.setEnabled(false);
/* 289 */       this.browseButton.setEnabled(false);
/*     */     }
/*     */     protected JLabel fileLabel;
/*     */     protected JTextField fileTextField;
/*     */     protected JButton browseButton;
/*     */     
/*     */     public String getPath() {
/* 296 */       if (this.fileCheckBox.isSelected()) {
/* 297 */         return this.fileTextField.getText();
/*     */       }
/*     */       
/* 300 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setPath(String s) {
/* 308 */       if (s == null) {
/* 309 */         this.fileTextField.setEnabled(false);
/* 310 */         this.fileCheckBox.setSelected(false);
/*     */       } else {
/*     */         
/* 313 */         this.fileTextField.setEnabled(true);
/* 314 */         this.fileTextField.setText(s);
/* 315 */         this.fileCheckBox.setSelected(true);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected class FileCheckBoxChangeListener
/*     */       implements ChangeListener
/*     */     {
/*     */       public void stateChanged(ChangeEvent e) {
/* 324 */         boolean selected = UserStyleDialog.Panel.this.fileCheckBox.isSelected();
/* 325 */         UserStyleDialog.Panel.this.fileLabel.setEnabled(selected);
/* 326 */         UserStyleDialog.Panel.this.fileTextField.setEnabled(selected);
/* 327 */         UserStyleDialog.Panel.this.browseButton.setEnabled(selected);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected class FileBrowseButtonAction
/*     */       extends AbstractAction
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 336 */         JFileChooser fileChooser = new JFileChooser(new File("."));
/* 337 */         fileChooser.setFileHidingEnabled(false);
/*     */         
/* 339 */         int choice = fileChooser.showOpenDialog(UserStyleDialog.Panel.this);
/* 340 */         if (choice == 0) {
/* 341 */           File f = fileChooser.getSelectedFile();
/*     */           try {
/* 343 */             UserStyleDialog.Panel.this.fileTextField.setText(f.getCanonicalPath());
/* 344 */           } catch (IOException iOException) {}
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/UserStyleDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */