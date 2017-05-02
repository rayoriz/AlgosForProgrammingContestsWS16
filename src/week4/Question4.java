package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// code inspired from multiple sources
public class Question4 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());

		for (int i = 1; i <= cases; i++) {

			String[] input1 = br.readLine().split(" ");

			int w = Integer.parseInt(input1[0]);
			int h = Integer.parseInt(input1[1]);

			boolean[][] mazeMatrix = new boolean[h][w];
			// hack to add a 3rd dimension to speed up processing
			int[][][] distanceMatrix = new int[h][w][4];

			PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> Integer.compare(a.cost, b.cost));
			Node start = null;
			Node end = null;
			for (int index1 = 0; index1 < h; index1++) {
				String line = br.readLine();

				for (int j = 0; j < w; j++) {
					if (line.charAt(j) == '*') {
						start = new Node(index1, j, 1, 0);
						mazeMatrix[index1][j] = true;
						distanceMatrix[index1][j][0] = 1;
						continue;
					}
					mazeMatrix[index1][j] = line.charAt(j) == '_';

					if ((index1 == 0 || index1 == h - 1 || j == 0 || j == w - 1) && mazeMatrix[index1][j])
						end = new Node(index1, j, Integer.MAX_VALUE, 0);
				}
			}
			// initialize the queue
			pq.add(start);

			Node node = null;
			Node newNode = null;
			int jumpCount = -1;
			int cost = 0;
			int source = 0;
			int destination = 0;
			boolean outOfMaze = false;

			while (!pq.isEmpty()) {

				node = pq.remove();

				// out of maze - end condition
				if (node.source == end.source && node.destination == end.destination) {
					outOfMaze = true;
					break;
				}

				cost = node.cost + 1;

				// move left
				{
					source = node.source;
					destination = node.destination - 1;
					if (destination >= 0 && mazeMatrix[source][destination]) {
						if ((distanceMatrix[source][destination][node.value] == 0
								|| cost < distanceMatrix[source][destination][node.value])
								&& (node.value == 0 || ((distanceMatrix[source][destination][node.value - 1] == 0
										|| cost < distanceMatrix[source][destination][node.value - 1])
										&& (node.value == 1
												|| ((distanceMatrix[source][destination][node.value - 2] == 0
														|| cost < distanceMatrix[source][destination][node.value - 2])
														&& (node.value == 2
																|| distanceMatrix[source][destination][node.value
																		- 3] == 0
																|| cost < distanceMatrix[source][destination][node.value
																		- 3])))))) {
							distanceMatrix[source][destination][node.value] = cost;

							newNode = new Node(source, destination, cost, node.value);
							newNode.jumpPos = node.jumpPos;
							pq.add(newNode);
						}

					} else if (node.value < 3) {
						jumpCount = -2;
						destination = -1;
						if (node.destination + jumpCount >= 0
								&& mazeMatrix[node.source][node.destination + jumpCount]) {
							destination = node.destination + jumpCount;
						}
						if (destination != -1
								&& (distanceMatrix[source][destination][node.value] == 0
										|| cost < distanceMatrix[source][destination][node.value])
								&& (node.value == 0 || ((distanceMatrix[source][destination][node.value - 1] == 0
										|| cost < distanceMatrix[source][destination][node.value - 1])
										&& (node.value == 1
												|| ((distanceMatrix[source][destination][node.value - 2] == 0
														|| cost < distanceMatrix[source][destination][node.value - 2])
														&& (node.value == 2
																|| distanceMatrix[source][destination][node.value
																		- 3] == 0
																|| cost < distanceMatrix[source][destination][node.value
																		- 3])))))) {
							newNode = new Node(source, destination, cost, node.value + 1);
							newNode.jumpPos = new Node(node.source, node.destination - 1, node.cost, node.value);
							newNode.jumpPos.jumpPos = node.jumpPos;
							pq.add(newNode);

							distanceMatrix[newNode.source][newNode.destination][newNode.value] = newNode.cost;
						}
					}
				}

				// move right
				{
					source = node.source;
					destination = node.destination + 1;
					if (destination < w && mazeMatrix[source][destination]) {
						if ((distanceMatrix[source][destination][node.value] == 0
								|| cost < distanceMatrix[source][destination][node.value])
								&& (node.value == 0 || ((distanceMatrix[source][destination][node.value - 1] == 0
										|| cost < distanceMatrix[source][destination][node.value - 1])
										&& (node.value == 1
												|| ((distanceMatrix[source][destination][node.value - 2] == 0
														|| cost < distanceMatrix[source][destination][node.value - 2])
														&& (node.value == 2
																|| distanceMatrix[source][destination][node.value
																		- 3] == 0
																|| cost < distanceMatrix[source][destination][node.value
																		- 3])))))) {
							distanceMatrix[source][destination][node.value] = cost;

							newNode = new Node(source, destination, cost, node.value);
							newNode.jumpPos = node.jumpPos;
							pq.add(newNode);
						}

					} else if (node.value < 3) {
						jumpCount = 2;
						destination = -1;
						if (node.destination + jumpCount < w && mazeMatrix[node.source][node.destination + jumpCount]) {
							destination = node.destination + jumpCount;
						}
						if (destination != -1
								&& (distanceMatrix[source][destination][node.value] == 0
										|| cost < distanceMatrix[source][destination][node.value])
								&& (node.value == 0 || ((distanceMatrix[source][destination][node.value - 1] == 0
										|| cost < distanceMatrix[source][destination][node.value - 1])
										&& (node.value == 1
												|| ((distanceMatrix[source][destination][node.value - 2] == 0
														|| cost < distanceMatrix[source][destination][node.value - 2])
														&& (node.value == 2
																|| distanceMatrix[source][destination][node.value
																		- 3] == 0
																|| cost < distanceMatrix[source][destination][node.value
																		- 3])))))) {
							newNode = new Node(source, destination, cost, node.value + 1);
							newNode.jumpPos = new Node(node.source, node.destination + 1, node.cost, node.value);
							newNode.jumpPos.jumpPos = node.jumpPos;
							pq.add(newNode);

							distanceMatrix[newNode.source][newNode.destination][newNode.value] = newNode.cost;
						}
					}
				}

				// move up
				{
					source = node.source - 1;
					destination = node.destination;
					if (source >= 0 && mazeMatrix[source][destination]) {
						if ((distanceMatrix[source][destination][node.value] == 0
								|| cost < distanceMatrix[source][destination][node.value])
								&& (node.value == 0 || ((distanceMatrix[source][destination][node.value - 1] == 0
										|| cost < distanceMatrix[source][destination][node.value - 1])
										&& (node.value == 1
												|| ((distanceMatrix[source][destination][node.value - 2] == 0
														|| cost < distanceMatrix[source][destination][node.value - 2])
														&& (node.value == 2
																|| distanceMatrix[source][destination][node.value
																		- 3] == 0
																|| cost < distanceMatrix[source][destination][node.value
																		- 3])))))) {
							distanceMatrix[source][destination][node.value] = cost;

							newNode = new Node(source, destination, cost, node.value);
							newNode.jumpPos = node.jumpPos;
							pq.add(newNode);
						}

					} else if (node.value < 3) {
						jumpCount = -2;
						source = -1;
						if (node.source + jumpCount >= 0 && mazeMatrix[node.source + jumpCount][node.destination]) {
							source = node.source + jumpCount;
						}
						if (source != -1
								&& (distanceMatrix[source][destination][node.value] == 0
										|| cost < distanceMatrix[source][destination][node.value])
								&& (node.value == 0 || ((distanceMatrix[source][destination][node.value - 1] == 0
										|| cost < distanceMatrix[source][destination][node.value - 1])
										&& (node.value == 1
												|| ((distanceMatrix[source][destination][node.value - 2] == 0
														|| cost < distanceMatrix[source][destination][node.value - 2])
														&& (node.value == 2
																|| distanceMatrix[source][destination][node.value
																		- 3] == 0
																|| cost < distanceMatrix[source][destination][node.value
																		- 3])))))) {
							newNode = new Node(source, destination, cost, node.value + 1);
							newNode.jumpPos = new Node(node.source - 1, node.destination, node.cost, node.value);
							newNode.jumpPos.jumpPos = node.jumpPos;
							pq.add(newNode);

							distanceMatrix[newNode.source][newNode.destination][newNode.value] = newNode.cost;
						}
					}
				}

				// move down
				{
					source = node.source + 1;
					destination = node.destination;
					if (source < h && mazeMatrix[source][destination]) {
						if ((distanceMatrix[source][destination][node.value] == 0
								|| cost < distanceMatrix[source][destination][node.value])
								&& (node.value == 0 || ((distanceMatrix[source][destination][node.value - 1] == 0
										|| cost < distanceMatrix[source][destination][node.value - 1])
										&& (node.value == 1
												|| ((distanceMatrix[source][destination][node.value - 2] == 0
														|| cost < distanceMatrix[source][destination][node.value - 2])
														&& (node.value == 2
																|| distanceMatrix[source][destination][node.value
																		- 3] == 0
																|| cost < distanceMatrix[source][destination][node.value
																		- 3])))))) {
							distanceMatrix[source][destination][node.value] = cost;

							newNode = new Node(source, destination, cost, node.value);
							newNode.jumpPos = node.jumpPos;
							pq.add(newNode);
						}

					} else if (node.value < 3) {
						jumpCount = 2;
						source = -1;
						if (node.source + jumpCount < h && mazeMatrix[node.source + jumpCount][node.destination]) {
							source = node.source + jumpCount;
						}
						if (source != -1
								&& (distanceMatrix[source][destination][node.value] == 0
										|| cost < distanceMatrix[source][destination][node.value])
								&& (node.value == 0 || ((distanceMatrix[source][destination][node.value - 1] == 0
										|| cost < distanceMatrix[source][destination][node.value - 1])
										&& (node.value == 1
												|| ((distanceMatrix[source][destination][node.value - 2] == 0
														|| cost < distanceMatrix[source][destination][node.value - 2])
														&& (node.value == 2
																|| distanceMatrix[source][destination][node.value
																		- 3] == 0
																|| cost < distanceMatrix[source][destination][node.value
																		- 3])))))) {
							newNode = new Node(source, destination, cost, node.value + 1);
							newNode.jumpPos = new Node(node.source + 1, node.destination, node.cost, node.value);
							newNode.jumpPos.jumpPos = node.jumpPos;
							pq.add(newNode);

							distanceMatrix[newNode.source][newNode.destination][newNode.value] = newNode.cost;
						}
					}
				}
			}

			String[] output = { "unused", "unused", "unused" };
			source = 0;

			while (outOfMaze && node != null && node.value > 0) {
				output[source++] = (node.jumpPos.destination + 1) + " " + (node.jumpPos.source + 1);
				node = node.jumpPos;
			}

			System.out.println("Case #" + i + ": ");
			for (String s : output) {
				System.out.println(s);
			}

				br.readLine();
		}

	}

	static class Node {
		int source, destination, cost, value;
		Node jumpPos;

		Node(int i, int j, int cost, int value) {
			this.source = i;
			this.destination = j;
			this.cost = cost;
			this.value = value;
		}
	}
}
