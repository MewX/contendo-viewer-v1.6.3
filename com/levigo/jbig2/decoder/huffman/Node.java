package com.levigo.jbig2.decoder.huffman;

import java.io.IOException;
import javax.imageio.stream.ImageInputStream;

abstract class Node {
  protected abstract long decode(ImageInputStream paramImageInputStream) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/huffman/Node.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */