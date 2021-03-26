/*     */ package org.apache.batik.css.engine;
/*     */ 
/*     */ import java.awt.SystemColor;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.css.engine.value.FloatValue;
/*     */ import org.apache.batik.css.engine.value.RGBColorValue;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.util.CSSConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SystemColorSupport
/*     */   implements CSSConstants
/*     */ {
/*     */   public static Value getSystemColor(String ident) {
/*  44 */     ident = ident.toLowerCase();
/*  45 */     SystemColor sc = (SystemColor)factories.get(ident);
/*  46 */     return (Value)new RGBColorValue((Value)new FloatValue((short)1, sc.getRed()), (Value)new FloatValue((short)1, sc.getGreen()), (Value)new FloatValue((short)1, sc.getBlue()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected static final Map factories = new HashMap<Object, Object>();
/*     */   static {
/*  57 */     factories.put("activeborder", SystemColor.windowBorder);
/*     */     
/*  59 */     factories.put("activecaption", SystemColor.activeCaption);
/*     */     
/*  61 */     factories.put("appworkspace", SystemColor.desktop);
/*     */     
/*  63 */     factories.put("background", SystemColor.desktop);
/*     */     
/*  65 */     factories.put("buttonface", SystemColor.control);
/*     */     
/*  67 */     factories.put("buttonhighlight", SystemColor.controlLtHighlight);
/*     */     
/*  69 */     factories.put("buttonshadow", SystemColor.controlDkShadow);
/*     */     
/*  71 */     factories.put("buttontext", SystemColor.controlText);
/*     */     
/*  73 */     factories.put("captiontext", SystemColor.activeCaptionText);
/*     */     
/*  75 */     factories.put("graytext", SystemColor.textInactiveText);
/*     */     
/*  77 */     factories.put("highlight", SystemColor.textHighlight);
/*     */     
/*  79 */     factories.put("highlighttext", SystemColor.textHighlightText);
/*     */     
/*  81 */     factories.put("inactiveborder", SystemColor.windowBorder);
/*     */     
/*  83 */     factories.put("inactivecaption", SystemColor.inactiveCaption);
/*     */     
/*  85 */     factories.put("inactivecaptiontext", SystemColor.inactiveCaptionText);
/*     */     
/*  87 */     factories.put("infobackground", SystemColor.info);
/*     */     
/*  89 */     factories.put("infotext", SystemColor.infoText);
/*     */     
/*  91 */     factories.put("menu", SystemColor.menu);
/*     */     
/*  93 */     factories.put("menutext", SystemColor.menuText);
/*     */     
/*  95 */     factories.put("scrollbar", SystemColor.scrollbar);
/*     */     
/*  97 */     factories.put("threeddarkshadow", SystemColor.controlDkShadow);
/*     */     
/*  99 */     factories.put("threedface", SystemColor.control);
/*     */     
/* 101 */     factories.put("threedhighlight", SystemColor.controlHighlight);
/*     */     
/* 103 */     factories.put("threedlightshadow", SystemColor.controlLtHighlight);
/*     */     
/* 105 */     factories.put("threedshadow", SystemColor.controlShadow);
/*     */     
/* 107 */     factories.put("window", SystemColor.window);
/*     */     
/* 109 */     factories.put("windowframe", SystemColor.windowBorder);
/*     */     
/* 111 */     factories.put("windowtext", SystemColor.windowText);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/SystemColorSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */