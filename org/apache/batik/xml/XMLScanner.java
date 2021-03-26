/*      */ package org.apache.batik.xml;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.util.Locale;
/*      */ import java.util.MissingResourceException;
/*      */ import org.apache.batik.i18n.Localizable;
/*      */ import org.apache.batik.i18n.LocalizableSupport;
/*      */ import org.apache.batik.util.io.NormalizingReader;
/*      */ import org.apache.batik.util.io.StreamNormalizingReader;
/*      */ import org.apache.batik.util.io.StringNormalizingReader;
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
/*      */ 
/*      */ 
/*      */ public class XMLScanner
/*      */   implements Localizable
/*      */ {
/*      */   public static final int DOCUMENT_START_CONTEXT = 0;
/*      */   public static final int TOP_LEVEL_CONTEXT = 1;
/*      */   public static final int PI_CONTEXT = 2;
/*      */   public static final int XML_DECL_CONTEXT = 3;
/*      */   public static final int DOCTYPE_CONTEXT = 4;
/*      */   public static final int START_TAG_CONTEXT = 5;
/*      */   public static final int CONTENT_CONTEXT = 6;
/*      */   public static final int DTD_DECLARATIONS_CONTEXT = 7;
/*      */   public static final int CDATA_SECTION_CONTEXT = 8;
/*      */   public static final int END_TAG_CONTEXT = 9;
/*      */   public static final int ATTRIBUTE_VALUE_CONTEXT = 10;
/*      */   public static final int ATTLIST_CONTEXT = 11;
/*      */   public static final int ELEMENT_DECLARATION_CONTEXT = 12;
/*      */   public static final int ENTITY_CONTEXT = 13;
/*      */   public static final int NOTATION_CONTEXT = 14;
/*      */   public static final int NOTATION_TYPE_CONTEXT = 15;
/*      */   public static final int ENUMERATION_CONTEXT = 16;
/*      */   public static final int ENTITY_VALUE_CONTEXT = 17;
/*      */   protected static final String BUNDLE_CLASSNAME = "org.apache.batik.xml.resources.Messages";
/*  140 */   protected LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.xml.resources.Messages", XMLScanner.class.getClassLoader());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NormalizingReader reader;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int current;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int type;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  162 */   protected char[] buffer = new char[1024];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int position;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int start;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int end;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int context;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int depth;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean piEndRead;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean inDTD;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected char attrDelimiter;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean cdataEndRead;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLScanner(Reader r) throws XMLException {
/*  214 */     this.context = 0;
/*      */     try {
/*  216 */       this.reader = (NormalizingReader)new StreamNormalizingReader(r);
/*  217 */       this.current = nextChar();
/*  218 */     } catch (IOException e) {
/*  219 */       throw new XMLException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLScanner(InputStream is, String enc) throws XMLException {
/*  229 */     this.context = 0;
/*      */     try {
/*  231 */       this.reader = (NormalizingReader)new StreamNormalizingReader(is, enc);
/*  232 */       this.current = nextChar();
/*  233 */     } catch (IOException e) {
/*  234 */       throw new XMLException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLScanner(String s) throws XMLException {
/*  243 */     this.context = 0;
/*      */     try {
/*  245 */       this.reader = (NormalizingReader)new StringNormalizingReader(s);
/*  246 */       this.current = nextChar();
/*  247 */     } catch (IOException e) {
/*  248 */       throw new XMLException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocale(Locale l) {
/*  256 */     this.localizableSupport.setLocale(l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Locale getLocale() {
/*  263 */     return this.localizableSupport.getLocale();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String formatMessage(String key, Object[] args) throws MissingResourceException {
/*  272 */     return this.localizableSupport.formatMessage(key, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDepth(int i) {
/*  279 */     this.depth = i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDepth() {
/*  286 */     return this.depth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setContext(int c) {
/*  293 */     this.context = c;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getContext() {
/*  300 */     return this.context;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getType() {
/*  307 */     return this.type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLine() {
/*  314 */     return this.reader.getLine();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getColumn() {
/*  321 */     return this.reader.getColumn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char[] getBuffer() {
/*  328 */     return this.buffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStart() {
/*  335 */     return this.start;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEnd() {
/*  342 */     return this.end;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char getStringDelimiter() {
/*  349 */     return this.attrDelimiter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStartOffset() {
/*  356 */     switch (this.type) {
/*      */       case 21:
/*  358 */         return -3;
/*      */       
/*      */       case 7:
/*  361 */         return -2;
/*      */       
/*      */       case 9:
/*      */       case 13:
/*      */       case 16:
/*      */       case 25:
/*      */       case 34:
/*  368 */         return 1;
/*      */       
/*      */       case 5:
/*      */       case 10:
/*      */       case 12:
/*  373 */         return 2;
/*      */       
/*      */       case 4:
/*  376 */         return 4;
/*      */     } 
/*      */     
/*  379 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEndOffset() {
/*  387 */     switch (this.type) {
/*      */       case 12:
/*      */       case 13:
/*      */       case 18:
/*      */       case 25:
/*      */       case 34:
/*  393 */         return -1;
/*      */       
/*      */       case 6:
/*  396 */         return -2;
/*      */       
/*      */       case 4:
/*  399 */         return -3;
/*      */       
/*      */       case 8:
/*  402 */         if (this.cdataEndRead) {
/*  403 */           return -3;
/*      */         }
/*  405 */         return 0;
/*      */     } 
/*      */     
/*  408 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBuffer() {
/*  416 */     if (this.position <= 0) {
/*  417 */       this.position = 0;
/*      */     } else {
/*  419 */       this.buffer[0] = this.buffer[this.position - 1];
/*  420 */       this.position = 1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int next() throws XMLException {
/*  429 */     return next(this.context);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int next(int ctx) throws XMLException {
/*  438 */     this.start = this.position - 1;
/*      */     try {
/*  440 */       switch (ctx) {
/*      */         case 0:
/*  442 */           this.type = nextInDocumentStart();
/*      */           break;
/*      */         
/*      */         case 1:
/*  446 */           this.type = nextInTopLevel();
/*      */           break;
/*      */         
/*      */         case 2:
/*  450 */           this.type = nextInPI();
/*      */           break;
/*      */         
/*      */         case 5:
/*  454 */           this.type = nextInStartTag();
/*      */           break;
/*      */         
/*      */         case 10:
/*  458 */           this.type = nextInAttributeValue();
/*      */           break;
/*      */         
/*      */         case 6:
/*  462 */           this.type = nextInContent();
/*      */           break;
/*      */         
/*      */         case 9:
/*  466 */           this.type = nextInEndTag();
/*      */           break;
/*      */         
/*      */         case 8:
/*  470 */           this.type = nextInCDATASection();
/*      */           break;
/*      */         
/*      */         case 3:
/*  474 */           this.type = nextInXMLDecl();
/*      */           break;
/*      */         
/*      */         case 4:
/*  478 */           this.type = nextInDoctype();
/*      */           break;
/*      */         
/*      */         case 7:
/*  482 */           this.type = nextInDTDDeclarations();
/*      */           break;
/*      */         
/*      */         case 12:
/*  486 */           this.type = nextInElementDeclaration();
/*      */           break;
/*      */         
/*      */         case 11:
/*  490 */           this.type = nextInAttList();
/*      */           break;
/*      */         
/*      */         case 14:
/*  494 */           this.type = nextInNotation();
/*      */           break;
/*      */         
/*      */         case 13:
/*  498 */           this.type = nextInEntity();
/*      */           break;
/*      */         
/*      */         case 17:
/*  502 */           return nextInEntityValue();
/*      */         
/*      */         case 15:
/*  505 */           return nextInNotationType();
/*      */         
/*      */         case 16:
/*  508 */           return nextInEnumeration();
/*      */         
/*      */         default:
/*  511 */           throw new IllegalArgumentException("unexpected ctx:" + ctx);
/*      */       } 
/*  513 */     } catch (IOException e) {
/*  514 */       throw new XMLException(e);
/*      */     } 
/*  516 */     this.end = this.position - ((this.current == -1) ? 0 : 1);
/*  517 */     return this.type;
/*      */   }
/*      */   protected int nextInDocumentStart() throws IOException, XMLException {
/*      */     int c1;
/*      */     int c2;
/*      */     int c3;
/*      */     int c4;
/*  524 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/*  530 */           nextChar();
/*  531 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*  532 */         this.context = (this.depth == 0) ? 1 : 6;
/*  533 */         return 1;
/*      */       
/*      */       case 60:
/*  536 */         switch (nextChar()) {
/*      */           case 63:
/*  538 */             c1 = nextChar();
/*  539 */             if (c1 == -1 || !XMLUtilities.isXMLNameFirstCharacter((char)c1))
/*      */             {
/*  541 */               throw createXMLException("invalid.pi.target");
/*      */             }
/*  543 */             this.context = 2;
/*  544 */             c2 = nextChar();
/*  545 */             if (c2 == -1 || !XMLUtilities.isXMLNameCharacter((char)c2)) {
/*  546 */               return 5;
/*      */             }
/*  548 */             c3 = nextChar();
/*  549 */             if (c3 == -1 || !XMLUtilities.isXMLNameCharacter((char)c3)) {
/*  550 */               return 5;
/*      */             }
/*  552 */             c4 = nextChar();
/*  553 */             if (c4 != -1 && XMLUtilities.isXMLNameCharacter((char)c4)) {
/*      */               do {
/*  555 */                 nextChar();
/*  556 */               } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */               
/*  558 */               return 5;
/*      */             } 
/*  560 */             if (c1 == 120 && c2 == 109 && c3 == 108) {
/*  561 */               this.context = 3;
/*  562 */               return 2;
/*      */             } 
/*  564 */             if ((c1 == 120 || c1 == 88) && (c2 == 109 || c2 == 77) && (c3 == 108 || c3 == 76))
/*      */             {
/*      */               
/*  567 */               throw createXMLException("xml.reserved");
/*      */             }
/*  569 */             return 5;
/*      */           
/*      */           case 33:
/*  572 */             switch (nextChar()) {
/*      */               case 45:
/*  574 */                 return readComment();
/*      */               
/*      */               case 68:
/*  577 */                 this.context = 4;
/*  578 */                 return readIdentifier("OCTYPE", 3, -1);
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/*  583 */             throw createXMLException("invalid.doctype");
/*      */         } 
/*      */ 
/*      */         
/*  587 */         this.context = 5;
/*  588 */         this.depth++;
/*  589 */         return readName(9);
/*      */ 
/*      */       
/*      */       case -1:
/*  593 */         return 0;
/*      */     } 
/*      */     
/*  596 */     if (this.depth == 0) {
/*  597 */       throw createXMLException("invalid.character");
/*      */     }
/*  599 */     return nextInContent();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInTopLevel() throws IOException, XMLException {
/*  609 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/*  615 */           nextChar();
/*  616 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*      */         
/*  618 */         return 1;
/*      */       
/*      */       case 60:
/*  621 */         switch (nextChar()) {
/*      */           case 63:
/*  623 */             this.context = 2;
/*  624 */             return readPIStart();
/*      */           
/*      */           case 33:
/*  627 */             switch (nextChar()) {
/*      */               case 45:
/*  629 */                 return readComment();
/*      */               
/*      */               case 68:
/*  632 */                 this.context = 4;
/*  633 */                 return readIdentifier("OCTYPE", 3, -1);
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/*  638 */             throw createXMLException("invalid.character");
/*      */         } 
/*      */         
/*  641 */         this.context = 5;
/*  642 */         this.depth++;
/*  643 */         return readName(9);
/*      */ 
/*      */       
/*      */       case -1:
/*  647 */         return 0;
/*      */     } 
/*      */     
/*  650 */     throw createXMLException("invalid.character");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInPI() throws IOException, XMLException {
/*  659 */     if (this.piEndRead) {
/*  660 */       this.piEndRead = false;
/*  661 */       this.context = (this.depth == 0) ? 1 : 6;
/*  662 */       return 7;
/*      */     } 
/*      */     
/*  665 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/*  671 */           nextChar();
/*  672 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*      */         
/*  674 */         return 1;
/*      */       case 63:
/*  676 */         if (nextChar() != 62) {
/*  677 */           throw createXMLException("pi.end.expected");
/*      */         }
/*  679 */         nextChar();
/*  680 */         if (this.inDTD) {
/*  681 */           this.context = 7;
/*  682 */         } else if (this.depth == 0) {
/*  683 */           this.context = 1;
/*      */         } else {
/*  685 */           this.context = 6;
/*      */         } 
/*  687 */         return 7;
/*      */     } 
/*      */ 
/*      */     
/*      */     while (true)
/*  692 */     { nextChar();
/*  693 */       if (this.current == -1 || this.current == 63)
/*  694 */       { nextChar();
/*  695 */         if (this.current == -1 || this.current == 62)
/*  696 */           break;  }  }  nextChar();
/*  697 */     this.piEndRead = true;
/*  698 */     return 6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInStartTag() throws IOException, XMLException {
/*  706 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/*  712 */           nextChar();
/*  713 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*  714 */         return 1;
/*      */       
/*      */       case 47:
/*  717 */         if (nextChar() != 62) {
/*  718 */           throw createXMLException("malformed.tag.end");
/*      */         }
/*  720 */         nextChar();
/*  721 */         this.context = (--this.depth == 0) ? 1 : 6;
/*  722 */         return 19;
/*      */       
/*      */       case 62:
/*  725 */         nextChar();
/*  726 */         this.context = 6;
/*  727 */         return 20;
/*      */       
/*      */       case 61:
/*  730 */         nextChar();
/*  731 */         return 15;
/*      */       
/*      */       case 34:
/*  734 */         this.attrDelimiter = '"';
/*  735 */         nextChar();
/*      */         
/*      */         while (true) {
/*  738 */           switch (this.current) {
/*      */             case 34:
/*  740 */               nextChar();
/*  741 */               return 25;
/*      */             
/*      */             case 38:
/*  744 */               this.context = 10;
/*  745 */               return 16;
/*      */             
/*      */             case 60:
/*  748 */               throw createXMLException("invalid.character");
/*      */             
/*      */             case -1:
/*  751 */               throw createXMLException("unexpected.eof");
/*      */           } 
/*  753 */           nextChar();
/*      */         } 
/*      */       
/*      */       case 39:
/*  757 */         this.attrDelimiter = '\'';
/*  758 */         nextChar();
/*      */         
/*      */         while (true) {
/*  761 */           switch (this.current) {
/*      */             case 39:
/*  763 */               nextChar();
/*  764 */               return 25;
/*      */             
/*      */             case 38:
/*  767 */               this.context = 10;
/*  768 */               return 16;
/*      */             
/*      */             case 60:
/*  771 */               throw createXMLException("invalid.character");
/*      */             
/*      */             case -1:
/*  774 */               throw createXMLException("unexpected.eof");
/*      */           } 
/*  776 */           nextChar();
/*      */         } 
/*      */     } 
/*      */     
/*  780 */     return readName(14);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInAttributeValue() throws IOException, XMLException {
/*  789 */     if (this.current == -1) {
/*  790 */       return 0;
/*      */     }
/*      */     
/*  793 */     if (this.current == 38) {
/*  794 */       return readReference();
/*      */     }
/*      */     
/*      */     while (true) {
/*  798 */       switch (this.current) {
/*      */         case -1:
/*      */         case 38:
/*      */         case 60:
/*      */           break;
/*      */         case 34:
/*      */         case 39:
/*  805 */           if (this.current == this.attrDelimiter)
/*      */             break; 
/*      */           break;
/*      */       } 
/*  809 */       nextChar();
/*      */     } 
/*      */     
/*  812 */     switch (this.current) {
/*      */ 
/*      */ 
/*      */       
/*      */       case 60:
/*  817 */         throw createXMLException("invalid.character");
/*      */       
/*      */       case 38:
/*  820 */         return 17;
/*      */       
/*      */       case 34:
/*      */       case 39:
/*  824 */         nextChar();
/*  825 */         if (this.inDTD) {
/*  826 */           this.context = 11; break;
/*      */         } 
/*  828 */         this.context = 5;
/*      */         break;
/*      */     } 
/*  831 */     return 18;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInContent() throws IOException, XMLException {
/*  839 */     switch (this.current) {
/*      */       case -1:
/*  841 */         return 0;
/*      */       
/*      */       case 38:
/*  844 */         return readReference();
/*      */       
/*      */       case 60:
/*  847 */         switch (nextChar()) {
/*      */           case 63:
/*  849 */             this.context = 2;
/*  850 */             return readPIStart();
/*      */           
/*      */           case 33:
/*  853 */             switch (nextChar()) {
/*      */               case 45:
/*  855 */                 return readComment();
/*      */               case 91:
/*  857 */                 this.context = 8;
/*  858 */                 return readIdentifier("CDATA[", 11, -1);
/*      */             } 
/*      */ 
/*      */             
/*  862 */             throw createXMLException("invalid.character");
/*      */ 
/*      */           
/*      */           case 47:
/*  866 */             nextChar();
/*  867 */             this.context = 9;
/*  868 */             return readName(10);
/*      */         } 
/*      */         
/*  871 */         this.depth++;
/*  872 */         this.context = 5;
/*  873 */         return readName(9);
/*      */     } 
/*      */ 
/*      */     
/*      */     while (true) {
/*  878 */       switch (this.current) {
/*      */         case -1:
/*      */         case 38:
/*      */         case 60:
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/*  889 */     return 8;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInEndTag() throws IOException, XMLException {
/*  897 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/*  903 */           nextChar();
/*  904 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*      */         
/*  906 */         return 1;
/*      */       
/*      */       case 62:
/*  909 */         if (--this.depth < 0)
/*  910 */           throw createXMLException("unexpected.end.tag"); 
/*  911 */         if (this.depth == 0) {
/*  912 */           this.context = 1;
/*      */         } else {
/*  914 */           this.context = 6;
/*      */         } 
/*  916 */         nextChar();
/*  917 */         return 20;
/*      */     } 
/*      */     
/*  920 */     throw createXMLException("invalid.character");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInCDATASection() throws IOException, XMLException {
/*  928 */     if (this.cdataEndRead) {
/*  929 */       this.cdataEndRead = false;
/*  930 */       this.context = 6;
/*  931 */       return 21;
/*      */     } 
/*      */     
/*  934 */     while (this.current != -1) {
/*  935 */       while (this.current != 93 && this.current != -1) {
/*  936 */         nextChar();
/*      */       }
/*  938 */       if (this.current != -1) {
/*  939 */         nextChar();
/*  940 */         if (this.current == 93) {
/*  941 */           nextChar();
/*  942 */           if (this.current == 62) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*  948 */     if (this.current == -1) {
/*  949 */       throw createXMLException("unexpected.eof");
/*      */     }
/*  951 */     nextChar();
/*  952 */     this.cdataEndRead = true;
/*  953 */     return 8;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInXMLDecl() throws IOException, XMLException {
/*  960 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/*  966 */           nextChar();
/*  967 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*      */         
/*  969 */         return 1;
/*      */       case 118:
/*  971 */         return readIdentifier("ersion", 22, -1);
/*      */ 
/*      */       
/*      */       case 101:
/*  975 */         return readIdentifier("ncoding", 23, -1);
/*      */ 
/*      */       
/*      */       case 115:
/*  979 */         return readIdentifier("tandalone", 24, -1);
/*      */ 
/*      */       
/*      */       case 61:
/*  983 */         nextChar();
/*  984 */         return 15;
/*      */       
/*      */       case 63:
/*  987 */         nextChar();
/*  988 */         if (this.current != 62) {
/*  989 */           throw createXMLException("pi.end.expected");
/*      */         }
/*  991 */         nextChar();
/*  992 */         this.context = 1;
/*  993 */         return 7;
/*      */       
/*      */       case 34:
/*  996 */         this.attrDelimiter = '"';
/*  997 */         return readString();
/*      */       
/*      */       case 39:
/* 1000 */         this.attrDelimiter = '\'';
/* 1001 */         return readString();
/*      */     } 
/*      */     
/* 1004 */     throw createXMLException("invalid.character");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInDoctype() throws IOException, XMLException {
/* 1012 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/* 1018 */           nextChar();
/* 1019 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*      */         
/* 1021 */         return 1;
/*      */       
/*      */       case 62:
/* 1024 */         nextChar();
/* 1025 */         this.context = 1;
/* 1026 */         return 20;
/*      */       
/*      */       case 83:
/* 1029 */         return readIdentifier("YSTEM", 26, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 80:
/* 1034 */         return readIdentifier("UBLIC", 27, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 34:
/* 1039 */         this.attrDelimiter = '"';
/* 1040 */         return readString();
/*      */       
/*      */       case 39:
/* 1043 */         this.attrDelimiter = '\'';
/* 1044 */         return readString();
/*      */       
/*      */       case 91:
/* 1047 */         nextChar();
/* 1048 */         this.context = 7;
/* 1049 */         this.inDTD = true;
/* 1050 */         return 28;
/*      */     } 
/*      */     
/* 1053 */     return readName(14);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInDTDDeclarations() throws IOException, XMLException {
/* 1061 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/* 1067 */           nextChar();
/* 1068 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*      */         
/* 1070 */         return 1;
/*      */       
/*      */       case 93:
/* 1073 */         nextChar();
/* 1074 */         this.context = 4;
/* 1075 */         this.inDTD = false;
/* 1076 */         return 29;
/*      */       
/*      */       case 37:
/* 1079 */         return readPEReference();
/*      */       
/*      */       case 60:
/* 1082 */         switch (nextChar()) {
/*      */           case 63:
/* 1084 */             this.context = 2;
/* 1085 */             return readPIStart();
/*      */           
/*      */           case 33:
/* 1088 */             switch (nextChar()) {
/*      */               case 45:
/* 1090 */                 return readComment();
/*      */               
/*      */               case 69:
/* 1093 */                 switch (nextChar()) {
/*      */                   case 76:
/* 1095 */                     this.context = 12;
/* 1096 */                     return readIdentifier("EMENT", 30, -1);
/*      */ 
/*      */ 
/*      */                   
/*      */                   case 78:
/* 1101 */                     this.context = 13;
/* 1102 */                     return readIdentifier("TITY", 32, -1);
/*      */                 } 
/*      */ 
/*      */                 
/* 1106 */                 throw createXMLException("invalid.character");
/*      */ 
/*      */               
/*      */               case 65:
/* 1110 */                 this.context = 11;
/* 1111 */                 return readIdentifier("TTLIST", 31, -1);
/*      */ 
/*      */               
/*      */               case 78:
/* 1115 */                 this.context = 14;
/* 1116 */                 return readIdentifier("OTATION", 33, -1);
/*      */             } 
/*      */ 
/*      */             
/* 1120 */             throw createXMLException("invalid.character");
/*      */         } 
/*      */         
/* 1123 */         throw createXMLException("invalid.character");
/*      */     } 
/*      */     
/* 1126 */     throw createXMLException("invalid.character");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int readString() throws IOException, XMLException {
/*      */     do {
/* 1138 */       nextChar();
/* 1139 */     } while (this.current != -1 && this.current != this.attrDelimiter);
/* 1140 */     if (this.current == -1) {
/* 1141 */       throw createXMLException("unexpected.eof");
/*      */     }
/* 1143 */     nextChar();
/* 1144 */     return 25;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int readComment() throws IOException, XMLException {
/* 1151 */     if (nextChar() != 45) {
/* 1152 */       throw createXMLException("malformed.comment");
/*      */     }
/* 1154 */     int c = nextChar();
/* 1155 */     while (c != -1) {
/* 1156 */       while (c != -1 && c != 45) {
/* 1157 */         c = nextChar();
/*      */       }
/* 1159 */       c = nextChar();
/* 1160 */       if (c == 45) {
/*      */         break;
/*      */       }
/*      */     } 
/* 1164 */     if (c == -1) {
/* 1165 */       throw createXMLException("unexpected.eof");
/*      */     }
/* 1167 */     c = nextChar();
/* 1168 */     if (c != 62) {
/* 1169 */       throw createXMLException("malformed.comment");
/*      */     }
/* 1171 */     nextChar();
/* 1172 */     return 4;
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
/*      */   protected int readIdentifier(String s, int type, int ntype) throws IOException, XMLException {
/* 1184 */     int len = s.length();
/* 1185 */     for (int i = 0; i < len; i++) {
/* 1186 */       nextChar();
/* 1187 */       if (this.current != s.charAt(i)) {
/* 1188 */         if (ntype == -1) {
/* 1189 */           throw createXMLException("invalid.character");
/*      */         }
/* 1191 */         while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1193 */           nextChar();
/*      */         }
/* 1195 */         return ntype;
/*      */       } 
/*      */     } 
/*      */     
/* 1199 */     nextChar();
/* 1200 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int readName(int type) throws IOException, XMLException {
/* 1209 */     if (this.current == -1) {
/* 1210 */       throw createXMLException("unexpected.eof");
/*      */     }
/* 1212 */     if (!XMLUtilities.isXMLNameFirstCharacter((char)this.current)) {
/* 1213 */       throw createXMLException("invalid.name");
/*      */     }
/*      */     do {
/* 1216 */       nextChar();
/* 1217 */     } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */     
/* 1219 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int readPIStart() throws IOException, XMLException {
/* 1228 */     int c1 = nextChar();
/* 1229 */     if (c1 == -1) {
/* 1230 */       throw createXMLException("unexpected.eof");
/*      */     }
/* 1232 */     if (!XMLUtilities.isXMLNameFirstCharacter((char)this.current)) {
/* 1233 */       throw createXMLException("malformed.pi.target");
/*      */     }
/* 1235 */     int c2 = nextChar();
/* 1236 */     if (c2 == -1 || !XMLUtilities.isXMLNameCharacter((char)c2)) {
/* 1237 */       return 5;
/*      */     }
/* 1239 */     int c3 = nextChar();
/* 1240 */     if (c3 == -1 || !XMLUtilities.isXMLNameCharacter((char)c3)) {
/* 1241 */       return 5;
/*      */     }
/* 1243 */     int c4 = nextChar();
/* 1244 */     if (c4 != -1 && XMLUtilities.isXMLNameCharacter((char)c4)) {
/*      */       do {
/* 1246 */         nextChar();
/* 1247 */       } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */       
/* 1249 */       return 5;
/*      */     } 
/* 1251 */     if ((c1 == 120 || c1 == 88) && (c2 == 109 || c2 == 77) && (c3 == 108 || c3 == 76))
/*      */     {
/*      */       
/* 1254 */       throw createXMLException("xml.reserved");
/*      */     }
/* 1256 */     return 5;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInElementDeclaration() throws IOException, XMLException {
/*      */     int t;
/* 1263 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/* 1269 */           nextChar();
/* 1270 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/* 1271 */         return 1;
/*      */       
/*      */       case 62:
/* 1274 */         nextChar();
/* 1275 */         this.context = 7;
/* 1276 */         return 20;
/*      */       
/*      */       case 37:
/* 1279 */         nextChar();
/* 1280 */         t = readName(34);
/* 1281 */         if (this.current != 59) {
/* 1282 */           throw createXMLException("malformed.parameter.entity");
/*      */         }
/* 1284 */         nextChar();
/* 1285 */         return t;
/*      */       
/*      */       case 69:
/* 1288 */         return readIdentifier("MPTY", 35, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 65:
/* 1293 */         return readIdentifier("NY", 36, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 63:
/* 1298 */         nextChar();
/* 1299 */         return 37;
/*      */       
/*      */       case 43:
/* 1302 */         nextChar();
/* 1303 */         return 38;
/*      */       
/*      */       case 42:
/* 1306 */         nextChar();
/* 1307 */         return 39;
/*      */       
/*      */       case 40:
/* 1310 */         nextChar();
/* 1311 */         return 40;
/*      */       
/*      */       case 41:
/* 1314 */         nextChar();
/* 1315 */         return 41;
/*      */       
/*      */       case 124:
/* 1318 */         nextChar();
/* 1319 */         return 42;
/*      */       
/*      */       case 44:
/* 1322 */         nextChar();
/* 1323 */         return 43;
/*      */       
/*      */       case 35:
/* 1326 */         return readIdentifier("PCDATA", 44, -1);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1331 */     return readName(14);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInAttList() throws IOException, XMLException {
/*      */     int t;
/* 1339 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/* 1345 */           nextChar();
/* 1346 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/* 1347 */         return 1;
/*      */       
/*      */       case 62:
/* 1350 */         nextChar();
/* 1351 */         this.context = 7;
/* 1352 */         return this.type = 20;
/*      */       
/*      */       case 37:
/* 1355 */         t = readName(34);
/* 1356 */         if (this.current != 59) {
/* 1357 */           throw createXMLException("malformed.parameter.entity");
/*      */         }
/* 1359 */         nextChar();
/* 1360 */         return t;
/*      */       
/*      */       case 67:
/* 1363 */         return readIdentifier("DATA", 45, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 73:
/* 1368 */         nextChar();
/* 1369 */         if (this.current != 68) {
/*      */           do {
/* 1371 */             nextChar();
/* 1372 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1374 */           return 14;
/*      */         } 
/* 1376 */         nextChar();
/* 1377 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1379 */           return 46;
/*      */         }
/* 1381 */         if (this.current != 82) {
/*      */           do {
/* 1383 */             nextChar();
/* 1384 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1386 */           return 14;
/*      */         } 
/* 1388 */         nextChar();
/* 1389 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1391 */           return 14;
/*      */         }
/* 1393 */         if (this.current != 69) {
/*      */           do {
/* 1395 */             nextChar();
/* 1396 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1398 */           return 14;
/*      */         } 
/* 1400 */         nextChar();
/* 1401 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1403 */           return 14;
/*      */         }
/* 1405 */         if (this.current != 70) {
/*      */           do {
/* 1407 */             nextChar();
/* 1408 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1410 */           return 14;
/*      */         } 
/* 1412 */         nextChar();
/* 1413 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1415 */           return 47;
/*      */         }
/* 1417 */         if (this.current != 83) {
/*      */           do {
/* 1419 */             nextChar();
/* 1420 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1422 */           return 14;
/*      */         } 
/* 1424 */         nextChar();
/* 1425 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1427 */           return 48;
/*      */         }
/*      */         do {
/* 1430 */           nextChar();
/* 1431 */         } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */         
/* 1433 */         return this.type = 14;
/*      */       
/*      */       case 78:
/* 1436 */         switch (nextChar()) {
/*      */           default:
/*      */             do {
/* 1439 */               nextChar();
/* 1440 */             } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */             
/* 1442 */             return 14;
/*      */           
/*      */           case 79:
/* 1445 */             this.context = 15;
/* 1446 */             return readIdentifier("TATION", 57, 14);
/*      */           
/*      */           case 77:
/*      */             break;
/*      */         } 
/* 1451 */         nextChar();
/* 1452 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1454 */           return 14;
/*      */         }
/* 1456 */         if (this.current != 84) {
/*      */           do {
/* 1458 */             nextChar();
/* 1459 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1461 */           return 14;
/*      */         } 
/* 1463 */         nextChar();
/* 1464 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1466 */           return 14;
/*      */         }
/* 1468 */         if (this.current != 79) {
/*      */           do {
/* 1470 */             nextChar();
/* 1471 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1473 */           return 14;
/*      */         } 
/* 1475 */         nextChar();
/* 1476 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1478 */           return 14;
/*      */         }
/* 1480 */         if (this.current != 75) {
/*      */           do {
/* 1482 */             nextChar();
/* 1483 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1485 */           return 14;
/*      */         } 
/* 1487 */         nextChar();
/* 1488 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1490 */           return 14;
/*      */         }
/* 1492 */         if (this.current != 69) {
/*      */           do {
/* 1494 */             nextChar();
/* 1495 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1497 */           return 14;
/*      */         } 
/* 1499 */         nextChar();
/* 1500 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1502 */           return 14;
/*      */         }
/* 1504 */         if (this.current != 78) {
/*      */           do {
/* 1506 */             nextChar();
/* 1507 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1509 */           return 14;
/*      */         } 
/* 1511 */         nextChar();
/* 1512 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1514 */           return 49;
/*      */         }
/* 1516 */         if (this.current != 83) {
/*      */           do {
/* 1518 */             nextChar();
/* 1519 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1521 */           return 14;
/*      */         } 
/* 1523 */         nextChar();
/* 1524 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1526 */           return 50;
/*      */         }
/*      */         do {
/* 1529 */           nextChar();
/* 1530 */         } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */         
/* 1532 */         return 14;
/*      */ 
/*      */       
/*      */       case 69:
/* 1536 */         nextChar();
/* 1537 */         if (this.current != 78) {
/*      */           do {
/* 1539 */             nextChar();
/* 1540 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1542 */           return 14;
/*      */         } 
/* 1544 */         nextChar();
/* 1545 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1547 */           return 14;
/*      */         }
/* 1549 */         if (this.current != 84) {
/*      */           do {
/* 1551 */             nextChar();
/* 1552 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1554 */           return 14;
/*      */         } 
/* 1556 */         nextChar();
/* 1557 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1559 */           return 14;
/*      */         }
/* 1561 */         if (this.current != 73) {
/*      */           do {
/* 1563 */             nextChar();
/* 1564 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1566 */           return 14;
/*      */         } 
/* 1568 */         nextChar();
/* 1569 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1571 */           return 14;
/*      */         }
/* 1573 */         if (this.current != 84) {
/*      */           do {
/* 1575 */             nextChar();
/* 1576 */           } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */           
/* 1578 */           return this.type = 14;
/*      */         } 
/* 1580 */         nextChar();
/* 1581 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1583 */           return 14;
/*      */         }
/* 1585 */         switch (this.current) {
/*      */           case 89:
/* 1587 */             nextChar();
/* 1588 */             if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */             {
/* 1590 */               return 51;
/*      */             }
/*      */             do {
/* 1593 */               nextChar();
/* 1594 */             } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */             
/* 1596 */             return 14;
/*      */           case 73:
/* 1598 */             nextChar();
/* 1599 */             if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */             {
/* 1601 */               return 14;
/*      */             }
/* 1603 */             if (this.current != 69) {
/*      */               do {
/* 1605 */                 nextChar();
/* 1606 */               } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */               
/* 1608 */               return 14;
/*      */             } 
/* 1610 */             nextChar();
/* 1611 */             if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */             {
/* 1613 */               return 14;
/*      */             }
/* 1615 */             if (this.current != 83) {
/*      */               do {
/* 1617 */                 nextChar();
/* 1618 */               } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */               
/* 1620 */               return 14;
/*      */             } 
/* 1622 */             return 52;
/*      */         } 
/*      */         
/* 1625 */         if (this.current == -1 || !XMLUtilities.isXMLNameCharacter((char)this.current))
/*      */         {
/* 1627 */           return 14;
/*      */         }
/*      */         do {
/* 1630 */           nextChar();
/* 1631 */         } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */         
/* 1633 */         return 14;
/*      */ 
/*      */       
/*      */       case 34:
/* 1637 */         this.attrDelimiter = '"';
/* 1638 */         nextChar();
/* 1639 */         if (this.current == -1) {
/* 1640 */           throw createXMLException("unexpected.eof");
/*      */         }
/* 1642 */         if (this.current != 34 && this.current != 38) {
/*      */           do {
/* 1644 */             nextChar();
/* 1645 */           } while (this.current != -1 && this.current != 34 && this.current != 38);
/*      */         }
/* 1647 */         switch (this.current) {
/*      */           case 38:
/* 1649 */             this.context = 10;
/* 1650 */             return 16;
/*      */           
/*      */           case 34:
/* 1653 */             nextChar();
/* 1654 */             return 25;
/*      */         } 
/*      */         
/* 1657 */         throw createXMLException("invalid.character");
/*      */ 
/*      */       
/*      */       case 39:
/* 1661 */         this.attrDelimiter = '\'';
/* 1662 */         nextChar();
/* 1663 */         if (this.current == -1) {
/* 1664 */           throw createXMLException("unexpected.eof");
/*      */         }
/* 1666 */         if (this.current != 39 && this.current != 38) {
/*      */           do {
/* 1668 */             nextChar();
/* 1669 */           } while (this.current != -1 && this.current != 39 && this.current != 38);
/*      */         }
/* 1671 */         switch (this.current) {
/*      */           case 38:
/* 1673 */             this.context = 10;
/* 1674 */             return 16;
/*      */           
/*      */           case 39:
/* 1677 */             nextChar();
/* 1678 */             return 25;
/*      */         } 
/*      */         
/* 1681 */         throw createXMLException("invalid.character");
/*      */ 
/*      */       
/*      */       case 35:
/* 1685 */         switch (nextChar()) {
/*      */           case 82:
/* 1687 */             return readIdentifier("EQUIRED", 53, -1);
/*      */ 
/*      */ 
/*      */           
/*      */           case 73:
/* 1692 */             return readIdentifier("MPLIED", 54, -1);
/*      */ 
/*      */           
/*      */           case 70:
/* 1696 */             return readIdentifier("IXED", 55, -1);
/*      */         } 
/*      */ 
/*      */         
/* 1700 */         throw createXMLException("invalid.character");
/*      */ 
/*      */       
/*      */       case 40:
/* 1704 */         nextChar();
/* 1705 */         this.context = 16;
/* 1706 */         return 40;
/*      */     } 
/*      */     
/* 1709 */     return readName(14);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInNotation() throws IOException, XMLException {
/*      */     int t;
/* 1717 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/* 1723 */           nextChar();
/* 1724 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*      */         
/* 1726 */         return 1;
/*      */       
/*      */       case 62:
/* 1729 */         nextChar();
/* 1730 */         this.context = 7;
/* 1731 */         return 20;
/*      */       
/*      */       case 37:
/* 1734 */         t = readName(34);
/* 1735 */         if (this.current != 59) {
/* 1736 */           throw createXMLException("malformed.parameter.entity");
/*      */         }
/* 1738 */         nextChar();
/* 1739 */         return t;
/*      */       case 83:
/* 1741 */         return readIdentifier("YSTEM", 26, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 80:
/* 1746 */         return readIdentifier("UBLIC", 27, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 34:
/* 1751 */         this.attrDelimiter = '"';
/* 1752 */         return readString();
/*      */       
/*      */       case 39:
/* 1755 */         this.attrDelimiter = '\'';
/* 1756 */         return readString();
/*      */     } 
/*      */     
/* 1759 */     return readName(14);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInEntity() throws IOException, XMLException {
/* 1767 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/* 1773 */           nextChar();
/* 1774 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*      */         
/* 1776 */         return 1;
/*      */       
/*      */       case 62:
/* 1779 */         nextChar();
/* 1780 */         this.context = 7;
/* 1781 */         return 20;
/*      */       
/*      */       case 37:
/* 1784 */         nextChar();
/* 1785 */         return 58;
/*      */       
/*      */       case 83:
/* 1788 */         return readIdentifier("YSTEM", 26, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 80:
/* 1793 */         return readIdentifier("UBLIC", 27, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 78:
/* 1798 */         return readIdentifier("DATA", 59, 14);
/*      */ 
/*      */ 
/*      */       
/*      */       case 34:
/* 1803 */         this.attrDelimiter = '"';
/* 1804 */         nextChar();
/* 1805 */         if (this.current == -1) {
/* 1806 */           throw createXMLException("unexpected.eof");
/*      */         }
/*      */         
/* 1809 */         if (this.current != 34 && this.current != 38 && this.current != 37) {
/*      */           do {
/* 1811 */             nextChar();
/*      */ 
/*      */           
/*      */           }
/* 1815 */           while (this.current != -1 && this.current != 34 && this.current != 38 && this.current != 37);
/*      */         }
/* 1817 */         switch (this.current)
/*      */         { default:
/* 1819 */             throw createXMLException("invalid.character");
/*      */           
/*      */           case 37:
/*      */           case 38:
/* 1823 */             this.context = 17;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1830 */             return 16;
/*      */           case 34: break; }  nextChar(); return 25;
/*      */       case 39:
/* 1833 */         this.attrDelimiter = '\'';
/* 1834 */         nextChar();
/* 1835 */         if (this.current == -1) {
/* 1836 */           throw createXMLException("unexpected.eof");
/*      */         }
/*      */         
/* 1839 */         if (this.current != 39 && this.current != 38 && this.current != 37) {
/*      */           do {
/* 1841 */             nextChar();
/*      */ 
/*      */           
/*      */           }
/* 1845 */           while (this.current != -1 && this.current != 39 && this.current != 38 && this.current != 37);
/*      */         }
/* 1847 */         switch (this.current)
/*      */         { default:
/* 1849 */             throw createXMLException("invalid.character");
/*      */           
/*      */           case 37:
/*      */           case 38:
/* 1853 */             this.context = 17;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1860 */             return 16;
/*      */           case 39:
/*      */             break; }  nextChar(); return 25;
/* 1863 */     }  return readName(14);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInEntityValue() throws IOException, XMLException {
/*      */     int t;
/* 1871 */     switch (this.current) {
/*      */       case 38:
/* 1873 */         return readReference();
/*      */       
/*      */       case 37:
/* 1876 */         t = nextChar();
/* 1877 */         readName(34);
/* 1878 */         if (this.current != 59) {
/* 1879 */           throw createXMLException("invalid.parameter.entity");
/*      */         }
/* 1881 */         nextChar();
/* 1882 */         return t;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1888 */     while (this.current != -1 && this.current != this.attrDelimiter && this.current != 38 && this.current != 37) {
/* 1889 */       nextChar();
/*      */     }
/* 1891 */     switch (this.current) {
/*      */       case -1:
/* 1893 */         throw createXMLException("unexpected.eof");
/*      */       
/*      */       case 34:
/*      */       case 39:
/* 1897 */         nextChar();
/* 1898 */         this.context = 13;
/* 1899 */         return 25;
/*      */     } 
/* 1901 */     return 16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInNotationType() throws IOException, XMLException {
/* 1909 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/* 1915 */           nextChar();
/* 1916 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/* 1917 */         return 1;
/*      */       
/*      */       case 124:
/* 1920 */         nextChar();
/* 1921 */         return 42;
/*      */       
/*      */       case 40:
/* 1924 */         nextChar();
/* 1925 */         return 40;
/*      */       
/*      */       case 41:
/* 1928 */         nextChar();
/* 1929 */         this.context = 11;
/* 1930 */         return 41;
/*      */     } 
/*      */     
/* 1933 */     return readName(14);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextInEnumeration() throws IOException, XMLException {
/* 1941 */     switch (this.current) {
/*      */       case 9:
/*      */       case 10:
/*      */       case 13:
/*      */       case 32:
/*      */         do {
/* 1947 */           nextChar();
/* 1948 */         } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/* 1949 */         return 1;
/*      */       
/*      */       case 124:
/* 1952 */         nextChar();
/* 1953 */         return 42;
/*      */       
/*      */       case 41:
/* 1956 */         nextChar();
/* 1957 */         this.context = 11;
/* 1958 */         return 41;
/*      */     } 
/*      */     
/* 1961 */     return readNmtoken();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int readReference() throws IOException, XMLException {
/* 1972 */     nextChar();
/* 1973 */     if (this.current == 35) {
/* 1974 */       nextChar();
/* 1975 */       int i = 0;
/* 1976 */       switch (this.current) {
/*      */         case 120:
/*      */           do {
/* 1979 */             i++;
/* 1980 */             nextChar();
/*      */           }
/* 1982 */           while ((this.current >= 48 && this.current <= 57) || (this.current >= 97 && this.current <= 102) || (this.current >= 65 && this.current <= 70));
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*      */           do {
/* 1988 */             i++;
/* 1989 */             nextChar();
/* 1990 */           } while (this.current >= 48 && this.current <= 57);
/*      */           break;
/*      */         
/*      */         case -1:
/* 1994 */           throw createXMLException("unexpected.eof");
/*      */       } 
/* 1996 */       if (i == 1 || this.current != 59) {
/* 1997 */         throw createXMLException("character.reference");
/*      */       }
/* 1999 */       nextChar();
/* 2000 */       return 12;
/*      */     } 
/* 2002 */     int t = readName(13);
/* 2003 */     if (this.current != 59) {
/* 2004 */       throw createXMLException("character.reference");
/*      */     }
/* 2006 */     nextChar();
/* 2007 */     return t;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int readPEReference() throws IOException, XMLException {
/* 2016 */     nextChar();
/* 2017 */     if (this.current == -1) {
/* 2018 */       throw createXMLException("unexpected.eof");
/*      */     }
/* 2020 */     if (!XMLUtilities.isXMLNameFirstCharacter((char)this.current)) {
/* 2021 */       throw createXMLException("invalid.parameter.entity");
/*      */     }
/*      */     do {
/* 2024 */       nextChar();
/* 2025 */     } while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current));
/*      */     
/* 2027 */     if (this.current != 59) {
/* 2028 */       throw createXMLException("invalid.parameter.entity");
/*      */     }
/* 2030 */     nextChar();
/* 2031 */     return 34;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int readNmtoken() throws IOException, XMLException {
/* 2039 */     if (this.current == -1) {
/* 2040 */       throw createXMLException("unexpected.eof");
/*      */     }
/* 2042 */     while (XMLUtilities.isXMLNameCharacter((char)this.current)) {
/* 2043 */       nextChar();
/*      */     }
/* 2045 */     return 56;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextChar() throws IOException {
/* 2053 */     this.current = this.reader.read();
/*      */     
/* 2055 */     if (this.current == -1) {
/* 2056 */       return this.current;
/*      */     }
/*      */     
/* 2059 */     if (this.position == this.buffer.length) {
/* 2060 */       char[] t = new char[1 + this.position + this.position / 2];
/* 2061 */       System.arraycopy(this.buffer, 0, t, 0, this.position);
/* 2062 */       this.buffer = t;
/*      */     } 
/*      */     
/* 2065 */     this.buffer[this.position++] = (char)this.current; return (char)this.current;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected XMLException createXMLException(String message) {
/*      */     String str;
/*      */     try {
/* 2074 */       str = formatMessage(message, new Object[] { Integer.valueOf(this.reader.getLine()), Integer.valueOf(this.reader.getColumn()) });
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2079 */     catch (MissingResourceException e) {
/* 2080 */       str = message;
/*      */     } 
/* 2082 */     return new XMLException(str);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/xml/XMLScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */