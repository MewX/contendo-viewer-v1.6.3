/*     */ package org.apache.batik.script;
/*     */ 
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.util.Service;
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
/*     */ public class InterpreterPool
/*     */ {
/*     */   public static final String BIND_NAME_DOCUMENT = "document";
/*  55 */   protected static Map defaultFactories = new HashMap<Object, Object>(7);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected Map factories = new HashMap<Object, Object>(7);
/*     */   
/*     */   static {
/*  63 */     Iterator<InterpreterFactory> iter = Service.providers(InterpreterFactory.class);
/*  64 */     while (iter.hasNext()) {
/*  65 */       InterpreterFactory factory = null;
/*  66 */       factory = iter.next();
/*  67 */       String[] mimeTypes = factory.getMimeTypes();
/*  68 */       for (String mimeType : mimeTypes) {
/*  69 */         defaultFactories.put(mimeType, factory);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterpreterPool() {
/*  78 */     this.factories.putAll(defaultFactories);
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
/*     */   public Interpreter createInterpreter(Document document, String language) {
/*  92 */     return createInterpreter(document, language, null);
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
/*     */   public Interpreter createInterpreter(Document document, String language, ImportInfo imports) {
/* 110 */     InterpreterFactory factory = (InterpreterFactory)this.factories.get(language);
/*     */     
/* 112 */     if (factory == null) return null;
/*     */     
/* 114 */     if (imports == null) {
/* 115 */       imports = ImportInfo.getImports();
/*     */     }
/* 117 */     Interpreter interpreter = null;
/* 118 */     SVGOMDocument svgDoc = (SVGOMDocument)document;
/* 119 */     URL url = null;
/*     */     try {
/* 121 */       url = new URL(svgDoc.getDocumentURI());
/* 122 */     } catch (MalformedURLException malformedURLException) {}
/*     */     
/* 124 */     interpreter = factory.createInterpreter(url, svgDoc.isSVG12(), imports);
/*     */ 
/*     */     
/* 127 */     if (interpreter == null) return null;
/*     */     
/* 129 */     if (document != null) {
/* 130 */       interpreter.bindObject("document", document);
/*     */     }
/* 132 */     return interpreter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putInterpreterFactory(String language, InterpreterFactory factory) {
/* 143 */     this.factories.put(language, factory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeInterpreterFactory(String language) {
/* 152 */     this.factories.remove(language);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/script/InterpreterPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */