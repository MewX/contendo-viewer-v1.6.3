/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGColor
/*     */   extends AbstractSVGConverter
/*     */ {
/*  40 */   public static final Color aqua = Color.cyan;
/*  41 */   public static final Color black = Color.black;
/*  42 */   public static final Color blue = Color.blue;
/*  43 */   public static final Color fuchsia = Color.magenta;
/*  44 */   public static final Color gray = Color.gray;
/*  45 */   public static final Color green = new Color(0, 128, 0);
/*  46 */   public static final Color lime = Color.green;
/*  47 */   public static final Color maroon = new Color(128, 0, 0);
/*  48 */   public static final Color navy = new Color(0, 0, 128);
/*  49 */   public static final Color olive = new Color(128, 128, 0);
/*  50 */   public static final Color purple = new Color(128, 0, 128);
/*  51 */   public static final Color red = Color.red;
/*  52 */   public static final Color silver = new Color(192, 192, 192);
/*  53 */   public static final Color teal = new Color(0, 128, 128);
/*  54 */   public static final Color white = Color.white;
/*  55 */   public static final Color yellow = Color.yellow;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private static Map colorMap = new HashMap<Object, Object>();
/*     */   
/*     */   static {
/*  63 */     colorMap.put(black, "black");
/*  64 */     colorMap.put(silver, "silver");
/*  65 */     colorMap.put(gray, "gray");
/*  66 */     colorMap.put(white, "white");
/*  67 */     colorMap.put(maroon, "maroon");
/*  68 */     colorMap.put(red, "red");
/*  69 */     colorMap.put(purple, "purple");
/*  70 */     colorMap.put(fuchsia, "fuchsia");
/*  71 */     colorMap.put(green, "green");
/*  72 */     colorMap.put(lime, "lime");
/*  73 */     colorMap.put(olive, "olive");
/*  74 */     colorMap.put(yellow, "yellow");
/*  75 */     colorMap.put(navy, "navy");
/*  76 */     colorMap.put(blue, "blue");
/*  77 */     colorMap.put(teal, "teal");
/*  78 */     colorMap.put(aqua, "aqua");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGColor(SVGGeneratorContext generatorContext) {
/*  86 */     super(generatorContext);
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
/*     */   public SVGDescriptor toSVG(GraphicContext gc) {
/* 100 */     Paint paint = gc.getPaint();
/* 101 */     return toSVG((Color)paint, this.generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGPaintDescriptor toSVG(Color color, SVGGeneratorContext gc) {
/* 112 */     String cssColor = (String)colorMap.get(color);
/* 113 */     if (cssColor == null) {
/*     */       
/* 115 */       StringBuffer cssColorBuffer = new StringBuffer("rgb(");
/* 116 */       cssColorBuffer.append(color.getRed());
/* 117 */       cssColorBuffer.append(",");
/* 118 */       cssColorBuffer.append(color.getGreen());
/* 119 */       cssColorBuffer.append(",");
/* 120 */       cssColorBuffer.append(color.getBlue());
/* 121 */       cssColorBuffer.append(")");
/* 122 */       cssColor = cssColorBuffer.toString();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     float alpha = color.getAlpha() / 255.0F;
/*     */     
/* 130 */     String alphaString = gc.doubleString(alpha);
/*     */     
/* 132 */     return new SVGPaintDescriptor(cssColor, alphaString);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */