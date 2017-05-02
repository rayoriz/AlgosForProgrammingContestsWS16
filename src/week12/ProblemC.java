package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 * @author rayo
 *
 */

class Room {

	int id;
	boolean visited;
	int counter = 0;
	int drainLevel = -1;
	HashMap<Room, Integer> adjacencyList = new HashMap<Room, Integer>();

	/**
	 * constructor
	 * 
	 * @param number
	 */
	public Room(int number) {
		this.id = number;
	}
}

public class ProblemC {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cases = Integer.parseInt(br.readLine());
		for (int i = 1; i <= cases; i++) {
			LinkedList<Room> ll = new LinkedList<Room>();
			HashMap<Room, Integer> hMap = new HashMap<Room, Integer>();
			int visitCounter = 0;

			/**
			 * The first line of the input contains an integer t. t test cases
			 * follow, each of them separated by a blank line. Each test case
			 * starts with a single line containing four space-separated
			 * integers n, m, k and l, where n is the amount of rooms in the
			 * water temple, numbered from 1 to n, and m is the amount of
			 * hallways connecting the rooms, k is the amount of rooms that are
			 * control rooms and l is the initial water level. m lines follow
			 * describing the hallways: the i-th line contains three
			 * space-separated integers ai , bi and li , describing a hallway
			 * from room ai to bi . To use this hallway, the water level must
			 * have been drained to at least water level li . You can assume
			 * that a room is drained of water as soon as there is at least one
			 * hallway to it that is usable. k lines follow describing the
			 * control rooms: the i-th line contains two space-separated
			 * integers ai , di specifying that room ai is a control room. If
			 * Lea reaches room ai and operates the machinery there, she can
			 * drain the water throughout the whole temple to any level between
			 * the current water level and di (inclusively).
			 */
			String[] input1 = br.readLine().split(" ");
			// amount of rooms
			int n = Integer.parseInt(input1[0]);
			Room[] rooms = new Room[n + 1];
			// amount of hallways
			int m = Integer.parseInt(input1[1]);
			// control rooms
			int k = Integer.parseInt(input1[2]);
			// initial water level
			int l = Integer.parseInt(input1[3]);
			
			
			for (int index1 = 1; index1 <= n; index1++){
				// initialize array
				rooms[index1] = new Room(index1);
			}
			
			for (int index2 = 0; index2 < m; index2++) {

				String[] input2 = br.readLine().split(" ");
				int ai = Integer.parseInt(input2[0]);
				int bi = Integer.parseInt(input2[1]);
				if (ai == bi){
					// case where same ai and bi - dont initialize
					continue;
				}
				
				// adding rooms to the array
				Room roomA = rooms[ai];
				Room roomB = rooms[bi];
				
				int li = Integer.parseInt(input2[2]);
				
				if (!roomA.adjacencyList
						.containsKey(roomB)  // add the rooms to the adjacency list
						|| 
						roomA
						.adjacencyList
						.get(roomB) < li) {
					roomA.adjacencyList
					.put(roomB, li);
					roomB.adjacencyList
					.put(roomA, li);
				}
			}
			
			
			for (int index3 = 0; index3 < k; index3++) {
				String[] input2 = br.readLine().split(" ");
				// check if control room
				int ci = Integer.parseInt(input2[0]);
				// drain Level
				int di = Integer.parseInt(input2[1]);
				rooms[ci].drainLevel = di;
			}

			int tempLevel = l;
			
			if (rooms[1].drainLevel != -1 
					&& 
				rooms[1].drainLevel < tempLevel){
				
				tempLevel = rooms[1].drainLevel;
			
			}
			int lastRoom = Integer.MIN_VALUE;
			label1: while (true) {
				int currentRoom = 1;

				ll.add(rooms[1]);
				visitCounter++;
				rooms[1].counter = visitCounter;
				
				int longValue = -1;
				boolean visited = false;
				for (Map.Entry<Room, Integer> val : hMap.entrySet()) {
					
					if (val.getValue() > longValue){
						longValue = val.getValue();
						 visited = true;
					}
				}
				if (longValue != -1) {
					// value is not -1
					if (longValue < tempLevel) {
						// not the last value,
						lastRoom = 0;
						// continue up
						break label1;
					}
					l = longValue;
					hMap.clear();
				}
				
				while (!ll.isEmpty()) {
					
					Room room = ll.remove(); // last entry
					
					for (Map.Entry<Room, Integer> adjacency :
						
						room.adjacencyList.entrySet()) {
						Room adjacentRoom = adjacency.getKey();
						int adjacentRoomValue = adjacency.getValue();			
						if (adjacentRoom.counter 
								< visitCounter) {
							if (adjacentRoomValue >= l) {
								
								ll.add(adjacentRoom); // update list
								
								if (adjacentRoom.drainLevel != -1 
									
										&& adjacentRoom.drainLevel < tempLevel)
									
									tempLevel = adjacentRoom.drainLevel;
								
								currentRoom++;
								adjacentRoom.counter = visitCounter;
								visited = true;
								hMap.remove(adjacentRoom); // room visited remove it
							} else {
								
								if (!hMap.containsKey(adjacentRoom) 
										|| 
										hMap.get(adjacentRoom) 
										< adjacentRoomValue) {
									visited = false;
									hMap.put(adjacentRoom, 
											adjacentRoomValue); // add to the list
								}
							}
						}
					}
				}
				if (currentRoom <= lastRoom){
					break label1;
				}
				lastRoom = currentRoom;
				if (currentRoom >= n){
					break label1;
				}
			}
/**
 * For each test case, print a line containing Case #i: r where i is its number, starting at 1, and r is the maximum water
level that can be left inside the temple so that every room is reachable from the entrance (room 1). Print Case #i:
impossible if Lea cannot lower the water level enough so that every room is reachable. Each line of the output
should end with a line break.
 */
			if (lastRoom >= n)
				System.out.println("Case #" + i + ": " + l);
			else
				System.out.println("Case #" + i + ": impossible");
			
			br.readLine();
		}
	}
}
