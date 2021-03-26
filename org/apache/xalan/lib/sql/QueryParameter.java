/*     */ package org.apache.xalan.lib.sql;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueryParameter
/*     */ {
/*     */   private int m_type;
/*     */   private String m_name;
/*     */   private String m_value;
/*     */   private boolean m_output;
/*     */   private String m_typeName;
/*  36 */   private static Hashtable m_Typetable = null;
/*     */ 
/*     */   
/*     */   public QueryParameter() {
/*  40 */     this.m_type = -1;
/*  41 */     this.m_name = null;
/*  42 */     this.m_value = null;
/*  43 */     this.m_output = false;
/*  44 */     this.m_typeName = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueryParameter(String v, String t) {
/*  53 */     this.m_name = null;
/*  54 */     this.m_value = v;
/*  55 */     this.m_output = false;
/*  56 */     setTypeName(t);
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryParameter(String name, String value, String type, boolean out_flag) {
/*  61 */     this.m_name = name;
/*  62 */     this.m_value = value;
/*  63 */     this.m_output = out_flag;
/*  64 */     setTypeName(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/*  71 */     return this.m_value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(String newValue) {
/*  79 */     this.m_value = newValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTypeName(String newType) {
/*  88 */     this.m_type = map_type(newType);
/*  89 */     this.m_typeName = newType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeName() {
/*  97 */     return this.m_typeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 105 */     return this.m_type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 113 */     return this.m_name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String n) {
/* 123 */     this.m_name = n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOutput() {
/* 131 */     return this.m_output;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsOutput(boolean flag) {
/* 141 */     this.m_output = flag;
/*     */   }
/*     */   
/*     */   private static int map_type(String typename) {
/*     */     int i;
/* 146 */     if (m_Typetable == null) {
/*     */ 
/*     */       
/* 149 */       m_Typetable = new Hashtable();
/* 150 */       m_Typetable.put("BIGINT", new Integer(-5));
/* 151 */       m_Typetable.put("BINARY", new Integer(-2));
/* 152 */       m_Typetable.put("BIT", new Integer(-7));
/* 153 */       m_Typetable.put("CHAR", new Integer(1));
/* 154 */       m_Typetable.put("DATE", new Integer(91));
/* 155 */       m_Typetable.put("DECIMAL", new Integer(3));
/* 156 */       m_Typetable.put("DOUBLE", new Integer(8));
/* 157 */       m_Typetable.put("FLOAT", new Integer(6));
/* 158 */       m_Typetable.put("INTEGER", new Integer(4));
/* 159 */       m_Typetable.put("LONGVARBINARY", new Integer(-4));
/* 160 */       m_Typetable.put("LONGVARCHAR", new Integer(-1));
/* 161 */       m_Typetable.put("NULL", new Integer(0));
/* 162 */       m_Typetable.put("NUMERIC", new Integer(2));
/* 163 */       m_Typetable.put("OTHER", new Integer(1111));
/* 164 */       m_Typetable.put("REAL", new Integer(7));
/* 165 */       m_Typetable.put("SMALLINT", new Integer(5));
/* 166 */       m_Typetable.put("TIME", new Integer(92));
/* 167 */       m_Typetable.put("TIMESTAMP", new Integer(93));
/* 168 */       m_Typetable.put("TINYINT", new Integer(-6));
/* 169 */       m_Typetable.put("VARBINARY", new Integer(-3));
/* 170 */       m_Typetable.put("VARCHAR", new Integer(12));
/*     */ 
/*     */       
/* 173 */       m_Typetable.put("STRING", new Integer(12));
/* 174 */       m_Typetable.put("BIGDECIMAL", new Integer(2));
/* 175 */       m_Typetable.put("BOOLEAN", new Integer(-7));
/* 176 */       m_Typetable.put("BYTES", new Integer(-4));
/* 177 */       m_Typetable.put("LONG", new Integer(-5));
/* 178 */       m_Typetable.put("SHORT", new Integer(5));
/*     */     } 
/*     */     
/* 181 */     Integer type = (Integer)m_Typetable.get(typename.toUpperCase());
/*     */     
/* 183 */     if (type == null) {
/* 184 */       i = 1111;
/*     */     } else {
/* 186 */       i = type.intValue();
/*     */     } 
/* 188 */     return i;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/QueryParameter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */