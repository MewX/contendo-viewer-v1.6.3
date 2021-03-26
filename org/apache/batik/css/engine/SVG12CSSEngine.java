/*     */ package org.apache.batik.css.engine;
/*     */ 
/*     */ import org.apache.batik.css.engine.value.ShorthandManager;
/*     */ import org.apache.batik.css.engine.value.ValueManager;
/*     */ import org.apache.batik.css.engine.value.svg.OpacityManager;
/*     */ import org.apache.batik.css.engine.value.svg.SVGColorManager;
/*     */ import org.apache.batik.css.engine.value.svg12.LineHeightManager;
/*     */ import org.apache.batik.css.engine.value.svg12.MarginLengthManager;
/*     */ import org.apache.batik.css.engine.value.svg12.MarginShorthandManager;
/*     */ import org.apache.batik.css.engine.value.svg12.TextAlignManager;
/*     */ import org.apache.batik.css.parser.ExtendedParser;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVG12CSSEngine
/*     */   extends SVGCSSEngine
/*     */ {
/*     */   public SVG12CSSEngine(Document doc, ParsedURL uri, ExtendedParser p, CSSContext ctx) {
/*  54 */     super(doc, uri, p, SVG_VALUE_MANAGERS, SVG_SHORTHAND_MANAGERS, ctx);
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.lineHeightIndex = 60;
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
/*     */   public SVG12CSSEngine(Document doc, ParsedURL uri, ExtendedParser p, ValueManager[] vms, ShorthandManager[] sms, CSSContext ctx) {
/*  76 */     super(doc, uri, p, mergeArrays(SVG_VALUE_MANAGERS, vms), mergeArrays(SVG_SHORTHAND_MANAGERS, sms), ctx);
/*     */ 
/*     */ 
/*     */     
/*  80 */     this.lineHeightIndex = 60;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public static final ValueManager[] SVG_VALUE_MANAGERS = new ValueManager[] { (ValueManager)new LineHeightManager(), (ValueManager)new MarginLengthManager("indent"), (ValueManager)new MarginLengthManager("margin-bottom"), (ValueManager)new MarginLengthManager("margin-left"), (ValueManager)new MarginLengthManager("margin-right"), (ValueManager)new MarginLengthManager("margin-top"), (ValueManager)new SVGColorManager("solid-color"), (ValueManager)new OpacityManager("solid-opacity", true), (ValueManager)new TextAlignManager() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public static final ShorthandManager[] SVG_SHORTHAND_MANAGERS = new ShorthandManager[] { (ShorthandManager)new MarginShorthandManager() };
/*     */   public static final int LINE_HEIGHT_INDEX = 60;
/*     */   public static final int INDENT_INDEX = 61;
/*     */   public static final int MARGIN_BOTTOM_INDEX = 62;
/*     */   public static final int MARGIN_LEFT_INDEX = 63;
/*     */   public static final int MARGIN_RIGHT_INDEX = 64;
/*     */   public static final int MARGIN_TOP_INDEX = 65;
/*     */   public static final int SOLID_COLOR_INDEX = 66;
/*     */   public static final int SOLID_OPACITY_INDEX = 67;
/*     */   public static final int TEXT_ALIGN_INDEX = 68;
/*     */   public static final int FINAL_INDEX = 68;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/SVG12CSSEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */