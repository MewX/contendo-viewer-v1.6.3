/*     */ package org.apache.batik.transcoder;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import org.apache.batik.anim.dom.SVGDOMImplementation;
/*     */ import org.apache.batik.svggen.SVGGraphics2D;
/*     */ import org.apache.batik.transcoder.keys.BooleanKey;
/*     */ import org.apache.batik.transcoder.keys.FloatKey;
/*     */ import org.apache.batik.transcoder.keys.IntegerKey;
/*     */ import org.apache.batik.util.Platform;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.xml.sax.XMLFilter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ToSVGAbstractTranscoder
/*     */   extends AbstractTranscoder
/*     */   implements SVGConstants
/*     */ {
/*  90 */   public static float PIXEL_TO_MILLIMETERS = 25.4F / Platform.getScreenResolution();
/*  91 */   public static float PIXEL_PER_INCH = Platform.getScreenResolution();
/*     */   
/*     */   public static final int TRANSCODER_ERROR_BASE = 65280;
/*     */   
/*     */   public static final int ERROR_NULL_INPUT = 65280;
/*     */   
/*     */   public static final int ERROR_INCOMPATIBLE_INPUT_TYPE = 65281;
/*     */   
/*     */   public static final int ERROR_INCOMPATIBLE_OUTPUT_TYPE = 65282;
/*     */   
/* 101 */   public static final TranscodingHints.Key KEY_WIDTH = (TranscodingHints.Key)new FloatKey();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public static final TranscodingHints.Key KEY_HEIGHT = (TranscodingHints.Key)new FloatKey();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static final TranscodingHints.Key KEY_INPUT_WIDTH = (TranscodingHints.Key)new IntegerKey();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public static final TranscodingHints.Key KEY_INPUT_HEIGHT = (TranscodingHints.Key)new IntegerKey();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public static final TranscodingHints.Key KEY_XOFFSET = (TranscodingHints.Key)new IntegerKey();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public static final TranscodingHints.Key KEY_YOFFSET = (TranscodingHints.Key)new IntegerKey();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public static final TranscodingHints.Key KEY_ESCAPED = (TranscodingHints.Key)new BooleanKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGGraphics2D svgGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Document createDocument(TranscoderOutput output) {
/*     */     Document doc;
/* 146 */     if (output.getDocument() == null) {
/* 147 */       DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
/*     */       
/* 149 */       doc = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);
/*     */     } else {
/* 151 */       doc = output.getDocument();
/*     */     } 
/*     */     
/* 154 */     return doc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGGraphics2D getGraphics2D() {
/* 161 */     return this.svgGenerator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeSVGToOutput(SVGGraphics2D svgGenerator, Element svgRoot, TranscoderOutput output) throws TranscoderException {
/* 171 */     Document doc = output.getDocument();
/*     */     
/* 173 */     if (doc != null) {
/*     */       return;
/*     */     }
/* 176 */     XMLFilter xmlFilter = output.getXMLFilter();
/* 177 */     if (xmlFilter != null) {
/* 178 */       this.handler.fatalError(new TranscoderException("65282"));
/*     */     }
/*     */     
/*     */     try {
/* 182 */       boolean escaped = false;
/* 183 */       if (this.hints.containsKey(KEY_ESCAPED)) {
/* 184 */         escaped = ((Boolean)this.hints.get(KEY_ESCAPED)).booleanValue();
/*     */       }
/*     */       
/* 187 */       OutputStream os = output.getOutputStream();
/* 188 */       if (os != null) {
/* 189 */         svgGenerator.stream(svgRoot, new OutputStreamWriter(os), false, escaped);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 194 */       Writer wr = output.getWriter();
/* 195 */       if (wr != null) {
/* 196 */         svgGenerator.stream(svgRoot, wr, false, escaped);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 201 */       String uri = output.getURI();
/* 202 */       if (uri != null) {
/*     */         try {
/* 204 */           URL url = new URL(uri);
/* 205 */           URLConnection urlCnx = url.openConnection();
/* 206 */           os = urlCnx.getOutputStream();
/* 207 */           svgGenerator.stream(svgRoot, new OutputStreamWriter(os), false, escaped);
/*     */           return;
/* 209 */         } catch (MalformedURLException e) {
/* 210 */           this.handler.fatalError(new TranscoderException(e));
/* 211 */         } catch (IOException e) {
/* 212 */           this.handler.fatalError(new TranscoderException(e));
/*     */         } 
/*     */       }
/* 215 */     } catch (IOException e) {
/* 216 */       throw new TranscoderException(e);
/*     */     } 
/*     */     
/* 219 */     throw new TranscoderException("65282");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/ToSVGAbstractTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */