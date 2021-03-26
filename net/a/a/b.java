/*     */ package net.a.a;
/*     */ 
/*     */ import javax.annotation.concurrent.GuardedBy;
/*     */ import javax.annotation.concurrent.ThreadSafe;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMResult;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import net.a.a.e.b.a;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.DOMException;
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
/*     */ @ThreadSafe
/*     */ public final class b
/*     */ {
/*     */   private static final Log b;
/*     */   @GuardedBy("itself")
/*     */   private final Transformer c;
/*     */   @GuardedBy("itself")
/*     */   private final Transformer d;
/*     */   @GuardedBy("itself")
/*     */   private final Transformer e;
/*     */   
/*     */   static {
/*  50 */     b = LogFactory.getLog(b.class);
/*     */   }
/*     */   
/*  53 */   private static final class a { private static final b a = new b(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected b() {
/*  72 */     this.d = c();
/*  73 */     this.c = a("/net/sourceforge/jeuclid/content/mathmlc2p.xsl", this.d);
/*     */ 
/*     */     
/*  76 */     this.e = a("/net/sourceforge/jeuclid/addMathMLNamespace.xsl", this.d);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Transformer c() {
/*     */     Transformer transformer;
/*     */     try {
/*  84 */       transformer = TransformerFactory.newInstance().newTransformer();
/*  85 */     } catch (TransformerException transformerException) {
/*  86 */       b.warn(transformerException.getMessage());
/*  87 */       transformer = null;
/*  88 */       if (!a) throw new AssertionError(); 
/*     */     } 
/*  90 */     return transformer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Transformer a(String paramString, Transformer paramTransformer) {
/*     */     Transformer transformer;
/*     */     try {
/*  99 */       transformer = TransformerFactory.newInstance().newTemplates(new StreamSource(b.class.getResourceAsStream(paramString))).newTransformer();
/* 100 */     } catch (TransformerException transformerException) {
/* 101 */       b.warn(transformerException.getMessage());
/* 102 */       transformer = paramTransformer;
/*     */     } 
/* 104 */     return transformer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static b a() {
/* 111 */     return a.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static b b() {
/* 122 */     return a();
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
/*     */   public a a(Node paramNode) {
/* 135 */     return a(paramNode, true);
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
/*     */   public a a(Node paramNode, boolean paramBoolean) {
/* 155 */     return a(paramNode, paramBoolean, false);
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
/*     */   public a a(Node paramNode, boolean paramBoolean1, boolean paramBoolean2) {
/*     */     Node node;
/*     */     a a1;
/*     */     a a2;
/* 179 */     if (paramNode instanceof Document) {
/* 180 */       node = ((Document)paramNode).getDocumentElement();
/* 181 */     } else if (paramNode instanceof org.w3c.dom.Element) {
/* 182 */       node = paramNode;
/* 183 */     } else if (paramNode instanceof org.w3c.dom.DocumentFragment) {
/* 184 */       Node node1 = paramNode.getFirstChild();
/* 185 */       if (!(node1 instanceof org.w3c.dom.Element)) {
/* 186 */         throw new IllegalArgumentException("Expected DocumentFragment with Element child");
/*     */       }
/*     */       
/* 189 */       node = node1;
/*     */     } else {
/* 191 */       throw new IllegalArgumentException("Unsupported node: " + paramNode + ". Expected either Document, Element or DocumentFragment");
/*     */     } 
/*     */ 
/*     */     
/* 195 */     if (paramBoolean2) {
/* 196 */       a1 = a(node, this.e);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 201 */     if (paramBoolean1) {
/* 202 */       a2 = a((Node)a1, this.c);
/*     */     } else {
/* 204 */       a2 = a((Node)a1, this.d);
/*     */     } 
/* 206 */     return a2;
/*     */   }
/*     */ 
/*     */   
/*     */   private a a(Node paramNode, Transformer paramTransformer) {
/*     */     a a;
/*     */     try {
/* 213 */       DOMSource dOMSource = new DOMSource(paramNode);
/* 214 */       a = new a();
/* 215 */       DOMResult dOMResult = new DOMResult((Node)a);
/* 216 */       synchronized (paramTransformer) {
/* 217 */         paramTransformer.transform(dOMSource, dOMResult);
/*     */       } 
/* 219 */     } catch (TransformerException transformerException) {
/* 220 */       a = null;
/* 221 */       b.warn(transformerException.getMessage());
/* 222 */     } catch (NullPointerException nullPointerException) {
/* 223 */       a = null;
/*     */       
/* 225 */       b.warn(nullPointerException.getMessage());
/* 226 */     } catch (DOMException dOMException) {
/* 227 */       a = null;
/* 228 */       b.warn(dOMException.getMessage());
/*     */     } 
/* 230 */     return a;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */