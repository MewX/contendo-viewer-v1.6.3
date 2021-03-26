/*     */ package org.apache.xalan.xsltc.cmdline;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Vector;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.DOMEnhancedForDTM;
/*     */ import org.apache.xalan.xsltc.TransletException;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.dom.DOMWSFilter;
/*     */ import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*     */ import org.apache.xalan.xsltc.runtime.Parameter;
/*     */ import org.apache.xalan.xsltc.runtime.output.TransletOutputHandlerFactory;
/*     */ import org.apache.xml.dtm.DTMWSFilter;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
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
/*     */ 
/*     */ public final class Transform
/*     */ {
/*     */   private SerializationHandler _handler;
/*     */   private String _fileName;
/*     */   private String _className;
/*     */   private String _jarFileSrc;
/*     */   private boolean _isJarFileSpecified = false;
/*  63 */   private Vector _params = null;
/*     */   
/*     */   private boolean _uri;
/*     */   private boolean _debug;
/*     */   private int _iterations;
/*     */   private static boolean _allowExit = true;
/*     */   
/*     */   public Transform(String className, String fileName, boolean uri, boolean debug, int iterations) {
/*  71 */     this._fileName = fileName;
/*  72 */     this._className = className;
/*  73 */     this._uri = uri;
/*  74 */     this._debug = debug;
/*  75 */     this._iterations = iterations;
/*     */   }
/*     */   
/*  78 */   public String getFileName() { return this._fileName; } public String getClassName() {
/*  79 */     return this._className;
/*     */   }
/*     */   public void setParameters(Vector params) {
/*  82 */     this._params = params;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setJarFileInputSrc(boolean flag, String jarFile) {
/*  91 */     this._isJarFileSpecified = flag;
/*     */     
/*  93 */     this._jarFileSrc = jarFile;
/*     */   }
/*     */   private void doTransform() {
/*     */     
/*     */     try { DTMWSFilter dTMWSFilter;
/*  98 */       Class clazz = ObjectFactory.findProviderClass(this._className, ObjectFactory.findClassLoader(), true);
/*     */       
/* 100 */       AbstractTranslet translet = clazz.newInstance();
/* 101 */       translet.postInitialization();
/*     */ 
/*     */       
/* 104 */       SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */       
/* 106 */       try { factory.setFeature("http://xml.org/sax/features/namespaces", true); } catch (Exception e)
/*     */       
/*     */       { 
/* 109 */         factory.setNamespaceAware(true); }
/*     */       
/* 111 */       SAXParser parser = factory.newSAXParser();
/* 112 */       XMLReader reader = parser.getXMLReader();
/*     */ 
/*     */       
/* 115 */       XSLTCDTMManager dtmManager = XSLTCDTMManager.getDTMManagerClass().newInstance();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 120 */       if (translet != null && translet instanceof org.apache.xalan.xsltc.StripFilter) {
/* 121 */         dTMWSFilter = (DTMWSFilter)new DOMWSFilter(translet);
/*     */       } else {
/* 123 */         dTMWSFilter = null;
/*     */       } 
/*     */       
/* 126 */       DOMEnhancedForDTM dom = (DOMEnhancedForDTM)dtmManager.getDTM(new SAXSource(reader, new InputSource(this._fileName)), false, dTMWSFilter, true, false, translet.hasIdCall());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 131 */       dom.setDocumentURI(this._fileName);
/* 132 */       translet.prepassDocument((DOM)dom);
/*     */ 
/*     */       
/* 135 */       int n = this._params.size();
/* 136 */       for (int i = 0; i < n; i++) {
/* 137 */         Parameter param = this._params.elementAt(i);
/* 138 */         translet.addParameter(param._name, param._value);
/*     */       } 
/*     */ 
/*     */       
/* 142 */       TransletOutputHandlerFactory tohFactory = TransletOutputHandlerFactory.newInstance();
/*     */       
/* 144 */       tohFactory.setOutputType(0);
/* 145 */       tohFactory.setEncoding(translet._encoding);
/* 146 */       tohFactory.setOutputMethod(translet._method);
/*     */       
/* 148 */       if (this._iterations == -1)
/* 149 */       { translet.transform((DOM)dom, tohFactory.getSerializationHandler()); }
/*     */       
/* 151 */       else if (this._iterations > 0)
/* 152 */       { long mm = System.currentTimeMillis();
/* 153 */         for (int j = 0; j < this._iterations; j++) {
/* 154 */           translet.transform((DOM)dom, tohFactory.getSerializationHandler());
/*     */         }
/*     */         
/* 157 */         mm = System.currentTimeMillis() - mm;
/*     */         
/* 159 */         System.err.println("\n<!--");
/* 160 */         System.err.println("  transform  = " + (mm / this._iterations) + " ms");
/*     */ 
/*     */         
/* 163 */         System.err.println("  throughput = " + (1000.0D / mm / this._iterations) + " tps");
/*     */ 
/*     */ 
/*     */         
/* 167 */         System.err.println("-->"); }  } catch (TransletException e)
/*     */     
/*     */     { 
/*     */       
/* 171 */       if (this._debug) e.printStackTrace(); 
/* 172 */       System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + e.getMessage());
/*     */       
/* 174 */       if (_allowExit) System.exit(-1);  } catch (RuntimeException e)
/*     */     
/*     */     { 
/* 177 */       if (this._debug) e.printStackTrace(); 
/* 178 */       System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + e.getMessage());
/*     */       
/* 180 */       if (_allowExit) System.exit(-1);  } catch (FileNotFoundException e)
/*     */     
/*     */     { 
/* 183 */       if (this._debug) e.printStackTrace(); 
/* 184 */       ErrorMsg err = new ErrorMsg("FILE_NOT_FOUND_ERR", this._fileName);
/* 185 */       System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + err.toString());
/*     */       
/* 187 */       if (_allowExit) System.exit(-1);  } catch (MalformedURLException e)
/*     */     
/*     */     { 
/* 190 */       if (this._debug) e.printStackTrace(); 
/* 191 */       ErrorMsg err = new ErrorMsg("INVALID_URI_ERR", this._fileName);
/* 192 */       System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + err.toString());
/*     */       
/* 194 */       if (_allowExit) System.exit(-1);  } catch (ClassNotFoundException e)
/*     */     
/*     */     { 
/* 197 */       if (this._debug) e.printStackTrace(); 
/* 198 */       ErrorMsg err = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
/* 199 */       System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + err.toString());
/*     */       
/* 201 */       if (_allowExit) System.exit(-1);  } catch (UnknownHostException e)
/*     */     
/*     */     { 
/* 204 */       if (this._debug) e.printStackTrace(); 
/* 205 */       ErrorMsg err = new ErrorMsg("INVALID_URI_ERR", this._fileName);
/* 206 */       System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + err.toString());
/*     */       
/* 208 */       if (_allowExit) System.exit(-1);  } catch (SAXException e)
/*     */     
/*     */     { 
/* 211 */       Exception ex = e.getException();
/* 212 */       if (this._debug) {
/* 213 */         if (ex != null) ex.printStackTrace(); 
/* 214 */         e.printStackTrace();
/*     */       } 
/* 216 */       System.err.print(new ErrorMsg("RUNTIME_ERROR_KEY"));
/* 217 */       if (ex != null) {
/* 218 */         System.err.println(ex.getMessage());
/*     */       } else {
/* 220 */         System.err.println(e.getMessage());
/* 221 */       }  if (_allowExit) System.exit(-1);  } catch (Exception e)
/*     */     
/*     */     { 
/* 224 */       if (this._debug) e.printStackTrace(); 
/* 225 */       System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + e.getMessage());
/*     */       
/* 227 */       if (_allowExit) System.exit(-1);  }
/*     */   
/*     */   }
/*     */   
/*     */   public static void printUsage() {
/* 232 */     System.err.println(new ErrorMsg("TRANSFORM_USAGE_STR"));
/* 233 */     if (_allowExit) System.exit(-1); 
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/*     */     
/* 238 */     try { if (args.length > 0)
/*     */       
/* 240 */       { int iterations = -1;
/* 241 */         boolean uri = false, debug = false;
/* 242 */         boolean isJarFileSpecified = false;
/* 243 */         String jarFile = null;
/*     */         
/*     */         int i;
/* 246 */         for (i = 0; i < args.length && args[i].charAt(0) == '-'; i++) {
/* 247 */           if (args[i].equals("-u")) {
/* 248 */             uri = true;
/*     */           }
/* 250 */           else if (args[i].equals("-x")) {
/* 251 */             debug = true;
/*     */           }
/* 253 */           else if (args[i].equals("-s")) {
/* 254 */             _allowExit = false;
/*     */           }
/* 256 */           else if (args[i].equals("-j")) {
/* 257 */             isJarFileSpecified = true;
/* 258 */             jarFile = args[++i];
/*     */           }
/* 260 */           else if (args[i].equals("-n")) {
/*     */             
/* 262 */             try { iterations = Integer.parseInt(args[++i]); } catch (NumberFormatException numberFormatException) {}
/*     */ 
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 269 */             printUsage();
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 274 */         if (args.length - i < 2) printUsage();
/*     */ 
/*     */         
/* 277 */         Transform handler = new Transform(args[i + 1], args[i], uri, debug, iterations);
/*     */         
/* 279 */         handler.setJarFileInputSrc(isJarFileSpecified, jarFile);
/*     */ 
/*     */         
/* 282 */         Vector params = new Vector();
/* 283 */         for (i += 2; i < args.length; i++) {
/* 284 */           int equal = args[i].indexOf('=');
/* 285 */           if (equal > 0) {
/* 286 */             String name = args[i].substring(0, equal);
/* 287 */             String value = args[i].substring(equal + 1);
/* 288 */             params.addElement(new Parameter(name, value));
/*     */           } else {
/*     */             
/* 291 */             printUsage();
/*     */           } 
/*     */         } 
/*     */         
/* 295 */         if (i == args.length) {
/* 296 */           handler.setParameters(params);
/* 297 */           handler.doTransform();
/* 298 */           if (_allowExit) System.exit(0); 
/*     */         }  }
/*     */       else
/* 301 */       { printUsage(); }  } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 305 */       e.printStackTrace(); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/cmdline/Transform.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */