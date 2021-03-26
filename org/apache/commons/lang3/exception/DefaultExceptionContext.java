/*     */ package org.apache.commons.lang3.exception;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultExceptionContext
/*     */   implements Serializable, ExceptionContext
/*     */ {
/*     */   private static final long serialVersionUID = 20110706L;
/*  47 */   private final List<Pair<String, Object>> contextValues = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultExceptionContext addContextValue(String label, Object value) {
/*  54 */     this.contextValues.add(new ImmutablePair(label, value));
/*  55 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultExceptionContext setContextValue(String label, Object value) {
/*  63 */     for (Iterator<Pair<String, Object>> iter = this.contextValues.iterator(); iter.hasNext(); ) {
/*  64 */       Pair<String, Object> p = iter.next();
/*  65 */       if (StringUtils.equals(label, (CharSequence)p.getKey())) {
/*  66 */         iter.remove();
/*     */       }
/*     */     } 
/*  69 */     addContextValue(label, value);
/*  70 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Object> getContextValues(String label) {
/*  78 */     List<Object> values = new ArrayList();
/*  79 */     for (Pair<String, Object> pair : this.contextValues) {
/*  80 */       if (StringUtils.equals(label, (CharSequence)pair.getKey())) {
/*  81 */         values.add(pair.getValue());
/*     */       }
/*     */     } 
/*  84 */     return values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFirstContextValue(String label) {
/*  92 */     for (Pair<String, Object> pair : this.contextValues) {
/*  93 */       if (StringUtils.equals(label, (CharSequence)pair.getKey())) {
/*  94 */         return pair.getValue();
/*     */       }
/*     */     } 
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> getContextLabels() {
/* 105 */     Set<String> labels = new HashSet<>();
/* 106 */     for (Pair<String, Object> pair : this.contextValues) {
/* 107 */       labels.add((String)pair.getKey());
/*     */     }
/* 109 */     return labels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Pair<String, Object>> getContextEntries() {
/* 117 */     return this.contextValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormattedExceptionMessage(String baseMessage) {
/* 128 */     StringBuilder buffer = new StringBuilder(256);
/* 129 */     if (baseMessage != null) {
/* 130 */       buffer.append(baseMessage);
/*     */     }
/*     */     
/* 133 */     if (!this.contextValues.isEmpty()) {
/* 134 */       if (buffer.length() > 0) {
/* 135 */         buffer.append('\n');
/*     */       }
/* 137 */       buffer.append("Exception Context:\n");
/*     */       
/* 139 */       int i = 0;
/* 140 */       for (Pair<String, Object> pair : this.contextValues) {
/* 141 */         buffer.append("\t[");
/* 142 */         buffer.append(++i);
/* 143 */         buffer.append(':');
/* 144 */         buffer.append((String)pair.getKey());
/* 145 */         buffer.append("=");
/* 146 */         Object value = pair.getValue();
/* 147 */         if (value == null) {
/* 148 */           buffer.append("null");
/*     */         } else {
/*     */           String valueStr;
/*     */           try {
/* 152 */             valueStr = value.toString();
/* 153 */           } catch (Exception e) {
/* 154 */             valueStr = "Exception thrown on toString(): " + ExceptionUtils.getStackTrace(e);
/*     */           } 
/* 156 */           buffer.append(valueStr);
/*     */         } 
/* 158 */         buffer.append("]\n");
/*     */       } 
/* 160 */       buffer.append("---------------------------------");
/*     */     } 
/* 162 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/exception/DefaultExceptionContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */