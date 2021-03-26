/*     */ package org.apache.batik.transcoder.svg2svg;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.transcoder.AbstractTranscoder;
/*     */ import org.apache.batik.transcoder.ErrorHandler;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderInput;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.TranscodingHints;
/*     */ import org.apache.batik.transcoder.keys.BooleanKey;
/*     */ import org.apache.batik.transcoder.keys.IntegerKey;
/*     */ import org.apache.batik.transcoder.keys.StringKey;
/*     */ import org.w3c.dom.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGTranscoder
/*     */   extends AbstractTranscoder
/*     */ {
/*  51 */   public static final ErrorHandler DEFAULT_ERROR_HANDLER = new ErrorHandler() {
/*     */       public void error(TranscoderException ex) throws TranscoderException {
/*  53 */         throw ex;
/*     */       }
/*     */       public void fatalError(TranscoderException ex) throws TranscoderException {
/*  56 */         throw ex;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void warning(TranscoderException ex) throws TranscoderException {}
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static final TranscodingHints.Key KEY_NEWLINE = new NewlineKey();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public static final NewlineValue VALUE_NEWLINE_CR = new NewlineValue("\r");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static final NewlineValue VALUE_NEWLINE_CR_LF = new NewlineValue("\r\n");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static final NewlineValue VALUE_NEWLINE_LF = new NewlineValue("\n");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public static final TranscodingHints.Key KEY_FORMAT = (TranscodingHints.Key)new BooleanKey();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public static final Boolean VALUE_FORMAT_ON = Boolean.TRUE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static final Boolean VALUE_FORMAT_OFF = Boolean.FALSE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public static final TranscodingHints.Key KEY_TABULATION_WIDTH = (TranscodingHints.Key)new IntegerKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public static final TranscodingHints.Key KEY_DOCUMENT_WIDTH = (TranscodingHints.Key)new IntegerKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public static final TranscodingHints.Key KEY_DOCTYPE = new DoctypeKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public static final DoctypeValue VALUE_DOCTYPE_CHANGE = new DoctypeValue(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public static final DoctypeValue VALUE_DOCTYPE_REMOVE = new DoctypeValue(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public static final DoctypeValue VALUE_DOCTYPE_KEEP_UNCHANGED = new DoctypeValue(2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public static final TranscodingHints.Key KEY_PUBLIC_ID = (TranscodingHints.Key)new StringKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static final TranscodingHints.Key KEY_SYSTEM_ID = (TranscodingHints.Key)new StringKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public static final TranscodingHints.Key KEY_XML_DECLARATION = (TranscodingHints.Key)new StringKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTranscoder() {
/* 156 */     setErrorHandler(DEFAULT_ERROR_HANDLER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void transcode(TranscoderInput input, TranscoderOutput output) throws TranscoderException {
/* 167 */     Reader r = input.getReader();
/* 168 */     Writer w = output.getWriter();
/*     */     
/* 170 */     if (r == null) {
/* 171 */       Document d = input.getDocument();
/* 172 */       if (d == null) {
/* 173 */         throw new RuntimeException("Reader or Document expected");
/*     */       }
/* 175 */       StringWriter sw = new StringWriter(1024);
/*     */       try {
/* 177 */         DOMUtilities.writeDocument(d, sw);
/* 178 */       } catch (IOException ioEx) {
/* 179 */         throw new RuntimeException("IO:" + ioEx.getMessage());
/*     */       } 
/* 181 */       r = new StringReader(sw.toString());
/*     */     } 
/* 183 */     if (w == null) {
/* 184 */       throw new RuntimeException("Writer expected");
/*     */     }
/* 186 */     prettyPrint(r, w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void prettyPrint(Reader in, Writer out) throws TranscoderException {
/*     */     try {
/* 195 */       PrettyPrinter pp = new PrettyPrinter();
/* 196 */       NewlineValue nlv = (NewlineValue)this.hints.get(KEY_NEWLINE);
/* 197 */       if (nlv != null) {
/* 198 */         pp.setNewline(nlv.getValue());
/*     */       }
/* 200 */       Boolean b = (Boolean)this.hints.get(KEY_FORMAT);
/* 201 */       if (b != null) {
/* 202 */         pp.setFormat(b.booleanValue());
/*     */       }
/* 204 */       Integer i = (Integer)this.hints.get(KEY_TABULATION_WIDTH);
/* 205 */       if (i != null) {
/* 206 */         pp.setTabulationWidth(i.intValue());
/*     */       }
/* 208 */       i = (Integer)this.hints.get(KEY_DOCUMENT_WIDTH);
/* 209 */       if (i != null) {
/* 210 */         pp.setDocumentWidth(i.intValue());
/*     */       }
/* 212 */       DoctypeValue dtv = (DoctypeValue)this.hints.get(KEY_DOCTYPE);
/* 213 */       if (dtv != null) {
/* 214 */         pp.setDoctypeOption(dtv.getValue());
/*     */       }
/* 216 */       String s = (String)this.hints.get(KEY_PUBLIC_ID);
/* 217 */       if (s != null) {
/* 218 */         pp.setPublicId(s);
/*     */       }
/* 220 */       s = (String)this.hints.get(KEY_SYSTEM_ID);
/* 221 */       if (s != null) {
/* 222 */         pp.setSystemId(s);
/*     */       }
/*     */       
/* 225 */       s = (String)this.hints.get(KEY_XML_DECLARATION);
/* 226 */       if (s != null) {
/* 227 */         pp.setXMLDeclaration(s);
/*     */       }
/*     */       
/* 230 */       pp.print(in, out);
/* 231 */       out.flush();
/* 232 */     } catch (IOException e) {
/* 233 */       getErrorHandler().fatalError(new TranscoderException(e.getMessage()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class NewlineKey
/*     */     extends TranscodingHints.Key
/*     */   {
/*     */     public boolean isCompatibleValue(Object v) {
/* 242 */       return v instanceof SVGTranscoder.NewlineValue;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class NewlineValue
/*     */   {
/*     */     protected final String value;
/*     */     
/*     */     protected NewlineValue(String val) {
/* 252 */       this.value = val;
/*     */     }
/*     */     public String getValue() {
/* 255 */       return this.value;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class DoctypeKey
/*     */     extends TranscodingHints.Key
/*     */   {
/*     */     public boolean isCompatibleValue(Object v) {
/* 264 */       return v instanceof SVGTranscoder.DoctypeValue;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class DoctypeValue
/*     */   {
/*     */     final int value;
/*     */     
/*     */     protected DoctypeValue(int value) {
/* 274 */       this.value = value;
/*     */     }
/*     */     public int getValue() {
/* 277 */       return this.value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/svg2svg/SVGTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */