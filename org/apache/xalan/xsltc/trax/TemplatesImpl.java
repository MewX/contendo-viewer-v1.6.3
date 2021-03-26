/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Properties;
/*     */ import javax.xml.transform.Templates;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.Translet;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*     */ import org.apache.xalan.xsltc.runtime.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TemplatesImpl
/*     */   implements Serializable, Templates
/*     */ {
/*  54 */   private static String ABSTRACT_TRANSLET = "org.apache.xalan.xsltc.runtime.AbstractTranslet";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private String _name = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private byte[][] _bytecodes = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   private Class[] _class = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   private int _transletIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private Hashtable _auxClasses = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Properties _outputProperties;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int _indentNumber;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   private transient URIResolver _uriResolver = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   private transient ThreadLocal _sdom = new ThreadLocal();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   private transient TransformerFactoryImpl _tfactory = null;
/*     */   
/*     */   static final class TransletClassLoader extends ClassLoader {
/*     */     TransletClassLoader(ClassLoader parent) {
/* 118 */       super(parent);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Class defineClass(byte[] b) {
/* 125 */       return defineClass(null, b, 0, b.length);
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
/*     */   protected TemplatesImpl(byte[][] bytecodes, String transletName, Properties outputProperties, int indentNumber, TransformerFactoryImpl tfactory) {
/* 139 */     this._bytecodes = bytecodes;
/* 140 */     this._name = transletName;
/* 141 */     this._outputProperties = outputProperties;
/* 142 */     this._indentNumber = indentNumber;
/* 143 */     this._tfactory = tfactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TemplatesImpl(Class[] transletClasses, String transletName, Properties outputProperties, int indentNumber, TransformerFactoryImpl tfactory) {
/* 153 */     this._class = transletClasses;
/* 154 */     this._name = transletName;
/* 155 */     this._transletIndex = 0;
/* 156 */     this._outputProperties = outputProperties;
/* 157 */     this._indentNumber = indentNumber;
/* 158 */     this._tfactory = tfactory;
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
/*     */   public TemplatesImpl() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
/* 179 */     is.defaultReadObject();
/* 180 */     if (is.readBoolean()) {
/* 181 */       this._uriResolver = (URIResolver)is.readObject();
/*     */     }
/*     */     
/* 184 */     this._tfactory = new TransformerFactoryImpl();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream os) throws IOException, ClassNotFoundException {
/* 195 */     os.defaultWriteObject();
/* 196 */     if (this._uriResolver instanceof Serializable) {
/* 197 */       os.writeBoolean(true);
/* 198 */       os.writeObject(this._uriResolver);
/*     */     } else {
/*     */       
/* 201 */       os.writeBoolean(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setURIResolver(URIResolver resolver) {
/* 210 */     this._uriResolver = resolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void setTransletBytecodes(byte[][] bytecodes) {
/* 218 */     this._bytecodes = bytecodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized byte[][] getTransletBytecodes() {
/* 225 */     return this._bytecodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Class[] getTransletClasses() {
/*     */     
/* 233 */     try { if (this._class == null) defineTransletClasses();  } catch (TransformerConfigurationException transformerConfigurationException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     return this._class;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int getTransletIndex() {
/*     */     
/* 246 */     try { if (this._class == null) defineTransletClasses();  } catch (TransformerConfigurationException transformerConfigurationException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     return this._transletIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void setTransletName(String name) {
/* 258 */     this._name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized String getTransletName() {
/* 265 */     return this._name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void defineTransletClasses() throws TransformerConfigurationException {
/* 275 */     if (this._bytecodes == null) {
/* 276 */       ErrorMsg err = new ErrorMsg("NO_TRANSLET_CLASS_ERR");
/* 277 */       throw new TransformerConfigurationException(err.toString());
/*     */     } 
/*     */     
/* 280 */     TransletClassLoader loader = AccessController.<TransletClassLoader>doPrivileged(new PrivilegedAction(this) { private final TemplatesImpl this$0;
/*     */           
/*     */           public Object run() {
/* 283 */             return new TemplatesImpl.TransletClassLoader(ObjectFactory.findClassLoader());
/*     */           } }
/*     */       );
/*     */ 
/*     */     
/* 288 */     try { int classCount = this._bytecodes.length;
/* 289 */       this._class = new Class[classCount];
/*     */       
/* 291 */       if (classCount > 1) {
/* 292 */         this._auxClasses = new Hashtable();
/*     */       }
/*     */       
/* 295 */       for (int i = 0; i < classCount; i++) {
/* 296 */         this._class[i] = loader.defineClass(this._bytecodes[i]);
/* 297 */         Class superClass = this._class[i].getSuperclass();
/*     */ 
/*     */         
/* 300 */         if (superClass.getName().equals(ABSTRACT_TRANSLET)) {
/* 301 */           this._transletIndex = i;
/*     */         } else {
/*     */           
/* 304 */           this._auxClasses.put(this._class[i].getName(), this._class[i]);
/*     */         } 
/*     */       } 
/*     */       
/* 308 */       if (this._transletIndex < 0)
/* 309 */       { ErrorMsg err = new ErrorMsg("NO_MAIN_TRANSLET_ERR", this._name);
/* 310 */         throw new TransformerConfigurationException(err.toString()); }  } catch (ClassFormatError e)
/*     */     
/*     */     { 
/*     */       
/* 314 */       ErrorMsg err = new ErrorMsg("TRANSLET_CLASS_ERR", this._name);
/* 315 */       throw new TransformerConfigurationException(err.toString()); } catch (LinkageError e)
/*     */     
/*     */     { 
/* 318 */       ErrorMsg err = new ErrorMsg("TRANSLET_OBJECT_ERR", this._name);
/* 319 */       throw new TransformerConfigurationException(err.toString()); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Translet getTransletInstance() throws TransformerConfigurationException {
/*     */     
/* 331 */     try { if (this._name == null) return null;
/*     */       
/* 333 */       if (this._class == null) defineTransletClasses();
/*     */ 
/*     */ 
/*     */       
/* 337 */       AbstractTranslet translet = this._class[this._transletIndex].newInstance();
/* 338 */       translet.postInitialization();
/* 339 */       translet.setTemplates(this);
/* 340 */       if (this._auxClasses != null) {
/* 341 */         translet.setAuxiliaryClasses(this._auxClasses);
/*     */       }
/*     */       
/* 344 */       return (Translet)translet; } catch (InstantiationException e)
/*     */     
/*     */     { 
/* 347 */       ErrorMsg err = new ErrorMsg("TRANSLET_OBJECT_ERR", this._name);
/* 348 */       throw new TransformerConfigurationException(err.toString()); } catch (IllegalAccessException e)
/*     */     
/*     */     { 
/* 351 */       ErrorMsg err = new ErrorMsg("TRANSLET_OBJECT_ERR", this._name);
/* 352 */       throw new TransformerConfigurationException(err.toString()); }
/*     */   
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
/*     */   public synchronized Transformer newTransformer() throws TransformerConfigurationException {
/* 366 */     TransformerImpl transformer = new TransformerImpl(getTransletInstance(), this._outputProperties, this._indentNumber, this._tfactory);
/*     */ 
/*     */     
/* 369 */     if (this._uriResolver != null) {
/* 370 */       transformer.setURIResolver(this._uriResolver);
/*     */     }
/* 372 */     return transformer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Properties getOutputProperties() {
/*     */     
/* 383 */     try { return newTransformer().getOutputProperties(); } catch (TransformerConfigurationException e)
/*     */     
/*     */     { 
/* 386 */       return null; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DOM getStylesheetDOM() {
/* 394 */     return this._sdom.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStylesheetDOM(DOM sdom) {
/* 401 */     this._sdom.set(sdom);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/TemplatesImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */