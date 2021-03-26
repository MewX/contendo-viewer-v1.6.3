/*      */ package org.apache.xalan.templates;
/*      */ 
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Locale;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xalan.transformer.CountersTable;
/*      */ import org.apache.xalan.transformer.DecimalToRoman;
/*      */ import org.apache.xalan.transformer.TransformerImpl;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.utils.FastStringBuffer;
/*      */ import org.apache.xml.utils.NodeVector;
/*      */ import org.apache.xml.utils.PrefixResolver;
/*      */ import org.apache.xml.utils.StringBufferPool;
/*      */ import org.apache.xml.utils.res.XResourceBundle;
/*      */ import org.apache.xpath.ExpressionOwner;
/*      */ import org.apache.xpath.NodeSetDTM;
/*      */ import org.apache.xpath.XPath;
/*      */ import org.apache.xpath.XPathContext;
/*      */ import org.apache.xpath.objects.XObject;
/*      */ import org.w3c.dom.Node;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ElemNumber
/*      */   extends ElemTemplateElement
/*      */ {
/*      */   private class MyPrefixResolver
/*      */     implements PrefixResolver
/*      */   {
/*      */     DTM dtm;
/*      */     int handle;
/*      */     boolean handleNullPrefix;
/*      */     private final ElemNumber this$0;
/*      */     
/*      */     public MyPrefixResolver(ElemNumber this$0, Node xpathExpressionContext, DTM dtm, int handle, boolean handleNullPrefix) {
/*   82 */       this.this$0 = this$0;
/*   83 */       this.dtm = dtm;
/*   84 */       this.handle = handle;
/*   85 */       this.handleNullPrefix = handleNullPrefix;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getNamespaceForPrefix(String prefix) {
/*   92 */       return this.dtm.getNamespaceURI(this.handle);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getNamespaceForPrefix(String prefix, Node context) {
/*  100 */       return getNamespaceForPrefix(prefix);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getBaseIdentifier() {
/*  107 */       return this.this$0.getBaseIdentifier();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean handlesNullPrefixes() {
/*  114 */       return this.handleNullPrefix;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  123 */   private XPath m_countMatchPattern = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCount(XPath v) {
/*  138 */     this.m_countMatchPattern = v;
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
/*      */   public XPath getCount() {
/*  154 */     return this.m_countMatchPattern;
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
/*  168 */   private XPath m_fromMatchPattern = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFrom(XPath v) {
/*  184 */     this.m_fromMatchPattern = v;
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
/*      */   public XPath getFrom() {
/*  201 */     return this.m_fromMatchPattern;
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
/*  230 */   private int m_level = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLevel(int v) {
/*  242 */     this.m_level = v;
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
/*      */   public int getLevel() {
/*  255 */     return this.m_level;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  264 */   private XPath m_valueExpr = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValue(XPath v) {
/*  276 */     this.m_valueExpr = v;
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
/*      */   public XPath getValue() {
/*  289 */     return this.m_valueExpr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  298 */   private AVT m_format_avt = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(AVT v) {
/*  310 */     this.m_format_avt = v;
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
/*      */   public AVT getFormat() {
/*  323 */     return this.m_format_avt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  331 */   private AVT m_lang_avt = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLang(AVT v) {
/*  346 */     this.m_lang_avt = v;
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
/*      */   public AVT getLang() {
/*  362 */     return this.m_lang_avt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  370 */   private AVT m_lettervalue_avt = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLetterValue(AVT v) {
/*  382 */     this.m_lettervalue_avt = v;
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
/*      */   public AVT getLetterValue() {
/*  395 */     return this.m_lettervalue_avt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  404 */   private AVT m_groupingSeparator_avt = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGroupingSeparator(AVT v) {
/*  417 */     this.m_groupingSeparator_avt = v;
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
/*      */   public AVT getGroupingSeparator() {
/*  431 */     return this.m_groupingSeparator_avt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  438 */   private AVT m_groupingSize_avt = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGroupingSize(AVT v) {
/*  449 */     this.m_groupingSize_avt = v;
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
/*      */   public AVT getGroupingSize() {
/*  461 */     return this.m_groupingSize_avt;
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
/*  474 */   private static final DecimalToRoman[] m_romanConvertTable = new DecimalToRoman[] { new DecimalToRoman(1000L, "M", 900L, "CM"), new DecimalToRoman(500L, "D", 400L, "CD"), new DecimalToRoman(100L, "C", 90L, "XC"), new DecimalToRoman(50L, "L", 40L, "XL"), new DecimalToRoman(10L, "X", 9L, "IX"), new DecimalToRoman(5L, "V", 4L, "IV"), new DecimalToRoman(1L, "I", 1L, "I") };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  487 */   private static char[] m_alphaCountTable = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void compose(StylesheetRoot sroot) throws TransformerException {
/*  497 */     super.compose(sroot);
/*  498 */     StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/*  499 */     Vector vnames = cstate.getVariableNames();
/*  500 */     if (null != this.m_countMatchPattern)
/*  501 */       this.m_countMatchPattern.fixupVariables(vnames, cstate.getGlobalsSize()); 
/*  502 */     if (null != this.m_format_avt)
/*  503 */       this.m_format_avt.fixupVariables(vnames, cstate.getGlobalsSize()); 
/*  504 */     if (null != this.m_fromMatchPattern)
/*  505 */       this.m_fromMatchPattern.fixupVariables(vnames, cstate.getGlobalsSize()); 
/*  506 */     if (null != this.m_groupingSeparator_avt)
/*  507 */       this.m_groupingSeparator_avt.fixupVariables(vnames, cstate.getGlobalsSize()); 
/*  508 */     if (null != this.m_groupingSize_avt)
/*  509 */       this.m_groupingSize_avt.fixupVariables(vnames, cstate.getGlobalsSize()); 
/*  510 */     if (null != this.m_lang_avt)
/*  511 */       this.m_lang_avt.fixupVariables(vnames, cstate.getGlobalsSize()); 
/*  512 */     if (null != this.m_lettervalue_avt)
/*  513 */       this.m_lettervalue_avt.fixupVariables(vnames, cstate.getGlobalsSize()); 
/*  514 */     if (null != this.m_valueExpr) {
/*  515 */       this.m_valueExpr.fixupVariables(vnames, cstate.getGlobalsSize());
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
/*      */   public int getXSLToken() {
/*  527 */     return 35;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeName() {
/*  537 */     return "number";
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
/*      */   public void execute(TransformerImpl transformer) throws TransformerException {
/*  555 */     if (TransformerImpl.S_DEBUG) {
/*  556 */       transformer.getTraceManager().fireTraceEvent(this);
/*      */     }
/*  558 */     int sourceNode = transformer.getXPathContext().getCurrentNode();
/*  559 */     String countString = getCountString(transformer, sourceNode);
/*      */ 
/*      */ 
/*      */     
/*  563 */     try { transformer.getResultTreeHandler().characters(countString.toCharArray(), 0, countString.length()); } catch (SAXException se)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  568 */       throw new TransformerException(se); }
/*      */     
/*      */     finally
/*      */     
/*  572 */     { if (TransformerImpl.S_DEBUG) {
/*  573 */         transformer.getTraceManager().fireTraceEndEvent(this);
/*      */       } }
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
/*      */   public ElemTemplateElement appendChild(ElemTemplateElement newChild) {
/*  589 */     error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), getNodeName() });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  594 */     return null;
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
/*      */   int findAncestor(XPathContext xctxt, XPath fromMatchPattern, XPath countMatchPattern, int context, ElemNumber namespaceContext) throws TransformerException {
/*  618 */     DTM dtm = xctxt.getDTM(context);
/*  619 */     while (-1 != context) {
/*      */       
/*  621 */       if (null != fromMatchPattern)
/*      */       {
/*  623 */         if (fromMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
/*      */           break;
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  632 */       if (null != countMatchPattern)
/*      */       {
/*  634 */         if (countMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
/*      */           break;
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  641 */       context = dtm.getParent(context);
/*      */     } 
/*      */     
/*  644 */     return context;
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
/*      */   private int findPrecedingOrAncestorOrSelf(XPathContext xctxt, XPath fromMatchPattern, XPath countMatchPattern, int context, ElemNumber namespaceContext) throws TransformerException {
/*  668 */     DTM dtm = xctxt.getDTM(context);
/*  669 */     while (-1 != context) {
/*      */       
/*  671 */       if (null != fromMatchPattern)
/*      */       {
/*  673 */         if (fromMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
/*      */ 
/*      */           
/*  676 */           context = -1;
/*      */           
/*      */           break;
/*      */         } 
/*      */       }
/*      */       
/*  682 */       if (null != countMatchPattern)
/*      */       {
/*  684 */         if (countMatchPattern.getMatchScore(xctxt, context) != Double.NEGATIVE_INFINITY) {
/*      */           break;
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  691 */       int prevSibling = dtm.getPreviousSibling(context);
/*      */       
/*  693 */       if (-1 == prevSibling) {
/*      */         
/*  695 */         context = dtm.getParent(context);
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  701 */       context = dtm.getLastChild(prevSibling);
/*      */       
/*  703 */       if (context == -1) {
/*  704 */         context = prevSibling;
/*      */       }
/*      */     } 
/*      */     
/*  708 */     return context;
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
/*      */   XPath getCountMatchPattern(XPathContext support, int contextNode) throws TransformerException {
/*  725 */     XPath countMatchPattern = this.m_countMatchPattern;
/*  726 */     DTM dtm = support.getDTM(contextNode);
/*  727 */     if (null == countMatchPattern)
/*      */     { MyPrefixResolver myPrefixResolver;
/*  729 */       switch (dtm.getNodeType(contextNode))
/*      */       
/*      */       { 
/*      */         
/*      */         case 1:
/*  734 */           if (dtm.getNamespaceURI(contextNode) == null) {
/*  735 */             myPrefixResolver = new MyPrefixResolver(this, dtm.getNode(contextNode), dtm, contextNode, false);
/*      */           } else {
/*  737 */             myPrefixResolver = new MyPrefixResolver(this, dtm.getNode(contextNode), dtm, contextNode, true);
/*      */           } 
/*      */           
/*  740 */           countMatchPattern = new XPath(dtm.getNodeName(contextNode), (SourceLocator)this, myPrefixResolver, 1, support.getErrorListener());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  777 */           return countMatchPattern;case 2: countMatchPattern = new XPath("@" + dtm.getNodeName(contextNode), (SourceLocator)this, this, 1, support.getErrorListener()); return countMatchPattern;case 3: case 4: countMatchPattern = new XPath("text()", (SourceLocator)this, this, 1, support.getErrorListener()); return countMatchPattern;case 8: countMatchPattern = new XPath("comment()", (SourceLocator)this, this, 1, support.getErrorListener()); return countMatchPattern;case 9: countMatchPattern = new XPath("/", (SourceLocator)this, this, 1, support.getErrorListener()); return countMatchPattern;case 7: countMatchPattern = new XPath("pi(" + dtm.getNodeName(contextNode) + ")", (SourceLocator)this, this, 1, support.getErrorListener()); return countMatchPattern; }  countMatchPattern = null; }  return countMatchPattern;
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
/*      */   String getCountString(TransformerImpl transformer, int sourceNode) throws TransformerException {
/*  794 */     long[] list = null;
/*  795 */     XPathContext xctxt = transformer.getXPathContext();
/*  796 */     CountersTable ctable = transformer.getCountersTable();
/*      */     
/*  798 */     if (null != this.m_valueExpr) {
/*      */       
/*  800 */       XObject countObj = this.m_valueExpr.execute(xctxt, sourceNode, this);
/*  801 */       long count = (long)Math.floor(countObj.num() + 0.5D);
/*      */       
/*  803 */       list = new long[1];
/*  804 */       list[0] = count;
/*      */ 
/*      */     
/*      */     }
/*  808 */     else if (3 == this.m_level) {
/*      */       
/*  810 */       list = new long[1];
/*  811 */       list[0] = ctable.countNode(xctxt, this, sourceNode);
/*      */     }
/*      */     else {
/*      */       
/*  815 */       NodeVector ancestors = getMatchingAncestors(xctxt, sourceNode, (1 == this.m_level));
/*      */ 
/*      */       
/*  818 */       int lastIndex = ancestors.size() - 1;
/*      */       
/*  820 */       if (lastIndex >= 0) {
/*      */         
/*  822 */         list = new long[lastIndex + 1];
/*      */         
/*  824 */         for (int i = lastIndex; i >= 0; i--) {
/*      */           
/*  826 */           int target = ancestors.elementAt(i);
/*      */           
/*  828 */           list[lastIndex - i] = ctable.countNode(xctxt, this, target);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  834 */     return (null != list) ? formatNumberList(transformer, list, sourceNode) : "";
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
/*      */   public int getPreviousNode(XPathContext xctxt, int pos) throws TransformerException {
/*  852 */     XPath countMatchPattern = getCountMatchPattern(xctxt, pos);
/*  853 */     DTM dtm = xctxt.getDTM(pos);
/*      */     
/*  855 */     if (3 == this.m_level) {
/*      */       
/*  857 */       XPath fromMatchPattern = this.m_fromMatchPattern;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  862 */       while (-1 != pos)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  868 */         int next = dtm.getPreviousSibling(pos);
/*      */         
/*  870 */         if (-1 == next) {
/*      */           
/*  872 */           next = dtm.getParent(pos);
/*      */           
/*  874 */           if (-1 != next && ((null != fromMatchPattern && fromMatchPattern.getMatchScore(xctxt, next) != Double.NEGATIVE_INFINITY) || dtm.getNodeType(next) == 9)) {
/*      */ 
/*      */ 
/*      */             
/*  878 */             pos = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } else {
/*  887 */           int child = next;
/*      */           
/*  889 */           while (-1 != child) {
/*      */             
/*  891 */             child = dtm.getLastChild(next);
/*      */             
/*  893 */             if (-1 != child) {
/*  894 */               next = child;
/*      */             }
/*      */           } 
/*      */         } 
/*  898 */         pos = next;
/*      */         
/*  900 */         if (-1 != pos && (null == countMatchPattern || countMatchPattern.getMatchScore(xctxt, pos) != Double.NEGATIVE_INFINITY))
/*      */         {
/*      */           break;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  911 */       while (-1 != pos) {
/*      */         
/*  913 */         pos = dtm.getPreviousSibling(pos);
/*      */         
/*  915 */         if (-1 != pos && (null == countMatchPattern || countMatchPattern.getMatchScore(xctxt, pos) != Double.NEGATIVE_INFINITY)) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  925 */     return pos;
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
/*      */   public int getTargetNode(XPathContext xctxt, int sourceNode) throws TransformerException {
/*  942 */     int target = -1;
/*  943 */     XPath countMatchPattern = getCountMatchPattern(xctxt, sourceNode);
/*      */     
/*  945 */     if (3 == this.m_level) {
/*      */       
/*  947 */       target = findPrecedingOrAncestorOrSelf(xctxt, this.m_fromMatchPattern, countMatchPattern, sourceNode, this);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  953 */       target = findAncestor(xctxt, this.m_fromMatchPattern, countMatchPattern, sourceNode, this);
/*      */     } 
/*      */ 
/*      */     
/*  957 */     return target;
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
/*      */   NodeVector getMatchingAncestors(XPathContext xctxt, int node, boolean stopAtFirstFound) throws TransformerException {
/*  980 */     NodeSetDTM ancestors = new NodeSetDTM(xctxt.getDTMManager());
/*  981 */     XPath countMatchPattern = getCountMatchPattern(xctxt, node);
/*  982 */     DTM dtm = xctxt.getDTM(node);
/*      */     
/*  984 */     while (-1 != node) {
/*      */       
/*  986 */       if (null != this.m_fromMatchPattern && this.m_fromMatchPattern.getMatchScore(xctxt, node) != Double.NEGATIVE_INFINITY)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  997 */         if (!stopAtFirstFound) {
/*      */           break;
/*      */         }
/*      */       }
/* 1001 */       if (null == countMatchPattern) {
/* 1002 */         System.out.println("Programmers error! countMatchPattern should never be null!");
/*      */       }
/*      */       
/* 1005 */       if (countMatchPattern.getMatchScore(xctxt, node) != Double.NEGATIVE_INFINITY) {
/*      */ 
/*      */         
/* 1008 */         ancestors.addElement(node);
/*      */         
/* 1010 */         if (stopAtFirstFound) {
/*      */           break;
/*      */         }
/*      */       } 
/* 1014 */       node = dtm.getParent(node);
/*      */     } 
/*      */     
/* 1017 */     return (NodeVector)ancestors;
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
/*      */   Locale getLocale(TransformerImpl transformer, int contextNode) throws TransformerException {
/* 1035 */     Locale locale = null;
/*      */     
/* 1037 */     if (null != this.m_lang_avt) {
/*      */       
/* 1039 */       XPathContext xctxt = transformer.getXPathContext();
/* 1040 */       String langValue = this.m_lang_avt.evaluate(xctxt, contextNode, this);
/*      */       
/* 1042 */       if (null != langValue) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1048 */         locale = new Locale(langValue.toUpperCase(), "");
/*      */ 
/*      */         
/* 1051 */         if (null == locale)
/*      */         {
/* 1053 */           transformer.getMsgMgr().warn((SourceLocator)this, null, xctxt.getDTM(contextNode).getNode(contextNode), "WG_LOCALE_NOT_FOUND", new Object[] { langValue });
/*      */ 
/*      */ 
/*      */           
/* 1057 */           locale = Locale.getDefault();
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1063 */       locale = Locale.getDefault();
/*      */     } 
/*      */     
/* 1066 */     return locale;
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
/*      */   private DecimalFormat getNumberFormatter(TransformerImpl transformer, int contextNode) throws TransformerException {
/* 1085 */     Locale locale = (Locale)getLocale(transformer, contextNode).clone();
/*      */ 
/*      */     
/* 1088 */     DecimalFormat formatter = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1095 */     String digitGroupSepValue = (null != this.m_groupingSeparator_avt) ? this.m_groupingSeparator_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1103 */     if (digitGroupSepValue != null && !this.m_groupingSeparator_avt.isSimple() && digitGroupSepValue.length() != 1)
/*      */     {
/*      */       
/* 1106 */       transformer.getMsgMgr().warn((SourceLocator)this, "WG_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "name", this.m_groupingSeparator_avt.getName() });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1112 */     String nDigitsPerGroupValue = (null != this.m_groupingSize_avt) ? this.m_groupingSize_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1118 */     if (null != digitGroupSepValue && null != nDigitsPerGroupValue && digitGroupSepValue.length() > 0) {
/*      */ 
/*      */       
/*      */       try { 
/*      */ 
/*      */         
/* 1124 */         formatter = (DecimalFormat)NumberFormat.getNumberInstance(locale);
/* 1125 */         formatter.setGroupingSize(Integer.valueOf(nDigitsPerGroupValue).intValue());
/*      */ 
/*      */         
/* 1128 */         DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
/* 1129 */         symbols.setGroupingSeparator(digitGroupSepValue.charAt(0));
/* 1130 */         formatter.setDecimalFormatSymbols(symbols);
/* 1131 */         formatter.setGroupingUsed(true); } catch (NumberFormatException ex)
/*      */       
/*      */       { 
/*      */         
/* 1135 */         formatter.setGroupingUsed(false); }
/*      */     
/*      */     }
/*      */     
/* 1139 */     return formatter;
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
/*      */   String formatNumberList(TransformerImpl transformer, long[] list, int contextNode) throws TransformerException {
/*      */     String numStr;
/* 1162 */     FastStringBuffer formattedNumber = StringBufferPool.get();
/*      */ 
/*      */     
/*      */     try {
/* 1166 */       int nNumbers = list.length, numberWidth = 1;
/* 1167 */       char numberType = '1';
/* 1168 */       String lastSepString = null, formatTokenString = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1177 */       String lastSep = ".";
/* 1178 */       boolean isFirstToken = true;
/* 1179 */       String formatValue = (null != this.m_format_avt) ? this.m_format_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1184 */       if (null == formatValue) {
/* 1185 */         formatValue = "1";
/*      */       }
/* 1187 */       NumberFormatStringTokenizer formatTokenizer = new NumberFormatStringTokenizer(this, formatValue);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1192 */       for (int i = 0; i < nNumbers; i++) {
/*      */ 
/*      */ 
/*      */         
/* 1196 */         if (formatTokenizer.hasMoreTokens()) {
/*      */           
/* 1198 */           String formatToken = formatTokenizer.nextToken();
/*      */ 
/*      */ 
/*      */           
/* 1202 */           if (Character.isLetterOrDigit(formatToken.charAt(formatToken.length() - 1))) {
/*      */ 
/*      */             
/* 1205 */             numberWidth = formatToken.length();
/* 1206 */             numberType = formatToken.charAt(numberWidth - 1);
/*      */ 
/*      */ 
/*      */           
/*      */           }
/* 1211 */           else if (formatTokenizer.isLetterOrDigitAhead()) {
/*      */             
/* 1213 */             formatTokenString = formatToken;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1218 */             while (formatTokenizer.nextIsSep()) {
/*      */               
/* 1220 */               formatToken = formatTokenizer.nextToken();
/* 1221 */               formatTokenString = formatTokenString + formatToken;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1228 */             if (!isFirstToken) {
/* 1229 */               lastSep = formatTokenString;
/*      */             }
/*      */             
/* 1232 */             formatToken = formatTokenizer.nextToken();
/* 1233 */             numberWidth = formatToken.length();
/* 1234 */             numberType = formatToken.charAt(numberWidth - 1);
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/* 1241 */             lastSepString = formatToken;
/*      */ 
/*      */             
/* 1244 */             while (formatTokenizer.hasMoreTokens()) {
/*      */               
/* 1246 */               formatToken = formatTokenizer.nextToken();
/* 1247 */               lastSepString = lastSepString + formatToken;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1256 */         if (null != formatTokenString && isFirstToken) {
/*      */           
/* 1258 */           formattedNumber.append(formatTokenString);
/*      */         }
/* 1260 */         else if (null != lastSep && !isFirstToken) {
/* 1261 */           formattedNumber.append(lastSep);
/*      */         } 
/* 1263 */         getFormattedNumber(transformer, contextNode, numberType, numberWidth, list[i], formattedNumber);
/*      */ 
/*      */         
/* 1266 */         isFirstToken = false;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1271 */       while (formatTokenizer.isLetterOrDigitAhead())
/*      */       {
/* 1273 */         formatTokenizer.nextToken();
/*      */       }
/*      */       
/* 1276 */       if (lastSepString != null) {
/* 1277 */         formattedNumber.append(lastSepString);
/*      */       }
/* 1279 */       while (formatTokenizer.hasMoreTokens()) {
/*      */         
/* 1281 */         String str = formatTokenizer.nextToken();
/*      */         
/* 1283 */         formattedNumber.append(str);
/*      */       } 
/*      */       
/* 1286 */       numStr = formattedNumber.toString();
/*      */     }
/*      */     finally {
/*      */       
/* 1290 */       StringBufferPool.free(formattedNumber);
/*      */     } 
/*      */     
/* 1293 */     return numStr;
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
/*      */   private void getFormattedNumber(TransformerImpl transformer, int contextNode, char numberType, int numberWidth, long listElement, FastStringBuffer formattedNumber) throws TransformerException {
/*      */     FastStringBuffer stringBuf;
/*      */     XResourceBundle thisBundle;
/* 1321 */     String letterVal = (this.m_lettervalue_avt != null) ? this.m_lettervalue_avt.evaluate(transformer.getXPathContext(), contextNode, this) : null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1326 */     switch (numberType) {
/*      */       
/*      */       case 'A':
/* 1329 */         if (m_alphaCountTable == null) {
/*      */ 
/*      */ 
/*      */           
/* 1333 */           XResourceBundle xResourceBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", getLocale(transformer, contextNode));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1339 */           char[] alphabet = (char[])xResourceBundle.getObject("alphabet");
/* 1340 */           m_alphaCountTable = alphabet;
/*      */         } 
/*      */         
/* 1343 */         int2alphaCount(listElement, m_alphaCountTable, formattedNumber);
/*      */         return;
/*      */       case 'a':
/* 1346 */         if (m_alphaCountTable == null) {
/*      */ 
/*      */ 
/*      */           
/* 1350 */           XResourceBundle xResourceBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", getLocale(transformer, contextNode));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1356 */           char[] alphabet = (char[])xResourceBundle.getObject("alphabet");
/* 1357 */           m_alphaCountTable = alphabet;
/*      */         } 
/*      */         
/* 1360 */         stringBuf = StringBufferPool.get();
/*      */ 
/*      */         
/*      */         try {
/* 1364 */           int2alphaCount(listElement, m_alphaCountTable, stringBuf);
/* 1365 */           formattedNumber.append(stringBuf.toString().toLowerCase(getLocale(transformer, contextNode)));
/*      */         
/*      */         }
/*      */         finally {
/*      */ 
/*      */           
/* 1371 */           StringBufferPool.free(stringBuf);
/*      */         } 
/*      */         return;
/*      */       case 'I':
/* 1375 */         formattedNumber.append(long2roman(listElement, true));
/*      */         return;
/*      */       case 'i':
/* 1378 */         formattedNumber.append(long2roman(listElement, true).toLowerCase(getLocale(transformer, contextNode)));
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 'あ':
/* 1386 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ja", "JP", "HA"));
/*      */ 
/*      */         
/* 1389 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1391 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1393 */           formattedNumber.append(int2singlealphaCount(listElement, (char[])thisBundle.getObject("alphabet")));
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 'い':
/* 1404 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ja", "JP", "HI"));
/*      */ 
/*      */         
/* 1407 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1409 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1411 */           formattedNumber.append(int2singlealphaCount(listElement, (char[])thisBundle.getObject("alphabet")));
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 'ア':
/* 1422 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ja", "JP", "A"));
/*      */ 
/*      */         
/* 1425 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1427 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1429 */           formattedNumber.append(int2singlealphaCount(listElement, (char[])thisBundle.getObject("alphabet")));
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 'イ':
/* 1440 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ja", "JP", "I"));
/*      */ 
/*      */         
/* 1443 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1445 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1447 */           formattedNumber.append(int2singlealphaCount(listElement, (char[])thisBundle.getObject("alphabet")));
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case '一':
/* 1458 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("zh", "CN"));
/*      */ 
/*      */         
/* 1461 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */ 
/*      */           
/* 1464 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/*      */           
/* 1467 */           int2alphaCount(listElement, (char[])thisBundle.getObject("alphabet"), formattedNumber);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case '壹':
/* 1477 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("zh", "TW"));
/*      */ 
/*      */         
/* 1480 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1482 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1484 */           int2alphaCount(listElement, (char[])thisBundle.getObject("alphabet"), formattedNumber);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case '๑':
/* 1494 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("th", ""));
/*      */ 
/*      */         
/* 1497 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1499 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1501 */           int2alphaCount(listElement, (char[])thisBundle.getObject("alphabet"), formattedNumber);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 'א':
/* 1511 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("he", ""));
/*      */ 
/*      */         
/* 1514 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1516 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1518 */           int2alphaCount(listElement, (char[])thisBundle.getObject("alphabet"), formattedNumber);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 'ა':
/* 1528 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("ka", ""));
/*      */ 
/*      */         
/* 1531 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1533 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1535 */           int2alphaCount(listElement, (char[])thisBundle.getObject("alphabet"), formattedNumber);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 'α':
/* 1545 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("el", ""));
/*      */ 
/*      */         
/* 1548 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1550 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1552 */           int2alphaCount(listElement, (char[])thisBundle.getObject("alphabet"), formattedNumber);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 'а':
/* 1562 */         thisBundle = XResourceBundle.loadResourceBundle("org.apache.xml.utils.res.XResources", new Locale("cy", ""));
/*      */ 
/*      */         
/* 1565 */         if (letterVal != null && letterVal.equals("traditional")) {
/*      */           
/* 1567 */           formattedNumber.append(tradAlphaCount(listElement, thisBundle));
/*      */         } else {
/* 1569 */           int2alphaCount(listElement, (char[])thisBundle.getObject("alphabet"), formattedNumber);
/*      */         } 
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1576 */     DecimalFormat formatter = getNumberFormatter(transformer, contextNode);
/* 1577 */     String padString = (formatter == null) ? String.valueOf(0) : formatter.format(0L);
/* 1578 */     String numString = (formatter == null) ? String.valueOf(listElement) : formatter.format(listElement);
/* 1579 */     int nPadding = numberWidth - numString.length();
/*      */     
/* 1581 */     for (int k = 0; k < nPadding; k++)
/*      */     {
/* 1583 */       formattedNumber.append(padString);
/*      */     }
/*      */     
/* 1586 */     formattedNumber.append(numString);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getZeroString() {
/* 1596 */     return "0";
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
/*      */   protected String int2singlealphaCount(long val, char[] table) {
/* 1614 */     int radix = table.length;
/*      */ 
/*      */     
/* 1617 */     if (val > radix)
/*      */     {
/* 1619 */       return getZeroString();
/*      */     }
/*      */     
/* 1622 */     return (new Character(table[(int)val - 1])).toString();
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
/*      */   protected void int2alphaCount(long val, char[] aTable, FastStringBuffer stringBuf) {
/* 1643 */     int radix = aTable.length;
/* 1644 */     char[] table = new char[aTable.length];
/*      */ 
/*      */     
/*      */     int i;
/*      */     
/* 1649 */     for (i = 0; i < aTable.length - 1; i++)
/*      */     {
/* 1651 */       table[i + 1] = aTable[i];
/*      */     }
/*      */     
/* 1654 */     table[0] = aTable[i];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1659 */     char[] buf = new char[100];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1667 */     int charPos = buf.length - 1;
/*      */ 
/*      */     
/* 1670 */     int lookupIndex = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1696 */     long correction = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 1705 */       correction = (lookupIndex == 0 || (correction != 0L && lookupIndex == radix - 1)) ? (radix - 1) : 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1710 */       lookupIndex = (int)(val + correction) % radix;
/*      */ 
/*      */       
/* 1713 */       val /= radix;
/*      */ 
/*      */       
/* 1716 */       if (lookupIndex == 0 && val == 0L) {
/*      */         break;
/*      */       }
/*      */       
/* 1720 */       buf[charPos--] = table[lookupIndex];
/*      */     }
/* 1722 */     while (val > 0L);
/*      */     
/* 1724 */     stringBuf.append(buf, charPos + 1, buf.length - charPos - 1);
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
/*      */   protected String tradAlphaCount(long val, XResourceBundle thisBundle) {
/* 1745 */     if (val > Long.MAX_VALUE) {
/*      */       
/* 1747 */       error("ER_NUMBER_TOO_BIG");
/* 1748 */       return "#error";
/*      */     } 
/* 1750 */     char[] table = null;
/*      */ 
/*      */     
/* 1753 */     int lookupIndex = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1758 */     char[] buf = new char[100];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1766 */     int charPos = 0;
/*      */ 
/*      */     
/* 1769 */     int[] groups = (int[])thisBundle.getObject("numberGroups");
/*      */ 
/*      */     
/* 1772 */     String[] tables = (String[])thisBundle.getObject("tables");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1777 */     String numbering = thisBundle.getString("numbering");
/*      */ 
/*      */     
/* 1780 */     if (numbering.equals("multiplicative-additive")) {
/*      */       
/* 1782 */       String mult_order = thisBundle.getString("multiplierOrder");
/* 1783 */       long[] multiplier = (long[])thisBundle.getObject("multiplier");
/*      */       
/* 1785 */       char[] zeroChar = (char[])thisBundle.getObject("zero");
/* 1786 */       int i = 0;
/*      */ 
/*      */       
/* 1789 */       while (i < multiplier.length && val < multiplier[i])
/*      */       {
/* 1791 */         i++;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1796 */       while (i < multiplier.length) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1802 */         if (val < multiplier[i]) {
/*      */           
/* 1804 */           if (zeroChar.length == 0)
/*      */           {
/* 1806 */             i++;
/*      */           }
/*      */           else
/*      */           {
/* 1810 */             if (buf[charPos - 1] != zeroChar[0]) {
/* 1811 */               buf[charPos++] = zeroChar[0];
/*      */             }
/* 1813 */             i++;
/*      */           }
/*      */         
/* 1816 */         } else if (val >= multiplier[i]) {
/*      */           
/* 1818 */           long mult = val / multiplier[i];
/*      */           
/* 1820 */           val %= multiplier[i];
/*      */           
/* 1822 */           int k = 0;
/*      */           
/* 1824 */           while (k < groups.length) {
/*      */             
/* 1826 */             lookupIndex = 1;
/*      */             
/* 1828 */             if (mult / groups[k] <= 0L) {
/* 1829 */               k++;
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/* 1834 */             char[] THEletters = (char[])thisBundle.getObject(tables[k]);
/*      */             
/* 1836 */             table = new char[THEletters.length + 1];
/*      */             
/*      */             int j;
/*      */             
/* 1840 */             for (j = 0; j < THEletters.length; j++)
/*      */             {
/* 1842 */               table[j + 1] = THEletters[j];
/*      */             }
/*      */             
/* 1845 */             table[0] = THEletters[j - 1];
/*      */ 
/*      */             
/* 1848 */             lookupIndex = (int)mult / groups[k];
/*      */ 
/*      */             
/* 1851 */             if (lookupIndex == 0 && mult == 0L) {
/*      */               break;
/*      */             }
/* 1854 */             char multiplierChar = ((char[])thisBundle.getObject("multiplierChar"))[i];
/*      */ 
/*      */ 
/*      */             
/* 1858 */             if (lookupIndex < table.length) {
/*      */               
/* 1860 */               if (mult_order.equals("precedes")) {
/*      */                 
/* 1862 */                 buf[charPos++] = multiplierChar;
/* 1863 */                 buf[charPos++] = table[lookupIndex];
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */               
/* 1869 */               if (lookupIndex != 1 || i != multiplier.length - 1)
/*      */               {
/* 1871 */                 buf[charPos++] = table[lookupIndex];
/*      */               }
/* 1873 */               buf[charPos++] = multiplierChar;
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 1879 */             return "#error";
/*      */           } 
/*      */ 
/*      */           
/* 1883 */           i++;
/*      */         } 
/*      */         
/* 1886 */         if (i >= multiplier.length)
/*      */           break; 
/*      */       } 
/*      */     } 
/* 1890 */     int count = 0;
/*      */ 
/*      */ 
/*      */     
/* 1894 */     while (count < groups.length) {
/*      */       
/* 1896 */       if (val / groups[count] <= 0L) {
/* 1897 */         count++;
/*      */         continue;
/*      */       } 
/* 1900 */       char[] theletters = (char[])thisBundle.getObject(tables[count]);
/*      */       
/* 1902 */       table = new char[theletters.length + 1];
/*      */ 
/*      */       
/*      */       int j;
/*      */       
/* 1907 */       for (j = 0; j < theletters.length; j++)
/*      */       {
/* 1909 */         table[j + 1] = theletters[j];
/*      */       }
/*      */       
/* 1912 */       table[0] = theletters[j - 1];
/*      */ 
/*      */       
/* 1915 */       lookupIndex = (int)val / groups[count];
/*      */ 
/*      */       
/* 1918 */       val %= groups[count];
/*      */ 
/*      */       
/* 1921 */       if (lookupIndex == 0 && val == 0L) {
/*      */         break;
/*      */       }
/* 1924 */       if (lookupIndex < table.length) {
/*      */ 
/*      */ 
/*      */         
/* 1928 */         buf[charPos++] = table[lookupIndex];
/*      */       } else {
/*      */         
/* 1931 */         return "#error";
/*      */       } 
/* 1933 */       count++;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1938 */     return new String(buf, 0, charPos);
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
/*      */   protected String long2roman(long val, boolean prefixesAreOK) {
/* 1953 */     if (val <= 0L)
/*      */     {
/* 1955 */       return getZeroString();
/*      */     }
/*      */     
/* 1958 */     String roman = "";
/* 1959 */     int place = 0;
/*      */     
/* 1961 */     if (val <= 3999L)
/*      */     
/*      */     { while (true)
/*      */       { while (true) {
/* 1965 */           if (val < (m_romanConvertTable[place]).m_postValue) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1971 */             if (prefixesAreOK)
/*      */             {
/* 1973 */               if (val >= (m_romanConvertTable[place]).m_preValue) {
/*      */                 
/* 1975 */                 roman = roman + (m_romanConvertTable[place]).m_preLetter;
/* 1976 */                 val -= (m_romanConvertTable[place]).m_preValue;
/*      */               } 
/*      */             }
/*      */             
/* 1980 */             place++;
/*      */             
/* 1982 */             if (val <= 0L)
/*      */               break; 
/*      */             continue;
/*      */           } 
/*      */           roman = roman + (m_romanConvertTable[place]).m_postLetter;
/*      */           val -= (m_romanConvertTable[place]).m_postValue;
/*      */         } 
/* 1989 */         return roman; }  } else { roman = "#error"; return roman; }
/*      */     
/*      */     roman = roman + (m_romanConvertTable[place]).m_postLetter;
/*      */     val -= (m_romanConvertTable[place]).m_postValue;
/*      */     continue;
/*      */   }
/*      */ 
/*      */   
/*      */   public void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 1998 */     if (callAttrs) {
/*      */       
/* 2000 */       if (null != this.m_countMatchPattern)
/* 2001 */         this.m_countMatchPattern.getExpression().callVisitors((ExpressionOwner)this.m_countMatchPattern, visitor); 
/* 2002 */       if (null != this.m_fromMatchPattern)
/* 2003 */         this.m_fromMatchPattern.getExpression().callVisitors((ExpressionOwner)this.m_fromMatchPattern, visitor); 
/* 2004 */       if (null != this.m_valueExpr) {
/* 2005 */         this.m_valueExpr.getExpression().callVisitors((ExpressionOwner)this.m_valueExpr, visitor);
/*      */       }
/* 2007 */       if (null != this.m_format_avt)
/* 2008 */         this.m_format_avt.callVisitors(visitor); 
/* 2009 */       if (null != this.m_groupingSeparator_avt)
/* 2010 */         this.m_groupingSeparator_avt.callVisitors(visitor); 
/* 2011 */       if (null != this.m_groupingSize_avt)
/* 2012 */         this.m_groupingSize_avt.callVisitors(visitor); 
/* 2013 */       if (null != this.m_lang_avt)
/* 2014 */         this.m_lang_avt.callVisitors(visitor); 
/* 2015 */       if (null != this.m_lettervalue_avt) {
/* 2016 */         this.m_lettervalue_avt.callVisitors(visitor);
/*      */       }
/*      */     } 
/* 2019 */     super.callChildVisitors(visitor, callAttrs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class NumberFormatStringTokenizer
/*      */   {
/*      */     private int currentPosition;
/*      */ 
/*      */ 
/*      */     
/*      */     private int maxPosition;
/*      */ 
/*      */ 
/*      */     
/*      */     private String str;
/*      */ 
/*      */ 
/*      */     
/*      */     private final ElemNumber this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NumberFormatStringTokenizer(ElemNumber this$0, String str) {
/* 2045 */       this.this$0 = this$0;
/* 2046 */       this.str = str;
/* 2047 */       this.maxPosition = str.length();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void reset() {
/* 2055 */       this.currentPosition = 0;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String nextToken() {
/* 2068 */       if (this.currentPosition >= this.maxPosition)
/*      */       {
/* 2070 */         throw new NoSuchElementException();
/*      */       }
/*      */       
/* 2073 */       int start = this.currentPosition;
/*      */ 
/*      */       
/* 2076 */       while (this.currentPosition < this.maxPosition && Character.isLetterOrDigit(this.str.charAt(this.currentPosition)))
/*      */       {
/* 2078 */         this.currentPosition++;
/*      */       }
/*      */       
/* 2081 */       if (start == this.currentPosition && !Character.isLetterOrDigit(this.str.charAt(this.currentPosition)))
/*      */       {
/*      */         
/* 2084 */         this.currentPosition++;
/*      */       }
/*      */       
/* 2087 */       return this.str.substring(start, this.currentPosition);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isLetterOrDigitAhead() {
/* 2098 */       int pos = this.currentPosition;
/*      */       
/* 2100 */       while (pos < this.maxPosition) {
/*      */         
/* 2102 */         if (Character.isLetterOrDigit(this.str.charAt(pos))) {
/* 2103 */           return true;
/*      */         }
/* 2105 */         pos++;
/*      */       } 
/*      */       
/* 2108 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean nextIsSep() {
/* 2119 */       if (Character.isLetterOrDigit(this.str.charAt(this.currentPosition))) {
/* 2120 */         return false;
/*      */       }
/* 2122 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasMoreTokens() {
/* 2134 */       return !(this.currentPosition >= this.maxPosition);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int countTokens() {
/* 2149 */       int count = 0;
/* 2150 */       int currpos = this.currentPosition;
/*      */       
/* 2152 */       while (currpos < this.maxPosition) {
/*      */         
/* 2154 */         int start = currpos;
/*      */ 
/*      */         
/* 2157 */         while (currpos < this.maxPosition && Character.isLetterOrDigit(this.str.charAt(currpos)))
/*      */         {
/* 2159 */           currpos++;
/*      */         }
/*      */         
/* 2162 */         if (start == currpos && !Character.isLetterOrDigit(this.str.charAt(currpos)))
/*      */         {
/*      */           
/* 2165 */           currpos++;
/*      */         }
/*      */         
/* 2168 */         count++;
/*      */       } 
/*      */       
/* 2171 */       return count;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemNumber.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */