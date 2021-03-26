/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.net.Authenticator;
/*     */ import java.net.InetAddress;
/*     */ import java.net.PasswordAuthentication;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JAuthenticator
/*     */   extends Authenticator
/*     */ {
/*     */   public static final String TITLE = "JAuthenticator.title";
/*     */   public static final String LABEL_SITE = "JAuthenticator.label.site";
/*     */   public static final String LABEL_REQ = "JAuthenticator.label.req";
/*     */   public static final String LABEL_USERID = "JAuthenticator.label.userID";
/*     */   public static final String LABEL_PASSWORD = "JAuthenticator.label.password";
/*     */   public static final String LABEL_CANCEL = "JAuthenticator.label.cancel";
/*     */   public static final String LABEL_OK = "JAuthenticator.label.ok";
/*     */   protected JDialog window;
/*     */   protected JButton cancelButton;
/*     */   protected JButton okButton;
/*     */   protected JLabel label1;
/*     */   protected JLabel label2;
/*     */   protected JTextField JUserID;
/*     */   protected JPasswordField JPassword;
/*  81 */   final Object lock = new Object();
/*     */   
/*     */   private boolean result;
/*     */   
/*     */   private volatile boolean wasNotified;
/*     */   
/*     */   private String userID;
/*     */   private char[] password;
/*     */   ActionListener okListener;
/*     */   ActionListener cancelListener;
/*     */   
/*     */   protected void initWindow() {
/*  93 */     String title = Resources.getString("JAuthenticator.title");
/*  94 */     this.window = new JDialog((Frame)null, title, true);
/*     */     
/*  96 */     Container mainPanel = this.window.getContentPane();
/*  97 */     mainPanel.setLayout(new BorderLayout());
/*  98 */     mainPanel.add(buildAuthPanel(), "Center");
/*  99 */     mainPanel.add(buildButtonPanel(), "South");
/* 100 */     this.window.pack();
/*     */     
/* 102 */     this.window.addWindowListener(new WindowAdapter() {
/*     */           public void windowClosing(WindowEvent e) {
/* 104 */             JAuthenticator.this.cancelListener.actionPerformed(new ActionEvent(e.getWindow(), 1001, "Close"));
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JComponent buildAuthPanel() {
/* 113 */     GridBagLayout gridBag = new GridBagLayout();
/* 114 */     GridBagConstraints c = new GridBagConstraints();
/* 115 */     JPanel proxyPanel = new JPanel(gridBag);
/* 116 */     c.fill = 1;
/* 117 */     c.weightx = 1.0D;
/*     */     
/* 119 */     c.gridwidth = 1;
/* 120 */     JLabel labelS = new JLabel(Resources.getString("JAuthenticator.label.site"));
/* 121 */     labelS.setHorizontalAlignment(2);
/* 122 */     gridBag.setConstraints(labelS, c);
/* 123 */     proxyPanel.add(labelS);
/*     */     
/* 125 */     c.gridwidth = 0;
/* 126 */     this.label1 = new JLabel("");
/* 127 */     this.label1.setHorizontalAlignment(2);
/* 128 */     gridBag.setConstraints(this.label1, c);
/* 129 */     proxyPanel.add(this.label1);
/*     */     
/* 131 */     c.gridwidth = 1;
/* 132 */     JLabel labelR = new JLabel(Resources.getString("JAuthenticator.label.req"));
/* 133 */     labelR.setHorizontalAlignment(2);
/* 134 */     gridBag.setConstraints(labelR, c);
/* 135 */     proxyPanel.add(labelR);
/*     */     
/* 137 */     c.gridwidth = 0;
/* 138 */     this.label2 = new JLabel("");
/* 139 */     this.label2.setHorizontalAlignment(2);
/* 140 */     gridBag.setConstraints(this.label2, c);
/* 141 */     proxyPanel.add(this.label2);
/*     */     
/* 143 */     c.gridwidth = 1;
/* 144 */     JLabel labelUserID = new JLabel(Resources.getString("JAuthenticator.label.userID"));
/* 145 */     labelUserID.setHorizontalAlignment(2);
/* 146 */     gridBag.setConstraints(labelUserID, c);
/* 147 */     proxyPanel.add(labelUserID);
/*     */     
/* 149 */     c.gridwidth = 0;
/* 150 */     this.JUserID = new JTextField(20);
/* 151 */     gridBag.setConstraints(this.JUserID, c);
/* 152 */     proxyPanel.add(this.JUserID);
/*     */     
/* 154 */     c.gridwidth = 1;
/* 155 */     JLabel labelPassword = new JLabel(Resources.getString("JAuthenticator.label.password"));
/* 156 */     labelPassword.setHorizontalAlignment(2);
/* 157 */     gridBag.setConstraints(labelPassword, c);
/* 158 */     proxyPanel.add(labelPassword);
/*     */     
/* 160 */     c.gridwidth = 0;
/* 161 */     this.JPassword = new JPasswordField(20);
/* 162 */     this.JPassword.setEchoChar('*');
/* 163 */     this.JPassword.addActionListener(this.okListener);
/* 164 */     gridBag.setConstraints(this.JPassword, c);
/* 165 */     proxyPanel.add(this.JPassword);
/*     */     
/* 167 */     return proxyPanel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JComponent buildButtonPanel() {
/* 173 */     JPanel buttonPanel = new JPanel();
/* 174 */     this.cancelButton = new JButton(Resources.getString("JAuthenticator.label.cancel"));
/* 175 */     this.cancelButton.addActionListener(this.cancelListener);
/* 176 */     buttonPanel.add(this.cancelButton);
/*     */     
/* 178 */     this.okButton = new JButton(Resources.getString("JAuthenticator.label.ok"));
/* 179 */     this.okButton.addActionListener(this.okListener);
/* 180 */     buttonPanel.add(this.okButton);
/*     */     
/* 182 */     return buttonPanel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PasswordAuthentication getPasswordAuthentication() {
/* 192 */     synchronized (this.lock) {
/* 193 */       EventQueue.invokeLater(new Runnable() {
/*     */             public void run() {
/* 195 */               JAuthenticator.this.label1.setText(JAuthenticator.this.getRequestingSite().getHostName());
/* 196 */               JAuthenticator.this.label2.setText(JAuthenticator.this.getRequestingPrompt());
/* 197 */               JAuthenticator.this.window.setVisible(true);
/*     */             }
/*     */           });
/* 200 */       this.wasNotified = false;
/* 201 */       while (!this.wasNotified) {
/*     */         try {
/* 203 */           this.lock.wait();
/* 204 */         } catch (InterruptedException interruptedException) {}
/*     */       } 
/* 206 */       if (!this.result) {
/* 207 */         return null;
/*     */       }
/* 209 */       return new PasswordAuthentication(this.userID, this.password);
/*     */     } 
/*     */   }
/*     */   public JAuthenticator() {
/* 213 */     this.okListener = new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 215 */           synchronized (JAuthenticator.this.lock) {
/* 216 */             JAuthenticator.this.window.setVisible(false);
/*     */             
/* 218 */             JAuthenticator.this.userID = JAuthenticator.this.JUserID.getText();
/* 219 */             JAuthenticator.this.password = JAuthenticator.this.JPassword.getPassword();
/* 220 */             JAuthenticator.this.JPassword.setText("");
/* 221 */             JAuthenticator.this.result = true;
/* 222 */             JAuthenticator.this.wasNotified = true;
/* 223 */             JAuthenticator.this.lock.notifyAll();
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/* 228 */     this.cancelListener = new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 230 */           synchronized (JAuthenticator.this.lock) {
/* 231 */             JAuthenticator.this.window.setVisible(false);
/*     */             
/* 233 */             JAuthenticator.this.userID = null;
/* 234 */             JAuthenticator.this.JUserID.setText("");
/* 235 */             JAuthenticator.this.password = null;
/* 236 */             JAuthenticator.this.JPassword.setText("");
/* 237 */             JAuthenticator.this.result = false;
/* 238 */             JAuthenticator.this.wasNotified = true;
/* 239 */             JAuthenticator.this.lock.notifyAll();
/*     */           } 
/*     */         }
/*     */       };
/*     */     initWindow();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/JAuthenticator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */