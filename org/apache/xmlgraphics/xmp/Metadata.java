/*     */ package org.apache.xmlgraphics.xmp;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.xmlgraphics.util.QName;
/*     */ import org.apache.xmlgraphics.util.XMLizable;
/*     */ import org.apache.xmlgraphics.xmp.merge.MergeRuleSet;
/*     */ import org.apache.xmlgraphics.xmp.merge.PropertyMerger;
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
/*     */ public class Metadata
/*     */   implements XMLizable, PropertyAccess
/*     */ {
/*  42 */   private Map properties = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   public void setProperty(XMPProperty prop) {
/*  46 */     this.properties.put(prop.getName(), prop);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPProperty getProperty(String uri, String localName) {
/*  51 */     return getProperty(new QName(uri, localName));
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPProperty getProperty(QName name) {
/*  56 */     XMPProperty prop = (XMPProperty)this.properties.get(name);
/*  57 */     return prop;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPProperty removeProperty(QName name) {
/*  62 */     return (XMPProperty)this.properties.remove(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPProperty getValueProperty() {
/*  67 */     return getProperty(XMPConstants.RDF_VALUE);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPropertyCount() {
/*  72 */     return this.properties.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/*  77 */     return this.properties.keySet().iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mergeInto(Metadata target, List<Class<?>> exclude) {
/*  86 */     XMPSchemaRegistry registry = XMPSchemaRegistry.getInstance();
/*  87 */     Iterator<XMPProperty> iter = this.properties.values().iterator();
/*  88 */     while (iter.hasNext()) {
/*  89 */       XMPProperty prop = iter.next();
/*  90 */       XMPSchema schema = registry.getSchema(prop.getNamespace());
/*  91 */       if (!exclude.contains(schema.getClass())) {
/*  92 */         MergeRuleSet rules = schema.getDefaultMergeRuleSet();
/*  93 */         PropertyMerger merger = rules.getPropertyMergerFor(prop);
/*  94 */         merger.merge(prop, target);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void toSAX(ContentHandler handler) throws SAXException {
/* 101 */     AttributesImpl atts = new AttributesImpl();
/* 102 */     handler.startPrefixMapping("x", "adobe:ns:meta/");
/* 103 */     handler.startElement("adobe:ns:meta/", "xmpmeta", "x:xmpmeta", atts);
/* 104 */     handler.startPrefixMapping("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
/* 105 */     handler.startElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "RDF", "rdf:RDF", atts);
/*     */     
/* 107 */     Set<String> namespaces = new HashSet();
/* 108 */     Iterator<QName> iter = this.properties.keySet().iterator();
/* 109 */     while (iter.hasNext()) {
/* 110 */       QName n = iter.next();
/* 111 */       namespaces.add(n.getNamespaceURI());
/*     */     } 
/*     */     
/* 114 */     iter = (Iterator)namespaces.iterator();
/* 115 */     while (iter.hasNext()) {
/* 116 */       String ns = (String)iter.next();
/* 117 */       XMPSchema schema = XMPSchemaRegistry.getInstance().getSchema(ns);
/* 118 */       String prefix = (schema != null) ? schema.getPreferredPrefix() : null;
/*     */       
/* 120 */       boolean first = true;
/* 121 */       boolean empty = true;
/*     */       
/* 123 */       Iterator<XMPProperty> props = this.properties.values().iterator();
/* 124 */       while (props.hasNext()) {
/* 125 */         XMPProperty prop = props.next();
/* 126 */         if (prop.getName().getNamespaceURI().equals(ns)) {
/* 127 */           if (first) {
/* 128 */             if (prefix == null) {
/* 129 */               prefix = prop.getName().getPrefix();
/*     */             }
/* 131 */             atts.clear();
/* 132 */             atts.addAttribute("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "about", "rdf:about", "CDATA", "");
/*     */             
/* 134 */             if (prefix != null) {
/* 135 */               handler.startPrefixMapping(prefix, ns);
/*     */             }
/* 137 */             handler.startElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "Description", "rdf:Description", atts);
/*     */             
/* 139 */             empty = false;
/* 140 */             first = false;
/*     */           } 
/* 142 */           prop.toSAX(handler);
/*     */         } 
/*     */       } 
/* 145 */       if (!empty) {
/* 146 */         handler.endElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "Description", "rdf:Description");
/* 147 */         if (prefix != null) {
/* 148 */           handler.endPrefixMapping(prefix);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     handler.endElement("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "RDF", "rdf:RDF");
/* 154 */     handler.endPrefixMapping("rdf");
/* 155 */     handler.endElement("adobe:ns:meta/", "xmpmeta", "x:xmpmeta");
/* 156 */     handler.endPrefixMapping("x");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/Metadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */