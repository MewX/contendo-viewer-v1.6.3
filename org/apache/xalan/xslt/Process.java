/*      */ package org.apache.xalan.xslt;
/*      */ 
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.StringReader;
/*      */ import java.io.Writer;
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Vector;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.FactoryConfigurationError;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.parsers.SAXParser;
/*      */ import javax.xml.parsers.SAXParserFactory;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.Templates;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import javax.xml.transform.TransformerFactory;
/*      */ import javax.xml.transform.TransformerFactoryConfigurationError;
/*      */ import javax.xml.transform.URIResolver;
/*      */ import javax.xml.transform.dom.DOMResult;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import javax.xml.transform.sax.SAXResult;
/*      */ import javax.xml.transform.sax.SAXSource;
/*      */ import javax.xml.transform.sax.SAXTransformerFactory;
/*      */ import javax.xml.transform.sax.TransformerHandler;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import javax.xml.transform.stream.StreamSource;
/*      */ import org.apache.xalan.Version;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xalan.trace.PrintTraceListener;
/*      */ import org.apache.xalan.trace.TraceListener;
/*      */ import org.apache.xalan.trace.TraceManager;
/*      */ import org.apache.xalan.transformer.TransformerImpl;
/*      */ import org.apache.xml.res.XMLMessages;
/*      */ import org.apache.xml.utils.DefaultErrorHandler;
/*      */ import org.apache.xml.utils.WrappedRuntimeException;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentFragment;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXNotRecognizedException;
/*      */ import org.xml.sax.SAXNotSupportedException;
/*      */ import org.xml.sax.XMLReader;
/*      */ import org.xml.sax.helpers.XMLReaderFactory;
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
/*      */ public class Process
/*      */ {
/*      */   protected static void printArgOptions(ResourceBundle resbundle) {
/*   78 */     System.out.println(resbundle.getString("xslProc_option"));
/*   79 */     System.out.println("\n\t\t\t" + resbundle.getString("xslProc_common_options") + "\n");
/*   80 */     System.out.println(resbundle.getString("optionXSLTC"));
/*   81 */     System.out.println(resbundle.getString("optionIN"));
/*   82 */     System.out.println(resbundle.getString("optionXSL"));
/*   83 */     System.out.println(resbundle.getString("optionOUT"));
/*      */ 
/*      */     
/*   86 */     System.out.println(resbundle.getString("optionV"));
/*      */ 
/*      */     
/*   89 */     System.out.println(resbundle.getString("optionEDUMP"));
/*   90 */     System.out.println(resbundle.getString("optionXML"));
/*   91 */     System.out.println(resbundle.getString("optionTEXT"));
/*   92 */     System.out.println(resbundle.getString("optionHTML"));
/*   93 */     System.out.println(resbundle.getString("optionPARAM"));
/*      */     
/*   95 */     System.out.println(resbundle.getString("optionMEDIA"));
/*   96 */     System.out.println(resbundle.getString("optionFLAVOR"));
/*   97 */     System.out.println(resbundle.getString("optionDIAG"));
/*   98 */     System.out.println(resbundle.getString("optionURIRESOLVER"));
/*   99 */     System.out.println(resbundle.getString("optionENTITYRESOLVER"));
/*  100 */     waitForReturnKey(resbundle);
/*  101 */     System.out.println(resbundle.getString("optionCONTENTHANDLER"));
/*      */     
/*  103 */     System.out.println("\n\t\t\t" + resbundle.getString("xslProc_xalan_options") + "\n");
/*      */     
/*  105 */     System.out.println(resbundle.getString("optionQC"));
/*      */ 
/*      */     
/*  108 */     System.out.println(resbundle.getString("optionTT"));
/*  109 */     System.out.println(resbundle.getString("optionTG"));
/*  110 */     System.out.println(resbundle.getString("optionTS"));
/*  111 */     System.out.println(resbundle.getString("optionTTC"));
/*  112 */     System.out.println(resbundle.getString("optionTCLASS"));
/*  113 */     System.out.println(resbundle.getString("optionLINENUMBERS"));
/*  114 */     System.out.println(resbundle.getString("optionINCREMENTAL"));
/*  115 */     System.out.println(resbundle.getString("optionNOOPTIMIMIZE"));
/*  116 */     System.out.println(resbundle.getString("optionRL"));
/*      */     
/*  118 */     System.out.println("\n\t\t\t" + resbundle.getString("xslProc_xsltc_options") + "\n");
/*  119 */     System.out.println(resbundle.getString("optionXO"));
/*  120 */     System.out.println(resbundle.getString("optionXD"));
/*  121 */     waitForReturnKey(resbundle);
/*  122 */     System.out.println(resbundle.getString("optionXJ"));
/*  123 */     System.out.println(resbundle.getString("optionXP"));
/*  124 */     System.out.println(resbundle.getString("optionXN"));
/*  125 */     System.out.println(resbundle.getString("optionXX"));
/*  126 */     System.out.println(resbundle.getString("optionXT"));
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
/*      */   public static void main(String[] argv) {
/*  148 */     boolean doStackDumpOnError = false;
/*  149 */     boolean setQuietMode = false;
/*  150 */     boolean doDiag = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  158 */     PrintWriter diagnosticsWriter = new PrintWriter(System.err, true);
/*  159 */     PrintWriter dumpWriter = diagnosticsWriter;
/*  160 */     ResourceBundle resbundle = XMLMessages.loadResourceBundle("org.apache.xalan.res.XSLTErrorResources");
/*      */ 
/*      */     
/*  163 */     String flavor = "s2s";
/*      */     
/*  165 */     if (argv.length < 1) {
/*      */       
/*  167 */       printArgOptions(resbundle);
/*      */     } else {
/*      */       TransformerFactory transformerFactory;
/*      */       
/*  171 */       boolean useXSLTC = false;
/*  172 */       for (int i = 0; i < argv.length; i++) {
/*      */         
/*  174 */         if ("-XSLTC".equalsIgnoreCase(argv[i]))
/*      */         {
/*  176 */           useXSLTC = true;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  181 */       if (useXSLTC) {
/*      */         
/*  183 */         String key = "javax.xml.transform.TransformerFactory";
/*  184 */         String value = "org.apache.xalan.xsltc.trax.TransformerFactoryImpl";
/*  185 */         Properties props = System.getProperties();
/*  186 */         props.put(key, value);
/*  187 */         System.setProperties(props);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  192 */       try { transformerFactory = TransformerFactory.newInstance(); } catch (TransformerFactoryConfigurationError pfe)
/*      */       
/*      */       { 
/*      */         
/*  196 */         pfe.printStackTrace(dumpWriter);
/*  197 */         diagnosticsWriter.println(XSLMessages.createMessage("ER_NOT_SUCCESSFUL", null));
/*      */ 
/*      */ 
/*      */         
/*  201 */         transformerFactory = null;
/*      */         
/*  203 */         doExit(-1); }
/*      */ 
/*      */       
/*  206 */       boolean formatOutput = false;
/*  207 */       boolean useSourceLocation = false;
/*  208 */       String inFileName = null;
/*  209 */       String outFileName = null;
/*  210 */       String dumpFileName = null;
/*  211 */       String xslFileName = null;
/*  212 */       String treedumpFileName = null;
/*  213 */       PrintTraceListener tracer = null;
/*  214 */       String outputType = null;
/*  215 */       String media = null;
/*  216 */       Vector params = new Vector();
/*  217 */       boolean quietConflictWarnings = false;
/*  218 */       URIResolver uriResolver = null;
/*  219 */       EntityResolver entityResolver = null;
/*  220 */       ContentHandler contentHandler = null;
/*  221 */       int recursionLimit = -1;
/*      */       
/*  223 */       for (int j = 0; j < argv.length; j++) {
/*      */         
/*  225 */         if (!"-XSLTC".equalsIgnoreCase(argv[j]))
/*      */         {
/*      */ 
/*      */           
/*  229 */           if ("-TT".equalsIgnoreCase(argv[j])) {
/*      */             
/*  231 */             if (!useXSLTC) {
/*      */               
/*  233 */               if (null == tracer) {
/*  234 */                 tracer = new PrintTraceListener(diagnosticsWriter);
/*      */               }
/*  236 */               tracer.m_traceTemplates = true;
/*      */             } else {
/*      */               
/*  239 */               printInvalidXSLTCOption("-TT");
/*      */             }
/*      */           
/*      */           }
/*  243 */           else if ("-TG".equalsIgnoreCase(argv[j])) {
/*      */             
/*  245 */             if (!useXSLTC) {
/*      */               
/*  247 */               if (null == tracer) {
/*  248 */                 tracer = new PrintTraceListener(diagnosticsWriter);
/*      */               }
/*  250 */               tracer.m_traceGeneration = true;
/*      */             } else {
/*      */               
/*  253 */               printInvalidXSLTCOption("-TG");
/*      */             }
/*      */           
/*      */           }
/*  257 */           else if ("-TS".equalsIgnoreCase(argv[j])) {
/*      */             
/*  259 */             if (!useXSLTC) {
/*      */               
/*  261 */               if (null == tracer) {
/*  262 */                 tracer = new PrintTraceListener(diagnosticsWriter);
/*      */               }
/*  264 */               tracer.m_traceSelection = true;
/*      */             } else {
/*      */               
/*  267 */               printInvalidXSLTCOption("-TS");
/*      */             }
/*      */           
/*      */           }
/*  271 */           else if ("-TTC".equalsIgnoreCase(argv[j])) {
/*      */             
/*  273 */             if (!useXSLTC) {
/*      */               
/*  275 */               if (null == tracer) {
/*  276 */                 tracer = new PrintTraceListener(diagnosticsWriter);
/*      */               }
/*  278 */               tracer.m_traceElements = true;
/*      */             } else {
/*      */               
/*  281 */               printInvalidXSLTCOption("-TTC");
/*      */             }
/*      */           
/*      */           }
/*  285 */           else if ("-INDENT".equalsIgnoreCase(argv[j])) {
/*      */ 
/*      */ 
/*      */             
/*  289 */             if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-')
/*      */             {
/*  291 */               int indentAmount = Integer.parseInt(argv[++j]);
/*      */             }
/*      */             else
/*      */             {
/*  295 */               boolean bool = false;
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/*  301 */           else if ("-IN".equalsIgnoreCase(argv[j])) {
/*      */             
/*  303 */             if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*  304 */               inFileName = argv[++j];
/*      */             } else {
/*  306 */               System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-IN" }));
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  311 */           else if ("-MEDIA".equalsIgnoreCase(argv[j])) {
/*      */             
/*  313 */             if (j + 1 < argv.length) {
/*  314 */               media = argv[++j];
/*      */             } else {
/*  316 */               System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-MEDIA" }));
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  321 */           else if ("-OUT".equalsIgnoreCase(argv[j])) {
/*      */             
/*  323 */             if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*  324 */               outFileName = argv[++j];
/*      */             } else {
/*  326 */               System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-OUT" }));
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  331 */           else if ("-XSL".equalsIgnoreCase(argv[j])) {
/*      */             
/*  333 */             if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*  334 */               xslFileName = argv[++j];
/*      */             } else {
/*  336 */               System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-XSL" }));
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  341 */           else if ("-FLAVOR".equalsIgnoreCase(argv[j])) {
/*      */             
/*  343 */             if (j + 1 < argv.length) {
/*      */               
/*  345 */               flavor = argv[++j];
/*      */             } else {
/*      */               
/*  348 */               System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-FLAVOR" }));
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  353 */           else if ("-PARAM".equalsIgnoreCase(argv[j])) {
/*      */             
/*  355 */             if (j + 2 < argv.length) {
/*      */               
/*  357 */               String name = argv[++j];
/*      */               
/*  359 */               params.addElement(name);
/*      */               
/*  361 */               String expression = argv[++j];
/*      */               
/*  363 */               params.addElement(expression);
/*      */             } else {
/*      */               
/*  366 */               System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-PARAM" }));
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  371 */           else if (!"-E".equalsIgnoreCase(argv[j])) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  377 */             if ("-V".equalsIgnoreCase(argv[j])) {
/*      */               
/*  379 */               diagnosticsWriter.println(resbundle.getString("version") + Version.getVersion() + ", " + resbundle.getString("version2"));
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*  385 */             else if ("-QC".equalsIgnoreCase(argv[j])) {
/*      */               
/*  387 */               if (!useXSLTC) {
/*  388 */                 quietConflictWarnings = true;
/*      */               } else {
/*  390 */                 printInvalidXSLTCOption("-QC");
/*      */               } 
/*  392 */             } else if ("-Q".equalsIgnoreCase(argv[j])) {
/*      */               
/*  394 */               setQuietMode = true;
/*      */             }
/*  396 */             else if ("-DIAG".equalsIgnoreCase(argv[j])) {
/*      */               
/*  398 */               doDiag = true;
/*      */             }
/*  400 */             else if ("-XML".equalsIgnoreCase(argv[j])) {
/*      */               
/*  402 */               outputType = "xml";
/*      */             }
/*  404 */             else if ("-TEXT".equalsIgnoreCase(argv[j])) {
/*      */               
/*  406 */               outputType = "text";
/*      */             }
/*  408 */             else if ("-HTML".equalsIgnoreCase(argv[j])) {
/*      */               
/*  410 */               outputType = "html";
/*      */             }
/*  412 */             else if ("-EDUMP".equalsIgnoreCase(argv[j])) {
/*      */               
/*  414 */               doStackDumpOnError = true;
/*      */               
/*  416 */               if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-')
/*      */               {
/*  418 */                 dumpFileName = argv[++j];
/*      */               }
/*      */             }
/*  421 */             else if ("-URIRESOLVER".equalsIgnoreCase(argv[j])) {
/*      */               
/*  423 */               if (j + 1 < argv.length) {
/*      */ 
/*      */ 
/*      */                 
/*  427 */                 try { uriResolver = (URIResolver)ObjectFactory.newInstance(argv[++j], ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */                   
/*  430 */                   transformerFactory.setURIResolver(uriResolver); } catch (ConfigurationError cnfe)
/*      */                 
/*      */                 { 
/*      */                   
/*  434 */                   System.err.println(XSLMessages.createMessage("ER_CLASS_NOT_FOUND_FOR_OPTION", new Object[] { "-URIResolver" }));
/*      */ 
/*      */ 
/*      */                   
/*  438 */                   doExit(-1); }
/*      */ 
/*      */               
/*      */               } else {
/*      */                 
/*  443 */                 System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-URIResolver" }));
/*      */ 
/*      */ 
/*      */                 
/*  447 */                 doExit(-1);
/*      */               }
/*      */             
/*  450 */             } else if ("-ENTITYRESOLVER".equalsIgnoreCase(argv[j])) {
/*      */               
/*  452 */               if (j + 1 < argv.length) {
/*      */ 
/*      */ 
/*      */                 
/*  456 */                 try { entityResolver = (EntityResolver)ObjectFactory.newInstance(argv[++j], ObjectFactory.findClassLoader(), true); } catch (ConfigurationError cnfe)
/*      */                 
/*      */                 { 
/*      */ 
/*      */                   
/*  461 */                   System.err.println(XSLMessages.createMessage("ER_CLASS_NOT_FOUND_FOR_OPTION", new Object[] { "-EntityResolver" }));
/*      */ 
/*      */ 
/*      */                   
/*  465 */                   doExit(-1); }
/*      */ 
/*      */               
/*      */               } else {
/*      */                 
/*  470 */                 System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-EntityResolver" }));
/*      */ 
/*      */ 
/*      */                 
/*  474 */                 doExit(-1);
/*      */               }
/*      */             
/*  477 */             } else if ("-CONTENTHANDLER".equalsIgnoreCase(argv[j])) {
/*      */               
/*  479 */               if (j + 1 < argv.length) {
/*      */ 
/*      */ 
/*      */                 
/*  483 */                 try { contentHandler = (ContentHandler)ObjectFactory.newInstance(argv[++j], ObjectFactory.findClassLoader(), true); } catch (ConfigurationError cnfe)
/*      */                 
/*      */                 { 
/*      */ 
/*      */                   
/*  488 */                   System.err.println(XSLMessages.createMessage("ER_CLASS_NOT_FOUND_FOR_OPTION", new Object[] { "-ContentHandler" }));
/*      */ 
/*      */ 
/*      */                   
/*  492 */                   doExit(-1); }
/*      */ 
/*      */               
/*      */               } else {
/*      */                 
/*  497 */                 System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-ContentHandler" }));
/*      */ 
/*      */ 
/*      */                 
/*  501 */                 doExit(-1);
/*      */               }
/*      */             
/*  504 */             } else if ("-L".equalsIgnoreCase(argv[j])) {
/*      */               
/*  506 */               if (!useXSLTC) {
/*  507 */                 useSourceLocation = true;
/*      */               } else {
/*  509 */                 printInvalidXSLTCOption("-L");
/*      */               } 
/*  511 */             } else if ("-INCREMENTAL".equalsIgnoreCase(argv[j])) {
/*      */               
/*  513 */               if (!useXSLTC) {
/*  514 */                 transformerFactory.setAttribute("http://xml.apache.org/xalan/features/incremental", Boolean.TRUE);
/*      */               }
/*      */               else {
/*      */                 
/*  518 */                 printInvalidXSLTCOption("-INCREMENTAL");
/*      */               } 
/*  520 */             } else if ("-NOOPTIMIZE".equalsIgnoreCase(argv[j])) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  527 */               if (!useXSLTC) {
/*  528 */                 transformerFactory.setAttribute("http://xml.apache.org/xalan/features/optimize", Boolean.FALSE);
/*      */               }
/*      */               else {
/*      */                 
/*  532 */                 printInvalidXSLTCOption("-NOOPTIMIZE");
/*      */               } 
/*  534 */             } else if ("-RL".equalsIgnoreCase(argv[j])) {
/*      */               
/*  536 */               if (!useXSLTC)
/*      */               {
/*  538 */                 if (j + 1 < argv.length) {
/*  539 */                   recursionLimit = Integer.parseInt(argv[++j]);
/*      */                 } else {
/*  541 */                   System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-rl" }));
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */               else
/*      */               {
/*  548 */                 if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*  549 */                   j++;
/*      */                 }
/*  551 */                 printInvalidXSLTCOption("-RL");
/*      */               
/*      */               }
/*      */             
/*      */             }
/*  556 */             else if ("-XO".equalsIgnoreCase(argv[j])) {
/*      */               
/*  558 */               if (useXSLTC) {
/*      */                 
/*  560 */                 if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*      */                   
/*  562 */                   transformerFactory.setAttribute("generate-translet", "true");
/*  563 */                   transformerFactory.setAttribute("translet-name", argv[++j]);
/*      */                 } else {
/*      */                   
/*  566 */                   transformerFactory.setAttribute("generate-translet", "true");
/*      */                 } 
/*      */               } else {
/*      */                 
/*  570 */                 if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-')
/*  571 */                   j++; 
/*  572 */                 printInvalidXalanOption("-XO");
/*      */               }
/*      */             
/*      */             }
/*  576 */             else if ("-XD".equalsIgnoreCase(argv[j])) {
/*      */               
/*  578 */               if (useXSLTC)
/*      */               {
/*  580 */                 if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*  581 */                   transformerFactory.setAttribute("destination-directory", argv[++j]);
/*      */                 } else {
/*  583 */                   System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-XD" }));
/*      */                 
/*      */                 }
/*      */ 
/*      */               
/*      */               }
/*      */               else
/*      */               {
/*  591 */                 if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*  592 */                   j++;
/*      */                 }
/*  594 */                 printInvalidXalanOption("-XD");
/*      */               }
/*      */             
/*      */             }
/*  598 */             else if ("-XJ".equalsIgnoreCase(argv[j])) {
/*      */               
/*  600 */               if (useXSLTC)
/*      */               {
/*  602 */                 if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*      */                   
/*  604 */                   transformerFactory.setAttribute("generate-translet", "true");
/*  605 */                   transformerFactory.setAttribute("jar-name", argv[++j]);
/*      */                 } else {
/*      */                   
/*  608 */                   System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-XJ" }));
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */               else
/*      */               {
/*  615 */                 if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*  616 */                   j++;
/*      */                 }
/*  618 */                 printInvalidXalanOption("-XJ");
/*      */               
/*      */               }
/*      */             
/*      */             }
/*  623 */             else if ("-XP".equalsIgnoreCase(argv[j])) {
/*      */               
/*  625 */               if (useXSLTC)
/*      */               {
/*  627 */                 if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*  628 */                   transformerFactory.setAttribute("package-name", argv[++j]);
/*      */                 } else {
/*  630 */                   System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-XP" }));
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */               else
/*      */               {
/*  637 */                 if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
/*  638 */                   j++;
/*      */                 }
/*  640 */                 printInvalidXalanOption("-XP");
/*      */               
/*      */               }
/*      */             
/*      */             }
/*  645 */             else if ("-XN".equalsIgnoreCase(argv[j])) {
/*      */               
/*  647 */               if (useXSLTC) {
/*      */                 
/*  649 */                 transformerFactory.setAttribute("enable-inlining", "true");
/*      */               } else {
/*      */                 
/*  652 */                 printInvalidXalanOption("-XN");
/*      */               }
/*      */             
/*  655 */             } else if ("-XX".equalsIgnoreCase(argv[j])) {
/*      */               
/*  657 */               if (useXSLTC) {
/*      */                 
/*  659 */                 transformerFactory.setAttribute("debug", "true");
/*      */               } else {
/*      */                 
/*  662 */                 printInvalidXalanOption("-XX");
/*      */               }
/*      */             
/*      */             }
/*  666 */             else if ("-XT".equalsIgnoreCase(argv[j])) {
/*      */               
/*  668 */               if (useXSLTC) {
/*      */                 
/*  670 */                 transformerFactory.setAttribute("auto-translet", "true");
/*      */               } else {
/*      */                 
/*  673 */                 printInvalidXalanOption("-XT");
/*      */               } 
/*      */             } else {
/*  676 */               System.err.println(XSLMessages.createMessage("ER_INVALID_OPTION", new Object[] { argv[j] }));
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  682 */       if (inFileName == null && xslFileName == null) {
/*      */         
/*  684 */         System.err.println(resbundle.getString("xslProc_no_input"));
/*  685 */         doExit(-1);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try { StreamResult streamResult;
/*      */         
/*  692 */         long start = System.currentTimeMillis();
/*      */         
/*  694 */         if (null != dumpFileName)
/*      */         {
/*  696 */           dumpWriter = new PrintWriter(new FileWriter(dumpFileName));
/*      */         }
/*      */         
/*  699 */         Templates stylesheet = null;
/*      */         
/*  701 */         if (null != xslFileName)
/*      */         {
/*  703 */           if (flavor.equals("d2d")) {
/*      */ 
/*      */ 
/*      */             
/*  707 */             DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
/*      */ 
/*      */             
/*  710 */             dfactory.setNamespaceAware(true);
/*      */             
/*  712 */             DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
/*  713 */             Node xslDOM = docBuilder.parse(new InputSource(xslFileName));
/*      */             
/*  715 */             stylesheet = transformerFactory.newTemplates(new DOMSource(xslDOM, xslFileName));
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/*  721 */             stylesheet = transformerFactory.newTemplates(new StreamSource(xslFileName));
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  729 */         if (null != outFileName) {
/*      */           
/*  731 */           streamResult = new StreamResult(new FileOutputStream(outFileName));
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  736 */           streamResult.setSystemId(outFileName);
/*      */         }
/*      */         else {
/*      */           
/*  740 */           streamResult = new StreamResult(System.out);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  747 */         SAXTransformerFactory stf = (SAXTransformerFactory)transformerFactory;
/*      */ 
/*      */         
/*  750 */         if (!useXSLTC && useSourceLocation) {
/*  751 */           stf.setAttribute("http://xml.apache.org/xalan/properties/source-location", Boolean.TRUE);
/*      */         }
/*      */ 
/*      */         
/*  755 */         if (null == stylesheet) {
/*      */           
/*  757 */           Source source = stf.getAssociatedStylesheet(new StreamSource(inFileName), media, null, null);
/*      */ 
/*      */ 
/*      */           
/*  761 */           if (null != source) {
/*  762 */             stylesheet = transformerFactory.newTemplates(source);
/*      */           } else {
/*      */             
/*  765 */             if (null != media) {
/*  766 */               throw new TransformerException(XSLMessages.createMessage("ER_NO_STYLESHEET_IN_MEDIA", new Object[] { inFileName, media }));
/*      */             }
/*      */ 
/*      */             
/*  770 */             throw new TransformerException(XSLMessages.createMessage("ER_NO_STYLESHEET_PI", new Object[] { inFileName }));
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  775 */         if (null != stylesheet) {
/*      */           
/*  777 */           Transformer transformer = flavor.equals("th") ? null : stylesheet.newTransformer();
/*      */ 
/*      */           
/*  780 */           if (null != outputType)
/*      */           {
/*  782 */             transformer.setOutputProperty("method", outputType);
/*      */           }
/*      */           
/*  785 */           if (transformer instanceof TransformerImpl) {
/*      */             
/*  787 */             TransformerImpl impl = (TransformerImpl)transformer;
/*  788 */             TraceManager tm = impl.getTraceManager();
/*      */             
/*  790 */             if (null != tracer) {
/*  791 */               tm.addTraceListener((TraceListener)tracer);
/*      */             }
/*  793 */             impl.setQuietConflictWarnings(quietConflictWarnings);
/*      */ 
/*      */             
/*  796 */             if (useSourceLocation) {
/*  797 */               impl.setProperty("http://xml.apache.org/xalan/properties/source-location", Boolean.TRUE);
/*      */             }
/*  799 */             if (recursionLimit > 0) {
/*  800 */               impl.setRecursionLimit(recursionLimit);
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  806 */           int nParams = params.size();
/*      */           
/*  808 */           for (int k = 0; k < nParams; k += 2)
/*      */           {
/*  810 */             transformer.setParameter(params.elementAt(k), params.elementAt(k + 1));
/*      */           }
/*      */ 
/*      */           
/*  814 */           if (uriResolver != null) {
/*  815 */             transformer.setURIResolver(uriResolver);
/*      */           }
/*  817 */           if (null != inFileName)
/*      */           {
/*  819 */             if (flavor.equals("d2d")) {
/*      */ 
/*      */ 
/*      */               
/*  823 */               DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
/*      */ 
/*      */               
/*  826 */               dfactory.setCoalescing(true);
/*  827 */               dfactory.setNamespaceAware(true);
/*      */               
/*  829 */               DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
/*      */               
/*  831 */               if (entityResolver != null) {
/*  832 */                 docBuilder.setEntityResolver(entityResolver);
/*      */               }
/*  834 */               Node xmlDoc = docBuilder.parse(new InputSource(inFileName));
/*  835 */               Document doc = docBuilder.newDocument();
/*  836 */               DocumentFragment outNode = doc.createDocumentFragment();
/*      */ 
/*      */               
/*  839 */               transformer.transform(new DOMSource(xmlDoc, inFileName), new DOMResult(outNode));
/*      */ 
/*      */ 
/*      */               
/*  843 */               Transformer serializer = stf.newTransformer();
/*  844 */               Properties serializationProps = stylesheet.getOutputProperties();
/*      */ 
/*      */               
/*  847 */               serializer.setOutputProperties(serializationProps);
/*      */               
/*  849 */               if (contentHandler != null) {
/*      */                 
/*  851 */                 SAXResult result = new SAXResult(contentHandler);
/*      */                 
/*  853 */                 serializer.transform(new DOMSource(outNode), result);
/*      */               } else {
/*      */                 
/*  856 */                 serializer.transform(new DOMSource(outNode), streamResult);
/*      */               } 
/*  858 */             } else if (flavor.equals("th")) {
/*      */               
/*  860 */               for (int m = 0; m < 1; m++)
/*      */               {
/*      */ 
/*      */ 
/*      */                 
/*  865 */                 XMLReader reader = null;
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  870 */                 try { SAXParserFactory factory = SAXParserFactory.newInstance();
/*      */ 
/*      */                   
/*  873 */                   factory.setNamespaceAware(true);
/*      */                   
/*  875 */                   SAXParser jaxpParser = factory.newSAXParser();
/*      */ 
/*      */                   
/*  878 */                   reader = jaxpParser.getXMLReader(); } catch (ParserConfigurationException ex)
/*      */                 
/*      */                 { 
/*      */                   
/*  882 */                   throw new SAXException(ex); } catch (FactoryConfigurationError ex1)
/*      */                 
/*      */                 { 
/*      */                   
/*  886 */                   throw new SAXException(ex1.toString()); } catch (NoSuchMethodError ex2)
/*      */                 {  }
/*  888 */                 catch (AbstractMethodError abstractMethodError) {}
/*      */ 
/*      */                 
/*  891 */                 if (null == reader)
/*      */                 {
/*  893 */                   reader = XMLReaderFactory.createXMLReader();
/*      */                 }
/*      */                 
/*  896 */                 if (!useXSLTC) {
/*  897 */                   stf.setAttribute("http://xml.apache.org/xalan/features/incremental", Boolean.TRUE);
/*      */                 }
/*      */                 
/*  900 */                 TransformerHandler th = stf.newTransformerHandler(stylesheet);
/*      */                 
/*  902 */                 reader.setContentHandler(th);
/*  903 */                 reader.setDTDHandler(th);
/*      */                 
/*  905 */                 if (th instanceof ErrorHandler) {
/*  906 */                   reader.setErrorHandler((ErrorHandler)th);
/*      */                 }
/*      */ 
/*      */                 
/*  910 */                 try { reader.setProperty("http://xml.org/sax/properties/lexical-handler", th); } catch (SAXNotRecognizedException e)
/*      */                 
/*      */                 {  }
/*  913 */                 catch (SAXNotSupportedException sAXNotSupportedException) {}
/*      */ 
/*      */ 
/*      */                 
/*  917 */                 try { reader.setFeature("http://xml.org/sax/features/namespace-prefixes", true); } catch (SAXException sAXException) {}
/*      */ 
/*      */ 
/*      */                 
/*  921 */                 th.setResult(streamResult);
/*      */                 
/*  923 */                 reader.parse(new InputSource(inFileName));
/*      */               
/*      */               }
/*      */             
/*      */             }
/*  928 */             else if (entityResolver != null) {
/*      */               
/*  930 */               XMLReader reader = null;
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  935 */               try { SAXParserFactory factory = SAXParserFactory.newInstance();
/*      */ 
/*      */                 
/*  938 */                 factory.setNamespaceAware(true);
/*      */                 
/*  940 */                 SAXParser jaxpParser = factory.newSAXParser();
/*      */ 
/*      */                 
/*  943 */                 reader = jaxpParser.getXMLReader(); } catch (ParserConfigurationException ex)
/*      */               
/*      */               { 
/*      */                 
/*  947 */                 throw new SAXException(ex); } catch (FactoryConfigurationError ex1)
/*      */               
/*      */               { 
/*      */                 
/*  951 */                 throw new SAXException(ex1.toString()); } catch (NoSuchMethodError ex2)
/*      */               {  }
/*  953 */               catch (AbstractMethodError abstractMethodError) {}
/*      */ 
/*      */               
/*  956 */               if (null == reader)
/*      */               {
/*  958 */                 reader = XMLReaderFactory.createXMLReader();
/*      */               }
/*      */               
/*  961 */               reader.setEntityResolver(entityResolver);
/*      */               
/*  963 */               if (contentHandler != null)
/*      */               {
/*  965 */                 SAXResult result = new SAXResult(contentHandler);
/*      */                 
/*  967 */                 transformer.transform(new SAXSource(reader, new InputSource(inFileName)), result);
/*      */               
/*      */               }
/*      */               else
/*      */               {
/*      */                 
/*  973 */                 transformer.transform(new SAXSource(reader, new InputSource(inFileName)), streamResult);
/*      */               
/*      */               }
/*      */             
/*      */             }
/*  978 */             else if (contentHandler != null) {
/*      */               
/*  980 */               SAXResult result = new SAXResult(contentHandler);
/*      */               
/*  982 */               transformer.transform(new StreamSource(inFileName), result);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  987 */               transformer.transform(new StreamSource(inFileName), streamResult);
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/*      */           else
/*      */           {
/*  995 */             StringReader reader = new StringReader("<?xml version=\"1.0\"?> <doc/>");
/*      */ 
/*      */             
/*  998 */             transformer.transform(new StreamSource(reader), streamResult);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1003 */           diagnosticsWriter.println(XSLMessages.createMessage("ER_NOT_SUCCESSFUL", null));
/*      */ 
/*      */           
/* 1006 */           doExit(-1);
/*      */         } 
/*      */ 
/*      */         
/* 1010 */         if (null != outFileName && streamResult != null) {
/*      */           
/* 1012 */           OutputStream out = streamResult.getOutputStream();
/* 1013 */           Writer writer = streamResult.getWriter();
/*      */ 
/*      */           
/* 1016 */           try { if (out != null) out.close(); 
/* 1017 */             if (writer != null) writer.close();  } catch (IOException iOException) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1022 */         long stop = System.currentTimeMillis();
/* 1023 */         long millisecondsDuration = stop - start;
/*      */         
/* 1025 */         if (doDiag)
/*      */         
/* 1027 */         { Object[] msgArgs = { inFileName, xslFileName, new Long(millisecondsDuration) };
/* 1028 */           String msg = XSLMessages.createMessage("diagTiming", msgArgs);
/* 1029 */           diagnosticsWriter.println('\n');
/* 1030 */           diagnosticsWriter.println(msg); }  } catch (Throwable throwable)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1037 */         while (throwable instanceof WrappedRuntimeException)
/*      */         {
/* 1039 */           throwable = ((WrappedRuntimeException)throwable).getException();
/*      */         }
/*      */ 
/*      */         
/* 1043 */         if (throwable instanceof NullPointerException || throwable instanceof ClassCastException)
/*      */         {
/* 1045 */           doStackDumpOnError = true;
/*      */         }
/* 1047 */         diagnosticsWriter.println();
/*      */         
/* 1049 */         if (doStackDumpOnError) {
/* 1050 */           throwable.printStackTrace(dumpWriter);
/*      */         } else {
/*      */           
/* 1053 */           DefaultErrorHandler.printLocation(diagnosticsWriter, throwable);
/* 1054 */           diagnosticsWriter.println(XSLMessages.createMessage("ER_XSLT_ERROR", null) + " (" + throwable.getClass().getName() + "): " + throwable.getMessage());
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1061 */         if (null != dumpFileName)
/*      */         {
/* 1063 */           dumpWriter.close();
/*      */         }
/*      */         
/* 1066 */         doExit(-1); }
/*      */ 
/*      */       
/* 1069 */       if (null != dumpFileName)
/*      */       {
/* 1071 */         dumpWriter.close();
/*      */       }
/*      */       
/* 1074 */       if (null != diagnosticsWriter);
/*      */     } 
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
/*      */   static void doExit(int i) {
/* 1093 */     System.exit(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void waitForReturnKey(ResourceBundle resbundle) {
/* 1103 */     System.out.println(resbundle.getString("xslProc_return_to_continue")); 
/*      */     try { do {
/*      */       
/* 1106 */       } while (System.in.read() != 10); } catch (IOException iOException) {}
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
/*      */   private static void printInvalidXSLTCOption(String option) {
/* 1118 */     System.err.println(XSLMessages.createMessage("xslProc_invalid_xsltc_option", new Object[] { option }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void printInvalidXalanOption(String option) {
/* 1128 */     System.err.println(XSLMessages.createMessage("xslProc_invalid_xalan_option", new Object[] { option }));
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xslt/Process.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */