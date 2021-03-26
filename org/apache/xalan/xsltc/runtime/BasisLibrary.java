/*      */ package org.apache.xalan.xsltc.runtime;
/*      */ 
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.FieldPosition;
/*      */ import java.text.MessageFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Locale;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.transform.dom.DOMSource;
/*      */ import org.apache.xalan.xsltc.DOM;
/*      */ import org.apache.xalan.xsltc.Translet;
/*      */ import org.apache.xalan.xsltc.dom.AbsoluteIterator;
/*      */ import org.apache.xalan.xsltc.dom.DOMAdapter;
/*      */ import org.apache.xalan.xsltc.dom.MultiDOM;
/*      */ import org.apache.xalan.xsltc.dom.SingletonIterator;
/*      */ import org.apache.xalan.xsltc.dom.StepIterator;
/*      */ import org.apache.xml.dtm.DTMAxisIterator;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBase;
/*      */ import org.apache.xml.serializer.NamespaceMappings;
/*      */ import org.apache.xml.serializer.SerializationHandler;
/*      */ import org.apache.xml.utils.XMLChar;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.xml.sax.SAXException;
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
/*      */ public final class BasisLibrary
/*      */   implements Operators
/*      */ {
/*      */   private static final String EMPTYSTRING = "";
/*      */   private static final int DOUBLE_FRACTION_DIGITS = 340;
/*      */   private static final double lowerBounds = 0.001D;
/*      */   private static final double upperBounds = 1.0E7D;
/*      */   private static DecimalFormat defaultFormatter;
/*      */   
/*      */   public static int countF(DTMAxisIterator iterator) {
/*   65 */     return iterator.getLast();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int positionF(DTMAxisIterator iterator) {
/*   74 */     return iterator.isReverse() ? (iterator.getLast() - iterator.getPosition() + 1) : iterator.getPosition();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double sumF(DTMAxisIterator iterator, DOM dom) {
/*      */     
/*   85 */     try { double result = 0.0D;
/*      */       int node;
/*   87 */       while ((node = iterator.next()) != -1) {
/*   88 */         result += Double.parseDouble(dom.getStringValueX(node));
/*      */       }
/*   90 */       return result; } catch (NumberFormatException e)
/*      */     
/*      */     { 
/*   93 */       return Double.NaN; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stringF(int node, DOM dom) {
/*  101 */     return dom.getStringValueX(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stringF(Object obj, DOM dom) {
/*  108 */     if (obj instanceof DTMAxisIterator) {
/*  109 */       return dom.getStringValueX(((DTMAxisIterator)obj).reset().next());
/*      */     }
/*  111 */     if (obj instanceof Node) {
/*  112 */       return dom.getStringValueX(((Node)obj).node);
/*      */     }
/*  114 */     if (obj instanceof DOM) {
/*  115 */       return ((DOM)obj).getStringValue();
/*      */     }
/*      */     
/*  118 */     return obj.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stringF(Object obj, int node, DOM dom) {
/*  126 */     if (obj instanceof DTMAxisIterator) {
/*  127 */       return dom.getStringValueX(((DTMAxisIterator)obj).reset().next());
/*      */     }
/*  129 */     if (obj instanceof Node) {
/*  130 */       return dom.getStringValueX(((Node)obj).node);
/*      */     }
/*  132 */     if (obj instanceof DOM)
/*      */     {
/*      */ 
/*      */       
/*  136 */       return ((DOM)obj).getStringValue();
/*      */     }
/*  138 */     if (obj instanceof Double) {
/*  139 */       Double d = (Double)obj;
/*  140 */       String result = d.toString();
/*  141 */       int length = result.length();
/*  142 */       if (result.charAt(length - 2) == '.' && result.charAt(length - 1) == '0')
/*      */       {
/*  144 */         return result.substring(0, length - 2);
/*      */       }
/*  146 */       return result;
/*      */     } 
/*      */     
/*  149 */     if (obj != null) {
/*  150 */       return obj.toString();
/*      */     }
/*  152 */     return stringF(node, dom);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double numberF(int node, DOM dom) {
/*  160 */     return stringToReal(dom.getStringValueX(node));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double numberF(Object obj, DOM dom) {
/*  167 */     if (obj instanceof Double) {
/*  168 */       return ((Double)obj).doubleValue();
/*      */     }
/*  170 */     if (obj instanceof Integer) {
/*  171 */       return ((Integer)obj).doubleValue();
/*      */     }
/*  173 */     if (obj instanceof Boolean) {
/*  174 */       return ((Boolean)obj).booleanValue() ? 1.0D : 0.0D;
/*      */     }
/*  176 */     if (obj instanceof String) {
/*  177 */       return stringToReal((String)obj);
/*      */     }
/*  179 */     if (obj instanceof DTMAxisIterator) {
/*  180 */       DTMAxisIterator iter = (DTMAxisIterator)obj;
/*  181 */       return stringToReal(dom.getStringValueX(iter.reset().next()));
/*      */     } 
/*  183 */     if (obj instanceof Node) {
/*  184 */       return stringToReal(dom.getStringValueX(((Node)obj).node));
/*      */     }
/*  186 */     if (obj instanceof DOM) {
/*  187 */       return stringToReal(((DOM)obj).getStringValue());
/*      */     }
/*      */     
/*  190 */     String className = obj.getClass().getName();
/*  191 */     runTimeError("INVALID_ARGUMENT_ERR", className, "number()");
/*  192 */     return 0.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double roundF(double d) {
/*  200 */     return (d < -0.5D || d > 0.0D) ? Math.floor(d + 0.5D) : ((d == 0.0D) ? d : (Double.isNaN(d) ? Double.NaN : -0.0D));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean booleanF(Object obj) {
/*  208 */     if (obj instanceof Double) {
/*  209 */       double temp = ((Double)obj).doubleValue();
/*  210 */       return (temp != 0.0D && !Double.isNaN(temp));
/*      */     } 
/*  212 */     if (obj instanceof Integer) {
/*  213 */       return (((Integer)obj).doubleValue() != 0.0D);
/*      */     }
/*  215 */     if (obj instanceof Boolean) {
/*  216 */       return ((Boolean)obj).booleanValue();
/*      */     }
/*  218 */     if (obj instanceof String) {
/*  219 */       return !((String)obj).equals("");
/*      */     }
/*  221 */     if (obj instanceof DTMAxisIterator) {
/*  222 */       DTMAxisIterator iter = (DTMAxisIterator)obj;
/*  223 */       return (iter.reset().next() != -1);
/*      */     } 
/*  225 */     if (obj instanceof Node) {
/*  226 */       return true;
/*      */     }
/*  228 */     if (obj instanceof DOM) {
/*  229 */       String temp = ((DOM)obj).getStringValue();
/*  230 */       return !temp.equals("");
/*      */     } 
/*      */     
/*  233 */     String className = obj.getClass().getName();
/*  234 */     runTimeError("INVALID_ARGUMENT_ERR", className, "number()");
/*      */     
/*  236 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substringF(String value, double start) {
/*      */     
/*  245 */     try { int strlen = value.length();
/*  246 */       int istart = (int)Math.round(start) - 1;
/*      */       
/*  248 */       if (Double.isNaN(start)) return ""; 
/*  249 */       if (istart > strlen) return ""; 
/*  250 */       if (istart < 1) istart = 0;
/*      */       
/*  252 */       return value.substring(istart); } catch (IndexOutOfBoundsException e)
/*      */     
/*      */     { 
/*  255 */       runTimeError("RUN_TIME_INTERNAL_ERR", "substring()");
/*  256 */       return null; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substringF(String value, double start, double length) {
/*      */     
/*  266 */     try { int strlen = value.length();
/*  267 */       int istart = (int)Math.round(start) - 1;
/*  268 */       int isum = istart + (int)Math.round(length);
/*      */       
/*  270 */       if (Double.isInfinite(length)) isum = Integer.MAX_VALUE;
/*      */       
/*  272 */       if (Double.isNaN(start) || Double.isNaN(length))
/*  273 */         return ""; 
/*  274 */       if (Double.isInfinite(start)) return ""; 
/*  275 */       if (istart > strlen) return ""; 
/*  276 */       if (isum < 0) return ""; 
/*  277 */       if (istart < 0) istart = 0;
/*      */       
/*  279 */       if (isum > strlen) {
/*  280 */         return value.substring(istart);
/*      */       }
/*  282 */       return value.substring(istart, isum); } catch (IndexOutOfBoundsException e)
/*      */     
/*      */     { 
/*  285 */       runTimeError("RUN_TIME_INTERNAL_ERR", "substring()");
/*  286 */       return null; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substring_afterF(String value, String substring) {
/*  294 */     int index = value.indexOf(substring);
/*  295 */     if (index >= 0) {
/*  296 */       return value.substring(index + substring.length());
/*      */     }
/*  298 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substring_beforeF(String value, String substring) {
/*  305 */     int index = value.indexOf(substring);
/*  306 */     if (index >= 0) {
/*  307 */       return value.substring(0, index);
/*      */     }
/*  309 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String translateF(String value, String from, String to) {
/*  316 */     int tol = to.length();
/*  317 */     int froml = from.length();
/*  318 */     int valuel = value.length();
/*      */     
/*  320 */     StringBuffer result = new StringBuffer();
/*  321 */     for (int i = 0; i < valuel; i++) {
/*  322 */       char ch = value.charAt(i); int j;
/*  323 */       for (j = 0; j < froml; j++) {
/*  324 */         if (ch == from.charAt(j)) {
/*  325 */           if (j < tol)
/*  326 */             result.append(to.charAt(j)); 
/*      */           break;
/*      */         } 
/*      */       } 
/*  330 */       if (j == froml)
/*  331 */         result.append(ch); 
/*      */     } 
/*  333 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String normalize_spaceF(int node, DOM dom) {
/*  340 */     return normalize_spaceF(dom.getStringValueX(node));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String normalize_spaceF(String value) {
/*  347 */     int i = 0, n = value.length();
/*  348 */     StringBuffer result = new StringBuffer();
/*      */     
/*  350 */     while (i < n && isWhiteSpace(value.charAt(i))) {
/*  351 */       i++;
/*      */     }
/*      */     while (true) {
/*  354 */       if (i >= n || isWhiteSpace(value.charAt(i))) {
/*      */ 
/*      */         
/*  357 */         if (i == n)
/*      */           break; 
/*  359 */         while (i < n && isWhiteSpace(value.charAt(i))) {
/*  360 */           i++;
/*      */         }
/*  362 */         if (i < n)
/*  363 */           result.append(' ');  continue;
/*      */       }  result.append(value.charAt(i++));
/*  365 */     }  return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String generate_idF(int node) {
/*  372 */     if (node > 0)
/*      */     {
/*  374 */       return "N" + node;
/*      */     }
/*      */     
/*  377 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getLocalName(String value) {
/*  384 */     int idx = value.lastIndexOf(':');
/*  385 */     if (idx >= 0) value = value.substring(idx + 1); 
/*  386 */     idx = value.lastIndexOf('@');
/*  387 */     if (idx >= 0) value = value.substring(idx + 1); 
/*  388 */     return value;
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
/*      */   public static void unresolved_externalF(String name) {
/*  401 */     runTimeError("EXTERNAL_FUNC_ERR", name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void unsupported_ElementF(String qname, boolean isExtension) {
/*  412 */     if (isExtension) {
/*  413 */       runTimeError("UNSUPPORTED_EXT_ERR", qname);
/*      */     } else {
/*  415 */       runTimeError("UNSUPPORTED_XSL_ERR", qname);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String namespace_uriF(DTMAxisIterator iter, DOM dom) {
/*  422 */     return namespace_uriF(iter.next(), dom);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String system_propertyF(String name) {
/*  429 */     if (name.equals("xsl:version"))
/*  430 */       return "1.0"; 
/*  431 */     if (name.equals("xsl:vendor"))
/*  432 */       return "Apache Software Foundation (Xalan XSLTC)"; 
/*  433 */     if (name.equals("xsl:vendor-url")) {
/*  434 */       return "http://xml.apache.org/xalan-j";
/*      */     }
/*  436 */     runTimeError("INVALID_ARGUMENT_ERR", name, "system-property()");
/*  437 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String namespace_uriF(int node, DOM dom) {
/*  444 */     String value = dom.getNodeName(node);
/*  445 */     int colon = value.lastIndexOf(':');
/*  446 */     if (colon >= 0) {
/*  447 */       return value.substring(0, colon);
/*      */     }
/*  449 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String objectTypeF(Object obj) {
/*  459 */     if (obj instanceof String)
/*  460 */       return "string"; 
/*  461 */     if (obj instanceof Boolean)
/*  462 */       return "boolean"; 
/*  463 */     if (obj instanceof Number)
/*  464 */       return "number"; 
/*  465 */     if (obj instanceof DOM)
/*  466 */       return "RTF"; 
/*  467 */     if (obj instanceof DTMAxisIterator) {
/*  468 */       return "node-set";
/*      */     }
/*  470 */     return "unknown";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static DTMAxisIterator nodesetF(Object obj) {
/*  477 */     if (obj instanceof DOM) {
/*      */       
/*  479 */       DOM dom = (DOM)obj;
/*  480 */       return (DTMAxisIterator)new SingletonIterator(dom.getDocument(), true);
/*      */     } 
/*  482 */     if (obj instanceof DTMAxisIterator) {
/*  483 */       return (DTMAxisIterator)obj;
/*      */     }
/*      */     
/*  486 */     String className = obj.getClass().getName();
/*  487 */     runTimeError("DATA_CONVERSION_ERR", "node-set", className);
/*  488 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isWhiteSpace(char ch) {
/*  495 */     return (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r');
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean compareStrings(String lstring, String rstring, int op, DOM dom) {
/*  500 */     switch (op) {
/*      */       case 0:
/*  502 */         return lstring.equals(rstring);
/*      */       
/*      */       case 1:
/*  505 */         return !lstring.equals(rstring);
/*      */       
/*      */       case 2:
/*  508 */         return (numberF(lstring, dom) > numberF(rstring, dom));
/*      */       
/*      */       case 3:
/*  511 */         return (numberF(lstring, dom) < numberF(rstring, dom));
/*      */       
/*      */       case 4:
/*  514 */         return (numberF(lstring, dom) >= numberF(rstring, dom));
/*      */       
/*      */       case 5:
/*  517 */         return (numberF(lstring, dom) <= numberF(rstring, dom));
/*      */     } 
/*      */     
/*  520 */     runTimeError("RUN_TIME_INTERNAL_ERR", "compare()");
/*  521 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean compare(DTMAxisIterator left, DTMAxisIterator right, int op, DOM dom) {
/*  531 */     left.reset();
/*      */     int lnode;
/*  533 */     while ((lnode = left.next()) != -1) {
/*  534 */       String lvalue = dom.getStringValueX(lnode);
/*      */ 
/*      */       
/*  537 */       right.reset(); int rnode;
/*  538 */       while ((rnode = right.next()) != -1) {
/*      */         
/*  540 */         if (lnode == rnode) {
/*  541 */           if (op == 0)
/*  542 */             return true; 
/*  543 */           if (op == 1) {
/*      */             continue;
/*      */           }
/*      */         } 
/*  547 */         if (compareStrings(lvalue, dom.getStringValueX(rnode), op, dom))
/*      */         {
/*  549 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*  553 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean compare(int node, DTMAxisIterator iterator, int op, DOM dom) {
/*      */     int rnode;
/*  563 */     switch (op) {
/*      */       case 0:
/*  565 */         rnode = iterator.next();
/*  566 */         if (rnode != -1) {
/*  567 */           String value = dom.getStringValueX(node);
/*      */           do {
/*  569 */             if (node == rnode || value.equals(dom.getStringValueX(rnode)))
/*      */             {
/*  571 */               return true;
/*      */             }
/*  573 */           } while ((rnode = iterator.next()) != -1);
/*      */         } 
/*      */         break;
/*      */       case 1:
/*  577 */         rnode = iterator.next();
/*  578 */         if (rnode != -1) {
/*  579 */           String str = dom.getStringValueX(node);
/*      */           do {
/*  581 */             if (node != rnode && !str.equals(dom.getStringValueX(rnode)))
/*      */             {
/*  583 */               return true;
/*      */             }
/*  585 */           } while ((rnode = iterator.next()) != -1);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 3:
/*  590 */         while ((rnode = iterator.next()) != -1) {
/*  591 */           if (rnode > node) return true;
/*      */         
/*      */         } 
/*      */         break;
/*      */       case 2:
/*  596 */         while ((rnode = iterator.next()) != -1) {
/*  597 */           if (rnode < node) return true; 
/*      */         } 
/*      */         break;
/*      */     } 
/*  601 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean compare(DTMAxisIterator left, double rnumber, int op, DOM dom) {
/*      */     int node;
/*  612 */     switch (op)
/*      */     { case 0:
/*  614 */         while ((node = left.next()) != -1) {
/*  615 */           if (numberF(dom.getStringValueX(node), dom) == rnumber) {
/*  616 */             return true;
/*      */           }
/*      */         } 
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
/*  659 */         return false;case 1: while ((node = left.next()) != -1) { if (numberF(dom.getStringValueX(node), dom) != rnumber) return true;  }  return false;case 2: while ((node = left.next()) != -1) { if (numberF(dom.getStringValueX(node), dom) > rnumber) return true;  }  return false;case 3: while ((node = left.next()) != -1) { if (numberF(dom.getStringValueX(node), dom) < rnumber) return true;  }  return false;case 4: while ((node = left.next()) != -1) { if (numberF(dom.getStringValueX(node), dom) >= rnumber) return true;  }  return false;case 5: while ((node = left.next()) != -1) { if (numberF(dom.getStringValueX(node), dom) <= rnumber) return true;  }  return false; }  runTimeError("RUN_TIME_INTERNAL_ERR", "compare()"); return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean compare(DTMAxisIterator left, String rstring, int op, DOM dom) {
/*      */     int node;
/*  669 */     while ((node = left.next()) != -1) {
/*  670 */       if (compareStrings(dom.getStringValueX(node), rstring, op, dom)) {
/*  671 */         return true;
/*      */       }
/*      */     } 
/*  674 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean compare(Object left, Object right, int op, DOM dom) {
/*  681 */     boolean result = false;
/*  682 */     boolean hasSimpleArgs = (hasSimpleType(left) && hasSimpleType(right));
/*      */     
/*  684 */     if (op != 0 && op != 1) {
/*      */       
/*  686 */       if (left instanceof Node || right instanceof Node) {
/*  687 */         if (left instanceof Boolean) {
/*  688 */           right = new Boolean(booleanF(right));
/*  689 */           hasSimpleArgs = true;
/*      */         } 
/*  691 */         if (right instanceof Boolean) {
/*  692 */           left = new Boolean(booleanF(left));
/*  693 */           hasSimpleArgs = true;
/*      */         } 
/*      */       } 
/*      */       
/*  697 */       if (hasSimpleArgs) {
/*  698 */         switch (op) {
/*      */           case 2:
/*  700 */             return (numberF(left, dom) > numberF(right, dom));
/*      */           
/*      */           case 3:
/*  703 */             return (numberF(left, dom) < numberF(right, dom));
/*      */           
/*      */           case 4:
/*  706 */             return (numberF(left, dom) >= numberF(right, dom));
/*      */           
/*      */           case 5:
/*  709 */             return (numberF(left, dom) <= numberF(right, dom));
/*      */         } 
/*      */         
/*  712 */         runTimeError("RUN_TIME_INTERNAL_ERR", "compare()");
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  718 */     if (hasSimpleArgs) {
/*  719 */       if (left instanceof Boolean || right instanceof Boolean) {
/*  720 */         result = (booleanF(left) == booleanF(right));
/*      */       }
/*  722 */       else if (left instanceof Double || right instanceof Double || left instanceof Integer || right instanceof Integer) {
/*      */         
/*  724 */         result = (numberF(left, dom) == numberF(right, dom));
/*      */       } else {
/*      */         
/*  727 */         result = stringF(left, dom).equals(stringF(right, dom));
/*      */       } 
/*      */       
/*  730 */       if (op == 1) {
/*  731 */         result = !result;
/*      */       }
/*      */     } else {
/*      */       
/*  735 */       if (left instanceof Node) {
/*  736 */         left = new SingletonIterator(((Node)left).node);
/*      */       }
/*  738 */       if (right instanceof Node) {
/*  739 */         right = new SingletonIterator(((Node)right).node);
/*      */       }
/*      */       
/*  742 */       if (hasSimpleType(left) || (left instanceof DOM && right instanceof DTMAxisIterator)) {
/*      */ 
/*      */         
/*  745 */         Object temp = right; right = left; left = temp;
/*      */       } 
/*      */       
/*  748 */       if (left instanceof DOM) {
/*  749 */         if (right instanceof Boolean) {
/*  750 */           result = ((Boolean)right).booleanValue();
/*  751 */           return (result == ((op == 0)));
/*      */         } 
/*      */         
/*  754 */         String sleft = ((DOM)left).getStringValue();
/*      */         
/*  756 */         if (right instanceof Number) {
/*  757 */           result = (((Number)right).doubleValue() == stringToReal(sleft));
/*      */         
/*      */         }
/*  760 */         else if (right instanceof String) {
/*  761 */           result = sleft.equals(right);
/*      */         }
/*  763 */         else if (right instanceof DOM) {
/*  764 */           result = sleft.equals(((DOM)right).getStringValue());
/*      */         } 
/*      */         
/*  767 */         if (op == 1) {
/*  768 */           result = !result;
/*      */         }
/*  770 */         return result;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  775 */       DTMAxisIterator iter = ((DTMAxisIterator)left).reset();
/*      */       
/*  777 */       if (right instanceof DTMAxisIterator) {
/*  778 */         result = compare(iter, (DTMAxisIterator)right, op, dom);
/*      */       }
/*  780 */       else if (right instanceof String) {
/*  781 */         result = compare(iter, (String)right, op, dom);
/*      */       }
/*  783 */       else if (right instanceof Number) {
/*  784 */         double temp = ((Number)right).doubleValue();
/*  785 */         result = compare(iter, temp, op, dom);
/*      */       }
/*  787 */       else if (right instanceof Boolean) {
/*  788 */         boolean temp = ((Boolean)right).booleanValue();
/*  789 */         result = (((iter.reset().next() != -1)) == temp);
/*      */       }
/*  791 */       else if (right instanceof DOM) {
/*  792 */         result = compare(iter, ((DOM)right).getStringValue(), op, dom);
/*      */       } else {
/*      */         
/*  795 */         if (right == null) {
/*  796 */           return false;
/*      */         }
/*      */         
/*  799 */         String className = right.getClass().getName();
/*  800 */         runTimeError("INVALID_ARGUMENT_ERR", className, "compare()");
/*      */       } 
/*      */     } 
/*  803 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean testLanguage(String testLang, DOM dom, int node) {
/*  811 */     String nodeLang = dom.getLanguage(node);
/*  812 */     if (nodeLang == null) {
/*  813 */       return false;
/*      */     }
/*  815 */     nodeLang = nodeLang.toLowerCase();
/*      */ 
/*      */     
/*  818 */     testLang = testLang.toLowerCase();
/*  819 */     if (testLang.length() == 2) {
/*  820 */       return nodeLang.startsWith(testLang);
/*      */     }
/*      */     
/*  823 */     return nodeLang.equals(testLang);
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean hasSimpleType(Object obj) {
/*  828 */     return (obj instanceof Boolean || obj instanceof Double || obj instanceof Integer || obj instanceof String || obj instanceof Node || obj instanceof DOM);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double stringToReal(String s) {
/*      */     
/*  838 */     try { return Double.valueOf(s).doubleValue(); } catch (NumberFormatException e)
/*      */     
/*      */     { 
/*  841 */       return Double.NaN; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int stringToInt(String s) {
/*      */     
/*  850 */     try { return Integer.parseInt(s); } catch (NumberFormatException e)
/*      */     
/*      */     { 
/*  853 */       return -1; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  861 */   private static String defaultPattern = ""; private static FieldPosition _fieldPosition; private static char[] _characterArray; private static int prefixIndex; public static final String RUN_TIME_INTERNAL_ERR = "RUN_TIME_INTERNAL_ERR"; public static final String RUN_TIME_COPY_ERR = "RUN_TIME_COPY_ERR"; public static final String DATA_CONVERSION_ERR = "DATA_CONVERSION_ERR"; public static final String EXTERNAL_FUNC_ERR = "EXTERNAL_FUNC_ERR"; public static final String EQUALITY_EXPR_ERR = "EQUALITY_EXPR_ERR"; public static final String INVALID_ARGUMENT_ERR = "INVALID_ARGUMENT_ERR"; public static final String FORMAT_NUMBER_ERR = "FORMAT_NUMBER_ERR"; public static final String ITERATOR_CLONE_ERR = "ITERATOR_CLONE_ERR"; public static final String AXIS_SUPPORT_ERR = "AXIS_SUPPORT_ERR"; public static final String TYPED_AXIS_SUPPORT_ERR = "TYPED_AXIS_SUPPORT_ERR"; public static final String STRAY_ATTRIBUTE_ERR = "STRAY_ATTRIBUTE_ERR"; public static final String STRAY_NAMESPACE_ERR = "STRAY_NAMESPACE_ERR"; public static final String NAMESPACE_PREFIX_ERR = "NAMESPACE_PREFIX_ERR"; public static final String DOM_ADAPTER_INIT_ERR = "DOM_ADAPTER_INIT_ERR"; public static final String PARSER_DTD_SUPPORT_ERR = "PARSER_DTD_SUPPORT_ERR"; public static final String NAMESPACES_SUPPORT_ERR = "NAMESPACES_SUPPORT_ERR"; public static final String CANT_RESOLVE_RELATIVE_URI_ERR = "CANT_RESOLVE_RELATIVE_URI_ERR"; public static final String UNSUPPORTED_XSL_ERR = "UNSUPPORTED_XSL_ERR"; public static final String UNSUPPORTED_EXT_ERR = "UNSUPPORTED_EXT_ERR"; public static final String UNKNOWN_TRANSLET_VERSION_ERR = "UNKNOWN_TRANSLET_VERSION_ERR"; public static final String INVALID_QNAME_ERR = "INVALID_QNAME_ERR"; public static final String INVALID_NCNAME_ERR = "INVALID_NCNAME_ERR"; protected static ResourceBundle m_bundle;
/*      */   public static final String ERROR_MESSAGES_KEY = "error-messages";
/*      */   
/*  864 */   static { NumberFormat f = NumberFormat.getInstance(Locale.getDefault());
/*  865 */     defaultFormatter = (f instanceof DecimalFormat) ? (DecimalFormat)f : new DecimalFormat();
/*      */ 
/*      */ 
/*      */     
/*  869 */     defaultFormatter.setMaximumFractionDigits(340);
/*  870 */     defaultFormatter.setMinimumFractionDigits(0);
/*  871 */     defaultFormatter.setMinimumIntegerDigits(1);
/*  872 */     defaultFormatter.setGroupingUsed(false);
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
/*  910 */     _fieldPosition = new FieldPosition(0);
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
/* 1252 */     _characterArray = new char[32];
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
/* 1403 */     prefixIndex = 0;
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
/* 1457 */     String resource = "org.apache.xalan.xsltc.runtime.ErrorMessages";
/* 1458 */     m_bundle = ResourceBundle.getBundle(resource); }
/*      */   public static String realToString(double d) { double m = Math.abs(d); if (m >= 0.001D && m < 1.0E7D) { String result = Double.toString(d); int length = result.length(); if (result.charAt(length - 2) == '.' && result.charAt(length - 1) == '0') return result.substring(0, length - 2);  return result; }  if (Double.isNaN(d) || Double.isInfinite(d)) return Double.toString(d);  return formatNumber(d, defaultPattern, defaultFormatter); }
/*      */   public static int realToInt(double d) { return (int)d; }
/*      */   public static String formatNumber(double number, String pattern, DecimalFormat formatter) { if (formatter == null) formatter = defaultFormatter;  try { StringBuffer result = new StringBuffer(); if (pattern != defaultPattern) formatter.applyLocalizedPattern(pattern);  formatter.format(number, result, _fieldPosition); return result.toString(); } catch (IllegalArgumentException e) { runTimeError("FORMAT_NUMBER_ERR", Double.toString(number), pattern); return ""; }  }
/*      */   public static DTMAxisIterator referenceToNodeSet(Object obj) { if (obj instanceof Node) return (DTMAxisIterator)new SingletonIterator(((Node)obj).node);  if (obj instanceof DTMAxisIterator) return ((DTMAxisIterator)obj).cloneIterator();  String className = obj.getClass().getName(); runTimeError("DATA_CONVERSION_ERR", className, "node-set"); return null; }
/*      */   public static NodeList referenceToNodeList(Object obj, DOM dom) { if (obj instanceof Node || obj instanceof DTMAxisIterator) { DTMAxisIterator iter = referenceToNodeSet(obj); return dom.makeNodeList(iter); }  if (obj instanceof DOM) { dom = (DOM)obj; return dom.makeNodeList(0); }  String className = obj.getClass().getName(); runTimeError("DATA_CONVERSION_ERR", className, "org.w3c.dom.NodeList"); return null; } public static Node referenceToNode(Object obj, DOM dom) { if (obj instanceof Node || obj instanceof DTMAxisIterator) { DTMAxisIterator iter = referenceToNodeSet(obj); return dom.makeNode(iter); }  if (obj instanceof DOM) { dom = (DOM)obj; DTMAxisIterator iter = dom.getChildren(0); return dom.makeNode(iter); }  String className = obj.getClass().getName(); runTimeError("DATA_CONVERSION_ERR", className, "org.w3c.dom.Node"); return null; } public static long referenceToLong(Object obj) { if (obj instanceof Number) return ((Number)obj).longValue();  String className = obj.getClass().getName(); runTimeError("DATA_CONVERSION_ERR", className, long.class); return 0L; } public static double referenceToDouble(Object obj) { if (obj instanceof Number) return ((Number)obj).doubleValue();  String className = obj.getClass().getName(); runTimeError("DATA_CONVERSION_ERR", className, double.class); return 0.0D; } public static boolean referenceToBoolean(Object obj) { if (obj instanceof Boolean) return ((Boolean)obj).booleanValue();  String className = obj.getClass().getName(); runTimeError("DATA_CONVERSION_ERR", className, boolean.class); return false; } public static String referenceToString(Object obj, DOM dom) { if (obj instanceof String) return (String)obj;  if (obj instanceof DTMAxisIterator) return dom.getStringValueX(((DTMAxisIterator)obj).reset().next());  if (obj instanceof Node) return dom.getStringValueX(((Node)obj).node);  if (obj instanceof DOM) return ((DOM)obj).getStringValue();  String className = obj.getClass().getName(); runTimeError("DATA_CONVERSION_ERR", className, String.class); return null; } public static DTMAxisIterator node2Iterator(Node node, Translet translet, DOM dom) { Node inNode = node; NodeList nodelist = new NodeList(inNode) {
/*      */         private final Node val$inNode; public int getLength() { return 1; } public Node item(int index) { if (index == 0) return this.val$inNode;  return null; }
/* 1465 */       }; return nodeList2Iterator(nodelist, translet, dom); } public static void runTimeError(String code) { throw new RuntimeException(m_bundle.getString(code)); }
/*      */   private static void copyNodes(NodeList nodeList, Document doc, Node parent) { int size = nodeList.getLength(); for (int i = 0; i < size; i++) { Element element; Node curr = nodeList.item(i); int nodeType = curr.getNodeType(); String value = null; try { value = curr.getNodeValue(); } catch (DOMException ex) { runTimeError("RUN_TIME_INTERNAL_ERR", ex.getMessage()); return; }  String nodeName = curr.getNodeName(); Node newNode = null; switch (nodeType) { case 2: newNode = doc.createAttributeNS(curr.getNamespaceURI(), nodeName); break;case 4: newNode = doc.createCDATASection(value); break;case 8: newNode = doc.createComment(value); break;case 11: newNode = doc.createDocumentFragment(); break;case 9: newNode = doc.createElementNS(null, "__document__"); copyNodes(curr.getChildNodes(), doc, newNode); break;case 1: element = doc.createElementNS(curr.getNamespaceURI(), nodeName); if (curr.hasAttributes()) { NamedNodeMap attributes = curr.getAttributes(); for (int k = 0; k < attributes.getLength(); k++) { Node attr = attributes.item(k); element.setAttributeNS(attr.getNamespaceURI(), attr.getNodeName(), attr.getNodeValue()); }  }  copyNodes(curr.getChildNodes(), doc, element); newNode = element; break;case 5: newNode = doc.createEntityReference(nodeName); break;case 7: newNode = doc.createProcessingInstruction(nodeName, value); break;case 3: newNode = doc.createTextNode(value); break; }  try { parent.appendChild(newNode); } catch (DOMException e) { runTimeError("RUN_TIME_INTERNAL_ERR", e.getMessage()); return; }  }  }
/*      */   public static DTMAxisIterator nodeList2Iterator(NodeList nodeList, Translet translet, DOM dom) { DocumentBuilderFactory dfac = DocumentBuilderFactory.newInstance(); DocumentBuilder docbldr = null; try { docbldr = dfac.newDocumentBuilder(); } catch (ParserConfigurationException e) { runTimeError("RUN_TIME_INTERNAL_ERR", e.getMessage()); return null; }  Document doc = docbldr.newDocument(); Node topElementNode = doc.appendChild(doc.createElementNS("", "__top__")); copyNodes(nodeList, doc, topElementNode); if (dom instanceof MultiDOM) { MultiDOM multiDOM = (MultiDOM)dom; DTMDefaultBase dtm = (DTMDefaultBase)((DOMAdapter)multiDOM.getMain()).getDOMImpl(); DTMManager dtmManager = dtm.getManager(); DOM idom = (DOM)dtmManager.getDTM(new DOMSource(doc), false, null, true, false); DOMAdapter domAdapter = new DOMAdapter(idom, translet.getNamesArray(), translet.getUrisArray(), translet.getTypesArray(), translet.getNamespaceArray()); multiDOM.addDOMAdapter(domAdapter); DTMAxisIterator iter1 = idom.getAxisIterator(3); DTMAxisIterator iter2 = idom.getAxisIterator(3); AbsoluteIterator absoluteIterator = new AbsoluteIterator((DTMAxisIterator)new StepIterator(iter1, iter2)); absoluteIterator.setStartNode(0); return (DTMAxisIterator)absoluteIterator; }  runTimeError("RUN_TIME_INTERNAL_ERR", "nodeList2Iterator()"); return null; }
/*      */   public static DOM referenceToResultTree(Object obj) { try { return (DOM)obj; } catch (IllegalArgumentException e) { String className = obj.getClass().getName(); runTimeError("DATA_CONVERSION_ERR", "reference", className); return null; }  }
/* 1469 */   public static DTMAxisIterator getSingleNode(DTMAxisIterator iterator) { int node = iterator.next(); return (DTMAxisIterator)new SingletonIterator(node); } public static void copy(Object obj, SerializationHandler handler, int node, DOM dom) { try { if (obj instanceof DTMAxisIterator) { DTMAxisIterator iter = (DTMAxisIterator)obj; dom.copy(iter.reset(), handler); } else if (obj instanceof Node) { dom.copy(((Node)obj).node, handler); } else if (obj instanceof DOM) { DOM newDom = (DOM)obj; newDom.copy(newDom.getDocument(), handler); } else { String string = obj.toString(); int length = string.length(); if (length > _characterArray.length) _characterArray = new char[length];  string.getChars(0, length, _characterArray, 0); handler.characters(_characterArray, 0, length); }  } catch (SAXException e) { runTimeError("RUN_TIME_COPY_ERR"); }  } public static void checkAttribQName(String name) { int firstOccur = name.indexOf(":"); int lastOccur = name.lastIndexOf(":"); String localName = name.substring(lastOccur + 1); if (firstOccur > 0) { String newPrefix = name.substring(0, firstOccur); if (firstOccur != lastOccur) { String oriPrefix = name.substring(firstOccur + 1, lastOccur); if (!XMLChar.isValidNCName(oriPrefix)) runTimeError("INVALID_QNAME_ERR", oriPrefix + ":" + localName);  }  if (!XMLChar.isValidNCName(newPrefix)) runTimeError("INVALID_QNAME_ERR", newPrefix + ":" + localName);  }  if (!XMLChar.isValidNCName(localName) || localName.equals("xmlns")) runTimeError("INVALID_QNAME_ERR", localName);  } public static void checkNCName(String name) { if (!XMLChar.isValidNCName(name)) runTimeError("INVALID_NCNAME_ERR", name);  } public static void checkQName(String name) { if (!XMLChar.isValidQName(name)) runTimeError("INVALID_QNAME_ERR", name);  } public static String startXslElement(String qname, String namespace, SerializationHandler handler, DOM dom, int node) { try { int index = qname.indexOf(':'); if (index > 0) { String prefix = qname.substring(0, index); if (namespace == null || namespace.length() == 0) try { namespace = dom.lookupNamespace(node, prefix); } catch (RuntimeException e) { handler.flushPending(); NamespaceMappings nm = handler.getNamespaceMappings(); namespace = nm.lookupNamespace(prefix); if (namespace == null) runTimeError("NAMESPACE_PREFIX_ERR", prefix);  }   handler.startElement(namespace, qname.substring(index + 1), qname); handler.namespaceAfterStartElement(prefix, namespace); } else if (namespace != null && namespace.length() > 0) { String str = generatePrefix(); qname = str + ':' + qname; handler.startElement(namespace, qname, qname); handler.namespaceAfterStartElement(str, namespace); } else { handler.startElement(null, null, qname); }  } catch (SAXException e) { throw new RuntimeException(e.getMessage()); }  return qname; } public static String getPrefix(String qname) { int index = qname.indexOf(':'); return (index > 0) ? qname.substring(0, index) : null; } public static String generatePrefix() { return "ns" + prefixIndex++; } public static void runTimeError(String code, Object[] args) { String message = MessageFormat.format(m_bundle.getString(code), args);
/*      */     
/* 1471 */     throw new RuntimeException(message); }
/*      */ 
/*      */   
/*      */   public static void runTimeError(String code, Object arg0) {
/* 1475 */     runTimeError(code, new Object[] { arg0 });
/*      */   }
/*      */   
/*      */   public static void runTimeError(String code, Object arg0, Object arg1) {
/* 1479 */     runTimeError(code, new Object[] { arg0, arg1 });
/*      */   }
/*      */   
/*      */   public static void consoleOutput(String msg) {
/* 1483 */     System.out.println(msg);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replace(String base, char ch, String str) {
/* 1490 */     return (base.indexOf(ch) < 0) ? base : replace(base, String.valueOf(ch), new String[] { str });
/*      */   }
/*      */ 
/*      */   
/*      */   public static String replace(String base, String delim, String[] str) {
/* 1495 */     int len = base.length();
/* 1496 */     StringBuffer result = new StringBuffer();
/*      */     
/* 1498 */     for (int i = 0; i < len; i++) {
/* 1499 */       char ch = base.charAt(i);
/* 1500 */       int k = delim.indexOf(ch);
/*      */       
/* 1502 */       if (k >= 0) {
/* 1503 */         result.append(str[k]);
/*      */       } else {
/*      */         
/* 1506 */         result.append(ch);
/*      */       } 
/*      */     } 
/* 1509 */     return result.toString();
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
/*      */   public static String mapQNameToJavaName(String base) {
/* 1523 */     return replace(base, ".-:/{}?#%*", new String[] { "$dot$", "$dash$", "$colon$", "$slash$", "", "$colon$", "$ques$", "$hash$", "$per$", "$aster$" });
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/BasisLibrary.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */