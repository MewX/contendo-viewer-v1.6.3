/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.value.IdentifierManager;
/*     */ import org.apache.batik.css.engine.value.StringMap;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DominantBaselineManager
/*     */   extends IdentifierManager
/*     */ {
/*  39 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  41 */     values.put("auto", SVGValueConstants.AUTO_VALUE);
/*     */     
/*  43 */     values.put("alphabetic", SVGValueConstants.ALPHABETIC_VALUE);
/*     */     
/*  45 */     values.put("central", SVGValueConstants.CENTRAL_VALUE);
/*     */     
/*  47 */     values.put("hanging", SVGValueConstants.HANGING_VALUE);
/*     */     
/*  49 */     values.put("ideographic", SVGValueConstants.IDEOGRAPHIC_VALUE);
/*     */     
/*  51 */     values.put("mathematical", SVGValueConstants.MATHEMATICAL_VALUE);
/*     */     
/*  53 */     values.put("middle", SVGValueConstants.MIDDLE_VALUE);
/*     */     
/*  55 */     values.put("no-change", SVGValueConstants.NO_CHANGE_VALUE);
/*     */     
/*  57 */     values.put("reset-size", SVGValueConstants.RESET_SIZE_VALUE);
/*     */     
/*  59 */     values.put("text-after-edge", SVGValueConstants.TEXT_AFTER_EDGE_VALUE);
/*     */     
/*  61 */     values.put("text-before-edge", SVGValueConstants.TEXT_BEFORE_EDGE_VALUE);
/*     */     
/*  63 */     values.put("text-bottom", SVGValueConstants.TEXT_BOTTOM_VALUE);
/*     */     
/*  65 */     values.put("text-top", SVGValueConstants.TEXT_TOP_VALUE);
/*     */     
/*  67 */     values.put("use-script", SVGValueConstants.USE_SCRIPT_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  97 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 105 */     return "dominant-baseline";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 113 */     return SVGValueConstants.AUTO_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 120 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/DominantBaselineManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */