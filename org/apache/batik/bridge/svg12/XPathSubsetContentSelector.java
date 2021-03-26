/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.batik.anim.dom.XBLOMContentElement;
/*     */ import org.apache.batik.parser.AbstractScanner;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.xml.XMLUtilities;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XPathSubsetContentSelector
/*     */   extends AbstractContentSelector
/*     */ {
/*     */   protected static final int SELECTOR_INVALID = -1;
/*     */   protected static final int SELECTOR_ANY = 0;
/*     */   protected static final int SELECTOR_QNAME = 1;
/*     */   protected static final int SELECTOR_ID = 2;
/*     */   protected int selectorType;
/*     */   protected String prefix;
/*     */   protected String localName;
/*     */   protected int index;
/*     */   protected SelectedNodes selectedContent;
/*     */   
/*     */   public XPathSubsetContentSelector(ContentManager cm, XBLOMContentElement content, Element bound, String selector) {
/*  78 */     super(cm, content, bound);
/*  79 */     parseSelector(selector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseSelector(String selector) {
/*  86 */     this.selectorType = -1;
/*  87 */     Scanner scanner = new Scanner(selector);
/*  88 */     int token = scanner.next();
/*  89 */     if (token == 1) {
/*  90 */       String name1 = scanner.getStringValue();
/*  91 */       token = scanner.next();
/*  92 */       if (token == 0) {
/*  93 */         this.selectorType = 1;
/*  94 */         this.prefix = null;
/*  95 */         this.localName = name1;
/*  96 */         this.index = 0; return;
/*     */       } 
/*  98 */       if (token == 2) {
/*  99 */         token = scanner.next();
/* 100 */         if (token == 1) {
/* 101 */           String name2 = scanner.getStringValue();
/* 102 */           token = scanner.next();
/* 103 */           if (token == 0) {
/* 104 */             this.selectorType = 1;
/* 105 */             this.prefix = name1;
/* 106 */             this.localName = name2;
/* 107 */             this.index = 0; return;
/*     */           } 
/* 109 */           if (token == 3) {
/* 110 */             token = scanner.next();
/* 111 */             if (token == 8) {
/* 112 */               int number = Integer.parseInt(scanner.getStringValue());
/* 113 */               token = scanner.next();
/* 114 */               if (token == 4) {
/* 115 */                 token = scanner.next();
/* 116 */                 if (token == 0) {
/* 117 */                   this.selectorType = 1;
/* 118 */                   this.prefix = name1;
/* 119 */                   this.localName = name2;
/* 120 */                   this.index = number;
/*     */                   return;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/* 126 */         } else if (token == 3) {
/* 127 */           token = scanner.next();
/* 128 */           if (token == 8) {
/* 129 */             int number = Integer.parseInt(scanner.getStringValue());
/* 130 */             token = scanner.next();
/* 131 */             if (token == 4) {
/* 132 */               token = scanner.next();
/* 133 */               if (token == 0) {
/* 134 */                 this.selectorType = 1;
/* 135 */                 this.prefix = null;
/* 136 */                 this.localName = name1;
/* 137 */                 this.index = number;
/*     */                 return;
/*     */               } 
/*     */             } 
/*     */           } 
/* 142 */         } else if (token == 5 && 
/* 143 */           name1.equals("id")) {
/* 144 */           token = scanner.next();
/* 145 */           if (token == 7) {
/* 146 */             String id = scanner.getStringValue();
/* 147 */             token = scanner.next();
/* 148 */             if (token == 6) {
/* 149 */               token = scanner.next();
/* 150 */               if (token == 0) {
/* 151 */                 this.selectorType = 2;
/* 152 */                 this.localName = id;
/*     */                 
/*     */                 return;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 160 */     } else if (token == 9) {
/* 161 */       token = scanner.next();
/* 162 */       if (token == 0) {
/* 163 */         this.selectorType = 0; return;
/*     */       } 
/* 165 */       if (token == 3) {
/* 166 */         token = scanner.next();
/* 167 */         if (token == 8) {
/* 168 */           int number = Integer.parseInt(scanner.getStringValue());
/* 169 */           token = scanner.next();
/* 170 */           if (token == 4) {
/* 171 */             token = scanner.next();
/* 172 */             if (token == 0) {
/* 173 */               this.selectorType = 0;
/* 174 */               this.index = number;
/*     */               return;
/*     */             } 
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
/*     */   public NodeList getSelectedContent() {
/* 188 */     if (this.selectedContent == null) {
/* 189 */       this.selectedContent = new SelectedNodes();
/*     */     }
/* 191 */     return this.selectedContent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean update() {
/* 202 */     if (this.selectedContent == null) {
/* 203 */       this.selectedContent = new SelectedNodes();
/* 204 */       return true;
/*     */     } 
/* 206 */     return this.selectedContent.update();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class SelectedNodes
/*     */     implements NodeList
/*     */   {
/* 218 */     protected ArrayList nodes = new ArrayList(10);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SelectedNodes() {
/* 224 */       update();
/*     */     }
/*     */     
/*     */     protected boolean update() {
/* 228 */       ArrayList<E> oldNodes = (ArrayList)this.nodes.clone();
/* 229 */       this.nodes.clear();
/* 230 */       int nth = 0;
/* 231 */       for (Node n = XPathSubsetContentSelector.this.boundElement.getFirstChild(); n != null; n = n.getNextSibling()) {
/* 232 */         if (n.getNodeType() == 1) {
/*     */ 
/*     */           
/* 235 */           Element e = (Element)n;
/* 236 */           boolean matched = (XPathSubsetContentSelector.this.selectorType == 0);
/* 237 */           switch (XPathSubsetContentSelector.this.selectorType) {
/*     */             case 2:
/* 239 */               matched = e.getAttributeNS((String)null, "id").equals(XPathSubsetContentSelector.this.localName);
/*     */               break;
/*     */             case 1:
/* 242 */               if (XPathSubsetContentSelector.this.prefix == null) {
/* 243 */                 matched = (e.getNamespaceURI() == null);
/*     */               } else {
/* 245 */                 String ns = XPathSubsetContentSelector.this.contentElement.lookupNamespaceURI(XPathSubsetContentSelector.this.prefix);
/* 246 */                 if (ns != null)
/*     */                 {
/*     */                   
/* 249 */                   matched = e.getNamespaceURI().equals(ns);
/*     */                 }
/*     */               } 
/* 252 */               matched = (matched && XPathSubsetContentSelector.this.localName.equals(e.getLocalName()));
/*     */               break;
/*     */           } 
/* 255 */           if (XPathSubsetContentSelector.this.selectorType == 0 || XPathSubsetContentSelector.this.selectorType == 1)
/*     */           {
/* 257 */             matched = (matched && (XPathSubsetContentSelector.this.index == 0 || ++nth == XPathSubsetContentSelector.this.index));
/*     */           }
/* 259 */           if (matched && !XPathSubsetContentSelector.this.isSelected(n))
/* 260 */             this.nodes.add(e); 
/*     */         } 
/*     */       } 
/* 263 */       int nodesSize = this.nodes.size();
/* 264 */       if (oldNodes.size() != nodesSize) {
/* 265 */         return true;
/*     */       }
/* 267 */       for (int i = 0; i < nodesSize; i++) {
/* 268 */         if (oldNodes.get(i) != this.nodes.get(i)) {
/* 269 */           return true;
/*     */         }
/*     */       } 
/* 272 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node item(int index) {
/* 279 */       if (index < 0 || index >= this.nodes.size()) {
/* 280 */         return null;
/*     */       }
/* 282 */       return this.nodes.get(index);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getLength() {
/* 289 */       return this.nodes.size();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class Scanner
/*     */     extends AbstractScanner
/*     */   {
/*     */     public static final int EOF = 0;
/*     */     
/*     */     public static final int NAME = 1;
/*     */     
/*     */     public static final int COLON = 2;
/*     */     
/*     */     public static final int LEFT_SQUARE_BRACKET = 3;
/*     */     
/*     */     public static final int RIGHT_SQUARE_BRACKET = 4;
/*     */     public static final int LEFT_PARENTHESIS = 5;
/*     */     public static final int RIGHT_PARENTHESIS = 6;
/*     */     public static final int STRING = 7;
/*     */     public static final int NUMBER = 8;
/*     */     public static final int ASTERISK = 9;
/*     */     
/*     */     public Scanner(String s) {
/* 313 */       super(s);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int endGap() {
/* 320 */       return (this.current == -1) ? 0 : 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void nextToken() throws ParseException {
/*     */       try {
/* 368 */         switch (this.current) {
/*     */           case -1:
/* 370 */             this.type = 0;
/*     */             return;
/*     */           case 58:
/* 373 */             nextChar();
/* 374 */             this.type = 2;
/*     */             return;
/*     */           case 91:
/* 377 */             nextChar();
/* 378 */             this.type = 3;
/*     */             return;
/*     */           case 93:
/* 381 */             nextChar();
/* 382 */             this.type = 4;
/*     */             return;
/*     */           case 40:
/* 385 */             nextChar();
/* 386 */             this.type = 5;
/*     */             return;
/*     */           case 41:
/* 389 */             nextChar();
/* 390 */             this.type = 6;
/*     */             return;
/*     */           case 42:
/* 393 */             nextChar();
/* 394 */             this.type = 9;
/*     */             return;
/*     */           case 9:
/*     */           case 10:
/*     */           case 12:
/*     */           case 13:
/*     */           case 32:
/*     */             while (true) {
/* 402 */               nextChar();
/* 403 */               if (!XMLUtilities.isXMLSpace((char)this.current))
/* 404 */               { nextToken(); return; } 
/*     */             } 
/*     */           case 39:
/* 407 */             this.type = string1();
/*     */             return;
/*     */           case 34:
/* 410 */             this.type = string2(); return;
/*     */           case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55:
/*     */           case 56:
/*     */           case 57:
/* 414 */             this.type = number();
/*     */             return;
/*     */         } 
/* 417 */         if (XMLUtilities.isXMLNameFirstCharacter((char)this.current)) {
/*     */           do {
/* 419 */             nextChar();
/*     */           
/*     */           }
/* 422 */           while (this.current != -1 && this.current != 58 && XMLUtilities.isXMLNameCharacter((char)this.current));
/* 423 */           this.type = 1;
/*     */           return;
/*     */         } 
/* 426 */         nextChar();
/* 427 */         throw new ParseException("identifier.character", this.reader.getLine(), this.reader.getColumn());
/*     */ 
/*     */       
/*     */       }
/* 431 */       catch (IOException e) {
/* 432 */         throw new ParseException(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int string1() throws IOException {
/* 440 */       this.start = this.position;
/*     */       while (true) {
/* 442 */         switch (nextChar()) {
/*     */           case -1:
/* 444 */             throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*     */           
/*     */           case 39:
/*     */             break;
/*     */         } 
/*     */       
/*     */       } 
/* 451 */       nextChar();
/* 452 */       return 7;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int string2() throws IOException {
/* 459 */       this.start = this.position;
/*     */       while (true) {
/* 461 */         switch (nextChar()) {
/*     */           case -1:
/* 463 */             throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*     */           
/*     */           case 34:
/*     */             break;
/*     */         } 
/*     */       
/*     */       } 
/* 470 */       nextChar();
/* 471 */       return 7;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int number() throws IOException {
/*     */       while (true)
/* 479 */       { switch (nextChar())
/*     */         { case 46:
/* 481 */             switch (nextChar()) { case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55:
/*     */               case 56:
/*     */               case 57:
/* 484 */                 return dotNumber(); }
/*     */             
/* 486 */             throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*     */           case 48: case 49: case 50:
/*     */           case 51:
/*     */           case 52:
/*     */           case 53:
/*     */           case 54:
/*     */           case 55:
/*     */           case 56:
/*     */           case 57:
/* 495 */             break; }  }  return 8;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int dotNumber() throws IOException {
/*     */       while (true)
/* 503 */       { switch (nextChar()) { case 48: case 49: case 50: case 51:
/*     */           case 52:
/*     */           case 53:
/*     */           case 54:
/*     */           case 55:
/*     */           case 56:
/*     */           case 57:
/* 510 */             break; }  }  return 8;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/XPathSubsetContentSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */