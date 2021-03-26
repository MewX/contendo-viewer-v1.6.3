/*     */ package net.a.a.e.d.c;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.e.c;
/*     */ import net.a.a.e.d;
/*     */ import net.a.a.e.d.a.d;
/*     */ import net.a.a.e.d.d;
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
/*     */ 
/*     */ 
/*     */ public final class e
/*     */ {
/*  61 */   public static final boolean a = (System.getProperty("mrj.version") != null);
/*     */   
/*  63 */   static final b b = b.a();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AttributedString a(String paramString, d paramd, float paramFloat, c paramc) {
/*  87 */     if (paramString == null) {
/*  88 */       return new AttributedString("");
/*     */     }
/*  90 */     StringBuilder stringBuilder = new StringBuilder();
/*  91 */     ArrayList<Font> arrayList = new ArrayList();
/*  92 */     String str = a.b(paramString);
/*     */     
/*  94 */     for (byte b1 = 0; b1 < str.length(); b1++) {
/*  95 */       if (!Character.isLowSurrogate(str.charAt(b1))) {
/*     */ 
/*     */         
/*  98 */         c c1 = new c(str.codePointAt(b1), paramd);
/*  99 */         Object[] arrayOfObject = a(c1, paramFloat, paramc);
/*     */         
/* 101 */         int j = ((Integer)arrayOfObject[0]).intValue();
/* 102 */         Font font = (Font)arrayOfObject[1];
/*     */         
/* 104 */         stringBuilder.appendCodePoint(j);
/* 105 */         arrayList.add(font);
/* 106 */         if (Character.isSupplementaryCodePoint(j)) {
/* 107 */           arrayList.add(font);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 113 */     AttributedString attributedString = new AttributedString(stringBuilder.toString());
/*     */     
/* 115 */     int i = stringBuilder.length();
/*     */     
/* 117 */     for (byte b2 = 0; b2 < i; b2++) {
/* 118 */       char c1 = stringBuilder.charAt(b2);
/* 119 */       if (!Character.isLowSurrogate(c1)) {
/* 120 */         byte b3; Font font = arrayList.get(b2);
/*     */         
/* 122 */         if (Character.isHighSurrogate(c1)) {
/* 123 */           b3 = 2;
/*     */         } else {
/* 125 */           b3 = 1;
/*     */         } 
/* 127 */         attributedString.addAttribute(TextAttribute.FONT, font, b2, b2 + b3);
/*     */       } 
/*     */     } 
/* 130 */     return attributedString;
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
/*     */   public static AttributedCharacterIterator a(c paramc, d paramd, Node paramNode, float paramFloat) {
/*     */     AttributedCharacterIterator attributedCharacterIterator;
/* 152 */     if (paramNode instanceof org.w3c.dom.Element) {
/*     */       
/* 154 */       d d1 = new d();
/* 155 */       NodeList nodeList = paramNode.getChildNodes();
/* 156 */       AttributedCharacterIterator attributedCharacterIterator1 = null;
/* 157 */       int i = nodeList.getLength();
/* 158 */       for (byte b1 = 0; b1 < i; b1++) {
/*     */         c c1; d d2;
/* 160 */         Node node = nodeList.item(b1);
/*     */         
/* 162 */         if (node instanceof c) {
/*     */           
/* 164 */           c1 = ((c)node).b(paramc);
/* 165 */           d2 = (d)node;
/*     */         } else {
/* 167 */           c1 = paramc;
/* 168 */           d2 = paramd;
/*     */         } 
/* 170 */         attributedCharacterIterator1 = a(c1, d2, node, paramFloat);
/*     */         
/* 172 */         d1.a(attributedCharacterIterator1);
/*     */       } 
/*     */       
/* 175 */       if (i != 1) {
/* 176 */         attributedCharacterIterator1 = d1;
/*     */       }
/*     */       
/* 179 */       if (paramNode instanceof g) {
/* 180 */         g g = (g)paramNode;
/* 181 */         attributedCharacterIterator = g.a(attributedCharacterIterator1, paramc);
/*     */       } else {
/* 183 */         attributedCharacterIterator = attributedCharacterIterator1;
/*     */       } 
/*     */     } else {
/* 186 */       String str = f.a(paramNode);
/*     */       
/* 188 */       float f = d.a(paramc) * paramFloat;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 193 */       attributedCharacterIterator = a(str, paramd.d(), f, paramc).getIterator();
/*     */     } 
/* 195 */     return attributedCharacterIterator;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object[] a(c paramc, float paramFloat, c paramc1) {
/* 201 */     List<c> list = b.b(paramc);
/*     */     
/* 203 */     Font font = null;
/* 204 */     int i = 0;
/* 205 */     Iterator<c> iterator = list.iterator();
/* 206 */     boolean bool = true;
/* 207 */     while (bool) {
/* 208 */       c c1 = iterator.next();
/* 209 */       if (iterator.hasNext()) {
/* 210 */         i = c1.a();
/* 211 */         font = c1.b().a(paramFloat, i, paramc1, false);
/*     */         
/* 213 */         if (font != null)
/* 214 */           bool = false; 
/*     */         continue;
/*     */       } 
/* 217 */       i = c1.a();
/* 218 */       font = c1.b().a(paramFloat, i, paramc1, true);
/*     */       
/* 220 */       bool = false;
/*     */     } 
/*     */     
/* 223 */     return new Object[] { Integer.valueOf(i), font };
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
/*     */   public static TextLayout a(Graphics2D paramGraphics2D, AttributedString paramAttributedString, c paramc) {
/*     */     int i;
/*     */     TextLayout textLayout;
/* 241 */     AttributedCharacterIterator attributedCharacterIterator = paramAttributedString.getIterator();
/* 242 */     boolean bool = (attributedCharacterIterator.first() == Character.MAX_VALUE) ? true : false;
/*     */     
/* 244 */     FontRenderContext fontRenderContext1 = paramGraphics2D.getFontRenderContext();
/* 245 */     boolean bool1 = ((Boolean)paramc
/* 246 */       .a(d.h)).booleanValue();
/* 247 */     if (!bool) {
/* 248 */       Font font = (Font)paramAttributedString.getIterator().getAttribute(TextAttribute.FONT);
/*     */       
/* 250 */       if (font != null) {
/* 251 */         float f1 = font.getSize2D();
/* 252 */         float f2 = ((Float)paramc
/* 253 */           .a(d.f)).floatValue();
/* 254 */         i = bool1 & ((f1 >= f2) ? 1 : 0);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 259 */     FontRenderContext fontRenderContext2 = new FontRenderContext(fontRenderContext1.getTransform(), i, false);
/*     */ 
/*     */     
/* 262 */     if (bool) {
/* 263 */       textLayout = new TextLayout(" ", new Font("", 0, 0), fontRenderContext2);
/*     */     } else {
/*     */       
/* 266 */       synchronized (TextLayout.class) {
/*     */ 
/*     */         
/* 269 */         textLayout = new TextLayout(paramAttributedString.getIterator(), fontRenderContext2);
/*     */       } 
/*     */     } 
/*     */     
/* 273 */     return textLayout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float a(TextLayout paramTextLayout) {
/* 284 */     Rectangle2D rectangle2D = paramTextLayout.getBounds();
/* 285 */     float f1 = (float)rectangle2D.getWidth();
/* 286 */     float f2 = (float)rectangle2D.getX();
/* 287 */     if (f2 > 0.0F) {
/* 288 */       f1 += f2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 293 */     float f3 = paramTextLayout.getAdvance() - paramTextLayout.getVisibleAdvance();
/* 294 */     return f1 + f3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */   {
/*     */     private final float a;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final float b;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final float c;
/*     */ 
/*     */ 
/*     */     
/*     */     private final float d;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected a(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
/* 323 */       this.a = param1Float1;
/* 324 */       this.b = param1Float2;
/* 325 */       this.c = param1Float3;
/* 326 */       this.d = param1Float4;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float a() {
/* 335 */       return this.a;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float b() {
/* 344 */       return this.b;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float c() {
/* 353 */       return this.c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float d() {
/* 362 */       return this.d;
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
/*     */ 
/*     */   
/*     */   public static a a(TextLayout paramTextLayout, boolean paramBoolean) {
/*     */     float f4;
/* 379 */     Rectangle2D rectangle2D = paramTextLayout.getBounds();
/* 380 */     float f1 = (float)-rectangle2D.getY();
/*     */     
/* 382 */     float f2 = (float)(rectangle2D.getY() + rectangle2D.getHeight());
/* 383 */     float f3 = (float)rectangle2D.getX();
/*     */     
/* 385 */     if (f3 < 0.0F) {
/* 386 */       f4 = -f3;
/*     */     }
/* 388 */     else if (paramBoolean) {
/* 389 */       f4 = -f3;
/*     */     } else {
/* 391 */       f4 = 0.0F;
/*     */     } 
/*     */     
/* 394 */     float f5 = a(paramTextLayout);
/* 395 */     return new a(f1, f2, f4, f5);
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
/*     */   public static int a(@Nullable String paramString) {
/* 407 */     if (paramString == null) {
/* 408 */       return 0;
/*     */     }
/* 410 */     byte b1 = 0;
/* 411 */     int i = paramString.length();
/* 412 */     for (byte b2 = 0; b2 < i; b2++) {
/* 413 */       char c = paramString.charAt(b2);
/* 414 */       if (!Character.isHighSurrogate(c)) {
/* 415 */         int j = paramString.codePointAt(b2);
/* 416 */         if (!b.a(j)) {
/* 417 */           b1++;
/*     */         }
/*     */       } 
/*     */     } 
/* 421 */     return b1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/c/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */