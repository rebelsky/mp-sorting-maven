package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class InsertionSorter<T> implements Sorter<T> {
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
   * Create a sorter using a particular comparator (included for
   * consistency with other sorters).
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Insert the value at position k into the proper location in
   * the subarray of indices [0..k).
   *
   * @param values
   *   Our array
   * @param k
   *   The location of the value to insert.
   */
  void insert(T[] values, int k) {
    for (int i = k; i > 0; i--) {
      if (order.compare(values[i - 1], values[i]) <= 0) {
        return;
      } else {
        ArrayUtils.swap(values, i - 1, i);
      } // if/else
    } // for
  } // insert

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
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
    for (int i = 1; i < values.length; i++) {
      insert(values, i);
    } // for
  } // sort(T[])
} // class InsertionSorter
