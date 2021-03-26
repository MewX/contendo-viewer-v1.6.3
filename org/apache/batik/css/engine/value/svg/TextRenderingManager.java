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
/*     */ public class TextRenderingManager
/*     */   extends IdentifierManager
/*     */ {
/*  39 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  41 */     values.put("auto", SVGValueConstants.AUTO_VALUE);
/*     */     
/*  43 */     values.put("optimizespeed", SVGValueConstants.OPTIMIZESPEED_VALUE);
/*     */     
/*  45 */     values.put("geometricprecision", SVGValueConstants.GEOMETRICPRECISION_VALUE);
/*     */     
/*  47 */     values.put("optimizelegibility", SVGValueConstants.OPTIMIZELEGIBILITY_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  77 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  85 */     return "text-rendering";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  93 */     return SVGValueConstants.AUTO_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 100 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/TextRenderingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */