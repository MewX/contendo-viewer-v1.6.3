/*     */ package org.apache.regexp;
/*     */ 
/*     */ import java.applet.Applet;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Label;
/*     */ import java.awt.TextArea;
/*     */ import java.awt.TextField;
/*     */ import java.awt.event.TextEvent;
/*     */ import java.awt.event.TextListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.CharArrayWriter;
/*     */ import java.io.PrintWriter;
/*     */ import javax.swing.JFrame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class REDemo
/*     */   extends Applet
/*     */   implements TextListener
/*     */ {
/*  76 */   RE r = new RE();
/*  77 */   REDebugCompiler compiler = new REDebugCompiler();
/*     */ 
/*     */   
/*     */   TextField fieldRE;
/*     */ 
/*     */   
/*     */   TextField fieldMatch;
/*     */ 
/*     */   
/*     */   TextArea outRE;
/*     */ 
/*     */   
/*     */   TextArea outMatch;
/*     */ 
/*     */   
/*     */   public void init() {
/*  93 */     GridBagLayout gridBagLayout = new GridBagLayout();
/*  94 */     setLayout(gridBagLayout);
/*  95 */     GridBagConstraints gridBagConstraints = new GridBagConstraints();
/*  96 */     gridBagConstraints.insets = new Insets(5, 5, 5, 5);
/*  97 */     gridBagConstraints.anchor = 13;
/*  98 */     gridBagLayout.setConstraints(add(new Label("Regular expression:", 2)), gridBagConstraints);
/*  99 */     gridBagConstraints.gridy = 0;
/* 100 */     gridBagConstraints.anchor = 17;
/* 101 */     gridBagLayout.setConstraints(add(this.fieldRE = new TextField("\\[([:javastart:][:javapart:]*)\\]", 40)), gridBagConstraints);
/* 102 */     gridBagConstraints.gridx = 0;
/* 103 */     gridBagConstraints.gridy = -1;
/* 104 */     gridBagConstraints.anchor = 13;
/* 105 */     gridBagLayout.setConstraints(add(new Label("String:", 2)), gridBagConstraints);
/* 106 */     gridBagConstraints.gridy = 1;
/* 107 */     gridBagConstraints.gridx = -1;
/* 108 */     gridBagConstraints.anchor = 17;
/* 109 */     gridBagLayout.setConstraints(add(this.fieldMatch = new TextField("aaa([foo])aaa", 40)), gridBagConstraints);
/* 110 */     gridBagConstraints.gridy = 2;
/* 111 */     gridBagConstraints.gridx = -1;
/* 112 */     gridBagConstraints.fill = 1;
/* 113 */     gridBagConstraints.weighty = 1.0D;
/* 114 */     gridBagConstraints.weightx = 1.0D;
/* 115 */     gridBagLayout.setConstraints(add(this.outRE = new TextArea()), gridBagConstraints);
/* 116 */     gridBagConstraints.gridy = 2;
/* 117 */     gridBagConstraints.gridx = -1;
/* 118 */     gridBagLayout.setConstraints(add(this.outMatch = new TextArea()), gridBagConstraints);
/*     */ 
/*     */     
/* 121 */     this.fieldRE.addTextListener(this);
/* 122 */     this.fieldMatch.addTextListener(this);
/*     */ 
/*     */     
/* 125 */     textValueChanged(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void sayRE(String paramString) {
/* 134 */     this.outRE.setText(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void sayMatch(String paramString) {
/* 143 */     this.outMatch.setText(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String throwableToString(Throwable paramThrowable) {
/* 152 */     String str1 = paramThrowable.getClass().getName();
/*     */     String str2;
/* 154 */     if ((str2 = paramThrowable.getMessage()) != null)
/*     */     {
/* 156 */       str1 = String.valueOf(str1) + "\n" + str2;
/*     */     }
/* 158 */     return str1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateRE(String paramString) {
/*     */     try {
/* 170 */       this.r.setProgram(this.compiler.compile(paramString));
/*     */ 
/*     */       
/* 173 */       CharArrayWriter charArrayWriter = new CharArrayWriter();
/* 174 */       this.compiler.dumpProgram(new PrintWriter(charArrayWriter));
/* 175 */       sayRE(charArrayWriter.toString());
/* 176 */       System.out.println(charArrayWriter);
/*     */     }
/* 178 */     catch (Exception exception) {
/*     */       
/* 180 */       this.r.setProgram(null);
/* 181 */       sayRE(throwableToString(exception));
/*     */     }
/* 183 */     catch (Throwable throwable) {
/*     */       
/* 185 */       this.r.setProgram(null);
/* 186 */       sayRE(throwableToString(throwable));
/*     */     } 
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
/*     */   void updateMatch(String paramString) {
/*     */     try {
/* 200 */       if (this.r.match(paramString))
/*     */       {
/*     */         
/* 203 */         String str = "Matches.\n\n";
/*     */ 
/*     */         
/* 206 */         for (byte b = 0; b < this.r.getParenCount(); b++)
/*     */         {
/* 208 */           str = String.valueOf(str) + "$" + b + " = " + this.r.getParen(b) + "\n";
/*     */         }
/* 210 */         sayMatch(str);
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 215 */         sayMatch("Does not match");
/*     */       }
/*     */     
/* 218 */     } catch (Throwable throwable) {
/*     */       
/* 220 */       sayMatch(throwableToString(throwable));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void textValueChanged(TextEvent paramTextEvent) {
/* 231 */     if (paramTextEvent == null || paramTextEvent.getSource() == this.fieldRE)
/*     */     {
/*     */       
/* 234 */       updateRE(this.fieldRE.getText());
/*     */     }
/*     */ 
/*     */     
/* 238 */     updateMatch(this.fieldMatch.getText());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] paramArrayOfString) {
/* 247 */     JFrame jFrame = new JFrame("RE Demo");
/*     */     
/* 249 */     jFrame.addWindowListener(new WindowAdapter()
/*     */         {
/*     */           public void windowClosing(WindowEvent param1WindowEvent)
/*     */           {
/* 253 */             System.exit(0);
/*     */           }
/*     */         });
/* 256 */     Container container = jFrame.getContentPane();
/* 257 */     container.setLayout(new FlowLayout());
/* 258 */     REDemo rEDemo = new REDemo();
/* 259 */     container.add(rEDemo);
/* 260 */     rEDemo.init();
/* 261 */     jFrame.pack();
/* 262 */     jFrame.setVisible(true);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/REDemo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */