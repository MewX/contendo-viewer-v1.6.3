/*     */ package org.apache.batik.apps.slideshow;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JWindow;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.DocumentLoader;
/*     */ import org.apache.batik.bridge.GVTBuilder;
/*     */ import org.apache.batik.bridge.UserAgent;
/*     */ import org.apache.batik.bridge.UserAgentAdapter;
/*     */ import org.apache.batik.bridge.ViewBox;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.renderer.StaticRenderer;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ import org.w3c.dom.svg.SVGSVGElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Main
/*     */   extends JComponent
/*     */ {
/*     */   StaticRenderer renderer;
/*     */   UserAgent userAgent;
/*     */   DocumentLoader loader;
/*     */   BridgeContext ctx;
/*     */   BufferedImage image;
/*     */   BufferedImage display;
/*     */   File[] files;
/*  72 */   static int duration = 3000;
/*  73 */   static int frameDelay = duration + 7000;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   volatile boolean done = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   volatile Thread transitionThread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long startLastTransition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   volatile boolean paused;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class RenderThread
/*     */     extends Thread
/*     */   {
/*     */     RenderThread() {
/* 121 */       super("RenderThread");
/* 122 */       setDaemon(true);
/*     */     }
/*     */     
/*     */     public void run() {
/* 126 */       Main.this.renderer.setDoubleBuffered(true);
/* 127 */       for (int i = 0; i < Main.this.files.length; i++) {
/* 128 */         GraphicsNode gvtRoot = null;
/* 129 */         GVTBuilder builder = new GVTBuilder();
/*     */         
/*     */         try {
/* 132 */           String fileName = Main.this.files[i].toURI().toURL().toString();
/* 133 */           System.out.println("Reading: " + fileName);
/* 134 */           Document svgDoc = Main.this.loader.loadDocument(fileName);
/* 135 */           System.out.println("Building: " + fileName);
/* 136 */           gvtRoot = builder.build(Main.this.ctx, svgDoc);
/* 137 */           System.out.println("Rendering: " + fileName);
/* 138 */           Main.this.renderer.setTree(gvtRoot);
/*     */           
/* 140 */           SVGSVGElement sVGSVGElement = ((SVGDocument)svgDoc).getRootElement();
/* 141 */           Main.this.renderer.setTransform(ViewBox.getViewTransform(null, (Element)sVGSVGElement, Main.this.display.getWidth(), Main.this.display.getHeight(), Main.this.ctx));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 146 */           Main.this.renderer.updateOffScreen(Main.this.display.getWidth(), Main.this.display.getHeight());
/*     */ 
/*     */           
/* 149 */           Rectangle r = new Rectangle(0, 0, Main.this.display.getWidth(), Main.this.display.getHeight());
/*     */ 
/*     */           
/* 152 */           Main.this.renderer.repaint(r);
/* 153 */           System.out.println("Painting: " + fileName);
/* 154 */           Main.this.image = Main.this.renderer.getOffScreen();
/* 155 */           Main.this.setTransition(Main.this.image);
/*     */         }
/* 157 */         catch (Exception ex) {
/* 158 */           ex.printStackTrace();
/*     */         } 
/*     */       } 
/* 161 */       if (Main.this.transitionThread != null) {
/*     */         try {
/* 163 */           Main.this.transitionThread.join();
/* 164 */         } catch (InterruptedException interruptedException) {}
/* 165 */         Main.this.done = true;
/* 166 */         Main.this.setCursor(new Cursor(3));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public Main(File[] files, Dimension size) {
/* 172 */     this.transitionThread = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     this.startLastTransition = 0L;
/*     */     
/* 189 */     this.paused = false; setBackground(Color.black); this.files = files; UserAgentAdapter ua = new UserAgentAdapter(); this.renderer = new StaticRenderer(); this.userAgent = (UserAgent)ua; this.loader = new DocumentLoader(this.userAgent); this.ctx = new BridgeContext(this.userAgent, this.loader); ua.setBridgeContext(this.ctx); if (size == null)
/*     */       size = Toolkit.getDefaultToolkit().getScreenSize();  setPreferredSize(size); setDoubleBuffered(false); addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent me) { if (Main.this.done) { System.exit(0); } else { Main.this.togglePause(); }
/*     */              } }); size.width += 2; size.height += 2; this.display = new BufferedImage(size.width, size.height, 4); Thread t = new RenderThread(); t.start(); JWindow w = new JWindow(); w.setBackground(Color.black); w.getContentPane().setBackground(Color.black); w.getContentPane().add(this); w.pack(); w.setLocation(new Point(-1, -1)); w.setVisible(true);
/* 192 */   } public void togglePause() { synchronized (this) {
/* 193 */       Cursor c; this.paused = !this.paused;
/*     */       
/* 195 */       if (this.paused) {
/* 196 */         c = new Cursor(3);
/*     */       } else {
/* 198 */         c = new Cursor(0);
/* 199 */         if (this.transitionThread != null) {
/* 200 */           synchronized (this.transitionThread) {
/* 201 */             this.transitionThread.notifyAll();
/*     */           } 
/*     */         }
/*     */       } 
/* 205 */       setCursor(c);
/*     */     }  } public void setTransition(BufferedImage newImg) { synchronized (this) { while (this.transitionThread != null) { try {
/*     */           wait();
/*     */         } catch (InterruptedException interruptedException) {} }
/*     */        this.transitionThread = new TransitionThread(newImg); this.transitionThread.start(); }
/*     */      } class TransitionThread extends Thread {
/* 211 */     BufferedImage src; int blockw = 75;
/* 212 */     int blockh = 75;
/*     */     
/*     */     public TransitionThread(BufferedImage bi) {
/* 215 */       super("TransitionThread");
/* 216 */       setDaemon(true);
/* 217 */       this.src = bi;
/*     */     }
/*     */     
/*     */     public void run() {
/* 221 */       int xblocks = (Main.this.display.getWidth() + this.blockw - 1) / this.blockw;
/* 222 */       int yblocks = (Main.this.display.getHeight() + this.blockh - 1) / this.blockh;
/* 223 */       int nblocks = xblocks * yblocks;
/*     */       
/* 225 */       int tblock = Main.duration / nblocks;
/*     */       
/* 227 */       Point[] rects = new Point[nblocks];
/* 228 */       for (int y = 0; y < yblocks; y++) {
/* 229 */         for (int x = 0; x < xblocks; x++)
/* 230 */           rects[y * xblocks + x] = new Point(x, y); 
/*     */       } 
/* 232 */       Graphics2D g2d = Main.this.display.createGraphics();
/* 233 */       g2d.setColor(Color.black);
/*     */       
/* 235 */       long currTrans = System.currentTimeMillis();
/* 236 */       while (currTrans - Main.this.startLastTransition < Main.frameDelay) {
/*     */         try {
/* 238 */           long stime = Main.frameDelay - currTrans - Main.this.startLastTransition;
/* 239 */           if (stime > 500L) {
/* 240 */             System.gc();
/* 241 */             currTrans = System.currentTimeMillis();
/* 242 */             stime = Main.frameDelay - currTrans - Main.this.startLastTransition;
/*     */           } 
/* 244 */           if (stime > 0L) sleep(stime); 
/* 245 */         } catch (InterruptedException interruptedException) {}
/* 246 */         currTrans = System.currentTimeMillis();
/*     */       } 
/*     */       
/* 249 */       synchronized (this) {
/* 250 */         while (Main.this.paused) {
/*     */           try {
/* 252 */             wait();
/* 253 */           } catch (InterruptedException interruptedException) {}
/*     */         } 
/*     */       } 
/*     */       
/* 257 */       long last = Main.this.startLastTransition = System.currentTimeMillis();
/*     */       
/* 259 */       for (int i = 0; i < rects.length; i++) {
/* 260 */         int idx = (int)(Math.random() * (rects.length - i));
/* 261 */         Point pt = rects[idx];
/* 262 */         System.arraycopy(rects, idx + 1, rects, idx + 1 - 1, rects.length - i - idx - 1);
/* 263 */         int x = pt.x * this.blockw, j = pt.y * this.blockh;
/* 264 */         int w = this.blockw, h = this.blockh;
/* 265 */         if (x + w > this.src.getWidth()) w = this.src.getWidth() - x; 
/* 266 */         if (j + h > this.src.getHeight()) h = this.src.getHeight() - j;
/*     */         
/* 268 */         synchronized (Main.this.display) {
/* 269 */           g2d.fillRect(x, j, w, h);
/*     */ 
/*     */           
/* 272 */           BufferedImage sub = this.src.getSubimage(x, j, w, h);
/* 273 */           g2d.drawImage(sub, (BufferedImageOp)null, x, j);
/*     */         } 
/*     */         
/* 276 */         Main.this.repaint(x, j, w, h);
/* 277 */         long current = System.currentTimeMillis();
/*     */         try {
/* 279 */           long dt = current - last;
/* 280 */           if (dt < tblock)
/* 281 */             sleep(tblock - dt); 
/* 282 */         } catch (InterruptedException interruptedException) {}
/* 283 */         last = current;
/*     */       } 
/*     */       
/* 286 */       synchronized (Main.this) {
/* 287 */         Main.this.transitionThread = null;
/* 288 */         Main.this.notifyAll();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 295 */     Graphics2D g2d = (Graphics2D)g;
/* 296 */     if (this.display == null)
/*     */       return; 
/* 298 */     g2d.drawImage(this.display, (BufferedImageOp)null, 0, 0);
/*     */   }
/*     */   
/*     */   public static void readFileList(String file, List<String> fileVec) {
/*     */     BufferedReader br;
/*     */     try {
/* 304 */       br = new BufferedReader(new FileReader(file));
/* 305 */     } catch (FileNotFoundException fnfe) {
/* 306 */       System.err.println("Unable to open file-list: " + file);
/*     */       return;
/*     */     } 
/*     */     
/* 310 */     try { URL flURL = (new File(file)).toURI().toURL();
/*     */       String line;
/* 312 */       while ((line = br.readLine()) != null) {
/* 313 */         String str = line;
/* 314 */         int idx = str.indexOf('#');
/* 315 */         if (idx != -1)
/* 316 */           str = str.substring(0, idx); 
/* 317 */         str = str.trim();
/* 318 */         if (str.length() == 0)
/*     */           continue; 
/*     */         try {
/* 321 */           URL imgURL = new URL(flURL, str);
/* 322 */           fileVec.add(imgURL.getFile());
/* 323 */         } catch (MalformedURLException mue) {
/* 324 */           System.err.println("Can't make sense of line:\n  " + line);
/*     */         } 
/*     */       }  }
/* 327 */     catch (IOException ioe)
/* 328 */     { System.err.println("Error while reading file-list: " + file); }
/*     */     finally { 
/* 330 */       try { br.close(); } catch (IOException iOException) {} }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 336 */     List<String> fileVec = new ArrayList();
/*     */     
/* 338 */     Dimension d = null;
/*     */     
/* 340 */     if (args.length == 0) {
/* 341 */       showUsage();
/*     */       
/*     */       return;
/*     */     } 
/* 345 */     for (int i = 0; i < args.length; i++) {
/* 346 */       if (args[i].equals("-h") || args[i].equals("-help") || args[i].equals("--help")) {
/*     */ 
/*     */         
/* 349 */         showUsage(); return;
/*     */       } 
/* 351 */       if (args[i].equals("--")) {
/* 352 */         i++;
/* 353 */         while (i < args.length)
/* 354 */           fileVec.add(args[i++]); 
/*     */         break;
/*     */       } 
/* 357 */       if (args[i].equals("-fl") || args[i].equals("--file-list")) {
/*     */         
/* 359 */         if (i + 1 == args.length) {
/* 360 */           System.err.println("Must provide name of file list file after " + args[i]);
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 365 */         readFileList(args[i + 1], fileVec);
/* 366 */         i++;
/* 367 */       } else if (args[i].equals("-ft") || args[i].equals("--frame-time")) {
/*     */         
/* 369 */         if (i + 1 == args.length) {
/* 370 */           System.err.println("Must provide time in millis after " + args[i]);
/*     */           
/*     */           break;
/*     */         } 
/*     */         try {
/* 375 */           frameDelay = Integer.decode(args[i + 1]).intValue();
/* 376 */           i++;
/* 377 */         } catch (NumberFormatException nfe) {
/* 378 */           System.err.println("Can't parse frame time: " + args[i + 1]);
/*     */         }
/*     */       
/* 381 */       } else if (args[i].equals("-tt") || args[i].equals("--transition-time")) {
/*     */         
/* 383 */         if (i + 1 == args.length) {
/* 384 */           System.err.println("Must provide time in millis after " + args[i]);
/*     */           
/*     */           break;
/*     */         } 
/*     */         try {
/* 389 */           duration = Integer.decode(args[i + 1]).intValue();
/* 390 */           i++;
/* 391 */         } catch (NumberFormatException nfe) {
/* 392 */           System.err.println("Can't parse transition time: " + args[i + 1]);
/*     */         }
/*     */       
/* 395 */       } else if (args[i].equals("-ws") || args[i].equals("--window-size")) {
/*     */ 
/*     */         
/* 398 */         if (i + 1 == args.length) {
/* 399 */           System.err.println("Must provide window size [w,h] after " + args[i]);
/*     */           
/*     */           break;
/*     */         } 
/*     */         try {
/* 404 */           int w, h, idx = args[i + 1].indexOf(',');
/*     */           
/* 406 */           if (idx == -1) {
/* 407 */             w = h = Integer.decode(args[i + 1]).intValue();
/*     */           } else {
/* 409 */             String wStr = args[i + 1].substring(0, idx);
/* 410 */             String hStr = args[i + 1].substring(idx + 1);
/* 411 */             w = Integer.decode(wStr).intValue();
/* 412 */             h = Integer.decode(hStr).intValue();
/*     */           } 
/* 414 */           d = new Dimension(w, h);
/* 415 */           i++;
/* 416 */         } catch (NumberFormatException nfe) {
/* 417 */           System.err.println("Can't parse window size: " + args[i + 1]);
/*     */         } 
/*     */       } else {
/*     */         
/* 421 */         fileVec.add(args[i]);
/*     */       } 
/*     */     } 
/* 424 */     File[] files = new File[fileVec.size()];
/*     */ 
/*     */     
/* 427 */     for (int j = 0; j < fileVec.size(); j++) {
/*     */       try {
/* 429 */         files[j] = new File(fileVec.get(j));
/* 430 */       } catch (Exception ex) {
/* 431 */         ex.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 435 */     new Main(files, d);
/*     */   }
/*     */   
/*     */   public static void showUsage() {
/* 439 */     System.out.println("Options:\n                                 -- : Remaining args are file names\n                         -fl <file>\n                 --file-list <file> : file contains list of images to\n                                      show one per line\n             -ws <width>[,<height>]\n    -window-size <width>[,<height>] : Set the size of slideshow window\n                                      defaults to full screen\n                          -ft <int>\n                 --frame-time <int> : Amount of time in millisecs to\n                                      show each frame.\n                                      Includes transition time.\n                          -tt <int>\n            --transition-time <int> : Amount of time in millisecs to\n                                      transition between frames.\n                             <file> : SVG file to display");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/slideshow/Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */