/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.serializer.NamespaceMappings;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xml.utils.XMLChar;
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
/*     */ 
/*     */ public class ElemAttribute
/*     */   extends ElemElement
/*     */ {
/*     */   public int getXSLToken() {
/*  56 */     return 48;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  66 */     return "attribute";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/*  81 */     SerializationHandler rhandler = transformer.getSerializationHandler();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     super.execute(transformer);
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
/*     */   protected String resolvePrefix(SerializationHandler rhandler, String prefix, String nodeNamespace) throws TransformerException {
/* 126 */     if (null != prefix && (prefix.length() == 0 || prefix.equals("xmlns"))) {
/*     */ 
/*     */ 
/*     */       
/* 130 */       prefix = rhandler.getPrefix(nodeNamespace);
/*     */ 
/*     */       
/* 133 */       if (null == prefix || prefix.length() == 0 || prefix.equals("xmlns"))
/*     */       {
/* 135 */         if (nodeNamespace.length() > 0) {
/*     */           
/* 137 */           NamespaceMappings prefixMapping = rhandler.getNamespaceMappings();
/* 138 */           prefix = prefixMapping.generateNextPrefix();
/*     */         } else {
/*     */           
/* 141 */           prefix = "";
/*     */         }  } 
/*     */     } 
/* 144 */     return prefix;
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
/*     */   protected boolean validateNodeName(String nodeName) {
/* 156 */     if (null == nodeName)
/* 157 */       return false; 
/* 158 */     if (nodeName.equals("xmlns"))
/* 159 */       return false; 
/* 160 */     return XMLChar.isValidQName(nodeName);
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
/*     */   void constructNode(String nodeName, String prefix, String nodeNamespace, TransformerImpl transformer) throws TransformerException {
/* 181 */     if (null != nodeName && nodeName.length() > 0) {
/*     */       
/* 183 */       SerializationHandler rhandler = transformer.getSerializationHandler();
/* 184 */       if (prefix != null && prefix.length() > 0) {
/*     */ 
/*     */         
/*     */         try { 
/* 188 */           rhandler.startPrefixMapping(prefix, nodeNamespace, false); } catch (SAXException se)
/*     */         
/*     */         { 
/*     */           
/* 192 */           throw new TransformerException(se); }
/*     */       
/*     */       }
/* 195 */       String val = transformer.transformToString(this);
/* 196 */       String localName = QName.getLocalPart(nodeName);
/*     */ 
/*     */       
/* 199 */       try { if (prefix != null && prefix.length() > 0)
/* 200 */         { rhandler.addAttribute(nodeNamespace, localName, nodeName, "CDATA", val); }
/*     */         else
/* 202 */         { rhandler.addAttribute("", localName, nodeName, "CDATA", val); }  } catch (SAXException sAXException) {}
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
/*     */ 
/*     */ 
/*     */   
/*     */   public ElemTemplateElement appendChild(ElemTemplateElement newChild) {
/* 230 */     int type = newChild.getXSLToken();
/*     */     
/* 232 */     switch (type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/*     */       case 17:
/*     */       case 28:
/*     */       case 30:
/*     */       case 35:
/*     */       case 36:
/*     */       case 37:
/*     */       case 42:
/*     */       case 50:
/*     */       case 72:
/*     */       case 73:
/*     */       case 74:
/*     */       case 75:
/*     */       case 78:
/* 265 */         return super.appendChild(newChild);
/*     */     } 
/*     */     error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), getNodeName() });
/*     */   }
/*     */   
/*     */   public void setName(AVT v) {
/* 271 */     if (v.isSimple())
/*     */     {
/* 273 */       if (v.getSimpleString().equals("xmlns"))
/*     */       {
/* 275 */         throw new IllegalArgumentException();
/*     */       }
/*     */     }
/* 278 */     super.setName(v);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemAttribute.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */