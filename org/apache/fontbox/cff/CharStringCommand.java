/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
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
/*     */ 
/*     */ public class CharStringCommand
/*     */ {
/*  32 */   private Key commandKey = null;
/*     */ 
/*     */   
/*     */   public static final Map<Key, String> TYPE1_VOCABULARY;
/*     */   
/*     */   public static final Map<Key, String> TYPE2_VOCABULARY;
/*     */ 
/*     */   
/*     */   public CharStringCommand(int b0) {
/*  41 */     setKey(new Key(b0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharStringCommand(int b0, int b1) {
/*  52 */     setKey(new Key(b0, b1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharStringCommand(int[] values) {
/*  62 */     setKey(new Key(values));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Key getKey() {
/*  71 */     return this.commandKey;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setKey(Key key) {
/*  76 */     this.commandKey = key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  85 */     String str = TYPE2_VOCABULARY.get(getKey());
/*  86 */     if (str == null)
/*     */     {
/*  88 */       str = TYPE1_VOCABULARY.get(getKey());
/*     */     }
/*  90 */     if (str == null)
/*     */     {
/*  92 */       return getKey().toString() + '|';
/*     */     }
/*  94 */     return str + '|';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 103 */     return getKey().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 112 */     if (object instanceof CharStringCommand) {
/*     */       
/* 114 */       CharStringCommand that = (CharStringCommand)object;
/* 115 */       return getKey().equals(that.getKey());
/*     */     } 
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Key
/*     */   {
/* 126 */     private int[] keyValues = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Key(int b0) {
/* 135 */       setValue(new int[] { b0 });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Key(int b0, int b1) {
/* 146 */       setValue(new int[] { b0, b1 });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Key(int[] values) {
/* 156 */       setValue(values);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int[] getValue() {
/* 166 */       return this.keyValues;
/*     */     }
/*     */ 
/*     */     
/*     */     private void setValue(int[] value) {
/* 171 */       this.keyValues = value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 180 */       return Arrays.toString(getValue());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 189 */       if (this.keyValues[0] == 12 && this.keyValues.length > 1)
/*     */       {
/* 191 */         return this.keyValues[0] ^ this.keyValues[1];
/*     */       }
/* 193 */       return this.keyValues[0];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object object) {
/* 202 */       if (object instanceof Key) {
/*     */         
/* 204 */         Key that = (Key)object;
/* 205 */         if (this.keyValues[0] == 12 && that.keyValues[0] == 12) {
/*     */           
/* 207 */           if (this.keyValues.length > 1 && that.keyValues.length > 1)
/*     */           {
/* 209 */             return (this.keyValues[1] == that.keyValues[1]);
/*     */           }
/* 211 */           return (this.keyValues.length == that.keyValues.length);
/*     */         } 
/* 213 */         return (this.keyValues[0] == that.keyValues[0]);
/*     */       } 
/* 215 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 226 */     Map<Key, String> map = new LinkedHashMap<Key, String>(26);
/* 227 */     map.put(new Key(1), "hstem");
/* 228 */     map.put(new Key(3), "vstem");
/* 229 */     map.put(new Key(4), "vmoveto");
/* 230 */     map.put(new Key(5), "rlineto");
/* 231 */     map.put(new Key(6), "hlineto");
/* 232 */     map.put(new Key(7), "vlineto");
/* 233 */     map.put(new Key(8), "rrcurveto");
/* 234 */     map.put(new Key(9), "closepath");
/* 235 */     map.put(new Key(10), "callsubr");
/* 236 */     map.put(new Key(11), "return");
/* 237 */     map.put(new Key(12), "escape");
/* 238 */     map.put(new Key(12, 0), "dotsection");
/* 239 */     map.put(new Key(12, 1), "vstem3");
/* 240 */     map.put(new Key(12, 2), "hstem3");
/* 241 */     map.put(new Key(12, 6), "seac");
/* 242 */     map.put(new Key(12, 7), "sbw");
/* 243 */     map.put(new Key(12, 12), "div");
/* 244 */     map.put(new Key(12, 16), "callothersubr");
/* 245 */     map.put(new Key(12, 17), "pop");
/* 246 */     map.put(new Key(12, 33), "setcurrentpoint");
/* 247 */     map.put(new Key(13), "hsbw");
/* 248 */     map.put(new Key(14), "endchar");
/* 249 */     map.put(new Key(21), "rmoveto");
/* 250 */     map.put(new Key(22), "hmoveto");
/* 251 */     map.put(new Key(30), "vhcurveto");
/* 252 */     map.put(new Key(31), "hvcurveto");
/*     */     
/* 254 */     TYPE1_VOCABULARY = Collections.unmodifiableMap(map);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     map = new LinkedHashMap<Key, String>(48);
/* 265 */     map.put(new Key(1), "hstem");
/* 266 */     map.put(new Key(3), "vstem");
/* 267 */     map.put(new Key(4), "vmoveto");
/* 268 */     map.put(new Key(5), "rlineto");
/* 269 */     map.put(new Key(6), "hlineto");
/* 270 */     map.put(new Key(7), "vlineto");
/* 271 */     map.put(new Key(8), "rrcurveto");
/* 272 */     map.put(new Key(10), "callsubr");
/* 273 */     map.put(new Key(11), "return");
/* 274 */     map.put(new Key(12), "escape");
/* 275 */     map.put(new Key(12, 3), "and");
/* 276 */     map.put(new Key(12, 4), "or");
/* 277 */     map.put(new Key(12, 5), "not");
/* 278 */     map.put(new Key(12, 9), "abs");
/* 279 */     map.put(new Key(12, 10), "add");
/* 280 */     map.put(new Key(12, 11), "sub");
/* 281 */     map.put(new Key(12, 12), "div");
/* 282 */     map.put(new Key(12, 14), "neg");
/* 283 */     map.put(new Key(12, 15), "eq");
/* 284 */     map.put(new Key(12, 18), "drop");
/* 285 */     map.put(new Key(12, 20), "put");
/* 286 */     map.put(new Key(12, 21), "get");
/* 287 */     map.put(new Key(12, 22), "ifelse");
/* 288 */     map.put(new Key(12, 23), "random");
/* 289 */     map.put(new Key(12, 24), "mul");
/* 290 */     map.put(new Key(12, 26), "sqrt");
/* 291 */     map.put(new Key(12, 27), "dup");
/* 292 */     map.put(new Key(12, 28), "exch");
/* 293 */     map.put(new Key(12, 29), "index");
/* 294 */     map.put(new Key(12, 30), "roll");
/* 295 */     map.put(new Key(12, 34), "hflex");
/* 296 */     map.put(new Key(12, 35), "flex");
/* 297 */     map.put(new Key(12, 36), "hflex1");
/* 298 */     map.put(new Key(12, 37), "flex1");
/* 299 */     map.put(new Key(14), "endchar");
/* 300 */     map.put(new Key(18), "hstemhm");
/* 301 */     map.put(new Key(19), "hintmask");
/* 302 */     map.put(new Key(20), "cntrmask");
/* 303 */     map.put(new Key(21), "rmoveto");
/* 304 */     map.put(new Key(22), "hmoveto");
/* 305 */     map.put(new Key(23), "vstemhm");
/* 306 */     map.put(new Key(24), "rcurveline");
/* 307 */     map.put(new Key(25), "rlinecurve");
/* 308 */     map.put(new Key(26), "vvcurveto");
/* 309 */     map.put(new Key(27), "hhcurveto");
/* 310 */     map.put(new Key(28), "shortint");
/* 311 */     map.put(new Key(29), "callgsubr");
/* 312 */     map.put(new Key(30), "vhcurveto");
/* 313 */     map.put(new Key(31), "hvcurveto");
/*     */     
/* 315 */     TYPE2_VOCABULARY = Collections.unmodifiableMap(map);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CharStringCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */