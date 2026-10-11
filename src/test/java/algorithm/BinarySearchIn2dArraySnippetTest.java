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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for 30 Seconds of Java code library.
 */
class BinarySearchIn2dArraySnippetTest {

  /**
   * Test for {@link BinarySearchIn2dArraySnippet#binarySearchIn2darr(int[][], int)}.
   */
  @Test
  void testBinarySearchIn2dArraySnippetTest() {
    int[][] arr = {{3, 4, 7, 9}, {12, 24, 26, 29}, {34, 55, 88, 99}, {100, 189, 232, 234}};
    Assertions.assertArrayEquals(new int[]{1, 2},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(arr, 26));
    Assertions.assertArrayEquals(new int[]{-1, -1},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(arr, 69));
  }

  /**
   * Every value of a sorted matrix is found at the position where it really is.
   */
  @Test
  void testEveryValueIsFoundAtItsOwnPosition() {
    int[][] matrix = {{1, 4, 7, 11, 15}, {16, 19, 22, 26, 30}, {31, 34, 37, 41, 45}};
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        Assertions.assertArrayEquals(new int[]{row, col},
            BinarySearchIn2dArraySnippet.binarySearchIn2darr(matrix, matrix[row][col]));
      }
    }
  }

  /**
   * A value that is not in the matrix is reported as not found.
   */
  @Test
  void testMissingValues() {
    int[][] matrix = {{1, 4, 7, 11}, {12, 15, 18, 21}};
    Assertions.assertArrayEquals(new int[]{-1, -1},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(matrix, 0));
    Assertions.assertArrayEquals(new int[]{-1, -1},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(matrix, 9));
    Assertions.assertArrayEquals(new int[]{-1, -1},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(matrix, 22));
  }

  /**
   * Matrices with a single row, a single column or a single element work as well.
   */
  @Test
  void testSmallMatrices() {
    Assertions.assertArrayEquals(new int[]{0, 2},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(new int[][]{{5, 6, 7}}, 7));
    Assertions.assertArrayEquals(new int[]{-1, -1},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(new int[][]{{5, 6, 7}}, 4));
    Assertions.assertArrayEquals(new int[]{2, 0},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(new int[][]{{5}, {6}, {7}}, 7));
    Assertions.assertArrayEquals(new int[]{-1, -1},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(new int[][]{{5}, {6}, {7}}, 8));
    Assertions.assertArrayEquals(new int[]{0, 0},
        BinarySearchIn2dArraySnippet.binarySearchIn2darr(new int[][]{{42}}, 42));
  }
}
