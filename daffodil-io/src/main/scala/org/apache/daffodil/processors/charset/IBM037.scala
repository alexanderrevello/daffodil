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
import java.nio.ByteBuffer
import java.nio.charset.Charset

object BitsCharsetIBM037 extends {
  override val name = "IBM037"
  override val aliases = Seq("EBCDIC-CP-US")
} with BitsCharsetJava {

  val decodeString = {
    val bytes = ByteBuffer.wrap((0 to 255).map{ _.toByte }.toArray)
    Charset.forName("ebcdic-cp-us").newDecoder().decode(bytes).toString
  }

  override def newDecoder() = new BitsCharsetDecoderIBM037()
}

class BitsCharsetDecoderIBM037
  extends BitsCharsetDecoderByteSize {

  protected override def decodeOneChar(dis: InputSourceDataInputStream, finfo: FormatInfo): Char = {
    val byte = getByte(dis, 0)
    BitsCharsetIBM037.decodeString(byte)
  }
}

final class BitsCharsetIBM037Definition
  extends BitsCharsetDefinition {

  override def name() = "IBM037"

  override def newFactory() = {
    new BitsCharsetIBM037Factory()
  }
}

final class BitsCharsetIBM037AliasDefinition
  extends BitsCharsetDefinition {

    override def name() = "EBCDIC-CP-US"

  override def newFactory() = {
    new BitsCharsetIBM037Factory()
  }
}

final class BitsCharsetIBM037Factory()
    extends BitsCharsetFactory {

  override def newInstance()= {
    BitsCharsetIBM037
  }
}
