/*     */ package org.cyberneko.html.filters;
/*     */ 
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Vector;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.NamespaceContext;
/*     */ import org.apache.xerces.xni.QName;
/*     */ import org.apache.xerces.xni.XMLAttributes;
/*     */ import org.apache.xerces.xni.XMLLocator;
/*     */ import org.apache.xerces.xni.XMLResourceIdentifier;
/*     */ import org.apache.xerces.xni.XMLString;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.apache.xerces.xni.parser.XMLDocumentFilter;
/*     */ import org.apache.xerces.xni.parser.XMLInputSource;
/*     */ import org.cyberneko.html.c;
/*     */ import org.cyberneko.html.d;
/*     */ import org.cyberneko.html.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Writer
/*     */   extends a
/*     */ {
/*     */   public static final String a = "http://apache.org/xml/features/scanner/notify-char-refs";
/*     */   public static final String b = "http://cyberneko.org/html/features/scanner/notify-builtin-refs";
/*     */   protected static final String c = "http://cyberneko.org/html/features/augmentations";
/*     */   protected static final String f = "http://cyberneko.org/html/properties/filters";
/*     */   protected String g;
/*     */   protected PrintWriter h;
/*     */   protected boolean i;
/*     */   protected boolean j;
/*     */   protected int k;
/*     */   protected boolean l;
/*     */   protected boolean m;
/*     */   static Class n;
/*     */   
/*     */   public Writer() {
/*     */     try {
/* 121 */       this.g = "UTF-8";
/* 122 */       this.h = new PrintWriter(new OutputStreamWriter(System.out, this.g));
/*     */     }
/* 124 */     catch (UnsupportedEncodingException e) {
/* 125 */       throw new RuntimeException(e.getMessage());
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
/*     */   public Writer(OutputStream outputStream, String encoding) throws UnsupportedEncodingException {
/* 139 */     this(new OutputStreamWriter(outputStream, encoding), encoding);
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
/*     */   public Writer(java.io.Writer writer, String encoding) {
/* 151 */     this.g = encoding;
/* 152 */     if (writer instanceof PrintWriter) {
/* 153 */       this.h = (PrintWriter)writer;
/*     */     } else {
/*     */       
/* 156 */       this.h = new PrintWriter(writer);
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
/*     */   public void startDocument(XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) throws XNIException {
/* 170 */     this.i = false;
/* 171 */     this.j = false;
/* 172 */     this.k = 0;
/* 173 */     this.l = true;
/* 174 */     this.m = true;
/* 175 */     super.startDocument(locator, encoding, nscontext, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(XMLLocator locator, String encoding, Augmentations augs) throws XNIException {
/* 183 */     startDocument(locator, encoding, null, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(XMLString text, Augmentations augs) throws XNIException {
/* 189 */     if (this.i && this.k <= 0) {
/* 190 */       this.h.println();
/*     */     }
/* 192 */     this.h.print("<!--");
/* 193 */     a(text, false);
/* 194 */     this.h.print("-->");
/* 195 */     if (!this.i) {
/* 196 */       this.h.println();
/*     */     }
/* 198 */     this.h.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
/* 204 */     this.i = true;
/* 205 */     this.k++;
/* 206 */     this.l = !d.a(element.rawname).e();
/* 207 */     a(element, attributes);
/* 208 */     super.startElement(element, attributes, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
/* 214 */     this.i = true;
/* 215 */     a(element, attributes);
/* 216 */     super.emptyElement(element, attributes, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(XMLString text, Augmentations augs) throws XNIException {
/* 222 */     if (this.m) {
/* 223 */       a(text, this.l);
/*     */     }
/* 225 */     super.characters(text, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(QName element, Augmentations augs) throws XNIException {
/* 231 */     this.k--;
/* 232 */     this.l = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     a(element);
/* 250 */     super.endElement(element, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startGeneralEntity(String name, XMLResourceIdentifier id, String encoding, Augmentations augs) throws XNIException {
/* 256 */     this.m = false;
/* 257 */     if (name.startsWith("#")) {
/*     */       try {
/* 259 */         boolean hex = name.startsWith("#x");
/* 260 */         int offset = hex ? 2 : 1;
/* 261 */         int base = hex ? 16 : 10;
/* 262 */         int value = Integer.parseInt(name.substring(offset), base);
/* 263 */         String entity = e.a(value);
/* 264 */         if (entity != null) {
/* 265 */           name = entity;
/*     */         }
/*     */       }
/* 268 */       catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */ 
/*     */     
/* 272 */     b(name);
/* 273 */     super.startGeneralEntity(name, id, encoding, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
/* 279 */     this.m = true;
/* 280 */     super.endGeneralEntity(name, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(String text) {
/* 289 */     int length = text.length();
/* 290 */     for (int j = 0; j < length; j++) {
/* 291 */       char c = text.charAt(j);
/* 292 */       if (c == '"') {
/* 293 */         this.h.print("&quot;");
/*     */       } else {
/*     */         
/* 296 */         this.h.print(c);
/*     */       } 
/*     */     } 
/* 299 */     this.h.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(XMLString text, boolean normalize) {
/* 304 */     if (normalize) {
/* 305 */       for (int i = 0; i < text.length; i++) {
/* 306 */         char c = text.ch[text.offset + i];
/* 307 */         if (c != '\n') {
/* 308 */           String entity = e.a(c);
/* 309 */           if (entity != null) {
/* 310 */             b(entity);
/*     */           } else {
/*     */             
/* 313 */             this.h.print(c);
/*     */           } 
/*     */         } else {
/*     */           
/* 317 */           this.h.println();
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 322 */       for (int i = 0; i < text.length; i++) {
/* 323 */         char c = text.ch[text.offset + i];
/* 324 */         this.h.print(c);
/*     */       } 
/*     */     } 
/* 327 */     this.h.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(QName element, XMLAttributes attributes) {
/* 334 */     int contentIndex = -1;
/* 335 */     String originalContent = null;
/* 336 */     if (element.rawname.toLowerCase().equals("meta")) {
/* 337 */       String httpEquiv = null;
/* 338 */       int length = attributes.getLength();
/* 339 */       for (int j = 0; j < length; j++) {
/* 340 */         String aname = attributes.getQName(j).toLowerCase();
/* 341 */         if (aname.equals("http-equiv")) {
/* 342 */           httpEquiv = attributes.getValue(j);
/*     */         }
/* 344 */         else if (aname.equals("content")) {
/* 345 */           contentIndex = j;
/*     */         } 
/*     */       } 
/* 348 */       if (httpEquiv != null && httpEquiv.toLowerCase().equals("content-type")) {
/* 349 */         this.j = true;
/* 350 */         String content = null;
/* 351 */         if (contentIndex != -1) {
/* 352 */           originalContent = attributes.getValue(contentIndex);
/* 353 */           content = originalContent.toLowerCase();
/*     */         } 
/* 355 */         if (content != null) {
/* 356 */           int charsetIndex = content.indexOf("charset=");
/* 357 */           if (charsetIndex != -1) {
/* 358 */             content = content.substring(0, charsetIndex + 8);
/*     */           } else {
/*     */             
/* 361 */             content = content + ";charset=";
/*     */           } 
/* 363 */           content = content + this.g;
/* 364 */           attributes.setValue(contentIndex, content);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 370 */     this.h.print('<');
/* 371 */     this.h.print(element.rawname);
/* 372 */     int attrCount = (attributes != null) ? attributes.getLength() : 0;
/* 373 */     for (int i = 0; i < attrCount; i++) {
/* 374 */       String aname = attributes.getQName(i);
/* 375 */       String avalue = attributes.getValue(i);
/* 376 */       this.h.print(' ');
/* 377 */       this.h.print(aname);
/* 378 */       this.h.print("=\"");
/* 379 */       a(avalue);
/* 380 */       this.h.print('"');
/*     */     } 
/* 382 */     this.h.print('>');
/* 383 */     this.h.flush();
/*     */ 
/*     */     
/* 386 */     if (contentIndex != -1 && originalContent != null) {
/* 387 */       attributes.setValue(contentIndex, originalContent);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(QName element) {
/* 394 */     this.h.print("</");
/* 395 */     this.h.print(element.rawname);
/* 396 */     this.h.print('>');
/* 397 */     this.h.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(String name) {
/* 402 */     this.h.print('&');
/* 403 */     this.h.print(name);
/* 404 */     this.h.print(';');
/* 405 */     this.h.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) throws Exception {
/* 414 */     if (argv.length == 0) {
/* 415 */       a();
/* 416 */       System.exit(1);
/*     */     } 
/* 418 */     c c = new c();
/* 419 */     c.setFeature("http://apache.org/xml/features/scanner/notify-char-refs", true);
/* 420 */     c.setFeature("http://cyberneko.org/html/features/scanner/notify-builtin-refs", true);
/* 421 */     String iencoding = null;
/* 422 */     String oencoding = "Windows-1252";
/* 423 */     boolean identity = false;
/* 424 */     boolean purify = false;
/* 425 */     for (int i = 0; i < argv.length; i++) {
/* 426 */       String arg = argv[i];
/* 427 */       if (arg.equals("-ie")) {
/* 428 */         iencoding = argv[++i];
/*     */       
/*     */       }
/* 431 */       else if (arg.equals("-e") || arg.equals("-oe")) {
/* 432 */         oencoding = argv[++i];
/*     */       
/*     */       }
/* 435 */       else if (arg.equals("-i")) {
/* 436 */         identity = true;
/*     */       
/*     */       }
/* 439 */       else if (arg.equals("-p")) {
/* 440 */         purify = true;
/*     */       } else {
/*     */         
/* 443 */         if (arg.equals("-h")) {
/* 444 */           a();
/* 445 */           System.exit(1);
/*     */         } 
/* 447 */         Vector filtersVector = new Vector(2);
/* 448 */         if (identity) {
/* 449 */           filtersVector.addElement(new c());
/*     */         }
/* 451 */         else if (purify) {
/* 452 */           filtersVector.addElement(new e());
/*     */         } 
/* 454 */         filtersVector.addElement(new Writer(System.out, oencoding));
/* 455 */         XMLDocumentFilter[] filters = new XMLDocumentFilter[filtersVector.size()];
/*     */         
/* 457 */         filtersVector.copyInto((Object[])filters);
/* 458 */         c.setProperty("http://cyberneko.org/html/properties/filters", filters);
/* 459 */         XMLInputSource source = new XMLInputSource(null, arg, null);
/* 460 */         source.setEncoding(iencoding);
/* 461 */         c.parse(source);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a() {
/* 467 */     System.err.println("usage: java " + ((n == null) ? (n = c("org.cyberneko.html.filters.Writer")) : n).getName() + " (options) file ...");
/* 468 */     System.err.println();
/* 469 */     System.err.println("options:");
/* 470 */     System.err.println("  -ie name  Specify IANA name of input encoding.");
/* 471 */     System.err.println("  -oe name  Specify IANA name of output encoding.");
/* 472 */     System.err.println("  -i        Perform identity transform.");
/* 473 */     System.err.println("  -p        Purify output to ensure XML well-formedness.");
/* 474 */     System.err.println("  -h        Display help screen.");
/* 475 */     System.err.println();
/* 476 */     System.err.println("notes:");
/* 477 */     System.err.println("  The -i and -p options are mutually exclusive.");
/* 478 */     System.err.println("  The -e option has been replaced with -oe.");
/*     */   }
/*     */   
/*     */   static Class c(String x0) {
/*     */     try {
/*     */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*     */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/filters/Writer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */