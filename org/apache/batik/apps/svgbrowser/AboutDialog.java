/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.net.URL;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JLayeredPane;
/*     */ import javax.swing.JWindow;
/*     */ import org.apache.batik.Version;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AboutDialog
/*     */   extends JWindow
/*     */ {
/*     */   public static final String ICON_BATIK_SPLASH = "AboutDialog.icon.batik.splash";
/*     */   
/*     */   public AboutDialog() {
/*  59 */     buildGUI();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AboutDialog(Frame owner) {
/*  66 */     super(owner);
/*  67 */     buildGUI();
/*     */     
/*  69 */     addKeyListener(new KeyAdapter() {
/*     */           public void keyPressed(KeyEvent e) {
/*  71 */             if (e.getKeyCode() == 27) {
/*  72 */               AboutDialog.this.setVisible(false);
/*  73 */               AboutDialog.this.dispose();
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/*  78 */     addMouseListener(new MouseAdapter() {
/*     */           public void mousePressed(MouseEvent e) {
/*  80 */             AboutDialog.this.setVisible(false);
/*  81 */             AboutDialog.this.dispose();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void setLocationRelativeTo(Frame f) {
/*  87 */     Dimension invokerSize = f.getSize();
/*  88 */     Point loc = f.getLocation();
/*  89 */     Point invokerScreenLocation = new Point(loc.x, loc.y);
/*     */     
/*  91 */     Rectangle bounds = getBounds();
/*  92 */     int dx = invokerScreenLocation.x + (invokerSize.width - bounds.width) / 2;
/*  93 */     int dy = invokerScreenLocation.y + (invokerSize.height - bounds.height) / 2;
/*  94 */     Dimension screenSize = getToolkit().getScreenSize();
/*     */     
/*  96 */     if (dy + bounds.height > screenSize.height) {
/*  97 */       dy = screenSize.height - bounds.height;
/*  98 */       dx = (invokerScreenLocation.x < screenSize.width >> 1) ? (invokerScreenLocation.x + invokerSize.width) : (invokerScreenLocation.x - bounds.width);
/*     */     } 
/*     */     
/* 101 */     if (dx + bounds.width > screenSize.width) {
/* 102 */       dx = screenSize.width - bounds.width;
/*     */     }
/*     */     
/* 105 */     if (dx < 0) dx = 0; 
/* 106 */     if (dy < 0) dy = 0; 
/* 107 */     setLocation(dx, dy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void buildGUI() {
/* 114 */     getContentPane().setBackground(Color.white);
/*     */     
/* 116 */     ClassLoader cl = getClass().getClassLoader();
/* 117 */     URL url = cl.getResource(Resources.getString("AboutDialog.icon.batik.splash"));
/* 118 */     ImageIcon icon = new ImageIcon(url);
/* 119 */     int w = icon.getIconWidth();
/* 120 */     int h = icon.getIconHeight();
/*     */     
/* 122 */     JLayeredPane p = new JLayeredPane();
/* 123 */     p.setSize(600, 425);
/* 124 */     getContentPane().add(p);
/*     */     
/* 126 */     JLabel l = new JLabel(icon);
/* 127 */     l.setBounds(0, 0, w, h);
/* 128 */     p.add(l, Integer.valueOf(0));
/*     */     
/* 130 */     JLabel l2 = new JLabel("Batik " + Version.getVersion());
/* 131 */     l2.setForeground(new Color(232, 232, 232, 255));
/* 132 */     l2.setOpaque(false);
/* 133 */     l2.setBackground(new Color(0, 0, 0, 0));
/* 134 */     l2.setHorizontalAlignment(4);
/* 135 */     l2.setVerticalAlignment(3);
/* 136 */     l2.setBounds(w - 320, h - 117, 300, 100);
/* 137 */     p.add(l2, Integer.valueOf(2));
/*     */     
/* 139 */     ((JComponent)getContentPane()).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.black), BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.black)), BorderFactory.createEmptyBorder(10, 10, 10, 10))));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/AboutDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */