/*!
 * @form-create/component-subform v3.2.34
 * (c) 2018-2025 xaboy
 * Github https://github.com/xaboy/form-create with subform
 * Released under the MIT License.
 */
import{defineComponent as e,reactive as t,markRaw as a,nextTick as i,createVNode as o}from"vue";var u=e({name:"fcSubForm",props:{rule:Array,options:{type:Object,default:function(){return t({submitBtn:!1,resetBtn:!1})}},modelValue:{type:Object,default:function(){return{}}},disabled:{type:Boolean,default:!1},syncDisabled:{type:Boolean,default:!0},formCreateInject:Object},data:function(){return{cacheValue:{},subApi:{},form:a(this.formCreateInject.form.$form())}},emits:["fc:subform","update:modelValue","change","itemMounted"],watch:{modelValue:function(e){this.setValue(e)}},methods:{formData:function(e){this.cacheValue=JSON.stringify(e),this.$emit("update:modelValue",e),this.$emit("change",e)},setValue:function(e){var t=JSON.stringify(e);this.cacheValue!==t&&(this.cacheValue=t,this.subApi.coverValue(e||{}))},add$f:function(e){var t=this;this.subApi=e,i((function(){t.$emit("itemMounted",e)}))}},render:function(){var e=this.form;return o(e,{disabled:this.disabled,"onUpdate:modelValue":this.formData,modelValue:this.modelValue,"onUpdate:api":this.add$f,rule:this.rule,option:this.options,extendOption:!0},null)}});export{u as default};
