package codes;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

import nj.util.*;
import nj.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveFriends {
    private final static Logger LOGGER = Logger.getLogger(RemoveFriends.class.getName());
    //static PrintWriter debug=null;
    public void solve(int testNumber, Scanner in, PrintWriter out) {

        java.util.LinkedList<Integer> li=new java.util.LinkedList<>();

        for(int i=0;i<24000000;i++){
            li.add(i);
        }
        //out.println("Hi There");
        //LOGGER.log(Level.FINE,"hiiii");
        //debug.println("Hi There");
        //int T=in.nextInt();

        int N=0;
        int K=0;
        int[] arr=null;

        //for(int i=0;i<T;i++){
            N=in.nextInt();
            K=in.nextInt();

            arr=new int[N];

            for(int j=0;j<N;j++){
                arr[j]=in.nextInt();
            }

            solver(arr,N,K,out);
        //}
    }

    public static void solver(int[] arr,int N,int K,PrintWriter out){

        LinkedList list=new LinkedList();

        for(int i=0;i<N;i++){
            list.add(arr[i]);
            //out.print(arr[i]+" ");
        }

        LinkedList.Node first=list.head.next;
        LinkedList.Node second=list.head;

//        list.delete(list.head);
//        first=list.head;
//        while(first!=null){
//            out.print(first.value+" ");
//            first=first.next;
//        }
//        out.println();

        int del=0;

        while(del<K && first!=null){
            if(second.value<first.value){
                list.delete(second);
                del++;
                if(first.prev!=null)
                    second=first.prev;
                else{
                    second=first;
                    first=first.next;
                }
            }else{
                second=first;
                first=first.next;
            }
        }

        if(del<K){
            list.delete(list.tail);
        }

        first=list.head;
        while(first!=null){
            out.print(first.value+" ");
            first=first.next;
        }
        out.println();






    }
}
