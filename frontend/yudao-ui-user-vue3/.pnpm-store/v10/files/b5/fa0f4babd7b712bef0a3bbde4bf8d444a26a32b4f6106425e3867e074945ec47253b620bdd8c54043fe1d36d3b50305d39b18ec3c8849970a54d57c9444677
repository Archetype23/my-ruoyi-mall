/*!
 * @form-create/component-elm-radio v3.2.31
 * (c) 2018-2025 xaboy
 * Github https://github.com/xaboy/form-create with radio
 * Released under the MIT License.
 */
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('vue')) :
  typeof define === 'function' && define.amd ? define(['exports', 'vue'], factory) :
  (global = typeof globalThis !== 'undefined' ? globalThis : global || self, factory(global.FcRadio = {}, global.Vue));
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

  function getSlot(slots, exclude) {
    return Object.keys(slots).reduce(function (lst, name) {
      if (!exclude || exclude.indexOf(name) === -1) {
        lst[name] = slots[name];
      }

      return lst;
    }, {});
  }

  var NAME = 'fcRadio';
  var Radio = vue.defineComponent({
    name: NAME,
    inheritAttrs: false,
    props: {
      formCreateInject: Object,
      modelValue: {
        type: [String, Number, Boolean],
        "default": ''
      },
      options: Array,
      type: String,
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
        var flag = false;

        if (!inputValue.value && n != null && input.value) {
          flag = _options.value.map(function (item) {
            return item.value;
          }).indexOf(n) === -1;
        }

        if (flag) {
          customValue.value = n;
        }
      }, {
        immediate: true
      });

      var onInput = function onInput(n) {
        _.emit('update:modelValue', n);
      };

      var updateCustomValue = function updateCustomValue(n) {
        var o = customValue.value;
        customValue.value = n;

        if (value.value === o) {
          onInput(n);
        }
      };

      return {
        options: _options,
        value: value,
        onInput: onInput,
        updateCustomValue: updateCustomValue,
        customValue: customValue,
        makeInput: function makeInput(Type) {
          if (!input.value) {
            return undefined;
          }

          return vue.createVNode(Type, {
            "checked": false,
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

      var name = this.type === 'button' ? 'ElRadioButton' : 'ElRadio';
      var Type = vue.resolveComponent(name);
      return vue.createVNode(vue.resolveComponent("ElRadioGroup"), vue.mergeProps(this.$attrs, {
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

  exports["default"] = Radio;

  Object.defineProperty(exports, '__esModule', { value: true });

}));
