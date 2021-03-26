/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.axes.OneStepIterator;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XObjectFactory
/*     */ {
/*     */   public static XObject create(Object val) {
/*     */     XObject xObject;
/*  46 */     if (val instanceof XObject) {
/*     */       
/*  48 */       xObject = (XObject)val;
/*     */     }
/*  50 */     else if (val instanceof String) {
/*     */       
/*  52 */       xObject = new XString((String)val);
/*     */     }
/*  54 */     else if (val instanceof Boolean) {
/*     */       
/*  56 */       xObject = new XBoolean((Boolean)val);
/*     */     }
/*  58 */     else if (val instanceof Double) {
/*     */       
/*  60 */       xObject = new XNumber((Double)val);
/*     */     }
/*     */     else {
/*     */       
/*  64 */       xObject = new XObject(val);
/*     */     } 
/*     */     
/*  67 */     return xObject;
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
/*     */   public static XObject create(Object val, XPathContext xctxt) {
/*     */     XObject xObject;
/*  85 */     if (val instanceof XObject) {
/*     */       
/*  87 */       xObject = (XObject)val;
/*     */     }
/*  89 */     else if (val instanceof String) {
/*     */       
/*  91 */       xObject = new XString((String)val);
/*     */     }
/*  93 */     else if (val instanceof Boolean) {
/*     */       
/*  95 */       xObject = new XBoolean((Boolean)val);
/*     */     }
/*  97 */     else if (val instanceof Number) {
/*     */       
/*  99 */       xObject = new XNumber((Number)val);
/*     */     }
/* 101 */     else if (val instanceof DTM) {
/*     */       
/* 103 */       DTM dtm = (DTM)val;
/*     */ 
/*     */       
/* 106 */       try { int dtmRoot = dtm.getDocument();
/* 107 */         DTMAxisIterator iter = dtm.getAxisIterator(13);
/* 108 */         iter.setStartNode(dtmRoot);
/* 109 */         OneStepIterator oneStepIterator = new OneStepIterator(iter, 13);
/* 110 */         oneStepIterator.setRoot(dtmRoot, xctxt);
/* 111 */         XNodeSet xNodeSet = new XNodeSet((DTMIterator)oneStepIterator); } catch (Exception ex)
/*     */       
/*     */       { 
/*     */         
/* 115 */         throw new WrappedRuntimeException(ex); }
/*     */ 
/*     */     
/* 118 */     } else if (val instanceof DTMAxisIterator) {
/*     */       
/* 120 */       DTMAxisIterator iter = (DTMAxisIterator)val;
/*     */ 
/*     */       
/* 123 */       try { OneStepIterator oneStepIterator = new OneStepIterator(iter, 13);
/* 124 */         oneStepIterator.setRoot(iter.getStartNode(), xctxt);
/* 125 */         XNodeSet xNodeSet = new XNodeSet((DTMIterator)oneStepIterator); } catch (Exception ex)
/*     */       
/*     */       { 
/*     */         
/* 129 */         throw new WrappedRuntimeException(ex); }
/*     */ 
/*     */     
/* 132 */     } else if (val instanceof DTMIterator) {
/*     */       
/* 134 */       XNodeSet xNodeSet = new XNodeSet((DTMIterator)val);
/*     */ 
/*     */     
/*     */     }
/* 138 */     else if (val instanceof Node) {
/*     */       
/* 140 */       XNodeSetForDOM xNodeSetForDOM = new XNodeSetForDOM((Node)val, (DTMManager)xctxt);
/*     */ 
/*     */     
/*     */     }
/* 144 */     else if (val instanceof NodeList) {
/*     */       
/* 146 */       XNodeSetForDOM xNodeSetForDOM = new XNodeSetForDOM((NodeList)val, xctxt);
/*     */     }
/* 148 */     else if (val instanceof NodeIterator) {
/*     */       
/* 150 */       XNodeSetForDOM xNodeSetForDOM = new XNodeSetForDOM((NodeIterator)val, xctxt);
/*     */     }
/*     */     else {
/*     */       
/* 154 */       xObject = new XObject(val);
/*     */     } 
/*     */     
/* 157 */     return xObject;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XObjectFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */