/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StylesheetPIHandler
/*     */   extends DefaultHandler
/*     */ {
/*     */   String m_baseID;
/*     */   String m_media;
/*     */   String m_title;
/*     */   String m_charset;
/*  54 */   Vector m_stylesheets = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   URIResolver m_uriResolver;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURIResolver(URIResolver resolver) {
/*  73 */     this.m_uriResolver = resolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIResolver getURIResolver() {
/*  84 */     return this.m_uriResolver;
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
/*     */   public StylesheetPIHandler(String baseID, String media, String title, String charset) {
/* 101 */     this.m_baseID = baseID;
/* 102 */     this.m_media = media;
/* 103 */     this.m_title = title;
/* 104 */     this.m_charset = charset;
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
/*     */   public Source getAssociatedStylesheet() {
/* 116 */     int sz = this.m_stylesheets.size();
/*     */     
/* 118 */     if (sz > 0) {
/*     */       
/* 120 */       Source source = this.m_stylesheets.elementAt(sz - 1);
/* 121 */       return source;
/*     */     } 
/*     */     
/* 124 */     return null;
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
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 142 */     if (target.equals("xml-stylesheet")) {
/*     */       
/* 144 */       String href = null;
/* 145 */       String type = null;
/* 146 */       String title = null;
/* 147 */       String media = null;
/* 148 */       String charset = null;
/* 149 */       boolean alternate = false;
/* 150 */       StringTokenizer tokenizer = new StringTokenizer(data, " \t=\n", true);
/* 151 */       boolean lookedAhead = false;
/* 152 */       Source source = null;
/*     */       
/* 154 */       String token = "";
/* 155 */       while (tokenizer.hasMoreTokens()) {
/*     */         
/* 157 */         if (!lookedAhead) {
/* 158 */           token = tokenizer.nextToken();
/*     */         } else {
/* 160 */           lookedAhead = false;
/* 161 */         }  if (tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("="))) {
/*     */           continue;
/*     */         }
/*     */         
/* 165 */         String name = token;
/* 166 */         if (name.equals("type")) {
/*     */           
/* 168 */           token = tokenizer.nextToken();
/* 169 */           while (tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")))
/*     */           {
/* 171 */             token = tokenizer.nextToken(); } 
/* 172 */           type = token.substring(1, token.length() - 1);
/*     */           continue;
/*     */         } 
/* 175 */         if (name.equals("href")) {
/*     */           
/* 177 */           token = tokenizer.nextToken();
/* 178 */           while (tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")))
/*     */           {
/* 180 */             token = tokenizer.nextToken(); } 
/* 181 */           href = token;
/* 182 */           if (tokenizer.hasMoreTokens()) {
/*     */             
/* 184 */             token = tokenizer.nextToken();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 193 */             while (token.equals("=") && tokenizer.hasMoreTokens()) {
/*     */               
/* 195 */               href = href + token + tokenizer.nextToken();
/* 196 */               if (tokenizer.hasMoreTokens()) {
/*     */                 
/* 198 */                 token = tokenizer.nextToken();
/* 199 */                 lookedAhead = true;
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 207 */           href = href.substring(1, href.length() - 1);
/*     */ 
/*     */ 
/*     */           
/* 211 */           try { if (this.m_uriResolver != null) {
/*     */               
/* 213 */               source = this.m_uriResolver.resolve(href, this.m_baseID);
/*     */               
/*     */               continue;
/*     */             } 
/* 217 */             href = SystemIDResolver.getAbsoluteURI(href, this.m_baseID);
/* 218 */             source = new SAXSource(new InputSource(href)); } catch (TransformerException te)
/*     */           
/*     */           { 
/*     */ 
/*     */             
/* 223 */             throw new SAXException(te); }
/*     */            continue;
/*     */         } 
/* 226 */         if (name.equals("title")) {
/*     */           
/* 228 */           token = tokenizer.nextToken();
/* 229 */           while (tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")))
/*     */           {
/* 231 */             token = tokenizer.nextToken(); } 
/* 232 */           title = token.substring(1, token.length() - 1); continue;
/*     */         } 
/* 234 */         if (name.equals("media")) {
/*     */           
/* 236 */           token = tokenizer.nextToken();
/* 237 */           while (tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")))
/*     */           {
/* 239 */             token = tokenizer.nextToken(); } 
/* 240 */           media = token.substring(1, token.length() - 1); continue;
/*     */         } 
/* 242 */         if (name.equals("charset")) {
/*     */           
/* 244 */           token = tokenizer.nextToken();
/* 245 */           while (tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")))
/*     */           {
/* 247 */             token = tokenizer.nextToken(); } 
/* 248 */           charset = token.substring(1, token.length() - 1); continue;
/*     */         } 
/* 250 */         if (name.equals("alternate")) {
/*     */           
/* 252 */           token = tokenizer.nextToken();
/* 253 */           while (tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")))
/*     */           {
/* 255 */             token = tokenizer.nextToken(); } 
/* 256 */           alternate = token.substring(1, token.length() - 1).equals("yes");
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 262 */       if (null != type && (type.equals("text/xsl") || type.equals("text/xml") || type.equals("application/xml+xslt")) && null != href) {
/*     */ 
/*     */ 
/*     */         
/* 266 */         if (null != this.m_media)
/*     */         {
/* 268 */           if (null != media) {
/*     */             
/* 270 */             if (!media.equals(this.m_media)) {
/*     */               return;
/*     */             }
/*     */           } else {
/*     */             return;
/*     */           } 
/*     */         }
/* 277 */         if (null != this.m_charset)
/*     */         {
/* 279 */           if (null != charset) {
/*     */             
/* 281 */             if (!charset.equals(this.m_charset)) {
/*     */               return;
/*     */             }
/*     */           } else {
/*     */             return;
/*     */           } 
/*     */         }
/* 288 */         if (null != this.m_title)
/*     */         {
/* 290 */           if (null != title) {
/*     */             
/* 292 */             if (!title.equals(this.m_title)) {
/*     */               return;
/*     */             }
/*     */           } else {
/*     */             return;
/*     */           } 
/*     */         }
/* 299 */         this.m_stylesheets.addElement(source);
/*     */       } 
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
/*     */ 
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 322 */     throw new StopParseException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseId(String baseId) {
/* 331 */     this.m_baseID = baseId;
/*     */   }
/*     */   
/*     */   public String getBaseId() {
/* 335 */     return this.m_baseID;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/StylesheetPIHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */