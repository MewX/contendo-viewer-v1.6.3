/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.XMLReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Include
/*     */   extends TopLevelElement
/*     */ {
/*  46 */   private Stylesheet _included = null;
/*     */   
/*     */   public Stylesheet getIncludedStylesheet() {
/*  49 */     return this._included;
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  53 */     XSLTC xsltc = parser.getXSLTC();
/*  54 */     Stylesheet context = parser.getCurrentStylesheet();
/*     */     
/*  56 */     String docToLoad = getAttribute("href"); try {
/*     */       SyntaxTreeNode syntaxTreeNode;
/*  58 */       if (context.checkForLoop(docToLoad)) {
/*  59 */         ErrorMsg msg = new ErrorMsg("CIRCULAR_INCLUDE_ERR", docToLoad, this);
/*     */         
/*  61 */         parser.reportError(2, msg);
/*     */         
/*     */         return;
/*     */       } 
/*  65 */       InputSource input = null;
/*  66 */       XMLReader reader = null;
/*  67 */       String currLoadedDoc = context.getSystemId();
/*  68 */       SourceLoader loader = context.getSourceLoader();
/*     */ 
/*     */       
/*  71 */       if (loader != null) {
/*  72 */         input = loader.loadSource(docToLoad, currLoadedDoc, xsltc);
/*  73 */         if (input != null) {
/*  74 */           docToLoad = input.getSystemId();
/*  75 */           reader = xsltc.getXMLReader();
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  80 */       if (input == null) {
/*  81 */         docToLoad = SystemIDResolver.getAbsoluteURI(docToLoad, currLoadedDoc);
/*  82 */         input = new InputSource(docToLoad);
/*     */       } 
/*     */ 
/*     */       
/*  86 */       if (input == null) {
/*  87 */         ErrorMsg msg = new ErrorMsg("FILE_NOT_FOUND_ERR", docToLoad, this);
/*     */         
/*  89 */         parser.reportError(2, msg);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  94 */       if (reader != null) {
/*  95 */         syntaxTreeNode = parser.parse(reader, input);
/*     */       } else {
/*     */         
/*  98 */         syntaxTreeNode = parser.parse(input);
/*     */       } 
/*     */       
/* 101 */       if (syntaxTreeNode == null)
/* 102 */         return;  this._included = parser.makeStylesheet(syntaxTreeNode);
/* 103 */       if (this._included == null)
/*     */         return; 
/* 105 */       this._included.setSourceLoader(loader);
/* 106 */       this._included.setSystemId(docToLoad);
/* 107 */       this._included.setParentStylesheet(context);
/* 108 */       this._included.setIncludingStylesheet(context);
/* 109 */       this._included.setTemplateInlining(context.getTemplateInlining());
/*     */ 
/*     */ 
/*     */       
/* 113 */       int precedence = context.getImportPrecedence();
/* 114 */       this._included.setImportPrecedence(precedence);
/* 115 */       parser.setCurrentStylesheet(this._included);
/* 116 */       this._included.parseContents(parser);
/*     */       
/* 118 */       Enumeration elements = this._included.elements();
/* 119 */       Stylesheet topStylesheet = parser.getTopLevelStylesheet();
/* 120 */       while (elements.hasMoreElements()) {
/* 121 */         Object element = elements.nextElement();
/* 122 */         if (element instanceof TopLevelElement) {
/* 123 */           if (element instanceof Variable) {
/* 124 */             topStylesheet.addVariable((Variable)element); continue;
/*     */           } 
/* 126 */           if (element instanceof Param) {
/* 127 */             topStylesheet.addParam((Param)element);
/*     */             continue;
/*     */           } 
/* 130 */           topStylesheet.addElement((TopLevelElement)element);
/*     */         }
/*     */       
/*     */       } 
/*     */     } catch (Exception e) {
/*     */       
/* 136 */       e.printStackTrace();
/*     */     } finally {
/*     */       
/* 139 */       parser.setCurrentStylesheet(context);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 144 */     return Type.Void;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Include.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */