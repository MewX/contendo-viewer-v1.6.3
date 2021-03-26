/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.serialize.SerializerUtils;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.ref.DTMTreeWalker;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.xml.sax.ContentHandler;
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
/*     */ public class TreeWalker2Result
/*     */   extends DTMTreeWalker
/*     */ {
/*     */   TransformerImpl m_transformer;
/*     */   SerializationHandler m_handler;
/*     */   int m_startNode;
/*     */   
/*     */   public TreeWalker2Result(TransformerImpl transformer, SerializationHandler handler) {
/*  54 */     super((ContentHandler)handler, null);
/*     */     
/*  56 */     this.m_transformer = transformer;
/*  57 */     this.m_handler = handler;
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
/*     */   public void traverse(int pos) throws SAXException {
/*  69 */     this.m_dtm = this.m_transformer.getXPathContext().getDTM(pos);
/*  70 */     this.m_startNode = pos;
/*     */     
/*  72 */     super.traverse(pos);
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
/*     */   protected void endNode(int node) throws SAXException {
/*  85 */     super.endNode(node);
/*  86 */     if (1 == this.m_dtm.getNodeType(node))
/*     */     {
/*  88 */       this.m_transformer.getXPathContext().popCurrentNode();
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
/*     */   protected void startNode(int node) throws SAXException {
/* 103 */     XPathContext xcntxt = this.m_transformer.getXPathContext();
/*     */ 
/*     */ 
/*     */     
/* 107 */     try { if (1 == this.m_dtm.getNodeType(node))
/*     */       
/* 109 */       { xcntxt.pushCurrentNode(node);
/*     */         
/* 111 */         if (this.m_startNode != node)
/*     */         {
/* 113 */           super.startNode(node);
/*     */         }
/*     */         else
/*     */         {
/* 117 */           String elemName = this.m_dtm.getNodeName(node);
/* 118 */           String localName = this.m_dtm.getLocalName(node);
/* 119 */           String namespace = this.m_dtm.getNamespaceURI(node);
/*     */ 
/*     */ 
/*     */           
/* 123 */           this.m_handler.startElement(namespace, localName, elemName);
/* 124 */           boolean hasNSDecls = false;
/* 125 */           DTM dtm = this.m_dtm;
/* 126 */           int ns = dtm.getFirstNamespaceNode(node, true);
/* 127 */           for (; -1 != ns; ns = dtm.getNextNamespaceNode(node, ns, true))
/*     */           {
/* 129 */             SerializerUtils.ensureNamespaceDeclDeclared(this.m_handler, dtm, ns);
/*     */           }
/*     */ 
/*     */           
/* 133 */           int attr = dtm.getFirstAttribute(node);
/* 134 */           for (; -1 != attr; attr = dtm.getNextAttribute(attr))
/*     */           {
/* 136 */             SerializerUtils.addAttribute(this.m_handler, attr);
/*     */           }
/*     */         }
/*     */          }
/*     */       
/*     */       else
/*     */       
/* 143 */       { xcntxt.pushCurrentNode(node);
/* 144 */         super.startNode(node);
/* 145 */         xcntxt.popCurrentNode(); }  } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 150 */       throw new SAXException(te); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/TreeWalker2Result.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */