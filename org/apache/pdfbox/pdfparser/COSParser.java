/*      */ package org.apache.pdfbox.pdfparser;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.security.KeyStore;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Queue;
/*      */ import java.util.Set;
/*      */ import java.util.TreeMap;
/*      */ import java.util.Vector;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSDictionary;
/*      */ import org.apache.pdfbox.cos.COSDocument;
/*      */ import org.apache.pdfbox.cos.COSInputStream;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSNull;
/*      */ import org.apache.pdfbox.cos.COSNumber;
/*      */ import org.apache.pdfbox.cos.COSObject;
/*      */ import org.apache.pdfbox.cos.COSObjectKey;
/*      */ import org.apache.pdfbox.cos.COSStream;
/*      */ import org.apache.pdfbox.io.IOUtils;
/*      */ import org.apache.pdfbox.io.RandomAccessRead;
/*      */ import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
/*      */ import org.apache.pdfbox.pdmodel.encryption.DecryptionMaterial;
/*      */ import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
/*      */ import org.apache.pdfbox.pdmodel.encryption.PDEncryption;
/*      */ import org.apache.pdfbox.pdmodel.encryption.PublicKeyDecryptionMaterial;
/*      */ import org.apache.pdfbox.pdmodel.encryption.SecurityHandler;
/*      */ import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
/*      */ import org.apache.pdfbox.util.Charsets;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class COSParser
/*      */   extends BaseParser
/*      */ {
/*      */   private static final String PDF_HEADER = "%PDF-";
/*      */   private static final String FDF_HEADER = "%FDF-";
/*      */   private static final String PDF_DEFAULT_VERSION = "1.4";
/*      */   private static final String FDF_DEFAULT_VERSION = "1.0";
/*   80 */   private static final char[] XREF_TABLE = new char[] { 'x', 'r', 'e', 'f' };
/*   81 */   private static final char[] XREF_STREAM = new char[] { '/', 'X', 'R', 'e', 'f' };
/*   82 */   private static final char[] STARTXREF = new char[] { 's', 't', 'a', 'r', 't', 'x', 'r', 'e', 'f' };
/*      */   
/*   84 */   private static final byte[] ENDSTREAM = new byte[] { 101, 110, 100, 115, 116, 114, 101, 97, 109 };
/*      */   
/*   86 */   private static final byte[] ENDOBJ = new byte[] { 101, 110, 100, 111, 98, 106 };
/*      */   
/*      */   private static final long MINIMUM_SEARCH_OFFSET = 6L;
/*      */   
/*      */   private static final int X = 120;
/*      */   
/*      */   private static final int STRMBUFLEN = 2048;
/*   93 */   private final byte[] strmBuf = new byte[2048];
/*      */   
/*      */   protected final RandomAccessRead source;
/*      */   
/*      */   private AccessPermission accessPermission;
/*   98 */   private InputStream keyStoreInputStream = null;
/*   99 */   private String password = "";
/*  100 */   private String keyAlias = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String SYSPROP_PARSEMINIMAL = "org.apache.pdfbox.pdfparser.nonSequentialPDFParser.parseMinimal";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String SYSPROP_EOFLOOKUPRANGE = "org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int DEFAULT_TRAIL_BYTECOUNT = 2048;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  122 */   protected static final char[] EOF_MARKER = new char[] { '%', '%', 'E', 'O', 'F' };
/*      */ 
/*      */ 
/*      */   
/*  126 */   protected static final char[] OBJ_MARKER = new char[] { 'o', 'b', 'j' };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  131 */   private static final char[] TRAILER_MARKER = new char[] { 't', 'r', 'a', 'i', 'l', 'e', 'r' };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  136 */   private static final char[] OBJ_STREAM = new char[] { '/', 'O', 'b', 'j', 'S', 't', 'm' };
/*      */ 
/*      */ 
/*      */   
/*      */   private long trailerOffset;
/*      */ 
/*      */ 
/*      */   
/*      */   protected long fileLen;
/*      */ 
/*      */   
/*      */   private boolean isLenient = true;
/*      */ 
/*      */   
/*      */   protected boolean initialParseDone = false;
/*      */ 
/*      */   
/*      */   private boolean trailerWasRebuild = false;
/*      */ 
/*      */   
/*  156 */   private Map<COSObjectKey, Long> bfSearchCOSObjectKeyOffsets = null;
/*  157 */   private Long lastEOFMarker = null;
/*  158 */   private List<Long> bfSearchXRefTablesOffsets = null;
/*  159 */   private List<Long> bfSearchXRefStreamsOffsets = null;
/*  160 */   private PDEncryption encryption = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  165 */   protected SecurityHandler securityHandler = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  170 */   private int readTrailBytes = 2048;
/*      */   
/*  172 */   private static final Log LOG = LogFactory.getLog(COSParser.class);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  178 */   protected XrefTrailerResolver xrefTrailerResolver = new XrefTrailerResolver();
/*      */ 
/*      */   
/*      */   public static final String TMP_FILE_PREFIX = "tmpPDF";
/*      */ 
/*      */   
/*      */   private static final int STREAMCOPYBUFLEN = 8192;
/*      */ 
/*      */   
/*      */   private final byte[] streamCopyBuf;
/*      */ 
/*      */ 
/*      */   
/*      */   public COSParser(RandomAccessRead source)
/*      */   {
/*  193 */     super(new RandomAccessSource(source));
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1086 */     this.streamCopyBuf = new byte[8192]; this.source = source; } public void setEOFLookupRange(int byteCount) { if (byteCount > 15) this.readTrailBytes = byteCount;  } protected COSDictionary retrieveTrailer() throws IOException { COSDictionary trailer = null; boolean rebuildTrailer = false; try { long startXRefOffset = getStartxrefOffset(); if (startXRefOffset > -1L) { trailer = parseXref(startXRefOffset); } else { rebuildTrailer = isLenient(); }  } catch (IOException exception) { if (isLenient()) { rebuildTrailer = true; } else { throw exception; }  }  if (trailer != null && trailer.getItem(COSName.ROOT) == null) rebuildTrailer = isLenient();  if (rebuildTrailer) { trailer = rebuildTrailer(); } else { prepareDecryption(); if (this.bfSearchCOSObjectKeyOffsets != null && !this.bfSearchCOSObjectKeyOffsets.isEmpty()) bfSearchForObjStreams();  }  return trailer; } protected COSDictionary parseXref(long startXRefOffset) throws IOException { this.source.seek(startXRefOffset); long startXrefOffset = Math.max(0L, parseStartXref()); long fixedOffset = checkXRefOffset(startXrefOffset); if (fixedOffset > -1L) startXrefOffset = fixedOffset;  this.document.setStartXref(startXrefOffset); long prev = startXrefOffset; Set<Long> prevSet = new HashSet<Long>(); while (prev > 0L) { this.source.seek(prev); skipSpaces(); if (this.source.peek() == 120) { if (!parseXrefTable(prev) || !parseTrailer()) throw new IOException("Expected trailer object at offset " + this.source.getPosition());  COSDictionary cOSDictionary = this.xrefTrailerResolver.getCurrentTrailer(); if (cOSDictionary.containsKey(COSName.XREF_STM)) { int streamOffset = cOSDictionary.getInt(COSName.XREF_STM); fixedOffset = checkXRefOffset(streamOffset); if (fixedOffset > -1L && fixedOffset != streamOffset) { LOG.warn("/XRefStm offset " + streamOffset + " is incorrect, corrected to " + fixedOffset); streamOffset = (int)fixedOffset; cOSDictionary.setInt(COSName.XREF_STM, streamOffset); }  if (streamOffset > 0) { this.source.seek(streamOffset); skipSpaces(); try { parseXrefObjStream(prev, false); } catch (IOException ex) { if (this.isLenient) { LOG.error("Failed to parse /XRefStm at offset " + streamOffset, ex); } else { throw ex; }  }  } else if (this.isLenient) { LOG.error("Skipped XRef stream due to a corrupt offset:" + streamOffset); } else { throw new IOException("Skipped XRef stream due to a corrupt offset:" + streamOffset); }  }  prev = cOSDictionary.getLong(COSName.PREV); if (prev > 0L) { fixedOffset = checkXRefOffset(prev); if (fixedOffset > -1L && fixedOffset != prev) { prev = fixedOffset; cOSDictionary.setLong(COSName.PREV, prev); }  }  } else { prev = parseXrefObjStream(prev, true); if (prev > 0L) { fixedOffset = checkXRefOffset(prev); if (fixedOffset > -1L && fixedOffset != prev) { prev = fixedOffset; COSDictionary cOSDictionary = this.xrefTrailerResolver.getCurrentTrailer(); cOSDictionary.setLong(COSName.PREV, prev); }  }  }  if (prevSet.contains(Long.valueOf(prev))) throw new IOException("/Prev loop at offset " + prev);  prevSet.add(Long.valueOf(prev)); }  this.xrefTrailerResolver.setStartxref(startXrefOffset); COSDictionary trailer = this.xrefTrailerResolver.getTrailer(); this.document.setTrailer(trailer); this.document.setIsXRefStream((XrefTrailerResolver.XRefType.STREAM == this.xrefTrailerResolver.getXrefType())); checkXrefOffsets(); this.document.addXRefTable(this.xrefTrailerResolver.getXrefTable()); return trailer; } private long parseXrefObjStream(long objByteOffset, boolean isStandalone) throws IOException { long objectNumber = readObjectNumber(); long currentHighestXRefObjectNumber = this.document.getHighestXRefObjectNumber(); this.document.setHighestXRefObjectNumber(Math.max(currentHighestXRefObjectNumber, objectNumber)); readGenerationNumber(); readExpectedString(OBJ_MARKER, true); COSDictionary dict = parseCOSDictionary(); COSStream xrefStream = parseCOSStream(dict); parseXrefStream(xrefStream, objByteOffset, isStandalone); xrefStream.close(); return dict.getLong(COSName.PREV); } protected final long getStartxrefOffset() throws IOException { byte[] buf; long skipBytes; try { int trailByteCount = (this.fileLen < this.readTrailBytes) ? (int)this.fileLen : this.readTrailBytes; buf = new byte[trailByteCount]; skipBytes = this.fileLen - trailByteCount; this.source.seek(skipBytes); int off = 0; while (off < trailByteCount) { int readBytes = this.source.read(buf, off, trailByteCount - off); if (readBytes < 1) throw new IOException("No more bytes to read for trailing buffer, but expected: " + (trailByteCount - off));  off += readBytes; }  } finally { this.source.seek(0L); }  int bufOff = lastIndexOf(EOF_MARKER, buf, buf.length); if (bufOff < 0) if (this.isLenient) { bufOff = buf.length; LOG.debug("Missing end of file marker '" + new String(EOF_MARKER) + "'"); } else { throw new IOException("Missing end of file marker '" + new String(EOF_MARKER) + "'"); }   bufOff = lastIndexOf(STARTXREF, buf, bufOff); if (bufOff < 0) throw new IOException("Missing 'startxref' marker.");  return skipBytes + bufOff; } public COSParser(RandomAccessRead source, String password, InputStream keyStore, String keyAlias) { super(new RandomAccessSource(source)); this.streamCopyBuf = new byte[8192]; this.source = source; this.password = password; this.keyAlias = keyAlias; this.keyStoreInputStream = keyStore; }
/*      */   protected int lastIndexOf(char[] pattern, byte[] buf, int endOff) { int lastPatternChOff = pattern.length - 1; int bufOff = endOff; int patOff = lastPatternChOff; char lookupCh = pattern[patOff]; while (--bufOff >= 0) {
/*      */       if (buf[bufOff] == lookupCh) {
/*      */         if (--patOff < 0)
/*      */           return bufOff; 
/*      */         lookupCh = pattern[patOff];
/*      */         continue;
/*      */       } 
/*      */       if (patOff < lastPatternChOff) {
/*      */         patOff = lastPatternChOff;
/*      */         lookupCh = pattern[patOff];
/*      */       } 
/*      */     } 
/*      */     return -1; }
/*      */   public boolean isLenient() { return this.isLenient; }
/*      */   public void setLenient(boolean lenient) { if (this.initialParseDone)
/*      */       throw new IllegalArgumentException("Cannot change leniency after parsing"); 
/*      */     this.isLenient = lenient; }
/* 1104 */   private long getObjectId(COSObject obj) { return obj.getObjectNumber() << 32L | obj.getGenerationNumber(); } protected COSStream parseCOSStream(COSDictionary dic) throws IOException { COSStream stream = this.document.createCOSStream(dic);
/*      */ 
/*      */     
/* 1107 */     readString();
/*      */     
/* 1109 */     skipWhiteSpaces();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1114 */     COSNumber streamLengthObj = getLength(dic.getItem(COSName.LENGTH), dic.getCOSName(COSName.TYPE));
/* 1115 */     if (streamLengthObj == null)
/*      */     {
/* 1117 */       if (this.isLenient) {
/*      */         
/* 1119 */         LOG.warn("The stream doesn't provide any stream length, using fallback readUntilEnd, at offset " + this.source
/* 1120 */             .getPosition());
/*      */       }
/*      */       else {
/*      */         
/* 1124 */         throw new IOException("Missing length for stream.");
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1129 */     if (streamLengthObj != null && validateStreamLength(streamLengthObj.longValue())) {
/*      */       
/* 1131 */       OutputStream out = stream.createRawOutputStream();
/*      */       
/*      */       try {
/* 1134 */         readValidStream(out, streamLengthObj);
/*      */       }
/*      */       finally {
/*      */         
/* 1138 */         out.close();
/*      */         
/* 1140 */         stream.setItem(COSName.LENGTH, (COSBase)streamLengthObj);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1145 */       OutputStream out = stream.createRawOutputStream();
/*      */       
/*      */       try {
/* 1148 */         readUntilEndStream(new EndstreamOutputStream(out));
/*      */       }
/*      */       finally {
/*      */         
/* 1152 */         out.close();
/*      */         
/* 1154 */         if (streamLengthObj != null)
/*      */         {
/* 1156 */           stream.setItem(COSName.LENGTH, (COSBase)streamLengthObj);
/*      */         }
/*      */       } 
/*      */     } 
/* 1160 */     String endStream = readString();
/* 1161 */     if (endStream.equals("endobj") && this.isLenient) {
/*      */       
/* 1163 */       LOG.warn("stream ends with 'endobj' instead of 'endstream' at offset " + this.source
/* 1164 */           .getPosition());
/*      */       
/* 1166 */       this.source.rewind(ENDOBJ.length);
/*      */     }
/* 1168 */     else if (endStream.length() > 9 && this.isLenient && endStream.substring(0, 9).equals("endstream")) {
/*      */       
/* 1170 */       LOG.warn("stream ends with '" + endStream + "' instead of 'endstream' at offset " + this.source
/* 1171 */           .getPosition());
/*      */       
/* 1173 */       this.source.rewind((endStream.substring(9).getBytes(Charsets.ISO_8859_1)).length);
/*      */     }
/* 1175 */     else if (!endStream.equals("endstream")) {
/*      */       
/* 1177 */       throw new IOException("Error reading stream, expected='endstream' actual='" + endStream + "' at offset " + this.source
/*      */           
/* 1179 */           .getPosition());
/*      */     } 
/*      */     
/* 1182 */     return stream; } private void addNewToList(Queue<COSBase> toBeParsedList, Collection<COSBase> newObjects, Set<Long> addedObjects) { for (COSBase newObject : newObjects)
/*      */       addNewToList(toBeParsedList, newObject, addedObjects);  }
/*      */   private void addNewToList(Queue<COSBase> toBeParsedList, COSBase newObject, Set<Long> addedObjects) { if (newObject instanceof COSObject) { long objId = getObjectId((COSObject)newObject); if (!addedObjects.add(Long.valueOf(objId)))
/*      */         return;  toBeParsedList.add(newObject); } else if (newObject instanceof COSDictionary || newObject instanceof COSArray) { toBeParsedList.add(newObject); }  }
/*      */   protected void parseDictObjects(COSDictionary dict, COSName... excludeObjects) throws IOException { Queue<COSBase> toBeParsedList = new LinkedList<COSBase>(); TreeMap<Long, List<COSObject>> objToBeParsed = new TreeMap<Long, List<COSObject>>(); Set<Long> parsedObjects = new HashSet<Long>(); Set<Long> addedObjects = new HashSet<Long>(); addExcludedToList(excludeObjects, dict, parsedObjects); addNewToList(toBeParsedList, dict.getValues(), addedObjects); while (!toBeParsedList.isEmpty() || !objToBeParsed.isEmpty()) { COSBase baseObj; while ((baseObj = toBeParsedList.poll()) != null) { if (baseObj instanceof COSDictionary) { addNewToList(toBeParsedList, ((COSDictionary)baseObj).getValues(), addedObjects); continue; }  if (baseObj instanceof COSArray) { for (COSBase cosBase : baseObj)
/*      */             addNewToList(toBeParsedList, cosBase, addedObjects);  continue; }  if (baseObj instanceof COSObject) { COSObject obj = (COSObject)baseObj; long objId = getObjectId(obj); COSObjectKey objKey = new COSObjectKey(obj.getObjectNumber(), obj.getGenerationNumber()); if (!parsedObjects.contains(Long.valueOf(objId))) { Long fileOffset = (Long)this.document.getXrefTable().get(objKey); if (fileOffset == null && this.isLenient && this.bfSearchCOSObjectKeyOffsets != null) { fileOffset = this.bfSearchCOSObjectKeyOffsets.get(objKey); if (fileOffset != null) { LOG.debug("Set missing " + fileOffset + " for object " + objKey); this.document.getXrefTable().put(objKey, fileOffset); }  }  if (fileOffset != null && fileOffset.longValue() != 0L) { if (fileOffset.longValue() > 0L) { objToBeParsed.put(fileOffset, Collections.singletonList(obj)); continue; }  COSObjectKey key = new COSObjectKey((int)-fileOffset.longValue(), 0); fileOffset = (Long)this.document.getXrefTable().get(key); if (fileOffset == null || fileOffset.longValue() <= 0L)
/*      */                 if (this.isLenient && this.bfSearchCOSObjectKeyOffsets != null) { fileOffset = this.bfSearchCOSObjectKeyOffsets.get(key); if (fileOffset != null) { LOG.debug("Set missing " + fileOffset + " for object " + key); this.document.getXrefTable().put(key, fileOffset); }  } else { String msg = "Invalid object stream xref object reference for key '" + objKey + "': " + fileOffset; if (this.isLenient && fileOffset == null) { LOG.warn(msg); continue; }  throw new IOException(msg); }   List<COSObject> stmObjects = objToBeParsed.get(fileOffset); if (stmObjects == null) { stmObjects = new ArrayList<COSObject>(); objToBeParsed.put(fileOffset, stmObjects); } else if (!(stmObjects instanceof ArrayList)) { throw new IOException(obj + " cannot be assigned to offset " + fileOffset + ", this belongs to " + stmObjects.get(0)); }
/*      */                stmObjects.add(obj); continue; }
/*      */              COSObject pdfObject = this.document.getObjectFromPool(objKey); pdfObject.setObject((COSBase)COSNull.NULL); }
/*      */            }
/*      */          }
/*      */        if (objToBeParsed.isEmpty())
/*      */         break;  for (COSObject obj : objToBeParsed.remove(objToBeParsed.firstKey())) { COSBase parsedObj = parseObjectDynamically(obj, false); if (parsedObj != null) { obj.setObject(parsedObj); addNewToList(toBeParsedList, parsedObj, addedObjects); parsedObjects.add(Long.valueOf(getObjectId(obj))); }
/*      */          }
/*      */        }
/*      */      }
/*      */   private void addExcludedToList(COSName[] excludeObjects, COSDictionary dict, Set<Long> parsedObjects) { if (excludeObjects != null)
/*      */       for (COSName objName : excludeObjects) { COSBase baseObj = dict.getItem(objName); if (baseObj instanceof COSObject)
/*      */           parsedObjects.add(Long.valueOf(getObjectId((COSObject)baseObj)));  }
/*      */         }
/* 1202 */   private void readUntilEndStream(OutputStream out) throws IOException { int charMatchCount = 0;
/* 1203 */     byte[] keyw = ENDSTREAM;
/*      */ 
/*      */     
/* 1206 */     int quickTestOffset = 5;
/*      */     
/*      */     int bufSize;
/* 1209 */     while ((bufSize = this.source.read(this.strmBuf, charMatchCount, 2048 - charMatchCount)) > 0) {
/*      */       
/* 1211 */       bufSize += charMatchCount;
/*      */       
/* 1213 */       int bIdx = charMatchCount;
/*      */ 
/*      */ 
/*      */       
/* 1217 */       for (int maxQuicktestIdx = bufSize - 5; bIdx < bufSize; bIdx++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1223 */         int quickTestIdx = bIdx + 5;
/* 1224 */         if (charMatchCount == 0 && quickTestIdx < maxQuicktestIdx) {
/*      */           
/* 1226 */           byte b = this.strmBuf[quickTestIdx];
/* 1227 */           if (b > 116 || b < 97) {
/*      */ 
/*      */ 
/*      */             
/* 1231 */             bIdx = quickTestIdx;
/*      */             
/*      */             continue;
/*      */           } 
/*      */         } 
/*      */         
/* 1237 */         byte ch = this.strmBuf[bIdx];
/*      */         
/* 1239 */         if (ch == keyw[charMatchCount]) {
/*      */           
/* 1241 */           if (++charMatchCount == keyw.length) {
/*      */ 
/*      */             
/* 1244 */             bIdx++;
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/* 1250 */         } else if (charMatchCount == 3 && ch == ENDOBJ[charMatchCount]) {
/*      */ 
/*      */           
/* 1253 */           keyw = ENDOBJ;
/* 1254 */           charMatchCount++;
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/* 1263 */           charMatchCount = (ch == 101) ? 1 : ((ch == 110 && charMatchCount == 7) ? 2 : 0);
/*      */           
/* 1265 */           keyw = ENDSTREAM;
/*      */         } 
/*      */         
/*      */         continue;
/*      */       } 
/* 1270 */       int contentBytes = Math.max(0, bIdx - charMatchCount);
/*      */ 
/*      */       
/* 1273 */       if (contentBytes > 0)
/*      */       {
/* 1275 */         out.write(this.strmBuf, 0, contentBytes);
/*      */       }
/* 1277 */       if (charMatchCount == keyw.length) {
/*      */ 
/*      */         
/* 1280 */         this.source.rewind(bufSize - contentBytes);
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*      */       
/* 1286 */       System.arraycopy(keyw, 0, this.strmBuf, 0, charMatchCount);
/*      */     } 
/*      */ 
/*      */     
/* 1290 */     out.flush(); } protected final COSBase parseObjectDynamically(COSObject obj, boolean requireExistingNotCompressedObj) throws IOException { return parseObjectDynamically(obj.getObjectNumber(), obj.getGenerationNumber(), requireExistingNotCompressedObj); }
/*      */   protected COSBase parseObjectDynamically(long objNr, int objGenNr, boolean requireExistingNotCompressedObj) throws IOException { COSObjectKey objKey = new COSObjectKey(objNr, objGenNr); COSObject pdfObject = this.document.getObjectFromPool(objKey); if (pdfObject.getObject() == null) { Long offsetOrObjstmObNr = (Long)this.document.getXrefTable().get(objKey); if (offsetOrObjstmObNr == null && this.isLenient && this.bfSearchCOSObjectKeyOffsets != null) { offsetOrObjstmObNr = this.bfSearchCOSObjectKeyOffsets.get(objKey); if (offsetOrObjstmObNr != null) { LOG.debug("Set missing offset " + offsetOrObjstmObNr + " for object " + objKey); this.document.getXrefTable().put(objKey, offsetOrObjstmObNr); }  }  if (requireExistingNotCompressedObj && (offsetOrObjstmObNr == null || offsetOrObjstmObNr.longValue() <= 0L)) throw new IOException("Object must be defined and must not be compressed object: " + objKey.getNumber() + ":" + objKey.getGeneration());  if (offsetOrObjstmObNr == null && this.isLenient && this.bfSearchCOSObjectKeyOffsets == null) { bfSearchForObjects(); if (this.bfSearchCOSObjectKeyOffsets != null && !this.bfSearchCOSObjectKeyOffsets.isEmpty()) { LOG.debug("Add all new read objects from brute force search to the xref table"); Map<COSObjectKey, Long> xrefOffset = this.document.getXrefTable(); Set<Map.Entry<COSObjectKey, Long>> entries = this.bfSearchCOSObjectKeyOffsets.entrySet(); for (Map.Entry<COSObjectKey, Long> entry : entries) { COSObjectKey key = entry.getKey(); if (!xrefOffset.containsKey(key)) xrefOffset.put(key, entry.getValue());  }  offsetOrObjstmObNr = xrefOffset.get(objKey); }  }  if (offsetOrObjstmObNr == null) { pdfObject.setObject((COSBase)COSNull.NULL); } else if (offsetOrObjstmObNr.longValue() > 0L) { parseFileObject(offsetOrObjstmObNr, objKey, pdfObject); } else { parseObjectStream((int)-offsetOrObjstmObNr.longValue()); }  }  return pdfObject.getObject(); }
/*      */   private void parseFileObject(Long offsetOrObjstmObNr, COSObjectKey objKey, COSObject pdfObject) throws IOException { COSStream cOSStream; this.source.seek(offsetOrObjstmObNr.longValue()); long readObjNr = readObjectNumber(); int readObjGen = readGenerationNumber(); readExpectedString(OBJ_MARKER, true); if (readObjNr != objKey.getNumber() || readObjGen != objKey.getGeneration()) throw new IOException("XREF for " + objKey.getNumber() + ":" + objKey.getGeneration() + " points to wrong object: " + readObjNr + ":" + readObjGen + " at offset " + offsetOrObjstmObNr);  skipSpaces(); COSBase pb = parseDirObject(); String endObjectKey = readString(); if (endObjectKey.equals("stream")) { this.source.rewind((endObjectKey.getBytes(Charsets.ISO_8859_1)).length); if (pb instanceof COSDictionary) { COSStream stream = parseCOSStream((COSDictionary)pb); if (this.securityHandler != null) this.securityHandler.decryptStream(stream, objKey.getNumber(), objKey.getGeneration());  cOSStream = stream; } else { throw new IOException("Stream not preceded by dictionary (offset: " + offsetOrObjstmObNr + ")."); }  skipSpaces(); endObjectKey = readLine(); if (!endObjectKey.startsWith("endobj") && endObjectKey.startsWith("endstream")) { endObjectKey = endObjectKey.substring(9).trim(); if (endObjectKey.length() == 0) endObjectKey = readLine();  }  } else if (this.securityHandler != null) { this.securityHandler.decrypt((COSBase)cOSStream, objKey.getNumber(), objKey.getGeneration()); }  pdfObject.setObject((COSBase)cOSStream); if (!endObjectKey.startsWith("endobj")) if (this.isLenient) { LOG.warn("Object (" + readObjNr + ":" + readObjGen + ") at offset " + offsetOrObjstmObNr + " does not end with 'endobj' but with '" + endObjectKey + "'"); } else { throw new IOException("Object (" + readObjNr + ":" + readObjGen + ") at offset " + offsetOrObjstmObNr + " does not end with 'endobj' but with '" + endObjectKey + "'"); }   }
/*      */   private void parseObjectStream(int objstmObjNr) throws IOException { COSBase objstmBaseObj = parseObjectDynamically(objstmObjNr, 0, true); if (objstmBaseObj instanceof COSStream) { PDFObjectStreamParser parser; try { parser = new PDFObjectStreamParser((COSStream)objstmBaseObj, this.document); } catch (IOException ex) { if (this.isLenient) { LOG.error("object stream " + objstmObjNr + " could not be parsed due to an exception", ex); return; }  throw ex; }  try { parser.parse(); } catch (IOException exception) { if (this.isLenient) { LOG.debug("Stop reading object stream " + objstmObjNr + " due to an exception", exception); return; }  throw exception; }  for (COSObject next : parser.getObjects()) { COSObjectKey stmObjKey = new COSObjectKey(next); Long offset = this.xrefTrailerResolver.getXrefTable().get(stmObjKey); if (offset != null && offset.longValue() == -objstmObjNr) { COSObject stmObj = this.document.getObjectFromPool(stmObjKey); stmObj.setObject(next.getObject()); }  }  }  }
/*      */   private COSNumber getLength(COSBase lengthBaseObj, COSName streamType) throws IOException { if (lengthBaseObj == null) return null;  COSNumber retVal = null; if (lengthBaseObj instanceof COSNumber) { retVal = (COSNumber)lengthBaseObj; } else if (lengthBaseObj instanceof COSObject) { COSObject lengthObj = (COSObject)lengthBaseObj; COSBase length = lengthObj.getObject(); if (length == null) { long curFileOffset = this.source.getPosition(); boolean isObjectStream = COSName.OBJ_STM.equals(streamType); parseObjectDynamically(lengthObj, isObjectStream); this.source.seek(curFileOffset); length = lengthObj.getObject(); }  if (length == null) throw new IOException("Length object content was not read.");  if (COSNull.NULL == length) { LOG.warn("Length object (" + lengthObj.getObjectNumber() + " " + lengthObj.getGenerationNumber() + ") not found"); return null; }  if (!(length instanceof COSNumber)) throw new IOException("Wrong type of referenced length object " + lengthObj + ": " + length.getClass().getSimpleName());  retVal = (COSNumber)length; } else { throw new IOException("Wrong type of length object: " + lengthBaseObj.getClass().getSimpleName()); }  return retVal; }
/* 1295 */   private void readValidStream(OutputStream out, COSNumber streamLengthObj) throws IOException { long remainBytes = streamLengthObj.longValue();
/* 1296 */     while (remainBytes > 0L) {
/*      */       
/* 1298 */       int chunk = (remainBytes > 8192L) ? 8192 : (int)remainBytes;
/* 1299 */       int readBytes = this.source.read(this.streamCopyBuf, 0, chunk);
/* 1300 */       if (readBytes <= 0)
/*      */       {
/*      */         
/* 1303 */         throw new IOException("read error at offset " + this.source.getPosition() + ": expected " + chunk + " bytes, but read() returns " + readBytes);
/*      */       }
/*      */       
/* 1306 */       out.write(this.streamCopyBuf, 0, readBytes);
/* 1307 */       remainBytes -= readBytes;
/*      */     }  }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean validateStreamLength(long streamLength) throws IOException {
/* 1313 */     boolean streamLengthIsValid = true;
/* 1314 */     long originOffset = this.source.getPosition();
/* 1315 */     long expectedEndOfStream = originOffset + streamLength;
/* 1316 */     if (expectedEndOfStream > this.fileLen) {
/*      */       
/* 1318 */       streamLengthIsValid = false;
/* 1319 */       LOG.warn("The end of the stream is out of range, using workaround to read the stream, stream start position: " + originOffset + ", length: " + streamLength + ", expected end position: " + expectedEndOfStream);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1325 */       this.source.seek(expectedEndOfStream);
/* 1326 */       skipSpaces();
/* 1327 */       if (!isString(ENDSTREAM)) {
/*      */         
/* 1329 */         streamLengthIsValid = false;
/* 1330 */         LOG.warn("The end of the stream doesn't point to the correct offset, using workaround to read the stream, stream start position: " + originOffset + ", length: " + streamLength + ", expected end position: " + expectedEndOfStream);
/*      */       } 
/*      */ 
/*      */       
/* 1334 */       this.source.seek(originOffset);
/*      */     } 
/* 1336 */     return streamLengthIsValid;
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
/*      */   private long checkXRefOffset(long startXRefOffset) throws IOException {
/* 1349 */     if (!this.isLenient)
/*      */     {
/* 1351 */       return startXRefOffset;
/*      */     }
/* 1353 */     this.source.seek(startXRefOffset);
/* 1354 */     skipSpaces();
/* 1355 */     if (this.source.peek() == 120 && isString(XREF_TABLE))
/*      */     {
/* 1357 */       return startXRefOffset;
/*      */     }
/* 1359 */     if (startXRefOffset > 0L) {
/*      */       
/* 1361 */       if (checkXRefStreamOffset(startXRefOffset))
/*      */       {
/* 1363 */         return startXRefOffset;
/*      */       }
/*      */ 
/*      */       
/* 1367 */       return calculateXRefFixedOffset(startXRefOffset, false);
/*      */     } 
/*      */ 
/*      */     
/* 1371 */     return -1L;
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
/*      */   private boolean checkXRefStreamOffset(long startXRefOffset) throws IOException {
/* 1384 */     if (!this.isLenient || startXRefOffset == 0L)
/*      */     {
/* 1386 */       return true;
/*      */     }
/*      */     
/* 1389 */     this.source.seek(startXRefOffset - 1L);
/* 1390 */     int nextValue = this.source.read();
/*      */     
/* 1392 */     if (isWhitespace(nextValue)) {
/*      */       
/* 1394 */       skipSpaces();
/* 1395 */       if (isDigit()) {
/*      */         
/*      */         try {
/*      */ 
/*      */           
/* 1400 */           readObjectNumber();
/* 1401 */           readGenerationNumber();
/* 1402 */           readExpectedString(OBJ_MARKER, true);
/*      */           
/* 1404 */           COSDictionary dict = parseCOSDictionary();
/* 1405 */           this.source.seek(startXRefOffset);
/* 1406 */           if ("XRef".equals(dict.getNameAsString(COSName.TYPE)))
/*      */           {
/* 1408 */             return true;
/*      */           }
/*      */         }
/* 1411 */         catch (IOException exception) {
/*      */ 
/*      */           
/* 1414 */           this.source.seek(startXRefOffset);
/*      */         } 
/*      */       }
/*      */     } 
/* 1418 */     return false;
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
/*      */   private long calculateXRefFixedOffset(long objectOffset, boolean streamsOnly) throws IOException {
/* 1432 */     if (objectOffset < 0L) {
/*      */       
/* 1434 */       LOG.error("Invalid object offset " + objectOffset + " when searching for a xref table/stream");
/* 1435 */       return 0L;
/*      */     } 
/*      */     
/* 1438 */     long newOffset = bfSearchForXRef(objectOffset, streamsOnly);
/* 1439 */     if (newOffset > -1L) {
/*      */       
/* 1441 */       LOG.debug("Fixed reference for xref table/stream " + objectOffset + " -> " + newOffset);
/* 1442 */       return newOffset;
/*      */     } 
/* 1444 */     LOG.error("Can't find the object xref table/stream at offset " + objectOffset);
/* 1445 */     return 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean validateXrefOffsets(Map<COSObjectKey, Long> xrefOffset) throws IOException {
/* 1450 */     if (xrefOffset == null)
/*      */     {
/* 1452 */       return true;
/*      */     }
/* 1454 */     for (Map.Entry<COSObjectKey, Long> objectEntry : xrefOffset.entrySet()) {
/*      */       
/* 1456 */       COSObjectKey objectKey = objectEntry.getKey();
/* 1457 */       Long objectOffset = objectEntry.getValue();
/*      */ 
/*      */       
/* 1460 */       if (objectOffset != null && objectOffset.longValue() >= 0L && 
/* 1461 */         !checkObjectKey(objectKey, objectOffset.longValue())) {
/*      */         
/* 1463 */         LOG.debug("Stop checking xref offsets as at least one (" + objectKey + ") couldn't be dereferenced");
/*      */         
/* 1465 */         return false;
/*      */       } 
/*      */     } 
/* 1468 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkXrefOffsets() throws IOException {
/* 1479 */     if (!this.isLenient) {
/*      */       return;
/*      */     }
/*      */     
/* 1483 */     Map<COSObjectKey, Long> xrefOffset = this.xrefTrailerResolver.getXrefTable();
/* 1484 */     if (!validateXrefOffsets(xrefOffset)) {
/*      */       
/* 1486 */       bfSearchForObjects();
/* 1487 */       if (this.bfSearchCOSObjectKeyOffsets != null && !this.bfSearchCOSObjectKeyOffsets.isEmpty()) {
/*      */         
/* 1489 */         LOG.debug("Replaced read xref table with the results of a brute force search");
/* 1490 */         xrefOffset.clear();
/* 1491 */         xrefOffset.putAll(this.bfSearchCOSObjectKeyOffsets);
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
/*      */ 
/*      */   
/*      */   private boolean checkObjectKey(COSObjectKey objectKey, long offset) throws IOException {
/* 1507 */     if (offset < 6L)
/*      */     {
/* 1509 */       return false;
/*      */     }
/* 1511 */     boolean objectKeyFound = false;
/*      */     
/*      */     try {
/* 1514 */       this.source.seek(offset);
/*      */       
/* 1516 */       if (objectKey.getNumber() == readObjectNumber()) {
/*      */         
/* 1518 */         int genNumber = readGenerationNumber();
/* 1519 */         if (genNumber == objectKey.getGeneration())
/*      */         {
/*      */           
/* 1522 */           readExpectedString(OBJ_MARKER, true);
/* 1523 */           objectKeyFound = true;
/*      */         }
/* 1525 */         else if (this.isLenient && genNumber > objectKey.getGeneration())
/*      */         {
/*      */           
/* 1528 */           readExpectedString(OBJ_MARKER, true);
/* 1529 */           objectKeyFound = true;
/* 1530 */           objectKey.fixGeneration(genNumber);
/*      */         }
/*      */       
/*      */       } 
/* 1534 */     } catch (IOException iOException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1539 */     return objectKeyFound;
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
/*      */   private void bfSearchForObjects() throws IOException {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield bfSearchCOSObjectKeyOffsets : Ljava/util/Map;
/*      */     //   4: ifnonnull -> 494
/*      */     //   7: aload_0
/*      */     //   8: invokespecial bfSearchForLastEOFMarker : ()V
/*      */     //   11: aload_0
/*      */     //   12: new java/util/HashMap
/*      */     //   15: dup
/*      */     //   16: invokespecial <init> : ()V
/*      */     //   19: putfield bfSearchCOSObjectKeyOffsets : Ljava/util/Map;
/*      */     //   22: aload_0
/*      */     //   23: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   26: invokeinterface getPosition : ()J
/*      */     //   31: lstore_1
/*      */     //   32: ldc2_w 6
/*      */     //   35: lstore_3
/*      */     //   36: ldc2_w -9223372036854775808
/*      */     //   39: lstore #5
/*      */     //   41: ldc -2147483648
/*      */     //   43: istore #7
/*      */     //   45: ldc2_w -9223372036854775808
/*      */     //   48: lstore #8
/*      */     //   50: ldc 'ndo'
/*      */     //   52: invokevirtual toCharArray : ()[C
/*      */     //   55: astore #10
/*      */     //   57: ldc 'bj'
/*      */     //   59: invokevirtual toCharArray : ()[C
/*      */     //   62: astore #11
/*      */     //   64: iconst_0
/*      */     //   65: istore #12
/*      */     //   67: aload_0
/*      */     //   68: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   71: lload_3
/*      */     //   72: invokeinterface seek : (J)V
/*      */     //   77: aload_0
/*      */     //   78: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   81: invokeinterface read : ()I
/*      */     //   86: istore #13
/*      */     //   88: lload_3
/*      */     //   89: lconst_1
/*      */     //   90: ladd
/*      */     //   91: lstore_3
/*      */     //   92: aload_0
/*      */     //   93: iload #13
/*      */     //   95: invokevirtual isWhitespace : (I)Z
/*      */     //   98: ifeq -> 335
/*      */     //   101: aload_0
/*      */     //   102: getstatic org/apache/pdfbox/pdfparser/COSParser.OBJ_MARKER : [C
/*      */     //   105: invokespecial isString : ([C)Z
/*      */     //   108: ifeq -> 335
/*      */     //   111: lload_3
/*      */     //   112: ldc2_w 2
/*      */     //   115: lsub
/*      */     //   116: lstore #14
/*      */     //   118: aload_0
/*      */     //   119: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   122: lload #14
/*      */     //   124: invokeinterface seek : (J)V
/*      */     //   129: aload_0
/*      */     //   130: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   133: invokeinterface peek : ()I
/*      */     //   138: istore #16
/*      */     //   140: iload #16
/*      */     //   142: invokestatic isDigit : (I)Z
/*      */     //   145: ifeq -> 332
/*      */     //   148: iinc #16, -48
/*      */     //   151: lload #14
/*      */     //   153: lconst_1
/*      */     //   154: lsub
/*      */     //   155: lstore #14
/*      */     //   157: aload_0
/*      */     //   158: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   161: lload #14
/*      */     //   163: invokeinterface seek : (J)V
/*      */     //   168: aload_0
/*      */     //   169: invokevirtual isWhitespace : ()Z
/*      */     //   172: ifeq -> 332
/*      */     //   175: lload #14
/*      */     //   177: ldc2_w 6
/*      */     //   180: lcmp
/*      */     //   181: ifle -> 210
/*      */     //   184: aload_0
/*      */     //   185: invokevirtual isWhitespace : ()Z
/*      */     //   188: ifeq -> 210
/*      */     //   191: aload_0
/*      */     //   192: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   195: lload #14
/*      */     //   197: lconst_1
/*      */     //   198: lsub
/*      */     //   199: dup2
/*      */     //   200: lstore #14
/*      */     //   202: invokeinterface seek : (J)V
/*      */     //   207: goto -> 175
/*      */     //   210: iconst_0
/*      */     //   211: istore #17
/*      */     //   213: lload #14
/*      */     //   215: ldc2_w 6
/*      */     //   218: lcmp
/*      */     //   219: ifle -> 251
/*      */     //   222: aload_0
/*      */     //   223: invokevirtual isDigit : ()Z
/*      */     //   226: ifeq -> 251
/*      */     //   229: aload_0
/*      */     //   230: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   233: lload #14
/*      */     //   235: lconst_1
/*      */     //   236: lsub
/*      */     //   237: dup2
/*      */     //   238: lstore #14
/*      */     //   240: invokeinterface seek : (J)V
/*      */     //   245: iconst_1
/*      */     //   246: istore #17
/*      */     //   248: goto -> 213
/*      */     //   251: iload #17
/*      */     //   253: ifeq -> 332
/*      */     //   256: aload_0
/*      */     //   257: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   260: invokeinterface read : ()I
/*      */     //   265: pop
/*      */     //   266: aload_0
/*      */     //   267: invokevirtual readObjectNumber : ()J
/*      */     //   270: lstore #18
/*      */     //   272: lload #8
/*      */     //   274: lconst_0
/*      */     //   275: lcmp
/*      */     //   276: ifle -> 305
/*      */     //   279: aload_0
/*      */     //   280: getfield bfSearchCOSObjectKeyOffsets : Ljava/util/Map;
/*      */     //   283: new org/apache/pdfbox/cos/COSObjectKey
/*      */     //   286: dup
/*      */     //   287: lload #5
/*      */     //   289: iload #7
/*      */     //   291: invokespecial <init> : (JI)V
/*      */     //   294: lload #8
/*      */     //   296: invokestatic valueOf : (J)Ljava/lang/Long;
/*      */     //   299: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*      */     //   304: pop
/*      */     //   305: lload #18
/*      */     //   307: lstore #5
/*      */     //   309: iload #16
/*      */     //   311: istore #7
/*      */     //   313: lload #14
/*      */     //   315: lconst_1
/*      */     //   316: ladd
/*      */     //   317: lstore #8
/*      */     //   319: lload_3
/*      */     //   320: getstatic org/apache/pdfbox/pdfparser/COSParser.OBJ_MARKER : [C
/*      */     //   323: arraylength
/*      */     //   324: iconst_1
/*      */     //   325: isub
/*      */     //   326: i2l
/*      */     //   327: ladd
/*      */     //   328: lstore_3
/*      */     //   329: iconst_0
/*      */     //   330: istore #12
/*      */     //   332: goto -> 408
/*      */     //   335: iload #13
/*      */     //   337: bipush #101
/*      */     //   339: if_icmpne -> 408
/*      */     //   342: aload_0
/*      */     //   343: aload #10
/*      */     //   345: invokespecial isString : ([C)Z
/*      */     //   348: ifeq -> 408
/*      */     //   351: lload_3
/*      */     //   352: aload #10
/*      */     //   354: arraylength
/*      */     //   355: i2l
/*      */     //   356: ladd
/*      */     //   357: lstore_3
/*      */     //   358: aload_0
/*      */     //   359: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   362: lload_3
/*      */     //   363: invokeinterface seek : (J)V
/*      */     //   368: aload_0
/*      */     //   369: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   372: invokeinterface isEOF : ()Z
/*      */     //   377: ifeq -> 386
/*      */     //   380: iconst_1
/*      */     //   381: istore #12
/*      */     //   383: goto -> 408
/*      */     //   386: aload_0
/*      */     //   387: aload #11
/*      */     //   389: invokespecial isString : ([C)Z
/*      */     //   392: ifeq -> 408
/*      */     //   395: lload_3
/*      */     //   396: aload #11
/*      */     //   398: arraylength
/*      */     //   399: i2l
/*      */     //   400: ladd
/*      */     //   401: lstore_3
/*      */     //   402: iconst_1
/*      */     //   403: istore #12
/*      */     //   405: goto -> 408
/*      */     //   408: lload_3
/*      */     //   409: aload_0
/*      */     //   410: getfield lastEOFMarker : Ljava/lang/Long;
/*      */     //   413: invokevirtual longValue : ()J
/*      */     //   416: lcmp
/*      */     //   417: ifge -> 432
/*      */     //   420: aload_0
/*      */     //   421: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   424: invokeinterface isEOF : ()Z
/*      */     //   429: ifeq -> 67
/*      */     //   432: aload_0
/*      */     //   433: getfield lastEOFMarker : Ljava/lang/Long;
/*      */     //   436: invokevirtual longValue : ()J
/*      */     //   439: ldc2_w 9223372036854775807
/*      */     //   442: lcmp
/*      */     //   443: iflt -> 451
/*      */     //   446: iload #12
/*      */     //   448: ifeq -> 484
/*      */     //   451: lload #8
/*      */     //   453: lconst_0
/*      */     //   454: lcmp
/*      */     //   455: ifle -> 484
/*      */     //   458: aload_0
/*      */     //   459: getfield bfSearchCOSObjectKeyOffsets : Ljava/util/Map;
/*      */     //   462: new org/apache/pdfbox/cos/COSObjectKey
/*      */     //   465: dup
/*      */     //   466: lload #5
/*      */     //   468: iload #7
/*      */     //   470: invokespecial <init> : (JI)V
/*      */     //   473: lload #8
/*      */     //   475: invokestatic valueOf : (J)Ljava/lang/Long;
/*      */     //   478: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*      */     //   483: pop
/*      */     //   484: aload_0
/*      */     //   485: getfield source : Lorg/apache/pdfbox/io/RandomAccessRead;
/*      */     //   488: lload_1
/*      */     //   489: invokeinterface seek : (J)V
/*      */     //   494: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1549	-> 0
/*      */     //   #1551	-> 7
/*      */     //   #1552	-> 11
/*      */     //   #1553	-> 22
/*      */     //   #1554	-> 32
/*      */     //   #1555	-> 36
/*      */     //   #1556	-> 41
/*      */     //   #1557	-> 45
/*      */     //   #1558	-> 50
/*      */     //   #1559	-> 57
/*      */     //   #1560	-> 64
/*      */     //   #1563	-> 67
/*      */     //   #1564	-> 77
/*      */     //   #1565	-> 88
/*      */     //   #1566	-> 92
/*      */     //   #1568	-> 111
/*      */     //   #1569	-> 118
/*      */     //   #1570	-> 129
/*      */     //   #1572	-> 140
/*      */     //   #1574	-> 148
/*      */     //   #1575	-> 151
/*      */     //   #1576	-> 157
/*      */     //   #1577	-> 168
/*      */     //   #1579	-> 175
/*      */     //   #1581	-> 191
/*      */     //   #1583	-> 210
/*      */     //   #1584	-> 213
/*      */     //   #1586	-> 229
/*      */     //   #1587	-> 245
/*      */     //   #1589	-> 251
/*      */     //   #1591	-> 256
/*      */     //   #1592	-> 266
/*      */     //   #1593	-> 272
/*      */     //   #1596	-> 279
/*      */     //   #1598	-> 296
/*      */     //   #1597	-> 299
/*      */     //   #1600	-> 305
/*      */     //   #1601	-> 309
/*      */     //   #1602	-> 313
/*      */     //   #1603	-> 319
/*      */     //   #1604	-> 329
/*      */     //   #1608	-> 332
/*      */     //   #1612	-> 335
/*      */     //   #1614	-> 351
/*      */     //   #1615	-> 358
/*      */     //   #1616	-> 368
/*      */     //   #1618	-> 380
/*      */     //   #1619	-> 383
/*      */     //   #1621	-> 386
/*      */     //   #1623	-> 395
/*      */     //   #1624	-> 402
/*      */     //   #1625	-> 405
/*      */     //   #1629	-> 408
/*      */     //   #1630	-> 432
/*      */     //   #1635	-> 458
/*      */     //   #1636	-> 475
/*      */     //   #1635	-> 478
/*      */     //   #1639	-> 484
/*      */     //   #1641	-> 494
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	495	0	this	Lorg/apache/pdfbox/pdfparser/COSParser;
/*      */     //   32	462	1	originOffset	J
/*      */     //   36	458	3	currentOffset	J
/*      */     //   41	453	5	lastObjectId	J
/*      */     //   45	449	7	lastGenID	I
/*      */     //   50	444	8	lastObjOffset	J
/*      */     //   57	437	10	endobjString	[C
/*      */     //   64	430	11	endobjRemainingString	[C
/*      */     //   67	427	12	endOfObjFound	Z
/*      */     //   88	320	13	nextChar	I
/*      */     //   118	214	14	tempOffset	J
/*      */     //   140	192	16	genID	I
/*      */     //   213	119	17	objectIDFound	Z
/*      */     //   272	60	18	objectId	J
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long bfSearchForXRef(long xrefOffset, boolean streamsOnly) throws IOException {
/* 1652 */     long newOffset = -1L;
/* 1653 */     long newOffsetTable = -1L;
/* 1654 */     long newOffsetStream = -1L;
/* 1655 */     if (!streamsOnly)
/*      */     {
/* 1657 */       bfSearchForXRefTables();
/*      */     }
/* 1659 */     bfSearchForXRefStreams();
/* 1660 */     if (!streamsOnly && this.bfSearchXRefTablesOffsets != null)
/*      */     {
/*      */       
/* 1663 */       newOffsetTable = searchNearestValue(this.bfSearchXRefTablesOffsets, xrefOffset);
/*      */     }
/* 1665 */     if (this.bfSearchXRefStreamsOffsets != null)
/*      */     {
/*      */       
/* 1668 */       newOffsetStream = searchNearestValue(this.bfSearchXRefStreamsOffsets, xrefOffset);
/*      */     }
/*      */     
/* 1671 */     if (newOffsetTable > -1L && newOffsetStream > -1L) {
/*      */       
/* 1673 */       long differenceTable = xrefOffset - newOffsetTable;
/* 1674 */       long differenceStream = xrefOffset - newOffsetStream;
/* 1675 */       if (Math.abs(differenceTable) > Math.abs(differenceStream))
/*      */       {
/* 1677 */         newOffset = newOffsetStream;
/* 1678 */         this.bfSearchXRefStreamsOffsets.remove(Long.valueOf(newOffsetStream));
/*      */       }
/*      */       else
/*      */       {
/* 1682 */         newOffset = newOffsetTable;
/* 1683 */         this.bfSearchXRefTablesOffsets.remove(Long.valueOf(newOffsetTable));
/*      */       }
/*      */     
/* 1686 */     } else if (newOffsetTable > -1L) {
/*      */       
/* 1688 */       newOffset = newOffsetTable;
/* 1689 */       this.bfSearchXRefTablesOffsets.remove(Long.valueOf(newOffsetTable));
/*      */     }
/* 1691 */     else if (newOffsetStream > -1L) {
/*      */       
/* 1693 */       newOffset = newOffsetStream;
/* 1694 */       this.bfSearchXRefStreamsOffsets.remove(Long.valueOf(newOffsetStream));
/*      */     } 
/* 1696 */     return newOffset;
/*      */   }
/*      */ 
/*      */   
/*      */   private long searchNearestValue(List<Long> values, long offset) {
/* 1701 */     long newValue = -1L;
/* 1702 */     Long currentDifference = null;
/* 1703 */     int currentOffsetIndex = -1;
/* 1704 */     int numberOfOffsets = values.size();
/*      */     
/* 1706 */     for (int i = 0; i < numberOfOffsets; i++) {
/*      */       
/* 1708 */       long newDifference = offset - ((Long)values.get(i)).longValue();
/*      */       
/* 1710 */       if (currentDifference == null || 
/* 1711 */         Math.abs(currentDifference.longValue()) > Math.abs(newDifference)) {
/*      */         
/* 1713 */         currentDifference = Long.valueOf(newDifference);
/* 1714 */         currentOffsetIndex = i;
/*      */       } 
/*      */     } 
/* 1717 */     if (currentOffsetIndex > -1)
/*      */     {
/* 1719 */       newValue = ((Long)values.get(currentOffsetIndex)).longValue();
/*      */     }
/* 1721 */     return newValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean bfSearchForTrailer(COSDictionary trailer) throws IOException {
/* 1731 */     Map<String, COSDictionary> trailerDicts = new HashMap<String, COSDictionary>();
/* 1732 */     long originOffset = this.source.getPosition();
/* 1733 */     this.source.seek(6L);
/* 1734 */     while (!this.source.isEOF()) {
/*      */ 
/*      */       
/* 1737 */       if (isString(TRAILER_MARKER)) {
/*      */         
/* 1739 */         this.source.seek(this.source.getPosition() + TRAILER_MARKER.length);
/*      */         
/*      */         try {
/* 1742 */           boolean rootFound = false;
/* 1743 */           boolean infoFound = false;
/* 1744 */           skipSpaces();
/* 1745 */           COSDictionary trailerDict = parseCOSDictionary();
/* 1746 */           StringBuilder trailerKeys = new StringBuilder();
/* 1747 */           COSObject rootObj = trailerDict.getCOSObject(COSName.ROOT);
/* 1748 */           if (rootObj != null) {
/*      */             
/* 1750 */             long objNumber = rootObj.getObjectNumber();
/* 1751 */             int genNumber = rootObj.getGenerationNumber();
/* 1752 */             trailerKeys.append(objNumber).append(" ");
/* 1753 */             trailerKeys.append(genNumber).append(" ");
/* 1754 */             rootFound = true;
/*      */           } 
/* 1756 */           COSObject infoObj = trailerDict.getCOSObject(COSName.INFO);
/* 1757 */           if (infoObj != null) {
/*      */             
/* 1759 */             long objNumber = infoObj.getObjectNumber();
/* 1760 */             int genNumber = infoObj.getGenerationNumber();
/* 1761 */             trailerKeys.append(objNumber).append(" ");
/* 1762 */             trailerKeys.append(genNumber).append(" ");
/* 1763 */             infoFound = true;
/*      */           } 
/* 1765 */           if (rootFound && infoFound)
/*      */           {
/* 1767 */             trailerDicts.put(trailerKeys.toString(), trailerDict);
/*      */           }
/*      */         }
/* 1770 */         catch (IOException exception) {
/*      */           continue;
/*      */         } 
/*      */       } 
/*      */       
/* 1775 */       this.source.read();
/*      */     } 
/* 1777 */     this.source.seek(originOffset);
/*      */     
/* 1779 */     int trailerdictsSize = trailerDicts.size();
/* 1780 */     String firstEntry = null;
/* 1781 */     if (trailerdictsSize > 0) {
/*      */       
/* 1783 */       String[] keys = new String[trailerdictsSize];
/* 1784 */       trailerDicts.keySet().toArray((Object[])keys);
/* 1785 */       firstEntry = keys[0];
/* 1786 */       for (int i = 1; i < trailerdictsSize; i++) {
/*      */         
/* 1788 */         if (firstEntry.equals(keys[i]))
/*      */         {
/* 1790 */           trailerDicts.remove(keys[i]);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1795 */     if (trailerDicts.size() == 1) {
/*      */       
/* 1797 */       boolean rootFound = false;
/* 1798 */       boolean infoFound = false;
/* 1799 */       COSDictionary trailerDict = trailerDicts.get(firstEntry);
/* 1800 */       COSBase rootObj = trailerDict.getItem(COSName.ROOT);
/* 1801 */       if (rootObj instanceof COSObject) {
/*      */ 
/*      */         
/* 1804 */         COSDictionary rootDict = retrieveCOSDictionary((COSObject)rootObj);
/* 1805 */         if (rootDict != null && isCatalog(rootDict))
/*      */         {
/* 1807 */           rootFound = true;
/*      */         }
/*      */       } 
/* 1810 */       COSBase infoObj = trailerDict.getItem(COSName.INFO);
/* 1811 */       if (infoObj instanceof COSObject) {
/*      */ 
/*      */         
/* 1814 */         COSDictionary infoDict = retrieveCOSDictionary((COSObject)infoObj);
/* 1815 */         if (infoDict != null && isInfo(infoDict))
/*      */         {
/* 1817 */           infoFound = true;
/*      */         }
/*      */       } 
/* 1820 */       if (rootFound && infoFound) {
/*      */         
/* 1822 */         trailer.setItem(COSName.ROOT, rootObj);
/* 1823 */         trailer.setItem(COSName.INFO, infoObj);
/* 1824 */         if (trailerDict.containsKey(COSName.ENCRYPT)) {
/*      */           
/* 1826 */           COSBase encObj = trailerDict.getItem(COSName.ENCRYPT);
/* 1827 */           if (encObj instanceof COSObject) {
/*      */ 
/*      */ 
/*      */             
/* 1831 */             COSDictionary encDict = retrieveCOSDictionary((COSObject)encObj);
/* 1832 */             if (encDict != null)
/*      */             {
/* 1834 */               trailer.setItem(COSName.ENCRYPT, encObj);
/*      */             }
/*      */           } 
/*      */         } 
/* 1838 */         if (trailerDict.containsKey(COSName.ID)) {
/*      */           
/* 1840 */           COSBase idObj = trailerDict.getItem(COSName.ID);
/* 1841 */           if (idObj instanceof COSArray)
/*      */           {
/* 1843 */             trailer.setItem(COSName.ID, idObj);
/*      */           }
/*      */         } 
/* 1846 */         return true;
/*      */       } 
/*      */     } 
/* 1849 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void bfSearchForLastEOFMarker() throws IOException {
/* 1859 */     if (this.lastEOFMarker == null) {
/*      */       
/* 1861 */       long originOffset = this.source.getPosition();
/* 1862 */       this.source.seek(6L);
/* 1863 */       while (!this.source.isEOF()) {
/*      */ 
/*      */         
/* 1866 */         if (isString(EOF_MARKER)) {
/*      */           
/* 1868 */           long tempMarker = this.source.getPosition();
/* 1869 */           this.source.seek(tempMarker + 5L);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/* 1875 */             skipSpaces();
/* 1876 */             if (!isString(XREF_TABLE))
/*      */             {
/* 1878 */               readObjectNumber();
/* 1879 */               readGenerationNumber();
/*      */             }
/*      */           
/* 1882 */           } catch (IOException exception) {
/*      */ 
/*      */             
/* 1885 */             this.lastEOFMarker = Long.valueOf(tempMarker);
/*      */           } 
/*      */         } 
/* 1888 */         this.source.read();
/*      */       } 
/* 1890 */       this.source.seek(originOffset);
/*      */       
/* 1892 */       if (this.lastEOFMarker == null)
/*      */       {
/* 1894 */         this.lastEOFMarker = Long.valueOf(Long.MAX_VALUE);
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
/*      */   private void bfSearchForObjStreams() throws IOException {
/* 1906 */     HashMap<Long, COSObjectKey> bfSearchObjStreamsOffsets = new HashMap<Long, COSObjectKey>();
/* 1907 */     long originOffset = this.source.getPosition();
/* 1908 */     this.source.seek(6L);
/* 1909 */     char[] string = " obj".toCharArray();
/* 1910 */     while (!this.source.isEOF()) {
/*      */ 
/*      */       
/* 1913 */       if (isString(OBJ_STREAM)) {
/*      */         
/* 1915 */         long currentPosition = this.source.getPosition();
/*      */         
/* 1917 */         long newOffset = -1L;
/* 1918 */         boolean objFound = false;
/* 1919 */         for (int i = 1; i < 40 && !objFound; i++) {
/*      */           
/* 1921 */           long currentOffset = currentPosition - (i * 10);
/* 1922 */           if (currentOffset > 0L) {
/*      */             
/* 1924 */             this.source.seek(currentOffset);
/* 1925 */             for (int j = 0;; j++);
/*      */           } 
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
/*      */           continue;
/*      */         } 
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
/* 1971 */         this.source.seek(currentPosition + OBJ_STREAM.length);
/*      */       } 
/* 1973 */       this.source.read();
/*      */     } 
/*      */     
/* 1976 */     for (Long offset : bfSearchObjStreamsOffsets.keySet()) {
/*      */       
/* 1978 */       Long bfOffset = this.bfSearchCOSObjectKeyOffsets.get(bfSearchObjStreamsOffsets.get(offset));
/*      */       
/* 1980 */       if (bfOffset == null) {
/*      */         
/* 1982 */         LOG.warn("Skipped incomplete object stream:" + bfSearchObjStreamsOffsets.get(offset) + " at " + offset);
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/* 1987 */       if (offset.equals(bfOffset)) {
/*      */         
/* 1989 */         this.source.seek(offset.longValue());
/* 1990 */         long stmObjNumber = readObjectNumber();
/* 1991 */         int stmGenNumber = readGenerationNumber();
/* 1992 */         readExpectedString(OBJ_MARKER, true);
/* 1993 */         int nrOfObjects = 0;
/* 1994 */         byte[] numbersBytes = null;
/* 1995 */         COSStream stream = null;
/* 1996 */         COSInputStream cOSInputStream = null;
/*      */ 
/*      */         
/* 1999 */         try { COSDictionary dict = parseCOSDictionary();
/* 2000 */           int offsetFirstStream = dict.getInt(COSName.FIRST);
/* 2001 */           nrOfObjects = dict.getInt(COSName.N);
/*      */           
/* 2003 */           if (offsetFirstStream == -1 || nrOfObjects == -1)
/*      */           
/*      */           { 
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
/* 2024 */             if (cOSInputStream != null)
/*      */             {
/* 2026 */               cOSInputStream.close();
/*      */             }
/* 2028 */             if (stream != null)
/*      */             {
/* 2030 */               stream.close(); }  continue; }  stream = parseCOSStream(dict); if (this.securityHandler != null) this.securityHandler.decryptStream(stream, stmObjNumber, stmGenNumber);  cOSInputStream = stream.createInputStream(); numbersBytes = new byte[offsetFirstStream]; cOSInputStream.read(numbersBytes); } catch (IOException exception) { LOG.debug("Skipped corrupt stream: (" + stmObjNumber + " 0 at offset " + offset); continue; } finally { if (cOSInputStream != null) cOSInputStream.close();  if (stream != null) stream.close();
/*      */            }
/*      */         
/* 2033 */         int start = 0;
/*      */         
/* 2035 */         while (start < numbersBytes.length && numbersBytes[start] == 32)
/*      */         {
/* 2037 */           start++;
/*      */         }
/* 2039 */         String numbersStr = new String(numbersBytes, start, numbersBytes.length - start, "ISO-8859-1");
/*      */         
/* 2041 */         String str1 = numbersStr.replaceAll("\n", " ").replaceAll("  ", " ");
/* 2042 */         String[] numbers = str1.split(" ");
/* 2043 */         if (numbers.length < nrOfObjects * 2) {
/*      */           
/* 2045 */           LOG.debug("Skipped corrupt stream: (" + stmObjNumber + " 0 at offset " + offset);
/*      */           
/*      */           continue;
/*      */         } 
/* 2049 */         Map<COSObjectKey, Long> xrefOffset = this.xrefTrailerResolver.getXrefTable();
/* 2050 */         for (int i = 0; i < nrOfObjects; i++) {
/*      */ 
/*      */           
/*      */           try {
/* 2054 */             long objNumber = Long.parseLong(numbers[i * 2]);
/* 2055 */             COSObjectKey objKey = new COSObjectKey(objNumber, 0);
/* 2056 */             Long existingOffset = this.bfSearchCOSObjectKeyOffsets.get(objKey);
/* 2057 */             if (existingOffset != null && existingOffset.longValue() < 0L) {
/*      */ 
/*      */               
/* 2060 */               COSObjectKey objStmKey = new COSObjectKey(Math.abs(existingOffset.longValue()), 0);
/* 2061 */               existingOffset = this.bfSearchCOSObjectKeyOffsets.get(objStmKey);
/*      */             } 
/* 2063 */             if (existingOffset == null || offset.longValue() > existingOffset.longValue())
/*      */             {
/* 2065 */               this.bfSearchCOSObjectKeyOffsets.put(objKey, Long.valueOf(-stmObjNumber));
/* 2066 */               xrefOffset.put(objKey, Long.valueOf(-stmObjNumber));
/*      */             }
/*      */           
/* 2069 */           } catch (NumberFormatException exception) {
/*      */             NumberFormatException numberFormatException1;
/* 2071 */             LOG.debug("Skipped corrupt object key in stream: " + stmObjNumber);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2076 */     this.source.seek(originOffset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void bfSearchForXRefTables() throws IOException {
/* 2086 */     if (this.bfSearchXRefTablesOffsets == null) {
/*      */ 
/*      */       
/* 2089 */       this.bfSearchXRefTablesOffsets = new Vector<Long>();
/* 2090 */       long originOffset = this.source.getPosition();
/* 2091 */       this.source.seek(6L);
/*      */       
/* 2093 */       while (!this.source.isEOF()) {
/*      */         
/* 2095 */         if (isString(XREF_TABLE)) {
/*      */           
/* 2097 */           long newOffset = this.source.getPosition();
/* 2098 */           this.source.seek(newOffset - 1L);
/*      */           
/* 2100 */           if (isWhitespace())
/*      */           {
/* 2102 */             this.bfSearchXRefTablesOffsets.add(Long.valueOf(newOffset));
/*      */           }
/* 2104 */           this.source.seek(newOffset + 4L);
/*      */         } 
/* 2106 */         this.source.read();
/*      */       } 
/* 2108 */       this.source.seek(originOffset);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void bfSearchForXRefStreams() throws IOException {
/* 2119 */     if (this.bfSearchXRefStreamsOffsets == null) {
/*      */ 
/*      */       
/* 2122 */       this.bfSearchXRefStreamsOffsets = new Vector<Long>();
/* 2123 */       long originOffset = this.source.getPosition();
/* 2124 */       this.source.seek(6L);
/*      */       
/* 2126 */       String objString = " obj";
/* 2127 */       char[] string = objString.toCharArray();
/* 2128 */       while (!this.source.isEOF()) {
/*      */         
/* 2130 */         if (isString(XREF_STREAM)) {
/*      */ 
/*      */           
/* 2133 */           long newOffset = -1L;
/* 2134 */           long xrefOffset = this.source.getPosition();
/* 2135 */           boolean objFound = false;
/* 2136 */           for (int i = 1; i < 40 && !objFound; i++) {
/*      */             
/* 2138 */             long currentOffset = xrefOffset - (i * 10);
/* 2139 */             if (currentOffset > 0L) {
/*      */               
/* 2141 */               this.source.seek(currentOffset);
/* 2142 */               for (int j = 0;; j++);
/*      */             } 
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
/*      */             continue;
/*      */           } 
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
/* 2183 */           if (newOffset > -1L)
/*      */           {
/* 2185 */             this.bfSearchXRefStreamsOffsets.add(Long.valueOf(newOffset));
/*      */           }
/* 2187 */           this.source.seek(xrefOffset + 5L);
/*      */         } 
/* 2189 */         this.source.read();
/*      */       } 
/* 2191 */       this.source.seek(originOffset);
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
/*      */   protected final COSDictionary rebuildTrailer() throws IOException {
/* 2204 */     COSDictionary trailer = null;
/* 2205 */     bfSearchForObjects();
/* 2206 */     if (this.bfSearchCOSObjectKeyOffsets != null) {
/*      */ 
/*      */       
/* 2209 */       this.xrefTrailerResolver.reset();
/*      */       
/* 2211 */       this.xrefTrailerResolver.nextXrefObj(0L, XrefTrailerResolver.XRefType.TABLE);
/* 2212 */       for (Map.Entry<COSObjectKey, Long> entry : this.bfSearchCOSObjectKeyOffsets.entrySet())
/*      */       {
/* 2214 */         this.xrefTrailerResolver.setXRef(entry.getKey(), ((Long)entry.getValue()).longValue());
/*      */       }
/* 2216 */       this.xrefTrailerResolver.setStartxref(0L);
/* 2217 */       trailer = this.xrefTrailerResolver.getTrailer();
/* 2218 */       getDocument().setTrailer(trailer);
/* 2219 */       boolean searchForObjStreamsDone = false;
/* 2220 */       if (!bfSearchForTrailer(trailer))
/*      */       {
/*      */         
/* 2223 */         if (!searchForTrailerItems(trailer)) {
/*      */ 
/*      */           
/* 2226 */           bfSearchForObjStreams();
/* 2227 */           searchForObjStreamsDone = true;
/*      */           
/* 2229 */           searchForTrailerItems(trailer);
/*      */         } 
/*      */       }
/*      */       
/* 2233 */       prepareDecryption();
/* 2234 */       if (!searchForObjStreamsDone)
/*      */       {
/* 2236 */         bfSearchForObjStreams();
/*      */       }
/*      */     } 
/* 2239 */     this.trailerWasRebuild = true;
/* 2240 */     return trailer;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean searchForTrailerItems(COSDictionary trailer) throws IOException {
/* 2245 */     boolean rootFound = false;
/* 2246 */     for (Map.Entry<COSObjectKey, Long> entry : this.bfSearchCOSObjectKeyOffsets.entrySet()) {
/*      */       
/* 2248 */       COSDictionary dictionary = retrieveCOSDictionary(entry.getKey(), ((Long)entry.getValue()).longValue());
/* 2249 */       if (dictionary == null) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */       
/* 2254 */       if (isCatalog(dictionary)) {
/*      */         
/* 2256 */         trailer.setItem(COSName.ROOT, (COSBase)this.document.getObjectFromPool(entry.getKey()));
/* 2257 */         rootFound = true;
/*      */         continue;
/*      */       } 
/* 2260 */       if (isInfo(dictionary))
/*      */       {
/* 2262 */         trailer.setItem(COSName.INFO, (COSBase)this.document.getObjectFromPool(entry.getKey()));
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2267 */     return rootFound;
/*      */   }
/*      */ 
/*      */   
/*      */   private COSDictionary retrieveCOSDictionary(COSObject object) throws IOException {
/* 2272 */     COSObjectKey key = new COSObjectKey(object);
/* 2273 */     Long offset = this.bfSearchCOSObjectKeyOffsets.get(key);
/* 2274 */     if (offset != null)
/*      */     {
/* 2276 */       return retrieveCOSDictionary(key, offset.longValue());
/*      */     }
/* 2278 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private COSDictionary retrieveCOSDictionary(COSObjectKey key, long offset) throws IOException {
/* 2283 */     COSDictionary dictionary = null;
/*      */     
/* 2285 */     if (offset < 0L) {
/*      */       
/* 2287 */       COSObject compressedObject = this.document.getObjectFromPool(key);
/* 2288 */       if (compressedObject.getObject() == null)
/*      */       {
/* 2290 */         parseObjectStream((int)-offset);
/*      */       }
/* 2292 */       COSBase baseObject = compressedObject.getObject();
/* 2293 */       if (baseObject instanceof COSDictionary)
/*      */       {
/* 2295 */         dictionary = (COSDictionary)baseObject;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 2300 */       this.source.seek(offset);
/* 2301 */       readObjectNumber();
/* 2302 */       readGenerationNumber();
/* 2303 */       readExpectedString(OBJ_MARKER, true);
/* 2304 */       if (this.source.peek() != 60)
/*      */       {
/* 2306 */         return null;
/*      */       }
/*      */       
/*      */       try {
/* 2310 */         dictionary = parseCOSDictionary();
/*      */       }
/* 2312 */       catch (IOException exception) {
/*      */         
/* 2314 */         LOG.debug("Skipped object " + key + ", either it's corrupt or not a dictionary");
/*      */       } 
/*      */     } 
/*      */     
/* 2318 */     return dictionary;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkPages(COSDictionary root) {
/* 2328 */     if (this.trailerWasRebuild && root != null) {
/*      */ 
/*      */       
/* 2331 */       COSBase pages = root.getDictionaryObject(COSName.PAGES);
/* 2332 */       if (pages instanceof COSDictionary)
/*      */       {
/* 2334 */         checkPagesDictionary((COSDictionary)pages, new HashSet<COSObject>());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int checkPagesDictionary(COSDictionary pagesDict, Set<COSObject> set) {
/* 2342 */     COSBase kids = pagesDict.getDictionaryObject(COSName.KIDS);
/* 2343 */     int numberOfPages = 0;
/* 2344 */     if (kids instanceof COSArray) {
/*      */       
/* 2346 */       COSArray kidsArray = (COSArray)kids;
/* 2347 */       List<? extends COSBase> kidsList = kidsArray.toList();
/* 2348 */       for (COSBase kid : kidsList) {
/*      */         
/* 2350 */         if (!(kid instanceof COSObject) || set.contains(kid)) {
/*      */           
/* 2352 */           kidsArray.remove(kid);
/*      */           continue;
/*      */         } 
/* 2355 */         COSObject kidObject = (COSObject)kid;
/* 2356 */         COSBase kidBaseobject = kidObject.getObject();
/*      */         
/* 2358 */         if (kidBaseobject == null || kidBaseobject.equals(COSNull.NULL)) {
/*      */           
/* 2360 */           LOG.warn("Removed null object " + kid + " from pages dictionary");
/* 2361 */           kidsArray.remove(kid); continue;
/*      */         } 
/* 2363 */         if (kidBaseobject instanceof COSDictionary) {
/*      */           
/* 2365 */           COSDictionary kidDictionary = (COSDictionary)kidBaseobject;
/* 2366 */           COSName type = kidDictionary.getCOSName(COSName.TYPE);
/* 2367 */           if (COSName.PAGES.equals(type)) {
/*      */ 
/*      */             
/* 2370 */             set.add(kidObject);
/* 2371 */             numberOfPages += checkPagesDictionary(kidDictionary, set); continue;
/*      */           } 
/* 2373 */           if (COSName.PAGE.equals(type))
/*      */           {
/*      */             
/* 2376 */             numberOfPages++;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2382 */     pagesDict.setInt(COSName.COUNT, numberOfPages);
/* 2383 */     return numberOfPages;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isCatalog(COSDictionary dictionary) {
/* 2394 */     return COSName.CATALOG.equals(dictionary.getCOSName(COSName.TYPE));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isInfo(COSDictionary dictionary) {
/* 2405 */     if (dictionary.containsKey(COSName.PARENT) || dictionary.containsKey(COSName.A) || dictionary.containsKey(COSName.DEST))
/*      */     {
/* 2407 */       return false;
/*      */     }
/* 2409 */     if (!dictionary.containsKey(COSName.MOD_DATE) && !dictionary.containsKey(COSName.TITLE) && 
/* 2410 */       !dictionary.containsKey(COSName.AUTHOR) && 
/* 2411 */       !dictionary.containsKey(COSName.SUBJECT) && 
/* 2412 */       !dictionary.containsKey(COSName.KEYWORDS) && 
/* 2413 */       !dictionary.containsKey(COSName.CREATOR) && 
/* 2414 */       !dictionary.containsKey(COSName.PRODUCER) && 
/* 2415 */       !dictionary.containsKey(COSName.CREATION_DATE))
/*      */     {
/* 2417 */       return false;
/*      */     }
/* 2419 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long parseStartXref() throws IOException {
/* 2430 */     long startXref = -1L;
/* 2431 */     if (isString(STARTXREF)) {
/*      */       
/* 2433 */       readString();
/* 2434 */       skipSpaces();
/*      */       
/* 2436 */       startXref = readLong();
/*      */     } 
/* 2438 */     return startXref;
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
/*      */   private boolean isString(byte[] string) throws IOException {
/* 2450 */     boolean bytesMatching = false;
/* 2451 */     if (this.source.peek() == string[0]) {
/*      */       
/* 2453 */       int length = string.length;
/* 2454 */       byte[] bytesRead = new byte[length];
/* 2455 */       int numberOfBytes = this.source.read(bytesRead, 0, length);
/* 2456 */       while (numberOfBytes < length) {
/*      */         
/* 2458 */         int readMore = this.source.read(bytesRead, numberOfBytes, length - numberOfBytes);
/* 2459 */         if (readMore < 0) {
/*      */           break;
/*      */         }
/*      */         
/* 2463 */         numberOfBytes += readMore;
/*      */       } 
/* 2465 */       bytesMatching = Arrays.equals(string, bytesRead);
/* 2466 */       this.source.rewind(numberOfBytes);
/*      */     } 
/* 2468 */     return bytesMatching;
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
/*      */   private boolean isString(char[] string) throws IOException {
/* 2480 */     boolean bytesMatching = true;
/* 2481 */     long originOffset = this.source.getPosition();
/* 2482 */     for (char c : string) {
/*      */       
/* 2484 */       if (this.source.read() != c) {
/*      */         
/* 2486 */         bytesMatching = false;
/*      */         break;
/*      */       } 
/*      */     } 
/* 2490 */     this.source.seek(originOffset);
/* 2491 */     return bytesMatching;
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
/*      */   private boolean parseTrailer() throws IOException {
/* 2503 */     this.trailerOffset = this.source.getPosition();
/*      */     
/* 2505 */     if (this.isLenient) {
/*      */       
/* 2507 */       int nextCharacter = this.source.peek();
/* 2508 */       while (nextCharacter != 116 && isDigit(nextCharacter)) {
/*      */         
/* 2510 */         if (this.source.getPosition() == this.trailerOffset)
/*      */         {
/*      */           
/* 2513 */           LOG.warn("Expected trailer object at offset " + this.trailerOffset + ", keep trying");
/*      */         }
/*      */         
/* 2516 */         readLine();
/* 2517 */         nextCharacter = this.source.peek();
/*      */       } 
/*      */     } 
/* 2520 */     if (this.source.peek() != 116)
/*      */     {
/* 2522 */       return false;
/*      */     }
/*      */     
/* 2525 */     long currentOffset = this.source.getPosition();
/* 2526 */     String nextLine = readLine();
/* 2527 */     if (!nextLine.trim().equals("trailer"))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2533 */       if (nextLine.startsWith("trailer")) {
/*      */ 
/*      */         
/* 2536 */         int len = "trailer".length();
/*      */         
/* 2538 */         this.source.seek(currentOffset + len);
/*      */       }
/*      */       else {
/*      */         
/* 2542 */         return false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2549 */     skipSpaces();
/*      */     
/* 2551 */     COSDictionary parsedTrailer = parseCOSDictionary();
/* 2552 */     this.xrefTrailerResolver.setTrailer(parsedTrailer);
/*      */     
/* 2554 */     skipSpaces();
/* 2555 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean parsePDFHeader() throws IOException {
/* 2566 */     return parseHeader("%PDF-", "1.4");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean parseFDFHeader() throws IOException {
/* 2577 */     return parseHeader("%FDF-", "1.0");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean parseHeader(String headerMarker, String defaultVersion) throws IOException {
/* 2583 */     String header = readLine();
/*      */     
/* 2585 */     if (!header.contains(headerMarker)) {
/*      */       
/* 2587 */       header = readLine();
/* 2588 */       while (!header.contains(headerMarker)) {
/*      */ 
/*      */         
/* 2591 */         if (header.length() > 0 && Character.isDigit(header.charAt(0))) {
/*      */           break;
/*      */         }
/*      */         
/* 2595 */         header = readLine();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2600 */     if (!header.contains(headerMarker)) {
/*      */       
/* 2602 */       this.source.seek(0L);
/* 2603 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2608 */     int headerStart = header.indexOf(headerMarker);
/*      */ 
/*      */     
/* 2611 */     if (headerStart > 0)
/*      */     {
/*      */       
/* 2614 */       header = header.substring(headerStart, header.length());
/*      */     }
/*      */ 
/*      */     
/* 2618 */     if (header.startsWith(headerMarker) && !header.matches(headerMarker + "\\d.\\d"))
/*      */     {
/* 2620 */       if (header.length() < headerMarker.length() + 3) {
/*      */ 
/*      */         
/* 2623 */         header = headerMarker + defaultVersion;
/* 2624 */         LOG.debug("No version found, set to " + defaultVersion + " as default.");
/*      */       }
/*      */       else {
/*      */         
/* 2628 */         String headerGarbage = header.substring(headerMarker.length() + 3, header.length()) + "\n";
/* 2629 */         header = header.substring(0, headerMarker.length() + 3);
/* 2630 */         this.source.rewind((headerGarbage.getBytes(Charsets.ISO_8859_1)).length);
/*      */       } 
/*      */     }
/* 2633 */     float headerVersion = -1.0F;
/*      */     
/*      */     try {
/* 2636 */       String[] headerParts = header.split("-");
/* 2637 */       if (headerParts.length == 2)
/*      */       {
/* 2639 */         headerVersion = Float.parseFloat(headerParts[1]);
/*      */       }
/*      */     }
/* 2642 */     catch (NumberFormatException exception) {
/*      */       
/* 2644 */       LOG.debug("Can't parse the header version.", exception);
/*      */     } 
/* 2646 */     if (headerVersion < 0.0F)
/*      */     {
/* 2648 */       if (this.isLenient) {
/*      */         
/* 2650 */         headerVersion = 1.7F;
/*      */       }
/*      */       else {
/*      */         
/* 2654 */         throw new IOException("Error getting header version: " + header);
/*      */       } 
/*      */     }
/* 2657 */     this.document.setVersion(headerVersion);
/*      */     
/* 2659 */     this.source.seek(0L);
/* 2660 */     return true;
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
/*      */   protected boolean parseXrefTable(long startByteOffset) throws IOException {
/* 2672 */     if (this.source.peek() != 120)
/*      */     {
/* 2674 */       return false;
/*      */     }
/* 2676 */     String xref = readString();
/* 2677 */     if (!xref.trim().equals("xref"))
/*      */     {
/* 2679 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 2683 */     String str = readString();
/* 2684 */     byte[] b = str.getBytes(Charsets.ISO_8859_1);
/* 2685 */     this.source.rewind(b.length);
/*      */ 
/*      */     
/* 2688 */     this.xrefTrailerResolver.nextXrefObj(startByteOffset, XrefTrailerResolver.XRefType.TABLE);
/*      */     
/* 2690 */     if (str.startsWith("trailer")) {
/*      */       
/* 2692 */       LOG.warn("skipping empty xref table");
/* 2693 */       return false;
/*      */     } 
/*      */     
/*      */     do {
/*      */       long currObjID;
/*      */       
/* 2699 */       String currentLine = readLine();
/* 2700 */       String[] splitString = currentLine.split("\\s");
/* 2701 */       if (splitString.length != 2) {
/*      */         
/* 2703 */         LOG.warn("Unexpected XRefTable Entry: " + currentLine);
/* 2704 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2710 */         currObjID = Long.parseLong(splitString[0]);
/*      */       }
/* 2712 */       catch (NumberFormatException exception) {
/*      */         
/* 2714 */         LOG.warn("XRefTable: invalid ID for the first object: " + currentLine);
/* 2715 */         return false;
/*      */       } 
/*      */ 
/*      */       
/* 2719 */       int count = 0;
/*      */       
/*      */       try {
/* 2722 */         count = Integer.parseInt(splitString[1]);
/*      */       }
/* 2724 */       catch (NumberFormatException exception) {
/*      */         
/* 2726 */         LOG.warn("XRefTable: invalid number of objects: " + currentLine);
/* 2727 */         return false;
/*      */       } 
/*      */       
/* 2730 */       skipSpaces();
/* 2731 */       for (int i = 0; i < count; i++) {
/*      */         
/* 2733 */         if (this.source.isEOF() || isEndOfName((char)this.source.peek())) {
/*      */           break;
/*      */         }
/*      */         
/* 2737 */         if (this.source.peek() == 116) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/* 2742 */         currentLine = readLine();
/* 2743 */         splitString = currentLine.split("\\s");
/* 2744 */         if (splitString.length < 3) {
/*      */           
/* 2746 */           LOG.warn("invalid xref line: " + currentLine);
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/* 2751 */         if (splitString[splitString.length - 1].equals("n")) {
/*      */           
/*      */           try
/*      */           {
/* 2755 */             long currOffset = Long.parseLong(splitString[0]);
/* 2756 */             int currGenID = Integer.parseInt(splitString[1]);
/* 2757 */             COSObjectKey objKey = new COSObjectKey(currObjID, currGenID);
/* 2758 */             this.xrefTrailerResolver.setXRef(objKey, currOffset);
/*      */           }
/* 2760 */           catch (NumberFormatException e)
/*      */           {
/* 2762 */             throw new IOException(e);
/*      */           }
/*      */         
/* 2765 */         } else if (!splitString[2].equals("f")) {
/*      */           
/* 2767 */           throw new IOException("Corrupt XRefTable Entry - ObjID:" + currObjID);
/*      */         } 
/* 2769 */         currObjID++;
/* 2770 */         skipSpaces();
/*      */       } 
/* 2772 */       skipSpaces();
/* 2773 */     } while (isDigit());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2778 */     return true;
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
/*      */   private void parseXrefStream(COSStream stream, long objByteOffset, boolean isStandalone) throws IOException {
/* 2793 */     if (isStandalone) {
/*      */       
/* 2795 */       this.xrefTrailerResolver.nextXrefObj(objByteOffset, XrefTrailerResolver.XRefType.STREAM);
/* 2796 */       this.xrefTrailerResolver.setTrailer((COSDictionary)stream);
/*      */     } 
/* 2798 */     PDFXrefStreamParser parser = new PDFXrefStreamParser(stream, this.document, this.xrefTrailerResolver);
/* 2799 */     parser.parse();
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
/*      */   public COSDocument getDocument() throws IOException {
/* 2812 */     if (this.document == null)
/*      */     {
/* 2814 */       throw new IOException("You must parse the document first before calling getDocument()");
/*      */     }
/* 2816 */     return this.document;
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
/*      */   public PDEncryption getEncryption() throws IOException {
/* 2828 */     if (this.document == null)
/*      */     {
/* 2830 */       throw new IOException("You must parse the document first before calling getEncryption()");
/*      */     }
/*      */     
/* 2833 */     return this.encryption;
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
/*      */   public AccessPermission getAccessPermission() throws IOException {
/* 2845 */     if (this.document == null)
/*      */     {
/* 2847 */       throw new IOException("You must parse the document first before calling getAccessPermission()");
/*      */     }
/*      */     
/* 2850 */     return this.accessPermission;
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
/*      */   protected COSBase parseTrailerValuesDynamically(COSDictionary trailer) throws IOException {
/* 2864 */     for (COSBase trailerEntry : trailer.getValues()) {
/*      */       
/* 2866 */       if (trailerEntry instanceof COSObject) {
/*      */         
/* 2868 */         COSObject tmpObj = (COSObject)trailerEntry;
/* 2869 */         parseObjectDynamically(tmpObj, false);
/*      */       } 
/*      */     } 
/*      */     
/* 2873 */     COSObject root = trailer.getCOSObject(COSName.ROOT);
/* 2874 */     if (root == null)
/*      */     {
/* 2876 */       throw new IOException("Missing root object specification in trailer.");
/*      */     }
/* 2878 */     return root.getObject();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareDecryption() throws InvalidPasswordException, IOException {
/* 2889 */     if (this.encryption == null) {
/*      */       
/* 2891 */       COSBase trailerEncryptItem = this.document.getTrailer().getItem(COSName.ENCRYPT);
/* 2892 */       if (trailerEncryptItem != null && !(trailerEncryptItem instanceof COSNull)) {
/*      */         
/* 2894 */         if (trailerEncryptItem instanceof COSObject) {
/*      */           
/* 2896 */           COSObject trailerEncryptObj = (COSObject)trailerEncryptItem;
/* 2897 */           parseDictionaryRecursive(trailerEncryptObj);
/*      */         } 
/*      */         try {
/*      */           StandardDecryptionMaterial standardDecryptionMaterial;
/* 2901 */           this.encryption = new PDEncryption(this.document.getEncryptionDictionary());
/*      */           
/* 2903 */           if (this.keyStoreInputStream != null) {
/*      */             
/* 2905 */             KeyStore ks = KeyStore.getInstance("PKCS12");
/* 2906 */             ks.load(this.keyStoreInputStream, this.password.toCharArray());
/*      */             
/* 2908 */             PublicKeyDecryptionMaterial publicKeyDecryptionMaterial = new PublicKeyDecryptionMaterial(ks, this.keyAlias, this.password);
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 2913 */             standardDecryptionMaterial = new StandardDecryptionMaterial(this.password);
/*      */           } 
/*      */           
/* 2916 */           this.securityHandler = this.encryption.getSecurityHandler();
/* 2917 */           this.securityHandler.prepareForDecryption(this.encryption, this.document.getDocumentID(), (DecryptionMaterial)standardDecryptionMaterial);
/*      */           
/* 2919 */           this.accessPermission = this.securityHandler.getCurrentAccessPermission();
/*      */         }
/* 2921 */         catch (IOException e) {
/*      */           
/* 2923 */           throw e;
/*      */         }
/* 2925 */         catch (Exception e) {
/*      */           
/* 2927 */           throw new IOException("Error (" + e.getClass().getSimpleName() + ") while creating security handler for decryption", e);
/*      */         
/*      */         }
/*      */         finally {
/*      */           
/* 2932 */           if (this.keyStoreInputStream != null)
/*      */           {
/* 2934 */             IOUtils.closeQuietly(this.keyStoreInputStream);
/*      */           }
/*      */         } 
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
/*      */   private void parseDictionaryRecursive(COSObject dictionaryObject) throws IOException {
/* 2950 */     parseObjectDynamically(dictionaryObject, true);
/* 2951 */     if (!(dictionaryObject.getObject() instanceof COSDictionary))
/*      */     {
/*      */ 
/*      */       
/* 2955 */       throw new IOException("Dictionary object expected at offset " + this.source.getPosition());
/*      */     }
/* 2957 */     COSDictionary dictionary = (COSDictionary)dictionaryObject.getObject();
/* 2958 */     for (COSBase value : dictionary.getValues()) {
/*      */       
/* 2960 */       if (value instanceof COSObject) {
/*      */         
/* 2962 */         COSObject object = (COSObject)value;
/* 2963 */         if (object.getObject() == null)
/*      */         {
/* 2965 */           parseDictionaryRecursive(object);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/COSParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */