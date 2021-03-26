/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.value.IdentifierManager;
/*     */ import org.apache.batik.css.engine.value.StringMap;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.ValueConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VisibilityManager
/*     */   extends IdentifierManager
/*     */ {
/*  41 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  43 */     values.put("visible", ValueConstants.VISIBLE_VALUE);
/*     */     
/*  45 */     values.put("hidden", ValueConstants.HIDDEN_VALUE);
/*     */     
/*  47 */     values.put("collapse", ValueConstants.COLLAPSE_VALUE);
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
/*  85 */     return "visibility";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  93 */     return ValueConstants.VISIBLE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 100 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/VisibilityManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */