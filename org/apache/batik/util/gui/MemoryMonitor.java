/*     */ package org.apache.batik.util.gui;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
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
/*     */ public class MemoryMonitor
/*     */   extends JFrame
/*     */   implements ActionMap
/*     */ {
/*     */   protected static final String RESOURCE = "org.apache.batik.util.gui.resources.MemoryMonitorMessages";
/*  89 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.util.gui.resources.MemoryMonitorMessages", Locale.getDefault());
/*  90 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   protected Map listeners = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Panel panel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MemoryMonitor() {
/* 108 */     this(1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MemoryMonitor(long time) {
/* 116 */     super(resources.getString("Frame.title"));
/* 117 */     this.listeners.put("CollectButtonAction", new CollectButtonAction());
/* 118 */     this.listeners.put("CloseButtonAction", new CloseButtonAction());
/*     */     
/* 120 */     this.panel = new Panel(time);
/*     */     
/* 122 */     getContentPane().add(this.panel);
/* 123 */     this.panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), resources.getString("Frame.border_title")));
/*     */ 
/*     */ 
/*     */     
/* 127 */     JPanel p = new JPanel(new FlowLayout(2));
/* 128 */     ButtonFactory bf = new ButtonFactory(bundle, this);
/* 129 */     p.add(bf.createJButton("CollectButton"));
/* 130 */     p.add(bf.createJButton("CloseButton"));
/* 131 */     getContentPane().add(p, "South");
/*     */     
/* 133 */     pack();
/*     */     
/* 135 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowActivated(WindowEvent e) {
/* 137 */             MemoryMonitor.RepaintThread t = MemoryMonitor.this.panel.getRepaintThread();
/* 138 */             if (!t.isAlive()) {
/* 139 */               t.start();
/*     */             } else {
/* 141 */               t.safeResume();
/*     */             } 
/*     */           }
/*     */           public void windowClosing(WindowEvent ev) {
/* 145 */             MemoryMonitor.this.panel.getRepaintThread().safeSuspend();
/*     */           }
/*     */           public void windowDeiconified(WindowEvent e) {
/* 148 */             MemoryMonitor.this.panel.getRepaintThread().safeResume();
/*     */           }
/*     */           public void windowIconified(WindowEvent e) {
/* 151 */             MemoryMonitor.this.panel.getRepaintThread().safeSuspend();
/*     */           }
/*     */         });
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
/*     */   public Action getAction(String key) throws MissingListenerException {
/* 165 */     return (Action)this.listeners.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected class CollectButtonAction
/*     */     extends AbstractAction
/*     */   {
/*     */     public void actionPerformed(ActionEvent e) {
/* 173 */       System.gc();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class CloseButtonAction
/*     */     extends AbstractAction
/*     */   {
/*     */     public void actionPerformed(ActionEvent e) {
/* 182 */       MemoryMonitor.this.panel.getRepaintThread().safeSuspend();
/* 183 */       MemoryMonitor.this.dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Panel
/*     */     extends JPanel
/*     */   {
/*     */     protected MemoryMonitor.RepaintThread repaintThread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Panel() {
/* 202 */       this(1000L);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Panel(long time) {
/* 212 */       super(new GridBagLayout());
/*     */       
/* 214 */       ExtendedGridBagConstraints constraints = new ExtendedGridBagConstraints();
/*     */       
/* 216 */       constraints.insets = new Insets(5, 5, 5, 5);
/*     */       
/* 218 */       List<JComponent> l = new ArrayList();
/* 219 */       JPanel p = new JPanel(new BorderLayout());
/* 220 */       p.setBorder(BorderFactory.createLoweredBevelBorder());
/* 221 */       JComponent c = new MemoryMonitor.Usage();
/* 222 */       p.add(c);
/* 223 */       constraints.weightx = 0.3D;
/* 224 */       constraints.weighty = 1.0D;
/* 225 */       constraints.fill = 1;
/* 226 */       constraints.setGridBounds(0, 0, 1, 1);
/* 227 */       add(p, constraints);
/* 228 */       l.add(c);
/*     */       
/* 230 */       p = new JPanel(new BorderLayout());
/* 231 */       p.setBorder(BorderFactory.createLoweredBevelBorder());
/* 232 */       c = new MemoryMonitor.History();
/* 233 */       p.add(c);
/* 234 */       constraints.weightx = 0.7D;
/* 235 */       constraints.setGridBounds(1, 0, 1, 1);
/* 236 */       add(p, constraints);
/* 237 */       l.add(c);
/*     */       
/* 239 */       this.repaintThread = new MemoryMonitor.RepaintThread(time, l);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MemoryMonitor.RepaintThread getRepaintThread() {
/* 246 */       return this.repaintThread;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Usage
/*     */     extends JPanel
/*     */     implements MemoryChangeListener
/*     */   {
/*     */     public static final int PREFERRED_WIDTH = 90;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int PREFERRED_HEIGHT = 100;
/*     */ 
/*     */ 
/*     */     
/* 267 */     protected static final String UNITS = MemoryMonitor.resources.getString("Usage.units");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     protected static final String TOTAL = MemoryMonitor.resources.getString("Usage.total");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     protected static final String USED = MemoryMonitor.resources.getString("Usage.used");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 285 */     protected static final boolean POSTFIX = MemoryMonitor.resources.getBoolean("Usage.postfix");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static final int FONT_SIZE = 9;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static final int BLOCK_MARGIN = 10;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static final int BLOCKS = 15;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static final double BLOCK_WIDTH = 70.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static final double BLOCK_HEIGHT = 3.8666666666666667D;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     protected static final int[] BLOCK_TYPE = new int[] { 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     protected Color[] usedColors = new Color[] { Color.red, new Color(255, 165, 0), Color.green };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 333 */     protected Color[] freeColors = new Color[] { new Color(130, 0, 0), new Color(130, 90, 0), new Color(0, 130, 0) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 342 */     protected Font font = new Font("SansSerif", 1, 9);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 347 */     protected Color textColor = Color.green;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected long totalMemory;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected long freeMemory;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Usage() {
/* 363 */       setBackground(Color.black);
/* 364 */       setPreferredSize(new Dimension(90, 100));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void memoryStateChanged(long total, long free) {
/* 373 */       this.totalMemory = total;
/* 374 */       this.freeMemory = free;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setTextColor(Color c) {
/* 381 */       this.textColor = c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setLowUsedMemoryColor(Color c) {
/* 388 */       this.usedColors[2] = c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setMediumUsedMemoryColor(Color c) {
/* 395 */       this.usedColors[1] = c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setHighUsedMemoryColor(Color c) {
/* 402 */       this.usedColors[0] = c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setLowFreeMemoryColor(Color c) {
/* 409 */       this.freeColors[2] = c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setMediumFreeMemoryColor(Color c) {
/* 416 */       this.freeColors[1] = c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setHighFreeMemoryColor(Color c) {
/* 423 */       this.freeColors[0] = c;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void paintComponent(Graphics g) {
/*     */       String totalText, usedText;
/* 430 */       super.paintComponent(g);
/* 431 */       Graphics2D g2d = (Graphics2D)g;
/*     */ 
/*     */       
/* 434 */       Dimension dim = getSize();
/* 435 */       double sx = dim.width / 90.0D;
/* 436 */       double sy = dim.height / 100.0D;
/* 437 */       g2d.transform(AffineTransform.getScaleInstance(sx, sy));
/*     */ 
/*     */       
/* 440 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */ 
/*     */ 
/*     */       
/* 444 */       int nfree = (int)Math.round(15.0D * this.freeMemory / this.totalMemory);
/*     */       
/*     */       int i;
/* 447 */       for (i = 0; i < nfree; i++) {
/* 448 */         Rectangle2D rect = new Rectangle2D.Double(10.0D, i * 3.8666666666666667D + i + 9.0D + 5.0D, 70.0D, 3.8666666666666667D);
/*     */ 
/*     */ 
/*     */         
/* 452 */         g2d.setPaint(this.freeColors[BLOCK_TYPE[i]]);
/* 453 */         g2d.fill(rect);
/*     */       } 
/*     */       
/* 456 */       for (i = nfree; i < 15; i++) {
/* 457 */         Rectangle2D rect = new Rectangle2D.Double(10.0D, i * 3.8666666666666667D + i + 9.0D + 5.0D, 70.0D, 3.8666666666666667D);
/*     */ 
/*     */ 
/*     */         
/* 461 */         g2d.setPaint(this.usedColors[BLOCK_TYPE[i]]);
/* 462 */         g2d.fill(rect);
/*     */       } 
/*     */ 
/*     */       
/* 466 */       g2d.setPaint(this.textColor);
/* 467 */       g2d.setFont(this.font);
/*     */       
/* 469 */       long total = this.totalMemory / 1024L;
/* 470 */       long used = (this.totalMemory - this.freeMemory) / 1024L;
/*     */ 
/*     */       
/* 473 */       if (POSTFIX) {
/* 474 */         totalText = total + UNITS + " " + TOTAL;
/* 475 */         usedText = used + UNITS + " " + USED;
/*     */       } else {
/* 477 */         totalText = TOTAL + " " + total + UNITS;
/* 478 */         usedText = USED + " " + used + UNITS;
/*     */       } 
/*     */       
/* 481 */       g2d.drawString(totalText, 10, 10);
/* 482 */       g2d.drawString(usedText, 10, 97);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class History
/*     */     extends JPanel
/*     */     implements MemoryChangeListener
/*     */   {
/*     */     public static final int PREFERRED_WIDTH = 200;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int PREFERRED_HEIGHT = 100;
/*     */ 
/*     */ 
/*     */     
/* 503 */     protected static final Stroke GRID_LINES_STROKE = new BasicStroke(1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 508 */     protected static final Stroke CURVE_STROKE = new BasicStroke(2.0F, 1, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 514 */     protected static final Stroke BORDER_STROKE = new BasicStroke(2.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 519 */     protected Color gridLinesColor = new Color(0, 130, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 524 */     protected Color curveColor = Color.yellow;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 529 */     protected Color borderColor = Color.green;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 534 */     protected List data = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 539 */     protected int xShift = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected long totalMemory;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected long freeMemory;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 554 */     protected GeneralPath path = new GeneralPath();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public History() {
/* 560 */       setBackground(Color.black);
/* 561 */       setPreferredSize(new Dimension(200, 100));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void memoryStateChanged(long total, long free) {
/* 570 */       this.totalMemory = total;
/* 571 */       this.freeMemory = free;
/*     */ 
/*     */       
/* 574 */       this.data.add(Long.valueOf(this.totalMemory - this.freeMemory));
/* 575 */       if (this.data.size() > 190) {
/* 576 */         this.data.remove(0);
/* 577 */         this.xShift = (this.xShift + 1) % 10;
/*     */       } 
/*     */ 
/*     */       
/* 581 */       Iterator<Long> it = this.data.iterator();
/* 582 */       GeneralPath p = new GeneralPath();
/* 583 */       long l = ((Long)it.next()).longValue();
/* 584 */       p.moveTo(5.0F, (float)(this.totalMemory - l) / (float)this.totalMemory * 80.0F + 10.0F);
/* 585 */       int i = 6;
/* 586 */       while (it.hasNext()) {
/* 587 */         l = ((Long)it.next()).longValue();
/* 588 */         p.lineTo(i, (float)(this.totalMemory - l) / (float)this.totalMemory * 80.0F + 10.0F);
/* 589 */         i++;
/*     */       } 
/* 591 */       this.path = p;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void paintComponent(Graphics g) {
/* 598 */       super.paintComponent(g);
/* 599 */       Graphics2D g2d = (Graphics2D)g;
/*     */ 
/*     */       
/* 602 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */ 
/*     */ 
/*     */       
/* 606 */       Dimension dim = getSize();
/* 607 */       double sx = dim.width / 200.0D;
/* 608 */       double sy = dim.height / 100.0D;
/* 609 */       g2d.transform(AffineTransform.getScaleInstance(sx, sy));
/*     */ 
/*     */       
/* 612 */       g2d.setPaint(this.gridLinesColor);
/* 613 */       g2d.setStroke(GRID_LINES_STROKE); int i;
/* 614 */       for (i = 1; i < 20; i++) {
/* 615 */         int lx = i * 10 + 5 - this.xShift;
/* 616 */         g2d.draw(new Line2D.Double(lx, 5.0D, lx, 95.0D));
/*     */       } 
/*     */ 
/*     */       
/* 620 */       for (i = 1; i < 9; i++) {
/* 621 */         int ly = i * 10 + 5;
/* 622 */         g2d.draw(new Line2D.Double(5.0D, ly, 195.0D, ly));
/*     */       } 
/*     */ 
/*     */       
/* 626 */       g2d.setPaint(this.curveColor);
/* 627 */       g2d.setStroke(CURVE_STROKE);
/*     */       
/* 629 */       g2d.draw(this.path);
/*     */ 
/*     */       
/* 632 */       g2d.setStroke(BORDER_STROKE);
/* 633 */       g2d.setPaint(this.borderColor);
/* 634 */       g2d.draw(new Rectangle2D.Double(5.0D, 5.0D, 190.0D, 90.0D));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface MemoryChangeListener
/*     */   {
/*     */     void memoryStateChanged(long param1Long1, long param1Long2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class RepaintThread
/*     */     extends Thread
/*     */   {
/*     */     protected long timeout;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected List components;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 672 */     protected Runtime runtime = Runtime.getRuntime();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean suspended;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected UpdateRunnable updateRunnable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public RepaintThread(long timeout, List components) {
/* 690 */       this.timeout = timeout;
/* 691 */       this.components = components;
/* 692 */       this.updateRunnable = createUpdateRunnable();
/* 693 */       setPriority(1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       while (true) {
/*     */         try {
/* 702 */           synchronized (this.updateRunnable) {
/* 703 */             if (!this.updateRunnable.inEventQueue)
/* 704 */               EventQueue.invokeLater(this.updateRunnable); 
/* 705 */             this.updateRunnable.inEventQueue = true;
/*     */           } 
/* 707 */           sleep(this.timeout);
/* 708 */           synchronized (this) {
/* 709 */             while (this.suspended) {
/* 710 */               wait();
/*     */             }
/*     */           } 
/* 713 */         } catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     }
/*     */     
/*     */     protected UpdateRunnable createUpdateRunnable() {
/* 718 */       return new UpdateRunnable();
/*     */     }
/*     */     
/*     */     protected class UpdateRunnable implements Runnable { public boolean inEventQueue = false;
/*     */       
/*     */       public void run() {
/* 724 */         long free = MemoryMonitor.RepaintThread.this.runtime.freeMemory();
/* 725 */         long total = MemoryMonitor.RepaintThread.this.runtime.totalMemory();
/* 726 */         for (Object component : MemoryMonitor.RepaintThread.this.components) {
/* 727 */           Component c = (Component)component;
/* 728 */           ((MemoryMonitor.MemoryChangeListener)c).memoryStateChanged(total, free);
/* 729 */           c.repaint();
/*     */         } 
/* 731 */         synchronized (this) { this.inEventQueue = false; }
/*     */       
/*     */       } }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void safeSuspend() {
/* 739 */       if (!this.suspended) {
/* 740 */         this.suspended = true;
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public synchronized void safeResume() {
/* 748 */       if (this.suspended) {
/* 749 */         this.suspended = false;
/* 750 */         notify();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/MemoryMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */