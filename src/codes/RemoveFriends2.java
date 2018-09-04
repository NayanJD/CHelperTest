package codes;

import nj.io.InputReader;
import nj.io.OutputWriter;
import nj.util.LinkedList;

import java.io.PrintWriter;

public class RemoveFriends2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
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

    public static void solver(int[] arr, int N, int K, OutputWriter out){

        LinkedList list=new LinkedList();

        for(int i=0;i<N;i++){
            list.add(arr[i]);
            //out.write(arr[i]+" ");
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
            out.write(first.value+" ");
            first=first.next;
        }
        out.write("\n");
        //out.flush();






    }
}

