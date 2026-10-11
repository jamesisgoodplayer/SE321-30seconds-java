/*
 * MIT License
 *
 * Copyright (c) 2017-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package algorithm;

/**
 * BinarySearchIn2dArraySnippet.
 */
public class BinarySearchIn2dArraySnippet {

  private BinarySearchIn2dArraySnippet() {}

  /**
   * Search an item with binarySearch algorithm.
   *
   * @param matrix a matrix whose rows are sorted and whose values increase row by row
   * @param target an item to search
   * @return if location of item is found, otherwise return {-1,-1}
   */
  public static int[] binarySearchIn2darr(int[][] matrix, int target) {
    int cols = matrix[0].length;
    int low = 0;
    int high = matrix.length * cols - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      int value = matrix[mid / cols][mid % cols];
      if (value == target) {
        return new int[]{mid / cols, mid % cols};
      }
      if (value < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return new int[]{-1, -1};
  }
}
