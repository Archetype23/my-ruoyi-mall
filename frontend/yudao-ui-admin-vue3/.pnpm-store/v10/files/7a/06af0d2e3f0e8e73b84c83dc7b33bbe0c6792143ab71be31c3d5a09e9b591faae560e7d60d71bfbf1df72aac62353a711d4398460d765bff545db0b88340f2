/*!
 * @form-create/component-subform v3.2.34
 * (c) 2018-2025 xaboy
 * Github https://github.com/xaboy/form-create with subform
 * Released under the MIT License.
 */
(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('vue')) :
    typeof define === 'function' && define.amd ? define(['exports', 'vue'], factory) :
    (global = typeof globalThis !== 'undefined' ? globalThis : global || self, factory(global.FcSubForm = {}, global.Vue));
})(this, (function (exports, vue) { 'use strict';

    var NAME = 'fcSubForm';
    var Sub = vue.defineComponent({
      name: NAME,
      props: {
        rule: Array,
        options: {
          type: Object,
          "default": function _default() {
            return vue.reactive({
              submitBtn: false,
              resetBtn: false
            });
          }
        },
        modelValue: {
          type: Object,
          "default": function _default() {
            return {};
          }
        },
        disabled: {
          type: Boolean,
          "default": false
        },
        syncDisabled: {
          type: Boolean,
          "default": true
        },
        formCreateInject: Object
      },
      data: function data() {
        return {
          cacheValue: {},
          subApi: {},
          form: vue.markRaw(this.formCreateInject.form.$form())
        };
      },
      emits: ['fc:subform', 'update:modelValue', 'change', 'itemMounted'],
      watch: {
        modelValue: function modelValue(n) {
          this.setValue(n);
        }
      },
      methods: {
        formData: function formData(value) {
          this.cacheValue = JSON.stringify(value);
          this.$emit('update:modelValue', value);
          this.$emit('change', value);
        },
        setValue: function setValue(value) {
          var str = JSON.stringify(value);

          if (this.cacheValue === str) {
            return;
          }

          this.cacheValue = str;
          this.subApi.coverValue(value || {});
        },
        add$f: function add$f(api) {
          var _this = this;

          this.subApi = api;
          vue.nextTick(function () {
            _this.$emit('itemMounted', api);
          });
        }
      },
      render: function render() {
        var Type = this.form;
        return vue.createVNode(Type, {
          "disabled": this.disabled,
          "onUpdate:modelValue": this.formData,
          "modelValue": this.modelValue,
          "onUpdate:api": this.add$f,
          "rule": this.rule,
          "option": this.options,
          "extendOption": true
        }, null);
      }
    });

    exports["default"] = Sub;

    Object.defineProperty(exports, '__esModule', { value: true });

}));
