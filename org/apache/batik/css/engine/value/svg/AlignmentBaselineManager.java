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
/*     */ public class AlignmentBaselineManager
/*     */   extends IdentifierManager
/*     */ {
/*  39 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  41 */     values.put("after-edge", SVGValueConstants.AFTER_EDGE_VALUE);
/*     */     
/*  43 */     values.put("alphabetic", SVGValueConstants.ALPHABETIC_VALUE);
/*     */     
/*  45 */     values.put("auto", SVGValueConstants.AUTO_VALUE);
/*     */     
/*  47 */     values.put("baseline", SVGValueConstants.BASELINE_VALUE);
/*     */     
/*  49 */     values.put("before-edge", SVGValueConstants.BEFORE_EDGE_VALUE);
/*     */     
/*  51 */     values.put("hanging", SVGValueConstants.HANGING_VALUE);
/*     */     
/*  53 */     values.put("ideographic", SVGValueConstants.IDEOGRAPHIC_VALUE);
/*     */     
/*  55 */     values.put("mathematical", SVGValueConstants.MATHEMATICAL_VALUE);
/*     */     
/*  57 */     values.put("middle", SVGValueConstants.MIDDLE_VALUE);
/*     */     
/*  59 */     values.put("text-after-edge", SVGValueConstants.TEXT_AFTER_EDGE_VALUE);
/*     */     
/*  61 */     values.put("text-before-edge", SVGValueConstants.TEXT_BEFORE_EDGE_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  91 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  99 */     return "alignment-baseline";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 107 */     return SVGValueConstants.AUTO_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 114 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/AlignmentBaselineManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */