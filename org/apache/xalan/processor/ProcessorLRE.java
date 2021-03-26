/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.ElemExtensionCall;
/*     */ import org.apache.xalan.templates.ElemLiteralResult;
/*     */ import org.apache.xalan.templates.ElemTemplate;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.Stylesheet;
/*     */ import org.apache.xalan.templates.StylesheetRoot;
/*     */ import org.apache.xalan.templates.XMLNSDecl;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.SAXSourceLocator;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProcessorLRE
/*     */   extends ProcessorTemplateElem
/*     */ {
/*     */   public void startElement(StylesheetHandler handler, String uri, String localName, String rawName, Attributes attributes) throws SAXException {
/*     */     
/*     */     try { ElemExtensionCall elemExtensionCall;
/*  73 */       ElemTemplateElement p = handler.getElemTemplateElement();
/*  74 */       boolean excludeXSLDecl = false;
/*  75 */       boolean isLREAsStyleSheet = false;
/*     */       
/*  77 */       if (null == p) {
/*     */         StylesheetRoot stylesheetRoot;
/*     */ 
/*     */         
/*  81 */         XSLTElementProcessor lreProcessor = handler.popProcessor();
/*  82 */         XSLTElementProcessor stylesheetProcessor = handler.getProcessorFor("http://www.w3.org/1999/XSL/Transform", "stylesheet", "xsl:stylesheet");
/*     */ 
/*     */ 
/*     */         
/*  86 */         handler.pushProcessor(lreProcessor);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  91 */         try { stylesheetRoot = new StylesheetRoot(handler.getSchema(), handler.getStylesheetProcessor().getErrorListener()); } catch (TransformerConfigurationException tfe)
/*     */         
/*     */         { 
/*     */           
/*  95 */           throw new TransformerException(tfe); }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 100 */         SAXSourceLocator slocator = new SAXSourceLocator();
/* 101 */         SAXSourceLocator sAXSourceLocator1 = handler.getLocator();
/* 102 */         if (null != sAXSourceLocator1) {
/*     */           
/* 104 */           slocator.setLineNumber(sAXSourceLocator1.getLineNumber());
/* 105 */           slocator.setColumnNumber(sAXSourceLocator1.getColumnNumber());
/* 106 */           slocator.setPublicId(sAXSourceLocator1.getPublicId());
/* 107 */           slocator.setSystemId(sAXSourceLocator1.getSystemId());
/*     */         } 
/* 109 */         stylesheetRoot.setLocaterInfo((SourceLocator)slocator);
/* 110 */         stylesheetRoot.setPrefixes(handler.getNamespaceSupport());
/* 111 */         handler.pushStylesheet((Stylesheet)stylesheetRoot);
/*     */         
/* 113 */         isLREAsStyleSheet = true;
/*     */         
/* 115 */         AttributesImpl stylesheetAttrs = new AttributesImpl();
/* 116 */         AttributesImpl lreAttrs = new AttributesImpl();
/* 117 */         int n = attributes.getLength();
/*     */         
/* 119 */         for (int i = 0; i < n; i++) {
/*     */           
/* 121 */           String attrLocalName = attributes.getLocalName(i);
/* 122 */           String attrUri = attributes.getURI(i);
/* 123 */           String value = attributes.getValue(i);
/*     */           
/* 125 */           if (null != attrUri && attrUri.equals("http://www.w3.org/1999/XSL/Transform")) {
/*     */             
/* 127 */             stylesheetAttrs.addAttribute(null, attrLocalName, attrLocalName, attributes.getType(i), attributes.getValue(i));
/*     */ 
/*     */           
/*     */           }
/* 131 */           else if ((!attrLocalName.startsWith("xmlns:") && !attrLocalName.equals("xmlns")) || !value.equals("http://www.w3.org/1999/XSL/Transform")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 139 */             lreAttrs.addAttribute(attrUri, attrLocalName, attributes.getQName(i), attributes.getType(i), attributes.getValue(i));
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 146 */         attributes = lreAttrs;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 152 */         try { stylesheetProcessor.setPropertiesFromAttributes(handler, "stylesheet", stylesheetAttrs, (ElemTemplateElement)stylesheetRoot); } catch (Exception e)
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 164 */           if (stylesheetRoot.getDeclaredPrefixes() == null || !declaredXSLNS((Stylesheet)stylesheetRoot))
/*     */           {
/*     */             
/* 167 */             throw new SAXException(XSLMessages.createWarning("WG_OLD_XSLT_NS", null));
/*     */           }
/*     */ 
/*     */           
/* 171 */           throw new SAXException(e); }
/*     */ 
/*     */         
/* 174 */         handler.pushElemTemplateElement((ElemTemplateElement)stylesheetRoot);
/*     */         
/* 176 */         ElemTemplate template = new ElemTemplate();
/*     */         
/* 178 */         appendAndPush(handler, (ElemTemplateElement)template);
/*     */         
/* 180 */         XPath rootMatch = new XPath("/", (SourceLocator)stylesheetRoot, (PrefixResolver)stylesheetRoot, 1, handler.getStylesheetProcessor().getErrorListener());
/*     */ 
/*     */         
/* 183 */         template.setMatch(rootMatch);
/*     */ 
/*     */         
/* 186 */         stylesheetRoot.setTemplate(template);
/*     */         
/* 188 */         p = handler.getElemTemplateElement();
/* 189 */         excludeXSLDecl = true;
/*     */       } 
/*     */       
/* 192 */       XSLTElementDef def = getElemDef();
/* 193 */       Class classObject = def.getClassObject();
/* 194 */       boolean isExtension = false;
/* 195 */       boolean isComponentDecl = false;
/* 196 */       boolean isUnknownTopLevel = false;
/*     */       
/* 198 */       while (null != p) {
/*     */ 
/*     */ 
/*     */         
/* 202 */         if (p instanceof ElemLiteralResult) {
/*     */           
/* 204 */           ElemLiteralResult parentElem = (ElemLiteralResult)p;
/*     */           
/* 206 */           isExtension = parentElem.containsExtensionElementURI(uri);
/*     */         }
/* 208 */         else if (p instanceof Stylesheet) {
/*     */           
/* 210 */           Stylesheet parentElem = (Stylesheet)p;
/*     */           
/* 212 */           isExtension = parentElem.containsExtensionElementURI(uri);
/*     */           
/* 214 */           if (false == isExtension && null != uri && (uri.equals("http://xml.apache.org/xalan") || uri.equals("http://xml.apache.org/xslt"))) {
/*     */ 
/*     */ 
/*     */             
/* 218 */             isComponentDecl = true;
/*     */           }
/*     */           else {
/*     */             
/* 222 */             isUnknownTopLevel = true;
/*     */           } 
/*     */         } 
/*     */         
/* 226 */         if (isExtension) {
/*     */           break;
/*     */         }
/* 229 */         p = p.getParentElem();
/*     */       } 
/*     */       
/* 232 */       ElemTemplateElement elem = null;
/*     */ 
/*     */ 
/*     */       
/* 236 */       try { if (isExtension) {
/*     */ 
/*     */ 
/*     */           
/* 240 */           elemExtensionCall = new ElemExtensionCall();
/*     */         }
/* 242 */         else if (isComponentDecl) {
/*     */           
/* 244 */           elem = classObject.newInstance();
/*     */         }
/* 246 */         else if (isUnknownTopLevel) {
/*     */ 
/*     */ 
/*     */           
/* 250 */           elem = classObject.newInstance();
/*     */         }
/*     */         else {
/*     */           
/* 254 */           elem = classObject.newInstance();
/*     */         } 
/*     */         
/* 257 */         elem.setDOMBackPointer(handler.getOriginatingNode());
/* 258 */         elem.setLocaterInfo((SourceLocator)handler.getLocator());
/* 259 */         elem.setPrefixes(handler.getNamespaceSupport(), excludeXSLDecl);
/*     */         
/* 261 */         if (elem instanceof ElemLiteralResult)
/*     */         
/* 263 */         { ((ElemLiteralResult)elem).setNamespace(uri);
/* 264 */           ((ElemLiteralResult)elem).setLocalName(localName);
/* 265 */           ((ElemLiteralResult)elem).setRawName(rawName);
/* 266 */           ((ElemLiteralResult)elem).setIsLiteralResultAsStylesheet(isLREAsStyleSheet); }  } catch (InstantiationException ie)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */         
/* 272 */         handler.error("ER_FAILED_CREATING_ELEMLITRSLT", null, ie); } catch (IllegalAccessException iae)
/*     */       
/*     */       { 
/*     */         
/* 276 */         handler.error("ER_FAILED_CREATING_ELEMLITRSLT", null, iae); }
/*     */ 
/*     */       
/* 279 */       setPropertiesFromAttributes(handler, rawName, attributes, elem);
/*     */ 
/*     */       
/* 282 */       if (!isExtension && elem instanceof ElemLiteralResult) {
/*     */         
/* 284 */         isExtension = ((ElemLiteralResult)elem).containsExtensionElementURI(uri);
/*     */ 
/*     */         
/* 287 */         if (isExtension) {
/*     */ 
/*     */ 
/*     */           
/* 291 */           elemExtensionCall = new ElemExtensionCall();
/*     */           
/* 293 */           elemExtensionCall.setLocaterInfo((SourceLocator)handler.getLocator());
/* 294 */           elemExtensionCall.setPrefixes(handler.getNamespaceSupport());
/* 295 */           ((ElemLiteralResult)elemExtensionCall).setNamespace(uri);
/* 296 */           ((ElemLiteralResult)elemExtensionCall).setLocalName(localName);
/* 297 */           ((ElemLiteralResult)elemExtensionCall).setRawName(rawName);
/* 298 */           setPropertiesFromAttributes(handler, rawName, attributes, (ElemTemplateElement)elemExtensionCall);
/*     */         } 
/*     */       } 
/*     */       
/* 302 */       appendAndPush(handler, (ElemTemplateElement)elemExtensionCall); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/* 306 */       throw new SAXException(te); }
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
/*     */   public void endElement(StylesheetHandler handler, String uri, String localName, String rawName) throws SAXException {
/* 326 */     ElemTemplateElement elem = handler.getElemTemplateElement();
/*     */     
/* 328 */     if (elem instanceof ElemLiteralResult)
/*     */     {
/* 330 */       if (((ElemLiteralResult)elem).getIsLiteralResultAsStylesheet())
/*     */       {
/* 332 */         handler.popStylesheet();
/*     */       }
/*     */     }
/*     */     
/* 336 */     super.endElement(handler, uri, localName, rawName);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean declaredXSLNS(Stylesheet stylesheet) {
/* 341 */     Vector declaredPrefixes = stylesheet.getDeclaredPrefixes();
/* 342 */     int n = declaredPrefixes.size();
/*     */     
/* 344 */     for (int i = 0; i < n; i++) {
/*     */       
/* 346 */       XMLNSDecl decl = declaredPrefixes.elementAt(i);
/* 347 */       if (decl.getURI().equals("http://www.w3.org/1999/XSL/Transform"))
/* 348 */         return true; 
/*     */     } 
/* 350 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/ProcessorLRE.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */