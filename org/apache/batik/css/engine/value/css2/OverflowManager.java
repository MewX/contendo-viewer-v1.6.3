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
/*     */ public class OverflowManager
/*     */   extends IdentifierManager
/*     */ {
/*  40 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  42 */     values.put("auto", ValueConstants.AUTO_VALUE);
/*     */     
/*  44 */     values.put("hidden", ValueConstants.HIDDEN_VALUE);
/*     */     
/*  46 */     values.put("scroll", ValueConstants.SCROLL_VALUE);
/*     */     
/*  48 */     values.put("visible", ValueConstants.VISIBLE_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  78 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  86 */     return "overflow";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  94 */     return ValueConstants.VISIBLE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 101 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/OverflowManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */