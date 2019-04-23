//Distance-Vector Algorithm (Bellman Ford)

/*
	@Tanishq Chaudhary, 2015105
*/

package a3_2015105;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;

public class BF_2015105 {
	static HashMap<Pair<String, String>, Integer> edges = new HashMap<>();
	private static final Integer INT_MAX = 100000;
	static List<String> nodes = new ArrayList<>();
	private static final Integer INT_MAX1 = 10000;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int i0 = 0;
		String line = new String();
		if (scanner.hasNextLine()) {
			line = scanner.nextLine();
		}
		int i1 = 0;
		String[] vals = line.split(";");
		for (int i = 0; i < vals.length; i++) {
        int val;
        String[] Cost = vals[i].split(", ");
		String[] Node2 = Cost[0].split(":");
		String[] Node1 = Node2[0].split(" ");
			if (nodes.contains(Node1[1]) != true) {
				nodes.add(Node1[1]);
			}
			if (nodes.contains(Node2[1]) != true) {
				nodes.add(Node2[1]);
			}
			val = Integer.parseInt(Cost[1]);
			edges.put(new Pair<String, String>(Node1[1], Node2[1]), val);
			edges.put(new Pair<String, String>(Node2[1], Node1[1]), val);
		}

		System.out.println("Nodes");
		while (i0 < nodes.size()) {
			System.out.print(nodes.get(i0) + " ");
			i0++;
		}
		System.out.println("\n");

		
		while( i1 < nodes.size()) {
			bellmanFord(nodes.get(i1));
			i1++;
		}

		while (true) {
            String sentence = "";
			System.out.println("\n\nEnter change in edges");
			if (scanner.hasNext()) {
				sentence = scanner.nextLine();
			}
            int flag1 = 0;
			String[] new_vals = sentence.split(";");
            int new_valsCount = 0;
			for (int i = 0; i < new_vals.length; i++) {
				int val;
				String[] new_Cost = new_vals[i].split(", ");
				String[] new_Node2 = new_Cost[0].split(":");
				String[] new_Node1 = new_Node2[0].split(" ");
				if (nodes.contains(new_Node1[1]) != true) {
				nodes.add(new_Node1[1]);
				}
				if (nodes.contains(new_Node2[1]) != true) {
					nodes.add(new_Node2[1]);
				}
				val = Integer.parseInt(new_Cost[1]);
				if(val==-1) {
					val = INT_MAX;
					if (edges.containsKey(new Pair<String, String>(new_Node1[1], new_Node2[1])) == true) {
					
						edges.remove(new Pair<String, String>(new_Node1[1], new_Node2[1]));
						edges.remove(new Pair<String, String>(new_Node2[1], new_Node1[1]));
						//nodes.remove(new_Node1[1]);
						//nodes.remove(new_Node2[1]);
						
					}
					}
					else {
				boolean flag = false;
				if (edges.containsKey(new Pair<String, String>(new_Node1[1], new_Node2[1])) != true) {
					flag = true;
					edges.put(new Pair<String, String>(new_Node1[1], new_Node2[1]), val);
					edges.put(new Pair<String, String>(new_Node2[1], new_Node1[1]), val);
				} else {
					edges.replace(new Pair<String, String>(new_Node1[1], new_Node2[1]), val);
					edges.replace(new Pair<String, String>(new_Node2[1], new_Node1[1]), val);
				}
					}
			
			int i2 = 0;
			while (i2 < nodes.size()) {
				bellmanFord(nodes.get(i2));
				i2++;
			}
		}
			}
	}
	public static void bellmanFord(String t) {
		System.out.println("*****TABLE FOR NODE: " + t + " ******");
		System.out.println();
		HashMap<String, Integer> d = new HashMap<>(); 
		int flag1 = 0;
		HashMap<String, String> successor = new HashMap<>();
		int successorCount = 0;
		for (int i = 0; i < nodes.size(); i++) {
			successorCount++;
			if (nodes.get(i).equals(t) != true) {
				if (d.containsKey(nodes.get(i)) != true) {
					d.put(nodes.get(i), INT_MAX);
				} else {
					d.replace(nodes.get(i), INT_MAX);
				}
			}
		}
		//System.out.println(successorCount);
		int flag2 = 0;
		if (d.containsKey(t) != true) {
			d.put(t, 0);
		} else {
			flag2++;
			d.replace(t, 0);
		}
		System.out.println("d[v]");
		int itCount = 0;
		Iterator it = d.entrySet().iterator();
		while (it.hasNext() != false) {
			itCount++;
			Map.Entry pair = (Map.Entry) it.next();
			if(itCount<INT_MAX){
				//System.out.println("Size Exceeded");
			}
			System.out.println(pair.getKey() + ": " + pair.getValue());
			flag2 = -1;
		}
		System.out.println("\n");
		int i3 = 0;
		while (i3< nodes.size()) {
			successor.put(nodes.get(i3), "");
			i3++;
		}
		int j1 = 0;
        int k,k1,distChanged = 0;
		while (j1< nodes.size() - 1) {
            Integer d_uv, d_u, d_v;
            k=0;
            k1=0;
            String u, v;
            int distDifference;
			Iterator j = edges.entrySet().iterator();
            distDifference = -1;
			
			while (j.hasNext() != false) {
				Map.Entry pair = (Map.Entry) j.next();
                distDifference= 0;
				u = ((Pair<String, String>) pair.getKey()).getLeft();
                if(u==""){
                    k=1;
                }
				v = ((Pair<String, String>) pair.getKey()).getRight();
                if(v==""){
                    k1=1;
                }
				d_uv = (Integer) pair.getValue();
                distDifference = d_uv;
				d_u = d.get(u);
                distDifference = distDifference + d_u;
                //System.out.println(distDifference);
				d_v = d.get(v);
                distDifference =distDifference -  d_v;				
				if (d_uv + d_u  < d_v) {
					d_v = d_u + d_uv;
                    distChanged = 1;
					d.replace(v, d_v);
					successor.replace(v, u);
				}}

			System.out.println("Round: " + (j1 + 1) + "/" + (nodes.size() - 1));
			System.out.println("d(v), successor");
			it = d.entrySet().iterator();
            int flag4 = 0;
            int count =0;
			while (it.hasNext() != false) {
				flag4=0;
				Map.Entry pair = (Map.Entry) it.next();
                count++;
				System.out.println(pair.getKey() + ": " + pair.getValue() + ", " + successor.get(pair.getKey()));
                if(flag4>INT_MAX){
                    //System.out.println("Flag reached limit!");
                }
            }
			System.out.println("\n");
            j1++;
		}

		int flag3 = 0;
		System.out.println("Forwarding table : " + t);
        int nodeCount = 0;
        int i4 = 0;
		System.out.println("Destination: Next Hop");
		for (int i = 0; i < nodes.size(); i++) {
            nodeCount++;
			String v = nodes.get(i);
			if (v.equals(t) != false) {
				continue;
			}
			String pr = successor.get(v);
			//System.out.println("PR VALUE: " + pr);
			if(pr!= "") {
			while (pr.equals(t) != true) {
				v = pr;
				flag3++;
				pr = successor.get(v);
			}
			System.out.println();

			System.out.println(nodes.get(i) + ": [" + t + ", " + v + "]"); 
			}
			else {
				System.out.println();
				//System.out.println("Node: "+ v + " is unreachable!");
				

				System.out.println(nodes.get(i) + ": [" + t + ", -]"); 
			}
			//System.out.println(flag3);
			
        }
		if(flag3>1){
            //System.out.println("Flag3 Raised to "+ flag3);
        }
		System.out.println("\n***************************************************");

	}
}


class Pair<L, R> {
	private final L left;
	private final R right;
	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}
	public L getLeft() {
		return left;
	}
	public R getRight() {
		return right;
	}
	@Override
	public int hashCode() {
		return left.hashCode() ^ right.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair)) {
			return false;
		}
		Pair pair = (Pair) o;
		return this.left.equals(pair.getLeft()) && this.right.equals(pair.getRight());
	}
}

