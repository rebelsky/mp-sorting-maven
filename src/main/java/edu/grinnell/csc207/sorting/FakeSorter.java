package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that fails to sort.  Intended primarily to allow us to watch
 * tests fail.
 *
 * @param <T>
 *   Some type. Ignored.
 *
 * @author Samuel A. Rebelsky
 */

public class FakeSorter<T> implements Sorter<T> {

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  public FakeSorter() {
  } // FakeSorter()

  /**
   * Create a sorter using a particular comparator (included for
   * consistency with other sorters).
   *
   * @param order
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public FakeSorter(Comparator<? super T> order) {
  } // FakeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place.
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
    // Cross your fingers and hope it's already sorted.
  } // sort(T[])
} // class FakeSorter
