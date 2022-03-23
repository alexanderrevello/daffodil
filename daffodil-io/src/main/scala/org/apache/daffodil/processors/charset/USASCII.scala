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

object BitsCharsetUSASCII extends {
  override val name = "US-ASCII"
  override val aliases = Seq("ASCII")
} with BitsCharsetJava {

  override def newDecoder() = new BitsCharsetDecoderUSASCII()
}

class BitsCharsetDecoderUSASCII
  extends BitsCharsetDecoderByteSize {

  override def decodeOneChar(dis: InputSourceDataInputStream, finfo: FormatInfo): Char = {
    val byte = getByte(dis, 0)
    if (byte >= 128) {
      throw new BitsCharsetDecoderMalformedException(8)
    } 
    byte.toChar
  }
}

final class ASCIICompilerAlias
  extends CharsetCompiler("ASCII") {

  override def compileCharset() = {
    new USASCIICharsetTransformerFactory(name)
  }
}

final class ASCIICompiler
  extends CharsetCompiler("US-ASCII") {

  override def compileCharset() = {
    new USASCIICharsetTransformerFactory(name)
  }
}

class USASCIICharsetTransformerFactory(name: String)
    extends BitsCharsetFactory {

  override def newInstance()= {
    BitsCharsetUSASCII
  }
}
