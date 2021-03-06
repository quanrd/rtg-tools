/*
 * Copyright (c) 2014. Real Time Genomics Limited.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the
 *    distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.rtg.tabix;

import java.io.File;
import java.io.IOException;

import com.rtg.util.io.FileUtils;
import com.rtg.util.test.BgzipFileHelper;
import com.rtg.util.test.FileHelper;

import htsjdk.samtools.util.BlockCompressedInputStream;
import junit.framework.TestCase;

/**
 * Test class
 */
public class VcfPositionReaderTest extends TestCase {

  public void testSomeMethod() throws IOException {
    final File dir = FileUtils.createTempDir("test", "vcf");
    try {
      final File vcfFile = BgzipFileHelper.resourceToBgzipFile("com/rtg/vcf/resources/vcf.txt", new File(dir, "test.vcf.gz"));
      try (VcfPositionReader reader = new VcfPositionReader(new BlockCompressedLineReader(new BlockCompressedInputStream(vcfFile)), 0)) {
        assertTrue(reader.hasNext());
        reader.next();
        assertEquals("20", reader.getReferenceName());
        assertEquals(14369, reader.getStartPosition());
        assertEquals(1, reader.getLengthOnReference());

        assertTrue(reader.hasNext());
        reader.next();
        assertEquals("20", reader.getReferenceName());
        assertEquals(17329, reader.getStartPosition());
        assertEquals(1, reader.getLengthOnReference());

        assertTrue(reader.hasNext());
        reader.next();
        assertEquals("20", reader.getReferenceName());
        assertEquals(1110695, reader.getStartPosition());
        assertEquals(1, reader.getLengthOnReference());

        assertTrue(reader.hasNext());
        reader.next();
        assertEquals("20", reader.getReferenceName());
        assertEquals(1230236, reader.getStartPosition());
        assertEquals(1, reader.getLengthOnReference());

        assertTrue(reader.hasNext());
        reader.next();
        assertEquals("20", reader.getReferenceName());
        assertEquals(1234566, reader.getStartPosition());
        assertEquals(4, reader.getLengthOnReference());

        assertFalse(reader.hasNext());
      }
    } finally {
      assertTrue(FileHelper.deleteAll(dir));
    }
  }
}
