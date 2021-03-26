/*     */ package org.apache.xalan.lib.sql;
/*     */ 
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Vector;
/*     */ import org.apache.xalan.extensions.ExpressionContext;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SQLQueryParser
/*     */ {
/*     */   private boolean m_InlineVariables = false;
/*     */   private boolean m_IsCallable = false;
/*  54 */   private String m_OrigQuery = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private StringBuffer m_ParsedQuery = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private Vector m_Parameters = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_hasOutput = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_HasParameters;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int NO_OVERRIDE = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int NO_INLINE_PARSER = 1;
/*     */ 
/*     */   
/*     */   public static final int INLINE_PARSER = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   public SQLQueryParser() {
/*  88 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SQLQueryParser(String query) {
/*  96 */     this.m_OrigQuery = query;
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
/*     */   private void init() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SQLQueryParser parse(XConnection xconn, String query, int override) {
/* 123 */     SQLQueryParser parser = new SQLQueryParser(query);
/*     */ 
/*     */ 
/*     */     
/* 127 */     parser.parse(xconn, override);
/*     */     
/* 129 */     return parser;
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
/*     */   private void parse(XConnection xconn, int override) {
/* 152 */     this.m_InlineVariables = "true".equals(xconn.getFeature("inline-variables"));
/* 153 */     if (override == 1) { this.m_InlineVariables = false; }
/* 154 */     else if (override == 2) { this.m_InlineVariables = true; }
/*     */     
/* 156 */     if (this.m_InlineVariables) inlineParser();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasParameters() {
/* 167 */     return this.m_HasParameters;
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
/*     */   public boolean isCallable() {
/* 182 */     return this.m_IsCallable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getParameters() {
/* 191 */     return this.m_Parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameters(Vector p) {
/* 201 */     this.m_HasParameters = true;
/* 202 */     this.m_Parameters = p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSQLQuery() {
/* 212 */     if (this.m_InlineVariables) return this.m_ParsedQuery.toString(); 
/* 213 */     return this.m_OrigQuery;
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
/*     */   public void populateStatement(PreparedStatement stmt, ExpressionContext ctx) {
/* 230 */     for (int indx = 0; indx < this.m_Parameters.size(); indx++) {
/*     */       
/* 232 */       QueryParameter parm = this.m_Parameters.elementAt(indx);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 237 */       try { if (this.m_InlineVariables)
/*     */         
/* 239 */         { XObject value = ctx.getVariableOrParam(new QName(parm.getName()));
/*     */           
/* 241 */           if (value != null)
/*     */           {
/* 243 */             stmt.setObject(indx + 1, value.object(), parm.getType(), 4);
/*     */ 
/*     */           
/*     */           }
/*     */           else
/*     */           {
/*     */             
/* 250 */             stmt.setNull(indx + 1, parm.getType());
/*     */           }
/*     */            }
/*     */         else
/*     */         
/* 255 */         { String value = parm.getValue();
/*     */           
/* 257 */           if (value != null)
/*     */           
/* 259 */           { stmt.setObject(indx + 1, value, parm.getType(), 4);
/*     */             
/*     */              }
/*     */           
/*     */           else
/*     */           
/*     */           { 
/* 266 */             stmt.setNull(indx + 1, parm.getType()); }  }  } catch (Exception exception) {}
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
/*     */   public void registerOutputParameters(CallableStatement cstmt) throws SQLException {
/* 281 */     if (this.m_IsCallable && this.m_hasOutput)
/*     */     {
/* 283 */       for (int indx = 0; indx < this.m_Parameters.size(); indx++) {
/*     */         
/* 285 */         QueryParameter parm = this.m_Parameters.elementAt(indx);
/* 286 */         if (parm.isOutput())
/*     */         {
/*     */           
/* 289 */           cstmt.registerOutParameter(indx + 1, parm.getType());
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void inlineParser() {
/* 300 */     QueryParameter curParm = null;
/* 301 */     int state = 0;
/* 302 */     StringBuffer tok = new StringBuffer();
/* 303 */     boolean firstword = true;
/*     */     
/* 305 */     if (this.m_Parameters == null) this.m_Parameters = new Vector();
/*     */     
/* 307 */     if (this.m_ParsedQuery == null) this.m_ParsedQuery = new StringBuffer();
/*     */     
/* 309 */     for (int idx = 0; idx < this.m_OrigQuery.length(); idx++) {
/*     */       
/* 311 */       char ch = this.m_OrigQuery.charAt(idx);
/* 312 */       switch (state) {
/*     */ 
/*     */         
/*     */         case 0:
/* 316 */           if (ch == '\'') { state = 1; }
/* 317 */           else if (ch == '?') { state = 4; }
/* 318 */           else if (firstword && (Character.isLetterOrDigit(ch) || ch == '#'))
/*     */           
/* 320 */           { tok.append(ch);
/* 321 */             state = 3; }
/*     */           
/* 323 */           this.m_ParsedQuery.append(ch);
/*     */           break;
/*     */         
/*     */         case 1:
/* 327 */           if (ch == '\'') { state = 0; }
/* 328 */           else if (ch == '\\') { state = 2; }
/* 329 */            this.m_ParsedQuery.append(ch);
/*     */           break;
/*     */         
/*     */         case 2:
/* 333 */           state = 1;
/* 334 */           this.m_ParsedQuery.append(ch);
/*     */           break;
/*     */         
/*     */         case 3:
/* 338 */           if (Character.isLetterOrDigit(ch) || ch == '#' || ch == '_') { tok.append(ch); }
/*     */           else
/*     */           
/* 341 */           { if (tok.toString().equalsIgnoreCase("call")) {
/*     */               
/* 343 */               this.m_IsCallable = true;
/* 344 */               if (curParm != null)
/*     */               {
/*     */                 
/* 347 */                 curParm.setIsOutput(true);
/*     */               }
/*     */             } 
/*     */             
/* 351 */             firstword = false;
/* 352 */             tok = new StringBuffer();
/* 353 */             if (ch == '\'') { state = 1; }
/* 354 */             else if (ch == '?') { state = 4; }
/* 355 */             else { state = 0; }
/*     */              }
/*     */           
/* 358 */           this.m_ParsedQuery.append(ch);
/*     */           break;
/*     */         
/*     */         case 4:
/* 362 */           if (ch == '[') state = 5;
/*     */           
/*     */           break;
/*     */         case 5:
/* 366 */           if (!Character.isWhitespace(ch) && ch != '=') {
/*     */             
/* 368 */             tok.append(Character.toUpperCase(ch)); break;
/*     */           } 
/* 370 */           if (tok.length() > 0) {
/*     */ 
/*     */             
/* 373 */             this.m_HasParameters = true;
/*     */             
/* 375 */             curParm = new QueryParameter();
/*     */             
/* 377 */             curParm.setTypeName(tok.toString());
/*     */             
/* 379 */             this.m_Parameters.addElement(curParm);
/* 380 */             tok = new StringBuffer();
/* 381 */             if (ch == '=') { state = 7; break; }
/* 382 */              state = 6;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 6:
/* 387 */           if (ch == '=') state = 7;
/*     */           
/*     */           break;
/*     */         case 7:
/* 391 */           if (!Character.isWhitespace(ch) && ch != ']') { tok.append(ch); break; }
/* 392 */            if (tok.length() > 0) {
/*     */             
/* 394 */             curParm.setName(tok.toString());
/* 395 */             tok = new StringBuffer();
/* 396 */             if (ch == ']') {
/*     */ 
/*     */               
/* 399 */               state = 0; break;
/*     */             } 
/* 401 */             state = 8;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 8:
/* 406 */           if (!Character.isWhitespace(ch) && ch != ']') {
/*     */             
/* 408 */             tok.append(ch); break;
/*     */           } 
/* 410 */           if (tok.length() > 0) {
/*     */             
/* 412 */             tok.setLength(3);
/* 413 */             if (tok.toString().equalsIgnoreCase("OUT")) {
/*     */               
/* 415 */               curParm.setIsOutput(true);
/* 416 */               this.m_hasOutput = true;
/*     */             } 
/*     */             
/* 419 */             tok = new StringBuffer();
/* 420 */             if (ch == ']')
/*     */             {
/* 422 */               state = 0;
/*     */             }
/*     */           } 
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 431 */     if (this.m_IsCallable) {
/*     */       
/* 433 */       this.m_ParsedQuery.insert(0, '{');
/* 434 */       this.m_ParsedQuery.append('}');
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/SQLQueryParser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */