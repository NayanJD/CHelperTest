package nj.graph;

import nj.io.OutputWriter;

public class Graph {

    OutputWriter out;
    static int time=0;
    static boolean[] bedges;
    static int[] parent;
    static int[] low;
    static boolean[] visited;
    static int[] dist;

    public int V;
    public long[][] edges;
    public long[][][] graph;

    public Graph(long[][] edges,int V,OutputWriter out){
        this.edges=edges;
        this.V=V;
        this.out=out;

        graph=new long[V][][];

        int[] vcount=new int[V];
        for(int i=0;i<edges.length;i++){
            vcount[(int)edges[i][0]]++;
            vcount[(int)edges[i][1]]++;

        }

        for(int i=0;i<V;i++){
            graph[i]=new long[2][vcount[i]+1];
            graph[i][0][0]=1;
        }

        int index=0;
        for(int i=0;i<edges.length;i++){

            index=(int)graph[(int) edges[i][0]][0][0];
            graph[(int)edges[i][0]][0][index]=edges[i][1];
            graph[(int)edges[i][0]][1][index++]=i;
            graph[(int) edges[i][0]][0][0]=index;

            index=(int)graph[(int)edges[i][1]][0][0];
            graph[(int)edges[i][1]][0][index]=edges[i][0];
            graph[(int)edges[i][1]][1][index++]=i;
            graph[(int)edges[i][1]][0][0]=index;
        }

//        for(int i=0;i<this.V;i++){
//            for(int j=1;j<graph[i][0][0];j++){
//                out.write(graph[i][0][j]+" "+graph[i][1][j]+"\n");
//            }
//            out.write("\n");
//        }
//
//        out.write("---------------\n\n");
    }

    public boolean[] getBridges(){
         bedges=new boolean[this.edges.length];

         parent=new int[this.V];
         for(int i=0;i<this.V;i++){
             parent[i]=-1;
         }

         low=new int[this.V];
         for(int i=0;i<this.V;i++){
             low[i]=Integer.MAX_VALUE;
         }

         dist=new int[this.V];

         visited=new boolean[this.V];

         Graph.time=0;

         bridgedfs(0);

         return bedges;


    }

    public void bridgedfs(int vertex){

        visited[vertex]=true;

        dist[vertex]=Graph.time;
        low[vertex]=Graph.time;

        Graph.time++;

        int child=0;
        int cv=0;
        boolean pflag=false;
        for(int i=1;i<graph[vertex][0][0];i++){
            cv=(int)graph[vertex][0][i];
            child++;
            if(!visited[cv]){


                parent[cv]=vertex;

                bridgedfs(cv);

                low[vertex]=Math.min(low[vertex],low[cv]);

                if(parent[vertex]!=-1 && dist[vertex]<low[cv]){
                    bedges[(int)graph[vertex][1][i]]=true;
                }

                if(parent[vertex]==-1 && dist[vertex]<low[cv]){
                    bedges[(int)graph[vertex][1][cv]]=true;
                }

            }else if(parent[vertex]!=cv){
                low[vertex]=Math.min(low[vertex],low[cv]);
            }
        }

        if(child==1)
            bedges[(int)graph[vertex][0][1]]=true;


    }
}
