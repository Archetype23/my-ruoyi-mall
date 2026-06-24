/*!
 * @form-create/component-elm-checkbox v3.2.31
 * (c) 2018-2025 xaboy
 * Github https://github.com/xaboy/form-create with checkbox
 * Released under the MIT License.
 */
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('vue')) :
  typeof define === 'function' && define.amd ? define(['exports', 'vue'], factory) :
  (global = typeof globalThis !== 'undefined' ? globalThis : global || self, factory(global.FcCheckbox = {}, global.Vue));
})(this, (function (exports, vue) { 'use strict';

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

  function _toConsumableArray(arr) {
    return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _unsupportedIterableToArray(arr) || _nonIterableSpread();
  }

  function _arrayWithoutHoles(arr) {
    if (Array.isArray(arr)) return _arrayLikeToArray(arr);
  }

  function _iterableToArray(iter) {
    if (typeof Symbol !== "undefined" && iter[Symbol.iterator] != null || iter["@@iterator"] != null) return Array.from(iter);
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

  function getSlot(slots, exclude) {
    return Object.keys(slots).reduce(function (lst, name) {
      if (!exclude || exclude.indexOf(name) === -1) {
        lst[name] = slots[name];
      }

      return lst;
    }, {});
  }

  function toArray(value) {
    return Array.isArray(value) ? value : [null, undefined, ''].indexOf(value) > -1 ? [] : [value];
  }

  var NAME = 'fcCheckbox';
  var Checkbox = vue.defineComponent({
    name: NAME,
    inheritAttrs: false,
    props: {
      formCreateInject: Object,
      modelValue: {
        type: Array,
        "default": function _default() {
          return [];
        }
      },
      type: String,
      options: Array,
      input: Boolean,
      inputValue: String
    },
    emits: ['update:modelValue', 'fc.el'],
    setup: function setup(props, _) {
      var options = vue.toRef(props.formCreateInject, 'options', []);
      var opt = vue.toRef(props, 'options');
      var value = vue.toRef(props, 'modelValue');
      var inputValue = vue.toRef(props, 'inputValue', '');
      var customValue = vue.ref(inputValue.value);
      var input = vue.toRef(props, 'input', false);

      var updateCustomValue = function updateCustomValue(n) {
        var _value = _toConsumableArray(toArray(value.value));

        var idx = _value.indexOf(customValue.value);

        customValue.value = n;

        if (idx > -1) {
          _value.splice(idx, 1);

          _value.push(n);

          onInput(_value);
        }
      };

      vue.watch(inputValue, function (n) {
        if (!input.value) {
          customValue.value = n;
          return undefined;
        }

        updateCustomValue(n);
      });

      var _options = vue.computed(function () {
        var arr = options.value || [];

        if (opt.value) {
          arr = opt.value || [];
        }

        return Array.isArray(arr) ? arr : [];
      });

      vue.watch(value, function (n) {
        var value = null;

        if (!inputValue.value && n != null && Array.isArray(n) && n.length > 0 && input.value) {
          var values = _options.value.map(function (item) {
            return item.value;
          });

          n.forEach(function (val) {
            if (values.indexOf(val) === -1) {
              value = val;
            }
          });
        }

        if (value != null) {
          customValue.value = value;
        }
      }, {
        immediate: true
      });

      var onInput = function onInput(n) {
        _.emit('update:modelValue', n);
      };

      return {
        options: _options,
        value: value,
        onInput: onInput,
        updateCustomValue: updateCustomValue,
        makeInput: function makeInput(Type) {
          if (!input.value) {
            return undefined;
          }

          return vue.createVNode(Type, {
            "value": customValue.value || undefined,
            "label": customValue.value || undefined
          }, {
            "default": function _default() {
              return [vue.createVNode(vue.resolveComponent("ElInput"), {
                "size": "small",
                "modelValue": customValue.value,
                "onUpdate:modelValue": updateCustomValue
              }, null)];
            }
          });
        }
      };
    },
    render: function render() {
      var _this$$slots$default,
          _this$$slots,
          _this = this;

      var name = this.type === 'button' ? 'ElCheckboxButton' : 'ElCheckbox';
      var Type = vue.resolveComponent(name);
      return vue.createVNode(vue.resolveComponent("ElCheckboxGroup"), vue.mergeProps(this.$attrs, {
        "modelValue": this.value,
        "onUpdate:modelValue": this.onInput,
        "ref": "el"
      }), _objectSpread2({
        "default": function _default() {
          return [_this.options.map(function (opt, index) {
            var props = _objectSpread2({}, opt);

            var value = props.value;
            var label = props.label;
            delete props.value;
            delete props.label;
            return vue.createVNode(Type, vue.mergeProps(props, {
              "label": value,
              "value": value,
              "key": name + index + '-' + value
            }), {
              "default": function _default() {
                return [label || value || ''];
              }
            });
          }), (_this$$slots$default = (_this$$slots = _this.$slots)["default"]) === null || _this$$slots$default === void 0 ? void 0 : _this$$slots$default.call(_this$$slots), _this.makeInput(Type)];
        }
      }, getSlot(this.$slots, ['default'])));
    },
    mounted: function mounted() {
      this.$emit('fc.el', this.$refs.el);
    }
  });

  exports["default"] = Checkbox;

  Object.defineProperty(exports, '__esModule', { value: true });

}));
