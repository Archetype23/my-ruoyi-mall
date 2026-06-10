/*!
 * @form-create/component-elm-select v3.2.37
 * (c) 2018-2026 xaboy
 * Github https://github.com/xaboy/form-create with select
 * Released under the MIT License.
 */
import { defineComponent, toRef, computed, resolveComponent, openBlock, createBlock, mergeProps, withCtx, createElementBlock, Fragment, renderList, renderSlot } from 'vue';

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
var script = defineComponent({
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
    var options = toRef(props.formCreateInject, 'options', []);
    var value = toRef(props, 'modelValue');

    var _options = computed(function () {
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
  var _component_el_option = resolveComponent("el-option");

  var _component_el_option_group = resolveComponent("el-option-group");

  var _component_el_select = resolveComponent("el-select");

  return openBlock(), createBlock(_component_el_select, mergeProps(_ctx.$attrs, {
    "model-value": _ctx.value,
    "onUpdate:modelValue": _ctx.handleUpdate,
    ref: "el"
  }), {
    "default": withCtx(function () {
      return [(openBlock(true), createElementBlock(Fragment, null, renderList(_ctx.options, function (props, index) {
        return openBlock(), createElementBlock(Fragment, null, [_ctx.hasProperty(props, 'options') ? (openBlock(), createBlock(_component_el_option_group, {
          label: props.label,
          key: "".concat(index, "-").concat(props.label)
        }, {
          "default": withCtx(function () {
            return [(openBlock(true), createElementBlock(Fragment, null, renderList(_ctx.is.trueArray(props.options) ? props.options : [], function (option, optIndex) {
              return openBlock(), createBlock(_component_el_option, mergeProps({
                key: "".concat(optIndex, "-").concat(option.value)
              }, option), null, 16);
            }), 128))];
          }),
          _: 2
        }, 1032, ["label"])) : (openBlock(), createBlock(_component_el_option, mergeProps(_defineProperty({
          key: 1
        }, "key", "".concat(index, "-").concat(props.value)), props), null, 16))], 64);
      }), 256)), renderSlot(_ctx.$slots, "default")];
    }),
    _: 3
  }, 16, ["model-value", "onUpdate:modelValue"]);
}

script.render = render;

export { script as default };
