'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

const sensibles = require('./shared/vue-types.db27fa20.cjs');

var __defProp = Object.defineProperty;
var __defNormalProp = (obj, key, value) => key in obj ? __defProp(obj, key, { enumerable: true, configurable: true, writable: true, value }) : obj[key] = value;
var __publicField = (obj, key, value) => {
  __defNormalProp(obj, typeof key !== "symbol" ? key + "" : key, value);
  return value;
};
const dfn = Object.defineProperty;
const isArray = Array.isArray || function(value) {
  return Object.prototype.toString.call(value) === "[object Array]";
};
function deepClone(input) {
  if ("structuredClone" in globalThis) {
    return structuredClone(input);
  }
  if (Array.isArray(input)) {
    return [...input];
  }
  if (sensibles.isPlainObject(input)) {
    return Object.assign({}, input);
  }
  return input;
}
function type(name, props = {}, validable = false) {
  const descriptors = {
    _vueTypes_name: {
      value: name,
      writable: true
    },
    def: {
      value(v) {
        const t = this;
        if (v === void 0) {
          if ("default" in this) {
            delete this.default;
          }
          return this;
        }
        if (isArray(v)) {
          t.default = () => deepClone(v);
        } else if (sensibles.isPlainObject(v)) {
          t.default = () => deepClone(v);
        } else {
          t.default = v;
        }
        return t;
      }
    },
    isRequired: {
      get() {
        this.required = true;
        return this;
      }
    }
  };
  if (validable) {
    descriptors.validate = {
      // eslint-disable-next-line @typescript-eslint/no-empty-function
      value() {
      }
    };
  }
  if (!props.validator) {
    props.validator = () => true;
  }
  return Object.defineProperties(props, descriptors);
}
const any = () => type("any", {}, true);
const func = () => type("func", { type: Function }, true);
const bool = () => type("bool", { type: Boolean }, true);
const string = () => type("string", { type: String }, true);
const number = () => type("number", { type: Number }, true);
const array = () => type("array", { type: Array }, true);
const object = () => type("object", { type: Object }, true);
const symbol = () => type("symbol");
const integer = () => type("integer", { type: Number });
const oneOf = (_a) => type("oneOf");
const custom = (_a) => type("custom");
const instanceOf = (Constr) => type("instanceOf", { type: Constr });
const oneOfType = (_a) => type("oneOfType");
const arrayOf = (_a) => type("arrayOf", { type: Array });
const objectOf = (_a) => type("objectOf", { type: Object });
const shape = (_a) => dfn(type("shape", { type: Object }), "loose", {
  get() {
    return this;
  }
});
const nullable = () => ({
  type: null
});
function createValidator(root, name, props, getter = false, validable = false) {
  const prop = getter ? "get" : "value";
  const descr = {
    [prop]: () => type(name, Object.assign({}, props), validable).def(
      getter ? root.defaults[name] : void 0
    )
  };
  return dfn(root, name, descr);
}
function fromType(name, source, props = {}) {
  const t = type(name, Object.assign({}, source, props), !!source.validable);
  t.validator && delete t.validator;
  return t;
}
const toValidableType = (name, props) => type(name, props, true);
const toType = (name, props) => type(name, props);
const BaseVueTypes = /* @__PURE__ */ (() => {
  var _a;
  return (
    // eslint-disable-next-line @typescript-eslint/no-extraneous-class
    _a = class {
      static get any() {
        return any();
      }
      static get func() {
        return func().def(this.defaults.func);
      }
      static get bool() {
        return bool().def(this.defaults.bool);
      }
      static get string() {
        return string().def(this.defaults.string);
      }
      static get number() {
        return number().def(this.defaults.number);
      }
      static get array() {
        return array().def(this.defaults.array);
      }
      static get object() {
        return object().def(this.defaults.object);
      }
      static get symbol() {
        return symbol();
      }
      static get integer() {
        return integer().def(this.defaults.integer);
      }
      static get nullable() {
        return nullable();
      }
      static extend(props) {
        if (isArray(props)) {
          props.forEach((p) => this.extend(p));
          return this;
        }
        const { name, validate, getter = false, type: type2 = null } = props;
        const extType = sensibles.isPlainObject(type2) && type2.type ? null : type2;
        return createValidator(this, name, { type: extType }, getter, !!validate);
      }
    }, __publicField(_a, "defaults", {}), __publicField(_a, "sensibleDefaults"), __publicField(_a, "config", sensibles.config), __publicField(_a, "oneOf", oneOf), __publicField(_a, "custom", custom), __publicField(_a, "instanceOf", instanceOf), __publicField(_a, "oneOfType", oneOfType), __publicField(_a, "arrayOf", arrayOf), __publicField(_a, "objectOf", objectOf), __publicField(_a, "shape", shape), __publicField(_a, "utils", {
      toType: type,
      validate: (...args) => !!args
    }), _a
  );
})();
function createTypes(defs = sensibles.typeDefaults()) {
  var _a;
  return _a = class extends BaseVueTypes {
    static get sensibleDefaults() {
      return Object.assign({}, this.defaults);
    }
    static set sensibleDefaults(v) {
      if (v === false) {
        this.defaults = {};
        return;
      }
      if (v === true) {
        this.defaults = Object.assign({}, defs);
        return;
      }
      this.defaults = Object.assign({}, v);
    }
  }, __publicField(_a, "defaults", Object.assign({}, defs)), _a;
}
function validateType(_type, _value, _silent = false) {
  return true;
}
if (process.env.NODE_ENV !== "production") {
  sensibles.config.silent === false && console.warn(
    "You are using the production shimmed version of VueTypes in a development build. Refer to https://dwightjack.github.io/vue-types/guide/installation.html#production-build to learn how to configure VueTypes for usage in multiple environments."
  );
}
class VueTypes extends createTypes() {
}

exports.config = sensibles.config;
exports.any = any;
exports.array = array;
exports.arrayOf = arrayOf;
exports.bool = bool;
exports.createTypes = createTypes;
exports.custom = custom;
exports.default = VueTypes;
exports.fromType = fromType;
exports.func = func;
exports.instanceOf = instanceOf;
exports.integer = integer;
exports.nullable = nullable;
exports.number = number;
exports.object = object;
exports.objectOf = objectOf;
exports.oneOf = oneOf;
exports.oneOfType = oneOfType;
exports.shape = shape;
exports.string = string;
exports.symbol = symbol;
exports.toType = toType;
exports.toValidableType = toValidableType;
exports.validateType = validateType;
//# sourceMappingURL=shim.cjs.map
