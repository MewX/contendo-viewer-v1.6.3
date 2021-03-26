/*     */ package org.apache.batik.css.engine;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StyleMap
/*     */ {
/*     */   public static final short IMPORTANT_MASK = 1;
/*     */   public static final short COMPUTED_MASK = 2;
/*     */   public static final short NULL_CASCADED_MASK = 4;
/*     */   public static final short INHERITED_MASK = 8;
/*     */   public static final short LINE_HEIGHT_RELATIVE_MASK = 16;
/*     */   public static final short FONT_SIZE_RELATIVE_MASK = 32;
/*     */   public static final short COLOR_RELATIVE_MASK = 64;
/*     */   public static final short PARENT_RELATIVE_MASK = 128;
/*     */   public static final short BLOCK_WIDTH_RELATIVE_MASK = 256;
/*     */   public static final short BLOCK_HEIGHT_RELATIVE_MASK = 512;
/*     */   public static final short BOX_RELATIVE_MASK = 1024;
/*     */   public static final short ORIGIN_MASK = -8192;
/*     */   public static final short USER_AGENT_ORIGIN = 0;
/*     */   public static final short USER_ORIGIN = 8192;
/*     */   public static final short NON_CSS_ORIGIN = 16384;
/*     */   public static final short AUTHOR_ORIGIN = 24576;
/*     */   public static final short INLINE_AUTHOR_ORIGIN = -32768;
/*     */   public static final short OVERRIDE_ORIGIN = -24576;
/*     */   protected Value[] values;
/*     */   protected short[] masks;
/*     */   protected boolean fixedCascadedValues;
/*     */   
/*     */   public StyleMap(int size) {
/*  79 */     this.values = new Value[size];
/*  80 */     this.masks = new short[size];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFixedCascadedValues() {
/*  87 */     return this.fixedCascadedValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFixedCascadedStyle(boolean b) {
/*  94 */     this.fixedCascadedValues = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getValue(int i) {
/* 101 */     return this.values[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMask(int i) {
/* 108 */     return this.masks[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isImportant(int i) {
/* 115 */     return ((this.masks[i] & 0x1) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComputed(int i) {
/* 122 */     return ((this.masks[i] & 0x2) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNullCascaded(int i) {
/* 129 */     return ((this.masks[i] & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInherited(int i) {
/* 137 */     return ((this.masks[i] & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getOrigin(int i) {
/* 144 */     return (short)(this.masks[i] & 0xFFFFE000);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isColorRelative(int i) {
/* 151 */     return ((this.masks[i] & 0x40) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isParentRelative(int i) {
/* 159 */     return ((this.masks[i] & 0x80) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLineHeightRelative(int i) {
/* 166 */     return ((this.masks[i] & 0x10) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFontSizeRelative(int i) {
/* 173 */     return ((this.masks[i] & 0x20) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlockWidthRelative(int i) {
/* 181 */     return ((this.masks[i] & 0x100) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlockHeightRelative(int i) {
/* 189 */     return ((this.masks[i] & 0x200) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putValue(int i, Value v) {
/* 198 */     this.values[i] = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putMask(int i, short m) {
/* 207 */     this.masks[i] = m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putImportant(int i, boolean b) {
/* 214 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x1); }
/* 215 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFFFE); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putOrigin(int i, short val) {
/* 222 */     this.masks[i] = (short)(this.masks[i] & 0x1FFF);
/* 223 */     this.masks[i] = (short)(this.masks[i] | (short)(val & 0xFFFFE000));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putComputed(int i, boolean b) {
/* 230 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x2); }
/* 231 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFFFD); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putNullCascaded(int i, boolean b) {
/* 238 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x4); }
/* 239 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFFFB); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putInherited(int i, boolean b) {
/* 247 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x8); }
/* 248 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFFF7); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putColorRelative(int i, boolean b) {
/* 255 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x40); }
/* 256 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFFBF); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putParentRelative(int i, boolean b) {
/* 263 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x80); }
/* 264 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFF7F); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putLineHeightRelative(int i, boolean b) {
/* 271 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x10); }
/* 272 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFFEF); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putFontSizeRelative(int i, boolean b) {
/* 279 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x20); }
/* 280 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFFDF); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putBlockWidthRelative(int i, boolean b) {
/* 287 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x100); }
/* 288 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFEFF); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void putBlockHeightRelative(int i, boolean b) {
/* 295 */     if (b) { this.masks[i] = (short)(this.masks[i] | 0x200); }
/* 296 */     else { this.masks[i] = (short)(this.masks[i] & 0xFFFFFDFF); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(CSSEngine eng) {
/* 306 */     int nSlots = this.values.length;
/* 307 */     StringBuffer sb = new StringBuffer(nSlots * 8);
/* 308 */     for (int i = 0; i < nSlots; i++) {
/* 309 */       Value v = this.values[i];
/* 310 */       if (v != null) {
/*     */         
/* 312 */         sb.append(eng.getPropertyName(i));
/* 313 */         sb.append(": ");
/* 314 */         sb.append(v);
/* 315 */         if (isImportant(i)) sb.append(" !important"); 
/* 316 */         sb.append(";\n");
/*     */       } 
/* 318 */     }  return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/StyleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */