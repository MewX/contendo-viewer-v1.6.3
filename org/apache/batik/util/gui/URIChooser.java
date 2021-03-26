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
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.DocumentEvent;
/*     */ import javax.swing.event.DocumentListener;
/*     */ import javax.swing.filechooser.FileFilter;
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
/*     */ public class URIChooser
/*     */   extends JDialog
/*     */   implements ActionMap
/*     */ {
/*     */   public static final int OK_OPTION = 0;
/*     */   public static final int CANCEL_OPTION = 1;
/*     */   protected static final String RESOURCES = "org.apache.batik.util.gui.resources.URIChooserMessages";
/*  83 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.util.gui.resources.URIChooserMessages", Locale.getDefault());
/*  84 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ButtonFactory buttonFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JTextField textField;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JButton okButton;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JButton clearButton;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   protected String currentPath = ".";
/*     */ 
/*     */ 
/*     */   
/*     */   protected FileFilter fileFilter;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int returnCode;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String chosenPath;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map listeners;
/*     */ 
/*     */ 
/*     */   
/*     */   public URIChooser(JDialog d)
/*     */   {
/* 132 */     super(d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 331 */     this.listeners = new HashMap<Object, Object>(10); initialize(); } public URIChooser(JFrame f) { super(f); this.listeners = new HashMap<Object, Object>(10);
/*     */     initialize(); }
/*     */   
/*     */   public int showDialog() {
/*     */     pack();
/*     */     setVisible(true);
/*     */     return this.returnCode;
/*     */   }
/*     */   public Action getAction(String key) throws MissingListenerException {
/* 340 */     return (Action)this.listeners.get(key);
/*     */   }
/*     */   
/*     */   public String getText() {
/*     */     return this.chosenPath;
/*     */   }
/*     */   
/*     */   public void setFileFilter(FileFilter ff) {
/*     */     this.fileFilter = ff;
/*     */   }
/*     */   
/*     */   protected void initialize() {
/*     */     setModal(true);
/*     */     this.listeners.put("BrowseButtonAction", new BrowseButtonAction());
/*     */     this.listeners.put("OKButtonAction", new OKButtonAction());
/*     */     this.listeners.put("CancelButtonAction", new CancelButtonAction());
/*     */     this.listeners.put("ClearButtonAction", new ClearButtonAction());
/*     */     setTitle(resources.getString("Dialog.title"));
/*     */     this.buttonFactory = new ButtonFactory(bundle, this);
/*     */     getContentPane().add(createURISelectionPanel(), "North");
/*     */     getContentPane().add(createButtonsPanel(), "South");
/*     */   }
/*     */   
/*     */   protected JPanel createURISelectionPanel() {
/*     */     JPanel p = new JPanel(new GridBagLayout());
/*     */     p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/*     */     ExtendedGridBagConstraints constraints = new ExtendedGridBagConstraints();
/*     */     constraints.insets = new Insets(5, 5, 5, 5);
/*     */     constraints.weightx = 0.0D;
/*     */     constraints.weighty = 0.0D;
/*     */     constraints.fill = 2;
/*     */     constraints.setGridBounds(0, 0, 2, 1);
/*     */     p.add(new JLabel(resources.getString("Dialog.label")), constraints);
/*     */     this.textField = new JTextField(30);
/*     */     this.textField.getDocument().addDocumentListener(new DocumentAdapter());
/*     */     constraints.weightx = 1.0D;
/*     */     constraints.weighty = 0.0D;
/*     */     constraints.fill = 2;
/*     */     constraints.setGridBounds(0, 1, 1, 1);
/*     */     p.add(this.textField, constraints);
/*     */     constraints.weightx = 0.0D;
/*     */     constraints.weighty = 0.0D;
/*     */     constraints.fill = 0;
/*     */     constraints.setGridBounds(1, 1, 1, 1);
/*     */     p.add(this.buttonFactory.createJButton("BrowseButton"), constraints);
/*     */     return p;
/*     */   }
/*     */   
/*     */   protected JPanel createButtonsPanel() {
/*     */     JPanel p = new JPanel(new FlowLayout());
/*     */     p.add(this.okButton = this.buttonFactory.createJButton("OKButton"));
/*     */     p.add(this.buttonFactory.createJButton("CancelButton"));
/*     */     p.add(this.clearButton = this.buttonFactory.createJButton("ClearButton"));
/*     */     this.okButton.setEnabled(false);
/*     */     this.clearButton.setEnabled(false);
/*     */     return p;
/*     */   }
/*     */   
/*     */   protected void updateOKButtonAction() {
/*     */     this.okButton.setEnabled(!this.textField.getText().equals(""));
/*     */   }
/*     */   
/*     */   protected void updateClearButtonAction() {
/*     */     this.clearButton.setEnabled(!this.textField.getText().equals(""));
/*     */   }
/*     */   
/*     */   protected class DocumentAdapter implements DocumentListener {
/*     */     public void changedUpdate(DocumentEvent e) {
/*     */       URIChooser.this.updateOKButtonAction();
/*     */       URIChooser.this.updateClearButtonAction();
/*     */     }
/*     */     
/*     */     public void insertUpdate(DocumentEvent e) {
/*     */       URIChooser.this.updateOKButtonAction();
/*     */       URIChooser.this.updateClearButtonAction();
/*     */     }
/*     */     
/*     */     public void removeUpdate(DocumentEvent e) {
/*     */       URIChooser.this.updateOKButtonAction();
/*     */       URIChooser.this.updateClearButtonAction();
/*     */     }
/*     */   }
/*     */   
/*     */   protected class BrowseButtonAction extends AbstractAction {
/*     */     public void actionPerformed(ActionEvent e) {
/*     */       JFileChooser fileChooser = new JFileChooser(URIChooser.this.currentPath);
/*     */       fileChooser.setFileHidingEnabled(false);
/*     */       fileChooser.setFileSelectionMode(2);
/*     */       if (URIChooser.this.fileFilter != null)
/*     */         fileChooser.setFileFilter(URIChooser.this.fileFilter); 
/*     */       int choice = fileChooser.showOpenDialog(URIChooser.this);
/*     */       if (choice == 0) {
/*     */         File f = fileChooser.getSelectedFile();
/*     */         try {
/*     */           URIChooser.this.textField.setText(URIChooser.this.currentPath = f.getCanonicalPath());
/*     */         } catch (IOException iOException) {}
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected class OKButtonAction extends AbstractAction {
/*     */     public void actionPerformed(ActionEvent e) {
/*     */       URIChooser.this.returnCode = 0;
/*     */       URIChooser.this.chosenPath = URIChooser.this.textField.getText();
/*     */       URIChooser.this.dispose();
/*     */     }
/*     */   }
/*     */   
/*     */   protected class CancelButtonAction extends AbstractAction {
/*     */     public void actionPerformed(ActionEvent e) {
/*     */       URIChooser.this.returnCode = 1;
/*     */       URIChooser.this.dispose();
/*     */       URIChooser.this.textField.setText(URIChooser.this.chosenPath);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class ClearButtonAction extends AbstractAction {
/*     */     public void actionPerformed(ActionEvent e) {
/*     */       URIChooser.this.textField.setText("");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/URIChooser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */