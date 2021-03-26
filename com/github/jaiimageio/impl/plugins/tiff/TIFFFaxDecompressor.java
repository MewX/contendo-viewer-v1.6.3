/*      */ package com.github.jaiimageio.impl.plugins.tiff;
/*      */ 
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import javax.imageio.IIOException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TIFFFaxDecompressor
/*      */   extends TIFFDecompressor
/*      */ {
/*      */   protected int fillOrder;
/*      */   protected int compression;
/*      */   private int t4Options;
/*      */   private int t6Options;
/*   81 */   protected int uncompressedMode = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   87 */   protected int fillBits = 0;
/*      */   
/*      */   protected int oneD;
/*      */   
/*      */   private byte[] data;
/*      */   
/*      */   private int bitPointer;
/*      */   
/*      */   private int bytePointer;
/*      */   
/*      */   private byte[] buffer;
/*      */   
/*      */   private int w;
/*      */   
/*      */   private int h;
/*      */   private int bitsPerScanline;
/*      */   private int lineBitNum;
/*  104 */   private int changingElemSize = 0;
/*      */   
/*      */   private int[] prevChangingElems;
/*      */   
/*      */   private int[] currChangingElems;
/*  109 */   private int lastChangingElement = 0;
/*      */   
/*  111 */   static int[] table1 = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  123 */   static int[] table2 = new int[] { 0, 128, 192, 224, 240, 248, 252, 254, 255 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  136 */   static byte[] flipTable = new byte[] { 0, Byte.MIN_VALUE, 64, -64, 32, -96, 96, -32, 16, -112, 80, -48, 48, -80, 112, -16, 8, -120, 72, -56, 40, -88, 104, -24, 24, -104, 88, -40, 56, -72, 120, -8, 4, -124, 68, -60, 36, -92, 100, -28, 20, -108, 84, -44, 52, -76, 116, -12, 12, -116, 76, -52, 44, -84, 108, -20, 28, -100, 92, -36, 60, -68, 124, -4, 2, -126, 66, -62, 34, -94, 98, -30, 18, -110, 82, -46, 50, -78, 114, -14, 10, -118, 74, -54, 42, -86, 106, -22, 26, -102, 90, -38, 58, -70, 122, -6, 6, -122, 70, -58, 38, -90, 102, -26, 22, -106, 86, -42, 54, -74, 118, -10, 14, -114, 78, -50, 46, -82, 110, -18, 30, -98, 94, -34, 62, -66, 126, -2, 1, -127, 65, -63, 33, -95, 97, -31, 17, -111, 81, -47, 49, -79, 113, -15, 9, -119, 73, -55, 41, -87, 105, -23, 25, -103, 89, -39, 57, -71, 121, -7, 5, -123, 69, -59, 37, -91, 101, -27, 21, -107, 85, -43, 53, -75, 117, -11, 13, -115, 77, -51, 45, -83, 109, -19, 29, -99, 93, -35, 61, -67, 125, -3, 3, -125, 67, -61, 35, -93, 99, -29, 19, -109, 83, -45, 51, -77, 115, -13, 11, -117, 75, -53, 43, -85, 107, -21, 27, -101, 91, -37, 59, -69, 123, -5, 7, -121, 71, -57, 39, -89, 103, -25, 23, -105, 87, -41, 55, -73, 119, -9, 15, -113, 79, -49, 47, -81, 111, -17, 31, -97, 95, -33, 63, -65, Byte.MAX_VALUE, -1 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  172 */   static short[] white = new short[] { 6430, 6400, 6400, 6400, 3225, 3225, 3225, 3225, 944, 944, 944, 944, 976, 976, 976, 976, 1456, 1456, 1456, 1456, 1488, 1488, 1488, 1488, 718, 718, 718, 718, 718, 718, 718, 718, 750, 750, 750, 750, 750, 750, 750, 750, 1520, 1520, 1520, 1520, 1552, 1552, 1552, 1552, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 654, 654, 654, 654, 654, 654, 654, 654, 1072, 1072, 1072, 1072, 1104, 1104, 1104, 1104, 1136, 1136, 1136, 1136, 1168, 1168, 1168, 1168, 1200, 1200, 1200, 1200, 1232, 1232, 1232, 1232, 622, 622, 622, 622, 622, 622, 622, 622, 1008, 1008, 1008, 1008, 1040, 1040, 1040, 1040, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 1712, 1712, 1712, 1712, 1744, 1744, 1744, 1744, 846, 846, 846, 846, 846, 846, 846, 846, 1264, 1264, 1264, 1264, 1296, 1296, 1296, 1296, 1328, 1328, 1328, 1328, 1360, 1360, 1360, 1360, 1392, 1392, 1392, 1392, 1424, 1424, 1424, 1424, 686, 686, 686, 686, 686, 686, 686, 686, 910, 910, 910, 910, 910, 910, 910, 910, 1968, 1968, 1968, 1968, 2000, 2000, 2000, 2000, 2032, 2032, 2032, 2032, 16, 16, 16, 16, 10257, 10257, 10257, 10257, 12305, 12305, 12305, 12305, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 878, 878, 878, 878, 878, 878, 878, 878, 1904, 1904, 1904, 1904, 1936, 1936, 1936, 1936, -18413, -18413, -16365, -16365, -14317, -14317, -10221, -10221, 590, 590, 590, 590, 590, 590, 590, 590, 782, 782, 782, 782, 782, 782, 782, 782, 1584, 1584, 1584, 1584, 1616, 1616, 1616, 1616, 1648, 1648, 1648, 1648, 1680, 1680, 1680, 1680, 814, 814, 814, 814, 814, 814, 814, 814, 1776, 1776, 1776, 1776, 1808, 1808, 1808, 1808, 1840, 1840, 1840, 1840, 1872, 1872, 1872, 1872, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, 14353, 14353, 14353, 14353, 16401, 16401, 16401, 16401, 22547, 22547, 24595, 24595, 20497, 20497, 20497, 20497, 18449, 18449, 18449, 18449, 26643, 26643, 28691, 28691, 30739, 30739, -32749, -32749, -30701, -30701, -28653, -28653, -26605, -26605, -24557, -24557, -22509, -22509, -20461, -20461, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  432 */   static short[] additionalMakeup = new short[] { 28679, 28679, 31752, -32759, -31735, -30711, -29687, -28663, 29703, 29703, 30727, 30727, -27639, -26615, -25591, -24567 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  440 */   static short[] initBlack = new short[] { 3226, 6412, 200, 168, 38, 38, 134, 134, 100, 100, 100, 100, 68, 68, 68, 68 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  448 */   static short[] twoBitBlack = new short[] { 292, 260, 226, 226 };
/*      */ 
/*      */   
/*  451 */   static short[] black = new short[] { 62, 62, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 588, 588, 588, 588, 588, 588, 588, 588, 1680, 1680, 20499, 22547, 24595, 26643, 1776, 1776, 1808, 1808, -24557, -22509, -20461, -18413, 1904, 1904, 1936, 1936, -16365, -14317, 782, 782, 782, 782, 814, 814, 814, 814, -12269, -10221, 10257, 10257, 12305, 12305, 14353, 14353, 16403, 18451, 1712, 1712, 1744, 1744, 28691, 30739, -32749, -30701, -28653, -26605, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 750, 750, 750, 750, 1616, 1616, 1648, 1648, 1424, 1424, 1456, 1456, 1488, 1488, 1520, 1520, 1840, 1840, 1872, 1872, 1968, 1968, 8209, 8209, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 1552, 1552, 1584, 1584, 2000, 2000, 2032, 2032, 976, 976, 1008, 1008, 1040, 1040, 1072, 1072, 1296, 1296, 1328, 1328, 718, 718, 718, 718, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 4113, 4113, 6161, 6161, 848, 848, 880, 880, 912, 912, 944, 944, 622, 622, 622, 622, 654, 654, 654, 654, 1104, 1104, 1136, 1136, 1168, 1168, 1200, 1200, 1232, 1232, 1264, 1264, 686, 686, 686, 686, 1360, 1360, 1392, 1392, 12, 12, 12, 12, 12, 12, 12, 12, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  582 */   static byte[] twoDCodes = new byte[] { 80, 88, 23, 71, 30, 30, 62, 62, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void beginDecoding() {
/*  624 */     super.beginDecoding();
/*      */     
/*  626 */     if (this.metadata instanceof TIFFImageMetadata) {
/*  627 */       TIFFImageMetadata tmetadata = (TIFFImageMetadata)this.metadata;
/*      */ 
/*      */       
/*  630 */       TIFFField f = tmetadata.getTIFFField(266);
/*  631 */       this.fillOrder = (f == null) ? 1 : f.getAsInt(0);
/*      */       
/*  633 */       f = tmetadata.getTIFFField(259);
/*  634 */       this
/*  635 */         .compression = (f == null) ? 2 : f.getAsInt(0);
/*      */       
/*  637 */       f = tmetadata.getTIFFField(292);
/*  638 */       this.t4Options = (f == null) ? 0 : f.getAsInt(0);
/*  639 */       this.oneD = this.t4Options & 0x1;
/*      */       
/*  641 */       this.uncompressedMode = (this.t4Options & 0x2) >> 1;
/*  642 */       this.fillBits = (this.t4Options & 0x4) >> 2;
/*  643 */       f = tmetadata.getTIFFField(293);
/*  644 */       this.t6Options = (f == null) ? 0 : f.getAsInt(0);
/*      */     } else {
/*  646 */       this.fillOrder = 1;
/*      */       
/*  648 */       this.compression = 2;
/*      */       
/*  650 */       this.t4Options = 0;
/*  651 */       this.oneD = 0;
/*  652 */       this.uncompressedMode = 0;
/*  653 */       this.fillBits = 0;
/*  654 */       this.t6Options = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decodeRaw(byte[] b, int dstOffset, int pixelBitStride, int scanlineStride) throws IOException {
/*  662 */     this.buffer = b;
/*      */     
/*  664 */     this.w = this.srcWidth;
/*  665 */     this.h = this.srcHeight;
/*  666 */     this.bitsPerScanline = scanlineStride * 8;
/*  667 */     this.lineBitNum = 8 * dstOffset;
/*      */     
/*  669 */     this.data = new byte[this.byteCount];
/*  670 */     this.bitPointer = 0;
/*  671 */     this.bytePointer = 0;
/*  672 */     this.prevChangingElems = new int[this.w + 1];
/*  673 */     this.currChangingElems = new int[this.w + 1];
/*      */     
/*  675 */     this.stream.seek(this.offset);
/*  676 */     this.stream.readFully(this.data);
/*      */     
/*      */     try {
/*  679 */       if (this.compression == 2) {
/*  680 */         decodeRLE();
/*  681 */       } else if (this.compression == 3) {
/*  682 */         decodeT4();
/*  683 */       } else if (this.compression == 4) {
/*  684 */         this.uncompressedMode = (this.t6Options & 0x2) >> 1;
/*  685 */         decodeT6();
/*      */       } else {
/*  687 */         throw new IIOException("Unknown compression type " + this.compression);
/*      */       } 
/*  689 */     } catch (ArrayIndexOutOfBoundsException e) {
/*  690 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  691 */       e.printStackTrace(new PrintStream(baos));
/*  692 */       String s = new String(baos.toByteArray());
/*  693 */       warning("Ignoring exception:\n " + s);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void decodeRLE() throws IIOException {
/*  698 */     for (int i = 0; i < this.h; i++) {
/*      */       
/*  700 */       decodeNextScanline(this.srcMinY + i);
/*      */ 
/*      */       
/*  703 */       if (this.bitPointer != 0) {
/*  704 */         this.bytePointer++;
/*  705 */         this.bitPointer = 0;
/*      */       } 
/*      */ 
/*      */       
/*  709 */       this.lineBitNum += this.bitsPerScanline;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void decodeNextScanline(int lineIndex) throws IIOException {
/*  714 */     int bits = 0, code = 0, isT = 0;
/*      */     
/*  716 */     boolean isWhite = true;
/*  717 */     int dstEnd = 0;
/*      */     
/*  719 */     int bitOffset = 0;
/*      */ 
/*      */     
/*  722 */     this.changingElemSize = 0;
/*      */ 
/*      */     
/*  725 */     while (bitOffset < this.w) {
/*      */ 
/*      */       
/*  728 */       int runOffset = bitOffset;
/*      */       
/*  730 */       while (isWhite && bitOffset < this.w) {
/*      */         
/*  732 */         int current = nextNBits(10);
/*  733 */         int entry = white[current];
/*      */ 
/*      */         
/*  736 */         isT = entry & 0x1;
/*  737 */         bits = entry >>> 1 & 0xF;
/*      */         
/*  739 */         if (bits == 12) {
/*      */           
/*  741 */           int twoBits = nextLesserThan8Bits(2);
/*      */           
/*  743 */           current = current << 2 & 0xC | twoBits;
/*  744 */           entry = additionalMakeup[current];
/*  745 */           bits = entry >>> 1 & 0x7;
/*  746 */           code = entry >>> 4 & 0xFFF;
/*  747 */           bitOffset += code;
/*      */           
/*  749 */           updatePointer(4 - bits); continue;
/*  750 */         }  if (bits == 0) {
/*  751 */           warning("Error 0"); continue;
/*      */         } 
/*  753 */         if (bits == 15) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  758 */           warning("Premature EOL in white run of line " + lineIndex + ": read " + bitOffset + " of " + this.w + " expected pixels.");
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  763 */         code = entry >>> 5 & 0x7FF;
/*  764 */         bitOffset += code;
/*      */         
/*  766 */         updatePointer(10 - bits);
/*  767 */         if (isT == 0) {
/*  768 */           isWhite = false;
/*  769 */           this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  775 */       if (bitOffset == this.w) {
/*      */ 
/*      */ 
/*      */         
/*  779 */         int runLength = bitOffset - runOffset;
/*  780 */         if (isWhite && runLength != 0 && runLength % 64 == 0 && 
/*      */           
/*  782 */           nextNBits(8) != 53) {
/*  783 */           warning("Missing zero white run length terminating code!");
/*  784 */           updatePointer(8);
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/*      */       
/*  790 */       runOffset = bitOffset;
/*      */       
/*  792 */       while (!isWhite && bitOffset < this.w) {
/*      */         
/*  794 */         int current = nextLesserThan8Bits(4);
/*  795 */         int entry = initBlack[current];
/*      */ 
/*      */         
/*  798 */         isT = entry & 0x1;
/*  799 */         bits = entry >>> 1 & 0xF;
/*  800 */         code = entry >>> 5 & 0x7FF;
/*      */         
/*  802 */         if (code == 100) {
/*  803 */           current = nextNBits(9);
/*  804 */           entry = black[current];
/*      */ 
/*      */           
/*  807 */           isT = entry & 0x1;
/*  808 */           bits = entry >>> 1 & 0xF;
/*  809 */           code = entry >>> 5 & 0x7FF;
/*      */           
/*  811 */           if (bits == 12) {
/*      */             
/*  813 */             updatePointer(5);
/*  814 */             current = nextLesserThan8Bits(4);
/*  815 */             entry = additionalMakeup[current];
/*  816 */             bits = entry >>> 1 & 0x7;
/*  817 */             code = entry >>> 4 & 0xFFF;
/*      */             
/*  819 */             setToBlack(bitOffset, code);
/*  820 */             bitOffset += code;
/*      */             
/*  822 */             updatePointer(4 - bits); continue;
/*  823 */           }  if (bits == 15) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  828 */             warning("Premature EOL in black run of line " + lineIndex + ": read " + bitOffset + " of " + this.w + " expected pixels.");
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/*  833 */           setToBlack(bitOffset, code);
/*  834 */           bitOffset += code;
/*      */           
/*  836 */           updatePointer(9 - bits);
/*  837 */           if (isT == 0) {
/*  838 */             isWhite = true;
/*  839 */             this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */           }  continue;
/*      */         } 
/*  842 */         if (code == 200) {
/*      */           
/*  844 */           current = nextLesserThan8Bits(2);
/*  845 */           entry = twoBitBlack[current];
/*  846 */           code = entry >>> 5 & 0x7FF;
/*  847 */           bits = entry >>> 1 & 0xF;
/*      */           
/*  849 */           setToBlack(bitOffset, code);
/*  850 */           bitOffset += code;
/*      */           
/*  852 */           updatePointer(2 - bits);
/*  853 */           isWhite = true;
/*  854 */           this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */           continue;
/*      */         } 
/*  857 */         setToBlack(bitOffset, code);
/*  858 */         bitOffset += code;
/*      */         
/*  860 */         updatePointer(4 - bits);
/*  861 */         isWhite = true;
/*  862 */         this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  867 */       if (bitOffset == this.w) {
/*      */ 
/*      */ 
/*      */         
/*  871 */         int runLength = bitOffset - runOffset;
/*  872 */         if (!isWhite && runLength != 0 && runLength % 64 == 0 && 
/*      */           
/*  874 */           nextNBits(10) != 55) {
/*  875 */           warning("Missing zero black run length terminating code!");
/*  876 */           updatePointer(10);
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  882 */     this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */   }
/*      */   
/*      */   public void decodeT4() throws IIOException {
/*  886 */     int height = this.h;
/*      */ 
/*      */     
/*  889 */     int[] b = new int[2];
/*      */ 
/*      */     
/*  892 */     int currIndex = 0;
/*      */ 
/*      */     
/*  895 */     if (this.data.length < 2) {
/*  896 */       throw new IIOException("Insufficient data to read initial EOL.");
/*      */     }
/*      */ 
/*      */     
/*  900 */     int next12 = nextNBits(12);
/*  901 */     if (next12 != 1) {
/*  902 */       warning("T.4 compressed data should begin with EOL.");
/*      */     }
/*  904 */     updatePointer(12);
/*      */ 
/*      */     
/*  907 */     int modeFlag = 0;
/*  908 */     int lines = -1;
/*  909 */     while (modeFlag != 1) {
/*      */       try {
/*  911 */         modeFlag = findNextLine();
/*  912 */         lines++;
/*  913 */       } catch (EOFException eofe) {
/*  914 */         throw new IIOException("No reference line present.");
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  922 */     decodeNextScanline(this.srcMinY);
/*  923 */     lines++;
/*  924 */     this.lineBitNum += this.bitsPerScanline;
/*      */     
/*  926 */     while (lines < height) {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/*  931 */         modeFlag = findNextLine();
/*  932 */       } catch (EOFException eofe) {
/*  933 */         warning("Input exhausted before EOL found at line " + (this.srcMinY + lines) + ": read 0 of " + this.w + " expected pixels.");
/*      */         
/*      */         break;
/*      */       } 
/*  937 */       if (modeFlag == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  942 */         int[] temp = this.prevChangingElems;
/*  943 */         this.prevChangingElems = this.currChangingElems;
/*  944 */         this.currChangingElems = temp;
/*  945 */         currIndex = 0;
/*      */ 
/*      */         
/*  948 */         int a0 = -1;
/*  949 */         boolean isWhite = true;
/*  950 */         int bitOffset = 0;
/*      */         
/*  952 */         this.lastChangingElement = 0;
/*      */         
/*  954 */         while (bitOffset < this.w) {
/*      */           
/*  956 */           getNextChangingElement(a0, isWhite, b);
/*      */           
/*  958 */           int b1 = b[0];
/*  959 */           int b2 = b[1];
/*      */ 
/*      */           
/*  962 */           int entry = nextLesserThan8Bits(7);
/*      */ 
/*      */           
/*  965 */           entry = twoDCodes[entry] & 0xFF;
/*      */ 
/*      */           
/*  968 */           int code = (entry & 0x78) >>> 3;
/*  969 */           int bits = entry & 0x7;
/*      */           
/*  971 */           if (code == 0) {
/*  972 */             if (!isWhite) {
/*  973 */               setToBlack(bitOffset, b2 - bitOffset);
/*      */             }
/*  975 */             bitOffset = a0 = b2;
/*      */ 
/*      */             
/*  978 */             updatePointer(7 - bits); continue;
/*  979 */           }  if (code == 1) {
/*      */             
/*  981 */             updatePointer(7 - bits);
/*      */ 
/*      */ 
/*      */             
/*  985 */             if (isWhite) {
/*  986 */               int number = decodeWhiteCodeWord();
/*  987 */               bitOffset += number;
/*  988 */               this.currChangingElems[currIndex++] = bitOffset;
/*      */               
/*  990 */               number = decodeBlackCodeWord();
/*  991 */               setToBlack(bitOffset, number);
/*  992 */               bitOffset += number;
/*  993 */               this.currChangingElems[currIndex++] = bitOffset;
/*      */             } else {
/*  995 */               int number = decodeBlackCodeWord();
/*  996 */               setToBlack(bitOffset, number);
/*  997 */               bitOffset += number;
/*  998 */               this.currChangingElems[currIndex++] = bitOffset;
/*      */               
/* 1000 */               number = decodeWhiteCodeWord();
/* 1001 */               bitOffset += number;
/* 1002 */               this.currChangingElems[currIndex++] = bitOffset;
/*      */             } 
/*      */             
/* 1005 */             a0 = bitOffset; continue;
/* 1006 */           }  if (code <= 8) {
/*      */             
/* 1008 */             int a1 = b1 + code - 5;
/*      */             
/* 1010 */             this.currChangingElems[currIndex++] = a1;
/*      */ 
/*      */ 
/*      */             
/* 1014 */             if (!isWhite) {
/* 1015 */               setToBlack(bitOffset, a1 - bitOffset);
/*      */             }
/* 1017 */             bitOffset = a0 = a1;
/* 1018 */             isWhite = !isWhite;
/*      */             
/* 1020 */             updatePointer(7 - bits); continue;
/*      */           } 
/* 1022 */           warning("Unknown coding mode encountered at line " + (this.srcMinY + lines) + ": read " + bitOffset + " of " + this.w + " expected pixels.");
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1027 */           int numLinesTested = 0;
/* 1028 */           while (modeFlag != 1) {
/*      */             try {
/* 1030 */               modeFlag = findNextLine();
/* 1031 */               numLinesTested++;
/* 1032 */             } catch (EOFException eofe) {
/* 1033 */               warning("Sync loss at line " + (this.srcMinY + lines) + ": read " + lines + " of " + height + " lines.");
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */           
/* 1039 */           lines += numLinesTested - 1;
/* 1040 */           updatePointer(13);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1047 */         this.currChangingElems[currIndex++] = bitOffset;
/* 1048 */         this.changingElemSize = currIndex;
/*      */       } else {
/*      */         
/* 1051 */         decodeNextScanline(this.srcMinY + lines);
/*      */       } 
/*      */       
/* 1054 */       this.lineBitNum += this.bitsPerScanline;
/* 1055 */       lines++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void decodeT6() throws IIOException {
/* 1060 */     int height = this.h;
/*      */     
/* 1062 */     int bufferOffset = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1072 */     int[] b = new int[2];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1079 */     int[] cce = this.currChangingElems;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1084 */     this.changingElemSize = 0;
/* 1085 */     cce[this.changingElemSize++] = this.w;
/* 1086 */     cce[this.changingElemSize++] = this.w;
/*      */ 
/*      */ 
/*      */     
/* 1090 */     for (int lines = 0; lines < height; lines++) {
/*      */       
/* 1092 */       int a0 = -1;
/* 1093 */       boolean isWhite = true;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1098 */       int[] temp = this.prevChangingElems;
/* 1099 */       this.prevChangingElems = this.currChangingElems;
/* 1100 */       cce = this.currChangingElems = temp;
/* 1101 */       int currIndex = 0;
/*      */ 
/*      */       
/* 1104 */       int bitOffset = 0;
/*      */ 
/*      */       
/* 1107 */       this.lastChangingElement = 0;
/*      */ 
/*      */       
/* 1110 */       while (bitOffset < this.w) {
/*      */         
/* 1112 */         getNextChangingElement(a0, isWhite, b);
/* 1113 */         int b1 = b[0];
/* 1114 */         int b2 = b[1];
/*      */ 
/*      */         
/* 1117 */         int entry = nextLesserThan8Bits(7);
/*      */         
/* 1119 */         entry = twoDCodes[entry] & 0xFF;
/*      */ 
/*      */         
/* 1122 */         int code = (entry & 0x78) >>> 3;
/* 1123 */         int bits = entry & 0x7;
/*      */         
/* 1125 */         if (code == 0) {
/*      */           
/* 1127 */           if (!isWhite) {
/* 1128 */             if (b2 > this.w) {
/* 1129 */               b2 = this.w;
/* 1130 */               warning("Decoded row " + (this.srcMinY + lines) + " too long; ignoring extra samples.");
/*      */             } 
/*      */             
/* 1133 */             setToBlack(bitOffset, b2 - bitOffset);
/*      */           } 
/* 1135 */           bitOffset = a0 = b2;
/*      */ 
/*      */           
/* 1138 */           updatePointer(7 - bits); continue;
/* 1139 */         }  if (code == 1) {
/*      */           
/* 1141 */           updatePointer(7 - bits);
/*      */ 
/*      */ 
/*      */           
/* 1145 */           if (isWhite) {
/*      */             
/* 1147 */             int number = decodeWhiteCodeWord();
/* 1148 */             bitOffset += number;
/* 1149 */             cce[currIndex++] = bitOffset;
/*      */             
/* 1151 */             number = decodeBlackCodeWord();
/* 1152 */             if (number > this.w - bitOffset) {
/* 1153 */               number = this.w - bitOffset;
/* 1154 */               warning("Decoded row " + (this.srcMinY + lines) + " too long; ignoring extra samples.");
/*      */             } 
/*      */             
/* 1157 */             setToBlack(bitOffset, number);
/* 1158 */             bitOffset += number;
/* 1159 */             cce[currIndex++] = bitOffset;
/*      */           } else {
/*      */             
/* 1162 */             int number = decodeBlackCodeWord();
/* 1163 */             if (number > this.w - bitOffset) {
/* 1164 */               number = this.w - bitOffset;
/* 1165 */               warning("Decoded row " + (this.srcMinY + lines) + " too long; ignoring extra samples.");
/*      */             } 
/*      */             
/* 1168 */             setToBlack(bitOffset, number);
/* 1169 */             bitOffset += number;
/* 1170 */             cce[currIndex++] = bitOffset;
/*      */             
/* 1172 */             number = decodeWhiteCodeWord();
/* 1173 */             bitOffset += number;
/* 1174 */             cce[currIndex++] = bitOffset;
/*      */           } 
/*      */           
/* 1177 */           a0 = bitOffset; continue;
/* 1178 */         }  if (code <= 8) {
/* 1179 */           int a1 = b1 + code - 5;
/* 1180 */           cce[currIndex++] = a1;
/*      */ 
/*      */ 
/*      */           
/* 1184 */           if (!isWhite) {
/* 1185 */             if (a1 > this.w) {
/* 1186 */               a1 = this.w;
/* 1187 */               warning("Decoded row " + (this.srcMinY + lines) + " too long; ignoring extra samples.");
/*      */             } 
/*      */             
/* 1190 */             setToBlack(bitOffset, a1 - bitOffset);
/*      */           } 
/* 1192 */           bitOffset = a0 = a1;
/* 1193 */           isWhite = !isWhite;
/*      */           
/* 1195 */           updatePointer(7 - bits); continue;
/* 1196 */         }  if (code == 11) {
/* 1197 */           int entranceCode = nextLesserThan8Bits(3);
/* 1198 */           if (entranceCode != 7) {
/* 1199 */             String str = "Unsupported entrance code " + entranceCode + " for extension mode at line " + (this.srcMinY + lines) + ".";
/*      */ 
/*      */             
/* 1202 */             warning(str);
/*      */           } 
/*      */           
/* 1205 */           int zeros = 0;
/* 1206 */           boolean exit = false;
/*      */           
/* 1208 */           while (!exit) {
/* 1209 */             while (nextLesserThan8Bits(1) != 1) {
/* 1210 */               zeros++;
/*      */             }
/*      */             
/* 1213 */             if (zeros > 5) {
/*      */ 
/*      */ 
/*      */               
/* 1217 */               zeros -= 6;
/*      */               
/* 1219 */               if (!isWhite && zeros > 0) {
/* 1220 */                 cce[currIndex++] = bitOffset;
/*      */               }
/*      */ 
/*      */               
/* 1224 */               bitOffset += zeros;
/* 1225 */               if (zeros > 0)
/*      */               {
/* 1227 */                 isWhite = true;
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1232 */               if (nextLesserThan8Bits(1) == 0) {
/* 1233 */                 if (!isWhite) {
/* 1234 */                   cce[currIndex++] = bitOffset;
/*      */                 }
/* 1236 */                 isWhite = true;
/*      */               } else {
/* 1238 */                 if (isWhite) {
/* 1239 */                   cce[currIndex++] = bitOffset;
/*      */                 }
/* 1241 */                 isWhite = false;
/*      */               } 
/*      */               
/* 1244 */               exit = true;
/*      */             } 
/*      */             
/* 1247 */             if (zeros == 5) {
/* 1248 */               if (!isWhite) {
/* 1249 */                 cce[currIndex++] = bitOffset;
/*      */               }
/* 1251 */               bitOffset += zeros;
/*      */ 
/*      */               
/* 1254 */               isWhite = true; continue;
/*      */             } 
/* 1256 */             bitOffset += zeros;
/*      */             
/* 1258 */             cce[currIndex++] = bitOffset;
/* 1259 */             setToBlack(bitOffset, 1);
/* 1260 */             bitOffset++;
/*      */ 
/*      */             
/* 1263 */             isWhite = false;
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/* 1268 */         String msg = "Unknown coding mode encountered at line " + (this.srcMinY + lines) + ".";
/*      */ 
/*      */         
/* 1271 */         warning(msg);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1277 */       if (currIndex <= this.w) {
/* 1278 */         cce[currIndex++] = bitOffset;
/*      */       }
/*      */       
/* 1281 */       this.changingElemSize = currIndex;
/*      */       
/* 1283 */       this.lineBitNum += this.bitsPerScanline;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setToBlack(int bitNum, int numBits) {
/* 1289 */     bitNum += this.lineBitNum;
/*      */     
/* 1291 */     int lastBit = bitNum + numBits;
/* 1292 */     int byteNum = bitNum >> 3;
/*      */ 
/*      */     
/* 1295 */     int shift = bitNum & 0x7;
/* 1296 */     if (shift > 0) {
/* 1297 */       int maskVal = 1 << 7 - shift;
/* 1298 */       byte val = this.buffer[byteNum];
/* 1299 */       while (maskVal > 0 && bitNum < lastBit) {
/* 1300 */         val = (byte)(val | maskVal);
/* 1301 */         maskVal >>= 1;
/* 1302 */         bitNum++;
/*      */       } 
/* 1304 */       this.buffer[byteNum] = val;
/*      */     } 
/*      */ 
/*      */     
/* 1308 */     byteNum = bitNum >> 3;
/* 1309 */     while (bitNum < lastBit - 7) {
/* 1310 */       this.buffer[byteNum++] = -1;
/* 1311 */       bitNum += 8;
/*      */     } 
/*      */ 
/*      */     
/* 1315 */     while (bitNum < lastBit) {
/* 1316 */       byteNum = bitNum >> 3;
/* 1317 */       this.buffer[byteNum] = (byte)(this.buffer[byteNum] | 1 << 7 - (bitNum & 0x7));
/* 1318 */       bitNum++;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private int decodeWhiteCodeWord() throws IIOException {
/* 1324 */     int code = -1;
/* 1325 */     int runLength = 0;
/* 1326 */     boolean isWhite = true;
/*      */     
/* 1328 */     while (isWhite) {
/* 1329 */       int current = nextNBits(10);
/* 1330 */       int entry = white[current];
/*      */ 
/*      */       
/* 1333 */       int isT = entry & 0x1;
/* 1334 */       int bits = entry >>> 1 & 0xF;
/*      */       
/* 1336 */       if (bits == 12) {
/*      */         
/* 1338 */         int twoBits = nextLesserThan8Bits(2);
/*      */         
/* 1340 */         current = current << 2 & 0xC | twoBits;
/* 1341 */         entry = additionalMakeup[current];
/* 1342 */         bits = entry >>> 1 & 0x7;
/* 1343 */         code = entry >>> 4 & 0xFFF;
/* 1344 */         runLength += code;
/* 1345 */         updatePointer(4 - bits); continue;
/* 1346 */       }  if (bits == 0)
/* 1347 */         throw new IIOException("Error 0"); 
/* 1348 */       if (bits == 15) {
/* 1349 */         throw new IIOException("Error 1");
/*      */       }
/*      */       
/* 1352 */       code = entry >>> 5 & 0x7FF;
/* 1353 */       runLength += code;
/* 1354 */       updatePointer(10 - bits);
/* 1355 */       if (isT == 0) {
/* 1356 */         isWhite = false;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1361 */     return runLength;
/*      */   }
/*      */ 
/*      */   
/*      */   private int decodeBlackCodeWord() throws IIOException {
/* 1366 */     int code = -1;
/* 1367 */     int runLength = 0;
/* 1368 */     boolean isWhite = false;
/*      */     
/* 1370 */     while (!isWhite) {
/* 1371 */       int current = nextLesserThan8Bits(4);
/* 1372 */       int entry = initBlack[current];
/*      */ 
/*      */       
/* 1375 */       int isT = entry & 0x1;
/* 1376 */       int bits = entry >>> 1 & 0xF;
/* 1377 */       code = entry >>> 5 & 0x7FF;
/*      */       
/* 1379 */       if (code == 100) {
/* 1380 */         current = nextNBits(9);
/* 1381 */         entry = black[current];
/*      */ 
/*      */         
/* 1384 */         isT = entry & 0x1;
/* 1385 */         bits = entry >>> 1 & 0xF;
/* 1386 */         code = entry >>> 5 & 0x7FF;
/*      */         
/* 1388 */         if (bits == 12) {
/*      */           
/* 1390 */           updatePointer(5);
/* 1391 */           current = nextLesserThan8Bits(4);
/* 1392 */           entry = additionalMakeup[current];
/* 1393 */           bits = entry >>> 1 & 0x7;
/* 1394 */           code = entry >>> 4 & 0xFFF;
/* 1395 */           runLength += code;
/*      */           
/* 1397 */           updatePointer(4 - bits); continue;
/* 1398 */         }  if (bits == 15)
/*      */         {
/* 1400 */           throw new IIOException("Error 2");
/*      */         }
/* 1402 */         runLength += code;
/* 1403 */         updatePointer(9 - bits);
/* 1404 */         if (isT == 0)
/* 1405 */           isWhite = true; 
/*      */         continue;
/*      */       } 
/* 1408 */       if (code == 200) {
/*      */         
/* 1410 */         current = nextLesserThan8Bits(2);
/* 1411 */         entry = twoBitBlack[current];
/* 1412 */         code = entry >>> 5 & 0x7FF;
/* 1413 */         runLength += code;
/* 1414 */         bits = entry >>> 1 & 0xF;
/* 1415 */         updatePointer(2 - bits);
/* 1416 */         isWhite = true;
/*      */         continue;
/*      */       } 
/* 1419 */       runLength += code;
/* 1420 */       updatePointer(4 - bits);
/* 1421 */       isWhite = true;
/*      */     } 
/*      */ 
/*      */     
/* 1425 */     return runLength;
/*      */   }
/*      */ 
/*      */   
/*      */   private int findNextLine() throws IIOException, EOFException {
/* 1430 */     int bitIndexMax = this.data.length * 8 - 1;
/* 1431 */     int bitIndexMax12 = bitIndexMax - 12;
/* 1432 */     int bitIndex = this.bytePointer * 8 + this.bitPointer;
/*      */ 
/*      */     
/* 1435 */     while (bitIndex <= bitIndexMax12) {
/*      */       
/* 1437 */       int next12Bits = nextNBits(12);
/* 1438 */       bitIndex += 12;
/*      */ 
/*      */ 
/*      */       
/* 1442 */       while (next12Bits != 1 && bitIndex < bitIndexMax) {
/*      */ 
/*      */         
/* 1445 */         next12Bits = (next12Bits & 0x7FF) << 1 | nextLesserThan8Bits(1) & 0x1;
/* 1446 */         bitIndex++;
/*      */       } 
/*      */       
/* 1449 */       if (next12Bits == 1) {
/* 1450 */         if (this.oneD == 1) {
/* 1451 */           if (bitIndex < bitIndexMax)
/*      */           {
/* 1453 */             return nextLesserThan8Bits(1); } 
/*      */           continue;
/*      */         } 
/* 1456 */         return 1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1462 */     throw new EOFException();
/*      */   }
/*      */ 
/*      */   
/*      */   private void getNextChangingElement(int a0, boolean isWhite, int[] ret) throws IIOException {
/* 1467 */     int[] pce = this.prevChangingElems;
/* 1468 */     int ces = this.changingElemSize;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1473 */     int start = (this.lastChangingElement > 0) ? (this.lastChangingElement - 1) : 0;
/* 1474 */     if (isWhite) {
/* 1475 */       start &= 0xFFFFFFFE;
/*      */     } else {
/* 1477 */       start |= 0x1;
/*      */     } 
/*      */     
/* 1480 */     int i = start;
/* 1481 */     for (; i < ces; i += 2) {
/* 1482 */       int temp = pce[i];
/* 1483 */       if (temp > a0) {
/* 1484 */         this.lastChangingElement = i;
/* 1485 */         ret[0] = temp;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1490 */     if (i + 1 < ces) {
/* 1491 */       ret[1] = pce[i + 1];
/*      */     }
/*      */   }
/*      */   
/*      */   private int nextNBits(int bitsToGet) throws IIOException {
/*      */     byte b, next, next2next;
/* 1497 */     int l = this.data.length - 1;
/* 1498 */     int bp = this.bytePointer;
/*      */     
/* 1500 */     if (this.fillOrder == 1) {
/* 1501 */       b = this.data[bp];
/*      */       
/* 1503 */       if (bp == l) {
/* 1504 */         next = 0;
/* 1505 */         next2next = 0;
/* 1506 */       } else if (bp + 1 == l) {
/* 1507 */         next = this.data[bp + 1];
/* 1508 */         next2next = 0;
/*      */       } else {
/* 1510 */         next = this.data[bp + 1];
/* 1511 */         next2next = this.data[bp + 2];
/*      */       } 
/* 1513 */     } else if (this.fillOrder == 2) {
/* 1514 */       b = flipTable[this.data[bp] & 0xFF];
/*      */       
/* 1516 */       if (bp == l) {
/* 1517 */         next = 0;
/* 1518 */         next2next = 0;
/* 1519 */       } else if (bp + 1 == l) {
/* 1520 */         next = flipTable[this.data[bp + 1] & 0xFF];
/* 1521 */         next2next = 0;
/*      */       } else {
/* 1523 */         next = flipTable[this.data[bp + 1] & 0xFF];
/* 1524 */         next2next = flipTable[this.data[bp + 2] & 0xFF];
/*      */       } 
/*      */     } else {
/* 1527 */       throw new IIOException("Invalid FillOrder");
/*      */     } 
/*      */     
/* 1530 */     int bitsLeft = 8 - this.bitPointer;
/* 1531 */     int bitsFromNextByte = bitsToGet - bitsLeft;
/* 1532 */     int bitsFromNext2NextByte = 0;
/* 1533 */     if (bitsFromNextByte > 8) {
/* 1534 */       bitsFromNext2NextByte = bitsFromNextByte - 8;
/* 1535 */       bitsFromNextByte = 8;
/*      */     } 
/*      */     
/* 1538 */     this.bytePointer++;
/*      */     
/* 1540 */     int i1 = (b & table1[bitsLeft]) << bitsToGet - bitsLeft;
/* 1541 */     int i2 = (next & table2[bitsFromNextByte]) >>> 8 - bitsFromNextByte;
/*      */     
/* 1543 */     int i3 = 0;
/* 1544 */     if (bitsFromNext2NextByte != 0) {
/* 1545 */       i2 <<= bitsFromNext2NextByte;
/* 1546 */       i3 = (next2next & table2[bitsFromNext2NextByte]) >>> 8 - bitsFromNext2NextByte;
/*      */       
/* 1548 */       i2 |= i3;
/* 1549 */       this.bytePointer++;
/* 1550 */       this.bitPointer = bitsFromNext2NextByte;
/*      */     }
/* 1552 */     else if (bitsFromNextByte == 8) {
/* 1553 */       this.bitPointer = 0;
/* 1554 */       this.bytePointer++;
/*      */     } else {
/* 1556 */       this.bitPointer = bitsFromNextByte;
/*      */     } 
/*      */ 
/*      */     
/* 1560 */     int i = i1 | i2;
/* 1561 */     return i;
/*      */   }
/*      */   
/*      */   private int nextLesserThan8Bits(int bitsToGet) throws IIOException {
/*      */     byte b, next;
/* 1566 */     int i1, l = this.data.length - 1;
/* 1567 */     int bp = this.bytePointer;
/*      */     
/* 1569 */     if (this.fillOrder == 1) {
/* 1570 */       b = this.data[bp];
/* 1571 */       if (bp == l) {
/* 1572 */         next = 0;
/*      */       } else {
/* 1574 */         next = this.data[bp + 1];
/*      */       } 
/* 1576 */     } else if (this.fillOrder == 2) {
/* 1577 */       b = flipTable[this.data[bp] & 0xFF];
/* 1578 */       if (bp == l) {
/* 1579 */         next = 0;
/*      */       } else {
/* 1581 */         next = flipTable[this.data[bp + 1] & 0xFF];
/*      */       } 
/*      */     } else {
/* 1584 */       throw new IIOException("Invalid FillOrder");
/*      */     } 
/*      */     
/* 1587 */     int bitsLeft = 8 - this.bitPointer;
/* 1588 */     int bitsFromNextByte = bitsToGet - bitsLeft;
/*      */     
/* 1590 */     int shift = bitsLeft - bitsToGet;
/*      */     
/* 1592 */     if (shift >= 0) {
/* 1593 */       i1 = (b & table1[bitsLeft]) >>> shift;
/* 1594 */       this.bitPointer += bitsToGet;
/* 1595 */       if (this.bitPointer == 8) {
/* 1596 */         this.bitPointer = 0;
/* 1597 */         this.bytePointer++;
/*      */       } 
/*      */     } else {
/* 1600 */       i1 = (b & table1[bitsLeft]) << -shift;
/* 1601 */       int i2 = (next & table2[bitsFromNextByte]) >>> 8 - bitsFromNextByte;
/*      */       
/* 1603 */       i1 |= i2;
/* 1604 */       this.bytePointer++;
/* 1605 */       this.bitPointer = bitsFromNextByte;
/*      */     } 
/*      */     
/* 1608 */     return i1;
/*      */   }
/*      */ 
/*      */   
/*      */   private void updatePointer(int bitsToMoveBack) {
/* 1613 */     if (bitsToMoveBack > 8) {
/* 1614 */       this.bytePointer -= bitsToMoveBack / 8;
/* 1615 */       bitsToMoveBack %= 8;
/*      */     } 
/*      */     
/* 1618 */     int i = this.bitPointer - bitsToMoveBack;
/* 1619 */     if (i < 0) {
/* 1620 */       this.bytePointer--;
/* 1621 */       this.bitPointer = 8 + i;
/*      */     } else {
/* 1623 */       this.bitPointer = i;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void warning(String msg) {
/* 1629 */     if (this.reader instanceof TIFFImageReader)
/* 1630 */       ((TIFFImageReader)this.reader).forwardWarningMessage(msg); 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFFaxDecompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */