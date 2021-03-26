/*     */ package net.a.a.i;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.beans.BeanDescriptor;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.beans.SimpleBeanInfo;
/*     */ import javax.swing.ImageIcon;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends SimpleBeanInfo
/*     */ {
/*  39 */   private static final Class<b> a = b.class;
/*     */   
/*  41 */   private final Image b = (new ImageIcon(a
/*     */       
/*  43 */       .getResource("/icons/jeuclid_16x16.png"))).getImage();
/*     */   
/*  45 */   private final Image c = (new ImageIcon(a
/*     */       
/*  47 */       .getResource("/icons/jeuclid_32x32.png"))).getImage();
/*     */   
/*  49 */   private final Image d = (new ImageIcon(a
/*     */       
/*  51 */       .getResource("/icons/jeuclid_16x16_bw.png"))).getImage();
/*     */   
/*  53 */   private final Image e = (new ImageIcon(a
/*     */       
/*  55 */       .getResource("/icons/jeuclid_32x32_bw.png"))).getImage();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getIcon(int paramInt) {
/*     */     Image image;
/*  68 */     switch (paramInt) {
/*     */       case 1:
/*  70 */         image = this.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  84 */         return image;case 2: image = this.c; return image;case 3: image = this.d; return image;case 4: image = this.e; return image;
/*     */     } 
/*     */     return this.c;
/*     */   }
/*     */   
/*     */   public BeanDescriptor getBeanDescriptor() {
/*  90 */     BeanDescriptor beanDescriptor = new BeanDescriptor(a);
/*     */     
/*  92 */     beanDescriptor.setName("JEuclid");
/*  93 */     beanDescriptor.setDisplayName("JEuclid Bean");
/*  94 */     beanDescriptor
/*  95 */       .setShortDescription("The JEuclid project creates the possibility to display MathML content. This Bean supports rendering MathML content as a Swing component.");
/*     */ 
/*     */     
/*  98 */     return beanDescriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PropertyDescriptor[] getPropertyDescriptors() {
/*     */     try {
/* 106 */       PropertyDescriptor propertyDescriptor1 = a("fontSize", "Font size", "This will modify the font size of the displayed MathML elements");
/*     */ 
/*     */ 
/*     */       
/* 110 */       PropertyDescriptor propertyDescriptor2 = a("content", "Content", "The XML content for the JEuclid Bean");
/*     */ 
/*     */ 
/*     */       
/* 114 */       PropertyDescriptor propertyDescriptor3 = a("foreground", "Foreground Color", "Foreground color if not specified within the document");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       PropertyDescriptor propertyDescriptor4 = a("background", "Background Color", "Background color for this component");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 124 */       PropertyDescriptor propertyDescriptor5 = a("opaque", "Opaque", "If true, will always overpaint the background");
/*     */ 
/*     */ 
/*     */       
/* 128 */       PropertyDescriptor propertyDescriptor6 = a("fontsSerif", "Serif Fonts", "Fonts to use for Serif characters (the default font)");
/*     */ 
/*     */       
/* 131 */       PropertyDescriptor propertyDescriptor7 = a("fontsSanserif", "Sans-Serif Fonts", "Fonts to use for Sans-Serif characters");
/*     */ 
/*     */ 
/*     */       
/* 135 */       PropertyDescriptor propertyDescriptor8 = a("fontsMonospaced", "Monospaced Fonts", "Fonts to use for Monospaced characters");
/*     */ 
/*     */ 
/*     */       
/* 139 */       PropertyDescriptor propertyDescriptor9 = a("fontsScript", "Script Fonts", "Fonts to use for Script characters");
/*     */ 
/*     */       
/* 142 */       PropertyDescriptor propertyDescriptor10 = a("fontsFraktur", "Fraktur Fonts", "Fonts to use for Fraktur characters");
/*     */ 
/*     */ 
/*     */       
/* 146 */       PropertyDescriptor propertyDescriptor11 = a("fontsDoublestruck", "Double-Struck Fonts", "Fonts to use for Double-Struck characters");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 151 */       PropertyDescriptor propertyDescriptor12 = a("verticalAlignment", "Vertical Alignment", "Vertical alignment, as defined by javax.net.sourceforge.jeuclid.swing.JLabel#getHorizontalAlignment");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 156 */       PropertyDescriptor propertyDescriptor13 = a("horizontalAlignment", "Horizontal Alignment", "Horizontal alignment, as defined by javax.net.sourceforge.jeuclid.swing.JLabel#getHorizontalAlignment");
/*     */ 
/*     */ 
/*     */       
/* 160 */       PropertyDescriptor propertyDescriptor14 = a("border", "Border", "Swing Border Property");
/*     */ 
/*     */       
/* 163 */       return new PropertyDescriptor[] { propertyDescriptor2, propertyDescriptor1, propertyDescriptor3, propertyDescriptor4, propertyDescriptor5, propertyDescriptor6, propertyDescriptor7, propertyDescriptor8, propertyDescriptor9, propertyDescriptor10, propertyDescriptor11, propertyDescriptor12, propertyDescriptor13, propertyDescriptor14 };
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 168 */     catch (IntrospectionException introspectionException) {
/*     */ 
/*     */       
/* 171 */       return super.getPropertyDescriptors();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private PropertyDescriptor a(String paramString1, String paramString2, String paramString3) throws IntrospectionException {
/* 178 */     PropertyDescriptor propertyDescriptor = new PropertyDescriptor(paramString1, a);
/*     */     
/* 180 */     propertyDescriptor.setDisplayName(paramString2);
/* 181 */     propertyDescriptor.setShortDescription(paramString3);
/* 182 */     return propertyDescriptor;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/i/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */