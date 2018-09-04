package nj.io;

import java.io.*;

public class OutputWriter{
    private OutputStream w;
    private Writer writer;
    final private static int SIZE = 1 << 16;
    final private byte[] buff=new byte[SIZE];
    final private char[] cbuff=new char[SIZE];
    private int curr=0;
    private boolean iswriter;

    public OutputWriter(OutputStream out){
        this.w=out;
        iswriter=false;
    }

    public OutputWriter(Writer out){
        this.writer=out;
        iswriter=true;
    }
//    public Writer(File file) throws Exception{
//        this.w=new PrintStream(file);
//    }

    public void write(String str){
        byte[] strbytes=str.getBytes();
        char[] chars=str.toCharArray();

        try {
            for (int i = 0; i < strbytes.length; i++) {
                if (curr == SIZE) {
                    if(!iswriter)
                        w.write(buff, 0, SIZE);
                    else
                        writer.write(cbuff,0,SIZE);
                    curr = 0;
                }

                if(!iswriter)
                    buff[curr++] = strbytes[i];
                else
                    cbuff[curr++]= chars[i];
            }
        }catch(Exception e){
            //Do nothing
        }

    }

    public void flush(){
        try {
            if(!iswriter)
                w.write(buff, 0, curr);
            else
                writer.write(cbuff,0,curr);
        }catch(Exception e){

        }
    }

    public void close(){
        //System.out.println("closed");
        try{
            flush();
            //writer.close();
        }catch(Exception e){

        }
    }
}