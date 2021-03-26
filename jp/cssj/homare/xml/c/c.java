/*     */ package jp.cssj.homare.xml.c;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import jp.cssj.b.a.g;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.homare.xml.a.a;
/*     */ import jp.cssj.homare.xml.b;
/*     */ import jp.cssj.homare.xml.b.d;
/*     */ import jp.cssj.homare.xml.c;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends c
/*     */ {
/*  23 */   private String a = "text/css";
/*     */   
/*     */   private final m b;
/*     */   
/*  27 */   private int c = 0;
/*     */   
/*  29 */   private List<jp.cssj.homare.xml.b.c.a> d = new ArrayList<>();
/*     */   
/*  31 */   private List<String[]> f = (List)new ArrayList<>();
/*     */   
/*     */   private StringBuffer g;
/*     */   
/*  35 */   private AttributesImpl h = new AttributesImpl();
/*     */   
/*     */   private boolean i = true;
/*     */   
/*     */   public c(m ua) {
/*  40 */     this.b = ua;
/*  41 */     this.i = B.aZ.a(ua);
/*     */   }
/*     */   
/*     */   public void startCDATA() throws SAXException {
/*  45 */     if (this.d == null) {
/*  46 */       super.startCDATA();
/*     */     } else {
/*  48 */       this.d.add(jp.cssj.homare.xml.b.c.a());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endCDATA() throws SAXException {
/*  53 */     if (this.d == null) {
/*  54 */       super.endCDATA();
/*     */     } else {
/*  56 */       this.d.add(jp.cssj.homare.xml.b.c.b());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/*  61 */     if (this.d == null) {
/*  62 */       super.startDTD(name, publicId, systemId);
/*     */     } else {
/*  64 */       this.d.add(jp.cssj.homare.xml.b.c.b(name, publicId, systemId));
/*  65 */       this.c = this.d.size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endDTD() throws SAXException {
/*  70 */     if (this.d == null) {
/*  71 */       super.endDTD();
/*     */     } else {
/*  73 */       this.d.add(jp.cssj.homare.xml.b.c.c());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startEntity(String name) throws SAXException {
/*  78 */     if (this.d == null) {
/*  79 */       super.startEntity(name);
/*     */     } else {
/*  81 */       this.d.add(jp.cssj.homare.xml.b.c.c(name));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endEntity(String name) throws SAXException {
/*  86 */     if (this.d == null) {
/*  87 */       super.endEntity(name);
/*     */     } else {
/*  89 */       this.d.add(jp.cssj.homare.xml.b.c.d(name));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void comment(char[] ch, int off, int len) throws SAXException {
/*  94 */     if (this.d == null) {
/*  95 */       super.comment(ch, off, len);
/*     */     } else {
/*  97 */       this.d.add(jp.cssj.homare.xml.b.c.c(ch, off, len));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 102 */     if (this.f == null) {
/* 103 */       super.processingInstruction(target, data);
/*     */     } else {
/* 105 */       this.f.add(new String[] { target, data });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/* 110 */     if (this.d == null) {
/* 111 */       super.startPrefixMapping(prefix, uri);
/*     */     } else {
/* 113 */       this.d.add(jp.cssj.homare.xml.b.c.a(prefix, uri));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endPrefixMapping(String prefix) throws SAXException {
/* 118 */     if (this.d == null) {
/* 119 */       super.endPrefixMapping(prefix);
/*     */     } else {
/* 121 */       this.d.add(jp.cssj.homare.xml.b.c.a(prefix));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void skippedEntity(String name) throws SAXException {
/* 126 */     if (this.d == null) {
/* 127 */       super.skippedEntity(name);
/*     */     } else {
/* 129 */       this.d.add(jp.cssj.homare.xml.b.c.b(name));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 134 */     if (this.d == null) {
/* 135 */       super.ignorableWhitespace(ch, start, length);
/*     */     } else {
/* 137 */       this.d.add(jp.cssj.homare.xml.b.c.b(ch, start, length));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 143 */     if (uri.equals("http://www.w3.org/1999/xhtml")) {
/* 144 */       String[] pi = null;
/* 145 */       if (a.w.b.equals(lName) || a.x.b.equals(lName) || a.y.b
/* 146 */         .equals(lName) || a.z.b.equals(lName) || a.A.b
/* 147 */         .equals(lName) || a.B.b.equals(lName)) {
/*     */         
/* 149 */         if (a.d.a(atts) == null) {
/* 150 */           this.h.setAttributes(atts);
/* 151 */           atts = this.h;
/* 152 */           a.d.a(this.h, lName.substring(1));
/*     */         } 
/* 154 */       } else if (lName.equals(a.j.b)) {
/*     */         
/* 156 */         String href = a.L.a(atts);
/* 157 */         if (href != null && 
/* 158 */           b.j.a(atts) == null) {
/* 159 */           this.h.setAttributes(atts);
/* 160 */           atts = this.h;
/* 161 */           b.j.a(this.h, href);
/*     */         } 
/*     */         
/* 164 */         String name = a.M.a(atts);
/* 165 */         if (name != null && 
/* 166 */           a.D.a(atts) == null) {
/* 167 */           if (atts != this.h) {
/* 168 */             this.h.setAttributes(atts);
/* 169 */             atts = this.h;
/*     */           } 
/* 171 */           a.D.a(this.h, name);
/*     */         }
/*     */       
/* 174 */       } else if (lName.equals(a.d.b)) {
/*     */         
/* 176 */         String disabled = atts.getValue("http://www.w3.org/1999/xhtml", "disabled");
/*     */         
/* 178 */         if (disabled == null) {
/* 179 */           String type = atts.getValue("http://www.w3.org/1999/xhtml", "type");
/* 180 */           String media = atts.getValue("http://www.w3.org/1999/xhtml", "media");
/* 181 */           if ((type == null && this.a.equalsIgnoreCase("text/css")) || "text/css"
/* 182 */             .equalsIgnoreCase(type)) {
/* 183 */             if (media != null) {
/* 184 */               media = media.toLowerCase();
/*     */             } else {
/* 186 */               media = "all";
/*     */             } 
/* 188 */             if (this.b.b(media)) {
/* 189 */               this.g = new StringBuffer();
/*     */             }
/*     */           } 
/*     */         } 
/* 193 */       } else if (lName.equals(a.e.b)) {
/*     */         
/* 195 */         this.g = new StringBuffer();
/* 196 */       } else if (lName.equals(a.f.b)) {
/*     */         
/* 198 */         String charset = atts.getValue("http://www.w3.org/1999/xhtml", "charset");
/* 199 */         if (charset != null) {
/* 200 */           pi = new String[] { "jp.cssj.default-encoding", charset };
/*     */         }
/*     */         
/* 203 */         String content = atts.getValue("http://www.w3.org/1999/xhtml", "content");
/* 204 */         if (content != null) {
/* 205 */           String httpEquiv = atts.getValue("http://www.w3.org/1999/xhtml", "http-equiv");
/* 206 */           if (httpEquiv != null) {
/*     */             
/* 208 */             httpEquiv = httpEquiv.trim().toLowerCase();
/* 209 */             if (httpEquiv.equals("content-type")) {
/* 210 */               String cTcharset = g.b(content, "charset");
/* 211 */               if (cTcharset != null) {
/* 212 */                 pi = new String[] { "jp.cssj.default-encoding", cTcharset };
/*     */               }
/* 214 */             } else if (httpEquiv.equals("content-style-type")) {
/*     */               
/* 216 */               this.a = content;
/* 217 */               pi = new String[] { "jp.cssj.default-style-type", content };
/*     */             } 
/*     */           } 
/*     */           
/* 221 */           String name = atts.getValue("http://www.w3.org/1999/xhtml", "name");
/* 222 */           if (name != null) {
/* 223 */             name = name.trim().toLowerCase();
/* 224 */             if (name.equals("viewport") && B.r.a(this.b)) {
/* 225 */               AttributesImpl attsi = new AttributesImpl();
/*     */               try {
/* 227 */                 d.a(content, attsi);
/* 228 */                 double width = Double.parseDouble(attsi.getValue("width"));
/* 229 */                 double height = Double.parseDouble(attsi.getValue("height"));
/* 230 */                 this.b.a(B.s.a, width + "px");
/* 231 */                 this.b.a(B.t.a, height + "px");
/* 232 */               } catch (Exception e) {
/* 233 */                 e.printStackTrace();
/*     */               }
/*     */             
/* 236 */             } else if (this.i) {
/*     */ 
/*     */               
/* 239 */               String data = "name='" + d.a(name) + "' value='" + d.a(content) + "'";
/* 240 */               pi = new String[] { "jp.cssj.document-info", data };
/*     */             } 
/* 242 */             this.g = null;
/*     */           } 
/*     */         } 
/* 245 */       } else if (lName.equals(a.i.b)) {
/*     */         
/* 247 */         String rel = atts.getValue("http://www.w3.org/1999/xhtml", "rel");
/* 248 */         boolean valid = false;
/* 249 */         boolean alternate = false;
/* 250 */         if (rel != null) {
/* 251 */           for (StringTokenizer st = new StringTokenizer(rel); st.hasMoreTokens(); ) {
/* 252 */             String token = st.nextToken();
/* 253 */             if (token.equalsIgnoreCase("stylesheet")) {
/* 254 */               valid = true; continue;
/* 255 */             }  if (token.equalsIgnoreCase("alternate")) {
/* 256 */               alternate = true;
/*     */             }
/*     */           } 
/*     */         }
/* 260 */         if (valid) {
/* 261 */           String type = atts.getValue("http://www.w3.org/1999/xhtml", "type");
/* 262 */           String href = atts.getValue("http://www.w3.org/1999/xhtml", "href");
/* 263 */           String title = atts.getValue("http://www.w3.org/1999/xhtml", "title");
/* 264 */           String mediaTypes = atts.getValue("http://www.w3.org/1999/xhtml", "media");
/* 265 */           String charset = atts.getValue("http://www.w3.org/1999/xhtml", "charset");
/* 266 */           StringBuffer data = new StringBuffer();
/* 267 */           if (type != null) {
/* 268 */             data.append(" type='");
/* 269 */             data.append(d.a(type));
/* 270 */             data.append('\'');
/*     */           } 
/* 272 */           if (href != null) {
/* 273 */             data.append(" href='");
/* 274 */             data.append(d.a(href));
/* 275 */             data.append('\'');
/*     */           } 
/* 277 */           if (title != null) {
/* 278 */             data.append(" title='");
/* 279 */             data.append(d.a(title));
/* 280 */             data.append('\'');
/*     */           } 
/* 282 */           if (mediaTypes != null) {
/* 283 */             data.append(" media='");
/* 284 */             data.append(d.a(mediaTypes));
/* 285 */             data.append('\'');
/*     */           } 
/* 287 */           if (charset != null) {
/* 288 */             data.append(" charset='");
/* 289 */             data.append(d.a(charset));
/* 290 */             data.append('\'');
/*     */           } 
/* 292 */           if (alternate) {
/* 293 */             data.append(" alternate='yes'");
/*     */           }
/* 295 */           pi = new String[] { "xml-stylesheet", data.toString() };
/*     */         } 
/* 297 */       } else if (lName.equals(a.h.b)) {
/*     */         
/* 299 */         String href = atts.getValue("http://www.w3.org/1999/xhtml", "href");
/* 300 */         if (href != null) {
/* 301 */           pi = new String[] { "jp.cssj.base-uri", href };
/*     */         }
/* 303 */       } else if (lName.equals(a.g.b)) {
/*     */         
/* 305 */         if (this.d != null) {
/* 306 */           b();
/*     */         }
/* 308 */         super.startElement(uri, lName, qName, atts);
/*     */         return;
/*     */       } 
/* 311 */       if (pi != null) {
/* 312 */         if (this.f == null) {
/* 313 */           super.processingInstruction(pi[0], pi[1]);
/*     */         } else {
/* 315 */           this.f.add(pi);
/*     */         } 
/*     */       }
/* 318 */       if (this.d != null) {
/* 319 */         this.d.add(jp.cssj.homare.xml.b.c.a(uri, lName, qName, atts));
/*     */       } else {
/* 321 */         super.startElement(uri, lName, qName, atts);
/*     */       } 
/*     */     } else {
/* 324 */       if (this.d != null) {
/* 325 */         b();
/*     */       }
/* 327 */       super.startElement(uri, lName, qName, atts);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void b() throws SAXException {
/* 332 */     List<jp.cssj.homare.xml.b.c.a> events = this.d;
/* 333 */     this.d = null; int i;
/* 334 */     for (i = 0; i < this.c; i++) {
/* 335 */       jp.cssj.homare.xml.b.c.a event = events.get(i);
/* 336 */       event.a((ContentHandler)this.e);
/*     */     } 
/*     */     
/* 339 */     for (i = 0; i < this.f.size(); i++) {
/* 340 */       String[] pi = this.f.get(i);
/* 341 */       super.processingInstruction(pi[0], pi[1]);
/*     */     } 
/* 343 */     this.f = null;
/* 344 */     for (i = this.c; i < events.size(); i++) {
/* 345 */       jp.cssj.homare.xml.b.c.a event = events.get(i);
/* 346 */       event.a((ContentHandler)this.e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int off, int len) throws SAXException {
/* 352 */     if (this.d == null) {
/* 353 */       super.characters(ch, off, len);
/*     */     } else {
/* 355 */       this.d.add(jp.cssj.homare.xml.b.c.a(ch, off, len));
/*     */     } 
/* 357 */     if (this.g != null) {
/* 358 */       this.g.append(ch, off, len);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void endElement(String uri, String lName, String qName) throws SAXException {
/* 364 */     if (uri.equals("http://www.w3.org/1999/xhtml")) {
/* 365 */       String[] pi = null;
/* 366 */       if (lName.equals(a.d.b)) {
/* 367 */         if (this.g != null) {
/*     */           
/* 369 */           pi = new String[] { "jp.cssj.stylesheet", "[" + d.b(this.g.toString()) + "]" };
/* 370 */           this.g = null;
/*     */         } 
/* 372 */       } else if (lName.equals(a.e.b) && 
/* 373 */         this.i && this.g != null) {
/* 374 */         String data = "name='title' value='" + d.a(this.g.toString()) + "'";
/* 375 */         pi = new String[] { "jp.cssj.document-info", data };
/* 376 */         this.g = null;
/*     */       } 
/*     */       
/* 379 */       if (pi != null) {
/* 380 */         if (this.f == null) {
/* 381 */           super.processingInstruction(pi[0], pi[1]);
/*     */         } else {
/* 383 */           this.f.add(pi);
/*     */         } 
/*     */       }
/*     */     } 
/* 387 */     if (this.d == null) {
/* 388 */       super.endElement(uri, lName, qName);
/*     */     } else {
/* 390 */       this.d.add(jp.cssj.homare.xml.b.c.a(uri, lName, qName));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/c/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */