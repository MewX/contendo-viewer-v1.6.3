/*     */ package jp.cssj.homare.impl.ua.svg;
/*     */ 
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jp.cssj.homare.css.f.x;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.a.f;
/*     */ import jp.cssj.sakae.c.a.b;
/*     */ import jp.cssj.sakae.c.a.c;
/*     */ import jp.cssj.sakae.c.a.d;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.g;
/*     */ import jp.cssj.sakae.c.a.i;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.CSSUtilities;
/*     */ import org.apache.batik.bridge.SVGTextElementBridge;
/*     */ import org.apache.batik.bridge.TextUtilities;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.gvt.font.GVTFont;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ public class h
/*     */   extends SVGTextElementBridge
/*     */ {
/*     */   protected final m a;
/*     */   protected final e b;
/*     */   protected final g c;
/*     */   protected final x d;
/*     */   
/*     */   public h(m ua) {
/*  37 */     this.a = ua;
/*  38 */     this.b = ua.q();
/*  39 */     this.c = (g)ua.j();
/*  40 */     this.d = ua.i();
/*     */   }
/*     */   
/*     */   public Bridge getInstance() {
/*  44 */     return (Bridge)new h(this.a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getFontList(BridgeContext ctx, Element element, Map<TextAttribute, Float> result) {
/*     */     byte style;
/*     */     short weight;
/*  55 */     result.put(TEXT_COMPOUND_ID, new WeakReference<>(element));
/*     */ 
/*     */     
/*  58 */     Float fontSizeFloat = TextUtilities.convertFontSize(element);
/*  59 */     float fontSize = fontSizeFloat.floatValue();
/*  60 */     result.put(TextAttribute.SIZE, fontSizeFloat);
/*     */ 
/*     */     
/*  63 */     result.put(TextAttribute.WIDTH, TextUtilities.convertFontStretch(element));
/*     */ 
/*     */     
/*  66 */     Float postureFloat = TextUtilities.convertFontStyle(element);
/*  67 */     result.put(TextAttribute.POSTURE, postureFloat);
/*     */ 
/*     */     
/*  70 */     Float weightFloat = TextUtilities.convertFontWeight(element);
/*  71 */     result.put(TextAttribute.WEIGHT, weightFloat);
/*     */     
/*  73 */     byte direction = 1;
/*  74 */     Value v = CSSUtilities.getComputedStyle(element, 59);
/*  75 */     String s = v.getStringValue();
/*  76 */     switch (s.charAt(0)) {
/*     */       case 'r':
/*  78 */         direction = 2;
/*     */         break;
/*     */       case 't':
/*  81 */         direction = 3;
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/*  86 */     result.put(TEXT_COMPOUND_DELIMITER, element);
/*     */ 
/*     */     
/*  89 */     Value val = CSSUtilities.getComputedStyle(element, 21);
/*  90 */     int len = val.getLength();
/*  91 */     b[] families = new b[len + this.d.b()]; int i;
/*  92 */     for (i = 0; i < len; i++) {
/*  93 */       Value it = val.item(i);
/*  94 */       String fontFamilyName = it.getStringValue();
/*  95 */       families[i] = b.a(fontFamilyName);
/*     */     } 
/*  97 */     for (i = 0; i < this.d.b(); i++) {
/*  98 */       families[i + len] = this.d.a(i);
/*     */     }
/*     */ 
/*     */     
/* 102 */     if (postureFloat.equals(TextAttribute.POSTURE_OBLIQUE)) {
/* 103 */       style = 2;
/*     */     } else {
/* 105 */       style = 1;
/*     */     } 
/*     */     
/* 108 */     if (weightFloat.equals(TextAttribute.WEIGHT_EXTRA_LIGHT)) {
/* 109 */       weight = 100;
/* 110 */     } else if (weightFloat.equals(TextAttribute.WEIGHT_LIGHT)) {
/* 111 */       weight = 200;
/* 112 */     } else if (weightFloat.equals(TextAttribute.WEIGHT_DEMILIGHT)) {
/* 113 */       weight = 300;
/* 114 */     } else if (weightFloat.equals(TextAttribute.WEIGHT_REGULAR)) {
/* 115 */       weight = 400;
/* 116 */     } else if (weightFloat.equals(TextAttribute.WEIGHT_SEMIBOLD)) {
/* 117 */       weight = 500;
/* 118 */     } else if (weightFloat.equals(TextAttribute.WEIGHT_BOLD)) {
/* 119 */       weight = 600;
/* 120 */     } else if (weightFloat.equals(TextAttribute.WEIGHT_HEAVY)) {
/* 121 */       weight = 700;
/* 122 */     } else if (weightFloat.equals(TextAttribute.WEIGHT_EXTRABOLD)) {
/* 123 */       weight = 800;
/* 124 */     } else if (weightFloat.equals(TextAttribute.WEIGHT_BOLD)) {
/* 125 */       weight = 900;
/*     */     } else {
/* 127 */       weight = 400;
/*     */     } 
/*     */     
/* 130 */     i i1 = new i(new c(families), fontSize, style, weight, direction, this.c);
/*     */     
/* 132 */     List<GVTFont> fontList = new ArrayList();
/*     */     
/* 134 */     d ms = this.b.a((jp.cssj.sakae.c.a.h)i1);
/* 135 */     for (int j = 0; j < ms.b(); j++) {
/* 136 */       f f = (f)ms.a(j);
/* 137 */       GVTFont font = new b((f)f, (jp.cssj.sakae.c.a.h)i1);
/* 138 */       fontList.add(font);
/*     */     } 
/*     */     
/* 141 */     if (!ctx.isDynamic())
/*     */     {
/*     */ 
/*     */       
/* 145 */       result.remove(TEXT_COMPOUND_DELIMITER);
/*     */     }
/* 147 */     return fontList;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */