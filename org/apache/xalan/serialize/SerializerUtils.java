/*     */ package org.apache.xalan.serialize;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.serializer.NamespaceMappings;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SerializerUtils
/*     */ {
/*     */   public static void addAttribute(SerializationHandler handler, int attr) throws TransformerException {
/*  56 */     TransformerImpl transformer = (TransformerImpl)handler.getTransformer();
/*     */     
/*  58 */     DTM dtm = transformer.getXPathContext().getDTM(attr);
/*     */     
/*  60 */     if (isDefinedNSDecl(handler, attr, dtm)) {
/*     */       return;
/*     */     }
/*  63 */     String ns = dtm.getNamespaceURI(attr);
/*     */     
/*  65 */     if (ns == null) {
/*  66 */       ns = "";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  71 */     try { handler.addAttribute(ns, dtm.getLocalName(attr), dtm.getNodeName(attr), "CDATA", dtm.getNodeValue(attr)); } catch (SAXException sAXException) {}
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
/*     */   public static void addAttributes(SerializationHandler handler, int src) throws TransformerException {
/*  95 */     TransformerImpl transformer = (TransformerImpl)handler.getTransformer();
/*     */     
/*  97 */     DTM dtm = transformer.getXPathContext().getDTM(src);
/*     */     
/*  99 */     int node = dtm.getFirstAttribute(src);
/* 100 */     for (; -1 != node; 
/* 101 */       node = dtm.getNextAttribute(node))
/*     */     {
/* 103 */       addAttribute(handler, node);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void outputResultTreeFragment(SerializationHandler handler, XObject obj, XPathContext support) throws SAXException {
/* 123 */     int doc = obj.rtf();
/* 124 */     DTM dtm = support.getDTM(doc);
/*     */     
/* 126 */     if (null != dtm) {
/*     */       
/* 128 */       int n = dtm.getFirstChild(doc);
/* 129 */       for (; -1 != n; 
/* 130 */         n = dtm.getNextSibling(n)) {
/*     */         
/* 132 */         handler.flushPending();
/*     */ 
/*     */         
/* 135 */         if (dtm.getNamespaceURI(n) == null)
/* 136 */           handler.startPrefixMapping("", ""); 
/* 137 */         dtm.dispatchToEvents(n, (ContentHandler)handler);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processNSDecls(SerializationHandler handler, int src, int type, DTM dtm) throws TransformerException {
/*     */     
/* 164 */     try { if (type == 1)
/*     */       
/* 166 */       { int namespace = dtm.getFirstNamespaceNode(src, true);
/* 167 */         for (; -1 != namespace; 
/* 168 */           namespace = dtm.getNextNamespaceNode(src, namespace, true))
/*     */         {
/*     */ 
/*     */           
/* 172 */           String prefix = dtm.getNodeNameX(namespace);
/* 173 */           String desturi = handler.getNamespaceURIFromPrefix(prefix);
/*     */           
/* 175 */           String srcURI = dtm.getNodeValue(namespace);
/*     */           
/* 177 */           if (!srcURI.equalsIgnoreCase(desturi))
/*     */           {
/* 179 */             handler.startPrefixMapping(prefix, srcURI, false);
/*     */           }
/*     */         }
/*     */          }
/* 183 */       else if (type == 13)
/*     */       
/* 185 */       { String prefix = dtm.getNodeNameX(src);
/*     */         
/* 187 */         String desturi = handler.getNamespaceURIFromPrefix(prefix);
/* 188 */         String srcURI = dtm.getNodeValue(src);
/*     */         
/* 190 */         if (!srcURI.equalsIgnoreCase(desturi))
/*     */         {
/* 192 */           handler.startPrefixMapping(prefix, srcURI, false); }  }  } catch (SAXException se)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 198 */       throw new TransformerException(se); }
/*     */   
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
/*     */   public static boolean isDefinedNSDecl(SerializationHandler serializer, int attr, DTM dtm) {
/* 218 */     if (13 == dtm.getNodeType(attr)) {
/*     */ 
/*     */ 
/*     */       
/* 222 */       String prefix = dtm.getNodeNameX(attr);
/* 223 */       String uri = serializer.getNamespaceURIFromPrefix(prefix);
/*     */ 
/*     */       
/* 226 */       if (null != uri && uri.equals(dtm.getStringValue(attr))) {
/* 227 */         return true;
/*     */       }
/*     */     } 
/* 230 */     return false;
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
/*     */   public static void ensureNamespaceDeclDeclared(SerializationHandler handler, DTM dtm, int namespace) throws SAXException {
/* 254 */     String uri = dtm.getNodeValue(namespace);
/* 255 */     String prefix = dtm.getNodeNameX(namespace);
/*     */     
/* 257 */     if (uri != null && uri.length() > 0 && null != prefix) {
/*     */ 
/*     */       
/* 260 */       NamespaceMappings ns = handler.getNamespaceMappings();
/* 261 */       if (ns != null) {
/*     */ 
/*     */         
/* 264 */         String foundURI = ns.lookupNamespace(prefix);
/* 265 */         if (null == foundURI || !foundURI.equals(uri))
/*     */         {
/* 267 */           handler.startPrefixMapping(prefix, uri, false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/serialize/SerializerUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */