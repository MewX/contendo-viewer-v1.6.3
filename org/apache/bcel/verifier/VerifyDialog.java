/*     */ package org.apache.bcel.verifier;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.SystemColor;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerifyDialog
/*     */   extends JDialog
/*     */ {
/*  78 */   private JPanel ivjJDialogContentPane = null;
/*     */   
/*  80 */   private JPanel ivjPass1Panel = null;
/*     */   
/*  82 */   private JPanel ivjPass2Panel = null;
/*     */   
/*  84 */   private JPanel ivjPass3Panel = null;
/*     */   
/*  86 */   private JButton ivjPass1Button = null;
/*     */   
/*  88 */   private JButton ivjPass2Button = null;
/*     */   
/*  90 */   private JButton ivjPass3Button = null;
/*     */   
/*  92 */   IvjEventHandler ivjEventHandler = new IvjEventHandler(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   private String class_name = "java.lang.Object";
/*     */   
/*     */   private static int classes_to_verify;
/*     */   
/*     */   class IvjEventHandler
/*     */     implements ActionListener
/*     */   {
/*     */     private final VerifyDialog this$0;
/*     */     
/*     */     IvjEventHandler(VerifyDialog this$0) {
/* 109 */       this.this$0 = this$0;
/*     */     } public void actionPerformed(ActionEvent e) {
/* 111 */       if (e.getSource() == this.this$0.getPass1Button())
/* 112 */         this.this$0.connEtoC1(e); 
/* 113 */       if (e.getSource() == this.this$0.getPass2Button())
/* 114 */         this.this$0.connEtoC2(e); 
/* 115 */       if (e.getSource() == this.this$0.getPass3Button())
/* 116 */         this.this$0.connEtoC3(e); 
/* 117 */       if (e.getSource() == this.this$0.getFlushButton()) {
/* 118 */         this.this$0.connEtoC4(e);
/*     */       }
/*     */     }
/*     */   }
/* 122 */   private JButton ivjFlushButton = null;
/*     */ 
/*     */   
/*     */   public VerifyDialog() {
/* 126 */     initialize();
/*     */   }
/*     */ 
/*     */   
/*     */   public VerifyDialog(Dialog owner) {
/* 131 */     super(owner);
/*     */   }
/*     */ 
/*     */   
/*     */   public VerifyDialog(Dialog owner, String title) {
/* 136 */     super(owner, title);
/*     */   }
/*     */ 
/*     */   
/*     */   public VerifyDialog(Dialog owner, String title, boolean modal) {
/* 141 */     super(owner, title, modal);
/*     */   }
/*     */ 
/*     */   
/*     */   public VerifyDialog(Dialog owner, boolean modal) {
/* 146 */     super(owner, modal);
/*     */   }
/*     */ 
/*     */   
/*     */   public VerifyDialog(Frame owner) {
/* 151 */     super(owner);
/*     */   }
/*     */ 
/*     */   
/*     */   public VerifyDialog(Frame owner, String title) {
/* 156 */     super(owner, title);
/*     */   }
/*     */ 
/*     */   
/*     */   public VerifyDialog(Frame owner, String title, boolean modal) {
/* 161 */     super(owner, title, modal);
/*     */   }
/*     */ 
/*     */   
/*     */   public VerifyDialog(Frame owner, boolean modal) {
/* 166 */     super(owner, modal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerifyDialog(String fully_qualified_class_name) {
/* 177 */     int dotclasspos = fully_qualified_class_name.lastIndexOf(".class");
/* 178 */     if (dotclasspos != -1) fully_qualified_class_name = fully_qualified_class_name.substring(0, dotclasspos); 
/* 179 */     fully_qualified_class_name = fully_qualified_class_name.replace('/', '.');
/*     */     
/* 181 */     this.class_name = fully_qualified_class_name;
/* 182 */     initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void connEtoC1(ActionEvent arg1) {
/*     */     try {
/* 191 */       pass1Button_ActionPerformed(arg1);
/*     */     
/*     */     }
/*     */     catch (Throwable ivjExc) {
/*     */ 
/*     */       
/* 197 */       handleException(ivjExc);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void connEtoC2(ActionEvent arg1) {
/*     */     try {
/* 206 */       pass2Button_ActionPerformed(arg1);
/*     */     
/*     */     }
/*     */     catch (Throwable ivjExc) {
/*     */ 
/*     */       
/* 212 */       handleException(ivjExc);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void connEtoC3(ActionEvent arg1) {
/*     */     try {
/* 221 */       pass4Button_ActionPerformed(arg1);
/*     */     
/*     */     }
/*     */     catch (Throwable ivjExc) {
/*     */ 
/*     */       
/* 227 */       handleException(ivjExc);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void connEtoC4(ActionEvent arg1) {
/*     */     try {
/* 236 */       flushButton_ActionPerformed(arg1);
/*     */     
/*     */     }
/*     */     catch (Throwable ivjExc) {
/*     */ 
/*     */       
/* 242 */       handleException(ivjExc);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void flushButton_ActionPerformed(ActionEvent actionEvent) {
/* 248 */     VerifierFactory.getVerifier(this.class_name).flush();
/* 249 */     Repository.removeClass(this.class_name);
/* 250 */     getPass1Panel().setBackground(Color.gray);
/* 251 */     getPass1Panel().repaint();
/* 252 */     getPass2Panel().setBackground(Color.gray);
/* 253 */     getPass2Panel().repaint();
/* 254 */     getPass3Panel().setBackground(Color.gray);
/* 255 */     getPass3Panel().repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton getFlushButton() {
/* 260 */     if (this.ivjFlushButton == null) {
/*     */       try {
/* 262 */         this.ivjFlushButton = new JButton();
/* 263 */         this.ivjFlushButton.setName("FlushButton");
/* 264 */         this.ivjFlushButton.setText("Flush: Forget old verification results");
/* 265 */         this.ivjFlushButton.setBackground(SystemColor.controlHighlight);
/* 266 */         this.ivjFlushButton.setBounds(60, 215, 300, 30);
/* 267 */         this.ivjFlushButton.setForeground(Color.red);
/* 268 */         this.ivjFlushButton.setActionCommand("FlushButton");
/*     */       
/*     */       }
/*     */       catch (Throwable ivjExc) {
/*     */ 
/*     */         
/* 274 */         handleException(ivjExc);
/*     */       } 
/*     */     }
/* 277 */     return this.ivjFlushButton;
/*     */   }
/*     */ 
/*     */   
/*     */   private JPanel getJDialogContentPane() {
/* 282 */     if (this.ivjJDialogContentPane == null) {
/*     */       try {
/* 284 */         this.ivjJDialogContentPane = new JPanel();
/* 285 */         this.ivjJDialogContentPane.setName("JDialogContentPane");
/* 286 */         this.ivjJDialogContentPane.setLayout(null);
/* 287 */         getJDialogContentPane().add(getPass1Panel(), getPass1Panel().getName());
/* 288 */         getJDialogContentPane().add(getPass3Panel(), getPass3Panel().getName());
/* 289 */         getJDialogContentPane().add(getPass2Panel(), getPass2Panel().getName());
/* 290 */         getJDialogContentPane().add(getPass1Button(), getPass1Button().getName());
/* 291 */         getJDialogContentPane().add(getPass2Button(), getPass2Button().getName());
/* 292 */         getJDialogContentPane().add(getPass3Button(), getPass3Button().getName());
/* 293 */         getJDialogContentPane().add(getFlushButton(), getFlushButton().getName());
/*     */       
/*     */       }
/*     */       catch (Throwable ivjExc) {
/*     */ 
/*     */         
/* 299 */         handleException(ivjExc);
/*     */       } 
/*     */     }
/* 302 */     return this.ivjJDialogContentPane;
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton getPass1Button() {
/* 307 */     if (this.ivjPass1Button == null) {
/*     */       try {
/* 309 */         this.ivjPass1Button = new JButton();
/* 310 */         this.ivjPass1Button.setName("Pass1Button");
/* 311 */         this.ivjPass1Button.setText("Pass1: Verify binary layout of .class file");
/* 312 */         this.ivjPass1Button.setBackground(SystemColor.controlHighlight);
/* 313 */         this.ivjPass1Button.setBounds(100, 40, 300, 30);
/* 314 */         this.ivjPass1Button.setActionCommand("Button1");
/*     */       
/*     */       }
/*     */       catch (Throwable ivjExc) {
/*     */ 
/*     */         
/* 320 */         handleException(ivjExc);
/*     */       } 
/*     */     }
/* 323 */     return this.ivjPass1Button;
/*     */   }
/*     */ 
/*     */   
/*     */   private JPanel getPass1Panel() {
/* 328 */     if (this.ivjPass1Panel == null) {
/*     */       try {
/* 330 */         this.ivjPass1Panel = new JPanel();
/* 331 */         this.ivjPass1Panel.setName("Pass1Panel");
/* 332 */         this.ivjPass1Panel.setLayout(null);
/* 333 */         this.ivjPass1Panel.setBackground(SystemColor.controlShadow);
/* 334 */         this.ivjPass1Panel.setBounds(30, 30, 50, 50);
/*     */       
/*     */       }
/*     */       catch (Throwable ivjExc) {
/*     */ 
/*     */         
/* 340 */         handleException(ivjExc);
/*     */       } 
/*     */     }
/* 343 */     return this.ivjPass1Panel;
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton getPass2Button() {
/* 348 */     if (this.ivjPass2Button == null) {
/*     */       try {
/* 350 */         this.ivjPass2Button = new JButton();
/* 351 */         this.ivjPass2Button.setName("Pass2Button");
/* 352 */         this.ivjPass2Button.setText("Pass 2: Verify static .class file constraints");
/* 353 */         this.ivjPass2Button.setBackground(SystemColor.controlHighlight);
/* 354 */         this.ivjPass2Button.setBounds(100, 100, 300, 30);
/* 355 */         this.ivjPass2Button.setActionCommand("Button2");
/*     */       
/*     */       }
/*     */       catch (Throwable ivjExc) {
/*     */ 
/*     */         
/* 361 */         handleException(ivjExc);
/*     */       } 
/*     */     }
/* 364 */     return this.ivjPass2Button;
/*     */   }
/*     */ 
/*     */   
/*     */   private JPanel getPass2Panel() {
/* 369 */     if (this.ivjPass2Panel == null) {
/*     */       try {
/* 371 */         this.ivjPass2Panel = new JPanel();
/* 372 */         this.ivjPass2Panel.setName("Pass2Panel");
/* 373 */         this.ivjPass2Panel.setLayout(null);
/* 374 */         this.ivjPass2Panel.setBackground(SystemColor.controlShadow);
/* 375 */         this.ivjPass2Panel.setBounds(30, 90, 50, 50);
/*     */       
/*     */       }
/*     */       catch (Throwable ivjExc) {
/*     */ 
/*     */         
/* 381 */         handleException(ivjExc);
/*     */       } 
/*     */     }
/* 384 */     return this.ivjPass2Panel;
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton getPass3Button() {
/* 389 */     if (this.ivjPass3Button == null) {
/*     */       try {
/* 391 */         this.ivjPass3Button = new JButton();
/* 392 */         this.ivjPass3Button.setName("Pass3Button");
/* 393 */         this.ivjPass3Button.setText("Passes 3a+3b: Verify code arrays");
/* 394 */         this.ivjPass3Button.setBackground(SystemColor.controlHighlight);
/* 395 */         this.ivjPass3Button.setBounds(100, 160, 300, 30);
/* 396 */         this.ivjPass3Button.setActionCommand("Button2");
/*     */       
/*     */       }
/*     */       catch (Throwable ivjExc) {
/*     */ 
/*     */         
/* 402 */         handleException(ivjExc);
/*     */       } 
/*     */     }
/* 405 */     return this.ivjPass3Button;
/*     */   }
/*     */ 
/*     */   
/*     */   private JPanel getPass3Panel() {
/* 410 */     if (this.ivjPass3Panel == null) {
/*     */       try {
/* 412 */         this.ivjPass3Panel = new JPanel();
/* 413 */         this.ivjPass3Panel.setName("Pass3Panel");
/* 414 */         this.ivjPass3Panel.setLayout(null);
/* 415 */         this.ivjPass3Panel.setBackground(SystemColor.controlShadow);
/* 416 */         this.ivjPass3Panel.setBounds(30, 150, 50, 50);
/*     */       
/*     */       }
/*     */       catch (Throwable ivjExc) {
/*     */ 
/*     */         
/* 422 */         handleException(ivjExc);
/*     */       } 
/*     */     }
/* 425 */     return this.ivjPass3Panel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleException(Throwable exception) {
/* 432 */     System.out.println("--------- UNCAUGHT EXCEPTION ---------");
/* 433 */     exception.printStackTrace(System.out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initConnections() throws Exception {
/* 441 */     getPass1Button().addActionListener(this.ivjEventHandler);
/* 442 */     getPass2Button().addActionListener(this.ivjEventHandler);
/* 443 */     getPass3Button().addActionListener(this.ivjEventHandler);
/* 444 */     getFlushButton().addActionListener(this.ivjEventHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialize() {
/*     */     try {
/* 452 */       setName("VerifyDialog");
/* 453 */       setDefaultCloseOperation(2);
/* 454 */       setSize(430, 280);
/* 455 */       setVisible(true);
/* 456 */       setModal(true);
/* 457 */       setResizable(false);
/* 458 */       setContentPane(getJDialogContentPane());
/* 459 */       initConnections();
/*     */     } catch (Throwable ivjExc) {
/* 461 */       handleException(ivjExc);
/*     */     } 
/*     */     
/* 464 */     setTitle("'" + this.class_name + "' verification - JustIce / BCEL");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 474 */     classes_to_verify = args.length;
/*     */     
/* 476 */     for (int i = 0; i < args.length; i++) {
/*     */ 
/*     */       
/*     */       try {
/* 480 */         VerifyDialog aVerifyDialog = new VerifyDialog(args[i]);
/* 481 */         aVerifyDialog.setModal(true);
/* 482 */         aVerifyDialog.addWindowListener(new WindowAdapter() {
/*     */               public void windowClosing(WindowEvent e) {
/*     */                 VerifyDialog.classes_to_verify--;
/* 485 */                 if (VerifyDialog.classes_to_verify == 0) System.exit(0); 
/*     */               }
/*     */             });
/* 488 */         aVerifyDialog.setVisible(true);
/*     */       } catch (Throwable exception) {
/* 490 */         System.err.println("Exception occurred in main() of javax.swing.JDialog");
/* 491 */         exception.printStackTrace(System.out);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void pass1Button_ActionPerformed(ActionEvent actionEvent) {
/* 499 */     Verifier v = VerifierFactory.getVerifier(this.class_name);
/* 500 */     VerificationResult vr = v.doPass1();
/* 501 */     if (vr.getStatus() == 1) {
/* 502 */       getPass1Panel().setBackground(Color.green);
/* 503 */       getPass1Panel().repaint();
/*     */     } 
/* 505 */     if (vr.getStatus() == 2) {
/* 506 */       getPass1Panel().setBackground(Color.red);
/* 507 */       getPass1Panel().repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void pass2Button_ActionPerformed(ActionEvent actionEvent) {
/* 513 */     pass1Button_ActionPerformed(actionEvent);
/*     */     
/* 515 */     Verifier v = VerifierFactory.getVerifier(this.class_name);
/* 516 */     VerificationResult vr = v.doPass2();
/* 517 */     if (vr.getStatus() == 1) {
/* 518 */       getPass2Panel().setBackground(Color.green);
/* 519 */       getPass2Panel().repaint();
/*     */     } 
/* 521 */     if (vr.getStatus() == 0) {
/* 522 */       getPass2Panel().setBackground(Color.yellow);
/* 523 */       getPass2Panel().repaint();
/*     */     } 
/* 525 */     if (vr.getStatus() == 2) {
/* 526 */       getPass2Panel().setBackground(Color.red);
/* 527 */       getPass2Panel().repaint();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void pass4Button_ActionPerformed(ActionEvent actionEvent) {
/* 534 */     pass2Button_ActionPerformed(actionEvent);
/*     */ 
/*     */     
/* 537 */     Color color = Color.green;
/*     */     
/* 539 */     Verifier v = VerifierFactory.getVerifier(this.class_name);
/* 540 */     VerificationResult vr = v.doPass2();
/* 541 */     if (vr.getStatus() == 1) {
/* 542 */       JavaClass jc = Repository.lookupClass(this.class_name);
/* 543 */       int nr = (jc.getMethods()).length;
/* 544 */       for (int i = 0; i < nr; i++) {
/* 545 */         vr = v.doPass3b(i);
/* 546 */         if (vr.getStatus() != 1) {
/* 547 */           color = Color.red;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 553 */       color = Color.yellow;
/*     */     } 
/*     */     
/* 556 */     getPass3Panel().setBackground(color);
/* 557 */     getPass3Panel().repaint();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/VerifyDialog.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */