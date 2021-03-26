/*     */ package org.cyberneko.html.filters;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.NamespaceContext;
/*     */ import org.apache.xerces.xni.QName;
/*     */ import org.apache.xerces.xni.XMLAttributes;
/*     */ import org.apache.xerces.xni.XMLLocator;
/*     */ import org.apache.xerces.xni.XMLResourceIdentifier;
/*     */ import org.apache.xerces.xni.XMLString;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends a
/*     */ {
/* 102 */   protected static final Object a = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   protected Hashtable b = new Hashtable();
/*     */ 
/*     */   
/* 114 */   protected Hashtable c = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int g;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String element, String[] attributes) {
/* 139 */     Object key = element.toLowerCase();
/* 140 */     Object value = a;
/* 141 */     if (attributes != null) {
/* 142 */       String[] newarray = new String[attributes.length];
/* 143 */       for (int i = 0; i < attributes.length; i++) {
/* 144 */         newarray[i] = attributes[i].toLowerCase();
/*     */       }
/* 146 */       value = attributes;
/*     */     } 
/* 148 */     this.b.put(key, value);
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
/*     */   public void a(String element) {
/* 160 */     Object key = element.toLowerCase();
/* 161 */     Object value = a;
/* 162 */     this.c.put(key, value);
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
/* 175 */     this.f = 0;
/* 176 */     this.g = Integer.MAX_VALUE;
/* 177 */     super.startDocument(locator, encoding, nscontext, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(XMLLocator locator, String encoding, Augmentations augs) throws XNIException {
/* 185 */     startDocument(locator, encoding, null, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String prefix, String uri, Augmentations augs) throws XNIException {
/* 191 */     if (this.f <= this.g) {
/* 192 */       super.a(prefix, uri, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
/* 199 */     if (this.f <= this.g && a(element, attributes)) {
/* 200 */       super.startElement(element, attributes, augs);
/*     */     }
/* 202 */     this.f++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
/* 208 */     if (this.f <= this.g && a(element, attributes)) {
/* 209 */       super.emptyElement(element, attributes, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(XMLString text, Augmentations augs) throws XNIException {
/* 216 */     if (this.f <= this.g) {
/* 217 */       super.comment(text, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
/* 224 */     if (this.f <= this.g) {
/* 225 */       super.processingInstruction(target, data, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(XMLString text, Augmentations augs) throws XNIException {
/* 232 */     if (this.f <= this.g) {
/* 233 */       super.characters(text, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
/* 240 */     if (this.f <= this.g) {
/* 241 */       super.ignorableWhitespace(text, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startGeneralEntity(String name, XMLResourceIdentifier id, String encoding, Augmentations augs) throws XNIException {
/* 248 */     if (this.f <= this.g) {
/* 249 */       super.startGeneralEntity(name, id, encoding, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
/* 256 */     if (this.f <= this.g) {
/* 257 */       super.textDecl(version, encoding, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
/* 264 */     if (this.f <= this.g) {
/* 265 */       super.endGeneralEntity(name, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void startCDATA(Augmentations augs) throws XNIException {
/* 271 */     if (this.f <= this.g) {
/* 272 */       super.startCDATA(augs);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void endCDATA(Augmentations augs) throws XNIException {
/* 278 */     if (this.f <= this.g) {
/* 279 */       super.endCDATA(augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(QName element, Augmentations augs) throws XNIException {
/* 286 */     if (this.f <= this.g && b(element.rawname)) {
/* 287 */       super.endElement(element, augs);
/*     */     }
/* 289 */     this.f--;
/* 290 */     if (this.f == this.g) {
/* 291 */       this.g = Integer.MAX_VALUE;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String prefix, Augmentations augs) throws XNIException {
/* 298 */     if (this.f <= this.g) {
/* 299 */       super.a(prefix, augs);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean b(String element) {
/* 309 */     Object key = element.toLowerCase();
/* 310 */     return this.b.containsKey(key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean c(String element) {
/* 315 */     Object key = element.toLowerCase();
/* 316 */     return this.c.containsKey(key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(QName element, XMLAttributes attributes) {
/* 321 */     if (b(element.rawname)) {
/* 322 */       Object key = element.rawname.toLowerCase();
/* 323 */       Object value = this.b.get(key);
/* 324 */       if (value != a) {
/* 325 */         String[] anames = (String[])value;
/* 326 */         int attributeCount = attributes.getLength();
/* 327 */         for (int i = 0; i < attributeCount; i++) {
/* 328 */           String aname = attributes.getQName(i).toLowerCase();
/* 329 */           int j = 0; while (true) { if (j < anames.length) {
/* 330 */               if (anames[j].equals(aname))
/*     */                 break;  j++;
/*     */               continue;
/*     */             } 
/* 334 */             attributes.removeAttributeAt(i--);
/* 335 */             attributeCount--; break; }
/*     */         
/*     */         } 
/*     */       } else {
/* 339 */         attributes.removeAllAttributes();
/*     */       } 
/* 341 */       return true;
/*     */     } 
/* 343 */     if (c(element.rawname)) {
/* 344 */       this.g = this.f;
/*     */     }
/* 346 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/filters/b.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */