/*     */ package net.a.a.i;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import net.a.a.c;
/*     */ import net.a.a.g.c;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */   extends ComponentUI
/*     */   implements PropertyChangeListener
/*     */ {
/*  55 */   private final Map<b, Reference<a>> a = new HashMap<b, Reference<a>>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private c a(Graphics paramGraphics, JComponent paramJComponent) {
/*  66 */     b b = (b)paramJComponent;
/*  67 */     a a = null;
/*  68 */     Reference<a> reference = this.a.get(b);
/*  69 */     if (reference != null) {
/*  70 */       a = reference.get();
/*     */     }
/*     */     
/*  73 */     if (a == null) {
/*  74 */       a = new a(b);
/*  75 */       this.a.put(b, new SoftReference<a>(a));
/*     */     } 
/*     */     
/*  78 */     return a.a((Graphics2D)paramGraphics);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics paramGraphics, JComponent paramJComponent) {
/*  84 */     c c = a(paramGraphics, paramJComponent);
/*  85 */     Dimension dimension = a(paramJComponent, c);
/*  86 */     Point point = a(paramJComponent, dimension);
/*     */     
/*  88 */     a(paramGraphics, paramJComponent, dimension, point);
/*     */     
/*  90 */     Point2D point2D = a((b)paramJComponent, c, dimension);
/*     */     
/*  92 */     c.a((Graphics2D)paramGraphics, (float)point2D.getX() + point.x, 
/*  93 */         (float)point2D.getY() + point.y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(Graphics paramGraphics, JComponent paramJComponent) {
/* 100 */     if (paramJComponent.isOpaque()) {
/* 101 */       paramGraphics.setColor(paramJComponent.getBackground());
/* 102 */       paramGraphics.fillRect(0, 0, paramJComponent.getWidth(), paramJComponent.getHeight());
/*     */     } 
/* 104 */     paint(paramGraphics, paramJComponent);
/*     */   }
/*     */   
/*     */   private Point2D a(b paramb, c paramc, Dimension paramDimension) {
/*     */     float f1;
/*     */     float f2;
/* 110 */     if (paramb.k() == 10 || paramb
/* 111 */       .k() == 2) {
/* 112 */       f1 = 0.0F;
/* 113 */     } else if (paramb.k() == 11 || paramb
/* 114 */       .k() == 4) {
/* 115 */       f1 = paramDimension.width - paramc.a();
/*     */     } else {
/* 117 */       f1 = (paramDimension.width - paramc.a()) / 2.0F;
/*     */     } 
/*     */     
/* 120 */     if (paramb.m() == 1) {
/* 121 */       f2 = paramc.b();
/* 122 */     } else if (paramb.m() == 3) {
/* 123 */       f2 = paramDimension.height - paramc.c();
/*     */     } else {
/*     */       
/* 126 */       f2 = (paramDimension.height + paramc.b() - paramc.c()) / 2.0F;
/*     */     } 
/* 128 */     return new Point2D.Float(f1, f2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(Graphics paramGraphics, JComponent paramJComponent, Dimension paramDimension, Point paramPoint) {
/* 133 */     Color color = b(paramJComponent);
/* 134 */     if (color != null) {
/* 135 */       paramGraphics.setColor(color);
/* 136 */       paramGraphics.fillRect(paramPoint.x, paramPoint.y, paramDimension.width, paramDimension.height);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Point a(JComponent paramJComponent, Dimension paramDimension) {
/* 142 */     Point point = new Point(0, 0);
/* 143 */     Border border = paramJComponent.getBorder();
/* 144 */     if (border != null) {
/* 145 */       Insets insets = border.getBorderInsets(paramJComponent);
/* 146 */       if (insets != null) {
/* 147 */         paramDimension.width -= insets.left + insets.right;
/* 148 */         paramDimension.height -= insets.top + insets.bottom;
/* 149 */         point = new Point(insets.left, insets.top);
/*     */       } 
/*     */     } 
/* 152 */     return point;
/*     */   }
/*     */   
/*     */   private Color b(JComponent paramJComponent) {
/* 156 */     Color color = paramJComponent.getBackground();
/* 157 */     if (paramJComponent.isOpaque()) {
/* 158 */       if (color == null) {
/* 159 */         color = Color.WHITE;
/*     */       }
/*     */       
/* 162 */       color = new Color(color.getRGB());
/*     */     } 
/* 164 */     return color;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void installUI(JComponent paramJComponent) {
/* 170 */     if (paramJComponent instanceof b) {
/* 171 */       paramJComponent.addPropertyChangeListener(this);
/* 172 */       a(paramJComponent);
/*     */     } else {
/* 174 */       throw new IllegalArgumentException("This UI can only be installed on a JMathComponent");
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
/*     */   
/*     */   protected void a(JComponent paramJComponent) {
/* 188 */     LookAndFeel.installProperty(paramJComponent, "opaque", Boolean.FALSE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent paramJComponent) {
/* 194 */     paramJComponent.removePropertyChangeListener(this);
/* 195 */     this.a.remove(paramJComponent);
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent paramPropertyChangeEvent) {
/* 200 */     this.a.remove(paramPropertyChangeEvent.getSource());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent paramJComponent) {
/* 206 */     return c(paramJComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Dimension c(JComponent paramJComponent) {
/* 217 */     c c = a(paramJComponent.getGraphics(), paramJComponent);
/* 218 */     return a(paramJComponent, c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Dimension a(JComponent paramJComponent, c paramc) {
/* 225 */     Dimension dimension = new Dimension((int)Math.ceil(paramc.a()), (int)Math.ceil((paramc.b() + paramc
/* 226 */           .c())));
/*     */     
/* 228 */     Border border = paramJComponent.getBorder();
/* 229 */     if (border != null) {
/* 230 */       Insets insets = border.getBorderInsets(paramJComponent);
/* 231 */       if (insets != null) {
/* 232 */         dimension.width += insets.left + insets.right;
/* 233 */         dimension.height += insets.top + insets.bottom;
/*     */       } 
/*     */     } 
/* 236 */     return dimension;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize(JComponent paramJComponent) {
/* 242 */     return c(paramJComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent paramJComponent) {
/* 248 */     return c(paramJComponent);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<c.a> a(b paramb, float paramFloat1, float paramFloat2) {
/* 265 */     c c = a(paramb.getGraphics(), paramb);
/*     */     
/* 267 */     Point2D point2D = a(paramb, c, paramb
/* 268 */         .getSize());
/* 269 */     return c.a(paramFloat1, paramFloat2, (float)point2D.getX(), 
/* 270 */         (float)point2D.getY());
/*     */   }
/*     */   
/*     */   private static class a {
/*     */     final Node a;
/*     */     final c b;
/* 276 */     final Map<Graphics2D, c> c = new HashMap<Graphics2D, c>();
/*     */     
/*     */     public a(b param1b) {
/* 279 */       this.a = param1b.c();
/* 280 */       this.b = (c)param1b.n();
/*     */     }
/*     */     
/*     */     public c a(Graphics2D param1Graphics2D) {
/* 284 */       c c1 = this.c.get(param1Graphics2D);
/* 285 */       if (c1 == null) {
/* 286 */         c1 = new c(this.a, this.b, param1Graphics2D);
/* 287 */         this.c.put(param1Graphics2D, c1);
/*     */       } 
/* 289 */       return c1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/i/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */