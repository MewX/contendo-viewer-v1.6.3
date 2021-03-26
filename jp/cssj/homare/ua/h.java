/*     */ package jp.cssj.homare.ua;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jp.cssj.homare.css.e.e;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ 
/*     */ 
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
/*  28 */   private final List<b> b = new ArrayList<>();
/*     */   
/*  30 */   private final jp.cssj.homare.xml.b.b<URI, a> c = new jp.cssj.homare.xml.b.b();
/*     */   
/*  32 */   private final Map<URI, int[]> d = (Map)new HashMap<>();
/*     */   
/*     */   public h() {
/*  35 */     this.b.add(new b(null, null, null));
/*     */   }
/*     */   
/*     */   public void a() {
/*  39 */     while (this.b.size() > 1) {
/*  40 */       this.b.remove(this.b.size() - 1);
/*     */     }
/*  42 */     b section = this.b.get(0);
/*  43 */     section.a();
/*  44 */     this.d.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(URI uri, c[] counters) {
/*  54 */     int[] seq = this.d.get(uri);
/*  55 */     if (seq == null) {
/*  56 */       seq = new int[] { 1 };
/*  57 */       this.d.put(uri, seq);
/*     */     } else {
/*  59 */       seq[0] = seq[0] + 1;
/*     */     } 
/*  61 */     Collection<?> col = b(uri);
/*  62 */     if (col != null) {
/*  63 */       for (Iterator<?> i = col.iterator(); i.hasNext(); ) {
/*  64 */         a f = (a)i.next();
/*  65 */         if (f.a == seq[0]) {
/*  66 */           f.c = counters;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*  71 */     a fragment = new a(seq[0], uri, counters);
/*  72 */     this.c.put(fragment.b, fragment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(URI uri, String title, c[] counters) {
/*  83 */     b section = this.b.get(this.b.size() - 1);
/*  84 */     b newEntry = section.a(uri, title, counters);
/*  85 */     this.b.add(newEntry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {
/*  92 */     if (!a && this.b.size() <= 1) throw new AssertionError(); 
/*  93 */     this.b.remove(this.b.size() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a a(URI uri) {
/* 103 */     Collection<?> col = b(uri);
/* 104 */     if (col == null || col.isEmpty()) {
/* 105 */       return null;
/*     */     }
/* 107 */     return col.iterator().next();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<?> b(URI uri) {
/* 117 */     Collection<?> col = (Collection)this.c.get(uri);
/* 118 */     return col;
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
/*     */   public void a(ContentHandler handler, String counter, short type) throws SAXException {
/* 130 */     AttributesImpl attsi = new AttributesImpl();
/* 131 */     attsi.addAttribute(jp.cssj.homare.xml.c.a.E.a, jp.cssj.homare.xml.c.a.E.b, jp.cssj.homare.xml.c.a.E.c, "CDATA", "cssj-toc");
/* 132 */     a(handler, counter, type, attsi, this.b.get(0));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void a(ContentHandler handler, String counter, short type, AttributesImpl attsi, b entry) throws SAXException {
/* 137 */     List<b> children = entry.b();
/* 138 */     if (children == null) {
/*     */       return;
/*     */     }
/* 141 */     handler.startElement(jp.cssj.homare.xml.c.a.q.a, jp.cssj.homare.xml.c.a.q.b, jp.cssj.homare.xml.c.a.q.c, attsi);
/* 142 */     attsi.clear();
/* 143 */     for (int i = 0; i < children.size(); i++) {
/* 144 */       b child = children.get(i);
/* 145 */       if (child.d != null) {
/* 146 */         handler.startElement(jp.cssj.homare.xml.c.a.r.a, jp.cssj.homare.xml.c.a.r.b, jp.cssj.homare.xml.c.a.r.c, attsi);
/* 147 */         jp.cssj.homare.xml.c.a.L.a(attsi, child.b.toString());
/* 148 */         jp.cssj.homare.xml.b.j.a(attsi, child.b.toString());
/* 149 */         handler.startElement(jp.cssj.homare.xml.c.a.j.a, jp.cssj.homare.xml.c.a.j.b, jp.cssj.homare.xml.c.a.j.c, attsi);
/* 150 */         attsi.clear();
/*     */         
/* 152 */         jp.cssj.homare.xml.c.a.E.a(attsi, "cssj-title");
/* 153 */         handler.startElement(jp.cssj.homare.xml.c.a.s.a, jp.cssj.homare.xml.c.a.s.b, jp.cssj.homare.xml.c.a.s.c, attsi);
/* 154 */         attsi.clear();
/* 155 */         char[] title = child.d.toCharArray();
/* 156 */         handler.characters(title, 0, title.length);
/* 157 */         handler.endElement(jp.cssj.homare.xml.c.a.s.a, jp.cssj.homare.xml.c.a.s.b, jp.cssj.homare.xml.c.a.s.c);
/*     */         
/* 159 */         jp.cssj.homare.xml.c.a.E.a(attsi, "cssj-page");
/* 160 */         handler.startElement(jp.cssj.homare.xml.c.a.s.a, jp.cssj.homare.xml.c.a.s.b, jp.cssj.homare.xml.c.a.s.c, attsi);
/* 161 */         attsi.clear();
/* 162 */         char[] page = e.a(child.a(counter), type).toCharArray();
/* 163 */         handler.characters(page, 0, page.length);
/* 164 */         handler.endElement(jp.cssj.homare.xml.c.a.s.a, jp.cssj.homare.xml.c.a.s.b, jp.cssj.homare.xml.c.a.s.c);
/* 165 */         handler.endElement(jp.cssj.homare.xml.c.a.r.a, jp.cssj.homare.xml.c.a.r.b, jp.cssj.homare.xml.c.a.r.c);
/*     */         
/* 167 */         handler.endElement(jp.cssj.homare.xml.c.a.j.a, jp.cssj.homare.xml.c.a.j.b, jp.cssj.homare.xml.c.a.j.c);
/*     */       } 
/* 169 */       a(handler, counter, type, attsi, child);
/*     */     } 
/* 171 */     handler.endElement(jp.cssj.homare.xml.c.a.q.a, jp.cssj.homare.xml.c.a.q.b, jp.cssj.homare.xml.c.a.q.c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */   {
/*     */     public int a;
/*     */ 
/*     */     
/*     */     public URI b;
/*     */ 
/*     */     
/*     */     public c[] c;
/*     */ 
/*     */     
/*     */     protected a(int uid, URI uri, c[] counters) {
/* 188 */       this.a = uid;
/* 189 */       this.c = counters;
/* 190 */       this.b = uri;
/*     */     }
/*     */     
/*     */     public int a(String name) {
/* 194 */       if (this.c == null) {
/* 195 */         return 0;
/*     */       }
/* 197 */       for (int i = 0; i < this.c.length; i++) {
/* 198 */         c counter = this.c[i];
/* 199 */         if (counter.a.equalsIgnoreCase(name)) {
/* 200 */           return counter.b;
/*     */         }
/*     */       } 
/* 203 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class b
/*     */     extends a
/*     */   {
/*     */     public String d;
/*     */ 
/*     */     
/* 216 */     private List<b> e = null;
/*     */     
/* 218 */     private int f = 0;
/*     */     
/*     */     b(URI uri, String title, c[] counters) {
/* 221 */       super(-1, uri, counters);
/* 222 */       this.d = title;
/*     */     }
/*     */     public b a(URI uri, String title, c[] counters) {
/*     */       b section;
/* 226 */       if (this.e == null) {
/* 227 */         this.e = new ArrayList<>();
/*     */       }
/*     */       
/* 230 */       if (this.f < this.e.size()) {
/* 231 */         section = this.e.get(this.f);
/* 232 */         section.b = uri;
/* 233 */         section.d = title;
/* 234 */         section.c = counters;
/*     */       } else {
/* 236 */         section = new b(uri, title, counters);
/* 237 */         this.e.add(section);
/*     */       } 
/* 239 */       this.f++;
/* 240 */       return section;
/*     */     }
/*     */     
/*     */     public void a() {
/* 244 */       this.f = 0;
/* 245 */       if (this.e != null) {
/* 246 */         for (int i = 0; i < this.e.size(); i++) {
/* 247 */           ((b)this.e.get(i)).a();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public List<b> b() {
/* 253 */       return this.e;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */