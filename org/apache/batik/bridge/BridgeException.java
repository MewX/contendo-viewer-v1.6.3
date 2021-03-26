/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BridgeException
/*     */   extends RuntimeException
/*     */ {
/*     */   protected Element e;
/*     */   protected String code;
/*     */   protected String message;
/*     */   protected Object[] params;
/*     */   protected int line;
/*     */   protected GraphicsNode node;
/*     */   
/*     */   public BridgeException(BridgeContext ctx, LiveAttributeException ex) {
/*  64 */     switch (ex.getCode()) {
/*     */       case 0:
/*  66 */         this.code = "attribute.missing";
/*     */         break;
/*     */       case 1:
/*  69 */         this.code = "attribute.malformed";
/*     */         break;
/*     */       case 2:
/*  72 */         this.code = "length.negative";
/*     */         break;
/*     */       default:
/*  75 */         throw new IllegalStateException("Unknown LiveAttributeException error code " + ex.getCode());
/*     */     } 
/*     */ 
/*     */     
/*  79 */     this.e = ex.getElement();
/*  80 */     this.params = new Object[] { ex.getAttributeName(), ex.getValue() };
/*  81 */     if (this.e != null && ctx != null) {
/*  82 */       this.line = ctx.getDocumentLoader().getLineNumber(this.e);
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
/*     */   public BridgeException(BridgeContext ctx, Element e, String code, Object[] params) {
/*  98 */     this.e = e;
/*  99 */     this.code = code;
/* 100 */     this.params = params;
/* 101 */     if (e != null && ctx != null) {
/* 102 */       this.line = ctx.getDocumentLoader().getLineNumber(e);
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
/*     */   public BridgeException(BridgeContext ctx, Element e, Exception ex, String code, Object[] params) {
/* 120 */     this.e = e;
/*     */     
/* 122 */     this.message = ex.getMessage();
/* 123 */     this.code = code;
/* 124 */     this.params = params;
/* 125 */     if (e != null && ctx != null) {
/* 126 */       this.line = ctx.getDocumentLoader().getLineNumber(e);
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
/*     */   public BridgeException(BridgeContext ctx, Element e, String message) {
/* 139 */     this.e = e;
/* 140 */     this.message = message;
/* 141 */     if (e != null && ctx != null) {
/* 142 */       this.line = ctx.getDocumentLoader().getLineNumber(e);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getElement() {
/* 150 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGraphicsNode(GraphicsNode node) {
/* 159 */     this.node = node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getGraphicsNode() {
/* 166 */     return this.node;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*     */     String uri;
/* 173 */     if (this.message != null) {
/* 174 */       return this.message;
/*     */     }
/*     */ 
/*     */     
/* 178 */     String lname = "<Unknown Element>";
/* 179 */     SVGDocument doc = null;
/* 180 */     if (this.e != null) {
/* 181 */       doc = (SVGDocument)this.e.getOwnerDocument();
/* 182 */       lname = this.e.getLocalName();
/*     */     } 
/* 184 */     if (doc == null) { uri = "<Unknown Document>"; }
/* 185 */     else { uri = doc.getURL(); }
/* 186 */      Object[] fullparams = new Object[this.params.length + 3];
/* 187 */     fullparams[0] = uri;
/* 188 */     fullparams[1] = Integer.valueOf(this.line);
/* 189 */     fullparams[2] = lname;
/* 190 */     System.arraycopy(this.params, 0, fullparams, 3, this.params.length);
/* 191 */     return Messages.formatMessage(this.code, fullparams);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCode() {
/* 198 */     return this.code;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/BridgeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */