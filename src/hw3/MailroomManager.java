//Halice Sachin Falcao
//SBU ID-115437214
//R-01

package hw3;

import java.util.Scanner;
import java.util.Stack;

/**
 * The MailroomManager class serves as the driver class for managing package
 * stacks in a mailroom.
 * It allows users to add, remove, and move packages between stacks based on
 * recipient names.
 */
public class MailroomManager {

	/**
	 * The main method of the MailroomManager class, which runs the menu-driven
	 * application.
	 * It allows the user to interact with package stacks by selecting various
	 * operations.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize an array
		PackageStack[] stacks = new PackageStack[6];

		int day = 0;
		boolean quit = false;
		String opt;
		// Create instances of PackageStack for each stack
		for (int i = 0; i < 6; i++) {
			stacks[i] = new PackageStack();
		}

		Scanner input = new Scanner(System.in);

		System.out.println(
				"Welcome to the Irving Mailroom Manager. You can try to make it better, but the odds are stacked against you. It is day "
						+ day + ".\n");
		while (!quit) {
			System.out.println("Menu:\n" +
					"     D) Deliver a package\n" +
					"     G) Get someone's package\n" +
					"     T) Make it tomorrow\n" +
					"     P) Print the stacks\n" +
					"     M) Move a package from one stack to another\n" +
					"     F) Find packages in the wrong stack and move to floor\n" +
					"     L) List all packages awaiting a user\n" +
					"     E) Empty the floor.\n" +
					"     Q) Quit\n");

			System.out.print("Please select an option: ");
			opt = input.nextLine().toUpperCase();
			switch (opt) {
				case "D":
					deliverPackage(input, stacks, day);

					break;
				case "G":
					try {
						getPackage(input, stacks);
					} catch (FullStackException e) {
						System.out.println("Stack full.");
					} catch (EmptyStackException e) {
						System.out.println("Empty Stack therefore cannot get packages.");
						e.printStackTrace();
					}

					break;
				case "T":
					day++;
					System.out.println("It is now day " + day + ".");
					break;
				case "P":
					print(stacks);
					break;
				case "M":
					movePackage(input, stacks);
					break;
				case "F":
					try {
						moveMisplacedPackages(stacks);
					} catch (FullStackException e) {
						System.out.println("Stack full.");
					} catch (EmptyStackException e) {
						System.out.println("Empty Stack.");
					}
					break;
				case "L":
					try {
						listPackages(input, stacks);
					} catch (FullStackException e) {
						System.out.println("Stack full.");
					} catch (EmptyStackException e) {
						System.out.println("Empty Stack.");
					}
					break;
				case "E":
					emptyFloor(stacks);
					break;
				case "Q":
					System.out.println("Use Amazon Locker next time.");
					System.out.println("(A-G, H-J, K-M, N-R, S-Z)");
					quit = true;
					break;
				default:
					System.out.println("Invalid option. Please try again.");

			}

		}
		input.close();

	}

	/**
	 * Adds a new package to the appropriate package stack based on recipient's
	 * name.
	 *
	 * @param input  the Scanner object to read user input
	 * @param stacks an array of PackageStack objects representing the package
	 *               stacks
	 * @param day    the current day
	 */

	private static void deliverPackage(Scanner input, PackageStack[] stacks, int day) {
		int stacknum = 0;
		System.out.print("Please enter the recipient name: ");
		String name = input.nextLine();
		System.out.print("Please enter the weight (lbs): ");
		double weight = input.nextDouble();
		input.nextLine();

		Package newPkg = new Package(name, day, weight);

		if (name.toUpperCase().charAt(0) == 'A' || name.toUpperCase().charAt(0) == 'B'
				|| name.toUpperCase().charAt(0) == 'C' || name.toUpperCase().charAt(0) == 'D'
				|| name.toUpperCase().charAt(0) == 'E' || name.toUpperCase().charAt(0) == 'F'
				|| name.toUpperCase().charAt(0) == 'G') {
			stacknum = 0;
		}
		if (name.toUpperCase().charAt(0) == 'H' || name.toUpperCase().charAt(0) == 'I'
				|| name.toUpperCase().charAt(0) == 'J') {
			stacknum = 1;
		}
		if (name.toUpperCase().charAt(0) == 'K' || name.toUpperCase().charAt(0) == 'L'
				|| name.toUpperCase().charAt(0) == 'M') {
			stacknum = 2;
		}
		if (name.toUpperCase().charAt(0) == 'N' || name.toUpperCase().charAt(0) == 'O'
				|| name.toUpperCase().charAt(0) == 'P' || name.toUpperCase().charAt(0) == 'Q'
				|| name.charAt(0) == 'R') {
			stacknum = 3;
		}
		if (name.toUpperCase().charAt(0) == 'S' || name.toUpperCase().charAt(0) == 'T'
				|| name.toUpperCase().charAt(0) == 'U' || name.toUpperCase().charAt(0) == 'V'
				|| name.toUpperCase().charAt(0) == 'W' || name.toUpperCase().charAt(0) == 'X'
				|| name.toUpperCase().charAt(0) == 'Y' || name.toUpperCase().charAt(0) == 'Z') {
			stacknum = 4;
		}
		try {
			stacks[stacknum].push(newPkg);
		} catch (FullStackException e) {
			e.printStackTrace();
		}
		System.out.println("A " + (weight % 1 == 0 ? String.valueOf((int) weight) : String.valueOf(weight))
				+ " lb package is awaiting pickup by " + name);
	}

	/**
	 * Retrieves a package belonging to a specified recipient from the package
	 * stacks.
	 *
	 * @param input  the Scanner object to read user input
	 * @param stacks an array of PackageStack objects representing the package
	 *               stacks
	 * @throws FullStackException  if the package stack is full while popping
	 * @throws EmptyStackException if the package stack is empty
	 */

	private static void getPackage(Scanner input, PackageStack[] stacks)
			throws FullStackException, EmptyStackException {

		System.out.print("Please enter the recipient name: ");
		String name = input.nextLine();
		boolean foundPackage = false;
		int count = 0;

		for (int x = 5; x >= 0; x--) {
			if (!stacks[x].isEmpty()) {
				Stack<Package> tempStack = new Stack<>();

				while (!stacks[x].isEmpty() && count == 0) {
					Package pkg = stacks[x].pop();
					tempStack.push(pkg);

					if (pkg.getRecipient().equals(name)) {
						tempStack.pop();
						System.out.println("Give " + name + " "
								+ (pkg.getWeight() % 1 == 0 ? String.valueOf((int) pkg.getWeight())
										: String.valueOf(pkg.getWeight()))
								+ " lb package delivered on day " + pkg.getArrivalDate());
						foundPackage = true;
						count++;
					}
				}

				while (!tempStack.isEmpty()) {
					stacks[x].push(tempStack.pop());
				}
			}
		}

		if (!foundPackage) {
			System.out.println("Empty Stack therefore cannot get packages.");
		}

	}

	/**
	 * Prints the current status of all package stacks.
	 *
	 * @param stacks an array of PackageStacks representing mail stacks
	 */

	private static void print(PackageStack[] stacks) {
		System.out.println("Current Packages:");
		System.out.println("----------------------");
		System.out.print("Stack 1 (A-G):|");
		System.out.println(stacks[0].toString());
		System.out.print("Stack 2 (H-J):|");
		System.out.println(stacks[1].toString());
		System.out.print("Stack 3 (K-M):|");
		System.out.println(stacks[2].toString());
		System.out.print("Stack 4 (N-R):|");
		System.out.println(stacks[3].toString());
		System.out.print("Stack 5 (S-Z):|");
		System.out.println(stacks[4].toString());
		System.out.print("Floor: |");
		System.out.println(stacks[5].toString());
	}

	/**
	 * Moves a package from one stack to another.
	 *
	 * @param input  the Scanner object for user input
	 * @param stacks an array of PackageStacks representing mail stacks
	 */

	private static void movePackage(Scanner input, PackageStack[] stacks) {

		System.out.print("Please enter the source stack (enter 0 for floor): ");
		int source = input.nextInt();

		if (source < 0 || source > 5) {
			System.out.println("Invalid source stack. Please try again.");
			return;
		}

		System.out.print("Please enter the destination stack: ");
		int dest = input.nextInt();
		input.nextLine();
		if (dest < 0 || dest > 5) {
			System.out.println("Invalid destination stack. Please try again.");
			return;
		}

		source--;
		dest--;

		try {
			if (source == -1 && dest != -1) {
				System.out.println("Invalid source stack. Please try again.");
				return;
			}
			if (dest == -1 && source != 3) {
				System.out.println("Invalid destination stack. Please try again.");
				return;
			}
			Package temp;
			if (source == -1) {
				temp = stacks[3].peek();
				stacks[3].pop();
			} else {
				temp = stacks[source].peek();
				stacks[source].pop();
			}

			if (dest == -1) {
				stacks[0].push(temp);
			} else {
				stacks[dest].push(temp);
			}

			System.out.println("Package moved successfully from stack " + (source == -1 ? "4" : source + 1)
					+ " to stack " + (dest == -1 ? "0" : dest + 1));
		} catch (EmptyStackException | FullStackException e) {
			System.out.println("Source stack is empty. Cannot move a package.");
		}

	}

	/**
	 * Moves misplaced packages to the floor stack based on recipient name.
	 *
	 * @param stacks an array of PackageStacks representing mail stacks
	 * @throws FullStackException  if the stack is full
	 * @throws EmptyStackException if the stack is empty
	 */

	private static void moveMisplacedPackages(PackageStack[] stacks) throws FullStackException, EmptyStackException {

		if (!stacks[0].isEmpty() && (Character.toUpperCase(stacks[0].peek().getRecipient().charAt(0)) != 'A'
				&& Character.toUpperCase(stacks[0].peek().getRecipient().charAt(0)) != 'B'
				&& Character.toUpperCase(stacks[0].peek().getRecipient().charAt(0)) != 'C'
				&& Character.toUpperCase(stacks[0].peek().getRecipient().charAt(0)) != 'D'
				&& Character.toUpperCase(stacks[0].peek().getRecipient().charAt(0)) != 'E'
				&& Character.toUpperCase(stacks[0].peek().getRecipient().charAt(0)) != 'F'
				&& Character.toUpperCase(stacks[0].peek().getRecipient().charAt(0)) != 'G')) {
			stacks[5].push(stacks[0].peek());
			stacks[0].pop();
		}

		if (!stacks[1].isEmpty() && (Character.toUpperCase(stacks[1].peek().getRecipient().charAt(0)) != 'H'
				&& Character.toUpperCase(stacks[1].peek().getRecipient().charAt(0)) != 'I'
				&& Character.toUpperCase(stacks[1].peek().getRecipient().charAt(0)) != 'J')) {
			stacks[5].push(stacks[1].peek());
			stacks[1].pop();
		}

		if (!stacks[2].isEmpty() && (Character.toUpperCase(stacks[2].peek().getRecipient().charAt(0)) != 'K'
				&& Character.toUpperCase(stacks[2].peek().getRecipient().charAt(0)) != 'L'
				&& Character.toUpperCase(stacks[2].peek().getRecipient().charAt(0)) != 'M')) {
			stacks[5].push(stacks[2].peek());
			stacks[2].pop();
		}

		if (!stacks[3].isEmpty() && (Character.toUpperCase(stacks[3].peek().getRecipient().charAt(0)) != 'N'
				&& Character.toUpperCase(stacks[3].peek().getRecipient().charAt(0)) != 'O'
				&& Character.toUpperCase(stacks[3].peek().getRecipient().charAt(0)) != 'P'
				&& Character.toUpperCase(stacks[3].peek().getRecipient().charAt(0)) != 'Q'
				&& Character.toUpperCase(stacks[3].peek().getRecipient().charAt(0)) != 'R')) {
			stacks[5].push(stacks[3].peek());
			stacks[3].pop();
		}

		if (!stacks[4].isEmpty() && (Character.toUpperCase(stacks[4].peek().getRecipient().charAt(0)) != 'S'
				&& Character.toUpperCase(stacks[4].peek().getRecipient().charAt(0)) != 'T'
				&& Character.toUpperCase(stacks[4].peek().getRecipient().charAt(0)) != 'U'
				&& Character.toUpperCase(stacks[4].peek().getRecipient().charAt(0)) != 'V'
				&& Character.toUpperCase(stacks[4].peek().getRecipient().charAt(0)) != 'W'
				&& Character.toUpperCase(stacks[4].peek().getRecipient().charAt(0)) != 'X'
				&& Character.toUpperCase(stacks[4].peek().getRecipient().charAt(0)) != 'Y'
				&& Character.toUpperCase(stacks[4].peek().getRecipient().charAt(0)) != 'Z')) {
			stacks[5].push(stacks[4].peek());
			stacks[4].pop();
		}

		System.out.println("Misplaced packages moved to floor.");
	}

	/**
	 * Lists all packages awaiting a specific user and their details.
	 *
	 * @param input  the Scanner object for user input
	 * @param stacks an array of PackageStacks representing mail stacks
	 * @throws FullStackException  if the stack is full
	 * @throws EmptyStackException if the stack is empty
	 */

	private static void listPackages(Scanner input, PackageStack[] stacks)
			throws EmptyStackException, FullStackException {
		System.out.print("Please enter the recipient name:");
		String name = input.next();
		input.nextLine();

		int totalPackages = 0;
		int count = 0;

		for (int x = 0; x < 6; x++) {
			Stack<Package> tempStack = new Stack<>();
			int packageCount = 1;

			while (!stacks[x].isEmpty()) {
				Package pkg = stacks[x].pop();
				tempStack.push(pkg);

				if (pkg.getRecipient().equals(name)) {

					totalPackages++;
					count++;
				}
			}

			if (totalPackages > 0) {
				System.out.println(name + " has " + totalPackages + " package total.");

				while (!tempStack.isEmpty()) {
					Package pkg = tempStack.pop();
					stacks[x].push(pkg);

					if (pkg.getRecipient().equals(name)) {
						System.out.println(
								"Package " + packageCount + " is in Stack " + (x + 1) + ", it was delivered on day "
										+ pkg.getArrivalDate() + ", and weighs " + (int) pkg.getWeight() + " lbs");
						packageCount++;
						totalPackages--;
					}
				}
			}
		}
		if (count == 0) {
			System.out.println("No packages found for " + name + ".");
		}
	}

	/**
	 * Empties the floor stack.
	 *
	 * @param stacks an array of PackageStacks representing mail stacks
	 */

	private static void emptyFloor(PackageStack[] stacks) {
		for (int i = 5; i < 6; i++) {
			while (!stacks[i].isEmpty()) {
				try {
					Package temp = stacks[i].pop();
				} catch (EmptyStackException e) {
					System.out.println("Stack Empty.");
				}
			}
		}
		System.out.println("\nThe floor has been emptied. Mr. Trash Can is no longer hungry.\n");
	}

}
