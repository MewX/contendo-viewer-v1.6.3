/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemTextLiteral
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   private boolean m_preserveSpace;
/*     */   private char[] m_ch;
/*     */   private String m_str;
/*     */   
/*     */   public void setPreserveSpace(boolean v) {
/*  49 */     this.m_preserveSpace = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPreserveSpace() {
/*  60 */     return this.m_preserveSpace;
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
/*     */   public void setChars(char[] v) {
/*  82 */     this.m_ch = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char[] getChars() {
/*  92 */     return this.m_ch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String getNodeValue() {
/* 103 */     if (null == this.m_str)
/*     */     {
/* 105 */       this.m_str = new String(this.m_ch);
/*     */     }
/*     */     
/* 108 */     return this.m_str;
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
/*     */   private boolean m_disableOutputEscaping = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisableOutputEscaping(boolean v) {
/* 140 */     this.m_disableOutputEscaping = v;
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
/*     */   public boolean getDisableOutputEscaping() {
/* 165 */     return this.m_disableOutputEscaping;
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
/*     */   public int getXSLToken() {
/* 177 */     return 78;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 187 */     return "#Text";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/*     */     
/* 205 */     try { SerializationHandler rth = transformer.getResultTreeHandler();
/* 206 */       if (TransformerImpl.S_DEBUG) {
/*     */         
/* 208 */         rth.flushPending();
/* 209 */         transformer.getTraceManager().fireTraceEvent(this);
/*     */       } 
/*     */       
/* 212 */       if (this.m_disableOutputEscaping)
/*     */       {
/* 214 */         rth.processingInstruction("javax.xml.transform.disable-output-escaping", "");
/*     */       }
/*     */       
/* 217 */       rth.characters(this.m_ch, 0, this.m_ch.length);
/*     */       
/* 219 */       if (this.m_disableOutputEscaping)
/*     */       {
/* 221 */         rth.processingInstruction("javax.xml.transform.enable-output-escaping", ""); }  } catch (SAXException se)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 226 */       throw new TransformerException(se); }
/*     */     
/*     */     finally
/*     */     
/* 230 */     { if (TransformerImpl.S_DEBUG)
/*     */ 
/*     */         
/*     */         try { 
/* 234 */           transformer.getResultTreeHandler().flushPending();
/* 235 */           transformer.getTraceManager().fireTraceEndEvent(this); } catch (SAXException se)
/*     */         
/*     */         { 
/*     */           
/* 239 */           throw new TransformerException(se); }
/*     */           }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemTextLiteral.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */