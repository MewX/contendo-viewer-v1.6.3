/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.filter.DecodeOptions;
/*     */ import org.apache.pdfbox.filter.Filter;
/*     */ import org.apache.pdfbox.filter.FilterFactory;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.io.RandomAccess;
/*     */ import org.apache.pdfbox.io.RandomAccessInputStream;
/*     */ import org.apache.pdfbox.io.RandomAccessOutputStream;
/*     */ import org.apache.pdfbox.io.RandomAccessRead;
/*     */ import org.apache.pdfbox.io.RandomAccessWrite;
/*     */ import org.apache.pdfbox.io.ScratchFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class COSStream
/*     */   extends COSDictionary
/*     */   implements Closeable
/*     */ {
/*     */   private RandomAccess randomAccess;
/*     */   private final ScratchFile scratchFile;
/*     */   private boolean isWriting;
/*  49 */   private static final Log LOG = LogFactory.getLog(COSStream.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStream() {
/*  61 */     this(ScratchFile.getMainMemoryOnlyInstance());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStream(ScratchFile scratchFile) {
/*  71 */     setInt(COSName.LENGTH, 0);
/*  72 */     this.scratchFile = (scratchFile != null) ? scratchFile : ScratchFile.getMainMemoryOnlyInstance();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkClosed() throws IOException {
/*  81 */     if (this.randomAccess != null && this.randomAccess.isClosed())
/*     */     {
/*  83 */       throw new IOException("COSStream has been closed and cannot be read. Perhaps its enclosing PDDocument has been closed?");
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
/*     */   @Deprecated
/*     */   public InputStream getFilteredStream() throws IOException {
/* 100 */     return createRawInputStream();
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
/*     */   private void ensureRandomAccessExists(boolean forInputStream) throws IOException {
/* 114 */     if (this.randomAccess == null) {
/*     */       
/* 116 */       if (forInputStream && LOG.isDebugEnabled())
/*     */       {
/*     */         
/* 119 */         LOG.debug("Create InputStream called without data being written before to stream.");
/*     */       }
/* 121 */       this.randomAccess = this.scratchFile.createBuffer();
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
/*     */   public InputStream createRawInputStream() throws IOException {
/* 133 */     checkClosed();
/* 134 */     if (this.isWriting)
/*     */     {
/* 136 */       throw new IllegalStateException("Cannot read while there is an open stream writer");
/*     */     }
/* 138 */     ensureRandomAccessExists(true);
/* 139 */     return (InputStream)new RandomAccessInputStream((RandomAccessRead)this.randomAccess);
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
/*     */   @Deprecated
/*     */   public InputStream getUnfilteredStream() throws IOException {
/* 152 */     return createInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSInputStream createInputStream() throws IOException {
/* 163 */     return createInputStream(DecodeOptions.DEFAULT);
/*     */   }
/*     */ 
/*     */   
/*     */   public COSInputStream createInputStream(DecodeOptions options) throws IOException {
/* 168 */     checkClosed();
/* 169 */     if (this.isWriting)
/*     */     {
/* 171 */       throw new IllegalStateException("Cannot read while there is an open stream writer");
/*     */     }
/* 173 */     ensureRandomAccessExists(true);
/* 174 */     RandomAccessInputStream randomAccessInputStream = new RandomAccessInputStream((RandomAccessRead)this.randomAccess);
/* 175 */     return COSInputStream.create(getFilterList(), this, (InputStream)randomAccessInputStream, this.scratchFile, options);
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
/*     */   @Deprecated
/*     */   public OutputStream createUnfilteredStream() throws IOException {
/* 188 */     return createOutputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream createOutputStream() throws IOException {
/* 199 */     return createOutputStream((COSBase)null);
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
/*     */   public OutputStream createOutputStream(COSBase filters) throws IOException {
/* 211 */     checkClosed();
/* 212 */     if (this.isWriting)
/*     */     {
/* 214 */       throw new IllegalStateException("Cannot have more than one open stream writer.");
/*     */     }
/*     */     
/* 217 */     if (filters != null)
/*     */     {
/* 219 */       setItem(COSName.FILTER, filters);
/*     */     }
/* 221 */     IOUtils.closeQuietly((Closeable)this.randomAccess);
/* 222 */     this.randomAccess = this.scratchFile.createBuffer();
/* 223 */     RandomAccessOutputStream randomAccessOutputStream = new RandomAccessOutputStream((RandomAccessWrite)this.randomAccess);
/* 224 */     OutputStream cosOut = new COSOutputStream(getFilterList(), this, (OutputStream)randomAccessOutputStream, this.scratchFile);
/* 225 */     this.isWriting = true;
/* 226 */     return new FilterOutputStream(cosOut)
/*     */       {
/*     */         
/*     */         public void write(byte[] b, int off, int len) throws IOException
/*     */         {
/* 231 */           this.out.write(b, off, len);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void close() throws IOException {
/* 237 */           super.close();
/* 238 */           COSStream.this.setInt(COSName.LENGTH, (int)COSStream.this.randomAccess.length());
/* 239 */           COSStream.this.isWriting = false;
/*     */         }
/*     */       };
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
/*     */   @Deprecated
/*     */   public OutputStream createFilteredStream() throws IOException {
/* 256 */     return createRawOutputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream createRawOutputStream() throws IOException {
/* 267 */     checkClosed();
/* 268 */     if (this.isWriting)
/*     */     {
/* 270 */       throw new IllegalStateException("Cannot have more than one open stream writer.");
/*     */     }
/* 272 */     IOUtils.closeQuietly((Closeable)this.randomAccess);
/* 273 */     this.randomAccess = this.scratchFile.createBuffer();
/* 274 */     RandomAccessOutputStream randomAccessOutputStream = new RandomAccessOutputStream((RandomAccessWrite)this.randomAccess);
/* 275 */     this.isWriting = true;
/* 276 */     return new FilterOutputStream((OutputStream)randomAccessOutputStream)
/*     */       {
/*     */         
/*     */         public void write(byte[] b, int off, int len) throws IOException
/*     */         {
/* 281 */           this.out.write(b, off, len);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void close() throws IOException {
/* 287 */           super.close();
/* 288 */           COSStream.this.setInt(COSName.LENGTH, (int)COSStream.this.randomAccess.length());
/* 289 */           COSStream.this.isWriting = false;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Filter> getFilterList() throws IOException {
/* 299 */     List<Filter> filterList = new ArrayList<Filter>();
/* 300 */     COSBase filters = getFilters();
/* 301 */     if (filters instanceof COSName) {
/*     */       
/* 303 */       filterList.add(FilterFactory.INSTANCE.getFilter((COSName)filters));
/*     */     }
/* 305 */     else if (filters instanceof COSArray) {
/*     */       
/* 307 */       COSArray filterArray = (COSArray)filters;
/* 308 */       for (int i = 0; i < filterArray.size(); i++) {
/*     */         
/* 310 */         COSName filterName = (COSName)filterArray.get(i);
/* 311 */         filterList.add(FilterFactory.INSTANCE.getFilter(filterName));
/*     */       } 
/*     */     } 
/* 314 */     return filterList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLength() {
/* 324 */     if (this.isWriting)
/*     */     {
/* 326 */       throw new IllegalStateException("There is an open OutputStream associated with this COSStream. It must be closed before queryinglength of this COSStream.");
/*     */     }
/*     */ 
/*     */     
/* 330 */     return getInt(COSName.LENGTH, 0);
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
/*     */   public COSBase getFilters() {
/* 344 */     return getDictionaryObject(COSName.FILTER);
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
/*     */   @Deprecated
/*     */   public void setFilters(COSBase filters) throws IOException {
/* 357 */     setItem(COSName.FILTER, filters);
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
/*     */   @Deprecated
/*     */   public String getString() {
/* 370 */     return toTextString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toTextString() {
/* 380 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 381 */     InputStream input = null;
/*     */     
/*     */     try {
/* 384 */       input = createInputStream();
/* 385 */       IOUtils.copy(input, out);
/*     */     }
/* 387 */     catch (IOException e) {
/*     */       
/* 389 */       return "";
/*     */     }
/*     */     finally {
/*     */       
/* 393 */       IOUtils.closeQuietly(input);
/*     */     } 
/* 395 */     COSString string = new COSString(out.toByteArray());
/* 396 */     return string.getString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object accept(ICOSVisitor visitor) throws IOException {
/* 402 */     return visitor.visitFromStream(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 409 */     if (this.randomAccess != null)
/*     */     {
/* 411 */       this.randomAccess.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */