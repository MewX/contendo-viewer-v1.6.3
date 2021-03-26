/*     */ package jp.cssj.a;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.xerces.util.XMLAttributesImpl;
/*     */ import org.apache.xerces.util.XMLResourceIdentifierImpl;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.QName;
/*     */ import org.apache.xerces.xni.XMLAttributes;
/*     */ import org.apache.xerces.xni.XMLDocumentHandler;
/*     */ import org.apache.xerces.xni.XMLResourceIdentifier;
/*     */ import org.apache.xerces.xni.XMLString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class h
/*     */ {
/*  37 */   protected List<a> a = new ArrayList<>();
/*     */   
/*  39 */   private int[] b = new int[] { -1, -1, -1, -1, -1 };
/*     */   
/*  41 */   private int[] c = new int[] { 0, 0, 0, 0, 0 };
/*     */   
/*  43 */   private int d = 0;
/*     */   
/*     */   private boolean e = false;
/*     */   
/*     */   public void a() {
/*     */     int pointer;
/*  49 */     if (this.d <= 0) {
/*  50 */       pointer = 0;
/*     */     } else {
/*  52 */       pointer = this.b[this.d - 1] + 1;
/*     */     } 
/*  54 */     this.b[this.d] = pointer;
/*  55 */     this.e = true;
/*     */   }
/*     */   
/*     */   public int b() {
/*  59 */     if (this.d == 0) {
/*  60 */       return 0;
/*     */     }
/*  62 */     return this.c[this.d - 1];
/*     */   }
/*     */   
/*     */   public void a(XMLDocumentHandler handler) {
/*  66 */     this.e = false;
/*  67 */     int level = this.d;
/*  68 */     int limit = (level == 0) ? this.a.size() : this.b[level - 1];
/*  69 */     this.d++;
/*  70 */     if (this.b.length <= this.d) {
/*  71 */       int[] pointer = new int[this.d * 3 / 2];
/*  72 */       int[] elementLevel = new int[this.d * 3 / 2];
/*  73 */       System.arraycopy(this.b, 0, pointer, 0, this.b.length);
/*  74 */       for (int i = this.d; i < pointer.length; i++) {
/*  75 */         pointer[i] = -1;
/*     */       }
/*  77 */       System.arraycopy(this.c, 0, elementLevel, 0, this.c.length);
/*     */     } 
/*  79 */     for (; this.b[level] < limit; this.b[level] = this.b[level] + 1) {
/*  80 */       int pointer = this.b[level];
/*  81 */       a event = this.a.get(pointer);
/*  82 */       event.a(handler);
/*  83 */       if (this.b[this.d] == -1 || pointer < this.b[this.d]) {
/*  84 */         this.a.set(pointer, null);
/*     */       }
/*     */     } 
/*  87 */     this.d--;
/*  88 */     this.b[level] = -1;
/*  89 */     this.c[level] = 0;
/*  90 */     if (level == 0) {
/*  91 */       this.a.clear();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  96 */     return this.e;
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 100 */     return this.a.isEmpty();
/*     */   }
/*     */   
/*     */   public void a(XMLString text, Augmentations augs) {
/* 104 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 107 */     char[] chars = new char[text.length];
/* 108 */     System.arraycopy(text.ch, text.offset, chars, 0, text.length);
/* 109 */     XMLString text_ = new XMLString(chars, 0, chars.length);
/* 110 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 111 */     a event = new a(this, text_, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 113 */           handler.characters(this.a, this.b);
/*     */         }
/*     */       };
/* 116 */     this.a.add(event);
/*     */   }
/*     */   
/*     */   public void b(XMLString text, Augmentations augs) {
/* 120 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 123 */     char[] chars = new char[text.length];
/* 124 */     System.arraycopy(text.ch, text.offset, chars, 0, text.length);
/* 125 */     XMLString text_ = new XMLString(chars, 0, chars.length);
/* 126 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 127 */     a event = new a(this, text_, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 129 */           handler.comment(this.a, this.b);
/*     */         }
/*     */       };
/* 132 */     this.a.add(event);
/*     */   }
/*     */   
/*     */   public void a(Augmentations augs) {
/* 136 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 139 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 140 */     a event = new a(this, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 142 */           handler.endCDATA(this.a);
/*     */         }
/*     */       };
/* 145 */     this.a.add(event);
/*     */   }
/*     */   
/*     */   public void a(QName element, Augmentations augs) {
/* 149 */     this.c[this.d] = this.c[this.d] - 1;
/* 150 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 153 */     QName element_ = new QName(element);
/* 154 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 155 */     a event = new a(this, element_, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 157 */           handler.endElement(this.a, this.b);
/*     */         }
/*     */       };
/* 160 */     this.a.add(event);
/*     */   }
/*     */   
/*     */   public void a(String name, Augmentations augs) {
/* 164 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 167 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 168 */     a event = new a(this, name, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 170 */           handler.endGeneralEntity(this.a, this.b);
/*     */         }
/*     */       };
/* 173 */     this.a.add(event);
/*     */   }
/*     */   
/*     */   public void a(String target, XMLString data, Augmentations augs) {
/* 177 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 180 */     char[] chars = new char[data.length];
/* 181 */     System.arraycopy(data.ch, data.offset, chars, 0, data.length);
/* 182 */     XMLString data_ = new XMLString(chars, 0, chars.length);
/* 183 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 184 */     a event = new a(this, target, data_, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 186 */           handler.processingInstruction(this.a, this.b, this.c);
/*     */         }
/*     */       };
/* 189 */     this.a.add(event);
/*     */   }
/*     */   
/*     */   public void b(Augmentations augs) {
/* 193 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 196 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 197 */     a event = new a(this, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 199 */           handler.startCDATA(this.a);
/*     */         }
/*     */       };
/* 202 */     this.a.add(event);
/*     */   }
/*     */   public void a(QName element, XMLAttributes attributes, Augmentations augs) {
/*     */     XMLAttributes attributes_;
/* 206 */     this.c[this.d] = this.c[this.d] + 1;
/* 207 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 210 */     QName element_ = new QName(element);
/*     */     
/* 212 */     if (attributes != null) {
/* 213 */       int length = attributes.getLength();
/* 214 */       if (length > 0) {
/* 215 */         XMLAttributesImpl xMLAttributesImpl = new XMLAttributesImpl();
/* 216 */         QName aqname = new QName();
/* 217 */         for (int i = 0; i < length; i++) {
/* 218 */           attributes.getName(i, aqname);
/* 219 */           String type = attributes.getType(i);
/* 220 */           String value = attributes.getValue(i);
/* 221 */           String nonNormalizedValue = attributes.getNonNormalizedValue(i);
/* 222 */           boolean specified = attributes.isSpecified(i);
/* 223 */           xMLAttributesImpl.addAttribute(aqname, type, value);
/* 224 */           xMLAttributesImpl.setNonNormalizedValue(i, nonNormalizedValue);
/* 225 */           xMLAttributesImpl.setSpecified(i, specified);
/*     */         } 
/*     */       } else {
/* 228 */         attributes_ = null;
/*     */       } 
/*     */     } else {
/* 231 */       attributes_ = null;
/*     */     } 
/* 233 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 234 */     a event = new a(this, element_, attributes_, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 236 */           handler.startElement(this.a, this.b, this.c);
/*     */         }
/*     */       };
/* 239 */     this.a.add(event);
/*     */   }
/*     */   public void b(QName element, XMLAttributes attributes, Augmentations augs) {
/*     */     XMLAttributes attributes_;
/* 243 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 246 */     QName element_ = new QName(element);
/*     */     
/* 248 */     if (attributes != null) {
/* 249 */       int length = attributes.getLength();
/* 250 */       if (length > 0) {
/* 251 */         XMLAttributesImpl xMLAttributesImpl = new XMLAttributesImpl();
/* 252 */         QName aqname = new QName();
/* 253 */         for (int i = 0; i < length; i++) {
/* 254 */           attributes.getName(i, aqname);
/* 255 */           String type = attributes.getType(i);
/* 256 */           String value = attributes.getValue(i);
/* 257 */           String nonNormalizedValue = attributes.getNonNormalizedValue(i);
/* 258 */           boolean specified = attributes.isSpecified(i);
/* 259 */           xMLAttributesImpl.addAttribute(aqname, type, value);
/* 260 */           xMLAttributesImpl.setNonNormalizedValue(i, nonNormalizedValue);
/* 261 */           xMLAttributesImpl.setSpecified(i, specified);
/*     */         } 
/*     */       } else {
/* 264 */         attributes_ = null;
/*     */       } 
/*     */     } else {
/* 267 */       attributes_ = null;
/*     */     } 
/* 269 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 270 */     a event = new a(this, element_, attributes_, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 272 */           handler.emptyElement(this.a, this.b, this.c);
/*     */         }
/*     */       };
/* 275 */     this.a.add(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) {
/* 280 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/*     */     
/* 284 */     XMLResourceIdentifierImpl xMLResourceIdentifierImpl = new XMLResourceIdentifierImpl(identifier.getPublicId(), identifier.getLiteralSystemId(), identifier.getBaseSystemId(), identifier.getExpandedSystemId());
/* 285 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 286 */     a event = new a(this, name, (XMLResourceIdentifier)xMLResourceIdentifierImpl, encoding, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 288 */           handler.startGeneralEntity(this.a, this.b, this.c, this.d);
/*     */         }
/*     */       };
/* 291 */     this.a.add(event);
/*     */   }
/*     */   
/*     */   public void a(String version, String encoding, Augmentations augs) {
/* 295 */     if (this.d > 0) {
/*     */       return;
/*     */     }
/* 298 */     Augmentations augs_ = (augs == null) ? null : new a(augs);
/* 299 */     a event = new a(this, version, encoding, augs_) {
/*     */         public void a(XMLDocumentHandler handler) {
/* 301 */           handler.textDecl(this.a, this.b, this.c);
/*     */         }
/*     */       };
/* 304 */     this.a.add(event);
/*     */   }
/*     */   
/*     */   protected static interface a {
/*     */     void a(XMLDocumentHandler param1XMLDocumentHandler);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/a/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */