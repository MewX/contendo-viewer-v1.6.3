/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xml.utils.XMLChar;
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
/*     */ 
/*     */ public class ElemElement
/*     */   extends ElemUse
/*     */ {
/*  54 */   protected AVT m_name_avt = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(AVT v) {
/*  66 */     this.m_name_avt = v;
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
/*     */   public AVT getName() {
/*  79 */     return this.m_name_avt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   protected AVT m_namespace_avt = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNamespace(AVT v) {
/* 102 */     this.m_namespace_avt = v;
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
/*     */   public AVT getNamespace() {
/* 116 */     return this.m_namespace_avt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/* 127 */     super.compose(sroot);
/*     */     
/* 129 */     StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/* 130 */     Vector vnames = cstate.getVariableNames();
/* 131 */     if (null != this.m_name_avt)
/* 132 */       this.m_name_avt.fixupVariables(vnames, cstate.getGlobalsSize()); 
/* 133 */     if (null != this.m_namespace_avt) {
/* 134 */       this.m_namespace_avt.fixupVariables(vnames, cstate.getGlobalsSize());
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
/*     */   public int getXSLToken() {
/* 146 */     return 46;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 156 */     return "element";
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
/*     */   protected String resolvePrefix(SerializationHandler rhandler, String prefix, String nodeNamespace) throws TransformerException {
/* 182 */     return prefix;
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 202 */     if (TransformerImpl.S_DEBUG) {
/* 203 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/* 205 */     SerializationHandler rhandler = transformer.getSerializationHandler();
/* 206 */     XPathContext xctxt = transformer.getXPathContext();
/* 207 */     int sourceNode = xctxt.getCurrentNode();
/*     */ 
/*     */     
/* 210 */     String nodeName = (this.m_name_avt == null) ? null : this.m_name_avt.evaluate(xctxt, sourceNode, this);
/*     */     
/* 212 */     String prefix = null;
/* 213 */     String nodeNamespace = "";
/*     */ 
/*     */     
/* 216 */     if (nodeName != null && !this.m_name_avt.isSimple() && !XMLChar.isValidQName(nodeName)) {
/*     */       
/* 218 */       transformer.getMsgMgr().warn((SourceLocator)this, "WG_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "name", nodeName });
/*     */ 
/*     */ 
/*     */       
/* 222 */       nodeName = null;
/*     */     
/*     */     }
/* 225 */     else if (nodeName != null) {
/*     */       
/* 227 */       prefix = QName.getPrefixPart(nodeName);
/*     */       
/* 229 */       if (null != this.m_namespace_avt) {
/*     */         
/* 231 */         nodeNamespace = this.m_namespace_avt.evaluate(xctxt, sourceNode, this);
/* 232 */         if (null == nodeNamespace || (prefix != null && prefix.length() > 0 && nodeNamespace.length() == 0)) {
/*     */           
/* 234 */           transformer.getMsgMgr().error((SourceLocator)this, "ER_NULL_URI_NAMESPACE");
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 240 */           prefix = resolvePrefix(rhandler, prefix, nodeNamespace);
/* 241 */           if (null == prefix) {
/* 242 */             prefix = "";
/*     */           }
/* 244 */           if (prefix.length() > 0) {
/* 245 */             nodeName = prefix + ":" + QName.getLocalPart(nodeName);
/*     */           } else {
/* 247 */             nodeName = QName.getLocalPart(nodeName);
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */ 
/*     */ 
/*     */         
/*     */         try { 
/*     */ 
/*     */ 
/*     */           
/* 258 */           nodeNamespace = getNamespaceForPrefix(prefix);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 264 */           if (null == nodeNamespace && prefix.length() == 0)
/* 265 */           { nodeNamespace = ""; }
/* 266 */           else if (null == nodeNamespace)
/*     */           
/* 268 */           { transformer.getMsgMgr().warn((SourceLocator)this, "WG_COULD_NOT_RESOLVE_PREFIX", new Object[] { prefix });
/*     */ 
/*     */ 
/*     */             
/* 272 */             nodeName = null; }  } catch (Exception ex)
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */           
/* 278 */           transformer.getMsgMgr().warn((SourceLocator)this, "WG_COULD_NOT_RESOLVE_PREFIX", new Object[] { prefix });
/*     */ 
/*     */ 
/*     */           
/* 282 */           nodeName = null; }
/*     */       
/*     */       } 
/*     */     } 
/*     */     
/* 287 */     constructNode(nodeName, prefix, nodeNamespace, transformer);
/*     */     
/* 289 */     if (TransformerImpl.S_DEBUG) {
/* 290 */       transformer.getTraceManager().fireTraceEndEvent(this);
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
/*     */   void constructNode(String nodeName, String prefix, String nodeNamespace, TransformerImpl transformer) throws TransformerException {
/*     */     
/*     */     try { boolean bool;
/* 317 */       SerializationHandler rhandler = transformer.getResultTreeHandler();
/*     */       
/* 319 */       if (null == nodeName) {
/*     */         
/* 321 */         bool = false;
/*     */       }
/*     */       else {
/*     */         
/* 325 */         if (null != prefix)
/*     */         {
/* 327 */           rhandler.startPrefixMapping(prefix, nodeNamespace, true);
/*     */         }
/*     */         
/* 330 */         rhandler.startElement(nodeNamespace, QName.getLocalPart(nodeName), nodeName);
/*     */ 
/*     */         
/* 333 */         super.execute(transformer);
/*     */         
/* 335 */         bool = true;
/*     */       } 
/*     */       
/* 338 */       transformer.executeChildTemplates(this, bool);
/*     */ 
/*     */       
/* 341 */       if (null != nodeName)
/*     */       
/* 343 */       { rhandler.endElement(nodeNamespace, QName.getLocalPart(nodeName), nodeName);
/*     */         
/* 345 */         if (null != prefix)
/*     */         {
/* 347 */           rhandler.endPrefixMapping(prefix); }  }  } catch (SAXException se)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 353 */       throw new TransformerException(se); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 363 */     if (callAttrs) {
/*     */       
/* 365 */       if (null != this.m_name_avt) {
/* 366 */         this.m_name_avt.callVisitors(visitor);
/*     */       }
/* 368 */       if (null != this.m_namespace_avt) {
/* 369 */         this.m_namespace_avt.callVisitors(visitor);
/*     */       }
/*     */     } 
/* 372 */     super.callChildVisitors(visitor, callAttrs);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */