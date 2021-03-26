/*      */ package org.apache.xalan.xslt;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileWriter;
/*      */ import java.io.PrintWriter;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.Attributes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EnvironmentCheck
/*      */ {
/*      */   public static final String ERROR = "ERROR.";
/*      */   public static final String WARNING = "WARNING.";
/*      */   public static final String ERROR_FOUND = "At least one error was found!";
/*      */   public static final String VERSION = "version.";
/*      */   public static final String FOUNDCLASSES = "foundclasses.";
/*      */   public static final String CLASS_PRESENT = "present-unknown-version";
/*      */   public static final String CLASS_NOTPRESENT = "not-present";
/*      */   
/*      */   public static void main(String[] args) {
/*  102 */     PrintWriter sendOutputTo = new PrintWriter(System.out, true);
/*      */ 
/*      */     
/*  105 */     for (int i = 0; i < args.length; i++) {
/*      */       
/*  107 */       if ("-out".equalsIgnoreCase(args[i])) {
/*      */         
/*  109 */         i++;
/*      */         
/*  111 */         if (i < args.length) {
/*      */ 
/*      */ 
/*      */           
/*  115 */           try { sendOutputTo = new PrintWriter(new FileWriter(args[i], true)); } catch (Exception e)
/*      */           
/*      */           { 
/*      */             
/*  119 */             System.err.println("# WARNING: -out " + args[i] + " threw " + e.toString()); }
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  125 */           System.err.println("# WARNING: -out argument should have a filename, output sent to console");
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  131 */     EnvironmentCheck app = new EnvironmentCheck();
/*  132 */     app.checkEnvironment(sendOutputTo);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkEnvironment(PrintWriter pw) {
/*  163 */     if (null != pw) {
/*  164 */       this.outWriter = pw;
/*      */     }
/*      */     
/*  167 */     Hashtable hash = getEnvironmentHash();
/*      */ 
/*      */     
/*  170 */     boolean environmentHasErrors = writeEnvironmentReport(hash);
/*      */     
/*  172 */     if (environmentHasErrors) {
/*      */ 
/*      */ 
/*      */       
/*  176 */       logMsg("# WARNING: Potential problems found in your environment!");
/*  177 */       logMsg("#    Check any 'ERROR' items above against the Xalan FAQs");
/*  178 */       logMsg("#    to correct potential problems with your classes/jars");
/*  179 */       logMsg("#    http://xml.apache.org/xalan-j/faq.html");
/*  180 */       if (null != this.outWriter)
/*  181 */         this.outWriter.flush(); 
/*  182 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  186 */     logMsg("# YAHOO! Your environment seems to be OK.");
/*  187 */     if (null != this.outWriter)
/*  188 */       this.outWriter.flush(); 
/*  189 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Hashtable getEnvironmentHash() {
/*  216 */     Hashtable hash = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  221 */     checkJAXPVersion(hash);
/*  222 */     checkProcessorVersion(hash);
/*  223 */     checkParserVersion(hash);
/*  224 */     checkAntVersion(hash);
/*  225 */     checkDOMVersion(hash);
/*  226 */     checkSAXVersion(hash);
/*  227 */     checkSystemProperties(hash);
/*      */     
/*  229 */     return hash;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean writeEnvironmentReport(Hashtable h) {
/*  249 */     if (null == h) {
/*      */       
/*  251 */       logMsg("# ERROR: writeEnvironmentReport called with null Hashtable");
/*  252 */       return false;
/*      */     } 
/*      */     
/*  255 */     boolean errors = false;
/*      */     
/*  257 */     logMsg("#---- BEGIN writeEnvironmentReport($Revision: 1.26 $): Useful stuff found: ----");
/*      */ 
/*      */ 
/*      */     
/*  261 */     Enumeration keys = h.keys();
/*  262 */     while (keys.hasMoreElements()) {
/*      */ 
/*      */ 
/*      */       
/*  266 */       Object key = keys.nextElement();
/*  267 */       String keyStr = (String)key;
/*      */ 
/*      */ 
/*      */       
/*  271 */       try { if (keyStr.startsWith("foundclasses.")) {
/*      */           
/*  273 */           Vector v = (Vector)h.get(keyStr);
/*  274 */           errors |= logFoundJars(v, keyStr);
/*      */ 
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  283 */         if (keyStr.startsWith("ERROR."))
/*      */         {
/*  285 */           errors = true;
/*      */         }
/*  287 */         logMsg(keyStr + "=" + h.get(keyStr)); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/*  292 */         logMsg("Reading-" + key + "= threw: " + e.toString()); }
/*      */     
/*      */     } 
/*      */     
/*  296 */     logMsg("#----- END writeEnvironmentReport: Useful properties found: -----");
/*      */ 
/*      */     
/*  299 */     return errors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  324 */   public String[] jarNames = new String[] { "xalan.jar", "xalansamples.jar", "xalanj1compat.jar", "xalanservlet.jar", "xerces.jar", "xercesImpl.jar", "testxsl.jar", "crimson.jar", "lotusxsl.jar", "jaxp.jar", "parser.jar", "dom.jar", "sax.jar", "xml.jar", "xml-apis.jar", "xsltc.jar" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean logFoundJars(Vector v, String desc) {
/*  353 */     if (null == v || v.size() < 1) {
/*  354 */       return false;
/*      */     }
/*  356 */     boolean errors = false;
/*      */     
/*  358 */     logMsg("#---- BEGIN Listing XML-related jars in: " + desc + " ----");
/*      */     
/*  360 */     for (int i = 0; i < v.size(); i++) {
/*      */       
/*  362 */       Hashtable subhash = v.elementAt(i);
/*      */       
/*  364 */       Enumeration keys = subhash.keys();
/*  365 */       while (keys.hasMoreElements()) {
/*      */ 
/*      */ 
/*      */         
/*  369 */         Object key = keys.nextElement();
/*  370 */         String keyStr = (String)key;
/*      */ 
/*      */         
/*  373 */         try { if (keyStr.startsWith("ERROR."))
/*      */           {
/*  375 */             errors = true;
/*      */           }
/*  377 */           logMsg(keyStr + "=" + subhash.get(keyStr)); } catch (Exception e)
/*      */         
/*      */         { 
/*      */ 
/*      */           
/*  382 */           errors = true;
/*  383 */           logMsg("Reading-" + key + "= threw: " + e.toString()); }
/*      */       
/*      */       } 
/*      */     } 
/*      */     
/*  388 */     logMsg("#----- END Listing XML-related jars in: " + desc + " -----");
/*      */     
/*  390 */     return errors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendEnvironmentReport(Node container, Document factory, Hashtable h) {
/*  408 */     if (null == container || null == factory) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  415 */     try { Element envCheckNode = factory.createElement("EnvironmentCheck");
/*  416 */       envCheckNode.setAttribute("version", "$Revision: 1.26 $");
/*  417 */       container.appendChild(envCheckNode);
/*      */       
/*  419 */       if (null == h) {
/*      */         
/*  421 */         Element element = factory.createElement("status");
/*  422 */         element.setAttribute("result", "ERROR");
/*  423 */         element.appendChild(factory.createTextNode("appendEnvironmentReport called with null Hashtable!"));
/*  424 */         envCheckNode.appendChild(element);
/*      */         
/*      */         return;
/*      */       } 
/*  428 */       boolean errors = false;
/*      */       
/*  430 */       Element hashNode = factory.createElement("environment");
/*  431 */       envCheckNode.appendChild(hashNode);
/*      */       
/*  433 */       Enumeration keys = h.keys();
/*  434 */       while (keys.hasMoreElements()) {
/*      */ 
/*      */ 
/*      */         
/*  438 */         Object key = keys.nextElement();
/*  439 */         String keyStr = (String)key;
/*      */ 
/*      */ 
/*      */         
/*  443 */         try { if (keyStr.startsWith("foundclasses.")) {
/*      */             
/*  445 */             Vector v = (Vector)h.get(keyStr);
/*      */             
/*  447 */             errors |= appendFoundJars(hashNode, factory, v, keyStr);
/*      */ 
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  456 */           if (keyStr.startsWith("ERROR."))
/*      */           {
/*  458 */             errors = true;
/*      */           }
/*  460 */           Element node = factory.createElement("item");
/*  461 */           node.setAttribute("key", keyStr);
/*  462 */           node.appendChild(factory.createTextNode((String)h.get(keyStr)));
/*  463 */           hashNode.appendChild(node); } catch (Exception e)
/*      */         
/*      */         { 
/*      */ 
/*      */           
/*  468 */           errors = true;
/*  469 */           Element node = factory.createElement("item");
/*  470 */           node.setAttribute("key", keyStr);
/*  471 */           node.appendChild(factory.createTextNode("ERROR. Reading " + key + " threw: " + e.toString()));
/*  472 */           hashNode.appendChild(node); }
/*      */       
/*      */       } 
/*      */       
/*  476 */       Element statusNode = factory.createElement("status");
/*  477 */       statusNode.setAttribute("result", errors ? "ERROR" : "OK");
/*  478 */       envCheckNode.appendChild(statusNode); } catch (Exception e2)
/*      */     
/*      */     { 
/*      */       
/*  482 */       System.err.println("appendEnvironmentReport threw: " + e2.toString());
/*  483 */       e2.printStackTrace(); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean appendFoundJars(Node container, Document factory, Vector v, String desc) {
/*  506 */     if (null == v || v.size() < 1) {
/*  507 */       return false;
/*      */     }
/*  509 */     boolean errors = false;
/*      */     
/*  511 */     for (int i = 0; i < v.size(); i++) {
/*      */       
/*  513 */       Hashtable subhash = v.elementAt(i);
/*      */       
/*  515 */       Enumeration keys = subhash.keys();
/*  516 */       while (keys.hasMoreElements()) {
/*      */ 
/*      */ 
/*      */         
/*  520 */         Object key = keys.nextElement();
/*      */ 
/*      */         
/*  523 */         try { String keyStr = (String)key;
/*  524 */           if (keyStr.startsWith("ERROR."))
/*      */           {
/*  526 */             errors = true;
/*      */           }
/*  528 */           Element node = factory.createElement("foundJar");
/*  529 */           node.setAttribute("name", keyStr.substring(0, keyStr.indexOf("-")));
/*  530 */           node.setAttribute("desc", keyStr.substring(keyStr.indexOf("-") + 1));
/*  531 */           node.appendChild(factory.createTextNode((String)subhash.get(keyStr)));
/*  532 */           container.appendChild(node); } catch (Exception e)
/*      */         
/*      */         { 
/*      */           
/*  536 */           errors = true;
/*  537 */           Element node = factory.createElement("foundJar");
/*  538 */           node.appendChild(factory.createTextNode("ERROR. Reading " + key + " threw: " + e.toString()));
/*  539 */           container.appendChild(node); }
/*      */       
/*      */       } 
/*      */     } 
/*  543 */     return errors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkSystemProperties(Hashtable h) {
/*  562 */     if (null == h) {
/*  563 */       h = new Hashtable();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  568 */     try { String javaVersion = System.getProperty("java.version");
/*      */       
/*  570 */       h.put("java.version", javaVersion); } catch (SecurityException se)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/*  576 */       h.put("java.version", "WARNING: SecurityException thrown accessing system version properties"); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  587 */     try { String cp = System.getProperty("java.class.path");
/*      */       
/*  589 */       h.put("java.class.path", cp);
/*      */       
/*  591 */       Vector classpathJars = checkPathForJars(cp, this.jarNames);
/*      */       
/*  593 */       if (null != classpathJars) {
/*  594 */         h.put("foundclasses.java.class.path", classpathJars);
/*      */       }
/*      */       
/*  597 */       String othercp = System.getProperty("sun.boot.class.path");
/*      */       
/*  599 */       if (null != othercp) {
/*      */         
/*  601 */         h.put("sun.boot.class.path", othercp);
/*      */         
/*  603 */         classpathJars = checkPathForJars(othercp, this.jarNames);
/*      */         
/*  605 */         if (null != classpathJars) {
/*  606 */           h.put("foundclasses.sun.boot.class.path", classpathJars);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  611 */       othercp = System.getProperty("java.ext.dirs");
/*      */       
/*  613 */       if (null != othercp)
/*      */       
/*  615 */       { h.put("java.ext.dirs", othercp);
/*      */         
/*  617 */         classpathJars = checkPathForJars(othercp, this.jarNames);
/*      */         
/*  619 */         if (null != classpathJars)
/*  620 */           h.put("foundclasses.java.ext.dirs", classpathJars);  }  } catch (SecurityException se2)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  630 */       h.put("java.class.path", "WARNING: SecurityException thrown accessing system classpath properties"); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Vector checkPathForJars(String cp, String[] jars) {
/*  656 */     if (null == cp || null == jars || 0 == cp.length() || 0 == jars.length)
/*      */     {
/*  658 */       return null;
/*      */     }
/*  660 */     Vector v = new Vector();
/*  661 */     StringTokenizer st = new StringTokenizer(cp, File.pathSeparator);
/*      */     
/*  663 */     while (st.hasMoreTokens()) {
/*      */ 
/*      */ 
/*      */       
/*  667 */       String filename = st.nextToken();
/*      */       
/*  669 */       for (int i = 0; i < jars.length; i++) {
/*      */         
/*  671 */         if (filename.indexOf(jars[i]) > -1) {
/*      */           
/*  673 */           File f = new File(filename);
/*      */           
/*  675 */           if (f.exists()) {
/*      */ 
/*      */ 
/*      */             
/*      */             try { 
/*      */ 
/*      */               
/*  682 */               Hashtable h = new Hashtable(2);
/*      */               
/*  684 */               h.put(jars[i] + "-path", f.getAbsolutePath());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  691 */               if (!"xalan.jar".equalsIgnoreCase(jars[i])) {
/*  692 */                 h.put(jars[i] + "-apparent.version", getApparentVersion(jars[i], f.length()));
/*      */               }
/*      */               
/*  695 */               v.addElement(h); } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  705 */             Hashtable h = new Hashtable(2);
/*      */             
/*  707 */             h.put(jars[i] + "-path", "WARNING. Classpath entry: " + filename + " does not exist");
/*      */             
/*  709 */             h.put(jars[i] + "-apparent.version", "not-present");
/*  710 */             v.addElement(h);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  716 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getApparentVersion(String jarName, long jarSize) {
/*  742 */     String foundSize = (String)jarVersions.get(new Long(jarSize));
/*      */     
/*  744 */     if (null != foundSize && foundSize.startsWith(jarName))
/*      */     {
/*  746 */       return foundSize;
/*      */     }
/*      */ 
/*      */     
/*  750 */     if ("xerces.jar".equalsIgnoreCase(jarName) || "xercesImpl.jar".equalsIgnoreCase(jarName))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  758 */       return jarName + " " + "WARNING." + "present-unknown-version";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  764 */     return jarName + " " + "present-unknown-version";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkJAXPVersion(Hashtable h) {
/*  781 */     if (null == h) {
/*  782 */       h = new Hashtable();
/*      */     }
/*  784 */     Class[] noArgs = new Class[0];
/*  785 */     Class clazz = null;
/*      */ 
/*      */ 
/*      */     
/*  789 */     try { String JAXP1_CLASS = "javax.xml.parsers.DocumentBuilder";
/*  790 */       String JAXP11_METHOD = "getDOMImplementation";
/*      */       
/*  792 */       clazz = ObjectFactory.findProviderClass("javax.xml.parsers.DocumentBuilder", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */       
/*  795 */       Method method = clazz.getMethod("getDOMImplementation", noArgs);
/*      */ 
/*      */       
/*  798 */       h.put("version.JAXP", "1.1 or higher"); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  802 */       if (null != clazz) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  807 */         h.put("ERROR.version.JAXP", "1.0.1");
/*  808 */         h.put("ERROR.", "At least one error was found!");
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  815 */         h.put("ERROR.version.JAXP", "not-present");
/*  816 */         h.put("ERROR.", "At least one error was found!");
/*      */       }  }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkProcessorVersion(Hashtable h) {
/*  831 */     if (null == h) {
/*  832 */       h = new Hashtable();
/*      */     }
/*      */ 
/*      */     
/*  836 */     try { String XALAN1_VERSION_CLASS = "org.apache.xalan.xslt.XSLProcessorVersion";
/*      */ 
/*      */       
/*  839 */       Class clazz = ObjectFactory.findProviderClass("org.apache.xalan.xslt.XSLProcessorVersion", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */ 
/*      */       
/*  843 */       StringBuffer buf = new StringBuffer();
/*  844 */       Field f = clazz.getField("PRODUCT");
/*      */       
/*  846 */       buf.append(f.get(null));
/*  847 */       buf.append(';');
/*      */       
/*  849 */       f = clazz.getField("LANGUAGE");
/*      */       
/*  851 */       buf.append(f.get(null));
/*  852 */       buf.append(';');
/*      */       
/*  854 */       f = clazz.getField("S_VERSION");
/*      */       
/*  856 */       buf.append(f.get(null));
/*  857 */       buf.append(';');
/*  858 */       h.put("version.xalan1", buf.toString()); } catch (Exception e1)
/*      */     
/*      */     { 
/*      */       
/*  862 */       h.put("version.xalan1", "not-present"); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  869 */     try { String XALAN2_VERSION_CLASS = "org.apache.xalan.processor.XSLProcessorVersion";
/*      */ 
/*      */       
/*  872 */       Class clazz = ObjectFactory.findProviderClass("org.apache.xalan.processor.XSLProcessorVersion", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */ 
/*      */       
/*  876 */       StringBuffer buf = new StringBuffer();
/*  877 */       Field f = clazz.getField("S_VERSION");
/*  878 */       buf.append(f.get(null));
/*      */       
/*  880 */       h.put("version.xalan2x", buf.toString()); } catch (Exception e2)
/*      */     
/*      */     { 
/*      */       
/*  884 */       h.put("version.xalan2x", "not-present"); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  889 */     try { String XALAN2_2_VERSION_CLASS = "org.apache.xalan.Version";
/*      */       
/*  891 */       String XALAN2_2_VERSION_METHOD = "getVersion";
/*  892 */       Class[] noArgs = new Class[0];
/*      */       
/*  894 */       Class clazz = ObjectFactory.findProviderClass("org.apache.xalan.Version", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */       
/*  897 */       Method method = clazz.getMethod("getVersion", noArgs);
/*  898 */       Object returnValue = method.invoke(null, new Object[0]);
/*      */       
/*  900 */       h.put("version.xalan2_2", returnValue); } catch (Exception e2)
/*      */     
/*      */     { 
/*      */       
/*  904 */       h.put("version.xalan2_2", "not-present"); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkParserVersion(Hashtable h) {
/*  920 */     if (null == h) {
/*  921 */       h = new Hashtable();
/*      */     }
/*      */ 
/*      */     
/*  925 */     try { String XERCES1_VERSION_CLASS = "org.apache.xerces.framework.Version";
/*      */       
/*  927 */       Class clazz = ObjectFactory.findProviderClass("org.apache.xerces.framework.Version", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */ 
/*      */       
/*  931 */       Field f = clazz.getField("fVersion");
/*  932 */       String parserVersion = (String)f.get(null);
/*      */       
/*  934 */       h.put("version.xerces1", parserVersion); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  938 */       h.put("version.xerces1", "not-present"); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  944 */     try { String XERCES2_VERSION_CLASS = "org.apache.xerces.impl.Version";
/*      */       
/*  946 */       Class clazz = ObjectFactory.findProviderClass("org.apache.xerces.impl.Version", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */ 
/*      */       
/*  950 */       Field f = clazz.getField("fVersion");
/*  951 */       String parserVersion = (String)f.get(null);
/*      */       
/*  953 */       h.put("version.xerces2", parserVersion); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  957 */       h.put("version.xerces2", "not-present"); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  962 */     try { String CRIMSON_CLASS = "org.apache.crimson.parser.Parser2";
/*      */       
/*  964 */       Class clazz = ObjectFactory.findProviderClass("org.apache.crimson.parser.Parser2", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */ 
/*      */       
/*  968 */       h.put("version.crimson", "present-unknown-version"); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  972 */       h.put("version.crimson", "not-present"); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkAntVersion(Hashtable h) {
/*  984 */     if (null == h) {
/*  985 */       h = new Hashtable();
/*      */     }
/*      */ 
/*      */     
/*  989 */     try { String ANT_VERSION_CLASS = "org.apache.tools.ant.Main";
/*  990 */       String ANT_VERSION_METHOD = "getAntVersion";
/*  991 */       Class[] noArgs = new Class[0];
/*      */       
/*  993 */       Class clazz = ObjectFactory.findProviderClass("org.apache.tools.ant.Main", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */       
/*  996 */       Method method = clazz.getMethod("getAntVersion", noArgs);
/*  997 */       Object returnValue = method.invoke(null, new Object[0]);
/*      */       
/*  999 */       h.put("version.ant", returnValue); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/* 1003 */       h.put("version.ant", "not-present"); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkDOMVersion(Hashtable h) {
/* 1019 */     if (null == h) {
/* 1020 */       h = new Hashtable();
/*      */     }
/* 1022 */     String DOM_LEVEL2_CLASS = "org.w3c.dom.Document";
/* 1023 */     String DOM_LEVEL2_METHOD = "createElementNS";
/* 1024 */     String DOM_LEVEL2WD_CLASS = "org.w3c.dom.Node";
/* 1025 */     String DOM_LEVEL2WD_METHOD = "supported";
/* 1026 */     String DOM_LEVEL2FD_CLASS = "org.w3c.dom.Node";
/* 1027 */     String DOM_LEVEL2FD_METHOD = "isSupported";
/* 1028 */     Class[] twoStringArgs = { String.class, String.class };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1033 */     try { Class clazz = ObjectFactory.findProviderClass("org.w3c.dom.Document", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */       
/* 1036 */       Method method = clazz.getMethod("createElementNS", twoStringArgs);
/*      */ 
/*      */ 
/*      */       
/* 1040 */       h.put("version.DOM", "2.0");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1046 */       try { clazz = ObjectFactory.findProviderClass("org.w3c.dom.Node", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */         
/* 1049 */         method = clazz.getMethod("supported", twoStringArgs);
/*      */         
/* 1051 */         h.put("ERROR.version.DOM.draftlevel", "2.0wd");
/* 1052 */         h.put("ERROR.", "At least one error was found!"); } catch (Exception e2)
/*      */       
/*      */       { 
/*      */         
/*      */         try { 
/*      */ 
/*      */           
/* 1059 */           clazz = ObjectFactory.findProviderClass("org.w3c.dom.Node", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */           
/* 1062 */           method = clazz.getMethod("isSupported", twoStringArgs);
/*      */           
/* 1064 */           h.put("version.DOM.draftlevel", "2.0fd"); } catch (Exception e3)
/*      */         
/*      */         { 
/*      */           
/* 1068 */           h.put("ERROR.version.DOM.draftlevel", "2.0unknown");
/* 1069 */           h.put("ERROR.", "At least one error was found!"); }  }  } catch (Exception e)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/* 1075 */       h.put("ERROR.version.DOM", "ERROR attempting to load DOM level 2 class: " + e.toString());
/*      */       
/* 1077 */       h.put("ERROR.", "At least one error was found!"); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkSAXVersion(Hashtable h) {
/* 1097 */     if (null == h) {
/* 1098 */       h = new Hashtable();
/*      */     }
/* 1100 */     String SAX_VERSION1_CLASS = "org.xml.sax.Parser";
/* 1101 */     String SAX_VERSION1_METHOD = "parse";
/* 1102 */     String SAX_VERSION2_CLASS = "org.xml.sax.XMLReader";
/* 1103 */     String SAX_VERSION2_METHOD = "parse";
/* 1104 */     String SAX_VERSION2BETA_CLASSNF = "org.xml.sax.helpers.AttributesImpl";
/* 1105 */     String SAX_VERSION2BETA_METHODNF = "setAttributes";
/* 1106 */     Class[] oneStringArg = { String.class };
/*      */     
/* 1108 */     Class[] attributesArg = { Attributes.class };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1114 */     try { Class clazz = ObjectFactory.findProviderClass("org.xml.sax.helpers.AttributesImpl", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */       
/* 1117 */       Method method = clazz.getMethod("setAttributes", attributesArg);
/*      */ 
/*      */ 
/*      */       
/* 1121 */       h.put("version.SAX", "2.0"); } catch (Exception e)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/* 1126 */       h.put("ERROR.version.SAX", "ERROR attempting to load SAX version 2 class: " + e.toString());
/*      */       
/* 1128 */       h.put("ERROR.", "At least one error was found!");
/*      */ 
/*      */ 
/*      */       
/* 1132 */       try { Class clazz = ObjectFactory.findProviderClass("org.xml.sax.XMLReader", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */         
/* 1135 */         Method method = clazz.getMethod("parse", oneStringArg);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1140 */         h.put("version.SAX-backlevel", "2.0beta2-or-earlier"); } catch (Exception e2)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/* 1145 */         h.put("ERROR.version.SAX", "ERROR attempting to load SAX version 2 class: " + e.toString());
/*      */         
/* 1147 */         h.put("ERROR.", "At least one error was found!");
/*      */ 
/*      */ 
/*      */         
/* 1151 */         try { Class clazz = ObjectFactory.findProviderClass("org.xml.sax.Parser", ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */           
/* 1154 */           Method method = clazz.getMethod("parse", oneStringArg);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1159 */           h.put("version.SAX-backlevel", "1.0"); } catch (Exception e3)
/*      */         
/*      */         { 
/*      */ 
/*      */ 
/*      */           
/* 1165 */           h.put("ERROR.version.SAX-backlevel", "ERROR attempting to load SAX version 1 class: " + e3.toString()); }
/*      */          }
/*      */        }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1181 */   protected static Hashtable jarVersions = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/* 1192 */     jarVersions.put(new Long(857192L), "xalan.jar from xalan-j_1_1");
/* 1193 */     jarVersions.put(new Long(440237L), "xalan.jar from xalan-j_1_2");
/* 1194 */     jarVersions.put(new Long(436094L), "xalan.jar from xalan-j_1_2_1");
/* 1195 */     jarVersions.put(new Long(426249L), "xalan.jar from xalan-j_1_2_2");
/* 1196 */     jarVersions.put(new Long(702536L), "xalan.jar from xalan-j_2_0_0");
/* 1197 */     jarVersions.put(new Long(720930L), "xalan.jar from xalan-j_2_0_1");
/* 1198 */     jarVersions.put(new Long(732330L), "xalan.jar from xalan-j_2_1_0");
/* 1199 */     jarVersions.put(new Long(872241L), "xalan.jar from xalan-j_2_2_D10");
/* 1200 */     jarVersions.put(new Long(882739L), "xalan.jar from xalan-j_2_2_D11");
/* 1201 */     jarVersions.put(new Long(923866L), "xalan.jar from xalan-j_2_2_0");
/* 1202 */     jarVersions.put(new Long(905872L), "xalan.jar from xalan-j_2_3_D1");
/* 1203 */     jarVersions.put(new Long(906122L), "xalan.jar from xalan-j_2_3_0");
/* 1204 */     jarVersions.put(new Long(906248L), "xalan.jar from xalan-j_2_3_1");
/* 1205 */     jarVersions.put(new Long(983377L), "xalan.jar from xalan-j_2_4_D1");
/* 1206 */     jarVersions.put(new Long(997276L), "xalan.jar from xalan-j_2_4_0");
/* 1207 */     jarVersions.put(new Long(1031036L), "xalan.jar from xalan-j_2_4_1");
/*      */ 
/*      */     
/* 1210 */     jarVersions.put(new Long(596540L), "xsltc.jar from xalan-j_2_2_0");
/* 1211 */     jarVersions.put(new Long(590247L), "xsltc.jar from xalan-j_2_3_D1");
/* 1212 */     jarVersions.put(new Long(589914L), "xsltc.jar from xalan-j_2_3_0");
/* 1213 */     jarVersions.put(new Long(589915L), "xsltc.jar from xalan-j_2_3_1");
/* 1214 */     jarVersions.put(new Long(1306667L), "xsltc.jar from xalan-j_2_4_D1");
/* 1215 */     jarVersions.put(new Long(1328227L), "xsltc.jar from xalan-j_2_4_0");
/* 1216 */     jarVersions.put(new Long(1344009L), "xsltc.jar from xalan-j_2_4_1");
/* 1217 */     jarVersions.put(new Long(1348361L), "xsltc.jar from xalan-j_2_5_D1");
/*      */ 
/*      */     
/* 1220 */     jarVersions.put(new Long(1268634L), "xsltc.jar-bundled from xalan-j_2_3_0");
/*      */     
/* 1222 */     jarVersions.put(new Long(100196L), "xml-apis.jar from xalan-j_2_2_0 or xalan-j_2_3_D1");
/* 1223 */     jarVersions.put(new Long(108484L), "xml-apis.jar from xalan-j_2_3_0, or xalan-j_2_3_1 from xml-commons-1.0.b2");
/* 1224 */     jarVersions.put(new Long(109049L), "xml-apis.jar from xalan-j_2_4_0 from xml-commons RIVERCOURT1 branch");
/* 1225 */     jarVersions.put(new Long(113749L), "xml-apis.jar from xalan-j_2_4_1 from factoryfinder-build of xml-commons RIVERCOURT1");
/* 1226 */     jarVersions.put(new Long(124704L), "xml-apis.jar from tck-jaxp-1_2_0 branch of xml-commons");
/* 1227 */     jarVersions.put(new Long(124724L), "xml-apis.jar from tck-jaxp-1_2_0 branch of xml-commons, tag: xml-commons-external_1_2_01");
/*      */ 
/*      */ 
/*      */     
/* 1231 */     jarVersions.put(new Long(424490L), "xalan.jar from Xerces Tools releases - ERROR:DO NOT USE!");
/*      */     
/* 1233 */     jarVersions.put(new Long(1591855L), "xerces.jar from xalan-j_1_1 from xerces-1...");
/* 1234 */     jarVersions.put(new Long(1498679L), "xerces.jar from xalan-j_1_2 from xerces-1_2_0.bin");
/* 1235 */     jarVersions.put(new Long(1484896L), "xerces.jar from xalan-j_1_2_1 from xerces-1_2_1.bin");
/* 1236 */     jarVersions.put(new Long(804460L), "xerces.jar from xalan-j_1_2_2 from xerces-1_2_2.bin");
/* 1237 */     jarVersions.put(new Long(1499244L), "xerces.jar from xalan-j_2_0_0 from xerces-1_2_3.bin");
/* 1238 */     jarVersions.put(new Long(1605266L), "xerces.jar from xalan-j_2_0_1 from xerces-1_3_0.bin");
/* 1239 */     jarVersions.put(new Long(904030L), "xerces.jar from xalan-j_2_1_0 from xerces-1_4.bin");
/* 1240 */     jarVersions.put(new Long(904030L), "xerces.jar from xerces-1_4_0.bin");
/* 1241 */     jarVersions.put(new Long(1802885L), "xerces.jar from xerces-1_4_2.bin");
/* 1242 */     jarVersions.put(new Long(1734594L), "xerces.jar from Xerces-J-bin.2.0.0.beta3");
/* 1243 */     jarVersions.put(new Long(1808883L), "xerces.jar from xalan-j_2_2_D10,D11,D12 or xerces-1_4_3.bin");
/* 1244 */     jarVersions.put(new Long(1812019L), "xerces.jar from xalan-j_2_2_0");
/* 1245 */     jarVersions.put(new Long(1720292L), "xercesImpl.jar from xalan-j_2_3_D1");
/* 1246 */     jarVersions.put(new Long(1730053L), "xercesImpl.jar from xalan-j_2_3_0 or xalan-j_2_3_1 from xerces-2_0_0");
/* 1247 */     jarVersions.put(new Long(1728861L), "xercesImpl.jar from xalan-j_2_4_D1 from xerces-2_0_1");
/* 1248 */     jarVersions.put(new Long(972027L), "xercesImpl.jar from xalan-j_2_4_0 from xerces-2_1");
/* 1249 */     jarVersions.put(new Long(831587L), "xercesImpl.jar from xalan-j_2_4_1 from xerces-2_2");
/* 1250 */     jarVersions.put(new Long(891817L), "xercesImpl.jar from xalan-j_2_5_D1 from xerces-2_3");
/* 1251 */     jarVersions.put(new Long(895924L), "xercesImpl.jar from xerces-2_4");
/* 1252 */     jarVersions.put(new Long(1010806L), "xercesImpl.jar from Xerces-J-bin.2.6.2");
/*      */     
/* 1254 */     jarVersions.put(new Long(37485L), "xalanj1compat.jar from xalan-j_2_0_0");
/* 1255 */     jarVersions.put(new Long(38100L), "xalanj1compat.jar from xalan-j_2_0_1");
/*      */     
/* 1257 */     jarVersions.put(new Long(18779L), "xalanservlet.jar from xalan-j_2_0_0");
/* 1258 */     jarVersions.put(new Long(21453L), "xalanservlet.jar from xalan-j_2_0_1");
/* 1259 */     jarVersions.put(new Long(24826L), "xalanservlet.jar from xalan-j_2_3_1 or xalan-j_2_4_1");
/* 1260 */     jarVersions.put(new Long(24831L), "xalanservlet.jar from xalan-j_2_4_1");
/*      */ 
/*      */ 
/*      */     
/* 1264 */     jarVersions.put(new Long(5618L), "jaxp.jar from jaxp1.0.1");
/* 1265 */     jarVersions.put(new Long(136133L), "parser.jar from jaxp1.0.1");
/* 1266 */     jarVersions.put(new Long(28404L), "jaxp.jar from jaxp-1.1");
/* 1267 */     jarVersions.put(new Long(187162L), "crimson.jar from jaxp-1.1");
/* 1268 */     jarVersions.put(new Long(801714L), "xalan.jar from jaxp-1.1");
/* 1269 */     jarVersions.put(new Long(196399L), "crimson.jar from crimson-1.1.1");
/* 1270 */     jarVersions.put(new Long(33323L), "jaxp.jar from crimson-1.1.1 or jakarta-ant-1.4.1b1");
/* 1271 */     jarVersions.put(new Long(152717L), "crimson.jar from crimson-1.1.2beta2");
/* 1272 */     jarVersions.put(new Long(88143L), "xml-apis.jar from crimson-1.1.2beta2");
/* 1273 */     jarVersions.put(new Long(206384L), "crimson.jar from crimson-1.1.3 or jakarta-ant-1.4.1b1");
/*      */ 
/*      */     
/* 1276 */     jarVersions.put(new Long(136198L), "parser.jar from jakarta-ant-1.3 or 1.2");
/* 1277 */     jarVersions.put(new Long(5537L), "jaxp.jar from jakarta-ant-1.3 or 1.2");
/*      */   }
/*      */ 
/*      */   
/* 1281 */   protected PrintWriter outWriter = new PrintWriter(System.out, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void logMsg(String s) {
/* 1289 */     this.outWriter.println(s);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xslt/EnvironmentCheck.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */