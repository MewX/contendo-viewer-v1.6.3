/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.Text;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class XmlWriter
/*     */   implements SVGConstants
/*     */ {
/*     */   private static String EOL;
/*     */   private static final String TAG_END = "/>";
/*     */   private static final String TAG_START = "</";
/*     */   
/*     */   static {
/*     */     String str;
/*     */   }
/*     */   
/*  53 */   private static final char[] SPACES = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */   
/*  56 */   private static final int SPACES_LEN = SPACES.length;
/*     */   
/*     */   static {
/*     */     
/*  60 */     try { str = System.getProperty("line.separator", "\n"); }
/*  61 */     catch (SecurityException e) { str = "\n"; }
/*  62 */      EOL = str;
/*     */   }
/*     */   
/*     */   static class IndentWriter extends Writer {
/*     */     protected Writer proxied;
/*     */     protected int indentLevel;
/*     */     protected int column;
/*     */     
/*     */     public IndentWriter(Writer proxied) {
/*  71 */       if (proxied == null) {
/*  72 */         throw new SVGGraphics2DRuntimeException("proxy should not be null");
/*     */       }
/*     */       
/*  75 */       this.proxied = proxied;
/*     */     }
/*     */     
/*     */     public void setIndentLevel(int indentLevel) {
/*  79 */       this.indentLevel = indentLevel;
/*     */     }
/*     */     
/*     */     public int getIndentLevel() {
/*  83 */       return this.indentLevel;
/*     */     }
/*     */     
/*     */     public void printIndent() throws IOException {
/*  87 */       this.proxied.write(XmlWriter.EOL);
/*  88 */       int temp = this.indentLevel;
/*  89 */       while (temp > 0) {
/*  90 */         if (temp > XmlWriter.SPACES_LEN) {
/*  91 */           this.proxied.write(XmlWriter.SPACES, 0, XmlWriter.SPACES_LEN);
/*  92 */           temp -= XmlWriter.SPACES_LEN; continue;
/*     */         } 
/*  94 */         this.proxied.write(XmlWriter.SPACES, 0, temp);
/*     */       } 
/*     */ 
/*     */       
/*  98 */       this.column = this.indentLevel;
/*     */     }
/*     */     
/*     */     public Writer getProxied() {
/* 102 */       return this.proxied;
/*     */     }
/*     */     public int getColumn() {
/* 105 */       return this.column;
/*     */     }
/*     */     public void write(int c) throws IOException {
/* 108 */       this.column++;
/* 109 */       this.proxied.write(c);
/*     */     }
/*     */     
/*     */     public void write(char[] cbuf) throws IOException {
/* 113 */       this.column += cbuf.length;
/* 114 */       this.proxied.write(cbuf);
/*     */     }
/*     */     
/*     */     public void write(char[] cbuf, int off, int len) throws IOException {
/* 118 */       this.column += len;
/* 119 */       this.proxied.write(cbuf, off, len);
/*     */     }
/*     */     
/*     */     public void write(String str) throws IOException {
/* 123 */       this.column += str.length();
/* 124 */       this.proxied.write(str);
/*     */     }
/*     */     
/*     */     public void write(String str, int off, int len) throws IOException {
/* 128 */       this.column += len;
/* 129 */       this.proxied.write(str, off, len);
/*     */     }
/*     */     
/*     */     public void flush() throws IOException {
/* 133 */       this.proxied.flush();
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/* 137 */       this.column = -1;
/* 138 */       this.proxied.close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeXml(Attr attr, IndentWriter out, boolean escaped) throws IOException {
/* 145 */     String name = attr.getName();
/* 146 */     out.write(name);
/* 147 */     out.write("=\"");
/* 148 */     writeChildrenXml(attr, out, escaped);
/* 149 */     out.write(34);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeChildrenXml(Attr attr, IndentWriter out, boolean escaped) throws IOException {
/* 158 */     char[] data = attr.getValue().toCharArray();
/* 159 */     if (data == null)
/*     */       return; 
/* 161 */     int length = data.length;
/* 162 */     int start = 0, last = 0;
/* 163 */     while (last < length) {
/* 164 */       char c = data[last];
/* 165 */       switch (c) {
/*     */         case '<':
/* 167 */           out.write(data, start, last - start);
/* 168 */           start = last + 1;
/* 169 */           out.write("&lt;");
/*     */           break;
/*     */         case '>':
/* 172 */           out.write(data, start, last - start);
/* 173 */           start = last + 1;
/* 174 */           out.write("&gt;");
/*     */           break;
/*     */         case '&':
/* 177 */           out.write(data, start, last - start);
/* 178 */           start = last + 1;
/* 179 */           out.write("&amp;");
/*     */           break;
/*     */         case '"':
/* 182 */           out.write(data, start, last - start);
/* 183 */           start = last + 1;
/* 184 */           out.write("&quot;");
/*     */           break;
/*     */         default:
/* 187 */           if (escaped && c > '') {
/* 188 */             out.write(data, start, last - start);
/* 189 */             String hex = "0000" + Integer.toHexString(c);
/* 190 */             out.write("&#x" + hex.substring(hex.length() - 4) + ";");
/* 191 */             start = last + 1;
/*     */           } 
/*     */           break;
/*     */       } 
/* 195 */       last++;
/*     */     } 
/* 197 */     out.write(data, start, last - start);
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
/*     */   private static void writeXml(Comment comment, IndentWriter out, boolean escaped) throws IOException {
/* 209 */     char[] data = comment.getData().toCharArray();
/*     */     
/* 211 */     if (data == null) {
/* 212 */       out.write("<!---->");
/*     */       
/*     */       return;
/*     */     } 
/* 216 */     out.write("<!--");
/* 217 */     boolean sawDash = false;
/* 218 */     int length = data.length;
/* 219 */     int start = 0, last = 0;
/*     */ 
/*     */     
/* 222 */     while (last < length) {
/* 223 */       char c = data[last];
/* 224 */       if (c == '-') {
/* 225 */         if (sawDash) {
/* 226 */           out.write(data, start, last - start);
/* 227 */           start = last;
/* 228 */           out.write(32);
/*     */         } 
/* 230 */         sawDash = true;
/*     */       } else {
/* 232 */         sawDash = false;
/*     */       } 
/* 234 */       last++;
/*     */     } 
/* 236 */     out.write(data, start, last - start);
/* 237 */     if (sawDash)
/* 238 */       out.write(32); 
/* 239 */     out.write("-->");
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeXml(Text text, IndentWriter out, boolean escaped) throws IOException {
/* 244 */     writeXml(text, out, false, escaped);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeXml(Text text, IndentWriter out, boolean trimWS, boolean escaped) throws IOException {
/* 250 */     char[] data = text.getData().toCharArray();
/*     */ 
/*     */     
/* 253 */     if (data == null) {
/* 254 */       System.err.println("Null text data??"); return;
/*     */     } 
/* 256 */     int length = data.length;
/* 257 */     int start = 0, last = 0;
/* 258 */     if (trimWS) {
/* 259 */       while (last < length) {
/* 260 */         char c = data[last];
/* 261 */         switch (c) { case '\t': case '\n': case '\r': case ' ':
/* 262 */             last++; }
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 267 */       start = last;
/*     */     } 
/*     */     
/* 270 */     while (last < length) {
/* 271 */       char c = data[last];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 281 */       switch (c) { case '\t': case '\n': case '\r':
/*     */         case ' ':
/* 283 */           if (trimWS) {
/* 284 */             int wsStart = last; last++;
/* 285 */             while (last < length) {
/* 286 */               switch (data[last]) { case '\t': case '\n': case '\r':
/*     */                 case ' ':
/* 288 */                   last++; }
/*     */ 
/*     */ 
/*     */             
/*     */             } 
/* 293 */             if (last == length) {
/* 294 */               out.write(data, start, wsStart - start);
/*     */               return;
/*     */             } 
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case '<':
/* 302 */           out.write(data, start, last - start);
/* 303 */           start = last + 1;
/* 304 */           out.write("&lt;");
/*     */           break;
/*     */         case '>':
/* 307 */           out.write(data, start, last - start);
/* 308 */           start = last + 1;
/* 309 */           out.write("&gt;");
/*     */           break;
/*     */         case '&':
/* 312 */           out.write(data, start, last - start);
/* 313 */           start = last + 1;
/* 314 */           out.write("&amp;");
/*     */           break;
/*     */         default:
/* 317 */           if (escaped && c > '') {
/* 318 */             out.write(data, start, last - start);
/* 319 */             String hex = "0000" + Integer.toHexString(c);
/* 320 */             out.write("&#x" + hex.substring(hex.length() - 4) + ";");
/* 321 */             start = last + 1;
/*     */           } 
/*     */           break; }
/*     */       
/* 325 */       last++;
/*     */     } 
/* 327 */     out.write(data, start, last - start);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeXml(CDATASection cdataSection, IndentWriter out, boolean escaped) throws IOException {
/* 333 */     char[] data = cdataSection.getData().toCharArray();
/* 334 */     if (data == null) {
/* 335 */       out.write("<![CDATA[]]>");
/*     */       
/*     */       return;
/*     */     } 
/* 339 */     out.write("<![CDATA[");
/* 340 */     int length = data.length;
/* 341 */     int start = 0, last = 0;
/* 342 */     while (last < length) {
/* 343 */       char c = data[last];
/*     */ 
/*     */ 
/*     */       
/* 347 */       if (c == ']' && 
/* 348 */         last + 2 < data.length && data[last + 1] == ']' && data[last + 2] == '>') {
/*     */ 
/*     */         
/* 351 */         out.write(data, start, last - start);
/* 352 */         start = last + 1;
/* 353 */         out.write("]]]]><![CDATA[>");
/*     */         
/*     */         continue;
/*     */       } 
/* 357 */       last++;
/*     */     } 
/* 359 */     out.write(data, start, last - start);
/* 360 */     out.write("]]>");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeXml(Element element, IndentWriter out, boolean escaped) throws IOException, SVGGraphics2DIOException {
/* 366 */     out.write("</", 0, 1);
/* 367 */     out.write(element.getTagName());
/*     */     
/* 369 */     NamedNodeMap attributes = element.getAttributes();
/* 370 */     if (attributes != null) {
/* 371 */       int nAttr = attributes.getLength();
/* 372 */       for (int i = 0; i < nAttr; i++) {
/* 373 */         Attr attr = (Attr)attributes.item(i);
/* 374 */         out.write(32);
/* 375 */         writeXml(attr, out, escaped);
/*     */       } 
/*     */     } 
/*     */     
/* 379 */     boolean lastElem = (element.getParentNode().getLastChild() == element);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 386 */     if (!element.hasChildNodes()) {
/* 387 */       if (lastElem)
/* 388 */         out.setIndentLevel(out.getIndentLevel() - 2); 
/* 389 */       out.printIndent();
/* 390 */       out.write("/>", 0, 2);
/*     */       return;
/*     */     } 
/* 393 */     Node child = element.getFirstChild();
/* 394 */     out.printIndent();
/* 395 */     out.write("/>", 1, 1);
/* 396 */     if (child.getNodeType() != 3 || element.getLastChild() != child)
/*     */     {
/* 398 */       out.setIndentLevel(out.getIndentLevel() + 2);
/*     */     }
/*     */     
/* 401 */     writeChildrenXml(element, out, escaped);
/*     */     
/* 403 */     out.write("</", 0, 2);
/* 404 */     out.write(element.getTagName());
/* 405 */     if (lastElem)
/* 406 */       out.setIndentLevel(out.getIndentLevel() - 2); 
/* 407 */     out.printIndent();
/* 408 */     out.write("/>", 1, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeChildrenXml(Element element, IndentWriter out, boolean escaped) throws IOException, SVGGraphics2DIOException {
/* 414 */     Node child = element.getFirstChild();
/* 415 */     while (child != null) {
/* 416 */       writeXml(child, out, escaped);
/* 417 */       child = child.getNextSibling();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeDocumentHeader(IndentWriter out) throws IOException {
/* 423 */     String encoding = null;
/*     */     
/* 425 */     if (out.getProxied() instanceof OutputStreamWriter) {
/* 426 */       OutputStreamWriter osw = (OutputStreamWriter)out.getProxied();
/* 427 */       encoding = java2std(osw.getEncoding());
/*     */     } 
/*     */     
/* 430 */     out.write("<?xml version=\"1.0\"");
/* 431 */     if (encoding != null) {
/* 432 */       out.write(" encoding=\"");
/* 433 */       out.write(encoding);
/* 434 */       out.write(34);
/*     */     } 
/* 436 */     out.write("?>");
/* 437 */     out.write(EOL);
/*     */ 
/*     */     
/* 440 */     out.write("<!DOCTYPE svg PUBLIC '");
/* 441 */     out.write("-//W3C//DTD SVG 1.0//EN");
/* 442 */     out.write("'"); out.write(EOL);
/*     */     
/* 444 */     out.write("          '");
/* 445 */     out.write("http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd");
/* 446 */     out.write("'"); out.write(">"); out.write(EOL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeXml(Document document, IndentWriter out, boolean escaped) throws IOException, SVGGraphics2DIOException {
/* 452 */     writeDocumentHeader(out);
/* 453 */     NodeList childList = document.getChildNodes();
/* 454 */     writeXml(childList, out, escaped);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeXml(NodeList childList, IndentWriter out, boolean escaped) throws IOException, SVGGraphics2DIOException {
/* 460 */     int length = childList.getLength();
/*     */     
/* 462 */     if (length == 0)
/*     */       return; 
/* 464 */     for (int i = 0; i < length; i++) {
/* 465 */       Node child = childList.item(i);
/* 466 */       writeXml(child, out, escaped);
/* 467 */       out.write(EOL);
/*     */     } 
/*     */   }
/*     */   
/*     */   static String java2std(String encodingName) {
/* 472 */     if (encodingName == null) {
/* 473 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 480 */     if (encodingName.startsWith("ISO8859_"))
/* 481 */       return "ISO-8859-" + encodingName.substring(8); 
/* 482 */     if (encodingName.startsWith("8859_")) {
/* 483 */       return "ISO-8859-" + encodingName.substring(5);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 488 */     if ("ASCII7".equalsIgnoreCase(encodingName) || "ASCII".equalsIgnoreCase(encodingName))
/*     */     {
/* 490 */       return "US-ASCII";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 496 */     if ("UTF8".equalsIgnoreCase(encodingName))
/* 497 */       return "UTF-8"; 
/* 498 */     if (encodingName.startsWith("Unicode")) {
/* 499 */       return "UTF-16";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 504 */     if ("SJIS".equalsIgnoreCase(encodingName))
/* 505 */       return "Shift_JIS"; 
/* 506 */     if ("JIS".equalsIgnoreCase(encodingName))
/* 507 */       return "ISO-2022-JP"; 
/* 508 */     if ("EUCJIS".equalsIgnoreCase(encodingName)) {
/* 509 */       return "EUC-JP";
/*     */     }
/*     */     
/* 512 */     return "UTF-8";
/*     */   }
/*     */   
/*     */   public static void writeXml(Node node, Writer writer, boolean escaped) throws SVGGraphics2DIOException {
/*     */     try {
/*     */       NodeList childList;
/* 518 */       IndentWriter out = null;
/* 519 */       if (writer instanceof IndentWriter) {
/* 520 */         out = (IndentWriter)writer;
/*     */       } else {
/* 522 */         out = new IndentWriter(writer);
/*     */       } 
/* 524 */       switch (node.getNodeType()) {
/*     */         case 2:
/* 526 */           writeXml((Attr)node, out, escaped);
/*     */           return;
/*     */         case 8:
/* 529 */           writeXml((Comment)node, out, escaped);
/*     */           return;
/*     */         case 3:
/* 532 */           writeXml((Text)node, out, escaped);
/*     */           return;
/*     */         case 4:
/* 535 */           writeXml((CDATASection)node, out, escaped);
/*     */           return;
/*     */         case 9:
/* 538 */           writeXml((Document)node, out, escaped);
/*     */           return;
/*     */         case 11:
/* 541 */           writeDocumentHeader(out);
/* 542 */           childList = node.getChildNodes();
/* 543 */           writeXml(childList, out, escaped);
/*     */           return;
/*     */         case 1:
/* 546 */           writeXml((Element)node, out, escaped);
/*     */           return;
/*     */       } 
/* 549 */       throw new SVGGraphics2DRuntimeException("Unable to write node of type " + node.getClass().getName());
/*     */     
/*     */     }
/* 552 */     catch (IOException io) {
/* 553 */       throw new SVGGraphics2DIOException(io);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/XmlWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */