/**
 * 
 */


/**
 * @author ardobryn
 *
 */
public class Runner {
	
	private enum Sorts {
		SelectionSort,
		InsertionSort,
		ShellSort,
		BubbleSort,
		MergeSort,
		QuickSort,
		HeapSort
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			StdOut.printf("Minimum 2 arguments required");
			return;
		}
		
		int wk = Integer.parseInt(args[0]);
		int q  = Integer.parseInt(args[1]);
		
		int rc = 0;
		switch (wk) {
		case 1:
			rc = Wk1.run(q);
			break;
		case 2:
			AbstractSort<Integer> srt = sortFactory(new Integer[]{7,3,1,8,2,0,5,6,2}, Sorts.values()[q]);
			//AbstractSort<Integer> srt = sortFactory(new Integer[]{0,1,2,3,4,4,3,2,1,0}, Sorts.values()[q]);
			//AbstractSort<Character> srt = sortFactory(new Character[]{'P','N','W','E','A','Z','F','I','G','U','M','O'}, Sorts.values()[q]);
			if (srt == null) {
				rc = 1;
			} else {
				if (args.length > 2) {
					if (Sorts.values()[q] == Sorts.BubbleSort) 
						((BubbleSort<Integer>)srt).setSortMethod(BubbleSort.SortMethod.values()[Integer.parseInt(args[2])]);
					else if (Sorts.values()[q] == Sorts.QuickSort)
						((QuickSort<Integer>)srt).setPartition(QuickSort.Partition.values()[Integer.parseInt(args[2])]);
				}
				srt.sort();
				srt.printData();
				StdOut.printf("%s took %d compares and %d swaps\n", srt.name(), srt.getCompares(), srt.getSwaps());
			}
			break;
		case 3:
			QuickSort<Integer> sel = new QuickSort<Integer>(new Integer[]{7, 3, 1, 8, 2, 0, 5, 6, 2});
			StdOut.printf("4th largest: %d\n", sel.select(4));
			StdOut.printf("%s took %d compares and %d swaps\n", sel.name(), sel.getCompares(), sel.getSwaps());
			break;
		case 4:
			BinaryHeap<Character> h = new BinaryHeap<Character>(5, BinaryHeap.Order.Max);
			for (char val : new Character[]{'P','N','W','E','A','Z','F','I','G','U','M','O'}) h.insert(val);
			h.printData();
			try {
				while (!h.isEmpty()) StdOut.printf("Max: %c\n", h.remove());
			} catch (Exception e) {
				StdOut.printf("Error: %s\n", e);
			}
			h.printData();
			break;
		case 5:
			BST<Character, Integer> tree = new BST<Character, Integer>();
			int val = 0;
			for (char k : new Character[]{'P','N','W','E','A','Z','F','I','G','U','M','O'}) tree.put(k, val++);
			StdOut.printf("BST size: %d\n", tree.size());
			char skey = 'M';
			StdOut.printf("key '%c' : %d\n", skey, tree.get(skey));
			StdOut.printf("Floor for 'H': '%d'\n", tree.floor('H'));
			StdOut.printf("Ceiling for 'H': '%c'\n", tree.ceiling('H'));
			StdOut.printf("Rank for 'H': '%d'\n", tree.rank('H'));
			break;
		default:
			StdOut.printf("No tasks defined for week %d\n", wk);
			rc = -1;
		}
		
		if (rc != 0) {
			StdOut.printf("Error: %d\n", rc);
		}
	}

	private static <T extends Comparable<T>> AbstractSort<T> sortFactory(T[] arr, Sorts sort) {
		AbstractSort<T> sortObj = null;
		switch (sort) {
		case SelectionSort:
			sortObj = new SelectionSort<T>(arr);
			break;
		case InsertionSort:
			sortObj = new InsertionSort<T>(arr);
			break;
		case ShellSort:
			sortObj = new ShellSort<T>(arr);
			break;
		case BubbleSort:
			sortObj = new BubbleSort<T>(arr);
			break;
		case MergeSort:
			sortObj = new MergeSort<T>(arr);
			break;
		case QuickSort:
			sortObj = new QuickSort<T>(arr);
			break;
		case HeapSort:
			sortObj = new HeapSort<T>(arr);
			break;
		default:
			break;
		}
		return sortObj;
	}
}
