/*     */ package org.apache.xpath.functions;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.StringVector;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FuncId
/*     */   extends FunctionOneArg
/*     */ {
/*     */   private StringVector getNodesByID(XPathContext xctxt, int docContext, String refval, StringVector usedrefs, NodeSetDTM nodeSet, boolean mayBeMore) {
/*  57 */     if (null != refval) {
/*     */       
/*  59 */       String ref = null;
/*     */       
/*  61 */       StringTokenizer tokenizer = new StringTokenizer(refval);
/*  62 */       boolean hasMore = tokenizer.hasMoreTokens();
/*  63 */       DTM dtm = xctxt.getDTM(docContext);
/*     */       
/*  65 */       while (hasMore) {
/*     */         
/*  67 */         ref = tokenizer.nextToken();
/*  68 */         hasMore = tokenizer.hasMoreTokens();
/*     */         
/*  70 */         if (null != usedrefs && usedrefs.contains(ref)) {
/*     */           
/*  72 */           ref = null;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/*  77 */         int node = dtm.getElementById(ref);
/*     */         
/*  79 */         if (-1 != node) {
/*  80 */           nodeSet.addNodeInDocOrder(node, xctxt);
/*     */         }
/*  82 */         if (null != ref && (hasMore || mayBeMore)) {
/*     */           
/*  84 */           if (null == usedrefs) {
/*  85 */             usedrefs = new StringVector();
/*     */           }
/*  87 */           usedrefs.addElement(ref);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  92 */     return usedrefs;
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 106 */     int context = xctxt.getCurrentNode();
/* 107 */     DTM dtm = xctxt.getDTM(context);
/* 108 */     int docContext = dtm.getDocument();
/*     */     
/* 110 */     if (-1 == docContext) {
/* 111 */       error(xctxt, "ER_CONTEXT_HAS_NO_OWNERDOC", null);
/*     */     }
/* 113 */     XObject arg = this.m_arg0.execute(xctxt);
/* 114 */     int argType = arg.getType();
/* 115 */     XNodeSet nodes = new XNodeSet(xctxt.getDTMManager());
/* 116 */     NodeSetDTM nodeSet = nodes.mutableNodeset();
/*     */     
/* 118 */     if (4 == argType) {
/*     */       
/* 120 */       DTMIterator ni = arg.iter();
/* 121 */       StringVector usedrefs = null;
/* 122 */       int pos = ni.nextNode();
/*     */       
/* 124 */       while (-1 != pos)
/*     */       {
/* 126 */         DTM ndtm = ni.getDTM(pos);
/* 127 */         String refval = ndtm.getStringValue(pos).toString();
/*     */         
/* 129 */         pos = ni.nextNode();
/* 130 */         usedrefs = getNodesByID(xctxt, docContext, refval, usedrefs, nodeSet, (-1 != pos));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 135 */       if (-1 == argType)
/*     */       {
/* 137 */         return (XObject)nodes;
/*     */       }
/*     */ 
/*     */       
/* 141 */       String refval = arg.str();
/*     */       
/* 143 */       getNodesByID(xctxt, docContext, refval, null, nodeSet, false);
/*     */     } 
/*     */     
/* 146 */     return (XObject)nodes;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncId.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */