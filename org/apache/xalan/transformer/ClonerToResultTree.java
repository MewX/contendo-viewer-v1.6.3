/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.serialize.SerializerUtils;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClonerToResultTree
/*     */ {
/*     */   public static void cloneToResultTree(int node, int nodeType, DTM dtm, SerializationHandler rth, boolean shouldCloneAttributes) throws TransformerException {
/*     */     
/*     */     try { String ns;
/*     */       XMLString xstr;
/*     */       String localName;
/* 138 */       switch (nodeType) {
/*     */         
/*     */         case 3:
/* 141 */           dtm.dispatchCharactersEvents(node, (ContentHandler)rth, false);
/*     */ 
/*     */ 
/*     */         
/*     */         case 9:
/*     */         case 11:
/*     */           return;
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 152 */           ns = dtm.getNamespaceURI(node);
/* 153 */           if (ns == null) ns = ""; 
/* 154 */           localName = dtm.getLocalName(node);
/*     */ 
/*     */ 
/*     */           
/* 158 */           rth.startElement(ns, localName, dtm.getNodeNameX(node));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 163 */           if (shouldCloneAttributes) {
/*     */             
/* 165 */             SerializerUtils.addAttributes(rth, node);
/* 166 */             SerializerUtils.processNSDecls(rth, node, nodeType, dtm);
/*     */           } 
/*     */ 
/*     */         
/*     */         case 4:
/* 171 */           rth.startCDATA();
/* 172 */           dtm.dispatchCharactersEvents(node, (ContentHandler)rth, false);
/* 173 */           rth.endCDATA();
/*     */         
/*     */         case 2:
/* 176 */           SerializerUtils.addAttribute(rth, node);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 13:
/* 183 */           SerializerUtils.processNSDecls(rth, node, 13, dtm);
/*     */         
/*     */         case 8:
/* 186 */           xstr = dtm.getStringValue(node);
/* 187 */           xstr.dispatchAsComment((LexicalHandler)rth);
/*     */         
/*     */         case 5:
/* 190 */           rth.entityReference(dtm.getNodeNameX(node));
/*     */ 
/*     */ 
/*     */         
/*     */         case 7:
/* 195 */           rth.processingInstruction(dtm.getNodeNameX(node), dtm.getNodeValue(node));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 201 */       throw new TransformerException("Can't clone node: " + dtm.getNodeName(node)); } catch (SAXException se)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 207 */       throw new TransformerException(se); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/ClonerToResultTree.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */