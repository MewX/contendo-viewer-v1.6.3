/*     */ package org.apache.batik.css.engine;
/*     */ 
/*     */ import org.apache.batik.css.engine.value.ShorthandManager;
/*     */ import org.apache.batik.css.engine.value.ValueConstants;
/*     */ import org.apache.batik.css.engine.value.ValueManager;
/*     */ import org.apache.batik.css.engine.value.css2.ClipManager;
/*     */ import org.apache.batik.css.engine.value.css2.CursorManager;
/*     */ import org.apache.batik.css.engine.value.css2.DirectionManager;
/*     */ import org.apache.batik.css.engine.value.css2.DisplayManager;
/*     */ import org.apache.batik.css.engine.value.css2.FontFamilyManager;
/*     */ import org.apache.batik.css.engine.value.css2.FontShorthandManager;
/*     */ import org.apache.batik.css.engine.value.css2.FontSizeAdjustManager;
/*     */ import org.apache.batik.css.engine.value.css2.FontSizeManager;
/*     */ import org.apache.batik.css.engine.value.css2.FontStretchManager;
/*     */ import org.apache.batik.css.engine.value.css2.FontStyleManager;
/*     */ import org.apache.batik.css.engine.value.css2.FontVariantManager;
/*     */ import org.apache.batik.css.engine.value.css2.FontWeightManager;
/*     */ import org.apache.batik.css.engine.value.css2.OverflowManager;
/*     */ import org.apache.batik.css.engine.value.css2.SrcManager;
/*     */ import org.apache.batik.css.engine.value.css2.TextDecorationManager;
/*     */ import org.apache.batik.css.engine.value.css2.UnicodeBidiManager;
/*     */ import org.apache.batik.css.engine.value.css2.VisibilityManager;
/*     */ import org.apache.batik.css.engine.value.svg.AlignmentBaselineManager;
/*     */ import org.apache.batik.css.engine.value.svg.BaselineShiftManager;
/*     */ import org.apache.batik.css.engine.value.svg.ClipPathManager;
/*     */ import org.apache.batik.css.engine.value.svg.ClipRuleManager;
/*     */ import org.apache.batik.css.engine.value.svg.ColorInterpolationFiltersManager;
/*     */ import org.apache.batik.css.engine.value.svg.ColorInterpolationManager;
/*     */ import org.apache.batik.css.engine.value.svg.ColorManager;
/*     */ import org.apache.batik.css.engine.value.svg.ColorProfileManager;
/*     */ import org.apache.batik.css.engine.value.svg.ColorRenderingManager;
/*     */ import org.apache.batik.css.engine.value.svg.DominantBaselineManager;
/*     */ import org.apache.batik.css.engine.value.svg.EnableBackgroundManager;
/*     */ import org.apache.batik.css.engine.value.svg.FillRuleManager;
/*     */ import org.apache.batik.css.engine.value.svg.FilterManager;
/*     */ import org.apache.batik.css.engine.value.svg.GlyphOrientationHorizontalManager;
/*     */ import org.apache.batik.css.engine.value.svg.GlyphOrientationVerticalManager;
/*     */ import org.apache.batik.css.engine.value.svg.ImageRenderingManager;
/*     */ import org.apache.batik.css.engine.value.svg.KerningManager;
/*     */ import org.apache.batik.css.engine.value.svg.MarkerManager;
/*     */ import org.apache.batik.css.engine.value.svg.MarkerShorthandManager;
/*     */ import org.apache.batik.css.engine.value.svg.MaskManager;
/*     */ import org.apache.batik.css.engine.value.svg.OpacityManager;
/*     */ import org.apache.batik.css.engine.value.svg.PointerEventsManager;
/*     */ import org.apache.batik.css.engine.value.svg.SVGColorManager;
/*     */ import org.apache.batik.css.engine.value.svg.SVGPaintManager;
/*     */ import org.apache.batik.css.engine.value.svg.ShapeRenderingManager;
/*     */ import org.apache.batik.css.engine.value.svg.SpacingManager;
/*     */ import org.apache.batik.css.engine.value.svg.StrokeDasharrayManager;
/*     */ import org.apache.batik.css.engine.value.svg.StrokeDashoffsetManager;
/*     */ import org.apache.batik.css.engine.value.svg.StrokeLinecapManager;
/*     */ import org.apache.batik.css.engine.value.svg.StrokeLinejoinManager;
/*     */ import org.apache.batik.css.engine.value.svg.StrokeMiterlimitManager;
/*     */ import org.apache.batik.css.engine.value.svg.StrokeWidthManager;
/*     */ import org.apache.batik.css.engine.value.svg.TextAnchorManager;
/*     */ import org.apache.batik.css.engine.value.svg.TextRenderingManager;
/*     */ import org.apache.batik.css.engine.value.svg.WritingModeManager;
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
/*     */ public class SVGCSSEngine
/*     */   extends CSSEngine
/*     */ {
/*     */   public SVGCSSEngine(Document doc, ParsedURL uri, ExtendedParser p, CSSContext ctx) {
/* 101 */     super(doc, uri, p, SVG_VALUE_MANAGERS, SVG_SHORTHAND_MANAGERS, null, null, "style", null, "class", true, null, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     this.lineHeightIndex = this.fontSizeIndex;
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
/*     */   public SVGCSSEngine(Document doc, ParsedURL uri, ExtendedParser p, ValueManager[] vms, ShorthandManager[] sms, CSSContext ctx) {
/* 131 */     super(doc, uri, p, mergeArrays(SVG_VALUE_MANAGERS, vms), mergeArrays(SVG_SHORTHAND_MANAGERS, sms), null, null, "style", null, "class", true, null, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     this.lineHeightIndex = this.fontSizeIndex;
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
/*     */   protected SVGCSSEngine(Document doc, ParsedURL uri, ExtendedParser p, ValueManager[] vms, ShorthandManager[] sms, String[] pe, String sns, String sln, String cns, String cln, boolean hints, String hintsNS, CSSContext ctx) {
/* 159 */     super(doc, uri, p, mergeArrays(SVG_VALUE_MANAGERS, vms), mergeArrays(SVG_SHORTHAND_MANAGERS, sms), pe, sns, sln, cns, cln, hints, hintsNS, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     this.lineHeightIndex = this.fontSizeIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static ValueManager[] mergeArrays(ValueManager[] a1, ValueManager[] a2) {
/* 173 */     ValueManager[] result = new ValueManager[a1.length + a2.length];
/* 174 */     System.arraycopy(a1, 0, result, 0, a1.length);
/* 175 */     System.arraycopy(a2, 0, result, a1.length, a2.length);
/* 176 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static ShorthandManager[] mergeArrays(ShorthandManager[] a1, ShorthandManager[] a2) {
/* 184 */     ShorthandManager[] result = new ShorthandManager[a1.length + a2.length];
/*     */     
/* 186 */     System.arraycopy(a1, 0, result, 0, a1.length);
/* 187 */     System.arraycopy(a2, 0, result, a1.length, a2.length);
/* 188 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public static final ValueManager[] SVG_VALUE_MANAGERS = new ValueManager[] { (ValueManager)new AlignmentBaselineManager(), (ValueManager)new BaselineShiftManager(), (ValueManager)new ClipManager(), (ValueManager)new ClipPathManager(), (ValueManager)new ClipRuleManager(), (ValueManager)new ColorManager(), (ValueManager)new ColorInterpolationManager(), (ValueManager)new ColorInterpolationFiltersManager(), (ValueManager)new ColorProfileManager(), (ValueManager)new ColorRenderingManager(), (ValueManager)new CursorManager(), (ValueManager)new DirectionManager(), (ValueManager)new DisplayManager(), (ValueManager)new DominantBaselineManager(), (ValueManager)new EnableBackgroundManager(), (ValueManager)new SVGPaintManager("fill"), (ValueManager)new OpacityManager("fill-opacity", true), (ValueManager)new FillRuleManager(), (ValueManager)new FilterManager(), (ValueManager)new SVGColorManager("flood-color"), (ValueManager)new OpacityManager("flood-opacity", false), (ValueManager)new FontFamilyManager(), (ValueManager)new FontSizeManager(), (ValueManager)new FontSizeAdjustManager(), (ValueManager)new FontStretchManager(), (ValueManager)new FontStyleManager(), (ValueManager)new FontVariantManager(), (ValueManager)new FontWeightManager(), (ValueManager)new GlyphOrientationHorizontalManager(), (ValueManager)new GlyphOrientationVerticalManager(), (ValueManager)new ImageRenderingManager(), (ValueManager)new KerningManager(), (ValueManager)new SpacingManager("letter-spacing"), (ValueManager)new SVGColorManager("lighting-color", ValueConstants.WHITE_RGB_VALUE), (ValueManager)new MarkerManager("marker-end"), (ValueManager)new MarkerManager("marker-mid"), (ValueManager)new MarkerManager("marker-start"), (ValueManager)new MaskManager(), (ValueManager)new OpacityManager("opacity", false), (ValueManager)new OverflowManager(), (ValueManager)new PointerEventsManager(), (ValueManager)new SrcManager(), (ValueManager)new ShapeRenderingManager(), (ValueManager)new SVGColorManager("stop-color"), (ValueManager)new OpacityManager("stop-opacity", false), (ValueManager)new SVGPaintManager("stroke", ValueConstants.NONE_VALUE), (ValueManager)new StrokeDasharrayManager(), (ValueManager)new StrokeDashoffsetManager(), (ValueManager)new StrokeLinecapManager(), (ValueManager)new StrokeLinejoinManager(), (ValueManager)new StrokeMiterlimitManager(), (ValueManager)new OpacityManager("stroke-opacity", true), (ValueManager)new StrokeWidthManager(), (ValueManager)new TextAnchorManager(), (ValueManager)new TextDecorationManager(), (ValueManager)new TextRenderingManager(), (ValueManager)new UnicodeBidiManager(), (ValueManager)new VisibilityManager(), (ValueManager)new SpacingManager("word-spacing"), (ValueManager)new WritingModeManager() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 273 */   public static final ShorthandManager[] SVG_SHORTHAND_MANAGERS = new ShorthandManager[] { (ShorthandManager)new FontShorthandManager(), (ShorthandManager)new MarkerShorthandManager() };
/*     */   public static final int ALIGNMENT_BASELINE_INDEX = 0;
/*     */   public static final int BASELINE_SHIFT_INDEX = 1;
/*     */   public static final int CLIP_INDEX = 2;
/*     */   public static final int CLIP_PATH_INDEX = 3;
/*     */   public static final int CLIP_RULE_INDEX = 4;
/*     */   public static final int COLOR_INDEX = 5;
/*     */   public static final int COLOR_INTERPOLATION_INDEX = 6;
/*     */   public static final int COLOR_INTERPOLATION_FILTERS_INDEX = 7;
/*     */   public static final int COLOR_PROFILE_INDEX = 8;
/*     */   public static final int COLOR_RENDERING_INDEX = 9;
/*     */   public static final int CURSOR_INDEX = 10;
/*     */   public static final int DIRECTION_INDEX = 11;
/*     */   public static final int DISPLAY_INDEX = 12;
/*     */   public static final int DOMINANT_BASELINE_INDEX = 13;
/*     */   public static final int ENABLE_BACKGROUND_INDEX = 14;
/*     */   public static final int FILL_INDEX = 15;
/*     */   public static final int FILL_OPACITY_INDEX = 16;
/*     */   public static final int FILL_RULE_INDEX = 17;
/*     */   public static final int FILTER_INDEX = 18;
/*     */   public static final int FLOOD_COLOR_INDEX = 19;
/*     */   public static final int FLOOD_OPACITY_INDEX = 20;
/*     */   public static final int FONT_FAMILY_INDEX = 21;
/*     */   public static final int FONT_SIZE_INDEX = 22;
/*     */   public static final int FONT_SIZE_ADJUST_INDEX = 23;
/*     */   public static final int FONT_STRETCH_INDEX = 24;
/*     */   public static final int FONT_STYLE_INDEX = 25;
/*     */   public static final int FONT_VARIANT_INDEX = 26;
/*     */   public static final int FONT_WEIGHT_INDEX = 27;
/*     */   public static final int GLYPH_ORIENTATION_HORIZONTAL_INDEX = 28;
/*     */   public static final int GLYPH_ORIENTATION_VERTICAL_INDEX = 29;
/*     */   public static final int IMAGE_RENDERING_INDEX = 30;
/*     */   public static final int KERNING_INDEX = 31;
/*     */   public static final int LETTER_SPACING_INDEX = 32;
/*     */   public static final int LIGHTING_COLOR_INDEX = 33;
/*     */   public static final int MARKER_END_INDEX = 34;
/*     */   public static final int MARKER_MID_INDEX = 35;
/*     */   public static final int MARKER_START_INDEX = 36;
/*     */   public static final int MASK_INDEX = 37;
/*     */   public static final int OPACITY_INDEX = 38;
/*     */   public static final int OVERFLOW_INDEX = 39;
/*     */   public static final int POINTER_EVENTS_INDEX = 40;
/*     */   public static final int SRC_INDEX = 41;
/*     */   public static final int SHAPE_RENDERING_INDEX = 42;
/*     */   public static final int STOP_COLOR_INDEX = 43;
/*     */   public static final int STOP_OPACITY_INDEX = 44;
/*     */   public static final int STROKE_INDEX = 45;
/*     */   public static final int STROKE_DASHARRAY_INDEX = 46;
/*     */   public static final int STROKE_DASHOFFSET_INDEX = 47;
/*     */   public static final int STROKE_LINECAP_INDEX = 48;
/*     */   public static final int STROKE_LINEJOIN_INDEX = 49;
/*     */   public static final int STROKE_MITERLIMIT_INDEX = 50;
/*     */   public static final int STROKE_OPACITY_INDEX = 51;
/*     */   public static final int STROKE_WIDTH_INDEX = 52;
/*     */   public static final int TEXT_ANCHOR_INDEX = 53;
/*     */   public static final int TEXT_DECORATION_INDEX = 54;
/*     */   public static final int TEXT_RENDERING_INDEX = 55;
/*     */   public static final int UNICODE_BIDI_INDEX = 56;
/*     */   public static final int VISIBILITY_INDEX = 57;
/*     */   public static final int WORD_SPACING_INDEX = 58;
/*     */   public static final int WRITING_MODE_INDEX = 59;
/*     */   public static final int FINAL_INDEX = 59;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/SVGCSSEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */