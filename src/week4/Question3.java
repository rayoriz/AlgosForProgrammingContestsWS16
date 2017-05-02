package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Question3 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);
		int cases = Integer.parseInt(br.readLine());

		for (int i = 1; i <= cases; i++) {

			String line = br.readLine();
			String[] input1 = line.split(" ");

			int arenaSize = Integer.parseInt(input1[0]);
			int totalSnek = Integer.parseInt(input1[1]);
			int totalLadder = Integer.parseInt(input1[2]);

			int[] snake = new int[arenaSize + 1];
			int[] ladder = new int[arenaSize + 1];

			for (int index1 = 0; index1 < totalSnek; index1++) {
				input1 = br.readLine().split(" ");

				snake[Integer.parseInt(input1[0])] = Integer.parseInt(input1[1]);
			}

			for (int index2 = 0; index2 < totalLadder; index2++) {
				line = br.readLine();
				input1 = line.split(" ");

				ladder[Integer.parseInt(input1[0])] = Integer.parseInt(input1[1]);
			}

			int minValue = -1;
			int nextValue = -1;
			boolean[] result = null;

			for (int diceRoll = 1; diceRoll <= 6; diceRoll++) {
				nextValue = nextMove(diceRoll, arenaSize, snake, ladder);

				if (nextValue == -1)
					continue;
				if (minValue == -1 || nextValue < minValue) {
					minValue = nextValue;
					result = new boolean[7];
					result[diceRoll] = true;
				} else if (nextValue == minValue)
					result[diceRoll] = true;
			}
			if (result != null) {
				String ans = "";
				for (int index = 1; index <= 6; index++){
					if (result[index]){
						ans += index+ " ";
					}
				}
				System.out.println("Case #"+i+": "+ans);			

				
			} else
				System.out.println("Case #"+i+": impossible ");					

			br.readLine();
		}

	}

	public static int nextMove(int step, int n, int[] snake, int[] ladder) {
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		dq.add(1);
		int[] cost = new int[n + 7];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[1] = 0;

		int node = 0;
		int value = Integer.MAX_VALUE;

		while (!dq.isEmpty()) {
			node = dq.remove();
			if (node >= n) {
				if (value > cost[node])
					value = cost[node];
				continue;
			}

			if (snake[node] > 0) {
				if (cost[snake[node]] > cost[node]) {
					dq.addFirst(snake[node]);
					cost[snake[node]] = cost[node];
				}
				continue;
				
			} else if (ladder[node] > 0 && cost[ladder[node]] > cost[node]) {
				dq.addFirst(ladder[node]);
				cost[ladder[node]] = cost[node];
			}

			if (cost[node + step] > cost[node] + 1) {
				cost[node + step] = cost[node] + 1;
				dq.add(node + step);
			}
		}

		return value == Integer.MAX_VALUE ? -1 : value;
	}
}
