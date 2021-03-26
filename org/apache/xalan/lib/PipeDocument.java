/*     */ package org.apache.xalan.lib;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.Templates;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.sax.SAXResult;
/*     */ import javax.xml.transform.sax.SAXTransformerFactory;
/*     */ import javax.xml.transform.sax.TransformerHandler;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.xalan.extensions.XSLProcessorContext;
/*     */ import org.apache.xalan.templates.AVT;
/*     */ import org.apache.xalan.templates.ElemExtensionCall;
/*     */ import org.apache.xalan.templates.ElemLiteralResult;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.serializer.Serializer;
/*     */ import org.apache.xml.serializer.SerializerFactory;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.XMLReaderFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PipeDocument
/*     */ {
/*     */   public void pipeDocument(XSLProcessorContext context, ElemExtensionCall elem) throws TransformerException, TransformerConfigurationException, SAXException, IOException, FileNotFoundException {
/*     */     
/* 112 */     try { SAXTransformerFactory saxTFactory = (SAXTransformerFactory)TransformerFactory.newInstance();
/*     */ 
/*     */       
/* 115 */       String source = elem.getAttribute("source", context.getContextNode(), context.getTransformer());
/*     */ 
/*     */       
/* 118 */       TransformerImpl transImpl = context.getTransformer();
/*     */ 
/*     */       
/* 121 */       String baseURLOfSource = transImpl.getBaseURLOfSource();
/*     */       
/* 123 */       String absSourceURL = SystemIDResolver.getAbsoluteURI(source, baseURLOfSource);
/*     */ 
/*     */       
/* 126 */       String target = elem.getAttribute("target", context.getContextNode(), context.getTransformer());
/*     */ 
/*     */ 
/*     */       
/* 130 */       XPathContext xctxt = context.getTransformer().getXPathContext();
/* 131 */       int xt = xctxt.getDTMHandleFromNode(context.getContextNode());
/*     */ 
/*     */       
/* 134 */       String sysId = elem.getSystemId();
/*     */       
/* 136 */       NodeList ssNodes = null;
/* 137 */       NodeList paramNodes = null;
/* 138 */       Node ssNode = null;
/* 139 */       Node paramNode = null;
/* 140 */       if (elem.hasChildNodes())
/*     */       
/* 142 */       { ssNodes = elem.getChildNodes();
/*     */         
/* 144 */         Vector vTHandler = new Vector(ssNodes.getLength());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 150 */         for (int i = 0; i < ssNodes.getLength(); i++) {
/*     */           
/* 152 */           ssNode = ssNodes.item(i);
/* 153 */           if (ssNode.getNodeType() == 1 && ((Element)ssNode).getTagName().equals("stylesheet") && ssNode instanceof ElemLiteralResult) {
/*     */ 
/*     */ 
/*     */             
/* 157 */             AVT avt = ((ElemLiteralResult)ssNode).getLiteralResultAttribute("href");
/* 158 */             String href = avt.evaluate(xctxt, xt, (PrefixResolver)elem);
/* 159 */             String absURI = SystemIDResolver.getAbsoluteURI(href, sysId);
/* 160 */             Templates tmpl = saxTFactory.newTemplates(new StreamSource(absURI));
/* 161 */             TransformerHandler tHandler = saxTFactory.newTransformerHandler(tmpl);
/* 162 */             Transformer trans = tHandler.getTransformer();
/*     */ 
/*     */             
/* 165 */             vTHandler.addElement(tHandler);
/*     */             
/* 167 */             paramNodes = ssNode.getChildNodes();
/* 168 */             for (int j = 0; j < paramNodes.getLength(); j++) {
/*     */               
/* 170 */               paramNode = paramNodes.item(j);
/* 171 */               if (paramNode.getNodeType() == 1 && ((Element)paramNode).getTagName().equals("param") && paramNode instanceof ElemLiteralResult) {
/*     */ 
/*     */ 
/*     */                 
/* 175 */                 avt = ((ElemLiteralResult)paramNode).getLiteralResultAttribute("name");
/* 176 */                 String pName = avt.evaluate(xctxt, xt, (PrefixResolver)elem);
/* 177 */                 avt = ((ElemLiteralResult)paramNode).getLiteralResultAttribute("value");
/* 178 */                 String pValue = avt.evaluate(xctxt, xt, (PrefixResolver)elem);
/* 179 */                 trans.setParameter(pName, pValue);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 184 */         usePipe(vTHandler, absSourceURL, target); }  } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 189 */       e.printStackTrace(); }
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
/*     */   public void usePipe(Vector vTHandler, String source, String target) throws TransformerException, TransformerConfigurationException, FileNotFoundException, IOException, SAXException, SAXNotRecognizedException {
/* 204 */     XMLReader reader = XMLReaderFactory.createXMLReader();
/* 205 */     TransformerHandler tHFirst = vTHandler.firstElement();
/* 206 */     reader.setContentHandler(tHFirst);
/* 207 */     reader.setProperty("http://xml.org/sax/properties/lexical-handler", tHFirst);
/* 208 */     for (int i = 1; i < vTHandler.size(); i++) {
/*     */       
/* 210 */       TransformerHandler tHFrom = vTHandler.elementAt(i - 1);
/* 211 */       TransformerHandler tHTo = vTHandler.elementAt(i);
/* 212 */       tHFrom.setResult(new SAXResult(tHTo));
/*     */     } 
/* 214 */     TransformerHandler tHLast = vTHandler.lastElement();
/* 215 */     Transformer trans = tHLast.getTransformer();
/* 216 */     Properties outputProps = trans.getOutputProperties();
/* 217 */     Serializer serializer = SerializerFactory.getSerializer(outputProps);
/*     */     
/* 219 */     FileOutputStream out = new FileOutputStream(target);
/*     */     
/*     */     try {
/* 222 */       serializer.setOutputStream(out);
/* 223 */       tHLast.setResult(new SAXResult(serializer.asContentHandler()));
/* 224 */       reader.parse(source);
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 230 */       if (out != null)
/* 231 */         out.close(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/PipeDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */