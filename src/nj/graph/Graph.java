package nj.graph;

public class Graph {
    static int time=0;

    public int V;
    public long[][] edges;
    public long[][][] graph;

    public Graph(long[][] edges,int V){
        this.edges=edges;
        this.V=V;

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

    }

    public boolean[] getBridges(){
         boolean[] bedges=new boolean[this.edges.length];

         int[] parent=new int[this.V];
         for(int i=0;i<this.V;i++){
             parent[i]=-1;
         }

         int[] low=new int[this.V];
         for(int i=0;i<this.V;i++){
             low[i]=Integer.MAX_VALUE;
         }

         int[] dist=new int[this.V];

         boolean[] visited=new boolean[this.V];

         time=0;

         bridgedfs(0,bedges,parent,low,dist,visited);

         return bedges;


    }

    public void bridgedfs(int vertex,boolean[] bedges,int[] parent,int[] low,int[] dist,boolean[] visited){

        visited[vertex]=true;

        dist[vertex]=time+1;
        low[vertex]=time+1;

        int child=0;
        int cv=0;
        boolean pflag=false;
        for(int i=1;i<graph[vertex][0][0];i++){
            cv=(int)graph[vertex][0][i];
            if(!visited[cv]){
                child++;

                parent[cv]=vertex;

                bridgedfs(cv,bedges,parent,low,dist,visited);

                low[vertex]=Math.min(low[vertex],low[cv]);

                if(parent[vertex]!=-1 && low[vertex]>=low[cv]){
                    bedges[(int)graph[vertex][1][i]]=true;
                }

                if(parent[vertex]==-1){
                    pflag=false;
                    for(int j=i;j<graph[vertex][0][0];j++){
                        if(j!=i && visited[(int)graph[vertex][0][j]]){
                            pflag=true;
                            break;
                        }

                    }

                    if(!pflag)
                        bedges[(int)graph[vertex][1][i]]=true;

                }

            }
        }
    }
}
