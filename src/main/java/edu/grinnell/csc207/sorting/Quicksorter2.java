package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter2<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  /**
   * A handy-dandy random-number generator.
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
  public Quicksorter2(Comparator<? super T> comparator) {
    this.order = comparator;
    this.rand = new Random();
  } // Quicksorter2(Comparator)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Partition the subarray with indices [lb .. ub) into three parts 
   * (smaller, equal, greater).
   *
   * @param values
   *   The array to partition.
   * @param lb
   *   The lower bound of the subarray (inclusive)
   * @param ub
   *   The upper bound of the subarray (exclusive)
   *
   * @pre lb &lt; ub
   *
   * @return an array containing the upper bound of the smaller section
   *   and the upper bound of the equal section.
   */
  public int[] partition(T[] values, int lb, int ub) {
    int small = lb;
    int equal = lb;
    int large = ub;
    T pivot = values[lb + rand.nextInt(ub - lb)];

    // +---------+---------+--------+--------+
    // |   <     |   =     |   ?    |   >    |
    // +---------+---------+--------+--------+
    //           |         |        |
    //           small     equal    large
    while (equal < large) {
      int comparison = order.compare(values[equal], pivot);
      if (comparison < 0) {
        ArrayUtils.swap(values, small++, equal++);
      } else if (comparison == 0) {
        ++equal;
      } else {
        ArrayUtils.swap(values, equal, --large);
      } // if/else
    } // while

    return new int[] {small, equal};
  } // partition(T[], lb, ub)

  /**
   * Sort the subarray given by indices [lb .. ub) in place.
   *
   * @param values
   *   The array to partition.
   * @param lb
   *   The lower bound of the subarray (inclusive)
   * @param ub
   *   The upper bound of the subarray (exclusive)
   */
  public void sort(T[] values, int lb, int ub) {
    if (lb >= ub - 1) {
      return;
    } // if
    int[] bounds = partition(values, lb, ub);
    sort(values, lb, bounds[0]);
    sort(values, bounds[1], ub);
  } // sort(T[], int, int)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    sort(values, 0, values.length);
  } // sort(T[])
} // class Quicksorter2
