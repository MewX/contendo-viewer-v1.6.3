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
/*     */ public class LengthParser
/*     */   extends AbstractParser
/*     */ {
/*  41 */   protected LengthHandler lengthHandler = DefaultLengthHandler.INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLengthHandler(LengthHandler handler) {
/*  56 */     this.lengthHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LengthHandler getLengthHandler() {
/*  63 */     return this.lengthHandler;
/*     */   }
/*     */   
/*     */   protected void doParse() throws ParseException, IOException {
/*  67 */     this.lengthHandler.startLength();
/*     */     
/*  69 */     this.current = this.reader.read();
/*  70 */     skipSpaces();
/*     */     
/*  72 */     parseLength();
/*     */     
/*  74 */     skipSpaces();
/*  75 */     if (this.current != -1) {
/*  76 */       reportError("end.of.stream.expected", new Object[] { Integer.valueOf(this.current) });
/*     */     }
/*     */     
/*  79 */     this.lengthHandler.endLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseLength() throws ParseException, IOException {
/*  86 */     int mant = 0;
/*  87 */     int mantDig = 0;
/*  88 */     boolean mantPos = true;
/*  89 */     boolean mantRead = false;
/*     */     
/*  91 */     int exp = 0;
/*  92 */     int expDig = 0;
/*  93 */     int expAdj = 0;
/*  94 */     boolean expPos = true;
/*     */     
/*  96 */     int unitState = 0;
/*     */     
/*  98 */     switch (this.current) {
/*     */       case 45:
/* 100 */         mantPos = false;
/*     */       case 43:
/* 102 */         this.current = this.reader.read();
/*     */         break;
/*     */     } 
/* 105 */     switch (this.current)
/*     */     { default:
/* 107 */         reportUnexpectedCharacterError(this.current);
/*     */         return;
/*     */       
/*     */       case 46:
/*     */         break;
/*     */       
/*     */       case 48:
/* 114 */         mantRead = true;
/*     */         while (true)
/* 116 */         { this.current = this.reader.read();
/* 117 */           switch (this.current) { case 49: // Byte code: goto -> 229
/*     */             case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: break;
/*     */             default: break;
/*     */             case 48:  }  }  break;
/*     */       case 49: case 50:
/*     */       case 51:
/*     */       case 52:
/*     */       case 53:
/*     */       case 54:
/*     */       case 55:
/*     */       case 56:
/*     */       case 57:
/* 129 */         mantRead = true;
/*     */         while (true)
/* 131 */         { if (mantDig < 9) {
/* 132 */             mantDig++;
/* 133 */             mant = mant * 10 + this.current - 48;
/*     */           } else {
/* 135 */             expAdj++;
/*     */           } 
/* 137 */           this.current = this.reader.read();
/* 138 */           switch (this.current) { case 48: case 49:
/*     */             case 50:
/*     */             case 51:
/*     */             case 52:
/*     */             case 53:
/*     */             case 54:
/*     */             case 55:
/*     */             case 56:
/*     */             case 57:
/* 147 */               break; }  }  break; }  if (this.current == 46)
/* 148 */     { this.current = this.reader.read();
/* 149 */       switch (this.current)
/*     */       
/*     */       { default:
/* 152 */           if (!mantRead) {
/* 153 */             reportUnexpectedCharacterError(this.current);
/*     */             return;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 48:
/* 159 */           if (mantDig == 0)
/*     */           { while (true)
/* 161 */             { this.current = this.reader.read();
/* 162 */               expAdj--;
/* 163 */               switch (this.current) { case 49: continue label134;
/*     */                 case 50: continue label134;
/*     */                 case 51: continue label134;
/*     */                 case 52: continue label134;
/*     */                 case 53: continue label134;
/*     */                 case 54: continue label134;
/*     */                 case 55: continue label134;
/*     */                 case 56: continue label134;
/*     */                 case 57: continue label134;
/*     */                 default: break;
/*     */                 case 48: break; }  }  break; } 
/*     */         case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56:
/*     */         case 57:
/* 176 */           label134: while (true) { if (mantDig < 9) {
/* 177 */               mantDig++;
/* 178 */               mant = mant * 10 + this.current - 48;
/* 179 */               expAdj--;
/*     */             } 
/* 181 */             this.current = this.reader.read();
/* 182 */             switch (this.current) { case 48:
/*     */               case 49:
/*     */               case 50:
/*     */               case 51:
/*     */               case 52:
/*     */               case 53:
/*     */               case 54:
/*     */               case 55:
/*     */               case 56:
/*     */               case 57:
/* 192 */                 break; }  }  break; }  }  boolean le = false;
/* 193 */     switch (this.current) {
/*     */       case 101:
/* 195 */         le = true;
/*     */       case 69:
/* 197 */         this.current = this.reader.read();
/* 198 */         switch (this.current)
/*     */         { default:
/* 200 */             reportUnexpectedCharacterError(this.current);
/*     */             return;
/*     */           case 109:
/* 203 */             if (!le) {
/* 204 */               reportUnexpectedCharacterError(this.current);
/*     */               return;
/*     */             } 
/* 207 */             unitState = 1;
/*     */             break;
/*     */           case 120:
/* 210 */             if (!le) {
/* 211 */               reportUnexpectedCharacterError(this.current);
/*     */               return;
/*     */             } 
/* 214 */             unitState = 2;
/*     */             break;
/*     */           case 45:
/* 217 */             expPos = false;
/*     */           case 43:
/* 219 */             this.current = this.reader.read();
/* 220 */             switch (this.current)
/*     */             { default:
/* 222 */                 reportUnexpectedCharacterError(this.current); return;
/*     */               case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: break; }  break;
/*     */           case 48: case 49: case 50: case 51:
/*     */           case 52:
/*     */           case 53:
/*     */           case 54:
/*     */           case 55:
/*     */           case 56:
/*     */           case 57:
/* 231 */             break; }  switch (this.current)
/*     */         { case 48:
/*     */             while (true)
/* 234 */             { this.current = this.reader.read();
/* 235 */               switch (this.current) { case 49: continue label135;
/*     */                 case 50: continue label135;
/*     */                 case 51: continue label135;
/*     */                 case 52: continue label135;
/*     */                 case 53: continue label135;
/*     */                 case 54: continue label135;
/*     */                 case 55: continue label135;
/*     */                 case 56: continue label135;
/*     */                 case 57: continue label135;
/*     */                 default: break;
/*     */                 case 48: break; }  }  break;
/*     */           case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56:
/*     */           case 57:
/* 248 */             label135: while (true) { if (expDig < 3) {
/* 249 */                 expDig++;
/* 250 */                 exp = exp * 10 + this.current - 48;
/*     */               } 
/* 252 */               this.current = this.reader.read();
/* 253 */               switch (this.current) { case 48:
/*     */                 case 49:
/*     */                 case 50:
/*     */                 case 51:
/*     */                 case 52:
/*     */                 case 53:
/*     */                 case 54:
/*     */                 case 55:
/*     */                 case 56:
/*     */                 case 57:
/*     */                   break; }  }  break; }  break;
/* 264 */     }  if (!expPos) {
/* 265 */       exp = -exp;
/*     */     }
/* 267 */     exp += expAdj;
/* 268 */     if (!mantPos) {
/* 269 */       mant = -mant;
/*     */     }
/*     */     
/* 272 */     this.lengthHandler.lengthValue(NumberParser.buildFloat(mant, exp));
/*     */     
/* 274 */     switch (unitState) {
/*     */       case 1:
/* 276 */         this.lengthHandler.em();
/* 277 */         this.current = this.reader.read();
/*     */         return;
/*     */       case 2:
/* 280 */         this.lengthHandler.ex();
/* 281 */         this.current = this.reader.read();
/*     */         return;
/*     */     } 
/*     */     
/* 285 */     switch (this.current) {
/*     */       case 101:
/* 287 */         this.current = this.reader.read();
/* 288 */         switch (this.current) {
/*     */           case 109:
/* 290 */             this.lengthHandler.em();
/* 291 */             this.current = this.reader.read();
/*     */             break;
/*     */           case 120:
/* 294 */             this.lengthHandler.ex();
/* 295 */             this.current = this.reader.read();
/*     */             break;
/*     */         } 
/* 298 */         reportUnexpectedCharacterError(this.current);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 112:
/* 303 */         this.current = this.reader.read();
/* 304 */         switch (this.current) {
/*     */           case 99:
/* 306 */             this.lengthHandler.pc();
/* 307 */             this.current = this.reader.read();
/*     */             break;
/*     */           case 116:
/* 310 */             this.lengthHandler.pt();
/* 311 */             this.current = this.reader.read();
/*     */             break;
/*     */           case 120:
/* 314 */             this.lengthHandler.px();
/* 315 */             this.current = this.reader.read();
/*     */             break;
/*     */         } 
/* 318 */         reportUnexpectedCharacterError(this.current);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 105:
/* 323 */         this.current = this.reader.read();
/* 324 */         if (this.current != 110) {
/* 325 */           reportCharacterExpectedError('n', this.current);
/*     */           break;
/*     */         } 
/* 328 */         this.lengthHandler.in();
/* 329 */         this.current = this.reader.read();
/*     */         break;
/*     */       case 99:
/* 332 */         this.current = this.reader.read();
/* 333 */         if (this.current != 109) {
/* 334 */           reportCharacterExpectedError('m', this.current);
/*     */           break;
/*     */         } 
/* 337 */         this.lengthHandler.cm();
/* 338 */         this.current = this.reader.read();
/*     */         break;
/*     */       case 109:
/* 341 */         this.current = this.reader.read();
/* 342 */         if (this.current != 109) {
/* 343 */           reportCharacterExpectedError('m', this.current);
/*     */           break;
/*     */         } 
/* 346 */         this.lengthHandler.mm();
/* 347 */         this.current = this.reader.read();
/*     */         break;
/*     */       case 37:
/* 350 */         this.lengthHandler.percentage();
/* 351 */         this.current = this.reader.read();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/LengthParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */