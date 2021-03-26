/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class SymbolTable
/*     */ {
/*  36 */   private final Hashtable _stylesheets = new Hashtable();
/*  37 */   private final Hashtable _primops = new Hashtable();
/*     */ 
/*     */   
/*  40 */   private Hashtable _variables = null;
/*  41 */   private Hashtable _templates = null;
/*  42 */   private Hashtable _attributeSets = null;
/*  43 */   private Hashtable _aliases = null;
/*  44 */   private Hashtable _excludedURI = null;
/*  45 */   private Hashtable _decimalFormats = null;
/*     */   
/*     */   public DecimalFormatting getDecimalFormatting(QName name) {
/*  48 */     if (this._decimalFormats == null) return null; 
/*  49 */     return (DecimalFormatting)this._decimalFormats.get(name);
/*     */   }
/*     */   
/*     */   public void addDecimalFormatting(QName name, DecimalFormatting symbols) {
/*  53 */     if (this._decimalFormats == null) this._decimalFormats = new Hashtable(); 
/*  54 */     this._decimalFormats.put(name, symbols);
/*     */   }
/*     */   
/*     */   public Stylesheet addStylesheet(QName name, Stylesheet node) {
/*  58 */     return this._stylesheets.put(name, node);
/*     */   }
/*     */   
/*     */   public Stylesheet lookupStylesheet(QName name) {
/*  62 */     return (Stylesheet)this._stylesheets.get(name);
/*     */   }
/*     */   
/*     */   public Template addTemplate(Template template) {
/*  66 */     QName name = template.getName();
/*  67 */     if (this._templates == null) this._templates = new Hashtable(); 
/*  68 */     return this._templates.put(name, template);
/*     */   }
/*     */   
/*     */   public Template lookupTemplate(QName name) {
/*  72 */     if (this._templates == null) return null; 
/*  73 */     return (Template)this._templates.get(name);
/*     */   }
/*     */   
/*     */   public Variable addVariable(Variable variable) {
/*  77 */     if (this._variables == null) this._variables = new Hashtable(); 
/*  78 */     String name = variable.getName().getStringRep();
/*  79 */     return this._variables.put(name, variable);
/*     */   }
/*     */   
/*     */   public Param addParam(Param parameter) {
/*  83 */     if (this._variables == null) this._variables = new Hashtable(); 
/*  84 */     String name = parameter.getName().getStringRep();
/*  85 */     return this._variables.put(name, parameter);
/*     */   }
/*     */   
/*     */   public Variable lookupVariable(QName qname) {
/*  89 */     if (this._variables == null) return null; 
/*  90 */     String name = qname.getStringRep();
/*  91 */     Object obj = this._variables.get(name);
/*  92 */     return (obj instanceof Variable) ? (Variable)obj : null;
/*     */   }
/*     */   
/*     */   public Param lookupParam(QName qname) {
/*  96 */     if (this._variables == null) return null; 
/*  97 */     String name = qname.getStringRep();
/*  98 */     Object obj = this._variables.get(name);
/*  99 */     return (obj instanceof Param) ? (Param)obj : null;
/*     */   }
/*     */   
/*     */   public SyntaxTreeNode lookupName(QName qname) {
/* 103 */     if (this._variables == null) return null; 
/* 104 */     String name = qname.getStringRep();
/* 105 */     return (SyntaxTreeNode)this._variables.get(name);
/*     */   }
/*     */   
/*     */   public AttributeSet addAttributeSet(AttributeSet atts) {
/* 109 */     if (this._attributeSets == null) this._attributeSets = new Hashtable(); 
/* 110 */     return this._attributeSets.put(atts.getName(), atts);
/*     */   }
/*     */   
/*     */   public AttributeSet lookupAttributeSet(QName name) {
/* 114 */     if (this._attributeSets == null) return null; 
/* 115 */     return (AttributeSet)this._attributeSets.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPrimop(String name, MethodType mtype) {
/* 124 */     Vector methods = (Vector)this._primops.get(name);
/* 125 */     if (methods == null) {
/* 126 */       this._primops.put(name, methods = new Vector());
/*     */     }
/* 128 */     methods.addElement(mtype);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector lookupPrimop(String name) {
/* 136 */     return (Vector)this._primops.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   private int _nsCounter = 0;
/*     */   
/*     */   public String generateNamespacePrefix() {
/* 146 */     return new String("ns" + this._nsCounter++);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   private SyntaxTreeNode _current = null;
/*     */   
/*     */   public void setCurrentNode(SyntaxTreeNode node) {
/* 155 */     this._current = node;
/*     */   }
/*     */   
/*     */   public String lookupNamespace(String prefix) {
/* 159 */     if (this._current == null) return ""; 
/* 160 */     return this._current.lookupNamespace(prefix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPrefixAlias(String prefix, String alias) {
/* 167 */     if (this._aliases == null) this._aliases = new Hashtable(); 
/* 168 */     this._aliases.put(prefix, alias);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String lookupPrefixAlias(String prefix) {
/* 175 */     if (this._aliases == null) return null; 
/* 176 */     return (String)this._aliases.get(prefix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void excludeURI(String uri) {
/* 185 */     if (uri == null) {
/*     */       return;
/*     */     }
/* 188 */     if (this._excludedURI == null) this._excludedURI = new Hashtable();
/*     */ 
/*     */     
/* 191 */     Integer refcnt = (Integer)this._excludedURI.get(uri);
/* 192 */     if (refcnt == null) {
/* 193 */       refcnt = new Integer(1);
/*     */     } else {
/* 195 */       refcnt = new Integer(refcnt.intValue() + 1);
/* 196 */     }  this._excludedURI.put(uri, refcnt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void excludeNamespaces(String prefixes) {
/* 204 */     if (prefixes != null) {
/* 205 */       StringTokenizer tokens = new StringTokenizer(prefixes);
/* 206 */       while (tokens.hasMoreTokens()) {
/* 207 */         String str1, prefix = tokens.nextToken();
/*     */         
/* 209 */         if (prefix.equals("#default")) {
/* 210 */           str1 = lookupNamespace("");
/*     */         } else {
/* 212 */           str1 = lookupNamespace(prefix);
/* 213 */         }  if (str1 != null) excludeURI(str1);
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExcludedNamespace(String uri) {
/* 222 */     if (uri != null && this._excludedURI != null) {
/* 223 */       Integer refcnt = (Integer)this._excludedURI.get(uri);
/* 224 */       return (refcnt != null && refcnt.intValue() > 0);
/*     */     } 
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unExcludeNamespaces(String prefixes) {
/* 233 */     if (this._excludedURI == null)
/* 234 */       return;  if (prefixes != null) {
/* 235 */       StringTokenizer tokens = new StringTokenizer(prefixes);
/* 236 */       while (tokens.hasMoreTokens()) {
/* 237 */         String str1, prefix = tokens.nextToken();
/*     */         
/* 239 */         if (prefix.equals("#default")) {
/* 240 */           str1 = lookupNamespace("");
/*     */         } else {
/* 242 */           str1 = lookupNamespace(prefix);
/* 243 */         }  Integer refcnt = (Integer)this._excludedURI.get(str1);
/* 244 */         if (refcnt != null)
/* 245 */           this._excludedURI.put(str1, new Integer(refcnt.intValue() - 1)); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/SymbolTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */