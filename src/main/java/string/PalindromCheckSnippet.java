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

package string;

/**
 * PalindromCheckSnippet.
 */
public class PalindromCheckSnippet {

  private PalindromCheckSnippet() {}

  /**
   * Checks if given string is palindrome (same forward and backward). Skips non-letter characters
   * Credits: https://github.com/kousen/java_8_recipes
   *
   * @param s string to check
   * @return true if palindrome
   */
  public static boolean isPalindrome(String s) {
    var left = 0;
    var right = s.length() - 1;
    while (left < right) {
      if (!Character.isLetter(s.charAt(left))) {
        left++;
      } else if (!Character.isLetter(s.charAt(right))) {
        right--;
      } else if (Character.toLowerCase(s.charAt(left))
          != Character.toLowerCase(s.charAt(right))) {
        return false;
      } else {
        left++;
        right--;
      }
    }

    return true;
  }
}
