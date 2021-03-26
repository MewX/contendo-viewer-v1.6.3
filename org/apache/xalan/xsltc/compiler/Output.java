/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.PUTFIELD;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ import org.apache.xml.serializer.Encodings;
/*     */ import org.apache.xml.utils.XMLChar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Output
/*     */   extends TopLevelElement
/*     */ {
/*     */   private String _version;
/*     */   private String _method;
/*     */   private String _encoding;
/*     */   private boolean _omitHeader = false;
/*     */   private String _standalone;
/*     */   private String _doctypePublic;
/*     */   private String _doctypeSystem;
/*     */   private String _cdata;
/*     */   private boolean _indent = false;
/*     */   private String _mediaType;
/*     */   private String _cdataToMerge;
/*     */   private boolean _disabled = false;
/*     */   private static final String STRING_SIG = "Ljava/lang/String;";
/*     */   private static final String XML_VERSION = "1.0";
/*     */   private static final String HTML_VERSION = "4.0";
/*     */   
/*     */   public void display(int indent) {
/*  75 */     indent(indent);
/*  76 */     Util.println("Output " + this._method);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disable() {
/*  85 */     this._disabled = true;
/*     */   }
/*     */   
/*     */   public boolean enabled() {
/*  89 */     return !this._disabled;
/*     */   }
/*     */   
/*     */   public String getCdata() {
/*  93 */     return this._cdata;
/*     */   }
/*     */   
/*     */   public String getOutputMethod() {
/*  97 */     return this._method;
/*     */   }
/*     */   
/*     */   public void mergeCdata(String cdata) {
/* 101 */     this._cdataToMerge = cdata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 108 */     Properties outputProperties = new Properties();
/*     */ 
/*     */     
/* 111 */     parser.setOutput(this);
/*     */ 
/*     */     
/* 114 */     if (this._disabled)
/*     */       return; 
/* 116 */     String attrib = null;
/*     */ 
/*     */     
/* 119 */     this._version = getAttribute("version");
/* 120 */     if (this._version == null || this._version.equals("")) {
/* 121 */       this._version = null;
/*     */     } else {
/*     */       
/* 124 */       outputProperties.setProperty("version", this._version);
/*     */     } 
/*     */ 
/*     */     
/* 128 */     this._method = getAttribute("method");
/* 129 */     if (this._method.equals("")) {
/* 130 */       this._method = null;
/*     */     }
/* 132 */     if (this._method != null) {
/* 133 */       this._method = this._method.toLowerCase();
/* 134 */       if (this._method.equals("xml") || this._method.equals("html") || this._method.equals("text") || (XMLChar.isValidQName(this._method) && this._method.indexOf(":") > 0)) {
/*     */ 
/*     */ 
/*     */         
/* 138 */         outputProperties.setProperty("method", this._method);
/*     */       } else {
/* 140 */         reportError(this, parser, "INVALID_METHOD_IN_OUTPUT", this._method);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 145 */     this._encoding = getAttribute("encoding");
/* 146 */     if (this._encoding.equals("")) {
/* 147 */       this._encoding = null;
/*     */     } else {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/* 153 */         String canonicalEncoding = Encodings.convertMime2JavaEncoding(this._encoding);
/* 154 */         OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out, canonicalEncoding); } catch (UnsupportedEncodingException e)
/*     */       
/*     */       { 
/*     */         
/* 158 */         ErrorMsg msg = new ErrorMsg("UNSUPPORTED_ENCODING", this._encoding, this);
/*     */         
/* 160 */         parser.reportError(4, msg); }
/*     */       
/* 162 */       outputProperties.setProperty("encoding", this._encoding);
/*     */     } 
/*     */ 
/*     */     
/* 166 */     attrib = getAttribute("omit-xml-declaration");
/* 167 */     if (attrib != null && !attrib.equals("")) {
/* 168 */       if (attrib.equals("yes")) {
/* 169 */         this._omitHeader = true;
/*     */       }
/* 171 */       outputProperties.setProperty("omit-xml-declaration", attrib);
/*     */     } 
/*     */ 
/*     */     
/* 175 */     this._standalone = getAttribute("standalone");
/* 176 */     if (this._standalone.equals("")) {
/* 177 */       this._standalone = null;
/*     */     } else {
/*     */       
/* 180 */       outputProperties.setProperty("standalone", this._standalone);
/*     */     } 
/*     */ 
/*     */     
/* 184 */     this._doctypeSystem = getAttribute("doctype-system");
/* 185 */     if (this._doctypeSystem.equals("")) {
/* 186 */       this._doctypeSystem = null;
/*     */     } else {
/*     */       
/* 189 */       outputProperties.setProperty("doctype-system", this._doctypeSystem);
/*     */     } 
/*     */ 
/*     */     
/* 193 */     this._doctypePublic = getAttribute("doctype-public");
/* 194 */     if (this._doctypePublic.equals("")) {
/* 195 */       this._doctypePublic = null;
/*     */     } else {
/*     */       
/* 198 */       outputProperties.setProperty("doctype-public", this._doctypePublic);
/*     */     } 
/*     */ 
/*     */     
/* 202 */     this._cdata = getAttribute("cdata-section-elements");
/* 203 */     if (this._cdata != null && this._cdata.equals("")) {
/* 204 */       this._cdata = null;
/*     */     } else {
/*     */       
/* 207 */       StringBuffer expandedNames = new StringBuffer();
/* 208 */       StringTokenizer tokens = new StringTokenizer(this._cdata);
/*     */ 
/*     */       
/* 211 */       while (tokens.hasMoreTokens()) {
/* 212 */         String qname = tokens.nextToken();
/* 213 */         if (!XMLChar.isValidQName(qname)) {
/* 214 */           ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", qname, this);
/* 215 */           parser.reportError(3, err);
/*     */         } 
/* 217 */         expandedNames.append(parser.getQName(qname).toString()).append(' ');
/*     */       } 
/*     */       
/* 220 */       this._cdata = expandedNames.toString();
/* 221 */       if (this._cdataToMerge != null) {
/* 222 */         this._cdata += this._cdataToMerge;
/*     */       }
/* 224 */       outputProperties.setProperty("cdata-section-elements", this._cdata);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 229 */     attrib = getAttribute("indent");
/* 230 */     if (attrib != null && !attrib.equals("")) {
/* 231 */       if (attrib.equals("yes")) {
/* 232 */         this._indent = true;
/*     */       }
/* 234 */       outputProperties.setProperty("indent", attrib);
/*     */     }
/* 236 */     else if (this._method != null && this._method.equals("html")) {
/* 237 */       this._indent = true;
/*     */     } 
/*     */ 
/*     */     
/* 241 */     this._mediaType = getAttribute("media-type");
/* 242 */     if (this._mediaType.equals("")) {
/* 243 */       this._mediaType = null;
/*     */     } else {
/*     */       
/* 246 */       outputProperties.setProperty("media-type", this._mediaType);
/*     */     } 
/*     */ 
/*     */     
/* 250 */     if (this._method != null) {
/* 251 */       if (this._method.equals("html")) {
/* 252 */         if (this._version == null) {
/* 253 */           this._version = "4.0";
/*     */         }
/* 255 */         if (this._mediaType == null) {
/* 256 */           this._mediaType = "text/html";
/*     */         }
/*     */       }
/* 259 */       else if (this._method.equals("text") && 
/* 260 */         this._mediaType == null) {
/* 261 */         this._mediaType = "text/plain";
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 267 */     parser.getCurrentStylesheet().setOutputProperties(outputProperties);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 277 */     if (this._disabled)
/*     */       return; 
/* 279 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 280 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 282 */     int field = 0;
/* 283 */     il.append(classGen.loadTranslet());
/*     */ 
/*     */     
/* 286 */     if (this._version != null && !this._version.equals("1.0")) {
/* 287 */       field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_version", "Ljava/lang/String;");
/* 288 */       il.append((Instruction)InstructionConstants.DUP);
/* 289 */       il.append((CompoundInstruction)new PUSH(cpg, this._version));
/* 290 */       il.append((Instruction)new PUTFIELD(field));
/*     */     } 
/*     */ 
/*     */     
/* 294 */     if (this._method != null) {
/* 295 */       field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_method", "Ljava/lang/String;");
/* 296 */       il.append((Instruction)InstructionConstants.DUP);
/* 297 */       il.append((CompoundInstruction)new PUSH(cpg, this._method));
/* 298 */       il.append((Instruction)new PUTFIELD(field));
/*     */     } 
/*     */ 
/*     */     
/* 302 */     if (this._encoding != null) {
/* 303 */       field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_encoding", "Ljava/lang/String;");
/* 304 */       il.append((Instruction)InstructionConstants.DUP);
/* 305 */       il.append((CompoundInstruction)new PUSH(cpg, this._encoding));
/* 306 */       il.append((Instruction)new PUTFIELD(field));
/*     */     } 
/*     */ 
/*     */     
/* 310 */     if (this._omitHeader) {
/* 311 */       field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_omitHeader", "Z");
/* 312 */       il.append((Instruction)InstructionConstants.DUP);
/* 313 */       il.append((CompoundInstruction)new PUSH(cpg, this._omitHeader));
/* 314 */       il.append((Instruction)new PUTFIELD(field));
/*     */     } 
/*     */ 
/*     */     
/* 318 */     if (this._standalone != null) {
/* 319 */       field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_standalone", "Ljava/lang/String;");
/* 320 */       il.append((Instruction)InstructionConstants.DUP);
/* 321 */       il.append((CompoundInstruction)new PUSH(cpg, this._standalone));
/* 322 */       il.append((Instruction)new PUTFIELD(field));
/*     */     } 
/*     */ 
/*     */     
/* 326 */     field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_doctypeSystem", "Ljava/lang/String;");
/* 327 */     il.append((Instruction)InstructionConstants.DUP);
/* 328 */     il.append((CompoundInstruction)new PUSH(cpg, this._doctypeSystem));
/* 329 */     il.append((Instruction)new PUTFIELD(field));
/* 330 */     field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_doctypePublic", "Ljava/lang/String;");
/* 331 */     il.append((Instruction)InstructionConstants.DUP);
/* 332 */     il.append((CompoundInstruction)new PUSH(cpg, this._doctypePublic));
/* 333 */     il.append((Instruction)new PUTFIELD(field));
/*     */ 
/*     */     
/* 336 */     if (this._mediaType != null) {
/* 337 */       field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_mediaType", "Ljava/lang/String;");
/* 338 */       il.append((Instruction)InstructionConstants.DUP);
/* 339 */       il.append((CompoundInstruction)new PUSH(cpg, this._mediaType));
/* 340 */       il.append((Instruction)new PUTFIELD(field));
/*     */     } 
/*     */ 
/*     */     
/* 344 */     if (this._indent) {
/* 345 */       field = cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "_indent", "Z");
/* 346 */       il.append((Instruction)InstructionConstants.DUP);
/* 347 */       il.append((CompoundInstruction)new PUSH(cpg, this._indent));
/* 348 */       il.append((Instruction)new PUTFIELD(field));
/*     */     } 
/*     */ 
/*     */     
/* 352 */     if (this._cdata != null) {
/* 353 */       int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addCdataElement", "(Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */       
/* 357 */       StringTokenizer tokens = new StringTokenizer(this._cdata);
/* 358 */       while (tokens.hasMoreTokens()) {
/* 359 */         il.append((Instruction)InstructionConstants.DUP);
/* 360 */         il.append((CompoundInstruction)new PUSH(cpg, tokens.nextToken()));
/* 361 */         il.append((Instruction)new INVOKEVIRTUAL(index));
/*     */       } 
/*     */     } 
/* 364 */     il.append((Instruction)InstructionConstants.POP);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Output.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */