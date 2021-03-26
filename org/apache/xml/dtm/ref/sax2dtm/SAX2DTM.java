/*      */ package org.apache.xml.dtm.ref.sax2dtm;
/*      */ 
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBase;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBaseIterators;
/*      */ import org.apache.xml.dtm.ref.DTMManagerDefault;
/*      */ import org.apache.xml.dtm.ref.DTMStringPool;
/*      */ import org.apache.xml.dtm.ref.DTMTreeWalker;
/*      */ import org.apache.xml.dtm.ref.IncrementalSAXSource;
/*      */ import org.apache.xml.dtm.ref.NodeLocator;
/*      */ import org.apache.xml.res.XMLMessages;
/*      */ import org.apache.xml.utils.FastStringBuffer;
/*      */ import org.apache.xml.utils.IntStack;
/*      */ import org.apache.xml.utils.IntVector;
/*      */ import org.apache.xml.utils.StringVector;
/*      */ import org.apache.xml.utils.SuballocatedIntVector;
/*      */ import org.apache.xml.utils.SystemIDResolver;
/*      */ import org.apache.xml.utils.WrappedRuntimeException;
/*      */ import org.apache.xml.utils.XMLString;
/*      */ import org.apache.xml.utils.XMLStringFactory;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.DTDHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.ext.DeclHandler;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SAX2DTM
/*      */   extends DTMDefaultBaseIterators
/*      */   implements ContentHandler, DTDHandler, EntityResolver, ErrorHandler, DeclHandler, LexicalHandler
/*      */ {
/*      */   private static final boolean DEBUG = false;
/*   65 */   private IncrementalSAXSource m_incrementalSAXSource = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected FastStringBuffer m_chars;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SuballocatedIntVector m_data;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient IntStack m_parents;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   95 */   protected transient int m_previous = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  100 */   protected transient Vector m_prefixMappings = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient IntStack m_contextIndexes;
/*      */ 
/*      */ 
/*      */   
/*  109 */   protected transient int m_textType = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  115 */   protected transient int m_coalescedTextType = 3;
/*      */ 
/*      */   
/*  118 */   protected transient Locator m_locator = null;
/*      */ 
/*      */   
/*  121 */   private transient String m_systemId = null;
/*      */ 
/*      */   
/*      */   protected transient boolean m_insideDTD = false;
/*      */ 
/*      */   
/*  127 */   protected DTMTreeWalker m_walker = new DTMTreeWalker();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected DTMStringPool m_valuesOrPrefixes;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_endDocumentOccured = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected SuballocatedIntVector m_dataOrQName;
/*      */ 
/*      */ 
/*      */   
/*  144 */   protected Hashtable m_idAttributes = new Hashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  149 */   static final String[] m_fixednames = new String[] { null, null, null, "#text", "#cdata_section", null, null, null, "#comment", "#document", null, "#document-fragment", null };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  161 */   private Vector m_entities = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ENTITY_FIELD_PUBLICID = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ENTITY_FIELD_SYSTEMID = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ENTITY_FIELD_NOTATIONNAME = 2;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ENTITY_FIELD_NAME = 3;
/*      */ 
/*      */   
/*      */   private static final int ENTITY_FIELDS_PER = 4;
/*      */ 
/*      */   
/*  183 */   protected int m_textPendingStart = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_useSourceLocationProperty = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean m_source_location = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected StringVector m_sourceSystemId;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected IntVector m_sourceLine;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected IntVector m_sourceColumn;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean m_pastFirstElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SAX2DTM(DTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing) {
/*  227 */     this(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, 512, true, false);
/*      */   } public static void setUseSourceLocation(boolean useSourceLocation) { m_source_location = useSourceLocation; } protected int _dataOrQName(int identity) { if (identity < ((DTMDefaultBase)this).m_size)
/*      */       return this.m_dataOrQName.elementAt(identity);  while (true) { boolean isMore = nextNode(); if (!isMore)
/*      */         return -1;  if (identity < ((DTMDefaultBase)this).m_size)
/*      */         return this.m_dataOrQName.elementAt(identity);  }  }
/*      */   public void clearCoRoutine() { clearCoRoutine(true); }
/*      */   public void clearCoRoutine(boolean callDoTerminate) { if (null != this.m_incrementalSAXSource) { if (callDoTerminate)
/*      */         this.m_incrementalSAXSource.deliverMoreNodes(false);  this.m_incrementalSAXSource = null; }  }
/*      */   public void setIncrementalSAXSource(IncrementalSAXSource incrementalSAXSource) { this.m_incrementalSAXSource = incrementalSAXSource; incrementalSAXSource.setContentHandler(this); incrementalSAXSource.setLexicalHandler(this); incrementalSAXSource.setDTDHandler(this); }
/*      */   public ContentHandler getContentHandler() { if (this.m_incrementalSAXSource instanceof org.apache.xml.dtm.ref.IncrementalSAXSource_Filter)
/*      */       return (ContentHandler)this.m_incrementalSAXSource;  return this; }
/*      */   public LexicalHandler getLexicalHandler() { if (this.m_incrementalSAXSource instanceof org.apache.xml.dtm.ref.IncrementalSAXSource_Filter)
/*      */       return (LexicalHandler)this.m_incrementalSAXSource;  return this; }
/*      */   public EntityResolver getEntityResolver() { return this; }
/*      */   public DTDHandler getDTDHandler() { return this; }
/*      */   public ErrorHandler getErrorHandler() { return this; }
/*      */   public DeclHandler getDeclHandler() { return this; }
/*      */   public boolean needsTwoThreads() { return (null != this.m_incrementalSAXSource); }
/*      */   public void dispatchCharactersEvents(int nodeHandle, ContentHandler ch, boolean normalize) throws SAXException { int identity = makeNodeIdentity(nodeHandle); if (identity == -1)
/*      */       return;  int type = _type(identity); if (isTextType(type)) { int dataIndex = this.m_dataOrQName.elementAt(identity); int offset = this.m_data.elementAt(dataIndex); int length = this.m_data.elementAt(dataIndex + 1); if (normalize) { this.m_chars.sendNormalizedSAXcharacters(ch, offset, length); } else { this.m_chars.sendSAXcharacters(ch, offset, length); }  } else { int firstChild = _firstch(identity); if (-1 != firstChild) { int offset = -1; int length = 0; int startNode = identity; identity = firstChild; do { type = _type(identity); if (isTextType(type)) { int dataIndex = _dataOrQName(identity); if (-1 == offset)
/*      */               offset = this.m_data.elementAt(dataIndex);  length += this.m_data.elementAt(dataIndex + 1); }  identity = getNextNodeIdentity(identity); } while (-1 != identity && _parent(identity) >= startNode); if (length > 0)
/*      */           if (normalize) { this.m_chars.sendNormalizedSAXcharacters(ch, offset, length); } else { this.m_chars.sendSAXcharacters(ch, offset, length); }
/*      */             }
/*      */       else if (type != 1) { int dataIndex = _dataOrQName(identity); if (dataIndex < 0) { dataIndex = -dataIndex; dataIndex = this.m_data.elementAt(dataIndex + 1); }
/*      */          String str = this.m_valuesOrPrefixes.indexToString(dataIndex); if (normalize) { FastStringBuffer.sendNormalizedSAXcharacters(str.toCharArray(), 0, str.length(), ch); }
/*      */         else { ch.characters(str.toCharArray(), 0, str.length()); }
/*      */          }
/*      */        }
/*      */      }
/*  256 */   public SAX2DTM(DTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing, int blocksize, boolean usePrevsib, boolean newNameTable) { super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, blocksize, usePrevsib, newNameTable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1836 */     this.m_pastFirstElement = false; if (blocksize <= 64) { this.m_data = new SuballocatedIntVector(blocksize, 4); this.m_dataOrQName = new SuballocatedIntVector(blocksize, 4); this.m_valuesOrPrefixes = new DTMStringPool(16); this.m_chars = new FastStringBuffer(7, 10); this.m_contextIndexes = new IntStack(4); this.m_parents = new IntStack(4); }
/*      */     else { this.m_data = new SuballocatedIntVector(blocksize, 32); this.m_dataOrQName = new SuballocatedIntVector(blocksize, 32); this.m_valuesOrPrefixes = new DTMStringPool(); this.m_chars = new FastStringBuffer(10, 13); this.m_contextIndexes = new IntStack(); this.m_parents = new IntStack(); }
/*      */      this.m_data.addElement(0); this.m_useSourceLocationProperty = m_source_location; this.m_sourceSystemId = this.m_useSourceLocationProperty ? new StringVector() : null; this.m_sourceLine = this.m_useSourceLocationProperty ? new IntVector() : null; this.m_sourceColumn = this.m_useSourceLocationProperty ? new IntVector() : null; } public String getNodeName(int nodeHandle) { int expandedTypeID = getExpandedTypeID(nodeHandle); int namespaceID = ((DTMDefaultBase)this).m_expandedNameTable.getNamespaceID(expandedTypeID); if (0 == namespaceID) { int type = getNodeType(nodeHandle); if (type == 13) { if (null == ((DTMDefaultBase)this).m_expandedNameTable.getLocalName(expandedTypeID))
/*      */           return "xmlns";  return "xmlns:" + ((DTMDefaultBase)this).m_expandedNameTable.getLocalName(expandedTypeID); }
/*      */        if (0 == ((DTMDefaultBase)this).m_expandedNameTable.getLocalNameID(expandedTypeID))
/*      */         return m_fixednames[type];  return ((DTMDefaultBase)this).m_expandedNameTable.getLocalName(expandedTypeID); }
/*      */      int qnameIndex = this.m_dataOrQName.elementAt(makeNodeIdentity(nodeHandle)); if (qnameIndex < 0) { qnameIndex = -qnameIndex; qnameIndex = this.m_data.elementAt(qnameIndex); }
/*      */      return this.m_valuesOrPrefixes.indexToString(qnameIndex); }
/*      */   public String getNodeNameX(int nodeHandle) { int expandedTypeID = getExpandedTypeID(nodeHandle); int namespaceID = ((DTMDefaultBase)this).m_expandedNameTable.getNamespaceID(expandedTypeID); if (0 == namespaceID) { String name = ((DTMDefaultBase)this).m_expandedNameTable.getLocalName(expandedTypeID); if (name == null)
/*      */         return "";  return name; }
/*      */      int qnameIndex = this.m_dataOrQName.elementAt(makeNodeIdentity(nodeHandle)); if (qnameIndex < 0) { qnameIndex = -qnameIndex; qnameIndex = this.m_data.elementAt(qnameIndex); }
/*      */      return this.m_valuesOrPrefixes.indexToString(qnameIndex); }
/*      */   public boolean isAttributeSpecified(int attributeHandle) { return true; }
/*      */   public String getDocumentTypeDeclarationSystemIdentifier() { error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null)); return null; }
/*      */   protected int getNextNodeIdentity(int identity) { identity++; while (identity >= ((DTMDefaultBase)this).m_size) { if (null == this.m_incrementalSAXSource)
/*      */         return -1;  nextNode(); }
/*      */      return identity; }
/*      */   public void dispatchToEvents(int nodeHandle, ContentHandler ch) throws SAXException { DTMTreeWalker treeWalker = this.m_walker; ContentHandler prevCH = treeWalker.getcontentHandler(); if (null != prevCH)
/*      */       treeWalker = new DTMTreeWalker();  treeWalker.setcontentHandler(ch); treeWalker.setDTM((DTM)this); try { treeWalker.traverse(nodeHandle); }
/*      */     finally { treeWalker.setcontentHandler(null); }
/*      */      }
/*      */   public int getNumberOfNodes() { return ((DTMDefaultBase)this).m_size; }
/*      */   protected boolean nextNode() { if (null == this.m_incrementalSAXSource)
/*      */       return false;  if (this.m_endDocumentOccured) { clearCoRoutine(); return false; }
/*      */      Object gotMore = this.m_incrementalSAXSource.deliverMoreNodes(true); if (!(gotMore instanceof Boolean)) { if (gotMore instanceof RuntimeException)
/*      */         throw (RuntimeException)gotMore;  if (gotMore instanceof Exception)
/*      */         throw new WrappedRuntimeException((Exception)gotMore);  clearCoRoutine(); return false; }
/*      */      if (gotMore != Boolean.TRUE)
/*      */       clearCoRoutine();  return true; }
/*      */   private final boolean isTextType(int type) { return (3 == type || 4 == type); }
/*      */   protected int addNode(int type, int expandedTypeID, int parentIndex, int previousSibling, int dataOrPrefix, boolean canHaveFirstChild) { int nodeIndex = ((DTMDefaultBase)this).m_size++; if (((DTMDefaultBase)this).m_dtmIdent.size() == nodeIndex >>> 16)
/*      */       addNewDTMID(nodeIndex);  ((DTMDefaultBase)this).m_firstch.addElement(canHaveFirstChild ? -2 : -1); ((DTMDefaultBase)this).m_nextsib.addElement(-2); ((DTMDefaultBase)this).m_parent.addElement(parentIndex); ((DTMDefaultBase)this).m_exptype.addElement(expandedTypeID); this.m_dataOrQName.addElement(dataOrPrefix); if (((DTMDefaultBase)this).m_prevsib != null)
/*      */       ((DTMDefaultBase)this).m_prevsib.addElement(previousSibling);  if (-1 != previousSibling)
/*      */       ((DTMDefaultBase)this).m_nextsib.setElementAt(nodeIndex, previousSibling);  if (this.m_locator != null && this.m_useSourceLocationProperty)
/*      */       setSourceLocation();  switch (type) {
/*      */       case 13:
/*      */         declareNamespaceInContext(parentIndex, nodeIndex);
/*      */       case 2:
/*      */         return nodeIndex;
/*      */     }  if (-1 == previousSibling && -1 != parentIndex)
/*      */       ((DTMDefaultBase)this).m_firstch.setElementAt(nodeIndex, parentIndex);  }
/*      */   protected void addNewDTMID(int nodeIndex) { try {
/*      */       if (((DTMDefaultBase)this).m_mgr == null)
/*      */         throw new ClassCastException();  DTMManagerDefault mgrD = (DTMManagerDefault)((DTMDefaultBase)this).m_mgr; int id = mgrD.getFirstFreeDTMID(); mgrD.addDTM((DTM)this, id, nodeIndex); ((DTMDefaultBase)this).m_dtmIdent.addElement(id << 16);
/*      */     } catch (ClassCastException e) {
/*      */       error(XMLMessages.createXMLMessage("ER_NO_DTMIDS_AVAIL", null));
/*      */     }  }
/*      */   public void migrateTo(DTMManager manager) { super.migrateTo(manager); int numDTMs = ((DTMDefaultBase)this).m_dtmIdent.size(); int dtmId = ((DTMDefaultBase)this).m_mgrDefault.getFirstFreeDTMID(); int nodeIndex = 0; for (int i = 0; i < numDTMs; i++) {
/*      */       ((DTMDefaultBase)this).m_dtmIdent.setElementAt(dtmId << 16, i); ((DTMDefaultBase)this).m_mgrDefault.addDTM((DTM)this, dtmId, nodeIndex); dtmId++; nodeIndex += 65536;
/*      */     }  }
/* 1886 */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { charactersFlush();
/*      */     
/* 1888 */     int exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(uri, localName, 1);
/* 1889 */     String prefix = getPrefix(qName, uri);
/* 1890 */     int prefixIndex = (null != prefix) ? this.m_valuesOrPrefixes.stringToIndex(qName) : 0;
/*      */ 
/*      */     
/* 1893 */     int elemNode = addNode(1, exName, this.m_parents.peek(), this.m_previous, prefixIndex, true);
/*      */ 
/*      */     
/* 1896 */     if (((DTMDefaultBase)this).m_indexing) {
/* 1897 */       indexNode(exName, elemNode);
/*      */     }
/*      */     
/* 1900 */     this.m_parents.push(elemNode);
/*      */     
/* 1902 */     int startDecls = this.m_contextIndexes.peek();
/* 1903 */     int nDecls = this.m_prefixMappings.size();
/* 1904 */     int prev = -1;
/*      */     
/* 1906 */     if (!this.m_pastFirstElement) {
/*      */ 
/*      */       
/* 1909 */       prefix = "xml";
/* 1910 */       String declURL = "http://www.w3.org/XML/1998/namespace";
/* 1911 */       exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(null, prefix, 13);
/* 1912 */       int val = this.m_valuesOrPrefixes.stringToIndex(declURL);
/* 1913 */       prev = addNode(13, exName, elemNode, prev, val, false);
/*      */       
/* 1915 */       this.m_pastFirstElement = true;
/*      */     } 
/*      */     
/* 1918 */     for (int i = startDecls; i < nDecls; i += 2) {
/*      */       
/* 1920 */       prefix = this.m_prefixMappings.elementAt(i);
/*      */       
/* 1922 */       if (prefix != null) {
/*      */ 
/*      */         
/* 1925 */         String declURL = this.m_prefixMappings.elementAt(i + 1);
/*      */         
/* 1927 */         exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(null, prefix, 13);
/*      */         
/* 1929 */         int val = this.m_valuesOrPrefixes.stringToIndex(declURL);
/*      */         
/* 1931 */         prev = addNode(13, exName, elemNode, prev, val, false);
/*      */       } 
/*      */     } 
/*      */     
/* 1935 */     int n = attributes.getLength();
/*      */     
/* 1937 */     for (int j = 0; j < n; j++) {
/*      */       byte b;
/* 1939 */       String attrUri = attributes.getURI(j);
/* 1940 */       String attrQName = attributes.getQName(j);
/* 1941 */       String valString = attributes.getValue(j);
/*      */       
/* 1943 */       prefix = getPrefix(attrQName, attrUri);
/*      */ 
/*      */ 
/*      */       
/* 1947 */       String attrLocalName = attributes.getLocalName(j);
/*      */       
/* 1949 */       if (null != attrQName && (attrQName.equals("xmlns") || attrQName.startsWith("xmlns:"))) {
/*      */ 
/*      */ 
/*      */         
/* 1953 */         if (declAlreadyDeclared(prefix)) {
/*      */           continue;
/*      */         }
/* 1956 */         b = 13;
/*      */       }
/*      */       else {
/*      */         
/* 1960 */         b = 2;
/*      */         
/* 1962 */         if (attributes.getType(j).equalsIgnoreCase("ID")) {
/* 1963 */           setIDAttribute(valString, elemNode);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1968 */       if (null == valString) {
/* 1969 */         valString = "";
/*      */       }
/* 1971 */       int val = this.m_valuesOrPrefixes.stringToIndex(valString);
/*      */ 
/*      */       
/* 1974 */       if (null != prefix) {
/*      */ 
/*      */         
/* 1977 */         prefixIndex = this.m_valuesOrPrefixes.stringToIndex(attrQName);
/*      */         
/* 1979 */         int dataIndex = this.m_data.size();
/*      */         
/* 1981 */         this.m_data.addElement(prefixIndex);
/* 1982 */         this.m_data.addElement(val);
/*      */         
/* 1984 */         val = -dataIndex;
/*      */       } 
/*      */       
/* 1987 */       exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(attrUri, attrLocalName, b);
/* 1988 */       prev = addNode(b, exName, elemNode, prev, val, false);
/*      */       
/*      */       continue;
/*      */     } 
/* 1992 */     if (-1 != prev) {
/* 1993 */       ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, prev);
/*      */     }
/* 1995 */     if (null != ((DTMDefaultBase)this).m_wsfilter) {
/*      */       
/* 1997 */       short wsv = ((DTMDefaultBase)this).m_wsfilter.getShouldStripSpace(makeNodeHandle(elemNode), (DTM)this);
/* 1998 */       boolean shouldStrip = (3 == wsv) ? getShouldStripWhitespace() : ((2 == wsv));
/*      */ 
/*      */ 
/*      */       
/* 2002 */       pushShouldStripWhitespace(shouldStrip);
/*      */     } 
/*      */     
/* 2005 */     this.m_previous = -1;
/*      */     
/* 2007 */     this.m_contextIndexes.push(this.m_prefixMappings.size()); }
/*      */   protected void setSourceLocation() { this.m_sourceSystemId.addElement(this.m_locator.getSystemId()); this.m_sourceLine.addElement(this.m_locator.getLineNumber()); this.m_sourceColumn.addElement(this.m_locator.getColumnNumber()); if (this.m_sourceSystemId.size() != ((DTMDefaultBase)this).m_size) {
/*      */       System.err.println("CODING ERROR in Source Location: " + ((DTMDefaultBase)this).m_size + " != " + this.m_sourceSystemId.size()); System.exit(1);
/*      */     }  }
/*      */   public String getNodeValue(int nodeHandle) { int identity = makeNodeIdentity(nodeHandle); int type = _type(identity); if (isTextType(type)) {
/*      */       int i = _dataOrQName(identity); int offset = this.m_data.elementAt(i); int length = this.m_data.elementAt(i + 1); return this.m_chars.getString(offset, length);
/*      */     }  if (1 == type || 11 == type || 9 == type)
/*      */       return null;  int dataIndex = _dataOrQName(identity); if (dataIndex < 0) {
/*      */       dataIndex = -dataIndex; dataIndex = this.m_data.elementAt(dataIndex + 1);
/*      */     }  return this.m_valuesOrPrefixes.indexToString(dataIndex); }
/*      */   public String getLocalName(int nodeHandle) { return ((DTMDefaultBase)this).m_expandedNameTable.getLocalName(_exptype(makeNodeIdentity(nodeHandle))); }
/*      */   public String getUnparsedEntityURI(String name) { String url = ""; if (null == this.m_entities)
/*      */       return url;  int n = this.m_entities.size(); for (int i = 0; i < n; i += 4) {
/*      */       String ename = this.m_entities.elementAt(i + 3); if (null != ename && ename.equals(name)) {
/*      */         String nname = this.m_entities.elementAt(i + 2); if (null != nname) {
/*      */           url = this.m_entities.elementAt(i + 1); if (null == url)
/*      */             url = this.m_entities.elementAt(i + 0); 
/*      */         }  break;
/*      */       } 
/*      */     }  return url; }
/*      */   public String getPrefix(int nodeHandle) { int identity = makeNodeIdentity(nodeHandle); int type = _type(identity); if (1 == type) {
/*      */       int prefixIndex = _dataOrQName(identity); if (0 == prefixIndex)
/*      */         return "";  String qname = this.m_valuesOrPrefixes.indexToString(prefixIndex); return getPrefix(qname, null);
/*      */     }  if (2 == type) {
/*      */       int prefixIndex = _dataOrQName(identity); if (prefixIndex < 0) {
/*      */         prefixIndex = this.m_data.elementAt(-prefixIndex); String qname = this.m_valuesOrPrefixes.indexToString(prefixIndex); return getPrefix(qname, null);
/*      */       } 
/*      */     }  return ""; }
/*      */   public int getAttributeNode(int nodeHandle, String namespaceURI, String name) { for (int attrH = getFirstAttribute(nodeHandle); -1 != attrH; attrH = getNextAttribute(attrH)) {
/*      */       String attrNS = getNamespaceURI(attrH); String attrName = getLocalName(attrH); boolean nsMatch = (namespaceURI == attrNS || (namespaceURI != null && namespaceURI.equals(attrNS)));
/*      */       if (nsMatch && name.equals(attrName))
/*      */         return attrH; 
/*      */     } 
/* 2040 */     return -1; } public void endElement(String uri, String localName, String qName) throws SAXException { charactersFlush();
/*      */ 
/*      */ 
/*      */     
/* 2044 */     this.m_contextIndexes.quickPop(1);
/*      */ 
/*      */     
/* 2047 */     int topContextIndex = this.m_contextIndexes.peek();
/* 2048 */     if (topContextIndex != this.m_prefixMappings.size()) {
/* 2049 */       this.m_prefixMappings.setSize(topContextIndex);
/*      */     }
/*      */     
/* 2052 */     int lastNode = this.m_previous;
/*      */     
/* 2054 */     this.m_previous = this.m_parents.pop();
/*      */ 
/*      */     
/* 2057 */     if (-1 == lastNode) {
/* 2058 */       ((DTMDefaultBase)this).m_firstch.setElementAt(-1, this.m_previous);
/*      */     } else {
/* 2060 */       ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, lastNode);
/*      */     } 
/* 2062 */     popShouldStripWhitespace(); }
/*      */   public String getDocumentTypeDeclarationPublicIdentifier() { error(XMLMessages.createXMLMessage("ER_METHOD_NOT_SUPPORTED", null)); return null; }
/*      */   public String getNamespaceURI(int nodeHandle) { return ((DTMDefaultBase)this).m_expandedNameTable.getNamespace(_exptype(makeNodeIdentity(nodeHandle))); }
/*      */   public XMLString getStringValue(int nodeHandle) { int i, identity = makeNodeIdentity(nodeHandle); if (identity == -1) { i = -1; } else { i = _type(identity); }  if (isTextType(i)) { int dataIndex = _dataOrQName(identity); int offset = this.m_data.elementAt(dataIndex); int length = this.m_data.elementAt(dataIndex + 1); return ((DTMDefaultBase)this).m_xstrf.newstr(this.m_chars, offset, length); }  int firstChild = _firstch(identity); if (-1 != firstChild) { int offset = -1; int length = 0; int startNode = identity; identity = firstChild; do { i = _type(identity); if (isTextType(i)) { int dataIndex = _dataOrQName(identity); if (-1 == offset) offset = this.m_data.elementAt(dataIndex);  length += this.m_data.elementAt(dataIndex + 1); }  identity = getNextNodeIdentity(identity); } while (-1 != identity && _parent(identity) >= startNode); if (length > 0) return ((DTMDefaultBase)this).m_xstrf.newstr(this.m_chars, offset, length);  } else if (i != 1) { int dataIndex = _dataOrQName(identity); if (dataIndex < 0) { dataIndex = -dataIndex; dataIndex = this.m_data.elementAt(dataIndex + 1); }  return ((DTMDefaultBase)this).m_xstrf.newstr(this.m_valuesOrPrefixes.indexToString(dataIndex)); }  return ((DTMDefaultBase)this).m_xstrf.emptystr(); }
/*      */   public boolean isWhitespace(int nodeHandle) { int i, identity = makeNodeIdentity(nodeHandle); if (identity == -1) { i = -1; } else { i = _type(identity); }  if (isTextType(i)) { int dataIndex = _dataOrQName(identity); int offset = this.m_data.elementAt(dataIndex); int length = this.m_data.elementAt(dataIndex + 1); return this.m_chars.isWhitespace(offset, length); }  return false; }
/*      */   public int getElementById(String elementId) { Integer intObj; boolean isMore = true; do { intObj = (Integer)this.m_idAttributes.get(elementId); if (null != intObj) return makeNodeHandle(intObj.intValue());  if (!isMore || this.m_endDocumentOccured) break;  isMore = nextNode(); } while (null == intObj); return -1; }
/*      */   public String getPrefix(String qname, String uri) { String str; int uriIndex = -1; if (null != uri && uri.length() > 0) { do { uriIndex = this.m_prefixMappings.indexOf(uri, ++uriIndex); } while ((uriIndex & 0x1) == 0); if (uriIndex >= 0) { str = this.m_prefixMappings.elementAt(uriIndex - 1); } else if (null != qname) { int indexOfNSSep = qname.indexOf(':'); if (qname.equals("xmlns")) { str = ""; } else if (qname.startsWith("xmlns:")) { str = qname.substring(indexOfNSSep + 1); } else { str = (indexOfNSSep > 0) ? qname.substring(0, indexOfNSSep) : null; }  } else { str = null; }  } else if (null != qname) { int indexOfNSSep = qname.indexOf(':'); if (indexOfNSSep > 0) { if (qname.startsWith("xmlns:")) { str = qname.substring(indexOfNSSep + 1); } else { str = qname.substring(0, indexOfNSSep); }  } else if (qname.equals("xmlns")) { str = ""; } else { str = null; }  } else { str = null; }  return str; }
/*      */   public int getIdForNamespace(String uri) { return this.m_valuesOrPrefixes.stringToIndex(uri); }
/*      */   public String getNamespaceURI(String prefix) { String uri = ""; int prefixIndex = this.m_contextIndexes.peek() - 1; if (null == prefix) prefix = "";  do { prefixIndex = this.m_prefixMappings.indexOf(prefix, ++prefixIndex); } while (prefixIndex >= 0 && (prefixIndex & 0x1) == 1); if (prefixIndex > -1) uri = this.m_prefixMappings.elementAt(prefixIndex + 1);  return uri; }
/*      */   public void setIDAttribute(String id, int elem) { this.m_idAttributes.put(id, new Integer(elem)); }
/*      */   protected void charactersFlush() { if (this.m_textPendingStart >= 0) { int length = this.m_chars.size() - this.m_textPendingStart; boolean doStrip = false; if (getShouldStripWhitespace()) doStrip = this.m_chars.isWhitespace(this.m_textPendingStart, length);  if (doStrip) { this.m_chars.setLength(this.m_textPendingStart); } else { int exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(3); int dataIndex = this.m_data.size(); this.m_previous = addNode(this.m_coalescedTextType, exName, this.m_parents.peek(), this.m_previous, dataIndex, false); this.m_data.addElement(this.m_textPendingStart); this.m_data.addElement(length); }  this.m_textPendingStart = -1; this.m_textType = this.m_coalescedTextType = 3; }  }
/*      */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException { return null; }
/*      */   public void notationDecl(String name, String publicId, String systemId) throws SAXException {}
/*      */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException { if (null == this.m_entities) this.m_entities = new Vector();  try { systemId = SystemIDResolver.getAbsoluteURI(systemId, getDocumentBaseURI()); } catch (Exception e) { throw new SAXException(e); }  this.m_entities.addElement(publicId); this.m_entities.addElement(systemId); this.m_entities.addElement(notationName); this.m_entities.addElement(name); }
/*      */   public void setDocumentLocator(Locator locator) { this.m_locator = locator; this.m_systemId = locator.getSystemId(); }
/*      */   public void startDocument() throws SAXException { int doc = addNode(9, ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(9), -1, -1, 0, true); this.m_parents.push(doc); this.m_previous = -1; this.m_contextIndexes.push(this.m_prefixMappings.size()); }
/*      */   public void endDocument() throws SAXException { charactersFlush(); ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, 0); if (((DTMDefaultBase)this).m_firstch.elementAt(0) == -2) ((DTMDefaultBase)this).m_firstch.setElementAt(-1, 0);  if (-1 != this.m_previous) ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, this.m_previous);  this.m_parents = null; this.m_prefixMappings = null; this.m_contextIndexes = null; this.m_endDocumentOccured = true; this.m_locator = null; }
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException { if (null == prefix) prefix = "";  this.m_prefixMappings.addElement(prefix); this.m_prefixMappings.addElement(uri); }
/*      */   public void endPrefixMapping(String prefix) throws SAXException { if (null == prefix)
/*      */       prefix = "";  int index = this.m_contextIndexes.peek() - 1; do { index = this.m_prefixMappings.indexOf(prefix, ++index); } while (index >= 0 && (index & 0x1) == 1); if (index > -1) { this.m_prefixMappings.setElementAt("%@$#^@#", index); this.m_prefixMappings.setElementAt("%@$#^@#", index + 1); }  } protected boolean declAlreadyDeclared(String prefix) { int startDecls = this.m_contextIndexes.peek(); Vector prefixMappings = this.m_prefixMappings; int nDecls = prefixMappings.size(); for (int i = startDecls; i < nDecls; i += 2) { String prefixDecl = prefixMappings.elementAt(i); if (prefixDecl != null)
/*      */         if (prefixDecl.equals(prefix))
/* 2083 */           return true;   }  return false; } public void characters(char[] ch, int start, int length) throws SAXException { if (this.m_textPendingStart == -1) {
/*      */       
/* 2085 */       this.m_textPendingStart = this.m_chars.size();
/* 2086 */       this.m_coalescedTextType = this.m_textType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2092 */     else if (this.m_textType == 3) {
/*      */       
/* 2094 */       this.m_coalescedTextType = 3;
/*      */     } 
/*      */     
/* 2097 */     this.m_chars.append(ch, start, length); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 2122 */     characters(ch, start, length);
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
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/* 2146 */     charactersFlush();
/*      */     
/* 2148 */     int exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(null, target, 7);
/*      */     
/* 2150 */     int dataIndex = this.m_valuesOrPrefixes.stringToIndex(data);
/*      */     
/* 2152 */     this.m_previous = addNode(7, exName, this.m_parents.peek(), this.m_previous, dataIndex, false);
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
/*      */   public void skippedEntity(String name) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void warning(SAXParseException e) throws SAXException {
/* 2199 */     System.err.println(e.getMessage());
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
/*      */   public void error(SAXParseException e) throws SAXException {
/* 2218 */     throw e;
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
/*      */   public void fatalError(SAXParseException e) throws SAXException {
/* 2240 */     throw e;
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
/*      */   public void elementDecl(String name, String model) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void internalEntityDecl(String name, String value) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/* 2364 */     this.m_insideDTD = true;
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
/*      */   public void endDTD() throws SAXException {
/* 2376 */     this.m_insideDTD = false;
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
/*      */   public void startEntity(String name) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endEntity(String name) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startCDATA() throws SAXException {
/* 2432 */     this.m_textType = 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endCDATA() throws SAXException {
/* 2443 */     this.m_textType = 3;
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
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 2461 */     if (this.m_insideDTD) {
/*      */       return;
/*      */     }
/* 2464 */     charactersFlush();
/*      */     
/* 2466 */     int exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(8);
/*      */ 
/*      */ 
/*      */     
/* 2470 */     int dataIndex = this.m_valuesOrPrefixes.stringToIndex(new String(ch, start, length));
/*      */ 
/*      */ 
/*      */     
/* 2474 */     this.m_previous = addNode(8, exName, this.m_parents.peek(), this.m_previous, dataIndex, false);
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
/*      */   public void setProperty(String property, Object value) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SourceLocator getSourceLocatorFor(int node) {
/* 2501 */     if (this.m_useSourceLocationProperty) {
/*      */ 
/*      */       
/* 2504 */       node = makeNodeIdentity(node);
/*      */ 
/*      */       
/* 2507 */       return (SourceLocator)new NodeLocator(null, this.m_sourceSystemId.elementAt(node), this.m_sourceLine.elementAt(node), this.m_sourceColumn.elementAt(node));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2512 */     if (this.m_locator != null)
/*      */     {
/* 2514 */       return (SourceLocator)new NodeLocator(null, this.m_locator.getSystemId(), -1, -1);
/*      */     }
/* 2516 */     if (this.m_systemId != null)
/*      */     {
/* 2518 */       return (SourceLocator)new NodeLocator(null, this.m_systemId, -1, -1);
/*      */     }
/* 2520 */     return null;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/sax2dtm/SAX2DTM.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */