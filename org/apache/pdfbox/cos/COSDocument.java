/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.io.ScratchFile;
/*     */ import org.apache.pdfbox.pdfparser.PDFObjectStreamParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class COSDocument
/*     */   extends COSBase
/*     */   implements Closeable
/*     */ {
/*  47 */   private static final Log LOG = LogFactory.getLog(COSDocument.class);
/*     */   
/*  49 */   private float version = 1.4F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   private final Map<COSObjectKey, COSObject> objectPool = new HashMap<COSObjectKey, COSObject>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private final Map<COSObjectKey, Long> xrefTable = new HashMap<COSObjectKey, Long>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private final List<COSStream> streams = new ArrayList<COSStream>();
/*     */ 
/*     */ 
/*     */   
/*     */   private COSDictionary trailer;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean warnMissingClose = true;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isDecrypted = false;
/*     */ 
/*     */   
/*     */   private long startXref;
/*     */ 
/*     */   
/*     */   private boolean closed = false;
/*     */ 
/*     */   
/*     */   private boolean isXRefStream;
/*     */ 
/*     */   
/*     */   private ScratchFile scratchFile;
/*     */ 
/*     */   
/*     */   private long highestXRefObjectNumber;
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDocument() {
/*  99 */     this(ScratchFile.getMainMemoryOnlyInstance());
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
/*     */   public COSDocument(ScratchFile scratchFile) {
/* 111 */     this.scratchFile = scratchFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStream createCOSStream() {
/* 121 */     COSStream stream = new COSStream(this.scratchFile);
/*     */ 
/*     */ 
/*     */     
/* 125 */     this.streams.add(stream);
/* 126 */     return stream;
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
/*     */   public COSStream createCOSStream(COSDictionary dictionary) {
/* 138 */     COSStream stream = new COSStream(this.scratchFile);
/* 139 */     for (Map.Entry<COSName, COSBase> entry : dictionary.entrySet())
/*     */     {
/* 141 */       stream.setItem(entry.getKey(), entry.getValue());
/*     */     }
/* 143 */     return stream;
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
/*     */   public COSObject getObjectByType(COSName type) throws IOException {
/* 156 */     for (COSObject object : this.objectPool.values()) {
/*     */       
/* 158 */       COSBase realObject = object.getObject();
/* 159 */       if (realObject instanceof COSDictionary) {
/*     */         
/*     */         try {
/*     */           
/* 163 */           COSDictionary dic = (COSDictionary)realObject;
/* 164 */           COSBase typeItem = dic.getItem(COSName.TYPE);
/* 165 */           if (typeItem instanceof COSName) {
/*     */             
/* 167 */             COSName objectType = (COSName)typeItem;
/* 168 */             if (objectType.equals(type))
/*     */             {
/* 170 */               return object; } 
/*     */             continue;
/*     */           } 
/* 173 */           if (typeItem != null)
/*     */           {
/* 175 */             LOG.debug("Expected a /Name object after /Type, got '" + typeItem + "' instead");
/*     */           }
/*     */         }
/* 178 */         catch (ClassCastException e) {
/*     */           
/* 180 */           LOG.warn(e, e);
/*     */         } 
/*     */       }
/*     */     } 
/* 184 */     return null;
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
/*     */   public List<COSObject> getObjectsByType(String type) throws IOException {
/* 197 */     return getObjectsByType(COSName.getPDFName(type));
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
/*     */   public List<COSObject> getObjectsByType(COSName type) throws IOException {
/* 210 */     List<COSObject> retval = new ArrayList<COSObject>();
/* 211 */     for (COSObject object : this.objectPool.values()) {
/*     */       
/* 213 */       COSBase realObject = object.getObject();
/* 214 */       if (realObject instanceof COSDictionary) {
/*     */         
/*     */         try {
/*     */           
/* 218 */           COSDictionary dic = (COSDictionary)realObject;
/* 219 */           COSBase typeItem = dic.getItem(COSName.TYPE);
/* 220 */           if (typeItem instanceof COSName) {
/*     */             
/* 222 */             COSName objectType = (COSName)typeItem;
/* 223 */             if (objectType.equals(type))
/*     */             {
/* 225 */               retval.add(object); } 
/*     */             continue;
/*     */           } 
/* 228 */           if (typeItem != null)
/*     */           {
/* 230 */             LOG.debug("Expected a /Name object after /Type, got '" + typeItem + "' instead");
/*     */           }
/*     */         }
/* 233 */         catch (ClassCastException e) {
/*     */           
/* 235 */           LOG.warn(e, e);
/*     */         } 
/*     */       }
/*     */     } 
/* 239 */     return retval;
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
/*     */   public COSObjectKey getKey(COSBase object) {
/* 251 */     for (Map.Entry<COSObjectKey, COSObject> entry : this.objectPool.entrySet()) {
/*     */       
/* 253 */       if (((COSObject)entry.getValue()).getObject() == object)
/*     */       {
/* 255 */         return entry.getKey();
/*     */       }
/*     */     } 
/* 258 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void print() {
/* 266 */     for (COSObject object : this.objectPool.values())
/*     */     {
/* 268 */       System.out.println(object);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(float versionValue) {
/* 279 */     this.version = versionValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVersion() {
/* 289 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecrypted() {
/* 297 */     this.isDecrypted = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDecrypted() {
/* 307 */     return this.isDecrypted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEncrypted() {
/* 317 */     boolean encrypted = false;
/* 318 */     if (this.trailer != null)
/*     */     {
/* 320 */       encrypted = this.trailer.getDictionaryObject(COSName.ENCRYPT) instanceof COSDictionary;
/*     */     }
/* 322 */     return encrypted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getEncryptionDictionary() {
/* 333 */     return this.trailer.getCOSDictionary(COSName.ENCRYPT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncryptionDictionary(COSDictionary encDictionary) {
/* 344 */     this.trailer.setItem(COSName.ENCRYPT, encDictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getDocumentID() {
/* 354 */     return getTrailer().getCOSArray(COSName.ID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentID(COSArray id) {
/* 364 */     getTrailer().setItem(COSName.ID, id);
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
/*     */   public COSObject getCatalog() throws IOException {
/* 378 */     COSObject catalog = getObjectByType(COSName.CATALOG);
/* 379 */     if (catalog == null)
/*     */     {
/* 381 */       throw new IOException("Catalog cannot be found");
/*     */     }
/* 383 */     return catalog;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<COSObject> getObjects() {
/* 393 */     return new ArrayList<COSObject>(this.objectPool.values());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getTrailer() {
/* 403 */     return this.trailer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrailer(COSDictionary newTrailer) {
/* 414 */     this.trailer = newTrailer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getHighestXRefObjectNumber() {
/* 425 */     return this.highestXRefObjectNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHighestXRefObjectNumber(long highestXRefObjectNumber) {
/* 436 */     this.highestXRefObjectNumber = highestXRefObjectNumber;
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
/*     */   public Object accept(ICOSVisitor visitor) throws IOException {
/* 449 */     return visitor.visitFromDocument(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 460 */     if (!this.closed) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 468 */       IOException firstException = null;
/*     */ 
/*     */       
/* 471 */       for (COSObject object : getObjects()) {
/*     */         
/* 473 */         COSBase cosObject = object.getObject();
/* 474 */         if (cosObject instanceof COSStream)
/*     */         {
/* 476 */           firstException = IOUtils.closeAndLogException((COSStream)cosObject, LOG, "COSStream", firstException);
/*     */         }
/*     */       } 
/* 479 */       for (COSStream stream : this.streams)
/*     */       {
/* 481 */         firstException = IOUtils.closeAndLogException(stream, LOG, "COSStream", firstException);
/*     */       }
/* 483 */       if (this.scratchFile != null)
/*     */       {
/* 485 */         firstException = IOUtils.closeAndLogException((Closeable)this.scratchFile, LOG, "ScratchFile", firstException);
/*     */       }
/* 487 */       this.closed = true;
/*     */ 
/*     */       
/* 490 */       if (firstException != null)
/*     */       {
/* 492 */         throw firstException;
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
/*     */   public boolean isClosed() {
/* 504 */     return this.closed;
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
/*     */   protected void finalize() throws IOException {
/* 516 */     if (!this.closed) {
/*     */       
/* 518 */       if (this.warnMissingClose)
/*     */       {
/* 520 */         LOG.warn("Warning: You did not close a PDF Document");
/*     */       }
/* 522 */       close();
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
/*     */   public void setWarnMissingClose(boolean warn) {
/* 535 */     this.warnMissingClose = warn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dereferenceObjectStreams() throws IOException {
/* 546 */     for (COSObject objStream : getObjectsByType(COSName.OBJ_STM)) {
/*     */       
/* 548 */       COSStream stream = (COSStream)objStream.getObject();
/* 549 */       PDFObjectStreamParser parser = new PDFObjectStreamParser(stream, this);
/* 550 */       parser.parse();
/* 551 */       for (COSObject next : parser.getObjects()) {
/*     */         
/* 553 */         COSObjectKey key = new COSObjectKey(next);
/* 554 */         if (this.objectPool.get(key) == null || ((COSObject)this.objectPool.get(key)).getObject() == null || (this.xrefTable
/*     */           
/* 556 */           .containsKey(key) && ((Long)this.xrefTable
/* 557 */           .get(key)).longValue() == -objStream.getObjectNumber())) {
/*     */           
/* 559 */           COSObject obj = getObjectFromPool(key);
/* 560 */           obj.setObject(next.getObject());
/*     */         } 
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
/*     */   public COSObject getObjectFromPool(COSObjectKey key) throws IOException {
/* 577 */     COSObject obj = null;
/* 578 */     if (key != null)
/*     */     {
/* 580 */       obj = this.objectPool.get(key);
/*     */     }
/* 582 */     if (obj == null) {
/*     */ 
/*     */       
/* 585 */       obj = new COSObject(null);
/* 586 */       if (key != null) {
/*     */         
/* 588 */         obj.setObjectNumber(key.getNumber());
/* 589 */         obj.setGenerationNumber(key.getGeneration());
/* 590 */         this.objectPool.put(key, obj);
/*     */       } 
/*     */     } 
/* 593 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSObject removeObject(COSObjectKey key) {
/* 603 */     return this.objectPool.remove(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addXRefTable(Map<COSObjectKey, Long> xrefTableValues) {
/* 613 */     this.xrefTable.putAll(xrefTableValues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<COSObjectKey, Long> getXrefTable() {
/* 623 */     return this.xrefTable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartXref(long startXrefValue) {
/* 634 */     this.startXref = startXrefValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getStartXref() {
/* 644 */     return this.startXref;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isXRefStream() {
/* 654 */     return this.isXRefStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsXRefStream(boolean isXRefStreamValue) {
/* 665 */     this.isXRefStream = isXRefStreamValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */