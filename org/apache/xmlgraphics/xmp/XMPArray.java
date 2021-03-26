/*     */ package org.apache.xmlgraphics.xmp;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMPArray
/*     */   extends XMPComplexValue
/*     */ {
/*     */   private XMPArrayType type;
/*  37 */   private List values = new ArrayList();
/*  38 */   private List xmllang = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMPArray(XMPArrayType type) {
/*  45 */     this.type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPArrayType getType() {
/*  50 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(int idx) {
/*  59 */     return this.values.get(idx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMPStructure getStructure(int idx) {
/*  69 */     return this.values.get(idx);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getSimpleValue() {
/*  74 */     if (this.values.size() == 1)
/*  75 */       return getValue(0); 
/*  76 */     if (this.values.size() > 1) {
/*  77 */       return getLangValue("x-default");
/*     */     }
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getParentLanguage(String lang) {
/*  84 */     if (lang == null) {
/*  85 */       return null;
/*     */     }
/*  87 */     int pos = lang.indexOf('-');
/*  88 */     if (pos > 0) {
/*  89 */       String parent = lang.substring(0, pos);
/*  90 */       return parent;
/*     */     } 
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLangValue(String lang) {
/* 101 */     String v = null;
/* 102 */     String valueForParentLanguage = null;
/* 103 */     for (int i = 0, c = this.values.size(); i < c; i++) {
/* 104 */       String l = this.xmllang.get(i);
/* 105 */       if ((l == null && lang == null) || (l != null && l.equals(lang))) {
/* 106 */         v = this.values.get(i).toString();
/*     */         break;
/*     */       } 
/* 109 */       if (l != null && lang != null) {
/*     */         
/* 111 */         String parent = getParentLanguage(l);
/* 112 */         if (parent != null && parent.equals(lang)) {
/* 113 */           valueForParentLanguage = this.values.get(i).toString();
/*     */         }
/*     */       } 
/*     */     } 
/* 117 */     if (lang != null && v == null && valueForParentLanguage != null)
/*     */     {
/* 119 */       v = valueForParentLanguage;
/*     */     }
/* 121 */     if (lang == null && v == null) {
/* 122 */       v = getLangValue("x-default");
/* 123 */       if (v == null && this.values.size() > 0) {
/* 124 */         v = getValue(0).toString();
/*     */       }
/*     */     } 
/* 127 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String removeLangValue(String lang) {
/* 136 */     if (lang == null || "".equals(lang)) {
/* 137 */       lang = "x-default";
/*     */     }
/* 139 */     for (int i = 0, c = this.values.size(); i < c; i++) {
/* 140 */       String l = this.xmllang.get(i);
/* 141 */       if (("x-default".equals(lang) && l == null) || lang.equals(l)) {
/* 142 */         String value = this.values.remove(i);
/* 143 */         this.xmllang.remove(i);
/* 144 */         return value;
/*     */       } 
/*     */     } 
/* 147 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Object value) {
/* 155 */     this.values.add(value);
/* 156 */     this.xmllang.add(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(String value) {
/* 165 */     int idx = this.values.indexOf(value);
/* 166 */     if (idx >= 0) {
/* 167 */       this.values.remove(idx);
/* 168 */       this.xmllang.remove(idx);
/* 169 */       return true;
/*     */     } 
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String value, String lang) {
/* 181 */     this.values.add(value);
/* 182 */     this.xmllang.add(lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 190 */     return this.values.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 198 */     return (getSize() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toObjectArray() {
/* 206 */     Object[] res = new Object[getSize()];
/* 207 */     for (int i = 0, c = res.length; i < c; i++) {
/* 208 */       res[i] = getValue(i);
/*     */     }
/* 210 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   public void toSAX(ContentHandler handler) throws SAXException {
/* 215 */     AttributesImpl atts = new AttributesImpl();
/* 216 */     handler.startElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", this.type.getName(), "rdf:" + this.type.getName(), atts);
/*     */     
/* 218 */     for (int i = 0, c = this.values.size(); i < c; i++) {
/* 219 */       String lang = this.xmllang.get(i);
/* 220 */       atts.clear();
/* 221 */       Object v = this.values.get(i);
/* 222 */       if (lang != null) {
/* 223 */         atts.addAttribute("http://www.w3.org/XML/1998/namespace", "lang", "xml:lang", "CDATA", lang);
/*     */       }
/* 225 */       if (v instanceof URI) {
/* 226 */         atts.addAttribute("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "resource", "rdf:resource", "CDATA", ((URI)v).toString());
/*     */       }
/*     */       
/* 229 */       handler.startElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "li", "rdf:li", atts);
/*     */       
/* 231 */       if (v instanceof XMPComplexValue) {
/* 232 */         ((XMPComplexValue)v).toSAX(handler);
/* 233 */       } else if (!(v instanceof URI)) {
/* 234 */         String value = this.values.get(i);
/* 235 */         char[] chars = value.toCharArray();
/* 236 */         handler.characters(chars, 0, chars.length);
/*     */       } 
/* 238 */       handler.endElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "li", "rdf:li");
/*     */     } 
/*     */     
/* 241 */     handler.endElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", this.type.getName(), "rdf:" + this.type.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 247 */     return "XMP array: " + this.type + ", " + getSize();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */