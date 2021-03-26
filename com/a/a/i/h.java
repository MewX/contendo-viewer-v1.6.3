/*     */ package com.a.a.i;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class h
/*     */ {
/*     */   public static <T, V> T a(Object[] array, V v, c<T, V> cmp) {
/*  29 */     return a((T[])array, 0, array.length, v, cmp);
/*     */   }
/*     */   
/*     */   public static <T, V> T a(Object[] array, int offset, int length, V v, c<T, V> cmp) {
/*  33 */     int s = offset;
/*  34 */     int e = s + length - 1;
/*     */     
/*  36 */     while (s <= e) {
/*     */       
/*  38 */       int m = e + s >> 1;
/*     */ 
/*     */       
/*  41 */       int i = cmp.a((T)array[m], v, m);
/*  42 */       if (i > 0) {
/*  43 */         e = m - 1; continue;
/*  44 */       }  if (i < 0) {
/*  45 */         s = m + 1; continue;
/*     */       } 
/*  47 */       return (T)array[m];
/*     */     } 
/*     */ 
/*     */     
/*  51 */     return null;
/*     */   }
/*     */   
/*     */   public static <T, V> int b(Object[] array, V v, c<T, V> cmp) {
/*  55 */     return b(array, 0, array.length, v, (c)cmp);
/*     */   }
/*     */   
/*     */   public static <T, V> int b(Object[] array, int offset, int length, V v, c<T, V> cmp) {
/*  59 */     int s = offset;
/*  60 */     int e = s + length - 1;
/*     */     
/*  62 */     int m = 1;
/*  63 */     while (s <= e) {
/*     */       
/*  65 */       m = e + s >> 1;
/*     */ 
/*     */       
/*  68 */       int i = cmp.a((T)array[m], v, m);
/*  69 */       if (i > 0) {
/*  70 */         e = m - 1; continue;
/*  71 */       }  if (i < 0) {
/*  72 */         s = m + 1; continue;
/*     */       } 
/*  74 */       return m;
/*     */     } 
/*     */ 
/*     */     
/*  78 */     return -m;
/*     */   }
/*     */   
/*     */   public static <T, V> T a(List<T> list, V v, c<T, V> cmp) {
/*  82 */     int s = 0;
/*  83 */     int e = s + list.size() - 1;
/*     */ 
/*     */     
/*  86 */     if (!(list instanceof java.util.RandomAccess)) {
/*  87 */       return c(list, v, cmp);
/*     */     }
/*     */     
/*  90 */     while (s <= e) {
/*     */       
/*  92 */       int m = e + s >> 1;
/*     */ 
/*     */       
/*  95 */       int i = cmp.a(list.get(m), v, m);
/*  96 */       if (i > 0) {
/*  97 */         e = m - 1; continue;
/*  98 */       }  if (i < 0) {
/*  99 */         s = m + 1; continue;
/*     */       } 
/* 101 */       return list.get(m);
/*     */     } 
/*     */     
/* 104 */     return null;
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<V>, V> T a(List<T> list, V v) {
/* 108 */     int s = 0;
/* 109 */     int e = s + list.size() - 1;
/*     */ 
/*     */     
/* 112 */     if (!(list instanceof java.util.RandomAccess)) {
/* 113 */       return d(list, v);
/*     */     }
/*     */     
/* 116 */     while (s <= e) {
/*     */       
/* 118 */       int m = e + s >> 1;
/*     */ 
/*     */       
/* 121 */       int c = ((Comparable<V>)list.get(m)).compareTo(v);
/* 122 */       if (c > 0) {
/* 123 */         e = m - 1; continue;
/* 124 */       }  if (c < 0) {
/* 125 */         s = m + 1; continue;
/*     */       } 
/* 127 */       return list.get(m);
/*     */     } 
/*     */     
/* 130 */     return null;
/*     */   }
/*     */   
/*     */   public static <T, V> T b(List<T> list, V v, c<T, V> cmp) {
/* 134 */     int s = 0;
/* 135 */     int e = s + list.size() - 1;
/*     */ 
/*     */     
/* 138 */     if (!(list instanceof java.util.RandomAccess)) {
/* 139 */       return d(list, v, cmp);
/*     */     }
/*     */     
/* 142 */     T last = null;
/* 143 */     while (s <= e) {
/*     */       
/* 145 */       int m = e + s >> 1;
/*     */ 
/*     */       
/* 148 */       last = list.get(m);
/* 149 */       int i = cmp.a(last, v, m);
/* 150 */       if (i > 0) {
/* 151 */         e = m - 1; continue;
/* 152 */       }  if (i < 0) {
/* 153 */         s = m + 1; continue;
/*     */       } 
/* 155 */       return list.get(m);
/*     */     } 
/*     */     
/* 158 */     return last;
/*     */   }
/*     */   public static <T extends Comparable<V>, V> T b(List<T> list, V v) {
/*     */     Comparable<V> comparable;
/* 162 */     int s = 0;
/* 163 */     int e = s + list.size() - 1;
/*     */ 
/*     */     
/* 166 */     if (!(list instanceof java.util.RandomAccess)) {
/* 167 */       return e(list, v);
/*     */     }
/*     */     
/* 170 */     T last = null;
/* 171 */     while (s <= e) {
/*     */       
/* 173 */       int m = e + s >> 1;
/*     */ 
/*     */       
/* 176 */       comparable = (Comparable)list.get(m);
/* 177 */       int c = comparable.compareTo(v);
/* 178 */       if (c > 0) {
/* 179 */         e = m - 1; continue;
/* 180 */       }  if (c < 0) {
/* 181 */         s = m + 1; continue;
/*     */       } 
/* 183 */       return list.get(m);
/*     */     } 
/*     */     
/* 186 */     return (T)comparable;
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<V>, V> int c(List<T> list, V v) {
/* 190 */     int s = 0;
/* 191 */     int e = s + list.size() - 1;
/*     */ 
/*     */     
/* 194 */     if (!(list instanceof java.util.RandomAccess)) {
/* 195 */       return f(list, v);
/*     */     }
/*     */     
/* 198 */     int m = -1;
/* 199 */     boolean f = false;
/* 200 */     while (s <= e) {
/*     */       
/* 202 */       m = e + s >> 1;
/*     */ 
/*     */       
/* 205 */       int c = ((Comparable<V>)list.get(m)).compareTo(v);
/* 206 */       if (c > 0) {
/* 207 */         e = m - 1;
/* 208 */         f = false; continue;
/* 209 */       }  if (c < 0) {
/* 210 */         s = m + 1;
/* 211 */         f = true; continue;
/*     */       } 
/* 213 */       return m;
/*     */     } 
/*     */     
/* 216 */     m++;
/* 217 */     if (f) {
/* 218 */       m++;
/*     */     }
/* 220 */     return -m;
/*     */   }
/*     */   
/*     */   public static <T, V> T c(List<T> list, V v, c<T, V> cmp) {
/* 224 */     return a(list, v, cmp, true);
/*     */   }
/*     */   
/*     */   public static <T, V> T a(List<T> list, V v, c<T, V> cmp, boolean sorted) {
/* 228 */     int n = 0;
/* 229 */     for (T e : list) {
/* 230 */       int i = cmp.a(e, v, n++);
/* 231 */       if (i < 0 && sorted)
/*     */         break; 
/* 233 */       if (i == 0) {
/* 234 */         return e;
/*     */       }
/*     */     } 
/* 237 */     return null;
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<V>, V> T d(List<T> list, V v) {
/* 241 */     for (Comparable<V> comparable : list) {
/* 242 */       int c = comparable.compareTo(v);
/* 243 */       if (c == 0) {
/* 244 */         return (T)comparable;
/*     */       }
/*     */     } 
/* 247 */     return null;
/*     */   }
/*     */   
/*     */   public static <T, V> T d(List<T> list, V v, c<T, V> cmp) {
/* 251 */     int n = 0;
/* 252 */     T last = null;
/* 253 */     for (T e : list) {
/* 254 */       int i = cmp.a(e, v, n++);
/* 255 */       if (i < 0)
/*     */         break; 
/* 257 */       if (i == 0) {
/* 258 */         return e;
/*     */       }
/* 260 */       last = e;
/*     */     } 
/* 262 */     return last;
/*     */   }
/*     */   public static <T extends Comparable<V>, V> T e(List<T> list, V v) {
/*     */     Comparable<V> comparable;
/* 266 */     T last = null;
/* 267 */     for (Comparable<V> comparable1 : list) {
/* 268 */       int c = comparable1.compareTo(v);
/* 269 */       if (c < 0)
/*     */         break; 
/* 271 */       if (c == 0) {
/* 272 */         return (T)comparable1;
/*     */       }
/* 274 */       comparable = comparable1;
/*     */     } 
/* 276 */     return (T)comparable;
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<V>, V> int f(List<T> list, V v) {
/* 280 */     int n = 0;
/* 281 */     for (Comparable<V> comparable : list) {
/* 282 */       int c = comparable.compareTo(v);
/* 283 */       if (c < 0)
/* 284 */         return -(n + 2); 
/* 285 */       if (c == 0) {
/* 286 */         return n;
/*     */       }
/* 288 */       n++;
/*     */     } 
/* 290 */     return -(n + 1);
/*     */   }
/*     */   
/*     */   public static int a(byte[] array, byte b, a cmp) {
/* 294 */     return a(array, 0, array.length, b, cmp);
/*     */   }
/*     */   
/*     */   public static int a(byte[] array, int offset, int length, byte b, a cmp) {
/* 298 */     int s = offset;
/* 299 */     int e = s + length - 1;
/*     */     
/* 301 */     while (s <= e) {
/*     */       
/* 303 */       int m = e + s >> 1;
/*     */ 
/*     */       
/* 306 */       int c = cmp.a(array[m], b, m);
/* 307 */       if (c > 0) {
/* 308 */         e = m - 1; continue;
/* 309 */       }  if (c < 0) {
/* 310 */         s = m + 1; continue;
/*     */       } 
/* 312 */       return m;
/*     */     } 
/*     */ 
/*     */     
/* 316 */     return -1;
/*     */   }
/*     */   
/*     */   public static int a(char[] array, char ch, b cmp) {
/* 320 */     return a(array, 0, array.length, ch, cmp);
/*     */   }
/*     */   
/*     */   public static int a(char[] array, int offset, int length, char ch, b cmp) {
/* 324 */     int s = offset;
/* 325 */     int e = s + length - 1;
/*     */     
/* 327 */     while (s <= e) {
/*     */       
/* 329 */       int m = e + s >> 1;
/*     */ 
/*     */       
/* 332 */       int c = cmp.a(array[m], ch, m);
/* 333 */       if (c > 0) {
/* 334 */         e = m - 1; continue;
/* 335 */       }  if (c < 0) {
/* 336 */         s = m + 1; continue;
/*     */       } 
/* 338 */       return m;
/*     */     } 
/*     */ 
/*     */     
/* 342 */     return -1;
/*     */   }
/*     */   
/*     */   public static int a(short[] array, short b, e cmp) {
/* 346 */     return a(array, 0, array.length, b, cmp);
/*     */   }
/*     */   
/*     */   public static int a(short[] array, int offset, int length, short b, e cmp) {
/* 350 */     int s = offset;
/* 351 */     int i = s + length - 1;
/*     */     
/* 353 */     while (s <= i) {
/*     */       
/* 355 */       int m = i + s >> 1;
/*     */ 
/*     */       
/* 358 */       int c = cmp.a(array[m], b, m);
/* 359 */       if (c > 0) {
/* 360 */         i = m - 1; continue;
/* 361 */       }  if (c < 0) {
/* 362 */         s = m + 1; continue;
/*     */       } 
/* 364 */       return m;
/*     */     } 
/*     */ 
/*     */     
/* 368 */     return -1;
/*     */   }
/*     */   
/*     */   public static int a(int[] array, int n, d cmp) {
/* 372 */     return a(array, 0, array.length, n, cmp);
/*     */   }
/*     */   
/*     */   public static int a(int[] array, int offset, int length, int n, d cmp) {
/* 376 */     int s = offset;
/* 377 */     int e = s + length - 1;
/*     */     
/* 379 */     while (s <= e) {
/*     */       
/* 381 */       int m = e + s >> 1;
/*     */ 
/*     */       
/* 384 */       int c = cmp.a(array[m], n, m);
/* 385 */       if (c > 0) {
/* 386 */         e = m - 1; continue;
/* 387 */       }  if (c < 0) {
/* 388 */         s = m + 1; continue;
/*     */       } 
/* 390 */       return m;
/*     */     } 
/*     */ 
/*     */     
/* 394 */     return -1;
/*     */   }
/*     */   
/*     */   public static interface a {
/*     */     int a(byte param1Byte1, byte param1Byte2, int param1Int);
/*     */   }
/*     */   
/*     */   public static interface b {
/*     */     int a(char param1Char1, char param1Char2, int param1Int);
/*     */   }
/*     */   
/*     */   public static interface c<T, V> {
/*     */     int a(T param1T, V param1V, int param1Int);
/*     */   }
/*     */   
/*     */   public static interface d {
/*     */     int a(int param1Int1, int param1Int2, int param1Int3);
/*     */   }
/*     */   
/*     */   public static interface e {
/*     */     int a(short param1Short1, short param1Short2, int param1Int);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/i/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */