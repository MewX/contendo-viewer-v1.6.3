/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformListParser
/*     */   extends NumberParser
/*     */ {
/*  41 */   protected TransformListHandler transformListHandler = DefaultTransformListHandler.INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransformListHandler(TransformListHandler handler) {
/*  56 */     this.transformListHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformListHandler getTransformListHandler() {
/*  63 */     return this.transformListHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doParse() throws ParseException, IOException {
/*  70 */     this.transformListHandler.startTransformList();
/*     */     
/*     */     while (true) {
/*     */       try {
/*  74 */         this.current = this.reader.read();
/*  75 */         switch (this.current) {
/*     */           case 9:
/*     */           case 10:
/*     */           case 13:
/*     */           case 32:
/*     */           case 44:
/*     */             continue;
/*     */           case 109:
/*  83 */             parseMatrix();
/*     */             continue;
/*     */           case 114:
/*  86 */             parseRotate();
/*     */             continue;
/*     */           case 116:
/*  89 */             parseTranslate();
/*     */             continue;
/*     */           case 115:
/*  92 */             this.current = this.reader.read();
/*  93 */             switch (this.current) {
/*     */               case 99:
/*  95 */                 parseScale();
/*     */                 continue;
/*     */               case 107:
/*  98 */                 parseSkew();
/*     */                 continue;
/*     */             } 
/* 101 */             reportUnexpectedCharacterError(this.current);
/* 102 */             skipTransform();
/*     */             continue;
/*     */           
/*     */           case -1:
/*     */             break;
/*     */         } 
/* 108 */         reportUnexpectedCharacterError(this.current);
/* 109 */         skipTransform();
/*     */       }
/* 111 */       catch (ParseException e) {
/* 112 */         this.errorHandler.error(e);
/* 113 */         skipTransform();
/*     */       } 
/*     */     } 
/* 116 */     skipSpaces();
/* 117 */     if (this.current != -1) {
/* 118 */       reportError("end.of.stream.expected", new Object[] { Integer.valueOf(this.current) });
/*     */     }
/*     */ 
/*     */     
/* 122 */     this.transformListHandler.endTransformList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseMatrix() throws ParseException, IOException {
/* 129 */     this.current = this.reader.read();
/*     */ 
/*     */     
/* 132 */     if (this.current != 97) {
/* 133 */       reportCharacterExpectedError('a', this.current);
/* 134 */       skipTransform();
/*     */       return;
/*     */     } 
/* 137 */     this.current = this.reader.read();
/* 138 */     if (this.current != 116) {
/* 139 */       reportCharacterExpectedError('t', this.current);
/* 140 */       skipTransform();
/*     */       return;
/*     */     } 
/* 143 */     this.current = this.reader.read();
/* 144 */     if (this.current != 114) {
/* 145 */       reportCharacterExpectedError('r', this.current);
/* 146 */       skipTransform();
/*     */       return;
/*     */     } 
/* 149 */     this.current = this.reader.read();
/* 150 */     if (this.current != 105) {
/* 151 */       reportCharacterExpectedError('i', this.current);
/* 152 */       skipTransform();
/*     */       return;
/*     */     } 
/* 155 */     this.current = this.reader.read();
/* 156 */     if (this.current != 120) {
/* 157 */       reportCharacterExpectedError('x', this.current);
/* 158 */       skipTransform();
/*     */       return;
/*     */     } 
/* 161 */     this.current = this.reader.read();
/* 162 */     skipSpaces();
/* 163 */     if (this.current != 40) {
/* 164 */       reportCharacterExpectedError('(', this.current);
/* 165 */       skipTransform();
/*     */       return;
/*     */     } 
/* 168 */     this.current = this.reader.read();
/* 169 */     skipSpaces();
/*     */     
/* 171 */     float a = parseFloat();
/* 172 */     skipCommaSpaces();
/* 173 */     float b = parseFloat();
/* 174 */     skipCommaSpaces();
/* 175 */     float c = parseFloat();
/* 176 */     skipCommaSpaces();
/* 177 */     float d = parseFloat();
/* 178 */     skipCommaSpaces();
/* 179 */     float e = parseFloat();
/* 180 */     skipCommaSpaces();
/* 181 */     float f = parseFloat();
/*     */     
/* 183 */     skipSpaces();
/* 184 */     if (this.current != 41) {
/* 185 */       reportCharacterExpectedError(')', this.current);
/* 186 */       skipTransform();
/*     */       
/*     */       return;
/*     */     } 
/* 190 */     this.transformListHandler.matrix(a, b, c, d, e, f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseRotate() throws ParseException, IOException {
/* 197 */     this.current = this.reader.read();
/*     */ 
/*     */     
/* 200 */     if (this.current != 111) {
/* 201 */       reportCharacterExpectedError('o', this.current);
/* 202 */       skipTransform();
/*     */       return;
/*     */     } 
/* 205 */     this.current = this.reader.read();
/* 206 */     if (this.current != 116) {
/* 207 */       reportCharacterExpectedError('t', this.current);
/* 208 */       skipTransform();
/*     */       return;
/*     */     } 
/* 211 */     this.current = this.reader.read();
/* 212 */     if (this.current != 97) {
/* 213 */       reportCharacterExpectedError('a', this.current);
/* 214 */       skipTransform();
/*     */       return;
/*     */     } 
/* 217 */     this.current = this.reader.read();
/* 218 */     if (this.current != 116) {
/* 219 */       reportCharacterExpectedError('t', this.current);
/* 220 */       skipTransform();
/*     */       return;
/*     */     } 
/* 223 */     this.current = this.reader.read();
/* 224 */     if (this.current != 101) {
/* 225 */       reportCharacterExpectedError('e', this.current);
/* 226 */       skipTransform();
/*     */       return;
/*     */     } 
/* 229 */     this.current = this.reader.read();
/* 230 */     skipSpaces();
/*     */     
/* 232 */     if (this.current != 40) {
/* 233 */       reportCharacterExpectedError('(', this.current);
/* 234 */       skipTransform();
/*     */       return;
/*     */     } 
/* 237 */     this.current = this.reader.read();
/* 238 */     skipSpaces();
/*     */     
/* 240 */     float theta = parseFloat();
/* 241 */     skipSpaces();
/*     */     
/* 243 */     switch (this.current) {
/*     */       case 41:
/* 245 */         this.transformListHandler.rotate(theta);
/*     */         return;
/*     */       case 44:
/* 248 */         this.current = this.reader.read();
/* 249 */         skipSpaces();
/*     */         break;
/*     */     } 
/* 252 */     float cx = parseFloat();
/* 253 */     skipCommaSpaces();
/* 254 */     float cy = parseFloat();
/*     */     
/* 256 */     skipSpaces();
/* 257 */     if (this.current != 41) {
/* 258 */       reportCharacterExpectedError(')', this.current);
/* 259 */       skipTransform();
/*     */       
/*     */       return;
/*     */     } 
/* 263 */     this.transformListHandler.rotate(theta, cx, cy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseTranslate() throws ParseException, IOException {
/* 271 */     this.current = this.reader.read();
/*     */ 
/*     */     
/* 274 */     if (this.current != 114) {
/* 275 */       reportCharacterExpectedError('r', this.current);
/* 276 */       skipTransform();
/*     */       return;
/*     */     } 
/* 279 */     this.current = this.reader.read();
/* 280 */     if (this.current != 97) {
/* 281 */       reportCharacterExpectedError('a', this.current);
/* 282 */       skipTransform();
/*     */       return;
/*     */     } 
/* 285 */     this.current = this.reader.read();
/* 286 */     if (this.current != 110) {
/* 287 */       reportCharacterExpectedError('n', this.current);
/* 288 */       skipTransform();
/*     */       return;
/*     */     } 
/* 291 */     this.current = this.reader.read();
/* 292 */     if (this.current != 115) {
/* 293 */       reportCharacterExpectedError('s', this.current);
/* 294 */       skipTransform();
/*     */       return;
/*     */     } 
/* 297 */     this.current = this.reader.read();
/* 298 */     if (this.current != 108) {
/* 299 */       reportCharacterExpectedError('l', this.current);
/* 300 */       skipTransform();
/*     */       return;
/*     */     } 
/* 303 */     this.current = this.reader.read();
/* 304 */     if (this.current != 97) {
/* 305 */       reportCharacterExpectedError('a', this.current);
/* 306 */       skipTransform();
/*     */       return;
/*     */     } 
/* 309 */     this.current = this.reader.read();
/* 310 */     if (this.current != 116) {
/* 311 */       reportCharacterExpectedError('t', this.current);
/* 312 */       skipTransform();
/*     */       return;
/*     */     } 
/* 315 */     this.current = this.reader.read();
/* 316 */     if (this.current != 101) {
/* 317 */       reportCharacterExpectedError('e', this.current);
/* 318 */       skipTransform();
/*     */       return;
/*     */     } 
/* 321 */     this.current = this.reader.read();
/* 322 */     skipSpaces();
/* 323 */     if (this.current != 40) {
/* 324 */       reportCharacterExpectedError('(', this.current);
/* 325 */       skipTransform();
/*     */       return;
/*     */     } 
/* 328 */     this.current = this.reader.read();
/* 329 */     skipSpaces();
/*     */     
/* 331 */     float tx = parseFloat();
/* 332 */     skipSpaces();
/*     */     
/* 334 */     switch (this.current) {
/*     */       case 41:
/* 336 */         this.transformListHandler.translate(tx);
/*     */         return;
/*     */       case 44:
/* 339 */         this.current = this.reader.read();
/* 340 */         skipSpaces();
/*     */         break;
/*     */     } 
/* 343 */     float ty = parseFloat();
/*     */     
/* 345 */     skipSpaces();
/* 346 */     if (this.current != 41) {
/* 347 */       reportCharacterExpectedError(')', this.current);
/* 348 */       skipTransform();
/*     */       
/*     */       return;
/*     */     } 
/* 352 */     this.transformListHandler.translate(tx, ty);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseScale() throws ParseException, IOException {
/* 359 */     this.current = this.reader.read();
/*     */ 
/*     */     
/* 362 */     if (this.current != 97) {
/* 363 */       reportCharacterExpectedError('a', this.current);
/* 364 */       skipTransform();
/*     */       return;
/*     */     } 
/* 367 */     this.current = this.reader.read();
/* 368 */     if (this.current != 108) {
/* 369 */       reportCharacterExpectedError('l', this.current);
/* 370 */       skipTransform();
/*     */       return;
/*     */     } 
/* 373 */     this.current = this.reader.read();
/* 374 */     if (this.current != 101) {
/* 375 */       reportCharacterExpectedError('e', this.current);
/* 376 */       skipTransform();
/*     */       return;
/*     */     } 
/* 379 */     this.current = this.reader.read();
/* 380 */     skipSpaces();
/* 381 */     if (this.current != 40) {
/* 382 */       reportCharacterExpectedError('(', this.current);
/* 383 */       skipTransform();
/*     */       return;
/*     */     } 
/* 386 */     this.current = this.reader.read();
/* 387 */     skipSpaces();
/*     */     
/* 389 */     float sx = parseFloat();
/* 390 */     skipSpaces();
/*     */     
/* 392 */     switch (this.current) {
/*     */       case 41:
/* 394 */         this.transformListHandler.scale(sx);
/*     */         return;
/*     */       case 44:
/* 397 */         this.current = this.reader.read();
/* 398 */         skipSpaces();
/*     */         break;
/*     */     } 
/* 401 */     float sy = parseFloat();
/*     */     
/* 403 */     skipSpaces();
/* 404 */     if (this.current != 41) {
/* 405 */       reportCharacterExpectedError(')', this.current);
/* 406 */       skipTransform();
/*     */       
/*     */       return;
/*     */     } 
/* 410 */     this.transformListHandler.scale(sx, sy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseSkew() throws ParseException, IOException {
/* 417 */     this.current = this.reader.read();
/*     */ 
/*     */     
/* 420 */     if (this.current != 101) {
/* 421 */       reportCharacterExpectedError('e', this.current);
/* 422 */       skipTransform();
/*     */       return;
/*     */     } 
/* 425 */     this.current = this.reader.read();
/* 426 */     if (this.current != 119) {
/* 427 */       reportCharacterExpectedError('w', this.current);
/* 428 */       skipTransform();
/*     */       return;
/*     */     } 
/* 431 */     this.current = this.reader.read();
/*     */     
/* 433 */     boolean skewX = false;
/* 434 */     switch (this.current) {
/*     */       case 88:
/* 436 */         skewX = true;
/*     */         break;
/*     */       case 89:
/*     */         break;
/*     */       default:
/* 441 */         reportCharacterExpectedError('X', this.current);
/* 442 */         skipTransform();
/*     */         return;
/*     */     } 
/* 445 */     this.current = this.reader.read();
/* 446 */     skipSpaces();
/* 447 */     if (this.current != 40) {
/* 448 */       reportCharacterExpectedError('(', this.current);
/* 449 */       skipTransform();
/*     */       return;
/*     */     } 
/* 452 */     this.current = this.reader.read();
/* 453 */     skipSpaces();
/*     */     
/* 455 */     float sk = parseFloat();
/*     */     
/* 457 */     skipSpaces();
/* 458 */     if (this.current != 41) {
/* 459 */       reportCharacterExpectedError(')', this.current);
/* 460 */       skipTransform();
/*     */       
/*     */       return;
/*     */     } 
/* 464 */     if (skewX) {
/* 465 */       this.transformListHandler.skewX(sk);
/*     */     } else {
/* 467 */       this.transformListHandler.skewY(sk);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void skipTransform() throws IOException {
/*     */     do {
/* 476 */       this.current = this.reader.read();
/* 477 */       switch (this.current) {
/*     */         case 41:
/*     */           break;
/*     */       } 
/* 481 */     } while (this.current != -1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/TransformListParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */