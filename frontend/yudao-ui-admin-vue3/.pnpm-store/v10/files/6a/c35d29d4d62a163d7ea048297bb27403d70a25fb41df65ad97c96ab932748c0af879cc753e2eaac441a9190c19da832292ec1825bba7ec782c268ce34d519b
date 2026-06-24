import {computed, defineComponent, ref, resolveComponent, toRef, watch} from 'vue';
import getSlot from '@form-create/utils/lib/slot';

const NAME = 'fcRadio';

export default defineComponent({
    name: NAME,
    inheritAttrs: false,
    props: {
        formCreateInject: Object,
        modelValue: {
            type: [String, Number, Boolean],
            default: ''
        },
        options: Array,
        type: String,
        input: Boolean,
        inputValue: String,
    },
    emits: ['update:modelValue', 'fc.el'],
    setup(props, _) {
        const options = toRef(props.formCreateInject, 'options', []);
        const opt = toRef(props, 'options');
        const value = toRef(props, 'modelValue');
        const inputValue = toRef(props, 'inputValue', '');
        const customValue = ref(inputValue.value);
        const input = toRef(props, 'input', false);
        watch(inputValue, (n) => {
            if (!input.value) {
                customValue.value = n;
                return undefined;
            }
            updateCustomValue(n);
        })

        const _options = computed(() => {
            let arr = options.value || [];
            if (opt.value) {
                arr = opt.value || [];
            }
            return Array.isArray(arr) ? arr : [];
        });

        watch(value, (n) => {
            let flag = false;
            if (!inputValue.value && n != null && input.value) {
                flag = _options.value.map(item => {
                    return item.value;
                }).indexOf(n) === -1;
            }
            if (flag) {
                customValue.value = n;
            }
        }, {immediate: true});

        const onInput = (n) => {
            _.emit('update:modelValue', n);
        }
        const updateCustomValue = (n) => {
            const o = customValue.value;
            customValue.value = n;
            if (value.value === o) {
                onInput(n);
            }
        }
        return {
            options: _options,
            value,
            onInput,
            updateCustomValue,
            customValue,
            makeInput(Type) {
                if (!input.value) {
                    return undefined;
                }
                return <Type checked={false} value={customValue.value || undefined}
                    label={customValue.value || undefined}>
                    <ElInput size="small" modelValue={customValue.value}
                        onUpdate:modelValue={updateCustomValue}></ElInput>
                </Type>
            },
        }
    },
    render() {
        const name = this.type === 'button' ? 'ElRadioButton' : 'ElRadio';
        const Type = resolveComponent(name);
        return <ElRadioGroup {...this.$attrs} modelValue={this.value} v-slots={getSlot(this.$slots, ['default'])}
            onUpdate:modelValue={this.onInput} ref="el">{this.options.map((opt, index) => {
                const props = {...opt};
                const value = props.value;
                const label = props.label;
                delete props.value;
                delete props.label;
                return <Type {...props} label={value} value={value}
                    key={name + index + '-' + value}>{label || value || ''}</Type>
            })}{this.$slots.default?.()}{this.makeInput(Type)}</ElRadioGroup>
    },
    mounted() {
        this.$emit('fc.el', this.$refs.el);
    }
});
