/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SwingSVGPrettyPrint
/*     */   implements SVGSyntax
/*     */ {
/*     */   public static void print(JComponent cmp, SVGGraphics2D svgGen) {
/*  52 */     if (cmp instanceof javax.swing.JComboBox || cmp instanceof javax.swing.JScrollBar) {
/*     */ 
/*     */       
/*  55 */       printHack(cmp, svgGen);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  60 */     SVGGraphics2D g = (SVGGraphics2D)svgGen.create();
/*  61 */     g.setColor(cmp.getForeground());
/*  62 */     g.setFont(cmp.getFont());
/*  63 */     Element topLevelGroup = g.getTopLevelGroup();
/*     */ 
/*     */     
/*  66 */     if (cmp.getWidth() <= 0 || cmp.getHeight() <= 0) {
/*     */       return;
/*     */     }
/*  69 */     Rectangle clipRect = g.getClipBounds();
/*  70 */     if (clipRect == null) {
/*  71 */       g.setClip(0, 0, cmp.getWidth(), cmp.getHeight());
/*     */     }
/*  73 */     paintComponent(cmp, g);
/*  74 */     paintBorder(cmp, g);
/*  75 */     paintChildren(cmp, g);
/*     */ 
/*     */     
/*  78 */     Element cmpGroup = g.getTopLevelGroup();
/*  79 */     cmpGroup.setAttributeNS((String)null, "id", (svgGen.getGeneratorContext()).idGenerator.generateID(cmp.getClass().getName()));
/*     */ 
/*     */ 
/*     */     
/*  83 */     topLevelGroup.appendChild(cmpGroup);
/*  84 */     svgGen.setTopLevelGroup(topLevelGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void printHack(JComponent cmp, SVGGraphics2D svgGen) {
/*  93 */     SVGGraphics2D g = (SVGGraphics2D)svgGen.create();
/*  94 */     g.setColor(cmp.getForeground());
/*  95 */     g.setFont(cmp.getFont());
/*  96 */     Element topLevelGroup = g.getTopLevelGroup();
/*     */ 
/*     */     
/*  99 */     if (cmp.getWidth() <= 0 || cmp.getHeight() <= 0) {
/*     */       return;
/*     */     }
/* 102 */     Rectangle clipRect = g.getClipBounds();
/* 103 */     if (clipRect == null) {
/* 104 */       g.setClip(0, 0, cmp.getWidth(), cmp.getHeight());
/*     */     }
/*     */     
/* 107 */     cmp.paint((Graphics)g);
/*     */ 
/*     */     
/* 110 */     Element cmpGroup = g.getTopLevelGroup();
/* 111 */     cmpGroup.setAttributeNS((String)null, "id", (svgGen.getGeneratorContext()).idGenerator.generateID(cmp.getClass().getName()));
/*     */ 
/*     */ 
/*     */     
/* 115 */     topLevelGroup.appendChild(cmpGroup);
/* 116 */     svgGen.setTopLevelGroup(topLevelGroup);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void paintComponent(JComponent cmp, SVGGraphics2D svgGen) {
/* 121 */     ComponentUI ui = UIManager.getUI(cmp);
/* 122 */     if (ui != null) {
/* 123 */       ui.installUI(cmp);
/* 124 */       ui.update((Graphics)svgGen, cmp);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void paintBorder(JComponent cmp, SVGGraphics2D svgGen) {
/* 135 */     Border border = cmp.getBorder();
/* 136 */     if (border != null) {
/* 137 */       if (cmp instanceof AbstractButton || cmp instanceof JPopupMenu || cmp instanceof JToolBar || cmp instanceof JMenuBar || cmp instanceof JProgressBar) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 146 */         if ((cmp instanceof AbstractButton && ((AbstractButton)cmp).isBorderPainted()) || (cmp instanceof JPopupMenu && ((JPopupMenu)cmp).isBorderPainted()) || (cmp instanceof JToolBar && ((JToolBar)cmp).isBorderPainted()) || (cmp instanceof JMenuBar && ((JMenuBar)cmp).isBorderPainted()) || (cmp instanceof JProgressBar && ((JProgressBar)cmp).isBorderPainted()))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 155 */           border.paintBorder(cmp, (Graphics)svgGen, 0, 0, cmp.getWidth(), cmp.getHeight()); } 
/*     */       } else {
/* 157 */         border.paintBorder(cmp, (Graphics)svgGen, 0, 0, cmp.getWidth(), cmp.getHeight());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void paintChildren(JComponent cmp, SVGGraphics2D svgGen) {
/* 163 */     int i = cmp.getComponentCount() - 1;
/* 164 */     Rectangle tmpRect = new Rectangle();
/*     */     
/* 166 */     for (; i >= 0; i--) {
/* 167 */       Component comp = cmp.getComponent(i);
/*     */       
/* 169 */       if (comp != null && JComponent.isLightweightComponent(comp) && comp.isVisible()) {
/* 170 */         Rectangle cr = null;
/* 171 */         boolean isJComponent = comp instanceof JComponent;
/*     */         
/* 173 */         if (isJComponent) {
/* 174 */           cr = tmpRect;
/* 175 */           ((JComponent)comp).getBounds(cr);
/*     */         } else {
/* 177 */           cr = comp.getBounds();
/*     */         } 
/*     */         
/* 180 */         boolean hitClip = svgGen.hitClip(cr.x, cr.y, cr.width, cr.height);
/*     */ 
/*     */         
/* 183 */         if (hitClip) {
/* 184 */           SVGGraphics2D cg = (SVGGraphics2D)svgGen.create(cr.x, cr.y, cr.width, cr.height);
/* 185 */           cg.setColor(comp.getForeground());
/* 186 */           cg.setFont(comp.getFont());
/* 187 */           if (comp instanceof JComponent) {
/* 188 */             print((JComponent)comp, cg);
/*     */           } else {
/* 190 */             comp.paint((Graphics)cg);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SwingSVGPrettyPrint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */