/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XBoolean
/*     */   extends XObject
/*     */ {
/*  33 */   public static XBoolean S_TRUE = new XBooleanStatic(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public static XBoolean S_FALSE = new XBooleanStatic(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean m_val;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XBoolean(boolean b) {
/*  55 */     this.m_val = b;
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
/*     */   public XBoolean(Boolean b) {
/*  68 */     this.m_val = b.booleanValue();
/*  69 */     this.m_obj = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/*  80 */     return 1;
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
/*  91 */     return "#BOOLEAN";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double num() {
/* 101 */     return this.m_val ? 1.0D : 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean bool() {
/* 111 */     return this.m_val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String str() {
/* 121 */     return this.m_val ? "true" : "false";
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
/* 132 */     if (null == this.m_obj)
/* 133 */       this.m_obj = new Boolean(this.m_val); 
/* 134 */     return this.m_obj;
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
/* 152 */     if (obj2.getType() == 4) {
/* 153 */       return obj2.equals(this);
/*     */     }
/*     */ 
/*     */     
/* 157 */     try { return (this.m_val == obj2.bool()); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/* 161 */       throw new WrappedRuntimeException(te); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XBoolean.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */