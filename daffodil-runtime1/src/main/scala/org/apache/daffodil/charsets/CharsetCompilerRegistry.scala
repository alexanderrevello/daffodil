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
package org.apache.daffodil.charsets

import org.apache.daffodil.exceptions.ThrowsSDE
import org.apache.daffodil.util.SimpleNamedServiceLoader
import org.apache.daffodil.processors.charset.CharsetUtils


/**
 * Transformers have factories. This lets you find the transformer factory
 * by the name obtained from dfdlx:layerTransform.
 */
object CharsetCompilerRegistry {

  private lazy val charsetCompilerMap: Map[String, CharsetCompiler] =
    SimpleNamedServiceLoader.loadClass[CharsetCompiler](classOf[CharsetCompiler])

  /**
   * Given name, finds the factory for the transformer. SDE otherwise.
   *
   * The state is passed in order to provide diagnostic context if not found.
   */
  def find(name: String, context: ThrowsSDE): CharsetCompiler = {
    val maybeCompiler: Option[CharsetCompiler] = charsetCompilerMap.get(name)
    if (maybeCompiler.isEmpty) {
      val choices = charsetCompilerMap.keySet.mkString(", ")
      context.SDE("The charset '%s' was not found. Available choices are: %s", name, choices + ", " + CharsetUtils.supportedEncodingsString)
    } else {
      maybeCompiler.get
    }
  }
}