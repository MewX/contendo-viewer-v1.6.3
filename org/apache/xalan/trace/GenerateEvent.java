/*     */ package org.apache.xalan.trace;
/*     */ 
/*     */ import java.util.EventListener;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenerateEvent
/*     */   implements EventListener
/*     */ {
/*     */   public TransformerImpl m_processor;
/*     */   public int m_eventtype;
/*     */   public char[] m_characters;
/*     */   public int m_start;
/*     */   public int m_length;
/*     */   public String m_name;
/*     */   public String m_data;
/*     */   public Attributes m_atts;
/*     */   
/*     */   public GenerateEvent(TransformerImpl processor, int eventType) {
/*  90 */     this.m_processor = processor;
/*  91 */     this.m_eventtype = eventType;
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
/*     */   public GenerateEvent(TransformerImpl processor, int eventType, String name, Attributes atts) {
/* 106 */     this.m_name = name;
/* 107 */     this.m_atts = atts;
/* 108 */     this.m_processor = processor;
/* 109 */     this.m_eventtype = eventType;
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
/*     */   public GenerateEvent(TransformerImpl processor, int eventType, char[] ch, int start, int length) {
/* 125 */     this.m_characters = ch;
/* 126 */     this.m_start = start;
/* 127 */     this.m_length = length;
/* 128 */     this.m_processor = processor;
/* 129 */     this.m_eventtype = eventType;
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
/*     */   public GenerateEvent(TransformerImpl processor, int eventType, String name, String data) {
/* 144 */     this.m_name = name;
/* 145 */     this.m_data = data;
/* 146 */     this.m_processor = processor;
/* 147 */     this.m_eventtype = eventType;
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
/*     */   public GenerateEvent(TransformerImpl processor, int eventType, String data) {
/* 160 */     this.m_data = data;
/* 161 */     this.m_processor = processor;
/* 162 */     this.m_eventtype = eventType;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/trace/GenerateEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */