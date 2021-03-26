/*     */ package c.a;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
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
/*     */ public class d
/*     */   implements Cloneable
/*     */ {
/*     */   public static final byte b = 0;
/*     */   public static final byte c = 1;
/*     */   public static final byte d = 2;
/*     */   public static final byte e = 0;
/*     */   public static final byte f = 1;
/*     */   public static final byte g = 2;
/*     */   public static final byte h = 3;
/*     */   protected int i;
/*  98 */   protected int j = 0;
/*     */ 
/*     */   
/* 101 */   protected int k = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[][] l;
/*     */ 
/*     */ 
/*     */   
/* 109 */   protected Object R_ = null;
/*     */ 
/*     */ 
/*     */   
/* 113 */   protected Object[] n = null;
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected Object[] o = null;
/*     */ 
/*     */   
/*     */   protected Hashtable p;
/*     */ 
/*     */   
/*     */   protected String q;
/*     */ 
/*     */ 
/*     */   
/*     */   public d c() {
/* 128 */     return (d)clone();
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
/*     */   
/*     */   public d(int nt, int nc, byte type) {
/* 145 */     this.j = nt;
/* 146 */     this.k = nc;
/* 147 */     this.l = new byte[nt][nc];
/* 148 */     switch (type) {
/*     */       case 1:
/* 150 */         this.i = 1;
/*     */         break;
/*     */       case 0:
/* 153 */         this.i = 0;
/*     */         break;
/*     */       case 2:
/* 156 */         this.i = 2;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object clone() {
/*     */     d ms;
/*     */     try {
/* 164 */       ms = (d)super.clone();
/* 165 */     } catch (CloneNotSupportedException e) {
/* 166 */       throw new Error("Error when cloning ModuleSpec instance");
/*     */     } 
/*     */     
/* 169 */     ms.l = new byte[this.j][this.k]; int t;
/* 170 */     for (t = 0; t < this.j; t++) {
/* 171 */       for (int c = 0; c < this.k; c++) {
/* 172 */         ms.l[t][c] = this.l[t][c];
/*     */       }
/*     */     } 
/*     */     
/* 176 */     if (this.o != null) {
/* 177 */       ms.o = new Object[this.j];
/* 178 */       for (t = 0; t < this.j; t++) {
/* 179 */         ms.o[t] = this.o[t];
/*     */       }
/*     */     } 
/*     */     
/* 183 */     if (this.p != null) {
/* 184 */       ms.p = new Hashtable<Object, Object>();
/*     */ 
/*     */       
/* 187 */       for (Enumeration<String> e = this.p.keys(); e.hasMoreElements(); ) {
/* 188 */         String tmpKey = e.nextElement();
/* 189 */         Object tmpVal = this.p.get(tmpKey);
/* 190 */         ms.p.put(tmpKey, tmpVal);
/*     */       } 
/*     */     } 
/* 193 */     return ms;
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
/*     */   public void a(Point anT) {
/* 205 */     byte[][] tmpsvt = new byte[this.j][];
/*     */     
/* 207 */     Point bnT = new Point(anT.y, anT.x);
/* 208 */     for (int by = 0; by < bnT.y; by++) {
/* 209 */       for (int bx = 0; bx < bnT.x; bx++) {
/* 210 */         int ay = bx;
/* 211 */         int ax = bnT.y - by - 1;
/* 212 */         tmpsvt[ay * anT.x + ax] = this.l[by * bnT.x + bx];
/*     */       } 
/*     */     } 
/* 215 */     this.l = tmpsvt;
/*     */ 
/*     */     
/* 218 */     if (this.o != null) {
/* 219 */       Object[] tmptd = new Object[this.j];
/* 220 */       for (int i = 0; i < bnT.y; i++) {
/* 221 */         for (int bx = 0; bx < bnT.x; bx++) {
/* 222 */           int ay = bx;
/* 223 */           int ax = bnT.y - i - 1;
/* 224 */           tmptd[ay * anT.x + ax] = this.o[i * bnT.x + bx];
/*     */         } 
/*     */       } 
/* 227 */       this.o = tmptd;
/*     */     } 
/*     */ 
/*     */     
/* 231 */     if (this.p != null && this.p.size() > 0) {
/* 232 */       Hashtable<Object, Object> tmptcv = new Hashtable<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 238 */       for (Enumeration<String> e = this.p.keys(); e.hasMoreElements(); ) {
/* 239 */         String tmpKey = e.nextElement();
/* 240 */         Object tmpVal = this.p.get(tmpKey);
/* 241 */         int i1 = tmpKey.indexOf('t');
/* 242 */         int i2 = tmpKey.indexOf('c');
/* 243 */         int btIdx = (new Integer(tmpKey.substring(i1 + 1, i2))).intValue();
/* 244 */         int bx = btIdx % bnT.x;
/* 245 */         int i = btIdx / bnT.x;
/* 246 */         int ay = bx;
/* 247 */         int ax = bnT.y - i - 1;
/* 248 */         int atIdx = ax + ay * anT.x;
/* 249 */         tmptcv.put("t" + atIdx + tmpKey.substring(i2), tmpVal);
/*     */       } 
/* 251 */       this.p = tmptcv;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Object value) {
/* 259 */     this.R_ = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object d() {
/* 268 */     return this.R_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int c, Object value) {
/* 278 */     if (this.i == 1) {
/* 279 */       String errMsg = "Option whose value is '" + value + "' cannot be " + "specified for components as it is a 'tile only' specific " + "option";
/*     */ 
/*     */       
/* 282 */       throw new Error(errMsg);
/*     */     } 
/* 284 */     if (this.n == null)
/* 285 */       this.n = new Object[this.k]; 
/* 286 */     for (int i = 0; i < this.j; i++) {
/* 287 */       if (this.l[i][c] < 1) {
/* 288 */         this.l[i][c] = 1;
/*     */       }
/*     */     } 
/* 291 */     this.n[c] = value;
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
/*     */   public Object e(int c) {
/* 306 */     if (this.i == 1) {
/* 307 */       throw new Error("Illegal use of ModuleSpec class");
/*     */     }
/* 309 */     if (this.n == null || this.n[c] == null) {
/* 310 */       return d();
/*     */     }
/*     */     
/* 313 */     return this.n[c];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(int t, Object value) {
/* 323 */     if (this.i == 0) {
/* 324 */       String errMsg = "Option whose value is '" + value + "' cannot be " + "specified for tiles as it is a 'component only' specific " + "option";
/*     */ 
/*     */       
/* 327 */       throw new Error(errMsg);
/*     */     } 
/* 329 */     if (this.o == null)
/* 330 */       this.o = new Object[this.j]; 
/* 331 */     for (int i = 0; i < this.k; i++) {
/* 332 */       if (this.l[t][i] < 2) {
/* 333 */         this.l[t][i] = 2;
/*     */       }
/*     */     } 
/* 336 */     this.o[t] = value;
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
/*     */   public Object f(int t) {
/* 350 */     if (this.i == 0) {
/* 351 */       throw new Error("Illegal use of ModuleSpec class");
/*     */     }
/* 353 */     if (this.o == null || this.o[t] == null) {
/* 354 */       return d();
/*     */     }
/*     */     
/* 357 */     return this.o[t];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int t, int c, Object value) {
/* 368 */     if (this.i != 2) {
/* 369 */       String errMsg = "Option whose value is '" + value + "' cannot be " + "specified for ";
/*     */       
/* 371 */       switch (this.i) {
/*     */         case 1:
/* 373 */           errMsg = errMsg + "components as it is a 'tile only' specific option";
/*     */           break;
/*     */         case 0:
/* 376 */           errMsg = errMsg + "tiles as it is a 'component only' specific option";
/*     */           break;
/*     */       } 
/* 379 */       throw new Error(errMsg);
/*     */     } 
/* 381 */     if (this.p == null)
/* 382 */       this.p = new Hashtable<Object, Object>(); 
/* 383 */     this.l[t][c] = 3;
/* 384 */     this.p.put("t" + t + "c" + c, value);
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
/*     */ 
/*     */   
/*     */   public Object a(int t, int c) {
/* 402 */     if (this.i != 2) {
/* 403 */       throw new Error("Illegal use of ModuleSpec class");
/*     */     }
/* 405 */     return b(t, c);
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
/*     */ 
/*     */   
/*     */   protected Object b(int t, int c) {
/* 423 */     switch (this.l[t][c]) {
/*     */       case 0:
/* 425 */         return d();
/*     */       case 1:
/* 427 */         return e(c);
/*     */       case 2:
/* 429 */         return f(t);
/*     */       case 3:
/* 431 */         return this.p.get("t" + t + "c" + c);
/*     */     } 
/* 433 */     throw new IllegalArgumentException("Not recognized spec type");
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
/*     */   public byte c(int t, int c) {
/* 445 */     return this.l[t][c];
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
/*     */   public boolean g(int c) {
/* 457 */     if (this.n == null || this.n[c] == null) {
/* 458 */       return false;
/*     */     }
/* 460 */     return true;
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
/*     */   public boolean h(int t) {
/* 472 */     if (this.o == null || this.o[t] == null) {
/* 473 */       return false;
/*     */     }
/* 475 */     return true;
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
/*     */   public boolean d(int t, int c) {
/* 488 */     if (this.p == null || this.p.get("t" + t + "c" + c) == null) {
/* 489 */       return false;
/*     */     }
/* 491 */     return true;
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
/*     */   public static final boolean[] a(String word, int maxIdx) {
/* 517 */     int nChar = word.length();
/* 518 */     char c = word.charAt(0);
/* 519 */     int idx = -1;
/* 520 */     int lastIdx = -1;
/* 521 */     boolean isDash = false;
/*     */     
/* 523 */     boolean[] idxSet = new boolean[maxIdx];
/* 524 */     int i = 1;
/*     */     
/* 526 */     while (i < nChar) {
/* 527 */       c = word.charAt(i);
/* 528 */       if (Character.isDigit(c)) {
/* 529 */         if (idx == -1)
/* 530 */           idx = 0; 
/* 531 */         idx = idx * 10 + c - 48;
/*     */       } else {
/*     */         
/* 534 */         if (idx == -1 || (c != ',' && c != '-')) {
/* 535 */           throw new IllegalArgumentException("Bad construction for parameter: " + word);
/*     */         }
/*     */         
/* 538 */         if (idx < 0 || idx >= maxIdx) {
/* 539 */           throw new IllegalArgumentException("Out of range index in parameter `" + word + "' : " + idx);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 545 */         if (c == ',') {
/* 546 */           if (isDash) {
/* 547 */             for (int j = lastIdx + 1; j < idx; j++) {
/* 548 */               idxSet[j] = true;
/*     */             }
/*     */           }
/* 551 */           isDash = false;
/*     */         } else {
/*     */           
/* 554 */           isDash = true;
/*     */         } 
/*     */         
/* 557 */         idxSet[idx] = true;
/* 558 */         lastIdx = idx;
/* 559 */         idx = -1;
/*     */       } 
/* 561 */       i++;
/*     */     } 
/*     */ 
/*     */     
/* 565 */     if (idx < 0 || idx >= maxIdx) {
/* 566 */       throw new IllegalArgumentException("Out of range index in parameter `" + word + "' : " + idx);
/*     */     }
/*     */     
/* 569 */     if (isDash)
/* 570 */       for (int j = lastIdx + 1; j < idx; j++) {
/* 571 */         idxSet[j] = true;
/*     */       } 
/* 573 */     idxSet[idx] = true;
/*     */     
/* 575 */     return idxSet;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */