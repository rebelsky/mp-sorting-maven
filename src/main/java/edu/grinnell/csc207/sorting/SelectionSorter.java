package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class SelectionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Find the index of the smallest element in positions 
   * [start .. finish).
   *
   * @param values
   *   The array of values in which we search.
   * @param start
   *   The start of the subarray (inclusive).
   * @param finish
   *   The end of the subarray (exclusive).
   *
   * @return an index, s, s.t., order.compare(values[s], values[i])
   *   is less than or equal to 0 for all i in the range [start .. finish).
   *
   * @pre start &lt; finish.
   */
  int indexOfSmallest(T[] values, int start, int finish) {
    int s = start;
    for (int i = start + 1; i < finish; i++) {
      if (order.compare(values[i], values[s]) < 0) {
        s = i;
      } // if
    } // for
    return s;
  } // indexOfSmallest(T[], int, int)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; vals.length,
   *     order.compare(vals[i-1], vals[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    for (int i = 0; i < values.length; i++) {
      ArrayUtils.swap(values, i, indexOfSmallest(values, i, values.length));
    } // for
  } // sort(T[])
} // class SelectionSorter
