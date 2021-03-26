/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathVisitor;
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
/*     */ public class XNumber
/*     */   extends XObject
/*     */ {
/*     */   double m_val;
/*     */   
/*     */   public XNumber(double d) {
/*  46 */     this.m_val = d;
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
/*     */   public XNumber(Number num) {
/*  59 */     this.m_val = num.doubleValue();
/*  60 */     this.m_obj = num;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/*  70 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeString() {
/*  81 */     return "#NUMBER";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double num() {
/*  91 */     return this.m_val;
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
/*     */   public double num(XPathContext xctxt) throws TransformerException {
/* 105 */     return this.m_val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean bool() {
/* 115 */     return !(Double.isNaN(this.m_val) || this.m_val == 0.0D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String str() {
/*     */     String str1;
/* 277 */     if (Double.isNaN(this.m_val))
/*     */     {
/* 279 */       return "NaN";
/*     */     }
/* 281 */     if (Double.isInfinite(this.m_val)) {
/*     */       
/* 283 */       if (this.m_val > 0.0D) {
/* 284 */         return "Infinity";
/*     */       }
/* 286 */       return "-Infinity";
/*     */     } 
/*     */     
/* 289 */     double num = this.m_val;
/* 290 */     String s = Double.toString(num);
/* 291 */     int len = s.length();
/*     */     
/* 293 */     if (s.charAt(len - 2) == '.' && s.charAt(len - 1) == '0') {
/*     */       
/* 295 */       s = s.substring(0, len - 2);
/*     */       
/* 297 */       if (s.equals("-0")) {
/* 298 */         return "0";
/*     */       }
/* 300 */       return s;
/*     */     } 
/*     */     
/* 303 */     int e = s.indexOf('E');
/*     */     
/* 305 */     if (e < 0) {
/*     */       
/* 307 */       if (s.charAt(len - 1) == '0') {
/* 308 */         return s.substring(0, len - 1);
/*     */       }
/* 310 */       return s;
/*     */     } 
/*     */     
/* 313 */     int exp = Integer.parseInt(s.substring(e + 1));
/*     */ 
/*     */     
/* 316 */     if (s.charAt(0) == '-') {
/*     */       
/* 318 */       str1 = "-";
/* 319 */       s = s.substring(1);
/*     */       
/* 321 */       e--;
/*     */     } else {
/*     */       
/* 324 */       str1 = "";
/*     */     } 
/* 326 */     int nDigits = e - 2;
/*     */     
/* 328 */     if (exp >= nDigits) {
/* 329 */       return str1 + s.substring(0, 1) + s.substring(2, e) + zeros(exp - nDigits);
/*     */     }
/*     */ 
/*     */     
/* 333 */     while (s.charAt(e - 1) == '0') {
/* 334 */       e--;
/*     */     }
/* 336 */     if (exp > 0) {
/* 337 */       return str1 + s.substring(0, 1) + s.substring(2, 2 + exp) + "." + s.substring(2 + exp, e);
/*     */     }
/*     */     
/* 340 */     return str1 + "0." + zeros(-1 - exp) + s.substring(0, 1) + s.substring(2, e);
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
/*     */   private static String zeros(int n) {
/* 355 */     if (n < 1) {
/* 356 */       return "";
/*     */     }
/* 358 */     char[] buf = new char[n];
/*     */     
/* 360 */     for (int i = 0; i < n; i++)
/*     */     {
/* 362 */       buf[i] = '0';
/*     */     }
/*     */     
/* 365 */     return new String(buf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object object() {
/* 376 */     if (null == this.m_obj)
/* 377 */       this.m_obj = new Double(this.m_val); 
/* 378 */     return this.m_obj;
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
/*     */   public boolean equals(XObject obj2) {
/* 396 */     int t = obj2.getType();
/*     */ 
/*     */     
/* 399 */     try { if (t == 4)
/* 400 */         return obj2.equals(this); 
/* 401 */       if (t == 1) {
/* 402 */         return (obj2.bool() == bool());
/*     */       }
/* 404 */       return (this.m_val == obj2.num()); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/* 408 */       throw new WrappedRuntimeException(te); }
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
/*     */   public boolean isStableNumber() {
/* 422 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 430 */     visitor.visitNumberLiteral(owner, this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XNumber.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */