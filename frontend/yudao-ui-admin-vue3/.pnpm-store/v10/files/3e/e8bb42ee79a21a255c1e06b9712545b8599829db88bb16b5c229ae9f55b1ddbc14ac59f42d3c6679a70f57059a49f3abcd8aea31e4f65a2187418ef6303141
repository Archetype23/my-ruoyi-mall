/*!
 * @form-create/core v3.2.37
 * (c) 2018-2026 xaboy
 * Github https://github.com/xaboy/form-create
 * Released under the MIT License.
 */
import { isVNode, defineComponent, getCurrentInstance, provide, inject, toRefs, reactive, onBeforeMount, watchEffect, onMounted, onBeforeUnmount, onUpdated, watch, nextTick, markRaw, computed, toRef, createVNode, resolveComponent, withDirectives, resolveDirective, createApp, h, ref } from 'vue';

function ownKeys(object, enumerableOnly) {
  var keys = Object.keys(object);

  if (Object.getOwnPropertySymbols) {
    var symbols = Object.getOwnPropertySymbols(object);

    if (enumerableOnly) {
      symbols = symbols.filter(function (sym) {
        return Object.getOwnPropertyDescriptor(object, sym).enumerable;
      });
    }

    keys.push.apply(keys, symbols);
  }

  return keys;
}

function _objectSpread2(target) {
  for (var i = 1; i < arguments.length; i++) {
    var source = arguments[i] != null ? arguments[i] : {};

    if (i % 2) {
      ownKeys(Object(source), true).forEach(function (key) {
        _defineProperty(target, key, source[key]);
      });
    } else if (Object.getOwnPropertyDescriptors) {
      Object.defineProperties(target, Object.getOwnPropertyDescriptors(source));
    } else {
      ownKeys(Object(source)).forEach(function (key) {
        Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key));
      });
    }
  }

  return target;
}

function _typeof(obj) {
  "@babel/helpers - typeof";

  if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") {
    _typeof = function (obj) {
      return typeof obj;
    };
  } else {
    _typeof = function (obj) {
      return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
    };
  }

  return _typeof(obj);
}

function _classCallCheck(instance, Constructor) {
  if (!(instance instanceof Constructor)) {
    throw new TypeError("Cannot call a class as a function");
  }
}

function _defineProperty(obj, key, value) {
  if (key in obj) {
    Object.defineProperty(obj, key, {
      value: value,
      enumerable: true,
      configurable: true,
      writable: true
    });
  } else {
    obj[key] = value;
  }

  return obj;
}

function _inherits(subClass, superClass) {
  if (typeof superClass !== "function" && superClass !== null) {
    throw new TypeError("Super expression must either be null or a function");
  }

  subClass.prototype = Object.create(superClass && superClass.prototype, {
    constructor: {
      value: subClass,
      writable: true,
      configurable: true
    }
  });
  if (superClass) _setPrototypeOf(subClass, superClass);
}

function _getPrototypeOf(o) {
  _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf : function _getPrototypeOf(o) {
    return o.__proto__ || Object.getPrototypeOf(o);
  };
  return _getPrototypeOf(o);
}

function _setPrototypeOf(o, p) {
  _setPrototypeOf = Object.setPrototypeOf || function _setPrototypeOf(o, p) {
    o.__proto__ = p;
    return o;
  };

  return _setPrototypeOf(o, p);
}

function _isNativeReflectConstruct() {
  if (typeof Reflect === "undefined" || !Reflect.construct) return false;
  if (Reflect.construct.sham) return false;
  if (typeof Proxy === "function") return true;

  try {
    Boolean.prototype.valueOf.call(Reflect.construct(Boolean, [], function () {}));
    return true;
  } catch (e) {
    return false;
  }
}

function _assertThisInitialized(self) {
  if (self === void 0) {
    throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
  }

  return self;
}

function _possibleConstructorReturn(self, call) {
  if (call && (typeof call === "object" || typeof call === "function")) {
    return call;
  } else if (call !== void 0) {
    throw new TypeError("Derived constructors may only return object or undefined");
  }

  return _assertThisInitialized(self);
}

function _createSuper(Derived) {
  var hasNativeReflectConstruct = _isNativeReflectConstruct();

  return function _createSuperInternal() {
    var Super = _getPrototypeOf(Derived),
        result;

    if (hasNativeReflectConstruct) {
      var NewTarget = _getPrototypeOf(this).constructor;

      result = Reflect.construct(Super, arguments, NewTarget);
    } else {
      result = Super.apply(this, arguments);
    }

    return _possibleConstructorReturn(this, result);
  };
}

function _slicedToArray(arr, i) {
  return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _unsupportedIterableToArray(arr, i) || _nonIterableRest();
}

function _toConsumableArray(arr) {
  return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _unsupportedIterableToArray(arr) || _nonIterableSpread();
}

function _arrayWithoutHoles(arr) {
  if (Array.isArray(arr)) return _arrayLikeToArray(arr);
}

function _arrayWithHoles(arr) {
  if (Array.isArray(arr)) return arr;
}

function _iterableToArray(iter) {
  if (typeof Symbol !== "undefined" && iter[Symbol.iterator] != null || iter["@@iterator"] != null) return Array.from(iter);
}

function _iterableToArrayLimit(arr, i) {
  var _i = arr == null ? null : typeof Symbol !== "undefined" && arr[Symbol.iterator] || arr["@@iterator"];

  if (_i == null) return;
  var _arr = [];
  var _n = true;
  var _d = false;

  var _s, _e;

  try {
    for (_i = _i.call(arr); !(_n = (_s = _i.next()).done); _n = true) {
      _arr.push(_s.value);

      if (i && _arr.length === i) break;
    }
  } catch (err) {
    _d = true;
    _e = err;
  } finally {
    try {
      if (!_n && _i["return"] != null) _i["return"]();
    } finally {
      if (_d) throw _e;
    }
  }

  return _arr;
}

function _unsupportedIterableToArray(o, minLen) {
  if (!o) return;
  if (typeof o === "string") return _arrayLikeToArray(o, minLen);
  var n = Object.prototype.toString.call(o).slice(8, -1);
  if (n === "Object" && o.constructor) n = o.constructor.name;
  if (n === "Map" || n === "Set") return Array.from(o);
  if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)) return _arrayLikeToArray(o, minLen);
}

function _arrayLikeToArray(arr, len) {
  if (len == null || len > arr.length) len = arr.length;

  for (var i = 0, arr2 = new Array(len); i < len; i++) arr2[i] = arr[i];

  return arr2;
}

function _nonIterableSpread() {
  throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.");
}

function _nonIterableRest() {
  throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.");
}

function toArray(value) {
  return Array.isArray(value) ? value : [null, undefined, ''].indexOf(value) > -1 ? [] : [value];
}

function debounce(fn, wait) {
  var timeout = null;
  return function () {
    var _this = this;

    for (var _len = arguments.length, arg = new Array(_len), _key = 0; _key < _len; _key++) {
      arg[_key] = arguments[_key];
    }

    if (timeout !== null) clearTimeout(timeout);
    timeout = setTimeout(function () {
      return fn.call.apply(fn, [_this].concat(arg));
    }, wait);
  };
}

function toLine(name) {
  var line = name.replace(/([A-Z])/g, '-$1').toLocaleLowerCase();
  if (line.indexOf('-') === 0) line = line.substr(1);
  return line;
}
function upper(str) {
  return str.replace(str[0], str[0].toLocaleUpperCase());
}

function $set(target, field, value) {
  target[field] = value;
}
function $del(target, field) {
  delete target[field];
}

var is = {
  type: function type(arg, _type) {
    return Object.prototype.toString.call(arg) === '[object ' + _type + ']';
  },
  Undef: function Undef(v) {
    return v === undefined || v === null;
  },
  Element: function Element(arg) {
    return _typeof(arg) === 'object' && arg !== null && arg.nodeType === 1 && !is.Object(arg);
  },
  trueArray: function trueArray(data) {
    return Array.isArray(data) && data.length > 0;
  },
  Function: function Function(v) {
    var type = this.getType(v);
    return type === 'Function' || type === 'AsyncFunction';
  },
  getType: function getType(v) {
    var str = Object.prototype.toString.call(v);
    return /^\[object (.*)\]$/.exec(str)[1];
  },
  empty: function empty(value) {
    if (value === undefined || value === null) {
      return true;
    }

    if (Array.isArray(value) && Array.isArray(value) && !value.length) {
      return true;
    }

    return typeof value === 'string' && !value;
  }
};
['Date', 'Object', 'String', 'Boolean', 'Array', 'Number'].forEach(function (t) {
  is[t] = function (arg) {
    return is.type(arg, t);
  };
});
function hasProperty(rule, k) {
  return {}.hasOwnProperty.call(rule, k);
}

function deepExtend(origin) {
  var target = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};
  var mode = arguments.length > 2 ? arguments[2] : undefined;
  var isArr = false;

  for (var key in target) {
    if (Object.prototype.hasOwnProperty.call(target, key)) {
      var clone = target[key];

      if ((isArr = Array.isArray(clone)) || is.Object(clone)) {
        var nst = origin[key] === undefined;

        if (isArr) {
          isArr = false;
          nst && $set(origin, key, []);
        } else if (clone._clone && mode !== undefined) {
          if (mode) {
            clone = clone.getRule();
            nst && $set(origin, key, {});
          } else {
            $set(origin, key, clone._clone());
            continue;
          }
        } else {
          nst && $set(origin, key, {});
        }

        origin[key] = deepExtend(origin[key], clone, mode);
      } else {
        $set(origin, key, clone);

        if (!is.Undef(clone)) {
          if (!is.Undef(clone.__json)) {
            origin[key].__json = clone.__json;
          }

          if (!is.Undef(clone.__origin)) {
            origin[key].__origin = clone.__origin;
          }
        }
      }
    }
  }

  return mode !== undefined && Array.isArray(origin) ? origin.filter(function (v) {
    return !v || !v.__ctrl;
  }) : origin;
}
function deepCopy(value) {
  return deepExtend({}, {
    value: value
  }).value;
}

var normalMerge = ['props'];
var toArrayMerge = ['class', 'style', 'directives'];
var functionalMerge = ['on', 'hook'];

var mergeProps = function mergeProps(objects) {
  var initial = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};
  var opt = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : {};

  var _normalMerge = [].concat(normalMerge, _toConsumableArray(opt['normal'] || []));

  var _toArrayMerge = [].concat(toArrayMerge, _toConsumableArray(opt['array'] || []));

  var _functionalMerge = [].concat(functionalMerge, _toConsumableArray(opt['functional'] || []));

  var propsMerge = opt['props'] || [];
  return objects.reduce(function (a, b) {
    for (var key in b) {
      if (a[key]) {
        if (propsMerge.indexOf(key) > -1) {
          a[key] = mergeProps([b[key]], a[key]);
        } else if (_normalMerge.indexOf(key) > -1) {
          a[key] = _objectSpread2(_objectSpread2({}, a[key]), b[key]);
        } else if (_toArrayMerge.indexOf(key) > -1) {
          var arrA = a[key] instanceof Array ? a[key] : [a[key]];
          var arrB = b[key] instanceof Array ? b[key] : [b[key]];
          a[key] = [].concat(_toConsumableArray(arrA), _toConsumableArray(arrB));
        } else if (_functionalMerge.indexOf(key) > -1) {
          for (var event in b[key]) {
            if (a[key][event]) {
              var _arrA = a[key][event] instanceof Array ? a[key][event] : [a[key][event]];

              var _arrB = b[key][event] instanceof Array ? b[key][event] : [b[key][event]];

              a[key][event] = [].concat(_toConsumableArray(_arrA), _toConsumableArray(_arrB));
            } else {
              a[key][event] = b[key][event];
            }
          }
        } else if (key === 'hook') {
          for (var hook in b[key]) {
            if (a[key][hook]) {
              a[key][hook] = mergeFn(a[key][hook], b[key][hook]);
            } else {
              a[key][hook] = b[key][hook];
            }
          }
        } else {
          a[key] = b[key];
        }
      } else {
        if (_normalMerge.indexOf(key) > -1 || _functionalMerge.indexOf(key) > -1 || propsMerge.indexOf(key) > -1) {
          a[key] = _objectSpread2({}, b[key]);
        } else if (_toArrayMerge.indexOf(key) > -1) {
          a[key] = b[key] instanceof Array ? _toConsumableArray(b[key]) : _typeof(b[key]) === 'object' ? _objectSpread2({}, b[key]) : b[key];
        } else a[key] = b[key];
      }
    }

    return a;
  }, initial);
};

var mergeFn = function mergeFn(fn1, fn2) {
  return function () {
    fn1 && fn1.apply(this, arguments);
    fn2 && fn2.apply(this, arguments);
  };
};

var keyAttrs = ['type', 'slot', 'ignore', 'emitPrefix', 'value', 'name', 'native', 'hidden', 'display', 'inject', 'options', 'emit', 'link', 'prefix', 'suffix', 'update', 'sync', 'optionsTo', 'key', 'slotUpdate', 'computed', 'preview', 'component', 'cache', 'modelEmit'];
var arrayAttrs = ['validate', 'children', 'control'];
var normalAttrs = ['effect', 'deep', 'renderSlots'];
function attrs() {
  return [].concat(keyAttrs, _toConsumableArray(normalMerge), _toConsumableArray(toArrayMerge), _toConsumableArray(functionalMerge), arrayAttrs, normalAttrs);
}

function format(type, msg, rule) {
  return "[form-create ".concat(type, "]: ").concat(msg) + (rule ? '\n\nrule: ' + JSON.stringify(rule.getRule ? rule.getRule() : rule) : '');
}
function err(msg, rule) {
  console.error(format('err', msg, rule));
}
function logError(e) {
  err(e.toString());
  console.error(e);
}

function toCase(str) {
  var to = str.replace(/(-[a-z])/g, function (v) {
    return v.replace('-', '').toLocaleUpperCase();
  });
  return lower(to);
}
function lower(str) {
  return str.replace(str[0], str[0].toLowerCase());
}

var PREFIX = '[[FORM-CREATE-PREFIX-';
var SUFFIX = '-FORM-CREATE-SUFFIX]]';
function toJson(obj, space) {
  return JSON.stringify(deepExtend(Array.isArray(obj) ? [] : {}, obj, true), function (key, val) {
    if (val && val._isVue === true) return undefined;

    if (typeof val !== 'function') {
      return val;
    }

    if (val.__json) {
      return val.__json;
    }

    if (val.__origin) val = val.__origin;
    if (val.__emit) return undefined;
    return PREFIX + val + SUFFIX;
  }, space);
}

function makeFn(fn) {
  return new Function('return ' + fn)();
}

function parseFn(fn, mode) {
  if (fn && is.String(fn) && fn.length > 4) {
    var v = fn.trim();
    var flag = false;

    try {
      if (v.indexOf(SUFFIX) > 0 && v.indexOf(PREFIX) === 0) {
        v = v.replace(SUFFIX, '').replace(PREFIX, '');
        flag = true;
      } else if (v.indexOf('$FN:') === 0) {
        v = v.substring(4);
        flag = true;
      } else if (v.indexOf('$EXEC:') === 0) {
        v = v.substring(6);
        flag = true;
      } else if (v.indexOf('$GLOBAL:') === 0) {
        var name = v.substring(8);

        v = function v() {
          for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
            args[_key] = arguments[_key];
          }

          var callback = args[0].api.getGlobalEvent(name);

          if (callback) {
            return callback.call.apply(callback, [this].concat(args));
          }

          return undefined;
        };

        v.__json = fn;
        v.__inject = true;
        return v;
      } else if (v.indexOf('$FNX:') === 0) {
        v = makeFn('function($inject){\n' + v.substring(5) + '\n}');
        v.__json = fn;
        v.__inject = true;
        return v;
      } else if (!mode && v.indexOf('function ') === 0 && v !== 'function ') {
        flag = true;
      } else if (!mode && v.indexOf('function(') === 0 && v !== 'function(') {
        flag = true;
      }

      if (!flag) return fn;
      var val;

      try {
        val = makeFn(v);
      } catch (e) {
        val = makeFn('function ' + v);
      }

      val.__json = fn;
      return val;
    } catch (e) {
      err("\u89E3\u6790\u5931\u8D25:".concat(v, "\n\nerr: ").concat(e));
      return undefined;
    }
  }

  return fn;
}
function parseJson(json, mode) {
  return JSON.parse(json, function (k, v) {
    if (is.Undef(v) || !v.indexOf) return v;
    return parseFn(v, mode);
  });
}

function enumerable(value, writable) {
  return {
    value: value,
    enumerable: false,
    configurable: false,
    writable: !!writable
  };
} //todo 优化位置

function copyRule(rule, mode) {
  return copyRules([rule], mode || false)[0];
}
function copyRules(rules, mode) {
  return deepExtend([], _toConsumableArray(rules), mode || false);
}
function mergeRule(rule, merge) {
  mergeProps(Array.isArray(merge) ? merge : [merge], rule, {
    array: arrayAttrs,
    normal: normalAttrs
  });
  return rule;
}
function getRule(rule) {
  var r = is.Function(rule.getRule) ? rule.getRule() : rule;

  if (!r.type) {
    r.type = 'input';
  }

  return r;
}
function mergeGlobal(target, merge) {
  if (!target) return merge;
  Object.keys(merge || {}).forEach(function (k) {
    if (merge[k]) {
      target[k] = mergeRule(target[k] || {}, merge[k]);
    }
  });
  return target;
}
function funcProxy(that, proxy) {
  Object.defineProperties(that, Object.keys(proxy).reduce(function (initial, k) {
    initial[k] = {
      get: function get() {
        return proxy[k]();
      }
    };
    return initial;
  }, {}));
}
function byCtx(rule) {
  return rule.__fc__ || (rule.__origin__ ? rule.__origin__.__fc__ : null);
}
function invoke(fn, def) {
  try {
    def = fn();
  } catch (e) {
    logError(e);
  }

  return def;
}
function makeSlotBag() {
  var slotBag = {};

  var slotName = function slotName(n) {
    return n || 'default';
  };

  return {
    setSlot: function setSlot(slot, vnFn) {
      slot = slotName(slot);
      if (!vnFn || Array.isArray(vnFn) && vnFn.length) return;
      if (!slotBag[slot]) slotBag[slot] = [];
      slotBag[slot].push(vnFn);
    },
    getSlot: function getSlot(slot, val) {
      slot = slotName(slot);
      var children = [];
      (slotBag[slot] || []).forEach(function (fn) {
        if (Array.isArray(fn)) {
          children.push.apply(children, _toConsumableArray(fn));
        } else if (is.Function(fn)) {
          var res = fn.apply(void 0, _toConsumableArray(val || []));

          if (Array.isArray(res)) {
            children.push.apply(children, _toConsumableArray(res));
          } else {
            children.push(res);
          }
        } else if (!is.Undef(fn)) {
          children.push(fn);
        }
      });
      return children;
    },
    getSlots: function getSlots() {
      var _this = this;

      var slots = {};
      Object.keys(slotBag).forEach(function (k) {
        slots[k] = function () {
          for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
            args[_key] = arguments[_key];
          }

          return _this.getSlot(k, args);
        };
      });
      return slots;
    },
    slotLen: function slotLen(slot) {
      slot = slotName(slot);
      return slotBag[slot] ? slotBag[slot].length : 0;
    },
    mergeBag: function mergeBag(bag) {
      var _this2 = this;

      if (!bag) return this;
      var slots = is.Function(bag.getSlots) ? bag.getSlots() : bag;

      if (Array.isArray(bag) || isVNode(bag)) {
        this.setSlot(undefined, function () {
          return bag;
        });
      } else {
        Object.keys(slots).forEach(function (k) {
          _this2.setSlot(k, slots[k]);
        });
      }

      return this;
    }
  };
}
function toProps(rule) {
  var prop = _objectSpread2({}, rule.props || {});

  Object.keys(rule.on || {}).forEach(function (k) {
    if (k.indexOf('-') > 0) {
      k = toCase(k);
    }

    var name = "on".concat(upper(k));

    if (Array.isArray(prop[name])) {
      prop[name] = [].concat(_toConsumableArray(prop[name]), [rule.on[k]]);
    } else if (prop[name]) {
      prop[name] = [prop[name], rule.on[k]];
    } else {
      prop[name] = rule.on[k];
    }
  });
  prop.key = rule.key;
  prop.ref = rule.ref;
  prop["class"] = rule["class"];
  prop.id = rule.id;
  prop.style = rule.style;
  if (prop.slot) delete prop.slot;
  return prop;
}
function setPrototypeOf(o, proto) {
  Object.setPrototypeOf(o, proto);
  return o;
}

var changeType = function changeType(a, b) {
  if (typeof a === 'string') {
    return String(b);
  } else if (typeof a === 'number') {
    return Number(b);
  }

  return b;
};

var condition = {
  '==': function _(a, b) {
    return JSON.stringify(a) === JSON.stringify(changeType(a, b));
  },
  '!=': function _(a, b) {
    return !condition['=='](a, b);
  },
  '>': function _(a, b) {
    return a > b;
  },
  '>=': function _(a, b) {
    return a >= b;
  },
  '<': function _(a, b) {
    return a < b;
  },
  '<=': function _(a, b) {
    return a <= b;
  },
  on: function on(a, b) {
    return a && a.indexOf && a.indexOf(changeType(a[0], b)) > -1;
  },
  notOn: function notOn(a, b) {
    return !condition.on(a, b);
  },
  "in": function _in(a, b) {
    return b && b.indexOf && b.indexOf(a) > -1;
  },
  notIn: function notIn(a, b) {
    return !condition["in"](a, b);
  },
  between: function between(a, b) {
    return a > b[0] && a < b[1];
  },
  notBetween: function notBetween(a, b) {
    return a < b[0] || a > b[1];
  },
  empty: function empty(a) {
    return is.empty(a);
  },
  notEmpty: function notEmpty(a) {
    return !is.empty(a);
  },
  pattern: function pattern(a, b) {
    return new RegExp(b, 'g').test(a);
  }
};
function deepGet(val, split) {
  (Array.isArray(split) ? split : (split || '').split('.')).forEach(function (k) {
    if (val != null) {
      val = val[k];
    }
  });
  return val;
}
function extractVar(str) {
  var regex = /{{\s*(.*?)\s*}}/g;
  var match;
  var matches = {};

  while ((match = regex.exec(str)) !== null) {
    if (match[1]) {
      matches[match[1]] = true;
    }
  }

  return Object.keys(matches);
}
function convertFieldToConditions(field) {
  var parts = field.split('.');
  var conditions = [];
  var currentCondition = '';
  parts.forEach(function (part, index) {
    if (index === 0) {
      currentCondition = part;
    } else {
      currentCondition += '.' + part;
    }

    conditions.push(currentCondition);
  });
  return conditions.join(' && ');
}
function parseTemplateToTree(template) {
  var tokens = [];
  var current = '';
  var depth = 0;

  for (var i = 0; i < template.length; i++) {
    var _char = template[i];

    if (_char === '[') {
      if (depth === 0 && current) {
        tokens.push({
          type: 'key',
          value: current
        });
        current = '';
      }

      depth++;
      current += _char;
    } else if (_char === ']') {
      depth--;
      current += _char;

      if (depth === 0) {
        tokens.push({
          type: 'bracket',
          value: parseTemplateToTree(current.slice(1, -1))
        });
        current = '';
      }
    } else if (_char === '.' && depth === 0) {
      if (current) {
        tokens.push({
          type: 'key',
          value: current
        });
        current = '';
      }
    } else {
      current += _char;
    }
  }

  if (current) {
    tokens.push({
      type: 'key',
      value: current
    });
  }

  return tokens.map(function (token) {
    if (token.type === 'key') {
      return {
        key: token.value
      };
    } else {
      return {
        children: token.value
      };
    }
  });
}

var _getGroupInject = function getGroupInject(vm, parent) {
  if (!vm || vm === parent) {
    return;
  }

  if (vm.props.formCreateInject) {
    return vm.props.formCreateInject;
  }

  if (vm.parent) {
    return _getGroupInject(vm.parent, parent);
  }
};

function $FormCreate(FormCreate, components, directives) {
  return defineComponent({
    name: 'FormCreate' + (FormCreate.isMobile ? 'Mobile' : ''),
    components: components,
    directives: directives,
    props: {
      rule: {
        type: Array,
        required: true,
        "default": function _default() {
          return [];
        }
      },
      option: {
        type: Object,
        "default": function _default() {
          return {};
        }
      },
      extendOption: Boolean,
      driver: [String, Object],
      modelValue: Object,
      disabled: {
        type: Boolean,
        "default": undefined
      },
      preview: {
        type: Boolean,
        "default": undefined
      },
      index: [String, Number],
      api: Object,
      locale: [String, Object],
      t: Function,
      name: String,
      subForm: {
        type: Boolean,
        "default": true
      },
      inFor: Boolean
    },
    emits: ['update:api', 'update:modelValue', 'mounted', 'submit', 'reset', 'change', 'emit-event', 'control', 'remove-rule', 'remove-field', 'sync', 'reload', 'repeat-field', 'update', 'validate-field-fail', 'validate-fail', 'created'],
    render: function render() {
      return this.fc.render();
    },
    setup: function setup(props) {
      var vm = getCurrentInstance();
      provide('parentFC', vm);
      var parent = inject('parentFC', null);
      var top = parent;

      if (parent) {
        while (top.setupState.parent) {
          top = top.setupState.parent;
        }
      } else {
        top = vm;
      }

      var _toRefs = toRefs(props),
          rule = _toRefs.rule,
          modelValue = _toRefs.modelValue,
          subForm = _toRefs.subForm,
          inFor = _toRefs.inFor;

      var data = reactive({
        ctxInject: {},
        destroyed: false,
        isShow: true,
        unique: 1,
        renderRule: _toConsumableArray(rule.value || []),
        updateValue: JSON.stringify(modelValue.value || {})
      });
      var fc = new FormCreate(vm);
      var fapi = fc.api();
      var isMore = inFor.value;

      var addSubForm = function addSubForm() {
        if (parent) {
          var _inject = _getGroupInject(vm, parent);

          if (_inject) {
            var sub;

            if (isMore) {
              sub = toArray(_inject.getSubForm());
              sub.push(fapi);
            } else {
              sub = fapi;
            }

            _inject.subForm(sub);
          }
        }
      };

      var rmSubForm = function rmSubForm() {
        var inject = _getGroupInject(vm, parent);

        if (inject) {
          if (isMore) {
            var sub = toArray(inject.getSubForm());
            var idx = sub.indexOf(fapi);

            if (idx > -1) {
              sub.splice(idx, 1);
            }
          } else {
            inject.subForm();
          }
        }
      };

      var styleEl = null;
      onBeforeMount(function () {
        watchEffect(function () {
          var content = '';
          var globalClass = props.option && props.option.globalClass || {};
          Object.keys(globalClass).forEach(function (k) {
            var subCss = '';
            globalClass[k].style && Object.keys(globalClass[k].style).forEach(function (key) {
              subCss += toLine(key) + ':' + globalClass[k].style[key] + ';';
            });

            if (globalClass[k].content) {
              subCss += globalClass[k].content + ';';
            }

            if (subCss) {
              content += ".".concat(k, "{").concat(subCss, "}");
            }
          });

          if (props.option && props.option.style) {
            content += props.option.style;
          }

          if (!styleEl) {
            styleEl = document.createElement('style');
            styleEl.type = 'text/css';
            document.head.appendChild(styleEl);
          }

          styleEl.innerHTML = content || '';
        });
      });
      var emit$topForm = debounce(function () {
        fc.bus.$emit('$loadData.$topForm');
      }, 100);
      var emit$scopeForm = debounce(function () {
        fc.bus.$emit('$loadData.$scopeForm');
      }, 100);
      var emit$form = debounce(function () {
        fc.bus.$emit('$loadData.$form');
      }, 100);

      var emit$change = function emit$change(field) {
        fc.bus.$emit('change-$form.' + field);
      };

      onMounted(function () {
        if (parent) {
          fapi.top.bus.$on('$loadData.$form', emit$topForm);
          fapi.top.bus.$on('change', emit$change);
        }

        if (fapi !== fapi.scope) {
          fapi.scope.bus.$on('$loadData.$scopeForm', emit$scopeForm);
        }

        fc.mounted();
      });
      onBeforeUnmount(function () {
        if (parent) {
          fapi.top.bus.$off('$loadData.$form', emit$topForm);
          fapi.top.bus.$off('change', emit$change);
        }

        if (fapi !== fapi.scope) {
          fapi.scope.bus.$off('$loadData.$scopeForm', emit$scopeForm);
        }

        rmSubForm();
        data.destroyed = true;
        fc.unmount();
        styleEl && (styleEl.parentNode || styleEl.parentElement) && document.head.removeChild(styleEl);
      });
      onUpdated(function () {
        fc.updated();
      });
      addSubForm();
      watch(function () {
        return props.option;
      }, function () {
        fc.initOptions();
        fapi.refresh();
      }, {
        deep: true,
        flush: 'sync'
      });
      watch(function () {
        return _toConsumableArray(rule.value);
      }, function (n) {
        if (fc.$handle.isBreakWatch() || n.length === data.renderRule.length && n.every(function (v) {
          return data.renderRule.indexOf(v) > -1;
        })) return;
        fc.$handle.updateAppendData();
        fc.$handle.reloadRule(rule.value);
        vm.setupState.renderRule();
      });
      watch(function () {
        return [props.disabled, props.preview];
      }, function () {
        fapi.refresh();
      });
      watch(modelValue, function (n) {
        if (toJson(n || {}) === data.updateValue) return;

        if (fapi.config.forceCoverValue) {
          fapi.coverValue(n || {});
        } else {
          fapi.setValue(n || {});
        }
      }, {
        deep: true,
        flush: 'post'
      });
      watch(function () {
        return props.index;
      }, function () {
        fapi.coverValue({});
        fc.$handle.updateAppendData();
        nextTick(function () {
          nextTick(function () {
            fapi.clearValidateState();
          });
        });
      }, {
        flush: 'sync'
      });
      return _objectSpread2(_objectSpread2({
        fc: markRaw(fc),
        parent: parent ? markRaw(parent) : parent,
        top: markRaw(top),
        fapi: markRaw(fapi)
      }, toRefs(data)), {}, {
        getGroupInject: function getGroupInject() {
          return _getGroupInject(vm, parent);
        },
        refresh: function refresh() {
          ++data.unique;
        },
        renderRule: function renderRule() {
          data.renderRule = _toConsumableArray(rule.value || []);
        },
        updateValue: function updateValue(value) {
          if (data.destroyed) return;
          var json = toJson(value);

          if (data.updateValue === json) {
            return;
          }

          data.updateValue = json;
          vm.emit('update:modelValue', value);
          nextTick(function () {
            emit$form();

            if (!parent) {
              emit$topForm();
              emit$scopeForm();
            } else if (!subForm.value) {
              emit$scopeForm();
            }
          });
        }
      });
    },
    created: function created() {
      var vm = getCurrentInstance();
      vm.emit('update:api', vm.setupState.fapi);
      vm.setupState.fc.init();
    }
  });
}

var _extends = Object.assign || function (a) {
  for (var b, c = 1; c < arguments.length; c++) {
    for (var d in b = arguments[c], b) {
      Object.prototype.hasOwnProperty.call(b, d) && $set(a, d, b[d]);
    }
  }

  return a;
};

function extend() {
  return _extends.apply(this, arguments);
}
function copy$1(obj) {
  if (_typeof(obj) !== 'object' || obj === null) return obj;
  return obj instanceof Array ? _toConsumableArray(obj) : _objectSpread2({}, obj);
}

function baseRule() {
  return {
    props: {},
    on: {},
    options: [],
    children: [],
    hidden: false,
    display: true,
    value: undefined
  };
}
function creatorFactory(name, init) {
  return function (title, field, value) {
    var props = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : {};
    var maker = new Creator(name, title, field, value, props);

    if (init) {
      if (is.Function(init)) init(maker);else maker.props(init);
    }

    return maker;
  };
}
function Creator(type, title, field, value, props) {
  this._data = extend(baseRule(), {
    type: type,
    title: title,
    field: field,
    value: value,
    props: props || {}
  });
  this.event = this.on;
}
extend(Creator.prototype, {
  getRule: function getRule() {
    return this._data;
  },
  setProp: function setProp(key, value) {
    $set(this._data, key, value);
    return this;
  },
  modelField: function modelField(field) {
    this._data.modelField = field;
    return this;
  },
  _clone: function _clone() {
    var clone = new this.constructor();
    clone._data = copyRule(this._data);
    return clone;
  }
});
function appendProto(attrs) {
  attrs.forEach(function (name) {
    Creator.prototype[name] = function (key) {
      mergeRule(this._data, _defineProperty({}, name, arguments.length < 2 ? key : _defineProperty({}, key, arguments[1])));
      return this;
    };
  });
}
appendProto(attrs());

var commonMaker = creatorFactory('');
function create(type, field, title) {
  var make = commonMaker('', field);
  make._data.type = type;
  make._data.title = title;
  return make;
}
function makerFactory() {
  return {
    create: create,
    factory: creatorFactory
  };
}

function toString(val) {
  return val == null ? '' : _typeof(val) === 'object' ? JSON.stringify(val, null, 2) : String(val);
}

function isPromise(value) {
  return value && _typeof(value) === 'object' && typeof value.then === 'function';
}

function toPromise(value) {
  if (isPromise(value)) {
    return value;
  }

  return Promise.resolve(value);
}

var id$2 = 0;
function uniqueId() {
  var num = 370 + ++id$2;
  return 'F' + Math.random().toString(36).substr(3, 3) + Number("".concat(Date.now())).toString(36) + num.toString(36) + 'c';
}

function deepSet(data, idx, val) {
  var _data = data,
      to;
  (idx || '').split('.').forEach(function (v) {
    if (to) {
      if (!_data[to] || _typeof(_data[to]) != 'object') {
        _data[to] = {};
      }

      _data = _data[to];
    }

    to = v;
  });
  _data[to] = val;
  return _data;
}

function getError(action, option, xhr) {
  var msg = "fail to ".concat(action, " ").concat(xhr.status, "'");
  var err = new Error(msg);
  err.status = xhr.status;
  err.url = action;
  return err;
}

function getBody(xhr) {
  var text = xhr.responseText || xhr.response;

  if (!text) {
    return text;
  }

  try {
    return JSON.parse(text);
  } catch (e) {
    return text;
  }
}

function fetch$1(option) {
  if (typeof XMLHttpRequest === 'undefined') {
    return;
  }

  var xhr = new XMLHttpRequest();
  var action = option.action || '';

  if (xhr.upload && option.onProgress) {
    xhr.upload.addEventListener('progress', function (evt) {
      evt.percent = evt.total > 0 ? evt.loaded / evt.total * 100 : 0;
      option.onProgress(evt);
    });
  }

  if (option.query) {
    var query = is.String(option.query) ? option.query : Object.keys(option.query).reduce(function (acc, key) {
      acc[key] = option.query[key] === null || option.query[key] === undefined ? '' : option.query[key];
      return acc;
    }, {});
    var queryString = new URLSearchParams(query).toString();

    if (queryString) {
      if (action.includes('?')) {
        action += "&".concat(queryString);
      } else {
        action += "?".concat(queryString);
      }
    }
  }

  xhr.onerror = function error(e) {
    option.onError(e);
  };

  xhr.onload = function onload() {
    if (xhr.status < 200 || xhr.status >= 300) {
      return option.onError(getError(action, option, xhr), getBody(xhr));
    }

    option.onSuccess(getBody(xhr));
  };

  xhr.open(option.method || 'get', action, true);
  var formData;

  if (option.data || option.file) {
    if (option.file || (option.dataType || '').toLowerCase() !== 'json') {
      formData = new FormData();
      Object.keys(option.data || {}).map(function (key) {
        formData.append(key, option.data[key]);
      });
    } else {
      formData = JSON.stringify(option.data || {});
      xhr.setRequestHeader('content-type', 'application/json');
    }
  }

  if (option.file) {
    formData.append(option.filename, option.file, option.file.name);
  }

  if (option.withCredentials && 'withCredentials' in xhr) {
    xhr.withCredentials = true;
  }

  var headers = option.headers || {};
  Object.keys(headers).forEach(function (item) {
    if (headers[item] != null) {
      xhr.setRequestHeader(item, headers[item]);
    }
  });
  xhr.send(formData);
}
function asyncFetch(config, _fetch, api) {
  return new Promise(function (resolve, reject) {
    (_fetch || fetch$1)(_objectSpread2(_objectSpread2({}, config), {}, {
      onSuccess: function onSuccess(res) {
        var fn = function fn(v) {
          return v;
        };

        var parse = parseFn(config.parse);

        if (is.Function(parse)) {
          fn = parse;
        } else if (parse && is.String(parse)) {
          fn = function fn(v) {
            return deepGet(v, parse);
          };
        }

        toPromise(fn(res, config.targetRule, api)).then(function (res) {
          resolve(res);
        });
      },
      onError: function onError(err) {
        reject(err);
      }
    }));
  });
}

function copy(value) {
  return deepCopy(value);
}

function Api(h) {
  function tidyFields(fields) {
    if (is.Undef(fields)) fields = h.fields();else if (!Array.isArray(fields)) fields = [fields];
    return fields;
  }

  function props(fields, key, val) {
    if (is.Undef(fields)) {
      fields = Object.keys(_objectSpread2(_objectSpread2({}, h.fieldCtx), h.nameCtx));
    } else if (!Array.isArray(fields)) {
      fields = [fields];
    }

    fields.forEach(function (field) {
      var ctxs = h.fieldCtx[field] || h.nameCtx[field];
      ctxs && ctxs.forEach(function (ctx) {
        $set(ctx.rule, key, val);
        h.$render.clearCache(ctx);
      });
    });
  }

  function allSubForm() {
    var subs = h.subForm;
    return Object.keys(subs).reduce(function (initial, k) {
      var sub = subs[k];
      if (!sub) return initial;
      if (Array.isArray(sub)) initial.push.apply(initial, _toConsumableArray(sub));else initial.push(sub);
      return initial;
    }, []);
  }

  var api = {
    get isScope() {
      return h.vm.props.subForm === false;
    },

    get isPreview() {
      return h.preview;
    },

    get config() {
      return h.options;
    },

    set config(val) {
      h.fc.options.value = val;
    },

    get options() {
      return h.options;
    },

    set options(val) {
      h.fc.options.value = val;
    },

    get form() {
      return h.form;
    },

    get rule() {
      return h.rules;
    },

    get parent() {
      return h.vm.setupState.parent && h.vm.setupState.parent.setupState.fapi;
    },

    get top() {
      if (api.parent) {
        return api.parent.top;
      }

      return api;
    },

    get scope() {
      var parent = h.vm;

      while (parent && parent.setupState.fapi) {
        if (!parent.props.subForm) {
          return parent.setupState.fapi;
        } else {
          parent = parent.setupState.parent;
        }
      }

      return api.top;
    },

    get children() {
      return allSubForm();
    },

    get siblings() {
      var inject = h.vm.setupState.getGroupInject();

      if (inject) {
        var subForm = inject.getSubForm();

        if (Array.isArray(subForm)) {
          return _toConsumableArray(subForm);
        }
      }

      return undefined;
    },

    get index() {
      var siblings = api.siblings;

      if (siblings) {
        var idx = siblings.indexOf(api);
        return idx > -1 ? idx : undefined;
      }

      return undefined;
    },

    get formulas() {
      return _objectSpread2({}, h.fc.formulas);
    },

    formData: function formData(fields) {
      if (fields == null || typeof fields === 'boolean') {
        var data = {};
        Object.keys(h.form).forEach(function (k) {
          if (fields === true || h.ignoreFields.indexOf(k) === -1) {
            data[k] = copy(h.form[k]);
          }
        });
        return data;
      } else {
        return tidyFields(fields).reduce(function (initial, id) {
          initial[id] = api.getValue(id);
          return initial;
        }, {});
      }
    },
    getValue: function getValue(field) {
      var ctx = h.getFieldCtx(field);

      if (!ctx) {
        if (h.options.appendValue !== false && hasProperty(h.appendData, field)) {
          return copy(h.appendData[field]);
        }

        return undefined;
      }

      return copy(ctx.rule.value);
    },
    coverValue: function coverValue(formData) {
      var data = _objectSpread2({}, formData || {});

      h.deferSyncValue(function () {
        h.appendData = {};
        api.fields().forEach(function (key) {
          var ctxs = h.fieldCtx[key];

          if (ctxs) {
            var flag = hasProperty(formData, key);
            ctxs.forEach(function (ctx) {
              ctx.rule.value = flag ? formData[key] : undefined;
            });
            delete data[key];
          }
        });
        extend(h.appendData, data);
      }, true);
    },
    setValue: function setValue(field) {
      var formData = field;
      if (arguments.length >= 2) formData = _defineProperty({}, field, arguments[1]);
      h.deferSyncValue(function () {
        Object.keys(formData).forEach(function (key) {
          var ctxs = h.fieldCtx[key];
          if (!ctxs) return h.appendData[key] = formData[key];
          ctxs.forEach(function (ctx) {
            ctx.rule.value = formData[key];
          });
        });
      }, true);
    },
    removeField: function removeField(field) {
      var ctx = h.getCtx(field);
      h.deferSyncValue(function () {
        h.getCtxs(field).forEach(function (ctx) {
          ctx.rm();
        });
      }, true);
      return ctx ? ctx.origin : undefined;
    },
    removeRule: function removeRule(rule) {
      var ctx = rule && byCtx(rule);
      if (!ctx) return;
      ctx.rm();
      return ctx.origin;
    },
    fields: function fields() {
      return h.fields();
    },
    append: function append(rule, after, child) {
      var index = h.sort.length - 1,
          rules;
      var ctx = h.getCtx(after);

      if (ctx) {
        if (child) {
          rules = ctx.getPending('children', ctx.rule.children);
          if (!Array.isArray(rules)) return;
          index = ctx.rule.children.length - 1;
        } else {
          index = ctx.root.indexOf(ctx.origin);
          rules = ctx.root;
        }
      } else rules = h.rules;

      rules.splice(index + 1, 0, rule);
    },
    prepend: function prepend(rule, after, child) {
      var index = 0,
          rules;
      var ctx = h.getCtx(after);

      if (ctx) {
        if (child) {
          rules = ctx.getPending('children', ctx.rule.children);
          if (!Array.isArray(rules)) return;
        } else {
          index = ctx.root.indexOf(ctx.origin);
          rules = ctx.root;
        }
      } else rules = h.rules;

      rules.splice(index, 0, rule);
    },
    hidden: function hidden(state, fields) {
      props(fields, 'hidden', !!state);
      h.refresh();
    },
    hiddenStatus: function hiddenStatus(id) {
      var ctx = h.getCtx(id);
      if (!ctx) return;
      return !!ctx.rule.hidden;
    },
    display: function display(state, fields) {
      props(fields, 'display', !!state);
      h.refresh();
    },
    displayStatus: function displayStatus(id) {
      var ctx = h.getCtx(id);
      if (!ctx) return;
      return !!ctx.rule.display;
    },
    disabled: function disabled(_disabled, fields) {
      tidyFields(fields).forEach(function (field) {
        h.getCtxs(field).forEach(function (ctx) {
          $set(ctx.rule.props, 'disabled', !!_disabled);
        });
      });
      h.refresh();
    },
    all: function all(origin) {
      return Object.keys(h.ctxs).map(function (k) {
        var ctx = h.ctxs[k];
        return origin ? ctx.origin : ctx.rule;
      });
    },
    model: function model(origin) {
      return h.fields().reduce(function (initial, key) {
        var ctx = h.fieldCtx[key][0];
        initial[key] = origin ? ctx.origin : ctx.rule;
        return initial;
      }, {});
    },
    component: function component(origin) {
      return Object.keys(h.nameCtx).reduce(function (initial, key) {
        var ctx = h.nameCtx[key].map(function (ctx) {
          return origin ? ctx.origin : ctx.rule;
        });
        initial[key] = ctx.length === 1 ? ctx[0] : ctx;
        return initial;
      }, {});
    },
    bind: function bind() {
      return api.form;
    },
    reload: function reload(rules) {
      h.reloadRule(rules);
    },
    updateOptions: function updateOptions(options) {
      h.fc.updateOptions(options);
      api.refresh();
    },
    onSubmit: function onSubmit(fn) {
      api.updateOptions({
        onSubmit: fn
      });
    },
    sync: function sync(field) {
      if (Array.isArray(field)) {
        field.forEach(function (v) {
          return api.sync(v);
        });
        return;
      }

      var ctxs = is.Object(field) ? byCtx(field) : h.getCtxs(field);

      if (!ctxs) {
        return;
      }

      ctxs = Array.isArray(ctxs) ? ctxs : [ctxs];
      ctxs.forEach(function (ctx) {
        if (!ctx.deleted) {
          var subForm = h.subForm[ctx.id];

          if (subForm) {
            if (Array.isArray(subForm)) {
              subForm.forEach(function (form) {
                form.refresh();
              });
            } else if (subForm) {
              subForm.refresh();
            }
          } //ctx.updateKey(true);


          h.$render.clearCache(ctx);
        }
      });
      h.refresh();
    },
    refresh: function refresh() {
      allSubForm().forEach(function (sub) {
        sub.refresh();
      });
      h.$render.clearCacheAll();
      h.refresh();
    },
    refreshOptions: function refreshOptions() {
      h.$manager.updateOptions(h.options);
      api.refresh();
    },
    hideForm: function hideForm(hide) {
      h.vm.setupState.isShow = !hide;
    },
    changeStatus: function changeStatus() {
      return h.changeStatus;
    },
    clearChangeStatus: function clearChangeStatus() {
      h.changeStatus = false;
    },
    updateRule: function updateRule(id, rule) {
      h.getCtxs(id).forEach(function (ctx) {
        extend(ctx.rule, rule);
      });
    },
    updateRules: function updateRules(rules) {
      Object.keys(rules).forEach(function (id) {
        api.updateRule(id, rules[id]);
      });
    },
    mergeRule: function mergeRule$1(id, rule) {
      h.getCtxs(id).forEach(function (ctx) {
        mergeRule(ctx.rule, rule);
      });
    },
    mergeRules: function mergeRules(rules) {
      Object.keys(rules).forEach(function (id) {
        api.mergeRule(id, rules[id]);
      });
    },
    getRule: function getRule(id, origin) {
      var ctx = h.getCtx(id);

      if (ctx) {
        return origin ? ctx.origin : ctx.rule;
      }
    },
    findType: function findType(type, origin) {
      var rule = undefined;
      Object.keys(h.ctxs).forEach(function (k) {
        var ctx = h.ctxs[k];

        if (ctx.rule.type === type) {
          rule = origin ? ctx.origin : ctx.rule;
        }
      });
      return rule;
    },
    findTypes: function findTypes(type, origin) {
      var rules = [];
      Object.keys(h.ctxs).forEach(function (k) {
        var ctx = h.ctxs[k];

        if (ctx.rule.type === type) {
          rules.push(origin ? ctx.origin : ctx.rule);
        }
      });
      return rules;
    },
    getRenderRule: function getRenderRule(id) {
      var ctx = h.getCtx(id);

      if (ctx) {
        return ctx.prop;
      }
    },
    getRefRule: function getRefRule(id) {
      var ctxs = h.getCtxs(id);

      if (ctxs) {
        var rules = ctxs.map(function (ctx) {
          return ctx.rule;
        });
        return rules.length === 1 ? rules[0] : rules;
      }
    },
    setEffect: function setEffect(id, attr, value) {
      var ctx = h.getCtx(id);

      if (ctx && attr) {
        if (attr[0] === '$') {
          attr = attr.substr(1);
        }

        if (hasProperty(ctx.rule, '$' + attr)) {
          $set(ctx.rule, '$' + attr, value);
        }

        if (!hasProperty(ctx.rule, 'effect')) {
          ctx.rule.effect = {};
        }

        $set(ctx.rule.effect, attr, value);
      }
    },
    clearEffectData: function clearEffectData(id, attr) {
      var ctx = h.getCtx(id);

      if (ctx) {
        if (attr && attr[0] === '$') {
          attr = attr.substr(1);
        }

        ctx.clearEffectData(attr);
        api.sync(id);
      }
    },
    updateValidate: function updateValidate(id, validate, merge) {
      if (merge) {
        api.mergeRule(id, {
          validate: validate
        });
      } else {
        props(id, 'validate', validate);
      }
    },
    updateValidates: function updateValidates(validates, merge) {
      Object.keys(validates).forEach(function (id) {
        api.updateValidate(id, validates[id], merge);
      });
    },
    refreshValidate: function refreshValidate() {
      api.refresh();
    },
    resetFields: function resetFields(fields) {
      tidyFields(fields).forEach(function (field) {
        h.getCtxs(field).forEach(function (ctx) {
          h.$render.clearCache(ctx);
          ctx.rule.value = copy(ctx.defaultValue);
        });
      });
      nextTick(function () {
        nextTick(function () {
          nextTick(function () {
            api.clearValidateState(fields);
          });
        });
      });

      if (fields == null) {
        is.Function(h.options.onReset) && invoke(function () {
          return h.options.onReset(api);
        });
        h.vm.emit('reset', api);
      }
    },
    method: function method(id, name) {
      var el = api.el(id);
      if (!el || !el[name]) throw new Error(format('err', "".concat(name, " \u65B9\u6CD5\u4E0D\u5B58\u5728")));
      return function () {
        return el[name].apply(el, arguments);
      };
    },
    exec: function exec(id, name) {
      for (var _len = arguments.length, args = new Array(_len > 2 ? _len - 2 : 0), _key = 2; _key < _len; _key++) {
        args[_key - 2] = arguments[_key];
      }

      return invoke(function () {
        return api.method(id, name).apply(void 0, args);
      });
    },
    toJson: function toJson$1(space) {
      return toJson(api.rule, space);
    },
    trigger: function trigger(id, event) {
      var el = api.el(id);

      for (var _len2 = arguments.length, args = new Array(_len2 > 2 ? _len2 - 2 : 0), _key2 = 2; _key2 < _len2; _key2++) {
        args[_key2 - 2] = arguments[_key2];
      }

      el && el.$emit.apply(el, [event].concat(args));
    },
    el: function el(id) {
      var ctx = h.getCtx(id);
      if (ctx) return ctx.el || h.vm.refs[ctx.ref];
    },
    closeModal: function closeModal(id) {
      h.bus.$emit('fc:closeModal:' + id);
    },
    getSubForm: function getSubForm(field) {
      var ctx = h.getCtx(field);
      return ctx ? h.subForm[ctx.id] : undefined;
    },
    getChildrenRuleList: function getChildrenRuleList(id) {
      var flag = _typeof(id) === 'object';
      var ctx = flag ? byCtx(id) : h.getCtx(id);
      var rule = ctx ? ctx.rule : flag ? id : api.getRule(id);

      if (!rule) {
        return [];
      }

      var rules = [];

      var findRules = function findRules(children) {
        children && children.forEach(function (item) {
          if (_typeof(item) !== 'object') {
            return;
          }

          if (item.field) {
            rules.push(item);
          }

          rules.push.apply(rules, _toConsumableArray(api.getChildrenRuleList(item)));
        });
      };

      findRules(ctx ? ctx.loadChildrenPending() : rule.children);
      return rules;
    },
    getParentRule: function getParentRule(id) {
      var flag = _typeof(id) === 'object';
      var ctx = flag ? byCtx(id) : h.getCtx(id);
      return ctx.parent.rule;
    },
    getParentSubRule: function getParentSubRule(id) {
      var flag = _typeof(id) === 'object';
      var ctx = flag ? byCtx(id) : h.getCtx(id);

      if (ctx) {
        var group = ctx.getParentGroup();

        if (group) {
          return group.rule;
        }
      }
    },
    getChildrenFormData: function getChildrenFormData(id, flag) {
      var rules = api.getChildrenRuleList(id);
      return rules.reduce(function (formData, rule) {
        if (rule.ignore !== true || flag === true) {
          formData[rule.field] = copy(rule.value);
        }

        return formData;
      }, {});
    },
    setChildrenFormData: function setChildrenFormData(id, formData, cover) {
      var rules = api.getChildrenRuleList(id);
      h.deferSyncValue(function () {
        rules.forEach(function (rule) {
          if (hasProperty(formData, rule.field)) {
            rule.value = formData[rule.field];
          } else if (cover) {
            rule.value = undefined;
          }
        });
      });
    },
    getGlobalEvent: function getGlobalEvent(name) {
      var event = api.options.globalEvent[name];

      if (event) {
        if (_typeof(event) === 'object') {
          event = event.handle;
        }

        return parseFn(event);
      }

      return undefined;
    },
    getGlobalData: function getGlobalData(name) {
      return new Promise(function (resolve, inject) {
        var config = api.options.globalData[name];

        if (!config) {
          resolve(h.fc.loadData[name]);
        } else if (config.type === 'fetch') {
          api.fetch(config).then(function (res) {
            resolve(res);
          })["catch"](inject);
        } else {
          resolve(config.data);
        }
      });
    },
    emitGlobalEvent: function emitGlobalEvent(name) {
      var fn = api.getGlobalEvent(name);

      if (fn) {
        var data = h.getInjectData({}, undefined);

        for (var _len3 = arguments.length, args = new Array(_len3 > 1 ? _len3 - 1 : 0), _key3 = 1; _key3 < _len3; _key3++) {
          args[_key3 - 1] = arguments[_key3];
        }

        data.args = [].concat(args);
        args.unshift(data);
        return fn.apply(null, args);
      }
    },
    setGlobalData: function setGlobalData(name, value) {
      api.setData('$globalData.' + name, value);
    },
    setGlobalVar: function setGlobalVar(name, value) {
      api.setData('$var.' + name, value);
    },
    renderRule: function renderRule(id, onInput, force) {
      var flag = _typeof(id) === 'object';
      var ctx = flag ? byCtx(id) : h.getCtx(id);
      return ctx ? h.$render.createRuleVnode(ctx, onInput, force) : undefined;
    },
    renderChildren: function renderChildren(id, onInput, force) {
      var flag = _typeof(id) === 'object';
      var ctx = flag ? byCtx(id) : h.getCtx(id);
      return ctx ? h.$render.createChildrenVnodes(ctx, onInput, force) : undefined;
    },
    nextTick: function nextTick(fn) {
      h.bus.$once('next-tick', fn);
      h.refresh();
    },
    nextRefresh: function nextRefresh(fn) {
      h.nextRefresh();
      fn && invoke(fn);
    },
    deferSyncValue: function deferSyncValue(fn, sync) {
      h.deferSyncValue(fn, sync);
    },
    emit: function emit(name) {
      var _h$vm;

      if (h.vm.emitsOptions && !h.vm.emitsOptions[name]) {
        h.vm.emitsOptions[name] = null;
      }

      for (var _len4 = arguments.length, args = new Array(_len4 > 1 ? _len4 - 1 : 0), _key4 = 1; _key4 < _len4; _key4++) {
        args[_key4 - 1] = arguments[_key4];
      }

      (_h$vm = h.vm).emit.apply(_h$vm, [name].concat(args));
    },
    bus: h.bus,
    getCurrentFormRule: function getCurrentFormRule() {
      var _h$vm$setupState$getG;

      return (_h$vm$setupState$getG = h.vm.setupState.getGroupInject()) === null || _h$vm$setupState$getG === void 0 ? void 0 : _h$vm$setupState$getG.rule;
    },
    fetch: function fetch(opt) {
      return new Promise(function (resolve, reject) {
        opt = deepCopy(opt);
        opt = h.loadFetchVar(opt);

        var fail = function fail(e) {
          invoke(function () {
            return opt.onError && opt.onError(e);
          });
          reject(e);
        };

        h.beforeFetch(opt).then(function () {
          return asyncFetch(opt, h.fc.create.fetch, api).then(function (res) {
            invoke(function () {
              return opt.onSuccess && opt.onSuccess(res);
            });
            resolve(res);
          })["catch"](function (e) {
            fail(e);
          });
        })["catch"](function (e) {
          fail(e);
        });
      });
    },
    watchFetch: function watchFetch(opt, callback, error, beforeFetch) {
      return h.fc.watchLoadData(function (get, change) {
        var _opt = deepCopy(opt);

        _opt = h.loadFetchVar(_opt, get);

        if (beforeFetch && beforeFetch(_opt, change) === false) {
          return;
        }

        var fail = function fail(e) {
          invoke(function () {
            return _opt.onError && _opt.onError(e);
          });
          error && error(e);
        };

        h.beforeFetch(_opt).then(function () {
          return asyncFetch(_opt, h.fc.create.fetch, api).then(function (res) {
            invoke(function () {
              return _opt.onSuccess && _opt.onSuccess(res);
            });
            callback && callback(res, change);
          })["catch"](function (e) {
            fail(e);
          });
        })["catch"](function (e) {
          fail(e);
        });
      }, opt.wait == null ? 1000 : opt.wait);
    },
    getData: function getData(id, def) {
      if (h.fc.get) {
        return h.fc.get(id, def);
      } else {
        return h.fc.getLoadData(id, def);
      }
    },
    watchData: function watchData(fn) {
      return h.fc.watchLoadData(function (get, change) {
        invoke(function () {
          return fn(get, change);
        });
      });
    },
    setData: function setData(id, data, isGlobal) {
      return h.fc.setData(id, data, isGlobal);
    },
    refreshData: function refreshData(id) {
      return h.fc.refreshData(id);
    },
    t: function t(id, params) {
      return h.fc.t(id, params);
    },
    getLocale: function getLocale() {
      return h.fc.getLocale();
    },
    helper: {
      tidyFields: tidyFields,
      props: props
    }
  };
  ['on', 'once', 'off'].forEach(function (n) {
    api[n] = function () {
      var _h$bus;

      (_h$bus = h.bus)["$".concat(n)].apply(_h$bus, arguments);
    };
  });
  api.changeValue = api.changeField = api.setValue;
  return api;
}

function useCache(Render) {
  extend(Render.prototype, {
    initCache: function initCache() {
      this.clearCacheAll();
    },
    clearCache: function clearCache(ctx) {
      if (ctx.rule.cache) {
        return;
      }

      if (!this.cache[ctx.id]) {
        if (ctx.parent) {
          this.clearCache(ctx.parent);
        }

        return;
      }

      if (this.cache[ctx.id].use === true || this.cache[ctx.id].parent) {
        this.$handle.refresh();
      }

      if (this.cache[ctx.id].parent) {
        this.clearCache(this.cache[ctx.id].parent);
      }

      this.cache[ctx.id] = null;
    },
    clearCacheAll: function clearCacheAll() {
      this.cache = {};
    },
    setCache: function setCache(ctx, vnode, parent) {
      this.cache[ctx.id] = {
        vnode: vnode,
        use: false,
        parent: parent,
        slot: ctx.rule.slot
      };
    },
    getCache: function getCache(ctx) {
      var cache = this.cache[ctx.id];

      if (cache) {
        cache.use = true;
        return cache.vnode;
      }

      return undefined;
    }
  });
}

function useRender$1(Render) {
  extend(Render.prototype, {
    initRender: function initRender() {
      this.cacheConfig = {};
    },
    getTypeSlot: function getTypeSlot(ctx) {
      var _fn = function _fn(vm) {
        if (vm) {
          var slot = undefined;

          if (ctx.rule.field) {
            slot = vm.slots['field-' + toLine(ctx.rule.field)] || vm.slots['field-' + ctx.rule.field];
          }

          if (!slot) {
            slot = vm.slots['type-' + toLine(ctx.type)] || vm.slots['type-' + ctx.type];
          }

          if (slot) {
            return slot;
          }

          return _fn(vm.setupState.parent);
        }
      };

      return _fn(this.vm);
    },
    render: function render() {
      var _this = this;

      // console.warn('renderrrrr', this.id);
      if (!this.vm.setupState.isShow) {
        return;
      }

      this.$manager.beforeRender();
      var slotBag = makeSlotBag();
      this.sort.forEach(function (k) {
        _this.renderSlot(slotBag, _this.$handle.ctxs[k]);
      });
      return this.$manager.render(slotBag);
    },
    renderSlot: function renderSlot(slotBag, ctx, parent) {
      if (this.isFragment(ctx)) {
        ctx.initProp();
        this.mergeGlobal(ctx);
        ctx.initNone();
        var slots = this.renderChildren(ctx.loadChildrenPending(), ctx);
        var def = slots["default"];
        def && slotBag.setSlot(ctx.rule.slot, function () {
          return def();
        });
        delete slots["default"];
        slotBag.mergeBag(slots);
      } else {
        slotBag.setSlot(ctx.rule.slot, this.renderCtx(ctx, parent));
      }
    },
    mergeGlobal: function mergeGlobal(ctx) {
      var _this2 = this;

      var g = this.$handle.options.global;
      if (!g) return;

      if (!this.cacheConfig[ctx.trueType]) {
        this.cacheConfig[ctx.trueType] = computed(function () {
          var g = _this2.$handle.options.global;
          return mergeRule({}, [g['*'] || g["default"] || {}, g[ctx.originType] || g[ctx.type] || g[ctx.type] || {}]);
        });
      }

      ctx.prop = mergeRule({}, [this.cacheConfig[ctx.trueType].value, ctx.prop]);
    },
    setOptions: function setOptions(ctx) {
      var opt = ctx.loadPending({
        key: 'options',
        origin: ctx.prop.options,
        def: []
      });
      ctx.prop.options = opt;

      if (ctx.prop.optionsTo && opt) {
        deepSet(ctx.prop, ctx.prop.optionsTo, opt);
      }
    },
    deepSet: function deepSet$1(ctx) {
      var deep = ctx.prop.deep;
      deep && Object.keys(deep).sort(function (a, b) {
        return a.length < b.length ? -1 : 1;
      }).forEach(function (str) {
        deepSet(ctx.prop, str, deep[str]);
      });
    },
    parseSide: function parseSide(side, ctx) {
      return is.Object(side) ? mergeRule({
        props: {
          formCreateInject: ctx.prop.props.formCreateInject
        }
      }, side) : side;
    },
    renderSides: function renderSides(vn, ctx, temp) {
      var prop = ctx[temp ? 'rule' : 'prop'];
      return [this.renderRule(this.parseSide(prop.prefix, ctx)), vn, this.renderRule(this.parseSide(prop.suffix, ctx))];
    },
    renderId: function renderId(name, type) {
      var _this3 = this;

      var ctxs = this.$handle[type === 'field' ? 'fieldCtx' : 'nameCtx'][name];
      return ctxs ? ctxs.map(function (ctx) {
        return _this3.renderCtx(ctx, ctx.parent);
      }) : undefined;
    },
    renderCtx: function renderCtx(ctx, parent) {
      var _this4 = this;

      try {
        if (ctx.type === 'hidden') return;
        var rule = ctx.rule;

        if (this.force || !this.cache[ctx.id] || this.cache[ctx.id].slot !== rule.slot) {
          var vn;
          ctx.initProp();
          this.mergeGlobal(ctx);
          ctx.initNone();
          this.$manager.tidyRule(ctx);
          this.deepSet(ctx);
          this.setOptions(ctx);
          this.ctxProp(ctx);
          var prop = ctx.prop;
          prop.preview = !!(prop.preview != null ? prop.preview : this.$handle.preview);
          prop.props.formCreateInject = this.injectProp(ctx);
          var cacheFlag = prop.cache !== false;
          var preview = prop.preview;

          if (prop.hidden) {
            this.setCache(ctx, undefined, parent);
            return;
          }

          vn = function vn() {
            for (var _len = arguments.length, slotValue = new Array(_len), _key = 0; _key < _len; _key++) {
              slotValue[_key] = arguments[_key];
            }

            var inject = {
              rule: rule,
              prop: prop,
              preview: preview,
              api: _this4.$handle.api,
              model: prop.model || {},
              slotValue: slotValue
            };

            if (slotValue.length && rule.slotUpdate) {
              invoke(function () {
                return rule.slotUpdate(inject);
              });
            }

            var children = {};

            var _load = ctx.loadChildrenPending();

            if (ctx.parser.renderChildren) {
              children = ctx.parser.renderChildren(_load, ctx);
            } else if (ctx.parser.loadChildren !== false) {
              children = _this4.renderChildren(_load, ctx);
            }

            Object.keys(prop.renderSlots || {}).forEach(function (key) {
              children[key] = function () {
                for (var _len2 = arguments.length, args = new Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
                  args[_key2] = arguments[_key2];
                }

                if (is.Function(prop.renderSlots[key])) {
                  return invoke(function () {
                    var _prop$renderSlots;

                    return (_prop$renderSlots = prop.renderSlots)[key].apply(_prop$renderSlots, args);
                  });
                }

                var rule = _this4.parseSide(prop.renderSlots[key], ctx);

                return _this4.renderRule(rule);
              };
            });

            var slot = _this4.getTypeSlot(ctx);

            var _vn;

            if (slot) {
              inject.children = children;
              _vn = slot(inject);
            } else {
              _vn = preview ? ctx.parser.preview(copy$1(children), ctx) : ctx.parser.render(copy$1(children), ctx);
            }

            _vn = _this4.renderSides(_vn, ctx);

            if (!(!ctx.input && is.Undef(prop["native"])) && prop["native"] !== true) {
              _this4.fc.targetFormDriver('updateWrap', ctx);

              _vn = _this4.$manager.makeWrap(ctx, _vn);
            }

            if (ctx.none) {
              if (Array.isArray(_vn)) {
                _vn = _vn.map(function (v) {
                  if (!v || !v.__v_isVNode) {
                    return v;
                  }

                  return _this4.none(v);
                });
              } else {
                _vn = _this4.none(_vn);
              }
            }

            cacheFlag && _this4.setCache(ctx, function () {
              return _this4.stable(_vn);
            }, parent);
            return _vn;
          };

          this.setCache(ctx, vn, parent);
        }

        return function () {
          var cache = _this4.getCache(ctx);

          if (cache) {
            return cache.apply(void 0, arguments);
          } else if (_this4.cache[ctx.id]) {
            return;
          }

          var _vn = _this4.renderCtx(ctx, ctx.parent);

          if (_vn) {
            return _vn();
          }
        };
      } catch (e) {
        console.error(e);
        return;
      }
    },
    none: function none(vn) {
      if (vn) {
        vn.props["class"] = this.mergeClass(vn.props["class"], 'fc-none');
        return vn;
      }
    },
    mergeClass: function mergeClass(target, value) {
      if (Array.isArray(target)) {
        target.push(value);
      } else {
        return target ? [target, value] : value;
      }

      return target;
    },
    stable: function stable(vn) {
      var _this5 = this;

      var list = Array.isArray(vn) ? vn : [vn];
      list.forEach(function (v) {
        if (v && v.__v_isVNode && v.children && _typeof(v.children) === 'object') {
          v.children.$stable = true;

          _this5.stable(v.children);
        }
      });
      return vn;
    },
    getModelField: function getModelField(ctx) {
      return ctx.prop.modelField || ctx.parser.modelField || this.fc.modelFields[this.vNode.aliasMap[ctx.type]] || this.fc.modelFields[ctx.type] || this.fc.modelFields[ctx.originType] || 'modelValue';
    },
    isFragment: function isFragment(ctx) {
      return ctx.type === 'fragment' || ctx.type === 'template';
    },
    injectProp: function injectProp(ctx) {
      var _this6 = this;

      var state = this.vm.setupState;

      if (!state.ctxInject[ctx.id]) {
        state.ctxInject[ctx.id] = {
          api: this.$handle.api,
          form: this.fc.create,
          subForm: function subForm(_subForm) {
            _this6.$handle.addSubForm(ctx, _subForm);
          },
          getSubForm: function getSubForm() {
            return _this6.$handle.subForm[ctx.id];
          },
          slots: function slots() {
            return _this6.vm.setupState.top.slots;
          },
          getWrap: function getWrap() {
            return _this6.vm.refs[ctx.wrapRef];
          },
          options: [],
          children: [],
          preview: false,
          id: ctx.id,
          field: ctx.field,
          rule: ctx.rule,
          input: ctx.input,
          t: function t() {
            var _this6$$handle$api;

            return (_this6$$handle$api = _this6.$handle.api).t.apply(_this6$$handle$api, arguments);
          },
          updateValue: function updateValue(data) {
            _this6.$handle.onUpdateValue(ctx, data);
          }
        };
      }

      var inject = state.ctxInject[ctx.id];
      extend(inject, {
        preview: ctx.prop.preview,
        options: ctx.prop.options,
        children: ctx.loadChildrenPending()
      });
      return inject;
    },
    ctxProp: function ctxProp(ctx) {
      var _this7 = this;

      var ref = ctx.ref,
          key = ctx.key,
          rule = ctx.rule;
      this.$manager.mergeProp(ctx);
      ctx.parser.mergeProp(ctx);
      var props = [{
        ref: ref,
        key: rule.key || "".concat(key, "fc"),
        slot: undefined,
        on: {
          vnodeMounted: function vnodeMounted(vn) {
            vn.el.__rule__ = ctx.rule;

            _this7.onMounted(ctx, vn.el);
          },
          'fc.updateValue': function fcUpdateValue(data) {
            _this7.$handle.onUpdateValue(ctx, data);
          },
          'fc.el': function fcEl(el) {
            ctx.exportEl = el;

            if (el) {
              (el.$el || el).__rule__ = ctx.rule;
            }
          }
        }
      }];

      if (ctx.input) {
        var tmpInput = this.tmpInput;

        if (this.vm.props.disabled === true) {
          ctx.prop.props.disabled = true;
        }

        var field = this.getModelField(ctx);
        var model = {
          callback: function callback(value) {
            tmpInput && tmpInput(ctx.field, value, ctx.rule);

            _this7.onInput(ctx, value);
          },
          modelField: field,
          value: this.$handle.getFormData(ctx)
        };
        props.push({
          on: _objectSpread2(_defineProperty({}, "update:".concat(field), model.callback), ctx.prop.modelEmit ? _defineProperty({}, ctx.prop.modelEmit, function () {
            return _this7.onEmitInput(ctx);
          }) : {}),
          props: _defineProperty({}, field, model.value)
        });
        ctx.prop.model = model;
      }

      mergeProps(props, ctx.prop);
      return ctx.prop;
    },
    onMounted: function onMounted(ctx, el) {
      ctx.el = this.vm.refs[ctx.ref] || el;
      ctx.parser.mounted(ctx);
      this.$handle.effect(ctx, 'mounted');
      this.$handle.targetHook(ctx, 'mounted');
    },
    onInput: function onInput(ctx, value) {
      if (ctx.prop.modelEmit) {
        this.$handle.onBaseInput(ctx, value);
        return;
      }

      this.$handle.onInput(ctx, value);
    },
    onEmitInput: function onEmitInput(ctx) {
      this.$handle.setValue(ctx, ctx.parser.toValue(ctx.modelValue, ctx), ctx.modelValue);
    },
    renderChildren: function renderChildren(children, ctx) {
      var _this8 = this;

      if (!is.trueArray(children)) return {};
      var slotBag = makeSlotBag();
      children.map(function (child) {
        if (!child) return;
        if (is.String(child) || is.Number(child)) return slotBag.setSlot(null, "".concat(child));

        if (child.__fc__) {
          return _this8.renderSlot(slotBag, child.__fc__, ctx);
        }

        if (child.type) {
          nextTick(function () {
            _this8.$handle.loadChildren(children, ctx);

            _this8.$handle.refresh();
          });
        }
      });
      return slotBag.getSlots();
    },
    defaultRender: function defaultRender(ctx, children) {
      var prop = ctx.prop;

      if (prop.component) {
        if (typeof prop.component === 'string') {
          return this.vNode.make(prop.component, prop, children);
        } else {
          return this.vNode.makeComponent(prop.component, prop, children);
        }
      }

      if (this.vNode[ctx.type]) return this.vNode[ctx.type](prop, children);
      if (this.vNode[ctx.originType]) return this.vNode[ctx.originType](prop, children);
      return this.vNode.make(lower(prop.type), prop, children);
    },
    createChildrenVnodes: function createChildrenVnodes(ctx, onInput, force) {
      this.force = force !== false;
      this.tmpInput = onInput;
      var res = this.renderChildren(ctx.rule.children, ctx);
      this.force = false;
      this.tmpInput = null;
      return res;
    },
    createRuleVnode: function createRuleVnode(ctx, onInput, force) {
      this.force = force !== false;
      this.tmpInput = onInput;
      var slotBag = makeSlotBag();
      this.renderSlot(slotBag, ctx, ctx.parent);
      this.force = false;
      this.tmpInput = null;
      return slotBag.getSlots();
    },
    renderRule: function renderRule(rule, children, origin) {
      var _this9 = this;

      if (!rule) return undefined;
      if (is.String(rule) || is.Number(rule)) return "".concat(rule);
      var type;

      if (origin) {
        type = rule.type;
      } else {
        type = rule.is;

        if (rule.type) {
          type = toCase(rule.type);
          var alias = this.vNode.aliasMap[type];
          if (alias) type = toCase(alias);
        }
      }

      if (!type) return undefined;
      var slotBag = makeSlotBag();

      if (is.trueArray(rule.children)) {
        rule.children.forEach(function (v) {
          v && slotBag.setSlot(v === null || v === void 0 ? void 0 : v.slot, function () {
            return _this9.renderRule(v);
          });
        });
      }

      var props = _objectSpread2({}, rule);

      delete props.type;
      delete props.is;
      return this.vNode.make(type, props, slotBag.mergeBag(children).getSlots());
    }
  });
}

