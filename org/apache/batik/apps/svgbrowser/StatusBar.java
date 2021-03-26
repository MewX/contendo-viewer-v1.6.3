/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.BevelBorder;
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
/*     */ public class StatusBar
/*     */   extends JPanel
/*     */ {
/*     */   protected static final String RESOURCES = "org.apache.batik.apps.svgbrowser.resources.StatusBarMessages";
/*  57 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.apps.svgbrowser.resources.StatusBarMessages", Locale.getDefault());
/*  58 */   protected static ResourceManager rManager = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JLabel xPosition;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JLabel yPosition;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JLabel zoom;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JLabel message;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String mainMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String temporaryMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DisplayThread displayThread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StatusBar() {
/* 100 */     super(new BorderLayout(5, 5));
/*     */     
/* 102 */     JPanel p = new JPanel(new BorderLayout(0, 0));
/* 103 */     add("West", p);
/*     */     
/* 105 */     this.xPosition = new JLabel();
/*     */     
/* 107 */     BevelBorder bb = new BevelBorder(1, getBackground().brighter().brighter(), getBackground(), getBackground().darker().darker(), getBackground());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     this.xPosition.setBorder(bb);
/* 113 */     this.xPosition.setPreferredSize(new Dimension(110, 16));
/* 114 */     p.add("West", this.xPosition);
/*     */     
/* 116 */     this.yPosition = new JLabel();
/* 117 */     this.yPosition.setBorder(bb);
/* 118 */     this.yPosition.setPreferredSize(new Dimension(110, 16));
/* 119 */     p.add("Center", this.yPosition);
/*     */     
/* 121 */     this.zoom = new JLabel();
/* 122 */     this.zoom.setBorder(bb);
/* 123 */     this.zoom.setPreferredSize(new Dimension(70, 16));
/* 124 */     p.add("East", this.zoom);
/*     */     
/* 126 */     p = new JPanel(new BorderLayout(0, 0));
/* 127 */     this.message = new JLabel();
/* 128 */     this.message.setBorder(bb);
/* 129 */     p.add(this.message);
/* 130 */     add(p);
/* 131 */     setMainMessage(rManager.getString("Panel.default_message"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXPosition(float x) {
/* 138 */     this.xPosition.setText("x: " + x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidth(float w) {
/* 145 */     this.xPosition.setText(rManager.getString("Position.width_letters") + " " + w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYPosition(float y) {
/* 153 */     this.yPosition.setText("y: " + y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeight(float h) {
/* 160 */     this.yPosition.setText(rManager.getString("Position.height_letters") + " " + h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZoom(float f) {
/* 168 */     f = (f > 0.0F) ? f : -f;
/* 169 */     if (f == 1.0F) {
/* 170 */       this.zoom.setText("1:1");
/* 171 */     } else if (f >= 1.0F) {
/* 172 */       String s = Float.toString(f);
/* 173 */       if (s.length() > 6) {
/* 174 */         s = s.substring(0, 6);
/*     */       }
/* 176 */       this.zoom.setText("1:" + s);
/*     */     } else {
/* 178 */       String s = Float.toString(1.0F / f);
/* 179 */       if (s.length() > 6) {
/* 180 */         s = s.substring(0, 6);
/*     */       }
/* 182 */       this.zoom.setText(s + ":1");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessage(String s) {
/* 191 */     setPreferredSize(new Dimension(0, (getPreferredSize()).height));
/* 192 */     if (this.displayThread != null) {
/* 193 */       this.displayThread.finish();
/*     */     }
/* 195 */     this.temporaryMessage = s;
/* 196 */     Thread old = this.displayThread;
/* 197 */     this.displayThread = new DisplayThread(old);
/* 198 */     this.displayThread.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMainMessage(String s) {
/* 206 */     this.mainMessage = s;
/* 207 */     this.message.setText(this.mainMessage = s);
/* 208 */     if (this.displayThread != null) {
/* 209 */       this.displayThread.finish();
/* 210 */       this.displayThread = null;
/*     */     } 
/* 212 */     setPreferredSize(new Dimension(0, (getPreferredSize()).height));
/*     */   }
/*     */   
/*     */   protected class DisplayThread
/*     */     extends Thread
/*     */   {
/*     */     static final long DEFAULT_DURATION = 5000L;
/*     */     long duration;
/*     */     Thread toJoin;
/*     */     
/*     */     public DisplayThread() {
/* 223 */       this(5000L, (Thread)null);
/*     */     }
/*     */     public DisplayThread(long duration) {
/* 226 */       this(duration, (Thread)null);
/*     */     }
/*     */     public DisplayThread(Thread toJoin) {
/* 229 */       this(5000L, toJoin);
/*     */     }
/*     */     public DisplayThread(long duration, Thread toJoin) {
/* 232 */       this.duration = duration;
/* 233 */       this.toJoin = toJoin;
/* 234 */       setPriority(1);
/*     */     }
/*     */     
/*     */     public synchronized void finish() {
/* 238 */       this.duration = 0L;
/* 239 */       notifyAll();
/*     */     }
/*     */     
/*     */     public void run() {
/* 243 */       synchronized (this) {
/* 244 */         if (this.toJoin != null) {
/* 245 */           while (this.toJoin.isAlive()) { try {
/* 246 */               this.toJoin.join();
/* 247 */             } catch (InterruptedException interruptedException) {} }
/*     */           
/* 249 */           this.toJoin = null;
/*     */         } 
/*     */         
/* 252 */         StatusBar.this.message.setText(StatusBar.this.temporaryMessage);
/*     */         
/* 254 */         long lTime = System.currentTimeMillis();
/*     */         
/* 256 */         while (this.duration > 0L) {
/*     */           try {
/* 258 */             wait(this.duration);
/* 259 */           } catch (InterruptedException interruptedException) {}
/* 260 */           long cTime = System.currentTimeMillis();
/* 261 */           this.duration -= cTime - lTime;
/* 262 */           lTime = cTime;
/*     */         } 
/* 264 */         StatusBar.this.message.setText(StatusBar.this.mainMessage);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/StatusBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */