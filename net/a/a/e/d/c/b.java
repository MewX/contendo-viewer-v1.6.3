/*     */ package net.a.a.e.d.c;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.a.a.e.d.a.d;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.fonts.Glyphs;
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
/*     */ public final class b
/*     */   implements Serializable
/*     */ {
/*     */   private static final String a = "Error loading character mappings";
/*     */   private static final int b = 0;
/*     */   private static final int c = 1;
/*     */   private static final int d = 2;
/*     */   private static final int e = 5;
/*     */   private static final int f = 119808;
/*     */   private static final int g = 65536;
/*     */   private static final long h = 1L;
/*     */   private static b i;
/*  78 */   private static final Log j = LogFactory.getLog(b.class);
/*     */ 
/*     */   
/*     */   private final Map<Integer, c> k;
/*     */ 
/*     */   
/*     */   private final Set<Integer> l;
/*     */   
/*     */   private final Set<Integer> m;
/*     */   
/*     */   private final Map<net.a.a.e.d.a.b, Map<Integer, Integer[]>> n;
/*     */   
/*     */   private transient Map<c, Reference<List<c>>> o;
/*     */ 
/*     */   
/*     */   private b() {
/*  94 */     this.k = new HashMap<Integer, c>();
/*  95 */     this.l = new HashSet<Integer>();
/*  96 */     this.m = new HashSet<Integer>();
/*  97 */     this.n = new EnumMap<net.a.a.e.d.a.b, Map<Integer, Integer[]>>(net.a.a.e.d.a.b.class);
/*     */     
/*  99 */     b();
/* 100 */     c();
/*     */   }
/*     */   
/*     */   private Object b() {
/* 104 */     this.o = new HashMap<c, Reference<List<c>>>();
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   private void c() {
/* 110 */     InputStream inputStream = b.class.getResourceAsStream("/net/sourceforge/jeuclid/UnicodeData.txt");
/*     */     try {
/* 112 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
/*     */       
/*     */       try {
/*     */         String str;
/* 116 */         while ((str = bufferedReader.readLine()) != null) {
/* 117 */           String[] arrayOfString = str.split(";");
/* 118 */           if (arrayOfString.length > 5) {
/* 119 */             a(arrayOfString[0], arrayOfString[1], arrayOfString[2], arrayOfString[5]);
/*     */           
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 125 */       catch (IOException iOException) {
/* 126 */         j.warn("Error loading character mappings", iOException);
/*     */       } finally {
/*     */         try {
/* 129 */           bufferedReader.close();
/* 130 */         } catch (IOException iOException) {
/* 131 */           j.warn("Error loading character mappings", iOException);
/*     */         }
/*     */       
/*     */       } 
/* 135 */     } catch (NullPointerException nullPointerException) {
/* 136 */       j.warn("Error loading character mappings", nullPointerException);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(String paramString1, String paramString2, String paramString3, String paramString4) {
/*     */     try {
/* 143 */       int i = Integer.parseInt(paramString1, 16);
/*     */       
/* 145 */       if (paramString3.startsWith("M")) {
/* 146 */         this.m.add(Integer.valueOf(i));
/*     */       }
/*     */       
/* 149 */       if (!paramString4.startsWith("<font> ")) {
/*     */         return;
/*     */       }
/* 152 */       int j = Integer.parseInt(paramString4.substring(7), 16);
/*     */       
/* 154 */       int k = a(paramString2);
/* 155 */       net.a.a.e.d.a.b b1 = b(paramString2);
/* 156 */       if (b1 == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 162 */       boolean bool = (i >= 119808 && (net.a.a.e.d.a.b.b.equals(b1) || net.a.a.e.d.a.b.e.equals(b1))) ? true : false;
/* 163 */       if (bool) {
/* 164 */         this.l.add(Integer.valueOf(i));
/*     */       }
/*     */ 
/*     */       
/* 168 */       c c = new c(j, new d(k, b1));
/*     */       
/* 170 */       this.k.put(Integer.valueOf(i), c);
/* 171 */       Map<Integer, Integer[]> map = a(b1);
/* 172 */       Integer[] arrayOfInteger = a(j, map);
/* 173 */       arrayOfInteger[k] = Integer.valueOf(i);
/* 174 */     } catch (NumberFormatException numberFormatException) {
/* 175 */       j.debug("Parse Error", numberFormatException);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Integer[] a(int paramInt, Map<Integer, Integer[]> paramMap) {
/* 181 */     Integer[] arrayOfInteger = paramMap.get(Integer.valueOf(paramInt));
/* 182 */     if (arrayOfInteger == null) {
/* 183 */       arrayOfInteger = new Integer[4];
/* 184 */       paramMap.put(Integer.valueOf(paramInt), arrayOfInteger);
/*     */     } 
/* 186 */     return arrayOfInteger;
/*     */   }
/*     */   
/*     */   private Map<Integer, Integer[]> a(net.a.a.e.d.a.b paramb) {
/* 190 */     Map<Object, Object> map = (Map)this.n.get(paramb);
/* 191 */     if (map == null) {
/* 192 */       map = new HashMap<Object, Object>();
/* 193 */       this.n.put(paramb, map);
/*     */     } 
/* 195 */     return (Map)map;
/*     */   }
/*     */   
/*     */   private int a(String paramString) {
/* 199 */     byte b1 = 0;
/* 200 */     if (paramString.contains("BOLD")) {
/* 201 */       b1++;
/*     */     }
/* 203 */     if (paramString.contains("ITALIC")) {
/* 204 */       b1 += 2;
/*     */     }
/* 206 */     return b1;
/*     */   }
/*     */   
/*     */   private net.a.a.e.d.a.b b(String paramString) {
/*     */     net.a.a.e.d.a.b b1;
/* 211 */     if (paramString.contains("DOUBLE-STRUCK")) {
/* 212 */       b1 = net.a.a.e.d.a.b.f;
/* 213 */     } else if (paramString.contains("SCRIPT")) {
/* 214 */       b1 = net.a.a.e.d.a.b.c;
/* 215 */     } else if (paramString.contains("BLACK-LETTER") || paramString
/* 216 */       .contains("FRAKTUR")) {
/* 217 */       b1 = net.a.a.e.d.a.b.d;
/* 218 */     } else if (paramString.contains("SANS-SERIF")) {
/* 219 */       b1 = net.a.a.e.d.a.b.b;
/* 220 */     } else if (paramString.contains("MONOSPACE")) {
/* 221 */       b1 = net.a.a.e.d.a.b.a;
/* 222 */     } else if (paramString.contains("MATHEMATICAL")) {
/* 223 */       b1 = net.a.a.e.d.a.b.e;
/*     */     } else {
/* 225 */       b1 = null;
/*     */     } 
/* 227 */     return b1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized b a() {
/* 236 */     if (i == null) {
/*     */       b b1;
/*     */       
/*     */       try {
/* 240 */         InputStream inputStream = b.class.getResourceAsStream("/net/sourceforge/jeuclid/charmap.ser");
/* 241 */         ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
/* 242 */         b1 = (b)objectInputStream.readObject();
/* 243 */         objectInputStream.close();
/* 244 */       } catch (ClassNotFoundException classNotFoundException) {
/* 245 */         b1 = null;
/* 246 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 247 */         b1 = null;
/* 248 */       } catch (IOException iOException) {
/* 249 */         b1 = null;
/* 250 */       } catch (NullPointerException nullPointerException) {
/* 251 */         b1 = null;
/*     */       } 
/* 253 */       if (b1 == null) {
/* 254 */         i = new b();
/*     */       } else {
/* 256 */         i = b1;
/*     */       } 
/*     */     } 
/* 259 */     return i;
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
/*     */   
/*     */   public c a(c paramc, boolean paramBoolean) {
/* 275 */     d d = paramc.b();
/*     */     
/* 277 */     Map map = this.n.get(d.b());
/* 278 */     if (map == null) {
/* 279 */       return paramc;
/*     */     }
/* 281 */     Integer[] arrayOfInteger = (Integer[])map.get(Integer.valueOf(paramc.a()));
/* 282 */     if (arrayOfInteger == null) {
/* 283 */       return paramc;
/*     */     }
/*     */     
/* 286 */     int i = d.a();
/* 287 */     Integer integer = arrayOfInteger[i];
/* 288 */     if (integer != null) {
/* 289 */       if (paramBoolean && integer.intValue() >= 65536) {
/* 290 */         return paramc;
/*     */       }
/* 292 */       return new c(integer.intValue(), d.j);
/*     */     } 
/* 294 */     if (i != 0) {
/* 295 */       integer = arrayOfInteger[0];
/*     */     }
/* 297 */     if (integer != null) {
/* 298 */       if (paramBoolean && integer.intValue() >= 65536) {
/* 299 */         return paramc;
/*     */       }
/* 301 */       return new c(integer.intValue(), new d(i, net.a.a.e.d.a.b.e));
/*     */     } 
/*     */     
/* 304 */     return paramc;
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
/*     */   public c a(c paramc) {
/* 318 */     c c2, c1 = this.k.get(Integer.valueOf(paramc
/* 319 */           .a()));
/* 320 */     if (c1 == null) {
/* 321 */       return paramc;
/*     */     }
/* 323 */     d d = paramc.b();
/* 324 */     int i = d.a();
/* 325 */     int j = c1.a();
/*     */     
/* 327 */     if (i == 0 || this.l
/* 328 */       .contains(Integer.valueOf(j))) {
/* 329 */       c2 = c1;
/*     */     } else {
/* 331 */       d d1 = c1.b();
/*     */ 
/*     */       
/* 334 */       c2 = new c(j, new d(i | d1.a(), d1.b()));
/*     */     } 
/* 336 */     return c2;
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
/*     */   public List<c> b(c paramc) {
/* 350 */     Reference<List> reference = (Reference)this.o.get(paramc);
/* 351 */     List<c> list = null;
/* 352 */     if (reference != null) {
/* 353 */       list = reference.get();
/*     */     }
/* 355 */     if (list == null) {
/* 356 */       list = b(paramc, true);
/* 357 */       this.o.put(paramc, new SoftReference<List<c>>(list));
/*     */     } 
/*     */     
/* 360 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<c> b(c paramc, boolean paramBoolean) {
/* 365 */     ArrayList<c> arrayList = new ArrayList(3);
/*     */ 
/*     */     
/* 368 */     c c1 = a(paramc);
/*     */     
/* 370 */     c c2 = a(c1, e.a);
/*     */ 
/*     */     
/* 373 */     a(arrayList, c1, paramBoolean);
/* 374 */     a(arrayList, c2, paramBoolean);
/* 375 */     a(arrayList, paramc, paramBoolean);
/*     */     
/* 377 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(List<c> paramList, c paramc, boolean paramBoolean) {
/* 383 */     if (!paramList.contains(paramc)) {
/* 384 */       paramList.add(paramc);
/* 385 */       if (paramBoolean) {
/* 386 */         a(paramList, paramc);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(List<c> paramList, c paramc) {
/* 393 */     int i = paramc.a();
/* 394 */     String str1 = new String(new int[] { i }, 0, 1);
/* 395 */     String str2 = Glyphs.stringToGlyph(str1);
/*     */     
/* 397 */     String[] arrayOfString = Glyphs.getCharNameAlternativesFor(str2);
/* 398 */     if (arrayOfString != null) {
/* 399 */       for (String str : arrayOfString) {
/*     */         
/* 401 */         int j = Glyphs.getUnicodeSequenceForGlyphName(str).codePointAt(0);
/*     */         
/* 403 */         List<c> list = b(new c(j, paramc
/* 404 */               .b()), false);
/* 405 */         for (c c1 : list) {
/* 406 */           if (!paramList.contains(c1)) {
/* 407 */             paramList.add(c1);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
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
/*     */   public boolean a(int paramInt) {
/* 424 */     return this.m.contains(Integer.valueOf(paramInt));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/c/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */