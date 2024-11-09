package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  /**
   * A random number generator.
   */
  Random rand;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
    this.rand = new Random();
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Partition the array using a DNF-like algorithm.
   *
   * @param values
   *   The array to partition
   * @param lb
   *   The lower bound of the subarray (inclusive)
   * @param ub
   *   The upper bound of the subarray (exclusive)
   *
   * @return a pair of integers, the first is the end of the
   *   small elements, the second is the end of the equal elements.
   */
  public int[] partition(T[] values, int lb, int ub) {
    // System.err.printf("partition(%s)\n", ArrayUtils.toString(values, lb, ub));
    T pivot = values[lb + rand.nextInt(ub - lb)];
    // System.err.printf("  pivot: %s\n", pivot.toString());
    int small = lb;     // Upper bound of small values
    int equal = lb;     // Upper bound of equal values
    int large = lb;     // Upper bound of large values
      
    while (large < ub) {
      int comparison = order.compare(values[large], pivot);
      // System.err.printf("  %s vs. %s: ", values[large], pivot);
      if (comparison < 0) {
        // System.err.println("smaller");
        if (equal == large) {
          ArrayUtils.swap(values, small++, large++);
          equal++;
        } else if (small == equal) {
          ArrayUtils.swap(values, small++, large++);
          equal++;
        } else {
          ArrayUtils.swap(values, small, equal++);
          ArrayUtils.swap(values, small++, large++);
        } // if/else
      } else if (comparison == 0) {
        // System.err.println("equal");
        ArrayUtils.swap(values, equal++, large++);
      } else {
        // System.err.println("greater");
        large++;
      } // if/else
      // System.err.printf("    values: %s\n", 
      //   ArrayUtils.toString(values, lb, ub));
      // System.err.printf("    small: %d, equal: %d, large: %d\n", 
      //   small, equal, large);
    } // while
    // System.err.printf("  Done partitioning\n");
    return new int[] {small, equal};
  } // partition

  /**
   * Sort the subarray given by [lb .. ub).
   *
   * @param values
   *   The array to partition
   * @param lb
   *   The lower bound of the subarray (inclusive)
   * @param ub
   *   The upper bound of the subarray (exclusive)
   */
  public void sort(T[] values, int lb, int ub) {
    if (lb >= ub) {
      return;
    } // if
    // System.err.printf("sort(%s)\n", ArrayUtils.toString(values, lb, ub));
    int[] boundaries = partition(values, lb, ub);
    sort(values, lb, boundaries[0]);
    sort(values, boundaries[1], ub);
  } // sort

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; valsues.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    sort(values, 0, values.length);
  } // sort(T[])
} // class Quicksorter
