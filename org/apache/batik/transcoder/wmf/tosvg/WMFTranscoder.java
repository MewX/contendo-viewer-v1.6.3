/*     */ package org.apache.batik.transcoder.wmf.tosvg;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import org.apache.batik.svggen.SVGGraphics2D;
/*     */ import org.apache.batik.transcoder.ToSVGAbstractTranscoder;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderInput;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMFTranscoder
/*     */   extends ToSVGAbstractTranscoder
/*     */ {
/*     */   public static final String WMF_EXTENSION = ".wmf";
/*     */   public static final String SVG_EXTENSION = ".svg";
/*     */   
/*     */   public void transcode(TranscoderInput input, TranscoderOutput output) throws TranscoderException {
/*     */     float wmfwidth, wmfheight;
/*     */     int vpW, vpH;
/* 105 */     DataInputStream is = getCompatibleInput(input);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     WMFRecordStore currentStore = new WMFRecordStore();
/*     */     try {
/* 112 */       currentStore.read(is);
/* 113 */     } catch (IOException e) {
/* 114 */       this.handler.fatalError(new TranscoderException(e));
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 121 */     float conv = 1.0F;
/*     */     
/* 123 */     if (this.hints.containsKey(KEY_INPUT_WIDTH)) {
/* 124 */       wmfwidth = ((Integer)this.hints.get(KEY_INPUT_WIDTH)).intValue();
/* 125 */       wmfheight = ((Integer)this.hints.get(KEY_INPUT_HEIGHT)).intValue();
/*     */     } else {
/* 127 */       wmfwidth = currentStore.getWidthPixels();
/* 128 */       wmfheight = currentStore.getHeightPixels();
/*     */     } 
/* 130 */     float width = wmfwidth;
/* 131 */     float height = wmfheight;
/*     */ 
/*     */     
/* 134 */     if (this.hints.containsKey(KEY_WIDTH)) {
/* 135 */       width = ((Float)this.hints.get(KEY_WIDTH)).floatValue();
/* 136 */       conv = width / wmfwidth;
/* 137 */       height = height * width / wmfwidth;
/*     */     } 
/*     */ 
/*     */     
/* 141 */     int xOffset = 0;
/* 142 */     int yOffset = 0;
/* 143 */     if (this.hints.containsKey(KEY_XOFFSET)) {
/* 144 */       xOffset = ((Integer)this.hints.get(KEY_XOFFSET)).intValue();
/*     */     }
/* 146 */     if (this.hints.containsKey(KEY_YOFFSET)) {
/* 147 */       yOffset = ((Integer)this.hints.get(KEY_YOFFSET)).intValue();
/*     */     }
/*     */ 
/*     */     
/* 151 */     float sizeFactor = currentStore.getUnitsToPixels() * conv;
/*     */     
/* 153 */     int vpX = (int)(currentStore.getVpX() * sizeFactor);
/* 154 */     int vpY = (int)(currentStore.getVpY() * sizeFactor);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     if (this.hints.containsKey(KEY_INPUT_WIDTH)) {
/* 160 */       vpW = (int)(((Integer)this.hints.get(KEY_INPUT_WIDTH)).intValue() * conv);
/* 161 */       vpH = (int)(((Integer)this.hints.get(KEY_INPUT_HEIGHT)).intValue() * conv);
/*     */     } else {
/*     */       
/* 164 */       vpW = (int)(currentStore.getWidthUnits() * sizeFactor);
/* 165 */       vpH = (int)(currentStore.getHeightUnits() * sizeFactor);
/*     */     } 
/*     */ 
/*     */     
/* 169 */     WMFPainter painter = new WMFPainter(currentStore, xOffset, yOffset, conv);
/*     */ 
/*     */     
/* 172 */     Document doc = createDocument(output);
/* 173 */     this.svgGenerator = new SVGGraphics2D(doc);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     this.svgGenerator.getGeneratorContext().setPrecision(4);
/*     */     
/* 181 */     painter.paint((Graphics)this.svgGenerator);
/*     */     
/* 183 */     this.svgGenerator.setSVGCanvasSize(new Dimension(vpW, vpH));
/*     */     
/* 185 */     Element svgRoot = this.svgGenerator.getRoot();
/*     */     
/* 187 */     svgRoot.setAttributeNS(null, "viewBox", String.valueOf(vpX) + ' ' + vpY + ' ' + vpW + ' ' + vpH);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     writeSVGToOutput(this.svgGenerator, svgRoot, output);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DataInputStream getCompatibleInput(TranscoderInput input) throws TranscoderException {
/* 202 */     if (input == null) {
/* 203 */       this.handler.fatalError(new TranscoderException(String.valueOf(65280)));
/*     */     }
/*     */ 
/*     */     
/* 207 */     InputStream in = input.getInputStream();
/* 208 */     if (in != null) {
/* 209 */       return new DataInputStream(new BufferedInputStream(in));
/*     */     }
/*     */ 
/*     */     
/* 213 */     String uri = input.getURI();
/* 214 */     if (uri != null) {
/*     */       try {
/* 216 */         URL url = new URL(uri);
/* 217 */         in = url.openStream();
/* 218 */         return new DataInputStream(new BufferedInputStream(in));
/* 219 */       } catch (MalformedURLException e) {
/* 220 */         this.handler.fatalError(new TranscoderException(e));
/* 221 */       } catch (IOException e) {
/* 222 */         this.handler.fatalError(new TranscoderException(e));
/*     */       } 
/*     */     }
/*     */     
/* 226 */     this.handler.fatalError(new TranscoderException(String.valueOf(65281)));
/* 227 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws TranscoderException {
/* 237 */     if (args.length < 1) {
/* 238 */       System.out.println("Usage : WMFTranscoder.main <file 1> ... <file n>");
/* 239 */       System.exit(1);
/*     */     } 
/*     */     
/* 242 */     WMFTranscoder transcoder = new WMFTranscoder();
/* 243 */     int nFiles = args.length;
/*     */     
/* 245 */     for (String fileName : args) {
/* 246 */       if (!fileName.toLowerCase().endsWith(".wmf")) {
/* 247 */         System.err.println(fileName + " does not have the " + ".wmf" + " extension. It is ignored");
/*     */       } else {
/* 249 */         System.out.print("Processing : " + fileName + "...");
/* 250 */         String outputFileName = fileName.substring(0, fileName.toLowerCase().indexOf(".wmf")) + ".svg";
/* 251 */         File inputFile = new File(fileName);
/* 252 */         File outputFile = new File(outputFileName);
/*     */         try {
/* 254 */           TranscoderInput input = new TranscoderInput(inputFile.toURI().toURL().toString());
/* 255 */           TranscoderOutput output = new TranscoderOutput(new FileOutputStream(outputFile));
/* 256 */           transcoder.transcode(input, output);
/* 257 */         } catch (MalformedURLException e) {
/* 258 */           throw new TranscoderException(e);
/* 259 */         } catch (IOException e) {
/* 260 */           throw new TranscoderException(e);
/*     */         } 
/* 262 */         System.out.println(".... Done");
/*     */       } 
/*     */     } 
/*     */     
/* 266 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/WMFTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */