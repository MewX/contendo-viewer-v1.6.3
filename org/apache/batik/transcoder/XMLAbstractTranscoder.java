/*     */ package org.apache.batik.transcoder;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.batik.dom.util.DocumentFactory;
/*     */ import org.apache.batik.dom.util.SAXDocumentFactory;
/*     */ import org.apache.batik.transcoder.keys.BooleanKey;
/*     */ import org.apache.batik.transcoder.keys.DOMImplementationKey;
/*     */ import org.apache.batik.transcoder.keys.StringKey;
/*     */ import org.apache.batik.util.XMLResourceDescriptor;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class XMLAbstractTranscoder
/*     */   extends AbstractTranscoder
/*     */ {
/*     */   protected XMLAbstractTranscoder() {
/*  59 */     this.hints.put(KEY_XML_PARSER_VALIDATING, Boolean.FALSE);
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
/*     */   public void transcode(TranscoderInput input, TranscoderOutput output) throws TranscoderException {
/*  75 */     Document document = null;
/*  76 */     String uri = input.getURI();
/*  77 */     if (input.getDocument() != null) {
/*  78 */       document = input.getDocument();
/*     */     } else {
/*  80 */       String parserClassname = (String)this.hints.get(KEY_XML_PARSER_CLASSNAME);
/*     */       
/*  82 */       String namespaceURI = (String)this.hints.get(KEY_DOCUMENT_ELEMENT_NAMESPACE_URI);
/*     */       
/*  84 */       String documentElement = (String)this.hints.get(KEY_DOCUMENT_ELEMENT);
/*     */       
/*  86 */       DOMImplementation domImpl = (DOMImplementation)this.hints.get(KEY_DOM_IMPLEMENTATION);
/*     */ 
/*     */       
/*  89 */       if (parserClassname == null) {
/*  90 */         parserClassname = XMLResourceDescriptor.getXMLParserClassName();
/*     */       }
/*  92 */       if (domImpl == null) {
/*  93 */         this.handler.fatalError(new TranscoderException("Unspecified transcoding hints: KEY_DOM_IMPLEMENTATION"));
/*     */         
/*     */         return;
/*     */       } 
/*  97 */       if (namespaceURI == null) {
/*  98 */         this.handler.fatalError(new TranscoderException("Unspecified transcoding hints: KEY_DOCUMENT_ELEMENT_NAMESPACE_URI"));
/*     */         
/*     */         return;
/*     */       } 
/* 102 */       if (documentElement == null) {
/* 103 */         this.handler.fatalError(new TranscoderException("Unspecified transcoding hints: KEY_DOCUMENT_ELEMENT"));
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 108 */       DocumentFactory f = createDocumentFactory(domImpl, parserClassname);
/* 109 */       Object xmlParserValidating = this.hints.get(KEY_XML_PARSER_VALIDATING);
/* 110 */       boolean validating = (xmlParserValidating != null && ((Boolean)xmlParserValidating).booleanValue());
/* 111 */       f.setValidating(validating);
/*     */       try {
/* 113 */         if (input.getInputStream() != null) {
/* 114 */           document = f.createDocument(namespaceURI, documentElement, input.getURI(), input.getInputStream());
/*     */ 
/*     */         
/*     */         }
/* 118 */         else if (input.getReader() != null) {
/* 119 */           document = f.createDocument(namespaceURI, documentElement, input.getURI(), input.getReader());
/*     */ 
/*     */         
/*     */         }
/* 123 */         else if (input.getXMLReader() != null) {
/* 124 */           document = f.createDocument(namespaceURI, documentElement, input.getURI(), input.getXMLReader());
/*     */ 
/*     */         
/*     */         }
/* 128 */         else if (uri != null) {
/* 129 */           document = f.createDocument(namespaceURI, documentElement, uri);
/*     */         }
/*     */       
/*     */       }
/* 133 */       catch (DOMException ex) {
/* 134 */         this.handler.fatalError(new TranscoderException(ex));
/* 135 */       } catch (IOException ex) {
/* 136 */         this.handler.fatalError(new TranscoderException(ex));
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     if (document != null) {
/*     */       try {
/* 142 */         transcode(document, uri, output);
/* 143 */       } catch (TranscoderException ex) {
/*     */         
/* 145 */         this.handler.fatalError(ex);
/*     */         return;
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
/*     */   protected DocumentFactory createDocumentFactory(DOMImplementation domImpl, String parserClassname) {
/* 162 */     return (DocumentFactory)new SAXDocumentFactory(domImpl, parserClassname);
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
/* 207 */   public static final TranscodingHints.Key KEY_XML_PARSER_CLASSNAME = (TranscodingHints.Key)new StringKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public static final TranscodingHints.Key KEY_XML_PARSER_VALIDATING = (TranscodingHints.Key)new BooleanKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   public static final TranscodingHints.Key KEY_DOCUMENT_ELEMENT = (TranscodingHints.Key)new StringKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public static final TranscodingHints.Key KEY_DOCUMENT_ELEMENT_NAMESPACE_URI = (TranscodingHints.Key)new StringKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public static final TranscodingHints.Key KEY_DOM_IMPLEMENTATION = (TranscodingHints.Key)new DOMImplementationKey();
/*     */   
/*     */   protected abstract void transcode(Document paramDocument, String paramString, TranscoderOutput paramTranscoderOutput) throws TranscoderException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/XMLAbstractTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */