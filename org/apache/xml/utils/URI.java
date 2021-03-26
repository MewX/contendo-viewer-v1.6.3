/*      */ package org.apache.xml.utils;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import org.apache.xml.res.XMLMessages;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class URI
/*      */   implements Serializable
/*      */ {
/*      */   private static final String RESERVED_CHARACTERS = ";/?:@&=+$,";
/*      */   private static final String MARK_CHARACTERS = "-_.!~*'() ";
/*      */   private static final String SCHEME_CHARACTERS = "+-.";
/*      */   private static final String USERINFO_CHARACTERS = ";:&=+$,";
/*      */   
/*      */   public static class MalformedURIException
/*      */     extends IOException
/*      */   {
/*      */     public MalformedURIException() {}
/*      */     
/*      */     public MalformedURIException(String p_msg) {
/*   87 */       super(p_msg);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  111 */   private String m_scheme = null;
/*      */ 
/*      */ 
/*      */   
/*  115 */   private String m_userinfo = null;
/*      */ 
/*      */ 
/*      */   
/*  119 */   private String m_host = null;
/*      */ 
/*      */ 
/*      */   
/*  123 */   private int m_port = -1;
/*      */ 
/*      */ 
/*      */   
/*  127 */   private String m_path = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   private String m_queryString = null;
/*      */ 
/*      */ 
/*      */   
/*  138 */   private String m_fragment = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean DEBUG = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URI() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URI(URI p_other) {
/*  156 */     initialize(p_other);
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
/*      */   public URI(String p_uriSpec) throws MalformedURIException {
/*  176 */     this((URI)null, p_uriSpec);
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
/*      */   public URI(URI p_base, String p_uriSpec) throws MalformedURIException {
/*  193 */     initialize(p_base, p_uriSpec);
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
/*      */   public URI(String p_scheme, String p_schemeSpecificPart) throws MalformedURIException {
/*  212 */     if (p_scheme == null || p_scheme.trim().length() == 0)
/*      */     {
/*  214 */       throw new MalformedURIException("Cannot construct URI with null/empty scheme!");
/*      */     }
/*      */ 
/*      */     
/*  218 */     if (p_schemeSpecificPart == null || p_schemeSpecificPart.trim().length() == 0)
/*      */     {
/*      */       
/*  221 */       throw new MalformedURIException("Cannot construct URI with null/empty scheme-specific part!");
/*      */     }
/*      */ 
/*      */     
/*  225 */     setScheme(p_scheme);
/*  226 */     setPath(p_schemeSpecificPart);
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
/*      */   public URI(String p_scheme, String p_host, String p_path, String p_queryString, String p_fragment) throws MalformedURIException {
/*  253 */     this(p_scheme, null, p_host, -1, p_path, p_queryString, p_fragment);
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
/*      */   
/*      */   public URI(String p_scheme, String p_userinfo, String p_host, int p_port, String p_path, String p_queryString, String p_fragment) throws MalformedURIException {
/*  285 */     if (p_scheme == null || p_scheme.trim().length() == 0)
/*      */     {
/*  287 */       throw new MalformedURIException(XMLMessages.createXMLMessage("ER_SCHEME_REQUIRED", null));
/*      */     }
/*      */     
/*  290 */     if (p_host == null) {
/*      */       
/*  292 */       if (p_userinfo != null)
/*      */       {
/*  294 */         throw new MalformedURIException(XMLMessages.createXMLMessage("ER_NO_USERINFO_IF_NO_HOST", null));
/*      */       }
/*      */ 
/*      */       
/*  298 */       if (p_port != -1)
/*      */       {
/*  300 */         throw new MalformedURIException(XMLMessages.createXMLMessage("ER_NO_PORT_IF_NO_HOST", null));
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  305 */     if (p_path != null) {
/*      */       
/*  307 */       if (p_path.indexOf('?') != -1 && p_queryString != null)
/*      */       {
/*  309 */         throw new MalformedURIException(XMLMessages.createXMLMessage("ER_NO_QUERY_STRING_IN_PATH", null));
/*      */       }
/*      */ 
/*      */       
/*  313 */       if (p_path.indexOf('#') != -1 && p_fragment != null)
/*      */       {
/*  315 */         throw new MalformedURIException(XMLMessages.createXMLMessage("ER_NO_FRAGMENT_STRING_IN_PATH", null));
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  320 */     setScheme(p_scheme);
/*  321 */     setHost(p_host);
/*  322 */     setPort(p_port);
/*  323 */     setUserinfo(p_userinfo);
/*  324 */     setPath(p_path);
/*  325 */     setQueryString(p_queryString);
/*  326 */     setFragment(p_fragment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initialize(URI p_other) {
/*  337 */     this.m_scheme = p_other.getScheme();
/*  338 */     this.m_userinfo = p_other.getUserinfo();
/*  339 */     this.m_host = p_other.getHost();
/*  340 */     this.m_port = p_other.getPort();
/*  341 */     this.m_path = p_other.getPath();
/*  342 */     this.m_queryString = p_other.getQueryString();
/*  343 */     this.m_fragment = p_other.getFragment();
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
/*      */   private void initialize(URI p_base, String p_uriSpec) throws MalformedURIException {
/*  366 */     if (p_base == null && (p_uriSpec == null || p_uriSpec.trim().length() == 0))
/*      */     {
/*      */       
/*  369 */       throw new MalformedURIException(XMLMessages.createXMLMessage("ER_CANNOT_INIT_URI_EMPTY_PARMS", null));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  374 */     if (p_uriSpec == null || p_uriSpec.trim().length() == 0) {
/*      */       
/*  376 */       initialize(p_base);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  381 */     String uriSpec = p_uriSpec.trim();
/*  382 */     int uriSpecLen = uriSpec.length();
/*  383 */     int index = 0;
/*      */ 
/*      */     
/*  386 */     int colonIndex = uriSpec.indexOf(':');
/*  387 */     if (colonIndex < 0) {
/*      */       
/*  389 */       if (p_base == null)
/*      */       {
/*  391 */         throw new MalformedURIException(XMLMessages.createXMLMessage("ER_NO_SCHEME_IN_URI", new Object[] { uriSpec }));
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  396 */       initializeScheme(uriSpec);
/*  397 */       uriSpec = uriSpec.substring(colonIndex + 1);
/*  398 */       uriSpecLen = uriSpec.length();
/*      */     } 
/*      */ 
/*      */     
/*  402 */     if (index + 1 < uriSpecLen && uriSpec.substring(index).startsWith("//")) {
/*      */ 
/*      */       
/*  405 */       index += 2;
/*      */       
/*  407 */       int startPos = index;
/*      */ 
/*      */       
/*  410 */       char testChar = Character.MIN_VALUE;
/*      */       
/*  412 */       while (index < uriSpecLen) {
/*      */         
/*  414 */         testChar = uriSpec.charAt(index);
/*      */         
/*  416 */         if (testChar == '/' || testChar == '?' || testChar == '#') {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/*  421 */         index++;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  426 */       if (index > startPos) {
/*      */         
/*  428 */         initializeAuthority(uriSpec.substring(startPos, index));
/*      */       }
/*      */       else {
/*      */         
/*  432 */         this.m_host = "";
/*      */       } 
/*      */     } 
/*      */     
/*  436 */     initializePath(uriSpec.substring(index));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  443 */     if (p_base != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  453 */       if (this.m_path.length() == 0 && this.m_scheme == null && this.m_host == null) {
/*      */         
/*  455 */         this.m_scheme = p_base.getScheme();
/*  456 */         this.m_userinfo = p_base.getUserinfo();
/*  457 */         this.m_host = p_base.getHost();
/*  458 */         this.m_port = p_base.getPort();
/*  459 */         this.m_path = p_base.getPath();
/*      */         
/*  461 */         if (this.m_queryString == null)
/*      */         {
/*  463 */           this.m_queryString = p_base.getQueryString();
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/*  471 */       if (this.m_scheme == null)
/*      */       {
/*  473 */         this.m_scheme = p_base.getScheme();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  478 */       if (this.m_host == null) {
/*      */         
/*  480 */         this.m_userinfo = p_base.getUserinfo();
/*  481 */         this.m_host = p_base.getHost();
/*  482 */         this.m_port = p_base.getPort();
/*      */       } else {
/*      */         return;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  490 */       if (this.m_path.length() > 0 && this.m_path.startsWith("/")) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  497 */       String path = new String();
/*  498 */       String basePath = p_base.getPath();
/*      */ 
/*      */       
/*  501 */       if (basePath != null) {
/*      */         
/*  503 */         int lastSlash = basePath.lastIndexOf('/');
/*      */         
/*  505 */         if (lastSlash != -1)
/*      */         {
/*  507 */           path = basePath.substring(0, lastSlash + 1);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  512 */       path = path.concat(this.m_path);
/*      */ 
/*      */       
/*  515 */       index = -1;
/*      */       
/*  517 */       while ((index = path.indexOf("/./")) != -1)
/*      */       {
/*  519 */         path = path.substring(0, index + 1).concat(path.substring(index + 3));
/*      */       }
/*      */ 
/*      */       
/*  523 */       if (path.endsWith("/."))
/*      */       {
/*  525 */         path = path.substring(0, path.length() - 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  530 */       index = -1;
/*      */       
/*  532 */       int segIndex = -1;
/*  533 */       String tempString = null;
/*      */       
/*  535 */       while ((index = path.indexOf("/../")) > 0) {
/*      */         
/*  537 */         tempString = path.substring(0, path.indexOf("/../"));
/*  538 */         segIndex = tempString.lastIndexOf('/');
/*      */         
/*  540 */         if (segIndex != -1)
/*      */         {
/*  542 */           if (!tempString.substring(segIndex++).equals(".."))
/*      */           {
/*  544 */             path = path.substring(0, segIndex).concat(path.substring(index + 4));
/*      */           }
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  552 */       if (path.endsWith("/..")) {
/*      */         
/*  554 */         tempString = path.substring(0, path.length() - 3);
/*  555 */         segIndex = tempString.lastIndexOf('/');
/*      */         
/*  557 */         if (segIndex != -1)
/*      */         {
/*  559 */           path = path.substring(0, segIndex + 1);
/*      */         }
/*      */       } 
/*      */       
/*  563 */       this.m_path = path;
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
/*      */   private void initializeScheme(String p_uriSpec) throws MalformedURIException {
/*  578 */     int uriSpecLen = p_uriSpec.length();
/*  579 */     int index = 0;
/*  580 */     String scheme = null;
/*  581 */     char testChar = Character.MIN_VALUE;
/*      */     
/*  583 */     while (index < uriSpecLen) {
/*      */       
/*  585 */       testChar = p_uriSpec.charAt(index);
/*      */       
/*  587 */       if (testChar == ':' || testChar == '/' || testChar == '?' || testChar == '#') {
/*      */         break;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  593 */       index++;
/*      */     } 
/*      */     
/*  596 */     scheme = p_uriSpec.substring(0, index);
/*      */     
/*  598 */     if (scheme.length() == 0)
/*      */     {
/*  600 */       throw new MalformedURIException(XMLMessages.createXMLMessage("ER_NO_SCHEME_INURI", null));
/*      */     }
/*      */ 
/*      */     
/*  604 */     setScheme(scheme);
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
/*      */   private void initializeAuthority(String p_uriSpec) throws MalformedURIException {
/*  620 */     int index = 0;
/*  621 */     int start = 0;
/*  622 */     int end = p_uriSpec.length();
/*  623 */     char testChar = Character.MIN_VALUE;
/*  624 */     String userinfo = null;
/*      */ 
/*      */     
/*  627 */     if (p_uriSpec.indexOf('@', start) != -1) {
/*      */       
/*  629 */       while (index < end) {
/*      */         
/*  631 */         testChar = p_uriSpec.charAt(index);
/*      */         
/*  633 */         if (testChar == '@') {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/*  638 */         index++;
/*      */       } 
/*      */       
/*  641 */       userinfo = p_uriSpec.substring(start, index);
/*      */       
/*  643 */       index++;
/*      */     } 
/*      */ 
/*      */     
/*  647 */     String host = null;
/*      */     
/*  649 */     start = index;
/*      */     
/*  651 */     while (index < end) {
/*      */       
/*  653 */       testChar = p_uriSpec.charAt(index);
/*      */       
/*  655 */       if (testChar == ':') {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  660 */       index++;
/*      */     } 
/*      */     
/*  663 */     host = p_uriSpec.substring(start, index);
/*      */     
/*  665 */     int port = -1;
/*      */     
/*  667 */     if (host.length() > 0)
/*      */     {
/*      */ 
/*      */       
/*  671 */       if (testChar == ':') {
/*      */ 
/*      */ 
/*      */         
/*  675 */         start = ++index;
/*      */         
/*  677 */         while (index < end)
/*      */         {
/*  679 */           index++;
/*      */         }
/*      */         
/*  682 */         String portStr = p_uriSpec.substring(start, index);
/*      */         
/*  684 */         if (portStr.length() > 0) {
/*      */           
/*  686 */           for (int i = 0; i < portStr.length(); i++) {
/*      */             
/*  688 */             if (!isDigit(portStr.charAt(i)))
/*      */             {
/*  690 */               throw new MalformedURIException(portStr + " is invalid. Port should only contain digits!");
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  697 */           try { port = Integer.parseInt(portStr); } catch (NumberFormatException numberFormatException) {}
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  708 */     setHost(host);
/*  709 */     setPort(port);
/*  710 */     setUserinfo(userinfo);
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
/*      */   private void initializePath(String p_uriSpec) throws MalformedURIException {
/*  723 */     if (p_uriSpec == null)
/*      */     {
/*  725 */       throw new MalformedURIException("Cannot initialize path from null string!");
/*      */     }
/*      */ 
/*      */     
/*  729 */     int index = 0;
/*  730 */     int start = 0;
/*  731 */     int end = p_uriSpec.length();
/*  732 */     char testChar = Character.MIN_VALUE;
/*      */ 
/*      */     
/*  735 */     while (index < end) {
/*      */       
/*  737 */       testChar = p_uriSpec.charAt(index);
/*      */       
/*  739 */       if (testChar == '?' || testChar == '#') {
/*      */         break;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  745 */       if (testChar == '%') {
/*      */         
/*  747 */         if (index + 2 >= end || !isHex(p_uriSpec.charAt(index + 1)) || !isHex(p_uriSpec.charAt(index + 2)))
/*      */         {
/*      */           
/*  750 */           throw new MalformedURIException(XMLMessages.createXMLMessage("ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE", null));
/*      */         
/*      */         }
/*      */       }
/*  754 */       else if (!isReservedCharacter(testChar) && !isUnreservedCharacter(testChar)) {
/*      */ 
/*      */         
/*  757 */         if ('\\' != testChar) {
/*  758 */           throw new MalformedURIException(XMLMessages.createXMLMessage("ER_PATH_INVALID_CHAR", new Object[] { String.valueOf(testChar) }));
/*      */         }
/*      */       } 
/*      */       
/*  762 */       index++;
/*      */     } 
/*      */     
/*  765 */     this.m_path = p_uriSpec.substring(start, index);
/*      */ 
/*      */     
/*  768 */     if (testChar == '?') {
/*      */ 
/*      */ 
/*      */       
/*  772 */       start = ++index;
/*      */       
/*  774 */       while (index < end) {
/*      */         
/*  776 */         testChar = p_uriSpec.charAt(index);
/*      */         
/*  778 */         if (testChar == '#') {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/*  783 */         if (testChar == '%') {
/*      */           
/*  785 */           if (index + 2 >= end || !isHex(p_uriSpec.charAt(index + 1)) || !isHex(p_uriSpec.charAt(index + 2)))
/*      */           {
/*      */             
/*  788 */             throw new MalformedURIException("Query string contains invalid escape sequence!");
/*      */           
/*      */           }
/*      */         }
/*  792 */         else if (!isReservedCharacter(testChar) && !isUnreservedCharacter(testChar)) {
/*      */ 
/*      */           
/*  795 */           throw new MalformedURIException("Query string contains invalid character:" + testChar);
/*      */         } 
/*      */ 
/*      */         
/*  799 */         index++;
/*      */       } 
/*      */       
/*  802 */       this.m_queryString = p_uriSpec.substring(start, index);
/*      */     } 
/*      */ 
/*      */     
/*  806 */     if (testChar == '#') {
/*      */ 
/*      */ 
/*      */       
/*  810 */       start = ++index;
/*      */       
/*  812 */       while (index < end) {
/*      */         
/*  814 */         testChar = p_uriSpec.charAt(index);
/*      */         
/*  816 */         if (testChar == '%') {
/*      */           
/*  818 */           if (index + 2 >= end || !isHex(p_uriSpec.charAt(index + 1)) || !isHex(p_uriSpec.charAt(index + 2)))
/*      */           {
/*      */             
/*  821 */             throw new MalformedURIException("Fragment contains invalid escape sequence!");
/*      */           
/*      */           }
/*      */         }
/*  825 */         else if (!isReservedCharacter(testChar) && !isUnreservedCharacter(testChar)) {
/*      */ 
/*      */           
/*  828 */           throw new MalformedURIException("Fragment contains invalid character:" + testChar);
/*      */         } 
/*      */ 
/*      */         
/*  832 */         index++;
/*      */       } 
/*      */       
/*  835 */       this.m_fragment = p_uriSpec.substring(start, index);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getScheme() {
/*  846 */     return this.m_scheme;
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
/*      */   public String getSchemeSpecificPart() {
/*  858 */     StringBuffer schemespec = new StringBuffer();
/*      */     
/*  860 */     if (this.m_userinfo != null || this.m_host != null || this.m_port != -1)
/*      */     {
/*  862 */       schemespec.append("//");
/*      */     }
/*      */     
/*  865 */     if (this.m_userinfo != null) {
/*      */       
/*  867 */       schemespec.append(this.m_userinfo);
/*  868 */       schemespec.append('@');
/*      */     } 
/*      */     
/*  871 */     if (this.m_host != null)
/*      */     {
/*  873 */       schemespec.append(this.m_host);
/*      */     }
/*      */     
/*  876 */     if (this.m_port != -1) {
/*      */       
/*  878 */       schemespec.append(':');
/*  879 */       schemespec.append(this.m_port);
/*      */     } 
/*      */     
/*  882 */     if (this.m_path != null)
/*      */     {
/*  884 */       schemespec.append(this.m_path);
/*      */     }
/*      */     
/*  887 */     if (this.m_queryString != null) {
/*      */       
/*  889 */       schemespec.append('?');
/*  890 */       schemespec.append(this.m_queryString);
/*      */     } 
/*      */     
/*  893 */     if (this.m_fragment != null) {
/*      */       
/*  895 */       schemespec.append('#');
/*  896 */       schemespec.append(this.m_fragment);
/*      */     } 
/*      */     
/*  899 */     return schemespec.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUserinfo() {
/*  909 */     return this.m_userinfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getHost() {
/*  919 */     return this.m_host;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPort() {
/*  929 */     return this.m_port;
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
/*      */   public String getPath(boolean p_includeQueryString, boolean p_includeFragment) {
/*  950 */     StringBuffer pathString = new StringBuffer(this.m_path);
/*      */     
/*  952 */     if (p_includeQueryString && this.m_queryString != null) {
/*      */       
/*  954 */       pathString.append('?');
/*  955 */       pathString.append(this.m_queryString);
/*      */     } 
/*      */     
/*  958 */     if (p_includeFragment && this.m_fragment != null) {
/*      */       
/*  960 */       pathString.append('#');
/*  961 */       pathString.append(this.m_fragment);
/*      */     } 
/*      */     
/*  964 */     return pathString.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPath() {
/*  975 */     return this.m_path;
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
/*      */   public String getQueryString() {
/*  987 */     return this.m_queryString;
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
/*      */   public String getFragment() {
/*  999 */     return this.m_fragment;
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
/*      */   public void setScheme(String p_scheme) throws MalformedURIException {
/* 1014 */     if (p_scheme == null)
/*      */     {
/* 1016 */       throw new MalformedURIException(XMLMessages.createXMLMessage("ER_SCHEME_FROM_NULL_STRING", null));
/*      */     }
/*      */     
/* 1019 */     if (!isConformantSchemeName(p_scheme))
/*      */     {
/* 1021 */       throw new MalformedURIException(XMLMessages.createXMLMessage("ER_SCHEME_NOT_CONFORMANT", null));
/*      */     }
/*      */     
/* 1024 */     this.m_scheme = p_scheme.toLowerCase();
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
/*      */   public void setUserinfo(String p_userinfo) throws MalformedURIException {
/* 1039 */     if (p_userinfo == null) {
/*      */       
/* 1041 */       this.m_userinfo = null;
/*      */     }
/*      */     else {
/*      */       
/* 1045 */       if (this.m_host == null)
/*      */       {
/* 1047 */         throw new MalformedURIException("Userinfo cannot be set when host is null!");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1053 */       int index = 0;
/* 1054 */       int end = p_userinfo.length();
/* 1055 */       char testChar = Character.MIN_VALUE;
/*      */       
/* 1057 */       while (index < end) {
/*      */         
/* 1059 */         testChar = p_userinfo.charAt(index);
/*      */         
/* 1061 */         if (testChar == '%') {
/*      */           
/* 1063 */           if (index + 2 >= end || !isHex(p_userinfo.charAt(index + 1)) || !isHex(p_userinfo.charAt(index + 2)))
/*      */           {
/*      */             
/* 1066 */             throw new MalformedURIException("Userinfo contains invalid escape sequence!");
/*      */           
/*      */           }
/*      */         }
/* 1070 */         else if (!isUnreservedCharacter(testChar) && ";:&=+$,".indexOf(testChar) == -1) {
/*      */ 
/*      */           
/* 1073 */           throw new MalformedURIException("Userinfo contains invalid character:" + testChar);
/*      */         } 
/*      */ 
/*      */         
/* 1077 */         index++;
/*      */       } 
/*      */     } 
/*      */     
/* 1081 */     this.m_userinfo = p_userinfo;
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
/*      */   public void setHost(String p_host) throws MalformedURIException {
/* 1096 */     if (p_host == null || p_host.trim().length() == 0) {
/*      */       
/* 1098 */       this.m_host = p_host;
/* 1099 */       this.m_userinfo = null;
/* 1100 */       this.m_port = -1;
/*      */     }
/* 1102 */     else if (!isWellFormedAddress(p_host)) {
/*      */       
/* 1104 */       throw new MalformedURIException(XMLMessages.createXMLMessage("ER_HOST_ADDRESS_NOT_WELLFORMED", null));
/*      */     } 
/*      */     
/* 1107 */     this.m_host = p_host;
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
/*      */   public void setPort(int p_port) throws MalformedURIException {
/* 1124 */     if (p_port >= 0 && p_port <= 65535) {
/*      */       
/* 1126 */       if (this.m_host == null)
/*      */       {
/* 1128 */         throw new MalformedURIException(XMLMessages.createXMLMessage("ER_PORT_WHEN_HOST_NULL", null));
/*      */       
/*      */       }
/*      */     }
/* 1132 */     else if (p_port != -1) {
/*      */       
/* 1134 */       throw new MalformedURIException(XMLMessages.createXMLMessage("ER_INVALID_PORT", null));
/*      */     } 
/*      */     
/* 1137 */     this.m_port = p_port;
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
/*      */   public void setPath(String p_path) throws MalformedURIException {
/* 1157 */     if (p_path == null) {
/*      */       
/* 1159 */       this.m_path = null;
/* 1160 */       this.m_queryString = null;
/* 1161 */       this.m_fragment = null;
/*      */     }
/*      */     else {
/*      */       
/* 1165 */       initializePath(p_path);
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
/*      */   
/*      */   public void appendPath(String p_addToPath) throws MalformedURIException {
/* 1185 */     if (p_addToPath == null || p_addToPath.trim().length() == 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1190 */     if (!isURIString(p_addToPath))
/*      */     {
/* 1192 */       throw new MalformedURIException(XMLMessages.createXMLMessage("ER_PATH_INVALID_CHAR", new Object[] { p_addToPath }));
/*      */     }
/*      */     
/* 1195 */     if (this.m_path == null || this.m_path.trim().length() == 0) {
/*      */       
/* 1197 */       if (p_addToPath.startsWith("/"))
/*      */       {
/* 1199 */         this.m_path = p_addToPath;
/*      */       }
/*      */       else
/*      */       {
/* 1203 */         this.m_path = "/" + p_addToPath;
/*      */       }
/*      */     
/* 1206 */     } else if (this.m_path.endsWith("/")) {
/*      */       
/* 1208 */       if (p_addToPath.startsWith("/"))
/*      */       {
/* 1210 */         this.m_path = this.m_path.concat(p_addToPath.substring(1));
/*      */       }
/*      */       else
/*      */       {
/* 1214 */         this.m_path = this.m_path.concat(p_addToPath);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1219 */     else if (p_addToPath.startsWith("/")) {
/*      */       
/* 1221 */       this.m_path = this.m_path.concat(p_addToPath);
/*      */     }
/*      */     else {
/*      */       
/* 1225 */       this.m_path = this.m_path.concat("/" + p_addToPath);
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
/*      */   
/*      */   public void setQueryString(String p_queryString) throws MalformedURIException {
/* 1245 */     if (p_queryString == null) {
/*      */       
/* 1247 */       this.m_queryString = null;
/*      */     } else {
/* 1249 */       if (!isGenericURI())
/*      */       {
/* 1251 */         throw new MalformedURIException("Query string can only be set for a generic URI!");
/*      */       }
/*      */       
/* 1254 */       if (getPath() == null)
/*      */       {
/* 1256 */         throw new MalformedURIException("Query string cannot be set when path is null!");
/*      */       }
/*      */       
/* 1259 */       if (!isURIString(p_queryString))
/*      */       {
/* 1261 */         throw new MalformedURIException("Query string contains invalid character!");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1266 */       this.m_queryString = p_queryString;
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
/*      */   public void setFragment(String p_fragment) throws MalformedURIException {
/* 1284 */     if (p_fragment == null) {
/*      */       
/* 1286 */       this.m_fragment = null;
/*      */     } else {
/* 1288 */       if (!isGenericURI())
/*      */       {
/* 1290 */         throw new MalformedURIException(XMLMessages.createXMLMessage("ER_FRAG_FOR_GENERIC_URI", null));
/*      */       }
/*      */       
/* 1293 */       if (getPath() == null)
/*      */       {
/* 1295 */         throw new MalformedURIException(XMLMessages.createXMLMessage("ER_FRAG_WHEN_PATH_NULL", null));
/*      */       }
/*      */       
/* 1298 */       if (!isURIString(p_fragment))
/*      */       {
/* 1300 */         throw new MalformedURIException(XMLMessages.createXMLMessage("ER_FRAG_INVALID_CHAR", null));
/*      */       }
/*      */ 
/*      */       
/* 1304 */       this.m_fragment = p_fragment;
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
/*      */   public boolean equals(Object p_test) {
/* 1319 */     if (p_test instanceof URI) {
/*      */       
/* 1321 */       URI testURI = (URI)p_test;
/*      */       
/* 1323 */       if (((this.m_scheme == null && testURI.m_scheme == null) || (this.m_scheme != null && testURI.m_scheme != null && this.m_scheme.equals(testURI.m_scheme))) && ((this.m_userinfo == null && testURI.m_userinfo == null) || (this.m_userinfo != null && testURI.m_userinfo != null && this.m_userinfo.equals(testURI.m_userinfo))) && ((this.m_host == null && testURI.m_host == null) || (this.m_host != null && testURI.m_host != null && this.m_host.equals(testURI.m_host))) && this.m_port == testURI.m_port && ((this.m_path == null && testURI.m_path == null) || (this.m_path != null && testURI.m_path != null && this.m_path.equals(testURI.m_path))) && ((this.m_queryString == null && testURI.m_queryString == null) || (this.m_queryString != null && testURI.m_queryString != null && this.m_queryString.equals(testURI.m_queryString))) && ((this.m_fragment == null && testURI.m_fragment == null) || (this.m_fragment != null && testURI.m_fragment != null && this.m_fragment.equals(testURI.m_fragment))))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1331 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1335 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1346 */     StringBuffer uriSpecString = new StringBuffer();
/*      */     
/* 1348 */     if (this.m_scheme != null) {
/*      */       
/* 1350 */       uriSpecString.append(this.m_scheme);
/* 1351 */       uriSpecString.append(':');
/*      */     } 
/*      */     
/* 1354 */     uriSpecString.append(getSchemeSpecificPart());
/*      */     
/* 1356 */     return uriSpecString.toString();
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
/*      */   public boolean isGenericURI() {
/* 1371 */     return (this.m_host != null);
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
/*      */   public static boolean isConformantSchemeName(String p_scheme) {
/* 1386 */     if (p_scheme == null || p_scheme.trim().length() == 0)
/*      */     {
/* 1388 */       return false;
/*      */     }
/*      */     
/* 1391 */     if (!isAlpha(p_scheme.charAt(0)))
/*      */     {
/* 1393 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1398 */     for (int i = 1; i < p_scheme.length(); i++) {
/*      */       
/* 1400 */       char testChar = p_scheme.charAt(i);
/*      */       
/* 1402 */       if (!isAlphanum(testChar) && "+-.".indexOf(testChar) == -1)
/*      */       {
/* 1404 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1408 */     return true;
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
/*      */   public static boolean isWellFormedAddress(String p_address) {
/* 1427 */     if (p_address == null)
/*      */     {
/* 1429 */       return false;
/*      */     }
/*      */     
/* 1432 */     String address = p_address.trim();
/* 1433 */     int addrLength = address.length();
/*      */     
/* 1435 */     if (addrLength == 0 || addrLength > 255)
/*      */     {
/* 1437 */       return false;
/*      */     }
/*      */     
/* 1440 */     if (address.startsWith(".") || address.startsWith("-"))
/*      */     {
/* 1442 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1448 */     int index = address.lastIndexOf('.');
/*      */     
/* 1450 */     if (address.endsWith("."))
/*      */     {
/* 1452 */       index = address.substring(0, index).lastIndexOf('.');
/*      */     }
/*      */     
/* 1455 */     if (index + 1 < addrLength && isDigit(p_address.charAt(index + 1))) {
/*      */ 
/*      */       
/* 1458 */       int numDots = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1463 */       for (int i = 0; i < addrLength; i++) {
/*      */         
/* 1465 */         char testChar = address.charAt(i);
/*      */         
/* 1467 */         if (testChar == '.') {
/*      */           
/* 1469 */           if (!isDigit(address.charAt(i - 1)) || (i + 1 < addrLength && !isDigit(address.charAt(i + 1))))
/*      */           {
/*      */             
/* 1472 */             return false;
/*      */           }
/*      */           
/* 1475 */           numDots++;
/*      */         }
/* 1477 */         else if (!isDigit(testChar)) {
/*      */           
/* 1479 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/* 1483 */       if (numDots != 3)
/*      */       {
/* 1485 */         return false;
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1495 */       for (int i = 0; i < addrLength; i++) {
/*      */         
/* 1497 */         char testChar = address.charAt(i);
/*      */         
/* 1499 */         if (testChar == '.') {
/*      */           
/* 1501 */           if (!isAlphanum(address.charAt(i - 1)))
/*      */           {
/* 1503 */             return false;
/*      */           }
/*      */           
/* 1506 */           if (i + 1 < addrLength && !isAlphanum(address.charAt(i + 1)))
/*      */           {
/* 1508 */             return false;
/*      */           }
/*      */         }
/* 1511 */         else if (!isAlphanum(testChar) && testChar != '-') {
/*      */           
/* 1513 */           return false;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1518 */     return true;
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
/*      */   private static boolean isDigit(char p_char) {
/* 1530 */     return (p_char >= '0' && p_char <= '9');
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
/*      */   private static boolean isHex(char p_char) {
/* 1543 */     return (isDigit(p_char) || (p_char >= 'a' && p_char <= 'f') || (p_char >= 'A' && p_char <= 'F'));
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
/*      */   private static boolean isAlpha(char p_char) {
/* 1556 */     return ((p_char >= 'a' && p_char <= 'z') || (p_char >= 'A' && p_char <= 'Z'));
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
/*      */   private static boolean isAlphanum(char p_char) {
/* 1569 */     return (isAlpha(p_char) || isDigit(p_char));
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
/*      */   private static boolean isReservedCharacter(char p_char) {
/* 1582 */     return (";/?:@&=+$,".indexOf(p_char) != -1);
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
/*      */   private static boolean isUnreservedCharacter(char p_char) {
/* 1594 */     return (isAlphanum(p_char) || "-_.!~*'() ".indexOf(p_char) != -1);
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
/*      */   private static boolean isURIString(String p_uric) {
/* 1609 */     if (p_uric == null)
/*      */     {
/* 1611 */       return false;
/*      */     }
/*      */     
/* 1614 */     int end = p_uric.length();
/* 1615 */     char testChar = Character.MIN_VALUE;
/*      */     
/* 1617 */     for (int i = 0; i < end; i++) {
/*      */       
/* 1619 */       testChar = p_uric.charAt(i);
/*      */       
/* 1621 */       if (testChar == '%') {
/*      */         
/* 1623 */         if (i + 2 >= end || !isHex(p_uric.charAt(i + 1)) || !isHex(p_uric.charAt(i + 2)))
/*      */         {
/*      */           
/* 1626 */           return false;
/*      */         }
/*      */ 
/*      */         
/* 1630 */         i += 2;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1636 */       else if (!isReservedCharacter(testChar) && !isUnreservedCharacter(testChar)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1642 */         return false;
/*      */       } 
/*      */     } 
/*      */     
/* 1646 */     return true;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/URI.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */