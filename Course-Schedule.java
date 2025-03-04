# Problem 2
Course Schedule (https://leetcode.com/problems/course-schedule/)
// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// As it was given that there is dependency then we can think off the Graph. 
// I plotted the graph which was directed. In the graph the vertices represents courses and edges with direction represents the dependency
// I iterated through an matrix given and found out that each course is dependent oin how many other courses and stored it in an array
// then I found out which one has 0 dependenxy and added it in a queue and then I popped and saw it was dependency for two other courses so instead of 
// iterating and finding out the dependency I just created a hashmap of key as a Integer and value as a list so I just
// got the dependent courses and I just subtracted those from the indegree array as our  prereq is done.
// Also when at the end indegree has all 0 then return true else return false. So instead of linearly iterating I am maintaining 
// a count that will be incremented while pushing into a queue.

//Time complexity is O(V+E) because all the loops will run for O(V) and the list in map is O(E) as it is storing edges i.e dependency
//Space Complexity : O(V+E)


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //Base Condition
        if(numCourses==0 || prerequisites.length==0){
            return true;
        }
        //Declaration and Initialization
        int[] indegree = new int[numCourses];
        HashMap<Integer,List<Integer>> map=new HashMap<>();
        Queue<Integer> q=new LinkedList<>();
        int count=0;
        // loop to iterate over the given 2-D array
        for(int[] prereq : prerequisites){
            int course =prereq[0];
            int pre =prereq[1];
            indegree[course]++;
            //checking is the key exist in a map or not
            if(!map.containsKey(pre)){
                map.put(pre, new ArrayList<>());
            }
            map.get(pre).add(course);
        }
        // checking which course has no prerequisite and adding in a queue
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                count++;    
                q.add(i);
            }
        }
        // Performing a BFS traversal
        while(!q.isEmpty()){
            int curr=q.poll();
            //There can be a situation that map doesnot have that key so we need to handle that
            if(!map.containsKey(curr)){
                continue;
            }
            List<Integer> list=map.get(curr);
            for(int a : list){
                indegree[a]--;
                if(indegree[a]==0){
                    count++;
                    q.add(a);
                }
            }
        }
        //At the end checking if all are 0 or not
        if(count==numCourses){
            return true;
        }
        return false;
    }
}