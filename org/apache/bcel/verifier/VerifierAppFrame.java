/*     */ package org.apache.bcel.verifier;
/*     */ 
/*     */ import java.awt.CardLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import org.apache.bcel.Repository;
/*     */ import org.apache.bcel.classfile.JavaClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerifierAppFrame
/*     */   extends JFrame
/*     */ {
/*     */   JPanel contentPane;
/*  75 */   JSplitPane jSplitPane1 = new JSplitPane();
/*  76 */   JPanel jPanel1 = new JPanel();
/*  77 */   JPanel jPanel2 = new JPanel();
/*  78 */   JSplitPane jSplitPane2 = new JSplitPane();
/*  79 */   JPanel jPanel3 = new JPanel();
/*  80 */   JList classNamesJList = new JList();
/*  81 */   GridLayout gridLayout1 = new GridLayout();
/*  82 */   JPanel messagesPanel = new JPanel();
/*  83 */   GridLayout gridLayout2 = new GridLayout();
/*  84 */   JMenuBar jMenuBar1 = new JMenuBar();
/*  85 */   JMenu jMenu1 = new JMenu();
/*  86 */   JScrollPane jScrollPane1 = new JScrollPane();
/*  87 */   JScrollPane messagesScrollPane = new JScrollPane();
/*  88 */   JScrollPane jScrollPane3 = new JScrollPane();
/*  89 */   GridLayout gridLayout4 = new GridLayout();
/*  90 */   JScrollPane jScrollPane4 = new JScrollPane();
/*  91 */   CardLayout cardLayout1 = new CardLayout();
/*     */   
/*  93 */   private String JUSTICE_VERSION = "JustIce by Enver Haase";
/*     */   private String current_class;
/*  95 */   GridLayout gridLayout3 = new GridLayout();
/*  96 */   JTextPane pass1TextPane = new JTextPane();
/*  97 */   JTextPane pass2TextPane = new JTextPane();
/*  98 */   JTextPane messagesTextPane = new JTextPane();
/*  99 */   JMenuItem newFileMenuItem = new JMenuItem();
/* 100 */   JSplitPane jSplitPane3 = new JSplitPane();
/* 101 */   JSplitPane jSplitPane4 = new JSplitPane();
/* 102 */   JScrollPane jScrollPane2 = new JScrollPane();
/* 103 */   JScrollPane jScrollPane5 = new JScrollPane();
/* 104 */   JScrollPane jScrollPane6 = new JScrollPane();
/* 105 */   JScrollPane jScrollPane7 = new JScrollPane();
/* 106 */   JList pass3aJList = new JList();
/* 107 */   JList pass3bJList = new JList();
/* 108 */   JTextPane pass3aTextPane = new JTextPane();
/* 109 */   JTextPane pass3bTextPane = new JTextPane();
/* 110 */   JMenu jMenu2 = new JMenu();
/* 111 */   JMenuItem whatisMenuItem = new JMenuItem();
/* 112 */   JMenuItem aboutMenuItem = new JMenuItem();
/*     */ 
/*     */   
/*     */   public VerifierAppFrame() {
/* 116 */     enableEvents(64L);
/*     */     try {
/* 118 */       jbInit();
/*     */     } catch (Exception e) {
/*     */       
/* 121 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void jbInit() throws Exception {
/* 127 */     this.contentPane = (JPanel)getContentPane();
/* 128 */     this.contentPane.setLayout(this.cardLayout1);
/* 129 */     setJMenuBar(this.jMenuBar1);
/* 130 */     setSize(new Dimension(708, 451));
/* 131 */     setTitle("JustIce");
/* 132 */     this.jPanel1.setMinimumSize(new Dimension(100, 100));
/* 133 */     this.jPanel1.setPreferredSize(new Dimension(100, 100));
/* 134 */     this.jPanel1.setLayout(this.gridLayout1);
/* 135 */     this.jSplitPane2.setOrientation(0);
/* 136 */     this.jPanel2.setLayout(this.gridLayout2);
/* 137 */     this.jPanel3.setMinimumSize(new Dimension(200, 100));
/* 138 */     this.jPanel3.setPreferredSize(new Dimension(400, 400));
/* 139 */     this.jPanel3.setLayout(this.gridLayout4);
/* 140 */     this.messagesPanel.setMinimumSize(new Dimension(100, 100));
/* 141 */     this.messagesPanel.setLayout(this.gridLayout3);
/* 142 */     this.jPanel2.setMinimumSize(new Dimension(200, 100));
/* 143 */     this.jMenu1.setText("File");
/*     */     
/* 145 */     this.jScrollPane1.getViewport().setBackground(Color.red);
/* 146 */     this.messagesScrollPane.getViewport().setBackground(Color.red);
/* 147 */     this.messagesScrollPane.setPreferredSize(new Dimension(10, 10));
/* 148 */     this.classNamesJList.addListSelectionListener(new ListSelectionListener(this) {
/*     */           public void valueChanged(ListSelectionEvent e) {
/* 150 */             this.this$0.classNamesJList_valueChanged(e);
/*     */           } private final VerifierAppFrame this$0;
/*     */         });
/* 153 */     this.classNamesJList.setSelectionMode(0);
/* 154 */     this.jScrollPane3.setBorder(BorderFactory.createLineBorder(Color.black));
/* 155 */     this.jScrollPane3.setPreferredSize(new Dimension(100, 100));
/* 156 */     this.gridLayout4.setRows(4);
/* 157 */     this.gridLayout4.setColumns(1);
/* 158 */     this.gridLayout4.setHgap(1);
/* 159 */     this.jScrollPane4.setBorder(BorderFactory.createLineBorder(Color.black));
/* 160 */     this.jScrollPane4.setPreferredSize(new Dimension(100, 100));
/* 161 */     this.pass1TextPane.setBorder(BorderFactory.createRaisedBevelBorder());
/* 162 */     this.pass1TextPane.setToolTipText("");
/* 163 */     this.pass1TextPane.setEditable(false);
/* 164 */     this.pass2TextPane.setBorder(BorderFactory.createRaisedBevelBorder());
/* 165 */     this.pass2TextPane.setEditable(false);
/* 166 */     this.messagesTextPane.setBorder(BorderFactory.createRaisedBevelBorder());
/* 167 */     this.messagesTextPane.setEditable(false);
/* 168 */     this.newFileMenuItem.setText("New...");
/* 169 */     this.newFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(78, 2, true));
/* 170 */     this.newFileMenuItem.addActionListener(new ActionListener(this) {
/*     */           public void actionPerformed(ActionEvent e) {
/* 172 */             this.this$0.newFileMenuItem_actionPerformed(e);
/*     */           } private final VerifierAppFrame this$0;
/*     */         });
/* 175 */     this.pass3aTextPane.setEditable(false);
/* 176 */     this.pass3bTextPane.setEditable(false);
/* 177 */     this.pass3aJList.addListSelectionListener(new ListSelectionListener(this) { private final VerifierAppFrame this$0;
/*     */           public void valueChanged(ListSelectionEvent e) {
/* 179 */             this.this$0.pass3aJList_valueChanged(e);
/*     */           } }
/*     */       );
/* 182 */     this.pass3bJList.addListSelectionListener(new ListSelectionListener(this) { private final VerifierAppFrame this$0;
/*     */           public void valueChanged(ListSelectionEvent e) {
/* 184 */             this.this$0.pass3bJList_valueChanged(e);
/*     */           } }
/*     */       );
/* 187 */     this.jMenu2.setText("Help");
/* 188 */     this.whatisMenuItem.setText("What is...");
/* 189 */     this.whatisMenuItem.addActionListener(new ActionListener(this) { private final VerifierAppFrame this$0;
/*     */           public void actionPerformed(ActionEvent e) {
/* 191 */             this.this$0.whatisMenuItem_actionPerformed(e);
/*     */           } }
/*     */       );
/* 194 */     this.aboutMenuItem.setText("About");
/* 195 */     this.aboutMenuItem.addActionListener(new ActionListener(this) { private final VerifierAppFrame this$0;
/*     */           public void actionPerformed(ActionEvent e) {
/* 197 */             this.this$0.aboutMenuItem_actionPerformed(e);
/*     */           } }
/*     */       );
/* 200 */     this.jSplitPane2.add(this.messagesPanel, "bottom");
/* 201 */     this.messagesPanel.add(this.messagesScrollPane, (Object)null);
/* 202 */     this.messagesScrollPane.getViewport().add(this.messagesTextPane, (Object)null);
/* 203 */     this.jSplitPane2.add(this.jPanel3, "top");
/* 204 */     this.jPanel3.add(this.jScrollPane3, (Object)null);
/* 205 */     this.jScrollPane3.getViewport().add(this.pass1TextPane, (Object)null);
/* 206 */     this.jPanel3.add(this.jScrollPane4, (Object)null);
/* 207 */     this.jPanel3.add(this.jSplitPane3, (Object)null);
/* 208 */     this.jSplitPane3.add(this.jScrollPane2, "left");
/* 209 */     this.jScrollPane2.getViewport().add(this.pass3aJList, (Object)null);
/* 210 */     this.jSplitPane3.add(this.jScrollPane5, "right");
/* 211 */     this.jScrollPane5.getViewport().add(this.pass3aTextPane, (Object)null);
/* 212 */     this.jPanel3.add(this.jSplitPane4, (Object)null);
/* 213 */     this.jSplitPane4.add(this.jScrollPane6, "left");
/* 214 */     this.jScrollPane6.getViewport().add(this.pass3bJList, (Object)null);
/* 215 */     this.jSplitPane4.add(this.jScrollPane7, "right");
/* 216 */     this.jScrollPane7.getViewport().add(this.pass3bTextPane, (Object)null);
/* 217 */     this.jScrollPane4.getViewport().add(this.pass2TextPane, (Object)null);
/* 218 */     this.jSplitPane1.add(this.jPanel2, "top");
/* 219 */     this.jPanel2.add(this.jScrollPane1, (Object)null);
/* 220 */     this.jSplitPane1.add(this.jPanel1, "bottom");
/* 221 */     this.jPanel1.add(this.jSplitPane2, (Object)null);
/* 222 */     this.jScrollPane1.getViewport().add(this.classNamesJList, (Object)null);
/* 223 */     this.jMenuBar1.add(this.jMenu1);
/* 224 */     this.jMenuBar1.add(this.jMenu2);
/* 225 */     this.contentPane.add(this.jSplitPane1, "jSplitPane1");
/* 226 */     this.jMenu1.add(this.newFileMenuItem);
/* 227 */     this.jMenu2.add(this.whatisMenuItem);
/* 228 */     this.jMenu2.add(this.aboutMenuItem);
/* 229 */     this.jSplitPane2.setDividerLocation(300);
/* 230 */     this.jSplitPane3.setDividerLocation(150);
/* 231 */     this.jSplitPane4.setDividerLocation(150);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processWindowEvent(WindowEvent e) {
/* 236 */     super.processWindowEvent(e);
/* 237 */     if (e.getID() == 201) {
/* 238 */       System.exit(0);
/*     */     }
/*     */   }
/*     */   
/*     */   synchronized void classNamesJList_valueChanged(ListSelectionEvent e) {
/* 243 */     if (e.getValueIsAdjusting())
/* 244 */       return;  this.current_class = this.classNamesJList.getSelectedValue().toString();
/* 245 */     verify();
/* 246 */     this.classNamesJList.setSelectedValue(this.current_class, true);
/*     */   }
/*     */   
/*     */   private void verify() {
/* 250 */     setTitle("PLEASE WAIT");
/*     */     
/* 252 */     Verifier v = VerifierFactory.getVerifier(this.current_class);
/* 253 */     v.flush();
/*     */ 
/*     */ 
/*     */     
/* 257 */     VerificationResult vr = v.doPass1();
/* 258 */     if (vr.getStatus() == 2) {
/* 259 */       this.pass1TextPane.setText(vr.getMessage());
/* 260 */       this.pass1TextPane.setBackground(Color.red);
/*     */       
/* 262 */       this.pass2TextPane.setText("");
/* 263 */       this.pass2TextPane.setBackground(Color.yellow);
/* 264 */       this.pass3aTextPane.setText("");
/* 265 */       this.pass3aJList.setListData(new Object[0]);
/* 266 */       this.pass3aTextPane.setBackground(Color.yellow);
/*     */       
/* 268 */       this.pass3bTextPane.setText("");
/* 269 */       this.pass3bJList.setListData(new Object[0]);
/* 270 */       this.pass3bTextPane.setBackground(Color.yellow);
/*     */     }
/*     */     else {
/*     */       
/* 274 */       this.pass1TextPane.setBackground(Color.green);
/* 275 */       this.pass1TextPane.setText(vr.getMessage());
/*     */       
/* 277 */       vr = v.doPass2();
/* 278 */       if (vr.getStatus() == 2) {
/* 279 */         this.pass2TextPane.setText(vr.getMessage());
/* 280 */         this.pass2TextPane.setBackground(Color.red);
/*     */         
/* 282 */         this.pass3aTextPane.setText("");
/* 283 */         this.pass3aTextPane.setBackground(Color.yellow);
/* 284 */         this.pass3aJList.setListData(new Object[0]);
/* 285 */         this.pass3bTextPane.setText("");
/* 286 */         this.pass3bTextPane.setBackground(Color.yellow);
/* 287 */         this.pass3bJList.setListData(new Object[0]);
/*     */       } else {
/*     */         
/* 290 */         this.pass2TextPane.setText(vr.getMessage());
/* 291 */         this.pass2TextPane.setBackground(Color.green);
/*     */         
/* 293 */         JavaClass jc = Repository.lookupClass(this.current_class);
/* 294 */         boolean all3aok = true;
/* 295 */         boolean all3bok = true;
/* 296 */         String all3amsg = "";
/* 297 */         String all3bmsg = "";
/*     */         
/* 299 */         String[] methodnames = new String[(jc.getMethods()).length];
/* 300 */         for (int j = 0; j < (jc.getMethods()).length; j++) {
/* 301 */           methodnames[j] = jc.getMethods()[j].toString().replace('\n', ' ').replace('\t', ' ');
/*     */         }
/* 303 */         this.pass3aJList.setListData(methodnames);
/* 304 */         this.pass3aJList.setSelectionInterval(0, (jc.getMethods()).length - 1);
/* 305 */         this.pass3bJList.setListData(methodnames);
/* 306 */         this.pass3bJList.setSelectionInterval(0, (jc.getMethods()).length - 1);
/*     */       } 
/*     */     } 
/*     */     
/* 310 */     String[] msgs = v.getMessages();
/* 311 */     this.messagesTextPane.setBackground((msgs.length == 0) ? Color.green : Color.yellow);
/* 312 */     String allmsgs = "";
/* 313 */     for (int i = 0; i < msgs.length; i++) {
/* 314 */       msgs[i] = msgs[i].replace('\n', ' ');
/* 315 */       allmsgs = allmsgs + msgs[i] + "\n\n";
/*     */     } 
/* 317 */     this.messagesTextPane.setText(allmsgs);
/*     */     
/* 319 */     setTitle(this.current_class + " - " + this.JUSTICE_VERSION);
/*     */   }
/*     */   
/*     */   void newFileMenuItem_actionPerformed(ActionEvent e) {
/* 323 */     String classname = JOptionPane.showInputDialog("Please enter the fully qualified name of a class or interface to verify:");
/* 324 */     if (classname == null || classname.equals(""))
/* 325 */       return;  VerifierFactory.getVerifier(classname);
/* 326 */     this.classNamesJList.setSelectedValue(classname, true);
/*     */   }
/*     */ 
/*     */   
/*     */   synchronized void pass3aJList_valueChanged(ListSelectionEvent e) {
/* 331 */     if (e.getValueIsAdjusting())
/* 332 */       return;  Verifier v = VerifierFactory.getVerifier(this.current_class);
/*     */     
/* 334 */     String all3amsg = "";
/* 335 */     boolean all3aok = true;
/* 336 */     boolean rejected = false;
/* 337 */     for (int i = 0; i < this.pass3aJList.getModel().getSize(); i++) {
/*     */       
/* 339 */       if (this.pass3aJList.isSelectedIndex(i)) {
/* 340 */         VerificationResult vr = v.doPass3a(i);
/*     */         
/* 342 */         if (vr.getStatus() == 2) {
/* 343 */           all3aok = false;
/* 344 */           rejected = true;
/*     */         } 
/* 346 */         all3amsg = all3amsg + "Method '" + Repository.lookupClass(v.getClassName()).getMethods()[i] + "': " + vr.getMessage().replace('\n', ' ') + "\n\n";
/*     */       } 
/*     */     } 
/* 349 */     this.pass3aTextPane.setText(all3amsg);
/* 350 */     this.pass3aTextPane.setBackground(all3aok ? Color.green : (rejected ? Color.red : Color.yellow));
/*     */   }
/*     */ 
/*     */   
/*     */   synchronized void pass3bJList_valueChanged(ListSelectionEvent e) {
/* 355 */     if (e.getValueIsAdjusting())
/*     */       return; 
/* 357 */     Verifier v = VerifierFactory.getVerifier(this.current_class);
/*     */     
/* 359 */     String all3bmsg = "";
/* 360 */     boolean all3bok = true;
/* 361 */     boolean rejected = false;
/* 362 */     for (int i = 0; i < this.pass3bJList.getModel().getSize(); i++) {
/*     */       
/* 364 */       if (this.pass3bJList.isSelectedIndex(i)) {
/* 365 */         VerificationResult vr = v.doPass3b(i);
/*     */         
/* 367 */         if (vr.getStatus() == 2) {
/* 368 */           all3bok = false;
/* 369 */           rejected = true;
/*     */         } 
/* 371 */         all3bmsg = all3bmsg + "Method '" + Repository.lookupClass(v.getClassName()).getMethods()[i] + "': " + vr.getMessage().replace('\n', ' ') + "\n\n";
/*     */       } 
/*     */     } 
/* 374 */     this.pass3bTextPane.setText(all3bmsg);
/* 375 */     this.pass3bTextPane.setBackground(all3bok ? Color.green : (rejected ? Color.red : Color.yellow));
/*     */   }
/*     */ 
/*     */   
/*     */   void aboutMenuItem_actionPerformed(ActionEvent e) {
/* 380 */     JOptionPane.showMessageDialog(this, "JustIce is a Java class file verifier.\nIt was implemented by Enver Haase in 2001.\nhttp://bcel.sourceforge.net", this.JUSTICE_VERSION, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void whatisMenuItem_actionPerformed(ActionEvent e) {
/* 386 */     JOptionPane.showMessageDialog(this, "The upper four boxes to the right reflect verification passes according to The Java Virtual Machine Specification.\nThese are (in that order): Pass one, Pass two, Pass three (before data flow analysis), Pass three (data flow analysis).\nThe bottom box to the right shows (warning) messages; warnings do not cause a class to be rejected.", this.JUSTICE_VERSION, 1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/VerifierAppFrame.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */