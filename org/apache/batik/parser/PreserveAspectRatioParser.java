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
/*     */ public class PreserveAspectRatioParser
/*     */   extends AbstractParser
/*     */ {
/*  41 */   protected PreserveAspectRatioHandler preserveAspectRatioHandler = DefaultPreserveAspectRatioHandler.INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPreserveAspectRatioHandler(PreserveAspectRatioHandler handler) {
/*  58 */     this.preserveAspectRatioHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PreserveAspectRatioHandler getPreserveAspectRatioHandler() {
/*  65 */     return this.preserveAspectRatioHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doParse() throws ParseException, IOException {
/*  72 */     this.current = this.reader.read();
/*  73 */     skipSpaces();
/*     */     
/*  75 */     parsePreserveAspectRatio();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parsePreserveAspectRatio() throws ParseException, IOException {
/*  83 */     this.preserveAspectRatioHandler.startPreserveAspectRatio();
/*     */     
/*  85 */     switch (this.current) {
/*     */       case 110:
/*  87 */         this.current = this.reader.read();
/*  88 */         if (this.current != 111) {
/*  89 */           reportCharacterExpectedError('o', this.current);
/*  90 */           skipIdentifier();
/*     */           break;
/*     */         } 
/*  93 */         this.current = this.reader.read();
/*  94 */         if (this.current != 110) {
/*  95 */           reportCharacterExpectedError('o', this.current);
/*  96 */           skipIdentifier();
/*     */           break;
/*     */         } 
/*  99 */         this.current = this.reader.read();
/* 100 */         if (this.current != 101) {
/* 101 */           reportCharacterExpectedError('e', this.current);
/* 102 */           skipIdentifier();
/*     */           break;
/*     */         } 
/* 105 */         this.current = this.reader.read();
/* 106 */         skipSpaces();
/* 107 */         this.preserveAspectRatioHandler.none();
/*     */         break;
/*     */       
/*     */       case 120:
/* 111 */         this.current = this.reader.read();
/* 112 */         if (this.current != 77) {
/* 113 */           reportCharacterExpectedError('M', this.current);
/* 114 */           skipIdentifier();
/*     */           break;
/*     */         } 
/* 117 */         this.current = this.reader.read();
/* 118 */         switch (this.current) {
/*     */           case 97:
/* 120 */             this.current = this.reader.read();
/* 121 */             if (this.current != 120) {
/* 122 */               reportCharacterExpectedError('x', this.current);
/* 123 */               skipIdentifier();
/*     */               break;
/*     */             } 
/* 126 */             this.current = this.reader.read();
/* 127 */             if (this.current != 89) {
/* 128 */               reportCharacterExpectedError('Y', this.current);
/* 129 */               skipIdentifier();
/*     */               break;
/*     */             } 
/* 132 */             this.current = this.reader.read();
/* 133 */             if (this.current != 77) {
/* 134 */               reportCharacterExpectedError('M', this.current);
/* 135 */               skipIdentifier();
/*     */               break;
/*     */             } 
/* 138 */             this.current = this.reader.read();
/* 139 */             switch (this.current) {
/*     */               case 97:
/* 141 */                 this.current = this.reader.read();
/* 142 */                 if (this.current != 120) {
/* 143 */                   reportCharacterExpectedError('x', this.current);
/* 144 */                   skipIdentifier();
/*     */                   break;
/*     */                 } 
/* 147 */                 this.preserveAspectRatioHandler.xMaxYMax();
/* 148 */                 this.current = this.reader.read();
/*     */                 break;
/*     */               case 105:
/* 151 */                 this.current = this.reader.read();
/* 152 */                 switch (this.current) {
/*     */                   case 100:
/* 154 */                     this.preserveAspectRatioHandler.xMaxYMid();
/* 155 */                     this.current = this.reader.read();
/*     */                     break;
/*     */                   case 110:
/* 158 */                     this.preserveAspectRatioHandler.xMaxYMin();
/* 159 */                     this.current = this.reader.read();
/*     */                     break;
/*     */                 } 
/* 162 */                 reportUnexpectedCharacterError(this.current);
/* 163 */                 skipIdentifier();
/*     */                 break;
/*     */             } 
/*     */             
/*     */             break;
/*     */           case 105:
/* 169 */             this.current = this.reader.read();
/* 170 */             switch (this.current) {
/*     */               case 100:
/* 172 */                 this.current = this.reader.read();
/* 173 */                 if (this.current != 89) {
/* 174 */                   reportCharacterExpectedError('Y', this.current);
/* 175 */                   skipIdentifier();
/*     */                   break;
/*     */                 } 
/* 178 */                 this.current = this.reader.read();
/* 179 */                 if (this.current != 77) {
/* 180 */                   reportCharacterExpectedError('M', this.current);
/* 181 */                   skipIdentifier();
/*     */                   break;
/*     */                 } 
/* 184 */                 this.current = this.reader.read();
/* 185 */                 switch (this.current) {
/*     */                   case 97:
/* 187 */                     this.current = this.reader.read();
/* 188 */                     if (this.current != 120) {
/* 189 */                       reportCharacterExpectedError('x', this.current);
/* 190 */                       skipIdentifier();
/*     */                       break;
/*     */                     } 
/* 193 */                     this.preserveAspectRatioHandler.xMidYMax();
/* 194 */                     this.current = this.reader.read();
/*     */                     break;
/*     */                   case 105:
/* 197 */                     this.current = this.reader.read();
/* 198 */                     switch (this.current) {
/*     */                       case 100:
/* 200 */                         this.preserveAspectRatioHandler.xMidYMid();
/* 201 */                         this.current = this.reader.read();
/*     */                         break;
/*     */                       case 110:
/* 204 */                         this.preserveAspectRatioHandler.xMidYMin();
/* 205 */                         this.current = this.reader.read();
/*     */                         break;
/*     */                     } 
/* 208 */                     reportUnexpectedCharacterError(this.current);
/* 209 */                     skipIdentifier();
/*     */                     break;
/*     */                 } 
/*     */                 
/*     */                 break;
/*     */               case 110:
/* 215 */                 this.current = this.reader.read();
/* 216 */                 if (this.current != 89) {
/* 217 */                   reportCharacterExpectedError('Y', this.current);
/* 218 */                   skipIdentifier();
/*     */                   break;
/*     */                 } 
/* 221 */                 this.current = this.reader.read();
/* 222 */                 if (this.current != 77) {
/* 223 */                   reportCharacterExpectedError('M', this.current);
/* 224 */                   skipIdentifier();
/*     */                   break;
/*     */                 } 
/* 227 */                 this.current = this.reader.read();
/* 228 */                 switch (this.current) {
/*     */                   case 97:
/* 230 */                     this.current = this.reader.read();
/* 231 */                     if (this.current != 120) {
/* 232 */                       reportCharacterExpectedError('x', this.current);
/* 233 */                       skipIdentifier();
/*     */                       break;
/*     */                     } 
/* 236 */                     this.preserveAspectRatioHandler.xMinYMax();
/* 237 */                     this.current = this.reader.read();
/*     */                     break;
/*     */                   case 105:
/* 240 */                     this.current = this.reader.read();
/* 241 */                     switch (this.current) {
/*     */                       case 100:
/* 243 */                         this.preserveAspectRatioHandler.xMinYMid();
/* 244 */                         this.current = this.reader.read();
/*     */                         break;
/*     */                       case 110:
/* 247 */                         this.preserveAspectRatioHandler.xMinYMin();
/* 248 */                         this.current = this.reader.read();
/*     */                         break;
/*     */                     } 
/* 251 */                     reportUnexpectedCharacterError(this.current);
/* 252 */                     skipIdentifier();
/*     */                     break;
/*     */                 } 
/*     */                 
/*     */                 break;
/*     */             } 
/* 258 */             reportUnexpectedCharacterError(this.current);
/* 259 */             skipIdentifier();
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 264 */         reportUnexpectedCharacterError(this.current);
/* 265 */         skipIdentifier();
/*     */         break;
/*     */       
/*     */       default:
/* 269 */         if (this.current != -1) {
/* 270 */           reportUnexpectedCharacterError(this.current);
/* 271 */           skipIdentifier();
/*     */         } 
/*     */         break;
/*     */     } 
/* 275 */     skipCommaSpaces();
/*     */     
/* 277 */     switch (this.current) {
/*     */       case 109:
/* 279 */         this.current = this.reader.read();
/* 280 */         if (this.current != 101) {
/* 281 */           reportCharacterExpectedError('e', this.current);
/* 282 */           skipIdentifier();
/*     */           break;
/*     */         } 
/* 285 */         this.current = this.reader.read();
/* 286 */         if (this.current != 101) {
/* 287 */           reportCharacterExpectedError('e', this.current);
/* 288 */           skipIdentifier();
/*     */           break;
/*     */         } 
/* 291 */         this.current = this.reader.read();
/* 292 */         if (this.current != 116) {
/* 293 */           reportCharacterExpectedError('t', this.current);
/* 294 */           skipIdentifier();
/*     */           break;
/*     */         } 
/* 297 */         this.preserveAspectRatioHandler.meet();
/* 298 */         this.current = this.reader.read();
/*     */         break;
/*     */       case 115:
/* 301 */         this.current = this.reader.read();
/* 302 */         if (this.current != 108) {
/* 303 */           reportCharacterExpectedError('l', this.current);
/* 304 */           skipIdentifier();
/*     */           break;
/*     */         } 
/* 307 */         this.current = this.reader.read();
/* 308 */         if (this.current != 105) {
/* 309 */           reportCharacterExpectedError('i', this.current);
/* 310 */           skipIdentifier();
/*     */           break;
/*     */         } 
/* 313 */         this.current = this.reader.read();
/* 314 */         if (this.current != 99) {
/* 315 */           reportCharacterExpectedError('c', this.current);
/* 316 */           skipIdentifier();
/*     */           break;
/*     */         } 
/* 319 */         this.current = this.reader.read();
/* 320 */         if (this.current != 101) {
/* 321 */           reportCharacterExpectedError('e', this.current);
/* 322 */           skipIdentifier();
/*     */           break;
/*     */         } 
/* 325 */         this.preserveAspectRatioHandler.slice();
/* 326 */         this.current = this.reader.read();
/*     */         break;
/*     */       default:
/* 329 */         if (this.current != -1) {
/* 330 */           reportUnexpectedCharacterError(this.current);
/* 331 */           skipIdentifier();
/*     */         } 
/*     */         break;
/*     */     } 
/* 335 */     skipSpaces();
/* 336 */     if (this.current != -1) {
/* 337 */       reportError("end.of.stream.expected", new Object[] { Integer.valueOf(this.current) });
/*     */     }
/*     */ 
/*     */     
/* 341 */     this.preserveAspectRatioHandler.endPreserveAspectRatio();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void skipIdentifier() throws IOException {
/*     */     do {
/* 349 */       this.current = this.reader.read();
/* 350 */       switch (this.current) { case 9: case 10: case 13:
/*     */         case 32:
/* 352 */           this.current = this.reader.read();
/*     */           break; }
/*     */     
/* 355 */     } while (this.current != -1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/PreserveAspectRatioParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */