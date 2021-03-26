/*      */ package org.apache.commons.io;
/*      */ 
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.CharArrayWriter;
/*      */ import java.io.Closeable;
/*      */ import java.io.EOFException;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.net.HttpURLConnection;
/*      */ import java.net.ServerSocket;
/*      */ import java.net.Socket;
/*      */ import java.net.URI;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.channels.ReadableByteChannel;
/*      */ import java.nio.channels.Selector;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.List;
/*      */ import org.apache.commons.io.output.ByteArrayOutputStream;
/*      */ import org.apache.commons.io.output.StringBuilderWriter;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class IOUtils
/*      */ {
/*      */   public static final int EOF = -1;
/*      */   public static final char DIR_SEPARATOR_UNIX = '/';
/*      */   public static final char DIR_SEPARATOR_WINDOWS = '\\';
/*  121 */   public static final char DIR_SEPARATOR = File.separatorChar;
/*      */   
/*      */   public static final String LINE_SEPARATOR_UNIX = "\n";
/*      */   
/*      */   public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
/*      */   
/*      */   public static final String LINE_SEPARATOR;
/*      */   
/*      */   private static final int DEFAULT_BUFFER_SIZE = 4096;
/*      */   
/*      */   private static final int SKIP_BUFFER_SIZE = 2048;
/*      */   
/*      */   private static char[] SKIP_CHAR_BUFFER;
/*      */   private static byte[] SKIP_BYTE_BUFFER;
/*      */   
/*      */   static {
/*  137 */     try(StringBuilderWriter buf = new StringBuilderWriter(4); 
/*  138 */         PrintWriter out = new PrintWriter((Writer)buf)) {
/*  139 */       out.println();
/*  140 */       LINE_SEPARATOR = buf.toString();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void close(URLConnection conn) {
/*  187 */     if (conn instanceof HttpURLConnection) {
/*  188 */       ((HttpURLConnection)conn).disconnect();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void closeQuietly(Reader input) {
/*  221 */     closeQuietly(input);
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
/*      */   @Deprecated
/*      */   public static void closeQuietly(Writer output) {
/*  252 */     closeQuietly(output);
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
/*      */   @Deprecated
/*      */   public static void closeQuietly(InputStream input) {
/*  284 */     closeQuietly(input);
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
/*      */   @Deprecated
/*      */   public static void closeQuietly(OutputStream output) {
/*  317 */     closeQuietly(output);
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
/*      */   @Deprecated
/*      */   public static void closeQuietly(Closeable closeable) {
/*      */     try {
/*  362 */       if (closeable != null) {
/*  363 */         closeable.close();
/*      */       }
/*  365 */     } catch (IOException iOException) {}
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
/*      */   @Deprecated
/*      */   public static void closeQuietly(Closeable... closeables) {
/*  419 */     if (closeables == null) {
/*      */       return;
/*      */     }
/*  422 */     for (Closeable closeable : closeables) {
/*  423 */       closeQuietly(closeable);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void closeQuietly(Socket sock) {
/*  456 */     if (sock != null) {
/*      */       try {
/*  458 */         sock.close();
/*  459 */       } catch (IOException iOException) {}
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void closeQuietly(Selector selector) {
/*  494 */     if (selector != null) {
/*      */       try {
/*  496 */         selector.close();
/*  497 */       } catch (IOException iOException) {}
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void closeQuietly(ServerSocket sock) {
/*  532 */     if (sock != null) {
/*      */       try {
/*  534 */         sock.close();
/*  535 */       } catch (IOException iOException) {}
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static InputStream toBufferedInputStream(InputStream input) throws IOException {
/*  563 */     return ByteArrayOutputStream.toBufferedInputStream(input);
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
/*      */   public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
/*  589 */     return ByteArrayOutputStream.toBufferedInputStream(input, size);
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
/*      */   public static BufferedReader toBufferedReader(Reader reader) {
/*  603 */     return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);
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
/*      */   public static BufferedReader toBufferedReader(Reader reader, int size) {
/*  618 */     return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader, size);
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
/*      */   public static BufferedReader buffer(Reader reader) {
/*  631 */     return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);
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
/*      */   public static BufferedReader buffer(Reader reader, int size) {
/*  645 */     return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader, size);
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
/*      */   public static BufferedWriter buffer(Writer writer) {
/*  658 */     return (writer instanceof BufferedWriter) ? (BufferedWriter)writer : new BufferedWriter(writer);
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
/*      */   public static BufferedWriter buffer(Writer writer, int size) {
/*  672 */     return (writer instanceof BufferedWriter) ? (BufferedWriter)writer : new BufferedWriter(writer, size);
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
/*      */   public static BufferedOutputStream buffer(OutputStream outputStream) {
/*  686 */     if (outputStream == null) {
/*  687 */       throw new NullPointerException();
/*      */     }
/*  689 */     return (outputStream instanceof BufferedOutputStream) ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream);
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
/*      */   public static BufferedOutputStream buffer(OutputStream outputStream, int size) {
/*  705 */     if (outputStream == null) {
/*  706 */       throw new NullPointerException();
/*      */     }
/*  708 */     return (outputStream instanceof BufferedOutputStream) ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream, size);
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
/*      */   public static BufferedInputStream buffer(InputStream inputStream) {
/*  723 */     if (inputStream == null) {
/*  724 */       throw new NullPointerException();
/*      */     }
/*  726 */     return (inputStream instanceof BufferedInputStream) ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream);
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
/*      */   public static BufferedInputStream buffer(InputStream inputStream, int size) {
/*  742 */     if (inputStream == null) {
/*  743 */       throw new NullPointerException();
/*      */     }
/*  745 */     return (inputStream instanceof BufferedInputStream) ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream, size);
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
/*      */   public static byte[] toByteArray(InputStream input) throws IOException {
/*  764 */     try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
/*  765 */       copy(input, (OutputStream)output);
/*  766 */       return output.toByteArray();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] toByteArray(InputStream input, long size) throws IOException {
/*  789 */     if (size > 2147483647L) {
/*  790 */       throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + size);
/*      */     }
/*      */     
/*  793 */     return toByteArray(input, (int)size);
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
/*      */   public static byte[] toByteArray(InputStream input, int size) throws IOException {
/*  811 */     if (size < 0) {
/*  812 */       throw new IllegalArgumentException("Size must be equal or greater than zero: " + size);
/*      */     }
/*      */     
/*  815 */     if (size == 0) {
/*  816 */       return new byte[0];
/*      */     }
/*      */     
/*  819 */     byte[] data = new byte[size];
/*  820 */     int offset = 0;
/*      */     
/*      */     int read;
/*  823 */     while (offset < size && (read = input.read(data, offset, size - offset)) != -1) {
/*  824 */       offset += read;
/*      */     }
/*      */     
/*  827 */     if (offset != size) {
/*  828 */       throw new IOException("Unexpected read size. current: " + offset + ", expected: " + size);
/*      */     }
/*      */     
/*  831 */     return data;
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
/*      */   @Deprecated
/*      */   public static byte[] toByteArray(Reader input) throws IOException {
/*  849 */     return toByteArray(input, Charset.defaultCharset());
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
/*      */   public static byte[] toByteArray(Reader input, Charset encoding) throws IOException {
/*  867 */     try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
/*  868 */       copy(input, (OutputStream)output, encoding);
/*  869 */       return output.toByteArray();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] toByteArray(Reader input, String encoding) throws IOException {
/*  894 */     return toByteArray(input, Charsets.toCharset(encoding));
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
/*      */   @Deprecated
/*      */   public static byte[] toByteArray(String input) throws IOException {
/*  912 */     return input.getBytes(Charset.defaultCharset());
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
/*      */   public static byte[] toByteArray(URI uri) throws IOException {
/*  925 */     return toByteArray(uri.toURL());
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
/*      */   public static byte[] toByteArray(URL url) throws IOException {
/*  938 */     URLConnection conn = url.openConnection();
/*      */     try {
/*  940 */       return toByteArray(conn);
/*      */     } finally {
/*  942 */       close(conn);
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
/*      */   public static byte[] toByteArray(URLConnection urlConn) throws IOException {
/*  956 */     try (InputStream inputStream = urlConn.getInputStream()) {
/*  957 */       return toByteArray(inputStream);
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
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static char[] toCharArray(InputStream is) throws IOException {
/*  980 */     return toCharArray(is, Charset.defaultCharset());
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
/*      */   public static char[] toCharArray(InputStream is, Charset encoding) throws IOException {
/*  999 */     CharArrayWriter output = new CharArrayWriter();
/* 1000 */     copy(is, output, encoding);
/* 1001 */     return output.toCharArray();
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
/*      */   public static char[] toCharArray(InputStream is, String encoding) throws IOException {
/* 1025 */     return toCharArray(is, Charsets.toCharset(encoding));
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
/*      */   public static char[] toCharArray(Reader input) throws IOException {
/* 1041 */     CharArrayWriter sw = new CharArrayWriter();
/* 1042 */     copy(input, sw);
/* 1043 */     return sw.toCharArray();
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
/*      */   @Deprecated
/*      */   public static String toString(InputStream input) throws IOException {
/* 1064 */     return toString(input, Charset.defaultCharset());
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
/*      */   public static String toString(InputStream input, Charset encoding) throws IOException {
/* 1083 */     try (StringBuilderWriter sw = new StringBuilderWriter()) {
/* 1084 */       copy(input, (Writer)sw, encoding);
/* 1085 */       return sw.toString();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toString(InputStream input, String encoding) throws IOException {
/* 1110 */     return toString(input, Charsets.toCharset(encoding));
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
/*      */   public static String toString(Reader input) throws IOException {
/* 1125 */     try (StringBuilderWriter sw = new StringBuilderWriter()) {
/* 1126 */       copy(input, (Writer)sw);
/* 1127 */       return sw.toString();
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
/*      */   @Deprecated
/*      */   public static String toString(URI uri) throws IOException {
/* 1142 */     return toString(uri, Charset.defaultCharset());
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
/*      */   public static String toString(URI uri, Charset encoding) throws IOException {
/* 1155 */     return toString(uri.toURL(), Charsets.toCharset(encoding));
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
/*      */   public static String toString(URI uri, String encoding) throws IOException {
/* 1171 */     return toString(uri, Charsets.toCharset(encoding));
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
/*      */   @Deprecated
/*      */   public static String toString(URL url) throws IOException {
/* 1185 */     return toString(url, Charset.defaultCharset());
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
/*      */   public static String toString(URL url, Charset encoding) throws IOException {
/* 1198 */     try (InputStream inputStream = url.openStream()) {
/* 1199 */       return toString(inputStream, encoding);
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
/*      */   public static String toString(URL url, String encoding) throws IOException {
/* 1216 */     return toString(url, Charsets.toCharset(encoding));
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
/*      */   @Deprecated
/*      */   public static String toString(byte[] input) throws IOException {
/* 1232 */     return new String(input, Charset.defaultCharset());
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
/*      */   public static String toString(byte[] input, String encoding) throws IOException {
/* 1249 */     return new String(input, Charsets.toCharset(encoding));
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
/*      */   public static String resourceToString(String name, Charset encoding) throws IOException {
/* 1272 */     return resourceToString(name, encoding, null);
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
/*      */   public static String resourceToString(String name, Charset encoding, ClassLoader classLoader) throws IOException {
/* 1293 */     return toString(resourceToURL(name, classLoader), encoding);
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
/*      */   public static byte[] resourceToByteArray(String name) throws IOException {
/* 1311 */     return resourceToByteArray(name, null);
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
/*      */   public static byte[] resourceToByteArray(String name, ClassLoader classLoader) throws IOException {
/* 1330 */     return toByteArray(resourceToURL(name, classLoader));
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
/*      */   public static URL resourceToURL(String name) throws IOException {
/* 1348 */     return resourceToURL(name, null);
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
/*      */   public static URL resourceToURL(String name, ClassLoader classLoader) throws IOException {
/* 1369 */     URL resource = (classLoader == null) ? IOUtils.class.getResource(name) : classLoader.getResource(name);
/*      */     
/* 1371 */     if (resource == null) {
/* 1372 */       throw new IOException("Resource not found: " + name);
/*      */     }
/*      */     
/* 1375 */     return resource;
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
/*      */   @Deprecated
/*      */   public static List<String> readLines(InputStream input) throws IOException {
/* 1397 */     return readLines(input, Charset.defaultCharset());
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
/*      */   public static List<String> readLines(InputStream input, Charset encoding) throws IOException {
/* 1415 */     InputStreamReader reader = new InputStreamReader(input, Charsets.toCharset(encoding));
/* 1416 */     return readLines(reader);
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
/*      */   public static List<String> readLines(InputStream input, String encoding) throws IOException {
/* 1440 */     return readLines(input, Charsets.toCharset(encoding));
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
/*      */   public static List<String> readLines(Reader input) throws IOException {
/* 1457 */     BufferedReader reader = toBufferedReader(input);
/* 1458 */     List<String> list = new ArrayList<>();
/* 1459 */     String line = reader.readLine();
/* 1460 */     while (line != null) {
/* 1461 */       list.add(line);
/* 1462 */       line = reader.readLine();
/*      */     } 
/* 1464 */     return list;
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
/*      */   public static LineIterator lineIterator(Reader reader) {
/* 1498 */     return new LineIterator(reader);
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
/*      */   public static LineIterator lineIterator(InputStream input, Charset encoding) throws IOException {
/* 1532 */     return new LineIterator(new InputStreamReader(input, Charsets.toCharset(encoding)));
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
/*      */   public static LineIterator lineIterator(InputStream input, String encoding) throws IOException {
/* 1569 */     return lineIterator(input, Charsets.toCharset(encoding));
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
/*      */   @Deprecated
/*      */   public static InputStream toInputStream(CharSequence input) {
/* 1585 */     return toInputStream(input, Charset.defaultCharset());
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
/*      */   public static InputStream toInputStream(CharSequence input, Charset encoding) {
/* 1598 */     return toInputStream(input.toString(), encoding);
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
/*      */   public static InputStream toInputStream(CharSequence input, String encoding) throws IOException {
/* 1618 */     return toInputStream(input, Charsets.toCharset(encoding));
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
/*      */   @Deprecated
/*      */   public static InputStream toInputStream(String input) {
/* 1634 */     return toInputStream(input, Charset.defaultCharset());
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
/*      */   public static InputStream toInputStream(String input, Charset encoding) {
/* 1647 */     return new ByteArrayInputStream(input.getBytes(Charsets.toCharset(encoding)));
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
/*      */   public static InputStream toInputStream(String input, String encoding) throws IOException {
/* 1667 */     byte[] bytes = input.getBytes(Charsets.toCharset(encoding));
/* 1668 */     return new ByteArrayInputStream(bytes);
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
/*      */   public static void write(byte[] data, OutputStream output) throws IOException {
/* 1686 */     if (data != null) {
/* 1687 */       output.write(data);
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
/*      */   public static void writeChunked(byte[] data, OutputStream output) throws IOException {
/* 1705 */     if (data != null) {
/* 1706 */       int bytes = data.length;
/* 1707 */       int offset = 0;
/* 1708 */       while (bytes > 0) {
/* 1709 */         int chunk = Math.min(bytes, 4096);
/* 1710 */         output.write(data, offset, chunk);
/* 1711 */         bytes -= chunk;
/* 1712 */         offset += chunk;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void write(byte[] data, Writer output) throws IOException {
/* 1733 */     write(data, output, Charset.defaultCharset());
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
/*      */   public static void write(byte[] data, Writer output, Charset encoding) throws IOException {
/* 1751 */     if (data != null) {
/* 1752 */       output.write(new String(data, Charsets.toCharset(encoding)));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void write(byte[] data, Writer output, String encoding) throws IOException {
/* 1777 */     write(data, output, Charsets.toCharset(encoding));
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
/*      */   public static void write(char[] data, Writer output) throws IOException {
/* 1794 */     if (data != null) {
/* 1795 */       output.write(data);
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
/*      */   public static void writeChunked(char[] data, Writer output) throws IOException {
/* 1812 */     if (data != null) {
/* 1813 */       int bytes = data.length;
/* 1814 */       int offset = 0;
/* 1815 */       while (bytes > 0) {
/* 1816 */         int chunk = Math.min(bytes, 4096);
/* 1817 */         output.write(data, offset, chunk);
/* 1818 */         bytes -= chunk;
/* 1819 */         offset += chunk;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void write(char[] data, OutputStream output) throws IOException {
/* 1842 */     write(data, output, Charset.defaultCharset());
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
/*      */   public static void write(char[] data, OutputStream output, Charset encoding) throws IOException {
/* 1861 */     if (data != null) {
/* 1862 */       output.write((new String(data)).getBytes(Charsets.toCharset(encoding)));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void write(char[] data, OutputStream output, String encoding) throws IOException {
/* 1888 */     write(data, output, Charsets.toCharset(encoding));
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
/*      */   public static void write(CharSequence data, Writer output) throws IOException {
/* 1904 */     if (data != null) {
/* 1905 */       write(data.toString(), output);
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
/*      */   
/*      */   @Deprecated
/*      */   public static void write(CharSequence data, OutputStream output) throws IOException {
/* 1926 */     write(data, output, Charset.defaultCharset());
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
/*      */   public static void write(CharSequence data, OutputStream output, Charset encoding) throws IOException {
/* 1944 */     if (data != null) {
/* 1945 */       write(data.toString(), output, encoding);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void write(CharSequence data, OutputStream output, String encoding) throws IOException {
/* 1969 */     write(data, output, Charsets.toCharset(encoding));
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
/*      */   public static void write(String data, Writer output) throws IOException {
/* 1985 */     if (data != null) {
/* 1986 */       output.write(data);
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
/*      */   
/*      */   @Deprecated
/*      */   public static void write(String data, OutputStream output) throws IOException {
/* 2007 */     write(data, output, Charset.defaultCharset());
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
/*      */   public static void write(String data, OutputStream output, Charset encoding) throws IOException {
/* 2024 */     if (data != null) {
/* 2025 */       output.write(data.getBytes(Charsets.toCharset(encoding)));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void write(String data, OutputStream output, String encoding) throws IOException {
/* 2049 */     write(data, output, Charsets.toCharset(encoding));
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
/*      */   @Deprecated
/*      */   public static void write(StringBuffer data, Writer output) throws IOException {
/* 2068 */     if (data != null) {
/* 2069 */       output.write(data.toString());
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
/*      */   
/*      */   @Deprecated
/*      */   public static void write(StringBuffer data, OutputStream output) throws IOException {
/* 2090 */     write(data, output, (String)null);
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
/*      */   @Deprecated
/*      */   public static void write(StringBuffer data, OutputStream output, String encoding) throws IOException {
/* 2115 */     if (data != null) {
/* 2116 */       output.write(data.toString().getBytes(Charsets.toCharset(encoding)));
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
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output) throws IOException {
/* 2139 */     writeLines(lines, lineEnding, output, Charset.defaultCharset());
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
/*      */   public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset encoding) throws IOException {
/* 2157 */     if (lines == null) {
/*      */       return;
/*      */     }
/* 2160 */     if (lineEnding == null) {
/* 2161 */       lineEnding = LINE_SEPARATOR;
/*      */     }
/* 2163 */     Charset cs = Charsets.toCharset(encoding);
/* 2164 */     for (Object line : lines) {
/* 2165 */       if (line != null) {
/* 2166 */         output.write(line.toString().getBytes(cs));
/*      */       }
/* 2168 */       output.write(lineEnding.getBytes(cs));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, String encoding) throws IOException {
/* 2193 */     writeLines(lines, lineEnding, output, Charsets.toCharset(encoding));
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
/*      */   public static void writeLines(Collection<?> lines, String lineEnding, Writer writer) throws IOException {
/* 2209 */     if (lines == null) {
/*      */       return;
/*      */     }
/* 2212 */     if (lineEnding == null) {
/* 2213 */       lineEnding = LINE_SEPARATOR;
/*      */     }
/* 2215 */     for (Object line : lines) {
/* 2216 */       if (line != null) {
/* 2217 */         writer.write(line.toString());
/*      */       }
/* 2219 */       writer.write(lineEnding);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int copy(InputStream input, OutputStream output) throws IOException {
/* 2246 */     long count = copyLarge(input, output);
/* 2247 */     if (count > 2147483647L) {
/* 2248 */       return -1;
/*      */     }
/* 2250 */     return (int)count;
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
/*      */   public static long copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
/* 2270 */     return copyLarge(input, output, new byte[bufferSize]);
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
/*      */   public static long copyLarge(InputStream input, OutputStream output) throws IOException {
/* 2291 */     return copy(input, output, 4096);
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
/*      */   public static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
/* 2312 */     long count = 0L;
/*      */     int n;
/* 2314 */     while (-1 != (n = input.read(buffer))) {
/* 2315 */       output.write(buffer, 0, n);
/* 2316 */       count += n;
/*      */     } 
/* 2318 */     return count;
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
/*      */   public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length) throws IOException {
/* 2347 */     return copyLarge(input, output, inputOffset, length, new byte[4096]);
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
/*      */   public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length, byte[] buffer) throws IOException {
/* 2376 */     if (inputOffset > 0L) {
/* 2377 */       skipFully(input, inputOffset);
/*      */     }
/* 2379 */     if (length == 0L) {
/* 2380 */       return 0L;
/*      */     }
/* 2382 */     int bufferLength = buffer.length;
/* 2383 */     int bytesToRead = bufferLength;
/* 2384 */     if (length > 0L && length < bufferLength) {
/* 2385 */       bytesToRead = (int)length;
/*      */     }
/*      */     
/* 2388 */     long totalRead = 0L; int read;
/* 2389 */     while (bytesToRead > 0 && -1 != (read = input.read(buffer, 0, bytesToRead))) {
/* 2390 */       output.write(buffer, 0, read);
/* 2391 */       totalRead += read;
/* 2392 */       if (length > 0L)
/*      */       {
/* 2394 */         bytesToRead = (int)Math.min(length - totalRead, bufferLength);
/*      */       }
/*      */     } 
/* 2397 */     return totalRead;
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
/*      */   @Deprecated
/*      */   public static void copy(InputStream input, Writer output) throws IOException {
/* 2419 */     copy(input, output, Charset.defaultCharset());
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
/*      */   public static void copy(InputStream input, Writer output, Charset inputEncoding) throws IOException {
/* 2440 */     InputStreamReader in = new InputStreamReader(input, Charsets.toCharset(inputEncoding));
/* 2441 */     copy(in, output);
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
/*      */   public static void copy(InputStream input, Writer output, String inputEncoding) throws IOException {
/* 2468 */     copy(input, output, Charsets.toCharset(inputEncoding));
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
/*      */   public static int copy(Reader input, Writer output) throws IOException {
/* 2493 */     long count = copyLarge(input, output);
/* 2494 */     if (count > 2147483647L) {
/* 2495 */       return -1;
/*      */     }
/* 2497 */     return (int)count;
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
/*      */   public static long copyLarge(Reader input, Writer output) throws IOException {
/* 2516 */     return copyLarge(input, output, new char[4096]);
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
/*      */   public static long copyLarge(Reader input, Writer output, char[] buffer) throws IOException {
/* 2535 */     long count = 0L;
/*      */     int n;
/* 2537 */     while (-1 != (n = input.read(buffer))) {
/* 2538 */       output.write(buffer, 0, n);
/* 2539 */       count += n;
/*      */     } 
/* 2541 */     return count;
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
/*      */   public static long copyLarge(Reader input, Writer output, long inputOffset, long length) throws IOException {
/* 2565 */     return copyLarge(input, output, inputOffset, length, new char[4096]);
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
/*      */   public static long copyLarge(Reader input, Writer output, long inputOffset, long length, char[] buffer) throws IOException {
/* 2590 */     if (inputOffset > 0L) {
/* 2591 */       skipFully(input, inputOffset);
/*      */     }
/* 2593 */     if (length == 0L) {
/* 2594 */       return 0L;
/*      */     }
/* 2596 */     int bytesToRead = buffer.length;
/* 2597 */     if (length > 0L && length < buffer.length) {
/* 2598 */       bytesToRead = (int)length;
/*      */     }
/*      */     
/* 2601 */     long totalRead = 0L; int read;
/* 2602 */     while (bytesToRead > 0 && -1 != (read = input.read(buffer, 0, bytesToRead))) {
/* 2603 */       output.write(buffer, 0, read);
/* 2604 */       totalRead += read;
/* 2605 */       if (length > 0L)
/*      */       {
/* 2607 */         bytesToRead = (int)Math.min(length - totalRead, buffer.length);
/*      */       }
/*      */     } 
/* 2610 */     return totalRead;
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
/*      */   @Deprecated
/*      */   public static void copy(Reader input, OutputStream output) throws IOException {
/* 2636 */     copy(input, output, Charset.defaultCharset());
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
/*      */   public static void copy(Reader input, OutputStream output, Charset outputEncoding) throws IOException {
/* 2664 */     OutputStreamWriter out = new OutputStreamWriter(output, Charsets.toCharset(outputEncoding));
/* 2665 */     copy(input, out);
/*      */ 
/*      */     
/* 2668 */     out.flush();
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
/*      */   public static void copy(Reader input, OutputStream output, String outputEncoding) throws IOException {
/* 2699 */     copy(input, output, Charsets.toCharset(outputEncoding));
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
/*      */   public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
/* 2721 */     if (input1 == input2) {
/* 2722 */       return true;
/*      */     }
/* 2724 */     if (!(input1 instanceof BufferedInputStream)) {
/* 2725 */       input1 = new BufferedInputStream(input1);
/*      */     }
/* 2727 */     if (!(input2 instanceof BufferedInputStream)) {
/* 2728 */       input2 = new BufferedInputStream(input2);
/*      */     }
/*      */     
/* 2731 */     int ch = input1.read();
/* 2732 */     while (-1 != ch) {
/* 2733 */       int i = input2.read();
/* 2734 */       if (ch != i) {
/* 2735 */         return false;
/*      */       }
/* 2737 */       ch = input1.read();
/*      */     } 
/*      */     
/* 2740 */     int ch2 = input2.read();
/* 2741 */     return (ch2 == -1);
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
/*      */   public static boolean contentEquals(Reader input1, Reader input2) throws IOException {
/* 2761 */     if (input1 == input2) {
/* 2762 */       return true;
/*      */     }
/*      */     
/* 2765 */     input1 = toBufferedReader(input1);
/* 2766 */     input2 = toBufferedReader(input2);
/*      */     
/* 2768 */     int ch = input1.read();
/* 2769 */     while (-1 != ch) {
/* 2770 */       int i = input2.read();
/* 2771 */       if (ch != i) {
/* 2772 */         return false;
/*      */       }
/* 2774 */       ch = input1.read();
/*      */     } 
/*      */     
/* 2777 */     int ch2 = input2.read();
/* 2778 */     return (ch2 == -1);
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
/*      */   public static boolean contentEqualsIgnoreEOL(Reader input1, Reader input2) throws IOException {
/* 2797 */     if (input1 == input2) {
/* 2798 */       return true;
/*      */     }
/* 2800 */     BufferedReader br1 = toBufferedReader(input1);
/* 2801 */     BufferedReader br2 = toBufferedReader(input2);
/*      */     
/* 2803 */     String line1 = br1.readLine();
/* 2804 */     String line2 = br2.readLine();
/* 2805 */     while (line1 != null && line2 != null && line1.equals(line2)) {
/* 2806 */       line1 = br1.readLine();
/* 2807 */       line2 = br2.readLine();
/*      */     } 
/* 2809 */     return (line1 == null) ? ((line2 == null)) : line1.equals(line2);
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
/*      */   public static long skip(InputStream input, long toSkip) throws IOException {
/* 2834 */     if (toSkip < 0L) {
/* 2835 */       throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2842 */     if (SKIP_BYTE_BUFFER == null) {
/* 2843 */       SKIP_BYTE_BUFFER = new byte[2048];
/*      */     }
/* 2845 */     long remain = toSkip;
/* 2846 */     while (remain > 0L) {
/*      */       
/* 2848 */       long n = input.read(SKIP_BYTE_BUFFER, 0, (int)Math.min(remain, 2048L));
/* 2849 */       if (n < 0L) {
/*      */         break;
/*      */       }
/* 2852 */       remain -= n;
/*      */     } 
/* 2854 */     return toSkip - remain;
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
/*      */   public static long skip(ReadableByteChannel input, long toSkip) throws IOException {
/* 2870 */     if (toSkip < 0L) {
/* 2871 */       throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
/*      */     }
/* 2873 */     ByteBuffer skipByteBuffer = ByteBuffer.allocate((int)Math.min(toSkip, 2048L));
/* 2874 */     long remain = toSkip;
/* 2875 */     while (remain > 0L) {
/* 2876 */       skipByteBuffer.position(0);
/* 2877 */       skipByteBuffer.limit((int)Math.min(remain, 2048L));
/* 2878 */       int n = input.read(skipByteBuffer);
/* 2879 */       if (n == -1) {
/*      */         break;
/*      */       }
/* 2882 */       remain -= n;
/*      */     } 
/* 2884 */     return toSkip - remain;
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
/*      */   public static long skip(Reader input, long toSkip) throws IOException {
/* 2909 */     if (toSkip < 0L) {
/* 2910 */       throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2917 */     if (SKIP_CHAR_BUFFER == null) {
/* 2918 */       SKIP_CHAR_BUFFER = new char[2048];
/*      */     }
/* 2920 */     long remain = toSkip;
/* 2921 */     while (remain > 0L) {
/*      */       
/* 2923 */       long n = input.read(SKIP_CHAR_BUFFER, 0, (int)Math.min(remain, 2048L));
/* 2924 */       if (n < 0L) {
/*      */         break;
/*      */       }
/* 2927 */       remain -= n;
/*      */     } 
/* 2929 */     return toSkip - remain;
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
/*      */   public static void skipFully(InputStream input, long toSkip) throws IOException {
/* 2952 */     if (toSkip < 0L) {
/* 2953 */       throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
/*      */     }
/* 2955 */     long skipped = skip(input, toSkip);
/* 2956 */     if (skipped != toSkip) {
/* 2957 */       throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
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
/*      */   public static void skipFully(ReadableByteChannel input, long toSkip) throws IOException {
/* 2972 */     if (toSkip < 0L) {
/* 2973 */       throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
/*      */     }
/* 2975 */     long skipped = skip(input, toSkip);
/* 2976 */     if (skipped != toSkip) {
/* 2977 */       throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void skipFully(Reader input, long toSkip) throws IOException {
/* 3001 */     long skipped = skip(input, toSkip);
/* 3002 */     if (skipped != toSkip) {
/* 3003 */       throw new EOFException("Chars to skip: " + toSkip + " actual: " + skipped);
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
/*      */ 
/*      */   
/*      */   public static int read(Reader input, char[] buffer, int offset, int length) throws IOException {
/* 3024 */     if (length < 0) {
/* 3025 */       throw new IllegalArgumentException("Length must not be negative: " + length);
/*      */     }
/* 3027 */     int remaining = length;
/* 3028 */     while (remaining > 0) {
/* 3029 */       int location = length - remaining;
/* 3030 */       int count = input.read(buffer, offset + location, remaining);
/* 3031 */       if (-1 == count) {
/*      */         break;
/*      */       }
/* 3034 */       remaining -= count;
/*      */     } 
/* 3036 */     return length - remaining;
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
/*      */   public static int read(Reader input, char[] buffer) throws IOException {
/* 3052 */     return read(input, buffer, 0, buffer.length);
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
/*      */   public static int read(InputStream input, byte[] buffer, int offset, int length) throws IOException {
/* 3071 */     if (length < 0) {
/* 3072 */       throw new IllegalArgumentException("Length must not be negative: " + length);
/*      */     }
/* 3074 */     int remaining = length;
/* 3075 */     while (remaining > 0) {
/* 3076 */       int location = length - remaining;
/* 3077 */       int count = input.read(buffer, offset + location, remaining);
/* 3078 */       if (-1 == count) {
/*      */         break;
/*      */       }
/* 3081 */       remaining -= count;
/*      */     } 
/* 3083 */     return length - remaining;
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
/*      */   public static int read(InputStream input, byte[] buffer) throws IOException {
/* 3099 */     return read(input, buffer, 0, buffer.length);
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
/*      */   public static int read(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
/* 3116 */     int length = buffer.remaining();
/* 3117 */     while (buffer.remaining() > 0) {
/* 3118 */       int count = input.read(buffer);
/* 3119 */       if (-1 == count) {
/*      */         break;
/*      */       }
/*      */     } 
/* 3123 */     return length - buffer.remaining();
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
/*      */   public static void readFully(Reader input, char[] buffer, int offset, int length) throws IOException {
/* 3143 */     int actual = read(input, buffer, offset, length);
/* 3144 */     if (actual != length) {
/* 3145 */       throw new EOFException("Length to read: " + length + " actual: " + actual);
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
/*      */   public static void readFully(Reader input, char[] buffer) throws IOException {
/* 3163 */     readFully(input, buffer, 0, buffer.length);
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
/*      */   public static void readFully(InputStream input, byte[] buffer, int offset, int length) throws IOException {
/* 3183 */     int actual = read(input, buffer, offset, length);
/* 3184 */     if (actual != length) {
/* 3185 */       throw new EOFException("Length to read: " + length + " actual: " + actual);
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
/*      */   public static void readFully(InputStream input, byte[] buffer) throws IOException {
/* 3203 */     readFully(input, buffer, 0, buffer.length);
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
/*      */   public static byte[] readFully(InputStream input, int length) throws IOException {
/* 3221 */     byte[] buffer = new byte[length];
/* 3222 */     readFully(input, buffer, 0, buffer.length);
/* 3223 */     return buffer;
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
/*      */   public static void readFully(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
/* 3239 */     int expected = buffer.remaining();
/* 3240 */     int actual = read(input, buffer);
/* 3241 */     if (actual != expected)
/* 3242 */       throw new EOFException("Length to read: " + expected + " actual: " + actual); 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/IOUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */