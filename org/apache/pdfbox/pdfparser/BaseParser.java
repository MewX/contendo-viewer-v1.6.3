/*      */ package org.apache.pdfbox.pdfparser;
/*      */ 
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.nio.charset.CharsetDecoder;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSBoolean;
/*      */ import org.apache.pdfbox.cos.COSDictionary;
/*      */ import org.apache.pdfbox.cos.COSDocument;
/*      */ import org.apache.pdfbox.cos.COSInteger;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSNull;
/*      */ import org.apache.pdfbox.cos.COSNumber;
/*      */ import org.apache.pdfbox.cos.COSObject;
/*      */ import org.apache.pdfbox.cos.COSObjectKey;
/*      */ import org.apache.pdfbox.cos.COSString;
/*      */ import org.apache.pdfbox.util.Charsets;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class BaseParser
/*      */ {
/*      */   private static final long OBJECT_NUMBER_THRESHOLD = 10000000000L;
/*      */   private static final long GENERATION_NUMBER_THRESHOLD = 65535L;
/*   55 */   static final int MAX_LENGTH_LONG = Long.toString(Long.MAX_VALUE).length();
/*      */   
/*   57 */   private final CharsetDecoder utf8Decoder = Charsets.UTF_8.newDecoder();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   62 */   private static final Log LOG = LogFactory.getLog(BaseParser.class);
/*      */ 
/*      */   
/*      */   protected static final int E = 101;
/*      */ 
/*      */   
/*      */   protected static final int N = 110;
/*      */ 
/*      */   
/*      */   protected static final int D = 100;
/*      */ 
/*      */   
/*      */   protected static final int S = 115;
/*      */ 
/*      */   
/*      */   protected static final int T = 116;
/*      */ 
/*      */   
/*      */   protected static final int R = 114;
/*      */ 
/*      */   
/*      */   protected static final int A = 97;
/*      */ 
/*      */   
/*      */   protected static final int M = 109;
/*      */ 
/*      */   
/*      */   protected static final int O = 111;
/*      */ 
/*      */   
/*      */   protected static final int B = 98;
/*      */ 
/*      */   
/*      */   protected static final int J = 106;
/*      */ 
/*      */   
/*      */   public static final String DEF = "def";
/*      */ 
/*      */   
/*      */   protected static final String ENDOBJ_STRING = "endobj";
/*      */ 
/*      */   
/*      */   protected static final String ENDSTREAM_STRING = "endstream";
/*      */ 
/*      */   
/*      */   protected static final String STREAM_STRING = "stream";
/*      */ 
/*      */   
/*      */   private static final String TRUE = "true";
/*      */ 
/*      */   
/*      */   private static final String FALSE = "false";
/*      */ 
/*      */   
/*      */   private static final String NULL = "null";
/*      */   
/*      */   protected static final byte ASCII_LF = 10;
/*      */   
/*      */   protected static final byte ASCII_CR = 13;
/*      */   
/*      */   private static final byte ASCII_ZERO = 48;
/*      */   
/*      */   private static final byte ASCII_NINE = 57;
/*      */   
/*      */   private static final byte ASCII_SPACE = 32;
/*      */   
/*      */   protected final SequentialSource seqSource;
/*      */   
/*      */   protected COSDocument document;
/*      */ 
/*      */   
/*      */   public BaseParser(SequentialSource pdfSource) {
/*  134 */     this.seqSource = pdfSource;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean isHexDigit(char ch) {
/*  139 */     return (isDigit(ch) || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F'));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private COSBase parseCOSDictionaryValue() throws IOException {
/*  153 */     long numOffset = this.seqSource.getPosition();
/*  154 */     COSBase value = parseDirObject();
/*  155 */     skipSpaces();
/*      */     
/*  157 */     if (!(value instanceof COSNumber) || !isDigit())
/*      */     {
/*  159 */       return value;
/*      */     }
/*      */     
/*  162 */     long genOffset = this.seqSource.getPosition();
/*  163 */     COSBase generationNumber = parseDirObject();
/*  164 */     skipSpaces();
/*  165 */     readExpectedChar('R');
/*  166 */     if (!(value instanceof COSInteger)) {
/*      */       
/*  168 */       LOG.error("expected number, actual=" + value + " at offset " + numOffset);
/*  169 */       return (COSBase)COSNull.NULL;
/*      */     } 
/*  171 */     if (!(generationNumber instanceof COSInteger)) {
/*      */       
/*  173 */       LOG.error("expected number, actual=" + value + " at offset " + genOffset);
/*  174 */       return (COSBase)COSNull.NULL;
/*      */     } 
/*      */     
/*  177 */     COSObjectKey key = new COSObjectKey(((COSInteger)value).longValue(), ((COSInteger)generationNumber).intValue());
/*      */     
/*  179 */     return getObjectFromPool(key);
/*      */   }
/*      */ 
/*      */   
/*      */   private COSBase getObjectFromPool(COSObjectKey key) throws IOException {
/*  184 */     if (this.document == null)
/*      */     {
/*  186 */       throw new IOException("object reference " + key + " at offset " + this.seqSource.getPosition() + " in content stream");
/*      */     }
/*      */     
/*  189 */     return (COSBase)this.document.getObjectFromPool(key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected COSDictionary parseCOSDictionary() throws IOException {
/*  201 */     readExpectedChar('<');
/*  202 */     readExpectedChar('<');
/*  203 */     skipSpaces();
/*  204 */     COSDictionary obj = new COSDictionary();
/*  205 */     boolean done = false;
/*  206 */     while (!done) {
/*      */       
/*  208 */       skipSpaces();
/*  209 */       char c = (char)this.seqSource.peek();
/*  210 */       if (c == '>') {
/*      */         
/*  212 */         done = true; continue;
/*      */       } 
/*  214 */       if (c == '/') {
/*      */         
/*  216 */         parseCOSDictionaryNameValuePair(obj);
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  221 */       LOG.warn("Invalid dictionary, found: '" + c + "' but expected: '/' at offset " + this.seqSource.getPosition());
/*  222 */       if (readUntilEndOfCOSDictionary())
/*      */       {
/*      */         
/*  225 */         return obj;
/*      */       }
/*      */     } 
/*      */     
/*  229 */     readExpectedChar('>');
/*  230 */     readExpectedChar('>');
/*  231 */     return obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean readUntilEndOfCOSDictionary() throws IOException {
/*  245 */     int c = this.seqSource.read();
/*  246 */     while (c != -1 && c != 47 && c != 62) {
/*      */ 
/*      */ 
/*      */       
/*  250 */       if (c == 101) {
/*      */         
/*  252 */         c = this.seqSource.read();
/*  253 */         if (c == 110) {
/*      */           
/*  255 */           c = this.seqSource.read();
/*  256 */           if (c == 100) {
/*      */             
/*  258 */             c = this.seqSource.read();
/*      */             
/*  260 */             boolean isStream = (c == 115 && this.seqSource.read() == 116 && this.seqSource.read() == 114 && this.seqSource.read() == 101 && this.seqSource.read() == 97 && this.seqSource.read() == 109);
/*  261 */             boolean isObj = (!isStream && c == 111 && this.seqSource.read() == 98 && this.seqSource.read() == 106);
/*  262 */             if (isStream || isObj)
/*      */             {
/*      */               
/*  265 */               return true;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*  270 */       c = this.seqSource.read();
/*      */     } 
/*  272 */     if (c == -1)
/*      */     {
/*  274 */       return true;
/*      */     }
/*  276 */     this.seqSource.unread(c);
/*  277 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void parseCOSDictionaryNameValuePair(COSDictionary obj) throws IOException {
/*  282 */     COSName key = parseCOSName();
/*  283 */     COSBase value = parseCOSDictionaryValue();
/*  284 */     skipSpaces();
/*  285 */     if ((char)this.seqSource.peek() == 'd') {
/*      */ 
/*      */ 
/*      */       
/*  289 */       String potentialDEF = readString();
/*  290 */       if (!potentialDEF.equals("def")) {
/*      */         
/*  292 */         this.seqSource.unread(potentialDEF.getBytes(Charsets.ISO_8859_1));
/*      */       }
/*      */       else {
/*      */         
/*  296 */         skipSpaces();
/*      */       } 
/*      */     } 
/*      */     
/*  300 */     if (value == null) {
/*      */       
/*  302 */       LOG.warn("Bad dictionary declaration at offset " + this.seqSource.getPosition());
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  307 */       value.setDirect(true);
/*  308 */       obj.setItem(key, value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void skipWhiteSpaces() throws IOException {
/*  317 */     int whitespace = this.seqSource.read();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  322 */     while (32 == whitespace)
/*      */     {
/*  324 */       whitespace = this.seqSource.read();
/*      */     }
/*      */     
/*  327 */     if (13 == whitespace) {
/*      */       
/*  329 */       whitespace = this.seqSource.read();
/*  330 */       if (10 != whitespace)
/*      */       {
/*  332 */         this.seqSource.unread(whitespace);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  337 */     else if (10 != whitespace) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  342 */       this.seqSource.unread(whitespace);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int checkForEndOfString(int bracesParameter) throws IOException {
/*  363 */     int braces = bracesParameter;
/*  364 */     byte[] nextThreeBytes = new byte[3];
/*  365 */     int amountRead = this.seqSource.read(nextThreeBytes);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  373 */     if (amountRead == 3 && nextThreeBytes[0] == 13)
/*      */     {
/*  375 */       if ((nextThreeBytes[1] == 10 && nextThreeBytes[2] == 47) || nextThreeBytes[2] == 62 || nextThreeBytes[1] == 47 || nextThreeBytes[1] == 62)
/*      */       {
/*      */         
/*  378 */         braces = 0;
/*      */       }
/*      */     }
/*  381 */     if (amountRead > 0)
/*      */     {
/*  383 */       this.seqSource.unread(nextThreeBytes, 0, amountRead);
/*      */     }
/*  385 */     return braces;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected COSString parseCOSString() throws IOException {
/*  397 */     char nextChar = (char)this.seqSource.read();
/*  398 */     if (nextChar == '<')
/*      */     {
/*  400 */       return parseCOSHexString();
/*      */     }
/*  402 */     if (nextChar != '(')
/*      */     {
/*  404 */       throw new IOException("parseCOSString string should start with '(' or '<' and not '" + nextChar + "' at offset " + this.seqSource
/*  405 */           .getPosition());
/*      */     }
/*      */     
/*  408 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*      */ 
/*      */     
/*  411 */     int braces = 1;
/*  412 */     int c = this.seqSource.read();
/*  413 */     while (braces > 0 && c != -1) {
/*      */       
/*  415 */       char ch = (char)c;
/*  416 */       int nextc = -2;
/*      */       
/*  418 */       if (ch == ')') {
/*      */ 
/*      */         
/*  421 */         braces--;
/*  422 */         braces = checkForEndOfString(braces);
/*  423 */         if (braces != 0)
/*      */         {
/*  425 */           out.write(ch);
/*      */         }
/*      */       }
/*  428 */       else if (ch == '(') {
/*      */         
/*  430 */         braces++;
/*  431 */         out.write(ch);
/*      */       }
/*  433 */       else if (ch == '\\') {
/*      */         StringBuilder octal; char digit;
/*      */         int character;
/*  436 */         char next = (char)this.seqSource.read();
/*  437 */         switch (next) {
/*      */           
/*      */           case 'n':
/*  440 */             out.write(10);
/*      */             break;
/*      */           case 'r':
/*  443 */             out.write(13);
/*      */             break;
/*      */           case 't':
/*  446 */             out.write(9);
/*      */             break;
/*      */           case 'b':
/*  449 */             out.write(8);
/*      */             break;
/*      */           case 'f':
/*  452 */             out.write(12);
/*      */             break;
/*      */           
/*      */           case ')':
/*  456 */             braces = checkForEndOfString(braces);
/*  457 */             if (braces != 0) {
/*      */               
/*  459 */               out.write(next);
/*      */               
/*      */               break;
/*      */             } 
/*  463 */             out.write(92);
/*      */             break;
/*      */           
/*      */           case '(':
/*      */           case '\\':
/*  468 */             out.write(next);
/*      */             break;
/*      */           
/*      */           case '\n':
/*      */           case '\r':
/*  473 */             c = this.seqSource.read();
/*  474 */             while (isEOL(c) && c != -1)
/*      */             {
/*  476 */               c = this.seqSource.read();
/*      */             }
/*  478 */             nextc = c;
/*      */             break;
/*      */           
/*      */           case '0':
/*      */           case '1':
/*      */           case '2':
/*      */           case '3':
/*      */           case '4':
/*      */           case '5':
/*      */           case '6':
/*      */           case '7':
/*  489 */             octal = new StringBuilder();
/*  490 */             octal.append(next);
/*  491 */             c = this.seqSource.read();
/*  492 */             digit = (char)c;
/*  493 */             if (digit >= '0' && digit <= '7') {
/*      */               
/*  495 */               octal.append(digit);
/*  496 */               c = this.seqSource.read();
/*  497 */               digit = (char)c;
/*  498 */               if (digit >= '0' && digit <= '7')
/*      */               {
/*  500 */                 octal.append(digit);
/*      */               }
/*      */               else
/*      */               {
/*  504 */                 nextc = c;
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/*  509 */               nextc = c;
/*      */             } 
/*      */             
/*  512 */             character = 0;
/*      */             
/*      */             try {
/*  515 */               character = Integer.parseInt(octal.toString(), 8);
/*      */             }
/*  517 */             catch (NumberFormatException e) {
/*      */               
/*  519 */               throw new IOException("Error: Expected octal character, actual='" + octal + "'", e);
/*      */             } 
/*  521 */             out.write(character);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/*  528 */             out.write(next);
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } else {
/*  534 */         out.write(ch);
/*      */       } 
/*  536 */       if (nextc != -2) {
/*      */         
/*  538 */         c = nextc;
/*      */         
/*      */         continue;
/*      */       } 
/*  542 */       c = this.seqSource.read();
/*      */     } 
/*      */     
/*  545 */     if (c != -1)
/*      */     {
/*  547 */       this.seqSource.unread(c);
/*      */     }
/*  549 */     return new COSString(out.toByteArray());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private COSString parseCOSHexString() throws IOException {
/*  566 */     StringBuilder sBuf = new StringBuilder();
/*      */     
/*      */     while (true) {
/*  569 */       int c = this.seqSource.read();
/*  570 */       if (isHexDigit((char)c)) {
/*      */         
/*  572 */         sBuf.append((char)c); continue;
/*      */       } 
/*  574 */       if (c == 62) {
/*      */         break;
/*      */       }
/*      */       
/*  578 */       if (c < 0)
/*      */       {
/*  580 */         throw new IOException("Missing closing bracket for hex string. Reached EOS.");
/*      */       }
/*  582 */       if (c == 32 || c == 10 || c == 9 || c == 13 || c == 8 || c == 12) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  592 */       if (sBuf.length() % 2 != 0)
/*      */       {
/*  594 */         sBuf.deleteCharAt(sBuf.length() - 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/*  600 */         c = this.seqSource.read();
/*      */       }
/*  602 */       while (c != 62 && c >= 0);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  607 */       if (c < 0)
/*      */       {
/*  609 */         throw new IOException("Missing closing bracket for hex string. Reached EOS.");
/*      */       }
/*      */ 
/*      */       
/*      */       break;
/*      */     } 
/*      */     
/*  616 */     return COSString.parseHex(sBuf.toString());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected COSArray parseCOSArray() throws IOException {
/*  628 */     long startPosition = this.seqSource.getPosition();
/*  629 */     readExpectedChar('[');
/*  630 */     COSArray po = new COSArray();
/*      */     
/*  632 */     skipSpaces();
/*      */     int i;
/*  634 */     while ((i = this.seqSource.peek()) > 0 && (char)i != ']') {
/*      */       
/*  636 */       COSBase pbo = parseDirObject();
/*  637 */       if (pbo instanceof COSObject)
/*      */       {
/*      */         
/*  640 */         if (po.size() > 0 && po.get(po.size() - 1) instanceof COSInteger) {
/*      */           
/*  642 */           COSInteger genNumber = (COSInteger)po.remove(po.size() - 1);
/*  643 */           if (po.size() > 0 && po.get(po.size() - 1) instanceof COSInteger)
/*      */           {
/*  645 */             COSInteger number = (COSInteger)po.remove(po.size() - 1);
/*  646 */             COSObjectKey key = new COSObjectKey(number.longValue(), genNumber.intValue());
/*  647 */             pbo = getObjectFromPool(key);
/*      */           
/*      */           }
/*      */           else
/*      */           {
/*  652 */             pbo = null;
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  657 */           pbo = null;
/*      */         } 
/*      */       }
/*  660 */       if (pbo != null) {
/*      */         
/*  662 */         po.add(pbo);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  667 */         LOG.warn("Corrupt object reference at offset " + this.seqSource
/*  668 */             .getPosition() + ", start offset: " + startPosition);
/*      */ 
/*      */ 
/*      */         
/*  672 */         String isThisTheEnd = readString();
/*  673 */         this.seqSource.unread(isThisTheEnd.getBytes(Charsets.ISO_8859_1));
/*  674 */         if ("endobj".equals(isThisTheEnd) || "endstream".equals(isThisTheEnd))
/*      */         {
/*  676 */           return po;
/*      */         }
/*      */       } 
/*  679 */       skipSpaces();
/*      */     } 
/*      */     
/*  682 */     this.seqSource.read();
/*  683 */     skipSpaces();
/*  684 */     return po;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isEndOfName(int ch) {
/*  695 */     return (ch == 32 || ch == 13 || ch == 10 || ch == 9 || ch == 62 || ch == 60 || ch == 91 || ch == 47 || ch == 93 || ch == 41 || ch == 40 || ch == 0 || ch == 12);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected COSName parseCOSName() throws IOException {
/*      */     String string;
/*  708 */     readExpectedChar('/');
/*  709 */     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
/*  710 */     int c = this.seqSource.read();
/*  711 */     while (c != -1) {
/*      */       
/*  713 */       int ch = c;
/*  714 */       if (ch == 35) {
/*      */         
/*  716 */         int ch1 = this.seqSource.read();
/*  717 */         int ch2 = this.seqSource.read();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  724 */         if (isHexDigit((char)ch1) && isHexDigit((char)ch2)) {
/*      */           
/*  726 */           String hex = "" + (char)ch1 + (char)ch2;
/*      */           
/*      */           try {
/*  729 */             buffer.write(Integer.parseInt(hex, 16));
/*      */           }
/*  731 */           catch (NumberFormatException e) {
/*      */             
/*  733 */             throw new IOException("Error: expected hex digit, actual='" + hex + "'", e);
/*      */           } 
/*  735 */           c = this.seqSource.read();
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*  740 */         if (ch2 == -1 || ch1 == -1) {
/*      */           
/*  742 */           LOG.error("Premature EOF in BaseParser#parseCOSName");
/*  743 */           c = -1;
/*      */           break;
/*      */         } 
/*  746 */         this.seqSource.unread(ch2);
/*  747 */         c = ch1;
/*  748 */         buffer.write(ch);
/*      */         continue;
/*      */       } 
/*  751 */       if (isEndOfName(ch)) {
/*      */         break;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  757 */       buffer.write(ch);
/*  758 */       c = this.seqSource.read();
/*      */     } 
/*      */     
/*  761 */     if (c != -1)
/*      */     {
/*  763 */       this.seqSource.unread(c);
/*      */     }
/*      */     
/*  766 */     byte[] bytes = buffer.toByteArray();
/*      */     
/*  768 */     if (isValidUTF8(bytes)) {
/*      */       
/*  770 */       string = new String(buffer.toByteArray(), Charsets.UTF_8);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  775 */       string = new String(buffer.toByteArray(), Charsets.WINDOWS_1252);
/*      */     } 
/*  777 */     return COSName.getPDFName(string);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isValidUTF8(byte[] input) {
/*      */     try {
/*  787 */       this.utf8Decoder.decode(ByteBuffer.wrap(input));
/*  788 */       return true;
/*      */     }
/*  790 */     catch (CharacterCodingException e) {
/*      */       
/*  792 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected COSBoolean parseBoolean() throws IOException {
/*  805 */     COSBoolean retval = null;
/*  806 */     char c = (char)this.seqSource.peek();
/*  807 */     if (c == 't') {
/*      */       
/*  809 */       String trueString = new String(this.seqSource.readFully(4), Charsets.ISO_8859_1);
/*  810 */       if (!trueString.equals("true"))
/*      */       {
/*  812 */         throw new IOException("Error parsing boolean: expected='true' actual='" + trueString + "' at offset " + this.seqSource
/*  813 */             .getPosition());
/*      */       }
/*      */ 
/*      */       
/*  817 */       retval = COSBoolean.TRUE;
/*      */     
/*      */     }
/*  820 */     else if (c == 'f') {
/*      */       
/*  822 */       String falseString = new String(this.seqSource.readFully(5), Charsets.ISO_8859_1);
/*  823 */       if (!falseString.equals("false"))
/*      */       {
/*  825 */         throw new IOException("Error parsing boolean: expected='true' actual='" + falseString + "' at offset " + this.seqSource
/*  826 */             .getPosition());
/*      */       }
/*      */ 
/*      */       
/*  830 */       retval = COSBoolean.FALSE;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  835 */       throw new IOException("Error parsing boolean expected='t or f' actual='" + c + "' at offset " + this.seqSource
/*  836 */           .getPosition());
/*      */     } 
/*  838 */     return retval;
/*      */   } protected COSBase parseDirObject() throws IOException {
/*      */     COSString cOSString2;
/*      */     COSArray cOSArray;
/*      */     COSString cOSString1;
/*      */     COSName cOSName;
/*      */     COSNull cOSNull;
/*      */     COSBoolean cOSBoolean;
/*      */     COSObject cOSObject;
/*      */     COSNumber cOSNumber;
/*      */     int leftBracket;
/*      */     String trueString, falseString;
/*  850 */     COSBase retval = null;
/*      */     
/*  852 */     skipSpaces();
/*  853 */     int nextByte = this.seqSource.peek();
/*  854 */     char c = (char)nextByte;
/*  855 */     switch (c)
/*      */     
/*      */     { 
/*      */       
/*      */       case '<':
/*  860 */         leftBracket = this.seqSource.read();
/*      */         
/*  862 */         c = (char)this.seqSource.peek();
/*  863 */         this.seqSource.unread(leftBracket);
/*  864 */         if (c == '<') {
/*      */ 
/*      */           
/*  867 */           COSDictionary cOSDictionary = parseCOSDictionary();
/*  868 */           skipSpaces();
/*      */         }
/*      */         else {
/*      */           
/*  872 */           cOSString2 = parseCOSString();
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  977 */         return (COSBase)cOSString2;case '[': return (COSBase)parseCOSArray();case '(': return (COSBase)parseCOSString();case '/': return (COSBase)parseCOSName();case 'n': readExpectedString("null"); return (COSBase)COSNull.NULL;case 't': trueString = new String(this.seqSource.readFully(4), Charsets.ISO_8859_1); if (trueString.equals("true")) { cOSBoolean = COSBoolean.TRUE; } else { throw new IOException("expected true actual='" + trueString + "' " + this.seqSource + "' at offset " + this.seqSource.getPosition()); }  return (COSBase)cOSBoolean;case 'f': falseString = new String(this.seqSource.readFully(5), Charsets.ISO_8859_1); if (falseString.equals("false")) { cOSBoolean = COSBoolean.FALSE; } else { throw new IOException("expected false actual='" + falseString + "' " + this.seqSource + "' at offset " + this.seqSource.getPosition()); }  return (COSBase)cOSBoolean;case 'R': this.seqSource.read(); return (COSBase)new COSObject(null);case '￿': return null; }  if (Character.isDigit(c) || c == '-' || c == '+' || c == '.') { StringBuilder buf = new StringBuilder(); int ic = this.seqSource.read(); c = (char)ic; while (Character.isDigit(c) || c == '-' || c == '+' || c == '.' || c == 'E' || c == 'e') { buf.append(c); ic = this.seqSource.read(); c = (char)ic; }  if (ic != -1) this.seqSource.unread(ic);  cOSNumber = COSNumber.get(buf.toString()); } else { String badString = readString(); if (badString.isEmpty()) { int peek = this.seqSource.peek(); throw new IOException("Unknown dir object c='" + c + "' cInt=" + c + " peek='" + (char)peek + "' peekInt=" + peek + " at offset " + this.seqSource.getPosition()); }  if ("endobj".equals(badString) || "endstream".equals(badString)) this.seqSource.unread(badString.getBytes(Charsets.ISO_8859_1));  }  return (COSBase)cOSNumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String readString() throws IOException {
/*  989 */     skipSpaces();
/*  990 */     StringBuilder buffer = new StringBuilder();
/*  991 */     int c = this.seqSource.read();
/*  992 */     while (!isEndOfName((char)c) && c != -1) {
/*      */       
/*  994 */       buffer.append((char)c);
/*  995 */       c = this.seqSource.read();
/*      */     } 
/*  997 */     if (c != -1)
/*      */     {
/*  999 */       this.seqSource.unread(c);
/*      */     }
/* 1001 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void readExpectedString(String expectedString) throws IOException {
/* 1013 */     readExpectedString(expectedString.toCharArray(), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void readExpectedString(char[] expectedString, boolean skipSpaces) throws IOException {
/* 1025 */     skipSpaces();
/* 1026 */     for (char c : expectedString) {
/*      */       
/* 1028 */       if (this.seqSource.read() != c)
/*      */       {
/* 1030 */         throw new IOException("Expected string '" + new String(expectedString) + "' but missed at character '" + c + "' at offset " + this.seqSource
/*      */             
/* 1032 */             .getPosition());
/*      */       }
/*      */     } 
/* 1035 */     skipSpaces();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void readExpectedChar(char ec) throws IOException {
/* 1047 */     char c = (char)this.seqSource.read();
/* 1048 */     if (c != ec)
/*      */     {
/* 1050 */       throw new IOException("expected='" + ec + "' actual='" + c + "' at offset " + this.seqSource.getPosition());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String readString(int length) throws IOException {
/* 1065 */     skipSpaces();
/*      */     
/* 1067 */     int c = this.seqSource.read();
/*      */ 
/*      */ 
/*      */     
/* 1071 */     StringBuilder buffer = new StringBuilder(length);
/* 1072 */     while (!isWhitespace(c) && !isClosing(c) && c != -1 && buffer.length() < length && c != 91 && c != 60 && c != 40 && c != 47) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1078 */       buffer.append((char)c);
/* 1079 */       c = this.seqSource.read();
/*      */     } 
/* 1081 */     if (c != -1)
/*      */     {
/* 1083 */       this.seqSource.unread(c);
/*      */     }
/* 1085 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isClosing() throws IOException {
/* 1097 */     return isClosing(this.seqSource.peek());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isClosing(int c) {
/* 1108 */     return (c == 93);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String readLine() throws IOException {
/* 1122 */     if (this.seqSource.isEOF())
/*      */     {
/* 1124 */       throw new IOException("Error: End-of-File, expected line");
/*      */     }
/*      */     
/* 1127 */     StringBuilder buffer = new StringBuilder(11);
/*      */     
/*      */     int c;
/* 1130 */     while ((c = this.seqSource.read()) != -1) {
/*      */ 
/*      */       
/* 1133 */       if (isEOL(c)) {
/*      */         break;
/*      */       }
/*      */       
/* 1137 */       buffer.append((char)c);
/*      */     } 
/*      */     
/* 1140 */     if (isCR(c) && isLF(this.seqSource.peek()))
/*      */     {
/* 1142 */       this.seqSource.read();
/*      */     }
/* 1144 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isEOL() throws IOException {
/* 1156 */     return isEOL(this.seqSource.peek());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isEOL(int c) {
/* 1167 */     return (isLF(c) || isCR(c));
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isLF(int c) {
/* 1172 */     return (10 == c);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isCR(int c) {
/* 1177 */     return (13 == c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isWhitespace() throws IOException {
/* 1189 */     return isWhitespace(this.seqSource.peek());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isWhitespace(int c) {
/* 1200 */     return (c == 0 || c == 9 || c == 12 || c == 10 || c == 13 || c == 32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isSpace() throws IOException {
/* 1213 */     return isSpace(this.seqSource.peek());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isSpace(int c) {
/* 1224 */     return (32 == c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isDigit() throws IOException {
/* 1236 */     return isDigit(this.seqSource.peek());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean isDigit(int c) {
/* 1247 */     return (c >= 48 && c <= 57);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void skipSpaces() throws IOException {
/* 1257 */     int c = this.seqSource.read();
/*      */     
/* 1259 */     while (isWhitespace(c) || c == 37) {
/*      */       
/* 1261 */       if (c == 37) {
/*      */ 
/*      */         
/* 1264 */         c = this.seqSource.read();
/* 1265 */         while (!isEOL(c) && c != -1)
/*      */         {
/* 1267 */           c = this.seqSource.read();
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/* 1272 */       c = this.seqSource.read();
/*      */     } 
/*      */     
/* 1275 */     if (c != -1)
/*      */     {
/* 1277 */       this.seqSource.unread(c);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long readObjectNumber() throws IOException {
/* 1291 */     long retval = readLong();
/* 1292 */     if (retval < 0L || retval >= 10000000000L)
/*      */     {
/* 1294 */       throw new IOException("Object Number '" + retval + "' has more than 10 digits or is negative");
/*      */     }
/* 1296 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int readGenerationNumber() throws IOException {
/* 1307 */     int retval = readInt();
/* 1308 */     if (retval < 0 || retval > 65535L)
/*      */     {
/* 1310 */       throw new IOException("Generation Number '" + retval + "' has more than 5 digits");
/*      */     }
/* 1312 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int readInt() throws IOException {
/* 1324 */     skipSpaces();
/* 1325 */     int retval = 0;
/*      */     
/* 1327 */     StringBuilder intBuffer = readStringNumber();
/*      */ 
/*      */     
/*      */     try {
/* 1331 */       retval = Integer.parseInt(intBuffer.toString());
/*      */     }
/* 1333 */     catch (NumberFormatException e) {
/*      */       
/* 1335 */       this.seqSource.unread(intBuffer.toString().getBytes(Charsets.ISO_8859_1));
/* 1336 */       throw new IOException("Error: Expected an integer type at offset " + this.seqSource
/* 1337 */           .getPosition() + ", instead got '" + intBuffer + "'", e);
/*      */     } 
/*      */     
/* 1340 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long readLong() throws IOException {
/* 1353 */     skipSpaces();
/* 1354 */     long retval = 0L;
/*      */     
/* 1356 */     StringBuilder longBuffer = readStringNumber();
/*      */ 
/*      */     
/*      */     try {
/* 1360 */       retval = Long.parseLong(longBuffer.toString());
/*      */     }
/* 1362 */     catch (NumberFormatException e) {
/*      */       
/* 1364 */       this.seqSource.unread(longBuffer.toString().getBytes(Charsets.ISO_8859_1));
/* 1365 */       throw new IOException("Error: Expected a long type at offset " + this.seqSource
/* 1366 */           .getPosition() + ", instead got '" + longBuffer + "'", e);
/*      */     } 
/* 1368 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final StringBuilder readStringNumber() throws IOException {
/* 1380 */     int lastByte = 0;
/* 1381 */     StringBuilder buffer = new StringBuilder();
/* 1382 */     while ((lastByte = this.seqSource.read()) != 32 && lastByte != 10 && lastByte != 13 && lastByte != 60 && lastByte != 91 && lastByte != 40 && lastByte != 0 && lastByte != -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1391 */       buffer.append((char)lastByte);
/* 1392 */       if (buffer.length() > MAX_LENGTH_LONG)
/*      */       {
/* 1394 */         throw new IOException("Number '" + buffer + "' is getting too long, stop reading at offset " + this.seqSource
/* 1395 */             .getPosition());
/*      */       }
/*      */     } 
/* 1398 */     if (lastByte != -1)
/*      */     {
/* 1400 */       this.seqSource.unread(lastByte);
/*      */     }
/* 1402 */     return buffer;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/BaseParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */