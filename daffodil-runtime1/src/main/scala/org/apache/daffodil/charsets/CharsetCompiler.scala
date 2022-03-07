
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

/**
 * Must be implemented by all charsets.
 *
 * These are the classes which must be dynamically loaded in order to add a charset implementation
 * to Daffodil.
 *
 * These instances are NOT serialized as part of a saved processor. The compileCharset method
 * is called and the resulting CharsetTransformerFactory is the serialized object.
 */
abstract class CharsetCompiler(nom: String) {

  def name() = nom

  def compileCharset(): CharsetTransformerFactory

}
