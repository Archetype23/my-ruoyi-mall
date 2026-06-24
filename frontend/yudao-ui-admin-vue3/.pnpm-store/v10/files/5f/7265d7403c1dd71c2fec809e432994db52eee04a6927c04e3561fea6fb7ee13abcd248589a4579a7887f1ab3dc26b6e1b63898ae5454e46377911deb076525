/*!
 * @form-create/component-elm-group v3.2.38
 * (c) 2018-2026 xaboy
 * Github https://github.com/xaboy/form-create with group
 * Released under the MIT License.
 */
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('vue')) :
  typeof define === 'function' && define.amd ? define(['exports', 'vue'], factory) :
  (global = typeof globalThis !== 'undefined' ? globalThis : global || self, factory(global.FcGroup = {}, global.Vue));
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

  function $set(target, field, value) {
    target[field] = value;
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

  function styleInject(css, ref) {
    if ( ref === void 0 ) ref = {};
    var insertAt = ref.insertAt;

    if (!css || typeof document === 'undefined') { return; }

    var head = document.head || document.getElementsByTagName('head')[0];
    var style = document.createElement('style');
    style.type = 'text/css';

    if (insertAt === 'top') {
      if (head.firstChild) {
        head.insertBefore(style, head.firstChild);
      } else {
        head.appendChild(style);
      }
    } else {
      head.appendChild(style);
    }

    if (style.styleSheet) {
      style.styleSheet.cssText = css;
    } else {
      style.appendChild(document.createTextNode(css));
    }
  }

  var css_248z = "._fc-group{display:flex;flex-direction:column;justify-content:center;min-height:38px;width:100%}._fc-group-disabled ._fc-group-add,._fc-group-disabled ._fc-group-btn{cursor:not-allowed}._fc-group-handle{background-color:#fff;border:1px dashed #d9d9d9;border-radius:15px;bottom:-15px;display:flex;flex-direction:row;padding:3px 8px;position:absolute;right:30px}._fc-group-btn{cursor:pointer}._fc-group-idx{align-items:center;background:#eee;border-radius:15px;bottom:-15px;display:flex;font-weight:700;height:30px;justify-content:center;left:10px;position:absolute;width:30px}._fc-group-handle ._fc-group-btn+._fc-group-btn{margin-left:7px}._fc-group-container{border:1px dashed #d9d9d9;border-radius:5px;display:flex;flex-direction:column;margin:5px 5px 25px;padding:20px 20px 25px;position:relative}._fc-group-arrow{height:20px;position:relative;width:20px}._fc-group-arrow:before{border-left:2px solid #999;border-top:2px solid #999;content:\"\";height:9px;left:5px;position:absolute;top:8px;transform:rotate(45deg);width:9px}._fc-group-arrow._fc-group-down{transform:rotate(180deg)}._fc-group-plus-minus{cursor:pointer;height:20px;position:relative;width:20px}._fc-group-plus-minus:after,._fc-group-plus-minus:before{background-color:#409eff;content:\"\";height:2px;left:50%;position:absolute;top:50%;transform:translate(-50%,-50%);width:60%}._fc-group-plus-minus:before{transform:translate(-50%,-50%) rotate(90deg)}._fc-group-plus-minus._fc-group-minus:before{display:none}._fc-group-plus-minus._fc-group-minus:after{background-color:#f56c6c}._fc-group-add{border:1px solid rgba(64,158,255,.5);border-radius:15px;cursor:pointer;height:25px;width:25px}._fc-group-add._fc-group-plus-minus:after,._fc-group-add._fc-group-plus-minus:before{width:50%}._fc-group._fc-group-card{display:flex;flex-direction:column;gap:16px;width:100%}._fc-group._fc-group-card._fc-group-disabled ._fc-group-add,._fc-group._fc-group-card._fc-group-disabled ._fc-group-btn{cursor:not-allowed;opacity:.5}._fc-group._fc-group-card ._fc-group-container{background:#fff;border:1px solid #f0f0f0;border-radius:6px;display:flex;flex-direction:column;margin:0;overflow:hidden;padding:0;position:relative}._fc-group._fc-group-card ._fc-group-header{align-items:center;background:#fafafa;border-bottom:1px solid #f0f0f0;display:flex;justify-content:space-between;padding:16px 20px 12px}._fc-group._fc-group-card ._fc-group-idx{align-items:center;background:#f0f0f0;border:1px solid #e0e0e0;border-radius:6px;color:#666;display:flex;flex-shrink:0;font-size:12px;font-weight:500;height:24px;justify-content:center;margin-right:12px;position:static;width:24px}._fc-group._fc-group-card ._fc-group-title{color:rgba(0,0,0,.85);flex:1;font-size:14px;font-weight:500;line-height:1.5715}._fc-group._fc-group-card ._fc-group-handle{align-items:center;background-color:transparent;border:none;border-radius:0;bottom:auto;display:flex;flex-direction:row;flex-shrink:0;gap:4px;margin-left:12px;padding:0;position:static;right:auto}._fc-group._fc-group-card ._fc-group-btn{align-items:center;background:#fff;border:1px solid #d9d9d9;border-radius:4px;color:rgba(0,0,0,.45);cursor:pointer;display:flex;height:24px;justify-content:center;position:relative;width:24px}._fc-group._fc-group-card ._fc-group-btn:hover{background:#f0f8ff;border-color:#1890ff;color:#1890ff}._fc-group._fc-group-card ._fc-group-btn:active{border-color:#096dd9;color:#096dd9}._fc-group._fc-group-card ._fc-group-arrow{position:relative}._fc-group._fc-group-card ._fc-group-arrow:before{border-left:1px solid;border-top:1px solid;content:\"\";height:5px;left:50%;position:absolute;top:50%;transform:translate(-50%,-50%) rotate(45deg);width:5px}._fc-group._fc-group-card ._fc-group-arrow._fc-group-down{transform:rotate(180deg)}._fc-group._fc-group-card ._fc-group-sort{align-items:center;display:flex;flex-direction:column;height:24px;justify-content:center;position:relative}._fc-group._fc-group-card ._fc-group-sort:after,._fc-group._fc-group-card ._fc-group-sort:before{border-left:1px solid;border-top:1px solid;content:\"\";height:4px;left:50%;position:absolute;transform:translateX(-50%);width:4px}._fc-group._fc-group-card ._fc-group-sort:before{top:6px;transform:translateX(-50%) rotate(45deg)}._fc-group._fc-group-card ._fc-group-sort:after{bottom:6px;transform:translateX(-50%) rotate(225deg)}._fc-group._fc-group-card ._fc-group-sort-down,._fc-group._fc-group-card ._fc-group-sort-up{cursor:pointer;height:12px;left:0;position:absolute;right:0;z-index:1}._fc-group._fc-group-card ._fc-group-sort-up{top:0}._fc-group._fc-group-card ._fc-group-sort-down{bottom:0}._fc-group._fc-group-card ._fc-group-sort-down:hover,._fc-group._fc-group-card ._fc-group-sort-up:hover{background:rgba(24,144,255,.1)}._fc-group._fc-group-card ._fc-group-plus-minus{background:#409eff;border-color:#409eff;color:#fff;height:24px;position:relative;width:24px}._fc-group._fc-group-card ._fc-group-plus-minus:hover{background:#66b1ff;border-color:#66b1ff;color:#fff}._fc-group._fc-group-card ._fc-group-plus-minus:after,._fc-group._fc-group-card ._fc-group-plus-minus:before{background-color:currentColor;content:\"\";height:1px;left:50%;position:absolute;top:50%;transform:translate(-50%,-50%);width:8px}._fc-group._fc-group-card ._fc-group-plus-minus:before{transform:translate(-50%,-50%) rotate(90deg)}._fc-group._fc-group-card ._fc-group-plus-minus._fc-group-minus{background:#f56c6c;border-color:#f56c6c}._fc-group._fc-group-card ._fc-group-plus-minus._fc-group-minus:hover{background:#f78989;border-color:#f78989}._fc-group._fc-group-card ._fc-group-plus-minus._fc-group-minus:before{display:none}._fc-group._fc-group-card ._fc-group-content{padding:20px}._fc-group._fc-group-card ._fc-group-add{align-items:center;background:#fff;border:1px solid #d9d9d9;border-radius:4px;color:rgba(0,0,0,.45);cursor:pointer;display:flex;height:24px;justify-content:center;position:relative;width:24px}._fc-group._fc-group-card ._fc-group-add:hover{background:#f0f8ff;border-color:#409eff;color:#409eff}._fc-group._fc-group-card ._fc-group-add._fc-group-plus-minus{background:#409eff;border-color:#409eff;color:#fff;height:24px;width:24px}._fc-group._fc-group-card ._fc-group-add._fc-group-plus-minus:hover{background:#66b1ff;border-color:#66b1ff;color:#fff}._fc-group._fc-group-card ._fc-group-add._fc-group-plus-minus:after,._fc-group._fc-group-card ._fc-group-add._fc-group-plus-minus:before{background-color:currentColor;content:\"\";height:1px;left:50%;position:absolute;top:50%;transform:translate(-50%,-50%);width:8px}._fc-group._fc-group-card ._fc-group-add._fc-group-plus-minus:before{transform:translate(-50%,-50%) rotate(90deg)}._fc-group._fc-group-card ._fc-group-empty{color:rgba(0,0,0,.45);font-size:14px;padding:40px 20px;text-align:center}@media (max-width:768px){._fc-group._fc-group-card ._fc-group-container{border-left:none;border-radius:0;border-right:none;margin:0 -8px}._fc-group._fc-group-card ._fc-group-header{padding:12px 16px 8px}._fc-group._fc-group-card ._fc-group-content{padding:16px}._fc-group._fc-group-card ._fc-group-handle{gap:2px}._fc-group._fc-group-card ._fc-group-btn{height:22px;width:22px}}";
  styleInject(css_248z);

  function isPromise(value) {
    return value && _typeof(value) === 'object' && typeof value.then === 'function';
  }

  function toPromise(value) {
    if (isPromise(value)) {
      return value;
    }

    return Promise.resolve(value);
  }

  var NAME = 'fcGroup';
  var Group = vue.defineComponent({
    name: NAME,
    props: {
      field: String,
      rule: Array,
      expand: Number,
      options: Object,
      button: {
        type: Boolean,
        "default": true
      },
      max: {
        type: Number,
        "default": 0
      },
      min: {
        type: Number,
        "default": 0
      },
      modelValue: {
        type: Array,
        "default": function _default() {
          return [];
        }
      },
      defaultValue: Object,
      sortBtn: {
        type: Boolean,
        "default": true
      },
      disabled: {
        type: Boolean,
        "default": false
      },
      syncDisabled: {
        type: Boolean,
        "default": true
      },
      title: {
        type: [String, Function],
        "default": null
      },
      type: {
        type: String,
        "default": 'default'
      },
      onBeforeRemove: {
        type: Function,
        "default": function _default() {}
      },
      onBeforeAdd: {
        type: Function,
        "default": function _default() {}
      },
      formCreateInject: Object,
      parse: Function
    },
    data: function data() {
      return {
        len: 0,
        cacheRule: {},
        cacheValue: {},
        sort: [],
        form: vue.markRaw(this.formCreateInject.form.$form())
      };
    },
    emits: ['update:modelValue', 'change', 'itemMounted', 'remove', 'add'],
    watch: {
      rule: {
        handler: function handler(n, o) {
          var _this = this;

          Object.keys(this.cacheRule).forEach(function (v) {
            var item = _this.cacheRule[v];

            if (item.$f) {
              var val = item.$f.formData();

              if (n === o) {
                item.$f.deferSyncValue(function () {
                  deepExtend(item.rule, n);
                  item.$f.setValue(val);
                }, true);
              } else {
                var _val = item.$f.formData();

                item.$f.once('reloading', function () {
                  item.$f.setValue(_val);
                });
                item.rule = deepCopy(n);
              }
            }
          });
        },
        deep: true
      },
      expand: function expand(n) {
        var d = n - this.modelValue.length;

        if (d > 0) {
          this.expandRule(d);
        }
      },
      modelValue: {
        handler: function handler(n) {
          var _this2 = this;

          n = n || [];
          var keys = this.sort,
              total = keys.length,
              len = total - n.length;

          if (len < 0) {
            for (var i = len; i < 0; i++) {
              this.addRule(n.length + i, true);
            }

            for (var _i = 0; _i < total; _i++) {
              this.setValue(keys[_i], n[_i]);
            }
          } else {
            if (len > 0) {
              for (var _i2 = 0; _i2 < len; _i2++) {
                this.removeRule(keys[total - _i2 - 1]);
              }
            }

            n.forEach(function (val, i) {
              _this2.setValue(keys[i], n[i]);
            });
          }
        },
        deep: true
      }
    },
    methods: {
      _value: function _value(v) {
        return v && hasProperty(v, this.field) ? v[this.field] : v;
      },
      cache: function cache(k, val) {
        this.cacheValue[k] = JSON.stringify(val);
      },
      input: function input(value) {
        this.$emit('update:modelValue', value);
        this.$emit('change', value);
      },
      formData: function formData(key, _formData) {
        var _this3 = this;

        var cacheRule = this.cacheRule;
        var keys = this.sort;

        if (keys.filter(function (k) {
          return cacheRule[k] && cacheRule[k].$f;
        }).length !== keys.length) {
          return;
        }

        var value = keys.map(function (k) {
          var data = key === k ? _formData : _objectSpread2({}, _this3.cacheRule[k].$f.form);
          var value = _this3.field ? data[_this3.field] || null : data;

          _this3.cache(k, value);

          return value;
        });
        this.input(value);
      },
      setValue: function setValue(key, value) {
        var field = this.field;

        if (field) {
          value = _defineProperty({}, field, this._value(value));
        }

        if (this.cacheValue[key] === JSON.stringify(field ? value[field] : value)) {
          return;
        }

        this.cacheRule[key].$f && this.cacheRule[key].$f.coverValue(value);
        this.cache(key, value);
      },
      addRule: function addRule(i, emit) {
        var _this4 = this;

        var rule = this.formCreateInject.form.copyRules(this.rule || []);
        var options = this.options ? _objectSpread2({}, this.options) : {
          submitBtn: false,
          resetBtn: false
        };

        if (this.defaultValue) {
          if (!options.formData) options.formData = {};
          var defVal = deepCopy(this.defaultValue);
          extend(options.formData, this.field ? _defineProperty({}, this.field, defVal) : defVal);
        }

        this.parse && this.parse({
          rule: rule,
          options: options,
          index: this.sort.length
        });
        this.cacheRule[++this.len] = {
          rule: rule,
          options: options
        };
        this.sort = Object.keys(this.cacheRule);

        if (emit) {
          vue.nextTick(function () {
            return _this4.$emit('add', rule, Object.keys(_this4.cacheRule).length - 1);
          });
        }
      },
      add$f: function add$f(i, key, $f) {
        var _this5 = this;

        this.cacheRule[key].$f = $f;
        vue.nextTick(function () {
          _this5.$emit('itemMounted', $f, Object.keys(_this5.cacheRule).indexOf(key));
        });
      },
      removeRule: function removeRule(key, emit) {
        var _this6 = this;

        var index = Object.keys(this.cacheRule).indexOf(key);
        delete this.cacheRule[key];
        delete this.cacheValue[key];
        this.sort = Object.keys(this.cacheRule);

        if (emit) {
          vue.nextTick(function () {
            return _this6.$emit('remove', index);
          });
        }
      },
      add: function add(i) {
        if (this.disabled || false === this.onBeforeAdd(this.modelValue)) {
          return;
        }

        var value = _toConsumableArray(this.modelValue);

        value.push(this.defaultValue ? deepCopy(this.defaultValue) : this.field ? null : {});
        this.input(value);
      },
      del: function del(index, key) {
        var _this7 = this;

        if (this.disabled) {
          return;
        }

        var del = function del() {
          _this7.removeRule(key, true);

          var value = _toConsumableArray(_this7.modelValue);

          value.splice(index, 1);

          _this7.input(value);
        };

        toPromise(this.onBeforeRemove(this.modelValue, index)).then(function (res) {
          if (false !== res) {
            del();
          }
        });
      },
      addIcon: function addIcon(key) {
        return vue.createVNode("div", {
          "class": "_fc-group-btn _fc-group-plus-minus",
          "onClick": this.add
        }, null);
      },
      delIcon: function delIcon(index, key) {
        var _this8 = this;

        return vue.createVNode("div", {
          "class": "_fc-group-btn _fc-group-plus-minus _fc-group-minus",
          "onClick": function onClick() {
            return _this8.del(index, key);
          }
        }, null);
      },
      sortUpIcon: function sortUpIcon(index) {
        var _this9 = this;

        return vue.createVNode("div", {
          "class": "_fc-group-btn _fc-group-arrow _fc-group-up",
          "onClick": function onClick() {
            return _this9.changeSort(index, -1);
          }
        }, null);
      },
      sortDownIcon: function sortDownIcon(index) {
        var _this10 = this;

        return vue.createVNode("div", {
          "class": "_fc-group-btn _fc-group-arrow _fc-group-down",
          "onClick": function onClick() {
            return _this10.changeSort(index, 1);
          }
        }, null);
      },
      changeSort: function changeSort(index, sort) {
        var _this11 = this;

        var a = this.sort[index];
        this.sort[index] = this.sort[index + sort];
        this.sort[index + sort] = a;
        this.formCreateInject.subForm(this.sort.map(function (k) {
          return _this11.cacheRule[k].$f;
        }));
        this.formData(0);
      },
      sortIcon: function sortIcon(index, total) {
        var _this12 = this;

        var canMoveUp = index > 0;
        var canMoveDown = index < total - 1;
        var btn = [];

        if (!canMoveUp && !canMoveDown) {
          return btn;
        }

        if (this.type === 'card' && canMoveUp && canMoveDown) {
          // 显示合并的上下箭头按钮
          btn.push(vue.createVNode("div", {
            "class": "_fc-group-btn _fc-group-sort"
          }, [vue.createVNode("div", {
            "class": " _fc-group-sort-up",
            "onClick": function onClick() {
              return _this12.changeSort(index, -1);
            }
          }, null), vue.createVNode("div", {
            "class": " _fc-group-sort-down",
            "onClick": function onClick() {
              return _this12.changeSort(index, 1);
            }
          }, null)]));
          return btn;
        }

        if (canMoveUp) {
          btn.push(this.sortUpIcon(index));
        }

        if (canMoveDown) {
          btn.push(this.sortDownIcon(index));
        }

        return btn;
      },
      makeIcon: function makeIcon(total, index, key) {
        var _this13 = this;

        if (this.$slots.button) {
          return this.$slots.button({
            total: total,
            index: index,
            vm: this,
            key: key,
            del: function del() {
              return _this13.del(index, key);
            },
            add: this.add
          });
        }

        var btn = [];

        if ((!this.max || total < this.max) && total === index + 1) {
          btn.push(this.addIcon(key));
        }

        if (total > this.min) {
          btn.push(this.delIcon(index, key));
        }

        if (this.sortBtn) {
          var sortBtn = this.sortIcon(index, total);

          if (sortBtn) {
            btn.push.apply(btn, _toConsumableArray(sortBtn));
          }
        }

        return btn;
      },
      expandRule: function expandRule(n) {
        for (var i = 0; i < n; i++) {
          this.modelValue.push(this.field ? null : {});
        }

        this.input(_toConsumableArray(this.modelValue));
      },
      getTitle: function getTitle(index, key) {
        if (typeof this.title === 'function') {
          return this.title(index, this.modelValue[index], key);
        }

        if (typeof this.title === 'string') {
          return this.title.replace('{index}', index + 1);
        }

        return false;
      }
    },
    created: function created() {
      var d = (this.expand || 0) - this.modelValue.length;

      for (var i = 0; i < this.modelValue.length; i++) {
        this.addRule(i);
      }

      if (d > 0) {
        this.expandRule(d);
      }
    },
    render: function render() {
      var _this14 = this;

      var keys = this.sort;
      var button = this.button;
      var Type = this.form;
      var disabled = this.disabled;
      var isCardType = this.type === 'card';
      var children = keys.length === 0 ? this.$slots["default"] ? this.$slots["default"]({
        vm: this,
        add: this.add
      }) : vue.createVNode("div", {
        "key": 'a_def',
        "class": "_fc-group-plus-minus _fc-group-add fc-clock",
        "onClick": this.add
      }, null) : keys.map(function (key, index) {
        var _this14$cacheRule$key = _this14.cacheRule[key],
            rule = _this14$cacheRule$key.rule,
            options = _this14$cacheRule$key.options;
        var btn = button && !disabled ? _this14.makeIcon(keys.length, index, key) : [];

        var title = _this14.getTitle(index, key);

        if (isCardType) {
          return vue.createVNode("div", {
            "class": "_fc-group-container",
            "key": key
          }, [vue.createVNode("div", {
            "class": "_fc-group-header"
          }, [title === false ? vue.createVNode("div", {
            "class": "_fc-group-idx"
          }, [index + 1]) : null, title !== false ? vue.createVNode("div", {
            "class": "_fc-group-title"
          }, [title]) : null, vue.createVNode("div", {
            "class": "_fc-group-handle fc-clock"
          }, [btn.length ? btn : null])]), vue.createVNode("div", {
            "class": "_fc-group-content"
          }, [vue.createVNode(Type, vue.mergeProps({
            "key": key
          }, _objectSpread2(_objectSpread2({}, _this14.$attrs), {}, {
            disabled: disabled,
            'onUpdate:modelValue': function onUpdateModelValue(formData) {
              return _this14.formData(key, formData);
            },
            'onUpdate:api': function onUpdateApi($f) {
              return _this14.add$f(index, key, $f);
            },
            inFor: true,
            modelValue: _this14.field ? _defineProperty({}, _this14.field, _this14._value(_this14.modelValue[index])) : _this14.modelValue[index],
            rule: rule,
            option: options,
            extendOption: true
          })), null)])]);
        } else {
          return vue.createVNode("div", {
            "class": "_fc-group-container",
            "key": key
          }, [vue.createVNode(Type, vue.mergeProps({
            "key": key
          }, _objectSpread2(_objectSpread2({}, _this14.$attrs), {}, {
            disabled: disabled,
            'onUpdate:modelValue': function onUpdateModelValue(formData) {
              return _this14.formData(key, formData);
            },
            'onUpdate:api': function onUpdateApi($f) {
              return _this14.add$f(index, key, $f);
            },
            inFor: true,
            modelValue: _this14.field ? _defineProperty({}, _this14.field, _this14._value(_this14.modelValue[index])) : _this14.modelValue[index],
            rule: rule,
            option: options,
            extendOption: true
          })), null), vue.createVNode("div", {
            "class": "_fc-group-idx"
          }, [index + 1]), btn.length ? vue.createVNode("div", {
            "class": "_fc-group-handle fc-clock"
          }, [btn]) : null]);
        }
      });
      return vue.createVNode("div", {
        "key": 'con',
        "class": '_fc-group ' + (disabled ? '_fc-group-disabled' : '') + (isCardType ? ' _fc-group-card' : '')
      }, [children]);
    }
  });

  exports["default"] = Group;

  Object.defineProperty(exports, '__esModule', { value: true });

}));