var id$1 = 1;
function Render(handle) {
  extend(this, {
    $handle: handle,
    fc: handle.fc,
    vm: handle.vm,
    $manager: handle.$manager,
    vNode: new handle.fc.CreateNode(handle),
    force: false,
    tmpInput: null,
    id: id$1++
  });
  funcProxy(this, {
    options: function options() {
      return handle.options;
    },
    sort: function sort() {
      return handle.sort;
    }
  });
  this.initCache();
  this.initRender();
}
useCache(Render);
useRender$1(Render);

function useInject(Handler) {
  extend(Handler.prototype, {
    parseInjectEvent: function parseInjectEvent(rule, on) {
      var inject = rule.inject || this.options.injectEvent;
      return this.parseEventLst(rule, on, inject);
    },
    parseEventLst: function parseEventLst(rule, data, inject, deep) {
      var _this = this;

      Object.keys(data).forEach(function (k) {
        var fn = _this.parseEvent(rule, data[k], inject, deep);

        if (fn) {
          data[k] = fn;
        }
      });
      return data;
    },
    parseEvent: function parseEvent(rule, fn, inject, deep) {
      if (is.Function(fn) && (inject !== false && !is.Undef(inject) || fn.__inject)) {
        return this.inject(rule, fn, inject);
      } else if (!deep && Array.isArray(fn) && fn[0] && (is.String(fn[0]) || is.Function(fn[0]))) {
        return this.parseEventLst(rule, fn, inject, true);
      } else if (is.String(fn)) {
        var val = parseFn(fn);

        if (val && fn !== val) {
          return val.__inject ? this.parseEvent(rule, val, inject, true) : val;
        }
      }
    },
    parseEmit: function parseEmit(ctx) {
      var _this2 = this;

      var event = {},
          rule = ctx.rule,
          emitPrefix = rule.emitPrefix,
          field = rule.field,
          name = rule.name,
          inject = rule.inject;
      var emit = rule.emit || [];

      if (is.trueArray(emit)) {
        emit.forEach(function (eventName) {
          if (!eventName) return;
          var eventInject;
          var emitKey = emitPrefix || field || name;

          if (is.Object(eventName)) {
            eventInject = eventName.inject;
            eventName = eventName.name;
            emitKey = eventName.prefix || emitKey;
          }

          if (emitKey) {
            var fieldKey = toLine("".concat(emitKey, "-").concat(eventName));

            var fn = function fn() {
              var _this2$vm, _this2$vm2, _this2$bus;

              if (_this2.vm.emitsOptions && !_this2.vm.emitsOptions[fieldKey]) {
                _this2.vm.emitsOptions[fieldKey] = null;
              }

              for (var _len = arguments.length, arg = new Array(_len), _key = 0; _key < _len; _key++) {
                arg[_key] = arguments[_key];
              }

              (_this2$vm = _this2.vm).emit.apply(_this2$vm, [fieldKey].concat(arg));

              (_this2$vm2 = _this2.vm).emit.apply(_this2$vm2, ['emit-event', fieldKey].concat(arg));

              (_this2$bus = _this2.bus).$emit.apply(_this2$bus, [fieldKey].concat(arg));
            };

            fn.__emit = true;

            if (!eventInject && inject === false) {
              event[toCase(eventName)] = fn;
            } else {
              var _inject = eventInject || inject || _this2.options.injectEvent;

              event[toCase(eventName)] = is.Undef(_inject) ? fn : _this2.inject(rule, fn, _inject);
            }
          }
        });
      }

      ctx.computed.on = event;
      return event;
    },
    getInjectData: function getInjectData(self, inject) {
      var $api = self.__fc__ && self.__fc__.$api;
      var vm = self.__fc__ && self.__fc__.$handle.vm || this.vm;
      var _vm$props = vm.props,
          option = _vm$props.option,
          rule = _vm$props.rule;
      return {
        $f: $api || this.api,
        api: $api || this.api,
        rule: rule,
        self: self.__origin__,
        option: option,
        inject: inject
      };
    },
    inject: function inject(self, _fn, _inject2) {
      if (_fn.__origin) {
        if (this.watching && !this.loading) return _fn;
        _fn = _fn.__origin;
      }

      var h = this;

      var fn = function fn() {
        var data = h.getInjectData(self, _inject2);

        for (var _len2 = arguments.length, args = new Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
          args[_key2] = arguments[_key2];
        }

        data.args = [].concat(args);
        args.unshift(data);
        return _fn.apply(this, args);
      };

      fn.__origin = _fn;
      fn.__json = _fn.__json;
      return fn;
    },
    loadStrVar: function loadStrVar(str, get, group) {
      var _this3 = this;

      if (str && typeof str === 'string' && str.indexOf('{{') > -1 && str.indexOf('}}') > -1) {
        var tmp = str;
        var vars = extractVar(str);

        var getValue = function getValue(field) {
          var flag = false;
          var val;

          if (group && field.indexOf('$form.') === 0) {
            var _split = field.split('.');

            _split.shift();

            if (hasProperty(group.value, _split[0])) {
              flag = true;
              val = get ? get({
                id: '$form.' + _split[0] + '_' + group.rule.__fc__.id,
                getValue: function getValue() {
                  return deepGet(group.value, _split);
                }
              }) : deepGet(group.value, _split);
            }
          }

          if (!flag) {
            val = get ? get(field) : _this3.fc.getLoadData(field);
          }

          return val;
        };

        var treeToVal = function treeToVal(tree) {
          var fields = [];
          tree.forEach(function (item) {
            if (item.key) {
              fields.push(item.key);
            } else if (item.children) {
              fields.push(treeToVal(item.children));
            }
          });
          var flag = false;
          fields.forEach(function (field, idx) {
            if (field != null && (field.indexOf('\'') === 0 || field.indexOf('"') === 0)) {
              fields[idx] = field.slice(1, -1);
              flag = true;
            }
          });

          if (fields.length === 1 && (flag || !isNaN(Number(fields[0])))) {
            return fields[0];
          }

          return getValue(fields.join('.'));
        };

        var lastVal;
        vars.forEach(function (v) {
          var split = v.split('||');
          var field = split[0].trim();

          if (field) {
            var def = (split[1] || '').trim();
            var tree = parseTemplateToTree(field);
            var val = invoke(function () {
              return treeToVal(tree);
            });

            if ((val == null || val === '') && split.length > 1) {
              val = def;
            }

            lastVal = val;
            str = str.replaceAll("{{".concat(v, "}}"), val == null ? '' : val);
          }
        });

        if (vars.length === 1 && tmp === "{{".concat(vars[0], "}}")) {
          return lastVal;
        }
      }

      return str;
    },
    loadFetchVar: function loadFetchVar(options, get, rule) {
      var _this4 = this;

      var group;

      if (rule && rule.__fc__) {
        group = rule.__fc__.getParentGroup();
      }

      var loadVal = function loadVal(str) {
        return _this4.loadStrVar(str, get, group ? {
          rule: rule,
          value: _this4.subRuleData[group.id] || {}
        } : null);
      };

      options.action = loadVal(options.action || '');
      ['headers', 'data', 'query'].forEach(function (key) {
        if (options[key]) {
          var data = Array.isArray(options[key]) ? [] : {};
          Object.keys(options[key]).forEach(function (k) {
            data[loadVal(k)] = loadVal(options[key][k]);
          });
          options[key] = data;
        }
      });
      return options;
    }
  });
}

var EVENT = ['hook:updated', 'hook:mounted'];
function usePage(Handler) {
  extend(Handler.prototype, {
    usePage: function usePage() {
      var _this = this;

      var page = this.options.page;
      if (!page) return;
      var first = 25;
      var limit = getLimit(this.rules);

      if (is.Object(page)) {
        if (page.first) first = parseInt(page.first, 10) || first;
        if (page.limit) limit = parseInt(page.limit, 10) || limit;
      }

      extend(this, {
        first: first,
        limit: limit,
        pageEnd: this.rules.length <= first
      });
      this.bus.$on('page-end', function () {
        return _this.vm.emit('page-end', _this.api);
      });
      this.pageLoad();
    },
    pageLoad: function pageLoad() {
      var _this2 = this;

      var pageFn = function pageFn() {
        if (_this2.pageEnd) {
          _this2.bus.$off(EVENT, pageFn);

          _this2.bus.$emit('page-end');
        } else {
          _this2.first += _this2.limit;
          _this2.pageEnd = _this2.rules.length <= _this2.first;

          _this2.loadRule();

          _this2.refresh();
        }
      };

      this.bus.$on(EVENT, pageFn);
    }
  });
}

function getLimit(rules) {
  return rules.length < 31 ? 31 : Math.ceil(rules.length / 3);
}

function useRender(Handler) {
  extend(Handler.prototype, {
    clearNextTick: function clearNextTick() {
      this.nextTick && clearTimeout(this.nextTick);
      this.nextTick = null;
    },
    bindNextTick: function bindNextTick(fn) {
      var _this = this;

      this.clearNextTick();
      this.nextTick = setTimeout(function () {
        fn();
        _this.nextTick = null;
      }, 10);
    },
    render: function render() {
      // console.warn('%c render', 'color:green');
      ++this.loadedId;
      if (this.vm.setupState.unique > 0) return this.$render.render();else {
        this.vm.setupState.unique = 1;
        return [];
      }
    }
  });
}

function bind(ctx) {
  Object.defineProperties(ctx.origin, {
    __fc__: enumerable(markRaw(ctx), true)
  });

  if (ctx.rule !== ctx.origin) {
    Object.defineProperties(ctx.rule, {
      __fc__: enumerable(markRaw(ctx), true)
    });
  }
}

function RuleContext(handle, rule, defaultValue) {
  var id = uniqueId();
  var isInput = !!rule.field;
  extend(this, {
    id: id,
    ref: id,
    wrapRef: id + 'fi',
    rule: rule,
    origin: rule.__origin__ || rule,
    name: rule.name,
    pending: {},
    none: false,
    watch: [],
    linkOn: [],
    root: [],
    ctrlRule: [],
    children: [],
    parent: null,
    group: rule.subRule ? this : null,
    cacheConfig: null,
    prop: _objectSpread2({}, rule),
    computed: {},
    payload: {},
    refRule: {},
    input: isInput,
    el: undefined,
    exportEl: undefined,
    defaultValue: isInput ? deepCopy(defaultValue) : undefined,
    field: rule.field || undefined
  });
  this.updateKey();
  bind(this);
  this.update(handle, true);
}
extend(RuleContext.prototype, {
  getParentGroup: function getParentGroup() {
    var ctx = this.parent;

    while (ctx) {
      if (ctx.group) {
        return ctx;
      }

      ctx = ctx.parent;
    }
  },
  loadChildrenPending: function loadChildrenPending() {
    var _this = this;

    var children = this.rule.children || [];
    if (Array.isArray(children)) return children;
    return this.loadPending({
      key: 'children',
      origin: children,
      def: [],
      onLoad: function onLoad(data) {
        _this.$handle && _this.$handle.loadChildren(data, _this);
      },
      onUpdate: function onUpdate(value, oldValue) {
        if (_this.$handle) {
          value === oldValue ? _this.$handle.loadChildren(value, _this) : _this.$handle.updateChildren(_this, value, oldValue);
        }
      },
      onReload: function onReload(value) {
        if (_this.$handle) {
          _this.$handle.updateChildren(_this, [], value);
        } else {
          delete _this.pending.children;
        }
      }
    });
  },
  loadPending: function loadPending(config) {
    var _this2 = this;

    var key = config.key,
        origin = config.origin,
        def = config.def,
        onLoad = config.onLoad,
        onReload = config.onReload,
        onUpdate = config.onUpdate;

    if (this.pending[key] && this.pending[key].origin === origin) {
      return this.getPending(key, def);
    }

    delete this.pending[key];
    var value = origin;

    if (is.Function(origin)) {
      var source = invoke(function () {
        return origin({
          rule: _this2.rule,
          api: _this2.$api,
          update: function update(data) {
            var value = data || def;

            var oldValue = _this2.getPending(key, def);

            _this2.setPending(key, origin, value);

            onUpdate && onUpdate(value, oldValue);
          },
          reload: function reload() {
            var oldValue = _this2.getPending(key, def);

            delete _this2.pending[key];
            onReload && onReload(oldValue);
            _this2.$api && _this2.$api.sync(_this2.rule);
          }
        });
      });

      if (source && is.Function(source.then)) {
        source.then(function (data) {
          var value = data || def;

          _this2.setPending(key, origin, value);

          onLoad && onLoad(value);
          _this2.$api && _this2.$api.sync(_this2.rule);
        })["catch"](function (e) {
          console.error(e);
        });
        value = def;
        this.setPending(key, origin, value);
      } else {
        value = source || def;
        this.setPending(key, origin, value);
        onLoad && onLoad(value);
      }
    }

    return value;
  },
  getPending: function getPending(key, def) {
    return this.pending[key] && this.pending[key].value || def;
  },
  setPending: function setPending(key, origin, value) {
    this.pending[key] = {
      origin: origin,
      value: reactive(value)
    };
  },
  effectData: function effectData(name) {
    if (!this.payload[name]) {
      this.payload[name] = {};
    }

    return this.payload[name];
  },
  clearEffectData: function clearEffectData(name) {
    if (name === undefined) {
      this.payload = {};
    } else {
      delete this.payload[name];
    }
  },
  updateKey: function updateKey(flag) {
    this.key = uniqueId();
    flag && this.parent && this.parent.updateKey(flag);
  },
  updateType: function updateType() {
    this.originType = this.rule.type;
    this.type = toCase(this.rule.type);
    this.trueType = this.$handle.getType(this.originType);
  },
  setParser: function setParser(parser) {
    this.parser = parser;
    parser.init(this);
  },
  initProp: function initProp() {
    var _this3 = this,
        _this$refRule,
        _this$refRule$__$vali;

    var rule = _objectSpread2({}, this.rule);

    delete rule.children;
    delete rule.validate;
    this.prop = mergeRule({}, [rule].concat(_toConsumableArray(Object.keys(this.payload).map(function (k) {
      return _this3.payload[k];
    })), [this.computed]));
    this.prop.validate = [].concat(_toConsumableArray(((_this$refRule = this.refRule) === null || _this$refRule === void 0 ? void 0 : (_this$refRule$__$vali = _this$refRule.__$validate) === null || _this$refRule$__$vali === void 0 ? void 0 : _this$refRule$__$vali.value) || []), _toConsumableArray(this.prop.validate || []));
  },
  initNone: function initNone() {
    this.none = !(is.Undef(this.prop.display) || !!this.prop.display);
  },
  hasHidden: function hasHidden() {
    return !!this.rule.hidden || (this.parent ? this.parent.hasHidden() : false);
  },
  injectValidate: function injectValidate() {
    return this.prop.validate;
  },
  check: function check(handle) {
    return this.vm === handle.vm;
  },
  unwatch: function unwatch() {
    var _this4 = this;

    this.watch.forEach(function (un) {
      return un();
    });
    this.watch = [];
    Object.keys(this.refRule).forEach(function (key) {
      if (key.indexOf('__$') !== 0) {
        delete _this4.refRule[key];
      }
    });
  },
  unlink: function unlink() {
    this.linkOn.forEach(function (un) {
      return un();
    });
    this.linkOn = [];
  },
  link: function link() {
    this.unlink();
    this.$handle.appendLink(this);
  },
  watchTo: function watchTo() {
    this.$handle.watchCtx(this);
  },
  "delete": function _delete() {
    this.unwatch();
    this.unlink();
    this.rmCtrl();

    if (this.parent) {
      this.parent.children.splice(this.parent.children.indexOf(this) >>> 0, 1);
    }

    extend(this, {
      deleted: true,
      computed: {},
      parent: null,
      children: [],
      cacheConfig: null,
      none: false
    });
  },
  rmCtrl: function rmCtrl() {
    this.ctrlRule.forEach(function (ctrl) {
      return ctrl.__fc__ && ctrl.__fc__.rm();
    });
    this.ctrlRule = [];
  },
  rm: function rm() {
    var _this5 = this;

    var _rm = function _rm() {
      var index = _this5.root.indexOf(_this5.origin);

      if (index > -1) {
        _this5.root.splice(index, 1);

        _this5.$handle && _this5.$handle.refresh();
      }
    };

    if (this.deleted) {
      _rm();

      return;
    }

    this.$handle.noWatch(function () {
      _this5.$handle.deferSyncValue(function () {
        _this5.rmCtrl();

        _rm();

        _this5.$handle.rmCtx(_this5);

        extend(_this5, {
          root: []
        });
      }, _this5.input);
    });
  },
  update: function update(handle, init) {
    extend(this, {
      deleted: false,
      $handle: handle,
      $render: handle.$render,
      $api: handle.api,
      vm: handle.vm,
      vNode: handle.$render.vNode,
      updated: false,
      cacheValue: this.rule.value
    });
    !init && this.unwatch();
    this.watchTo();
    this.link();
    this.updateType();
  }
});

function useLoader(Handler) {
  extend(Handler.prototype, {
    nextRefresh: function nextRefresh(fn) {
      var _this = this;

      var id = this.loadedId;
      nextTick(function () {
        id === _this.loadedId && (fn ? fn() : _this.refresh());
      });
    },
    parseRule: function parseRule(_rule) {
      var _this2 = this;

      var rule = getRule(_rule);
      Object.defineProperties(rule, {
        __origin__: enumerable(_rule, true)
      });
      fullRule(rule);
      this.appendValue(rule);
      [rule, rule['prefix'], rule['suffix']].forEach(function (item) {
        if (!item) {
          return;
        }

        _this2.loadFn(item, rule);
      });
      this.loadCtrl(rule);

      if (rule.update) {
        rule.update = parseFn(rule.update);
      }

      return rule;
    },
    loadFn: function loadFn(item, rule) {
      var _this3 = this;

      ['on', 'props', 'deep'].forEach(function (k) {
        item[k] && _this3.parseInjectEvent(rule, item[k]);
      });
    },
    loadCtrl: function loadCtrl(rule) {
      rule.control && rule.control.forEach(function (ctrl) {
        if (ctrl.handle) {
          ctrl.handle = parseFn(ctrl.handle);
        }
      });
    },
    syncProp: function syncProp(ctx) {
      var _this4 = this;

      var rule = ctx.rule;
      is.trueArray(rule.sync) && mergeProps([{
        on: rule.sync.reduce(function (pre, prop) {
          pre[_typeof(prop) === 'object' && prop.event || "update:".concat(prop)] = function (val) {
            rule.props[_typeof(prop) === 'object' && prop.prop || prop] = val;

            _this4.vm.emit('sync', prop, val, rule, _this4.fapi);
          };

          return pre;
        }, {})
      }], ctx.computed);
    },
    loadRule: function loadRule() {
      var _this5 = this;

      // console.warn('%c load', 'color:blue');
      this.cycleLoad = false;
      this.loading = true;

      if (this.pageEnd) {
        this.bus.$emit('load-start');
      }

      this.deferSyncValue(function () {
        _this5._loadRule(_this5.rules);

        _this5.loading = false;

        if (_this5.cycleLoad && _this5.pageEnd) {
          return _this5.loadRule();
        }

        _this5.syncForm();

        if (_this5.pageEnd) {
          _this5.bus.$emit('load-end');
        }

        _this5.vm.setupState.renderRule();
      });
    },
    loadChildren: function loadChildren(children, parent) {
      this.cycleLoad = false;
      this.loading = true;
      this.bus.$emit('load-start');

      this._loadRule(children, parent);

      this.loading = false;

      if (this.cycleLoad) {
        return this.loadRule();
      } else {
        this.syncForm();
        this.bus.$emit('load-end');
      }

      this.$render.clearCache(parent);
    },
    _loadRule: function _loadRule(rules, parent) {
      var _this6 = this;

      var preIndex = function preIndex(i) {
        var pre = rules[i - 1];

        if (!pre || !pre.__fc__) {
          return i > 0 ? preIndex(i - 1) : -1;
        }

        var index = _this6.sort.indexOf(pre.__fc__.id);

        return index > -1 ? index : preIndex(i - 1);
      };

      var loadChildren = function loadChildren(children, parent) {
        if (is.trueArray(children)) {
          _this6._loadRule(children, parent);
        }
      };

      var ctxs = rules.map(function (_rule, index) {
        if (parent && !is.Object(_rule)) return;
        if (!_this6.pageEnd && !parent && index >= _this6.first) return;

        if (_rule.__fc__ && _rule.__fc__.root === rules && _this6.ctxs[_rule.__fc__.id]) {
          loadChildren(_rule.__fc__.loadChildrenPending(), _rule.__fc__);
          return _rule.__fc__;
        }

        var rule = getRule(_rule);

        var isRepeat = function isRepeat() {
          return !!(rule.field && _this6.fieldCtx[rule.field] && _this6.fieldCtx[rule.field][0] !== _rule.__fc__);
        };

        _this6.fc.targetFormDriver('loadRule', {
          rule: rule,
          api: _this6.api
        }, _this6.fc);

        _this6.ruleEffect(rule, 'init', {
          repeat: isRepeat()
        });

        if (isRepeat()) {
          _this6.vm.emit('repeat-field', _rule, _this6.api);
        }

        var ctx;
        var isCopy = false;
        var isInit = !!_rule.__fc__;
        var defaultValue = rule.value;

        if (isInit) {
          ctx = _rule.__fc__;
          defaultValue = ctx.defaultValue;

          if (ctx.deleted) {
            if (isCtrl(ctx)) {
              return;
            }

            ctx.update(_this6);
          } else {
            if (!ctx.check(_this6)) {
              if (isCtrl(ctx)) {
                return;
              }

              rules[index] = _rule = _rule._clone ? _rule._clone() : parseJson(toJson(_rule));
              ctx = null;
              isCopy = true;
            }
          }
        }

        if (!ctx) {
          var _rule2 = _this6.parseRule(_rule);

          ctx = new RuleContext(_this6, _rule2, defaultValue);

          _this6.bindParser(ctx);
        } else {
          if (ctx.originType !== ctx.rule.type) {
            ctx.updateType();
          }

          _this6.bindParser(ctx);

          _this6.appendValue(ctx.rule);

          if (ctx.parent && ctx.parent !== parent) {
            _this6.rmSubRuleData(ctx);
          }
        }

        _this6.parseEmit(ctx);

        _this6.syncProp(ctx);

        ctx.parent = parent || null;
        ctx.root = rules;

        _this6.setCtx(ctx);

        if (!isCopy && !isInit) {
          _this6.effect(ctx, 'load');

          _this6.targetHook(ctx, 'load');
        }

        _this6.effect(ctx, 'created');

        var _load = ctx.loadChildrenPending();

        ctx.parser.loadChildren === false || loadChildren(_load, ctx);

        if (!parent) {
          var _preIndex = preIndex(index);

          if (_preIndex > -1 || !index) {
            _this6.sort.splice(_preIndex + 1, 0, ctx.id);
          } else {
            _this6.sort.push(ctx.id);
          }
        }

        var r = ctx.rule;

        if (!ctx.updated) {
          ctx.updated = true;

          if (is.Function(r.update)) {
            _this6.bus.$once('load-end', function () {
              _this6.refreshUpdate(ctx, r.value, 'init');
            });
          }

          _this6.effect(ctx, 'loaded');
        } // if (ctx.input)
        //     Object.defineProperty(r, 'value', this.valueHandle(ctx));


        if (_this6.refreshControl(ctx)) _this6.cycleLoad = true;
        return ctx;
      }).filter(function (v) {
        return !!v;
      });

      if (parent) {
        parent.children = ctxs;
      }
    },
    refreshControl: function refreshControl(ctx) {
      return ctx.input && ctx.rule.control && this.useCtrl(ctx);
    },
    useCtrl: function useCtrl(ctx) {
      var _this7 = this;

      var controls = getCtrl(ctx),
          validate = [],
          api = this.api;
      if (!controls.length) return false;

      var _loop = function _loop(i) {
        var control = controls[i],
            handleFn = control.handle || function (val) {
          return (condition[control.condition || '=='] || condition['=='])(val, control.value);
        };

        if (!is.trueArray(control.rule)) return "continue";

        var data = _objectSpread2(_objectSpread2({}, control), {}, {
          valid: invoke(function () {
            return handleFn(ctx.rule.value, api);
          }),
          ctrl: findCtrl(ctx, control.rule),
          isHidden: is.String(control.rule[0])
        });

        if (data.valid && data.ctrl || !data.valid && !data.ctrl && !data.isHidden) return "continue";
        validate.push(data);
      };

      for (var i = 0; i < controls.length; i++) {
        var _ret = _loop(i);

        if (_ret === "continue") continue;
      }

      if (!validate.length) return false;
      var hideLst = [];
      var flag = false;
      this.deferSyncValue(function () {
        validate.reverse().forEach(function (_ref) {
          var isHidden = _ref.isHidden,
              valid = _ref.valid,
              rule = _ref.rule,
              prepend = _ref.prepend,
              append = _ref.append,
              child = _ref.child,
              ctrl = _ref.ctrl,
              method = _ref.method;

          if (isHidden) {
            valid ? ctx.ctrlRule.push({
              __ctrl: true,
              children: rule,
              valid: valid
            }) : ctrl && ctx.ctrlRule.splice(ctx.ctrlRule.indexOf(ctrl) >>> 0, 1);
            hideLst[valid ? 'push' : 'unshift'](function () {
              if (method === 'disabled' || method === 'enabled') {
                _this7.api.disabled(!valid, rule);
              } else if (method === 'display' || method === 'show') {
                _this7.api.display(valid, rule);
              } else if (method === 'required') {
                rule.forEach(function (item) {
                  _this7.api.setEffect(item, 'required', valid);
                });

                if (!valid) {
                  _this7.api.clearValidateState(rule);
                }
              } else if (method === 'if') {
                _this7.api.hidden(!valid, rule);
              } else {
                _this7.api.hidden(!valid, rule);
              }
            });
            return;
          }

          if (valid) {
            flag = true;
            var ruleCon = {
              type: 'fragment',
              "native": true,
              __ctrl: true,
              children: rule
            };
            ctx.ctrlRule.push(ruleCon);

            _this7.bus.$once('load-start', function () {
              // this.cycleLoad = true;
              if (prepend) {
                api.prepend(ruleCon, prepend, child);
              } else if (append || child) {
                api.append(ruleCon, append || ctx.id, child);
              } else {
                ctx.root.splice(ctx.root.indexOf(ctx.origin) + 1, 0, ruleCon);
              }
            });
          } else {
            ctx.ctrlRule.splice(ctx.ctrlRule.indexOf(ctrl), 1);
            var ctrlCtx = byCtx(ctrl);
            ctrlCtx && ctrlCtx.rm();
          }
        });
      });

      if (hideLst.length) {
        if (this.loading) {
          hideLst.length && this.bus.$once('load-end', function () {
            hideLst.forEach(function (v) {
              return v();
            });
          });
        } else {
          hideLst.length && nextTick(function () {
            hideLst.forEach(function (v) {
              return v();
            });
          });
        }
      }

      this.vm.emit('control', ctx.origin, this.api);
      this.effect(ctx, 'control');
      return flag;
    },
    reloadRule: function reloadRule(rules) {
      return this._reloadRule(rules);
    },
    _reloadRule: function _reloadRule(rules) {
      var _this8 = this;

      // console.warn('%c reload', 'color:red');
      if (!rules) rules = this.rules;

      var ctxs = _objectSpread2({}, this.ctxs);

      this.clearNextTick();
      this.initData(rules);
      this.fc.rules = rules;
      this.deferSyncValue(function () {
        _this8.bus.$once('load-end', function () {
          Object.keys(ctxs).filter(function (id) {
            return _this8.ctxs[id] === undefined;
          }).forEach(function (id) {
            return _this8.rmCtx(ctxs[id]);
          });

          _this8.$render.clearCacheAll();
        });

        _this8.reloading = true;

        _this8.loadRule();

        _this8.reloading = false;

        _this8.refresh();

        _this8.bus.$emit('reloading', _this8.api);
      });
      this.bus.$off('next-tick', this.nextReload);
      this.bus.$once('next-tick', this.nextReload);
      this.bus.$emit('update', this.api);
    },
    //todo 组件生成全部通过 alias
    refresh: function refresh() {
      this.vm.setupState.refresh();
    }
  });
}

function fullRule(rule) {
  var def = baseRule();
  Object.keys(def).forEach(function (k) {
    if (!hasProperty(rule, k)) rule[k] = def[k];
  });
  return rule;
}

function getCtrl(ctx) {
  var control = ctx.rule.control || [];
  if (is.Object(control)) return [control];else return control;
}

function findCtrl(ctx, rule) {
  for (var i = 0; i < ctx.ctrlRule.length; i++) {
    var ctrl = ctx.ctrlRule[i];
    if (ctrl.children === rule) return ctrl;
  }
}

function isCtrl(ctx) {
  return !!ctx.rule.__ctrl;
}

function useInput(Handler) {
  extend(Handler.prototype, {
    setValue: function setValue(ctx, value, formValue, setFlag) {
      var _this = this;

      if (ctx.deleted) return;
      ctx.rule.value = value;
      this.changeStatus = true;
      this.nextRefresh();
      this.$render.clearCache(ctx);
      this.setFormData(ctx, formValue);
      this.syncValue();
      this.valueChange(ctx, value);
      this.vm.emit('change', ctx.field, value, ctx.origin, this.api, setFlag || false);
      this.effect(ctx, 'value');
      this.targetHook(ctx, 'value', {
        value: value
      });
      this.emitEvent('change', ctx.field, value, {
        rule: ctx.origin,
        api: this.api,
        setFlag: setFlag || false
      });

      if (setFlag) {
        nextTick(function () {
          nextTick(function () {
            nextTick(function () {
              _this.api.clearValidateState(ctx.id);
            });
          });
        });
      }

      this.$manager.fieldChange(ctx, value, formValue, setFlag);
    },
    onInput: function onInput(ctx, value) {
      var val;

      if (ctx.input && (this.isQuote(ctx, val = ctx.parser.toValue(value, ctx)) || this.isChange(ctx, value))) {
        this.setValue(ctx, val, value);
      }
    },
    onUpdateValue: function onUpdateValue(ctx, data) {
      var _this2 = this;

      this.deferSyncValue(function () {
        var group = ctx.getParentGroup();
        var subForm = group ? _this2.subRuleData[group.id] : null;
        var subData = {};
        Object.keys(data || {}).forEach(function (k) {
          if (subForm && hasProperty(subForm, k)) {
            subData[k] = data[k];
          } else if (hasProperty(_this2.api.form, k)) {
            _this2.api.form[k] = data[k];
          } else if (_this2.api.top !== _this2.api && hasProperty(_this2.api.top.form, k)) {
            _this2.api.top.form[k] = data[k];
          }
        });

        if (Object.keys(subData).length) {
          _this2.api.setChildrenFormData(group.rule, subData);
        }
      });
    },
    onBaseInput: function onBaseInput(ctx, value) {
      this.setFormData(ctx, value);
      ctx.modelValue = value;
      this.nextRefresh();
      this.$render.clearCache(ctx);
    },
    setFormData: function setFormData(ctx, value) {
      ctx.modelValue = value;
      var group = ctx.getParentGroup();

      if (group) {
        if (!this.subRuleData[group.id]) {
          this.subRuleData[group.id] = {};
        }

        this.subRuleData[group.id][ctx.field] = ctx.rule.value;
      }

      $set(this.formData, ctx.id, value);
    },
    rmSubRuleData: function rmSubRuleData(ctx) {
      var group = ctx.getParentGroup();

      if (group && this.subRuleData[group.id]) {
        delete this.subRuleData[group.id][ctx.field];
      }
    },
    getFormData: function getFormData(ctx) {
      return this.formData[ctx.id];
    },
    syncForm: function syncForm() {
      var _this3 = this;

      var data = reactive({});
      var fields = this.fields();
      var ignoreFields = [];

      if (this.options.appendValue !== false) {
        Object.keys(this.appendData).reduce(function (initial, field) {
          if (fields.indexOf(field) === -1) {
            initial[field] = toRef(_this3.appendData, field);
          }

          return initial;
        }, data);
      }

      fields.reduce(function (initial, field) {
        var ctx = (_this3.fieldCtx[field] || []).filter(function (ctx) {
          return !_this3.isIgnore(ctx);
        })[0];

        if (!ctx) {
          ctx = _this3.fieldCtx[field][0];
          ignoreFields.push(field);
        }

        initial[field] = toRef(ctx.rule, 'value');
        return initial;
      }, data);
      this.form = data;
      this.ignoreFields = ignoreFields;
      this.syncValue();
    },
    isIgnore: function isIgnore(ctx) {
      return ctx.rule.ignore === true || (ctx.rule.ignore === 'hidden' || this.options.ignoreHiddenFields) && ctx.hasHidden();
    },
    appendValue: function appendValue(rule) {
      if ((!rule.field || !hasProperty(this.appendData, rule.field)) && (!is.Undef(rule.value) || !this.options.forceCoverValue)) {
        return;
      }

      rule.value = this.appendData[rule.field];
      delete this.appendData[rule.field];
    },
    addSubForm: function addSubForm(ctx, subForm) {
      this.subForm[ctx.id] = subForm;
    },
    deferSyncValue: function deferSyncValue(fn, sync) {
      if (!this.deferSyncFn) {
        this.deferSyncFn = fn;
      }

      if (!this.deferSyncFn.sync) {
        this.deferSyncFn.sync = sync;
      }

      invoke(fn);

      if (this.deferSyncFn === fn) {
        this.deferSyncFn = null;

        if (fn.sync) {
          this.syncForm();
        }
      }
    },
    syncValue: function syncValue() {
      var _this4 = this;

      if (this.deferSyncFn) {
        return this.deferSyncFn.sync = true;
      }

      var data = {};
      Object.keys(this.form).forEach(function (k) {
        if (_this4.ignoreFields.indexOf(k) === -1) {
          data[k] = _this4.form[k];
        }
      });
      this.vm.setupState.updateValue(data);
    },
    isChange: function isChange(ctx, value) {
      return JSON.stringify(this.getFormData(ctx), strFn) !== JSON.stringify(value, strFn);
    },
    isQuote: function isQuote(ctx, value) {
      return (value instanceof Function || is.Object(value) || Array.isArray(value)) && value === ctx.rule.value;
    },
    refreshUpdate: function refreshUpdate(ctx, val, origin, field) {
      var _this5 = this;

      if (is.Function(ctx.rule.update)) {
        var state = invoke(function () {
          return ctx.rule.update(val, ctx.origin, _this5.api, {
            origin: origin || 'change',
            linkField: field
          });
        });
        if (state === undefined) return;
        ctx.rule.hidden = state === true;
      }
    },
    valueChange: function valueChange(ctx, val) {
      this.refreshRule(ctx, val);
      this.bus.$emit('change-' + ctx.field, val);
    },
    refreshRule: function refreshRule(ctx, val, origin, field) {
      if (this.refreshControl(ctx)) {
        this.$render.clearCacheAll();
        this.loadRule();
        this.bus.$emit('update', this.api);
        this.refresh();
      }

      this.refreshUpdate(ctx, val, origin, field);
    },
    appendLink: function appendLink(ctx) {
      var _this6 = this;

      var link = ctx.rule.link;
      is.trueArray(link) && link.forEach(function (field) {
        var fn = function fn() {
          return _this6.refreshRule(ctx, ctx.rule.value, 'link', field);
        };

        _this6.bus.$on('change-' + field, fn);

        ctx.linkOn.push(function () {
          return _this6.bus.$off('change-' + field, fn);
        });
      });
    },
    fields: function fields() {
      return Object.keys(this.fieldCtx);
    }
  });
}

function strFn(key, val) {
  return typeof val === 'function' ? '' + val : val;
}

var BaseParser = {
  init: function init(ctx) {},
  toFormValue: function toFormValue(value, ctx) {
    return value;
  },
  toValue: function toValue(formValue, ctx) {
    return formValue;
  },
  mounted: function mounted(ctx) {},
  render: function render(children, ctx) {
    if (ctx.$handle.fc.renderDriver && ctx.$handle.fc.renderDriver.defaultRender) {
      return ctx.$handle.fc.renderDriver.defaultRender(ctx, children);
    }

    return ctx.$render.defaultRender(ctx, children);
  },
  preview: function preview(children, ctx) {
    if (ctx.$handle.fc.renderDriver && ctx.$handle.fc.renderDriver.defaultPreview) {
      return ctx.$handle.fc.renderDriver.defaultPreview(ctx, children);
    }

    return this.render(children, ctx);
  },
  mergeProp: function mergeProp(ctx) {}
};

var noneKey = ['field', 'value', 'vm', 'template', 'name', 'config', 'control', 'inject', 'sync', 'payload', 'optionsTo', 'update', 'slotUpdate', 'computed', 'component', 'cache'];
var oldValueTag = Symbol('oldValue');
function useContext(Handler) {
  extend(Handler.prototype, {
    getCtx: function getCtx(id) {
      return this.getFieldCtx(id) || this.getNameCtx(id)[0] || this.ctxs[id];
    },
    getCtxs: function getCtxs(id) {
      return this.fieldCtx[id] || this.nameCtx[id] || (this.ctxs[id] ? [this.ctxs[id]] : []);
    },
    setIdCtx: function setIdCtx(ctx, key, type) {
      var field = "".concat(type, "Ctx");

      if (!this[field][key]) {
        this[field][key] = [ctx];
      } else {
        this[field][key].push(ctx);
      }
    },
    rmIdCtx: function rmIdCtx(ctx, key, type) {
      var field = "".concat(type, "Ctx");
      var lst = this[field][key];
      if (!lst) return false;
      var flag = lst.splice(lst.indexOf(ctx) >>> 0, 1).length > 0;

      if (!lst.length) {
        delete this[field][key];
      }

      return flag;
    },
    getFieldCtx: function getFieldCtx(field) {
      return (this.fieldCtx[field] || [])[0];
    },
    getNameCtx: function getNameCtx(name) {
      return this.nameCtx[name] || [];
    },
    setCtx: function setCtx(ctx) {
      var id = ctx.id,
          field = ctx.field,
          name = ctx.name,
          rule = ctx.rule;
      this.ctxs[id] = ctx;
      name && this.setIdCtx(ctx, name, 'name');
      if (!ctx.input) return;
      this.setIdCtx(ctx, field, 'field');
      this.setFormData(ctx, ctx.parser.toFormValue(rule.value, ctx));

      if (this.isMounted && !this.reloading) {
        this.vm.emit('change', ctx.field, rule.value, ctx.origin, this.api, false, true);
      }
    },
    getParser: function getParser(ctx) {
      var list = this.fc.parsers;
      var renderDriver = this.fc.renderDriver;

      if (renderDriver) {
        var parsers = renderDriver.parsers || {};
        var parser = parsers[ctx.originType] || parsers[toCase(ctx.type)] || parsers[ctx.trueType];

        if (parser) {
          return parser;
        }
      }

      return list[ctx.originType] || list[toCase(ctx.type)] || list[ctx.trueType] || BaseParser;
    },
    bindParser: function bindParser(ctx) {
      ctx.setParser(this.getParser(ctx));
    },
    getType: function getType(alias) {
      var map = this.fc.CreateNode.aliasMap;
      var type = map[alias] || map[toCase(alias)] || alias;
      return toCase(type);
    },
    noWatch: function noWatch(fn) {
      if (!this.noWatchFn) {
        this.noWatchFn = fn;
      }

      invoke(fn);

      if (this.noWatchFn === fn) {
        this.noWatchFn = null;
      }
    },
    watchCtx: function watchCtx(ctx) {
      var _this = this;

      var all = attrs();
      all.filter(function (k) {
        return k[0] !== '_' && k[0] !== '$' && noneKey.indexOf(k) === -1;
      }).forEach(function (key) {
        var ref = toRef(ctx.rule, key);
        var flag = key === 'children';
        ctx.refRule[key] = ref;
        ctx.watch.push(watch(flag ? function () {
          return is.Function(ref.value) ? ref.value : _toConsumableArray(ref.value || []);
        } : function () {
          return ref.value;
        }, function (_, o) {
          var n = ref.value;
          if (_this.isBreakWatch()) return;

          if (flag && ctx.parser.loadChildren === false) {
            _this.$render.clearCache(ctx);

            _this.nextRefresh();

            return;
          }

          _this.watching = true;
          nextTick(function () {
            _this.targetHook(ctx, 'watch', {
              key: key,
              oldValue: o,
              newValue: n
            });
          });

          if (key === 'hidden' && Boolean(n) !== Boolean(o)) {
            _this.$render.clearCacheAll();

            nextTick(function () {
              _this.targetHook(ctx, 'hidden', {
                value: n
              });
            });
          }

          if (key === 'ignore' && ctx.input || key === 'hidden' && (ctx.rule.ignore === 'hidden' || _this.options.ignoreHiddenFields)) {
            _this.syncForm();
          } else if (key === 'link') {
            ctx.link();
            return;
          } else if (['props', 'on', 'deep'].indexOf(key) > -1) {
            _this.parseInjectEvent(ctx.rule, n || {});

            if (key === 'props' && ctx.input) {
              _this.setFormData(ctx, ctx.parser.toFormValue(ctx.rule.value, ctx));
            }
          } else if (key === 'emit') {
            _this.parseEmit(ctx);
          } else if (['prefix', 'suffix'].indexOf(key) > -1) n && _this.loadFn(n, ctx.rule);else if (key === 'type') {
            ctx.updateType();

            _this.bindParser(ctx);
          } else if (flag) {
            if (is.Function(o)) {
              o = ctx.getPending('children', []);
            }

            if (is.Function(n)) {
              n = ctx.loadChildrenPending();
            }

            _this.updateChildren(ctx, n, o);
          }

          _this.$render.clearCache(ctx);

          _this.refresh();

          _this.watching = false;
        }, {
          deep: !flag,
          sync: flag
        }));
      });
      ctx.refRule['__$title'] = computed(function () {
        var title = (_typeof(ctx.rule.title) === 'object' ? ctx.rule.title.title : ctx.rule.title) || '';

        if (title) {
          var match = title.match(/^\{\{\s*\$t\.(.+)\s*\}\}$/);

          if (match) {
            title = _this.api.t(match[1]);
          }
        }

        return title;
      });
      ctx.refRule['__$info'] = computed(function () {
        var info = (_typeof(ctx.rule.info) === 'object' ? ctx.rule.info.info : ctx.rule.info) || '';

        if (info) {
          var match = info.match(/^\{\{\s*\$t\.(.+)\s*\}\}$/);

          if (match) {
            info = _this.api.t(match[1]);
          }
        }

        return info;
      });
      ctx.refRule['__$validate'] = computed(function () {
        var t = function t(msg) {
          var match = msg.match(/^\{\{\s*\$t\.(.+)\s*\}\}$/);

          if (match) {
            var _ctx$refRule, _ctx$refRule$__$title;

            return _this.api.t(match[1], {
              title: (_ctx$refRule = ctx.refRule) === null || _ctx$refRule === void 0 ? void 0 : (_ctx$refRule$__$title = _ctx$refRule.__$title) === null || _ctx$refRule$__$title === void 0 ? void 0 : _ctx$refRule$__$title.value
            });
          }

          return msg;
        };

        return toArray(ctx.rule.validate).map(function (item) {
          var temp = _objectSpread2({}, item);

          if (temp.message) {
            temp.message = t(temp.message);
          }

          if (is.Function(temp.validator)) {
            var that = ctx;

            temp.validator = function () {
              var _item$validator;

              for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
                args[_key] = arguments[_key];
              }

              return (_item$validator = item.validator).call.apply(_item$validator, [{
                that: this,
                id: that.id,
                field: that.field,
                rule: that.rule,
                api: that.$handle.api
              }].concat(args));
            };
          }

          if (temp.adapter) {
            if (_typeof(temp.error) === 'object') {
              var msg = _objectSpread2({}, temp.error);

              Object.keys(msg).forEach(function (key) {
                msg[key] = t(msg[key]);
              });
              temp.error = msg;
            }

            return _this.adapterValidate(temp, ctx);
          }

          return temp;
        });
      });

      if (ctx.input) {
        var val = toRef(ctx.rule, 'value');
        ctx.watch.push(watch(function () {
          return val.value;
        }, function () {
          var formValue = ctx.parser.toFormValue(val.value, ctx);

          if (_this.isChange(ctx, formValue)) {
            _this.setValue(ctx, val.value, formValue, true);
          }
        }));
      }

      this.bus.$once('load-end', function () {
        var computedRule = ctx.rule.computed;

        if (!computedRule) {
          return;
        }

        if (_typeof(computedRule) !== 'object') {
          computedRule = {
            value: computedRule
          };
        }

        Object.keys(computedRule).forEach(function (k) {
          var computedValue = computed(function () {
            var item = computedRule[k];
            if (!item) return undefined;

            var value = _this.compute(ctx, item);

            if ((item.linkage || item.linkageVariable) && value === oldValueTag) {
              return oldValueTag;
            }

            return value;
          });

          var callback = function callback(n) {
            if (k === 'value') {
              _this.onInput(ctx, n);
            } else if (k[0] === '$') {
              _this.api.setEffect(ctx.id, k, n);
            } else {
              deepSet(ctx.rule, k, n);
            }
          };

          if (k === 'value' ? [undefined, null, ''].indexOf(ctx.rule.value) > -1 : computedValue.value !== deepGet(ctx.rule, k)) {
            callback(computedValue.value);
          }

          ctx.watch.push(watch(computedValue, function (n) {
            if (n === oldValueTag) {
              return;
            }

            setTimeout(function () {
              callback(n);
            });
          }, {
            deep: true
          }));
        });
      });
      this.watchEffect(ctx);
    },
    adapterValidate: function adapterValidate(validate, ctx) {
      var _this2 = this;

      var validator = function validator(value, callback) {
        var before = validate.beforeValidate && invoke(function () {
          return validate.beforeValidate({
            value: value,
            api: _this2.api,
            validate: validate,
            rule: ctx.rule
          });
        });

        if (before === false) {
          callback();
        } else {
          var key = _this2.validator(ctx, value, validate);

          if (!key) {
            if (validate.validator) {
              var res = validate.validator && invoke(function () {
                return validate.validator(value, callback);
              });

              if (res && is.Function(res.then)) {
                res.then(function () {
                  return callback();
                })["catch"](function (e) {
                  return callback(e);
                });
              }
            } else {
              callback();
            }
          } else {
            var message = '';

            if (_typeof(validate.error) === 'object') {
              message = validate.error[key] || validate.error["default"];
            }

            if (!message && typeof validate.message === 'string') {
              message = validate.message;
            }

            if (!message) {
              message = _this2.getValidateMessage(ctx, {
                key: key,
                rule: validate[key]
              });
            }

            callback(message);
          }
        }
      };

      return this.$manager.adapterValidate({
        required: validate.required,
        message: validate.message,
        trigger: validate.trigger
      }, validator);
    },
    getValidateMessage: function getValidateMessage(ctx, invalid) {
      var _ctx$refRule2, _ctx$refRule2$__$titl, _this$api$t;

      var formatRule = Array.isArray(invalid.rule) ? invalid.rule.join(',') : '' + invalid.rule;
      return this.api.t(invalid.key === 'required' ? invalid.key : 'validate.' + invalid.key, (_this$api$t = {}, _defineProperty(_this$api$t, invalid.key, formatRule), _defineProperty(_this$api$t, "title", (_ctx$refRule2 = ctx.refRule) === null || _ctx$refRule2 === void 0 ? void 0 : (_ctx$refRule2$__$titl = _ctx$refRule2.__$title) === null || _ctx$refRule2$__$titl === void 0 ? void 0 : _ctx$refRule2$__$titl.value), _this$api$t));
    },
    validator: function validator(ctx, value, validate) {
      var _this3 = this;

      var isEmpty = is.empty(value);

      if (isEmpty) {
        if (validate.required) {
          return 'required';
        }

        return;
      }

      var _loop = function _loop() {
        var _Object$entries$_i = _slicedToArray(_Object$entries[_i], 2),
            key = _Object$entries$_i[0],
            rule = _Object$entries$_i[1];

        switch (key) {
          case 'len':
          case 'maxLen':
          case 'minLen':
            var check = function check(val) {
              if (key === 'len') {
                return val === rule;
              } else if (key === 'maxLen') {
                return val <= rule;
              } else {
                return val >= rule;
              }
            };

            if (Array.isArray(value)) {
              if (!check(value.length)) {
                return {
                  v: key
                };
              }
            } else if (_typeof(value) === 'object') {
              return {
                v: key
              };
            } else if (!check(('' + value).length)) {
              return {
                v: key
              };
            }

            break;

          case 'pattern':
            var reg = typeof rule === 'string' ? new RegExp(rule) : rule;

            if (!reg.test('' + value)) {
              return {
                v: key
              };
            }

            break;

          case 'uppercase':
            if (rule && (typeof value !== 'string' || !/^[A-Z]*$/.test(value))) {
              return {
                v: key
              };
            }

            break;

          case 'lowercase':
            if (rule && (typeof value !== 'string' || !/^[a-z]*$/.test(value))) {
              return {
                v: key
              };
            }

            break;

          case 'min':
          case 'max':
          case 'positive':
          case 'negative':
          case 'integer':
          case 'number':
            var num = Number(value);

            if (Number.isNaN(num)) {
              return {
                v: key
              };
            }

            if (key === 'min' && num < rule) {
              return {
                v: key
              };
            }

            if (key === 'max' && num > rule) {
              return {
                v: key
              };
            }

            if (key === 'positive' && num <= 0) {
              return {
                v: key
              };
            }

            if (key === 'negative' && num >= 0) {
              return {
                v: key
              };
            }

            if (key === 'integer' && !Number.isInteger(num)) {
              return {
                v: key
              };
            }

            break;

          case 'equal':
            if (value !== rule) {
              return {
                v: key
              };
            }

            break;

          case 'enum':
            if (Array.isArray(rule) && !rule.includes(value)) {
              return {
                v: key
              };
            }

            break;

          case 'hasKeys':
            if (_typeof(value) !== 'object' || Array.isArray(rule) && rule.some(function (key) {
              return !(key in value);
            })) {
              return {
                v: key
              };
            }

            break;

          case 'email':
            var regexEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

            if (!regexEmail.test('' + value)) {
              return {
                v: key
              };
            }

            break;

          case 'url':
            var regexUrl = new RegExp("^(?!mailto:)(?:(?:http|https|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?:(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[0-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))|localhost)(?::\\d{2,5})?(?:(/|\\?|#)[^\\s]*)?$", 'i');

            if (!regexUrl.test('' + value)) {
              return {
                v: key
              };
            }

            break;

          case 'ip':
            var regexIp = /^(2(5[0-5]{1}|[0-4]\d{1})|[0-1]?\d{1,2})(\.(2(5[0-5]{1}|[0-4]\d{1})|[0-1]?\d{1,2})){3}$/;

            if (!regexIp.test('' + value)) {
              return {
                v: key
              };
            }

            break;

          case 'phone':
            var regexPhone = /^(?:(?:\+|00)86)?1[3-9]\d{9}$/;

            if (!regexPhone.test('' + value)) {
              return {
                v: key
              };
            }

            break;

          case 'computed':
            if (!_this3.compute(ctx, rule)) {
              return {
                v: key
              };
            }

            break;
        }
      };

      for (var _i = 0, _Object$entries = Object.entries(validate); _i < _Object$entries.length; _i++) {
        var _ret = _loop();

        if (_typeof(_ret) === "object") return _ret.v;
      }
    },
    compute: function compute(ctx, item) {
      var _this4 = this;

      var fn;

      if (_typeof(item) === 'object') {
        var group = ctx.getParentGroup();

        var checkCondition = function checkCondition(item) {
          item = Array.isArray(item) ? {
            mode: 'AND',
            group: item
          } : item;

          if (!is.trueArray(item.group)) {
            return true;
          }

          var or = item.mode === 'OR';
          var valid = true;

          var _loop2 = function _loop2(i) {
            var one = item.group[i];
            var flag = void 0;
            var field = null;
            var variableVal = null;

            if (one.variable) {
              variableVal = _this4.fc.getLoadData(one.variable);
            } else if (one.field) {
              field = convertFieldToConditions(one.field || '');
            } else if (!one.mode) {
              return {
                v: true
              };
            }

            var compare = one.compare;

            if (compare) {
              compare = convertFieldToConditions(compare || '');
            }

            if (one.mode) {
              flag = checkCondition(one);
            } else if (!condition[one.condition]) {
              flag = false;
            } else if (is.Function(one.handler)) {
              flag = invoke(function () {
                return one.handler(_this4.api, ctx.rule);
              });
            } else {
              flag = invoke(function () {
                return new Function('$condition', '$variableVal', '$val', '$form', '$scope', '$group', '$rule', "with($form){with($scope){with(this){with($group){ return $condition['".concat(one.condition, "'](").concat(one.variable ? '$variableVal' : field, ", ").concat(compare ? compare : '$val', "); }}}}")).call(_this4.api.form, condition, variableVal, one.value, _this4.api.top.form, _this4.api.top === _this4.api.scope ? {} : _this4.api.scope.form, group ? _this4.subRuleData[group.id] || {} : {}, ctx.rule);
              });
            }

            if (or && flag) {
              return {
                v: true
              };
            }

            if (!or) {
              valid = valid && flag;
            }
          };

          for (var i = 0; i < item.group.length; i++) {
            var _ret2 = _loop2(i);

            if (_typeof(_ret2) === "object") return _ret2.v;
          }

          return or ? false : valid;
        };

        var val = checkCondition(item);
        val = item.invert === true ? !val : val;

        if (item.linkage) {
          return val ? invoke(function () {
            return _this4.computeValue(item.linkage, ctx, group);
          }, undefined) : oldValueTag;
        } else if (item.linkageVariable) {
          return val ? invoke(function () {
            return _this4.fc.getLoadData(item.linkageVariable);
          }, undefined) : oldValueTag;
        }

        return val;
      } else if (is.Function(item)) {
        fn = function fn() {
          return item(_this4.api.form, _this4.api, ctx.rule);
        };
      } else {
        var _group = ctx.getParentGroup();

        fn = function fn() {
          return _this4.computeValue(item, ctx, _group);
        };
      }

      return invoke(fn, undefined);
    },
    computeValue: function computeValue(str, ctx, group) {
      var _this5 = this;

      var that = this;
      var formulas = Object.keys(this.fc.formulas).reduce(function (obj, k) {
        obj[k] = function () {
          var _that$fc$formulas$k;

          for (var _len2 = arguments.length, args = new Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
            args[_key2] = arguments[_key2];
          }

          return (_that$fc$formulas$k = that.fc.formulas[k]).call.apply(_that$fc$formulas$k, [{
            that: this,
            rule: ctx.rule,
            api: that.api,
            fc: that.fc
          }].concat(args));
        };

        return obj;
      }, {});
      return invoke(function () {
        return new Function('$formulas', '$form', '$scope', '$group', '$rule', '$api', "with($form){with($scope){with(this){with($group){with($formulas){ return ".concat(str, " }}}}}")).call(_this5.api.form, formulas, _this5.api.top.form, _this5.api.top === _this5.api.scope ? {} : _this5.api.scope.form, group ? _this5.subRuleData[group.id] || {} : {}, ctx.rule, _this5.api);
      }, undefined);
    },
    updateChildren: function updateChildren(ctx, n, o) {
      var _this6 = this;

      this.deferSyncValue(function () {
        o && o.forEach(function (child) {
          if ((n || []).indexOf(child) === -1 && child && !is.String(child) && child.__fc__ && child.__fc__.parent === ctx) {
            _this6.rmCtx(child.__fc__);
          }
        });

        if (is.trueArray(n)) {
          _this6.loadChildren(n, ctx);

          _this6.bus.$emit('update', _this6.api);
        }
      });
    },
    rmSub: function rmSub(sub) {
      var _this7 = this;

      is.trueArray(sub) && sub.forEach(function (r) {
        r && r.__fc__ && _this7.rmCtx(r.__fc__);
      });
    },
    rmCtx: function rmCtx(ctx) {
      var _this8 = this;

      if (ctx.deleted) return;
      var id = ctx.id,
          field = ctx.field,
          input = ctx.input,
          name = ctx.name;
      $del(this.ctxs, id);
      $del(this.formData, id);
      $del(this.subForm, id);
      $del(this.vm.setupState.ctxInject, id);
      var group = ctx.getParentGroup();

      if (group && this.subRuleData[group.id]) {
        $del(this.subRuleData[group.id], field);
      }

      if (ctx.group) {
        $del(this.subRuleData, id);
      }

      input && this.rmIdCtx(ctx, field, 'field');
      name && this.rmIdCtx(ctx, name, 'name');

      if (input && !hasProperty(this.fieldCtx, field)) {
        $del(this.form, field);
      }

      this.deferSyncValue(function () {
        if (!_this8.reloading) {
          if (ctx.parser.loadChildren !== false) {
            var children = ctx.getPending('children', ctx.rule.children);

            if (is.trueArray(children)) {
              children.forEach(function (h) {
                return h && h.__fc__ && _this8.rmCtx(h.__fc__);
              });
            }
          }

          if (ctx.root === _this8.rules) {
            _this8.vm.setupState.renderRule();
          }
        }
      }, input);
      var index = this.sort.indexOf(id);

      if (index > -1) {
        this.sort.splice(index, 1);
      }

      this.$render.clearCache(ctx);
      ctx["delete"]();
      this.effect(ctx, 'deleted');
      this.targetHook(ctx, 'deleted');
      input && !this.fieldCtx[field] && this.vm.emit('remove-field', field, ctx.rule, this.api);
      ctx.rule.__ctrl || this.vm.emit('remove-rule', ctx.rule, this.api);
      return ctx;
    }
  });
}

function useLifecycle(Handler) {
  extend(Handler.prototype, {
    mounted: function mounted() {
      var _this = this;

      var _mounted = function _mounted() {
        _this.isMounted = true;

        _this.lifecycle('mounted');
      };

      if (this.pageEnd) {
        _mounted();
      } else {
        this.bus.$once('page-end', _mounted);
      }
    },
    lifecycle: function lifecycle(name) {
      this.fc.targetFormDriver(name, this.api, this.fc);
      this.vm.emit(name, this.api);
      this.emitEvent(name, this.api);
    },
    emitEvent: function emitEvent(name) {
      var _this$bus;

      for (var _len = arguments.length, args = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
        args[_key - 1] = arguments[_key];
      }

      var _fn = this.options[name] || this.options[toCase('on-' + name)];

      if (_fn) {
        var fn = parseFn(_fn);
        is.Function(fn) && invoke(function () {
          return fn.apply(void 0, args);
        });
      }

      (_this$bus = this.bus).$emit.apply(_this$bus, [name].concat(args));
    },
    targetHook: function targetHook(ctx, name, args) {
      var _ctx$prop,
          _ctx$prop$hook,
          _this2 = this;

      var hook = (_ctx$prop = ctx.prop) === null || _ctx$prop === void 0 ? void 0 : (_ctx$prop$hook = _ctx$prop.hook) === null || _ctx$prop$hook === void 0 ? void 0 : _ctx$prop$hook[name];

      var run = function run(on, p) {
        if (on) {
          on = Array.isArray(on) ? on : [on];
          on.forEach(function (fn) {
            invoke(function () {
              return fn(_objectSpread2(_objectSpread2({
                args: Object.values(args || {})
              }, args || {}), {}, {
                self: ctx.rule,
                rule: ctx.rule,
                parent: p === null || p === void 0 ? void 0 : p.rule,
                $f: _this2.api,
                api: _this2.api,
                option: _this2.vm.props.option
              }));
            });
          });
        }
      };

      run(hook);
      var deepName = 'deep' + upper(name);
      var parent = ctx.parent;

      while (parent) {
        var _parent$prop, _parent$prop$hook;

        var deepHook = (_parent$prop = parent.prop) === null || _parent$prop === void 0 ? void 0 : (_parent$prop$hook = _parent$prop.hook) === null || _parent$prop$hook === void 0 ? void 0 : _parent$prop$hook[deepName];
        run(deepHook, parent);
        parent = parent.parent;
      }
    }
  });
}

function useEffect(Handler) {
  extend(Handler.prototype, {
    useProvider: function useProvider() {
      var _this = this;

      var ps = this.fc.providers;
      Object.keys(ps).forEach(function (k) {
        var prop = ps[k];

        if (is.Function(prop)) {
          prop = prop(_this.fc);
        }

        prop._c = getComponent(prop);

        _this.onEffect(prop, k);

        _this.providers[k] = prop;
      });
    },
    onEffect: function onEffect(provider, key) {
      var _this2 = this;

      var used = [];
      (provider._c || ['*']).forEach(function (name) {
        var type = name === '*' ? '*' : _this2.getType(name);
        if (used.indexOf(type) > -1) return;
        used.push(type);

        _this2.bus.$on("p:".concat(key || provider.name, ":").concat(type, ":").concat(provider.input ? 1 : 0), function (event, args) {
          provider[event] && provider[event].apply(provider, _toConsumableArray(args));
        });
      });
      provider._used = used;
    },
    watchEffect: function watchEffect(ctx) {
      var _this3 = this;

      var effect = {
        required: function required() {
          var _ctx$rule, _ctx$rule$effect;

          return (hasProperty(ctx.rule, '$required') ? ctx.rule['$required'] : (_ctx$rule = ctx.rule) === null || _ctx$rule === void 0 ? void 0 : (_ctx$rule$effect = _ctx$rule.effect) === null || _ctx$rule$effect === void 0 ? void 0 : _ctx$rule$effect.required) || false;
        }
      };
      Object.keys(ctx.rule.effect || {}).forEach(function (k) {
        effect[k] = function () {
          return ctx.rule.effect[k];
        };
      });
      Object.keys(ctx.rule).forEach(function (k) {
        if (k[0] === '$') {
          effect[k.substr(1)] = function () {
            return ctx.rule[k];
          };
        }
      });
      Object.keys(effect).forEach(function (k) {
        ctx.watch.push(watch(effect[k], function (n) {
          _this3.effect(ctx, 'watch', _defineProperty({}, k, n));
        }, {
          deep: true
        }));
      });
    },
    ruleEffect: function ruleEffect(rule, event, append) {
      this.emitEffect({
        rule: rule,
        input: !!rule.field,
        type: this.getType(rule.type)
      }, event, append);
    },
    effect: function effect(ctx, event, custom) {
      this.emitEffect({
        rule: ctx.rule,
        input: ctx.input,
        type: ctx.trueType,
        ctx: ctx,
        custom: custom
      }, event);
    },
    getEffect: function getEffect(rule, name) {
      if (hasProperty(rule, '$' + name)) {
        return rule['$' + name];
      }

      if (hasProperty(rule, 'effect') && hasProperty(rule.effect, name)) return rule.effect[name];
      return undefined;
    },
    emitEffect: function emitEffect(_ref, event, append) {
      var _this4 = this;

      var ctx = _ref.ctx,
          rule = _ref.rule,
          input = _ref.input,
          type = _ref.type,
          custom = _ref.custom;
      if (!type || ['fcFragment', 'fragment'].indexOf(type) > -1) return;
      var effect = custom ? custom : Object.keys(rule).reduce(function (i, k) {
        if (k[0] === '$') {
          i[k.substr(1)] = rule[k];
        }

        return i;
      }, _objectSpread2({}, rule.effect || {}));
      Object.keys(effect).forEach(function (attr) {
        var p = _this4.providers[attr];
        if (!p || p.input && !input) return;

        var _type;

        if (!p._c) {
          _type = '*';
        } else if (p._used.indexOf(type) > -1) {
          _type = type;
        } else {
          return;
        }

        var data = _objectSpread2({
          value: effect[attr],
          getValue: function getValue() {
            return _this4.getEffect(rule, attr);
          }
        }, append || {});

        if (ctx) {
          data.getProp = function () {
            return ctx.effectData(attr);
          };

          data.clearProp = function () {
            return ctx.clearEffectData(attr);
          };

          data.mergeProp = function (prop) {
            return mergeRule(data.getProp(), [prop]);
          };

          data.id = ctx.id;
        }

        _this4.bus.$emit("p:".concat(attr, ":").concat(_type, ":").concat(p.input ? 1 : 0), event, [data, rule, _this4.api]);
      });
    }
  });
}

function unique(arr) {
  return arr.filter(function (item, index, arr) {
    return arr.indexOf(item, 0) === index;
  });
}

function getComponent(p) {
  var c = p.components;

  if (Array.isArray(c)) {
    var arr = unique(c.filter(function (v) {
      return v !== '*';
    }));
    return arr.length ? arr : false;
  } else if (is.String(c)) return [c];else return false;
}

function Handler(fc) {
  var _this = this;

  funcProxy(this, {
    options: function options() {
      return fc.options.value || {};
    },
    bus: function bus() {
      return fc.bus;
    },
    preview: function preview() {
      if (fc.vm.props.preview != null) {
        return fc.vm.props.preview;
      } else if (fc.vm.setupState.parent && fc.vm.setupState.parent.props.preview != null) {
        return fc.vm.setupState.parent.props.preview;
      }

      return fc.options.value.preview || false;
    }
  });
  extend(this, {
    fc: fc,
    vm: fc.vm,
    watching: false,
    loading: false,
    reloading: false,
    noWatchFn: null,
    deferSyncFn: null,
    isMounted: false,
    formData: reactive({}),
    subRuleData: reactive({}),
    subForm: {},
    form: reactive({}),
    appendData: {},
    ignoreFields: [],
    providers: {},
    cycleLoad: null,
    loadedId: 1,
    nextTick: null,
    changeStatus: false,
    pageEnd: true,
    nextReload: function nextReload() {
      _this.lifecycle('reload');
    }
  });
  this.initData(fc.rules);
  this.$manager = new fc.manager(this);
  this.$render = new Render(this);
  this.api = fc.extendApiFn.reduce(function (api, fn) {
    var extendApi = invoke(function () {
      return fn(api, _this);
    });

    if (extendApi && extendApi !== api) {
      extend(api, extendApi);
    }

    return api;
  }, Api(this));
}
extend(Handler.prototype, {
  initData: function initData(rules) {
    extend(this, {
      ctxs: {},
      fieldCtx: {},
      nameCtx: {},
      sort: [],
      rules: rules
    });
  },
  init: function init() {
    this.updateAppendData();
    this.useProvider();
    this.usePage();
    this.loadRule();

    this.$manager.__init();

    this.lifecycle('created');
  },
  updateAppendData: function updateAppendData() {
    this.appendData = _objectSpread2(_objectSpread2(_objectSpread2({}, deepCopy(this.options.formData || {})), this.fc.vm.props.modelValue || {}), this.appendData);
  },
  isBreakWatch: function isBreakWatch() {
    return this.loading || this.noWatchFn || this.reloading;
  },
  globalBeforeFetch: function globalBeforeFetch(opt) {
    var _this2 = this;

    return new Promise(function (resolve, reject) {
      var source = _this2.options.beforeFetch && invoke(function () {
        return _this2.options.beforeFetch(opt, {
          api: _this2.api
        });
      });

      if (source && is.Function(source.then)) {
        source.then(resolve)["catch"](reject);
      } else {
        resolve();
      }
    });
  },
  beforeFetch: function beforeFetch(opt) {
    var _this3 = this;

    return new Promise(function (resolve, reject) {
      var res = opt && opt.beforeFetch && invoke(function () {
        return opt.beforeFetch(opt, {
          api: _this3.api
        });
      });

      if (res && is.Function(res.then)) {
        res.then(resolve)["catch"](reject);
      } else if (res === false) {
        reject();
      } else {
        resolve();
      }
    }).then(function () {
      return _this3.globalBeforeFetch(opt);
    });
  },
  beforeSubmit: function beforeSubmit(formData) {
    var _this4 = this;

    return new Promise(function (resolve, reject) {
      var res = _this4.options.beforeSubmit && invoke(function () {
        return _this4.options.beforeSubmit(formData, {
          api: _this4.api
        });
      });

      if (res && is.Function(res.then)) {
        res.then(resolve)["catch"](reject);
      } else if (res === false) {
        reject();
      } else {
        resolve();
      }
    });
  }
});
useInject(Handler);
usePage(Handler);
useRender(Handler);
useLoader(Handler);
useInput(Handler);
useContext(Handler);
useLifecycle(Handler);
useEffect(Handler);

var NAME = 'fcFragment';
var fragment = defineComponent({
  name: NAME,
  inheritAttrs: false,
  props: ['vnode'],
  render: function render() {
    return this.vnode;
  }
});

function tidyDirectives(directives) {
  return Object.keys(directives).map(function (n) {
    var data = directives[n];
    var directive = resolveDirective(n);
    if (!directive) return;
    return [directive, data.value, data.arg, data.modifiers];
  }).filter(function (v) {
    return !!v;
  });
}

function makeDirective(data, vn) {
  var directives = data.directives;
  if (!directives) return vn;

  if (!Array.isArray(directives)) {
    directives = [directives];
  }

  return withDirectives(vn, directives.reduce(function (lst, v) {
    return lst.concat(tidyDirectives(v));
  }, []));
}

function CreateNodeFactory() {
  var aliasMap = {};

  function CreateNode(handle) {
    this.vm = handle.vm;
    this.handle = handle;
  }

  extend(CreateNode.prototype, {
    make: function make(tag, data, children) {
      return makeDirective(data, this.h(tag, toProps(data), children));
    },
    makeComponent: function makeComponent(type, data, children) {
      try {
        return makeDirective(data, createVNode(type, toProps(data), children));
      } catch (e) {
        console.error(e);
        return createVNode('');
      }
    },
    h: function h(tag, data, children) {
      var vm = this.vm || getCurrentInstance();
      var isNativeTag = vm.appContext.config.isNativeTag(tag);
      var component = this.handle.fc.prop.components[tag];

      if (!component && isNativeTag) {
        delete data.formCreateInject;
      }

      try {
        return createVNode(component || (isNativeTag ? tag : resolveComponent(tag)), data, children);
      } catch (e) {
        console.error(e);
        return createVNode('');
      }
    },
    aliasMap: aliasMap
  });
  extend(CreateNode, {
    aliasMap: aliasMap,
    alias: function alias(_alias, name) {
      aliasMap[_alias] = name;
    },
    use: function use(nodes) {
      Object.keys(nodes).forEach(function (k) {
        var line = toLine(k);
        var lower = toString(k).toLocaleLowerCase();
        var v = nodes[k];
        CreateNode.alias(k, v);
        [k, line, lower].forEach(function (n) {
          CreateNode.prototype[n] = function (data, children) {
            return this.make(aliasMap[k] || n, data, children);
          };
        });
      });
    }
  });
  return CreateNode;
}

function createManager(proto) {
  var CustomManager = /*#__PURE__*/function (_Manager) {
    _inherits(CustomManager, _Manager);

    var _super = _createSuper(CustomManager);

    function CustomManager() {
      _classCallCheck(this, CustomManager);

      return _super.apply(this, arguments);
    }

    return CustomManager;
  }(Manager);

  Object.assign(CustomManager.prototype, proto);
  return CustomManager;
}
function Manager(handler) {
  extend(this, {
    $handle: handler,
    vm: handler.vm,
    options: {},
    ref: 'fcForm',
    mergeOptionsRule: {
      normal: ['form', 'row', 'info', 'submitBtn', 'resetBtn']
    }
  });
  this.updateKey();
  this.init();
}
extend(Manager.prototype, {
  __init: function __init() {
    var _this = this;

    this.$render = this.$handle.$render;

    this.$r = function () {
      var _this$$render;

      return (_this$$render = _this.$render).renderRule.apply(_this$$render, arguments);
    };
  },
  updateKey: function updateKey() {
    this.key = uniqueId();
  },
  //TODO interface
  init: function init() {},
  update: function update() {},
  beforeRender: function beforeRender() {},
  form: function form() {
    return this.vm.refs[this.ref];
  },
  adapterValidate: function adapterValidate(validate, validator) {
    validate.validator = function (rule, value, callback) {
      return validator(value, callback);
    };

    return validate;
  },
  getSlot: function getSlot(name) {
    var _fn = function _fn(vm) {
      if (vm) {
        var slot = vm.slots[name];

        if (slot) {
          return slot;
        }

        return _fn(vm.setupState.parent);
      }

      return undefined;
    };

    return _fn(this.vm);
  },
  mergeOptions: function mergeOptions(args, opt) {
    var _this2 = this;

    return mergeProps(args.map(function (v) {
      return _this2.tidyOptions(v);
    }), opt, this.mergeOptionsRule);
  },
  updateOptions: function updateOptions(options) {
    this.$handle.fc.targetFormDriver('updateOptions', options, {
      handle: this.$handle,
      api: this.$handle.api
    });
    this.options = this.mergeOptions([options], this.getDefaultOptions());
    this.update();
  },
  tidyOptions: function tidyOptions(options) {
    return options;
  },
  tidyRule: function tidyRule(ctx) {},
  mergeProp: function mergeProp(ctx) {},
  getDefaultOptions: function getDefaultOptions() {
    return {};
  },
  fieldChange: function fieldChange(ctx, value, formValue, setFlag) {},
  render: function render(children) {}
});

var loadData = function loadData(fc) {
  var loadData = {
    name: 'loadData',
    _fn: [],
    loaded: function loaded(inject, rule, api) {
      var _this = this;

      this.deleted(inject);
      nextTick(function () {
        var attrs = toArray(inject.getValue());
        var unwatchs = [];
        attrs.forEach(function (attr) {
          if (attr && (attr.attr || attr.template)) {
            var fn = function fn(get) {
              var group;

              if (rule && rule.__fc__) {
                group = rule.__fc__.getParentGroup();
              }

              var value;

              if (attr.template) {
                value = fc.$handle.loadStrVar(attr.template, get, group ? {
                  rule: rule,
                  value: fc.$handle.subRuleData[group.id] || {}
                } : null);
              } else if (attr.handler && is.Function(attr.handler)) {
                value = attr.handler(get, rule, api);
              } else {
                value = fc.$handle.loadStrVar("{{".concat(attr.attr, "}}"), get, group ? {
                  rule: rule,
                  value: fc.$handle.subRuleData[group.id] || {}
                } : null);
              }

              if ((value == null || value === '') && attr["default"] != null) {
                value = attr["default"];
              }

              if (attr.copy !== false) {
                value = deepCopy(value);
              }

              var _rule = attr.modify ? rule : inject.getProp();

              if (attr.to === 'child') {
                if (_rule.children) {
                  _rule.children[0] = value;
                } else {
                  _rule.children = [value];
                }
              } else {
                deepSet(_rule, attr.to || 'options', value);
              }

              api.sync(rule);
            };

            var callback = function callback(get) {
              return fn(get);
            };

            var unwatch = fc.watchLoadData(callback);
            fn = debounce(fn, attr.wait || 300);

            if (attr.watch !== false) {
              unwatchs.push(unwatch);
            } else {
              unwatch();
            }
          }
        });
        _this._fn[inject.id] = unwatchs;
      });
    },
    deleted: function deleted(inject) {
      if (this._fn[inject.id]) {
        this._fn[inject.id].forEach(function (un) {
          un();
        });

        delete this._fn[inject.id];
      }

      inject.clearProp();
    }
  };
  loadData.watch = loadData.loaded;
  return loadData;
};

var t = function t(fc) {
  var t = {
    name: 't',
    _fn: [],
    loaded: function loaded(inject, rule, api) {
      this.deleted(inject);
      var attrs = inject.getValue() || {};
      var unwatchs = [];
      Object.keys(attrs).forEach(function (key) {
        var attr = attrs[key];

        if (attr) {
          var isObj = _typeof(attr) === 'object';

          var fn = function fn(get) {
            var value = fc.t(isObj ? attr.attr : attr, isObj ? attr.params : null, get);

            var _rule = isObj && attr.modify ? rule : inject.getProp();

            if (key === 'child') {
              if (_rule.children) {
                _rule.children[0] = value;
              } else {
                _rule.children = [value];
              }
            } else {
              deepSet(_rule, key, value);
            }

            api.sync(rule);
          };

          var callback = function callback(get) {
            return fn(get);
          };

          var unwatch = fc.watchLoadData(callback);
          fn = debounce(fn, attr.wait || 300);

          if (attr.watch !== false) {
            unwatchs.push(unwatch);
          } else {
            unwatch();
          }
        }
      });
      this._fn[inject.id] = unwatchs;
    },
    deleted: function deleted(inject) {
      if (this._fn[inject.id]) {
        this._fn[inject.id].forEach(function (un) {
          un();
        });

        delete this._fn[inject.id];
      }

      inject.clearProp();
    }
  };
  t.watch = t.loaded;
  return t;
};

var componentValidate = {
  name: 'componentValidate',
  load: function load(attr, rule, api) {
    var options = attr.getValue();

    if (!options || options.method === false) {
      attr.clearProp();
      api.clearValidateState([rule.field]);
    } else {
      if (!is.Object(options)) {
        options = {
          method: options
        };
      }

      var method = options.method;

      var validate = _objectSpread2(_objectSpread2({}, options), {}, {
        validator: function validator() {
          var ctx = byCtx(rule);

          if (ctx) {
            for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
              args[_key] = arguments[_key];
            }

            return api.exec.apply(api, [ctx.id, is.String(method) ? method : 'formCreateValidate'].concat(args, [{
              attr: attr,
              rule: rule,
              api: api
            }]));
          }
        }
      });

      delete validate.method;
      attr.getProp().validate = [validate];
    }
  },
  watch: function watch() {
    componentValidate.load.apply(componentValidate, arguments);
  }
};

var fetch = function fetch(fc) {
  function parseOpt(option) {
    if (is.String(option)) {
      option = {
        action: option,
        to: 'options'
      };
    }

    return option;
  }

  function run(inject, rule, api) {
    var option = inject.value;
    fetchAttr.deleted(inject);

    if (is.Function(option)) {
      option = option(rule, api);
    }

    option = parseOpt(option);

    var set = function set(val) {
      if (val === undefined) {
        inject.clearProp();
      } else {
        deepSet(inject.getProp(), option.to || 'options', val);
      }

      if (val != null && option && option.key && fc.$handle.options.globalData[option.key]) {
        fc.fetchCache.set(fc.$handle.options.globalData[option.key], {
          status: true,
          data: val
        });
      }

      api.sync(rule);
    };

    if (!option || !option.action && !option.key) {
      set(undefined);
      return;
    }

    option = deepCopy(option);

    if (!option.to) {
      option.to = 'options';
    }

    var _onError = option.onError;

    var check = function check() {
      if (!inject.getValue()) {
        inject.clearProp();
        api.sync(rule);
        return true;
      }
    };

    fetchAttr._fn[inject.id] = fc.watchLoadData(debounce(function (get, change) {
      if (change && option.watch === false) {
        return fetchAttr._fn[inject.id]();
      }

      if (option.key) {
        fc.targetRule = rule;
        var res = get('$globalData.' + option.key);
        delete fc.targetRule;

        if (res) {
          if (check()) return;
          set(res);
        }

        return;
      }

      var _option = fc.$handle.loadFetchVar(deepCopy(option), get, rule);

      var config = _objectSpread2(_objectSpread2({
        headers: {}
      }, _option), {}, {
        onSuccess: function onSuccess(body, flag) {
          if (check()) return;

          var fn = function fn(v) {
            return flag ? v : hasProperty(v, 'data') ? v.data : v;
          };

          var parse = parseFn(_option.parse);

          if (is.Function(parse)) {
            fn = parse;
          } else if (parse && is.String(parse)) {
            fn = function fn(v) {
              return deepGet(v, parse);
            };
          }

          toPromise(fn(body, rule, api)).then(function (res) {
            set(res);
          });
        },
        onError: function onError(e) {
          set(undefined);
          if (check()) return;

          (_onError || function (e) {
            return err(e.message || 'fetch fail ' + _option.action);
          })(e, rule, api);
        }
      });

      fc.$handle.beforeFetch(config, {
        rule: rule,
        api: api
      }).then(function () {
        if (is.Function(_option.action)) {
          toPromise(_option.action(rule, api)).then(function (val) {
            config.onSuccess(val, true);
          })["catch"](function (e) {
            config.onError(e);
          });
          return;
        }

        invoke(function () {
          return fc.create.fetch(config, {
            inject: inject,
            rule: rule,
            api: api
          });
        });
      })["catch"](function (e) {});
    }, option.wait || 600));
  }

  var fetchAttr = {
    name: 'fetch',
    _fn: [],
    loaded: function loaded() {
      run.apply(void 0, arguments);
    },
    watch: function watch() {
      run.apply(void 0, arguments);
    },
    deleted: function deleted(inject) {
      if (this._fn[inject.id]) {
        this._fn[inject.id]();

        delete this._fn[inject.id];
      }

      inject.clearProp();
    }
  };
  return fetchAttr;
};

var $provider = {
  fetch: fetch,
  loadData: loadData,
  t: t,
  componentValidate: componentValidate
};

// https://github.com/developit/mitt
function Mitt(all) {
  all = all || new Map();
  var mitt = {
    $on: function $on(type, handler) {
      var handlers = all.get(type);
      var added = handlers && handlers.push(handler);

      if (!added) {
        all.set(type, [handler]);
      }
    },
    $once: function $once(type, handler) {
      handler._once = true;
      mitt.$on(type, handler);
    },
    $off: function $off(type, handler) {
      var handlers = all.get(type);

      if (handlers) {
        handlers.splice(handlers.indexOf(handler) >>> 0, 1);
      }
    },
    $emit: function $emit(type) {
      for (var _len = arguments.length, args = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
        args[_key - 1] = arguments[_key];
      }

      (all.get(type) || []).slice().map(function (handler) {
        if (handler._once) {
          mitt.$off(type, handler);
          delete handler._once;
        }

        handler.apply(void 0, args);
      });
      (all.get('*') || []).slice().map(function (handler) {
        handler(type, args);
      });
    }
  };
  return mitt;
}

var name = 'html';
var html = {
  name: name,
  loadChildren: false,
  render: function render(children, ctx) {
    ctx.prop.props.innerHTML = children["default"]();
    return ctx.vNode.make(ctx.prop.props.tag || 'div', ctx.prop);
  },
  renderChildren: function renderChildren(children) {
    return {
      "default": function _default() {
        return children.filter(function (v) {
          return is.String(v);
        }).join('');
      }
    };
  }
};

function getCookie(name) {
  name = name + '=';
  var decodedCookie = decodeURIComponent(document.cookie);
  var cookieArray = decodedCookie.split(';');

  for (var i = 0; i < cookieArray.length; i++) {
    var cookie = cookieArray[i];

    while (cookie.charAt(0) === ' ') {
      cookie = cookie.substring(1);
    }

    if (cookie.indexOf(name) === 0) {
      cookie = cookie.substring(name.length, cookie.length);

      try {
        return JSON.parse(cookie);
      } catch (e) {
        return cookie;
      }
    }
  }

  return null;
}

function getLocalStorage(name) {
  var value = localStorage.getItem(name);

  if (value) {
    try {
      return JSON.parse(value);
    } catch (e) {
      return value;
    }
  }

  return null;
}

function getSessionStorage(name) {
  var value = sessionStorage.getItem(name);

  if (value) {
    try {
      return JSON.parse(value);
    } catch (e) {
      return value;
    }
  }

  return null;
}

function baseDriver(driver, name) {
  if (!name) {
    return null;
  }

  var split = name.split('.');
  var value = driver(split.shift());

  if (!split.length) {
    return value;
  }

  if (value == null) {
    return null;
  }

  return deepGet(value, split);
}
function cookieDriver(name) {
  return baseDriver(getCookie, name);
}
function localStorageDriver(name) {
  return baseDriver(getLocalStorage, name);
}
function sessionStorageDriver(name) {
  return baseDriver(getSessionStorage, name);
}

var baseLanguage = {
  en: {
    required: '{title} is required',
    validate: {
      url: '{title} is not a valid url',
      email: '{title} is not a valid email',
      ip: '{title} {title} is not a valid ip',
      phone: '{title} {title} is not a valid phone',
      pattern: '{title} does not match pattern {pattern}',
      uppercase: '{title} must be all uppercase',
      lowercase: '{title} must be all lowercased',
      positive: '{title} is not a positive number',
      negative: '{title} is not a negative number',
      equal: '{title} is not equal to {equal}',
      min: '{title} cannot be less than {min}',
      max: '{title} cannot be greater than {max}',
      "enum": '{title} must be one of {enum}',
      hasKeys: '{title} does not contain required fields {hasKeys}',
      minLen: '{title} must be at least {minLen}',
      maxLen: '{title} cannot be longer than {maxLen}',
      len: '{title} must be exactly {len}',
      integer: '{title} is not an integer',
      number: '{title} is not an number'
    }
  },
  'zh-cn': {
    required: '{title}不能为空',
    validate: {
      url: '{title}不是有效的 url 地址',
      email: '{title}不是有效的邮箱地址',
      ip: '{title}不是有效的 IP 地址',
      phone: '{title}不是正确的手机号',
      pattern: '{title}必须匹配 {pattern}',
      uppercase: '{title}必须全大写',
      lowercase: '{title}必须全小写',
      positive: '{title}不是正数',
      negative: '{title}不是负数',
      equal: '{title}必须等于 {equal}',
      min: '{title}必须大于等于 {min}',
      max: '{title}必须小于等于 {max}',
      "enum": '{title}必须是 {enum} 之一',
      hasKeys: '{title}必须包含 {hasKeys} 字段',
      minLen: '{title}长度必须大于 {minLen}',
      maxLen: '{title}长度必须小于 {maxLen}',
      len: '{title}长度必须为 {len}',
      integer: '{title}必须为整数',
      number: '{title}必须为数字'
    }
  }
};

function parseProp(name, id) {
  var prop;

  if (arguments.length === 2) {
    prop = arguments[1];
    id = prop[name];
  } else {
    prop = arguments[2];
  }

  return {
    id: id,
    prop: prop
  };
}

function nameProp() {
  return parseProp.apply(void 0, ['name'].concat(Array.prototype.slice.call(arguments)));
}

function exportAttrs(attrs) {
  var key = attrs.key || [];
  var array = attrs.array || [];
  var normal = attrs.normal || [];
  keyAttrs.push.apply(keyAttrs, _toConsumableArray(key));
  arrayAttrs.push.apply(arrayAttrs, _toConsumableArray(array));
  normalAttrs.push.apply(normalAttrs, _toConsumableArray(normal));
  appendProto([].concat(_toConsumableArray(key), _toConsumableArray(array), _toConsumableArray(normal)));
}

var id = 1;
var instance = {};
var defValueTag = Symbol('defValue'); //todo 表单嵌套

function FormCreateFactory(config) {
  var components = _defineProperty({}, fragment.name, fragment);

  var parsers = {};
  var directives = {};
  var modelFields = {};
  var drivers = {};
  var useApps = [];
  var listener = [];
  var extendApiFn = [config.extendApi];

  var providers = _objectSpread2({}, $provider);

  var maker = makerFactory();
  var globalConfig = {
    global: {}
  };
  var isMobile = config.isMobile === true;
  var loadData = reactive({
    $mobile: isMobile
  });
  var CreateNode = CreateNodeFactory();
  var formulas = {};
  var prototype = {};
  exportAttrs(config.attrs || {});

  function getApi(name) {
    var val = instance[name];

    if (Array.isArray(val)) {
      return val.map(function (v) {
        return v.api();
      });
    } else if (val) {
      return val.api();
    }
  }

  function useApp(fn) {
    useApps.push(fn);
  }

  function directive() {
    var data = nameProp.apply(void 0, arguments);
    if (data.id && data.prop) directives[data.id] = data.prop;
  }

  function register() {
    var data = nameProp.apply(void 0, arguments);
    if (data.id && data.prop) providers[data.id] = is.Function(data.prop) ? data.prop : _objectSpread2(_objectSpread2({}, data.prop), {}, {
      name: data.id
    });
  }

  function componentAlias(alias) {
    CreateNode.use(alias);
  }

  function parser(key) {
    if (arguments.length === 0) {
      return BaseParser;
    } else if (typeof key === 'string' && arguments.length === 1) {
      return parsers[toCase(key)];
    }

    var data = nameProp.apply(void 0, arguments);
    if (!data.id || !data.prop) return BaseParser;
    var name = toCase(data.id);
    var parser = data.prop;
    var base = parser.merge === true ? parsers[name] : undefined;
    parsers[name] = setPrototypeOf(parser, base || BaseParser);
    maker[name] = creatorFactory(name);
    parser.maker && extend(maker, parser.maker);
  }

  function component(id, component) {
    var name;

    if (is.String(id)) {
      name = id;

      if (component === undefined) {
        return components[name];
      }
    } else {
      name = id.displayName || id.name;
      component = id;
    }

    if (!name || !component) return;
    var nameAlias = toCase(name);
    components[name] = component;
    components[nameAlias] = component;
    delete CreateNode.aliasMap[name];
    delete CreateNode.aliasMap[nameAlias];
    delete parsers[name];
    delete parsers[nameAlias];
    if (component.formCreateParser) parser(name, component.formCreateParser);
  }

  function $form() {
    return $FormCreate(FormCreate, components, directives);
  }

  function createFormApp(rule, option) {
    var Type = $form();
    return createApp({
      data: function data() {
        return reactive({
          rule: rule,
          option: option
        });
      },
      render: function render() {
        return h(Type, _objectSpread2({
          ref: 'fc'
        }, this.$data));
      }
    });
  }

  function $vnode() {
    return fragment;
  } //todo 检查回调函数作用域


  function use(fn, opt) {
    if (is.Function(fn.install)) fn.install(create, opt);else if (is.Function(fn)) fn(create, opt);
    return this;
  }

  function create(rules, option) {
    var app = createFormApp(rules, option || {});
    useApps.forEach(function (v) {
      invoke(function () {
        return v(create, app);
      });
    });
    var div = document.createElement('div');
    ((option === null || option === void 0 ? void 0 : option.el) || document.body).appendChild(div);
    var vm = app.mount(div);
    return vm.$refs.fc.fapi;
  }

  setPrototypeOf(create, prototype);

  function factory(inherit) {
    var _config = _objectSpread2({}, config);

    if (inherit) {
      _config.inherit = {
        components: components,
        parsers: parsers,
        directives: directives,
        modelFields: modelFields,
        providers: providers,
        useApps: useApps,
        maker: maker,
        formulas: formulas,
        loadData: loadData
      };
    } else {
      delete _config.inherit;
    }

    return FormCreateFactory(_config);
  }

  function setModelField(name, field) {
    modelFields[name] = field;
  }

  function setFormula(name, fn) {
    formulas[name] = fn;
  }

  function setDriver(name, driver) {
    var parent = drivers[name] || {};
    var parsers = parent.parsers || {};

    if (driver.parsers) {
      Object.keys(driver.parsers).forEach(function (k) {
        parsers[k] = setPrototypeOf(driver.parsers[k], BaseParser);
      });
    }

    driver.name = name;
    drivers[name] = _objectSpread2(_objectSpread2(_objectSpread2({}, parent), driver), {}, {
      parsers: parsers
    });
  }

  function refreshData(id) {
    if (id) {
      Object.keys(instance).forEach(function (v) {
        var apis = Array.isArray(instance[v]) ? instance[v] : [instance[v]];
        apis.forEach(function (that) {
          that.bus.$emit('$loadData.' + id);
        });
      });
    }
  }

  function _setData(id, data) {
    deepSet(loadData, id, data);
    refreshData(id);
  }

  function setDataDriver(id, data) {
    var callback = function callback() {
      for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
        args[_key] = arguments[_key];
      }

      return invoke(function () {
        return data.apply(void 0, args);
      });
    };

    callback._driver = true;

    _setData(id, callback);
  }

  function getData(id, def) {
    var split = (id || '').split('.');
    id = split.shift();
    var field = split.join('.');

    if (!hasProperty(loadData, id)) {
      loadData[id] = defValueTag;
    }

    if (loadData[id] !== defValueTag) {
      var val = loadData[id];

      if (val && val._driver) {
        val = val(field);
      } else if (split.length) {
        val = deepGet(val, split);
      }

      return val == null || val === '' ? def : val;
    } else {
      return def;
    }
  }

  function extendApi(fn) {
    extendApiFn.push(fn);
  }

  function removeData(id) {
    delete loadData[id];
    refreshData(id);
  }

  function on(name, callback) {
    listener.push({
      name: name,
      callback: callback
    });
  }

  function FormCreate(vm) {
    var _this = this;

    extend(this, {
      id: id++,
      create: create,
      vm: vm,
      manager: createManager(config.manager),
      parsers: parsers,
      providers: providers,
      modelFields: modelFields,
      formulas: formulas,
      isMobile: isMobile,
      rules: vm.props.rule,
      name: vm.props.name || uniqueId(),
      inFor: vm.props.inFor,
      prop: {
        components: components,
        directives: directives
      },
      get: null,
      drivers: drivers,
      renderDriver: null,
      refreshData: refreshData,
      loadData: loadData,
      CreateNode: CreateNode,
      bus: new Mitt(),
      unwatch: [],
      options: ref({}),
      extendApiFn: extendApiFn,
      fetchCache: new WeakMap(),
      tmpData: reactive({})
    });
    listener.forEach(function (item) {
      _this.bus.$on(item.name, item.callback);
    });
    nextTick(function () {
      watch(_this.options, function () {
        _this.$handle.$manager.updateOptions(_this.options.value);

        _this.api().refresh();
      }, {
        deep: true
      });
    });
    extend(vm.appContext.components, components);
    extend(vm.appContext.directives, directives);
    this.$handle = new Handler(this);

    if (this.name) {
      if (this.inFor) {
        if (!instance[this.name]) instance[this.name] = [];
        instance[this.name].push(this);
      } else {
        instance[this.name] = this;
      }
    }
  }

  FormCreate.isMobile = isMobile;
  extend(FormCreate.prototype, {
    init: function init() {
      var _this2 = this;

      if (this.isSub()) {
        this.unwatch.push(watch(function () {
          return _this2.vm.setupState.parent.setupState.fc.options.value;
        }, function () {
          _this2.initOptions();

          _this2.$handle.api.refresh();
        }, {
          deep: true,
          flush: 'sync'
        }));
      }

      if (this.vm.props.driver) {
        this.renderDriver = _typeof(this.vm.props.driver) === 'object' ? this.vm.props.driver : this.drivers[this.vm.props.driver];
      }

      if (!this.renderDriver && this.vm.setupState.parent) {
        this.renderDriver = this.vm.setupState.parent.setupState.fc.renderDriver;
      }

      if (!this.renderDriver) {
        this.renderDriver = this.drivers["default"];
      }

      this.initOptions();
      this.$handle.init();
    },
    targetFormDriver: function targetFormDriver(method) {
      var _this$bus,
          _this3 = this;

      for (var _len2 = arguments.length, args = new Array(_len2 > 1 ? _len2 - 1 : 0), _key2 = 1; _key2 < _len2; _key2++) {
        args[_key2 - 1] = arguments[_key2];
      }

      (_this$bus = this.bus).$emit.apply(_this$bus, [method].concat(args));

      if (this.renderDriver && this.renderDriver[method]) {
        return invoke(function () {
          var _this3$renderDriver;

          return (_this3$renderDriver = _this3.renderDriver)[method].apply(_this3$renderDriver, args);
        });
      }
    },
    t: function t(id, params, get) {
      var value = get ? get('$t.' + id) : this.globalLanguageDriver(id);

      if (value == null) {
        value = '';
      }

      if (value && params) {
        Object.keys(params).forEach(function (param) {
          var regex = new RegExp("{".concat(param, "}"), 'g');
          value = value.replace(regex, params[param]);
        });
      }

      return value;
    },
    globalDataDriver: function globalDataDriver(id) {
      var _this4 = this;

      var split = id.split('.');
      var key = split.shift();
      var option = this.options.value.globalData && this.options.value.globalData[key];

      if (option) {
        if (option.type === 'static') {
          return deepGet(option.data, split);
        } else {
          var val;
          var res = this.fetchCache.get(option);

          if (res) {
            if (res.status) {
              val = deepGet(res.data, split);
            }

            if (!res.loading) {
              return val;
            }

            res.loading = false;
            this.fetchCache.set(option, res);
          } else {
            this.fetchCache.set(option, {
              status: false
            });
          }

          var reload = debounce(function () {
            unwatch();

            var res = _this4.fetchCache.get(option);

            if (_this4.options.value.globalData && Object.values(_this4.options.value.globalData).indexOf(option) !== -1) {
              if (res) {
                res.loading = true;

                _this4.fetchCache.set(option, res);
              }

              _this4.bus.$emit('$loadData.$globalData.' + key);
            } else {
              _this4.fetchCache["delete"](option);
            }
          }, option.wait || 600);

          var _emit = function _emit(data) {
            _this4.fetchCache.set(option, {
              status: true,
              data: data
            });

            _this4.bus.$emit('$loadData.$globalData.' + key);
          };

          var callback = function callback(get, change) {
            if (change && option.watch === false) {
              return unwatch();
            }

            if (change) {
              reload();
              return;
            }

            var options = _this4.$handle.loadFetchVar(copy$1(option), get);

            options.targetRule = _this4.targetRule;

            _this4.$handle.api.fetch(options).then(function (res) {
              _emit(res);
            })["catch"](function (e) {
              _emit(null);
            });
          };

          var unwatch = this.watchLoadData(callback);

          if (option.watch === false) {
            unwatch();
          }

          this.unwatch.push(unwatch);
          return val;
        }
      }
    },
    getLocale: function getLocale() {
      var locale = this.vm.setupState.top.props.locale;

      if (locale && _typeof(locale) === 'object') {
        return locale.name;
      }

      if (typeof locale === 'string') {
        return locale;
      }

      return 'zh-cn';
    },
    globalLanguageDriver: function globalLanguageDriver(id) {
      var t = this.vm.setupState.top.props.t;
      var locale = this.vm.setupState.top.props.locale;
      var value = undefined;

      if (t) {
        value = invoke(function () {
          return t(id);
        });
      }

      if (value == null && locale && _typeof(locale) === 'object') {
        value = deepGet(locale, id);
      }

      if (value == null) {
        var language = this.options.value.language || {};

        var _locale = this.getLocale();

        value = deepGet(language[_locale] || {}, id);

        if (value == null) {
          value = deepGet(baseLanguage[_locale] || {}, id);
        }
      }

      return value;
    },
    globalVarDriver: function globalVarDriver(id) {
      var _this5 = this;

      var split = id.split('.');
      var key = split.shift();
      var option = this.options.value.globalVariable && this.options.value.globalVariable[key];

      if (option) {
        var handle = is.Function(option) ? option : parseFn(option.handle);

        if (handle) {
          var val = handle(function () {
            var _this5$$handle$api;

            return (_this5$$handle$api = _this5.$handle.api).getData.apply(_this5$$handle$api, arguments);
          }, this.$handle.api);
          return deepGet(val, split);
        }
      }
    },
    setData: function setData(id, data, isGlobal) {
      if (!isGlobal) {
        deepSet(this.vm.setupState.top.setupState.fc.tmpData, id, data);
        this.bus.$emit('$loadData.' + id);
      } else {
        _setData(id, data);
      }
    },
    getLoadData: function getLoadData(id, def) {
      var val = null;

      if (id != null) {
        var split = id.split('.');
        var key = split.shift();
        val = deepGet(this.vm.setupState.top.setupState.fc.tmpData, id);

        if (val != null) {
          return val;
        } else if (key === '$topForm') {
          val = this.$handle.api.top.formData(true);
        } else if (key === '$scopeForm') {
          val = this.$handle.api.scope.formData(true);
        } else if (key === '$form') {
          val = this.$handle.api.formData(true);
        } else if (key === '$options') {
          val = this.options.value;
        } else if (key === '$globalData') {
          val = this.globalDataDriver(split.join('.'));
          split = [];
        } else if (key === '$var') {
          val = this.globalVarDriver(split.join('.'));
          split = [];
        } else if (key === '$locale') {
          val = this.getLocale();
          split = [];
        } else if (key === '$t') {
          val = this.globalLanguageDriver(split.join('.'));
          split = [];
        } else if (key === '$preview') {
          return this.$handle.preview;
        } else {
          val = getData(id);
          split = [];
        }

        if (val && split.length) {
          val = deepGet(val, split);
        }
      }

      return val == null || val === '' ? def : val;
    },
    watchLoadData: function watchLoadData(fn, wait) {
      var _this6 = this;

      var unwatch = {};

      var run = function run(flag) {
        if (!_this6.get) {
          _this6.get = get;
        }

        invoke(function () {
          fn(get, flag);
        });

        if (_this6.get === get) {
          _this6.get = undefined;
        }
      };

      var get = function get(id, def) {
        var getValue;

        if (_typeof(id) === 'object') {
          getValue = id.getValue;
          id = id.id;
        }

        if (unwatch[id]) {
          return unwatch[id].val;
        }

        var data = computed(function () {
          return getValue ? getValue() : _this6.getLoadData(id, def);
        });
        var split = id.split('.');
        var key = split.shift();
        var key2 = split.shift() || '';
        var callback = debounce(function () {
          var temp = getValue ? getValue() : _this6.getLoadData(id, def);

          if (!unwatch[id]) {
            return;
          } else if ((temp instanceof Function || is.Object(temp) || Array.isArray(temp)) && temp === unwatch[id].val || JSON.stringify(temp) !== JSON.stringify(unwatch[id].val)) {
            unwatch[id].val = temp;
            run(true);
          }
        }, wait || 0);
        var un = watch(data, function (n) {
          callback();
        });

        _this6.bus.$on('$loadData.' + key, callback);

        if (key2) {
          _this6.bus.$on('$loadData.' + key + '.' + key2, callback);
        }

        unwatch[id] = {
          fn: function fn() {
            _this6.bus.$off('$loadData.' + key, callback);

            if (key2) {
              _this6.bus.$off('$loadData.' + key + '.' + key2, callback);
            }

            un();
          },
          val: data.value
        };
        return data.value;
      };

      run(false);

      var un = function un() {
        Object.keys(unwatch).forEach(function (k) {
          return unwatch[k].fn();
        });
        unwatch = {};
      };

      this.unwatch.push(un);
      return un;
    },
    isSub: function isSub() {
      return this.vm.setupState.parent && this.vm.props.extendOption;
    },
    initOptions: function initOptions() {
      this.options.value = {};

      var options = _objectSpread2({
        formData: {},
        submitBtn: {},
        resetBtn: {},
        globalEvent: {},
        globalData: {}
      }, deepCopy(globalConfig));

      var isSubForm = this.isSub();

      if (isSubForm) {
        options = this.mergeOptions(options, this.vm.setupState.parent.setupState.fc.options.value || {}, true);
      }

      options = this.mergeOptions(options, this.vm.props.option);
      var api = this.api();
      this.targetFormDriver('initOptions', options, {
        api: api,
        isSubForm: isSubForm
      });
      this.updateOptions(options);
    },
    mergeOptions: function mergeOptions(target, opt, parent) {
      opt = _objectSpread2({}, opt || {});
      parent && ['page', 'onSubmit', 'onReset', 'onCreated', 'onChange', 'onMounted', 'mounted', 'onReload', 'reload', 'formData', 'el', 'globalClass', 'style'].forEach(function (n) {
        delete opt[n];
      });

      if (opt.global) {
        target.global = mergeGlobal(target.global, opt.global);
        delete opt.global;
      }

      this.$handle.$manager.mergeOptions([opt], target);
      return target;
    },
    updateOptions: function updateOptions(options) {
      this.options.value = this.mergeOptions(this.options.value, options);
      this.$handle.$manager.updateOptions(this.options.value);
      this.bus.$emit('$loadData.$options');
    },
    api: function api() {
      return this.$handle.api;
    },
    render: function render() {
      return this.$handle.render();
    },
    mounted: function mounted() {
      this.$handle.mounted();
    },
    unmount: function unmount() {
      var _this7 = this;

      if (this.name) {
        if (this.inFor) {
          var idx = instance[this.name].indexOf(this);
          instance[this.name].splice(idx, 1);
        } else {
          delete instance[this.name];
        }
      }

      listener.forEach(function (item) {
        _this7.bus.$off(item.name, item.callback);
      });
      this.tmpData = {};
      this.unwatch.forEach(function (fn) {
        return fn();
      });
      this.unwatch = [];
      this.$handle.reloadRule([]);
    },
    updated: function updated() {
      var _this8 = this;

      this.$handle.bindNextTick(function () {
        return _this8.bus.$emit('next-tick', _this8.$handle.api);
      });
    }
  });

  function useAttr(formCreate) {
    extend(formCreate, {
      version: config.version,
      ui: config.ui,
      isMobile: isMobile,
      extendApi: extendApi,
      getData: getData,
      setDataDriver: setDataDriver,
      setData: _setData,
      removeData: removeData,
      refreshData: refreshData,
      maker: maker,
      component: component,
      directive: directive,
      setModelField: setModelField,
      setFormula: setFormula,
      setDriver: setDriver,
      register: register,
      $vnode: $vnode,
      parser: parser,
      use: use,
      factory: factory,
      componentAlias: componentAlias,
      copyRule: copyRule,
      copyRules: copyRules,
      mergeRule: mergeRule,
      fetch: fetch$1,
      $form: $form,
      parseFn: parseFn,
      parseJson: parseJson,
      toJson: toJson,
      useApp: useApp,
      getApi: getApi,
      on: on
    });
  }

  function useStatic(formCreate) {
    extend(formCreate, {
      create: create,
      install: function install(app, options) {
        globalConfig = _objectSpread2(_objectSpread2({}, globalConfig), options || {});
        var key = "_installedFormCreate".concat(isMobile ? 'Mobile' : '', "_").concat(config.ui);
        if (app[key] === true) return;
        app[key] = true;

        var $formCreate = function $formCreate(rules) {
          var opt = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};
          return create(rules, opt);
        };

        useAttr($formCreate);
        app.config.globalProperties.$formCreate = $formCreate;
        var $component = $form();
        app.component($component.name, $component);
        useApps.forEach(function (v) {
          invoke(function () {
            return v(formCreate, app);
          });
        });
      }
    });
  }

  useAttr(prototype);
  useStatic(prototype);
  setDataDriver('$cookie', cookieDriver);
  setDataDriver('$localStorage', localStorageDriver);
  setDataDriver('$sessionStorage', sessionStorageDriver);
  CreateNode.use({
    fragment: 'fcFragment'
  });
  config.install && create.use(config);
  useApp(function (_, app) {
    app.mixin({
      props: ['formCreateInject']
    });
  });
  parser(html);

  if (config.inherit) {
    var inherit = config.inherit;
    inherit.components && extend(components, inherit.components);
    inherit.parsers && extend(parsers, inherit.parsers);
    inherit.directives && extend(directives, inherit.directives);
    inherit.modelFields && extend(modelFields, inherit.modelFields);
    inherit.providers && extend(providers, inherit.providers);
    inherit.useApps && extend(useApps, inherit.useApps);
    inherit.maker && extend(maker, inherit.maker);
    inherit.loadData && extend(loadData, inherit.loadData);
    inherit.formulas && extend(formulas, inherit.formulas);
  }

  var FcComponent = $form();
  setPrototypeOf(FcComponent, prototype);
  Object.defineProperties(FcComponent, {
    fetch: {
      get: function get() {
        return prototype.fetch;
      },
      set: function set(val) {
        prototype.fetch = val;
      }
    }
  });
  FcComponent.util = prototype;
  return FcComponent;
}

export { Creator, Manager, copyRule, copyRules, creatorFactory, FormCreateFactory as default, fragment, mergeRule, parseJson, toJson };
