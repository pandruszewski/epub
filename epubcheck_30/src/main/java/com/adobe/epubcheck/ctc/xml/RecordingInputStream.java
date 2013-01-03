package com.adobe.epubcheck.ctc.xml;

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;
 
/**
 * 
 * @author Unknown
 *
 */
 
class RecordingInputStream  extends  FilterInputStream { 
	protected ByteArrayOutputStream sink; 
    
    RecordingInputStream(InputStream in) { 
        this(in, new ByteArrayOutputStream()); 
    } 
     
    RecordingInputStream(InputStream in, ByteArrayOutputStream sink) { 
        super(in); 
        this.sink = sink; 
    } 
     
    public synchronized int read() throws IOException { 
        int i = in.read(); 
        sink.write(i); 
        return i; 
    } 
     
    public synchronized int read(byte[] buf, int off, int len) throws IOException { 
        int l = in.read(buf, off, len); 
        String s = new String(buf);
        sink.write(buf, off, l); 
        return l; 
    } 
     
    public synchronized int read(byte[] buf) throws IOException { 
        return read(buf, 0, buf.length); 
    } 
     
    public synchronized long skip(long len) throws IOException { 
        long l = 0; 
        int i = 0; 
        byte[] buf = new byte[1024]; 
        while (l < len) { 
            i = read(buf, 0, (int)Math.min((long)buf.length, len - l)); 
            if (i == -1) break; 
            l += i; 
        } 
        return l; 
    } 
     
    byte[] getBytes() { 
        return sink.toByteArray(); 
    } 
     
    void resetSink() { 
        sink.reset(); 
    } 
}

