/*     */ package org.apache.xpath;
/*     */ 
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Arg
/*     */ {
/*     */   private QName m_qname;
/*     */   private XObject m_val;
/*     */   private String m_expression;
/*     */   private boolean m_isFromWithParam;
/*     */   private boolean m_isVisible;
/*     */   
/*     */   public final QName getQName() {
/*  47 */     return this.m_qname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setQName(QName name) {
/*  57 */     this.m_qname = name;
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
/*     */   public final XObject getVal() {
/*  74 */     return this.m_val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setVal(XObject val) {
/*  85 */     this.m_val = val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detach() {
/*  94 */     if (null != this.m_val) {
/*     */       
/*  96 */       this.m_val.allowDetachToRelease(true);
/*  97 */       this.m_val.detach();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExpression() {
/* 117 */     return this.m_expression;
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
/*     */   public void setExpression(String expr) {
/* 129 */     this.m_expression = expr;
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
/*     */   public boolean isFromWithParam() {
/* 144 */     return this.m_isFromWithParam;
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
/*     */   public boolean isVisible() {
/* 161 */     return this.m_isVisible;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsVisible(boolean b) {
/* 169 */     this.m_isVisible = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Arg() {
/* 180 */     this.m_qname = new QName("");
/*     */     
/* 182 */     this.m_val = null;
/* 183 */     this.m_expression = null;
/* 184 */     this.m_isVisible = true;
/* 185 */     this.m_isFromWithParam = false;
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
/*     */   public Arg(QName qname, String expression, boolean isFromWithParam) {
/* 198 */     this.m_qname = qname;
/* 199 */     this.m_val = null;
/* 200 */     this.m_expression = expression;
/* 201 */     this.m_isFromWithParam = isFromWithParam;
/* 202 */     this.m_isVisible = !isFromWithParam;
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
/*     */   public Arg(QName qname, XObject val) {
/* 215 */     this.m_qname = qname;
/* 216 */     this.m_val = val;
/* 217 */     this.m_isVisible = true;
/* 218 */     this.m_isFromWithParam = false;
/* 219 */     this.m_expression = null;
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
/*     */   public boolean equals(Object obj) {
/* 232 */     if (obj instanceof QName)
/*     */     {
/* 234 */       return this.m_qname.equals(obj);
/*     */     }
/*     */     
/* 237 */     return super.equals(obj);
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
/*     */   public Arg(QName qname, XObject val, boolean isFromWithParam) {
/* 250 */     this.m_qname = qname;
/* 251 */     this.m_val = val;
/* 252 */     this.m_isFromWithParam = isFromWithParam;
/* 253 */     this.m_isVisible = !isFromWithParam;
/* 254 */     this.m_expression = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/Arg.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */