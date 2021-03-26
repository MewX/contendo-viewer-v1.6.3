/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.util.Hashtable;
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
/*     */ public class AttributesImplSerializer
/*     */   extends AttributesImpl
/*     */ {
/*  42 */   private Hashtable m_indexFromQName = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MAX = 12;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int MAXMinus1 = 11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex(String qname) {
/*     */     int j;
/*  66 */     if (getLength() < 12) {
/*     */ 
/*     */ 
/*     */       
/*  70 */       j = super.getIndex(qname);
/*  71 */       return j;
/*     */     } 
/*     */ 
/*     */     
/*  75 */     Integer i = (Integer)this.m_indexFromQName.get(qname);
/*  76 */     if (i == null) {
/*  77 */       j = -1;
/*     */     } else {
/*  79 */       j = i.intValue();
/*  80 */     }  return j;
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
/*     */   public void addAttribute(String uri, String local, String qname, String type, String val) {
/* 101 */     int index = getLength();
/* 102 */     super.addAttribute(uri, local, qname, type, val);
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (index < 11) {
/*     */       return;
/*     */     }
/*     */     
/* 110 */     if (index == 11) {
/*     */       
/* 112 */       switchOverToHash(12);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 117 */       Integer i = new Integer(index);
/* 118 */       this.m_indexFromQName.put(qname, i);
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
/*     */   
/*     */   private void switchOverToHash(int numAtts) {
/* 133 */     for (int index = 0; index < numAtts; index++) {
/*     */       
/* 135 */       String qName = getQName(index);
/* 136 */       Integer i = new Integer(index);
/* 137 */       this.m_indexFromQName.put(qName, i);
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
/*     */   public void clear() {
/* 149 */     int len = getLength();
/* 150 */     super.clear();
/* 151 */     if (12 <= len)
/*     */     {
/*     */ 
/*     */       
/* 155 */       this.m_indexFromQName.clear();
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
/*     */ 
/*     */   
/*     */   public void setAttributes(Attributes atts) {
/* 171 */     super.setAttributes(atts);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     int numAtts = atts.getLength();
/* 177 */     if (12 <= numAtts)
/* 178 */       switchOverToHash(numAtts); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/AttributesImplSerializer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */