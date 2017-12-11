package fi.pku.gameoflife;

import java.util.Random;

public class GOL {
	int[][] board;

	int columns = 8, rows = 8;
	int[][] next = new int[columns][rows];

	public void GOL() {
		// Initialize board
		board = new int[columns][rows];
		init();
	}

	void init() {
		Random random = new Random();
		for (int i = 1; i < columns - 1; i++) {
			for (int j = 1; j < rows - 1; j++) {
				board[i][j] = random.nextInt(2);
			}
		}
	}

	void display() {
		System.out.println("Initial Board");
		System.out.println("==============");
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}

	}

	void generate() {

		for (int x = 1; x < columns - 1; x++) {
			for (int y = 1; y < rows - 1; y++) {

				int neighbors = 0;
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						neighbors += board[x + i][y + j];
					}
				}

				// subtract current cell
				neighbors -= board[x][y];

				if ((board[x][y] == 1) && (neighbors < 2))
					next[x][y] = 0; // Loneliness
				else if ((board[x][y] == 1) && (neighbors > 3))
					next[x][y] = 0; // Overpopulation death
				else if ((board[x][y] == 0) && (neighbors == 3))
					next[x][y] = 1; // Reproduction
				else
					next[x][y] = board[x][y]; // No Change
			}
		}

		board = next;
	}

	void displayNextState() {
		System.out.println("Updated Board");
		System.out.println("==============");
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				System.out.print(next[i][j]);
			}
			System.out.println("");
		}

	}

	public static void main(String[] args) {
		GOL gol = new GOL();
		gol.GOL();
		gol.display();
		gol.generate();
		System.out.println("==============");
		gol.displayNextState();
	}
}
