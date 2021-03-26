/*     */ package net.a.a.d;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMResult;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.c;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public final class l
/*     */ {
/*     */   private static final Log b;
/*     */   private final Transformer c;
/*     */   
/*     */   private static final class a
/*     */   {
/*  61 */     private static final l a = new l();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  70 */     b = LogFactory.getLog(l.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected l() {
/*     */     Transformer transformer;
/*     */     try {
/*  83 */       transformer = TransformerFactory.newInstance().newTransformer();
/*  84 */     } catch (TransformerException transformerException) {
/*  85 */       transformer = null;
/*  86 */       b.warn(transformerException.getMessage());
/*  87 */       if (!a) throw new AssertionError(); 
/*     */     } 
/*  89 */     this.c = transformer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static l a() {
/*  98 */     return a.a();
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
/*     */   @Deprecated
/*     */   public static l b() throws TransformerException {
/* 111 */     return a();
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
/*     */   public void a(Source paramSource, Result paramResult, c paramc) throws TransformerException {
/* 128 */     b.info("Processing " + paramSource.getSystemId() + " to " + paramResult
/* 129 */         .getSystemId());
/*     */     try {
/* 131 */       Node node = net.a.a.h.a.a().a(paramSource);
/* 132 */       a(node, paramc);
/* 133 */       DOMSource dOMSource = new DOMSource(node);
/* 134 */       this.c.transform(dOMSource, paramResult);
/* 135 */     } catch (IOException iOException) {
/* 136 */       throw new TransformerException("IOException", iOException);
/* 137 */     } catch (SAXException sAXException) {
/* 138 */       throw new TransformerException("SAXException", sAXException);
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
/*     */   public void a(Source paramSource, Result paramResult) throws TransformerException {
/* 154 */     a(paramSource, paramResult, 
/* 155 */         c.a());
/*     */   }
/*     */   
/*     */   private void a(Node paramNode, c paramc) {
/* 159 */     if ("http://www.w3.org/1998/Math/MathML".equals(paramNode.getNamespaceURI()) && "math"
/* 160 */       .equals(paramNode.getLocalName())) {
/*     */ 
/*     */ 
/*     */       
/* 164 */       e.a a = c.a().a(paramNode, "image/svg+xml", paramc);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 170 */       float f = -(a.c() / (float)a.b().getHeight()) * 100.0F;
/*     */       
/* 172 */       Node node = paramNode.getParentNode();
/* 173 */       if ("http://www.w3.org/1999/XSL/Format".equals(node
/* 174 */           .getNamespaceURI()) && "instream-foreign-object"
/* 175 */         .equals(node.getLocalName())) {
/* 176 */         Element element = (Element)node;
/* 177 */         element
/* 178 */           .setAttribute("alignment-adjust", f + "%");
/*     */       } 
/* 180 */       a(node, paramNode, a.a()
/* 181 */           .getFirstChild());
/*     */     } else {
/* 183 */       b(paramNode, paramc);
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
/*     */   private void a(Node paramNode1, Node paramNode2, Node paramNode3) {
/*     */     try {
/* 211 */       DOMSource dOMSource = new DOMSource(paramNode3);
/* 212 */       DOMResult dOMResult = new DOMResult(paramNode1);
/*     */       
/* 214 */       this.c.transform(dOMSource, dOMResult);
/* 215 */     } catch (TransformerException transformerException) {
/* 216 */       b.warn("TranformerException: " + transformerException.getMessage());
/*     */     } 
/* 218 */     paramNode1.removeChild(paramNode2);
/*     */   }
/*     */   
/*     */   private void b(Node paramNode, c paramc) {
/* 222 */     NodeList nodeList = paramNode.getChildNodes();
/* 223 */     if (nodeList != null)
/* 224 */       for (byte b = 0; b < nodeList.getLength(); b++) {
/* 225 */         Node node = nodeList.item(b);
/* 226 */         a(node, paramc);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */