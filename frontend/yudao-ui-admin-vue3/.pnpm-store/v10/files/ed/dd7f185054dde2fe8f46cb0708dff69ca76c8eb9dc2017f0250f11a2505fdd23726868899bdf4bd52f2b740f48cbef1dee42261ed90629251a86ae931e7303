'use strict';

const config = {
  silent: false,
  logLevel: "warn"
};

function isPlainObject(value) {
  if (typeof value !== "object" || value === null) {
    return false;
  }
  const prototype = Object.getPrototypeOf(value);
  return (prototype === null || prototype === Object.prototype || Object.getPrototypeOf(prototype) === null) && !(Symbol.toStringTag in value) && !(Symbol.iterator in value);
}

const typeDefaults = () => ({
  func: () => void 0,
  bool: true,
  string: "",
  number: 0,
  array: () => [],
  object: () => ({}),
  integer: 0
});

exports.config = config;
exports.isPlainObject = isPlainObject;
exports.typeDefaults = typeDefaults;
//# sourceMappingURL=vue-types.db27fa20.cjs.map
