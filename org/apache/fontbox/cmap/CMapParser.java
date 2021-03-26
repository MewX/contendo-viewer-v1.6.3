/*     */ package org.apache.fontbox.cmap;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.fontbox.util.Charsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CMapParser
/*     */ {
/*     */   private static final String MARK_END_OF_DICTIONARY = ">>";
/*     */   private static final String MARK_END_OF_ARRAY = "]";
/*  41 */   private final byte[] tokenParserByteBuffer = new byte[512];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CMap parse(File file) throws IOException {
/*  59 */     FileInputStream input = null;
/*     */     
/*     */     try {
/*  62 */       input = new FileInputStream(file);
/*  63 */       return parse(input);
/*     */     }
/*     */     finally {
/*     */       
/*  67 */       if (input != null)
/*     */       {
/*  69 */         input.close();
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
/*     */   public CMap parsePredefined(String name) throws IOException {
/*  83 */     InputStream input = null;
/*     */     
/*     */     try {
/*  86 */       input = getExternalCMap(name);
/*  87 */       return parse(input);
/*     */     }
/*     */     finally {
/*     */       
/*  91 */       if (input != null)
/*     */       {
/*  93 */         input.close();
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
/*     */   public CMap parse(InputStream input) throws IOException {
/* 107 */     PushbackInputStream cmapStream = new PushbackInputStream(input);
/* 108 */     CMap result = new CMap();
/* 109 */     Object previousToken = null;
/*     */     Object token;
/* 111 */     while ((token = parseNextToken(cmapStream)) != null) {
/*     */       
/* 113 */       if (token instanceof Operator) {
/*     */         
/* 115 */         Operator op = (Operator)token;
/* 116 */         if (op.op.equals("usecmap")) {
/*     */           
/* 118 */           parseUsecmap((LiteralName)previousToken, result);
/*     */         } else {
/* 120 */           if (op.op.equals("endcmap")) {
/*     */             break;
/*     */           }
/*     */ 
/*     */           
/* 125 */           if (op.op.equals("begincodespacerange")) {
/*     */             
/* 127 */             parseBegincodespacerange((Number)previousToken, cmapStream, result);
/*     */           }
/* 129 */           else if (op.op.equals("beginbfchar")) {
/*     */             
/* 131 */             parseBeginbfchar((Number)previousToken, cmapStream, result);
/*     */           }
/* 133 */           else if (op.op.equals("beginbfrange")) {
/*     */             
/* 135 */             parseBeginbfrange((Number)previousToken, cmapStream, result);
/*     */           }
/* 137 */           else if (op.op.equals("begincidchar")) {
/*     */             
/* 139 */             parseBegincidchar((Number)previousToken, cmapStream, result);
/*     */           }
/* 141 */           else if (op.op.equals("begincidrange")) {
/*     */             
/* 143 */             parseBegincidrange(((Integer)previousToken).intValue(), cmapStream, result);
/*     */           } 
/*     */         } 
/* 146 */       } else if (token instanceof LiteralName) {
/*     */         
/* 148 */         parseLiteralName((LiteralName)token, cmapStream, result);
/*     */       } 
/* 150 */       previousToken = token;
/*     */     } 
/* 152 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseUsecmap(LiteralName useCmapName, CMap result) throws IOException {
/* 157 */     InputStream useStream = getExternalCMap(useCmapName.name);
/* 158 */     CMap useCMap = parse(useStream);
/* 159 */     result.useCmap(useCMap);
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseLiteralName(LiteralName literal, PushbackInputStream cmapStream, CMap result) throws IOException {
/* 164 */     if ("WMode".equals(literal.name)) {
/*     */       
/* 166 */       Object next = parseNextToken(cmapStream);
/* 167 */       if (next instanceof Integer)
/*     */       {
/* 169 */         result.setWMode(((Integer)next).intValue());
/*     */       }
/*     */     }
/* 172 */     else if ("CMapName".equals(literal.name)) {
/*     */       
/* 174 */       Object next = parseNextToken(cmapStream);
/* 175 */       if (next instanceof LiteralName)
/*     */       {
/* 177 */         result.setName(((LiteralName)next).name);
/*     */       }
/*     */     }
/* 180 */     else if ("CMapVersion".equals(literal.name)) {
/*     */       
/* 182 */       Object next = parseNextToken(cmapStream);
/* 183 */       if (next instanceof Number)
/*     */       {
/* 185 */         result.setVersion(next.toString());
/*     */       }
/* 187 */       else if (next instanceof String)
/*     */       {
/* 189 */         result.setVersion((String)next);
/*     */       }
/*     */     
/* 192 */     } else if ("CMapType".equals(literal.name)) {
/*     */       
/* 194 */       Object next = parseNextToken(cmapStream);
/* 195 */       if (next instanceof Integer)
/*     */       {
/* 197 */         result.setType(((Integer)next).intValue());
/*     */       }
/*     */     }
/* 200 */     else if ("Registry".equals(literal.name)) {
/*     */       
/* 202 */       Object next = parseNextToken(cmapStream);
/* 203 */       if (next instanceof String)
/*     */       {
/* 205 */         result.setRegistry((String)next);
/*     */       }
/*     */     }
/* 208 */     else if ("Ordering".equals(literal.name)) {
/*     */       
/* 210 */       Object next = parseNextToken(cmapStream);
/* 211 */       if (next instanceof String)
/*     */       {
/* 213 */         result.setOrdering((String)next);
/*     */       }
/*     */     }
/* 216 */     else if ("Supplement".equals(literal.name)) {
/*     */       
/* 218 */       Object next = parseNextToken(cmapStream);
/* 219 */       if (next instanceof Integer)
/*     */       {
/* 221 */         result.setSupplement(((Integer)next).intValue());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseBegincodespacerange(Number cosCount, PushbackInputStream cmapStream, CMap result) throws IOException {
/* 228 */     for (int j = 0; j < cosCount.intValue(); j++) {
/*     */       
/* 230 */       Object nextToken = parseNextToken(cmapStream);
/* 231 */       if (nextToken instanceof Operator) {
/*     */         
/* 233 */         if (!((Operator)nextToken).op.equals("endcodespacerange"))
/*     */         {
/* 235 */           throw new IOException("Error : ~codespacerange contains an unexpected operator : " + ((Operator)nextToken)
/* 236 */               .op);
/*     */         }
/*     */         break;
/*     */       } 
/* 240 */       byte[] startRange = (byte[])nextToken;
/* 241 */       byte[] endRange = (byte[])parseNextToken(cmapStream);
/* 242 */       CodespaceRange range = new CodespaceRange();
/* 243 */       range.setStart(startRange);
/* 244 */       range.setEnd(endRange);
/* 245 */       result.addCodespaceRange(range);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseBeginbfchar(Number cosCount, PushbackInputStream cmapStream, CMap result) throws IOException {
/* 251 */     for (int j = 0; j < cosCount.intValue(); j++) {
/*     */       
/* 253 */       Object nextToken = parseNextToken(cmapStream);
/* 254 */       if (nextToken instanceof Operator) {
/*     */         
/* 256 */         if (!((Operator)nextToken).op.equals("endbfchar"))
/*     */         {
/* 258 */           throw new IOException("Error : ~bfchar contains an unexpected operator : " + ((Operator)nextToken)
/* 259 */               .op);
/*     */         }
/*     */         break;
/*     */       } 
/* 263 */       byte[] inputCode = (byte[])nextToken;
/* 264 */       nextToken = parseNextToken(cmapStream);
/* 265 */       if (nextToken instanceof byte[]) {
/*     */         
/* 267 */         byte[] bytes = (byte[])nextToken;
/* 268 */         String value = createStringFromBytes(bytes);
/* 269 */         result.addCharMapping(inputCode, value);
/*     */       }
/* 271 */       else if (nextToken instanceof LiteralName) {
/*     */         
/* 273 */         result.addCharMapping(inputCode, ((LiteralName)nextToken).name);
/*     */       }
/*     */       else {
/*     */         
/* 277 */         throw new IOException("Error parsing CMap beginbfchar, expected{COSString or COSName} and not " + nextToken);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseBegincidrange(int numberOfLines, PushbackInputStream cmapStream, CMap result) throws IOException {
/* 285 */     for (int n = 0; n < numberOfLines; n++) {
/*     */       
/* 287 */       Object nextToken = parseNextToken(cmapStream);
/* 288 */       if (nextToken instanceof Operator) {
/*     */         
/* 290 */         if (!((Operator)nextToken).op.equals("endcidrange"))
/*     */         {
/* 292 */           throw new IOException("Error : ~cidrange contains an unexpected operator : " + ((Operator)nextToken)
/* 293 */               .op);
/*     */         }
/*     */         break;
/*     */       } 
/* 297 */       byte[] startCode = (byte[])nextToken;
/* 298 */       int start = createIntFromBytes(startCode);
/* 299 */       byte[] endCode = (byte[])parseNextToken(cmapStream);
/* 300 */       int end = createIntFromBytes(endCode);
/* 301 */       int mappedCode = ((Integer)parseNextToken(cmapStream)).intValue();
/* 302 */       if (startCode.length <= 2 && endCode.length <= 2) {
/*     */ 
/*     */         
/* 305 */         if (end == start)
/*     */         {
/* 307 */           result.addCIDMapping(mappedCode, start);
/*     */         }
/*     */         else
/*     */         {
/* 311 */           result.addCIDRange((char)start, (char)end, mappedCode);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 317 */         int endOfMappings = mappedCode + end - start;
/* 318 */         while (mappedCode <= endOfMappings) {
/*     */           
/* 320 */           int mappedCID = createIntFromBytes(startCode);
/* 321 */           result.addCIDMapping(mappedCode++, mappedCID);
/* 322 */           increment(startCode);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseBegincidchar(Number cosCount, PushbackInputStream cmapStream, CMap result) throws IOException {
/* 330 */     for (int j = 0; j < cosCount.intValue(); j++) {
/*     */       
/* 332 */       Object nextToken = parseNextToken(cmapStream);
/* 333 */       if (nextToken instanceof Operator) {
/*     */         
/* 335 */         if (!((Operator)nextToken).op.equals("endcidchar"))
/*     */         {
/* 337 */           throw new IOException("Error : ~cidchar contains an unexpected operator : " + ((Operator)nextToken)
/* 338 */               .op);
/*     */         }
/*     */         break;
/*     */       } 
/* 342 */       byte[] inputCode = (byte[])nextToken;
/* 343 */       int mappedCode = ((Integer)parseNextToken(cmapStream)).intValue();
/* 344 */       int mappedCID = createIntFromBytes(inputCode);
/* 345 */       result.addCIDMapping(mappedCode, mappedCID);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseBeginbfrange(Number cosCount, PushbackInputStream cmapStream, CMap result) throws IOException {
/* 351 */     for (int j = 0; j < cosCount.intValue(); j++) {
/*     */       
/* 353 */       Object nextToken = parseNextToken(cmapStream);
/* 354 */       if (nextToken instanceof Operator) {
/*     */         
/* 356 */         if (!((Operator)nextToken).op.equals("endbfrange"))
/*     */         {
/* 358 */           throw new IOException("Error : ~bfrange contains an unexpected operator : " + ((Operator)nextToken)
/* 359 */               .op);
/*     */         }
/*     */         break;
/*     */       } 
/* 363 */       byte[] startCode = (byte[])nextToken;
/* 364 */       byte[] endCode = (byte[])parseNextToken(cmapStream);
/* 365 */       int start = CMap.toInt(startCode, startCode.length);
/* 366 */       int end = CMap.toInt(endCode, endCode.length);
/*     */       
/* 368 */       if (end < start) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 373 */       nextToken = parseNextToken(cmapStream);
/* 374 */       if (nextToken instanceof List) {
/*     */         
/* 376 */         List<byte[]> array = (List<byte[]>)nextToken;
/*     */         
/* 378 */         if (!array.isEmpty() && array.size() >= end - start)
/*     */         {
/* 380 */           addMappingFrombfrange(result, startCode, array);
/*     */         
/*     */         }
/*     */       }
/* 384 */       else if (nextToken instanceof byte[]) {
/*     */ 
/*     */         
/* 387 */         if (end - start > 255) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 392 */         byte[] tokenBytes = (byte[])nextToken;
/*     */         
/* 394 */         if (tokenBytes.length > 0)
/*     */         {
/* 396 */           addMappingFrombfrange(result, startCode, end - start + 1, tokenBytes);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addMappingFrombfrange(CMap cmap, byte[] startCode, List<byte[]> tokenBytesList) {
/* 404 */     for (byte[] tokenBytes : tokenBytesList) {
/*     */       
/* 406 */       String value = createStringFromBytes(tokenBytes);
/* 407 */       cmap.addCharMapping(startCode, value);
/* 408 */       increment(startCode);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addMappingFrombfrange(CMap cmap, byte[] startCode, int values, byte[] tokenBytes) {
/* 415 */     for (int i = 0; i < values; i++) {
/*     */       
/* 417 */       String value = createStringFromBytes(tokenBytes);
/* 418 */       cmap.addCharMapping(startCode, value);
/* 419 */       increment(startCode);
/* 420 */       increment(tokenBytes);
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
/*     */   protected InputStream getExternalCMap(String name) throws IOException {
/* 433 */     InputStream is = getClass().getResourceAsStream(name);
/* 434 */     if (is == null)
/*     */     {
/* 436 */       throw new IOException("Error: Could not find referenced cmap stream " + name);
/*     */     }
/* 438 */     return is; } private Object parseNextToken(PushbackInputStream is) throws IOException { StringBuilder stringBuilder1; int secondCloseBrace; List<Object> list;
/*     */     int theNextByte, i;
/*     */     Object nextToken;
/*     */     int stringByte;
/*     */     String value;
/* 443 */     Object<Object> retval = null;
/* 444 */     int nextByte = is.read();
/*     */     
/* 446 */     while (nextByte == 9 || nextByte == 32 || nextByte == 13 || nextByte == 10)
/*     */     {
/* 448 */       nextByte = is.read();
/*     */     }
/* 450 */     switch (nextByte)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/*     */       case 37:
/* 456 */         stringBuilder1 = new StringBuilder();
/* 457 */         stringBuilder1.append((char)nextByte);
/* 458 */         readUntilEndOfLine(is, stringBuilder1);
/* 459 */         retval = (Object<Object>)stringBuilder1.toString();
/*     */ 
/*     */ 
/*     */       
/*     */       case 40:
/* 464 */         stringBuilder1 = new StringBuilder();
/* 465 */         i = is.read();
/*     */         
/* 467 */         while (i != -1 && i != 41) {
/*     */           
/* 469 */           stringBuilder1.append((char)i);
/* 470 */           i = is.read();
/*     */         } 
/* 472 */         retval = (Object<Object>)stringBuilder1.toString();
/*     */ 
/*     */ 
/*     */       
/*     */       case 62:
/* 477 */         secondCloseBrace = is.read();
/* 478 */         if (secondCloseBrace == 62) {
/*     */           
/* 480 */           retval = (Object<Object>)">>";
/*     */         }
/*     */         else {
/*     */           
/* 484 */           throw new IOException("Error: expected the end of a dictionary.");
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       case 93:
/* 490 */         retval = (Object<Object>)"]";
/*     */ 
/*     */ 
/*     */       
/*     */       case 91:
/* 495 */         list = new ArrayList();
/*     */         
/* 497 */         nextToken = parseNextToken(is);
/* 498 */         while (nextToken != null && !"]".equals(nextToken)) {
/*     */           
/* 500 */           list.add(nextToken);
/* 501 */           nextToken = parseNextToken(is);
/*     */         } 
/* 503 */         retval = (Object<Object>)list;
/*     */ 
/*     */ 
/*     */       
/*     */       case 60:
/* 508 */         theNextByte = is.read();
/* 509 */         if (theNextByte == 60) {
/*     */           
/* 511 */           Map<String, Object> result = new HashMap<String, Object>();
/*     */           
/* 513 */           Object key = parseNextToken(is);
/* 514 */           while (key instanceof LiteralName && !">>".equals(key)) {
/*     */             
/* 516 */             Object object = parseNextToken(is);
/* 517 */             result.put(((LiteralName)key).name, object);
/* 518 */             key = parseNextToken(is);
/*     */           } 
/* 520 */           Map<String, Object> map1 = result;
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 526 */           int multiplyer = 16;
/* 527 */           int bufferIndex = -1;
/* 528 */           while (theNextByte != -1 && theNextByte != 62) {
/*     */             
/* 530 */             int intValue = 0;
/* 531 */             if (theNextByte >= 48 && theNextByte <= 57) {
/*     */               
/* 533 */               intValue = theNextByte - 48;
/*     */             }
/* 535 */             else if (theNextByte >= 65 && theNextByte <= 70) {
/*     */               
/* 537 */               intValue = 10 + theNextByte - 65;
/*     */             }
/* 539 */             else if (theNextByte >= 97 && theNextByte <= 102) {
/*     */               
/* 541 */               intValue = 10 + theNextByte - 97;
/*     */             }
/*     */             else {
/*     */               
/* 545 */               if (isWhitespaceOrEOF(theNextByte)) {
/*     */ 
/*     */                 
/* 548 */                 theNextByte = is.read();
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/* 553 */               throw new IOException("Error: expected hex character and not " + (char)theNextByte + ":" + theNextByte);
/*     */             } 
/*     */             
/* 556 */             intValue *= multiplyer;
/* 557 */             if (multiplyer == 16) {
/*     */               
/* 559 */               bufferIndex++;
/* 560 */               this.tokenParserByteBuffer[bufferIndex] = 0;
/* 561 */               multiplyer = 1;
/*     */             }
/*     */             else {
/*     */               
/* 565 */               multiplyer = 16;
/*     */             } 
/* 567 */             this.tokenParserByteBuffer[bufferIndex] = (byte)(this.tokenParserByteBuffer[bufferIndex] + intValue);
/* 568 */             theNextByte = is.read();
/*     */           } 
/* 570 */           byte[] finalResult = new byte[bufferIndex + 1];
/* 571 */           System.arraycopy(this.tokenParserByteBuffer, 0, finalResult, 0, bufferIndex + 1);
/* 572 */           byte[] arrayOfByte1 = finalResult;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       case 47:
/* 578 */         buffer = new StringBuilder();
/* 579 */         stringByte = is.read();
/*     */         
/* 581 */         while (!isWhitespaceOrEOF(stringByte) && !isDelimiter(stringByte)) {
/*     */           
/* 583 */           buffer.append((char)stringByte);
/* 584 */           stringByte = is.read();
/*     */         } 
/* 586 */         if (isDelimiter(stringByte))
/*     */         {
/* 588 */           is.unread(stringByte);
/*     */         }
/* 590 */         retval = (Object<Object>)new LiteralName(buffer.toString());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case -1:
/* 652 */         return retval;
/*     */       case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55:
/*     */       case 56:
/*     */       case 57:
/*     */         buffer = new StringBuilder(); buffer.append((char)nextByte); nextByte = is.read(); while (!isWhitespaceOrEOF(nextByte) && (Character.isDigit((char)nextByte) || nextByte == 46)) { buffer.append((char)nextByte); nextByte = is.read(); }  is.unread(nextByte); value = buffer.toString(); if (value.indexOf('.') >= 0) { retval = (Object<Object>)Double.valueOf(value); } else { retval = (Object<Object>)Integer.valueOf(value); }  }  StringBuilder buffer = new StringBuilder(); buffer.append((char)nextByte); nextByte = is.read(); while (!isWhitespaceOrEOF(nextByte) && !isDelimiter(nextByte) && !Character.isDigit(nextByte)) { buffer.append((char)nextByte); nextByte = is.read(); }  if (isDelimiter(nextByte) || Character.isDigit(nextByte))
/* 657 */       is.unread(nextByte);  retval = (Object<Object>)new Operator(buffer.toString()); } private void readUntilEndOfLine(InputStream is, StringBuilder buf) throws IOException { int nextByte = is.read();
/* 658 */     while (nextByte != -1 && nextByte != 13 && nextByte != 10) {
/*     */       
/* 660 */       buf.append((char)nextByte);
/* 661 */       nextByte = is.read();
/*     */     }  }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isWhitespaceOrEOF(int aByte) {
/* 667 */     return (aByte == -1 || aByte == 32 || aByte == 13 || aByte == 10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isDelimiter(int aByte) {
/* 673 */     switch (aByte) {
/*     */       
/*     */       case 37:
/*     */       case 40:
/*     */       case 41:
/*     */       case 47:
/*     */       case 60:
/*     */       case 62:
/*     */       case 91:
/*     */       case 93:
/*     */       case 123:
/*     */       case 125:
/* 685 */         return true;
/*     */     } 
/* 687 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void increment(byte[] data) {
/* 693 */     increment(data, data.length - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void increment(byte[] data, int position) {
/* 698 */     if (position > 0 && (data[position] & 0xFF) == 255) {
/*     */       
/* 700 */       data[position] = 0;
/* 701 */       increment(data, position - 1);
/*     */     }
/*     */     else {
/*     */       
/* 705 */       data[position] = (byte)(data[position] + 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int createIntFromBytes(byte[] bytes) {
/* 711 */     int intValue = bytes[0] & 0xFF;
/* 712 */     if (bytes.length == 2) {
/*     */       
/* 714 */       intValue <<= 8;
/* 715 */       intValue += bytes[1] & 0xFF;
/*     */     } 
/* 717 */     return intValue;
/*     */   }
/*     */ 
/*     */   
/*     */   private String createStringFromBytes(byte[] bytes) {
/* 722 */     return new String(bytes, (bytes.length == 1) ? Charsets.ISO_8859_1 : Charsets.UTF_16BE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class LiteralName
/*     */   {
/*     */     private String name;
/*     */ 
/*     */ 
/*     */     
/*     */     private LiteralName(String theName) {
/* 734 */       this.name = theName;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class Operator
/*     */   {
/*     */     private String op;
/*     */ 
/*     */ 
/*     */     
/*     */     private Operator(String theOp) {
/* 747 */       this.op = theOp;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cmap/CMapParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */