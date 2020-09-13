package com.yokedox

import javax.lang.model.element.ModuleElement

fun toJson(directive: ModuleElement.Directive): JsonValue {
  val base = mutableMapOf(
    "kind" to toJson(directive.kind)
  )
  base.putAll(JsonModuleDirectiveVisitor().visit(directive))
  return JsonValue(base)
}

fun toJson(kind: ModuleElement.DirectiveKind): JsonValue {
  return JsonValue(kind.name)
}

class JsonModuleDirectiveVisitor : ModuleElement.DirectiveVisitor<JsonObject, Void> {
  override fun visitRequires(d: ModuleElement.RequiresDirective, p: Void?): JsonObject {
    return mapOf(
      "dependency" to toJson(d.dependency),
      "isStatic" to toJson(d.isStatic),
      "isTransitive" to toJson(d.isTransitive)
    )
  }

  override fun visitExports(d: ModuleElement.ExportsDirective, p: Void?): JsonObject {
    return mapOf(
      "package" to toJson(d.`package`),
      "targetModules" to toJson(d.targetModules)
    )
  }

  override fun visitOpens(d: ModuleElement.OpensDirective, p: Void?): JsonObject {
    return mapOf(
      "package" to toJson(d.`package`),
      "targetModules" to toJson(d.targetModules)
    )
  }

  override fun visitUses(d: ModuleElement.UsesDirective, p: Void?): JsonObject {
    return mapOf(
      "service" to toJson(d.service)
    )
  }

  override fun visitProvides(d: ModuleElement.ProvidesDirective, p: Void?): JsonObject {
    return mapOf(
      "implementations" to toJson(d.implementations),
      "service" to toJson(d.service)
    )
  }
}