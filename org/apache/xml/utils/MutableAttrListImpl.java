/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.xml.sax.Attributes;
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
/*     */ public class MutableAttrListImpl
/*     */   extends AttributesImpl
/*     */   implements Serializable
/*     */ {
/*     */   public MutableAttrListImpl() {}
/*     */   
/*     */   public MutableAttrListImpl(Attributes atts) {
/*  53 */     super(atts);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(String uri, String localName, String qName, String type, String value) {
/*  77 */     if (null == uri) {
/*  78 */       uri = "";
/*     */     }
/*     */ 
/*     */     
/*  82 */     int index = getIndex(qName);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     if (index >= 0) {
/*  88 */       setAttribute(index, uri, localName, qName, type, value);
/*     */     } else {
/*  90 */       super.addAttribute(uri, localName, qName, type, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttributes(Attributes atts) {
/* 101 */     int nAtts = atts.getLength();
/*     */     
/* 103 */     for (int i = 0; i < nAtts; i++) {
/*     */       
/* 105 */       String uri = atts.getURI(i);
/*     */       
/* 107 */       if (null == uri) {
/* 108 */         uri = "";
/*     */       }
/* 110 */       String localName = atts.getLocalName(i);
/* 111 */       String qname = atts.getQName(i);
/* 112 */       int index = getIndex(uri, localName);
/*     */       
/* 114 */       if (index >= 0) {
/* 115 */         setAttribute(index, uri, localName, qname, atts.getType(i), atts.getValue(i));
/*     */       } else {
/*     */         
/* 118 */         addAttribute(uri, localName, qname, atts.getType(i), atts.getValue(i));
/*     */       } 
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
/*     */   public boolean contains(String name) {
/* 132 */     return (getValue(name) != null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/MutableAttrListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */