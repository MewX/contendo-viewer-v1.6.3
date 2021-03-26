/*     */ package jp.cssj.d.a;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ class k
/*     */   extends DefaultHandler
/*     */ {
/*     */   final URI a;
/*  18 */   d b = new d();
/*     */   
/*     */   boolean c = false;
/*  21 */   StringBuffer d = null;
/*     */   String e;
/*     */   String f;
/*  24 */   Map<String, String> i = new HashMap<>(); String g; String h;
/*  25 */   List<f> j = new ArrayList<>();
/*  26 */   Map<String, f> k = new HashMap<>();
/*  27 */   Map<String, f> l = new HashMap<>();
/*  28 */   List<g> m = new ArrayList<>();
/*  29 */   List<m> n = new ArrayList<>();
/*  30 */   Map<String, l> o = new HashMap<>();
/*     */   
/*     */   public k(String base) {
/*  33 */     this.a = URI.create(base);
/*  34 */     this.b.d = base;
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/*  38 */     if (this.c) {
/*  39 */       this.d = new StringBuffer();
/*  40 */       this.f = atts.getValue("id");
/*  41 */       this.g = atts.getValue("refines");
/*  42 */       if (uri.equals("http://www.idpf.org/2007/opf")) {
/*  43 */         if (lName.equals("meta")) {
/*  44 */           this.h = atts.getValue("property");
/*  45 */           String name = atts.getValue("name");
/*  46 */           if (name != null) {
/*  47 */             String content = atts.getValue("content");
/*  48 */             if (content != null) {
/*  49 */               this.i.put(name, content);
/*     */             }
/*     */           } 
/*     */         } 
/*  53 */       } else if (uri.equals("http://purl.org/dc/elements/1.1/") && 
/*  54 */         lName.equals("identifier")) {
/*  55 */         String scheme = atts.getValue("http://www.idpf.org/2007/opf", "scheme");
/*  56 */         if (scheme != null) {
/*  57 */           this.d.append(scheme.toLowerCase()).append(":");
/*     */         }
/*     */       }
/*     */     
/*  61 */     } else if (uri.equals("http://www.idpf.org/2007/opf")) {
/*  62 */       if (lName.equals("package")) {
/*  63 */         this.e = atts.getValue("unique-identifier");
/*  64 */       } else if (lName.equals("metadata")) {
/*  65 */         this.c = true;
/*  66 */       } else if (lName.equals("item")) {
/*     */         
/*  68 */         f item = new f();
/*  69 */         item.a = atts.getValue("id");
/*  70 */         item.c = atts.getValue("href");
/*  71 */         item.d = this.a.resolve(item.c).getPath();
/*  72 */         item.b = atts.getValue("media-type");
/*     */         
/*  74 */         String properties = atts.getValue("properties");
/*  75 */         if (properties != null) {
/*  76 */           List<String> list = new ArrayList<>();
/*  77 */           for (StringTokenizer st = new StringTokenizer(properties); st.hasMoreElements();) {
/*  78 */             list.add(st.nextToken().trim());
/*     */           }
/*  80 */           item.g = Collections.unmodifiableList(list);
/*     */         } else {
/*  82 */           item.g = Collections.emptyList();
/*     */         } 
/*     */         
/*  85 */         this.j.add(item);
/*  86 */         this.k.put(item.a, item);
/*  87 */         this.l.put(item.c, item);
/*     */         
/*  89 */         if (item.g != null) {
/*     */           
/*  91 */           if (item.g.contains("cover-image")) {
/*  92 */             this.b.o = item;
/*     */           }
/*     */ 
/*     */           
/*  96 */           if (item.g.indexOf("nav") != -1) {
/*  97 */             this.b.n = item;
/*     */           }
/*     */         } 
/* 100 */       } else if (lName.equals("spine")) {
/*     */         
/* 102 */         if (this.b.n == null) {
/* 103 */           String toc = atts.getValue("toc");
/* 104 */           if (toc != null) {
/* 105 */             this.b.n = this.k.get(toc);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 110 */         String pageProgressionDirection = atts.getValue("page-progression-direction");
/* 111 */         if (pageProgressionDirection != null) {
/* 112 */           if (pageProgressionDirection.equals("ltr")) {
/* 113 */             this.b.s = 1;
/* 114 */           } else if (pageProgressionDirection.equals("rtl")) {
/* 115 */             this.b.s = 2;
/*     */           } 
/*     */         }
/* 118 */       } else if (lName.equals("itemref")) {
/* 119 */         String idref = atts.getValue("idref");
/* 120 */         f item = this.k.get(idref);
/* 121 */         if (item != null) {
/* 122 */           g itemRef = new g(item);
/* 123 */           this.m.add(itemRef);
/*     */           
/* 125 */           String properties = atts.getValue("properties");
/* 126 */           if (properties != null) {
/* 127 */             List<String> list = new ArrayList<>();
/* 128 */             for (StringTokenizer st = new StringTokenizer(properties); st.hasMoreElements();) {
/* 129 */               list.add(st.nextToken().trim());
/*     */             }
/* 131 */             itemRef.f = Collections.unmodifiableList(list);
/*     */ 
/*     */             
/* 134 */             if (itemRef.f.contains("page-spread-left")) {
/* 135 */               itemRef.e = 1;
/* 136 */             } else if (itemRef.f.contains("page-spread-right")) {
/* 137 */               itemRef.e = 2;
/*     */             } 
/*     */           } else {
/* 140 */             itemRef.f = Collections.emptyList();
/*     */           } 
/*     */         } 
/* 143 */       } else if (lName.equals("reference")) {
/*     */         
/* 145 */         m reference = new m();
/* 146 */         reference.c = atts.getValue("href");
/* 147 */         reference.d = this.a.resolve(reference.c).getPath();
/* 148 */         reference.a = atts.getValue("type");
/* 149 */         reference.b = atts.getValue("title");
/* 150 */         this.n.add(reference);
/* 151 */         reference.e = this.l.get(reference.c);
/* 152 */         if (reference.e != null) {
/* 153 */           reference.e.f = reference;
/* 154 */           reference.e.e = reference.b;
/*     */           
/* 156 */           if (this.b.o == null && 
/* 157 */             "cover".equals(reference.a)) {
/* 158 */             this.b.o = reference.e;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int off, int len) throws SAXException {
/* 167 */     if (this.d != null) {
/* 168 */       this.d.append(ch, off, len);
/*     */     }
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String lName, String qName) throws SAXException {
/* 173 */     if (uri.equals("http://www.idpf.org/2007/opf")) {
/* 174 */       if (lName.equals("metadata")) {
/* 175 */         this.c = false;
/* 176 */       } else if (lName.equals("manifest") && 
/* 177 */         this.b.o == null) {
/* 178 */         String cover = this.i.get("cover");
/* 179 */         if (cover != null) {
/* 180 */           f item = this.k.get(cover);
/* 181 */           if (item != null) {
/* 182 */             this.b.o = item;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 188 */     if (this.d != null) {
/* 189 */       String text = this.d.toString();
/* 190 */       this.d = null;
/* 191 */       if (uri.equals("http://www.idpf.org/2007/opf")) {
/* 192 */         if (lName.equals("meta")) {
/* 193 */           if (this.g != null) {
/* 194 */             l propstr = this.o.get(this.g.substring(1));
/* 195 */             if (propstr != null) {
/* 196 */               propstr.b.put(this.h, text);
/*     */             }
/* 198 */           } else if (this.h != null) {
/* 199 */             this.i.put(this.h, text);
/*     */           } 
/*     */         }
/* 202 */       } else if (uri.equals("http://purl.org/dc/elements/1.1/")) {
/* 203 */         l propstr = null;
/* 204 */         if (lName.equals("title")) {
/* 205 */           this.b.f = propstr = new l(text);
/* 206 */         } else if (lName.equals("description")) {
/* 207 */           this.b.g = propstr = new l(text);
/* 208 */         } else if (lName.equals("creator")) {
/* 209 */           this.b.j.add(propstr = new l(text));
/* 210 */         } else if (lName.equals("language")) {
/* 211 */           this.b.h.add(propstr = new l(text));
/* 212 */         } else if (lName.equals("identifier")) {
/* 213 */           propstr = new l(text);
/* 214 */           this.b.i.add(propstr);
/* 215 */           if (this.f != null && this.f.equals(this.e)) {
/* 216 */             this.b.e = propstr;
/*     */           }
/* 218 */         } else if (lName.equals("rights")) {
/* 219 */           this.b.l.add(propstr = new l(text));
/* 220 */         } else if (lName.equals("publisher")) {
/* 221 */           this.b.k.add(propstr = new l(text));
/*     */         } 
/* 223 */         if (propstr != null && this.f != null) {
/* 224 */           this.o.put(this.f, propstr);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public d a() {
/* 231 */     this.b.m = Collections.unmodifiableMap(this.i);
/*     */     
/* 233 */     this.b.p = this.j.<f>toArray(new f[this.j.size()]);
/*     */     
/* 235 */     Map<String, f> map = new HashMap<>();
/* 236 */     for (f item : this.b.p) {
/* 237 */       map.put(item.d, item);
/*     */     }
/* 239 */     this.b.q = Collections.unmodifiableMap(map);
/*     */     
/* 241 */     this.b.r = this.m.<g>toArray(new g[this.m.size()]);
/* 242 */     this.b.t = this.n.<m>toArray(new m[this.m.size()]);
/* 243 */     return this.b;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */