importjava.util.PriorityQueue;
importjava.util.HashSet;
importjava.util.Set;
importjava.util.List;
importjava.util.Comparator;
importjava.util.ArrayList;
importjava.util.Collections;
publicclassAstarSearchAlgo{

        //h scores is the stright-line distance from the current city to
Bucharest
        publicstaticvoidmain(String[] args){
                //initialize the graph base on the Romania map
                Noden1 = newNode(&quot;Arad&quot;,366);
                Noden2 = newNode(&quot;Zerind&quot;,374);
                Noden3 = newNode(&quot;Oradea&quot;,380);
                Noden4 = newNode(&quot;Sibiu&quot;,253);
                Noden5 = newNode(&quot;Fagaras&quot;,178);
                Noden6 = newNode(&quot;Rimnicu Vilcea&quot;,193);
                Noden7 = newNode(&quot;Pitesti&quot;,98);
                Noden8 = newNode(&quot;Timisoara&quot;,329);
                Noden9 = newNode(&quot;Lugoj&quot;,244);
                Noden10 = newNode(&quot;Mehadia&quot;,241);
                Noden11 = newNode(&quot;Drobeta&quot;,242);
                Noden12 = newNode(&quot;Craiova&quot;,160);
                Noden13 = newNode(&quot;Bucharest&quot;,0);
                        Noden14 = newNode(&quot;Giurgiu&quot;,77);
 
                //initialize the edges
                //Arad
                n1.adjacencies = newEdge[]{
                        newEdge(n2,75),
                        newEdge(n4,140),
                        newEdge(n8,118)
                };
                 
                 //Zerind
                n2.adjacencies = newEdge[]{
                        newEdge(n1,75),
                        newEdge(n3,71)
                };

                 
                 //Oradea
                n3.adjacencies = newEdge[]{
                        newEdge(n2,71),
                        newEdge(n4,151)
                };
                 
                 //Sibiu
                n4.adjacencies = newEdge[]{
                        newEdge(n1,140),
                        newEdge(n5,99),
                        newEdge(n3,151),
                        newEdge(n6,80),
                };
                 
                 //Fagaras
                n5.adjacencies = newEdge[]{
                        newEdge(n4,99),
                        //178
                        newEdge(n13,211)
                };
                 
                 //RimnicuVilcea
                n6.adjacencies = newEdge[]{
                        newEdge(n4,80),
                        newEdge(n7,97),
                        newEdge(n12,146)
                };
                 
                 //Pitesti
                n7.adjacencies = newEdge[]{
                        newEdge(n6,97),
                        newEdge(n13,101),
                        newEdge(n12,138)
                };
                 
                 //Timisoara
                n8.adjacencies = newEdge[]{
                        newEdge(n1,118),
                        newEdge(n9,111)
                };
                 

                 //Lugoj
                n9.adjacencies = newEdge[]{
                        newEdge(n8,111),
                        newEdge(n10,70)
                };
                 //Mehadia
                n10.adjacencies = newEdge[]{
                        newEdge(n9,70),
                        newEdge(n11,75)
                };
                 
                 //Drobeta
                n11.adjacencies = newEdge[]{
                        newEdge(n10,75),
                        newEdge(n12,120)
                };
                 //Craiova
                n12.adjacencies = newEdge[]{
                        newEdge(n11,120),
                        newEdge(n6,146),
                        newEdge(n7,138)
                };
                //Bucharest
                n13.adjacencies = newEdge[]{
                        newEdge(n7,101),
                        newEdge(n14,90),
                        newEdge(n5,211)
                };
                 
                 //Giurgiu
                n14.adjacencies = newEdge[]{
                        newEdge(n13,90)
                };
                AstarSearch(n1,n13);
                List&lt;Node&gt;path = printPath(n13);
                        System.out.println(&quot;Path: &quot; + path);

        }

        publicstaticList&lt;Node&gt;printPath(Nodetarget){
                List&lt;Node&gt;path = newArrayList&lt;Node&gt;();
       
        for(Nodenode = target; node!=null; node = node.parent){
            path.add(node);
        }
        Collections.reverse(path);
        return path;
        }
        publicstaticvoidAstarSearch(Nodesource, Nodegoal){
                Set&lt;Node&gt;explored = newHashSet&lt;Node&gt;();
                PriorityQueue&lt;Node&gt;queue = newPriorityQueue&lt;Node&gt;(20,
                        newComparator&lt;Node&gt;(){
                                 //override compare method
                 publicintcompare(Nodei, Nodej){
                    if(i.f_scores&gt;j.f_scores){
                        return1;
                    }
                    elseif (i.f_scores&lt;j.f_scores){
                        return -1;
                    }
                    else{
                        return0;
                    }
                 }
                        }
                        );
                //cost from start
                source.g_scores = 0;
                queue.add(source);
                booleanfound = false;
                while((!queue.isEmpty())&amp;&amp;(!found)){

                        //the node in having the lowest f_score value
                        Nodecurrent = queue.poll();
                        explored.add(current);
                        //goal found
                        if(current.value.equals(goal.value)){
                                found = true;
                        }
                        //check every child of current node
                        for(Edgee:current.adjacencies){
                                Nodechild = e.target;
                                doublecost = e.cost;
                                doubletemp_g_scores = current.g_scores + cost;
                                doubletemp_f_scores = temp_g_scores +
child.h_scores;

                                /*if child node has been evaluated and
                                the newer f_score is higher, skip*/
                               
                                if((explored.contains(child)) &amp;&amp;
                                        (temp_f_scores&gt;= child.f_scores)){
                                        continue;
                                }
                                /*else if child node is not in queue or
                                newer f_score is lower*/
                               
                                elseif((!queue.contains(child)) ||
                                        (temp_f_scores&lt;child.f_scores)){
                                        child.parent = current;
                                        child.g_scores = temp_g_scores;
                                        child.f_scores = temp_f_scores;
                                        if(queue.contains(child)){
                                                queue.remove(child);
                                        }
                                        queue.add(child);
                                }

                        }
                }
        }
       
}
classNode{
        publicfinalStringvalue;
        publicdoubleg_scores;
        publicfinaldoubleh_scores;
        publicdoublef_scores = 0;
        publicEdge[] adjacencies;
        publicNodeparent;
        publicNode(Stringval, doublehVal){
                value = val;
                h_scores = hVal;
        }
        publicStringtoString(){
                return value;
        }
}
classEdge{
        publicfinaldoublecost;
        publicfinalNodetarget;
        publicEdge(NodetargetNode, doublecostVal){
                target = targetNode;
                cost = costVal;
        }
}
