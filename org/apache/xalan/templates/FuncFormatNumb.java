/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.Locale;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.functions.Function3Args;
/*     */ import org.apache.xpath.functions.WrongNumberArgsException;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.objects.XString;
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
/*     */ public class FuncFormatNumb
/*     */   extends Function3Args
/*     */ {
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  54 */     ElemTemplateElement templElem = (ElemTemplateElement)xctxt.getNamespaceContext();
/*     */     
/*  56 */     StylesheetRoot ss = templElem.getStylesheetRoot();
/*  57 */     DecimalFormat formatter = null;
/*  58 */     DecimalFormatSymbols dfs = null;
/*  59 */     double num = getArg0().execute(xctxt).num();
/*  60 */     String patternStr = getArg1().execute(xctxt).str();
/*     */ 
/*     */     
/*  63 */     if (patternStr.indexOf('¤') > 0) {
/*  64 */       ss.error("ER_CURRENCY_SIGN_ILLEGAL");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     try { Expression arg2Expr = getArg2();
/*     */       
/*  72 */       if (null != arg2Expr) {
/*     */         
/*  74 */         String dfName = arg2Expr.execute(xctxt).str();
/*  75 */         QName qname = new QName(dfName, xctxt.getNamespaceContext());
/*     */         
/*  77 */         dfs = ss.getDecimalFormatComposed(qname);
/*     */         
/*  79 */         if (null == dfs) {
/*     */           
/*  81 */           warn(xctxt, "WG_NO_DECIMALFORMAT_DECLARATION", new Object[] { dfName });
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */           
/*  90 */           formatter = new DecimalFormat();
/*     */           
/*  92 */           formatter.setDecimalFormatSymbols(dfs);
/*  93 */           formatter.applyLocalizedPattern(patternStr);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  98 */       if (null == formatter) {
/*     */ 
/*     */ 
/*     */         
/* 102 */         dfs = ss.getDecimalFormatComposed(new QName(""));
/*     */         
/* 104 */         if (dfs != null) {
/*     */           
/* 106 */           formatter = new DecimalFormat();
/*     */           
/* 108 */           formatter.setDecimalFormatSymbols(dfs);
/* 109 */           formatter.applyLocalizedPattern(patternStr);
/*     */         }
/*     */         else {
/*     */           
/* 113 */           dfs = new DecimalFormatSymbols(Locale.US);
/*     */           
/* 115 */           dfs.setInfinity("Infinity");
/* 116 */           dfs.setNaN("NaN");
/*     */           
/* 118 */           formatter = new DecimalFormat();
/*     */           
/* 120 */           formatter.setDecimalFormatSymbols(dfs);
/*     */           
/* 122 */           if (null != patternStr) {
/* 123 */             formatter.applyLocalizedPattern(patternStr);
/*     */           }
/*     */         } 
/*     */       } 
/* 127 */       return (XObject)new XString(formatter.format(num)); } catch (Exception iae)
/*     */     
/*     */     { 
/*     */       
/* 131 */       templElem.error("ER_MALFORMED_FORMAT_STRING", new Object[] { patternStr });
/*     */ 
/*     */       
/* 134 */       return (XObject)XString.EMPTYSTRING; }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void warn(XPathContext xctxt, String msg, Object[] args) throws TransformerException {
/* 155 */     String formattedMsg = XSLMessages.createWarning(msg, args);
/* 156 */     ErrorListener errHandler = xctxt.getErrorListener();
/*     */     
/* 158 */     errHandler.warning(new TransformerException(formattedMsg, xctxt.getSAXLocator()));
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
/*     */   
/*     */   public void checkNumberArgs(int argNum) throws WrongNumberArgsException {
/* 172 */     if (argNum > 3 || argNum < 2) {
/* 173 */       reportWrongNumberArgs();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/* 183 */     throw new WrongNumberArgsException(XSLMessages.createMessage("ER_TWO_OR_THREE", null));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/FuncFormatNumb.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */