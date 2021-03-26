/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.serialize.SerializerUtils;
/*     */ import org.apache.xalan.transformer.ClonerToResultTree;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemCopy
/*     */   extends ElemUse
/*     */ {
/*     */   public int getXSLToken() {
/*  53 */     return 9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  63 */     return "copy";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/*  91 */     XPathContext xctxt = transformer.getXPathContext();
/*     */ 
/*     */ 
/*     */     
/*  95 */     try { int sourceNode = xctxt.getCurrentNode();
/*  96 */       xctxt.pushCurrentNode(sourceNode);
/*  97 */       DTM dtm = xctxt.getDTM(sourceNode);
/*  98 */       short nodeType = dtm.getNodeType(sourceNode);
/*     */       
/* 100 */       if (9 != nodeType && 11 != nodeType)
/*     */       
/* 102 */       { SerializationHandler rthandler = transformer.getSerializationHandler();
/*     */         
/* 104 */         if (TransformerImpl.S_DEBUG) {
/* 105 */           transformer.getTraceManager().fireTraceEvent(this);
/*     */         }
/*     */         
/* 108 */         ClonerToResultTree.cloneToResultTree(sourceNode, nodeType, dtm, rthandler, false);
/*     */ 
/*     */         
/* 111 */         if (1 == nodeType) {
/*     */           
/* 113 */           super.execute(transformer);
/* 114 */           SerializerUtils.processNSDecls(rthandler, sourceNode, nodeType, dtm);
/* 115 */           transformer.executeChildTemplates(this, true);
/*     */           
/* 117 */           String ns = dtm.getNamespaceURI(sourceNode);
/* 118 */           String localName = dtm.getLocalName(sourceNode);
/* 119 */           transformer.getResultTreeHandler().endElement(ns, localName, dtm.getNodeName(sourceNode));
/*     */         } 
/*     */         
/* 122 */         if (TransformerImpl.S_DEBUG) {
/* 123 */           transformer.getTraceManager().fireTraceEndEvent(this);
/*     */         } }
/*     */       else
/*     */       
/* 127 */       { if (TransformerImpl.S_DEBUG) {
/* 128 */           transformer.getTraceManager().fireTraceEvent(this);
/*     */         }
/* 130 */         super.execute(transformer);
/* 131 */         transformer.executeChildTemplates(this, true);
/*     */         
/* 133 */         if (TransformerImpl.S_DEBUG)
/* 134 */           transformer.getTraceManager().fireTraceEndEvent(this);  }  } catch (SAXException se)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 139 */       throw new TransformerException(se); }
/*     */     
/*     */     finally
/*     */     
/* 143 */     { xctxt.popCurrentNode(); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemCopy.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */