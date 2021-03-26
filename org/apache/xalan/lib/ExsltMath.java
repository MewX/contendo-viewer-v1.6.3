/*     */ package org.apache.xalan.lib;
/*     */ 
/*     */ import org.apache.xpath.NodeSet;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExsltMath
/*     */   extends ExsltBase
/*     */ {
/*  43 */   private static String PI = "3.1415926535897932384626433832795028841971693993751";
/*  44 */   private static String E = "2.71828182845904523536028747135266249775724709369996";
/*  45 */   private static String SQRRT2 = "1.41421356237309504880168872420969807856967187537694";
/*  46 */   private static String LN2 = "0.69314718055994530941723212145817656807550013436025";
/*  47 */   private static String LN10 = "2.302585092994046";
/*  48 */   private static String LOG2E = "1.4426950408889633";
/*  49 */   private static String SQRT1_2 = "0.7071067811865476";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double max(NodeList nl) {
/*  69 */     if (nl == null || nl.getLength() == 0) {
/*  70 */       return Double.NaN;
/*     */     }
/*  72 */     double m = -1.7976931348623157E308D;
/*  73 */     for (int i = 0; i < nl.getLength(); i++) {
/*     */       
/*  75 */       Node n = nl.item(i);
/*  76 */       double d = ExsltBase.toNumber(n);
/*  77 */       if (Double.isNaN(d))
/*  78 */         return Double.NaN; 
/*  79 */       if (d > m) {
/*  80 */         m = d;
/*     */       }
/*     */     } 
/*  83 */     return m;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double min(NodeList nl) {
/* 104 */     if (nl == null || nl.getLength() == 0) {
/* 105 */       return Double.NaN;
/*     */     }
/* 107 */     double m = Double.MAX_VALUE;
/* 108 */     for (int i = 0; i < nl.getLength(); i++) {
/*     */       
/* 110 */       Node n = nl.item(i);
/* 111 */       double d = ExsltBase.toNumber(n);
/* 112 */       if (Double.isNaN(d))
/* 113 */         return Double.NaN; 
/* 114 */       if (d < m) {
/* 115 */         m = d;
/*     */       }
/*     */     } 
/* 118 */     return m;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList highest(NodeList nl) {
/* 140 */     double maxValue = max(nl);
/*     */     
/* 142 */     NodeSet highNodes = new NodeSet();
/* 143 */     highNodes.setShouldCacheNodes(true);
/*     */     
/* 145 */     if (Double.isNaN(maxValue)) {
/* 146 */       return (NodeList)highNodes;
/*     */     }
/* 148 */     for (int i = 0; i < nl.getLength(); i++) {
/*     */       
/* 150 */       Node n = nl.item(i);
/* 151 */       double d = ExsltBase.toNumber(n);
/* 152 */       if (d == maxValue)
/* 153 */         highNodes.addElement(n); 
/*     */     } 
/* 155 */     return (NodeList)highNodes;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList lowest(NodeList nl) {
/* 177 */     double minValue = min(nl);
/*     */     
/* 179 */     NodeSet lowNodes = new NodeSet();
/* 180 */     lowNodes.setShouldCacheNodes(true);
/*     */     
/* 182 */     if (Double.isNaN(minValue)) {
/* 183 */       return (NodeList)lowNodes;
/*     */     }
/* 185 */     for (int i = 0; i < nl.getLength(); i++) {
/*     */       
/* 187 */       Node n = nl.item(i);
/* 188 */       double d = ExsltBase.toNumber(n);
/* 189 */       if (d == minValue)
/* 190 */         lowNodes.addElement(n); 
/*     */     } 
/* 192 */     return (NodeList)lowNodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double abs(double num) {
/* 203 */     return Math.abs(num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double acos(double num) {
/* 214 */     return Math.acos(num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double asin(double num) {
/* 225 */     return Math.asin(num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double atan(double num) {
/* 236 */     return Math.atan(num);
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
/*     */   public static double atan2(double num1, double num2) {
/* 248 */     return Math.atan2(num1, num2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double cos(double num) {
/* 259 */     return Math.cos(num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double exp(double num) {
/* 270 */     return Math.exp(num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double log(double num) {
/* 281 */     return Math.log(num);
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
/*     */   public static double power(double num1, double num2) {
/* 293 */     return Math.pow(num1, num2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double random() {
/* 303 */     return Math.random();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double sin(double num) {
/* 314 */     return Math.sin(num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double sqrt(double num) {
/* 325 */     return Math.sqrt(num);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double tan(double num) {
/* 336 */     return Math.tan(num);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double constant(String name, double precision) {
/* 357 */     String value = null;
/* 358 */     if (name.equals("PI")) {
/* 359 */       value = PI;
/* 360 */     } else if (name.equals("E")) {
/* 361 */       value = E;
/* 362 */     } else if (name.equals("SQRRT2")) {
/* 363 */       value = SQRRT2;
/* 364 */     } else if (name.equals("LN2")) {
/* 365 */       value = LN2;
/* 366 */     } else if (name.equals("LN10")) {
/* 367 */       value = LN10;
/* 368 */     } else if (name.equals("LOG2E")) {
/* 369 */       value = LOG2E;
/* 370 */     } else if (name.equals("SQRT1_2")) {
/* 371 */       value = SQRT1_2;
/*     */     } 
/* 373 */     if (value != null) {
/*     */       
/* 375 */       int bits = (new Double(precision)).intValue();
/*     */       
/* 377 */       if (bits <= value.length()) {
/* 378 */         value = value.substring(0, bits);
/*     */       }
/* 380 */       return (new Double(value)).doubleValue();
/*     */     } 
/*     */     
/* 383 */     return Double.NaN;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/ExsltMath.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */