/*     */ package org.apache.xalan.xsltc.runtime.output;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.apache.xalan.xsltc.trax.SAX2DOM;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.serializer.ToHTMLSAXHandler;
/*     */ import org.apache.xml.serializer.ToHTMLStream;
/*     */ import org.apache.xml.serializer.ToTextSAXHandler;
/*     */ import org.apache.xml.serializer.ToTextStream;
/*     */ import org.apache.xml.serializer.ToUnknownStream;
/*     */ import org.apache.xml.serializer.ToXMLSAXHandler;
/*     */ import org.apache.xml.serializer.ToXMLStream;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.ContentHandler;
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
/*     */ public class TransletOutputHandlerFactory
/*     */ {
/*     */   public static final int STREAM = 0;
/*     */   public static final int SAX = 1;
/*     */   public static final int DOM = 2;
/*  51 */   private String _encoding = "utf-8";
/*  52 */   private String _method = null;
/*  53 */   private int _outputType = 0;
/*  54 */   private OutputStream _ostream = System.out;
/*  55 */   private Writer _writer = null;
/*  56 */   private Node _node = null;
/*  57 */   private int _indentNumber = -1;
/*  58 */   private ContentHandler _handler = null;
/*  59 */   private LexicalHandler _lexHandler = null;
/*     */   
/*     */   public static TransletOutputHandlerFactory newInstance() {
/*  62 */     return new TransletOutputHandlerFactory();
/*     */   }
/*     */   
/*     */   public void setOutputType(int outputType) {
/*  66 */     this._outputType = outputType;
/*     */   }
/*     */   
/*     */   public void setEncoding(String encoding) {
/*  70 */     if (encoding != null) {
/*  71 */       this._encoding = encoding;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setOutputMethod(String method) {
/*  76 */     this._method = method;
/*     */   }
/*     */   
/*     */   public void setOutputStream(OutputStream ostream) {
/*  80 */     this._ostream = ostream;
/*     */   }
/*     */   
/*     */   public void setWriter(Writer writer) {
/*  84 */     this._writer = writer;
/*     */   }
/*     */   
/*     */   public void setHandler(ContentHandler handler) {
/*  88 */     this._handler = handler;
/*     */   }
/*     */   
/*     */   public void setLexicalHandler(LexicalHandler lex) {
/*  92 */     this._lexHandler = lex;
/*     */   }
/*     */   
/*     */   public void setNode(Node node) {
/*  96 */     this._node = node;
/*     */   }
/*     */   
/*     */   public Node getNode() {
/* 100 */     return (this._handler instanceof SAX2DOM) ? ((SAX2DOM)this._handler).getDOM() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndentNumber(int value) {
/* 105 */     this._indentNumber = value;
/*     */   }
/*     */   
/*     */   public SerializationHandler getSerializationHandler() throws IOException, ParserConfigurationException {
/*     */     ToTextStream toTextStream;
/*     */     ToTextSAXHandler toTextSAXHandler;
/* 111 */     SerializationHandler result = null;
/* 112 */     switch (this._outputType) {
/*     */ 
/*     */       
/*     */       case 0:
/* 116 */         if (this._method == null) {
/*     */           
/* 118 */           ToUnknownStream toUnknownStream = new ToUnknownStream();
/*     */         }
/* 120 */         else if (this._method.equalsIgnoreCase("xml")) {
/*     */ 
/*     */           
/* 123 */           ToXMLStream toXMLStream = new ToXMLStream();
/*     */         
/*     */         }
/* 126 */         else if (this._method.equalsIgnoreCase("html")) {
/*     */ 
/*     */           
/* 129 */           ToHTMLStream toHTMLStream = new ToHTMLStream();
/*     */         
/*     */         }
/* 132 */         else if (this._method.equalsIgnoreCase("text")) {
/*     */ 
/*     */           
/* 135 */           toTextStream = new ToTextStream();
/*     */         } 
/*     */ 
/*     */         
/* 139 */         if (toTextStream != null && this._indentNumber >= 0)
/*     */         {
/* 141 */           toTextStream.setIndentAmount(this._indentNumber);
/*     */         }
/*     */         
/* 144 */         toTextStream.setEncoding(this._encoding);
/*     */         
/* 146 */         if (this._writer != null) {
/*     */           
/* 148 */           toTextStream.setWriter(this._writer);
/*     */         }
/*     */         else {
/*     */           
/* 152 */           toTextStream.setOutputStream(this._ostream);
/*     */         } 
/* 154 */         return (SerializationHandler)toTextStream;
/*     */       
/*     */       case 2:
/* 157 */         this._handler = (this._node != null) ? (ContentHandler)new SAX2DOM(this._node) : (ContentHandler)new SAX2DOM();
/* 158 */         this._lexHandler = (LexicalHandler)this._handler;
/*     */       
/*     */       case 1:
/* 161 */         if (this._method == null)
/*     */         {
/* 163 */           this._method = "xml";
/*     */         }
/*     */         
/* 166 */         if (this._method.equalsIgnoreCase("xml")) {
/*     */ 
/*     */           
/* 169 */           if (this._lexHandler == null)
/*     */           {
/* 171 */             ToXMLSAXHandler toXMLSAXHandler = new ToXMLSAXHandler(this._handler, this._encoding);
/*     */           }
/*     */           else
/*     */           {
/* 175 */             ToXMLSAXHandler toXMLSAXHandler = new ToXMLSAXHandler(this._handler, this._lexHandler, this._encoding);
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 183 */         else if (this._method.equalsIgnoreCase("html")) {
/*     */ 
/*     */           
/* 186 */           if (this._lexHandler == null)
/*     */           {
/* 188 */             ToHTMLSAXHandler toHTMLSAXHandler = new ToHTMLSAXHandler(this._handler, this._encoding);
/*     */           }
/*     */           else
/*     */           {
/* 192 */             ToHTMLSAXHandler toHTMLSAXHandler = new ToHTMLSAXHandler(this._handler, this._lexHandler, this._encoding);
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 200 */         else if (this._method.equalsIgnoreCase("text")) {
/*     */ 
/*     */           
/* 203 */           if (this._lexHandler == null) {
/*     */             
/* 205 */             toTextSAXHandler = new ToTextSAXHandler(this._handler, this._encoding);
/*     */           }
/*     */           else {
/*     */             
/* 209 */             toTextSAXHandler = new ToTextSAXHandler(this._handler, this._lexHandler, this._encoding);
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 217 */         return (SerializationHandler)toTextSAXHandler;
/*     */     } 
/* 219 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/output/TransletOutputHandlerFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */