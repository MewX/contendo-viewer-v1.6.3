/*     */ package org.apache.batik.ext.swing;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.Serializable;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.text.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JAffineTransformChooser
/*     */   extends JGridBagPanel
/*     */ {
/*     */   public static final String LABEL_ANGLE = "JAffineTransformChooser.label.angle";
/*     */   public static final String LABEL_DEGREE = "JAffineTransformChooser.label.degree";
/*     */   public static final String LABEL_PERCENT = "JAffineTransformChooser.label.percent";
/*     */   public static final String LABEL_ROTATE = "JAffineTransformChooser.label.rotate";
/*     */   public static final String LABEL_SCALE = "JAffineTransformChooser.label.scale";
/*     */   public static final String LABEL_RX = "JAffineTransformChooser.label.rx";
/*     */   public static final String LABEL_RY = "JAffineTransformChooser.label.ry";
/*     */   public static final String LABEL_SX = "JAffineTransformChooser.label.sx";
/*     */   public static final String LABEL_SY = "JAffineTransformChooser.label.sy";
/*     */   public static final String LABEL_TRANSLATE = "JAffineTransformChooser.label.translate";
/*     */   public static final String LABEL_TX = "JAffineTransformChooser.label.tx";
/*     */   public static final String LABEL_TY = "JAffineTransformChooser.label.ty";
/*     */   public static final String CONFIG_TEXT_FIELD_WIDTH = "JAffineTransformChooser.config.text.field.width";
/*     */   public static final String CONFIG_TOP_PAD = "JAffineTransformChooser.config.top.pad";
/*     */   public static final String CONFIG_LEFT_PAD = "JAffineTransformChooser.config.left.pad";
/*     */   public static final String CONFIG_BOTTOM_PAD = "JAffineTransformChooser.config.bottom.pad";
/*     */   public static final String CONFIG_RIGHT_PAD = "JAffineTransformChooser.config.right.pad";
/*     */   protected AffineTransform txf;
/* 117 */   protected DoubleDocument txModel = new DoubleDocument();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   protected DoubleDocument tyModel = new DoubleDocument();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   protected DoubleDocument sxModel = new DoubleDocument();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   protected DoubleDocument syModel = new DoubleDocument();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   protected DoubleDocument rxModel = new DoubleDocument();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   protected DoubleDocument ryModel = new DoubleDocument();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   protected DoubleDocument rotateModel = new DoubleDocument();
/*     */ 
/*     */   
/*     */   protected static final double RAD_TO_DEG = 57.29577951308232D;
/*     */   
/*     */   protected static final double DEG_TO_RAD = 0.017453292519943295D;
/*     */ 
/*     */   
/*     */   public JAffineTransformChooser() {
/* 156 */     build();
/* 157 */     setAffineTransform(new AffineTransform());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void build() {
/* 164 */     Component txyCmp = buildPanel(Resources.getString("JAffineTransformChooser.label.translate"), Resources.getString("JAffineTransformChooser.label.tx"), this.txModel, Resources.getString("JAffineTransformChooser.label.ty"), this.tyModel, "", "", true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     Component sxyCmp = buildPanel(Resources.getString("JAffineTransformChooser.label.scale"), Resources.getString("JAffineTransformChooser.label.sx"), this.sxModel, Resources.getString("JAffineTransformChooser.label.sy"), this.syModel, Resources.getString("JAffineTransformChooser.label.percent"), Resources.getString("JAffineTransformChooser.label.percent"), true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     Component rCmp = buildRotatePanel();
/*     */     
/* 184 */     add(txyCmp, 0, 0, 1, 1, 10, 1, 1.0D, 1.0D);
/* 185 */     add(sxyCmp, 1, 0, 1, 1, 10, 1, 1.0D, 1.0D);
/* 186 */     add(rCmp, 0, 1, 2, 1, 10, 1, 1.0D, 1.0D);
/*     */   }
/*     */   
/*     */   protected Component buildRotatePanel() {
/* 190 */     JGridBagPanel panel = new JGridBagPanel();
/*     */     
/* 192 */     Component anglePanel = buildPanel(Resources.getString("JAffineTransformChooser.label.rotate"), Resources.getString("JAffineTransformChooser.label.angle"), this.rotateModel, (String)null, (Document)null, Resources.getString("JAffineTransformChooser.label.degree"), (String)null, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     Component centerPanel = buildPanel("", Resources.getString("JAffineTransformChooser.label.rx"), this.rxModel, Resources.getString("JAffineTransformChooser.label.ry"), this.ryModel, (String)null, (String)null, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     panel.add(anglePanel, 0, 0, 1, 1, 10, 1, 1.0D, 1.0D);
/* 211 */     panel.add(centerPanel, 1, 0, 1, 1, 10, 1, 1.0D, 1.0D);
/*     */     
/* 213 */     setPanelBorder(panel, Resources.getString("JAffineTransformChooser.label.rotate"));
/*     */     
/* 215 */     return panel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Component buildPanel(String panelName, String tfALabel, Document tfAModel, String tfBLabel, Document tfBModel, String tfASuffix, String tfBSuffix, boolean setBorder) {
/* 226 */     JGridBagPanel panel = new JGridBagPanel();
/*     */     
/* 228 */     addToPanelAtRow(tfALabel, tfAModel, tfASuffix, panel, 0);
/* 229 */     if (tfBLabel != null) {
/* 230 */       addToPanelAtRow(tfBLabel, tfBModel, tfBSuffix, panel, 1);
/*     */     }
/*     */ 
/*     */     
/* 234 */     if (setBorder) {
/* 235 */       setPanelBorder(panel, panelName);
/*     */     }
/*     */     
/* 238 */     return panel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPanelBorder(JComponent panel, String panelName) {
/* 243 */     Border border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), panelName);
/*     */ 
/*     */ 
/*     */     
/* 247 */     int topPad = Resources.getInteger("JAffineTransformChooser.config.top.pad");
/* 248 */     int leftPad = Resources.getInteger("JAffineTransformChooser.config.left.pad");
/* 249 */     int bottomPad = Resources.getInteger("JAffineTransformChooser.config.bottom.pad");
/* 250 */     int rightPad = Resources.getInteger("JAffineTransformChooser.config.right.pad");
/*     */     
/* 252 */     border = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(topPad, leftPad, bottomPad, rightPad));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 258 */     panel.setBorder(border);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addToPanelAtRow(String label, Document model, String suffix, JGridBagPanel p, int row) {
/* 266 */     JTextField tf = new JTextField(Resources.getInteger("JAffineTransformChooser.config.text.field.width"));
/* 267 */     tf.setDocument(model);
/* 268 */     p.add(new JLabel(label), 0, row, 1, 1, 17, 2, 0.0D, 0.0D);
/* 269 */     p.add(tf, 1, row, 1, 1, 10, 2, 1.0D, 0.0D);
/* 270 */     p.add(new JLabel(suffix), 2, row, 1, 1, 17, 2, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public AffineTransform getAffineTransform() {
/* 274 */     double sx = this.sxModel.getValue() / 100.0D;
/* 275 */     double sy = this.syModel.getValue() / 100.0D;
/* 276 */     double theta = this.rotateModel.getValue() * 0.017453292519943295D;
/* 277 */     double rx = this.rxModel.getValue();
/* 278 */     double ry = this.ryModel.getValue();
/* 279 */     double tx = this.txModel.getValue();
/* 280 */     double ty = this.tyModel.getValue();
/*     */     
/* 282 */     double[] m = new double[6];
/*     */     
/* 284 */     double SIN_THETA = Math.sin(theta);
/* 285 */     double COS_THETA = Math.cos(theta);
/*     */     
/* 287 */     m[0] = sx * COS_THETA;
/* 288 */     m[1] = sx * SIN_THETA;
/* 289 */     m[2] = -sy * SIN_THETA;
/* 290 */     m[3] = sy * COS_THETA;
/* 291 */     m[4] = tx + rx - rx * COS_THETA + ry * SIN_THETA;
/* 292 */     m[5] = ty + ry - rx * SIN_THETA - ry * COS_THETA;
/*     */     
/* 294 */     this.txf = new AffineTransform(m);
/*     */     
/* 296 */     return this.txf;
/*     */   }
/*     */   
/*     */   public void setAffineTransform(AffineTransform txf) {
/* 300 */     if (txf == null) {
/* 301 */       txf = new AffineTransform();
/*     */     }
/*     */     
/* 304 */     this.txf = txf;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 309 */     double[] m = new double[6];
/* 310 */     txf.getMatrix(m);
/*     */ 
/*     */     
/* 313 */     this.txModel.setValue(m[4]);
/* 314 */     this.tyModel.setValue(m[5]);
/*     */ 
/*     */     
/* 317 */     double sx = Math.sqrt(m[0] * m[0] + m[1] * m[1]);
/* 318 */     double sy = Math.sqrt(m[2] * m[2] + m[3] * m[3]);
/* 319 */     this.sxModel.setValue(100.0D * sx);
/* 320 */     this.syModel.setValue(100.0D * sy);
/*     */ 
/*     */     
/* 323 */     double theta = 0.0D;
/* 324 */     if (m[0] > 0.0D) {
/* 325 */       theta = Math.atan2(m[1], m[0]);
/*     */     }
/*     */ 
/*     */     
/* 329 */     this.rotateModel.setValue(57.29577951308232D * theta);
/* 330 */     this.rxModel.setValue(0.0D);
/* 331 */     this.ryModel.setValue(0.0D);
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
/*     */   public static AffineTransform showDialog(Component cmp, String title) {
/* 344 */     JAffineTransformChooser pane = new JAffineTransformChooser();
/*     */ 
/*     */     
/* 347 */     AffineTransformTracker tracker = new AffineTransformTracker(pane);
/* 348 */     JDialog dialog = new Dialog(cmp, title, true, pane, tracker, null);
/* 349 */     dialog.addWindowListener(new Closer());
/* 350 */     dialog.addComponentListener(new DisposeOnClose());
/*     */     
/* 352 */     dialog.setVisible(true);
/*     */     
/* 354 */     return tracker.getAffineTransform();
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
/*     */   public static Dialog createDialog(Component cmp, String title) {
/* 367 */     JAffineTransformChooser pane = new JAffineTransformChooser();
/*     */ 
/*     */     
/* 370 */     AffineTransformTracker tracker = new AffineTransformTracker(pane);
/* 371 */     Dialog dialog = new Dialog(cmp, title, true, pane, tracker, null);
/* 372 */     dialog.addWindowListener(new Closer());
/* 373 */     dialog.addComponentListener(new DisposeOnClose());
/*     */     
/* 375 */     return dialog;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 380 */     AffineTransform t = showDialog((Component)null, "Hello");
/*     */ 
/*     */ 
/*     */     
/* 384 */     if (t == null) {
/* 385 */       System.out.println("Cancelled");
/*     */     } else {
/*     */       
/* 388 */       System.out.println("t = " + t);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Dialog
/*     */     extends JDialog
/*     */   {
/*     */     private JAffineTransformChooser chooserPane;
/*     */ 
/*     */     
/*     */     private AffineTransformTracker tracker;
/*     */ 
/*     */     
/*     */     public static final String LABEL_OK = "JAffineTransformChooser.label.ok";
/*     */ 
/*     */     
/*     */     public static final String LABEL_CANCEL = "JAffineTransformChooser.label.cancel";
/*     */ 
/*     */     
/*     */     public static final String LABEL_RESET = "JAffineTransformChooser.label.reset";
/*     */ 
/*     */     
/*     */     public static final String ACTION_COMMAND_OK = "OK";
/*     */ 
/*     */     
/*     */     public static final String ACTION_COMMAND_CANCEL = "cancel";
/*     */ 
/*     */ 
/*     */     
/*     */     public Dialog(Component c, String title, boolean modal, JAffineTransformChooser chooserPane, AffineTransformTracker okListener, ActionListener cancelListener) {
/* 421 */       super(JOptionPane.getFrameForComponent(c), title, modal);
/*     */       
/* 423 */       this.chooserPane = chooserPane;
/* 424 */       this.tracker = okListener;
/*     */       
/* 426 */       String okString = Resources.getString("JAffineTransformChooser.label.ok");
/* 427 */       String cancelString = Resources.getString("JAffineTransformChooser.label.cancel");
/* 428 */       String resetString = Resources.getString("JAffineTransformChooser.label.reset");
/*     */       
/* 430 */       Container contentPane = getContentPane();
/* 431 */       contentPane.setLayout(new BorderLayout());
/* 432 */       contentPane.add(chooserPane, "Center");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 437 */       JPanel buttonPane = new JPanel();
/* 438 */       buttonPane.setLayout(new FlowLayout(1));
/* 439 */       JButton okButton = new JButton(okString);
/* 440 */       getRootPane().setDefaultButton(okButton);
/* 441 */       okButton.setActionCommand("OK");
/* 442 */       if (okListener != null) {
/* 443 */         okButton.addActionListener(okListener);
/*     */       }
/* 445 */       okButton.addActionListener(new ActionListener() {
/*     */             public void actionPerformed(ActionEvent e) {
/* 447 */               JAffineTransformChooser.Dialog.this.setVisible(false);
/*     */             }
/*     */           });
/* 450 */       buttonPane.add(okButton);
/*     */       
/* 452 */       JButton cancelButton = new JButton(cancelString);
/*     */       
/* 454 */       addKeyListener(new KeyAdapter() {
/*     */             public void keyPressed(KeyEvent evt) {
/* 456 */               if (evt.getKeyCode() == 27) {
/* 457 */                 JAffineTransformChooser.Dialog.this.setVisible(false);
/*     */               }
/*     */             }
/*     */           });
/*     */       
/* 462 */       cancelButton.addActionListener(new ActionListener() {
/*     */             public void actionPerformed(ActionEvent e) {
/* 464 */               JAffineTransformChooser.Dialog.this.setVisible(false);
/*     */             }
/*     */           });
/*     */       
/* 468 */       buttonPane.add(cancelButton);
/*     */       
/* 470 */       JButton resetButton = new JButton(resetString);
/* 471 */       resetButton.addActionListener(new ActionListener() {
/*     */             public void actionPerformed(ActionEvent e) {
/* 473 */               JAffineTransformChooser.Dialog.this.reset();
/*     */             }
/*     */           });
/* 476 */       buttonPane.add(resetButton);
/* 477 */       contentPane.add(buttonPane, "South");
/*     */       
/* 479 */       pack();
/* 480 */       setLocationRelativeTo(c);
/*     */     }
/*     */     
/*     */     public void setVisible(boolean b) {
/* 484 */       if (b) this.tracker.reset(); 
/* 485 */       super.setVisible(b);
/*     */     }
/*     */     
/*     */     public AffineTransform showDialog() {
/* 489 */       setVisible(true);
/* 490 */       return this.tracker.getAffineTransform();
/*     */     }
/*     */     
/*     */     public void reset() {
/* 494 */       this.chooserPane.setAffineTransform(new AffineTransform());
/*     */     }
/*     */     
/*     */     public void setTransform(AffineTransform at) {
/* 498 */       if (at == null) {
/* 499 */         at = new AffineTransform();
/*     */       }
/*     */       
/* 502 */       this.chooserPane.setAffineTransform(at);
/*     */     }
/*     */   }
/*     */   
/*     */   static class Closer
/*     */     extends WindowAdapter
/*     */     implements Serializable
/*     */   {
/*     */     public void windowClosing(WindowEvent e) {
/* 511 */       Window w = e.getWindow();
/* 512 */       w.setVisible(false);
/*     */     }
/*     */   }
/*     */   
/*     */   static class DisposeOnClose extends ComponentAdapter implements Serializable {
/*     */     public void componentHidden(ComponentEvent e) {
/* 518 */       Window w = (Window)e.getComponent();
/* 519 */       w.dispose();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/swing/JAffineTransformChooser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */