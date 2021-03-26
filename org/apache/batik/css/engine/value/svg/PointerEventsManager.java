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
/*     */ public class PointerEventsManager
/*     */   extends IdentifierManager
/*     */ {
/*  39 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  41 */     values.put("all", SVGValueConstants.ALL_VALUE);
/*     */     
/*  43 */     values.put("fill", SVGValueConstants.FILL_VALUE);
/*     */     
/*  45 */     values.put("fillstroke", SVGValueConstants.FILLSTROKE_VALUE);
/*     */     
/*  47 */     values.put("none", SVGValueConstants.NONE_VALUE);
/*     */     
/*  49 */     values.put("painted", SVGValueConstants.PAINTED_VALUE);
/*     */     
/*  51 */     values.put("stroke", SVGValueConstants.STROKE_VALUE);
/*     */     
/*  53 */     values.put("visible", SVGValueConstants.VISIBLE_VALUE);
/*     */     
/*  55 */     values.put("visiblefill", SVGValueConstants.VISIBLEFILL_VALUE);
/*     */     
/*  57 */     values.put("visiblefillstroke", SVGValueConstants.VISIBLEFILLSTROKE_VALUE);
/*     */     
/*  59 */     values.put("visiblepainted", SVGValueConstants.VISIBLEPAINTED_VALUE);
/*     */     
/*  61 */     values.put("visiblestroke", SVGValueConstants.VISIBLESTROKE_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  70 */     return true;
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
/*  99 */     return "pointer-events";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 107 */     return SVGValueConstants.VISIBLEPAINTED_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 114 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/PointerEventsManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */