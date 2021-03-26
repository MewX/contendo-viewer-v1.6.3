/*     */ package net.a.a.e.d.a;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import net.a.a.c;
/*     */ import net.a.a.e.c;
/*     */ import net.a.a.e.d.d;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class a
/*     */ {
/*     */   public static final String a = "transparent";
/*     */   public static final String b = "0.0555556em";
/*     */   public static final String c = "0.111111em";
/*     */   public static final String d = "0.166667em";
/*     */   public static final String e = "0.222222em";
/*     */   public static final String f = "0.277778em";
/*     */   public static final String g = "0.333333em";
/*     */   public static final String h = "0.388889em";
/*     */   public static final String i = "pt";
/*     */   public static final String j = "9999999pt";
/*     */   private static final String k = "Error Parsing number: ";
/*     */   private static final int l = 5;
/*     */   private static final int m = 4;
/*     */   private static final int n = 1;
/*     */   private static final int o = 2;
/*     */   private static final int p = 3;
/*     */   private static final int q = 9;
/*     */   private static final int r = 7;
/*     */   private static final int s = 16;
/*     */   private static final float t = 15.0F;
/*     */   private static final int u = 255;
/*     */   private static final float v = 100.0F;
/*     */   private static final float w = 255.0F;
/*     */   private static final String x = "%";
/*     */   private static final String y = ",";
/*     */   private static final float z = 0.8388889F;
/*     */   private static final float A = 0.5F;
/*     */   private static final float B = 72.0F;
/*     */   private static final float C = 0.01F;
/*     */   private static final float D = 2.54F;
/*     */   private static final float E = 25.4F;
/*     */   private static final float F = 12.0F;
/* 154 */   private static final Map<String, String> G = new HashMap<String, String>();
/*     */   
/* 156 */   private static final Map<String, Float> H = new HashMap<String, Float>();
/*     */   
/* 158 */   private static final Map<String, Float> I = new HashMap<String, Float>();
/*     */   
/* 160 */   private static final Map<String, Color> J = new HashMap<String, Color>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   private static final Log K = LogFactory.getLog(c.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float a(String paramString, c paramc, float paramFloat) {
/*     */     float f;
/* 188 */     if (paramString == null) {
/* 189 */       return paramFloat;
/*     */     }
/* 191 */     String str = a(paramString);
/*     */     
/*     */     try {
/* 194 */       if (str.length() >= 2) {
/* 195 */         int i = str.length() - 2;
/* 196 */         String str1 = str.substring(i);
/* 197 */         float f1 = Float.parseFloat(str.substring(0, i));
/*     */         
/* 199 */         if (I.containsKey(str1)) {
/* 200 */           f = a(paramString, paramc, "pt");
/*     */         }
/* 202 */         else if (H.containsKey(str1)) {
/*     */           
/* 204 */           f = f1 * paramFloat * ((Float)H.get(str1)).floatValue();
/*     */         } else {
/* 206 */           f = Float.parseFloat(str) * paramFloat;
/*     */         } 
/*     */       } else {
/* 209 */         f = Float.parseFloat(str) * paramFloat;
/*     */       } 
/* 211 */     } catch (NumberFormatException numberFormatException) {
/* 212 */       f = paramFloat;
/* 213 */       K.warn("Error Parsing number: " + paramString);
/*     */     } 
/*     */     
/* 216 */     return f;
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
/*     */   public static float a(String paramString1, c paramc, String paramString2) {
/*     */     float f;
/* 233 */     String str = a(paramString1);
/* 234 */     if (str.length() == 0) {
/* 235 */       return 0.0F;
/*     */     }
/*     */     
/*     */     try {
/*     */       String str1;
/*     */       float f1;
/* 241 */       if (str.length() <= 2) {
/* 242 */         str1 = paramString2;
/* 243 */         f1 = Float.parseFloat(str);
/*     */       } else {
/* 245 */         int i = str.length() - 2;
/* 246 */         str1 = str.substring(i);
/* 247 */         f1 = Float.parseFloat(str.substring(0, i));
/*     */       } 
/* 249 */       if (f1 == 0.0F) {
/* 250 */         f = 0.0F;
/* 251 */       } else if (H.containsKey(str1)) {
/*     */         
/* 253 */         f = f1 * d.a(paramc) * ((Float)H.get(str1)).floatValue();
/* 254 */       } else if (I.containsKey(str1)) {
/* 255 */         f = f1 * ((Float)I.get(str1)).floatValue();
/* 256 */       } else if (paramString2.length() > 0) {
/* 257 */         f = a(paramString1 + paramString2, paramc, "");
/*     */       } else {
/*     */         
/* 260 */         f = Float.parseFloat(str);
/* 261 */         K.warn("Error Parsing attribute: " + paramString1 + " assuming " + f + "pt");
/*     */       }
/*     */     
/*     */     }
/* 265 */     catch (NumberFormatException numberFormatException) {
/* 266 */       f = 1.0F;
/* 267 */       K.warn("Error Parsing number: " + paramString1 + " falling back to " + f + "pt");
/*     */     } 
/*     */ 
/*     */     
/* 271 */     return f;
/*     */   }
/*     */   
/*     */   private static String a(String paramString) {
/* 275 */     if (paramString == null) {
/* 276 */       return "";
/*     */     }
/* 278 */     String str1 = paramString.trim().toLowerCase(Locale.ENGLISH);
/*     */ 
/*     */     
/* 281 */     String str2 = G.get(str1);
/* 282 */     if (str2 != null) {
/* 283 */       str1 = str2;
/*     */     }
/* 285 */     if (str1.endsWith("%"))
/*     */     {
/* 287 */       str1 = str1 + ' ';
/*     */     }
/* 289 */     return str1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Color a(String paramString, Color paramColor) {
/* 316 */     if (paramString == null) {
/* 317 */       return null;
/*     */     }
/*     */     
/* 320 */     String str = paramString.toLowerCase(Locale.ENGLISH);
/* 321 */     Color color = null;
/*     */     
/* 323 */     if (J.containsKey(str)) {
/* 324 */       color = J.get(str);
/*     */     } else {
/* 326 */       if (paramString.charAt(0) == '#') {
/* 327 */         color = f(paramString);
/* 328 */       } else if (paramString.startsWith("rgb(")) {
/* 329 */         color = c(paramString);
/* 330 */       } else if (paramString.startsWith("java.awt.Color")) {
/* 331 */         color = b(paramString);
/*     */       } 
/*     */       
/* 334 */       if (color == null) {
/* 335 */         color = paramColor;
/*     */       }
/*     */       
/* 338 */       if (color != null) {
/* 339 */         J.put(paramString, color);
/*     */       }
/*     */     } 
/* 342 */     return color;
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
/*     */   private static Color b(String paramString) {
/*     */     Color color;
/* 355 */     int i = paramString.indexOf('[');
/* 356 */     int j = paramString.indexOf(']');
/* 357 */     if (i == -1 || j == -1) {
/* 358 */       color = null;
/*     */     } else {
/* 360 */       color = d(paramString
/* 361 */           .substring(i + 1, j));
/*     */     } 
/* 363 */     return color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Color c(String paramString) {
/*     */     Color color;
/* 375 */     int i = paramString.indexOf('(');
/* 376 */     int j = paramString.indexOf(')');
/* 377 */     if (i == -1 || j == -1) {
/* 378 */       color = null;
/*     */     } else {
/* 380 */       color = d(paramString
/* 381 */           .substring(i + 1, j));
/*     */     } 
/* 383 */     return color;
/*     */   }
/*     */   
/*     */   private static Color d(String paramString) {
/*     */     Color color;
/* 388 */     StringTokenizer stringTokenizer = new StringTokenizer(paramString, ",");
/*     */     
/*     */     try {
/* 391 */       float f1 = 0.0F;
/* 392 */       float f2 = 0.0F;
/* 393 */       float f3 = 0.0F;
/* 394 */       if (stringTokenizer.hasMoreTokens()) {
/* 395 */         String str = stringTokenizer.nextToken().trim();
/* 396 */         f1 = e(str);
/*     */       } 
/* 398 */       if (stringTokenizer.hasMoreTokens()) {
/* 399 */         String str = stringTokenizer.nextToken().trim();
/* 400 */         f2 = e(str);
/*     */       } 
/* 402 */       if (stringTokenizer.hasMoreTokens()) {
/* 403 */         String str = stringTokenizer.nextToken().trim();
/* 404 */         f3 = e(str);
/*     */       } 
/* 406 */       color = new Color(f1, f2, f3);
/* 407 */     } catch (NumberFormatException numberFormatException) {
/* 408 */       K.warn(numberFormatException);
/* 409 */       color = null;
/*     */     } 
/* 411 */     return color;
/*     */   }
/*     */   
/*     */   private static float e(String paramString) {
/*     */     float f;
/* 416 */     if (paramString.endsWith("%")) {
/* 417 */       f = Float.parseFloat(paramString.substring(0, paramString.length() - 1)) / 100.0F;
/*     */     } else {
/*     */       
/* 420 */       f = Float.parseFloat(paramString) / 255.0F;
/*     */     } 
/* 422 */     if (f < 0.0F || f > 1.0F) {
/* 423 */       throw new NumberFormatException(paramString + " is out of Range");
/*     */     }
/* 425 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Color f(String paramString) {
/* 436 */     Color color = null;
/*     */     try {
/* 438 */       int i = paramString.length();
/* 439 */       if (i >= 4 && i <= 5) {
/*     */ 
/*     */         
/* 442 */         float f1 = Integer.parseInt(paramString.substring(1, 2), 16) / 15.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 447 */         float f2 = Integer.parseInt(paramString.substring(2, 3), 16) / 15.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 452 */         float f3 = Integer.parseInt(paramString.substring(3, 4), 16) / 15.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 457 */         float f4 = 1.0F;
/* 458 */         if (i == 5) {
/* 459 */           f4 = Integer.parseInt(paramString
/* 460 */               .substring(4), 16) / 15.0F;
/*     */         }
/*     */ 
/*     */         
/* 464 */         color = new Color(f1, f2, f3, f4);
/* 465 */       } else if (i == 7 || i == 9) {
/*     */         
/* 467 */         int j = Integer.parseInt(paramString.substring(1, 3), 16);
/*     */         
/* 469 */         int k = Integer.parseInt(paramString.substring(3, 5), 16);
/*     */         
/* 471 */         int m = Integer.parseInt(paramString.substring(5, 7), 16);
/*     */ 
/*     */         
/* 474 */         int n = 255;
/* 475 */         if (i == 9) {
/* 476 */           n = Integer.parseInt(paramString
/* 477 */               .substring(7), 16);
/*     */         }
/*     */         
/* 480 */         color = new Color(j, k, m, n);
/*     */       } else {
/* 482 */         throw new NumberFormatException();
/*     */       } 
/* 484 */     } catch (NumberFormatException numberFormatException) {
/* 485 */       return null;
/*     */     } 
/* 487 */     return color;
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
/*     */   public static String a(Color paramColor) {
/* 501 */     if (paramColor == null) {
/* 502 */       return "transparent";
/*     */     }
/* 504 */     StringBuffer stringBuffer = new StringBuffer(10);
/* 505 */     stringBuffer.append('#');
/* 506 */     String str = Integer.toHexString(paramColor.getRed());
/* 507 */     if (str.length() == 1) {
/* 508 */       stringBuffer.append('0');
/*     */     }
/* 510 */     stringBuffer.append(str);
/* 511 */     str = Integer.toHexString(paramColor.getGreen());
/* 512 */     if (str.length() == 1) {
/* 513 */       stringBuffer.append('0');
/*     */     }
/* 515 */     stringBuffer.append(str);
/* 516 */     str = Integer.toHexString(paramColor.getBlue());
/* 517 */     if (str.length() == 1) {
/* 518 */       stringBuffer.append('0');
/*     */     }
/* 520 */     stringBuffer.append(str);
/* 521 */     if (paramColor.getAlpha() != 255) {
/* 522 */       str = Integer.toHexString(paramColor.getAlpha());
/* 523 */       if (str.length() == 1) {
/* 524 */         stringBuffer.append('0');
/*     */       }
/* 526 */       stringBuffer.append(str);
/*     */     } 
/* 528 */     return stringBuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 536 */     G.put("veryverythinmathspace", "0.0555556em");
/*     */ 
/*     */     
/* 539 */     G.put("verythinmathspace", "0.111111em");
/*     */ 
/*     */     
/* 542 */     G.put("thinmathspace", "0.166667em");
/*     */ 
/*     */     
/* 545 */     G.put("mediummathspace", "0.222222em");
/*     */ 
/*     */     
/* 548 */     G.put("thickmathspace", "0.277778em");
/*     */ 
/*     */     
/* 551 */     G.put("verythickmathspace", "0.333333em");
/*     */ 
/*     */     
/* 554 */     G.put("veryverythickmathspace", "0.388889em");
/*     */ 
/*     */     
/* 557 */     G.put("infinity", "9999999pt");
/*     */     
/* 559 */     G.put("small", "68%");
/* 560 */     G.put("normal", "100%");
/* 561 */     G.put("big", "147%");
/*     */ 
/*     */     
/* 564 */     G.put("thin", "0.5");
/* 565 */     G.put("medium", "1");
/* 566 */     G.put("thick", "2");
/*     */     
/* 568 */     G.put("null", "0");
/*     */     
/* 570 */     H.put("em", Float.valueOf(0.8388889F));
/* 571 */     H.put("ex", Float.valueOf(0.5F));
/* 572 */     H.put("% ", Float.valueOf(0.01F));
/*     */     
/* 574 */     I.put("px", Float.valueOf(1.0F));
/* 575 */     I.put("in", Float.valueOf(72.0F));
/* 576 */     I.put("cm", Float.valueOf(28.346457F));
/*     */     
/* 578 */     I.put("mm", Float.valueOf(2.8346457F));
/*     */     
/* 580 */     I.put("pt", Float.valueOf(1.0F));
/* 581 */     I.put("pc", Float.valueOf(12.0F));
/*     */ 
/*     */     
/* 584 */     J.put("aqua", new Color(0, 255, 255));
/* 585 */     J.put("black", Color.BLACK);
/* 586 */     J.put("blue", Color.BLUE);
/* 587 */     J.put("fuchsia", new Color(255, 0, 255));
/* 588 */     J.put("gray", Color.GRAY);
/* 589 */     J.put("green", Color.GREEN);
/* 590 */     J.put("lime", new Color(0, 255, 0));
/* 591 */     J.put("maroon", new Color(128, 0, 0));
/* 592 */     J.put("navy", new Color(0, 0, 128));
/* 593 */     J.put("olive", new Color(128, 128, 0));
/* 594 */     J.put("purple", new Color(128, 0, 128));
/* 595 */     J.put("red", Color.RED);
/* 596 */     J.put("silver", new Color(192, 192, 192));
/* 597 */     J.put("teal", new Color(0, 128, 128));
/* 598 */     J.put("white", Color.WHITE);
/* 599 */     J.put("yellow", Color.YELLOW);
/*     */ 
/*     */     
/* 602 */     J.put("transparent", null);
/*     */     
/* 604 */     J.put("null", null);
/* 605 */     J.put("", null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */