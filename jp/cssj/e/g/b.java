/*     */ package jp.cssj.e.g;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class b
/*     */ {
/*     */   protected static final int a = -1;
/*     */   protected static final int b = -2;
/*     */   protected static final int c = -4;
/*     */   protected static final int d = -5;
/*     */   protected static final int e = -3;
/*     */   
/*     */   public static int[] a(String data) throws NullPointerException {
/* 113 */     int[] expr = new int[data.length() + 2];
/* 114 */     char[] buff = data.toCharArray();
/*     */ 
/*     */     
/* 117 */     int y = 0;
/* 118 */     boolean slash = false;
/*     */ 
/*     */     
/* 121 */     expr[y++] = -4;
/*     */     
/* 123 */     if (buff.length > 0) {
/* 124 */       if (buff[0] == '\\') {
/* 125 */         slash = true;
/* 126 */       } else if (buff[0] == '*') {
/* 127 */         expr[y++] = -1;
/*     */       } else {
/* 129 */         expr[y++] = buff[0];
/*     */       } 
/*     */ 
/*     */       
/* 133 */       for (int x = 1; x < buff.length; x++) {
/*     */         
/* 135 */         if (slash) {
/* 136 */           expr[y++] = buff[x];
/* 137 */           slash = false;
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 142 */         else if (buff[x] == '\\') {
/* 143 */           slash = true;
/*     */         }
/* 145 */         else if (buff[x] == '*') {
/*     */           
/* 147 */           if (expr[y - 1] <= -1) {
/* 148 */             expr[y - 1] = -2;
/*     */           } else {
/* 150 */             expr[y++] = -1;
/*     */           } 
/*     */         } else {
/* 153 */           expr[y++] = buff[x];
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 160 */     expr[y] = -5;
/* 161 */     return expr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(String data, int[] expr) throws NullPointerException {
/* 170 */     if (data == null)
/* 171 */       throw new NullPointerException("No data provided"); 
/* 172 */     if (expr == null) {
/* 173 */       throw new NullPointerException("No pattern expression provided");
/*     */     }
/* 175 */     char[] buff = data.toCharArray();
/*     */     
/* 177 */     char[] rslt = new char[expr.length + buff.length];
/*     */ 
/*     */ 
/*     */     
/* 181 */     int charpos = 0;
/*     */ 
/*     */     
/* 184 */     int exprpos = 0;
/* 185 */     int buffpos = 0;
/* 186 */     int rsltpos = 0;
/* 187 */     int offset = -1;
/*     */ 
/*     */     
/* 190 */     boolean matchBegin = false;
/* 191 */     if (expr[charpos] == -4) {
/* 192 */       matchBegin = true;
/* 193 */       exprpos = ++charpos;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 198 */     while (expr[charpos] >= 0) {
/* 199 */       charpos++;
/*     */     }
/*     */     
/* 202 */     int exprchr = expr[charpos];
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 207 */       if (matchBegin) {
/* 208 */         if (!c(expr, exprpos, charpos, buff, buffpos))
/* 209 */           return false; 
/* 210 */         matchBegin = false;
/*     */       } else {
/* 212 */         offset = a(expr, exprpos, charpos, buff, buffpos);
/* 213 */         if (offset < 0) {
/* 214 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 218 */       if (matchBegin) {
/* 219 */         if (offset != 0)
/* 220 */           return false; 
/* 221 */         matchBegin = false;
/*     */       } 
/*     */ 
/*     */       
/* 225 */       buffpos += charpos - exprpos;
/*     */ 
/*     */       
/* 228 */       if (exprchr == -3)
/*     */       {
/* 230 */         return true; } 
/* 231 */       if (exprchr == -5)
/*     */       {
/* 233 */         return (buffpos == buff.length);
/*     */       }
/*     */ 
/*     */       
/* 237 */       exprpos = ++charpos;
/* 238 */       while (expr[charpos] >= 0)
/* 239 */         charpos++; 
/* 240 */       int prevchr = exprchr;
/* 241 */       exprchr = expr[charpos];
/*     */ 
/*     */ 
/*     */       
/* 245 */       offset = (prevchr == -1) ? a(expr, exprpos, charpos, buff, buffpos) : b(expr, exprpos, charpos, buff, buffpos);
/*     */ 
/*     */       
/* 248 */       if (offset < 0) {
/* 249 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 253 */       if (prevchr == -2) {
/* 254 */         while (buffpos < offset) {
/* 255 */           rslt[rsltpos++] = buff[buffpos++];
/*     */         }
/*     */       } else {
/* 258 */         while (buffpos < offset) {
/* 259 */           if (buff[buffpos] == '/')
/* 260 */             return false; 
/* 261 */           rslt[rsltpos++] = buff[buffpos++];
/*     */         } 
/*     */       } 
/*     */       
/* 265 */       rsltpos = 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int a(int[] r, int rpos, int rend, char[] d, int dpos) {
/* 291 */     if (rend < rpos) {
/* 292 */       throw new IllegalArgumentException("rend < rpos");
/*     */     }
/* 294 */     if (rend == rpos) {
/* 295 */       return d.length;
/*     */     }
/* 297 */     if (rend - rpos == 1)
/*     */     {
/* 299 */       for (int x = dpos; x < d.length; x++) {
/* 300 */         if (r[rpos] == d[x]) {
/* 301 */           return x;
/*     */         }
/*     */       } 
/*     */     }
/* 305 */     while (dpos + rend - rpos <= d.length) {
/*     */       
/* 307 */       int y = dpos;
/*     */ 
/*     */       
/* 310 */       for (int x = rpos; x <= rend; x++) {
/* 311 */         if (x == rend)
/* 312 */           return dpos; 
/* 313 */         if (r[x] != d[y++]) {
/*     */           break;
/*     */         }
/*     */       } 
/* 317 */       dpos++;
/*     */     } 
/*     */ 
/*     */     
/* 321 */     return -1;
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
/*     */   protected static int b(int[] r, int rpos, int rend, char[] d, int dpos) {
/* 346 */     if (rend < rpos) {
/* 347 */       throw new IllegalArgumentException("rend < rpos");
/*     */     }
/* 349 */     if (rend == rpos) {
/* 350 */       return d.length;
/*     */     }
/*     */     
/* 353 */     if (rend - rpos == 1)
/*     */     {
/* 355 */       for (int x = d.length - 1; x > dpos; x--) {
/* 356 */         if (r[rpos] == d[x]) {
/* 357 */           return x;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 362 */     int l = d.length - rend - rpos;
/* 363 */     while (l >= dpos) {
/*     */       
/* 365 */       int y = l;
/*     */ 
/*     */       
/* 368 */       for (int x = rpos; x <= rend; x++) {
/* 369 */         if (x == rend)
/* 370 */           return l; 
/* 371 */         if (r[x] != d[y++]) {
/*     */           break;
/*     */         }
/*     */       } 
/* 375 */       l--;
/*     */     } 
/*     */ 
/*     */     
/* 379 */     return -1;
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
/*     */   protected static boolean c(int[] r, int rpos, int rend, char[] d, int dpos) {
/* 400 */     if (d.length - dpos < rend - rpos)
/* 401 */       return false; 
/* 402 */     for (int i = rpos; i < rend; i++) {
/* 403 */       if (r[i] != d[dpos++])
/* 404 */         return false; 
/* 405 */     }  return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/g/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */