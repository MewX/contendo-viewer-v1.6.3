/*      */ package org.apache.pdfbox.pdfwriter;
/*      */ 
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.Closeable;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.SequenceInputStream;
/*      */ import java.security.MessageDigest;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Deque;
/*      */ import java.util.HashSet;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSBoolean;
/*      */ import org.apache.pdfbox.cos.COSDictionary;
/*      */ import org.apache.pdfbox.cos.COSDocument;
/*      */ import org.apache.pdfbox.cos.COSFloat;
/*      */ import org.apache.pdfbox.cos.COSInteger;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSNull;
/*      */ import org.apache.pdfbox.cos.COSObject;
/*      */ import org.apache.pdfbox.cos.COSObjectKey;
/*      */ import org.apache.pdfbox.cos.COSStream;
/*      */ import org.apache.pdfbox.cos.COSString;
/*      */ import org.apache.pdfbox.cos.COSUpdateInfo;
/*      */ import org.apache.pdfbox.cos.ICOSVisitor;
/*      */ import org.apache.pdfbox.io.IOUtils;
/*      */ import org.apache.pdfbox.io.RandomAccessInputStream;
/*      */ import org.apache.pdfbox.io.RandomAccessRead;
/*      */ import org.apache.pdfbox.pdfparser.PDFXRefStream;
/*      */ import org.apache.pdfbox.pdmodel.PDDocument;
/*      */ import org.apache.pdfbox.pdmodel.encryption.SecurityHandler;
/*      */ import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
/*      */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.COSFilterInputStream;
/*      */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
/*      */ import org.apache.pdfbox.util.Charsets;
/*      */ import org.apache.pdfbox.util.Hex;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class COSWriter
/*      */   implements Closeable, ICOSVisitor
/*      */ {
/*   80 */   private static final Log LOG = LogFactory.getLog(COSWriter.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   85 */   public static final byte[] DICT_OPEN = "<<".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*   89 */   public static final byte[] DICT_CLOSE = ">>".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*   93 */   public static final byte[] SPACE = new byte[] { 32 };
/*      */ 
/*      */ 
/*      */   
/*   97 */   public static final byte[] COMMENT = new byte[] { 37 };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  102 */   public static final byte[] VERSION = "PDF-1.4".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  106 */   public static final byte[] GARBAGE = new byte[] { -10, -28, -4, -33 };
/*      */ 
/*      */ 
/*      */   
/*  110 */   public static final byte[] EOF = "%%EOF".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  116 */   public static final byte[] REFERENCE = "R".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  120 */   public static final byte[] XREF = "xref".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  124 */   public static final byte[] XREF_FREE = "f".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  128 */   public static final byte[] XREF_USED = "n".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  132 */   public static final byte[] TRAILER = "trailer".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  136 */   public static final byte[] STARTXREF = "startxref".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  140 */   public static final byte[] OBJ = "obj".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  144 */   public static final byte[] ENDOBJ = "endobj".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  148 */   public static final byte[] ARRAY_OPEN = "[".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  152 */   public static final byte[] ARRAY_CLOSE = "]".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  156 */   public static final byte[] STREAM = "stream".getBytes(Charsets.US_ASCII);
/*      */ 
/*      */ 
/*      */   
/*  160 */   public static final byte[] ENDSTREAM = "endstream".getBytes(Charsets.US_ASCII);
/*      */   
/*  162 */   private final NumberFormat formatXrefOffset = new DecimalFormat("0000000000", 
/*  163 */       DecimalFormatSymbols.getInstance(Locale.US));
/*      */ 
/*      */   
/*  166 */   private final NumberFormat formatXrefGeneration = new DecimalFormat("00000", 
/*  167 */       DecimalFormatSymbols.getInstance(Locale.US));
/*      */ 
/*      */   
/*      */   private OutputStream output;
/*      */ 
/*      */   
/*      */   private COSStandardOutputStream standardOutput;
/*      */ 
/*      */   
/*  176 */   private long startxref = 0L;
/*      */ 
/*      */   
/*  179 */   private long number = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  185 */   private final Map<COSBase, COSObjectKey> objectKeys = new Hashtable<COSBase, COSObjectKey>();
/*  186 */   private final Map<COSObjectKey, COSBase> keyObject = new Hashtable<COSObjectKey, COSBase>();
/*      */ 
/*      */   
/*  189 */   private final List<COSWriterXRefEntry> xRefEntries = new ArrayList<COSWriterXRefEntry>();
/*  190 */   private final Set<COSBase> objectsToWriteSet = new HashSet<COSBase>();
/*      */ 
/*      */   
/*  193 */   private final Deque<COSBase> objectsToWrite = new LinkedList<COSBase>();
/*      */ 
/*      */   
/*  196 */   private final Set<COSBase> writtenObjects = new HashSet<COSBase>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  204 */   private final Set<COSBase> actualsAdded = new HashSet<COSBase>();
/*      */   
/*  206 */   private COSObjectKey currentObjectKey = null;
/*  207 */   private PDDocument pdDocument = null;
/*  208 */   private FDFDocument fdfDocument = null;
/*      */   
/*      */   private boolean willEncrypt = false;
/*      */   
/*      */   private boolean incrementalUpdate = false;
/*      */   
/*      */   private boolean reachedSignature = false;
/*      */   
/*      */   private long signatureOffset;
/*      */   
/*      */   private long signatureLength;
/*      */   
/*      */   private long byteRangeOffset;
/*      */   
/*      */   private long byteRangeLength;
/*      */   private RandomAccessRead incrementalInput;
/*      */   private OutputStream incrementalOutput;
/*      */   private SignatureInterface signatureInterface;
/*      */   private byte[] incrementPart;
/*      */   private COSArray byteRangeArray;
/*      */   
/*      */   public COSWriter(OutputStream outputStream) {
/*  230 */     setOutput(outputStream);
/*  231 */     setStandardOutput(new COSStandardOutputStream(this.output));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSWriter(OutputStream outputStream, RandomAccessRead inputData) throws IOException {
/*  248 */     setOutput(new ByteArrayOutputStream());
/*  249 */     setStandardOutput(new COSStandardOutputStream(this.output, inputData.length()));
/*      */     
/*  251 */     this.incrementalInput = inputData;
/*  252 */     this.incrementalOutput = outputStream;
/*  253 */     this.incrementalUpdate = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareIncrement(PDDocument doc) {
/*      */     try {
/*  260 */       if (doc != null)
/*      */       {
/*  262 */         COSDocument cosDoc = doc.getDocument();
/*      */         
/*  264 */         Map<COSObjectKey, Long> xrefTable = cosDoc.getXrefTable();
/*  265 */         Set<COSObjectKey> keySet = xrefTable.keySet();
/*  266 */         long highestNumber = doc.getDocument().getHighestXRefObjectNumber();
/*  267 */         for (COSObjectKey cosObjectKey : keySet) {
/*      */           
/*  269 */           COSBase object = cosDoc.getObjectFromPool(cosObjectKey).getObject();
/*  270 */           if (object != null && cosObjectKey != null && !(object instanceof org.apache.pdfbox.cos.COSNumber)) {
/*      */             
/*  272 */             this.objectKeys.put(object, cosObjectKey);
/*  273 */             this.keyObject.put(cosObjectKey, object);
/*      */           } 
/*      */           
/*  276 */           if (cosObjectKey != null) {
/*      */             
/*  278 */             long num = cosObjectKey.getNumber();
/*  279 */             if (num > highestNumber)
/*      */             {
/*  281 */               highestNumber = num;
/*      */             }
/*      */           } 
/*      */         } 
/*  285 */         setNumber(highestNumber);
/*      */       }
/*      */     
/*  288 */     } catch (IOException e) {
/*      */       
/*  290 */       LOG.error(e, e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addXRefEntry(COSWriterXRefEntry entry) {
/*  301 */     getXRefEntries().add(entry);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws IOException {
/*  312 */     if (getStandardOutput() != null)
/*      */     {
/*  314 */       getStandardOutput().close();
/*      */     }
/*  316 */     if (this.incrementalOutput != null)
/*      */     {
/*  318 */       this.incrementalOutput.close();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long getNumber() {
/*  329 */     return this.number;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<COSBase, COSObjectKey> getObjectKeys() {
/*  339 */     return this.objectKeys;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected OutputStream getOutput() {
/*  349 */     return this.output;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected COSStandardOutputStream getStandardOutput() {
/*  359 */     return this.standardOutput;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long getStartxref() {
/*  369 */     return this.startxref;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<COSWriterXRefEntry> getXRefEntries() {
/*  378 */     return this.xRefEntries;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setNumber(long newNumber) {
/*  388 */     this.number = newNumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setOutput(OutputStream newOutput) {
/*  398 */     this.output = newOutput;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setStandardOutput(COSStandardOutputStream newStandardOutput) {
/*  408 */     this.standardOutput = newStandardOutput;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setStartxref(long newStartxref) {
/*  418 */     this.startxref = newStartxref;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doWriteBody(COSDocument doc) throws IOException {
/*  430 */     COSDictionary trailer = doc.getTrailer();
/*  431 */     COSDictionary root = trailer.getCOSDictionary(COSName.ROOT);
/*  432 */     COSDictionary info = trailer.getCOSDictionary(COSName.INFO);
/*  433 */     COSDictionary encrypt = trailer.getCOSDictionary(COSName.ENCRYPT);
/*  434 */     if (root != null)
/*      */     {
/*  436 */       addObjectToWrite((COSBase)root);
/*      */     }
/*  438 */     if (info != null)
/*      */     {
/*  440 */       addObjectToWrite((COSBase)info);
/*      */     }
/*      */     
/*  443 */     doWriteObjects();
/*  444 */     this.willEncrypt = false;
/*  445 */     if (encrypt != null)
/*      */     {
/*  447 */       addObjectToWrite((COSBase)encrypt);
/*      */     }
/*      */     
/*  450 */     doWriteObjects();
/*      */   }
/*      */ 
/*      */   
/*      */   private void doWriteObjects() throws IOException {
/*  455 */     while (this.objectsToWrite.size() > 0) {
/*      */       
/*  457 */       COSBase nextObject = this.objectsToWrite.removeFirst();
/*  458 */       this.objectsToWriteSet.remove(nextObject);
/*  459 */       doWriteObject(nextObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void addObjectToWrite(COSBase object) {
/*  465 */     COSBase actual = object;
/*  466 */     if (actual instanceof COSObject)
/*      */     {
/*  468 */       actual = ((COSObject)actual).getObject();
/*      */     }
/*      */     
/*  471 */     if (!this.writtenObjects.contains(object) && 
/*  472 */       !this.objectsToWriteSet.contains(object) && 
/*  473 */       !this.actualsAdded.contains(actual)) {
/*      */       
/*  475 */       COSBase cosBase = null;
/*  476 */       COSObjectKey cosObjectKey = null;
/*  477 */       if (actual != null)
/*      */       {
/*  479 */         cosObjectKey = this.objectKeys.get(actual);
/*      */       }
/*  481 */       if (cosObjectKey != null)
/*      */       {
/*  483 */         cosBase = this.keyObject.get(cosObjectKey);
/*      */       }
/*  485 */       if (actual != null && this.objectKeys.containsKey(actual) && object instanceof COSUpdateInfo && 
/*  486 */         !((COSUpdateInfo)object).isNeedToBeUpdated() && cosBase instanceof COSUpdateInfo && 
/*  487 */         !((COSUpdateInfo)cosBase).isNeedToBeUpdated()) {
/*      */         return;
/*      */       }
/*      */       
/*  491 */       this.objectsToWrite.add(object);
/*  492 */       this.objectsToWriteSet.add(object);
/*  493 */       if (actual != null)
/*      */       {
/*  495 */         this.actualsAdded.add(actual);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void doWriteObject(COSBase obj) throws IOException {
/*  509 */     this.writtenObjects.add(obj);
/*      */     
/*  511 */     this.currentObjectKey = getObjectKey(obj);
/*      */     
/*  513 */     addXRefEntry(new COSWriterXRefEntry(getStandardOutput().getPos(), obj, this.currentObjectKey));
/*      */     
/*  515 */     getStandardOutput().write(String.valueOf(this.currentObjectKey.getNumber()).getBytes(Charsets.ISO_8859_1));
/*  516 */     getStandardOutput().write(SPACE);
/*  517 */     getStandardOutput().write(String.valueOf(this.currentObjectKey.getGeneration()).getBytes(Charsets.ISO_8859_1));
/*  518 */     getStandardOutput().write(SPACE);
/*  519 */     getStandardOutput().write(OBJ);
/*  520 */     getStandardOutput().writeEOL();
/*  521 */     obj.accept(this);
/*  522 */     getStandardOutput().writeEOL();
/*  523 */     getStandardOutput().write(ENDOBJ);
/*  524 */     getStandardOutput().writeEOL();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doWriteHeader(COSDocument doc) throws IOException {
/*      */     String headerString;
/*  537 */     if (this.fdfDocument != null) {
/*      */       
/*  539 */       headerString = "%FDF-" + Float.toString(doc.getVersion());
/*      */     }
/*      */     else {
/*      */       
/*  543 */       headerString = "%PDF-" + Float.toString(doc.getVersion());
/*      */     } 
/*  545 */     getStandardOutput().write(headerString.getBytes(Charsets.ISO_8859_1));
/*      */     
/*  547 */     getStandardOutput().writeEOL();
/*  548 */     getStandardOutput().write(COMMENT);
/*  549 */     getStandardOutput().write(GARBAGE);
/*  550 */     getStandardOutput().writeEOL();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doWriteTrailer(COSDocument doc) throws IOException {
/*  563 */     getStandardOutput().write(TRAILER);
/*  564 */     getStandardOutput().writeEOL();
/*      */     
/*  566 */     COSDictionary trailer = doc.getTrailer();
/*      */     
/*  568 */     Collections.sort(getXRefEntries());
/*  569 */     COSWriterXRefEntry lastEntry = getXRefEntries().get(getXRefEntries().size() - 1);
/*  570 */     trailer.setLong(COSName.SIZE, lastEntry.getKey().getNumber() + 1L);
/*      */     
/*  572 */     if (!this.incrementalUpdate)
/*      */     {
/*  574 */       trailer.removeItem(COSName.PREV);
/*      */     }
/*  576 */     if (!doc.isXRefStream())
/*      */     {
/*  578 */       trailer.removeItem(COSName.XREF_STM);
/*      */     }
/*      */     
/*  581 */     trailer.removeItem(COSName.DOC_CHECKSUM);
/*      */     
/*  583 */     COSArray idArray = trailer.getCOSArray(COSName.ID);
/*  584 */     if (idArray != null)
/*      */     {
/*  586 */       idArray.setDirect(true);
/*      */     }
/*      */     
/*  589 */     trailer.accept(this);
/*      */   }
/*      */ 
/*      */   
/*      */   private void doWriteXRefInc(COSDocument doc, long hybridPrev) throws IOException {
/*  594 */     if (doc.isXRefStream() || hybridPrev != -1L) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  601 */       PDFXRefStream pdfxRefStream = new PDFXRefStream(doc);
/*      */ 
/*      */       
/*  604 */       List<COSWriterXRefEntry> xRefEntries2 = getXRefEntries();
/*  605 */       for (COSWriterXRefEntry cosWriterXRefEntry : xRefEntries2)
/*      */       {
/*  607 */         pdfxRefStream.addEntry(cosWriterXRefEntry);
/*      */       }
/*      */       
/*  610 */       COSDictionary trailer = doc.getTrailer();
/*  611 */       if (this.incrementalUpdate) {
/*      */ 
/*      */         
/*  614 */         trailer.setLong(COSName.PREV, doc.getStartXref());
/*      */       }
/*      */       else {
/*      */         
/*  618 */         trailer.removeItem(COSName.PREV);
/*      */       } 
/*  620 */       pdfxRefStream.addTrailerInfo(trailer);
/*      */ 
/*      */       
/*  623 */       pdfxRefStream.setSize(getNumber() + 2L);
/*      */       
/*  625 */       setStartxref(getStandardOutput().getPos());
/*  626 */       COSStream stream2 = pdfxRefStream.getStream();
/*  627 */       doWriteObject((COSBase)stream2);
/*      */     } 
/*      */     
/*  630 */     if (!doc.isXRefStream() || hybridPrev != -1L) {
/*      */       
/*  632 */       COSDictionary trailer = doc.getTrailer();
/*  633 */       trailer.setLong(COSName.PREV, doc.getStartXref());
/*  634 */       if (hybridPrev != -1L) {
/*      */         
/*  636 */         COSName xrefStm = COSName.XREF_STM;
/*  637 */         trailer.removeItem(xrefStm);
/*  638 */         trailer.setLong(xrefStm, getStartxref());
/*      */       } 
/*  640 */       doWriteXRefTable();
/*  641 */       doWriteTrailer(doc);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void doWriteXRefTable() throws IOException {
/*  648 */     addXRefEntry(COSWriterXRefEntry.getNullEntry());
/*      */ 
/*      */     
/*  651 */     Collections.sort(getXRefEntries());
/*      */ 
/*      */     
/*  654 */     setStartxref(getStandardOutput().getPos());
/*      */     
/*  656 */     getStandardOutput().write(XREF);
/*  657 */     getStandardOutput().writeEOL();
/*      */ 
/*      */ 
/*      */     
/*  661 */     Long[] xRefRanges = getXRefRanges(getXRefEntries());
/*  662 */     int xRefLength = xRefRanges.length;
/*  663 */     int x = 0;
/*  664 */     int j = 0;
/*  665 */     while (x < xRefLength && xRefLength % 2 == 0) {
/*      */       
/*  667 */       writeXrefRange(xRefRanges[x].longValue(), xRefRanges[x + 1].longValue());
/*      */       
/*  669 */       for (int i = 0; i < xRefRanges[x + 1].longValue(); i++)
/*      */       {
/*  671 */         writeXrefEntry(this.xRefEntries.get(j++));
/*      */       }
/*  673 */       x += 2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doWriteIncrement() throws IOException {
/*  686 */     IOUtils.copy((InputStream)new RandomAccessInputStream(this.incrementalInput), this.incrementalOutput);
/*      */     
/*  688 */     this.incrementalOutput.write(((ByteArrayOutputStream)this.output).toByteArray());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void doWriteSignature() throws IOException {
/*  694 */     long inLength = this.incrementalInput.length();
/*  695 */     long beforeLength = this.signatureOffset;
/*  696 */     long afterOffset = this.signatureOffset + this.signatureLength;
/*  697 */     long afterLength = getStandardOutput().getPos() - inLength + this.signatureLength - this.signatureOffset - inLength;
/*      */     
/*  699 */     String byteRange = "0 " + beforeLength + " " + afterOffset + " " + afterLength + "]";
/*      */ 
/*      */     
/*  702 */     this.byteRangeArray.set(0, (COSBase)COSInteger.ZERO);
/*  703 */     this.byteRangeArray.set(1, (COSBase)COSInteger.get(beforeLength));
/*  704 */     this.byteRangeArray.set(2, (COSBase)COSInteger.get(afterOffset));
/*  705 */     this.byteRangeArray.set(3, (COSBase)COSInteger.get(afterLength));
/*      */     
/*  707 */     if (byteRange.length() > this.byteRangeLength)
/*      */     {
/*  709 */       throw new IOException("Can't write new byteRange '" + byteRange + "' not enough space: byteRange.length(): " + byteRange
/*  710 */           .length() + ", byteRangeLength: " + this.byteRangeLength);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  715 */     ByteArrayOutputStream byteOut = (ByteArrayOutputStream)this.output;
/*  716 */     byteOut.flush();
/*  717 */     this.incrementPart = byteOut.toByteArray();
/*      */ 
/*      */     
/*  720 */     byte[] byteRangeBytes = byteRange.getBytes(Charsets.ISO_8859_1);
/*  721 */     for (int i = 0; i < this.byteRangeLength; i++) {
/*      */       
/*  723 */       if (i >= byteRangeBytes.length) {
/*      */         
/*  725 */         this.incrementPart[(int)(this.byteRangeOffset + i - inLength)] = 32;
/*      */       }
/*      */       else {
/*      */         
/*  729 */         this.incrementPart[(int)(this.byteRangeOffset + i - inLength)] = byteRangeBytes[i];
/*      */       } 
/*      */     } 
/*      */     
/*  733 */     if (this.signatureInterface != null) {
/*      */ 
/*      */       
/*  736 */       InputStream dataToSign = getDataToSign();
/*      */ 
/*      */       
/*  739 */       byte[] signatureBytes = this.signatureInterface.sign(dataToSign);
/*  740 */       writeExternalSignature(signatureBytes);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InputStream getDataToSign() throws IOException {
/*  759 */     if (this.incrementPart == null || this.incrementalInput == null)
/*      */     {
/*  761 */       throw new IllegalStateException("PDF not prepared for signing");
/*      */     }
/*      */     
/*  764 */     int incPartSigOffset = (int)(this.signatureOffset - this.incrementalInput.length());
/*  765 */     int afterSigOffset = incPartSigOffset + (int)this.signatureLength;
/*  766 */     int[] range = { 0, incPartSigOffset, afterSigOffset, this.incrementPart.length - afterSigOffset };
/*      */ 
/*      */     
/*  769 */     return new SequenceInputStream((InputStream)new RandomAccessInputStream(this.incrementalInput), (InputStream)new COSFilterInputStream(this.incrementPart, range));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeExternalSignature(byte[] cmsSignature) throws IOException {
/*  783 */     if (this.incrementPart == null || this.incrementalInput == null)
/*      */     {
/*  785 */       throw new IllegalStateException("PDF not prepared for setting signature");
/*      */     }
/*  787 */     byte[] signatureBytes = Hex.getBytes(cmsSignature);
/*      */ 
/*      */     
/*  790 */     if (signatureBytes.length > this.signatureLength - 2L)
/*      */     {
/*  792 */       throw new IOException("Can't write signature, not enough space");
/*      */     }
/*      */ 
/*      */     
/*  796 */     int incPartSigOffset = (int)(this.signatureOffset - this.incrementalInput.length());
/*  797 */     System.arraycopy(signatureBytes, 0, this.incrementPart, incPartSigOffset + 1, signatureBytes.length);
/*      */ 
/*      */     
/*  800 */     IOUtils.copy((InputStream)new RandomAccessInputStream(this.incrementalInput), this.incrementalOutput);
/*  801 */     this.incrementalOutput.write(this.incrementPart);
/*      */ 
/*      */     
/*  804 */     this.incrementPart = null;
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeXrefRange(long x, long y) throws IOException {
/*  809 */     getStandardOutput().write(String.valueOf(x).getBytes(Charsets.ISO_8859_1));
/*  810 */     getStandardOutput().write(SPACE);
/*  811 */     getStandardOutput().write(String.valueOf(y).getBytes(Charsets.ISO_8859_1));
/*  812 */     getStandardOutput().writeEOL();
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeXrefEntry(COSWriterXRefEntry entry) throws IOException {
/*  817 */     String offset = this.formatXrefOffset.format(entry.getOffset());
/*  818 */     String generation = this.formatXrefGeneration.format(entry.getKey().getGeneration());
/*  819 */     getStandardOutput().write(offset.getBytes(Charsets.ISO_8859_1));
/*  820 */     getStandardOutput().write(SPACE);
/*  821 */     getStandardOutput().write(generation.getBytes(Charsets.ISO_8859_1));
/*  822 */     getStandardOutput().write(SPACE);
/*  823 */     getStandardOutput().write(entry.isFree() ? XREF_FREE : XREF_USED);
/*  824 */     getStandardOutput().writeCRLF();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Long[] getXRefRanges(List<COSWriterXRefEntry> xRefEntriesList) {
/*  847 */     long last = -2L;
/*  848 */     long count = 1L;
/*      */     
/*  850 */     List<Long> list = new ArrayList<Long>();
/*  851 */     for (COSWriterXRefEntry object : xRefEntriesList) {
/*      */       
/*  853 */       long nr = (int)((COSWriterXRefEntry)object).getKey().getNumber();
/*  854 */       if (nr == last + 1L) {
/*      */         
/*  856 */         count++;
/*  857 */         last = nr; continue;
/*      */       } 
/*  859 */       if (last == -2L) {
/*      */         
/*  861 */         last = nr;
/*      */         
/*      */         continue;
/*      */       } 
/*  865 */       list.add(Long.valueOf(last - count + 1L));
/*  866 */       list.add(Long.valueOf(count));
/*  867 */       last = nr;
/*  868 */       count = 1L;
/*      */     } 
/*      */ 
/*      */     
/*  872 */     if (xRefEntriesList.size() > 0) {
/*      */       
/*  874 */       list.add(Long.valueOf(last - count + 1L));
/*  875 */       list.add(Long.valueOf(count));
/*      */     } 
/*  877 */     return list.<Long>toArray(new Long[list.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private COSObjectKey getObjectKey(COSBase obj) {
/*  889 */     COSBase actual = obj;
/*  890 */     if (actual instanceof COSObject)
/*      */     {
/*  892 */       actual = ((COSObject)obj).getObject();
/*      */     }
/*      */ 
/*      */     
/*  896 */     COSObjectKey key = this.objectKeys.get(obj);
/*  897 */     if (key == null && actual != null)
/*      */     {
/*  899 */       key = this.objectKeys.get(actual);
/*      */     }
/*  901 */     if (key == null) {
/*      */       
/*  903 */       setNumber(getNumber() + 1L);
/*  904 */       key = new COSObjectKey(getNumber(), 0);
/*  905 */       this.objectKeys.put(obj, key);
/*  906 */       if (actual != null)
/*      */       {
/*  908 */         this.objectKeys.put(actual, key);
/*      */       }
/*      */     } 
/*  911 */     return key;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromArray(COSArray obj) throws IOException {
/*  917 */     int count = 0;
/*  918 */     getStandardOutput().write(ARRAY_OPEN);
/*  919 */     for (Iterator<COSBase> i = obj.iterator(); i.hasNext(); ) {
/*      */       
/*  921 */       COSBase current = i.next();
/*  922 */       if (current instanceof COSDictionary) {
/*      */         
/*  924 */         if (current.isDirect())
/*      */         {
/*  926 */           visitFromDictionary((COSDictionary)current);
/*      */         }
/*      */         else
/*      */         {
/*  930 */           addObjectToWrite(current);
/*  931 */           writeReference(current);
/*      */         }
/*      */       
/*  934 */       } else if (current instanceof COSObject) {
/*      */         
/*  936 */         COSBase subValue = ((COSObject)current).getObject();
/*  937 */         if (this.willEncrypt || this.incrementalUpdate || subValue instanceof COSDictionary || subValue == null)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  943 */           addObjectToWrite(current);
/*  944 */           writeReference(current);
/*      */         }
/*      */         else
/*      */         {
/*  948 */           subValue.accept(this);
/*      */         }
/*      */       
/*  951 */       } else if (current == null) {
/*      */         
/*  953 */         COSNull.NULL.accept(this);
/*      */       }
/*      */       else {
/*      */         
/*  957 */         current.accept(this);
/*      */       } 
/*  959 */       count++;
/*  960 */       if (i.hasNext()) {
/*      */         
/*  962 */         if (count % 10 == 0) {
/*      */           
/*  964 */           getStandardOutput().writeEOL();
/*      */           
/*      */           continue;
/*      */         } 
/*  968 */         getStandardOutput().write(SPACE);
/*      */       } 
/*      */     } 
/*      */     
/*  972 */     getStandardOutput().write(ARRAY_CLOSE);
/*  973 */     getStandardOutput().writeEOL();
/*  974 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromBoolean(COSBoolean obj) throws IOException {
/*  980 */     obj.writePDF(getStandardOutput());
/*  981 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromDictionary(COSDictionary obj) throws IOException {
/*  987 */     if (!this.reachedSignature) {
/*      */       
/*  989 */       COSBase itemType = obj.getItem(COSName.TYPE);
/*  990 */       if (COSName.SIG.equals(itemType) || COSName.DOC_TIME_STAMP.equals(itemType))
/*      */       {
/*  992 */         this.reachedSignature = true;
/*      */       }
/*      */     } 
/*  995 */     getStandardOutput().write(DICT_OPEN);
/*  996 */     getStandardOutput().writeEOL();
/*  997 */     for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)obj.entrySet()) {
/*      */       
/*  999 */       COSBase value = entry.getValue();
/* 1000 */       if (value != null) {
/*      */         
/* 1002 */         ((COSName)entry.getKey()).accept(this);
/* 1003 */         getStandardOutput().write(SPACE);
/* 1004 */         if (value instanceof COSDictionary) {
/*      */           
/* 1006 */           COSDictionary dict = (COSDictionary)value;
/*      */           
/* 1008 */           if (!this.incrementalUpdate) {
/*      */ 
/*      */ 
/*      */             
/* 1012 */             COSBase item = dict.getItem(COSName.XOBJECT);
/* 1013 */             if (item != null && !COSName.XOBJECT.equals(entry.getKey()))
/*      */             {
/* 1015 */               item.setDirect(true);
/*      */             }
/* 1017 */             item = dict.getItem(COSName.RESOURCES);
/* 1018 */             if (item != null && !COSName.RESOURCES.equals(entry.getKey()))
/*      */             {
/* 1020 */               item.setDirect(true);
/*      */             }
/*      */           } 
/*      */           
/* 1024 */           if (dict.isDirect())
/*      */           {
/*      */ 
/*      */             
/* 1028 */             visitFromDictionary(dict);
/*      */           }
/*      */           else
/*      */           {
/* 1032 */             addObjectToWrite((COSBase)dict);
/* 1033 */             writeReference((COSBase)dict);
/*      */           }
/*      */         
/* 1036 */         } else if (value instanceof COSObject) {
/*      */           
/* 1038 */           COSBase subValue = ((COSObject)value).getObject();
/* 1039 */           if (this.willEncrypt || this.incrementalUpdate || subValue instanceof COSDictionary || subValue == null)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1045 */             addObjectToWrite(value);
/* 1046 */             writeReference(value);
/*      */           }
/*      */           else
/*      */           {
/* 1050 */             subValue.accept(this);
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/* 1057 */         else if (this.reachedSignature && COSName.CONTENTS.equals(entry.getKey())) {
/*      */           
/* 1059 */           this.signatureOffset = getStandardOutput().getPos();
/* 1060 */           value.accept(this);
/* 1061 */           this.signatureLength = getStandardOutput().getPos() - this.signatureOffset;
/*      */         }
/* 1063 */         else if (this.reachedSignature && COSName.BYTERANGE.equals(entry.getKey())) {
/*      */           
/* 1065 */           this.byteRangeArray = (COSArray)entry.getValue();
/* 1066 */           this.byteRangeOffset = getStandardOutput().getPos() + 1L;
/* 1067 */           value.accept(this);
/* 1068 */           this.byteRangeLength = getStandardOutput().getPos() - 1L - this.byteRangeOffset;
/* 1069 */           this.reachedSignature = false;
/*      */         }
/*      */         else {
/*      */           
/* 1073 */           value.accept(this);
/*      */         } 
/*      */         
/* 1076 */         getStandardOutput().writeEOL();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1087 */     getStandardOutput().write(DICT_CLOSE);
/* 1088 */     getStandardOutput().writeEOL();
/* 1089 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromDocument(COSDocument doc) throws IOException {
/* 1095 */     if (!this.incrementalUpdate) {
/*      */       
/* 1097 */       doWriteHeader(doc);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 1105 */       getStandardOutput().writeCRLF();
/*      */     } 
/*      */     
/* 1108 */     doWriteBody(doc);
/*      */ 
/*      */     
/* 1111 */     COSDictionary trailer = doc.getTrailer();
/* 1112 */     long hybridPrev = -1L;
/*      */     
/* 1114 */     if (trailer != null)
/*      */     {
/* 1116 */       hybridPrev = trailer.getLong(COSName.XREF_STM);
/*      */     }
/*      */     
/* 1119 */     if (this.incrementalUpdate || doc.isXRefStream()) {
/*      */       
/* 1121 */       doWriteXRefInc(doc, hybridPrev);
/*      */     }
/*      */     else {
/*      */       
/* 1125 */       doWriteXRefTable();
/* 1126 */       doWriteTrailer(doc);
/*      */     } 
/*      */ 
/*      */     
/* 1130 */     getStandardOutput().write(STARTXREF);
/* 1131 */     getStandardOutput().writeEOL();
/* 1132 */     getStandardOutput().write(String.valueOf(getStartxref()).getBytes(Charsets.ISO_8859_1));
/* 1133 */     getStandardOutput().writeEOL();
/* 1134 */     getStandardOutput().write(EOF);
/* 1135 */     getStandardOutput().writeEOL();
/*      */     
/* 1137 */     if (this.incrementalUpdate)
/*      */     {
/* 1139 */       if (this.signatureOffset == 0L || this.byteRangeOffset == 0L) {
/*      */         
/* 1141 */         doWriteIncrement();
/*      */       }
/*      */       else {
/*      */         
/* 1145 */         doWriteSignature();
/*      */       } 
/*      */     }
/*      */     
/* 1149 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromFloat(COSFloat obj) throws IOException {
/* 1155 */     obj.writePDF(getStandardOutput());
/* 1156 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromInt(COSInteger obj) throws IOException {
/* 1162 */     obj.writePDF(getStandardOutput());
/* 1163 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromName(COSName obj) throws IOException {
/* 1169 */     obj.writePDF(getStandardOutput());
/* 1170 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromNull(COSNull obj) throws IOException {
/* 1176 */     obj.writePDF(getStandardOutput());
/* 1177 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeReference(COSBase obj) throws IOException {
/* 1189 */     COSObjectKey key = getObjectKey(obj);
/* 1190 */     getStandardOutput().write(String.valueOf(key.getNumber()).getBytes(Charsets.ISO_8859_1));
/* 1191 */     getStandardOutput().write(SPACE);
/* 1192 */     getStandardOutput().write(String.valueOf(key.getGeneration()).getBytes(Charsets.ISO_8859_1));
/* 1193 */     getStandardOutput().write(SPACE);
/* 1194 */     getStandardOutput().write(REFERENCE);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromStream(COSStream obj) throws IOException {
/* 1200 */     if (this.willEncrypt)
/*      */     {
/* 1202 */       this.pdDocument.getEncryption().getSecurityHandler()
/* 1203 */         .encryptStream(obj, this.currentObjectKey.getNumber(), this.currentObjectKey.getGeneration());
/*      */     }
/*      */     
/* 1206 */     InputStream input = null;
/*      */ 
/*      */     
/*      */     try {
/* 1210 */       visitFromDictionary((COSDictionary)obj);
/* 1211 */       getStandardOutput().write(STREAM);
/* 1212 */       getStandardOutput().writeCRLF();
/*      */       
/* 1214 */       input = obj.createRawInputStream();
/* 1215 */       IOUtils.copy(input, getStandardOutput());
/*      */       
/* 1217 */       getStandardOutput().writeCRLF();
/* 1218 */       getStandardOutput().write(ENDSTREAM);
/* 1219 */       getStandardOutput().writeEOL();
/* 1220 */       return null;
/*      */     }
/*      */     finally {
/*      */       
/* 1224 */       if (input != null)
/*      */       {
/* 1226 */         input.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object visitFromString(COSString obj) throws IOException {
/* 1234 */     if (this.willEncrypt)
/*      */     {
/* 1236 */       this.pdDocument.getEncryption().getSecurityHandler().encryptString(obj, this.currentObjectKey
/*      */           
/* 1238 */           .getNumber(), this.currentObjectKey
/* 1239 */           .getGeneration());
/*      */     }
/* 1241 */     writeString(obj, getStandardOutput());
/* 1242 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(COSDocument doc) throws IOException {
/* 1253 */     PDDocument pdDoc = new PDDocument(doc);
/* 1254 */     write(pdDoc);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(PDDocument doc) throws IOException {
/* 1268 */     write(doc, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(PDDocument doc, SignatureInterface signInterface) throws IOException {
/* 1286 */     Long idTime = Long.valueOf((doc.getDocumentId() == null) ? System.currentTimeMillis() : doc
/* 1287 */         .getDocumentId().longValue());
/*      */     
/* 1289 */     this.pdDocument = doc;
/* 1290 */     this.signatureInterface = signInterface;
/*      */     
/* 1292 */     if (this.incrementalUpdate)
/*      */     {
/* 1294 */       prepareIncrement(doc);
/*      */     }
/*      */ 
/*      */     
/* 1298 */     if (doc.isAllSecurityToBeRemoved()) {
/*      */       
/* 1300 */       this.willEncrypt = false;
/*      */ 
/*      */       
/* 1303 */       COSDocument cOSDocument = doc.getDocument();
/* 1304 */       COSDictionary cOSDictionary = cOSDocument.getTrailer();
/* 1305 */       cOSDictionary.removeItem(COSName.ENCRYPT);
/*      */ 
/*      */     
/*      */     }
/* 1309 */     else if (this.pdDocument.getEncryption() != null) {
/*      */       
/* 1311 */       if (!this.incrementalUpdate) {
/*      */         
/* 1313 */         SecurityHandler securityHandler = this.pdDocument.getEncryption().getSecurityHandler();
/* 1314 */         if (!securityHandler.hasProtectionPolicy())
/*      */         {
/* 1316 */           throw new IllegalStateException("PDF contains an encryption dictionary, please remove it with setAllSecurityToBeRemoved() or set a protection policy with protect()");
/*      */         }
/*      */         
/* 1319 */         securityHandler.prepareDocumentForEncryption(this.pdDocument);
/*      */       } 
/* 1321 */       this.willEncrypt = true;
/*      */     }
/*      */     else {
/*      */       
/* 1325 */       this.willEncrypt = false;
/*      */     } 
/*      */ 
/*      */     
/* 1329 */     COSDocument cosDoc = this.pdDocument.getDocument();
/* 1330 */     COSDictionary trailer = cosDoc.getTrailer();
/* 1331 */     COSArray idArray = null;
/* 1332 */     boolean missingID = true;
/* 1333 */     COSBase base = trailer.getDictionaryObject(COSName.ID);
/* 1334 */     if (base instanceof COSArray) {
/*      */       
/* 1336 */       idArray = (COSArray)base;
/* 1337 */       if (idArray.size() == 2)
/*      */       {
/* 1339 */         missingID = false;
/*      */       }
/*      */     } 
/*      */     
/* 1343 */     if (idArray != null && idArray.size() == 2)
/*      */     {
/* 1345 */       missingID = false;
/*      */     }
/* 1347 */     if (missingID || this.incrementalUpdate) {
/*      */       MessageDigest md5;
/*      */ 
/*      */       
/*      */       try {
/* 1352 */         md5 = MessageDigest.getInstance("MD5");
/*      */       }
/* 1354 */       catch (NoSuchAlgorithmException e) {
/*      */ 
/*      */         
/* 1357 */         throw new RuntimeException(e);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1362 */       md5.update(Long.toString(idTime.longValue()).getBytes(Charsets.ISO_8859_1));
/*      */       
/* 1364 */       COSDictionary info = trailer.getCOSDictionary(COSName.INFO);
/* 1365 */       if (info != null)
/*      */       {
/* 1367 */         for (COSBase cosBase : info.getValues())
/*      */         {
/* 1369 */           md5.update(cosBase.toString().getBytes(Charsets.ISO_8859_1));
/*      */         }
/*      */       }
/*      */       
/* 1373 */       COSString firstID = missingID ? new COSString(md5.digest()) : (COSString)idArray.get(0);
/*      */       
/* 1375 */       COSString secondID = missingID ? firstID : new COSString(md5.digest());
/* 1376 */       idArray = new COSArray();
/* 1377 */       idArray.add((COSBase)firstID);
/* 1378 */       idArray.add((COSBase)secondID);
/* 1379 */       trailer.setItem(COSName.ID, (COSBase)idArray);
/*      */     } 
/* 1381 */     cosDoc.accept(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(FDFDocument doc) throws IOException {
/* 1393 */     this.fdfDocument = doc;
/* 1394 */     this.willEncrypt = false;
/* 1395 */     COSDocument cosDoc = this.fdfDocument.getDocument();
/* 1396 */     cosDoc.accept(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeString(COSString string, OutputStream output) throws IOException {
/* 1407 */     writeString(string.getBytes(), string.getForceHexForm(), output);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeString(byte[] bytes, OutputStream output) throws IOException {
/* 1419 */     writeString(bytes, false, output);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void writeString(byte[] bytes, boolean forceHex, OutputStream output) throws IOException {
/* 1432 */     boolean isASCII = true;
/* 1433 */     if (!forceHex)
/*      */     {
/* 1435 */       for (byte b : bytes) {
/*      */ 
/*      */         
/* 1438 */         if (b < 0) {
/*      */           
/* 1440 */           isASCII = false;
/*      */           
/*      */           break;
/*      */         } 
/* 1444 */         if (b == 13 || b == 10) {
/*      */           
/* 1446 */           isASCII = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/* 1452 */     if (isASCII && !forceHex) {
/*      */ 
/*      */       
/* 1455 */       output.write(40);
/* 1456 */       for (byte b : bytes) {
/*      */         
/* 1458 */         switch (b) {
/*      */           
/*      */           case 40:
/*      */           case 41:
/*      */           case 92:
/* 1463 */             output.write(92);
/* 1464 */             output.write(b);
/*      */             break;
/*      */           default:
/* 1467 */             output.write(b);
/*      */             break;
/*      */         } 
/*      */       } 
/* 1471 */       output.write(41);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1476 */       output.write(60);
/* 1477 */       Hex.writeHexBytes(bytes, output);
/* 1478 */       output.write(62);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfwriter/COSWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */