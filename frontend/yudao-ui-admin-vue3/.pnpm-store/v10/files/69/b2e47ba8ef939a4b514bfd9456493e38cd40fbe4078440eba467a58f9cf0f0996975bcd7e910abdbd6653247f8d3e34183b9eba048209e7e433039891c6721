/*!
 * @form-create/component-elm-select v3.2.37
 * (c) 2018-2026 xaboy
 * Github https://github.com/xaboy/form-create with select
 * Released under the MIT License.
 */
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('vue')) :
  typeof define === 'function' && define.amd ? define(['exports', 'vue'], factory) :
  (global = typeof globalThis !== 'undefined' ? globalThis : global || self, factory(global.FcSelect = {}, global.Vue));
})(this, (function (exports, vue) { 'use strict';

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

  var NAME = 'fcSelect';
  var script = vue.defineComponent({
    name: NAME,
    inheritAttrs: false,
    props: {
      formCreateInject: Object,
      modelValue: {
        type: [Array, String, Number, Boolean, Object],
        "default": undefined
      },
      type: String
    },
    emits: ['update:modelValue', 'fc.el'],
    setup: function setup(props, _ref) {
      var emit = _ref.emit;
      var options = vue.toRef(props.formCreateInject, 'options', []);
      var value = vue.toRef(props, 'modelValue');

      var _options = vue.computed(function () {
        return Array.isArray(options.value) ? options.value : [];
      });

      var handleUpdate = function handleUpdate(v) {
        emit('update:modelValue', v);
      };

      return {
        options: _options,
        value: value,
        handleUpdate: handleUpdate,
        hasProperty: hasProperty,
        is: is
      };
    },
    mounted: function mounted() {
      this.$emit('fc.el', this.$refs.el);
    }
  });

  function render(_ctx, _cache, $props, $setup, $data, $options) {
    var _component_el_option = vue.resolveComponent("el-option");

    var _component_el_option_group = vue.resolveComponent("el-option-group");

    var _component_el_select = vue.resolveComponent("el-select");

    return vue.openBlock(), vue.createBlock(_component_el_select, vue.mergeProps(_ctx.$attrs, {
      "model-value": _ctx.value,
      "onUpdate:modelValue": _ctx.handleUpdate,
      ref: "el"
    }), {
      "default": vue.withCtx(function () {
        return [(vue.openBlock(true), vue.createElementBlock(vue.Fragment, null, vue.renderList(_ctx.options, function (props, index) {
          return vue.openBlock(), vue.createElementBlock(vue.Fragment, null, [_ctx.hasProperty(props, 'options') ? (vue.openBlock(), vue.createBlock(_component_el_option_group, {
            label: props.label,
            key: "".concat(index, "-").concat(props.label)
          }, {
            "default": vue.withCtx(function () {
              return [(vue.openBlock(true), vue.createElementBlock(vue.Fragment, null, vue.renderList(_ctx.is.trueArray(props.options) ? props.options : [], function (option, optIndex) {
                return vue.openBlock(), vue.createBlock(_component_el_option, vue.mergeProps({
                  key: "".concat(optIndex, "-").concat(option.value)
                }, option), null, 16);
              }), 128))];
            }),
            _: 2
          }, 1032, ["label"])) : (vue.openBlock(), vue.createBlock(_component_el_option, vue.mergeProps(_defineProperty({
            key: 1
          }, "key", "".concat(index, "-").concat(props.value)), props), null, 16))], 64);
        }), 256)), vue.renderSlot(_ctx.$slots, "default")];
      }),
      _: 3
    }, 16, ["model-value", "onUpdate:modelValue"]);
  }

  script.render = render;

  exports["default"] = script;

  Object.defineProperty(exports, '__esModule', { value: true });

}));
