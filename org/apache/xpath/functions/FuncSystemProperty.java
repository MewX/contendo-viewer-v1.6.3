/*     */ package org.apache.xpath.functions;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.util.Properties;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XNumber;
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
/*     */ public class FuncSystemProperty
/*     */   extends FunctionOneArg
/*     */ {
/*  42 */   static String XSLT_PROPERTIES = "org/apache/xalan/res/XSLTInfo.properties";
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  55 */     String str1, fullName = this.m_arg0.execute(xctxt).str();
/*  56 */     int indexOfNSSep = fullName.indexOf(':');
/*     */     
/*  58 */     String propName = "";
/*     */ 
/*     */ 
/*     */     
/*  62 */     Properties xsltInfo = new Properties();
/*     */     
/*  64 */     loadPropertyFile(XSLT_PROPERTIES, xsltInfo);
/*     */     
/*  66 */     if (indexOfNSSep > 0) {
/*     */       
/*  68 */       String prefix = (indexOfNSSep >= 0) ? fullName.substring(0, indexOfNSSep) : "";
/*     */ 
/*     */ 
/*     */       
/*  72 */       String namespace = xctxt.getNamespaceContext().getNamespaceForPrefix(prefix);
/*  73 */       propName = (indexOfNSSep < 0) ? fullName : fullName.substring(indexOfNSSep + 1);
/*     */ 
/*     */       
/*  76 */       if (namespace.startsWith("http://www.w3.org/XSL/Transform") || namespace.equals("http://www.w3.org/1999/XSL/Transform")) {
/*     */ 
/*     */         
/*  79 */         str1 = xsltInfo.getProperty(propName);
/*     */         
/*  81 */         if (null == str1)
/*     */         {
/*  83 */           warn(xctxt, "WG_PROPERTY_NOT_SUPPORTED", new Object[] { fullName });
/*     */ 
/*     */           
/*  86 */           return (XObject)XString.EMPTYSTRING;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  91 */         warn(xctxt, "WG_DONT_DO_ANYTHING_WITH_NS", new Object[] { namespace, fullName });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  97 */         try { str1 = System.getProperty(propName);
/*     */           
/*  99 */           if (null == str1)
/*     */           {
/*     */ 
/*     */             
/* 103 */             return (XObject)XString.EMPTYSTRING; }  } catch (SecurityException se)
/*     */         
/*     */         { 
/*     */ 
/*     */           
/* 108 */           warn(xctxt, "WG_SECURITY_EXCEPTION", new Object[] { fullName });
/*     */ 
/*     */           
/* 111 */           return (XObject)XString.EMPTYSTRING; }
/*     */       
/*     */       } 
/*     */     } else {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/* 119 */         str1 = System.getProperty(fullName);
/*     */         
/* 121 */         if (null == str1)
/*     */         {
/*     */ 
/*     */           
/* 125 */           return (XObject)XString.EMPTYSTRING; }  } catch (SecurityException se)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 130 */         warn(xctxt, "WG_SECURITY_EXCEPTION", new Object[] { fullName });
/*     */ 
/*     */         
/* 133 */         return (XObject)XString.EMPTYSTRING; }
/*     */     
/*     */     } 
/*     */     
/* 137 */     if (propName.equals("version") && str1.length() > 0) {
/*     */ 
/*     */       
/*     */       try { 
/*     */         
/* 142 */         return (XObject)new XNumber(1.0D); } catch (Exception ex)
/*     */       
/*     */       { 
/*     */         
/* 146 */         return (XObject)new XString(str1); }
/*     */     
/*     */     }
/*     */     
/* 150 */     return (XObject)new XString(str1);
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
/*     */   public void loadPropertyFile(String file, Properties target) {
/*     */     
/* 165 */     try { SecuritySupport ss = SecuritySupport.getInstance();
/*     */       
/* 167 */       InputStream is = ss.getResourceAsStream(ObjectFactory.findClassLoader(), file);
/*     */ 
/*     */ 
/*     */       
/* 171 */       BufferedInputStream bis = new BufferedInputStream(is);
/*     */       
/* 173 */       target.load(bis);
/* 174 */       bis.close(); } catch (Exception ex)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 179 */       throw new WrappedRuntimeException(ex); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncSystemProperty.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */