/*      */ package org.apache.xmlgraphics.image.codec.tiff;
/*      */ 
/*      */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class TIFFFaxDecoder
/*      */ {
/*      */   private int bitPointer;
/*      */   private int bytePointer;
/*      */   private byte[] data;
/*      */   private int w;
/*      */   private int fillOrder;
/*      */   private int changingElemSize;
/*      */   private int[] prevChangingElems;
/*      */   private int[] currChangingElems;
/*      */   private int lastChangingElement;
/*   46 */   private int compression = 2;
/*      */ 
/*      */   
/*      */   private int fillBits;
/*      */   
/*      */   private int oneD;
/*      */   
/*   53 */   static int[] table1 = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   65 */   static int[] table2 = new int[] { 0, 128, 192, 224, 240, 248, 252, 254, 255 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   78 */   static byte[] flipTable = new byte[] { 0, Byte.MIN_VALUE, 64, -64, 32, -96, 96, -32, 16, -112, 80, -48, 48, -80, 112, -16, 8, -120, 72, -56, 40, -88, 104, -24, 24, -104, 88, -40, 56, -72, 120, -8, 4, -124, 68, -60, 36, -92, 100, -28, 20, -108, 84, -44, 52, -76, 116, -12, 12, -116, 76, -52, 44, -84, 108, -20, 28, -100, 92, -36, 60, -68, 124, -4, 2, -126, 66, -62, 34, -94, 98, -30, 18, -110, 82, -46, 50, -78, 114, -14, 10, -118, 74, -54, 42, -86, 106, -22, 26, -102, 90, -38, 58, -70, 122, -6, 6, -122, 70, -58, 38, -90, 102, -26, 22, -106, 86, -42, 54, -74, 118, -10, 14, -114, 78, -50, 46, -82, 110, -18, 30, -98, 94, -34, 62, -66, 126, -2, 1, -127, 65, -63, 33, -95, 97, -31, 17, -111, 81, -47, 49, -79, 113, -15, 9, -119, 73, -55, 41, -87, 105, -23, 25, -103, 89, -39, 57, -71, 121, -7, 5, -123, 69, -59, 37, -91, 101, -27, 21, -107, 85, -43, 53, -75, 117, -11, 13, -115, 77, -51, 45, -83, 109, -19, 29, -99, 93, -35, 61, -67, 125, -3, 3, -125, 67, -61, 35, -93, 99, -29, 19, -109, 83, -45, 51, -77, 115, -13, 11, -117, 75, -53, 43, -85, 107, -21, 27, -101, 91, -37, 59, -69, 123, -5, 7, -121, 71, -57, 39, -89, 103, -25, 23, -105, 87, -41, 55, -73, 119, -9, 15, -113, 79, -49, 47, -81, 111, -17, 31, -97, 95, -33, 63, -65, Byte.MAX_VALUE, -1 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  114 */   static short[] white = new short[] { 6430, 6400, 6400, 6400, 3225, 3225, 3225, 3225, 944, 944, 944, 944, 976, 976, 976, 976, 1456, 1456, 1456, 1456, 1488, 1488, 1488, 1488, 718, 718, 718, 718, 718, 718, 718, 718, 750, 750, 750, 750, 750, 750, 750, 750, 1520, 1520, 1520, 1520, 1552, 1552, 1552, 1552, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 654, 654, 654, 654, 654, 654, 654, 654, 1072, 1072, 1072, 1072, 1104, 1104, 1104, 1104, 1136, 1136, 1136, 1136, 1168, 1168, 1168, 1168, 1200, 1200, 1200, 1200, 1232, 1232, 1232, 1232, 622, 622, 622, 622, 622, 622, 622, 622, 1008, 1008, 1008, 1008, 1040, 1040, 1040, 1040, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 1712, 1712, 1712, 1712, 1744, 1744, 1744, 1744, 846, 846, 846, 846, 846, 846, 846, 846, 1264, 1264, 1264, 1264, 1296, 1296, 1296, 1296, 1328, 1328, 1328, 1328, 1360, 1360, 1360, 1360, 1392, 1392, 1392, 1392, 1424, 1424, 1424, 1424, 686, 686, 686, 686, 686, 686, 686, 686, 910, 910, 910, 910, 910, 910, 910, 910, 1968, 1968, 1968, 1968, 2000, 2000, 2000, 2000, 2032, 2032, 2032, 2032, 16, 16, 16, 16, 10257, 10257, 10257, 10257, 12305, 12305, 12305, 12305, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 878, 878, 878, 878, 878, 878, 878, 878, 1904, 1904, 1904, 1904, 1936, 1936, 1936, 1936, -18413, -18413, -16365, -16365, -14317, -14317, -10221, -10221, 590, 590, 590, 590, 590, 590, 590, 590, 782, 782, 782, 782, 782, 782, 782, 782, 1584, 1584, 1584, 1584, 1616, 1616, 1616, 1616, 1648, 1648, 1648, 1648, 1680, 1680, 1680, 1680, 814, 814, 814, 814, 814, 814, 814, 814, 1776, 1776, 1776, 1776, 1808, 1808, 1808, 1808, 1840, 1840, 1840, 1840, 1872, 1872, 1872, 1872, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, 14353, 14353, 14353, 14353, 16401, 16401, 16401, 16401, 22547, 22547, 24595, 24595, 20497, 20497, 20497, 20497, 18449, 18449, 18449, 18449, 26643, 26643, 28691, 28691, 30739, 30739, -32749, -32749, -30701, -30701, -28653, -28653, -26605, -26605, -24557, -24557, -22509, -22509, -20461, -20461, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 460, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 232 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  374 */   static short[] additionalMakeup = new short[] { 28679, 28679, 31752, -32759, -31735, -30711, -29687, -28663, 29703, 29703, 30727, 30727, -27639, -26615, -25591, -24567 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  382 */   static short[] initBlack = new short[] { 3226, 6412, 200, 168, 38, 38, 134, 134, 100, 100, 100, 100, 68, 68, 68, 68 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  390 */   static short[] twoBitBlack = new short[] { 292, 260, 226, 226 };
/*      */ 
/*      */   
/*  393 */   static short[] black = new short[] { 62, 62, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 588, 588, 588, 588, 588, 588, 588, 588, 1680, 1680, 20499, 22547, 24595, 26643, 1776, 1776, 1808, 1808, -24557, -22509, -20461, -18413, 1904, 1904, 1936, 1936, -16365, -14317, 782, 782, 782, 782, 814, 814, 814, 814, -12269, -10221, 10257, 10257, 12305, 12305, 14353, 14353, 16403, 18451, 1712, 1712, 1744, 1744, 28691, 30739, -32749, -30701, -28653, -26605, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 750, 750, 750, 750, 1616, 1616, 1648, 1648, 1424, 1424, 1456, 1456, 1488, 1488, 1520, 1520, 1840, 1840, 1872, 1872, 1968, 1968, 8209, 8209, 524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 1552, 1552, 1584, 1584, 2000, 2000, 2032, 2032, 976, 976, 1008, 1008, 1040, 1040, 1072, 1072, 1296, 1296, 1328, 1328, 718, 718, 718, 718, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 4113, 4113, 6161, 6161, 848, 848, 880, 880, 912, 912, 944, 944, 622, 622, 622, 622, 654, 654, 654, 654, 1104, 1104, 1136, 1136, 1168, 1168, 1200, 1200, 1232, 1232, 1264, 1264, 686, 686, 686, 686, 1360, 1360, 1392, 1392, 12, 12, 12, 12, 12, 12, 12, 12, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 390 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  524 */   static byte[] twoDCodes = new byte[] { 80, 88, 23, 71, 30, 30, 62, 62, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TIFFFaxDecoder(int fillOrder, int w, int h) {
/*  565 */     this.fillOrder = fillOrder;
/*  566 */     this.w = w;
/*      */ 
/*      */     
/*  569 */     this.bitPointer = 0;
/*  570 */     this.bytePointer = 0;
/*  571 */     this.prevChangingElems = new int[w];
/*  572 */     this.currChangingElems = new int[w];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decode1D(byte[] buffer, byte[] compData, int startX, int height) {
/*  579 */     this.data = compData;
/*      */     
/*  581 */     int lineOffset = 0;
/*  582 */     int scanlineStride = (this.w + 7) / 8;
/*      */     
/*  584 */     this.bitPointer = 0;
/*  585 */     this.bytePointer = 0;
/*      */     
/*  587 */     for (int i = 0; i < height; i++) {
/*  588 */       decodeNextScanline(buffer, lineOffset, startX);
/*  589 */       lineOffset += scanlineStride;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void decodeNextScanline(byte[] buffer, int lineOffset, int bitOffset) {
/*  595 */     int bits = 0;
/*  596 */     int code = 0;
/*  597 */     int isT = 0;
/*      */ 
/*      */ 
/*      */     
/*  601 */     boolean isWhite = true;
/*      */ 
/*      */     
/*  604 */     this.changingElemSize = 0;
/*      */ 
/*      */     
/*  607 */     while (bitOffset < this.w) {
/*  608 */       while (isWhite) {
/*      */         
/*  610 */         int current = nextNBits(10);
/*  611 */         int entry = white[current];
/*      */ 
/*      */         
/*  614 */         isT = entry & 0x1;
/*  615 */         bits = entry >>> 1 & 0xF;
/*      */         
/*  617 */         if (bits == 12) {
/*      */           
/*  619 */           int twoBits = nextLesserThan8Bits(2);
/*      */           
/*  621 */           current = current << 2 & 0xC | twoBits;
/*  622 */           entry = additionalMakeup[current];
/*  623 */           bits = entry >>> 1 & 0x7;
/*  624 */           code = entry >>> 4 & 0xFFF;
/*  625 */           bitOffset += code;
/*      */           
/*  627 */           updatePointer(4 - bits); continue;
/*  628 */         }  if (bits == 0)
/*  629 */           throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder0")); 
/*  630 */         if (bits == 15) {
/*  631 */           throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder1"));
/*      */         }
/*      */         
/*  634 */         code = entry >>> 5 & 0x7FF;
/*  635 */         bitOffset += code;
/*      */         
/*  637 */         updatePointer(10 - bits);
/*  638 */         if (isT == 0) {
/*  639 */           isWhite = false;
/*  640 */           this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  647 */       if (bitOffset == this.w) {
/*  648 */         if (this.compression == 2) {
/*  649 */           advancePointer();
/*      */         }
/*      */         
/*      */         break;
/*      */       } 
/*  654 */       while (!isWhite) {
/*      */         
/*  656 */         int current = nextLesserThan8Bits(4);
/*  657 */         int entry = initBlack[current];
/*      */ 
/*      */         
/*  660 */         isT = entry & 0x1;
/*  661 */         bits = entry >>> 1 & 0xF;
/*  662 */         code = entry >>> 5 & 0x7FF;
/*      */         
/*  664 */         if (code == 100) {
/*  665 */           current = nextNBits(9);
/*  666 */           entry = black[current];
/*      */ 
/*      */           
/*  669 */           isT = entry & 0x1;
/*  670 */           bits = entry >>> 1 & 0xF;
/*  671 */           code = entry >>> 5 & 0x7FF;
/*      */           
/*  673 */           if (bits == 12) {
/*      */             
/*  675 */             updatePointer(5);
/*  676 */             current = nextLesserThan8Bits(4);
/*  677 */             entry = additionalMakeup[current];
/*  678 */             bits = entry >>> 1 & 0x7;
/*  679 */             code = entry >>> 4 & 0xFFF;
/*      */             
/*  681 */             setToBlack(buffer, lineOffset, bitOffset, code);
/*  682 */             bitOffset += code;
/*      */             
/*  684 */             updatePointer(4 - bits); continue;
/*  685 */           }  if (bits == 15)
/*      */           {
/*  687 */             throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder2"));
/*      */           }
/*  689 */           setToBlack(buffer, lineOffset, bitOffset, code);
/*  690 */           bitOffset += code;
/*      */           
/*  692 */           updatePointer(9 - bits);
/*  693 */           if (isT == 0) {
/*  694 */             isWhite = true;
/*  695 */             this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */           }  continue;
/*      */         } 
/*  698 */         if (code == 200) {
/*      */           
/*  700 */           current = nextLesserThan8Bits(2);
/*  701 */           entry = twoBitBlack[current];
/*  702 */           code = entry >>> 5 & 0x7FF;
/*  703 */           bits = entry >>> 1 & 0xF;
/*      */           
/*  705 */           setToBlack(buffer, lineOffset, bitOffset, code);
/*  706 */           bitOffset += code;
/*      */           
/*  708 */           updatePointer(2 - bits);
/*  709 */           isWhite = true;
/*  710 */           this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */           continue;
/*      */         } 
/*  713 */         setToBlack(buffer, lineOffset, bitOffset, code);
/*  714 */         bitOffset += code;
/*      */         
/*  716 */         updatePointer(4 - bits);
/*  717 */         isWhite = true;
/*  718 */         this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  723 */       if (bitOffset == this.w) {
/*  724 */         if (this.compression == 2) {
/*  725 */           advancePointer();
/*      */         }
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  731 */     this.currChangingElems[this.changingElemSize++] = bitOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decode2D(byte[] buffer, byte[] compData, int startX, int height, long tiffT4Options) {
/*  741 */     this.data = compData;
/*  742 */     this.compression = 3;
/*      */     
/*  744 */     this.bitPointer = 0;
/*  745 */     this.bytePointer = 0;
/*      */     
/*  747 */     int scanlineStride = (this.w + 7) / 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  753 */     int[] b = new int[2];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  758 */     int currIndex = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  767 */     this.oneD = (int)(tiffT4Options & 0x1L);
/*      */     
/*  769 */     this.fillBits = (int)((tiffT4Options & 0x4L) >> 2L);
/*      */ 
/*      */     
/*  772 */     if (readEOL() != 1) {
/*  773 */       throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder3"));
/*      */     }
/*      */     
/*  776 */     int lineOffset = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  781 */     decodeNextScanline(buffer, lineOffset, startX);
/*  782 */     lineOffset += scanlineStride;
/*      */     
/*  784 */     for (int lines = 1; lines < height; lines++) {
/*      */ 
/*      */ 
/*      */       
/*  788 */       if (readEOL() == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  793 */         int[] temp = this.prevChangingElems;
/*  794 */         this.prevChangingElems = this.currChangingElems;
/*  795 */         this.currChangingElems = temp;
/*  796 */         currIndex = 0;
/*      */ 
/*      */         
/*  799 */         int a0 = -1;
/*  800 */         boolean isWhite = true;
/*  801 */         int bitOffset = startX;
/*      */         
/*  803 */         this.lastChangingElement = 0;
/*      */         
/*  805 */         while (bitOffset < this.w) {
/*      */           
/*  807 */           getNextChangingElement(a0, isWhite, b);
/*      */           
/*  809 */           int b1 = b[0];
/*  810 */           int b2 = b[1];
/*      */ 
/*      */           
/*  813 */           int entry = nextLesserThan8Bits(7);
/*      */ 
/*      */           
/*  816 */           entry = twoDCodes[entry] & 0xFF;
/*      */ 
/*      */           
/*  819 */           int code = (entry & 0x78) >>> 3;
/*  820 */           int bits = entry & 0x7;
/*      */           
/*  822 */           if (code == 0) {
/*  823 */             if (!isWhite) {
/*  824 */               setToBlack(buffer, lineOffset, bitOffset, b2 - bitOffset);
/*      */             }
/*      */             
/*  827 */             bitOffset = a0 = b2;
/*      */ 
/*      */             
/*  830 */             updatePointer(7 - bits); continue;
/*  831 */           }  if (code == 1) {
/*      */             
/*  833 */             updatePointer(7 - bits);
/*      */ 
/*      */ 
/*      */             
/*  837 */             if (isWhite) {
/*  838 */               int number = decodeWhiteCodeWord();
/*  839 */               bitOffset += number;
/*  840 */               this.currChangingElems[currIndex++] = bitOffset;
/*      */               
/*  842 */               number = decodeBlackCodeWord();
/*  843 */               setToBlack(buffer, lineOffset, bitOffset, number);
/*  844 */               bitOffset += number;
/*  845 */               this.currChangingElems[currIndex++] = bitOffset;
/*      */             } else {
/*  847 */               int number = decodeBlackCodeWord();
/*  848 */               setToBlack(buffer, lineOffset, bitOffset, number);
/*  849 */               bitOffset += number;
/*  850 */               this.currChangingElems[currIndex++] = bitOffset;
/*      */               
/*  852 */               number = decodeWhiteCodeWord();
/*  853 */               bitOffset += number;
/*  854 */               this.currChangingElems[currIndex++] = bitOffset;
/*      */             } 
/*      */             
/*  857 */             a0 = bitOffset; continue;
/*  858 */           }  if (code <= 8) {
/*      */             
/*  860 */             int a1 = b1 + code - 5;
/*      */             
/*  862 */             this.currChangingElems[currIndex++] = a1;
/*      */ 
/*      */ 
/*      */             
/*  866 */             if (!isWhite) {
/*  867 */               setToBlack(buffer, lineOffset, bitOffset, a1 - bitOffset);
/*      */             }
/*      */             
/*  870 */             bitOffset = a0 = a1;
/*  871 */             isWhite = !isWhite;
/*      */             
/*  873 */             updatePointer(7 - bits); continue;
/*      */           } 
/*  875 */           throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder4"));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  881 */         this.currChangingElems[currIndex++] = bitOffset;
/*  882 */         this.changingElemSize = currIndex;
/*      */       } else {
/*      */         
/*  885 */         decodeNextScanline(buffer, lineOffset, startX);
/*      */       } 
/*      */       
/*  888 */       lineOffset += scanlineStride;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void decodeT6(byte[] buffer, byte[] compData, int startX, int height, long tiffT6Options) {
/*  897 */     this.data = compData;
/*  898 */     this.compression = 4;
/*      */     
/*  900 */     this.bitPointer = 0;
/*  901 */     this.bytePointer = 0;
/*      */     
/*  903 */     int scanlineStride = (this.w + 7) / 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  917 */     int[] b = new int[2];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  925 */     int[] cce = this.currChangingElems;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  930 */     this.changingElemSize = 0;
/*  931 */     cce[this.changingElemSize++] = this.w;
/*  932 */     cce[this.changingElemSize++] = this.w;
/*      */     
/*  934 */     int lineOffset = 0;
/*      */ 
/*      */     
/*  937 */     for (int lines = 0; lines < height; lines++) {
/*      */       
/*  939 */       int a0 = -1;
/*  940 */       boolean isWhite = true;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  945 */       int[] temp = this.prevChangingElems;
/*  946 */       this.prevChangingElems = this.currChangingElems;
/*  947 */       cce = this.currChangingElems = temp;
/*  948 */       int currIndex = 0;
/*      */ 
/*      */       
/*  951 */       int bitOffset = startX;
/*      */ 
/*      */       
/*  954 */       this.lastChangingElement = 0;
/*      */ 
/*      */       
/*  957 */       while (bitOffset < this.w) {
/*      */         
/*  959 */         getNextChangingElement(a0, isWhite, b);
/*  960 */         int b1 = b[0];
/*  961 */         int b2 = b[1];
/*      */ 
/*      */         
/*  964 */         int entry = nextLesserThan8Bits(7);
/*      */         
/*  966 */         entry = twoDCodes[entry] & 0xFF;
/*      */ 
/*      */         
/*  969 */         int code = (entry & 0x78) >>> 3;
/*  970 */         int bits = entry & 0x7;
/*      */         
/*  972 */         if (code == 0) {
/*      */           
/*  974 */           if (!isWhite) {
/*  975 */             setToBlack(buffer, lineOffset, bitOffset, b2 - bitOffset);
/*      */           }
/*      */           
/*  978 */           bitOffset = a0 = b2;
/*      */ 
/*      */           
/*  981 */           updatePointer(7 - bits); continue;
/*  982 */         }  if (code == 1) {
/*      */           
/*  984 */           updatePointer(7 - bits);
/*      */ 
/*      */ 
/*      */           
/*  988 */           if (isWhite) {
/*      */             
/*  990 */             int number = decodeWhiteCodeWord();
/*  991 */             bitOffset += number;
/*  992 */             cce[currIndex++] = bitOffset;
/*      */             
/*  994 */             number = decodeBlackCodeWord();
/*  995 */             setToBlack(buffer, lineOffset, bitOffset, number);
/*  996 */             bitOffset += number;
/*  997 */             cce[currIndex++] = bitOffset;
/*      */           } else {
/*      */             
/* 1000 */             int number = decodeBlackCodeWord();
/* 1001 */             setToBlack(buffer, lineOffset, bitOffset, number);
/* 1002 */             bitOffset += number;
/* 1003 */             cce[currIndex++] = bitOffset;
/*      */             
/* 1005 */             number = decodeWhiteCodeWord();
/* 1006 */             bitOffset += number;
/* 1007 */             cce[currIndex++] = bitOffset;
/*      */           } 
/*      */           
/* 1010 */           a0 = bitOffset; continue;
/* 1011 */         }  if (code <= 8) {
/* 1012 */           int a1 = b1 + code - 5;
/* 1013 */           cce[currIndex++] = a1;
/*      */ 
/*      */ 
/*      */           
/* 1017 */           if (!isWhite) {
/* 1018 */             setToBlack(buffer, lineOffset, bitOffset, a1 - bitOffset);
/*      */           }
/*      */           
/* 1021 */           bitOffset = a0 = a1;
/* 1022 */           isWhite = !isWhite;
/*      */           
/* 1024 */           updatePointer(7 - bits); continue;
/* 1025 */         }  if (code == 11) {
/* 1026 */           if (nextLesserThan8Bits(3) != 7) {
/* 1027 */             throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder5"));
/*      */           }
/*      */           
/* 1030 */           int zeros = 0;
/* 1031 */           boolean exit = false;
/*      */           
/* 1033 */           while (!exit) {
/* 1034 */             while (nextLesserThan8Bits(1) != 1) {
/* 1035 */               zeros++;
/*      */             }
/*      */             
/* 1038 */             if (zeros > 5) {
/*      */ 
/*      */ 
/*      */               
/* 1042 */               zeros -= 6;
/*      */               
/* 1044 */               if (!isWhite && zeros > 0) {
/* 1045 */                 cce[currIndex++] = bitOffset;
/*      */               }
/*      */ 
/*      */               
/* 1049 */               bitOffset += zeros;
/* 1050 */               if (zeros > 0)
/*      */               {
/* 1052 */                 isWhite = true;
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1057 */               if (nextLesserThan8Bits(1) == 0) {
/* 1058 */                 if (!isWhite) {
/* 1059 */                   cce[currIndex++] = bitOffset;
/*      */                 }
/* 1061 */                 isWhite = true;
/*      */               } else {
/* 1063 */                 if (isWhite) {
/* 1064 */                   cce[currIndex++] = bitOffset;
/*      */                 }
/* 1066 */                 isWhite = false;
/*      */               } 
/*      */               
/* 1069 */               exit = true;
/*      */             } 
/*      */             
/* 1072 */             if (zeros == 5) {
/* 1073 */               if (!isWhite) {
/* 1074 */                 cce[currIndex++] = bitOffset;
/*      */               }
/* 1076 */               bitOffset += zeros;
/*      */ 
/*      */               
/* 1079 */               isWhite = true; continue;
/*      */             } 
/* 1081 */             bitOffset += zeros;
/*      */             
/* 1083 */             cce[currIndex++] = bitOffset;
/* 1084 */             setToBlack(buffer, lineOffset, bitOffset, 1);
/* 1085 */             bitOffset++;
/*      */ 
/*      */             
/* 1088 */             isWhite = false;
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/* 1093 */         throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder5"));
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1099 */       cce[currIndex++] = bitOffset;
/*      */ 
/*      */       
/* 1102 */       this.changingElemSize = currIndex;
/*      */       
/* 1104 */       lineOffset += scanlineStride;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setToBlack(byte[] buffer, int lineOffset, int bitOffset, int numBits) {
/* 1111 */     int bitNum = 8 * lineOffset + bitOffset;
/* 1112 */     int lastBit = bitNum + numBits;
/*      */     
/* 1114 */     int byteNum = bitNum >> 3;
/*      */ 
/*      */     
/* 1117 */     int shift = bitNum & 0x7;
/* 1118 */     if (shift > 0) {
/* 1119 */       int maskVal = 1 << 7 - shift;
/* 1120 */       byte val = buffer[byteNum];
/* 1121 */       while (maskVal > 0 && bitNum < lastBit) {
/* 1122 */         val = (byte)(val | maskVal);
/* 1123 */         maskVal >>= 1;
/* 1124 */         bitNum++;
/*      */       } 
/* 1126 */       buffer[byteNum] = val;
/*      */     } 
/*      */ 
/*      */     
/* 1130 */     byteNum = bitNum >> 3;
/* 1131 */     while (bitNum < lastBit - 7) {
/* 1132 */       buffer[byteNum++] = -1;
/* 1133 */       bitNum += 8;
/*      */     } 
/*      */ 
/*      */     
/* 1137 */     while (bitNum < lastBit) {
/* 1138 */       byteNum = bitNum >> 3;
/* 1139 */       buffer[byteNum] = (byte)(buffer[byteNum] | 1 << 7 - (bitNum & 0x7));
/* 1140 */       bitNum++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int decodeWhiteCodeWord() {
/* 1151 */     int code = -1;
/* 1152 */     int runLength = 0;
/* 1153 */     boolean isWhite = true;
/*      */     
/* 1155 */     while (isWhite) {
/* 1156 */       int current = nextNBits(10);
/* 1157 */       int entry = white[current];
/*      */ 
/*      */       
/* 1160 */       int isT = entry & 0x1;
/* 1161 */       int bits = entry >>> 1 & 0xF;
/*      */       
/* 1163 */       if (bits == 12) {
/*      */         
/* 1165 */         int twoBits = nextLesserThan8Bits(2);
/*      */         
/* 1167 */         current = current << 2 & 0xC | twoBits;
/* 1168 */         entry = additionalMakeup[current];
/* 1169 */         bits = entry >>> 1 & 0x7;
/* 1170 */         code = entry >>> 4 & 0xFFF;
/* 1171 */         runLength += code;
/* 1172 */         updatePointer(4 - bits); continue;
/* 1173 */       }  if (bits == 0)
/* 1174 */         throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder0")); 
/* 1175 */       if (bits == 15) {
/* 1176 */         throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder1"));
/*      */       }
/*      */       
/* 1179 */       code = entry >>> 5 & 0x7FF;
/* 1180 */       runLength += code;
/* 1181 */       updatePointer(10 - bits);
/* 1182 */       if (isT == 0) {
/* 1183 */         isWhite = false;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1188 */     return runLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int decodeBlackCodeWord() {
/* 1197 */     int code = -1;
/* 1198 */     int runLength = 0;
/* 1199 */     boolean isWhite = false;
/*      */     
/* 1201 */     while (!isWhite) {
/* 1202 */       int current = nextLesserThan8Bits(4);
/* 1203 */       int entry = initBlack[current];
/*      */ 
/*      */ 
/*      */       
/* 1207 */       int bits = entry >>> 1 & 0xF;
/* 1208 */       code = entry >>> 5 & 0x7FF;
/*      */       
/* 1210 */       if (code == 100) {
/* 1211 */         current = nextNBits(9);
/* 1212 */         entry = black[current];
/*      */ 
/*      */         
/* 1215 */         int isT = entry & 0x1;
/* 1216 */         bits = entry >>> 1 & 0xF;
/* 1217 */         code = entry >>> 5 & 0x7FF;
/*      */         
/* 1219 */         if (bits == 12) {
/*      */           
/* 1221 */           updatePointer(5);
/* 1222 */           current = nextLesserThan8Bits(4);
/* 1223 */           entry = additionalMakeup[current];
/* 1224 */           bits = entry >>> 1 & 0x7;
/* 1225 */           code = entry >>> 4 & 0xFFF;
/* 1226 */           runLength += code;
/*      */           
/* 1228 */           updatePointer(4 - bits); continue;
/* 1229 */         }  if (bits == 15)
/*      */         {
/* 1231 */           throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder2"));
/*      */         }
/* 1233 */         runLength += code;
/* 1234 */         updatePointer(9 - bits);
/* 1235 */         if (isT == 0)
/* 1236 */           isWhite = true; 
/*      */         continue;
/*      */       } 
/* 1239 */       if (code == 200) {
/*      */         
/* 1241 */         current = nextLesserThan8Bits(2);
/* 1242 */         entry = twoBitBlack[current];
/* 1243 */         code = entry >>> 5 & 0x7FF;
/* 1244 */         runLength += code;
/* 1245 */         bits = entry >>> 1 & 0xF;
/* 1246 */         updatePointer(2 - bits);
/* 1247 */         isWhite = true;
/*      */         continue;
/*      */       } 
/* 1250 */       runLength += code;
/* 1251 */       updatePointer(4 - bits);
/* 1252 */       isWhite = true;
/*      */     } 
/*      */ 
/*      */     
/* 1256 */     return runLength;
/*      */   }
/*      */   
/*      */   private int readEOL() {
/* 1260 */     if (this.fillBits == 0) {
/* 1261 */       if (nextNBits(12) != 1) {
/* 1262 */         throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder6"));
/*      */       }
/* 1264 */     } else if (this.fillBits == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1270 */       int bitsLeft = 8 - this.bitPointer;
/*      */       
/* 1272 */       if (nextNBits(bitsLeft) != 0) {
/* 1273 */         throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder8"));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1280 */       if (bitsLeft < 4 && 
/* 1281 */         nextNBits(8) != 0) {
/* 1282 */         throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder8"));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       int n;
/*      */ 
/*      */       
/* 1290 */       while ((n = nextNBits(8)) != 1) {
/*      */ 
/*      */         
/* 1293 */         if (n != 0) {
/* 1294 */           throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder8"));
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1300 */     if (this.oneD == 0) {
/* 1301 */       return 1;
/*      */     }
/*      */ 
/*      */     
/* 1305 */     return nextLesserThan8Bits(1);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void getNextChangingElement(int a0, boolean isWhite, int[] ret) {
/* 1311 */     int[] pce = this.prevChangingElems;
/* 1312 */     int ces = this.changingElemSize;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1317 */     int start = (this.lastChangingElement > 0) ? (this.lastChangingElement - 1) : 0;
/* 1318 */     if (isWhite) {
/* 1319 */       start &= 0xFFFFFFFE;
/*      */     } else {
/* 1321 */       start |= 0x1;
/*      */     } 
/*      */     
/* 1324 */     int i = start;
/* 1325 */     for (; i < ces; i += 2) {
/* 1326 */       int temp = pce[i];
/* 1327 */       if (temp > a0) {
/* 1328 */         this.lastChangingElement = i;
/* 1329 */         ret[0] = temp;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1334 */     if (i + 1 < ces) {
/* 1335 */       ret[1] = pce[i + 1];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int nextNBits(int bitsToGet) {
/*      */     byte b, next, next2next;
/* 1343 */     int l = this.data.length - 1;
/* 1344 */     int bp = this.bytePointer;
/*      */     
/* 1346 */     if (this.fillOrder == 1) {
/* 1347 */       b = this.data[bp];
/*      */       
/* 1349 */       if (bp == l) {
/* 1350 */         next = 0;
/* 1351 */         next2next = 0;
/* 1352 */       } else if (bp + 1 == l) {
/* 1353 */         next = this.data[bp + 1];
/* 1354 */         next2next = 0;
/*      */       } else {
/* 1356 */         next = this.data[bp + 1];
/* 1357 */         next2next = this.data[bp + 2];
/*      */       } 
/* 1359 */     } else if (this.fillOrder == 2) {
/* 1360 */       b = flipTable[this.data[bp] & 0xFF];
/*      */       
/* 1362 */       if (bp == l) {
/* 1363 */         next = 0;
/* 1364 */         next2next = 0;
/* 1365 */       } else if (bp + 1 == l) {
/* 1366 */         next = flipTable[this.data[bp + 1] & 0xFF];
/* 1367 */         next2next = 0;
/*      */       } else {
/* 1369 */         next = flipTable[this.data[bp + 1] & 0xFF];
/* 1370 */         next2next = flipTable[this.data[bp + 2] & 0xFF];
/*      */       } 
/*      */     } else {
/* 1373 */       throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder7"));
/*      */     } 
/*      */     
/* 1376 */     int bitsLeft = 8 - this.bitPointer;
/* 1377 */     int bitsFromNextByte = bitsToGet - bitsLeft;
/* 1378 */     int bitsFromNext2NextByte = 0;
/* 1379 */     if (bitsFromNextByte > 8) {
/* 1380 */       bitsFromNext2NextByte = bitsFromNextByte - 8;
/* 1381 */       bitsFromNextByte = 8;
/*      */     } 
/*      */     
/* 1384 */     this.bytePointer++;
/*      */     
/* 1386 */     int i1 = (b & table1[bitsLeft]) << bitsToGet - bitsLeft;
/* 1387 */     int i2 = (next & table2[bitsFromNextByte]) >>> 8 - bitsFromNextByte;
/*      */     
/* 1389 */     int i3 = 0;
/* 1390 */     if (bitsFromNext2NextByte != 0) {
/* 1391 */       i2 <<= bitsFromNext2NextByte;
/* 1392 */       i3 = (next2next & table2[bitsFromNext2NextByte]) >>> 8 - bitsFromNext2NextByte;
/*      */       
/* 1394 */       i2 |= i3;
/* 1395 */       this.bytePointer++;
/* 1396 */       this.bitPointer = bitsFromNext2NextByte;
/*      */     }
/* 1398 */     else if (bitsFromNextByte == 8) {
/* 1399 */       this.bitPointer = 0;
/* 1400 */       this.bytePointer++;
/*      */     } else {
/* 1402 */       this.bitPointer = bitsFromNextByte;
/*      */     } 
/*      */ 
/*      */     
/* 1406 */     int i = i1 | i2;
/* 1407 */     return i;
/*      */   }
/*      */ 
/*      */   
/*      */   private int nextLesserThan8Bits(int bitsToGet) {
/*      */     byte b, next;
/* 1413 */     int i1, l = this.data.length - 1;
/* 1414 */     int bp = this.bytePointer;
/*      */     
/* 1416 */     if (this.fillOrder == 1) {
/* 1417 */       b = this.data[bp];
/* 1418 */       if (bp == l) {
/* 1419 */         next = 0;
/*      */       } else {
/* 1421 */         next = this.data[bp + 1];
/*      */       } 
/* 1423 */     } else if (this.fillOrder == 2) {
/* 1424 */       b = flipTable[this.data[bp] & 0xFF];
/* 1425 */       if (bp == l) {
/* 1426 */         next = 0;
/*      */       } else {
/* 1428 */         next = flipTable[this.data[bp + 1] & 0xFF];
/*      */       } 
/*      */     } else {
/* 1431 */       throw new RuntimeException(PropertyUtil.getString("TIFFFaxDecoder7"));
/*      */     } 
/*      */     
/* 1434 */     int bitsLeft = 8 - this.bitPointer;
/* 1435 */     int bitsFromNextByte = bitsToGet - bitsLeft;
/*      */     
/* 1437 */     int shift = bitsLeft - bitsToGet;
/*      */ 
/*      */     
/* 1440 */     if (shift >= 0) {
/* 1441 */       i1 = (b & table1[bitsLeft]) >>> shift;
/* 1442 */       this.bitPointer += bitsToGet;
/* 1443 */       if (this.bitPointer == 8) {
/* 1444 */         this.bitPointer = 0;
/* 1445 */         this.bytePointer++;
/*      */       } 
/*      */     } else {
/* 1448 */       i1 = (b & table1[bitsLeft]) << -shift;
/* 1449 */       int i2 = (next & table2[bitsFromNextByte]) >>> 8 - bitsFromNextByte;
/*      */       
/* 1451 */       i1 |= i2;
/* 1452 */       this.bytePointer++;
/* 1453 */       this.bitPointer = bitsFromNextByte;
/*      */     } 
/*      */     
/* 1456 */     return i1;
/*      */   }
/*      */ 
/*      */   
/*      */   private void updatePointer(int bitsToMoveBack) {
/* 1461 */     int i = this.bitPointer - bitsToMoveBack;
/*      */     
/* 1463 */     if (i < 0) {
/* 1464 */       this.bytePointer--;
/* 1465 */       this.bitPointer = 8 + i;
/*      */     } else {
/* 1467 */       this.bitPointer = i;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean advancePointer() {
/* 1473 */     if (this.bitPointer != 0) {
/* 1474 */       this.bytePointer++;
/* 1475 */       this.bitPointer = 0;
/*      */     } 
/*      */     
/* 1478 */     return true;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/TIFFFaxDecoder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */