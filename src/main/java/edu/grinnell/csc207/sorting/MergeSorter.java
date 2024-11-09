package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  /**
   * The array we'll be using for the merge.
   */
  T[] scratch;

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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Merge the subarray given by [lb .. mid) and the subarray given
   * by [mid .. ub).
   *
   * @param values
   *   The array.
   * @param lb
   *   The lower bound of the first subarray we'll be merging.
   * @param mid
   *   The upper bound of the first subarray we'll be merging; also the
   *   lower bound of the second subarray.
   * @param ub
   *   The upper bound of the second subarray we'll be merging.
   */
  public void merge(T[] values, int lb, int mid, int ub) {
    int i = lb;         // Index into first subarray
    int j = mid;        // Index into second subarray
    int s = 0;          // Index into scratch array

    while ((i < mid) && (j < ub)) {
      if (order.compare(values[i], values[j]) <= 0) {
        scratch[s++] = values[i++];
      } else {
        scratch[s++] = values[j++];
      } // if/else
    } // while

    // Copy the rest of the first subarray.
    while (i < mid) {
      scratch[s++] = values[i++];
    } // while

    // Copy the rest of the second subarray.
    while (j < ub) {
      scratch[s++] = values[j++];
    } // while

    // Copy everything back
    for (i = 0; i < s; i++) {
      values[lb + i] = scratch[i];
    } // for
  } // merge(T[], int, int, int)

  /**
   * Sort the subarray given by [lb .. ub).
   *
   * @param values
   *   The array that we're sorting.
   * @param lb
   *   The lower bound of the subarray (inclusive).
   * @param ub
   *   The upper bound of the subarray (exclusive).
   */
  public void sort(T[] values, int lb, int ub) {
    if (lb >= ub - 1) {
      return;
    } // if
    int mid = lb + (ub - lb)/2;
    sort(values, lb, mid);
    sort(values, mid, ub);
    merge(values, lb, mid, ub);
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
    // Make a scratch array
    scratch = Arrays.copyOf(values, values.length);
    sort(values, 0, values.length);
  } // sort(T[])
} // class MergeSorter
