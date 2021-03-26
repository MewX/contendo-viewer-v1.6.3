/*     */ package org.apache.fontbox.type1;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.fontbox.encoding.BuiltInEncoding;
/*     */ import org.apache.fontbox.encoding.Encoding;
/*     */ import org.apache.fontbox.encoding.StandardEncoding;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Type1Parser
/*     */ {
/*     */   private static final int EEXEC_KEY = 55665;
/*     */   private static final int CHARSTRING_KEY = 4330;
/*     */   private Type1Lexer lexer;
/*     */   private Type1Font font;
/*     */   
/*     */   public Type1Font parse(byte[] segment1, byte[] segment2) throws IOException {
/*  60 */     this.font = new Type1Font(segment1, segment2);
/*  61 */     parseASCII(segment1);
/*  62 */     if (segment2.length > 0)
/*     */     {
/*  64 */       parseBinary(segment2);
/*     */     }
/*  66 */     return this.font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseASCII(byte[] bytes) throws IOException {
/*  74 */     if (bytes.length == 0)
/*     */     {
/*  76 */       throw new IllegalArgumentException("byte[] is empty");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  81 */     if (bytes.length < 2 || (bytes[0] != 37 && bytes[1] != 33))
/*     */     {
/*  83 */       throw new IOException("Invalid start of ASCII segment");
/*     */     }
/*     */     
/*  86 */     this.lexer = new Type1Lexer(bytes);
/*     */ 
/*     */     
/*  89 */     if (this.lexer.peekToken().getText().equals("FontDirectory")) {
/*     */       
/*  91 */       read(Token.NAME, "FontDirectory");
/*  92 */       read(Token.LITERAL);
/*  93 */       read(Token.NAME, "known");
/*  94 */       read(Token.START_PROC);
/*  95 */       readProc();
/*  96 */       read(Token.START_PROC);
/*  97 */       readProc();
/*  98 */       read(Token.NAME, "ifelse");
/*     */     } 
/*     */ 
/*     */     
/* 102 */     int length = read(Token.INTEGER).intValue();
/* 103 */     read(Token.NAME, "dict");
/*     */     
/* 105 */     readMaybe(Token.NAME, "dup");
/*     */     
/* 107 */     read(Token.NAME, "begin");
/*     */     
/* 109 */     for (int i = 0; i < length; i++) {
/*     */ 
/*     */       
/* 112 */       Token token = this.lexer.peekToken();
/* 113 */       if (token == null) {
/*     */         break;
/*     */       }
/*     */       
/* 117 */       if (token.getKind() == Token.NAME && (token
/* 118 */         .getText().equals("currentdict") || token.getText().equals("end"))) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 124 */       String key = read(Token.LITERAL).getText();
/* 125 */       if (key.equals("FontInfo") || key.equals("Fontinfo")) {
/*     */         
/* 127 */         readFontInfo(readSimpleDict());
/*     */       }
/* 129 */       else if (key.equals("Metrics")) {
/*     */         
/* 131 */         readSimpleDict();
/*     */       }
/* 133 */       else if (key.equals("Encoding")) {
/*     */         
/* 135 */         readEncoding();
/*     */       }
/*     */       else {
/*     */         
/* 139 */         readSimpleValue(key);
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     readMaybe(Token.NAME, "currentdict");
/* 144 */     read(Token.NAME, "end");
/*     */     
/* 146 */     read(Token.NAME, "currentfile");
/* 147 */     read(Token.NAME, "eexec");
/*     */   }
/*     */ 
/*     */   
/*     */   private void readSimpleValue(String key) throws IOException {
/* 152 */     List<Token> value = readDictValue();
/*     */     
/* 154 */     if (key.equals("FontName")) {
/*     */       
/* 156 */       this.font.fontName = ((Token)value.get(0)).getText();
/*     */     }
/* 158 */     else if (key.equals("PaintType")) {
/*     */       
/* 160 */       this.font.paintType = ((Token)value.get(0)).intValue();
/*     */     }
/* 162 */     else if (key.equals("FontType")) {
/*     */       
/* 164 */       this.font.fontType = ((Token)value.get(0)).intValue();
/*     */     }
/* 166 */     else if (key.equals("FontMatrix")) {
/*     */       
/* 168 */       this.font.fontMatrix = arrayToNumbers(value);
/*     */     }
/* 170 */     else if (key.equals("FontBBox")) {
/*     */       
/* 172 */       this.font.fontBBox = arrayToNumbers(value);
/*     */     }
/* 174 */     else if (key.equals("UniqueID")) {
/*     */       
/* 176 */       this.font.uniqueID = ((Token)value.get(0)).intValue();
/*     */     }
/* 178 */     else if (key.equals("StrokeWidth")) {
/*     */       
/* 180 */       this.font.strokeWidth = ((Token)value.get(0)).floatValue();
/*     */     }
/* 182 */     else if (key.equals("FID")) {
/*     */       
/* 184 */       this.font.fontID = ((Token)value.get(0)).getText();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void readEncoding() throws IOException {
/* 190 */     if (this.lexer.peekToken().getKind() == Token.NAME) {
/*     */       
/* 192 */       String name = this.lexer.nextToken().getText();
/*     */       
/* 194 */       if (name.equals("StandardEncoding")) {
/*     */         
/* 196 */         this.font.encoding = (Encoding)StandardEncoding.INSTANCE;
/*     */       }
/*     */       else {
/*     */         
/* 200 */         throw new IOException("Unknown encoding: " + name);
/*     */       } 
/* 202 */       readMaybe(Token.NAME, "readonly");
/* 203 */       read(Token.NAME, "def");
/*     */     }
/*     */     else {
/*     */       
/* 207 */       read(Token.INTEGER).intValue();
/* 208 */       readMaybe(Token.NAME, "array");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 213 */       while (this.lexer.peekToken().getKind() != Token.NAME || (
/* 214 */         !this.lexer.peekToken().getText().equals("dup") && 
/* 215 */         !this.lexer.peekToken().getText().equals("readonly") && 
/* 216 */         !this.lexer.peekToken().getText().equals("def")))
/*     */       {
/* 218 */         this.lexer.nextToken();
/*     */       }
/*     */       
/* 221 */       Map<Integer, String> codeToName = new HashMap<Integer, String>();
/* 222 */       while (this.lexer.peekToken().getKind() == Token.NAME && this.lexer
/* 223 */         .peekToken().getText().equals("dup")) {
/*     */         
/* 225 */         read(Token.NAME, "dup");
/* 226 */         int code = read(Token.INTEGER).intValue();
/* 227 */         String name = read(Token.LITERAL).getText();
/* 228 */         read(Token.NAME, "put");
/* 229 */         codeToName.put(Integer.valueOf(code), name);
/*     */       } 
/* 231 */       this.font.encoding = (Encoding)new BuiltInEncoding(codeToName);
/* 232 */       readMaybe(Token.NAME, "readonly");
/* 233 */       read(Token.NAME, "def");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Number> arrayToNumbers(List<Token> value) throws IOException {
/* 242 */     List<Number> numbers = new ArrayList<Number>();
/* 243 */     for (int i = 1, size = value.size() - 1; i < size; i++) {
/*     */       
/* 245 */       Token token = value.get(i);
/* 246 */       if (token.getKind() == Token.REAL) {
/*     */         
/* 248 */         numbers.add(Float.valueOf(token.floatValue()));
/*     */       }
/* 250 */       else if (token.getKind() == Token.INTEGER) {
/*     */         
/* 252 */         numbers.add(Integer.valueOf(token.intValue()));
/*     */       }
/*     */       else {
/*     */         
/* 256 */         throw new IOException("Expected INTEGER or REAL but got " + token.getKind());
/*     */       } 
/*     */     } 
/* 259 */     return numbers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readFontInfo(Map<String, List<Token>> fontInfo) {
/* 267 */     for (Map.Entry<String, List<Token>> entry : fontInfo.entrySet()) {
/*     */       
/* 269 */       String key = entry.getKey();
/* 270 */       List<Token> value = entry.getValue();
/*     */       
/* 272 */       if (key.equals("version")) {
/*     */         
/* 274 */         this.font.version = ((Token)value.get(0)).getText(); continue;
/*     */       } 
/* 276 */       if (key.equals("Notice")) {
/*     */         
/* 278 */         this.font.notice = ((Token)value.get(0)).getText(); continue;
/*     */       } 
/* 280 */       if (key.equals("FullName")) {
/*     */         
/* 282 */         this.font.fullName = ((Token)value.get(0)).getText(); continue;
/*     */       } 
/* 284 */       if (key.equals("FamilyName")) {
/*     */         
/* 286 */         this.font.familyName = ((Token)value.get(0)).getText(); continue;
/*     */       } 
/* 288 */       if (key.equals("Weight")) {
/*     */         
/* 290 */         this.font.weight = ((Token)value.get(0)).getText(); continue;
/*     */       } 
/* 292 */       if (key.equals("ItalicAngle")) {
/*     */         
/* 294 */         this.font.italicAngle = ((Token)value.get(0)).floatValue(); continue;
/*     */       } 
/* 296 */       if (key.equals("isFixedPitch")) {
/*     */         
/* 298 */         this.font.isFixedPitch = ((Token)value.get(0)).booleanValue(); continue;
/*     */       } 
/* 300 */       if (key.equals("UnderlinePosition")) {
/*     */         
/* 302 */         this.font.underlinePosition = ((Token)value.get(0)).floatValue(); continue;
/*     */       } 
/* 304 */       if (key.equals("UnderlineThickness"))
/*     */       {
/* 306 */         this.font.underlineThickness = ((Token)value.get(0)).floatValue();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<String, List<Token>> readSimpleDict() throws IOException {
/* 317 */     Map<String, List<Token>> dict = new HashMap<String, List<Token>>();
/*     */     
/* 319 */     int length = read(Token.INTEGER).intValue();
/* 320 */     read(Token.NAME, "dict");
/* 321 */     readMaybe(Token.NAME, "dup");
/* 322 */     read(Token.NAME, "begin");
/*     */     
/* 324 */     for (int i = 0; i < length; i++) {
/*     */       
/* 326 */       if (this.lexer.peekToken() == null) {
/*     */         break;
/*     */       }
/*     */       
/* 330 */       if (this.lexer.peekToken().getKind() == Token.NAME && 
/* 331 */         !this.lexer.peekToken().getText().equals("end"))
/*     */       {
/* 333 */         read(Token.NAME);
/*     */       }
/*     */       
/* 336 */       if (this.lexer.peekToken() == null) {
/*     */         break;
/*     */       }
/*     */       
/* 340 */       if (this.lexer.peekToken().getKind() == Token.NAME && this.lexer
/* 341 */         .peekToken().getText().equals("end")) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 347 */       String key = read(Token.LITERAL).getText();
/* 348 */       List<Token> value = readDictValue();
/* 349 */       dict.put(key, value);
/*     */     } 
/*     */     
/* 352 */     read(Token.NAME, "end");
/* 353 */     readMaybe(Token.NAME, "readonly");
/* 354 */     read(Token.NAME, "def");
/*     */     
/* 356 */     return dict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Token> readDictValue() throws IOException {
/* 364 */     List<Token> value = readValue();
/* 365 */     readDef();
/* 366 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Token> readValue() throws IOException {
/* 376 */     List<Token> value = new ArrayList<Token>();
/* 377 */     Token token = this.lexer.nextToken();
/* 378 */     if (this.lexer.peekToken() == null)
/*     */     {
/* 380 */       return value;
/*     */     }
/* 382 */     value.add(token);
/*     */     
/* 384 */     if (token.getKind() == Token.START_ARRAY) {
/*     */       
/* 386 */       int openArray = 1;
/*     */       
/*     */       while (true) {
/* 389 */         if (this.lexer.peekToken() == null)
/*     */         {
/* 391 */           return value;
/*     */         }
/* 393 */         if (this.lexer.peekToken().getKind() == Token.START_ARRAY)
/*     */         {
/* 395 */           openArray++;
/*     */         }
/*     */         
/* 398 */         token = this.lexer.nextToken();
/* 399 */         value.add(token);
/*     */         
/* 401 */         if (token.getKind() == Token.END_ARRAY)
/*     */         {
/* 403 */           openArray--;
/* 404 */           if (openArray == 0) {
/*     */             break;
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 411 */     } else if (token.getKind() == Token.START_PROC) {
/*     */       
/* 413 */       value.addAll(readProc());
/*     */     }
/* 415 */     else if (token.getKind() == Token.START_DICT) {
/*     */ 
/*     */       
/* 418 */       read(Token.END_DICT);
/* 419 */       return value;
/*     */     } 
/*     */     
/* 422 */     readPostScriptWrapper(value);
/* 423 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readPostScriptWrapper(List<Token> value) throws IOException {
/* 429 */     if (this.lexer.peekToken().getText().equals("systemdict")) {
/*     */       
/* 431 */       read(Token.NAME, "systemdict");
/* 432 */       read(Token.LITERAL, "internaldict");
/* 433 */       read(Token.NAME, "known");
/*     */       
/* 435 */       read(Token.START_PROC);
/* 436 */       readProc();
/*     */       
/* 438 */       read(Token.START_PROC);
/* 439 */       readProc();
/*     */       
/* 441 */       read(Token.NAME, "ifelse");
/*     */ 
/*     */       
/* 444 */       read(Token.START_PROC);
/* 445 */       read(Token.NAME, "pop");
/* 446 */       value.clear();
/* 447 */       value.addAll(readValue());
/* 448 */       read(Token.END_PROC);
/*     */       
/* 450 */       read(Token.NAME, "if");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Token> readProc() throws IOException {
/* 459 */     List<Token> value = new ArrayList<Token>();
/*     */     
/* 461 */     int openProc = 1;
/*     */     
/*     */     while (true) {
/* 464 */       if (this.lexer.peekToken().getKind() == Token.START_PROC)
/*     */       {
/* 466 */         openProc++;
/*     */       }
/*     */       
/* 469 */       Token token = this.lexer.nextToken();
/* 470 */       value.add(token);
/*     */       
/* 472 */       if (token.getKind() == Token.END_PROC) {
/*     */         
/* 474 */         openProc--;
/* 475 */         if (openProc == 0) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 481 */     Token executeonly = readMaybe(Token.NAME, "executeonly");
/* 482 */     if (executeonly != null)
/*     */     {
/* 484 */       value.add(executeonly);
/*     */     }
/*     */     
/* 487 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseBinary(byte[] bytes) throws IOException {
/*     */     byte[] decrypted;
/* 497 */     if (isBinary(bytes)) {
/*     */       
/* 499 */       decrypted = decrypt(bytes, 55665, 4);
/*     */     }
/*     */     else {
/*     */       
/* 503 */       decrypted = decrypt(hexToBinary(bytes), 55665, 4);
/*     */     } 
/* 505 */     this.lexer = new Type1Lexer(decrypted);
/*     */ 
/*     */     
/* 508 */     Token peekToken = this.lexer.peekToken();
/* 509 */     while (peekToken != null && !peekToken.getText().equals("Private")) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 514 */       this.lexer.nextToken();
/* 515 */       peekToken = this.lexer.peekToken();
/*     */     } 
/* 517 */     if (peekToken == null)
/*     */     {
/* 519 */       throw new IOException("/Private token not found");
/*     */     }
/*     */ 
/*     */     
/* 523 */     read(Token.LITERAL, "Private");
/* 524 */     int length = read(Token.INTEGER).intValue();
/* 525 */     read(Token.NAME, "dict");
/*     */ 
/*     */     
/* 528 */     readMaybe(Token.NAME, "dup");
/* 529 */     read(Token.NAME, "begin");
/*     */     
/* 531 */     int lenIV = 4;
/*     */     
/* 533 */     for (int i = 0; i < length; i++) {
/*     */ 
/*     */       
/* 536 */       if (this.lexer.peekToken() == null || this.lexer.peekToken().getKind() != Token.LITERAL) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 542 */       String key = read(Token.LITERAL).getText();
/*     */       
/* 544 */       if ("Subrs".equals(key)) {
/*     */         
/* 546 */         readSubrs(lenIV);
/*     */       }
/* 548 */       else if ("OtherSubrs".equals(key)) {
/*     */         
/* 550 */         readOtherSubrs();
/*     */       }
/* 552 */       else if ("lenIV".equals(key)) {
/*     */         
/* 554 */         lenIV = ((Token)readDictValue().get(0)).intValue();
/*     */       }
/* 556 */       else if ("ND".equals(key)) {
/*     */         
/* 558 */         read(Token.START_PROC);
/*     */         
/* 560 */         readMaybe(Token.NAME, "noaccess");
/* 561 */         read(Token.NAME, "def");
/* 562 */         read(Token.END_PROC);
/* 563 */         readMaybe(Token.NAME, "executeonly");
/* 564 */         read(Token.NAME, "def");
/*     */       }
/* 566 */       else if ("NP".equals(key)) {
/*     */         
/* 568 */         read(Token.START_PROC);
/* 569 */         readMaybe(Token.NAME, "noaccess");
/* 570 */         read(Token.NAME);
/* 571 */         read(Token.END_PROC);
/* 572 */         readMaybe(Token.NAME, "executeonly");
/* 573 */         read(Token.NAME, "def");
/*     */       }
/* 575 */       else if ("RD".equals(key)) {
/*     */ 
/*     */         
/* 578 */         read(Token.START_PROC);
/* 579 */         readProc();
/* 580 */         readMaybe(Token.NAME, "bind");
/* 581 */         readMaybe(Token.NAME, "executeonly");
/* 582 */         read(Token.NAME, "def");
/*     */       }
/*     */       else {
/*     */         
/* 586 */         readPrivate(key, readDictValue());
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 593 */     while (this.lexer.peekToken().getKind() != Token.LITERAL || 
/* 594 */       !this.lexer.peekToken().getText().equals("CharStrings"))
/*     */     {
/* 596 */       this.lexer.nextToken();
/*     */     }
/*     */ 
/*     */     
/* 600 */     read(Token.LITERAL, "CharStrings");
/* 601 */     readCharStrings(lenIV);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readPrivate(String key, List<Token> value) throws IOException {
/* 609 */     if (key.equals("BlueValues")) {
/*     */       
/* 611 */       this.font.blueValues = arrayToNumbers(value);
/*     */     }
/* 613 */     else if (key.equals("OtherBlues")) {
/*     */       
/* 615 */       this.font.otherBlues = arrayToNumbers(value);
/*     */     }
/* 617 */     else if (key.equals("FamilyBlues")) {
/*     */       
/* 619 */       this.font.familyBlues = arrayToNumbers(value);
/*     */     }
/* 621 */     else if (key.equals("FamilyOtherBlues")) {
/*     */       
/* 623 */       this.font.familyOtherBlues = arrayToNumbers(value);
/*     */     }
/* 625 */     else if (key.equals("BlueScale")) {
/*     */       
/* 627 */       this.font.blueScale = ((Token)value.get(0)).floatValue();
/*     */     }
/* 629 */     else if (key.equals("BlueShift")) {
/*     */       
/* 631 */       this.font.blueShift = ((Token)value.get(0)).intValue();
/*     */     }
/* 633 */     else if (key.equals("BlueFuzz")) {
/*     */       
/* 635 */       this.font.blueFuzz = ((Token)value.get(0)).intValue();
/*     */     }
/* 637 */     else if (key.equals("StdHW")) {
/*     */       
/* 639 */       this.font.stdHW = arrayToNumbers(value);
/*     */     }
/* 641 */     else if (key.equals("StdVW")) {
/*     */       
/* 643 */       this.font.stdVW = arrayToNumbers(value);
/*     */     }
/* 645 */     else if (key.equals("StemSnapH")) {
/*     */       
/* 647 */       this.font.stemSnapH = arrayToNumbers(value);
/*     */     }
/* 649 */     else if (key.equals("StemSnapV")) {
/*     */       
/* 651 */       this.font.stemSnapV = arrayToNumbers(value);
/*     */     }
/* 653 */     else if (key.equals("ForceBold")) {
/*     */       
/* 655 */       this.font.forceBold = ((Token)value.get(0)).booleanValue();
/*     */     }
/* 657 */     else if (key.equals("LanguageGroup")) {
/*     */       
/* 659 */       this.font.languageGroup = ((Token)value.get(0)).intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readSubrs(int lenIV) throws IOException {
/* 670 */     int length = read(Token.INTEGER).intValue(); int i;
/* 671 */     for (i = 0; i < length; i++)
/*     */     {
/* 673 */       this.font.subrs.add(null);
/*     */     }
/* 675 */     read(Token.NAME, "array");
/*     */     
/* 677 */     for (i = 0; i < length; i++) {
/*     */ 
/*     */       
/* 680 */       if (this.lexer.peekToken() == null) {
/*     */         break;
/*     */       }
/*     */       
/* 684 */       if (this.lexer.peekToken().getKind() != Token.NAME || 
/* 685 */         !this.lexer.peekToken().getText().equals("dup")) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 690 */       read(Token.NAME, "dup");
/* 691 */       Token index = read(Token.INTEGER);
/* 692 */       read(Token.INTEGER);
/*     */ 
/*     */       
/* 695 */       Token charstring = read(Token.CHARSTRING);
/* 696 */       this.font.subrs.set(index.intValue(), decrypt(charstring.getData(), 4330, lenIV));
/* 697 */       readPut();
/*     */     } 
/* 699 */     readDef();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readOtherSubrs() throws IOException {
/* 705 */     if (this.lexer.peekToken().getKind() == Token.START_ARRAY) {
/*     */       
/* 707 */       readValue();
/* 708 */       readDef();
/*     */     }
/*     */     else {
/*     */       
/* 712 */       int length = read(Token.INTEGER).intValue();
/* 713 */       read(Token.NAME, "array");
/*     */       
/* 715 */       for (int i = 0; i < length; i++) {
/*     */         
/* 717 */         read(Token.NAME, "dup");
/* 718 */         read(Token.INTEGER);
/* 719 */         readValue();
/* 720 */         readPut();
/*     */       } 
/* 722 */       readDef();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readCharStrings(int lenIV) throws IOException {
/* 732 */     int length = read(Token.INTEGER).intValue();
/* 733 */     read(Token.NAME, "dict");
/*     */ 
/*     */     
/* 736 */     read(Token.NAME, "dup");
/* 737 */     read(Token.NAME, "begin");
/*     */     
/* 739 */     for (int i = 0; i < length; i++) {
/*     */ 
/*     */       
/* 742 */       if (this.lexer.peekToken() == null) {
/*     */         break;
/*     */       }
/*     */       
/* 746 */       if (this.lexer.peekToken().getKind() == Token.NAME && this.lexer
/* 747 */         .peekToken().getText().equals("end")) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 752 */       String name = read(Token.LITERAL).getText();
/*     */ 
/*     */       
/* 755 */       read(Token.INTEGER);
/* 756 */       Token charstring = read(Token.CHARSTRING);
/* 757 */       this.font.charstrings.put(name, decrypt(charstring.getData(), 4330, lenIV));
/* 758 */       readDef();
/*     */     } 
/*     */ 
/*     */     
/* 762 */     read(Token.NAME, "end");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readDef() throws IOException {
/* 773 */     readMaybe(Token.NAME, "readonly");
/* 774 */     readMaybe(Token.NAME, "noaccess");
/*     */     
/* 776 */     Token token = read(Token.NAME);
/* 777 */     if (token.getText().equals("ND") || token.getText().equals("|-")) {
/*     */       return;
/*     */     }
/*     */     
/* 781 */     if (token.getText().equals("noaccess"))
/*     */     {
/* 783 */       token = read(Token.NAME);
/*     */     }
/*     */     
/* 786 */     if (token.getText().equals("def")) {
/*     */       return;
/*     */     }
/*     */     
/* 790 */     throw new IOException("Found " + token + " but expected ND");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readPut() throws IOException {
/* 798 */     readMaybe(Token.NAME, "readonly");
/*     */     
/* 800 */     Token token = read(Token.NAME);
/* 801 */     if (token.getText().equals("NP") || token.getText().equals("|")) {
/*     */       return;
/*     */     }
/*     */     
/* 805 */     if (token.getText().equals("noaccess"))
/*     */     {
/* 807 */       token = read(Token.NAME);
/*     */     }
/*     */     
/* 810 */     if (token.getText().equals("put")) {
/*     */       return;
/*     */     }
/*     */     
/* 814 */     throw new IOException("Found " + token + " but expected NP");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Token read(Token.Kind kind) throws IOException {
/* 822 */     Token token = this.lexer.nextToken();
/* 823 */     if (token == null || token.getKind() != kind)
/*     */     {
/* 825 */       throw new IOException("Found " + token + " but expected " + kind);
/*     */     }
/* 827 */     return token;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void read(Token.Kind kind, String name) throws IOException {
/* 836 */     Token token = read(kind);
/* 837 */     if (!token.getText().equals(name))
/*     */     {
/* 839 */       throw new IOException("Found " + token + " but expected " + name);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Token readMaybe(Token.Kind kind, String name) throws IOException {
/* 849 */     Token token = this.lexer.peekToken();
/* 850 */     if (token != null && token.getKind() == kind && token.getText().equals(name))
/*     */     {
/* 852 */       return this.lexer.nextToken();
/*     */     }
/* 854 */     return null;
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
/*     */   private byte[] decrypt(byte[] cipherBytes, int r, int n) {
/* 868 */     if (n == -1)
/*     */     {
/* 870 */       return cipherBytes;
/*     */     }
/*     */     
/* 873 */     if (cipherBytes.length == 0 || n > cipherBytes.length)
/*     */     {
/* 875 */       return new byte[0];
/*     */     }
/*     */     
/* 878 */     int c1 = 52845;
/* 879 */     int c2 = 22719;
/* 880 */     byte[] plainBytes = new byte[cipherBytes.length - n];
/* 881 */     for (int i = 0; i < cipherBytes.length; i++) {
/*     */       
/* 883 */       int cipher = cipherBytes[i] & 0xFF;
/* 884 */       int plain = cipher ^ r >> 8;
/* 885 */       if (i >= n)
/*     */       {
/* 887 */         plainBytes[i - n] = (byte)plain;
/*     */       }
/* 889 */       r = (cipher + r) * c1 + c2 & 0xFFFF;
/*     */     } 
/* 891 */     return plainBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isBinary(byte[] bytes) {
/* 898 */     if (bytes.length < 4)
/*     */     {
/* 900 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 904 */     for (int i = 0; i < 4; i++) {
/*     */       
/* 906 */       byte by = bytes[i];
/* 907 */       if (by != 10 && by != 13 && by != 32 && by != 9 && 
/* 908 */         Character.digit((char)by, 16) == -1)
/*     */       {
/* 910 */         return true;
/*     */       }
/*     */     } 
/* 913 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] hexToBinary(byte[] bytes) {
/* 919 */     int len = 0;
/* 920 */     for (byte by : bytes) {
/*     */       
/* 922 */       if (Character.digit((char)by, 16) != -1)
/*     */       {
/* 924 */         len++;
/*     */       }
/*     */     } 
/* 927 */     byte[] res = new byte[len / 2];
/* 928 */     int r = 0;
/* 929 */     int prev = -1;
/* 930 */     for (byte by : bytes) {
/*     */       
/* 932 */       int digit = Character.digit((char)by, 16);
/* 933 */       if (digit != -1)
/*     */       {
/* 935 */         if (prev == -1) {
/*     */           
/* 937 */           prev = digit;
/*     */         }
/*     */         else {
/*     */           
/* 941 */           res[r++] = (byte)(prev * 16 + digit);
/* 942 */           prev = -1;
/*     */         } 
/*     */       }
/*     */     } 
/* 946 */     return res;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/type1/Type1Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */