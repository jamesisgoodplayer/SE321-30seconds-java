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

package file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.junit.jupiter.api.Test;

/*
 * Tests for 30 Seconds of Java code library
 *
 */
class ZipDirectorySnippetTest {
  /**
   * Tests for {@link ZipDirectorySnippet#zipDirectory(String, String)}.
   */
  @Test
  void testZipFileDirectory() throws IOException {
    final var src = "src/test/resources/dir3";
    final var dst = "src/test/resources/dir3.zip";
    try {
      ZipDirectorySnippet.zipDirectory(src, dst);
      assertTrue(Files.exists(Paths.get(dst)));
      var regularFiles = 0;
      var directories = 0;
      try (var zipFile = new ZipFile(Paths.get(dst).toFile())) {
        var zipEntries = zipFile.entries();
        while (zipEntries.hasMoreElements()) {
          if (zipEntries.nextElement().isDirectory()) {
            directories++;
          } else {
            regularFiles++;
          }
        }
        assertEquals(4, zipFile.size());
      }
      assertEquals(2, directories); // The root directory + inner directory
      assertEquals(2, regularFiles); // Two simple files
    } finally {
      Files.deleteIfExists(new File(dst).toPath());
    }
  }

  @Test()
  void testZipFileDirectoryInvalid() throws IOException {

    final var src = "src/test/resources/dirUnknown";
    final var dst = "src/test/resources/dirUnknown.zip";
    assertThrows(IOException.class, () -> {
      ZipDirectorySnippet.zipDirectory(src, dst);
    });
    Files.deleteIfExists(new File(dst).toPath());
  }

  /**
   * Tests that a directory entry given with a trailing slash is written as a directory.
   */
  @Test
  void testZipFileDirectoryEntryWithTrailingSlash() throws IOException {
    var source = Files.createTempDirectory("zip-slash").toFile();
    var dst = Files.createTempFile("zip-slash", ".zip").toFile();
    try (var zipOut = new ZipOutputStream(new FileOutputStream(dst))) {
      ZipDirectorySnippet.zipFile(source, "folder/", zipOut);
    }
    try (var zipFile = new ZipFile(dst)) {
      assertEquals(1, zipFile.size());
      assertNotNull(zipFile.getEntry("folder/"));
    }
  }

  /**
   * Tests that the content of a file inside the directory is copied into the archive.
   */
  @Test
  void testZipDirectoryWithFileContent() throws IOException {
    var source = Files.createTempDirectory("zip-content");
    Files.writeString(source.resolve("content.txt"), "30 seconds of java");
    var dst = Files.createTempFile("zip-content", ".zip").toFile();
    try {
      ZipDirectorySnippet.zipDirectory(source.toString(), dst.toString());
      try (var zipFile = new ZipFile(dst)) {
        assertEquals(2, zipFile.size());
        assertEquals("30 seconds of java", new String(zipFile
            .getInputStream(zipFile.getEntry(source.getFileName() + "/content.txt"))
            .readAllBytes(), StandardCharsets.UTF_8));
      }
    } finally {
      Files.deleteIfExists(dst.toPath());
    }
  }
}
