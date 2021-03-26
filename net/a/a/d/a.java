/*     */ package net.a.a.d;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import net.a.a.c;
/*     */ import net.a.a.g.c;
/*     */ import org.apache.batik.svggen.SVGGeneratorContext;
/*     */ import org.apache.batik.svggen.SVGGraphics2D;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   implements e
/*     */ {
/*  52 */   private static final Log a = LogFactory.getLog(a.class);
/*     */   
/*     */   private final DOMImplementation b;
/*     */   
/*     */   a(DOMImplementation paramDOMImplementation) {
/*  57 */     this.b = paramDOMImplementation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension a(Node paramNode, c paramc, OutputStream paramOutputStream) throws IOException {
/*  63 */     e.a a1 = a(paramNode, paramc);
/*  64 */     if (a1 != null) {
/*     */       
/*     */       try {
/*  67 */         Transformer transformer = TransformerFactory.newInstance().newTransformer();
/*     */         
/*  69 */         DOMSource dOMSource = new DOMSource(a1.a());
/*  70 */         StreamResult streamResult = new StreamResult(paramOutputStream);
/*  71 */         transformer.transform(dOMSource, streamResult);
/*  72 */       } catch (TransformerException transformerException) {
/*  73 */         a.warn(transformerException);
/*     */       } 
/*  75 */       return a1.b();
/*     */     } 
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public e.a a(Node paramNode, c paramc) {
/*  84 */     Document document = null;
/*     */     
/*  86 */     document = this.b.createDocument("http://www.w3.org/2000/svg", "svg", null);
/*  87 */     if (document != null) {
/*     */       
/*  89 */       SVGGeneratorContext sVGGeneratorContext = SVGGeneratorContext.createDefault(document);
/*  90 */       sVGGeneratorContext.setComment("Converted from MathML using JEuclid");
/*  91 */       SVGGraphics2D sVGGraphics2D = new SVGGraphics2D(sVGGeneratorContext, true);
/*     */       
/*  93 */       c c1 = new c(paramNode, paramc, (Graphics2D)sVGGraphics2D);
/*     */       
/*  95 */       int i = (int)Math.ceil(c1.b());
/*  96 */       int j = (int)Math.ceil(c1.c());
/*  97 */       int k = i + j;
/*  98 */       int m = (int)Math.ceil(c1.a());
/*  99 */       Dimension dimension = new Dimension(m, k);
/* 100 */       sVGGraphics2D.setSVGCanvasSize(dimension);
/* 101 */       c1.a((Graphics2D)sVGGraphics2D, 0.0F, i);
/* 102 */       document.replaceChild(sVGGraphics2D.getRoot(), document
/* 103 */           .getFirstChild());
/* 104 */       return new e.a(document, new Dimension(m, k), j);
/*     */     } 
/*     */     
/* 107 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */