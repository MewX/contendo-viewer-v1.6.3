/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CFFOperator
/*     */ {
/*  30 */   private Key operatorKey = null;
/*  31 */   private String operatorName = null;
/*     */ 
/*     */   
/*     */   private CFFOperator(Key key, String name) {
/*  35 */     setKey(key);
/*  36 */     setName(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Key getKey() {
/*  45 */     return this.operatorKey;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setKey(Key key) {
/*  50 */     this.operatorKey = key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  59 */     return this.operatorName;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setName(String name) {
/*  64 */     this.operatorName = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  73 */     return getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  82 */     return getKey().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/*  91 */     if (object instanceof CFFOperator) {
/*     */       
/*  93 */       CFFOperator that = (CFFOperator)object;
/*  94 */       return getKey().equals(that.getKey());
/*     */     } 
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void register(Key key, String name) {
/* 101 */     CFFOperator operator = new CFFOperator(key, name);
/* 102 */     keyMap.put(key, operator);
/* 103 */     nameMap.put(name, operator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CFFOperator getOperator(Key key) {
/* 113 */     return keyMap.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CFFOperator getOperator(String name) {
/* 123 */     return nameMap.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Key
/*     */   {
/* 132 */     private int[] value = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Key(int b0) {
/* 140 */       this(new int[] { b0 });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Key(int b0, int b1) {
/* 150 */       this(new int[] { b0, b1 });
/*     */     }
/*     */ 
/*     */     
/*     */     private Key(int[] value) {
/* 155 */       setValue(value);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int[] getValue() {
/* 164 */       return this.value;
/*     */     }
/*     */ 
/*     */     
/*     */     private void setValue(int[] value) {
/* 169 */       this.value = value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 178 */       return Arrays.toString(getValue());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 187 */       return Arrays.hashCode(getValue());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object object) {
/* 196 */       if (object instanceof Key) {
/*     */         
/* 198 */         Key that = (Key)object;
/* 199 */         return Arrays.equals(getValue(), that.getValue());
/*     */       } 
/* 201 */       return false;
/*     */     }
/*     */   }
/*     */   
/* 205 */   private static Map<Key, CFFOperator> keyMap = new LinkedHashMap<Key, CFFOperator>(52);
/* 206 */   private static Map<String, CFFOperator> nameMap = new LinkedHashMap<String, CFFOperator>(52);
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 211 */     register(new Key(0), "version");
/* 212 */     register(new Key(1), "Notice");
/* 213 */     register(new Key(12, 0), "Copyright");
/* 214 */     register(new Key(2), "FullName");
/* 215 */     register(new Key(3), "FamilyName");
/* 216 */     register(new Key(4), "Weight");
/* 217 */     register(new Key(12, 1), "isFixedPitch");
/* 218 */     register(new Key(12, 2), "ItalicAngle");
/* 219 */     register(new Key(12, 3), "UnderlinePosition");
/* 220 */     register(new Key(12, 4), "UnderlineThickness");
/* 221 */     register(new Key(12, 5), "PaintType");
/* 222 */     register(new Key(12, 6), "CharstringType");
/* 223 */     register(new Key(12, 7), "FontMatrix");
/* 224 */     register(new Key(13), "UniqueID");
/* 225 */     register(new Key(5), "FontBBox");
/* 226 */     register(new Key(12, 8), "StrokeWidth");
/* 227 */     register(new Key(14), "XUID");
/* 228 */     register(new Key(15), "charset");
/* 229 */     register(new Key(16), "Encoding");
/* 230 */     register(new Key(17), "CharStrings");
/* 231 */     register(new Key(18), "Private");
/* 232 */     register(new Key(12, 20), "SyntheticBase");
/* 233 */     register(new Key(12, 21), "PostScript");
/* 234 */     register(new Key(12, 22), "BaseFontName");
/* 235 */     register(new Key(12, 23), "BaseFontBlend");
/* 236 */     register(new Key(12, 30), "ROS");
/* 237 */     register(new Key(12, 31), "CIDFontVersion");
/* 238 */     register(new Key(12, 32), "CIDFontRevision");
/* 239 */     register(new Key(12, 33), "CIDFontType");
/* 240 */     register(new Key(12, 34), "CIDCount");
/* 241 */     register(new Key(12, 35), "UIDBase");
/* 242 */     register(new Key(12, 36), "FDArray");
/* 243 */     register(new Key(12, 37), "FDSelect");
/* 244 */     register(new Key(12, 38), "FontName");
/*     */ 
/*     */     
/* 247 */     register(new Key(6), "BlueValues");
/* 248 */     register(new Key(7), "OtherBlues");
/* 249 */     register(new Key(8), "FamilyBlues");
/* 250 */     register(new Key(9), "FamilyOtherBlues");
/* 251 */     register(new Key(12, 9), "BlueScale");
/* 252 */     register(new Key(12, 10), "BlueShift");
/* 253 */     register(new Key(12, 11), "BlueFuzz");
/* 254 */     register(new Key(10), "StdHW");
/* 255 */     register(new Key(11), "StdVW");
/* 256 */     register(new Key(12, 12), "StemSnapH");
/* 257 */     register(new Key(12, 13), "StemSnapV");
/* 258 */     register(new Key(12, 14), "ForceBold");
/* 259 */     register(new Key(12, 15), "LanguageGroup");
/* 260 */     register(new Key(12, 16), "ExpansionFactor");
/* 261 */     register(new Key(12, 17), "initialRandomSeed");
/* 262 */     register(new Key(19), "Subrs");
/* 263 */     register(new Key(20), "defaultWidthX");
/* 264 */     register(new Key(21), "nominalWidthX");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */