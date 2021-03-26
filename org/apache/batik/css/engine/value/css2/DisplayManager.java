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
/*     */ public class DisplayManager
/*     */   extends IdentifierManager
/*     */ {
/*  40 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  42 */     values.put("block", ValueConstants.BLOCK_VALUE);
/*     */     
/*  44 */     values.put("compact", ValueConstants.COMPACT_VALUE);
/*     */     
/*  46 */     values.put("inline", ValueConstants.INLINE_VALUE);
/*     */     
/*  48 */     values.put("inline-table", ValueConstants.INLINE_TABLE_VALUE);
/*     */     
/*  50 */     values.put("list-item", ValueConstants.LIST_ITEM_VALUE);
/*     */     
/*  52 */     values.put("marker", ValueConstants.MARKER_VALUE);
/*     */     
/*  54 */     values.put("none", ValueConstants.NONE_VALUE);
/*     */     
/*  56 */     values.put("run-in", ValueConstants.RUN_IN_VALUE);
/*     */     
/*  58 */     values.put("table", ValueConstants.TABLE_VALUE);
/*     */     
/*  60 */     values.put("table-caption", ValueConstants.TABLE_CAPTION_VALUE);
/*     */     
/*  62 */     values.put("table-cell", ValueConstants.TABLE_CELL_VALUE);
/*     */     
/*  64 */     values.put("table-column", ValueConstants.TABLE_COLUMN_VALUE);
/*     */     
/*  66 */     values.put("table-column-group", ValueConstants.TABLE_COLUMN_GROUP_VALUE);
/*     */     
/*  68 */     values.put("table-footer-group", ValueConstants.TABLE_FOOTER_GROUP_VALUE);
/*     */     
/*  70 */     values.put("table-header-group", ValueConstants.TABLE_HEADER_GROUP_VALUE);
/*     */     
/*  72 */     values.put("table-row", ValueConstants.TABLE_ROW_VALUE);
/*     */     
/*  74 */     values.put("table-row-group", ValueConstants.TABLE_ROW_GROUP_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/* 104 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 112 */     return "display";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 120 */     return ValueConstants.INLINE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 127 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/DisplayManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */