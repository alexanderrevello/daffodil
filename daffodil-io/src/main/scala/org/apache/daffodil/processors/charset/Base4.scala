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

import org.apache.daffodil.schema.annotation.props.gen.BitOrder

/**
 * Base 4 aka Quarternary
 */

object BitsCharsetBase4LSBF extends {
  override val name = "X-DFDL-BASE4-LSBF"
  override val bitWidthOfACodeUnit = 2
  override val decodeString = "0123"
  override val replacementCharCode = 0x0
  override val requiredBitOrder = BitOrder.LeastSignificantBitFirst
} with BitsCharsetNonByteSize

final class BitsCharsetBase4LSBFCompiler
  extends CharsetCompiler("X-DFDL-BASE4-LSBF") {

  override def compileCharset() = {
    new BitsCharsetBase4LSBFTransformerFactory(name)
  }
}

class BitsCharsetBase4LSBFTransformerFactory(name: String)
    extends BitsCharsetFactory {

  override def newInstance()= {
    BitsCharsetBase4LSBF
  }
}

object BitsCharsetBase4MSBF extends {
  override val name = "X-DFDL-BASE4-MSBF"
  override val bitWidthOfACodeUnit = 2
  override val decodeString = "0123"
  override val replacementCharCode = 0x0
  override val requiredBitOrder = BitOrder.MostSignificantBitFirst
} with BitsCharsetNonByteSize

final class BitsCharsetBase4MSBFCompiler
  extends CharsetCompiler("X-DFDL-BASE4-MSBF") {

  override def compileCharset() = {
    new BitsCharsetBase4MSBFTransformerFactory(name)
  }
}

class BitsCharsetBase4MSBFTransformerFactory(name: String)
    extends BitsCharsetFactory {

  override def newInstance()= {
    BitsCharsetBase4MSBF
  }
}
