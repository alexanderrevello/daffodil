package daffodil.dsom

import scala.xml._
import scala.xml.parsing._
import daffodil.exceptions._
import daffodil.schema.annotation.props.gen._
import java.io.ByteArrayInputStream
import java.io.InputStream
import scala.collection.JavaConversions._

/**
 * Base class for any DFDL annotation
 */
abstract class DFDLAnnotation(node : Node, annotatedSC : AnnotatedMixin) {
  lazy val xml = node
  def getPropertyOption(name: String) = {
    val a = xml.attribute(name)
    a match {
      case None => None
      case Some(ns) => Some(ns.text)
    }
  }
}

/**
 * Base class for annotations that carry format properties
 */
abstract class DFDLFormatAnnotation(node : Node, annotatedSC : AnnotatedMixin) 
extends DFDLAnnotation (node, annotatedSC)

/**
 * Base class for assertions, variable assignments, etc
 */
abstract class DFDLStatement(node : Node, annotatedSC : AnnotatedMixin) 
extends DFDLAnnotation (node, annotatedSC)

class DFDLFormat(node : Node, sd: SchemaDocument)
  extends DFDLFormatAnnotation(node, sd) with Format_AnnotationMixin {
}

class DFDLElement(node : Node, decl: AnnotatedElementMixin)
  extends DFDLFormatAnnotation(node, decl) with Element_AnnotationMixin {
}

class DFDLGroup(node : Node, decl: AnnotatedMixin)
  extends DFDLFormatAnnotation(node, decl) with Group_AnnotationMixin {
}

class DFDLSequence(node : Node, decl: AnnotatedMixin)
  extends DFDLFormatAnnotation(node, decl) with Sequence_AnnotationMixin {
}

class DFDLChoice(node : Node, decl: AnnotatedMixin)
  extends DFDLFormatAnnotation(node, decl) with Choice_AnnotationMixin {
}

class DFDLSimpleType(node : Node, decl: AnnotatedMixin)
  extends DFDLFormatAnnotation(node, decl) with SimpleType_AnnotationMixin {
}

class DFDLDefineFormat(node : Node, decl: AnnotatedMixin)
  extends DFDLFormatAnnotation(node, decl) with DefineFormat_AnnotationMixin {
}

class DFDLEscapeScheme(node : Node, decl: AnnotatedMixin)
  extends DFDLFormatAnnotation(node, decl) with EscapeScheme_AnnotationMixin {
}

class DFDLDefineEscapeScheme(node : Node, decl: AnnotatedMixin)
  extends DFDLFormatAnnotation(node, decl) with DefineEscapeScheme_AnnotationMixin {
}

class DFDLAssert(node : Node, decl: AnnotatedMixin)
  extends DFDLStatement(node, decl) with Assert_AnnotationMixin {
}

class DFDLDiscriminator(node : Node, decl: AnnotatedMixin)
  extends DFDLStatement(node, decl) with Discriminator_AnnotationMixin {
}

class DFDLDefineVariable(node : Node, decl: AnnotatedMixin)
  extends DFDLStatement(node, decl) with DefineVariable_AnnotationMixin {
}

class DFDLNewVariableInstance(node : Node, decl: AnnotatedMixin)
  extends DFDLStatement(node, decl) with NewVariableInstance_AnnotationMixin {
}

class DFDLSetVariable(node : Node, decl: AnnotatedMixin)
  extends DFDLStatement(node, decl) with SetVariable_AnnotationMixin {
}


