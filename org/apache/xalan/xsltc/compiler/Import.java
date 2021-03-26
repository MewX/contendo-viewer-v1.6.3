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
/*     */ final class Import
/*     */   extends TopLevelElement
/*     */ {
/*  45 */   private Stylesheet _imported = null;
/*     */   
/*     */   public Stylesheet getImportedStylesheet() {
/*  48 */     return this._imported;
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  52 */     XSLTC xsltc = parser.getXSLTC();
/*  53 */     Stylesheet context = parser.getCurrentStylesheet();
/*     */     try {
/*     */       SyntaxTreeNode syntaxTreeNode;
/*  56 */       String docToLoad = getAttribute("href");
/*  57 */       if (context.checkForLoop(docToLoad)) {
/*  58 */         ErrorMsg msg = new ErrorMsg("CIRCULAR_INCLUDE_ERR", docToLoad, this);
/*     */         
/*  60 */         parser.reportError(2, msg);
/*     */         
/*     */         return;
/*     */       } 
/*  64 */       InputSource input = null;
/*  65 */       XMLReader reader = null;
/*  66 */       String currLoadedDoc = context.getSystemId();
/*  67 */       SourceLoader loader = context.getSourceLoader();
/*     */ 
/*     */       
/*  70 */       if (loader != null) {
/*  71 */         input = loader.loadSource(docToLoad, currLoadedDoc, xsltc);
/*  72 */         if (input != null) {
/*  73 */           docToLoad = input.getSystemId();
/*  74 */           reader = xsltc.getXMLReader();
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  79 */       if (input == null) {
/*  80 */         docToLoad = SystemIDResolver.getAbsoluteURI(docToLoad, currLoadedDoc);
/*  81 */         input = new InputSource(docToLoad);
/*     */       } 
/*     */ 
/*     */       
/*  85 */       if (input == null) {
/*  86 */         ErrorMsg msg = new ErrorMsg("FILE_NOT_FOUND_ERR", docToLoad, this);
/*     */         
/*  88 */         parser.reportError(2, msg);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  93 */       if (reader != null) {
/*  94 */         syntaxTreeNode = parser.parse(reader, input);
/*     */       } else {
/*     */         
/*  97 */         syntaxTreeNode = parser.parse(input);
/*     */       } 
/*     */       
/* 100 */       if (syntaxTreeNode == null)
/* 101 */         return;  this._imported = parser.makeStylesheet(syntaxTreeNode);
/* 102 */       if (this._imported == null)
/*     */         return; 
/* 104 */       this._imported.setSourceLoader(loader);
/* 105 */       this._imported.setSystemId(docToLoad);
/* 106 */       this._imported.setParentStylesheet(context);
/* 107 */       this._imported.setImportingStylesheet(context);
/*     */ 
/*     */       
/* 110 */       int currPrecedence = parser.getCurrentImportPrecedence();
/* 111 */       int nextPrecedence = parser.getNextImportPrecedence();
/* 112 */       this._imported.setImportPrecedence(currPrecedence);
/* 113 */       context.setImportPrecedence(nextPrecedence);
/* 114 */       parser.setCurrentStylesheet(this._imported);
/* 115 */       this._imported.parseContents(parser);
/*     */       
/* 117 */       Enumeration elements = this._imported.elements();
/* 118 */       Stylesheet topStylesheet = parser.getTopLevelStylesheet();
/* 119 */       while (elements.hasMoreElements()) {
/* 120 */         Object element = elements.nextElement();
/* 121 */         if (element instanceof TopLevelElement) {
/* 122 */           if (element instanceof Variable) {
/* 123 */             topStylesheet.addVariable((Variable)element); continue;
/*     */           } 
/* 125 */           if (element instanceof Param) {
/* 126 */             topStylesheet.addParam((Param)element);
/*     */             continue;
/*     */           } 
/* 129 */           topStylesheet.addElement((TopLevelElement)element);
/*     */         }
/*     */       
/*     */       } 
/*     */     } catch (Exception e) {
/*     */       
/* 135 */       e.printStackTrace();
/*     */     } finally {
/*     */       
/* 138 */       parser.setCurrentStylesheet(context);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 143 */     return Type.Void;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Import.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */