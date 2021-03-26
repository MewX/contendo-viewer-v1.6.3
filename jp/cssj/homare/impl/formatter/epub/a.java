/*     */ package jp.cssj.homare.impl.formatter.epub;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.Map;
/*     */ import jp.cssj.d.a.f;
/*     */ import jp.cssj.e.e.d;
/*     */ import jp.cssj.homare.xml.c;
/*     */ import jp.cssj.homare.xml.g;
/*     */ import org.xml.sax.Attributes;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class a
/*     */   extends c
/*     */ {
/* 258 */   final AttributesImpl a = new AttributesImpl();
/*     */   final f b;
/*     */   final Map<URI, f> c;
/*     */   final URI d;
/*     */   
/*     */   a(g handler, f item, Map<URI, f> fullPathToItem) {
/* 264 */     super(handler);
/* 265 */     this.b = item;
/* 266 */     this.d = URI.create(item.d);
/* 267 */     this.c = fullPathToItem;
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 271 */     if (lName.equals("body")) {
/* 272 */       super.startElement(uri, lName, qName, atts);
/*     */       
/* 274 */       this.a.clear();
/* 275 */       this.a.addAttribute("", "id", "id", "CDATA", this.b.d);
/* 276 */       this.a.addAttribute("", "name", "name", "CDATA", "x-epub-" + this.b.d);
/* 277 */       super.startElement(uri, "a", "a", this.a);
/* 278 */       endElement(uri, "a", "a"); return;
/*     */     } 
/* 280 */     if (lName.equals("a")) {
/* 281 */       int href = atts.getIndex("href");
/* 282 */       if (href != -1) {
/* 283 */         String ref = atts.getValue(href);
/*     */         try {
/* 285 */           URI fullPath = d.a("UTF-8", this.d, ref);
/* 286 */           f item = this.c.get(fullPath);
/* 287 */           this.a.setAttributes(atts);
/* 288 */           if (item != null) {
/* 289 */             atts = this.a;
/* 290 */             this.a.setValue(href, "#x-epub-" + item.d);
/*     */           } 
/* 292 */         } catch (URISyntaxException e) {
/* 293 */           throw new SAXException(e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 297 */     super.startElement(uri, lName, qName, atts);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/formatter/epub/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */