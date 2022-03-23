/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.daffodil.processors.charset

import org.apache.daffodil.io.InputSourceDataInputStream
import org.apache.daffodil.io.FormatInfo

object BitsCharsetUTF16LE extends {
  override val name = "UTF-16LE"
} with BitsCharsetJava {

  override def newDecoder() = new BitsCharsetDecoderUTF16LE()
}

class BitsCharsetDecoderUTF16LE
  extends BitsCharsetDecoderCreatesSurrogates {

  protected override def decodeOneUnicodeChar(dis: InputSourceDataInputStream, finfo: FormatInfo): Char = {
    val byte2 = getByte(dis, 0)
    val byte1 = getByte(dis, 8)
  
    val high = (byte1 << 8) | byte2

    if (high >= 0xD800 && high <= 0xDFFF) {
      // surrogate pair, this needs to be a high surrogate or its an error
      if (high >= 0xDC00) throw new BitsCharsetDecoderMalformedException(16)

      // this is a valid high surrogate pair, need to get the low and save it
      // for the next decode
      val byte4 = getByte(dis, 16)
      val byte3 = getByte(dis, 24)

      val low = (byte3 << 8) | byte4
      // ensure valid low surrogate
      if (low < 0xDC00 || low > 0xDFFF) throw new BitsCharsetDecoderMalformedException(32)

      setLowSurrogate(low.toChar)
    }
    high.toChar
  }
}

final class BitsCharsetUTF16LECompiler
  extends CharsetCompiler("UTF-16LE") {

  override def compileCharset() = {
    new BitsCharsetUTF16LETransformerFactory(name)
  }
}

class BitsCharsetUTF16LETransformerFactory(name: String)
    extends BitsCharsetFactory {

  override def newInstance()= {
    BitsCharsetUTF16LE
  }
}
