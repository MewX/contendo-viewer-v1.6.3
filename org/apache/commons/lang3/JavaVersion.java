/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import org.apache.commons.lang3.math.NumberUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum JavaVersion
/*     */ {
/*  33 */   JAVA_0_9(1.5F, "0.9"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   JAVA_1_1(1.1F, "1.1"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   JAVA_1_2(1.2F, "1.2"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   JAVA_1_3(1.3F, "1.3"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   JAVA_1_4(1.4F, "1.4"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   JAVA_1_5(1.5F, "1.5"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   JAVA_1_6(1.6F, "1.6"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   JAVA_1_7(1.7F, "1.7"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   JAVA_1_8(1.8F, "1.8"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   JAVA_1_9(9.0F, "9"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   JAVA_9(9.0F, "9"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   JAVA_10(10.0F, "10"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   JAVA_11(11.0F, "11"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   JAVA_12(12.0F, "12"),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   JAVA_13(13.0F, "13"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   JAVA_RECENT(maxVersion(), Float.toString(maxVersion()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final float value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JavaVersion(float value, String name) {
/* 140 */     this.value = value;
/* 141 */     this.name = name;
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
/*     */   public boolean atLeast(JavaVersion requiredVersion) {
/* 155 */     return (this.value >= requiredVersion.value);
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
/*     */   public boolean atMost(JavaVersion requiredVersion) {
/* 170 */     return (this.value <= requiredVersion.value);
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
/*     */   static JavaVersion getJavaVersion(String nom) {
/* 184 */     return get(nom);
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
/*     */   static JavaVersion get(String nom) {
/* 197 */     if ("0.9".equals(nom))
/* 198 */       return JAVA_0_9; 
/* 199 */     if ("1.1".equals(nom))
/* 200 */       return JAVA_1_1; 
/* 201 */     if ("1.2".equals(nom))
/* 202 */       return JAVA_1_2; 
/* 203 */     if ("1.3".equals(nom))
/* 204 */       return JAVA_1_3; 
/* 205 */     if ("1.4".equals(nom))
/* 206 */       return JAVA_1_4; 
/* 207 */     if ("1.5".equals(nom))
/* 208 */       return JAVA_1_5; 
/* 209 */     if ("1.6".equals(nom))
/* 210 */       return JAVA_1_6; 
/* 211 */     if ("1.7".equals(nom))
/* 212 */       return JAVA_1_7; 
/* 213 */     if ("1.8".equals(nom))
/* 214 */       return JAVA_1_8; 
/* 215 */     if ("9".equals(nom))
/* 216 */       return JAVA_9; 
/* 217 */     if ("10".equals(nom))
/* 218 */       return JAVA_10; 
/* 219 */     if ("11".equals(nom))
/* 220 */       return JAVA_11; 
/* 221 */     if ("12".equals(nom))
/* 222 */       return JAVA_12; 
/* 223 */     if ("13".equals(nom)) {
/* 224 */       return JAVA_13;
/*     */     }
/* 226 */     if (nom == null) {
/* 227 */       return null;
/*     */     }
/* 229 */     float v = toFloatVersion(nom);
/* 230 */     if (v - 1.0D < 1.0D) {
/* 231 */       int firstComma = Math.max(nom.indexOf('.'), nom.indexOf(','));
/* 232 */       int end = Math.max(nom.length(), nom.indexOf(',', firstComma));
/* 233 */       if (Float.parseFloat(nom.substring(firstComma + 1, end)) > 0.9F) {
/* 234 */         return JAVA_RECENT;
/*     */       }
/* 236 */     } else if (v > 10.0F) {
/* 237 */       return JAVA_RECENT;
/*     */     } 
/* 239 */     return null;
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
/*     */   public String toString() {
/* 252 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static float maxVersion() {
/* 261 */     float v = toFloatVersion(System.getProperty("java.specification.version", "99.0"));
/* 262 */     if (v > 0.0F) {
/* 263 */       return v;
/*     */     }
/* 265 */     return 99.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static float toFloatVersion(String value) {
/* 275 */     int defaultReturnValue = -1;
/* 276 */     if (value.contains(".")) {
/* 277 */       String[] toParse = value.split("\\.");
/* 278 */       if (toParse.length >= 2) {
/* 279 */         return NumberUtils.toFloat(toParse[0] + '.' + toParse[1], -1.0F);
/*     */       }
/*     */     } else {
/* 282 */       return NumberUtils.toFloat(value, -1.0F);
/*     */     } 
/* 284 */     return -1.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/JavaVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */