/*     */ package org.apache.xalan.lib;
/*     */ 
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.extensions.ExpressionContext;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xpath.NodeSet;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.Text;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExsltDynamic
/*     */   extends ExsltBase
/*     */ {
/*     */   public static final String EXSL_URI = "http://exslt.org/common";
/*     */   
/*     */   public static double max(ExpressionContext myContext, NodeList nl, String expr) throws SAXNotSupportedException {
/* 101 */     XPathContext xctxt = null;
/* 102 */     if (myContext instanceof XPathContext.XPathExpressionContext) {
/* 103 */       xctxt = ((XPathContext.XPathExpressionContext)myContext).getXPathContext();
/*     */     } else {
/* 105 */       throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[] { myContext }));
/*     */     } 
/* 107 */     if (expr == null || expr.length() == 0) {
/* 108 */       return Double.NaN;
/*     */     }
/* 110 */     NodeSetDTM contextNodes = new NodeSetDTM(nl, xctxt);
/* 111 */     xctxt.pushContextNodeList((DTMIterator)contextNodes);
/*     */     
/* 113 */     double maxValue = -1.7976931348623157E308D;
/* 114 */     for (int i = 0; i < contextNodes.getLength(); i++) {
/*     */       
/* 116 */       int contextNode = contextNodes.item(i);
/* 117 */       xctxt.pushCurrentNode(contextNode);
/*     */       
/* 119 */       double result = 0.0D;
/*     */ 
/*     */       
/* 122 */       try { XPath dynamicXPath = new XPath(expr, xctxt.getSAXLocator(), xctxt.getNamespaceContext(), 0);
/*     */ 
/*     */         
/* 125 */         result = dynamicXPath.execute(xctxt, contextNode, xctxt.getNamespaceContext()).num(); } catch (TransformerException e)
/*     */       
/*     */       { 
/*     */         
/* 129 */         xctxt.popCurrentNode();
/* 130 */         xctxt.popContextNodeList();
/* 131 */         return Double.NaN; }
/*     */ 
/*     */       
/* 134 */       xctxt.popCurrentNode();
/*     */       
/* 136 */       if (result > maxValue) {
/* 137 */         maxValue = result;
/*     */       }
/*     */     } 
/* 140 */     xctxt.popContextNodeList();
/* 141 */     return maxValue;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double min(ExpressionContext myContext, NodeList nl, String expr) throws SAXNotSupportedException {
/* 182 */     XPathContext xctxt = null;
/* 183 */     if (myContext instanceof XPathContext.XPathExpressionContext) {
/* 184 */       xctxt = ((XPathContext.XPathExpressionContext)myContext).getXPathContext();
/*     */     } else {
/* 186 */       throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[] { myContext }));
/*     */     } 
/* 188 */     if (expr == null || expr.length() == 0) {
/* 189 */       return Double.NaN;
/*     */     }
/* 191 */     NodeSetDTM contextNodes = new NodeSetDTM(nl, xctxt);
/* 192 */     xctxt.pushContextNodeList((DTMIterator)contextNodes);
/*     */     
/* 194 */     double minValue = Double.MAX_VALUE;
/* 195 */     for (int i = 0; i < nl.getLength(); i++) {
/*     */       
/* 197 */       int contextNode = contextNodes.item(i);
/* 198 */       xctxt.pushCurrentNode(contextNode);
/*     */       
/* 200 */       double result = 0.0D;
/*     */ 
/*     */       
/* 203 */       try { XPath dynamicXPath = new XPath(expr, xctxt.getSAXLocator(), xctxt.getNamespaceContext(), 0);
/*     */ 
/*     */         
/* 206 */         result = dynamicXPath.execute(xctxt, contextNode, xctxt.getNamespaceContext()).num(); } catch (TransformerException e)
/*     */       
/*     */       { 
/*     */         
/* 210 */         xctxt.popCurrentNode();
/* 211 */         xctxt.popContextNodeList();
/* 212 */         return Double.NaN; }
/*     */ 
/*     */       
/* 215 */       xctxt.popCurrentNode();
/*     */       
/* 217 */       if (result < minValue) {
/* 218 */         minValue = result;
/*     */       }
/*     */     } 
/* 221 */     xctxt.popContextNodeList();
/* 222 */     return minValue;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double sum(ExpressionContext myContext, NodeList nl, String expr) throws SAXNotSupportedException {
/* 262 */     XPathContext xctxt = null;
/* 263 */     if (myContext instanceof XPathContext.XPathExpressionContext) {
/* 264 */       xctxt = ((XPathContext.XPathExpressionContext)myContext).getXPathContext();
/*     */     } else {
/* 266 */       throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[] { myContext }));
/*     */     } 
/* 268 */     if (expr == null || expr.length() == 0) {
/* 269 */       return Double.NaN;
/*     */     }
/* 271 */     NodeSetDTM contextNodes = new NodeSetDTM(nl, xctxt);
/* 272 */     xctxt.pushContextNodeList((DTMIterator)contextNodes);
/*     */     
/* 274 */     double sum = 0.0D;
/* 275 */     for (int i = 0; i < nl.getLength(); i++) {
/*     */       
/* 277 */       int contextNode = contextNodes.item(i);
/* 278 */       xctxt.pushCurrentNode(contextNode);
/*     */       
/* 280 */       double result = 0.0D;
/*     */ 
/*     */       
/* 283 */       try { XPath dynamicXPath = new XPath(expr, xctxt.getSAXLocator(), xctxt.getNamespaceContext(), 0);
/*     */ 
/*     */         
/* 286 */         result = dynamicXPath.execute(xctxt, contextNode, xctxt.getNamespaceContext()).num(); } catch (TransformerException e)
/*     */       
/*     */       { 
/*     */         
/* 290 */         xctxt.popCurrentNode();
/* 291 */         xctxt.popContextNodeList();
/* 292 */         return Double.NaN; }
/*     */ 
/*     */       
/* 295 */       xctxt.popCurrentNode();
/*     */       
/* 297 */       sum += result;
/*     */     } 
/*     */ 
/*     */     
/* 301 */     xctxt.popContextNodeList();
/* 302 */     return sum;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList map(ExpressionContext myContext, NodeList nl, String expr) throws SAXNotSupportedException {
/* 364 */     XPathContext xctxt = null;
/* 365 */     Document lDoc = null;
/*     */     
/* 367 */     if (myContext instanceof XPathContext.XPathExpressionContext) {
/* 368 */       xctxt = ((XPathContext.XPathExpressionContext)myContext).getXPathContext();
/*     */     } else {
/* 370 */       throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[] { myContext }));
/*     */     } 
/* 372 */     if (expr == null || expr.length() == 0) {
/* 373 */       return (NodeList)new NodeSet();
/*     */     }
/* 375 */     NodeSetDTM contextNodes = new NodeSetDTM(nl, xctxt);
/* 376 */     xctxt.pushContextNodeList((DTMIterator)contextNodes);
/*     */     
/* 378 */     NodeSet resultSet = new NodeSet();
/* 379 */     resultSet.setShouldCacheNodes(true);
/*     */     
/* 381 */     for (int i = 0; i < nl.getLength(); i++) {
/*     */       
/* 383 */       int contextNode = contextNodes.item(i);
/* 384 */       xctxt.pushCurrentNode(contextNode);
/*     */       
/* 386 */       XObject object = null;
/*     */ 
/*     */       
/* 389 */       try { XPath dynamicXPath = new XPath(expr, xctxt.getSAXLocator(), xctxt.getNamespaceContext(), 0);
/*     */ 
/*     */         
/* 392 */         object = dynamicXPath.execute(xctxt, contextNode, xctxt.getNamespaceContext());
/*     */         
/* 394 */         if (object instanceof XNodeSet)
/*     */         
/* 396 */         { NodeList nodelist = null;
/* 397 */           nodelist = ((XNodeSet)object).nodelist();
/*     */           
/* 399 */           for (int k = 0; k < nodelist.getLength(); k++) {
/*     */             
/* 401 */             Node n = nodelist.item(k);
/* 402 */             if (!resultSet.contains(n)) {
/* 403 */               resultSet.addNode(n);
/*     */             }
/*     */           }  }
/*     */         else
/*     */         
/* 408 */         { if (lDoc == null) {
/*     */             
/* 410 */             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/* 411 */             dbf.setNamespaceAware(true);
/* 412 */             DocumentBuilder db = dbf.newDocumentBuilder();
/* 413 */             lDoc = db.newDocument();
/*     */           } 
/*     */           
/* 416 */           Element element = null;
/* 417 */           if (object instanceof org.apache.xpath.objects.XNumber) {
/* 418 */             element = lDoc.createElementNS("http://exslt.org/common", "exsl:number");
/* 419 */           } else if (object instanceof org.apache.xpath.objects.XBoolean) {
/* 420 */             element = lDoc.createElementNS("http://exslt.org/common", "exsl:boolean");
/*     */           } else {
/* 422 */             element = lDoc.createElementNS("http://exslt.org/common", "exsl:string");
/*     */           } 
/* 424 */           Text textNode = lDoc.createTextNode(object.str());
/* 425 */           element.appendChild(textNode);
/* 426 */           resultSet.addNode(element); }  } catch (Exception e)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 431 */         xctxt.popCurrentNode();
/* 432 */         xctxt.popContextNodeList();
/* 433 */         return (NodeList)new NodeSet(); }
/*     */ 
/*     */       
/* 436 */       xctxt.popCurrentNode();
/*     */     } 
/*     */ 
/*     */     
/* 440 */     xctxt.popContextNodeList();
/* 441 */     return (NodeList)resultSet;
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
/*     */   public static XObject evaluate(ExpressionContext myContext, String xpathExpr) throws SAXNotSupportedException {
/* 463 */     if (myContext instanceof XPathContext.XPathExpressionContext) {
/*     */       
/* 465 */       XPathContext xctxt = null;
/*     */ 
/*     */       
/* 468 */       try { xctxt = ((XPathContext.XPathExpressionContext)myContext).getXPathContext();
/* 469 */         XPath dynamicXPath = new XPath(xpathExpr, xctxt.getSAXLocator(), xctxt.getNamespaceContext(), 0);
/*     */ 
/*     */ 
/*     */         
/* 473 */         return dynamicXPath.execute(xctxt, myContext.getContextNode(), xctxt.getNamespaceContext()); } catch (TransformerException e)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 478 */         return (XObject)new XNodeSet(xctxt.getDTMManager()); }
/*     */     
/*     */     } 
/*     */     
/* 482 */     throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[] { myContext }));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList closure(ExpressionContext myContext, NodeList nl, String expr) throws SAXNotSupportedException {
/*     */     NodeSet nodeSet1;
/* 531 */     XPathContext xctxt = null;
/* 532 */     if (myContext instanceof XPathContext.XPathExpressionContext) {
/* 533 */       xctxt = ((XPathContext.XPathExpressionContext)myContext).getXPathContext();
/*     */     } else {
/* 535 */       throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[] { myContext }));
/*     */     } 
/* 537 */     if (expr == null || expr.length() == 0) {
/* 538 */       return (NodeList)new NodeSet();
/*     */     }
/* 540 */     NodeSet closureSet = new NodeSet();
/* 541 */     closureSet.setShouldCacheNodes(true);
/*     */     
/* 543 */     NodeList iterationList = nl;
/*     */ 
/*     */     
/*     */     do {
/* 547 */       NodeSet iterationSet = new NodeSet();
/*     */       
/* 549 */       NodeSetDTM contextNodes = new NodeSetDTM(iterationList, xctxt);
/* 550 */       xctxt.pushContextNodeList((DTMIterator)contextNodes);
/*     */       
/* 552 */       for (int i = 0; i < iterationList.getLength(); i++) {
/*     */         
/* 554 */         int contextNode = contextNodes.item(i);
/* 555 */         xctxt.pushCurrentNode(contextNode);
/*     */         
/* 557 */         XObject object = null;
/*     */ 
/*     */         
/* 560 */         try { XPath dynamicXPath = new XPath(expr, xctxt.getSAXLocator(), xctxt.getNamespaceContext(), 0);
/*     */ 
/*     */           
/* 563 */           object = dynamicXPath.execute(xctxt, contextNode, xctxt.getNamespaceContext());
/*     */           
/* 565 */           if (object instanceof XNodeSet)
/*     */           
/* 567 */           { NodeList nodelist = null;
/* 568 */             nodelist = ((XNodeSet)object).nodelist();
/*     */             
/* 570 */             for (int k = 0; k < nodelist.getLength(); k++) {
/*     */               
/* 572 */               Node n = nodelist.item(k);
/* 573 */               if (!iterationSet.contains(n)) {
/* 574 */                 iterationSet.addNode(n);
/*     */               }
/*     */             }  }
/*     */           else
/*     */           
/* 579 */           { xctxt.popCurrentNode();
/* 580 */             xctxt.popContextNodeList();
/* 581 */             return (NodeList)new NodeSet(); }  } catch (TransformerException e)
/*     */         
/*     */         { 
/*     */ 
/*     */           
/* 586 */           xctxt.popCurrentNode();
/* 587 */           xctxt.popContextNodeList();
/* 588 */           return (NodeList)new NodeSet(); }
/*     */ 
/*     */         
/* 591 */         xctxt.popCurrentNode();
/*     */       } 
/*     */ 
/*     */       
/* 595 */       xctxt.popContextNodeList();
/*     */       
/* 597 */       nodeSet1 = iterationSet;
/*     */       
/* 599 */       for (int j = 0; j < nodeSet1.getLength(); j++) {
/*     */         
/* 601 */         Node n = nodeSet1.item(j);
/* 602 */         if (!closureSet.contains(n)) {
/* 603 */           closureSet.addNode(n);
/*     */         }
/*     */       } 
/* 606 */     } while (nodeSet1.getLength() > 0);
/*     */     
/* 608 */     return (NodeList)closureSet;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/ExsltDynamic.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */